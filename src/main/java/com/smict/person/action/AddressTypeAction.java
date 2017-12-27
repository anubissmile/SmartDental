package com.smict.person.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.AddressData;
import com.smict.person.model.AddressModel;

import ldc.util.Auth;

public class AddressTypeAction extends ActionSupport {
	
	AddressModel addrModel;
	
	/**
	 * CONSTRUCTOR
	 */
	public AddressTypeAction(){
		Auth.authCheck(false);
	}
	
	public AddressModel getAddrModel() {
		return addrModel;
	}
	public void setAddrModel(AddressModel addrModel) {
		this.addrModel = addrModel;
	}
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		AddressData addrData = new AddressData();
		addrModel = new AddressModel();
		List AddrTypeList = addrData.getAddress_type(addrModel);
		request.setAttribute("AddrTypeList", AddrTypeList);
		
		return SUCCESS;
	}
	public String excute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		AddressData addrData = new AddressData();
		String saveBTN 	= request.getParameter("saveType");
		String updatBTN = request.getParameter("upType");
		String deletBTN = request.getParameter("delType");
		if(saveBTN!=null){
			addrData.addAddress_type(addrModel);
			request.setAttribute("del_error", "");
			request.setAttribute("del_success", "Add Success!");
		}
		if(updatBTN!=null){
			addrModel.setAddr_typeid(request.getParameter("id_up"));
			addrModel.setAddr_typename(request.getParameter("name_up"));
			addrData.updateAddrType(addrModel);
			request.setAttribute("del_error", "");
			request.setAttribute("del_success", "Update Success!");
		}
		if(deletBTN!=null){
			String tel_typeid = request.getParameter("id_de");
			if(!addrData.CheckAddressType(tel_typeid)){
				Boolean delStatus = addrData.DeletePre_name(tel_typeid);
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
		addrModel = new AddressModel();
		List AddrTypeList = addrData.getAddress_type(addrModel);
		request.setAttribute("AddrTypeList", AddrTypeList);
	return SUCCESS;
	}


}
