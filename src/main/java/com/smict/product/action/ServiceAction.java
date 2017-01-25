package com.smict.product.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.product.data.LabModeDB;
import com.smict.product.data.ServiceDB; 
import com.smict.product.model.ServiceModel;  

public class ServiceAction extends ActionSupport{
	
	ServiceModel serviceModel;   
	public ServiceModel getServiceModel() {
		return serviceModel;
	}
	public void setServiceModel(ServiceModel serviceModel) {
		this.serviceModel = serviceModel;
	}
	
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		ServiceDB serviceDB = new ServiceDB();
		List servicelist = serviceDB.Get_ServiceList("", "", "");
		request.setAttribute("servicelist", servicelist);
		
		LabModeDB labModeDB = new LabModeDB();
		List labmodelist = labModeDB.Get_LabModeList("", "");
		request.setAttribute("labmodelist", labmodelist);
		
		return SUCCESS;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		ServiceDB serviceDB = new ServiceDB();
		  
		String save 	= 	request.getParameter("save");
		String updateg 	=	request.getParameter("updateg");
		String deleteg 	=	request.getParameter("deleteg");
		 
		if(save!=null){
		//	String service_id 		= serviceModel.getService_id();
			String labmode_id		= serviceModel.getLabmode_id(); 
			String service_name 	= serviceModel.getService_name(); 
			
			serviceDB.AddService(labmode_id, service_name);
		}
		
		if(updateg!=null){
			String labmode_id 		= request.getParameter("labid_up"); 
			String service_id 		= request.getParameter("id_up"); 
			String service_name 	= request.getParameter("name_up");  
			
			serviceDB.UpdateService(labmode_id, service_id, service_name);
		}
		
		if(deleteg!=null){ 
			String labmode_id 		= request.getParameter("labid_de");
			String service_id 		= request.getParameter("id_de");
			
			serviceDB.DeleteService(labmode_id, service_id);
		} 
		
		LabModeDB labModeDB = new LabModeDB();
		List labmodelist = labModeDB.Get_LabModeList("", "");
		request.setAttribute("labmodelist", labmodelist);
		
		List servicelist = serviceDB.Get_ServiceList("", "", "");
		request.setAttribute("servicelist", servicelist);
		
		return SUCCESS;
	} 
	
}
