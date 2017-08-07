package com.smict.appointment.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.smict.schedule.model.ScheduleModel;
import ldc.util.DBConnect;
import ldc.util.DateUtil;

public class AppointmentData {
	private DBConnect agent = new DBConnect();
	private DateUtil dateutil = new DateUtil();
	private ResultSet rs;
	
	
	/**
	 * Get doctor's agenda (without branch conditions).
	 * @author anubi
	 * @param AppointmentModel appModel |
	 * @return List<AppointmentModel> appList |
	 */
	public List<AppointmentModel> ajaxGetAgendaByDoctor(AppointmentModel appModel){
		List<AppointmentModel> appList = new ArrayList<AppointmentModel>();
		String SQL = "SELECT dentist_appointment.id, dentist_appointment.doctor_id, "
				+ "dentist_appointment.hn, dentist_appointment.recommend, "
				+ "dentist_appointment.branch_code, dentist_appointment.branch_id, "
				+ "dentist_appointment.datetime_start, dentist_appointment.datetime_end, "
				+ "dentist_appointment.appointment_status, dentist_appointment.contact_status, "
				+ "dentist_appointment.created_date, dentist_appointment.updated_date, "
				+ "dentist_appointment.`code`, dentist_appointment.refer_other_appointment_id, "
				+ "dentist_appointment.reminder_date "
				+ "FROM dentist_appointment "
				+ "WHERE dentist_appointment.doctor_id = '" + appModel.getDoctorID() + "' AND "
				+ "dentist_appointment.datetime_start LIKE '" + appModel.getDate() + "%' "
				+ "ORDER BY dentist_appointment.datetime_start ASC ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		try {
			rs = agent.getRs();
			while(rs.next()){
				AppointmentModel aModel = new AppointmentModel();
				aModel.setAppointmentID(rs.getInt("id"));
				aModel.setDoctorID(rs.getInt("doctor_id"));
				aModel.setHN(rs.getString("hn"));
				aModel.setDescription(rs.getString("recommend"));
				aModel.setBranchCode(rs.getString("branch_code"));
				aModel.setBranchID(rs.getString("branch_id"));
				aModel.setDateStart(rs.getString("datetime_start"));
				aModel.setDateEnd(rs.getString("datetime_end"));
				aModel.setAppointmentStatus(rs.getInt("appointment_status"));
				aModel.setContactStatus(rs.getInt("contact_status"));
				aModel.setAppointmentCode(rs.getString("code"));
				aModel.setPostponeReferenceID(rs.getString("refer_other_appointment_id"));
				aModel.setRemindDate(rs.getInt("reminder_date"));
				appList.add(aModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		return appList;
	}
	
	/**
	 * Get all doctor's schedule by date range (without branch conditions).
	 * @author anubi
	 * @param List<AppointmentModel> appModel
	 * @return List<ScheduleModel> scheduleList
	 */
	public List<ScheduleModel> getAllDoctorScheduleByDateRange(AppointmentModel appModel){
		List<ScheduleModel> scheduleList = new ArrayList<ScheduleModel>();
		String SQL = "SELECT doctor_workday.workday_id, doctor_workday.doctor_id, "
				+ "doctor_workday.start_datetime, doctor_workday.end_datetime, "
				+ "doctor_workday.work_hour, doctor_workday.branch_id, "
				+ "doctor_workday.branch_room_id, doctor_workday.checkin_status, "
				+ "doctor_workday.checkin_datetime, doctor_workday.checkout_datetime, "
				+ "doctor.pre_name_id, doctor.first_name_th, "
				+ "doctor.last_name_th, doctor.first_name_en, "
				+ "doctor.last_name_en, pre_name.pre_name_th, "
				+ "pre_name.pre_name_en, branch.branch_code, branch.branch_id, "
				+ "branch.branch_name FROM doctor_workday "
				+ "LEFT JOIN doctor ON doctor_workday.doctor_id = doctor.doctor_id "
				+ "LEFT JOIN pre_name ON doctor.pre_name_id = pre_name.pre_name_id "
				+ "LEFT JOIN branch ON doctor_workday.branch_id = branch.branch_code	 "
				+ "WHERE doctor_workday.doctor_id = '" + appModel.getDoctorID() + "' "
				+ "AND doctor_workday.start_datetime LIKE '" + appModel.getDate() + "%' ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		try {
			if(agent.size() > 0){
				rs = agent.getRs();
				while(rs.next()){
					ScheduleModel schModel = new ScheduleModel();
					schModel.setWorkDayId(rs.getInt("workday_id"));
					schModel.setDoctorId(rs.getInt("doctor_id"));
					schModel.setStartDateTime(rs.getString("start_datetime"));
					schModel.setEndDateTime(rs.getString("end_datetime"));
					schModel.setWorkHour(rs.getInt("work_hour"));
					schModel.setStrBranchID(rs.getString("branch.branch_id"));
					schModel.setStrBranchCode(rs.getString("branch.branch_code"));
					schModel.setBranchName(rs.getString("branch_name"));
					schModel.setBranchRoomId(rs.getInt("branch_room_id"));
					schModel.setCheckInStatus(rs.getString("checkin_status"));
					schModel.setCheckInDateTime(rs.getString("checkin_datetime"));
					schModel.setCheckOutDateTime(rs.getString("checkout_datetime"));
					schModel.setPre_name_th(rs.getString("pre_name_th"));
					schModel.setFirst_name_th(rs.getString("first_name_th"));
					schModel.setLast_name_th(rs.getString("last_name_th"));
					scheduleList.add(schModel);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		
		return scheduleList;
	}
	
	/**
	 * Chunk all doctor's appointment.
	 * @author anubi
	 * @param AppointmentModel appModel
	 * @return List<AppointmentModel> appList
	 */
	public List<AppointmentModel> getDoctorAppointment(AppointmentModel appModel){
		List<AppointmentModel> appList = new ArrayList<AppointmentModel>();
		String SQL = "SELECT dentist_appointment.id, dentist_appointment.doctor_id, "
				+ "dentist_appointment.hn, dentist_appointment.recommend, "
				+ "dentist_appointment.branch_code, dentist_appointment.branch_id, "
				+ "dentist_appointment.datetime_start, dentist_appointment.datetime_end, "
				+ "dentist_appointment.contact_status, dentist_appointment.appointment_status, "
				+ "dentist_appointment.created_date, dentist_appointment.updated_date "
				+ "FROM dentist_appointment "
				+ "WHERE (dentist_appointment.datetime_start BETWEEN '" + appModel.getDateStart() + "' AND '" + appModel.getDateEnd() + "') "
				+ "AND dentist_appointment.branch_id = '" + appModel.getBranchID() + "' "
				+ "AND dentist_appointment.branch_code = '" + appModel.getBranchCode() + "' ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		try {
			if(agent.size() > 0){
				ResultSet rs = agent.getRs();
				while(rs.next()){
					AppointmentModel aModel = new AppointmentModel();
					aModel.setAppointmentID(rs.getInt("id"));
					aModel.setDoctorID(rs.getInt("doctor_id"));
					aModel.setHN(rs.getString("hn"));
					aModel.setDescription(rs.getString("recommend"));
					aModel.setBranchCode(rs.getString("branch_code"));
					aModel.setBranchID(rs.getString("branch_id"));
					aModel.setContactStatus(rs.getInt("contact_status"));
					aModel.setAppointmentStatus(rs.getInt("appointment_status"));
					aModel.setDateStart(rs.getString("datetime_start"));
					aModel.setDateEnd(rs.getString("datetime_end"));
					appList.add(aModel);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		agent.disconnectMySQL();
		return appList;
	}
	
	/**
	 * Make a new appointment into weed calendar.
	 * @author anubi
	 * @param AppointmentModel appModel | 
	 * @return int rec | Count of row that get affected.
	 */
	public int postMakeAppointmentWeekCalendar(AppointmentModel appModel){
		int rec = 0;
		String SQL = "INSERT INTO `dentist_appointment` (`doctor_id`, `code`, `hn`, "
				+ "`recommend`, `branch_code`, "
				+ "`branch_id`, `datetime_start`, "
				+ "`datetime_end`, `contact_status`, "
				+ "`appointment_status`, `created_date`, "
				+ "`updated_date`) "
				+ "VALUES ('" + appModel.getDoctorID() + "', 'fixed code', '" + appModel.getHN() + "', "
				+ "'" + appModel.getDescription() + "', '" + appModel.getBranchCode() + "', "
				+ "'" + appModel.getBranchID() + "', '" + appModel.getDateStart() + "', "
				+ "'" + appModel.getDateEnd() + "', '2', "
				+ "'5', NOW(), NOW())";
		
		agent.connectMySQL();
		agent.begin();
		rec = agent.exeUpdate(SQL);
		if(rec > 0){
			agent.commit();
		}else{
			agent.rollback();
		}
		agent.disconnectMySQL();
		return rec;
	}
	
	/**
	 * Make a new appointment into calendar.
	 * @param AppointmentModel appModel |
	 * @return int rec | Count of row that get affected.
	 */
	public int postMakeAppointment(AppointmentModel appModel){
		int rec = 0;
		String SQL = "INSERT INTO `dentist_appointment` "
				+ "(`doctor_id`, `hn`, "
				+ "`description`, `branch_code`, "
				+ "`branch_id`, `datetime_start`, "
				+ "`datetime_end`, `status`, "
				+ "`created_date`, `updated_date`) "
				+ "VALUES ('" + appModel.getDoctorID() + "', "
				+ "'" + appModel.getHN() + "', "
				+ "'" + appModel.getDescription() + "', "
				+ "'" + appModel.getBranchCode() + "', "
				+ "'" + appModel.getBranchID() + "', "
				+ "'" + appModel.getDateStart() + "', "
				+ "'" + appModel.getDateEnd() + "', "
				+ "'0', "
				+ "NOW(), "
				+ "NOW()"
				+ ") ";
		
		agent.connectMySQL();
		agent.begin();
		rec = agent.exeUpdate(SQL);
		if(rec > 0){
			agent.commit();
		}else{
			agent.rollback();
		}
		agent.disconnectMySQL();
		return rec;
	}
	
	/**
	 * Get doctor's appointment that incoming.
	 * @author anubi
	 * @param AppointmentModel appModel
	 * @return List<AppointmentModel> appModelList
	 */
	public List<AppointmentModel> getAppointmentIncoming(AppointmentModel appModel){
		List<AppointmentModel> appModelList = new ArrayList<AppointmentModel>();
		String SQL = "SELECT dentist_appointment.id, dentist_appointment.doctor_id, "
				+ "dentist_appointment.hn, dentist_appointment.description, "
				+ "dentist_appointment.branch_code, dentist_appointment.branch_id, "
				+ "dentist_appointment.datetime_start, dentist_appointment.datetime_end, "
				+ "dentist_appointment.`contact_status`, dentist_appointment.`appointment_status`, dentist_appointment.created_date, "
				+ "dentist_appointment.updated_date, patient.hn, patient.first_name_th, "
				+ "patient.last_name_th, patient.first_name_en, "
				+ "patient.last_name_en, patient.identification "
				+ "FROM dentist_appointment "
				+ "INNER JOIN patient ON dentist_appointment.hn = patient.hn "
				+ "WHERE dentist_appointment.doctor_id = '" + appModel.getDoctorID() + "' AND "
				+ "(dentist_appointment.branch_code = '" + appModel.getBranchID() + "' OR dentist_appointment.branch_id = '" + appModel.getBranchID() + "') AND "
				+ "dentist_appointment.`status` = '0' "
				+ "ORDER BY dentist_appointment.datetime_start ASC ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		try {
			if(agent.size() > 0){
				while(agent.getRs().next()){
					AppointmentModel apModel = new AppointmentModel();
					/*dentist_appointment.id,
					dentist_appointment.doctor_id,
					dentist_appointment.hn,
					dentist_appointment.description,
					dentist_appointment.branch_code,
					dentist_appointment.branch_id,
					dentist_appointment.datetime_start,
					dentist_appointment.datetime_end,
					dentist_appointment.`status`,
					dentist_appointment.created_date,
					dentist_appointment.updated_date
					patient.first_name_th,
					patient.last_name_th,
					patient.first_name_en,
					patient.last_name_en,
					patient.identification*/
					
					apModel.setAppointmentID(agent.getRs().getInt("id"));
					apModel.setDoctorID(agent.getRs().getInt("doctor_id"));
					apModel.setHN(agent.getRs().getString("hn"));
					apModel.setDescription(agent.getRs().getString("description"));
					apModel.setBranchCode(agent.getRs().getString("branch_code"));
					apModel.setBranchID(agent.getRs().getString("branch_id"));
					apModel.setDateStart(agent.getRs().getString("datetime_start"));
					apModel.setDateEnd(agent.getRs().getString("datetime_end"));
					
					apModel.setFirstNameTH(agent.getRs().getString("first_name_th"));
					apModel.setLastNameTH(agent.getRs().getString("last_name_th"));
					apModel.setFirstNameEN(agent.getRs().getString("first_name_en"));
					apModel.setLastNameEN(agent.getRs().getString("last_name_en"));
					apModel.setIdentification(agent.getRs().getString("identification"));
					appModelList.add(apModel);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		return appModelList;
	}
	public AppointmentModel getAppointmentallDetail(AppointmentModel appModel){
		AppointmentModel apModel = new AppointmentModel();
		String SQL = "SELECT dentist_appointment.id,dentist_appointment.`code`, "
				+ "dentist_appointment.doctor_id,dentist_appointment.hn, "
				+ "dentist_appointment.recommend,dentist_appointment.branch_code, "
				+ "dentist_appointment.branch_id,dentist_appointment.datetime_start, "
				+ "dentist_appointment.datetime_end,dentist_appointment.contact_status, "
				+ "dentist_appointment.appointment_status,dentist_appointment.refer_other_appointment_id, "
				+ "dentist_appointment.reminder_date,dentist_appointment.created_date, "
				+ "dentist_appointment.updated_date,dentist_appointment_status_log.description, "
				+ "branch.branch_name,doctor.first_name_th,doctor.last_name_th, "
				+ "pre_name.pre_name_th,patient.first_name_th,patient.last_name_th,p1.pre_name_th,patient.contact_time_start,patient.contact_time_end "
				+ "FROM "
				+ "dentist_appointment "
				+ "LEFT JOIN dentist_appointment_status_log ON dentist_appointment.id = dentist_appointment_status_log.appointment_id "
				+ "INNER JOIN branch ON branch.branch_id = dentist_appointment.branch_id "
				+ "INNER JOIN doctor ON doctor.doctor_id = dentist_appointment.doctor_id "
				+ "INNER JOIN pre_name ON doctor.pre_name_id = pre_name.pre_name_id "
				+ "INNER JOIN patient ON patient.hn = dentist_appointment.hn "
				+ "INNER JOIN pre_name p1 ON patient.pre_name_id = p1.pre_name_id "
				+ "WHERE dentist_appointment.id = '"+appModel.getAppointmentID()+"' ";
		
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		try {
			if(agent.size() > 0){
				while(agent.getRs().next()){
					
					apModel.setAppointmentID(agent.getRs().getInt("dentist_appointment.id"));
					apModel.setAppointCode(agent.getRs().getString("dentist_appointment.code"));
					apModel.setDoctorID(agent.getRs().getInt("dentist_appointment.doctor_id"));
					apModel.setHN(agent.getRs().getString("dentist_appointment.hn"));
					apModel.setRecommend(agent.getRs().getString("dentist_appointment.recommend"));
					apModel.setDescription(agent.getRs().getString("dentist_appointment_status_log.description"));
					apModel.setBranchCode(agent.getRs().getString("dentist_appointment.branch_code"));
					apModel.setBranchID(agent.getRs().getString("dentist_appointment.branch_id"));
					apModel.setAppconstatus(agent.getRs().getInt("dentist_appointment.contact_status"));
					apModel.setAppointmentStatus(agent.getRs().getInt("dentist_appointment.appointment_status"));
					apModel.setReferID(agent.getRs().getString("dentist_appointment.refer_other_appointment_id"));
					apModel.setBranchName(agent.getRs().getString("branch.branch_name"));
					apModel.setDocfirstname(agent.getRs().getString("doctor.first_name_th"));
					apModel.setDoclastname(agent.getRs().getString("doctor.last_name_th"));
					apModel.setFirstNameTH(agent.getRs().getString("patient.first_name_th"));
					apModel.setLastNameTH(agent.getRs().getString("patient.last_name_th"));
					apModel.setPatPrenameth(agent.getRs().getString("p1.pre_name_th"));
					apModel.setDocprenameth(agent.getRs().getString("pre_name.pre_name_th"));
					apModel.setPattimestart(agent.getRs().getString("patient.contact_time_start"));
					apModel.setPattimeend(agent.getRs().getString("patient.contact_time_end"));
					String start = dateutil.convertDateSpecificationPattern("yyyy-MM-dd HH:mm:ss.S","dd/MM/yyyy HH:mm",agent.getRs().getString("dentist_appointment.datetime_start"),false);
					String startarr [] = start.split(" ");
					apModel.setDate(startarr [0]);
					apModel.setTimeStart(startarr[1]);
					apModel.setTimeEnd(dateutil.convertDateSpecificationPattern("yyyy-MM-dd HH:mm:ss.S","HH:mm",agent.getRs().getString("dentist_appointment.datetime_end"),false));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		return apModel;
	}
	public List<AppointmentModel> getAppointmentSymptomRelate(AppointmentModel appModel){
		List<AppointmentModel> appModelList = new ArrayList<AppointmentModel>();
		String SQL = "SELECT appointment_symptom_relate.id,appointment_symptom_relate.appointment_id, "
				+ "appointment_symptom_relate.symptom_id,appointment_symptom_relate.description, "
				+ "appointment_symptom.symptom_th,appointment_symptom.symptom_en "
				+ "FROM "
				+ "appointment_symptom_relate "
				+ "INNER JOIN appointment_symptom ON appointment_symptom_relate.symptom_id = appointment_symptom.id "
				+ "WHERE appointment_symptom_relate.appointment_id = '"+appModel.getAppointmentID()+"' ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		try {
			if(agent.size() > 0){
				while(agent.getRs().next()){
					AppointmentModel apModel = new AppointmentModel();
					apModel.setSympDescription(agent.getRs().getString("appointment_symptom_relate.description"));
					appModelList.add(apModel);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		return appModelList;
	}
	public List<AppointmentModel> getAppointmentContactLog(AppointmentModel appModel){
		List<AppointmentModel> appModelList = new ArrayList<AppointmentModel>();
		String SQL = "SELECT dentist_appointment_contact_log.id,dentist_appointment_contact_log.appointment_id, "
				+ "dentist_appointment_contact_log.description,dentist_appointment_contact_log.status_code, "
				+ "dentist_appointment_contact_log.created_date "
				+ "FROM "
				+ "dentist_appointment_contact_log "
				+ "WHERE dentist_appointment_contact_log.appointment_id = '"+appModel.getAppointmentID()+"' "
				+ "ORDER BY dentist_appointment_contact_log.id ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		try {
			if(agent.size() > 0){
				while(agent.getRs().next()){
					AppointmentModel apModel = new AppointmentModel();
					apModel.setConractdes(agent.getRs().getString("dentist_appointment_contact_log.description"));
					apModel.setContactStatus(agent.getRs().getInt("dentist_appointment_contact_log.status_code"));
					apModel.setContactdate(dateutil.convertDateSpecificationPattern("yyyy-MM-dd HH:mm:ss.S","dd/MM/yyyy HH:mm",agent.getRs().getString("dentist_appointment_contact_log.created_date"),false));
					appModelList.add(apModel);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		return appModelList;
	}
	public int updateAppointmentStatus(int appID,String conStatus,String appStatus){
		int rec = 0;
		String SQL = "UPDATE dentist_appointment "
				+ "SET ";
				if(conStatus != null){
					SQL += "contact_status = '"+conStatus+"'";
				}else{
					SQL += "appointment_status = '"+appStatus+"'";
				}
				SQL +="WHERE id = '"+appID+"' ";
		
		agent.connectMySQL();
		agent.begin();
		rec = agent.exeUpdate(SQL);
		if(rec > 0){
			agent.commit();
		}else{
			agent.rollback();
		}
		agent.disconnectMySQL();
		return rec;
	}
	public int insertAppointmentContactLog(AppointmentModel appModel){
		int rec = 0;
		String SQL = "INSERT INTO `dentist_appointment_contact_log` "
				+ "(`appointment_id`, `description`, "
				+ "`status_code`, `created_date`) "
				+ "VALUES ('" + appModel.getAppointmentID() + "', "
				+ "'" + appModel.getConractdes() + "', "
				+ "'" + appModel.getContactStatus() + "', "
				+ "NOW()"
				+ ") ";
		
		agent.connectMySQL();
		agent.begin();
		rec = agent.exeUpdate(SQL);
		if(rec > 0){
			agent.commit();
		}else{
			agent.rollback();
		}
		agent.disconnectMySQL();
		return rec;
	}
	public int insertAppointmentStatusLog(AppointmentModel appModel){
		int rec = 0;
		String SQL = "INSERT INTO `dentist_appointment_status_log` "
				+ "(`appointment_id`, `description`, "
				+ "`status_code`, `created_date`) "
				+ "VALUES ('" + appModel.getAppointmentID() + "', "
				+ "'" + appModel.getDescription() + "', "
				+ "'" + appModel.getAppointmentStatus() + "', "
				+ "NOW()"
				+ ") ";
		
		agent.connectMySQL();
		agent.begin();
		rec = agent.exeUpdate(SQL);
		if(rec > 0){
			agent.commit();
		}else{
			agent.rollback();
		}
		agent.disconnectMySQL();
		return rec;
	}	
	public int updateAppointmentIsView(AppointmentModel appModel){
		int rec = 0;
		String SQL = "UPDATE  dentist_appointment "
				+ "SET "
				+ "isview = '1' "
				+ "WHERE id = '" + appModel.getAppointmentID() + "' ";
		
		agent.connectMySQL();
		agent.begin();
		rec = agent.exeUpdate(SQL);
		if(rec > 0){
			agent.commit();
		}else{
			agent.rollback();
		}
		agent.disconnectMySQL();
		return rec;
	}
	public int updateAppointmentIsdayView(AppointmentModel appModel){
		int rec = 0;
		String SQL = "UPDATE  dentist_appointment "
				+ "SET "
				+ "isdayview = now() "
				+ "WHERE id = '" + appModel.getAppointmentID() + "' ";
		
		agent.connectMySQL();
		agent.begin();
		rec = agent.exeUpdate(SQL);
		if(rec > 0){
			agent.commit();
		}else{
			agent.rollback();
		}
		agent.disconnectMySQL();
		return rec;
	}
}
