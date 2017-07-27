package com.smict.person.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ServicePatientModel;
import com.smict.all.model.ToothModel;
import com.smict.all.model.TreatmentMasterModel;
import com.smict.all.model.TreatmentPlanModel;
import com.smict.person.data.DoctorData;
import com.smict.person.data.TreatmentPlanData;
import com.smict.person.model.DoctorModel;
import com.smict.treatment.data.ToothMasterData;
import com.smict.treatment.data.TreatmentMasterData;
import com.smict.treatment.model.TreatmentModel;

import ldc.util.Auth;

@SuppressWarnings("serial")
public class TreatmentPlanAction extends ActionSupport {

	TreatmentPlanModel treatPlanModel;
	ServicePatientModel servicePatModel;
	List<TreatmentPlanModel> listTreatmentPlanModel, listTreatPlanDetail;
	List<TreatmentMasterModel> listTreatmentModel;
	HashMap<String, String> doctorMap;
	List<DoctorModel> doctorList;
	TreatmentModel treatModel;
	String alertStatus, alertMessage, btnUpdate, btnDelete,
		btnAdd, btnChangeStatus;
	
	/**
	 * CONSTRUCTOR
	 */
	public TreatmentPlanAction(){
		Auth.authCheck(false);
	}
	
	public List<TreatmentPlanModel> getListTreatmentPlanModel() {
		return listTreatmentPlanModel;
	}

	public void setListTreatmentPlanModel(List<TreatmentPlanModel> listTreatmentPlanModel) {
		this.listTreatmentPlanModel = listTreatmentPlanModel;
	}

	public ServicePatientModel getServicePatModel() {
		return servicePatModel;
	}

	public void setServicePatModel(ServicePatientModel servicePatModel) {
		this.servicePatModel = servicePatModel;
	}
	
	public TreatmentPlanModel getTreatPlanModel() {
		return treatPlanModel;
	}

	public void setTreatPlanModel(TreatmentPlanModel treatPlanModel) {
		this.treatPlanModel = treatPlanModel;
	}

	public List<TreatmentMasterModel> getListTreatmentModel() {
		return listTreatmentModel;
	}

	public void setListTreatmentModel(List<TreatmentMasterModel> listTreatmentModel) {
		this.listTreatmentModel = listTreatmentModel;
	}

	public List<TreatmentPlanModel> getListTreatPlanDetail() {
		return listTreatPlanDetail;
	}

