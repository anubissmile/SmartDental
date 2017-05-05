package com.smict.schedule.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
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
				ResultSet rs = agent.getRs();
				List<ScheduleModel> schList = new ArrayList<ScheduleModel>();
				while(agent.getRs().next()){
					ScheduleModel scheduleModel = new ScheduleModel();
					
					String[] re_start = agent.getRs().getString("start_datetime").split(" ");
					String[] re_end = agent.getRs().getString("end_datetime").split(" ");
					
					scheduleModel.setDBField(
						rs.getInt("doctor_id"), 
						rs.getInt("branch_id"), 
						rs.getInt("branch_room_id"),
						re_start[1],
						re_end[1],
						rs.getString("checkin_status"),
						rs.getString("checkin_datetime"),
						rs.getString("checkout_datetime"),
						rs.getInt("work_hour"),
						rs.getString("first_name_th"),
						rs.getString("last_name_th"),
						rs.getString("room_name"),
						schModel.getWorkDate(),
						rs.getInt("workday_id")
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
	
	
	/**
	 * Finding the overlap time range.
	 * @author anubissmile
	 * @param ScheduleModel | schModel
	 * @return boolean | Return True when is overlap otherwise False.
	 */
	public boolean findOverlapTimeRange(ScheduleModel schModel){
		String SQL = "SELECT doctor_workday.workday_id, "
				+ "doctor_workday.doctor_id, "
				+ "doctor_workday.start_datetime, "
				+ "doctor_workday.end_datetime, "
				+ "doctor_workday.work_hour, "
				+ "doctor_workday.branch_id, "
				+ "doctor_workday.branch_room_id, "
				+ "doctor_workday.checkin_status, "
				+ "doctor_workday.checkin_datetime, "
				+ "doctor_workday.checkout_datetime "
				+ "FROM doctor_workday "
				+ "WHERE doctor_workday.start_datetime BETWEEN '" + schModel.getStartDateTime() + "' AND '" + schModel.getEndDateTime() + "' OR "
				+ "doctor_workday.end_datetime BETWEEN '" + schModel.getStartDateTime() + "' AND '" + schModel.getEndDateTime() + "' "
				+ "AND doctor_workday.branch_room_id = '" + schModel.getBranchRoomId() + "' ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		int size = agent.size();
		agent.disconnectMySQL();
		if(size > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * Checking in the treatment room
	 * @param ScheduleModel schModel
	 * @return int | Count of row that get affected by manipulate.
	 */
	public int scheduleCheckingIn(ScheduleModel schModel){
		String SQL = "UPDATE `doctor_workday` SET `checkin_status`='2', `checkin_datetime` = '" + DateUtil.GetDatetime_YYYY_MM_DD_HH_MM_SS() + "' "
				+ " WHERE (`workday_id`='" + schModel.getWorkDayId() + "' AND `branch_id` = '" + schModel.getBranchId() + "')";
		
		agent.connectMySQL();
		int rec = agent.exeUpdate(SQL);
		agent.disconnectMySQL();
		return rec;
	}
	
	/**
	 * Checking Out the treatment room
	 * @param ScheduleModel schModel
	 * @return int | Count of row that get affected by manipulate.
	 */
	public int scheduleCheckingOut(ScheduleModel schModel){
		
		String SQL = "UPDATE `doctor_workday` SET `checkin_status`='3', `checkout_datetime` = '" + DateUtil.GetDatetime_YYYY_MM_DD_HH_MM_SS() + "' "
				+ " WHERE (`workday_id`='" + schModel.getWorkDayId() + "' AND `branch_id` = '" + schModel.getBranchId() + "')";
		
		agent.connectMySQL();
		int rec = agent.exeUpdate(SQL);
		agent.disconnectMySQL();
		return rec;
	}
	public int EmpCheckingIn(ScheduleModel schModel, String [] Emp){
		int i = 0;
		
		String SQL = "INSERT INTO employee_workday (emp_id,branch_id,doctor_workday_id) "
						+"VALUES ";
				
				for(String empId : Emp){
					if(i>0){
						SQL +=",";
					}
					SQL += "('"+empId+"','"+schModel.getBranchId()+"','"+schModel.getWorkDayId()+"')";
					i++;
				}
				
		
		agent.connectMySQL();
		int rec = agent.exeUpdate(SQL);
		agent.disconnectMySQL();
		return rec;
	}	

		
	
	
	
	
	
	
	
	
}
