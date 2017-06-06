package com.smict.schedule.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.auth.AuthModel;
import com.smict.person.data.BranchData;
import com.smict.person.data.DoctorData;
import com.smict.person.data.EmployeeData;
import com.smict.person.data.TreatmentRoomData;
import com.smict.person.model.BranchModel;
import com.smict.person.model.DoctorModel;
import com.smict.person.model.Person;
import com.smict.person.model.TreatmentRoomModel;
import com.smict.schedule.data.ScheduleData;
import com.smict.schedule.model.ScheduleModel;
import com.sun.net.httpserver.Authenticator.Success;

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
	private Person person;
	
	/**
	 * DATA
	 */
	private DoctorData doctorData = new DoctorData();
	private BranchData branchData = new BranchData();
	private TreatmentRoomData treatmentRoomData = new TreatmentRoomData();
	private ScheduleData schData = new ScheduleData();
	private EmployeeData empData = new EmployeeData();
	
	
	/**
	 * MAP & LIST
	 */
	private HashMap<String, String> doctorMap = new HashMap<String, String>();
	private HashMap<String, String> trMap = new HashMap<String, String>();
	private List<DoctorModel> doctorList = new ArrayList<DoctorModel>();
	private List<TreatmentRoomModel> trList = new ArrayList<TreatmentRoomModel>();
	private List<ScheduleModel> schList = new ArrayList<ScheduleModel>();
	private List<Person> personList = new ArrayList<Person>();
	private Map<String, String> doctorWorkList, doctorRoom ;
	private List<ScheduleModel> doctorListRoom;
	/**
	 * ETC
	 */
	private int[] chkEmpId;
	
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
		getAssistantList();
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
	 * Get employee assistant list.
	 * @author anubissmile
	 */
	public void getAssistantList(){
		personList = empData.getAssistantList();
	}
	
	/**
	 * ======================================================================================= *
	 */

	
	
	/**
	 * GETTER & SETTER ZONE.
	 */
	/**
	 * @return the doctorModel
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String DentiStscheduleCheck() throws IOException, Exception{
		ScheduleData schData = new ScheduleData();
		setDoctorWorkList(schData.Get_DoctorlistForWork());
		setSchList(schData.ListDoctorWorkDayCheck());
		
		return SUCCESS;
	}
	public String AddDentistEmergency() throws IOException, Exception{
		schModel.setBranchId(Integer.valueOf(Auth.user().getBranchCode()));
		schModel.setCheckInStatus("1");
		schModel.setCheckInDateTime("0000-00-00 00:00:01");
		schModel.setCheckOutDateTime("0000-00-00 00:00:01");
		ScheduleData schData = new ScheduleData();
		DateUtil dateU = new DateUtil();
		schModel.setWorkHour(dateU.getMinutes(schModel.getStartDateTime(),schModel.getEndDateTime()));
		if(schModel.getWorkHour()>0){
			int rec =  schData.InsertDentistEmergency(schModel);
			if(rec>0){ 
					addActionMessage("เพิ่มแพทย์ฉุกเฉินสำเร็จ");
					setDoctorWorkList(schData.Get_DoctorlistForWork());
					setSchList(schData.ListDoctorWorkDayCheck());
				
			}else{
				addActionError("เพิ่มแพทย์ฉุกเฉินไม่สำเร็จ");
				setDoctorWorkList(schData.Get_DoctorlistForWork());
				setSchList(schData.ListDoctorWorkDayCheck());
			}
		}else{
			addActionError("เพิ่มแพทย์ฉุกเฉินไม่สำเร็จ เพราะช่วงเวลาผิด!");
			setDoctorWorkList(schData.Get_DoctorlistForWork());
			setSchList(schData.ListDoctorWorkDayCheck());
			
		}
		
		return INPUT;
	}
	public String DentistScheduleCheckinRoom() throws IOException, Exception{
		ScheduleData schData = new ScheduleData();
		setDoctorRoom(schData.Get_DoctorRoom());
		setSchList(schData.ListDoctorWorkDayIsCheckIn());
		setDoctorListRoom(schData.ListDoctorIsInRoom());
		personList = empData.getAssistantList();
		return SUCCESS;
	}
	public String DentistCheckinRoomWithEmpolyee() throws IOException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		ScheduleData schData = new ScheduleData();
		/*String[] empId = request.getParameterValues("chkEmpId");*/
		String[] empId = person.getEmp_ID_arr();
		if(empId != null & empId.length <= 2){
			int rec = schData.DoctorUpdateRoom(schModel);
			schData.DeleteEmpCheckInRoom(schModel);
			int res = schData.EmpCheckingIn(schModel,empId);
				if(rec > 0 & res>0){
					addActionMessage("เพิ่มแพทย์เข้าห้องสำเร็จ");
					setDoctorRoom(schData.Get_DoctorRoom());
					setSchList(schData.ListDoctorWorkDayIsCheckIn());
					setDoctorListRoom(schData.ListDoctorIsInRoom());
					personList = empData.getAssistantList();
				}
		}else if(empId.length > 2){
			addActionError("เพิ่มแพทย์เข้าห้องไม่สำเร็จ เพราะเลือกผู้ช่วยแพทย์เกิน 2 คน!");
			setDoctorRoom(schData.Get_DoctorRoom());
			setSchList(schData.ListDoctorWorkDayIsCheckIn());
			setDoctorListRoom(schData.ListDoctorIsInRoom());
			personList = empData.getAssistantList();
		}else{
			addActionError("กรุณาเลือกผู้ช่วยแพทย์!");
			setDoctorRoom(schData.Get_DoctorRoom());
			setSchList(schData.ListDoctorWorkDayIsCheckIn());
			setDoctorListRoom(schData.ListDoctorIsInRoom());
			personList = empData.getAssistantList();
		}

		return INPUT;
	}
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

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return the personList
	 */
	public List<Person> getPersonList() {
		return personList;
	}

	/**
	 * @param personList the personList to set
	 */
	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}

	/**
	 * @return the chkEmpId
	 */
	public int[] getChkEmpId() {
		return chkEmpId;
	}

	/**
	 * @param chkEmpId the chkEmpId to set
	 */
	public void setChkEmpId(int[] chkEmpId) {
		this.chkEmpId = chkEmpId;
	}

	public Map<String, String> getDoctorWorkList() {
		return doctorWorkList;
	}

	public void setDoctorWorkList(Map<String, String> doctorWorkList) {
		this.doctorWorkList = doctorWorkList;
	}

	public Map<String, String> getDoctorRoom() {
		return doctorRoom;
	}

	public void setDoctorRoom(Map<String, String> doctorRoom) {
		this.doctorRoom = doctorRoom;
	}

	public List<ScheduleModel> getDoctorListRoom() {
		return doctorListRoom;
	}

	public void setDoctorListRoom(List<ScheduleModel> doctorListRoom) {
		this.doctorListRoom = doctorListRoom;
	}


	
}
