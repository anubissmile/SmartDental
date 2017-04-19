package com.smict.person.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.Pre_nameData;
import com.smict.person.model.Pre_nameModel;

import ldc.util.Auth;

public class PreNameAction extends ActionSupport {
	
	Pre_nameModel prenameModel;
	
	/**
	 * CONSTRUCTOR
	 */
	public PreNameAction(){
		Auth.authCheck(false);
	}
	
	public Pre_nameModel getPrenameModel() {
		return prenameModel;
	}
	public void setPrenameModel(Pre_nameModel prenameModel) {
		this.prenameModel = prenameModel;
	}
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		Pre_nameData PreNameData = new Pre_nameData();
		List PreNameList = PreNameData.select_pre_name("", "", "");
		request.setAttribute("PreNameList", PreNameList);
		
		return SUCCESS;
	}
	public String excute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		Pre_nameData PreNameData = new Pre_nameData();
		String savePre 	= 	request.getParameter("savePre");
		String updatPre 	=	request.getParameter("updatPre");
		String deletPre 	=	request.getParameter("deletPre");
		System.out.println(prenameModel.getPre_name_en());
		if(savePre!=null){ 
			PreNameData.add_pre_name(prenameModel);
		}
		if(updatPre!=null){
			String pre_name_id = request.getParameter("PreNameID") ,
				   pre_name_th= request.getParameter("PreNameTH") ,
				   pre_name_en= request.getParameter("PreNameEN") ;
			PreNameData.UpdatePre_name(pre_name_id, pre_name_th, pre_name_en);
		}
		if(deletPre!=null){
			String pre_name_id = request.getParameter("PreNameID_de");
			if(!PreNameData.CheckPre_name(pre_name_id)){
			
				Boolean delStatus = PreNameData.DeletePre_name(pre_name_id);
				if(delStatus){
					request.setAttribute("del_status", "Deleted !");
				}else { request.setAttribute("del_status", "Deleted Not Success!"); }
			
			}else{request.setAttribute("del_status", "Delete error PreName in use!");}
		}
		List PreNameList = PreNameData.select_pre_name("", "", "");
		request.setAttribute("PreNameList", PreNameList);
	return SUCCESS;
	}


}
