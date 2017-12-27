package com.smict.product.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ServicePatientModel;
import com.smict.auth.AuthModel;
import com.smict.person.data.BranchData;
import com.smict.person.data.PatContypeData;
import com.smict.person.data.PatientData;
import com.smict.person.data.TelephoneData;
import com.smict.person.model.CongenitalDiseaseModel;
import com.smict.person.model.PatientFileIdModel;
import com.smict.person.model.PatientModel;
import com.smict.person.model.TelephoneModel;
import com.smict.product.data.LabDB;
import com.smict.product.data.SendLabDB;
import com.smict.product.data.ServiceDB; 
import com.smict.product.model.SendLabModel;

import ldc.util.Auth;
import ldc.util.GeneratePatientBranchID;
import ldc.util.Validate;  

public class SendLabAction extends ActionSupport{
	
	SendLabModel sendLabModel;   
	
	/**
	 * CONSTRUCTOR
	 */
	public SendLabAction(){
		Auth.authCheck(false);
	}
	
	public SendLabModel getSendLabModel() {
		return sendLabModel;
	}
	public void setSendLabModel(SendLabModel sendLabModel) {
		this.sendLabModel = sendLabModel;
	}
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		LabDB labDB = new LabDB();
		List lablist = labDB.Get_LabList("", "");
		request.setAttribute("lablist", lablist);
		 
		ServiceDB serviceDB = new ServiceDB();
		List servicelist = serviceDB.Get_ServiceList("", "", "");
		request.setAttribute("servicelist", servicelist);
		 
		SendLabDB sendLabDB = new SendLabDB();
		List sendlablist = sendLabDB.Get_SendLabList("", "", "", "", "", "", "", "");
		request.setAttribute("sendlablist", sendlablist);
		
		List treatmentlist = sendLabDB.Get_TreatmentList("");
		request.setAttribute("treatmentlist", treatmentlist);
		
