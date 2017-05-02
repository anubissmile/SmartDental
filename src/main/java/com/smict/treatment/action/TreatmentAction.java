package com.smict.treatment.action;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ServicePatientModel;
import com.smict.all.model.ToothModel;
import com.smict.person.data.PatientData;
import com.smict.schedule.data.ScheduleData;
import com.smict.schedule.model.ScheduleModel;
import com.smict.treatment.data.ToothMasterData;
import com.smict.treatment.data.TreatmentData;
import com.smict.treatment.data.TreatmentMasterData;
import com.sun.xml.bind.api.impl.NameConverter.Standard;

import ldc.util.Auth;


@SuppressWarnings("serial")
public class TreatmentAction extends ActionSupport{
	ServicePatientModel servicePatModel; 
	String alertStatus, alertMessage;
	
	/**
	 * CONSTRUCTOR
	 */
	private List<ScheduleModel> schList = new LinkedList<ScheduleModel>();	
	private ScheduleModel schModel;
	public ScheduleModel getSchModel() {
		return schModel;
	}

	public void setSchModel(ScheduleModel schModel) {
		this.schModel = schModel;
	}

	public List<ScheduleModel> getSchList() {
		return schList;
	}

	public void setSchList(List<ScheduleModel> schList) {
		this.schList = schList;
	}

	public TreatmentAction(){
		Auth.authCheck(false);
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
	public String BeginTreatment() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		TreatmentData TreatData = new TreatmentData();
		
		setSchList(TreatData.DoctorReadyToWork());
		/*if(session.getAttribute("ServicePatientModel")!=null){
			TreatmentData treatmentdb = new TreatmentData(); 
			String hn			= servicePatModel.getHn();
			int treatment_id 	= treatmentdb.Select_Treatment_ID(hn); 
				
			TreatmentMasterData treatmentMasterData = new TreatmentMasterData();
			List treatmentMasterList = treatmentMasterData.select_treatment_master_history(hn, treatment_id);
			request.setAttribute("treatmentMasterList", treatmentMasterList);
			
			List drugList = treatmentMasterData.select_drug(null);
			request.setAttribute("drugList", drugList);
		 
			List transectionTreatmentList = treatmentdb.transectionTreatment(hn, treatment_id);
			request.setAttribute("transectionTreatmentList", transectionTreatmentList); 
			setToothList(request);//set parameter tooth img
		}else{
				alertStatus = "danger";
				alertMessage = "กรุณาเลือกคนไข้ก่อนทำรายการ";
				return "getCustomer"; 
		} */
		
		return SUCCESS;
	}
	
	public String waiting() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		TreatmentData treatmentdb = new TreatmentData(); 
		  
		String savewaiting 	= 	request.getParameter("savewaiting");
	  
