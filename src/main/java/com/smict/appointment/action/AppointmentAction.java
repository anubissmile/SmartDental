package com.smict.appointment.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ServicePatientModel;
import com.smict.person.data.DoctorData;
import com.smict.person.model.DoctorModel;

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
	
	private ServicePatientModel servicePatModel;
	private AppointmentModel appointmentModel;
	private AppointmentModel appointmentModelOutPut = new AppointmentModel();
	private DoctorModel doctorModel;
	
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
	 * Get searching another branch id for get into calendar.
	 * @author anubi
	 * @return String | Action result.
	 */
	public String getSearchAnotherBranch(){
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


}
