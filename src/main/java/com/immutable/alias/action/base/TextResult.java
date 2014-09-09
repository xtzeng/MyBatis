package com.immutable.alias.action.base;

import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

public class TextResult extends StrutsResultSupport {

    /**
     * 
     */
    private static final long serialVersionUID = 7850454605550122184L;
    public static final int BUFFER_SIZE = 1024;
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TextResult.class);

    private String charSet = "UTF-8";
    private String text;

    public TextResult(String text) {
        this.text = text;
    }

    public TextResult(String charSet, String text) {
        this.charSet = charSet;
        this.text = text;
    }

    @Override
    protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
        Charset charset = null;
        if (charSet != null) {
            if (Charset.isSupported(charSet)) {
                charset = Charset.forName(charSet);
            } else {
                logger.warn("charset [" + charSet + "] is not recognized ");
                charset = null;
            }
        }

        HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(HTTP_RESPONSE);

        if (charset != null) {
            response.setContentType("text/plain; charset=" + charSet);
        } else {
            response.setContentType("text/plain");
        }
        response.setHeader("Content-Disposition", "inline");

        PrintWriter writer = response.getWriter();
        try {
            writer.write(text);
        }
        finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }
}
