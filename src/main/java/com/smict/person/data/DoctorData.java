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


import com.smict.all.model.DoctTimeModel;
import com.smict.person.model.BranchModel;
import com.smict.person.model.DoctorModel;

import ldc.util.DBConnect;
import ldc.util.Validate;

public class DoctorData {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	ResultSet rs = null;
	PreparedStatement pStmt = null,pStmt2=null;
	
	
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
				+ "doctor.doc_education_id,doctor.emp_id,doctor.contract_id "
				+ "FROM doctor "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = doctor.pre_name_id "
				+ "WHERE doctor_id = "+ doctor_id+" AND work_status=1";
 
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		DoctorModel docModel = new DoctorModel();
		while (rs.next()) {
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
				+ "identification , identification_type , tel_id  , profile_pic , remark , doc_branch_id , hired_date , work_status  ,addr_id,contract_id,emp_id,work_history_id,doc_education_id,bookbank_id) "
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
				+doctor.getBookBankId()+ ")";
		
			
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
					+ ",TMC_license='"+doctor.getTMCLicense()+"'"
					+ ",title='"+doctor.getTitle()+"'"
					+ ",identification='"+doctor.getIdentification()+"'"
					+ ",identification_type='"+doctor.getIdentification_type()+"'"
					+ ",profile_pic='"+doctor.getProfile_pic()+"'"
					+ ",remark='"+doctor.getRemark()+"'"
					+ ",doc_branch_id='"+doctor.getBranchID()+"'"
					+ ",tel_id="+doctor.getTel_id()
					+ ",addr_id="+doctor.getAddr_id()
					+ ",bookbank_id="+doctor.getBookBankId()
					+ " "
					+ "WHERE doctor_id="+doctor.getDoctorID();
			Stmt.executeUpdate(sql);
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
}






