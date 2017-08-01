package com.smict.appointment.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ldc.util.DBConnect;

public class AppointmentData {
	private DBConnect agent = new DBConnect();
	
	
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
		String SQL = "INSERT INTO `dentist_appointment` (`doctor_id`, `hn`, "
				+ "`recommend`, `branch_code`, "
				+ "`branch_id`, `datetime_start`, "
				+ "`datetime_end`, `contact_status`, "
				+ "`appointment_status`, `created_date`, "
				+ "`updated_date`) "
				+ "VALUES ('" + appModel.getDoctorID() + "', '" + appModel.getHN() + "', "
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
}