		return SUCCESS;
	}
	
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		SendLabDB sendLabDB = new SendLabDB();
		  
		String save 			= 	request.getParameter("save");  
		String savetime 		=	request.getParameter("savetime");
		String savereceivelab 	=	request.getParameter("savereceivelab");
		String search			=	request.getParameter("search");
		String saveedit		=	request.getParameter("saveedit");
		 
		if(save!=null){
			String service_id 		= sendLabModel.getService_id();
			String lab_id 			= sendLabModel.getLab_id();
			String treatment_id		= sendLabModel.getTreatment_id();
			String treatment_code	= sendLabModel.getTreatment_code();
			String doctor_id		= sendLabModel.getDoctor_id();
			String est_fee			= sendLabModel.getEst_fee();
			String required_date	= sendLabModel.getRequired_date(); 
			String remark			= sendLabModel.getRemark();
			
			sendLabDB.AddLab(service_id, lab_id, treatment_id, treatment_code, doctor_id, est_fee, required_date, remark);
		}
		if(saveedit!=null){
			String service_id 		= sendLabModel.getUp_service_id();
			String lab_id 			= sendLabModel.getUp_lab_id();
			String treatment_code	= sendLabModel.getUp_treatment_code();
			String doctor_id		= sendLabModel.getUp_doctor_id();
			String est_fee			= sendLabModel.getUp_est_fee();
			String required_date	= sendLabModel.getUp_required_date(); 
			String remark			= sendLabModel.getUp_remark();
			String ref_id			= sendLabModel.getRef_id(); 
			String id				= sendLabModel.getId();
			
			sendLabDB.InsertRefLab(service_id, lab_id, treatment_code, doctor_id, est_fee, required_date, remark, ref_id, id);
		}
		if(savetime!=null){
			String id 				= sendLabModel.getId();
			String return_lab		= sendLabModel.getReturn_lab();
			String timebegin		= sendLabModel.getTimebegin();
			String timeend			= sendLabModel.getTimeend();
			  
			String title			= sendLabModel.getTitle();
			String hn				= sendLabModel.getHn();
			String doctor_id		= sendLabModel.getDoctor_id();
			String room				= sendLabModel.getRoom();
			String confirm_status	= sendLabModel.getConfirm_status(); 
			
			sendLabDB.UpdateTimeLab(id, return_lab, timebegin, timeend, title, hn, doctor_id, room, confirm_status);
		}
		
		if(savereceivelab!=null){
			String id 				= sendLabModel.getId();
			String update_date		= sendLabModel.getUpdate_date();
			String ref_invoice		= sendLabModel.getRef_invoice();
			String lab_fee			= sendLabModel.getLab_fee();
			String lab_status		= sendLabModel.getLab_status();
			
			sendLabDB.UpdateLab(id, update_date, ref_invoice, lab_fee, lab_status);
		}
		
		if(search!=null){
			String s_service_name 		= sendLabModel.getS_service_name();
			String s_lab_name			= sendLabModel.getS_lab_name();
			String s_doctor_name		= sendLabModel.getS_doctor_name();
			String s_treatment_code		= sendLabModel.getS_treatment_code();	 
			String req_date_from 		= sendLabModel.getReq_date_from();
			String req_date_to			= sendLabModel.getReq_date_to();
			String upd_date_from		= sendLabModel.getUpd_date_from();
			String upd_date_to			= sendLabModel.getUpd_date_to();
			
			List sendlablist = sendLabDB.Get_SendLabList(s_service_name, s_lab_name, s_doctor_name, s_treatment_code,
					req_date_from, req_date_to, upd_date_from, upd_date_to);
			request.setAttribute("sendlablist", sendlablist);
		}else{ 
			List sendlablist = sendLabDB.Get_SendLabList("", "", "", "", "", "", "", "");
			request.setAttribute("sendlablist", sendlablist);
		}
		
		LabDB labDB = new LabDB();
		List lablist = labDB.Get_LabList("", "");
		request.setAttribute("lablist", lablist);
		 
		ServiceDB serviceDB = new ServiceDB();
		List servicelist = serviceDB.Get_ServiceList("", "", "");
		request.setAttribute("servicelist", servicelist); 
		
		List treatmentlist = sendLabDB.Get_TreatmentList("");
		request.setAttribute("treatmentlist", treatmentlist);
		
		return SUCCESS;
	} 
	
	public String window() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		SendLabDB sendLabDB = new SendLabDB();
		
		String id = request.getParameter("id");
		id = sendLabDB.Get_RefID(id);
		
		List refList = sendLabDB.Get_Ref_List(id);
		request.setAttribute("refList", refList); 
		
		request.setAttribute("id", id);
		
		return SUCCESS;
	}
	public void makePatientSession(String userHN){
		
		PatientModel patModel;
		ServicePatientModel servicePatModel;
		List<PatientFileIdModel> patBranchHnList = null;
		
		if(new Validate().Check_String_notnull_notempty(userHN)){
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			PatientData patData = new PatientData();
			BranchData branchData = new BranchData();
			patModel = patData.selectPatientByHN(userHN);
			patModel.setHnFormat(GeneratePatientBranchID.hnFormat(patModel.getHn()));
			
			/**
			 * GET AGE BY BIRTH DATE.
			 */
			patModel.setAge(GeneratePatientBranchID.calculateAge(patModel.getBirth_date()));
			
			/**
			 * GET PATIENT'S PHONE NUMBER.
			 */
			patModel.setListTelModel(patData.getPatientPhone(userHN));
			
			/**
			 * GET PATIENT'S EMERGENCY PHONE NUMBER.
			 */
			TelephoneData tData = new TelephoneData();
			List<TelephoneModel> telList = tData.getEmergencyTelByHN(patModel.getHn());
			for(TelephoneModel tModel : telList){
				patModel.setEmTellID(tModel.getTel_id());
				patModel.setEmTellRelevantPerson(tModel.getRelevant_person());
				patModel.setEmTellNumber(tModel.getTel_number());
				patModel.setEmRelative(tModel.getTel_relative());
			}
			
			/**
			 * GET PATIENT'S ADDRESS.
			 */
			patModel.setAddrModel(patData.getPatientAddr(userHN));
			
			/**
			 * GET PATIENT'S NEEDS.
			 */
			patModel.setPatneed_message(patData.getPatientNeed(userHN));
			
			/**
			 * GET PATIENT'S ALLERGIC.
			 */
			patModel.setBeallergic(patData.getListBeallergic(userHN));

			/**
			 * GET Document Need.
			 */
			patModel.setDocumentneed(patData.getListDocument(userHN));
			PatContypeData aPatContypeData = new PatContypeData();
			patModel.setContypeList(aPatContypeData.getListContype(userHN, 0));
			/**
			 * GET PATIENT'S CONGENITAL DISEASE.
			 */
			patModel.setCongenital_disease(patData.getPatientCongenitalDisease(userHN));
			
			List<CongenitalDiseaseModel> congenList = new ArrayList<CongenitalDiseaseModel>();
			
			for(String name_th :patModel.getCongenital_disease()){
				CongenitalDiseaseModel conMo = new CongenitalDiseaseModel();
				conMo.setCongenital_name_th(name_th);
				congenList.add(conMo);
			}
			patModel.setCongenList(congenList);
			
			/**
			 * GET PATIENT'S CONTYPE.
			 */
			PatContypeData patContypeData = new PatContypeData();
			patModel.setContypeList(patContypeData.getListContype(userHN, 1));
			
			/**
			 * GET BRANCH HN CODE.
			 */
			@SuppressWarnings("unchecked")
			HashMap<String, AuthModel> userSession = (HashMap<String, AuthModel>)session.getAttribute("userSession");
			AuthModel authModel = userSession.get("userEmployee");
			String branchID = branchData.getBranchHNExist(patModel.getHn(), authModel.getBranchID());
			if(branchID != null){
				patModel.setHnBranch(branchID);
			}
			
			servicePatModel = new ServicePatientModel(patModel);	
			session.setAttribute("ServicePatientModel", servicePatModel);
			session.setAttribute("patBranchHnList", patBranchHnList);
		} 
	}
}
