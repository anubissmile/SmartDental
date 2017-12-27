package com.smict.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import ldc.util.Servlet;

@SuppressWarnings("serial")
public class AuthFormController extends ActionSupport {
	public String execute(){
		authCheck();
		return SUCCESS;
	}
	
	public void authCheck(){
		String site = null;
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if(session.getAttribute("userSession") != null){
			site = "home";
		}
		
		if(site != null){
			Servlet serve = new Servlet();
			try {
				serve.redirect(request, response, site);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
