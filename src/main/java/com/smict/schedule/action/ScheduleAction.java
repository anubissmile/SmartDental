package com.smict.schedule.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.lucene.search.FieldComparator.ShortComparator;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.auth.AuthModel;
import com.smict.person.data.BranchData;
import com.smict.person.data.DoctorData;
import com.smict.person.data.TreatmentRoomData;
import com.smict.person.model.BranchModel;
import com.smict.person.model.DoctorModel;
import com.smict.person.model.TreatmentRoomModel;
import com.smict.schedule.data.ScheduleData;
import com.smict.schedule.model.ScheduleModel;

import ldc.util.Auth;
import ldc.util.DateUtil;

@SuppressWarnings("serial")
public class ScheduleAction extends ActionSupport{
	
	/**
	 * MODEL
	 */
	private DoctorModel doctorModel;
	private BranchModel branchModel;
	private TreatmentRoomModel treatmentRoomModel;
	private ScheduleModel schModel;
	
	/**
	 * DATA
	 */
	private DoctorData doctorData = new DoctorData();
	private BranchData branchData = new BranchData();
	private TreatmentRoomData treatmentRoomData = new TreatmentRoomData();
	private ScheduleData schData = new ScheduleData();
	
	
	/**
	 * MAP & LIST
	 */
	private HashMap<String, String> doctorMap = new HashMap<String, String>();
	private HashMap<String, String> trMap = new HashMap<String, String>();
	private List<DoctorModel> doctorList = new ArrayList<DoctorModel>();
	private List<TreatmentRoomModel> trList = new ArrayList<TreatmentRoomModel>();
	
	
	/**
	 * CONSTRUCTOR.
	 */
	public ScheduleAction(){
		/**
		 * AUTH CHECKING.
		 */
		Auth.authCheck(false);
	}
	
	@SuppressWarnings("unchecked")
	public String dentistScheduleForm(){
		
		/**
		 * GET DOCTOR LIST.
		 */
		getDoctorDropDown();

		/**
		 * GET TREATMENT ROOM LIST.
		 */
		getTreatmentRoomDropDown();
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String addDentistSchedule(){
		/**
		 * FETCH BRANCH ID.
		 */
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		HashMap<String, AuthModel> userSession = new HashMap<String, AuthModel>();
		userSession = (HashMap<String, AuthModel>) session.getAttribute("userSession");
		schModel.setBranchId(Integer.valueOf(userSession.get("userEmployee").getBranchCode()));
		
		/**
		 * SET THE DEFAULTS VALUE;
		 */
		schModel.setCheckInStatus("0");
		schModel.setCheckInDateTime("0000-00-00 00:00:00");
		schModel.setCheckOutDateTime("0000-00-00 00:00:00");
		
		/**
		 * ADD NEW SCHEDULE.
		 */
		schData.insertDentistSchedule(schModel);
		
		schModel = new ScheduleModel();
		getDoctorDropDown();
		getTreatmentRoomDropDown();
		addActionMessage("Add dentist's schedule success!");
		return INPUT;
	}
	
	/**
	 * validate;
	 */
	public void validateAddDentistSchedule(){
		String msg = "";
		DateUtil dateUtl = new DateUtil();
		
		/**
		 * CONCAT DATE TIME.
		 */
		schModel.setStartDateTime(schModel.getWorkDate() + " " + schModel.getStartTime());
		schModel.setEndDateTime(schModel.getWorkDate() + " " + schModel.getEndTime());
		
		if(schModel.getWorkDate() == null || schModel.getWorkDate().equals("")){
			msg = "Please fill working date.";
		}
		
		if(schModel.getStartTime() == null || schModel.getStartTime().equals("")){
			msg += "Please fill start working time.";
		}
		
		if(schModel.getEndTime() == null || schModel.getEndTime().equals("")){
			msg += "Please fill end working time.";
		}


		/**
		 * FETCH MINUTES DIFF.
		 */
		if(msg.equals("")){
			schModel.setWorkHour(dateUtl.getMinutesDiff(
				schModel.getStartDateTime(), 
				schModel.getEndDateTime()
			));
			
			if(schModel.getWorkHour() < 0){
				msg += "Your range of time was wrong!";
			}
		}
		
		if(!msg.equals("")){
			getDoctorDropDown();
			getTreatmentRoomDropDown();
			addActionError(msg);
		}
	}
	
	/**
	 * UTILIZE METHOD.
	 */
	
	/**
	 * Get treatment room list for dropdown.
	 * @author anubissmile
	 */
	@SuppressWarnings("unchecked")
	public void getTreatmentRoomDropDown(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		HashMap<String, AuthModel> userSession = new HashMap<String, AuthModel>();
		AuthModel authModel = new AuthModel();
		userSession = (HashMap<String, AuthModel>) session.getAttribute("userSession");
		authModel = userSession.get("userEmployee");

		trList = treatmentRoomData.findRoomByBranchCode(authModel.getBranchCode());
		for(TreatmentRoomModel tm : trList){
			trMap.put(String.valueOf(tm.getRoom_id()).toString(), tm.getRoom_name());
		}
	}
	
	/**
	 * Get doctor list for dropdown.
	 * @author anubissmile
	 */
	public void getDoctorDropDown(){
		
		doctorList = doctorData.getDentistList(null);
		for(DoctorModel dm : doctorList){
			doctorMap.put(Integer.valueOf(dm.getDoctorID()).toString(), dm.getFirstname_th() + " " + dm.getLastname_th());
		}
	}
	
	/**
	 * ======================================================================================= *
	 */

	/**
	 * GETTER & SETTER ZONE.
	 */
	
	public DoctorModel getDoctorModel() {
		return doctorModel;
	}

	public void setDoctorModel(DoctorModel doctorModel) {
		this.doctorModel = doctorModel;
	}

	public BranchModel getBranchModel() {
		return branchModel;
	}

	public void setBranchModel(BranchModel branchModel) {
		this.branchModel = branchModel;
	}


	public TreatmentRoomModel getTreatmentRoomModel() {
		return treatmentRoomModel;
	}


	public void setTreatmentRoomModel(TreatmentRoomModel treatmentRoomModel) {
		this.treatmentRoomModel = treatmentRoomModel;
	}
	
	
	public HashMap<String, String> getDoctorMap() {
		return doctorMap;
	}


	public void setDoctorMap(HashMap<String, String> doctorMap) {
		this.doctorMap = doctorMap;
	}


	public HashMap<String, String> getTrMap() {
		return trMap;
	}


	public void setTrMap(HashMap<String, String> trMap) {
		this.trMap = trMap;
	}


	public List<DoctorModel> getDoctorList() {
		return doctorList;
	}


	public void setDoctorList(List<DoctorModel> doctorList) {
		this.doctorList = doctorList;
	}


	public List<TreatmentRoomModel> getTrList() {
		return trList;
	}


	public void setTrList(List<TreatmentRoomModel> trList) {
		this.trList = trList;
	}


	/**
	 * @return the schModel
	 */
	public ScheduleModel getSchModel() {
		return schModel;
	}


	/**
	 * @param schModel the schModel to set
	 */
	public void setSchModel(ScheduleModel schModel) {
		this.schModel = schModel;
	}
	/**
	 * ======================================================================================= *
	 */
}
