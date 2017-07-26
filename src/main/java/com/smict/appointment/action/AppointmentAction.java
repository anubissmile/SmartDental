package com.smict.appointment.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.joda.time.LocalDate;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ServicePatientModel;
import com.smict.person.data.BranchData;
import com.smict.person.data.DoctorData;
import com.smict.person.model.DoctorModel;
import com.smict.schedule.data.ScheduleData;
import com.smict.schedule.model.ScheduleModel;

import ldc.util.Auth;
import ldc.util.DateUtil;
import net.sf.jasperreports.engine.fill.DatasetSortUtil;

@SuppressWarnings("serial")
public class AppointmentAction extends ActionSupport {
	
	/**
	 * CONSTRUCTOR
	 */
	public AppointmentAction(){
		Auth.authCheck(false);
	}
	
	private ServicePatientModel servicePatModel;
	private AppointmentModel appointmentModel;
	private AppointmentModel appointmentModelOutPut = new AppointmentModel();
	private DoctorModel doctorModel;
	private HashMap<String, String> branchMap;
	private ScheduleModel scheduleModel;
	private List<ScheduleModel> scheduleList;
	
	/**
	 * Alert messages
	 */
	private String alertError, alertSuccess, alertMSG;
	
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		return SUCCESS;
	}
	
	
	/**
	 * Add an appointment from week calendar by method POST.
	 * @return String | Action result.
	 */
	public String postAddAppointmentWeekCalendar(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getMethod().equals("POST")){
			System.out.println("This is POST");
		}else{
			System.out.println("This is POST");
		}
		return SUCCESS;
	}

	/**
	 * Get doctor's appointment list on week calendar.
	 * @author anubi
	 * @return String | Action result.
	 */
	public String getAppoinmentWeekCalendar(){
		
		return SUCCESS;
	}
	
	/**
	 * Get doctor's appointment list into week calendar by AJAX.
	 * @author anubi
	 * @return null
	 */
	public String ajaxAppoinmentWeekCalendar(){
		/**
		 * - Get doctor's appointment list today.
		 * - Get doctor list.
		 */
		ScheduleData scheduleData = new ScheduleData();
		LocalDate localDate = new LocalDate();
		if(scheduleModel == null){
			scheduleModel = new ScheduleModel();
		}
		if(scheduleList == null){
			scheduleList = new ArrayList<ScheduleModel>();
		}
		scheduleModel.setWorkDate(localDate.toString());
		scheduleList = scheduleData.fetchDentistSchedule(scheduleModel);
		/**
		 * Convert into json format like this.
		 * {'id':1, 'start': new Date(year, month, day, 12), 'end': new Date(year, month, day, 13, 30), 'title': 'Lunch with Mike', userId: 0}
		 */
		JSONArray jsonArr = new JSONArray();
		for(ScheduleModel schModel : scheduleList){
			JSONObject jsonObj = new JSONObject();
			try {
				jsonObj.put("id", schModel.getWorkDayId());
				jsonObj.put("start", schModel.getWorkDate() + ":" + schModel.getStartDateTime());
				jsonObj.put("end", schModel.getWorkDate() + ":" + schModel.getEndDateTime());
				jsonObj.put("title", "เวรลงตรวจ " + schModel.getFirst_name_th() + " " + schModel.getLast_name_th());
				jsonObj.put("userId", schModel.getDoctorId());
				jsonObj.put("doctorId", schModel.getDoctorId());
				jsonObj.put("doctor", schModel.getFirst_name_th() + " " + schModel.getLast_name_th());
				jsonObj.put("free", true);
				jsonObj.put("type", "workday");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonArr.put(jsonObj);
		}

		
		/**
		 * Write the response to JSON type.
		 */
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {
			response.getWriter().write(jsonArr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Get searching another branch id for get into calendar.
	 * @author anubi
	 * @return String | Action result.
	 */
	public String getSearchAnotherBranch(){
		BranchData branchData = new BranchData();
		if(branchMap == null){
			branchMap = new HashMap<String, String>();
		}
		try {
			branchMap = branchData.chunkBranch();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * Do searching another branch appointment.
	 * @author anubi
	 * @return String | Action result.
	 */
	public String postSearchAnotherBranch(){
		String branch = appointmentModel.getBranchCode();
		appointmentModel.setBranchID(StringUtils.split(branch, "-")[2]);
		appointmentModel.setBranchCode(StringUtils.split(branch, "-")[0]);
		return SUCCESS;
	}
	
	/**
	 * Get first page appointment.
	 * @author anubi
	 * @return String | Action result.
	 */
	public String getAppointment(){
		/**
		 * Get manager doctor by branch id.
		 */
		this.getDoctorByMgrBranch(Auth.user().getBranchID());
		return SUCCESS;
	}
	
	/**
	 * Get page make appointment form
	 * @author anubi
	 * @return String | Action result.
	 */
	public String getMakeAppointmentFrm(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		return SUCCESS;
	}
	
	/**
	 * Do making an appointment.
	 * @author anubi
	 * @return String | Action result.
	 */
	public String postMakeAppointment(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getMethod().equals("POST")){
			/**
			 * Prepare patient's HN.
			 */
			HttpSession session = request.getSession();
			servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
			appointmentModel.setHN(servicePatModel.getHn());
			
			/**
			 * Prepare date & time
			 */
			StringBuilder sb = new StringBuilder();
			String dtStart = sb.append(appointmentModel.getDate()).append(" ").append(appointmentModel.getDateStart()).append(":00").toString();
			sb.setLength(0);
			String dtEnd = sb.append(appointmentModel.getDate()).append(" ").append(appointmentModel.getDateEnd()).append(":00").toString();
			appointmentModel.setDateStart(dtStart);
			appointmentModel.setDateEnd(dtEnd);
			
			/**
			 * Insert it!
			 */
			int rec = this.postMakeAppointment(appointmentModel);
			System.out.println(rec);
		}else{
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * Displaying appointment calendar.
	 * @author anubi
	 * @return String | Action result.
	 */
	public String getViewAppointmentCalendar(){
		return SUCCESS;
	}
	
	/**
	 * Get doctor's appointment into calendar by AJAX.
	 * @author anubi
	 * @return String | Action result
	 */
	public String ajaxGetDoctorAppointmentCalendar(){
		this.getAppointmentIncoming(appointmentModel);
		if(appointmentModel.getAppoinmentList() != null){
			JSONArray jsonArr = new JSONArray();
			StringBuilder sb = new StringBuilder();
			for(AppointmentModel aModel : appointmentModel.getAppoinmentList()){
				JSONObject jsonObj = new JSONObject();
				/*backgroundColor
				id
				start
				end
				title*/
//				String date = StringUtils.split(aModel.getDateStart(), " ")[0];
				String startTime = StringUtils.split(aModel.getDateStart(), " ")[1];
				String endTime = StringUtils.split(aModel.getDateEnd(), " ")[1];
				String title = sb.append("นัดพบ ").append(aModel.getDescription()).append("\n")
						.append("คนไข้ : คุณ").append(aModel.getFirstNameTH()).append(" ").append(aModel.getLastNameTH()).append("\n")
						.append("เวลา : ").append(startTime).append(" ถึง ").append(endTime).toString();
				sb.setLength(0);
				
				try {
					jsonObj.put("backgroundColor", "#445353");
					jsonObj.put("id", String.valueOf(aModel.getAppointmentID()));
					jsonObj.put("start", aModel.getDateStart());
					jsonObj.put("end", aModel.getDateEnd());
					jsonObj.put("title", title);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					jsonArr.put(jsonObj);
				}
			}
			
			/**
			 * Write the response to JSON type.
			 */
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			try {
				response.getWriter().write(jsonArr.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		return INPUT;
	}
	
	/**
	 * PRIVATE METHOD ZONE.
	 */
	
	/**
	 * Get appointment that incoming by branch and doctor.
	 * @author anubi
	 * @param AppointmentModel appModel 
	 */
	private void getAppointmentIncoming(AppointmentModel appModel){
		AppointmentData appData = new AppointmentData();
		appointmentModel.setAppoinmentList(appData.getAppointmentIncoming(appModel));
	}
	
	/**
	 * Get manager doctor by branch id.
	 * @author anubi
	 * @param String branchID | branch id.
	 */
	private void getDoctorByMgrBranch(String branchID){
		DoctorData docData = new DoctorData();
		if(doctorModel == null){
			doctorModel = new DoctorModel();
		}
		doctorModel.setDocModelList(docData.getDoctorByMgrBranch(branchID));
	}
	

	/**
	 * Make a new appointment into calendar.
	 * @param AppointmentModel appModel |
	 * @return int rec | Count of row that get affected.
	 */
	private int postMakeAppointment(AppointmentModel appModel){
		AppointmentData appData = new AppointmentData();
		return appData.postMakeAppointment(appModel);
	}

	
	/**
	 * GETTER & SETTER ZONE
	 */
	public ServicePatientModel getServicePatModel() {
		return servicePatModel;
	}

	public void setServicePatModel(ServicePatientModel servicePatModel) {
		this.servicePatModel = servicePatModel;
	}

	public AppointmentModel getAppointmentModel() {
		return appointmentModel;
	}

	public void setAppointmentModel(AppointmentModel appointmentModel) {
		this.appointmentModel = appointmentModel;
	}

	public String getAlertError() {
		return alertError;
	}

	public String getAlertSuccess() {
		return alertSuccess;
	}

	public String getAlertMSG() {
		return alertMSG;
	}

	public void setAlertError(String alertError) {
		this.alertError = alertError;
	}

	public void setAlertSuccess(String alertSuccess) {
		this.alertSuccess = alertSuccess;
	}

	public void setAlertMSG(String alertMSG) {
		this.alertMSG = alertMSG;
	}

	public AppointmentModel getAppointmentModelOutPut() {
		return appointmentModelOutPut;
	}

	public void setAppointmentModelOutPut(AppointmentModel appointmentModelOutPut) {
		this.appointmentModelOutPut = appointmentModelOutPut;
	}

	public DoctorModel getDoctorModel() {
		return doctorModel;
	}

	public void setDoctorModel(DoctorModel doctorModel) {
		this.doctorModel = doctorModel;
	}

	public HashMap<String, String> getBranchMap() {
		return branchMap;
	}

	public void setBranchMap(HashMap<String, String> branchMap) {
		this.branchMap = branchMap;
	}

	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}

	public void setScheduleModel(ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}

	public List<ScheduleModel> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<ScheduleModel> scheduleList) {
		this.scheduleList = scheduleList;
	}


}
