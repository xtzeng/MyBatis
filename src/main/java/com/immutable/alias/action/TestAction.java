package com.immutable.alias.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.immutable.alias.action.base.BaseAction;
import com.immutable.alias.service.TestService;
import com.opensymphony.xwork2.Result;

@Controller("testAction")
@Scope("prototype")
public class TestAction extends BaseAction{

	/**
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name="testService")
	private TestService testService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Result test() {
			testService.testService();
			logger.info("hello ===========jj");
			return dispatch("/jsp/test.jsp");
		}
}
