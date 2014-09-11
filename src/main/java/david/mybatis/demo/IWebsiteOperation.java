package david.mybatis.demo;

import java.util.List;

import david.mybatis.model.BasicQueryArgs;
import david.mybatis.model.PagenateArgs;
import david.mybatis.model.Website;

public interface IWebsiteOperation {
    
    public int add(Website website);
    
    public int delete(int id);
    
    public int update(Website website);
    
    public Website query(int id);
    
    public List<Website> getList();
    
    public List<Website> getListByVisitorId(BasicQueryArgs args);
    
    public List<Website> getListByPagenate(PagenateArgs args);
}
