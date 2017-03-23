package com.smict.schedule.data;

import com.smict.schedule.model.ScheduleModel;

import ldc.util.DBConnect;

public class ScheduleData {
	
	private DBConnect agent = new DBConnect();
	
	/**
	 * Insert dentist's schedule.
	 * @author anubissmile
	 * @param schModel
	 * @return int | Count of records that affected.
	 */
	public int insertDentistSchedule(ScheduleModel schModel){
		
		String SQL = "INSERT INTO `doctor_workday` "
				+ " (`doctor_id`, `start_datetime`, `end_datetime`, `work_hour`, "
				+ "`branch_id`, `branch_room_id`, `checkin_status`, `checkin_datetime`, `checkout_datetime`)"
				+ " VALUES ('" + schModel.getDoctorId() + "', "
				+ "'" + schModel.getStartDateTime() + "', "
				+ "'" + schModel.getEndDateTime() + "', "
				+ "'" + schModel.getWorkHour() + "', "
				+ "'" + schModel.getBranchId() + "', "
				+ "'" + schModel.getBranchRoomId() + "', "
				+ "'" + schModel.getCheckInStatus() + "', "
				+ "'" + schModel.getCheckInDateTime() + "', "
				+ "'" + schModel.getCheckOutDateTime() + "')";
		
		System.out.println("==============\n" + SQL + "\n==================");
		agent.connectMySQL();
		int rec = agent.exeUpdate(SQL);
		agent.disconnectMySQL();
		return rec;
	}
}
