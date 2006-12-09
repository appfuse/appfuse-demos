package org.appfuse.helloworld;

import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {
    String message;

    public String getMessage() {
        return message;
    }

    public String execute() {
        message = "Howdy Pardner!";
        return SUCCESS;
    }
}