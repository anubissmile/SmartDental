package com.smict.person.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ContypeModel;
import com.smict.all.model.ServicePatientModel;
import com.smict.person.data.PatContypeData;
import com.smict.person.model.PatientModel;
import com.smict.promotion.data.Promotiondata;
import com.smict.promotion.model.PromotionModel;
import com.smict.treatment.action.TreatmentAction;

import ldc.util.Auth;
import ldc.util.CalculateNumber;
import ldc.util.Servlet;

public class ContypeAction extends ActionSupport {

	ContypeModel patContypeModel;
	ServicePatientModel servicePatModel;
	String alertStatus, alertMessage;
	private PromotionModel protionModel;
	/**
	 * CONSTRUCTOR
	 */
	public ContypeAction(){
		Auth.authCheck(false);
	}
	
	public void setSessionToServicePatModel(){
		HttpServletRequest request = ServletActionContext.getRequest();		
		HttpSession session = request.getSession();  		
	//	PatientAction patAction = new PatientAction();
		//patAction.getServiceModelNewData(request);
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
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

	public ServicePatientModel getServicePatModel() {
		return servicePatModel;
	}

	public void setServicePatModel(ServicePatientModel servicePatModel) {
		this.servicePatModel = servicePatModel;
	}

	public ContypeModel getPatContypeModel() {
		return patContypeModel;
	}

	public void setPatContypeModel(ContypeModel patContypeModel) {
		this.patContypeModel = patContypeModel;
	}
	
	public String renewalMember() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		setSessionToServicePatModel();
		TreatmentAction treatAction = new TreatmentAction();
		treatAction.setToothList(request);
		
		PatContypeData patConData = new PatContypeData();
		
		if(patConData.renewalMember(patContypeModel)){
			alertStatus = "success";
			alertMessage = "ต่ออายุสำเร็จ";
		}
		
		PatientModel patModel = new PatientModel();
		CalculateNumber classCalNum = new CalculateNumber();
		
		new Servlet().redirect(request, response, "selectPatient/view/" + servicePatModel.getHn());
		return SUCCESS;
	}
	
	public String beginAddPatientContype(){
		
		setSessionToServicePatModel();
		if(servicePatModel == null){
			return "getCustomer";
		}
		
		
		return SUCCESS;
	}
	
	public String addPatientContype() throws ServletException, IOException{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpServletResponse response = ServletActionContext.getResponse();
		setSessionToServicePatModel();
		PatContypeData patcontDB = new PatContypeData();
		Promotiondata promoData = new Promotiondata();
		
		if(patcontDB.addPatContype(servicePatModel.getHn(), patContypeModel.getSub_contact_id())) {
			if(protionModel.getSub_contact_type_id().equals("3")){
				String defaultamount = request.getParameter("totalamountall");
				 if(defaultamount!="" && defaultamount != null){
					 protionModel.setSub_contact_amount(Double.parseDouble(defaultamount.replace(",", "")));
					 promoData.insertsubcontactWallet(patContypeModel.getSub_contact_id(),protionModel.getSub_contact_amount(),servicePatModel.getHn());
				 }
			}
			
			
			alertStatus = "success";
			alertMessage = "เพิ่มสมาชิกสำเร็จ";
		}else{
			alertStatus = "danger";
			alertMessage = "เพิ่มสมาชิกไม่สำเร็จ";
		}
		new Servlet().redirect(request, response, "selectPatient/view/" + servicePatModel.getHn());
		return SUCCESS;
	}
	
	public String deletePatientContype() throws ServletException, IOException{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpServletResponse response = ServletActionContext.getResponse();
		setSessionToServicePatModel();
		PatContypeData patcontDB = new PatContypeData();
		if(patcontDB.CheckStatusPatContype(servicePatModel.getHn()) || patContypeModel.getPat_con_status().equals("1")){
			if(patcontDB.updateStatusPatContype(patContypeModel.getPatient_contypeid(),patContypeModel.getPat_con_status())) {
				alertStatus = "success";
				alertMessage = "เปลื่ยนสถานะสมาชิกสำเร็จ";
			}else{
				alertStatus = "danger";
				alertMessage = "เปลื่ยนสถานะสมาชิกไม่สำเร็จ";
				new Servlet().redirect(request, response, "selectPatient/view/" + servicePatModel.getHn());
				return INPUT;
			}
		}else{
			alertStatus = "danger";
			alertMessage = "ไม่สามารถเปิดการใช้งาน ประเภทคนไข้ได้มากกว่า 1 !";
			new Servlet().redirect(request, response, "selectPatient/view/" + servicePatModel.getHn());
			return INPUT;
		}
		new Servlet().redirect(request, response, "selectPatient/view/" + servicePatModel.getHn());
		return SUCCESS;
	}

	public PromotionModel getProtionModel() {
		return protionModel;
	}

	public void setProtionModel(PromotionModel protionModel) {
		this.protionModel = protionModel;
	}
}
