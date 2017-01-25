package com.smict.product.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.BranchData;
import com.smict.product.data.LabBranchDB;
import com.smict.product.data.LabDB;
import com.smict.product.model.LabBranchModel;
import com.smict.product.model.LabModel; 



public class LabBranchAction extends ActionSupport{
	
	LabBranchModel labBranchModel;  
	  
	public LabBranchModel getLabBranchModel() {
		return labBranchModel;
	}
	public void setLabBranchModel(LabBranchModel labBranchModel) {
		this.labBranchModel = labBranchModel;
	}
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		LabBranchDB labBranchDB = new LabBranchDB();
		List labBranchlist = labBranchDB.Get_LabBranchList("", "", "");
		request.setAttribute("labBranchlist", labBranchlist);
		
		LabDB labDB = new LabDB();
		List lablist = labDB.Get_LabList("", "");
		request.setAttribute("lablist", lablist);
		
		BranchData branchData = new BranchData();
		List branchlist = branchData.select_branch("", "", "", "");
		request.setAttribute("branchlist", branchlist); 
		
		return SUCCESS;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		LabBranchDB labBranchDB = new LabBranchDB();
		  
		String save 	= 	request.getParameter("save");
		String updateg 	=	request.getParameter("updateg");
		String deleteg 	=	request.getParameter("deleteg");
		 
		if(save!=null){
			String lab_id 		= labBranchModel.getLab_id();
		//	String lab_name 	= labBranchModel.getLab_name();
			String addr_id		= labBranchModel.getAddr_id();
			String branch_id	= labBranchModel.getBranch_id();
			
			labBranchDB.Addlab(lab_id, addr_id, branch_id);
		}
		
		if(updateg!=null){
			String branch_idup	= request.getParameter("branch_idup"); 
			String lab_id 		= request.getParameter("id_up"); 
			String branch_idhd	= request.getParameter("branch_idhd"); 
			String lab_idhd 	= request.getParameter("lab_idhd"); 
			String addr_id		= request.getParameter("addr_up"); 
			
			labBranchDB.Updatelab(lab_id, addr_id, branch_idup, lab_idhd, branch_idhd);
		}
		
		if(deleteg!=null){
			String lab_id 	= request.getParameter("id_de"); 
			
			labBranchDB.Deletelab(lab_id);
		} 
		
		List labBranchlist = labBranchDB.Get_LabBranchList("", "", "");
		request.setAttribute("labBranchlist", labBranchlist);
		
		LabDB labDB = new LabDB();
		List lablist = labDB.Get_LabList("", "");
		request.setAttribute("lablist", lablist);
		
		BranchData branchData = new BranchData();
		List branchlist = branchData.select_branch("", "", "", "");
		request.setAttribute("branchlist", branchlist); 
		
		return SUCCESS;
	} 
	
}
