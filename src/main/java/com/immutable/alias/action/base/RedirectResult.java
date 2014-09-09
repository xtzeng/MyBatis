package com.immutable.alias.action.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

/**
 * @author Jet
 */
@SuppressWarnings("serial")
public class RedirectResult implements Result {
	private String location;

	public RedirectResult(String location) {
		this.location = location;
	}

	public void execute(ActionInvocation invocation) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		location = getRealRedirectPath();
		response.sendRedirect(location);
	}

	public String getRealRedirectPath() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		if (isPathURL(location) && location.startsWith("/")) {
			String contextPath = request.getContextPath();

			if (contextPath!=null&&!contextPath.equals("")) {
				location = contextPath + location;
			}
		}

		return response.encodeRedirectURL(location);
	}

	boolean isPathURL(String url) {
		// filter out "http:", "https:", "mailto:", "file:", "ftp:"
		// since the only valid places for : in URL's is before the path
		// specification
		// either before the port, or after the protocol
		return (url.indexOf(':') == -1);
	}
}
