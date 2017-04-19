package com.smict.person.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.Pre_nameData;
import com.smict.person.data.TelephoneData;
import com.smict.person.model.Pre_nameModel;
import com.smict.person.model.TelephoneModel;

import ldc.util.Auth;

public class TypeTelAction extends ActionSupport {
	TelephoneModel telModel;
	
	/**
	 * CONSTRUCTOR
	 */
	public TypeTelAction(){
		Auth.authCheck(false);
	}
	
	public TelephoneModel getTelModel() {
		return telModel;
	}
	public void setTelModel(TelephoneModel telModel) {
		this.telModel = telModel;
	}
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		TelephoneData telData = new TelephoneData();
		List TypeTelList = telData.select_telType();
		request.setAttribute("TypeTelList", TypeTelList);
		
		return SUCCESS;
	}
	public String excute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		TelephoneData telData = new TelephoneData();
		String saveBTN 	= 	request.getParameter("saveType");
		String updatBTN 	=	request.getParameter("upType");
		String deletBTN 	=	request.getParameter("delType");
		if(saveBTN!=null){ 
			telData.has_addTelType(telModel);
			request.setAttribute("del_error", "");
			request.setAttribute("del_success", "Add Success!");
		}
		if(updatBTN!=null){
			telModel.setTel_typeid(Integer.parseInt(request.getParameter("id_up")));  
			telModel.setTel_typename(request.getParameter("name_up"));
			telData.has_updateTelType(telModel);
			request.setAttribute("del_error", "");
			request.setAttribute("del_success", "Update Success!");
		}
		if(deletBTN!=null){
			int tel_typeid = Integer.parseInt(request.getParameter("id_de"));
			if(!telData.CheckTypeTel(tel_typeid)){
				Boolean delStatus = telData.removeTelTypeID(tel_typeid);
				if(delStatus){
					request.setAttribute("del_error", "");
					request.setAttribute("del_success", "Deleted !");
				}else { 
					request.setAttribute("del_success", "");
					request.setAttribute("del_error", "Deleted Not Success!"); }
			}else{
				request.setAttribute("del_success", "");
				request.setAttribute("del_error", "Delete error TelType in use!");}
		}
		List TypeTelList = telData.select_telType();
		request.setAttribute("TypeTelList", TypeTelList);
	return SUCCESS;
	}


}
