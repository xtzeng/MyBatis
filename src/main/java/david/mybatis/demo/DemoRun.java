package david.mybatis.demo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import david.mybatis.model.BasicQueryArgs;
import david.mybatis.model.CRUD_Enum;
import david.mybatis.model.Channel;
import david.mybatis.model.PagenateArgs;
import david.mybatis.model.Visitor;
import david.mybatis.model.VisitorWithRn;
import david.mybatis.model.Website;

public class DemoRun {
	
	/*
	 * 动态查询foreach实例
	 */
	public static void getListForeachDemo(List<Integer> ids) {
		SqlSession session = MybatisUtils.getSqlSession();
		IVisitorOperation vOperation = session.getMapper(IVisitorOperation.class);
		List<Visitor> ls = vOperation.getListForeachDemo(ids);
		for (Visitor visitor : ls) {
			System.out.println(visitor);
		}
	}
	
	/*
	 * 动态查询where if实例
	 */
	public static void getListWhereCondition(int id, String name, Date createTime) {
		name = name == "" ? null : name;
		SqlSession session = MybatisUtils.getSqlSession();
		BasicQueryArgs args = new BasicQueryArgs(id, name, createTime);
		IVisitorOperation vOperation = session.getMapper(IVisitorOperation.class);
		List<Visitor> ls = vOperation.getListWhereDemo(args);
		if (ls.size() == 0)
			System.out.println("查无匹配！");
		else {
			for (Visitor visitor : ls) {
				System.out.println(visitor);
			}
		}
	}

	/*
	 * 动态查询choose when实例
	 */
	public static void getListChooseWhenDemo(int id, String name, Date createTime) {
		name = name == "" ? null : name;
		SqlSession session = MybatisUtils.getSqlSession();
		BasicQueryArgs args = new BasicQueryArgs(id, name, createTime);
		IVisitorOperation vOperation = session.getMapper(IVisitorOperation.class);
		List<Visitor> ls = vOperation.getListChooseWhenDemo(args);
		if (ls.size() == 0)
			System.out.println("查无匹配！");
		else {
			for (Visitor visitor : ls) {
				System.out.println(visitor);
			}
		}
	}

	public static void testBasicQuery(int id) {
		SqlSession session = MybatisUtils.getSqlSession();
		try {
			/*
			 * 此处的david.mybatis.demo.IVisitorOperation.
			 * basicQuery必须和下图中配置里面的namespace对应
			 */
			Visitor visitor = (Visitor) session.selectOne("david.mybatis.demo.IVisitorOperation.basicQuery", id);
			MybatisUtils.closeSession(session);
			System.out.println(visitor);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/*
	 * 批量添加访问者记录
	 */
	public static void addVisitors() {
		SqlSession session = MybatisUtils.getSqlSession();
		List<Visitor> visitors = Arrays.asList(new Visitor[] { new Visitor("mongodb", "mongodb@gmail.com"),
				new Visitor("redis", "redis@gmail.com"), new Visitor("memcached", "memcached@gmail.com"),
				new Visitor("CouchDB", "CouchDB@gmail.com"), new Visitor("HBase", "HBase@gmail.com"),
				new Visitor("Bigtable", "Bigtable@gmail.com"), new Visitor("Hive", "Hive@gmail.com"),
				new Visitor("MapReduce", "MapReduce@gmail.com"), });

		for (Visitor visitor : visitors) {
			addVisitor(visitor, session);
		}
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.List, visitors.size());
	}

	/*
	 * 添加访问者信息
	 */
	@SuppressWarnings("unused")
	private static void addVisitor(Visitor visitor, SqlSession session) {
		if (session == null)
			session = MybatisUtils.getSqlSession();
		IVisitorOperation vOperation = session.getMapper(IVisitorOperation.class);
		int recordCount = vOperation.add(visitor);
		session.commit();
		if (session == null)
			MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.Add, recordCount);
	}

	/*
	 * 重载添加访问者
	 */
	public static void addVisitor(Visitor visitor) {
		addVisitor(visitor, null);
	}

