package david.mybatis.model;

/*
 * 分页参数实体类
 */
public class PagenateArgs {
	
    private int pageIndex;
    private int pageSize;
    private int pageStart;
    private String orderFieldStr;
    private String orderDirectionStr;

    public PagenateArgs() {
        // TODO Auto-generated constructor stub
    }

    public PagenateArgs(int pageIndex, int pageSize, String orderFieldStr, String orderDirectionStr) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.orderFieldStr = orderFieldStr;
        this.orderDirectionStr = orderDirectionStr;
        pageStart = pageIndex * pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageStart() {
        return pageStart;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String orderFieldStr() {
        return orderFieldStr;
    }

    public String getOrderDirectionStr() {
        return orderDirectionStr;
    }
}