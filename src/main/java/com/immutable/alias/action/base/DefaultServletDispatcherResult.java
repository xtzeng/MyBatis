package com.immutable.alias.action.base;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;

import org.apache.struts2.dispatcher.ServletDispatcherResult;

@SuppressWarnings("serial")
public class DefaultServletDispatcherResult extends ServletDispatcherResult {

    @Override
    public void execute(ActionInvocation invocation) throws Exception {
        ActionProxy actionProxy = invocation.getProxy();
        String actionName = actionProxy.getActionName();

        //String location = Constant.locationPrefix + "/" + actionName.replace(".", "/") + ".jsp";
        //super.doExecute(location, invocation);
    }
}
