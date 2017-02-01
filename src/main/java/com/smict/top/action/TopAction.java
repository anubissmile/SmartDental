package com.smict.top.action;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
 
import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ServicePatientModel;
import com.smict.person.data.PatientData;
import com.smict.person.data.TelephoneData;
import com.smict.person.model.PatientModel;
import com.smict.person.model.TelephoneModel;
import com.smict.treatment.action.TreatmentAction;

import ldc.util.CalculateNumber;
import ldc.util.DBConnect;  

public class TopAction extends ActionSupport{ 
	ServicePatientModel servicePatModel;
	public ServicePatientModel getServicePatModel() {
		return servicePatModel;
	}
	public void setServicePatModel(ServicePatientModel servicePatModel) {
		this.servicePatModel = servicePatModel;
	} 
	
	public String execute() throws Exception{
		 

		 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();  

		PatientModel patModel = new PatientModel();
		CalculateNumber classCalNum = new CalculateNumber();

		//////////// Receive From FrontEnd
		patModel.setHn(servicePatModel.getHn());
		patModel.setHnFormat(servicePatModel.getHnFormat());
		patModel.setAddr_id(servicePatModel.getAddr_id());
		patModel.setTel_id(servicePatModel.getTel_id());
		patModel.setPatneed_id(servicePatModel.getPatneed_id());
		patModel.setBe_allergic_id(servicePatModel.getBe_allergic_id());
		patModel.setPat_congenital_disease_id(servicePatModel.getPat_congenital_disease_id());
		patModel.setFam_id(servicePatModel.getFam_id());
		////////////Receive From FrontEnd
		
		servicePatModel = new ServicePatientModel(new PatientData().getPatModel_patient(patModel));
		
		  
		//servicePatModel = new ServicePatientModel(hn, pre_name_id, first_name_th, last_name_th, first_name_en, last_name_en, birth_date, Integer.valueOf(tel_id), deposit_money);
	    session.setAttribute("ServicePatientModel", servicePatModel);
	    TreatmentAction treatAction = new TreatmentAction();
	    treatAction.setToothList(request);
		return SUCCESS;
	}
	
	
}
