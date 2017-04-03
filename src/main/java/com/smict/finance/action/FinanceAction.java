package com.smict.finance.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.FinanceModel;
import com.smict.all.model.ServicePatientModel;
import com.smict.finance.data.FinanceData;
import com.smict.person.data.PatientData;
import com.smict.treatment.data.TreatmentData;

import ldc.util.Auth;
import ldc.util.DateUtil;
 
 
public class FinanceAction extends ActionSupport{
	FinanceModel financeModel;
	ServicePatientModel servicePatModel; 
	String alertStatus, alertMessage; 
	
	/**
	 * CONSTRUCTOR
	 */
	public FinanceAction(){
		Auth.authCheck(false);
	}
	
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(); 
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		if(session.getAttribute("ServicePatientModel")!=null){
			TreatmentData treatmentdb = new TreatmentData(); 
			String hn			= servicePatModel.getHn();
			int treatment_id 	= treatmentdb.Select_Treatment_ID(hn);
			
			List transectionTreatmentList = treatmentdb.transectionTreatment(hn, treatment_id);
			request.setAttribute("transectionTreatmentList", transectionTreatmentList); 
			
			FinanceData financeData = new FinanceData();
			List drugList = financeData.getDrug(treatment_id);
			request.setAttribute("drugList", drugList); 
			
			List productList = financeData.getProduct(treatment_id);
			request.setAttribute("productList", productList);
		}else{
			alertStatus = "danger";
			alertMessage = "กรุณาเลือกคนไข้ก่อนทำรายการ";
			return "getCustomer"; 
		} 
		return SUCCESS;
	}
	public String print() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(); 
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		if(session.getAttribute("ServicePatientModel")!=null){
			DateUtil dateUtil = new DateUtil();
			FinanceData financeData = new FinanceData();
			String hn			= servicePatModel.getHn();
			TreatmentData treatmentdb = new TreatmentData(); 
			int treatment_id 	= treatmentdb.Select_Treatment_ID(hn);
			String report_no = "";
			
			String year = dateUtil.curYear();
			boolean checkReportNo = financeData.checkReportNo(year, treatment_id);
			if(checkReportNo==false){
				report_no = financeData.getReportNo_Running(year);
				financeData.insertReportNo(year, treatment_id, report_no);
			}else{
				report_no = financeData.getReportNo(year, treatment_id);
			}
			financeData.updateTreatmentidfornull(hn);
			servicePatModel = new ServicePatientModel(new PatientData().getPatModel_patient(servicePatModel));
			servicePatModel.setStatus(new PatientData().getPatStatus(hn));  // working teartment button 
			session.setAttribute("ServicePatientModel", servicePatModel);
			 
			request.setAttribute("hn", hn);
			request.setAttribute("treatment_id", treatment_id);
			request.setAttribute("report_no", "SN"+dateUtil.curTHYear().substring(2, 4)+"/"+report_no);
			request.setAttribute("date", dateUtil.curDateTH().replace("-", "/"));
			request.setAttribute("time", dateUtil.curTime()+" น.");
			request.setAttribute("amounttotal", request.getParameter("amounttotal").toString());
			request.setAttribute("discount", request.getParameter("discount").toString());
			request.setAttribute("net", request.getParameter("net").toString());
			request.setAttribute("owe", request.getParameter("owe").toString());
		}else{
			alertStatus = "danger";
			alertMessage = "กรุณาเลือกคนไข้ก่อนทำรายการ";
			return "getCustomer"; 
		} 
		return SUCCESS;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		 
		  
		 
		return SUCCESS;
	}
	
	public ServicePatientModel getServicePatModel() {
		return servicePatModel;
	}
	public void setServicePatModel(ServicePatientModel servicePatModel) {
		this.servicePatModel = servicePatModel;
	} 
	public String getAlertStatus() {
		return alertStatus;
	}
	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}
	public String getAlertMessage() {
		return alertMessage;
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
	public FinanceModel getFinanceModel() {
		return financeModel;
	}
	public void setFinanceModel(FinanceModel financeModel) {
		this.financeModel = financeModel;
	} 
	
}
