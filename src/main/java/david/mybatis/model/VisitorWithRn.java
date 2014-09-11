package david.mybatis.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VisitorWithRn {
    private int id;
    private String name;
    private String email;
    private int status;
    private Date createTime;
    private int rownum;

    public VisitorWithRn() {
        // TODO Auto-generated constructor stub
        createTime = new Date();
    }

    public VisitorWithRn(String name, String email) {
        this.name = name;
        this.email = email;
        this.setStatus(1);
        this.createTime = new Date();
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("{Rownum：%d, Id: %d, Name: %s, CreateTime: %s}", rownum, id, name,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime));
    }
}