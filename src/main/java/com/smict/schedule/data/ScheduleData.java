package com.smict.schedule.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.smict.schedule.model.ScheduleModel;

import ldc.util.Auth;
import ldc.util.DBConnect;
import ldc.util.DateUtil;

public class ScheduleData {
	
	private DBConnect agent = new DBConnect();
	
	/**
	 * fetch Treatment room schedule.
	 * @author anubissmile
	 * @param ScheduleModel schModel
	 * @return List<ScheduleModel>
	 */
	public List<ScheduleModel> fetchDentistSchedule(ScheduleModel schModel){
		String start, end, branch_id, room;
		
		start = schModel.getWorkDate() + " 00:00:00";
		end = schModel.getWorkDate() + " 23:59:59";
		branch_id = Auth.user().getBranchCode();
		room = Integer.toString(schModel.getBranchRoomId());
		
		String SQL = "SELECT doctor_workday.workday_id, "
				+ "doctor_workday.doctor_id, "
				+ "doctor_workday.start_datetime, "
				+ "doctor_workday.end_datetime, "
				+ "doctor_workday.work_hour, "
				+ "doctor_workday.branch_id, "
				+ "doctor_workday.branch_room_id, "
				+ "doctor_workday.checkin_status, "
				+ "doctor_workday.checkin_datetime, "
				+ "doctor_workday.checkout_datetime, "
				+ "doctor.first_name_th, "
				+ "doctor.last_name_th, "
				+ "room_id.room_id, room_id.room_name "
				+ "FROM doctor_workday "
				+ "LEFT JOIN doctor ON doctor_workday.doctor_id = doctor.doctor_id "
				+ "LEFT JOIN room_id ON doctor_workday.branch_id = room_id.room_branch_code "
				+ " AND doctor_workday.branch_room_id = room_id.room_id"
				+ " WHERE doctor_workday.start_datetime BETWEEN '" + start + "' AND '" + end + "' "
				+ "AND doctor_workday.branch_id = '" + branch_id + "' AND doctor_workday.branch_room_id = '" + room + "' "
				+ "ORDER BY doctor_workday.branch_room_id ASC, 	doctor_workday.start_datetime ASC ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				List<ScheduleModel> schList = new ArrayList<ScheduleModel>();
				while(agent.getRs().next()){
					ScheduleModel scheduleModel = new ScheduleModel();

					String[] re_start = agent.getRs().getString("start_datetime").split(" ");
					String[] re_end = agent.getRs().getString("end_datetime").split(" ");
					
					scheduleModel.setDBField(
						agent.getRs().getInt("doctor_id"), 
						agent.getRs().getInt("branch_id"), 
						agent.getRs().getInt("branch_room_id"),
						re_start[0],
						re_end[0],
						agent.getRs().getString("checkin_status"),
						agent.getRs().getString("checkin_datetime"),
						agent.getRs().getString("checkout_datetime"),
						agent.getRs().getInt("work_hour"),
						agent.getRs().getString("first_name_th"),
						agent.getRs().getString("last_name_th"),
						agent.getRs().getString("room_name")
					);
					schList.add(scheduleModel);
				}
				return schList;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}
	
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
		
//		System.out.println("==============\n" + SQL + "\n==================");
		agent.connectMySQL();
		int rec = agent.exeUpdate(SQL);
		agent.disconnectMySQL();
		return rec;
	}
}
