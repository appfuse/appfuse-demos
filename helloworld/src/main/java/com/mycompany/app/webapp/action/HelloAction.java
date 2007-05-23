package com.mycompany.app.webapp.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author mraible
 */
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
