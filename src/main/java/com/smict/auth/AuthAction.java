package com.smict.auth;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AuthAction extends ActionSupport{
	
	public String authAttempt(){
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println(request.getMethod().toString());
		
		
		return SUCCESS;
	}
	
}