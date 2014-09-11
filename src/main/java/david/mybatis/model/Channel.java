package david.mybatis.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Channel {
	private int id;
	private String name;
	private int websiteId;
	private int status;
	private Date createTime;
	private Website website;

	public Channel() {
		// TODO Auto-generated constructor stub
		status = 1;
		createTime = new Date();
	}

	public Channel(String name, int websiteId) {
		this.name = name;
		this.websiteId = websiteId;
		this.status = 1;
		this.createTime = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWebsiteId() {
		int id = 0;
		if (website == null)
			id = websiteId;
		else
			id = website.getId();
		return id;
	}

	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Channel=> {Id£º%d, Name: %s, CreateTime: %s}\r\n", id, name, new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").format(createTime)));
		sb.append(website.toString());
		return sb.toString();
	}
}
