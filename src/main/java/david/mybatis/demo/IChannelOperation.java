package david.mybatis.demo;

import java.util.List;

import david.mybatis.model.BasicQueryArgs;
import david.mybatis.model.Channel;

public interface IChannelOperation {
	
	public int add(Channel channel);
	
	public int delete(int id);
	
	public int update(Channel channel);
	
	public Channel query(int id);
	
	public List<Channel> getList();
	
	public List<Channel> getListBySiteId(BasicQueryArgs args);
	
	public List<Channel> getListByVisitorId(BasicQueryArgs args);
	
	public List<Channel> getListByIds(List<Integer> ids);
	
	public int retrieveListCount();
}
