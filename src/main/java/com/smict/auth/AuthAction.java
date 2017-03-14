package com.smict.auth;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import ldc.util.Encrypted;
import ldc.util.Servlet;
import ldc.util.Validate;

@SuppressWarnings("serial")
public class AuthAction extends ActionSupport{
	AuthModel authModel;
	private final String SALT = "LDCDENTALCLINIC";

	public String authAttempt(){
//		System.out.println(authModel.getEmpUsr() + " " + authModel.getEmpPWD());
		authModel.setEmpPWD(encrypt(authModel.getEmpPWD()));
		
		AuthData authData = new AuthData();
		HashMap<String, AuthModel> userSession = new HashMap<String, AuthModel>();
		userSession = authData.attempt(authModel.getEmpUsr(), authModel.getEmpPWD());
		if(!userSession.isEmpty()){
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession(false);

			if(null == session.getAttribute("userSession")){
				/**
				 * DO LOG IN.
				 */
				session.setAttribute("userSession", userSession);
				return SUCCESS;
			}else{
				/**
				 * USER IS LOGGED IN ALREADY.
				 */
				return "ALREADY";
			}
		}else{
			/**
			 * USER NOT FOUND | LOGGED IN MISMATCH.
			 */
			addFieldError("authModel.empUsr", "User not found.");
			return INPUT;
		}
		
	}

	public void validateAuthAttempt(){
		
		Validate val = new Validate();
		
		if(!val.Check_String_notnull_notempty(authModel.getEmpUsr())){
			addFieldError("authModel.empUsr", "Please fill your username.");
		}
		
		if(!val.Check_String_notnull_notempty(authModel.getEmpPWD())){
			addFieldError("authModel.empPWD", "Please fille your password");
		}
		
		if(authModel.getEmpPWD().length() < 5){
			addFieldError("authModel.empPWD", "Your password must be more than 10 character.");
		}
	}
	
	/**
	 * Checking whether user was logged in already or not.
	 * @author anubissmile
	 * @param Boolean true : It will redirect to /home | false : do nothings.
	 * @return void
	 */
	public void authCheck(Boolean active){
		String site = null;
		HttpServletRequest request  = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if(session.getAttribute("userSession") == null){
			site = "authenticate";
		}else{
			site = "home";
		}

		/**
		 * REDIRECT
		 */
		if(active || (site == "authenticate")){
			Servlet serve = new Servlet();
			try {
				serve.redirect(request, response, site);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				System.out.println("ServletException");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("IOException");
				e.printStackTrace();
			}
		}
		
	}
	
	public String logOut(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("userSession") != null){
			session.invalidate();
		}
		return SUCCESS;
	}
	
	/**
	 * @author anubissmile
	 * encrypt the string to SHA
	 * @param str
	 * @return String encrypted String set
	 */
	private String encrypt(String str){
		try {
			Encrypted enc = new Encrypted();
			str = enc.encrypt(str);
			str += enc.encrypt(SALT);
			str = enc.encrypt(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();						
		}
		return str;
	}
	

	/**
	 * GETTER SETTER ZONE.
	 */
	public AuthModel getAuthModel() {
		return authModel;
	}

	public void setAuthModel(AuthModel authModel) {
		this.authModel = authModel;
	}

}