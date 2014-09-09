package com.immutable.alias.action.base;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.ServletDispatcherResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Result;



public class BaseAction extends ActionSupport{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 判定页面的变量
	 */
	private String currentModule = "";
	
	/**
	 * 直接使用response输出一段字符串
	 * @param content
	 */
	public void write(String content){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			logger.debug("输出:"+content);
			writer.print(content);
		} catch (Exception e) {
			logger.error("输出字符串：" + content + " 出错！",e);
		}finally{
			if( writer!=null ){
				writer.close();
			}
		}
	}
	
	
	
	/**
	 * 输出json对象(强制规范为使用J5Response对象)
	 * @param obj
	 */
//	public void writeJson(J5Response obj){
//		String json = JacksonUtil.toJson(obj);
//		write(json);
//	}
//	
//	protected ListPager page = new ListPager("page");
	
//	public ListPager getPage() {
//		return page;
//	}
//
//	public void setPage(ListPager page) {
//		this.page = page;
//	}
	
private static final long serialVersionUID = 1L;
	
	protected Result dispatch(String location) {
		return new ServletDispatcherResult(location);
	}

	protected Result dispatch() {
		return new DefaultServletDispatcherResult();
	}
	
	protected Result tilesDispatch() {
		return new TilesServletDispatcherResult();
	}
	
	protected Result redirect(String location) {
		return new RedirectResult(location);
	}

	protected Result text(String text) {
		return new TextResult(text);
	}

    protected Result validateResult() {
        return dispatch("/commons/validate-result.jsp");
    }

    protected Result json(Object responseObject) {
        return new JSONResult(responseObject);
    }

	public String getCurrentModule() {
		return currentModule;
	}

	public void setCurrentModule(String currentModule) {
		this.currentModule = currentModule;
	}
    
}
