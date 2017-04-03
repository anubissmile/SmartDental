package com.smict.product.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport; 
import com.smict.product.data.LabModeDB;
import com.smict.product.model.LabModeModel;

import ldc.util.Auth; 
 
public class LabModeAction extends ActionSupport{
	
	LabModeModel labModeModel;   
	
	/**
	 * CONSTRUCTOR
	 */
	public LabModeAction(){
		Auth.authCheck(false);
	}
	
	public LabModeModel getLabModeModel() {
		return labModeModel;
	}
	public void setLabModeModel(LabModeModel labModeModel) {
		this.labModeModel = labModeModel;
	}
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		LabModeDB labModeDB = new LabModeDB();
		List labmodelist = labModeDB.Get_LabModeList("", "");
		request.setAttribute("labmodelist", labmodelist);
		
		return SUCCESS;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		LabModeDB labModeDB = new LabModeDB();
		  
		String save 	= 	request.getParameter("save");
		String updateg 	=	request.getParameter("updateg");
		String deleteg 	=	request.getParameter("deleteg");
		 
		if(save!=null){
			String labmode_id 		= labModeModel.getLabmode_id();
			String labmode_name 	= labModeModel.getLabmode_name(); 
			
			labModeDB.Addlabmode(labmode_id, labmode_name);
		}
		
		if(updateg!=null){
			String hdlabmode_id 	= request.getParameter("hdid_up"); 
			String labmode_id 		= request.getParameter("id_up"); 
			String labmode_name		= request.getParameter("name_up"); 
			
			labModeDB.Updatelabmode(labmode_id, labmode_name, hdlabmode_id);
		}
		
		if(deleteg!=null){
			String lab_id 	= request.getParameter("id_de"); 
			
			labModeDB.Deletelabmode(lab_id);
		} 
		
		List labmodelist = labModeDB.Get_LabModeList("", "");
		request.setAttribute("labmodelist", labmodelist);
		
		return SUCCESS;
	} 
	
}
