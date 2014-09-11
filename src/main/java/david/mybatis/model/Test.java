package david.mybatis.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import david.mybatis.demo.IVisitorOperation;
import david.mybatis.demo.MybatisUtils;

public class Test {

	public static void queryVisitorListWithPagenate(int pageIndex, int pageSize, String orderField, String orderDire) {
	    PagenateArgs args = new PagenateArgs(pageIndex, pageSize, orderField, orderDire);
	    SqlSession session = MybatisUtils.getSqlSession();
	    IVisitorOperation vOperation = session.getMapper(IVisitorOperation.class);
	    List<Visitor> visitors = vOperation.getListByPagenate(args);
	    for (Visitor visitor : visitors) {
	        System.out.println(visitor);
	    }
	    MybatisUtils.closeSession(session);
	    MybatisUtils.showMessages(CRUD_Enum.List, visitors.size());
	}
}
