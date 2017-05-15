package com.smict.schedule.data;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.smict.person.model.DoctorModel;
import com.smict.schedule.model.ScheduleModel;

import ldc.util.Auth;
import ldc.util.DBConnect;
import ldc.util.DateUtil;

public class ScheduleData {
	
	private DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	
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

	public Map<String,String> Get_DoctorlistForWork() throws IOException, Exception {
		String SQL = "SELECT doctor.doctor_id, doctor.first_name_th, doctor.last_name_th, pre_name.pre_name_th "
				+ "FROM doctor "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = doctor.pre_name_id ";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		ResultSet rs = Stmt.executeQuery(SQL);

		Map <String,String>ResultList = new HashMap<String,String>();
		
		while (rs.next()) {
			// vender_id,vender_name,create_by,create_datetime,update_by,update_datetime
			ResultList.put(rs.getString("doctor_id"), rs.getString("pre_name_th")+""+rs.getString("first_name_th")+" "+rs.getString("last_name_th"));	
		}

		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return ResultList;
	}
	public List<ScheduleModel> ListDoctorWorkDayCheck(){
		String branchID = Auth.user().getBranchCode();
		String SQL = "SELECT doctor_workday.workday_id, doctor_workday.doctor_id, pre_name.pre_name_th, doctor.first_name_th, doctor.last_name_th, "
					+ "doctor_workday.start_datetime, doctor_workday.end_datetime, "
					+ "CASE doctor_workday.checkin_status WHEN '1' THEN 'Waiting' WHEN '2' THEN 'CheckIn' WHEN '3' THEN 'CheckOut' END AS 'Status' "
					+ "FROM "
					+ "doctor_workday "
					+ "INNER JOIN doctor ON doctor_workday.doctor_id = doctor.doctor_id "
					+ "INNER JOIN pre_name ON doctor.pre_name_id = pre_name.pre_name_id "
					+ "WHERE DATE_FORMAT(doctor_workday.start_datetime,'%Y-%m-%d')  =  CURDATE() "
					+ "AND doctor_workday.branch_id = '"+branchID+"' "
					+ "ORDER BY doctor_workday.checkin_status desc ";
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			ResultSet res = Stmt.executeQuery(SQL);
			
			List<ScheduleModel> schModelList = new ArrayList<ScheduleModel>();
			while(res.next()){
				ScheduleModel schModel = new ScheduleModel();
				schModel.setWorkDayId(res.getInt("workday_id"));
				schModel.setDoctorId(res.getInt("doctor_id"));
				schModel.setPre_name_th(res.getString("pre_name_th"));
				schModel.setFirst_name_th(res.getString("first_name_th"));
				schModel.setLast_name_th(res.getString("last_name_th"));
				schModel.setCheckInStatus(res.getString("Status"));
				String[] dateTime = res.getString("start_datetime").split(" ");
				String[] time = dateTime[1].split(Pattern.quote("."));
				schModel.setStartDateTime(time[0]);
				String[] dateTimeend = res.getString("end_datetime").split(" ");
				String[] timeend = dateTimeend[1].split(Pattern.quote("."));
				schModel.setEndDateTime(timeend[0]);
				schModelList.add(schModel);
			}
			if (!res.isClosed())
				res.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			return schModelList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	public int InsertDentistEmergency(ScheduleModel schModel){
		String SQL = "INSERT INTO `doctor_workday` "
				+ " (`doctor_id`, `start_datetime`, `end_datetime`, `work_hour`, "
				+ "`branch_id`, `checkin_status`, `branch_room_id`, `checkin_datetime`, `checkout_datetime`)"
				+ " VALUES ('" + schModel.getDoctorId() + "', "
				+ " concat(CURDATE(),' " + schModel.getStartDateTime() + "',':00'), "
				+ " concat(CURDATE(),' " + schModel.getEndDateTime() + "',':00'), "
				+ "'" + schModel.getWorkHour() + "', "
				+ "'" + schModel.getBranchId() + "', "
				+ "'" + schModel.getCheckInStatus() + "', "
				+ "'0', "
				+ "'" + schModel.getCheckInDateTime() + "', "
				+ "'" + schModel.getCheckOutDateTime() + "')";
		
//		System.out.println("==============\n" + SQL + "\n==================");
		agent.connectMySQL();
		int rec = agent.exeUpdate(SQL);
		agent.disconnectMySQL();
		return rec;
	}
	
	
	
	
	
	
	
}
