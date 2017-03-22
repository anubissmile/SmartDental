package com.smict.schedule.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.auth.AuthModel;
import com.smict.person.data.BranchData;
import com.smict.person.data.DoctorData;
import com.smict.person.data.TreatmentRoomData;
import com.smict.person.model.BranchModel;
import com.smict.person.model.DoctorModel;
import com.smict.person.model.TreatmentRoomModel;

public class ScheduleAction extends ActionSupport{
	
	/**
	 * MODEL
	 */
	private DoctorModel doctorModel;
	private BranchModel branchModel;
	private TreatmentRoomModel treatmentRoomModel;
	
	/**
	 * DATA
	 */
	private DoctorData doctorData = new DoctorData();
	private BranchData branchData = new BranchData();
	private TreatmentRoomData treatmentRoomData = new TreatmentRoomData();
	
	
	/**
	 * MAP & LIST
	 */
	private HashMap<String, String> doctorMap = new HashMap<String, String>();
	private HashMap<String, String> trMap = new HashMap<String, String>();
	private List<DoctorModel> doctorList = new ArrayList<DoctorModel>();
	private List<TreatmentRoomModel> trList = new ArrayList<TreatmentRoomModel>();
	
	@SuppressWarnings("unchecked")
	public String dentistScheduleForm(){
		
		/**
		 * GET DOCTOR LIST.
		 */
		doctorList = doctorData.getDentistList(null);
		for(DoctorModel dm : doctorList){
			doctorMap.put(Integer.valueOf(dm.getDoctorID()).toString(), dm.getFirstname_th() + " " + dm.getLastname_th());
		}

		/**
		 * GET TREATMENT ROOM LIST.
		 */
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		HashMap<String, AuthModel> userSession = new HashMap<String, AuthModel>();
		AuthModel authModel = new AuthModel();
		userSession = (HashMap<String, AuthModel>) session.getAttribute("userSession");
		authModel = userSession.get("userEmployee");
		System.out.println("==========================\n" + authModel.getBranchID() + "\n=======================");
		trList = treatmentRoomData.findRoomByBranchCode(authModel.getBranchCode());
		for(TreatmentRoomModel tm : trList){
			trMap.put(String.valueOf(tm.getRoom_id()).toString(), tm.getRoom_name());
		}
		
		return SUCCESS;
	}


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
}