	/*
	 * 删除访问者信息
	 */
	public static void deleteVisitor(int id) {
		SqlSession session = MybatisUtils.getSqlSession();
		IVisitorOperation vOperation = session.getMapper(IVisitorOperation.class);
		int recordCount = vOperation.delete(id);
		session.commit();
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.Delete, recordCount);
	}

	/*
	 * 更新访问者信息
	 */
	public static void updateVisitor(int id) {
		SqlSession session = MybatisUtils.getSqlSession();
		IVisitorOperation vOperation = session.getMapper(IVisitorOperation.class);
		Visitor visitor = vOperation.query(id);
		System.out.println("原始对象：" + visitor);
		String name = visitor.getName();
		if (name.contains("updated")) {
			visitor.setName(name.substring(0, name.indexOf("updated")));
		} else {
			visitor.setName(name + "updated");
		}
		int recordCount = vOperation.update(visitor);
		session.commit();
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.Update, recordCount);
		System.out.println("更新后对象：" + visitor);
	}

	/*
	 * 查询访问者信息
	 */
	public static void queryVisitor(int id) {
		SqlSession session = MybatisUtils.getSqlSession();
		IVisitorOperation vOperation = session.getMapper(IVisitorOperation.class);
		Visitor visitor = vOperation.query(id);
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.Query, 1);
		System.out.println(visitor);
	}

	/*
	 * 查询访问者列表
	 */
	public static void queryVisitorList() {
		SqlSession session = MybatisUtils.getSqlSession();
		IVisitorOperation vOperation = session.getMapper(IVisitorOperation.class);
		List<Visitor> visitors = vOperation.getList();
		for (Visitor visitor : visitors) {
			System.out.println(visitor);
		}
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.List, visitors.size());
	}

	/*
	 * 分页参数
	 */
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

	/*
	 * 带rownum的显示的分页
	 */
	public static void queryVisitorListWithRnPagenate(int pageIndex, int pageSize, String orderField, String orderDire) {
		PagenateArgs args = new PagenateArgs(pageIndex, pageSize, orderField, orderDire);
		SqlSession session = MybatisUtils.getSqlSession();
		IVisitorOperation vOperation = session.getMapper(IVisitorOperation.class);
		List<VisitorWithRn> visitors = vOperation.getListByPagenateWithRn(args);
		for (VisitorWithRn visitor : visitors) {
			System.out.println(visitor);
		}
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.List, visitors.size());
	}

	/*
	 * 添加Website
	 */
	@SuppressWarnings("unused")
	private static void addWebsite(Website site, SqlSession session) {
		if (session == null)
			session = MybatisUtils.getSqlSession();
		IWebsiteOperation wOperation = session.getMapper(IWebsiteOperation.class);
		int count = wOperation.add(site);
		session.commit();
		if (session == null)
			MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.Add, count);
	}

	public static void addWebsite(Website site) {
		addWebsite(site, null);
	}

	/*
	 * 批量添加网站
	 */
	public static void addWebsites() {
		SqlSession session = MybatisUtils.getSqlSession();
		List<Website> websites = Arrays.asList(new Website[] { new Website("果壳网", 1), new Website("网易网", 2),
				new Website("新浪网", 2), new Website("盛大网络", 2) });
		for (Website website : websites) {
			addWebsite(website, session);
		}
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.List, websites.size());
	}

	/*
	 * 更新Website
	 */
	public static void deleteWebsite(int id) {
		SqlSession session = MybatisUtils.getSqlSession();
		IWebsiteOperation wOperation = session.getMapper(IWebsiteOperation.class);
		int count = wOperation.delete(id);
		session.commit();
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.Delete, count);
	}

	public static void updateWebsite(int id) {
		SqlSession session = MybatisUtils.getSqlSession();
		IWebsiteOperation wOperation = session.getMapper(IWebsiteOperation.class);
		Website website = wOperation.query(id);
		System.out.println("更新前：" + website);
		String name = website.getName();
		if (name.contains("updated")) {
			name = name.substring(0, name.indexOf("updated"));
		} else {
			name = name + "updated";
		}
		website.setName(name);
		int count = wOperation.update(website);
		session.commit();
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.Update, count);
		System.out.println("更新后：" + website);
	}

	/*
	 * 查询Website
	 */
	public static void queryWebsite(int id) {
		SqlSession session = MybatisUtils.getSqlSession();
		IWebsiteOperation wOperation = session.getMapper(IWebsiteOperation.class);
		Website site = wOperation.query(id);
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.Query, 1);
		System.out.println(site);
	}

	/*
	 * 根据VisitorId查询相应的满足条件的列表
	 */
	public static void queryWebsiteListByVisitorId(BasicQueryArgs args) {
		SqlSession session = MybatisUtils.getSqlSession();
		IWebsiteOperation wOperation = session.getMapper(IWebsiteOperation.class);
		List<Website> ls = wOperation.getListByVisitorId(args);
		for (Website site : ls) {
			System.out.println(site);
		}
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.List, ls.size());
	}

	/*
	 * 查询所有Website列表
	 */
	public static void queryWebsiteList() {
		SqlSession session = MybatisUtils.getSqlSession();
		IWebsiteOperation wOperation = session.getMapper(IWebsiteOperation.class);
		List<Website> ls = wOperation.getList();
		for (Website site : ls) {
			System.out.println(site);
		}
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.List, ls.size());
	}

	/*
	 * 添加Channel
	 */
	@SuppressWarnings("unused")
	private static void addChannel(Channel channel, SqlSession session) {
		if (session == null)
			session = MybatisUtils.getSqlSession();
		IChannelOperation cOperation = session.getMapper(IChannelOperation.class);
		int count = cOperation.add(channel);
		session.commit();
		if (session == null)
			MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.Add, count);
	}

	public static void addChannel(Channel channel) {
		addChannel(channel, null);
	}

	public static void addChannels() {
		SqlSession session = MybatisUtils.getSqlSession();
		List<Channel> channels = Arrays.asList(new Channel[] { new Channel("女性频道", 2), new Channel("男性频道", 2),
				new Channel("汽车频道", 2), new Channel("情感频道", 4), new Channel("社会频道", 4), new Channel("校园频道", 4),
				new Channel("时尚频道", 6), new Channel("娱乐频道", 6) });
		for (Channel channel : channels) {
			addChannel(channel, session);
		}
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.List, channels.size());
	}

	/*
	 * 删除频道
	 */
	public static void deleteChannel(int id) {
		SqlSession session = MybatisUtils.getSqlSession();
		IChannelOperation cOperation = session.getMapper(IChannelOperation.class);
		int count = cOperation.delete(id);
		session.commit();
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.Delete, count);
	}

	/*
	 * 更新频道
	 */
	public static void updateChannel(int id) {
		SqlSession session = MybatisUtils.getSqlSession();
		IChannelOperation cOperation = session.getMapper(IChannelOperation.class);
		Channel channel = cOperation.query(id);
		System.out.println("更新前：" + channel);
		String name = channel.getName();
		if (name.contains("updated")) {
			name = name.substring(0, name.indexOf("updated"));
		} else {
			name = name + "updated";
		}
		channel.setName(name);
		int count = cOperation.update(channel);
		session.commit();
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.Update, count);
		System.out.println("更新后：" + channel);
	}

	/*
	 * 查询频道
	 */
	public static void queryChannel(int id) {
		SqlSession session = MybatisUtils.getSqlSession();
		IChannelOperation cOperation = session.getMapper(IChannelOperation.class);
		Channel channel = cOperation.query(id);
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.Query, 1);
		System.out.println(channel);
	}

	/*
	 * 查询频道列表
	 */
	public static void queryChannelList() {
		SqlSession session = MybatisUtils.getSqlSession();
		IChannelOperation cOperation = session.getMapper(IChannelOperation.class);
		List<Channel> channels = cOperation.getList();
		for (Channel channel : channels) {
			System.out.println(channel);
			System.out.println("--------分割线---------");
		}
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.List, channels.size());
	}

	/*
	 * 查询某个Website下的频道
	 */
	public static void queryChannelListBySiteId(int siteId) {
		SqlSession session = MybatisUtils.getSqlSession();
		IChannelOperation cOperation = session.getMapper(IChannelOperation.class);
		List<Channel> channels = cOperation.getListBySiteId(new BasicQueryArgs(siteId));
		for (Channel channel : channels) {
			System.out.println(channel);
			System.out.println("--------分割线---------");
		}
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.List, channels.size());
	}

	/*
	 * 查询某个Website下的频道
	 */
	public static void queryChannelListByVisitorId(int siteId) {
		SqlSession session = MybatisUtils.getSqlSession();
		IChannelOperation cOperation = session.getMapper(IChannelOperation.class);
		List<Channel> channels = cOperation.getListByVisitorId(new BasicQueryArgs(siteId));
		for (Channel channel : channels) {
			System.out.println(channel);
			System.out.println("--------分割线---------");
		}
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.List, channels.size());
	}

	/*
	 * 测试foreach配置用
	 */
	public static void queryChannelListByVisitorId(List<Integer> ids) {
		SqlSession session = MybatisUtils.getSqlSession();
		IChannelOperation cOperation = session.getMapper(IChannelOperation.class);
		List<Channel> channels = cOperation.getListByIds(ids);
		for (Channel channel : channels) {
			System.out.println(channel);
			System.out.println("--------分割线---------");
		}
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.List, channels.size());
	}

	/*
	 * 测试简易list
	 */
	public static void testSelectList() {
		SqlSession session = MybatisUtils.getSqlSession();
		List<Visitor> visitors = session.selectList("select * from visitor where status>0");
		for (Visitor visitor : visitors) {
			System.out.println(visitor);
		}
		MybatisUtils.closeSession(session);
		MybatisUtils.showMessages(CRUD_Enum.List, visitors.size());
	}

	/*
	 * 测试scalar语句效果
	 */
	public static void retrieveListCount() {
		SqlSession session = MybatisUtils.getSqlSession();
		IChannelOperation cOperation = session.getMapper(IChannelOperation.class);
		int count = cOperation.retrieveListCount();
		System.out.println(count);
	}
}
