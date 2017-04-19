package com.smict.product.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.BranchData;
import com.smict.product.data.LabDB; 
import com.smict.product.model.LabModel;

import ldc.util.Auth; 



public class LabAction extends ActionSupport{
	
	LabModel labModel;  
	
	/**
	 * CONSTRUCTOR
	 */
	public LabAction(){
		Auth.authCheck(false);
	}
	 
	public LabModel getLabModel() {
		return labModel;
	}
	public void setLabModel(LabModel labModel) {
		this.labModel = labModel;
	}
	
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		LabDB labDB = new LabDB();
		List lablist = labDB.Get_LabList("", "");
		request.setAttribute("lablist", lablist);
		
		BranchData branchData = new BranchData();
		List branchlist = branchData.select_branch("", "", "", "", 1);
		request.setAttribute("branchlist", branchlist); 
		
		return SUCCESS;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		LabDB labDB = new LabDB();
		  
		String save 	= 	request.getParameter("save");
		String updateg 	=	request.getParameter("updateg");
		String deleteg 	=	request.getParameter("deleteg");
		 
		if(save!=null){
		//	String lab_id 		= labModel.getLab_id();
			String lab_name 	= labModel.getLab_name();
			String addr_id		= labModel.getAddr_id();
			
			labDB.Addlab(lab_name, addr_id);
		}
		
		if(updateg!=null){
			String lab_id 		= request.getParameter("id_up"); 
			String lab_name 	= request.getParameter("name_up"); 
			String addr_id		= request.getParameter("addr_up"); 
			
			labDB.Updatelab(lab_id, lab_name, addr_id);
		}
		
		if(deleteg!=null){
			String lab_id 	= request.getParameter("id_de"); 
			
			labDB.Deletelab(lab_id);
		} 
		
		List lablist = labDB.Get_LabList("", "");
		request.setAttribute("lablist", lablist);
		
		BranchData branchData = new BranchData();
		List branchlist = branchData.select_branch("", "", "", "", 1);
		request.setAttribute("branchlist", branchlist); 
		
		return SUCCESS;
	} 
	
}
