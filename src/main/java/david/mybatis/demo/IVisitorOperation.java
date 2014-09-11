package david.mybatis.demo;

import java.util.List;

import david.mybatis.model.BasicQueryArgs;
import david.mybatis.model.PagenateArgs;
import david.mybatis.model.Visitor;
import david.mybatis.model.VisitorWithRn;

public interface IVisitorOperation {
	/*
	 * 添加访问者
	 */
	public int add(Visitor visitor);
	
	/*
	 * 删除访问者
	 */
	public int delete(int id);
	
	/*
	 * 更新访问者
	 */
	public int update(Visitor visitor);
	
	/*
	 * 查询访问者
	 */
	public Visitor query(int id);
	
	/*
	 * 查询List
	 */
	public List<Visitor> getList();
	
	/*
	 * 分页查询List
	 */
	public List<Visitor> getListByPagenate(PagenateArgs args);
	
	/*
	 * 分页查询List（包含Rownum）
	 */
	public List<VisitorWithRn> getListByPagenateWithRn(PagenateArgs args);
	
	/*
	 * 基础查询
	 */
	public Visitor basicQuery(int id);
	
	/*
	 * 动态条件查询(choose,when)实例
	 */
	public List<Visitor> getListChooseWhenDemo(BasicQueryArgs args);
	
	/*
	 * 动态条件查询(where,if)实例
	 */
	public List<Visitor> getListWhereDemo(BasicQueryArgs args);
	
	/*
	 * 动态查询(foreach)实例
	 */
	public List<Visitor> getListForeachDemo(List<Integer> ids);
	
}
