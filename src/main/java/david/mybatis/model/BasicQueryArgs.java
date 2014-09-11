package david.mybatis.model;

import java.util.Date;

public class BasicQueryArgs {
	private int queryId;
	private String queryName;
	private int queryStatus;
	private Date queryTime;

	public BasicQueryArgs() {
		// TODO Auto-generated constructor stub
	}

	public BasicQueryArgs(int id) {
		this(id, "", null);
	}

	public BasicQueryArgs(String name) {
		this(0, name, null);
	}

	public BasicQueryArgs(Date date) {
		this(0, "", date);
	}

	public BasicQueryArgs(int id, String name, Date createTime) {
		this.setQueryId(id);
		this.setQueryName(name);
		this.setQueryTime(createTime);
	}

	public int getQueryId() {
		return queryId;
	}

	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public int getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(int queryStatus) {
		this.queryStatus = queryStatus;
	}

	public Date getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(Date queryTime) {
		this.queryTime = queryTime;
	}
}
