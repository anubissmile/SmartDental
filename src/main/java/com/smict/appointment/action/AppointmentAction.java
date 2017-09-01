package com.smict.appointment.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.smict.person.data.EmployeeData;
import com.smict.person.data.PatientData;
import com.smict.person.model.BranchModel;
import com.smict.person.model.DoctorModel;
import com.smict.person.model.TelephoneModel;
import com.smict.schedule.data.ScheduleData;
import com.smict.schedule.model.ScheduleModel;

import ldc.util.Auth;
import ldc.util.DateUtil;

@SuppressWarnings("serial")
public class AppointmentAction extends ActionSupport {
	
	/**
	 * CONSTRUCTOR
	 */
	public AppointmentAction(){
		Auth.authCheck(false);
	}
	private DateUtil dateutil = new DateUtil();
	private ServicePatientModel servicePatModel;
	private AppointmentModel appointmentModel;
	private AppointmentModel appointmentModelOutPut = new AppointmentModel();
	private BranchModel branchModel;
	private DoctorModel doctorModel;
	private HashMap<String, String> branchMap;
	private ScheduleModel scheduleModel;
	private List<ScheduleModel> scheduleList;
	private List<AppointmentModel> getSymptomRelatelist,contactLogList,appointmentList;
	private HashMap<String, String> symptomMap;
	private List<TelephoneModel> telephoneList;
	private Map<String,String> branchlist;
	/**
	 * Alert messages
	 */
	private String alertError, alertSuccess, alertMSG;
	private String branchCodeCheck,branchIDCheck;
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		return SUCCESS;
	}
	
	/**
	 * Get doctor's appointment & agenda by doctor (without branch conditions).
	 * @author anubi
	 * @return String Action result.
	 */
	public void ajaxGetDoctorAgenda(){
		/**
		 * Prepare data.
		 */
		if(appointmentModel != null){
			if(appointmentModel.getDate() == null){
				LocalDate ld = new LocalDate();
				appointmentModel.setDate(ld.toString());
			}

			List<AppointmentModel> appointmentList = this.ajaxGetAgendaByDoctor(appointmentModel);

			/**
			 * Convert to json
			 * {"id":734,"start":"2017-07-24:08:20:00.0","end":"2017-07-24:10:00:00.0","title":"เวรลงตรวจ","userId":3},
			 */
			JSONArray jsonArr = new JSONArray();
			for(AppointmentModel appModel : appointmentList){
				JSONObject jsonObj = new JSONObject();
				try {
					
					StringBuilder sb = new StringBuilder();
					sb.append("นัดหมาย<br/>คนไข้: " + appModel.getHN() + "<br/> คำแนะนำ: " + appModel.getDescription())
						.append(" <br/> แพทย์: ")
						.append(appModel.getDocprenameth() + " ")
						.append(appModel.getDocfirstname() + " ")
						.append(appModel.getDoclastname())
						.append(" <br/> สถานะ: ");
					int appStatus = appModel.getAppointmentStatus();
					if(appStatus == 0){
						/**
						 * Success.
						 */
						sb.append(" มาตามนัด/รายงานตัวแล้ว<br/>");
					} else if(appStatus == 1){
						/**
						 * Disappointment.
						 */
						sb.append(" ติดต่อได้/ไม่มาตามนัด<br/>");
					} else if(appStatus == 2){
						/**
						 * Confirm.
						 */
						sb.append(" คอนเฟิร์มนัดแล้ว<br/>");
					} else if(appStatus == 3){
						/**
						 * Cancel.
						 */
						sb.append(" คนไข้ขอยกเลิกนัด<br/>");
					} else if(appStatus == 4){
						/**
						 * Postpone.
						 */
						sb.append(" คนไข้ขอเลื่อนนัด<br/>");
					} else if(appStatus == 5){
						/**
						 * Wait.
						 */
						sb.append(" รอการติดต่อ<br/>");
					} else if(appStatus == 6){
						/**
						 * Discard.
						 */
						sb.append(" ติดต่อไม่ได้/ไม่มาตามนัด<br/>");
					} else if(appStatus == 7){
						/**
						 * ETC.
						 */
						sb.append(" อื่นๆ<br/>");
					}
					
					jsonObj.put("id", appModel.getAppointmentID());
					jsonObj.put("start", appModel.getDateStart());
					jsonObj.put("end", appModel.getDateEnd());
					jsonObj.put("title", sb.toString());
					jsonObj.put("userId", appModel.getBranchID());
					jsonObj.put("branch_id", appModel.getBranchID());
					jsonObj.put("branch_code", appModel.getBranchCode());
					jsonObj.put("appointment_status", appModel.getAppointmentStatus());
					jsonObj.put("contact_status", appModel.getContactStatus());
					jsonObj.put("colour", appModel.getColour());
					sb.setLength(0);
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
		}
	}
	
	/**
	 * Get doctor's schedule (without branch conditions).
	 * @author anubi
	 * @return String Action result.
	 */
	public String ajaxGetAppointmentByDoctor(){
		
		/**
		 * Prepare data.
		 */
		if(appointmentModel != null){
			if(appointmentModel.getDate() == null){	
				LocalDate ld = new LocalDate();
				System.out.println(ld.toString());
				appointmentModel.setDate(ld.toString());
			}
			
			if(scheduleList == null){
				scheduleList = new ArrayList<ScheduleModel>();
			}
			scheduleList = this.getAllDoctorScheduleByDateRange(appointmentModel);
		}
		
		/**
		 * Convert to json
		 * {"id":734,"start":"2017-07-24:08:20:00.0","end":"2017-07-24:10:00:00.0","title":"เวรลงตรวจ","userId":3},
		 */
		JSONArray jsonArr = new JSONArray();
		for(ScheduleModel schModel : scheduleList){
			JSONObject jsonObj = new JSONObject();
			try {
				String title = "เวรลงตรวจสาขา " + schModel.getBranchName();
				jsonObj.put("id", schModel.getWorkDayId());
				jsonObj.put("start", schModel.getStartDateTime());
				jsonObj.put("end", schModel.getEndDateTime());
				jsonObj.put("title", title);
				jsonObj.put("userId", schModel.getStrBranchID());
				jsonObj.put("branch_id", schModel.getStrBranchID());
				jsonObj.put("branch_code", schModel.getStrBranchCode());
				jsonObj.put("branch", schModel.getBranchName());
				jsonObj.put("free", true);
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
	 * First page of week calendar by doctor.
	 * @author anubi
	 * @return String Action result.
	 */
	public String getAppointmentByDoctor(){
		/**
		 * Get customer.
		 */
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		
		/**
		 * Get doctor name
		 */
		if(doctorModel == null){
			doctorModel = new DoctorModel();
		}
		doctorModel = this.getDoctorDetails(appointmentModel.getDoctorID());
		
		/**
		 * Get branch host.
		 */
		if(branchModel == null){
			branchModel = new BranchModel();
		}
		branchModel.setBranch_code(Auth.user().getBranchCode());
		branchModel.setBranch_id(Auth.user().getBranchID());
		
		return SUCCESS;
	}
	
	/**
	 * Create new postpone appointment.
	 * @author anubiss
	 * @return String | Action result.
	 */
	public String postAddPostponeAppointment(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int rec = 0;
		if(request.getMethod().equals("POST")){
			if(appointmentModel.getPostponeReferenceID() != null){
				if(appointmentModel.getPostponeReferenceID().length() > 0){
					/**
					 * POSTPONE.
					 */
					AppointmentData appData = new AppointmentData();
					
					/**
					 * Update old appointment status.
					 */
					appData.updateAppointmentStatus(appointmentModel.getAppointmentID(),null,"4");
					String descript = appointmentModel.getDescription();
					appointmentModel.setDescription(appointmentModel.getReason());
					appointmentModel.setAppointmentStatus(4);
					appData.insertAppointmentStatusLog(appointmentModel);
					
					/**
					 * Insert new appointment.
					 */
					appointmentModel.setDescription(descript);
					appointmentModel.setBranchCode(Auth.user().getBranchCode());
					appointmentModel.setBranchID(Auth.user().getBranchID());
					appointmentModel.setAppointCode(AppointmentUtil.getAppointmentCode(appointmentModel).getAppointCode());
					rec = this.postMakeAppointmentWeekCalendar(appointmentModel);
					
					
				}else{
					/**
					 * reference id equals ''
					 * - set err msg.
					 */
				}
			}else{
				/**
				 * reference id was null
				 * - set err msg.
				 */
			}
		}else{
			/**
			 * NOT POST METHOD.
			 * - set err msg.
			 */
			return INPUT;
		}
		return SUCCESS;
	}
	
	/**
	 * Add an appointment from week calendar by method POST.
	 * @author anubi
	 * @return String | Action result.
	 */
	public String postAddAppointmentWeekCalendar(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int rec = 0;
		if(request.getMethod().equals("POST")){
			System.out.println("This is POST");
			appointmentModel.setBranchCode(Auth.user().getBranchCode());
			appointmentModel.setBranchID(Auth.user().getBranchID());
			appointmentModel.setAppointCode(AppointmentUtil.getAppointmentCode(appointmentModel).getAppointCode());
			rec = this.postMakeAppointmentWeekCalendar(appointmentModel);
		}else{
			System.out.println("This isn't POST");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * Get doctor's appointment list on week calendar.
	 * @author anubi
	 * @return String | Action result.
	 */
	public String getAppoinmentWeekCalendar(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		/**
		 * Get customer.
		 */
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		
		/**
		 * Get symptom list.
		 */
		getSymptomRelatelist = this.getSymptom();
		/**
		 * Convert to hashmap.
		 */
		if(symptomMap == null){
			symptomMap = new HashMap<String, String>();
		}
		for(AppointmentModel appointmentModel : getSymptomRelatelist){
			symptomMap.put(String.valueOf(appointmentModel.getSymptomID()), appointmentModel.getSymptom());
		}
		
		
		return SUCCESS;
	}
	
	public String ajaxGetDoctorAppointment(){
		if(appointmentModel == null){
			appointmentModel = new AppointmentModel();
		}
		appointmentModel.setBranchID(Auth.user().getBranchID());
		appointmentModel.setBranchCode(Auth.user().getBranchCode());
		List<AppointmentModel> appList = this.getDoctorAppointment(appointmentModel);
		
		/**
		 * Convert into json format like this.
		 * {'id':1, 'start': new Date(year, month, day, 12), 'end': new Date(year, month, day, 13, 30), 'title': 'Lunch with Mike', userId: 0}
		 */
		JSONArray jsonArr = new JSONArray();
		for(AppointmentModel appModel : appList){
			JSONObject jsonObj = new JSONObject();
			try {
				StringBuilder sb = new StringBuilder();
				sb.append("นัดหมาย<br/>คนไข้: " + appModel.getHN() + "<br/> คำแนะนำ: " + appModel.getDescription())
					.append(" <br/> แพทย์: ")
					.append(appModel.getDocprenameth() + " ")
					.append(appModel.getDocfirstname() + " ")
					.append(appModel.getDoclastname())
					.append(" <br/> สถานะ: ");
				int appStatus = appModel.getAppointmentStatus();
				if(appStatus == 0){
					/**
					 * Success.
					 */
					sb.append(" มาตามนัด/รายงานตัวแล้ว<br/>");
				} else if(appStatus == 1){
					/**
					 * Disappointment.
					 */
					sb.append(" ติดต่อได้/ไม่มาตามนัด<br/>");
				} else if(appStatus == 2){
					/**
					 * Confirm.
					 */
					sb.append(" คอนเฟิร์มนัดแล้ว<br/>");
				} else if(appStatus == 3){
					/**
					 * Cancel.
					 */
					sb.append(" คนไข้ขอยกเลิกนัด<br/>");
				} else if(appStatus == 4){
					/**
					 * Postpone.
					 */
					sb.append(" คนไข้ขอเลื่อนนัด<br/>");
				} else if(appStatus == 5){
					/**
					 * Wait.
					 */
					sb.append(" รอการติดต่อ<br/>");
				} else if(appStatus == 6){
					/**
					 * Discard.
					 */
					sb.append(" ติดต่อไม่ได้/ไม่มาตามนัด<br/>");
				} else if(appStatus == 7){
					/**
					 * ETC.
					 */
					sb.append(" อื่นๆ<br/>");
				}
				
				jsonObj.put("id", appModel.getAppointmentID());
				jsonObj.put("start", appModel.getDateStart());
				jsonObj.put("end", appModel.getDateEnd());
				jsonObj.put("title", sb.toString());
				jsonObj.put("userId", appModel.getDoctorID());
				jsonObj.put("doctorId", appModel.getDoctorID());
				jsonObj.put("doctor", appModel.getFirstNameTH() + " " + appModel.getLastNameTH());
				jsonObj.put("free", true);
				jsonObj.put("type", "appointment");
				jsonObj.put("appointment_status", appModel.getAppointmentStatus());
				jsonObj.put("contact_status", appModel.getContactStatus());
				jsonObj.put("colour", appModel.getColour());
				sb.setLength(0);
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
			scheduleModel.setStartDateTime(localDate.toString() + " 00:00:00");
			scheduleModel.setEndDateTime(localDate.toString() + " 23:59:59");
		}
		if(scheduleList == null){
			scheduleList = new ArrayList<ScheduleModel>();
		}
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
				jsonObj.put("start", schModel.getStartDateTime());
				jsonObj.put("end", schModel.getEndDateTime());
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
	 * Get all symptom.
	 * @author anubi
	 * @return List<AppointmentModel> sympList | Symptom dataset.
	 */
	private List<AppointmentModel> getSymptom(){
		return new AppointmentData().getSymptom();
	}

	
	/**
	 * Get doctor details.
	 * @author anubi
	 * @param int doctorID | Doctor's id.
	 * @return DoctorModel;
	 */
	private DoctorModel getDoctorDetails(int doctorID){
		DoctorData docData = new DoctorData();
		try {
			return docData.Get_DoctorDetail(doctorID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new DoctorModel();
	}
	

	/**
	 * Get doctor's agenda (without branch conditions).
	 * @author anubi
	 * @param AppointmentModel appModel |
	 * @return List<AppointmentModel> appList |
	 */
	private List<AppointmentModel> ajaxGetAgendaByDoctor(AppointmentModel appModel){
		AppointmentData appData = new AppointmentData();
		return appData.ajaxGetAgendaByDoctor(appModel);
	}
	
	
	/**
	 * Get all doctor's schedule by date range (without branch conditions).
	 * @author anubi
	 * @param AppointmentModel appModel
	 * @return List<ScheduleModel> scheduleList
	 */
	private List<ScheduleModel> getAllDoctorScheduleByDateRange(AppointmentModel appModel){
		AppointmentData appData = new AppointmentData();
		return appData.getAllDoctorScheduleByDateRange(appModel);
	}
	
	/**
	 * Get doctor appointment list
	 * @author anubi
	 * @param AppointmentModel appModel |
	 * @return List<AppointmentModel> |
	 */
	private List<AppointmentModel> getDoctorAppointment(AppointmentModel appModel){
		AppointmentData appData = new AppointmentData();
		return appData.getDoctorAppointment(appModel);
	}
	
	/**
	 * Get appointment that incoming in weekday calendar.
	 * @author anubi
	 * @param AppointmentModel appModel
	 * @return int rec | Count of record that get affected.
	 */
	private int postMakeAppointmentWeekCalendar(AppointmentModel appModel){
		AppointmentData appData = new AppointmentData();
		return appData.postMakeAppointmentWeekCalendar(appModel);
	}
	
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

	/**S
	 *  get appointment 
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String getAppointmentListTable() throws IOException, Exception{
		AppointmentData appData = new AppointmentData();
		AppointmentModel app = new AppointmentModel();
		EmployeeData empdata1 = new EmployeeData();
		/**
		 * Appointment header
		 */
		setAppointmentList(appData.getAppointmentListShow());
		app.setDateToday(dateutil.convertDateSpecificationPattern("yyyy-MM-dd HH:mm:ss.S","dd-MM-yyyy",appData.getDatetime(),true));
		app.setDatetodayend(dateutil.convertDateSpecificationPattern("yyyy-MM-dd HH:mm:ss.S","dd-MM-yyyy",appData.getDatetime(),true));
		app.setAuthenBranchcode(Auth.user().getBranchID());
		setAppointmentModel(app);
		setBranchCodeCheck(Auth.user().getBranchCode());
		setBranchlist(empdata1.Get_branchList());
		return SUCCESS;
	}
	
	/**
	 * Get appointment with searching by appointment id.
	 * @author anubi
	 * @return String | Action result.
	 */
	public String getAppointmentListTableByID(){
		AppointmentData appData = new AppointmentData();
		EmployeeData empdata1 = new EmployeeData();
		setAppointmentList(appData.getAppointmentListByID(appointmentModel));
		appointmentModel.setDateToday(dateutil.convertDateSpecificationPattern("yyyy-MM-dd HH:mm:ss.S","dd-MM-yyyy",appData.getDatetime(),true));
		appointmentModel.setDatetodayend(dateutil.convertDateSpecificationPattern("yyyy-MM-dd HH:mm:ss.S","dd-MM-yyyy",appData.getDatetime(),true));
		appointmentModel.setAuthenBranchcode(Auth.user().getBranchID());
		setBranchCodeCheck(Auth.user().getBranchCode());
		try {
			setBranchlist(empdata1.Get_branchList());
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
	 * get appointment with search
	 * @throws Exception 
	 * @throws IOException 
	 */
	public String getAppointmentListSearch() throws IOException, Exception{
		AppointmentData appData = new AppointmentData();
		EmployeeData empdata1 = new EmployeeData();
		setBranchlist(empdata1.Get_branchList());
		setAppointmentList(appData.getAppointmentListSearchDate(dateutil.CnvToYYYYMMDDEngYear(appointmentModel.getDateToday(),'-')
				,dateutil.CnvToYYYYMMDDEngYear(appointmentModel.getDatetodayend(),'-')
				,appointmentModel.getAuthenBranchcode()));
		setBranchCodeCheck(Auth.user().getBranchCode());
		return SUCCESS;
	}
	/**
	 * delete Appointment
	 */
	public String deleteAppointment(){
		AppointmentData appData = new AppointmentData();
		appData.deleteAppoinment(appointmentModel.getAppointmentID());
		return SUCCESS;
	}
	
	/**
	 *  get appointment with patient
	 */
	public String getAppointmentpatient(){
		appointmentModel.getAppointmentID();
		AppointmentData appData = new AppointmentData();
		PatientData patData = new PatientData();
		
		/**
		 * Appointment header
		 */
		setAppointmentModel(appData.getAppointmentallDetail(appointmentModel));
		/**
		 * relate
		 */
		setGetSymptomRelatelist(appData.getAppointmentSymptomRelate(appointmentModel));
		/**
		 * patient telephone
		 */
		setTelephoneList(patData.getPatientPhone(appointmentModel.getHN()));
		/**
		 * contact log
		 */
		setContactLogList(appData.getAppointmentContactLog(appointmentModel));
		setBranchCodeCheck(Auth.user().getBranchCode());
		return SUCCESS;
	}
	public String updateIsviewStatus(){
		appointmentModel.getAppointmentID();
		AppointmentData appData = new AppointmentData();
		/**
		 * update isView
		 */
		appData.updateAppointmentIsView(appointmentModel);
		
		return SUCCESS;
	}
	public String updateStatusIsdayview(){
		appointmentModel.getAppointmentID();
		AppointmentData appData = new AppointmentData();
		/**
		 * update isdayView
		 */
		appData.updateAppointmentIsdayView(appointmentModel);
		
		return SUCCESS;
	}
	public String updateContactlog(){
		appointmentModel.getAppointmentID();
		AppointmentData appData = new AppointmentData();
		/**
		 * update Appointment Status
		 */
		appData.updateAppointmentStatus(appointmentModel.getAppointmentID(),Integer.toString(appointmentModel.getContactStatus()),null);
		/**
		 * Add contact log
		 */
		appData.insertAppointmentContactLog(appointmentModel);
		
		return SUCCESS;
	}
	public String updateAppStatuslog(){
		appointmentModel.getAppointmentID();
		AppointmentData appData = new AppointmentData();
		/**
		 * update Appointment Status
		 */
		appData.updateAppointmentStatus(appointmentModel.getAppointmentID(),null,Integer.toString(appointmentModel.getAppointmentStatus()));
		/**
		 * Add Status log
		 */
		appData.insertAppointmentStatusLog(appointmentModel);
		if(appointmentModel.getAppointmentStatus()== 0){
			return INPUT;
		}else{
		return SUCCESS;
		}
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


	public List<AppointmentModel> getGetSymptomRelatelist() {
		return getSymptomRelatelist;
	}


	public void setGetSymptomRelatelist(List<AppointmentModel> getSymptomRelatelist) {
		this.getSymptomRelatelist = getSymptomRelatelist;
	}


	public List<TelephoneModel> getTelephoneList() {
		return telephoneList;
	}


	public void setTelephoneList(List<TelephoneModel> telephoneList) {
		this.telephoneList = telephoneList;
	}


	public List<AppointmentModel> getContactLogList() {
		return contactLogList;
	}


	public void setContactLogList(List<AppointmentModel> contactLogList) {
		this.contactLogList = contactLogList;
	}

	public BranchModel getBranchModel() {
		return branchModel;
	}

	public void setBranchModel(BranchModel branchModel) {
		this.branchModel = branchModel;
	}

	public List<AppointmentModel> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<AppointmentModel> appointmentList) {
		this.appointmentList = appointmentList;
	}

	public DateUtil getDateutil() {
		return dateutil;
	}

	public String getBranchCodeCheck() {
		return branchCodeCheck;
	}

	public String getBranchIDCheck() {
		return branchIDCheck;
	}

	public void setDateutil(DateUtil dateutil) {
		this.dateutil = dateutil;
	}

	public void setBranchCodeCheck(String branchCodeCheck) {
		this.branchCodeCheck = branchCodeCheck;
	}

	public void setBranchIDCheck(String branchIDCheck) {
		this.branchIDCheck = branchIDCheck;
	}

	public Map<String,String> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(Map<String,String> branchlist) {
		this.branchlist = branchlist;
	}

	public HashMap<String, String> getSymptomMap() {
		return symptomMap;
	}

	public void setSymptomMap(HashMap<String, String> symptomMap) {
		this.symptomMap = symptomMap;
	}


}