	public void setListTreatPlanDetail(List<TreatmentPlanModel> listTreatPlanDetail) {
		this.listTreatPlanDetail = listTreatPlanDetail;
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

	public String getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(String btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public String getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(String btnDelete) {
		this.btnDelete = btnDelete;
	}

	public String getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(String btnAdd) {
		this.btnAdd = btnAdd;
	}
	
	public String getBtnChangeStatus() {
		return btnChangeStatus;
	}

	public void setBtnChangeStatus(String btnChangeStatus) {
		this.btnChangeStatus = btnChangeStatus;
	}
	
	public HashMap<String, String> getDoctorMap() {
		return doctorMap;
	}

	public void setDoctorMap(HashMap<String, String> doctorMap) {
		this.doctorMap = doctorMap;
	}

	public List<DoctorModel> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(List<DoctorModel> doctorList) {
		this.doctorList = doctorList;
	}

	public String viewAllTreatmentPlan(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();  
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		if(servicePatModel == null){
			return "getCustomer";
		}
		
		treatPlanModel = new TreatmentPlanModel();
		treatPlanModel.setHn(servicePatModel.getHn());
		
		TreatmentPlanData aTreatmentPlanData = new TreatmentPlanData();
		listTreatmentPlanModel = aTreatmentPlanData.getListTreatmentPlanHeader(treatPlanModel);
		
		
		return SUCCESS;
	}
	
	public String viewDetailTreatmentPlan(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();  
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		TreatmentMasterData treatmentMasterData = new TreatmentMasterData();
		TreatmentPlanData treatPlanData = new TreatmentPlanData();
		List<TreatmentPlanModel> listDetailHeader = new ArrayList<TreatmentPlanModel>(treatPlanData.getTreatmentPlanDetailHeader(treatPlanModel));

			if(listDetailHeader.size() > 0){
				TreatmentPlanModel tmpModel = listDetailHeader.get(0);
				treatPlanModel.setTreatmentPlanname(tmpModel.getTreatmentPlanname());
				treatPlanModel.setHeaderStatusName(tmpModel.getHeaderStatusName());
			}
			listTreatmentModel = treatmentMasterData.select_treatment_master();
			listTreatPlanDetail = treatPlanData.getListTreatmentPlanDetail(treatPlanModel);
			ToothMasterData toothData= new ToothMasterData();
			List<ToothModel> toothListUp = toothData.select_tooth_list_arch("upper");
			request.setAttribute("toothListUp", toothListUp); 
			
			List<ToothModel> toothListLow = toothData.select_tooth_list_arch("lower");
			request.setAttribute("toothListLow", toothListLow); 
			List<ToothModel> toothHistory = toothData.get_tooth_history(servicePatModel.getHn());
			request.setAttribute("toothHistory", toothHistory);
		return SUCCESS;
	}
	
	public String entranchCreateTreatmentPlan(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();  
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");

		/**
		 * GET DOCTOR LIST.
		 */
		DoctorData doctorData = new DoctorData();
		doctorList = doctorData.getDentistList(null);
		doctorMap = new HashMap<String, String>();
		for(DoctorModel dm : doctorList){
			doctorMap.put(
				Integer.valueOf(dm.getDoctorID()).toString(), 
				dm.getFirstname_th() + " " + dm.getLastname_th()
			);
		}
		
		return SUCCESS;
	}
	
	public String createTreatmentPlan(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();  
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		treatPlanModel.setHn(servicePatModel.getHn());
		
		TreatmentPlanData aTreatmentPlanData = new TreatmentPlanData();
		TreatmentMasterData treatmentMasterData = new TreatmentMasterData();
		
		if(aTreatmentPlanData.hasCreateTreatmentPlan(treatPlanModel)){

			listTreatmentModel = treatmentMasterData.select_treatment_master();

			treatPlanModel.setTreatment_planid(aTreatmentPlanData.getTreatmentPlanIdByHnOderbyLimit(treatPlanModel));
			
			alertStatus = "success";
			alertMessage = "สร้างแผนการรักษาสำเร็จ";
			
			return "success";
		}else{
			return "treatmentplanfailed";
		}
		
	}
	
	public String submitTreatmentPlanDetail(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();  
		String forwardText = "";
		ServicePatientModel treatmentPlanDetail =  servicePatModel;
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		TreatmentPlanData treatPlanData = new TreatmentPlanData();
		TreatmentMasterData treatmentMasterData = new TreatmentMasterData();
		List<TreatmentPlanModel> listDetailHeader = new ArrayList<TreatmentPlanModel>(treatPlanData.getTreatmentPlanDetailHeader(treatPlanModel));
		if(listDetailHeader.size() > 0){
			TreatmentPlanModel tmpModel = listDetailHeader.get(0);
			treatPlanModel.setTreatmentPlanname(tmpModel.getTreatmentPlanname());
			treatPlanModel.setHeaderStatusName(tmpModel.getHeaderStatusName());
		}
		
		if(btnDelete != null){
			
			if(treatPlanData.hasdeleteHeaderTreatmentPlan(treatPlanModel)){
				
				treatPlanModel.setHn(servicePatModel.getHn());
				listTreatmentPlanModel = treatPlanData.getListTreatmentPlanHeader(treatPlanModel);
				
				alertStatus = "success";
				alertMessage = "ลบแผนการรักษาสำเร็จ";
				
				forwardText = "deletesuccess";
				
			}else{
				
				forwardText = "treatmentplanfailed";
				
			}
			
		}else if(btnUpdate != null){
			
			if(treatPlanData.hasUpdateHeaderTreatmentPlan(treatPlanModel)){
				
				listTreatPlanDetail = treatPlanData.getListTreatmentPlanDetail(treatPlanModel);
				treatPlanData.updateDateTime(String.valueOf(treatPlanModel.getTreatment_planid()));
				alertStatus = "success";
				alertMessage = "แก้ไขรายการรักษาสำเร็จ";
				
				forwardText = "success";
				
			}else{
				forwardText = "treatmentplanfailed";
			}
			
		}else if(btnChangeStatus != null){
			treatPlanModel.setHn(servicePatModel.getHn());
			treatPlanData.changePlanHeaderStatusToDeactive(treatPlanModel);
			
			if(treatPlanData.changePlanHeaderStatusToActive(treatPlanModel)){
				listTreatPlanDetail = treatPlanData.getListTreatmentPlanDetail(treatPlanModel);
				treatPlanModel.setHeaderStatusName("ใช้งาน");
				treatPlanModel.setHeaderStatus("1");
				
				/**
				 * UPDATE TREATMENT PLAN DATETIME.
				 */
				treatPlanData.updateDateTime(String.valueOf(treatPlanModel.getTreatment_planid()));
				
				alertStatus = "success";
				alertMessage = "เปลี่ยนสถานะสำเร็จ";
				
				forwardText = "success";
			}else{
				forwardText = "treatmentplanfailed";
			}
		
		}else { 
			if(treatPlanData.addDetailTreatmentPlan(treatPlanModel.getTreatment_planid(),treatModel)){
				listTreatPlanDetail = treatPlanData.getListTreatmentPlanDetail(treatPlanModel);
				alertStatus = "success";
				alertMessage = "เพิ่มรายการรักษาสำเร็จ";
				
				forwardText = "success";
			}else{
				forwardText = "treatmentplanfailed";
			}

		}
		

			listTreatmentModel = treatmentMasterData.select_treatment_master();

		
		return forwardText;
		
	}
	
	public String deleteDetailTreatmentPlan(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		
		TreatmentPlanData treatPlanData = new TreatmentPlanData();
		TreatmentMasterData treatmentMasterData = new TreatmentMasterData();
		
		List<TreatmentPlanModel> listDetailHeader = new ArrayList<TreatmentPlanModel>(treatPlanData.getTreatmentPlanDetailHeader(treatPlanModel));
		if(listDetailHeader.size() > 0){
			TreatmentPlanModel tmpModel = listDetailHeader.get(0);
			treatPlanModel.setTreatmentPlanname(tmpModel.getTreatmentPlanname());
			treatPlanModel.setHeaderStatusName(tmpModel.getHeaderStatusName());
		}
		
		if(treatPlanData.hasDeleteDetailTreatmentPlan(treatPlanModel)){
			
			listTreatPlanDetail = treatPlanData.getListTreatmentPlanDetail(treatPlanModel);

				listTreatmentModel = treatmentMasterData.select_treatment_master();

			
			alertStatus = "success";
			alertMessage = "ลบรายการรักษาสำเร็จ";
			return "success";
		}else{
			return "treatmentplanfailed";
		}
	}

	public TreatmentModel getTreatModel() {
		return treatModel;
	}

	public void setTreatModel(TreatmentModel treatModel) {
		this.treatModel = treatModel;
	}
	
}
