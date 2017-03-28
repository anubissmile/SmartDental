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
import com.smict.schedule.data.ScheduleData;
import com.smict.schedule.model.ScheduleModel;

import ldc.util.Auth;
import ldc.util.DateUtil;
import ldc.util.Validate;

@SuppressWarnings("serial")
public class ViewScheduleAction extends ActionSupport{
	
	/**
	 * MODEL
	 */
	private DoctorModel doctorModel;
	private BranchModel branchModel;
	private TreatmentRoomModel treatmentRoomModel;
	private ScheduleModel schModel;
	private AuthModel authModel = new AuthModel();
	
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
	private List<ScheduleModel> schList = new ArrayList<ScheduleModel>();
	
	/**
	 * CONSTRUCTOR.
	 */
	public ViewScheduleAction(){
		Auth.authCheck(false);
	}
	
	/**
	 * ACTION
	 */
	
	public String viewDentistSchedule(){
		getTreatmentRoomDropDown();
		return SUCCESS;
	}
	
	public String viewDentistScheduleAction(){
		schList = schData.fetchDentistSchedule(schModel);
		getTreatmentRoomDropDown();
		return SUCCESS;
	}

	public void validateViewDentistScheduleAction(){
		Validate v = new Validate();
		String msg = null;
		if(!v.Check_String_notnull_notempty(schModel.getWorkDate())){
			msg = "Please fill the date.";
		}else if(!v.Check_String_notnull_notempty(String.valueOf(schModel.getBranchRoomId()))){
			msg = "Please select treatment room.";
		}
		
		if(v.Check_String_notnull_notempty(msg)){
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
	public void getTreatmentRoomDropDown(){
		authModel = Auth.user();
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
	/**
	 * @return the doctorModel
	 */
	public DoctorModel getDoctorModel() {
		return doctorModel;
	}

	/**
	 * @param doctorModel the doctorModel to set
	 */
	public void setDoctorModel(DoctorModel doctorModel) {
		this.doctorModel = doctorModel;
	}

	/**
	 * @return the branchModel
	 */
	public BranchModel getBranchModel() {
		return branchModel;
	}

	/**
	 * @param branchModel the branchModel to set
	 */
	public void setBranchModel(BranchModel branchModel) {
		this.branchModel = branchModel;
	}

	public TreatmentRoomModel getTreatmentRoomModel() {
		return treatmentRoomModel;
	}

	public void setTreatmentRoomModel(TreatmentRoomModel treatmentRoomModel) {
		this.treatmentRoomModel = treatmentRoomModel;
	}

	public ScheduleModel getSchModel() {
		return schModel;
	}

	public void setSchModel(ScheduleModel schModel) {
		this.schModel = schModel;
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
	 * @return the schList
	 */
	public List<ScheduleModel> getSchList() {
		return schList;
	}

	/**
	 * @param schList the schList to set
	 */
	public void setSchList(List<ScheduleModel> schList) {
		this.schList = schList;
	}
	
}
