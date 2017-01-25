package com.smict.product.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.product.data.LabDB;
import com.smict.product.data.LabServiceDB;
import com.smict.product.data.ServiceDB;
import com.smict.product.model.LabModel;
import com.smict.product.model.LabServiceModel; 



public class LabServiceAction extends ActionSupport{
	
	LabServiceModel labserviceModel;  
	   
	public LabServiceModel getLabserviceModel() {
		return labserviceModel;
	}
	public void setLabserviceModel(LabServiceModel labserviceModel) {
		this.labserviceModel = labserviceModel;
	}
	
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		LabDB labDB = new LabDB();
		List lablist = labDB.Get_LabList("", "");
		request.setAttribute("lablist", lablist);
		
		ServiceDB serviceDB = new ServiceDB();
		List servicelist = serviceDB.Get_ServiceList("", "", "");
		request.setAttribute("servicelist", servicelist);
		
		LabServiceDB labserviceDB = new LabServiceDB();
		List labservicelist = labserviceDB.Get_LabServiceList("", "");
		request.setAttribute("labservicelist", labservicelist);
		
		return SUCCESS;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		LabServiceDB labserviceDB = new LabServiceDB();
		ServiceDB serviceDB = new ServiceDB();
		LabDB labDB = new LabDB();
		  
		String save 	= 	request.getParameter("save");
		String updateg 	=	request.getParameter("updateg");
		String deleteg 	=	request.getParameter("deleteg");
		 
		if(save!=null){
		 	String lab_id 		= labserviceModel.getLab_id(); 
			String service_id	= labserviceModel.getService_id();
			String est_fee		= labserviceModel.getEst_fee();
			
			labserviceDB.Addlabservice(lab_id, service_id, est_fee);
		}
		
		if(updateg!=null){
			String lab_id_up 		= request.getParameter("lab_id_up"); 
			String service_id_up 	= request.getParameter("service_id_up"); 
			String est_fee			= request.getParameter("est_fee_up"); 
			String lab_id_up_hd 	= request.getParameter("lab_id_up_hd"); 
			String service_id_up_hd	= request.getParameter("service_id_up_hd"); 
			
			labserviceDB.Updatelab(lab_id_up, service_id_up, est_fee, lab_id_up_hd, service_id_up_hd);
		}
		
		if(deleteg!=null){ 
			String lab_id_up 		= request.getParameter("lab_id_de_hd"); 
			String service_id_up 	= request.getParameter("service_id_de_hd");
			
			labserviceDB.Deletelabservice(lab_id_up, service_id_up);
		} 
		
		List lablist = labDB.Get_LabList("", "");
		request.setAttribute("lablist", lablist);
		 
		List servicelist = serviceDB.Get_ServiceList("", "", "");
		request.setAttribute("servicelist", servicelist);
		 
		List labservicelist = labserviceDB.Get_LabServiceList("", "");
		request.setAttribute("labservicelist", labservicelist);
		
		return SUCCESS;
	} 
	
}
