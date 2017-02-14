package com.smict.page.action;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.auth.AuthAction;

@SuppressWarnings("serial")
public class HomeAction extends ActionSupport {
	public String execute(){
		AuthAction auth = new AuthAction();
		auth.authCheck(false);
		return SUCCESS;
	}
}