		if(savewaiting!=null){  
			servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
			String hn 				= servicePatModel.getHn(); 
			int treatment_id 	= treatmentdb.Select_Treatment_ID(hn); 
			 
			int room_id				= 0;
			String status			= "W";
			
		  /*List patient = new ArrayList(); 
			patient 				= (List) session.getAttribute("patient");  
			String hn 				= (String) patient.get(0);
			String room_id			= "0";
			String status			= "W"; */
			
			treatmentdb.AddTreatmentWaiting(hn, room_id, status); 
			//servicePatModel = treatmentdb.select_TP(hn);
			servicePatModel = new ServicePatientModel(new PatientData().getPatModel_patient(servicePatModel));
			servicePatModel.setStatus(new PatientData().getPatStatus(hn));  // working teartment button 
			session.setAttribute("ServicePatientModel", servicePatModel);
			setToothList(request);//set parameter tooth img
			
			TreatmentMasterData treatmentMasterData = new TreatmentMasterData();
			List treatmentMasterList = treatmentMasterData.select_treatment_master_history(hn, treatment_id);
			request.setAttribute("treatmentMasterList", treatmentMasterList);
			
			if(session.getAttribute("ServicePatientModel")!=null){  
				List transectionTreatmentList = treatmentdb.transectionTreatment(hn, treatment_id);
				request.setAttribute("transectionTreatmentList", transectionTreatmentList); 
			}
		}   
		return SUCCESS;
	}  
	
	public String alert() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		TreatmentData treatmentdb = new TreatmentData(); 
		
		String hn = request.getParameter("hn"); 
		int treatment_id 	= treatmentdb.Select_Treatment_ID(hn);
		servicePatModel = new ServicePatientModel();
		servicePatModel.setHn(hn);
		//servicePatModel = treatmentdb.select_TP(hn);
		servicePatModel = new ServicePatientModel(new PatientData().getPatModel_patient(servicePatModel));
		
		if(hn!=null){  
			List transectionTreatmentList = treatmentdb.transectionTreatment(hn, treatment_id);
			request.setAttribute("transectionTreatmentList", transectionTreatmentList); 
			
		}else{
			alertStatus = "danger";
			alertMessage = "กรุณาเลือกคนไข้ก่อนทำรายการ";
			return "getCustomer";
		}
			TreatmentMasterData treatmentMasterData = new TreatmentMasterData();
			List treatmentMasterList = treatmentMasterData.select_treatment_master_history(hn, treatment_id);
			request.setAttribute("treatmentMasterList", treatmentMasterList);
		 
		session.setAttribute("ServicePatientModel", servicePatModel);
		
		setToothList(request);//set parameter tooth img
		 
		return SUCCESS;
	}
	
	public String treatmentsuccess() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		TreatmentData treatmentdb = new TreatmentData(); 
		
		String savesuccess 	= 	request.getParameter("savesuccess");
		if(savesuccess!=null){  
		
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		String hn 				= servicePatModel.getHn();
		int treatment_id 	= treatmentdb.Select_Treatment_ID(hn);
		String status			= "S";
		treatmentdb.UpdateTreatmentingToSuccess(hn, status);
		
		servicePatModel = treatmentdb.select_TP(hn);
		
		if(session.getAttribute("ServicePatientModel")!=null){  
			List transectionTreatmentList = treatmentdb.transectionTreatment(hn, treatment_id);
			request.setAttribute("transectionTreatmentList", transectionTreatmentList); 
		}
		session.setAttribute("ServicePatientModel", servicePatModel);
		}
		setToothList(request);//set parameter tooth img
		
		return SUCCESS;
	}  
	
	public String treatmentHistory() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		TreatmentData treatmentdb = new TreatmentData(); 
		 
		if(session.getAttribute("ServicePatientModel")!=null){
		
			String savehistory 	= 	request.getParameter("savehistory");
			int treatment_id = 0;
			if(savehistory!=null){   
			 
				String treatment_code	= servicePatModel.getTreatment_code();
				int doctor_id			= servicePatModel.getDoctor_id();
				String tooth_tooth		= servicePatModel.getTooth_tooth();
				String surf_tooth		= servicePatModel.getSurf_tooth();
				String surf				= servicePatModel.getSurf();
				String quadrant			= servicePatModel.getQuadrant();
				String arch				= servicePatModel.getArch(); 
				
				servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
				String hn 				= servicePatModel.getHn();
				
				treatment_id 	= treatmentdb.Select_Treatment_ID(hn); 
				List product_id = treatmentdb.Select_ProductID(treatment_code);   
				if(product_id!=null){  
					for(int i=0; i<product_id.size(); i++){ 
						int product_free	= treatmentdb.Select_DrugFree(Integer.valueOf(product_id.get(i).toString()), treatment_code);
						treatmentdb.TreatmentHistoryProduct(treatment_id, Integer.valueOf(product_id.get(i).toString()), product_free, 0);
					}
					
				}
				
				if(tooth_tooth!=null&&!tooth_tooth.equals("")){ 
					surf 				= "B";
					String tooth		= tooth_tooth;
					String tooth_range 	= "";
					String tooth_type 	= "tooth";
					
					treatmentdb.AddTreatmentHistory(treatment_id, hn, treatment_code, doctor_id, surf, tooth, tooth_range, tooth_type, 0);
				}else if(surf_tooth!=null&&surf!=null&&!surf_tooth.equals("")&&!surf.equals("")){ 
					String surfS = "", surfU = "";  
					for(int i=0,j=1; i<surf.length(); i++,j++){  
						surfU = surf.substring(i,j);
						surfS += ","+surfU;
						if(i==0) surfS = surfS.replace(",", "");
					}
					surf 				= surfS;
					String tooth		= surf_tooth;
					String tooth_range 	= "";
					String tooth_type = "surf_tooth";
					treatmentdb.AddTreatmentHistory(treatment_id, hn, treatment_code, doctor_id, surf, tooth, tooth_range, tooth_type, 0);
				}else if(quadrant!=null&&!quadrant.equals("")){  
					String tooth		= quadrant; 
					String tooth_range 	= "";
					String tooth_type = "quadrant";
					treatmentdb.AddTreatmentHistory(treatment_id, hn, treatment_code, doctor_id, surf, tooth, tooth_range, tooth_type, 0); 
				}else if(arch!=null&&!arch.equals("")){ 
					surf 				= "";
					String tooth		= arch;
					String tooth_range 	= "";
					String tooth_type = "arch";
					treatmentdb.AddTreatmentHistory(treatment_id, hn, treatment_code, doctor_id, surf, tooth, tooth_range, tooth_type, 0); 
				}else{
					// Mouth, SexTant
					surf 				= "B";
					String tooth		= "11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28,31,32,33,34,35,36,37,38,41,42,43,44,45,46,47,48";
					String tooth_range 	= "";
					String tooth_type = "Mouth_SexTant";
					treatmentdb.AddTreatmentHistory(treatment_id, hn, treatment_code, doctor_id, surf, tooth, tooth_range, tooth_type, 0);
				}
				
			//	servicePatModel = treatmentdb.select_TP(hn);
				
				if(session.getAttribute("ServicePatientModel")!=null){  
			//		List transectionTreatmentList = treatmentdb.transectionTreatment(servicePatModel.getHn());
					List transectionTreatmentList = treatmentdb.transectionTreatment(hn, treatment_id);
					request.setAttribute("transectionTreatmentList", transectionTreatmentList);  
				}
				TreatmentMasterData treatmentMasterData = new TreatmentMasterData();
				List treatmentMasterList = treatmentMasterData.select_treatment_master_history(hn, treatment_id);
				request.setAttribute("treatmentMasterList", treatmentMasterList);
				  
				session.setAttribute("ServicePatientModel", servicePatModel);
				}  
			setToothList(request);//set parameter tooth img
		}
		return SUCCESS;
	} 
	public String treatmentContinue() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		
		if(session.getAttribute("ServicePatientModel")!=null){ 
			TreatmentData treatmentdb = new TreatmentData(); 
			
			servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
			String hn 				= servicePatModel.getHn(); 
			int treatment_id 	= treatmentdb.Select_Treatment_ID(hn);
			if(request.getParameter("doctor_id_contunus")!=null){
				String doctor_id 			= request.getParameter("doctor_id_contunus");
				String treatment_code 		= request.getParameter("continue_treatment_code");
				String continue_id 			= request.getParameter("continue_treatment_id"); 
				String continue_phase 		= request.getParameter("continue_phase");
				String continue_count 		= request.getParameter("continue_count");
				String continue_count_all 		= request.getParameter("continue_count_all");
				
				treatmentdb.UpdateTreatmentContinue(Integer.valueOf(treatment_id), Integer.valueOf(continue_id), Integer.valueOf(continue_phase), Integer.valueOf(continue_count));
				treatmentdb.AddTreatmentHistory(treatment_id, hn, treatment_code, Integer.valueOf(doctor_id), "", "", "", "", Integer.valueOf(continue_count_all)); 
				
				List product_id = treatmentdb.Select_Continue_ProductID(Integer.valueOf(continue_id), Integer.valueOf(continue_phase), Integer.valueOf(continue_count));
				if(product_id!=null){  
					for(int i=0; i<product_id.size(); i++){ 
						int product_continue	= treatmentdb.Select_DrugContinue(Integer.valueOf(product_id.get(i).toString()), 
								Integer.valueOf(continue_id), Integer.valueOf(continue_phase), Integer.valueOf(continue_count));
						treatmentdb.TreatmentHistoryProductContinue(treatment_id, Integer.valueOf(product_id.get(i).toString()), 0, product_continue);
					} 
				}
				List product_id_free = treatmentdb.Select_ProductID(treatment_code);   
				if(product_id_free!=null){  
					for(int i=0; i<product_id_free.size(); i++){ 
						int product_free	= treatmentdb.Select_DrugFree(Integer.valueOf(product_id_free.get(i).toString()), treatment_code);
						treatmentdb.TreatmentHistoryProduct(treatment_id, Integer.valueOf(product_id_free.get(i).toString()), product_free, 0);
					} 
				}
				
			}
			session.setAttribute("ServicePatientModel", servicePatModel);
			
			List transectionTreatmentList = treatmentdb.transectionTreatment(hn, treatment_id);
			request.setAttribute("transectionTreatmentList", transectionTreatmentList); 
			
			TreatmentMasterData treatmentMasterData = new TreatmentMasterData();
			List treatmentMasterList = treatmentMasterData.select_treatment_master_history(hn, treatment_id);
			request.setAttribute("treatmentMasterList", treatmentMasterList);
		} 
		
		return SUCCESS;
	} 
	public String treatmentDrug() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		TreatmentData treatmentdb = new TreatmentData(); 
		 
		if(session.getAttribute("ServicePatientModel")!=null){ 
			int treatment_id = 0; 
			servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
			String hn 				= servicePatModel.getHn(); 
			
			String savedrug 	= 	request.getParameter("savedrug");
			if(savedrug!=null){
				
				treatment_id 	= treatmentdb.Select_Treatment_ID(hn); 
			 
				if(request.getParameterValues("product_id")!=null){
					String	 product_delete 		= "(";
					String[] product_id 			= request.getParameterValues("product_id");
					String[] product_free 			= request.getParameterValues("product_free");
					String[] product_transfer 		= request.getParameterValues("product_transfer");
					 
					for(int i=0; i<product_id.length; i++){
						product_delete += "'"+product_id[i]+"',";
				 		treatmentdb.TreatmentHistoryProductDrug(treatment_id, Integer.valueOf(product_id[i]), Integer.valueOf(product_free[i]), Integer.valueOf(product_transfer[i]), 0);
					}
					product_delete += ")";
					product_delete = product_delete.replace(",)", ")");
					
					treatmentdb.TreatmentHistoryProductDrugDelete(treatment_id, product_delete);
				}else{
					treatmentdb.TreatmentHistoryProductDrugDelete(treatment_id, "('')");
				}
			
				if(session.getAttribute("ServicePatientModel")!=null){  
					List transectionTreatmentList = treatmentdb.transectionTreatment(hn, treatment_id);
					request.setAttribute("transectionTreatmentList", transectionTreatmentList); 
				}
				session.setAttribute("ServicePatientModel", servicePatModel);
			}
			TreatmentMasterData treatmentMasterData = new TreatmentMasterData();
			List treatmentMasterList = treatmentMasterData.select_treatment_master_history(hn, treatment_id);
			request.setAttribute("treatmentMasterList", treatmentMasterList); 
			setToothList(request);//set parameter tooth img
		}  
		return SUCCESS;
	}
	public String treatmentProduct() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		TreatmentData treatmentdb = new TreatmentData(); 
		TreatmentMasterData treatmentMasterData = new TreatmentMasterData(); 
		if(session.getAttribute("ServicePatientModel")!=null){
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		String hn 				= servicePatModel.getHn();
		
		String saveproduct 	= 	request.getParameter("saveproduct"); 
		int treatment_id = 0;
		if(saveproduct!=null){
		
		
		treatment_id 	= treatmentdb.Select_Treatment_ID(hn); 
		 
		if(request.getParameterValues("product_id_pro")!=null){
			String	 product_delete 		= "(";
			String[] product_id_pro 		= request.getParameterValues("product_id_pro");
			String[] qty_pro 				= request.getParameterValues("qty_pro"); 
			 
			for(int i=0; i<product_id_pro.length; i++){
				product_delete += "'"+product_id_pro[i]+"',";
		 		treatmentdb.TreatmentHistoryProductDrug(treatment_id, Integer.valueOf(product_id_pro[i]), 0, 0, Integer.valueOf(qty_pro[i]));
			}
			product_delete += ")";
			product_delete = product_delete.replace(",)", ")");
			
			treatmentdb.TreatmentHistoryProductDelete(treatment_id, product_delete);
		}else{
			treatmentdb.TreatmentHistoryProductDelete(treatment_id, "('')");
		} 
		
		if(session.getAttribute("ServicePatientModel")!=null){  
			List transectionTreatmentList = treatmentdb.transectionTreatment(hn, treatment_id);
			request.setAttribute("transectionTreatmentList", transectionTreatmentList); 
			
		}
		session.setAttribute("ServicePatientModel", servicePatModel);
		}
		
		List treatmentMasterList = treatmentMasterData.select_treatment_master_history(hn, treatment_id);
		request.setAttribute("treatmentMasterList", treatmentMasterList); 
		setToothList(request);//set parameter tooth img
		}
	 
		return SUCCESS;
	}
	public String treatmentDelete() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		TreatmentData treatmentdb = new TreatmentData(); 
		TreatmentMasterData treatmentMasterData = new TreatmentMasterData();
		int treatment_id = 0; 
		
		if(session.getAttribute("ServicePatientModel")!=null){
		 
		treatment_id			= servicePatModel.getTreatment_id();
		String treatment_code	= request.getParameter("treatment_code_delete"); //treatment code
		String treatment_mode	= servicePatModel.getTreatment_mode();
		
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		String hn 				= servicePatModel.getHn();
		 
		if(treatment_mode!=null&&treatment_mode.equals("2")){
		treatmentdb.DeleteTransectionTreatmentContinue(treatment_id, treatment_code);
		treatmentdb.UpdateTreatmentContinueIsDelete(treatment_id, treatment_code);
		} 
		treatmentdb.DeleteTransectionTreatment(treatment_id, treatment_code);
		
		  
		List transectionTreatmentList = treatmentdb.transectionTreatment(hn, treatment_id);
		request.setAttribute("transectionTreatmentList", transectionTreatmentList); 
		 
		List treatmentMasterList = treatmentMasterData.select_treatment_master_history(hn, treatment_id);
		request.setAttribute("treatmentMasterList", treatmentMasterList);
		
		session.setAttribute("ServicePatientModel", servicePatModel);
		  
		setToothList(request);//set parameter tooth img
		} 
		
		return SUCCESS;
	}
	public String print() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		
		String drugname = request.getParameter("drugname");
		byte[] bs = drugname.getBytes(StandardCharsets.ISO_8859_1);
		drugname = new String(bs, StandardCharsets.UTF_8);
		
		String pill = request.getParameter("pill");
		bs = pill.getBytes(StandardCharsets.ISO_8859_1);
		pill = new String(bs, StandardCharsets.UTF_8);
		
		String episode = request.getParameter("episode");
		bs = episode.getBytes(StandardCharsets.ISO_8859_1);
		episode = new String(bs, StandardCharsets.UTF_8);
		
		String mealstatus = request.getParameter("mealstatus");
		bs = mealstatus.getBytes(StandardCharsets.ISO_8859_1);
		mealstatus = new String(bs, StandardCharsets.UTF_8);
		
		String mealtime = request.getParameter("mealtime");
		bs = mealtime.getBytes(StandardCharsets.ISO_8859_1);
		mealtime = new String(bs, StandardCharsets.UTF_8);
		
		request.setAttribute("drugname", 	drugname);
		request.setAttribute("pill", 		"รับประทาน "+pill+" เม็ด ");
		request.setAttribute("episode", 	"วันละ "+episode+" ครั้ง ");
		request.setAttribute("mealstatus", 	mealstatus);
		request.setAttribute("mealtime", 	mealtime);
		
		return SUCCESS;
	} 
	public void setToothList(HttpServletRequest request){
		
		HttpSession session = request.getSession();  
		ToothMasterData toothData= new ToothMasterData();
		List<ToothModel> toothListUp = toothData.select_tooth_list_arch("upper");
		request.setAttribute("toothListUp", toothListUp); 
		
		List<ToothModel> toothListLow = toothData.select_tooth_list_arch("lower");
		request.setAttribute("toothListLow", toothListLow); 
		ServicePatientModel pmodel=(ServicePatientModel) session.getAttribute("ServicePatientModel");
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		
		List<ToothModel> toothHistory = toothData.get_tooth_history(pmodel.getHn());
		request.setAttribute("toothHistory", toothHistory);
	}
}
