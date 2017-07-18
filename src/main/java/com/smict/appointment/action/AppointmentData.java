package com.smict.appointment.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ldc.util.DBConnect;

public class AppointmentData {
	private DBConnect agent = new DBConnect();
	
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
				+ "dentist_appointment.`status`, dentist_appointment.created_date, "
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
					apModel.setStatus(agent.getRs().getInt("status"));
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
