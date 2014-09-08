package com.immutable.alias.utils;

/**
 * a generator for create java pojo class according to connected db tables 
 * usage: need input command line params as tableName,(NOTE:  oracle's tableName 
 * is always uppercase! ) 
 * example1: >java Table2Bean TEST    example2: >java Table2Bean T%
 * @author Charles lei
 */
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Table2Bean {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		List<String> tables = new ArrayList<String>();// 表名数组
		Map<String,String> fields = new HashMap<String,String>();// 当前表字段数组
		//List<String> fieldType = new ArrayList<String>();// 当前表字段类型数组

		// 连数据库
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "scott",
					"tiger");// test
			System.out.println("db connected.");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("error in connect db.");
		}
		// 获得用户所有的表结构信息
		String sql = "select  object_name from dba_objects "
				+ "where object_name   like '"+args[0]+"' "
				+ "and object_type='TABLE' " + "and owner='RHPC' ";// test
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				tables.add(rs.getString(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("error in query data :" + sql);

		}

		// 循环创建tableNamebean.java
		sql = "select  COLUMN_NAME , DATA_TYPE, DATA_LENGTH, DATA_SCALE"
				+ " from dba_tab_columns a " + " where table_name =?  ";
		try {
			pstmt = conn.prepareStatement(sql);
			String tName = null;
			String f,v;
			for (Iterator it = tables.iterator(); it.hasNext();) {
				tName = it.next().toString();
				pstmt.setString(1, tName);
				//System.out.println("create file:" + sql);
				
				

				rs = pstmt.executeQuery();
				fields.clear();
				
				while (rs.next()) {
//					String.format(format, args)"sdfsdf".format(format, args)
					
					f =String.format( "%10s",rs.getString(1) )+"; //"+
					String.format( "%10s",rs.getString(2) )+","+
					   rs.getString(3)+","+rs.getString(4);
					v ="NULL";
					if (rs.getString(2).equals("NUMBER") && rs.getInt(4) == 0) {
						v= "int";
					} 
					if (rs.getString(2).equals("NUMBER")
							&& rs.getInt(4) > 0) {
						v="float";
					} 
					if (rs.getString(2).equals("FLOAT")) {
						v="float";
					}
                    if (rs.getString(2).equals("VARCHAR2")) {
						v= "String";
					}
                    v = String.format("%10s", v);
					fields.put(f, v);
				}
//				 建文件,打开
				tName = tName.substring(0, 1).toUpperCase()
						+ tName.substring(1).toLowerCase();
				FileWriter fw = new FileWriter(".\\beans\\" + tName + ".java");

				// 写文件头
				System.out.println("create file:" + tName+".java");
				
				//fw.write("/* POJO Class created by T2B tools 1.1,   */");
				fw.write("\n");
				fw.write("\n");
				fw.write("package com.tianrui.ldrk.bean;");
				fw.write("\n");
				fw.write("\n");
				fw.write("/** ");
				fw.write("POJO Class '"+tName);				
				fw.write("' , created by T2B tools 1.1");
				fw.write("*/ ");
				fw.write("\n");
				fw.write("\n");
				fw.write("\n");
				fw.write("public class " + tName + "{");
				fw.write("\n");
				fw.write("\n");
				
				// 写字段
				fw.write("//Parameter declare block");
				fw.write("\n");
				for (Iterator ittt= fields.keySet().iterator();ittt.hasNext();){
					f= ittt.next().toString();
					v= fields.get(f);	 
					
					fw.write("   private  "+v +" "+  f.toLowerCase()  );
					fw.write("\n");
				} 

				// 写setter,getter方法
				fw.write("\n");
				fw.write("\n");
				fw.write("//Function implements block");
				for (Iterator itt= fields.keySet().iterator();itt.hasNext();){
					f= itt.next().toString();
					v= fields.get(f).trim();
					f = f.trim();
					f = f.substring(0,1)+ f.substring(1,f.indexOf(";")).toLowerCase();
							
					
					fw.write("\n");
					fw.write("   public void  set"+ f + "("+v+" "+f.toLowerCase()+"){");
					fw.write("\n");
					fw.write("      this."+f.toLowerCase()+" = "+f.toLowerCase()+";");
					fw.write("\n");
					fw.write("   }");
					fw.write("\n");
					
					fw.write("\n");
					fw.write("   public "+v +"  get"+ f + "(){");
					fw.write("\n");
					fw.write("      return  this."+f.toLowerCase() +";");
					fw.write("\n");
					fw.write("   }");
					fw.write("\n");
					
				} 
				// 类结束 写 "}"
				fw.write("}");
				// 关闭文件
				fw.flush();
				fw.close();
			}//for
			

			
		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			try{
				conn.close();	
			}catch(SQLException e){}
			
		}

//		 结束,释放资源 in finally block
		System.out.println("OK.");

	}

}
