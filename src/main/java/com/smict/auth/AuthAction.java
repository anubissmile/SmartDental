package com.smict.auth;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import ldc.util.Encrypted;
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
			HttpSession session = request.getSession();
			session.setAttribute("userSession", userSession);	
			return SUCCESS;
		}else{
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
		
		if(authModel.getEmpPWD().length() < 10){
			addFieldError("authModel.empPWD", "Your password must be more than 10 character.");
		}
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