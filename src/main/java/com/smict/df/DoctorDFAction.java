package com.smict.df;
 
import javax.servlet.http.HttpServletRequest; 

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport; 

public class DoctorDFAction extends ActionSupport{
		 
public String execute() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		DFDB dfDB = new DFDB();
		 
		String doctor_id = request.getParameter("doctor_id");
		String branch_id = request.getParameter("branch_id");
		if(request.getParameterValues("checkbox_branch")!=null){
			String[] checkbox_branch = request.getParameterValues("checkbox_branch");
			for(String checked_branch_id : checkbox_branch){
				for(int i=0; i<request.getParameterValues("df_percent").length; i++){
					String[] treatment_id = request.getParameterValues("treatment_id");
					String[] df_percent = request.getParameterValues("df_percent");
					String[] df_baht = request.getParameterValues("df_baht");
					String[] price_lab = request.getParameterValues("price_lab");
					
					if(Double.parseDouble(df_percent[i].replace(",", ""))>0||Double.parseDouble(df_baht[i].replace(",", ""))>0||Double.parseDouble(price_lab[i].replace(",", ""))>0){
						if(!dfDB.hasExistsTreatmentBranchDoctor(treatment_id[i], doctor_id, checked_branch_id)){
							dfDB.AddTreatmentDoctorBranch(treatment_id[i], doctor_id, checked_branch_id, df_percent[i].replace(",", ""), df_baht[i].replace(",", ""), price_lab[i].replace(",", ""));
						}else{
							dfDB.hasUpdatedTreatmentDoctorBranch(treatment_id[i], doctor_id, checked_branch_id, df_percent[i], df_baht[i], price_lab[i]);
						} 
					} 
				}
			}
		} 
		 
		return SUCCESS;  
	}

}
