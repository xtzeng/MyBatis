package david.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import david.mybatis.demo.IVisitorOperation;
import david.mybatis.demo.MybatisUtils;
import david.mybatis.model.Visitor;

public class BasicQueryVistorTest {

	@Test
	public void testBasic(){
		testBasicQuery(2);
	}
	
	@Test 
	public void testInface() {
		testBasicQueryByInterfaceWay(2);
	}
	
	
	 public static void testBasicQuery(int id) {
	        SqlSession session = MybatisUtils.getSqlSession();
	        try {
	            Visitor visitor = (Visitor) session.selectOne("david.mybatis.demo.IVisitorOperation.basicQuery", id);
	            MybatisUtils.closeSession(session);
	            System.out.println(visitor);
	        } catch (Exception e) {
	            // TODO: handle exception
	        }
	 }
	 
	 
	 public static void testBasicQueryByInterfaceWay(int id) {
	        SqlSession session = MybatisUtils.getSqlSession();
	        try {
	            IVisitorOperation vOperation = session.getMapper(IVisitorOperation.class);
	            Visitor visitor = vOperation.basicQuery(id);
	            MybatisUtils.closeSession(session);
	            System.out.println(visitor);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
}
