package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.Map;
import com.smict.all.model.DoctTimeModel;
import com.smict.person.model.BranchModel;
import com.smict.person.model.DoctorModel;
import com.smict.person.model.Person;

import ldc.util.Auth;
import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class DoctorData {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	ResultSet rs = null;
	PreparedStatement pStmt = null,pStmt2=null;
	
	
	
	public int delScheduleFromCalendar(DoctTimeModel docTimeModel){
		int rec = 0;
		String SQL = "DELETE FROM `doctor_workday` WHERE (`workday_id`='" + docTimeModel.getWorkday_id() + "')";
		
		agent.connectMySQL();
		agent.begin();
		rec = agent.exeUpdate(SQL);
		agent.commit();
		agent.disconnectMySQL();
		return rec;
	}
	
	/**
	 * Add doctor workday from calendar.
	 * @author anubissmile
	 * @param DoctTimeModel docTimeModel
	 * @return int rec | Count of record that get inserted.
	 */
	public int addDoctorWorkdayFromCalendar(DoctTimeModel docTimeModel){
		int rec = 0;
		String SQL = "INSERT INTO "
				+ "`doctor_workday` (`doctor_id`, `start_datetime`, "
				+ "`end_datetime`, `work_hour`, "
				+ "`branch_id`, `branch_room_id`, "
				+ "`checkin_datetime`, `checkout_datetime`) "
				+ "VALUES ('" + docTimeModel.getDoctorID() + "', '" + docTimeModel.getTime_in() + "', "
				+ "'" + docTimeModel.getTime_out() + "', '" + docTimeModel.getMinutes() + "', "
				+ "'" + docTimeModel.getBranch_id() + "', '0', '0000-00-00 00:00:01', "
				+ "'0000-00-00 00:00:01') ";

		agent.connectMySQL();
		agent.begin();
		rec = agent.exeUpdate(SQL);
		agent.commit();
		agent.disconnectMySQL();
		return rec;
	}
	
	/**
	 * Checking out for doctor's workday that was duplicates.
	 * @author anubissmile
	 * @param DoctTimeModel docTimeModel
	 * @return int rec | Amount of record that duplicates.
	 */
	public int checkDoctorWorkDayDuplicate(DoctTimeModel docTimeModel){
		int rec = 0;
		String SQL = "SELECT COUNT(doctor_workday.workday_id) AS count "
				+ "FROM doctor_workday "
				+ "LEFT JOIN branch ON branch.branch_code = doctor_workday.branch_id "
				+ "WHERE doctor_workday.doctor_id = '" + docTimeModel.getDoctorID() + "' "
				+ "AND ( ( doctor_workday.end_datetime > '" + docTimeModel.getTime_in() + "' AND doctor_workday.start_datetime < '" + docTimeModel.getTime_in() + "' ) "
				+ "OR ( doctor_workday.start_datetime < '" + docTimeModel.getTime_out() + "' AND doctor_workday.end_datetime > '" + docTimeModel.getTime_out() + "' ) ) ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		rs = agent.getRs();
		if(agent.size() > 0){
			try {
				rs.next();
				rec = rs.getInt("count");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				agent.disconnectMySQL();
			}
		}
		
		return rec;
	}
	
	/**
	 * Get doctor schedule by doctor's ID.
	 * @author anubissmile
	 * @param String docID | Doctor 's ID
	 * @return List<HashMap<String, String>> docWorkDayList | Result set as List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getDoctorWorkDayByID(String docID){
		List<HashMap<String, String>> docWorkDayList = new ArrayList<HashMap<String, String>>();
		String SQL = "SELECT doctor_workday.workday_id, doctor_workday.doctor_id, doctor_workday.start_datetime, "
				+ "doctor_workday.end_datetime, pre_name.pre_name_th, "
				+ "doctor.first_name_th, doctor.last_name_th, "
				+ "doctor.first_name_en, doctor.last_name_en, "
				+ "doctor_workday.work_hour, branch.branch_code, "
				+ "branch.brand_id, branch.branch_id, "
				+ "branch.branch_name "
				+ "FROM doctor_workday "
				+ "LEFT JOIN doctor ON doctor_workday.doctor_id = doctor.doctor_id "
				+ "LEFT JOIN pre_name ON doctor.pre_name_id = pre_name.pre_name_id "
				+ "LEFT JOIN branch ON doctor_workday.branch_id = branch.branch_code "
				+ "WHERE doctor_workday.doctor_id = '" + docID + "' "
				+ "ORDER BY doctor_workday.start_datetime DESC "
				+ "LIMIT 0, 500 ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		rs = agent.getRs();
		try {
			if(agent.size() > 0){
				while(rs.next()){
					HashMap<String, String> result = new HashMap<String, String>();
					result.put("workday_id", rs.getString("workday_id"));
					result.put("doctor_id", rs.getString("doctor_id"));
					result.put("start_datetime", rs.getString("start_datetime"));
					result.put("end_datetime", rs.getString("end_datetime"));
					result.put("pre_name_th", rs.getString("pre_name_th"));
					result.put("first_name_th", rs.getString("first_name_th"));
					result.put("last_name_th", rs.getString("last_name_th"));
					result.put("first_name_en", rs.getString("first_name_en"));
					result.put("first_name_en", rs.getString("first_name_en"));
					result.put("work_hour", rs.getString("work_hour"));
					result.put("branch_code", rs.getString("branch_code"));
					result.put("branch_code", rs.getString("branch_id"));
					result.put("brand_id", rs.getString("brand_id"));
					result.put("branch_name", rs.getString("branch_name"));
					docWorkDayList.add(result);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		return docWorkDayList;
	}
	

	
	/**
	 * Add doctor's workday depend on month pattern.
	 * @author anubissmile
	 * @param DoctorModel docModel
	 * @param DoctTimeModel docTimeModel
	 * @return int rec | Count of record that get affected.
	 */
	public int addDoctorWorkdayPattern(DoctorModel docModel, DoctTimeModel docTimeModel){
		DateUtil dt = new DateUtil();
		int key = 0;
		String[] workMonth;
		List<String> insertVal = new ArrayList<String>();
		DateTime firstOfMonth, endOfMonth, nowDate;
		DateTimeFormatter dayName = DateTimeFormat.forPattern("E");
		DateTimeFormatter fullDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter fullDate = DateTimeFormat.forPattern("yyyy-MM-dd");
		String day, fullDay;
		String startDateTime, endDateTime;
		int workMinutes;

		/**
		 * Loop month.
		 */
		for(String month : docTimeModel.getWork_month()){
			/**
			 * Convert BE. to AD.
			 */
			workMonth = month.split("-");
			workMonth[1] = String.valueOf(Integer.parseInt(workMonth[1]) - 543);
			
			/**
			 * Make first date of month.
			 */
			nowDate = firstOfMonth = DateTime.parse(workMonth[1] + "-" + workMonth[0] + "-" + "01");
			System.out.println(firstOfMonth);
			
			/**
			 * Find Maximum date of month.	
			 */
			endOfMonth = firstOfMonth.dayOfMonth().withMaximumValue();
			System.out.println(endOfMonth);
			
			/**
			 * Loop day
			 */
			while(Days.daysBetween(nowDate, endOfMonth).getDays() >= 0){
				/**
				 * Get day name.
				 */
				day = dayName.print(nowDate);
				fullDay = fullDate.print(nowDate);
				System.out.print(day.concat(" | "));
				System.out.print(fullDay.concat(" | "));
				System.out.println(nowDate);

				/**
				 * Fetch time range by day
				 */
				//Mon
				// ('1', '2017-06-01 05:23:24', '2017-06-01 15:23:24', '25', '431', '0', '0000-00-00 00:00:01', '0000-00-00 00:00:01')
				if(day.equals("Mon") || day.equals("จ.") && (!docTimeModel.getTime_in_mon().get(key).equals("00:00") || !docTimeModel.getTime_out_mon().get(key).equals("00:00"))){
					workMinutes = Minutes.minutesBetween(
							LocalTime.parse(docTimeModel.getTime_in_mon().get(key)), 
							LocalTime.parse(docTimeModel.getTime_out_mon().get(key))
					).getMinutes();
					startDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_in_mon().get(key)).concat(":00");
					endDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_out_mon().get(key)).concat(":00");
					insertVal.add(" ('" + docModel.getDoctorID() + "', '" + startDateTime + "', '" + endDateTime + "', '" + workMinutes + "', '" + docModel.getBranch_id() + "', '0', '1', '0000-00-00 00:00:01', '0000-00-00 00:00:01') ");
					nowDate = nowDate.plusDays(1);
					continue;
				}
				
				//Tue
				if(day.equals("Tue") || day.equals("อ.") && (!docTimeModel.getTime_in_tue().get(key).equals("00:00") || !docTimeModel.getTime_out_tue().get(key).equals("00:00"))){
					workMinutes = Minutes.minutesBetween(
							LocalTime.parse(docTimeModel.getTime_in_tue().get(key)), 
							LocalTime.parse(docTimeModel.getTime_out_tue().get(key))
					).getMinutes();
					startDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_in_tue().get(key)).concat(":00");
					endDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_out_tue().get(key)).concat(":00");
					insertVal.add(" ('" + docModel.getDoctorID() + "', '" + startDateTime + "', '" + endDateTime + "', '" + workMinutes + "', '" + docModel.getBranch_id() + "', '0', '1', '0000-00-00 00:00:01', '0000-00-00 00:00:01') ");
					nowDate = nowDate.plusDays(1);
					continue;
				}
				
				//Wed
				if(day.equals("Wed") || day.equals("พ.") && (!docTimeModel.getTime_in_wed().get(key).equals("00:00") || !docTimeModel.getTime_out_wed().get(key).equals("00:00"))){
					workMinutes = Minutes.minutesBetween(
							LocalTime.parse(docTimeModel.getTime_in_wed().get(key)), 
							LocalTime.parse(docTimeModel.getTime_out_wed().get(key))
					).getMinutes();
					startDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_in_wed().get(key)).concat(":00");
					endDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_out_wed().get(key)).concat(":00");
					insertVal.add(" ('" + docModel.getDoctorID() + "', '" + startDateTime + "', '" + endDateTime + "', '" + workMinutes + "', '" + docModel.getBranch_id() + "', '0', '1', '0000-00-00 00:00:01', '0000-00-00 00:00:01') ");
					nowDate = nowDate.plusDays(1);
					continue;
				}
				
				//Thu
				if(day.equals("Thu") || day.equals("พฤ.") && (!docTimeModel.getTime_in_thu().get(key).equals("00:00") || !docTimeModel.getTime_out_thu().get(key).equals("00:00"))){
					workMinutes = Minutes.minutesBetween(
							LocalTime.parse(docTimeModel.getTime_in_thu().get(key)), 
							LocalTime.parse(docTimeModel.getTime_out_thu().get(key))
					).getMinutes();
					startDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_in_thu().get(key)).concat(":00");
					endDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_out_thu().get(key)).concat(":00");
					insertVal.add(" ('" + docModel.getDoctorID() + "', '" + startDateTime + "', '" + endDateTime + "', '" + workMinutes + "', '" + docModel.getBranch_id() + "', '0', '1', '0000-00-00 00:00:01', '0000-00-00 00:00:01') ");
					nowDate = nowDate.plusDays(1);
					continue;
				}
				
				//Fri
				if(day.equals("Fri") || day.equals("ศ.") && (!docTimeModel.getTime_in_fri().get(key).equals("00:00") || !docTimeModel.getTime_out_fri().get(key).equals("00:00"))){
					workMinutes = Minutes.minutesBetween(
							LocalTime.parse(docTimeModel.getTime_in_fri().get(key)), 
							LocalTime.parse(docTimeModel.getTime_out_fri().get(key))
					).getMinutes();
					startDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_in_fri().get(key)).concat(":00");
					endDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_out_fri().get(key)).concat(":00");
					insertVal.add(" ('" + docModel.getDoctorID() + "', '" + startDateTime + "', '" + endDateTime + "', '" + workMinutes + "', '" + docModel.getBranch_id() + "', '0', '1', '0000-00-00 00:00:01', '0000-00-00 00:00:01') ");
					nowDate = nowDate.plusDays(1);
					continue;
				}
				
				//Sat
				if(day.equals("Sat") || day.equals("ส.") && (!docTimeModel.getTime_in_sat().get(key).equals("00:00") || !docTimeModel.getTime_out_sat().get(key).equals("00:00"))){
					workMinutes = Minutes.minutesBetween(
							LocalTime.parse(docTimeModel.getTime_in_sat().get(key)), 
							LocalTime.parse(docTimeModel.getTime_out_sat().get(key))
					).getMinutes();
					startDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_in_sat().get(key)).concat(":00");
					endDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_out_sat().get(key)).concat(":00");
					insertVal.add(" ('" + docModel.getDoctorID() + "', '" + startDateTime + "', '" + endDateTime + "', '" + workMinutes + "', '" + docModel.getBranch_id() + "', '0', '1', '0000-00-00 00:00:01', '0000-00-00 00:00:01') ");
					nowDate = nowDate.plusDays(1);
					continue;
				}
				
				//Sun
				if(day.equals("Sun") || day.equals("อา.") && (!docTimeModel.getTime_in_sun().get(key).equals("00:00") || !docTimeModel.getTime_out_sun().get(key).equals("00:00"))){
					workMinutes = Minutes.minutesBetween(
							LocalTime.parse(docTimeModel.getTime_in_sun().get(key)), 
							LocalTime.parse(docTimeModel.getTime_out_sun().get(key))
					).getMinutes();
					startDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_in_sun().get(key)).concat(":00");
					endDateTime = fullDate.print(nowDate).toString().concat(" ").concat(docTimeModel.getTime_out_sun().get(key)).concat(":00");
					insertVal.add(" ('" + docModel.getDoctorID() + "', '" + startDateTime + "', '" + endDateTime + "', '" + workMinutes + "', '" + docModel.getBranch_id() + "', '0', '1', '0000-00-00 00:00:01', '0000-00-00 00:00:01') ");
					nowDate = nowDate.plusDays(1);
					continue;
				}
				
				/**
				 * Plus one day.
				 */
				nowDate = nowDate.plusDays(1);
			}
			
			++key;
		}
		
		String SQL = String.join(", ", insertVal);
		SQL = "INSERT INTO `doctor_workday` "
				+ "(`doctor_id`, `start_datetime`, "
				+ "`end_datetime`, `work_hour`, "
				+ "`branch_id`, `branch_room_id`, "
				+ "`checkin_status`, `checkin_datetime`, `checkout_datetime`) "
				+ "VALUES ".concat(SQL);
		
		agent.connectMySQL();
		agent.begin();
		int rec = agent.exeUpdate(SQL);
		agent.commit();
		agent.disconnectMySQL();
		
		return rec;
	}
	
	/**
	 * Get telephone list and return HashMap
	 * @author anubissmile
	 * @return HashMap<String, String> telTypeMap | Telephone list in HashMap. 
	 */
	public HashMap<String, String> getTelephoneTypeList(){
		HashMap<String, String> telTypeMap = new HashMap<String, String>();
		String SQL = "SELECT tel_teltype.tel_typeid, tel_teltype.tel_typename "
				+ "FROM tel_teltype"
				+ " WHERE tel_teltype.tel_typeid NOT IN (5) ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		try {
			if(agent.size() > 0){
				while(agent.getRs().next()){
					telTypeMap.put(
							agent.getRs().getString("tel_typeid"),
							agent.getRs().getString("tel_typename")
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		return telTypeMap;
	}
	
	public List<DoctorModel> getDentistList(String dentistId){
		List<DoctorModel> doctorList = new ArrayList<DoctorModel>();
		String SQL = " SELECT * FROM `doctor` ";

		if(dentistId != null && !dentistId.equals("")){
			SQL += " WHERE doctor_id = '" + dentistId + "' ";
		}
		
		SQL += " LIMIT 0,500 ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		try {
			if(agent.size()>0){
				while(agent.getRs().next()){
					DoctorModel docModel = new DoctorModel();
					docModel.setDoctorID(agent.getRs().getInt("doctor_id"));
					docModel.setPre_name_id(agent.getRs().getString("pre_name_id"));
					docModel.setFirstname_th(agent.getRs().getString("first_name_th"));
					docModel.setLastname_th(agent.getRs().getString("last_name_th"));
					docModel.setFirstname_en(agent.getRs().getString("first_name_en"));
					docModel.setLastname_en(agent.getRs().getString("last_name_en"));
					docModel.setBranchID(agent.getRs().getInt("doc_branch_id"));
					docModel.setNickname(agent.getRs().getString("nickname"));
					docModel.setBirth_date(agent.getRs().getString("birth_date"));
					docModel.setTMCLicense(agent.getRs().getString("TMC_license"));
					docModel.setTitle(agent.getRs().getString("title"));
					docModel.setIdentification(agent.getRs().getString("identification"));
					docModel.setIdentification_type(agent.getRs().getString("identification_type"));
					docModel.setTel_id(agent.getRs().getInt("tel_id"));
					docModel.setHireDate(agent.getRs().getString("hired_date"));
					docModel.setRemark(agent.getRs().getString("remark"));
					docModel.setAddr_id(agent.getRs().getInt("addr_id"));
					docModel.setBookBankId(agent.getRs().getInt("bookbank_id"));
					docModel.setWork_history_id(agent.getRs().getInt("work_history_id"));
					docModel.setEdu_id(agent.getRs().getInt("doc_education_id"));
					docModel.setEmp_id(agent.getRs().getString("emp_id"));
					docModel.setContract_id(agent.getRs().getString("contract_id"));
					doctorList.add(docModel);
				}
			}else{
				doctorList = null;
			}
			agent.disconnectMySQL();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctorList;
	}
	
	public List<DoctorModel> Get_DoctorList(String doctor_id) throws IOException, Exception {
		String sqlQuery = "SELECT doctor.doctor_id,doctor.pre_name_id,pre_name.pre_name_th,pre_name.pre_name_en,doctor.first_name_th,doctor.last_name_th,doctor.first_name_en,"
				+ "doctor.last_name_en,doctor.nickname,doctor.birth_date,doctor.TMC_license,doctor.title,doctor.identification,doctor.identification_type,"
				+ "doctor.profile_pic,doctor.remark,doctor.hired_date,doctor.tel_id,doctor.doc_branch_id,doctor.addr_id,doctor.work_status,doctor.bookbank_id,doctor.work_history_id,"
				+ "doctor.doc_education_id,doctor.emp_id,doctor.contract_id "
				+ "FROM doctor "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = doctor.pre_name_id "
				+ "WHERE ";
		if (new Validate().Check_String_notnull_notempty(doctor_id))
			sqlQuery += "doctor.doctor_id = '" + doctor_id + "' and ";
 
		
		sqlQuery += "doctor.doctor_id <> '' AND work_status=1 "
				+ "ORDER BY doctor.doctor_id ASC ";
		
		System.out.println(sqlQuery);
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<DoctorModel> ResultList = new ArrayList<DoctorModel>();
		while (rs.next()) {
			DoctorModel docModel = new DoctorModel();
			docModel.setDoctorID(rs.getInt("doctor_id"));
			docModel.setPre_name_id(rs.getString("pre_name_id"));
			docModel.setPre_name(rs.getString("pre_name_th"));
			docModel.setPre_name_en(rs.getString("pre_name_en"));
			docModel.setFirstname_th(rs.getString("first_name_th"));
			docModel.setLastname_th(rs.getString("last_name_th"));
			docModel.setFirstname_en(rs.getString("first_name_en"));
			docModel.setLastname_en(rs.getString("last_name_en"));
			docModel.setBranchID(rs.getInt("doc_branch_id"));
			docModel.setNickname(rs.getString("nickname"));
			docModel.setBirth_date(rs.getString("birth_date"));
			docModel.setTMCLicense(rs.getString("TMC_license"));
			docModel.setTitle(rs.getString("title"));
			docModel.setIdentification(rs.getString("identification"));
			docModel.setIdentification_type(rs.getString("identification_type"));
			docModel.setTel_id(rs.getInt("tel_id"));
			docModel.setHireDate(rs.getString("hired_date"));
			docModel.setRemark(rs.getString("remark"));
			docModel.setAddr_id(rs.getInt("addr_id"));
			docModel.setBookBankId(rs.getInt("bookbank_id"));
			docModel.setWork_history_id(rs.getInt("work_history_id"));
			docModel.setEdu_id(rs.getInt("doc_education_id"));
			docModel.setEmp_id(rs.getString("emp_id"));
			docModel.setContract_id(rs.getString("contract_id"));
			
			ResultList.add(docModel);
		}
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return ResultList;
	}
	
	public DoctorModel Get_DoctorDetail(int doctor_id) throws IOException, Exception {
		String sqlQuery = "SELECT doctor.doctor_id,doctor.pre_name_id,pre_name.pre_name_th,pre_name.pre_name_en,doctor.first_name_th,doctor.last_name_th,doctor.first_name_en,"
				+ "doctor.last_name_en,doctor.nickname,doctor.birth_date,doctor.TMC_license,doctor.title,doctor.identification,doctor.identification_type,"
				+ "doctor.profile_pic,doctor.remark,doctor.hired_date,doctor.tel_id,doctor.doc_branch_id,doctor.addr_id,doctor.work_status,doctor.bookbank_id,doctor.work_history_id,"
				+ "doctor.doc_education_id,doctor.emp_id,doctor.contract_id, doctor.line_id, doctor.email "
				+ "FROM doctor "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = doctor.pre_name_id "
				+ "WHERE doctor_id = "+ doctor_id+" AND work_status=1";
 
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		DoctorModel docModel = new DoctorModel();
		while (rs.next()) {
			docModel.setWork_status(rs.getString("work_status"));
			docModel.setDoctorID(rs.getInt("doctor_id"));
			docModel.setPre_name_id(rs.getString("pre_name_id"));
			docModel.setPre_name(rs.getString("pre_name_th"));
			docModel.setPre_name_en(rs.getString("pre_name_en"));
			docModel.setFirstname_th(rs.getString("first_name_th"));
			docModel.setLastname_th(rs.getString("last_name_th"));
			docModel.setFirstname_en(rs.getString("first_name_en"));
			docModel.setLastname_en(rs.getString("last_name_en"));
			docModel.setBranchID(rs.getInt("doc_branch_id"));
			docModel.setNickname(rs.getString("nickname"));
			docModel.setBirth_date(rs.getString("birth_date"));
			docModel.setTMCLicense(rs.getString("TMC_license"));
			docModel.setTitle(rs.getString("title"));
			docModel.setIdentification(rs.getString("identification"));
			docModel.setIdentification_type(rs.getString("identification_type"));
			docModel.setTel_id(rs.getInt("tel_id"));
			docModel.setHireDate(rs.getString("hired_date"));
			docModel.setProfile_pic(rs.getString("profile_pic"));
			docModel.setRemark(rs.getString("remark"));
			docModel.setAddr_id(rs.getInt("addr_id"));
			docModel.setBookBankId(rs.getInt("bookbank_id"));
			docModel.setWork_history_id(rs.getInt("work_history_id"));
			docModel.setEdu_id(rs.getInt("doc_education_id"));
			docModel.setEmp_id(rs.getString("emp_id"));
			docModel.setContract_id(rs.getString("contract_id"));
			docModel.setLineId(rs.getString("line_id"));
			docModel.setEmail(rs.getString("email"));
		}
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return docModel;
	}
	public int AddDoctor(DoctorModel doctor){
		int doctor_id = 0;
		String sql = "select max(doctor_id)+1 as doctor_id from doctor";
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			while(rs.next()){
				doctor_id = rs.getInt("doctor_id");
			}
			
		 sql = "INSERT INTO doctor ( "
				+ "doctor_id , pre_name_id , first_name_th , last_name_th , first_name_en , last_name_en , nickname , birth_date , TMC_license , title , "
				+ "identification , identification_type , tel_id  , profile_pic , remark , doc_branch_id , hired_date , work_status  ,addr_id,contract_id,emp_id,work_history_id,doc_education_id,bookbank_id, line_id, email) "
				+ "VALUES ("
				+doctor_id+","
				+ "'"+doctor.getPre_name_id()+"',"
				+ "'"+doctor.getFirstname_th()+"',"
				+ "'"+doctor.getLastname_th()+"',"
				+ "'"+doctor.getFirstname_en()+"',"
				+ "'"+doctor.getLastname_en()+"',"
				+ "'"+doctor.getNickname()+"',"
				+ "'"+doctor.getBirth_date()+"',"
				+ "'"+doctor.getTMCLicense()+"',"
				+ "'"+doctor.getTitle()+"',"
				+ "'"+doctor.getIdentification()+"',"
				+ "'"+doctor.getIdentification_type()+"',"
				+ "'"+doctor.getTel_id()+"',"
				+ "'"+doctor.getProfile_pic()+"',"
				+ "'"+doctor.getRemark()+"',"
				+ "'"+doctor.getBranchID()+"',"
				+ "'"+doctor.getHireDate()+"',"
				+ "1,"
				+ doctor.getAddr_id()+","
				+ "'"+doctor.getContract_id()+ "',"
				+ "'"+doctor.getEmp_id()+ "',"
				+ "'"+doctor.getWork_history_id()+ "',"
				+ doctor.getEdu_id()+","
				+doctor.getBookBankId()+ ","
				+ "'" + doctor.getLineId() + "', '" + doctor.getEmail() + "')";
		
			System.out.println(sql);
		 	pStmt = conn.prepareStatement(sql);
			
			
			if(pStmt.executeUpdate()<=0){

				return -99;
			}
			pStmt.close();
			conn.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -99;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -99;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -99;
		}
		return doctor_id;
	}
	public void UpdateDoctor(DoctorModel doctor){
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			int addr_id = doctor.getAddr_id();
			if(addr_id == 0){
				
			}
			doctor.getTel_id();
			doctor.getBookBankId();
			
			String sql = "UPDATE doctor SET "
					+ "pre_name_id="+doctor.getPre_name_id()
					+ ",first_name_th='"+doctor.getFirstname_th()+"'"
					+ ",last_name_th='"+doctor.getLastname_th()+"'"
					+ ",first_name_en='"+doctor.getFirstname_en()+"'"
					+ ",last_name_en='"+doctor.getLastname_en()+"'"
					+ ",nickname='"+doctor.getNickname()+"'"
					+ ",birth_date='"+doctor.getBirth_date()+"'"
					+ ",hired_date='"+doctor.getHireDate()+"'"					
					+ ",TMC_license='"+doctor.getTMCLicense()+"'"
					+ ",title='"+doctor.getTitle()+"'"
					+ ",identification='"+doctor.getIdentification()+"'"
					+ ",identification_type='"+doctor.getIdentification_type()+"'"
					+ ",profile_pic='"+doctor.getProfile_pic()+"'"
					+ ",remark='"+doctor.getRemark()+"'"
					+ ",doc_branch_id='"+doctor.getBranchID()+"'"
					+ ",tel_id= '"+doctor.getTel_id() + "'"
					+ ",addr_id= '"+doctor.getAddr_id() + "'"
					+ ",line_id = '"+doctor.getLineId() + "'"
					+ ",email = '"+doctor.getEmail() + "'"
					+ ",bookbank_id = '"+doctor.getBookBankId() + "'"
					+ ",contract_id = '"+doctor.getContract_id() + "'"
					+ ",emp_id = '"+doctor.getEmp_id() + "', "
					+ "work_status = '"+doctor.getWork_status()+"' "						
					+"WHERE doctor_id="+doctor.getDoctorID();
			Stmt.executeUpdate(sql);
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int AddDoctorBranch(List<BranchModel> branch){
		int doc_branch_id=0;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			
			String sqlid = "SELECT MAX(doc_branch_id)+1 AS doc_branch_id FROM doctor_branch";
			rs = Stmt.executeQuery(sqlid);
			while(rs.next()){
				doc_branch_id = rs.getInt("doc_branch_id");
			}
			
			String sql = "INSERT INTO doctor_branch (doc_branch_id,branch_id)VALUES";
			int i = 0;
			for (BranchModel branchModel : branch) {
				i++;
				if(i>1){
					sql += ",";
				}
				sql += "("+doc_branch_id+",'"+branchModel.getBranch_id()+"')";
			}
			
			pStmt = conn.prepareStatement(sql);
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return doc_branch_id;
	}
	public int AddDoctorBranch(List<BranchModel> branch,int doc_branch_id){
		
		
		try {
			conn = agent.getConnectMYSql();
			
			String sql = "INSERT INTO doctor_branch (doc_branch_id,branch_id)VALUES";
			int i = 0;
			for (BranchModel branchModel : branch) {
				i++;
				if(i>1){
					sql += ",";
				}
				sql += "("+doc_branch_id+",'"+branchModel.getBranch_id()+"')";
			}
			
			pStmt = conn.prepareStatement(sql);
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return doc_branch_id;
	}
	public void del_doctor_branch(int doc_branch_id){
		try {
			conn = agent.getConnectMYSql();
			String sql = "DELETE FROM doctor_branch WHERE doc_branch_id="+doc_branch_id;
			pStmt = conn.prepareStatement(sql);
			pStmt.executeUpdate();
		//	conn.commit();
			pStmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void UpdateMgrBranch(List<BranchModel> branch){
		try {
			conn = agent.getConnectMYSql();
			for (BranchModel branchModel : branch) {
				
				String sql = "UPDATE branch set doctor_id='"+branchModel.getDoctor_id()+"' WHERE branch_id ='"+branchModel.getBranch_id()+"'";
					
				pStmt = conn.prepareStatement(sql);
				pStmt.executeUpdate();
				String transacSLQ = "INSERT INTO branch_mgr_history "
						+ "(brand_id_start,brand_name_start,branch_id_start,branch_name_start,doctor_id_start,price_doctor_start,start_date) "
						+ "SELECT branch.brand_id,brand.brand_name,branch.branch_id,branch.branch_name,branch.doctor_id,branch.price_doctor,NOW() "
						+ "FROM branch "
						+ "INNER JOIN brand ON brand.brand_id = branch.brand_id "
						+ "WHERE branch.doctor_id = "+branchModel.getDoctor_id()+" AND branch.branch_id = '"+branchModel.getBranch_id()+"'";
				pStmt2 = conn.prepareStatement(transacSLQ);
				pStmt2.executeUpdate();
			}
			pStmt.close();
			pStmt2.close();
			conn.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	public void UpdateMgrBranch(List<BranchModel> branch,int doctor_id){
		try {
			conn = agent.getConnectMYSql();
			String selectSQL  = "SELECT * FROM branch WHERE doctor_id="+doctor_id;
			pStmt = conn.prepareStatement(selectSQL);
			List<String> rsList = new ArrayList<String>();
			rs=pStmt.executeQuery();
			while(rs.next()){
				rsList.add(rs.getString("branch_id"));
			}
			List<String> rsList2 = new ArrayList<String>();
			for (BranchModel branchModel : branch) {
				rsList2.add(branchModel.getBranch_id());
			}
				List<String> similar = new  ArrayList<String>(rsList);
				List<String> different = new ArrayList<String>();
	          different.addAll( rsList );
	          different.addAll( rsList2 );
	          similar.retainAll( rsList2 );
	          different.removeAll( similar );
	        //System.out.printf("One:%s%nTwo:%s%nSimilar:%s%nDifferent:%s%n", rsList, rsList2, similar, different);
	          for (String branchID : different) {
	        	  
	        	  String transacSLQ = "UPDATE branch_mgr_history a "
	        	  		+ "INNER JOIN "
	        	  		+ "(SELECT a.brand_id,b.brand_name,a.branch_id,a.branch_name,a.doctor_id,a.price_doctor,a.addr_id,a.tel_id "
	        	  		+ "FROM branch a INNER JOIN brand b ON b.brand_id = a.brand_id ) as b "
	        	  		+ "ON b.branch_id = a.branch_id_start "
	        	  		+ "SET a.brand_id_end = b.brand_id , "
	        	  		+ "a.brand_name_end = b.brand_name , "
	        	  		+ "a.branch_id_end = b.branch_id, "
	        	  		+ "a.branch_name_end = b.branch_name, "
	        	  		+ "a.doctor_id_end = b.doctor_id, "
	        	  		+ "a.price_doctor_end = b.price_doctor, "
	        	  		+ "a.end_date = NOW() "
	        	  		+ "WHERE a.branch_id_start = '"+branchID+"' AND  a.branch_id_end IS NULL ";
				  pStmt2 = conn.prepareStatement(transacSLQ);
				  pStmt2.executeUpdate();
				  
				  String sql = "UPDATE branch set doctor_id=0 WHERE branch_id ='"+branchID+"'";
	        	  pStmt = conn.prepareStatement(sql);
	        	  pStmt.executeUpdate();
	          }
	          for (String branchID :  rsList2){
	        	  String sql = "UPDATE branch set doctor_id="+doctor_id+" WHERE branch_id ='"+branchID+"'";
	        	  pStmt = conn.prepareStatement(sql);
	        	  pStmt.executeUpdate();
	        	  String transacSLQ = "INSERT INTO branch_mgr_history "
							+ "(brand_id_start,brand_name_start,branch_id_start,branch_name_start,doctor_id_start,price_doctor_start,start_date) "
							+ "SELECT branch.brand_id,brand.brand_name,branch.branch_id,branch.branch_name,branch.doctor_id,branch.price_doctor,NOW() "
							+ "FROM branch "
							+ "INNER JOIN brand ON brand.brand_id = branch.brand_id "
							+ "WHERE branch.doctor_id = "+doctor_id+" AND branch.branch_id = '"+branchID+"'";
					pStmt2 = conn.prepareStatement(transacSLQ);
					pStmt2.executeUpdate();
	          }
	      	if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!pStmt.isClosed())
				pStmt.close();
			if (!pStmt2.isClosed())
				pStmt2.close();
			if (!conn.isClosed())
				conn.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public int delDoctorTime(int doctor_id,String branch_id){
		int rt=0;
		String delSQL = "DELETE FROM doctor_workday WHERE doctor_id ="+doctor_id+" AND branch_id = '"+branch_id+"' AND checkin_status IS NULL AND DATEDIFF(NOW(),start_datetime)<=0";
		String selectSQL  = "DELETE FROM doctor_workday_month WHERE doctor_id = "+doctor_id+" AND branch_id = '"+branch_id+"'";
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			pStmt = conn.prepareStatement(selectSQL);
			Stmt.executeUpdate(delSQL);
		  	rt = pStmt.executeUpdate();
		  	Stmt.close();
		  	pStmt.close();
		  	conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;
	}
	
	public int addDoctorTime(DoctTimeModel timeModel){
		int rt=0;
		try {
			conn = agent.getConnectMYSql();
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat DateS = new SimpleDateFormat("dd");
			Date ConvertDate;
			int dayOfWeek;
			String lastDayOfMonth,loopMonth;
		String AddWorkDaySQL = "INSERT INTO doctor_workday (doctor_id,start_datetime,end_datetime,work_hour,branch_id)"
				+ "VALUES ";
		String selectSQL  = "INSERT INTO doctor_workday_month "
				+ "(doctor_id,branch_id,work_month,time_in_mon,time_out_mon"
				+ ",time_in_tue,time_out_tue,time_in_wed,time_out_wed,time_in_thu,time_out_thu"
				+ ",time_in_fri,time_out_fri,time_in_sat,time_out_sat,time_in_sun,time_out_sun)"
				+ "VALUES ";
		int chkFirst = 0;
		for(int i =0; i<timeModel.getWork_month().size();i++){
			if(!timeModel.getWork_month().get(i).equals("")){
				String work_month = timeModel.getWork_month().get(i);
				String[] part = work_month.split("/");
				loopMonth = String.valueOf(Integer.parseInt(part[1])-543)+"-"+part[0];
				work_month =String.valueOf(Integer.parseInt(part[1])-543)+"-"+part[0]+"-01";
			selectSQL+=(i>0)?",":"";
			selectSQL+="("
					+ timeModel.getDoctorID()
					+ ",'"+timeModel.getBranch_id()+"'"
					+ ",'"+work_month+"'"
					+ ",'"+timeModel.getTime_in_mon().get(i)+":00'"
					+ ",'"+timeModel.getTime_out_mon().get(i)+":00'"
					+ ",'"+timeModel.getTime_in_tue().get(i)+":00'"
					+ ",'"+timeModel.getTime_out_tue().get(i)+":00'"
					+ ",'"+timeModel.getTime_in_wed().get(i)+":00'"
					+ ",'"+timeModel.getTime_out_wed().get(i)+":00'"
					+ ",'"+timeModel.getTime_in_thu().get(i)+":00'"
					+ ",'"+timeModel.getTime_out_thu().get(i)+":00'"
					+ ",'"+timeModel.getTime_in_fri().get(i)+":00'"
					+ ",'"+timeModel.getTime_out_fri().get(i)+":00'"
					+ ",'"+timeModel.getTime_in_sat().get(i)+":00'"
					+ ",'"+timeModel.getTime_out_sat().get(i)+":00'"
					+ ",'"+timeModel.getTime_in_sun().get(i)+":00'"
					+ ",'"+timeModel.getTime_out_sun().get(i)+":00'"
					+ ")";
			 
			 ConvertDate = DateFormat.parse(work_month);
			 calendar.setTime(ConvertDate);
			 calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			 lastDayOfMonth = DateS.format(calendar.getTime());
			 
			 for(int d=1;d<=Integer.parseInt(lastDayOfMonth);d++){
				 ConvertDate = DateFormat.parse(loopMonth+"-"+d);
				 calendar.setTime(ConvertDate);
				 dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
				 
				 if(!timeModel.getTime_in_sun().get(i).equals("00:00")&&dayOfWeek==1){
					 AddWorkDaySQL+=(chkFirst>0)?",":"";
					 
					 AddWorkDaySQL+="("+timeModel.getDoctorID()+",'"+loopMonth+"-"+d+" "+timeModel.getTime_in_sun().get(i)+":00',"
					 		+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_out_sun().get(i)+":00',"
					 		+ "HOUR(TIMEDIFF('"+loopMonth+"-"+d+" "+timeModel.getTime_out_sun().get(i)+":00',"
					 				+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_in_sun().get(i)+":00')),"
					 						+ "'"+timeModel.getBranch_id()+"')";
					 chkFirst =1;
				 }
				 if(!timeModel.getTime_in_mon().get(i).equals("00:00")&&dayOfWeek==2){
					 AddWorkDaySQL+=(chkFirst>0)?",":"";
					 AddWorkDaySQL+="("+timeModel.getDoctorID()+",'"+loopMonth+"-"+d+" "+timeModel.getTime_in_mon().get(i)+":00',"
						 		+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_out_mon().get(i)+":00',"
						 		+ "HOUR(TIMEDIFF('"+loopMonth+"-"+d+" "+timeModel.getTime_out_mon().get(i)+":00',"
						 				+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_in_mon().get(i)+":00')),"
						 						+ "'"+timeModel.getBranch_id()+"')";
					 chkFirst =1;
				 }
				 if(!timeModel.getTime_in_tue().get(i).equals("00:00")&&dayOfWeek==3){
					 AddWorkDaySQL+=(chkFirst>0)?",":"";
					 AddWorkDaySQL+="("+timeModel.getDoctorID()+",'"+loopMonth+"-"+d+" "+timeModel.getTime_in_tue().get(i)+":00',"
						 		+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_out_tue().get(i)+":00',"
						 		+ "HOUR(TIMEDIFF('"+loopMonth+"-"+d+" "+timeModel.getTime_out_tue().get(i)+":00',"
						 				+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_in_tue().get(i)+":00')),"
						 						+ "'"+timeModel.getBranch_id()+"')";
					 chkFirst =1;
				 }
				 if(!timeModel.getTime_in_wed().get(i).equals("00:00")&&dayOfWeek==4){
					 AddWorkDaySQL+=(chkFirst>0)?",":"";
					 AddWorkDaySQL+="("+timeModel.getDoctorID()+",'"+loopMonth+"-"+d+" "+timeModel.getTime_in_wed().get(i)+":00',"
						 		+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_out_wed().get(i)+":00',"
						 		+ "HOUR(TIMEDIFF('"+loopMonth+"-"+d+" "+timeModel.getTime_out_wed().get(i)+":00',"
						 				+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_in_wed().get(i)+":00')),"
						 						+ "'"+timeModel.getBranch_id()+"')";
					 chkFirst =1;
				 }
				 if(!timeModel.getTime_in_thu().get(i).equals("00:00")&&dayOfWeek==5){
					 AddWorkDaySQL+=(chkFirst>0)?",":"";
					 AddWorkDaySQL+="("+timeModel.getDoctorID()+",'"+loopMonth+"-"+d+" "+timeModel.getTime_in_thu().get(i)+":00',"
						 		+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_out_thu().get(i)+":00',"
						 		+ "HOUR(TIMEDIFF('"+loopMonth+"-"+d+" "+timeModel.getTime_out_thu().get(i)+":00',"
						 				+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_in_thu().get(i)+":00')),"
						 						+ "'"+timeModel.getBranch_id()+"')";
					 chkFirst =1;
				 }
				 if(!timeModel.getTime_in_fri().get(i).equals("00:00")&&dayOfWeek==6){
					 AddWorkDaySQL+=(chkFirst>0)?",":"";
					 AddWorkDaySQL+="("+timeModel.getDoctorID()+",'"+loopMonth+"-"+d+" "+timeModel.getTime_in_fri().get(i)+":00',"
						 		+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_out_fri().get(i)+":00',"
						 		+ "HOUR(TIMEDIFF('"+loopMonth+"-"+d+" "+timeModel.getTime_out_fri().get(i)+":00',"
						 				+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_in_fri().get(i)+":00')),"
						 						+ "'"+timeModel.getBranch_id()+"')";
					 chkFirst =1;
				 }
				 if(!timeModel.getTime_in_sat().get(i).equals("00:00")&&dayOfWeek==7){
					 AddWorkDaySQL+=(chkFirst>0)?",":"";
					 AddWorkDaySQL+="("+timeModel.getDoctorID()+",'"+loopMonth+"-"+d+" "+timeModel.getTime_in_sat().get(i)+":00',"
						 		+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_out_sat().get(i)+":00',"
						 		+ "HOUR(TIMEDIFF('"+loopMonth+"-"+d+" "+timeModel.getTime_out_sat().get(i)+":00',"
						 				+ "'"+loopMonth+"-"+d+" "+timeModel.getTime_in_sat().get(i)+":00')),"
						 						+ "'"+timeModel.getBranch_id()+"')";
					 chkFirst =1;
				 }
			 }
			
			}
		}
	//System.out.println(AddWorkDaySQL);
		
		Stmt = conn.createStatement();
		rt = Stmt.executeUpdate(AddWorkDaySQL);
		Stmt.close();
		
		pStmt = conn.prepareStatement(selectSQL);
	  	pStmt.executeUpdate();
	  	pStmt.close();
	  	conn.close();
	  	check_duplicate_Time();
	  	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;
	}
	public int check_duplicate_Time(){
		//clear duplicate 
		int rt =0;
		String checkDupSQL= "SELECT a.workday_id "
				+ "FROM "
				+ "doctor_workday AS a "
				+ "INNER JOIN ( "
				+ "SELECT doctor_id, DATE(start_datetime)AS aa, branch_id "
				+ "FROM doctor_workday "
				+ "GROUP BY  "
				+ "doctor_id,aa,branch_id "
				+ "HAVING "
				+ "COUNT(start_datetime) > 1) dup ON a.doctor_id = dup.doctor_id "
				+ "AND a.branch_id = dup.branch_id "
				+ "WHERE "
				+ "checkin_status IS NULL "
				+ "AND DATE(a.start_datetime) = DATE(dup.aa) ";
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(checkDupSQL);
			
			
			 String dupSQL = "DELETE FROM doctor_workday "
				 		+ "WHERE workday_id IN(";
			 int chk= 0;
			while (rs.next()) {
				dupSQL +=(chk>0)?",":"";
				 
				 dupSQL +=rs.getString("workday_id");
				 
				 chk++;
				
			}
			dupSQL +=")";
			
			pStmt = conn.prepareStatement(dupSQL);
			rt = pStmt.executeUpdate();
			Stmt.close();
			rs.close();
			pStmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;
	}
	public void addBranchStandard(DoctorModel docModel){
		
		String SQL ="INSERT INTO branch_standard_rel_doctor (branch_id,doctor_id,price)"
				+ "VALUES ("
				+ "'"+docModel.getBranch_id()+"',"+docModel.getDoctorID()+","+docModel.getPrice()+")";
		
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void DeleteBranchStandard(DoctorModel docModel){
		
		String SQL ="DELETE FROM branch_standard_rel_doctor "
				+ "Where branch_id = '"+docModel.getBranchStandID()+"' and "
				+ "doctor_id ="+docModel.getDoctorID();
		
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void UpdateBranchStandard(DoctorModel docModel){
		
		String SQL ="UPDATE branch_standard_rel_doctor set "
				+ "price = "+docModel.getPrice()+" "
				+ "Where branch_id = '"+docModel.getBranchStandID()+"' and "
				+ "doctor_id ="+docModel.getDoctorID();
		
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	public boolean branchStandardCheck(DoctorModel doc ){
		
		String SQL = "SELECT 	branch_standard_rel_doctor.price,branch.branch_name,doctor.first_name_th, "
						+"doctor.last_name_th,pre_name.pre_name_th, branch_standard_rel_doctor.branch_id "
						+"FROM	branch "
						+ "INNER JOIN branch_standard_rel_doctor ON branch.branch_id = branch_standard_rel_doctor.branch_id "
						+ "INNER JOIN doctor ON doctor.doctor_id = branch_standard_rel_doctor.doctor_id "
						+ "INNER JOIN pre_name ON doctor.pre_name_id = pre_name.pre_name_id "
						+ "WHERE branch_standard_rel_doctor.doctor_id = "+doc.getDoctorID()+" and branch_standard_rel_doctor.branch_id = '"+doc.getBranch_id()+"'";
		boolean newAllergic = true;
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			ResultSet rs = Stmt.executeQuery(SQL);
			
			while(rs.next()){
				newAllergic = false;
			}
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			return newAllergic;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return newAllergic;
	}	
	public List<DoctorModel> getBranchStandard(int docId){
		
		String SQL = "SELECT 	branch_standard_rel_doctor.price,branch.branch_name,doctor.first_name_th, "
						+"doctor.last_name_th,pre_name.pre_name_th, branch_standard_rel_doctor.branch_id "
						+"FROM	branch "
						+ "INNER JOIN branch_standard_rel_doctor ON branch.branch_id = branch_standard_rel_doctor.branch_id "
						+ "INNER JOIN doctor ON doctor.doctor_id = branch_standard_rel_doctor.doctor_id "
						+ "INNER JOIN pre_name ON doctor.pre_name_id = pre_name.pre_name_id "
						+ "WHERE branch_standard_rel_doctor.doctor_id = "+docId;
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			ResultSet rs = Stmt.executeQuery(SQL);
			
			List<DoctorModel> doctorList = new ArrayList<DoctorModel>();
			while(rs.next()){
				DoctorModel docModel = new DoctorModel();
				docModel.setPrice(rs.getInt("price"));
				docModel.setBranchStandID(rs.getString("branch_id"));
				docModel.setBranchName(rs.getString("branch_name"));
				docModel.setFirst_name_th(rs.getString("first_name_th"));
				docModel.setLast_name_th(rs.getString("last_name_th"));
				docModel.setPre_name_th(rs.getString("pre_name_th"));
				doctorList.add(docModel);
			}
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			return doctorList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	public List<DoctorModel> getBranchMgr(int docId ){
		
		String SQL = "SELECT 	branch_mgr_rel_doctor.price,branch.branch_name,doctor.first_name_th, "
						+"doctor.last_name_th,pre_name.pre_name_th, branch_mgr_rel_doctor.branch_id "
						+"FROM	branch "
						+ "INNER JOIN branch_mgr_rel_doctor ON branch.branch_id = branch_mgr_rel_doctor.branch_id "
						+ "INNER JOIN doctor ON doctor.doctor_id = branch_mgr_rel_doctor.doctor_id "
						+ "INNER JOIN pre_name ON doctor.pre_name_id = pre_name.pre_name_id "
						+ "WHERE branch_mgr_rel_doctor.doctor_id = "+docId;
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			ResultSet rs = Stmt.executeQuery(SQL);
			
			List<DoctorModel> doctorList = new ArrayList<DoctorModel>();
			while(rs.next()){
				DoctorModel docModel = new DoctorModel();
				docModel.setPrice(rs.getInt("price"));
				docModel.setBranchStandID(rs.getString("branch_id"));
				docModel.setBranchName(rs.getString("branch_name"));
				docModel.setFirst_name_th(rs.getString("first_name_th"));
				docModel.setLast_name_th(rs.getString("last_name_th"));
				docModel.setPre_name_th(rs.getString("pre_name_th"));
				doctorList.add(docModel);
			}
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			return doctorList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	public void addBranchMgr(DoctorModel docModel){
		
		String SQL ="INSERT INTO branch_mgr_rel_doctor (branch_id,doctor_id,price)"
				+ "VALUES ("
				+ "'"+docModel.getBranch_id()+"',"+docModel.getDoctorID()+","+docModel.getPrice()+")";
		
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public int branchMgrCheckSize(int doc ){
		int i = 0;
		String SQL = "SELECT 	branch_mgr_rel_doctor.price,branch.branch_name,doctor.first_name_th, "
						+"doctor.last_name_th,pre_name.pre_name_th, branch_mgr_rel_doctor.branch_id "
						+"FROM	branch "
						+ "INNER JOIN branch_mgr_rel_doctor ON branch.branch_id = branch_mgr_rel_doctor.branch_id "
						+ "INNER JOIN doctor ON doctor.doctor_id = branch_mgr_rel_doctor.doctor_id "
						+ "INNER JOIN pre_name ON doctor.pre_name_id = pre_name.pre_name_id "
						+ "WHERE branch_mgr_rel_doctor.doctor_id = "+doc;
		System.out.println("DoctorData.branchMgrCheckSize : " + SQL);
		try {
			agent.connectMySQL();
			agent.exeQuery(SQL);
			 i = agent.size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			agent.disconnectMySQL();
		}
		
		
		return i;
	}
	public boolean branchMgrCheck(DoctorModel doc ){
		
		String SQL = "SELECT 	branch_mgr_rel_doctor.price,branch.branch_name,doctor.first_name_th, "
						+"doctor.last_name_th,pre_name.pre_name_th, branch_mgr_rel_doctor.branch_id "
						+"FROM	branch "
						+ "INNER JOIN branch_mgr_rel_doctor ON branch.branch_id = branch_mgr_rel_doctor.branch_id "
						+ "INNER JOIN doctor ON doctor.doctor_id = branch_mgr_rel_doctor.doctor_id "
						+ "INNER JOIN pre_name ON doctor.pre_name_id = pre_name.pre_name_id "
						+ "WHERE branch_mgr_rel_doctor.doctor_id = "+doc.getDoctorID()+" and branch_mgr_rel_doctor.branch_id = '"+doc.getBranch_id()+"'";
		boolean newAllergic = true;
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			ResultSet res = Stmt.executeQuery(SQL);
			
			while(res.next()){
				newAllergic = false;
			}
			if (!res.isClosed())
				res.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			return newAllergic;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return newAllergic;
	}
	public void DeleteBranchMgr(DoctorModel docModel){
		
		String SQL ="DELETE FROM branch_mgr_rel_doctor "
				+ "Where branch_id = '"+docModel.getBranchStandID()+"' and "
				+ "doctor_id ="+docModel.getDoctorID();
		
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void UpadteBranchMgr(DoctorModel docModel){
		
		String SQL ="UPDATE branch_mgr_rel_doctor set "
				+ "price = "+docModel.getPrice()+" "
				+ "Where branch_id = '"+docModel.getBranchStandID()+"' and "
				+ "doctor_id ="+docModel.getDoctorID();
		
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}		
	public Person editDoctor(String doc_id){
		Person returnempmodel = new Person();
		
		String sql = "SELECT "
				+ "profile_pic, "
				+ "title "
				+ "FROM "
				+ "doctor "
				+ "where doctor_id = '"+doc_id+"' ";
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				returnempmodel.setProfile_pic(rs.getString("profile_pic"));
				returnempmodel.setChecktitle(rs.getString("title"));
			}
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnempmodel;
	}
	public List<DoctorModel> Get_DoctorStatusList() throws IOException, Exception {
		String sqlQuery = "SELECT doctor.doctor_id,doctor.pre_name_id,pre_name.pre_name_th,pre_name.pre_name_en,doctor.first_name_th,doctor.last_name_th,doctor.first_name_en,"
				+ "doctor.last_name_en,doctor.nickname,doctor.birth_date,doctor.TMC_license,doctor.title,doctor.identification,doctor.identification_type,"
				+ "doctor.profile_pic,doctor.remark,doctor.hired_date,doctor.tel_id,doctor.doc_branch_id,doctor.addr_id,doctor.work_status,doctor.bookbank_id,doctor.work_history_id,"
				+ "doctor.doc_education_id,doctor.emp_id,doctor.contract_id "
				+ "FROM doctor "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = doctor.pre_name_id "
				+ "ORDER BY doctor.work_status DESC";
		
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<DoctorModel> ResultList = new ArrayList<DoctorModel>();
		while (rs.next()) {
			DoctorModel docModel = new DoctorModel();
			docModel.setWork_status(rs.getString("work_status"));
			docModel.setDoctorID(rs.getInt("doctor_id"));
			docModel.setPre_name_id(rs.getString("pre_name_id"));
			docModel.setPre_name(rs.getString("pre_name_th"));
			docModel.setPre_name_en(rs.getString("pre_name_en"));
			docModel.setFirstname_th(rs.getString("first_name_th"));
			docModel.setLastname_th(rs.getString("last_name_th"));
			docModel.setFirstname_en(rs.getString("first_name_en"));
			docModel.setLastname_en(rs.getString("last_name_en"));
			docModel.setBranchID(rs.getInt("doc_branch_id"));
			docModel.setNickname(rs.getString("nickname"));
			docModel.setBirth_date(rs.getString("birth_date"));
			docModel.setTMCLicense(rs.getString("TMC_license"));
			docModel.setTitle(rs.getString("title"));
			docModel.setIdentification(rs.getString("identification"));
			docModel.setIdentification_type(rs.getString("identification_type"));
			docModel.setTel_id(rs.getInt("tel_id"));
			docModel.setHireDate(rs.getString("hired_date"));
			docModel.setRemark(rs.getString("remark"));
			docModel.setAddr_id(rs.getInt("addr_id"));
			docModel.setBookBankId(rs.getInt("bookbank_id"));
			docModel.setWork_history_id(rs.getInt("work_history_id"));
			docModel.setEdu_id(rs.getInt("doc_education_id"));
			docModel.setEmp_id(rs.getString("emp_id"));
			docModel.setContract_id(rs.getString("contract_id"));
			
			ResultList.add(docModel);
		}
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return ResultList;
	}
	public DoctorModel Get_DoctorDetailStatus(int doctor_id) throws IOException, Exception {
		String sqlQuery = "SELECT doctor.doctor_id,doctor.pre_name_id,pre_name.pre_name_th,pre_name.pre_name_en,doctor.first_name_th,doctor.last_name_th,doctor.first_name_en,"
				+ "doctor.last_name_en,doctor.nickname,doctor.birth_date,doctor.TMC_license,doctor.title,doctor.identification,doctor.identification_type,"
				+ "doctor.profile_pic,doctor.remark,doctor.hired_date,doctor.tel_id,doctor.doc_branch_id,doctor.addr_id,doctor.work_status,doctor.bookbank_id,doctor.work_history_id,"
				+ "doctor.doc_education_id,doctor.emp_id,doctor.contract_id, doctor.line_id, doctor.email "
				+ "FROM doctor "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = doctor.pre_name_id "
				+ "WHERE doctor_id = "+ doctor_id+" ";
 
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		DoctorModel docModel = new DoctorModel();
		while (rs.next()) {
			docModel.setWork_status(rs.getString("work_status"));
			docModel.setDoctorID(rs.getInt("doctor_id"));
			docModel.setPre_name_id(rs.getString("pre_name_id"));
			docModel.setPre_name(rs.getString("pre_name_th"));
			docModel.setPre_name_en(rs.getString("pre_name_en"));
			docModel.setFirstname_th(rs.getString("first_name_th"));
			docModel.setLastname_th(rs.getString("last_name_th"));
			docModel.setFirstname_en(rs.getString("first_name_en"));
			docModel.setLastname_en(rs.getString("last_name_en"));
			docModel.setBranchID(rs.getInt("doc_branch_id"));
			docModel.setNickname(rs.getString("nickname"));
			docModel.setBirth_date(rs.getString("birth_date"));
			docModel.setTMCLicense(rs.getString("TMC_license"));
			docModel.setTitle(rs.getString("title"));
			docModel.setIdentification(rs.getString("identification"));
			docModel.setIdentification_type(rs.getString("identification_type"));
			docModel.setTel_id(rs.getInt("tel_id"));
			docModel.setHireDate(rs.getString("hired_date"));
			docModel.setProfile_pic(rs.getString("profile_pic"));
			docModel.setRemark(rs.getString("remark"));
			docModel.setAddr_id(rs.getInt("addr_id"));
			docModel.setBookBankId(rs.getInt("bookbank_id"));
			docModel.setWork_history_id(rs.getInt("work_history_id"));
			docModel.setEdu_id(rs.getInt("doc_education_id"));
			docModel.setEmp_id(rs.getString("emp_id"));
			docModel.setContract_id(rs.getString("contract_id"));
			docModel.setLineId(rs.getString("line_id"));
			docModel.setEmail(rs.getString("email"));
		}
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return docModel;
	}
	public List<DoctorModel> Get_DoctorSearchList(String work) throws IOException, Exception {
		String sqlQuery = "SELECT doctor.doctor_id,doctor.pre_name_id,pre_name.pre_name_th,pre_name.pre_name_en,doctor.first_name_th,doctor.last_name_th,doctor.first_name_en,"
				+ "doctor.last_name_en,doctor.nickname,doctor.birth_date,doctor.TMC_license,doctor.title,doctor.identification,doctor.identification_type,"
				+ "doctor.profile_pic,doctor.remark,doctor.hired_date,doctor.tel_id,doctor.doc_branch_id,doctor.addr_id,doctor.work_status,doctor.bookbank_id,doctor.work_history_id,"
				+ "doctor.doc_education_id,doctor.emp_id,doctor.contract_id "
				+ "FROM doctor "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = doctor.pre_name_id "
				+ "Where doctor.work_status = '"+work+"' "
				+ "ORDER BY doctor.work_status DESC";
		
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<DoctorModel> ResultList = new ArrayList<DoctorModel>();
		while (rs.next()) {
			DoctorModel docModel = new DoctorModel();
			docModel.setWork_status(rs.getString("work_status"));
			docModel.setDoctorID(rs.getInt("doctor_id"));
			docModel.setPre_name_id(rs.getString("pre_name_id"));
			docModel.setPre_name(rs.getString("pre_name_th"));
			docModel.setPre_name_en(rs.getString("pre_name_en"));
			docModel.setFirstname_th(rs.getString("first_name_th"));
			docModel.setLastname_th(rs.getString("last_name_th"));
			docModel.setFirstname_en(rs.getString("first_name_en"));
			docModel.setLastname_en(rs.getString("last_name_en"));
			docModel.setBranchID(rs.getInt("doc_branch_id"));
			docModel.setNickname(rs.getString("nickname"));
			docModel.setBirth_date(rs.getString("birth_date"));
			docModel.setTMCLicense(rs.getString("TMC_license"));
			docModel.setTitle(rs.getString("title"));
			docModel.setIdentification(rs.getString("identification"));
			docModel.setIdentification_type(rs.getString("identification_type"));
			docModel.setTel_id(rs.getInt("tel_id"));
			docModel.setHireDate(rs.getString("hired_date"));
			docModel.setRemark(rs.getString("remark"));
			docModel.setAddr_id(rs.getInt("addr_id"));
			docModel.setBookBankId(rs.getInt("bookbank_id"));
			docModel.setWork_history_id(rs.getInt("work_history_id"));
			docModel.setEdu_id(rs.getInt("doc_education_id"));
			docModel.setEmp_id(rs.getString("emp_id"));
			docModel.setContract_id(rs.getString("contract_id"));
			
			ResultList.add(docModel);
		}
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return ResultList;
	}
	public List<DoctorModel> Get_DoctorSearchBranchList(String work, String branch, String branchstand) throws IOException, Exception {
		String sqlQuery = "SELECT doctor.doctor_id,doctor.pre_name_id,pre_name.pre_name_th,pre_name.pre_name_en,doctor.first_name_th,doctor.last_name_th,doctor.first_name_en,"
				+ "doctor.last_name_en,doctor.nickname,doctor.birth_date,doctor.TMC_license,doctor.title,doctor.identification,doctor.identification_type,"
				+ "doctor.profile_pic,doctor.remark,doctor.hired_date,doctor.tel_id,doctor.doc_branch_id,doctor.addr_id,doctor.work_status,doctor.bookbank_id,doctor.work_history_id,"
				+ "doctor.doc_education_id,doctor.emp_id,doctor.contract_id "
				+ "FROM doctor "
				+ "INNER JOIN branch_standard_rel_doctor ON doctor.doctor_id = branch_standard_rel_doctor.doctor_id "
				+ "INNER JOIN branch_mgr_rel_doctor ON doctor.doctor_id = branch_mgr_rel_doctor.doctor_id "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = doctor.pre_name_id "
				+ "Where doctor.work_status = '"+work+"' AND  ";
				if(branch.isEmpty()){
					sqlQuery+= "branch_standard_rel_doctor.branch_id = '"+branchstand+"' ";
				}else if(branchstand.isEmpty()){
					sqlQuery+= "branch_mgr_rel_doctor.branch_id = '"+branch+"' ";
				}else{
					sqlQuery+= "branch_mgr_rel_doctor.branch_id = '"+branch+"' AND branch_standard_rel_doctor.branch_id= '"+branchstand+"' ";
				}
				sqlQuery+="GROUP BY doctor.doctor_id ";					

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<DoctorModel> ResultList = new ArrayList<DoctorModel>();
		while (rs.next()) {
			DoctorModel docModel = new DoctorModel();
			docModel.setWork_status(rs.getString("work_status"));
			docModel.setDoctorID(rs.getInt("doctor_id"));
			docModel.setPre_name_id(rs.getString("pre_name_id"));
			docModel.setPre_name(rs.getString("pre_name_th"));
			docModel.setPre_name_en(rs.getString("pre_name_en"));
			docModel.setFirstname_th(rs.getString("first_name_th"));
			docModel.setLastname_th(rs.getString("last_name_th"));
			docModel.setFirstname_en(rs.getString("first_name_en"));
			docModel.setLastname_en(rs.getString("last_name_en"));
			docModel.setBranchID(rs.getInt("doc_branch_id"));
			docModel.setNickname(rs.getString("nickname"));
			docModel.setBirth_date(rs.getString("birth_date"));
			docModel.setTMCLicense(rs.getString("TMC_license"));
			docModel.setTitle(rs.getString("title"));
			docModel.setIdentification(rs.getString("identification"));
			docModel.setIdentification_type(rs.getString("identification_type"));
			docModel.setTel_id(rs.getInt("tel_id"));
			docModel.setHireDate(rs.getString("hired_date"));
			docModel.setRemark(rs.getString("remark"));
			docModel.setAddr_id(rs.getInt("addr_id"));
			docModel.setBookBankId(rs.getInt("bookbank_id"));
			docModel.setWork_history_id(rs.getInt("work_history_id"));
			docModel.setEdu_id(rs.getInt("doc_education_id"));
			docModel.setEmp_id(rs.getString("emp_id"));
			docModel.setContract_id(rs.getString("contract_id"));
			
			ResultList.add(docModel);
		}
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return ResultList;
	}
	
	public List<DoctorModel> getScopeDentist(){

		String SQL = "SELECT position_id, position_name_th, position_name_en, position_name_short "						
						+"FROM doctor_position ";

		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(SQL);
			List<DoctorModel> scopelist = new ArrayList<DoctorModel>();
			while(rs.next()){
				DoctorModel scopeModel = new DoctorModel();
				scopeModel.setPosition_id(rs.getString("position_id"));
				scopeModel.setPosition_name_th(rs.getString("position_name_th"));
				scopeModel.setPosition_name_en(rs.getString("position_name_en"));
				scopeModel.setPosition_name_short(rs.getString("position_name_short"));
				scopelist.add(scopeModel);
				
			}
			
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			return scopelist;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}	
	public void addScopeDentist(DoctorModel scopeModel){
		
		String SQL ="INSERT INTO doctor_position (position_name_th) "
					+"VALUES ('"+scopeModel.getPosition_name_th()+"') ";
	
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void DelectScopeDentist(DoctorModel scopeModel){
		
		String SQL ="DELETE FROM doctor_position "
				+ "Where position_id = '"+scopeModel.getPosition_id()+"'";
		
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public List<DoctorModel> getPositionTreatmentList(String posi_id){

		String SQL = "SELECT treatment_master.code, treatment_master.id, "
						+ "treatment_master.nameth,IFNULL(doctor_position_treatment.treatment_id,'nu') AS isCHECK,doctor_position_treatment.doc_position_id "						
						+"FROM treatment_master "
						+ "LEFT JOIN doctor_position_treatment ON  (treatment_master.id = doctor_position_treatment.treatment_id 	and doc_position_id = '"+posi_id+"' ) ";
						

		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(SQL);
			List<DoctorModel> scopelist = new ArrayList<DoctorModel>();
			while(rs.next()){
				DoctorModel scopeModel = new DoctorModel();
				scopeModel.setTreatmentID(rs.getString("treatment_master.id"));
				scopeModel.setPosition_treatment_id(rs.getString("doc_position_id"));
				scopeModel.setPositontreatmentCode(rs.getString("treatment_master.code"));
				scopeModel.setTreatment_nameth(rs.getString("nameth"));
				scopeModel.setIsCheck(rs.getString("isCHECK"));
				scopelist.add(scopeModel);
				
			}
			
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			return scopelist;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	public List<DoctorModel> getTreatmentList(){

		String SQL = "SELECT code, "
						+ "nameth "						
						+"FROM treatment_master ";

		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(SQL);
			List<DoctorModel> scopelist = new ArrayList<DoctorModel>();
			while(rs.next()){
				DoctorModel scopeModel = new DoctorModel();
				scopeModel.setTreatment_Code(rs.getString("code"));
				scopeModel.setTreatment_nameth(rs.getString("nameth"));
				scopelist.add(scopeModel);
				
			}
			
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			return scopelist;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	public Map<String,String> GetDentistTreatment() throws IOException, Exception {

		String SQL = "SELECT id, nameth "
				+ "FROM treatment_master ";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		ResultSet rs = Stmt.executeQuery(SQL);

		Map <String,String>ResultList = new HashMap<String,String>();
		
		while (rs.next()) {
			// vender_id,vender_name,create_by,create_datetime,update_by,update_datetime
			ResultList.put(rs.getString("id"), rs.getString("nameth"));	
		}

		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return ResultList;
	}
	public Map<String,String> GetSocpeTreatment() throws IOException, Exception {

		String SQL = "SELECT position_id, position_name_th "
				+ "FROM doctor_position ";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		ResultSet rs = Stmt.executeQuery(SQL);

		Map <String,String>ResultList = new HashMap<String,String>();
		
		while (rs.next()) {
			// vender_id,vender_name,create_by,create_datetime,update_by,update_datetime
			ResultList.put(rs.getString("position_id"), rs.getString("position_name_th"));	
		}

		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return ResultList;
	}	
	public void insertTreatmentDentist(DoctorModel scopeModel,String treatcode){
		
		String [] treatment_coded = treatcode.split(",");
		String SQL = "INSERT INTO doctor_position_treatment  (doc_position_id,treatment_id) "
				+"VALUES ";
				int i = 0;
				for(String treat_code : treatment_coded){
					
					if(i>0){
						SQL+=",";
					}
					SQL +="('"+scopeModel.getPosition_id()+"','"+treat_code+"')";
					i++;
				}
		
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void DeleteTreatmentDentist(DoctorModel scopeModel){
		
		
		 String SQL = "DELETE FROM doctor_position_treatment "
			        + "WHERE doc_position_id = '"+scopeModel.getPosition_id()+"' ";
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void insertDoctorTreatment(DoctorModel scopeModel,int doc_id){
		

		String SQL = "INSERT INTO doctor_treatment (doctor_id,treatment_id,can_change_from_scope) "
				+"(SELECT '"+doc_id+"',treatment_id,'t' FROM doctor_position_treatment WHERE doc_position_id = '"+scopeModel.getTitle()+"') ";
				
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public boolean DoctorTreatmentMoreCheck(DoctorModel doc ){
		
		String SQL = "SELECT 	doctor_id,treatment_id,can_change_from_scope "
						+"FROM	doctor_treatment "
						+ "WHERE doctor_id = "+doc.getDoctorID()+" and treatment_id = '"+doc.getTreatment_Code()+"'";
		boolean newAllergic = true;
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			ResultSet rs = Stmt.executeQuery(SQL);
			
			while(rs.next()){
				newAllergic = false;
			}
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			return newAllergic;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return newAllergic;
	}	
	public void insertDoctorTreatmentMore(DoctorModel docModel){
		

		String SQL = "INSERT INTO doctor_treatment (doctor_id,treatment_id,can_change_from_scope) "
				+ "values ('"+docModel.getDoctorID()+"','"+docModel.getTreatment_Code()+"','"+docModel.getCan_change()+"')";
					
				
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	public void updateDoctorTreatmentMore(DoctorModel docModel){
		

		String SQL = "UPDATE doctor_treatment SET "
				+ "can_change_from_scope ='"+docModel.getCan_change()+"' "
				+ "WHERE  doctor_id = '"+docModel.getDoctorID()+"' AND treatment_id = '"+docModel.getTreatment_Code()+"' ";
					
				
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void DeleteDoctorTreatmentMore(DoctorModel docModel){
		

		String SQL = "DELETE FROM doctor_treatment "
				+ "WHERE doctor_id = '"+docModel.getDoctorID()+"' AND treatment_id='"+docModel.getTreatment_Code()+"' ";
	
					
				
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void DeleteDoctorTreatmentWithUpdateDoctorScope(int docid){
		

		String SQL = "DELETE FROM doctor_treatment "
				+ "WHERE doctor_id = '"+docid+"' AND can_change_from_scope='t' ";
	
					
				
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void insertDoctorTreatmentWithUpdateDoctorScope(String scopetitle,int doc_id){
		

		String SQL = "INSERT INTO doctor_treatment (doctor_id,treatment_id,can_change_from_scope) "
				+ "select doctor.doctor_id, doc_pos.treatment_id,'t' "
				+ "from doctor_position_treatment doc_pos "
				+ "INNER JOIN doctor on (doctor.title = doc_pos.doc_position_id) "
				+ "LEFT JOIN doctor_treatment doc_treat on (doctor.doctor_id = doc_treat.doctor_id  and doc_treat.treatment_id = doc_pos.treatment_id) "
				+ "where doc_pos.doc_position_id = '"+scopetitle+"' and (doc_treat.can_change_from_scope is null) AND doctor.doctor_id = '"+doc_id+"' ";
				
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	public void DeleteDoctorTreatmentUpdateChange(DoctorModel scopeModel,String treatcode){
		
		String [] treatment_coded = treatcode.split(",");
		String SQL = "DELETE doctor_treatment.* FROM doctor_treatment "
				+ "INNER JOIN doctor ON doctor.doctor_id = doctor_treatment.doctor_id "
				+ "WHERE doctor.title = '"+scopeModel.getPosition_id()+"' AND "
				+ "treatment_id NOT IN ("; 
				int i = 0;
				for(String treat_code : treatment_coded){
					
					if(i>0){
						SQL+=",";
					}
					SQL +="'"+treat_code+"'";
					i++;
				}
				SQL +=") AND can_change_from_scope = 't' ";
				
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void UpdateDoctorTreatmentScopeUpdateChange(DoctorModel scopeModel){
		
		String SQL = "INSERT INTO doctor_treatment (doctor_id,treatment_id,can_change_from_scope) "
				+ "select doctor.doctor_id, doc_pos.treatment_id, 't' "
				+ "from doctor_position_treatment doc_pos "
				+ "INNER JOIN doctor on (doctor.title = doc_pos.doc_position_id) "
				+ "LEFT JOIN doctor_treatment doc_treat on (doctor.doctor_id = doc_treat.doctor_id and doc_treat.treatment_id = doc_pos.treatment_id) "
				+ "where doc_pos.doc_position_id = '"+scopeModel.getPosition_id()+"' and (doc_treat.can_change_from_scope is null) "; 

				
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}	
	public List<DoctorModel> getdoctorTreatmentList(DoctorModel docModel){

		String SQL = "SELECT doctor_treatment.doctor_id,treatment_master.nameth,doctor_treatment.can_change_from_scope, doctor_treatment.treatment_id "					
						+"FROM doctor_treatment "
						+ "INNER JOIN treatment_master ON doctor_treatment.treatment_id = treatment_master.id "
						+ "WHERE doctor_treatment.doctor_id = '"+docModel.getDoctorID()+"'";

		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(SQL);
			List<DoctorModel> scopelist = new ArrayList<DoctorModel>();
			while(rs.next()){
				DoctorModel scopeModel = new DoctorModel();
				scopeModel.setDoctorID(rs.getInt("doctor_id"));
				scopeModel.setTreatment_Code(rs.getString("treatment_id"));
				scopeModel.setTreatment_nameth(rs.getString("nameth"));
				scopeModel.setCan_change(rs.getString("can_change_from_scope"));
				scopelist.add(scopeModel);
				
			}
			
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			return scopelist;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}






