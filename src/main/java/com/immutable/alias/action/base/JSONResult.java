package com.immutable.alias.action.base;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.ValueStack;

public class JSONResult implements Result {

	/** */
	private static final long serialVersionUID = 1L;
	private Object responseObject;
	private String expression;

	public JSONResult() {
	}

	public JSONResult(Object responseObject) {
		this.responseObject = responseObject;
	}

	public void execute(ActionInvocation invocation) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			Gson gson = new Gson();
			String json = gson.toJson(getResponseObject(invocation));
			writer.print(json);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if( writer!=null ){
				writer.close();
			}
		}
	}

	private Object getResponseObject(ActionInvocation invocation) {
		if (expression != null) {
			ValueStack stack = invocation.getStack();
			return stack.findValue(expression);
		}
		return responseObject;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
}
