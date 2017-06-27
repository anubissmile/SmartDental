package com.smict.treatment.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.core.IsNull;

import com.smict.all.model.ServicePatientModel;
import com.smict.all.model.TreatmentMasterModel;
import com.smict.person.model.BrandModel;
import com.smict.person.model.DoctorModel;
import com.smict.person.model.Person;
import com.smict.person.model.TelephoneModel;
import com.smict.product.model.ProductModel;
import com.smict.schedule.model.ScheduleModel;
import com.smict.treatment.model.TreatmentModel;

import ldc.util.Auth;
import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;
import sun.invoke.empty.Empty;

public class TreatmentData
{
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	/**
	 * Chunking tooth type or get by id.
	 * @author anubissmile
	 * @param int toothTypeID | id of tooth type.
	 * @return List<TreatmentModel> treatmentList | List of tooth type model.
	 */
	public List<TreatmentModel> getToothType(int toothTypeID){
		List<TreatmentModel> treatmentList = new ArrayList<TreatmentModel>();
		String SQL = "SELECT * FROM `tooth_type` ";
				
		if(toothTypeID > 0){
			SQL.concat(" WHERE id = '" + toothTypeID + "' ");
		}
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		rs = agent.getRs();
		try {
			if(agent.size()>0){
				while(rs.next()){
					TreatmentModel tModel = new TreatmentModel();
					tModel.setToothTypeID(rs.getInt("id"));
					tModel.setToothTypeNameTH(rs.getString("name_th"));
					tModel.setToothTypeNameEN(rs.getString("name_en"));
					treatmentList.add(tModel);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		return treatmentList;
	}
	
	
	/**
	 * Get tooth picture type
	 * @author anubissmile
	 * @param String toothPicID | Tooth picture ID 
	 * @return List<TreatmentModel> | 
	 */
	public List<TreatmentModel> getToothPicture(String toothPicID){
		List<TreatmentModel> treatList = new ArrayList<TreatmentModel>();
		String SQL = "SELECT * FROM `tooth_pic` ";
		
		if(new Validate().Check_String_notnull_notempty(toothPicID)){
			SQL.concat(" WHERE tooth_pic_code = '" + toothPicID + "' ");
		}
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		rs = agent.getRs();
		try {
			if(agent.size()>0){
				while(rs.next()){
					TreatmentModel tModel = new TreatmentModel();
					tModel.setToothPicCode(rs.getString("tooth_pic_code"));
					tModel.setToothPicName(rs.getString("tooth_pic_name"));
					treatList.add(tModel);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { 
			agent.disconnectMySQL();
		}
		return treatList;
	}
	
	/**
	 * Get treatment category by chunkgin all or search by id.
	 * @author anubissmile
	 * @param int treatmentGroupID | treatment group id.
	 * @return List<TreatmentModel>
	 */
	public List<TreatmentModel> getTreatmentCategory(int treatmentGroupID){
		List<TreatmentModel> treatModel = new ArrayList<TreatmentModel>();
		String SQL = "SELECT treatment_category.id AS category_id, "
				+ "treatment_category.`name` AS category_name, "
				+ "treatment_category.`code` AS category_code, "
				+ "treatment_category.group_id AS group_id, "
				+ "treatment_group.`code` AS group_code, "
				+ "treatment_group.`name` AS group_name "
				+ "FROM treatment_group "
				+ "INNER JOIN treatment_category ON treatment_category.group_id = treatment_group.id ";
		
		if(String.valueOf(treatmentGroupID) != null && treatmentGroupID > 0){
			SQL += " WHERE treatment_group.id = '" + treatmentGroupID + "' ";
		}
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		rs = agent.getRs();
		try {
			if(agent.size()>0){
				while(rs.next()){
					TreatmentModel tModel = new TreatmentModel();
					tModel.setTreatmentCategoryID(rs.getInt("category_id"));
					tModel.setTreatmentCategoryName(rs.getString("category_name"));
					tModel.setTreatmentCategoryCode(rs.getString("category_code"));
					tModel.setTreatmentGroupID(rs.getInt("group_id"));
					tModel.setTreatmentGroupCode(rs.getString("group_code"));
					tModel.setTreatmentGroupName(rs.getString("group_name"));
					treatModel.add(tModel);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		return treatModel;
	}
	
	/**
	 * Get treatment group by chunking all or search by id.
	 * @author anubissmile
	 * @param int treatmentGroupID | treatment group id.
	 * @return List<TreatmentModel> | List of treatment group.
	 */
	public List<TreatmentModel> getTreatmentGroup(int treatmentGroupID){
		List<TreatmentModel> treatmentList = new ArrayList<TreatmentModel>();
		String SQL = "SELECT treatment_group.id, "
				+ "treatment_group.`code`, "
				+ "treatment_group.`name` "
				+ "FROM treatment_group ";
		
		/**
		 * Conditions.
		 */
		if(String.valueOf(treatmentGroupID) != null && treatmentGroupID > 0){
			SQL.concat(" WHERE treatment_group.id = '" + treatmentGroupID + "' ");
		}
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		rs = agent.getRs();
		try {
			if(agent.size() > 0){
				while(rs.next()){
					TreatmentModel treatModel = new TreatmentModel();
					treatModel.setTreatmentGroupID(rs.getInt("id"));
					treatModel.setTreatmentGroupName(rs.getString("name"));
					treatModel.setTreatmentGroupCode(rs.getString("code"));
					treatmentList.add(treatModel);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		return treatmentList;
	}
	
	/**
	 * Put patient into the available treatment room.
	 * @author anubissmile
	 * @param int queueId | Queue id.
	 * @param int workDaId | Work day id.
	 * @return int rec | Count of the record that get affected.
	 */
	public int putPatientToRoom(int queueId, int workDayId){
		int rec = 0;
		rec = changeTreatmentQueueStatus(queueId, workDayId, 2);
		return rec;
	}
	
	/**
	 * Changing treatment queue status.
	 * @author anubissmile
	 * @param int queueId | Queue id.
	 * @param int workDayId | Work day id. 
	 * @param int status | status.
	 * @return int rec | Count of the record that get affected.
	 */
	public int changeTreatmentQueueStatus(int queueId, int workDayId, int status){
		int rec = 0;
		String SQL = "UPDATE `patient_queue` "
				+ "SET `pq_workday_id`='" + workDayId + "', "
				+ "`pq_status`='" + status + "', "
				+ "`updated_at`= NOW() "
				+ "WHERE (`pq_id`='" + queueId + "')";
		
		try {
			agent.connectMySQL();
			rec = agent.exeUpdate(SQL);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		
		return rec;
	}
	public int changeTreatmentQueueStatusDone(String hn){
		int rec = 0;
		String SQL = "UPDATE `patient_queue` "
				+ "SET  "
				+ "`pq_status`='5', "
				+ "`updated_at`= NOW() "
				+ "WHERE (`pq_hn`='" + hn + "') AND pq_status = '4' ";
		
		try {
			agent.connectMySQL();
			rec = agent.exeUpdate(SQL);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		
		return rec;
	}	
	/**
	 * Removing patient from treatment queue list.
	 * @param int | queueId
	 * @return int | Count of record that get affected.
	 */
	public int removeQueuePatientById(int queueId){
		String SQL = "UPDATE `patient_queue` SET `pq_status`='3', `updated_at` = NOW() WHERE (`pq_id`='" + queueId + "')";
		int rec = 0;
		try{
			agent.connectMySQL();
			rec = agent.exeUpdate(SQL);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(rec > 0){
				agent.commit();
			}else{
				agent.rollback();
			}
			agent.disconnectMySQL();
		}
		
		return rec;
	}
	
	/**
	 * Fetch treatment queue list.
	 * @author anubissmile
	 * @return List<TreatmentModel>
	 */
	public List<TreatmentModel> fetchTreatmentQueue(){
		String SQL = "SELECT patient_queue.pq_id, pre_name.pre_name_th, patient.first_name_th, "
				+ "patient.last_name_th, branch.branch_name, "
				+ "branch.branch_id, branch.branch_code, "
				+ "patient.hn, treatment_queue_status.tqs_id, "
				+ "treatment_queue_status.tqs_description, "
				+ "patient_queue.pq_workday_id, "
				+ "patient_queue.created_at, patient_queue.updated_at "
				+ "FROM patient_queue "
				+ "LEFT JOIN treatment_queue_status ON patient_queue.pq_status = treatment_queue_status.tqs_id "
				+ "LEFT JOIN patient ON patient_queue.pq_hn = patient.hn "
				+ "LEFT JOIN pre_name ON patient.pre_name_id = pre_name.pre_name_id "
				+ "LEFT JOIN branch ON patient_queue.pq_branch = branch.branch_code ";
		
		List<TreatmentModel> treatList = new ArrayList<TreatmentModel>();
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		try {
			if(agent.size() > 0){
				while(agent.getRs().next()){
					rs = agent.getRs();
					TreatmentModel treatModel = new TreatmentModel();
					/**
					 * Patient 
					 */
					treatModel.setHn(rs.getString("patient.hn"));
					treatModel.setPreName(rs.getString("pre_name_th"));
					treatModel.setFirstNameTH(rs.getString("first_name_th"));
					treatModel.setLastNameTH(rs.getString("last_name_th"));
					/**
					 * Branch
					 */
					treatModel.setBranchName(rs.getString("branch_name"));
					treatModel.setBranchId(rs.getString("branch_id"));
					treatModel.setBranchCode(rs.getString("branch_code"));
					treatModel.setHn(rs.getString("hn"));
					/**
					 * Treatment queue.
					 */
					treatModel.setQueueId(rs.getInt("pq_id"));
					treatModel.setQstatusKey(rs.getInt("tqs_id"));
					treatModel.setQstatusDescription(rs.getString("tqs_description"));
					treatModel.setWorkdayId(rs.getInt("pq_workday_id"));
					treatModel.setCreatedAt(rs.getString("created_at"));
					treatModel.setUpdatedAt(rs.getString("updated_at"));
					treatModel.setQstatusKey(rs.getInt("tqs_id"));
					treatList.add(treatModel);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		return treatList;
	}
	
	/**
	 * Insert patient into the treatment queue.
	 * @author anubissmile
	 * @param String | hn
	 * @param String | branchCode
	 * @return int | Count of record that get affected.
	 */
	public int insertPatientQueue(String hn, String branchCode){
		String SQL = "INSERT INTO `patient_queue` (`pq_hn`, `pq_branch`, `pq_status`, `created_at`, `updated_at`) "
				+ "VALUES ('" + hn + "', '" + branchCode + "', '1', NOW(), NOW())";
		int rec = 0;
		
		/**
		 * Checking for exist item.
		 */
		String SQL2 = "SELECT patient_queue.pq_id "
				+ "FROM patient_queue "
				+ "WHERE patient_queue.pq_hn = '" + hn + "' "
						+ "AND patient_queue.pq_branch = '" + branchCode + "' "
								+ "AND patient_queue.pq_status < 5 "
								+ "AND patient_queue.pq_status <> 3 ";
		agent.connectMySQL();
		try{
			agent.exeQuery(SQL2);
			rec = agent.size();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		if(rec < 1){
			/**
			 * Let's fetch treatment queue.
			 */
			try{
				agent.connectMySQL();
				agent.begin();
				rec = agent.exeUpdate(SQL);
			} catch(Exception e){
				e.printStackTrace();
			} finally {
				if(rec > 0){
					agent.commit();
				}else{
					agent.rollback();
				}
				agent.disconnectMySQL();
			}
		}else{
			return 0;
		}
		return rec;
	}
	
	public void AddTreatmentWaiting(String hn, int room_id, String status){
  
		String sql = "INSERT INTO treatment_working "
				+ "(hn, room_id, status, datetime) "
				+ "VALUES ('"+hn+"', "+room_id+", '"+ status+"', now())" ;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sql);
			
			Stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void AddTreatmentHistory(int treatment_id, String hn, String treatment_code, int doctor_id,  
			String surf, String tooth, String tooth_range, String tooth_type, int continue_count_all){
			try {
			conn = agent.getConnectMYSql();
			 
			String sql = "INSERT INTO history_treatment "
				+ "(treatment_id, hn, treatment_code, count, doctor_Id, surf, tooth, tooth_range, tooth_type, treatment_date, branch_id, emp_username) "
				+ "VALUES ("+treatment_id+", '"+hn+"', '"+treatment_code+"', 0, "+doctor_id+", '"+surf+"', '"+tooth+"', '"+tooth_range+"', '"+tooth_type+"' "
						+ ",now(), 0, 'manuwat')" ;
		
		
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sql);
			
			Stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void UpdateTreatmentContinue(int treatment_id, int continue_id, int continue_phase, int continue_count){
		  
		String sql = "UPDATE treatcontinue_transaction "
				+ "set treatment_id = "+treatment_id+" "
				+ "WHERE continue_id = "+continue_id+" and continue_phase = "+continue_phase+" and continue_count = "+continue_count+"" ;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sql);
			
			Stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
public void TreatmentHistoryProduct(int treatment_id, int product_id, int product_free, int product_transfer){
		  
		boolean checkDrug = checkDrug(treatment_id, product_id);
		String sql = "";
		if(checkDrug==false){
			sql = "INSERT INTO history_treatment_product "
					+ "(treatment_id, product_id, product_free, product_transfer) "
					+ "VALUES ("+treatment_id+", "+product_id+", "+product_free+", "+product_transfer+")" ;
		}else{
			 
			product_free += Select_DrugFreeHistory(treatment_id, product_id);
			
			sql = "UPDATE history_treatment_product set "
					+ "product_free = "+product_free+" "
					+ "WHERE treatment_id = "+treatment_id+" and product_id = "+product_id+"" ;
		}
		 
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sql);
			
			Stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
public void TreatmentHistoryProductContinue(int treatment_id, int product_id, int product_free, int product_transfer){
	  
	boolean checkDrug = checkDrug(treatment_id, product_id);
	String sql = "";
	if(checkDrug==false){
		sql = "INSERT INTO history_treatment_product "
				+ "(treatment_id, product_id, product_free, product_transfer) "
				+ "VALUES ("+treatment_id+", "+product_id+", "+product_free+", "+product_transfer+")" ;
	}else{
		 
		product_transfer += Select_DrugFreeHistoryContinue(treatment_id, product_id);
		
		sql = "UPDATE history_treatment_product set "
				+ "product_transfer = "+product_transfer+" "
				+ "WHERE treatment_id = "+treatment_id+" and product_id = "+product_id+"" ;
	}
	 
	try {
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
		
		Stmt.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}
public void TreatmentHistoryProductDrug(int treatment_id, int product_id, int product_free, int product_transfer, int qty){
	  
	boolean checkDrug = checkDrug(treatment_id, product_id);
	String sql = "";
	if(checkDrug==false){
		sql = "INSERT INTO history_treatment_product "
				+ "(treatment_id, product_id, product_free, product_transfer, qty) "
				+ "VALUES ("+treatment_id+", "+product_id+", "+product_free+", "+product_transfer+", "+qty+")" ;
	}else{
		  
		sql = "UPDATE history_treatment_product set "
				+ "product_free = "+product_free+", product_transfer = "+product_transfer+", qty = "+qty+" "
				+ "WHERE treatment_id = "+treatment_id+" and product_id = "+product_id+"" ;
	}
	 
	try {
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
		
		Stmt.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}
public void TreatmentHistoryProductDrugDelete(int treatment_id, String product_id) throws Exception{
	conn = agent.getConnectMYSql();  
	 
		String sql  = "DELETE FROM history_treatment_product "
				+ " WHERE treatment_id = "+treatment_id+" and product_id not in "+product_id+" "
				+ "and product_id not in (select product_id from pro_product where producttype_id = '0002') " ;
	   
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
		
		Stmt.close();
		conn.close(); 
}
public void TreatmentHistoryProductDelete(int treatment_id, String product_id) throws Exception{
	conn = agent.getConnectMYSql();  
	 
		String sql  = "DELETE FROM history_treatment_product "
				+ "WHERE treatment_id = "+treatment_id+" and product_id not in "+product_id+" "
				+ "and product_id not in (select product_id from pro_product where producttype_id = '0001') " ;
	   
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
		
		Stmt.close();
		conn.close(); 
}
public List Select_ProductID(String treatment_code){
	
	List product_id = new ArrayList<List>();
	try 
	{
		conn = agent.getConnectMYSql();
		
		String sql = "select product_id from treatment_product "
				+ "WHERE treatment_code = '"+treatment_code+"' ";
		
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql);
		int i=0;
		while (rs.next()){ 
			product_id.add(rs.getString("product_id"));  
			i++;
		}
		rs.close();
		Stmt.close();
		conn.close();
	} 
	catch (IOException e) {
		e.printStackTrace();
	}
	catch (Exception e)  { 
		e.printStackTrace();
	} 
	return product_id;
}
public List Select_Continue_ProductID(int continue_id, int continue_phase, int continue_count){
	
	List product_id = new ArrayList<List>();
	try 
	{
		conn = agent.getConnectMYSql();
		
		String sql = "select product_id from treatcontinue_product "
				+ "WHERE continue_id = "+continue_id+" and continue_phase = "+continue_phase+" and continue_count = "+continue_count+" ";
		
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql);
		int i=0;
		while (rs.next()){ 
			product_id.add(rs.getString("product_id"));  
			i++;
		}
		rs.close();
		Stmt.close();
		conn.close();
	} 
	catch (IOException e) {
		e.printStackTrace();
	}
	catch (Exception e)  { 
		e.printStackTrace();
	} 
	return product_id;
}
public int Select_DrugFreeHistory(int treatment_id, int product_id){
	String sqlQuery = "select product_free from history_treatment_product "
			+ "WHERE treatment_id = "+treatment_id+" and product_id = '"+product_id+"' ";
	int product_free = 0; 
	try 
	{
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		 
		while (rs.next()){
			product_free = rs.getInt("product_free");  
		}
		rs.close();
		Stmt.close();
		conn.close();
	} 
	catch (IOException e) {
		e.printStackTrace();
	}
	catch (Exception e)  { 
		e.printStackTrace();
	} 
	return product_free;
}
public int Select_DrugFreeHistoryContinue(int treatment_id, int product_id){
	String sqlQuery = "select product_transfer from history_treatment_product "
			+ "WHERE treatment_id = "+treatment_id+" and product_id = '"+product_id+"' ";
	int product_transfer = 0; 
	try 
	{
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		 
		while (rs.next()){
			product_transfer = rs.getInt("product_transfer");  
		}
		rs.close();
		Stmt.close();
		conn.close();
	} 
	catch (IOException e) {
		e.printStackTrace();
	}
	catch (Exception e)  { 
		e.printStackTrace();
	} 
	return product_transfer;
}
public int Select_DrugFree(int product_id, String treatment_code){
	String sqlQuery = "select product_free from treatment_product "
			+ "WHERE product_id = "+product_id+" and treatment_code = '"+treatment_code+"' ";
	int product_free = 0; 
	try 
	{
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		 
		while (rs.next()){
			product_free = rs.getInt("product_free");  
		}
		rs.close();
		Stmt.close();
		conn.close();
	} 
	catch (IOException e) {
		e.printStackTrace();
	}
	catch (Exception e)  { 
		e.printStackTrace();
	} 
	return product_free;
}
public int Select_DrugContinue(int product_id, int continue_id, int continue_phase, int continue_count){
	String sqlQuery = "select qty from treatcontinue_product "
			+ "WHERE product_id = "+product_id+" and continue_id = "+continue_id+" and continue_phase = "+continue_phase+" and continue_count = "+continue_count+" ";
	int product_continue = 0; 
	try 
	{
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		 
		while (rs.next()){
			product_continue = rs.getInt("qty");  
		}
		rs.close();
		Stmt.close();
		conn.close();
	} 
	catch (IOException e) {
		e.printStackTrace();
	}
	catch (Exception e)  { 
		e.printStackTrace();
	} 
	return product_continue;
}
public boolean checkDrug(int treatment_id, int product_id){
		boolean checkdrug = false;
	  
	    String sqlQuery = "SELECT * FROM history_treatment_product "
				+ "WHERE treatment_id = '"+treatment_id+"' and product_id = "+product_id+" "; 
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next()) {
				checkdrug = true;
			} 
			rs.close();
			Stmt.close();
			conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return checkdrug; 
}
public int Select_Treatment_ID(String hn){
		String sqlQuery = "select treatment_id from patient "
				+ "WHERE hn = '"+hn+"' ";
		int ResultInt = 0;
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next()) {
				ResultInt = rs.getInt("treatment_id"); 
				
				if(ResultInt==0){
					sqlQuery = "select MAX(treatment_id) as treatment_id from running_treatmentid "
							+ "WHERE treatment_id <> '' ";
					Stmt = conn.createStatement();
					ResultSet rs1 = Stmt.executeQuery(sqlQuery);
					while (rs1.next()) {
						ResultInt = rs1.getInt("treatment_id"); 
						ResultInt += 1;
					} 
					rs1.close();
					
					sqlQuery = "UPDATE patient "
							+ "set treatment_id = '"+ResultInt+"' "
							+ "WHERE hn = '"+hn+"'" ; 
					Stmt = conn.createStatement();
					Stmt.executeUpdate(sqlQuery);
					
					Stmt.close();
					
					sqlQuery = "UPDATE running_treatmentid "
							+ "set treatment_id = '"+ResultInt+"' "; 
					Stmt = conn.createStatement();
					Stmt.executeUpdate(sqlQuery);
					
					Stmt.close();
				}
				
			} 
				 
			rs.close();
			Stmt.close();
			conn.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e)  { 
			e.printStackTrace();
		} 
		return ResultInt;
}	
public int Select_Count(int treatment_id, String hn){
		String sqlQuery = "select MAX(count) as countno from history_treatment "
				+ "WHERE treatment_id = "+treatment_id+" and hn = '"+hn+"' ";
		int ResultInt = 0;
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next()) {
				ResultInt = rs.getInt("countno"); 
				ResultInt += 1;
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e)  { 
			e.printStackTrace();
		} 
		return ResultInt;
}
	
	public void UpdateWaitingToTreatmenting(String hn, int room_id, String status){
		  
		String sql = "UPDATE treatment_working "
				+ "set room_id = "+room_id+", status = '"+status+"', datetime = now() "
				+ "WHERE hn = '"+hn+"'" ;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sql);
			
			Stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void UpdateTreatmentingToSuccess(String hn, String status){
		  
		String sql = "UPDATE treatment_working "
				+ "set status = '"+status+"', datetime = now() "
				+ "WHERE hn = '"+hn+"'" ;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sql);
			
			Stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
		
	public ServicePatientModel select_TP(String hn) throws IOException, Exception
	{
		String sqlQuery = "select a.hn, c.room_id, c.status, TIMESTAMPDIFF(MINUTE, c.datetime, now()) as minute, TIMESTAMPDIFF(year,a.birth_date,NOW()) as birthdate, "
				+ "a.first_name_th, a.last_name_th, a.first_name_en, a.last_name_en, a.deposit_money, b.pre_name_th, d.tel_number "
				+ "from patient a inner join pre_name b on(b.pre_name_id = a.pre_name_id) "
				+ "inner join treatment_working c on(c.hn = a.hn) "
				+ "left join tel_telephone d on(d.tel_id = a.tel_id and d.tel_typeid = '1') where ";
 
		if (new Validate().Check_String_notnull_notempty(hn))
			sqlQuery += "a.hn = '"+hn+"' "; 
		//System.out.println(sqlQuery);
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		ServicePatientModel smModel = null;
		List<TelephoneModel> tel_no = new ArrayList<TelephoneModel>();
		while (rs.next()){ 
			tel_no.add(new TelephoneModel(0, rs.getString("tel_number"), 0));
			
			smModel = new ServicePatientModel(rs.getString("hn"), rs.getString("pre_name_th"), rs.getString("first_name_th"), rs.getString("last_name_th")
					, rs.getString("first_name_en"), rs.getString("last_name_en"), rs.getString("birthdate"), rs.getDouble("deposit_money"), rs.getString("status")
					, tel_no);
			
			smModel.setTel_number(rs.getString("tel_number"));
		 
		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return smModel;
		
	}
	
	public List<ServicePatientModel> select_doctor_room(ServicePatientModel servicePatientModel) throws IOException, Exception {
		
		//String doctor_name = "", room_name = "";
		
		String sqlQuery = "select a.room_id, b.room_name, a.doctor_id, concat(c.first_name_th,' ',c.last_name_th) as doctor_name "
				+ ",IF(d.status='P', 'ไม่ว่าง', 'ว่าง') as status_room "
				+ "FROM treatment_room a INNER JOIN room_id b on(b.room_id = a.room_id) " 
				+ "INNER JOIN doctor c on(c.doctor_id = a.doctor_id) "
				+ "LEFT JOIN treatment_working d on(d.room_id = a.room_id and d.status in ('W','P')) WHERE a.doctor_id <> '' ORDER BY status";
 
		/*if (servicePatientModel.getDoctor_id() != 0)
			sqlQuery += "and a.doctor_id = '"+servicePatientModel.getDoctor_id()+"' "; 
		if(servicePatientModel.getRoom_id() != 0)
			sqlQuery += "and a.room_id = "+servicePatientModel.getRoom_id()+" ";*/
		
		//System.out.println(sqlQuery);
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List <ServicePatientModel> resultList = new ArrayList<ServicePatientModel>();
		ServicePatientModel smModel = null; 
		while (rs.next()){   
			resultList.add(new ServicePatientModel(rs.getString("doctor_name"), rs.getString("room_name"), rs.getString("status_room"), rs.getInt("doctor_id"), rs.getInt("room_id"))); 
		//	smModel.setTel_number(rs.getString("tel_number")); 
		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return resultList;
		
	}
	
public List transectionTreatment(String hn, int treatment_id) throws IOException, Exception {
		
		//String doctor_name = "", room_name = "";
		
		String sqlQuery = "SELECT a.treatment_id, a.count, concat(b.first_name_th,' ',b.last_name_th) as doctor_name, "
				+ "a.treatment_code, c.treatment_nameth, "
				+ "IF(c.treatment_mode='1',c.price_standard,(select setup_price from treatcontinue_transaction aa "
				+ "INNER JOIN treatcontinue_setup bb on(aa.continue_id = bb.continue_id) "
				+ "WHERE treatment_id = a.treatment_id and bb.treatment_code = a.treatment_code)) as price_standard "
				+ ",c.treatment_mode "
				+ "FROM history_treatment a " 
				+ "INNER JOIN doctor b on(b.doctor_id = a.doctor_Id) "
				+ "INNER JOIN treatment_master c on(c.treatment_code = a.treatment_code) "
				+ "WHERE ";
		 
		if (new Validate().Check_String_notnull_notempty(hn)){
			sqlQuery += "a.hn = '"+hn+"' ";  
		}else{
			sqlQuery += "a.hn = '' "; 
		}
		if (new Validate().Check_String_notnull_notempty(hn)){
			sqlQuery += "and a.treatment_id  = "+treatment_id+" ";  
		}else{
			sqlQuery += "and a.treatment_id  = "+treatment_id+" "; 
		}
  
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List <ServicePatientModel> resultList = new ArrayList<ServicePatientModel>();
		ServicePatientModel smModel = null; 
		while (rs.next()){   
			resultList.add(new ServicePatientModel(rs.getInt("treatment_id"), rs.getInt("count"),
					rs.getString("doctor_name"), rs.getString("treatment_code"), rs.getString("treatment_nameth"), 
					rs.getString("price_standard"),rs.getString("treatment_mode"))); 
		//	smModel.setTel_number(rs.getString("tel_number")); 
		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return resultList; 
	}
public void DeleteTransectionTreatment(int treatment_id, String treatment_code){ 
	 
	try {
		conn = agent.getConnectMYSql();
		
		String sqlQuery = "delete from history_treatment where treatment_id = "+treatment_id+" and treatment_code = '"+treatment_code+"' ";
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sqlQuery); 
		Stmt.close();
		 
		List product_id = Select_ProductID(treatment_code);
		for(int i=0; i<product_id.size(); i++){ 
			int product_free			= Select_DrugFree(Integer.valueOf(product_id.get(i).toString()), treatment_code);
			int product_free_history	= Select_DrugFreeHistory(treatment_id, Integer.valueOf(product_id.get(i).toString())); // sum total free
			if(product_free>=product_free_history){
				product_free = product_free-product_free_history;
			}else{
				product_free = product_free_history-product_free;
			}
			if(product_free<=0){
				sqlQuery = "DELETE FROM history_treatment_product " 
						+ "WHERE treatment_id = "+treatment_id+" and product_id = "+Integer.valueOf(product_id.get(i).toString())+" " ;
				conn = agent.getConnectMYSql();
				Stmt = conn.createStatement();
				Stmt.executeUpdate(sqlQuery);
				Stmt.close();
			}else{
				sqlQuery = "UPDATE history_treatment_product "
						+ "set product_free = "+product_free+" "
						+ "WHERE treatment_id = "+treatment_id+" and product_id = "+Integer.valueOf(product_id.get(i).toString())+" " ;
				conn = agent.getConnectMYSql();
				Stmt = conn.createStatement();
				Stmt.executeUpdate(sqlQuery);
				
				Stmt.close();
			}  
		}
		
		conn.close();  
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
 
}	 
public void DeleteTransectionTreatmentContinue(int treatment_id, String treatment_code){  
	try {
		conn = agent.getConnectMYSql();
		
		String continue_id 			= ""; 
		String continue_phase 		= "";
		String continue_count 		= ""; 
		String continue_count_all 	= "";
		
		String sql = "select a.continue_id,a.continue_phase,a.continue_count,a.continue_count_all from treatcontinue_transaction a "
				+ "inner join treatcontinue_setup b on(b.continue_id = a.continue_id) "
				+ "WHERE a.treatment_id = "+treatment_id+" and b.treatment_code = '"+treatment_code+"' ";
		
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		while (rs.next()){ 
			continue_id 		= rs.getString("continue_id"); 
			continue_phase 		= rs.getString("continue_phase");
			continue_count 		= rs.getString("continue_count");
			continue_count_all 	= rs.getString("continue_count_all");
		}
		rs.close();
		Stmt.close();
		 
			String sqlQuery = "delete from history_treatment where treatment_id = "+treatment_id+" and treatment_code = '"+treatment_code+"' ";
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sqlQuery); 
			Stmt.close(); 
			
			sqlQuery = "delete from treatcontinue_product where producttype_id = '0003' and continue_id = "+continue_id+" and continue_count_all = '"+continue_count_all+"' ";
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sqlQuery); 
			Stmt.close();
			
			List product_id = Select_Continue_ProductID(Integer.valueOf(continue_id), Integer.valueOf(continue_phase), Integer.valueOf(continue_count));
			for(int i=0; i<product_id.size(); i++){ 
				int product_continue	= Select_DrugContinue(Integer.valueOf(product_id.get(i).toString()), 
						Integer.valueOf(continue_id), Integer.valueOf(continue_phase), Integer.valueOf(continue_count));
				int product_continue_history	= Select_DrugFreeHistoryContinue(treatment_id, Integer.valueOf(product_id.get(i).toString())); // sum total free
				if(product_continue>=product_continue_history){
					product_continue = product_continue-product_continue_history;
				}else{
					product_continue = product_continue_history-product_continue;
				}
				if(product_continue<=0){
					sqlQuery = "DELETE FROM history_treatment_product " 
							+ "WHERE treatment_id = "+treatment_id+" and product_id = "+Integer.valueOf(product_id.get(i).toString())+" " ;
					conn = agent.getConnectMYSql();
					Stmt = conn.createStatement();
					Stmt.executeUpdate(sqlQuery);
					Stmt.close();
				}else{
					sqlQuery = "UPDATE history_treatment_product "
							+ "set product_transfer = "+product_continue+" "
							+ "WHERE treatment_id = "+treatment_id+" and product_id = "+Integer.valueOf(product_id.get(i).toString())+" " ;
					conn = agent.getConnectMYSql();
					Stmt = conn.createStatement();
					Stmt.executeUpdate(sqlQuery);
					
					Stmt.close();
				}  
			}
			 
		conn.close();  
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
 
}
public void UpdateTreatmentContinueIsDelete(int treatment_id, String treatment_code){
	  
	String sql = "UPDATE treatcontinue_transaction a inner join treatcontinue_setup b on(a.continue_id = b.continue_id) "
			+ "set a.treatment_id = null "
			+ "WHERE a.treatment_id = "+treatment_id+" and b.treatment_code = '"+treatment_code+"' " ;
	
	try {
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
		
		Stmt.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}
		
	public List<BrandModel> select_brand(int brand_id, String brand_name) throws IOException, Exception
	{
		String sqlQuery = "select * from brand where ";

		if (brand_id !=0)
			sqlQuery += "brand_id = " + brand_id + " and ";

		if (new Validate().Check_String_notnull_notempty(brand_name))
			sqlQuery += "brand_name like '%" + brand_name + "%' and ";
		
		sqlQuery += "brand_id != '' ";
		//System.out.println("-----------");
		//System.out.println(sqlQuery);
		
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		
		List<BrandModel> ResultList = new ArrayList<BrandModel>();
		while (rs.next())
		{
			BrandModel brdModel = new BrandModel(rs.getInt("brand_id"), rs.getString("brand_name"));
		//	brdModel.setBrand_name("aaaa");
			ResultList.add(brdModel);

		}
		
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return ResultList;
		
	}
	
	
	
	
	public int GetHighest_add_brand()
	
	{
		String sqlQuery = "select MAX(brand_id) as brand_id from brand";
		int ResultInt = 0;
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				ResultInt = rs.getInt("brand_id");
				//ResultString = rs.getString("pre_name_th");
				//ResultString = rs.getString("pre_name_en");
			}
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResultInt;
	}
	
	
	
	
	public int PlusOneID_FormatID(int brand_id)
	{
		if(brand_id == 0)
		{
			brand_id = 1;
		}
		else
		{
			brand_id = brand_id+1;
		}
		
		return brand_id;
	}
	
	
	
	
	public Boolean DeleteBrand(int brand_id)
	{
		String sqlQuery = "delete from brand where brand_id = ?";
		Boolean delete_success = false;
		try {

			conn = agent.getConnectMYSql();
			
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setInt(1, brand_id);
			int rowsupdate = pStmt.executeUpdate();
			

			if (rowsupdate > 0)
				delete_success = true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (!pStmt.isClosed())
					pStmt.close();
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return delete_success;
	}
	
	
	
	public int UpdateBrand(int brand_id, String brand_name)
	{
		
		String sqlQuery = "update brand set brand_id = ? , brand_name = ? where brand_id = ?";
		
		//System.out.println(sqlQuery);
		
		int rowsupdate = 0;
		try 
		{

			conn = agent.getConnectMYSql();
			conn.setAutoCommit(false);
			pStmt = conn.prepareStatement(sqlQuery);
			pStmt.setInt(1, brand_id);
			pStmt.setString(2, brand_name);
			pStmt.setInt(3, brand_id);

			
			rowsupdate = pStmt.executeUpdate();
			conn.commit();

		} 
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally 
			{
				try 
				{
					if (!pStmt.isClosed())
						pStmt.close();
					if (!conn.isClosed())
						conn.close();
				} 
			
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		return rowsupdate;
	}
	public List<ScheduleModel> DoctorReadyToWork() throws Exception{
		String branch_id = Auth.user().getBranchCode();
/*		DateUtil dateU = new DateUtil();
		String dateE = dateU.curDate();
		String dateE2 = dateU.CnvToYYYYMMDD(dateE,'-');
		String dateT = dateU.CnvYYYYMMDDToYYYYMMDDThaiYear(dateE2,"-");*/
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
				+ "room_id.room_name, "
				+ "pre_name.pre_name_th "
				+ "FROM doctor_workday "
				+ "INNER JOIN doctor ON doctor_workday.doctor_id = doctor.doctor_id "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = doctor.pre_name_id "
				+ "INNER JOIN room_id ON doctor_workday.branch_id = room_id.room_branch_code AND doctor_workday.branch_room_id = room_id.room_id "
				+ "Where doctor_workday.checkin_status = '2' and doctor_workday.start_datetime BETWEEN concat(CURDATE(),' 00:00:00') AND concat(CURDATE(),' 23:59:59') "
				+ "and doctor_workday.branch_id = '"+branch_id+"' "
				+ "ORDER BY  	doctor_workday.start_datetime ASC ";

		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();
				List<ScheduleModel> schList = new LinkedList<ScheduleModel>();
				while(rs.next()){
					ScheduleModel schModel = new ScheduleModel();
					schModel.setRoomId(rs.getInt("branch_room_id"));
					schModel.setWorkDayId(rs.getInt("workday_id"));
					schModel.setFirst_name_th(rs.getString("first_name_th"));
					schModel.setLast_name_th(rs.getString("last_name_th"));
					schModel.setRoomName(rs.getString("room_name"));
					schModel.setPre_name_th(rs.getString("pre_name_th"));
					schModel.setEmployeeList(getEmpWorkdayList(schModel.getWorkDayId(),schModel.getRoomId()));
					schList.add(schModel);
				}
				return schList;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}
	
	public List<Person> getEmpWorkdayList(int doctorWorkId , int roomid){
		String SQL = "SELECT employee.emp_id, "
				+ "pre_name.pre_name_th, "
				+ "employee.first_name_th, "
				+ "employee.last_name_th "
				+ "FROM employee_workday "
				+ "INNER JOIN employee ON employee_workday.emp_id = employee.emp_id "
				+ "INNER JOIN pre_name ON employee.pre_name_id = pre_name.pre_name_id "
				+ "WHERE doctor_workday_id = "+doctorWorkId+" AND branch_room_id = "+roomid ;
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size()>0){
			ResultSet rss = agent.getRs();
			try {
				List<Person> personList = new ArrayList<Person>();
				while(rss.next()){
					Person personModel = new Person();
					personModel.setEmp_id(rss.getString("employee.emp_id"));
					personModel.setPre_name_th(rss.getString("pre_name_th"));
					personModel.setEmpname_th(rss.getString("first_name_th"));
					personModel.setEmplastname_th(rss.getString("last_name_th"));
					personList.add(personModel);
				}
				return personList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		
		return null;
	}	
	public void addRoomCheckInTime(TreatmentModel treatment) throws Exception{
		ScheduleModel schModel = new ScheduleModel();
		schModel = DoctorWork(treatment.getWorkdayId());
		String SQL = "INSERT INTO treatment_room_checkin (trch_id,trch_doctor_id,trch_room_id,trch_workday_id,trch_time_in,trch_time_out) "
				+ "VALUES ( "
				+ "'"+treatment.getQueueId()+"', "
				+ "'"+schModel.getDoctorId()+"', "
				+ "'"+schModel.getRoomId()+"', "
				+ "'"+treatment.getWorkdayId()+"', "
				+ "now(), "			
				+ "now()) ";
		conn = agent.getConnectMYSql();
		pStmt = conn.prepareStatement(SQL);
		pStmt.executeUpdate();
		if(!pStmt.isClosed()) pStmt.close();
		if(!conn.isClosed()) conn.close();

	}
	public void DeleteRoomCheckInTime(TreatmentModel treatment) throws Exception{

		String SQL = "DELETE FROM treatment_room_checkin "
				+ "WHERE trch_id ='"+treatment.getQueueId()+"' ";
		conn = agent.getConnectMYSql();
		pStmt = conn.prepareStatement(SQL);
		pStmt.executeUpdate();
		if(!pStmt.isClosed()) pStmt.close();
		if(!conn.isClosed()) conn.close();

	}
	public void UpdateRoomCheckInTime(TreatmentModel treatment) throws Exception{

		String SQL = "UPDATE treatment_room_checkin "
				+ "SET "
				+ "trch_time_out = now() "
				+ "WHERE trch_id ='"+treatment.getQueueId()+"' ";
		conn = agent.getConnectMYSql();
		pStmt = conn.prepareStatement(SQL);
		pStmt.executeUpdate();
		if(!pStmt.isClosed()) pStmt.close();
		if(!conn.isClosed()) conn.close();

	}		
	public ScheduleModel  DoctorWork(int workdayID) throws Exception{
		ScheduleModel schModel = new ScheduleModel();
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
				+ "room_id.room_name, "
				+ "pre_name.pre_name_th "
				+ "FROM doctor_workday "
				+ "INNER JOIN doctor ON doctor_workday.doctor_id = doctor.doctor_id "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = doctor.pre_name_id "
				+ "INNER JOIN room_id ON doctor_workday.branch_id = room_id.room_branch_code AND doctor_workday.branch_room_id = room_id.room_id "
				+ "Where doctor_workday.checkin_status = '2' and doctor_workday.start_datetime BETWEEN concat(CURDATE(),' 00:00:00') AND concat(CURDATE(),' 23:59:59') "
				+ "and doctor_workday.workday_id = '"+workdayID+"' "
				+ "ORDER BY  	doctor_workday.start_datetime ASC ";

		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					schModel.setDoctorId(rs.getInt("doctor_id"));
					schModel.setRoomId(rs.getInt("branch_room_id"));
					schModel.setWorkDayId(rs.getInt("workday_id"));
					schModel.setFirst_name_th(rs.getString("first_name_th"));
					schModel.setLast_name_th(rs.getString("last_name_th"));
					schModel.setRoomName(rs.getString("room_name"));
					schModel.setPre_name_th(rs.getString("pre_name_th"));
				}
				return schModel;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}
	public DoctorModel  getDoctor(String docid) throws Exception{
		DoctorModel docModel = new DoctorModel();
		String SQL = "SELECT "
				+ "doctor.doctor_id,pre_name.pre_name_th,doctor.first_name_th,doctor.last_name_th "
				+ "FROM doctor "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = doctor.pre_name_id "
				+ "Where  "
				+ "doctor.doctor_id = '"+docid+"' ";


		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					docModel.setDoctorID(rs.getInt("doctor_id"));
					docModel.setFirst_name_th(rs.getString("first_name_th"));
					docModel.setLast_name_th(rs.getString("last_name_th"));
					docModel.setPre_name_th(rs.getString("pre_name_th"));
				}
				return docModel;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}		
	public ScheduleModel  RoomTreatment(int roomid) throws Exception{
		ScheduleModel schModel = new ScheduleModel();
		String branch_id = Auth.user().getBranchCode();
		String SQL = "SELECT room_name, "
				+ "room_id "
				+ "FROM room_id "
				+ "Where  room_id = '"+roomid+"' AND room_branch_code ='"+branch_id+"' ";

		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					schModel.setRoomId(rs.getInt("room_id"));
					schModel.setRoomName(rs.getString("room_name"));
				}
				return schModel;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}
	public List<TreatmentMasterModel>  TreatmentWithDoctortreatmentList(String treatpatid) throws Exception{
			
		String SQL = "SELECT treatment_master.id,treatment_master.code, "
				+ "treatment_master.is_continue,treatment_pricelist.amount,treatment_master.nameth, "
				+ "treatment_master.nameen, (IFNULL(treatment_patient_line.treatment_id,'nu')) "
				+ "FROM treatment_master "
				+ "INNER JOIN treatment_pricelist ON treatment_master.id = treatment_pricelist.treatment_id "
				+ "LEFT  JOIN treatment_patient_line ON treatment_master.id = treatment_patient_line.treatment_id  "
				+ "AND treatment_patient_line.treatment_patient_id = '"+treatpatid+"' "
				+ "WHERE treatment_pricelist.brand_id = (SELECT brand_id FROM branch where branch_id = '"+Auth.user().getBranchID()+"') "
				+ "AND treatment_pricelist.price_typeid = '1' "
				+ "AND ((IFNULL(treatment_patient_line.treatment_id,'nu')) = 'nu' OR treatment_master.is_repeat = '1') "
				+ "GROUP BY treatment_master.id "
				+ "order by treatment_master.id ";
		
		List<TreatmentMasterModel> treatList = new ArrayList<TreatmentMasterModel>();
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					TreatmentMasterModel treatModel = new TreatmentMasterModel();
					treatModel.setTreatment_id(rs.getString("treatment_master.id"));
					treatModel.setTreatment_code(rs.getString("treatment_master.code"));
					treatModel.setTreatment_nameth(rs.getString("treatment_master.nameth"));
					treatModel.setTreatment_nameen(rs.getString("treatment_master.nameen"));
					treatModel.setPrice(rs.getString("treatment_pricelist.amount"));
					treatModel.setTreatment_iscon(rs.getString("treatment_master.is_continue"));
					treatList.add(treatModel);

				}
				return treatList;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}	
	public String insertTreatmentPatient(String hn,int workdayID){
		ScheduleModel schModel = new ScheduleModel();
		try {
			schModel = DoctorWork(workdayID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String SQL ="INSERT INTO treatment_patient (doctor_id,patient_hn,room_id,start_time,status_work) "
				+ "VALUES ('"+schModel.getDoctorId()+"','"+hn+"','"+schModel.getRoomId()+"',NOW(),'2') ";
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			
			
			
			int treatment_patient_id =0;
			if(rs.next()){
				treatment_patient_id = rs.getInt(1);
			}
			if (!rs.isClosed())
				rs.close();
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			return treatment_patient_id+","+schModel.getRoomId();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		
		return null;
	}
	public void insertTreatmentAssistant(int treatment_patientID,int workdayID,int roomid){
		

		List<Person> emplist  = getEmpWorkdayList(workdayID,roomid);
		String empempolyee_id = "";
		for(Person empmodel : emplist){
			empempolyee_id += empmodel.getEmp_id()+",";
		}
		String [] emp_id = empempolyee_id.split(",");
		
		String SQL ="INSERT INTO treatment_assistant (emp_id,treatment_patient_id) "
				+ "VALUES ";
				int checksize = 0;
				for(String empolyee : emp_id ){
					if(checksize > 0){
						SQL +=",";
					}
					SQL += "('"+empolyee+"','"+treatment_patientID+"' )";
					checksize++;
				}
				
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

					
			if (!pStmt.isClosed())
				pStmt.close();
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
	public void UpdateTreatmentPatientForCancel(String hn){
				
		String SQL ="UPDATE  treatment_patient  "
				+ "set status_work = '3' "
				+ "Where patient_hn = '"+hn+"' AND status_work = '2' ";
				
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

					
			if (!pStmt.isClosed())
				pStmt.close();
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
	public List<ProductModel>  ProductListForTreatment(String hn) throws Exception{
		
		String SQL = "SELECT pro_product.product_id, pro_product.producttype_id, "
				+ "pro_product.product_name, IFNULL(patient_beallergic.hn,'nu') AS HN "
				+ "FROM pro_product "
				+ "LEFT JOIN patient_beallergic ON pro_product.product_id = patient_beallergic.product_id AND hn ='"+hn+"' "
				+ "WHERE pro_product.product_id != '1' ";
		
		List<ProductModel> protreatList = new ArrayList<ProductModel>();
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					ProductModel protreatModel = new ProductModel();
					protreatModel.setProduct_id(rs.getInt("pro_product.product_id"));
					protreatModel.setProducttype_Id(rs.getString("pro_product.producttype_id"));
					protreatModel.setProduct_name(rs.getString("pro_product.product_name"));
					protreatModel.setProduct_isCheck(rs.getString("HN"));
					protreatList.add(protreatModel);

				}
				return protreatList;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}
	public List<TreatmentModel> getTreatmentPatAndQueue(){
		String SQL = "SELECT patient.hn,pre_name.pre_name_th,patient.first_name_th, "
				+ "patient.last_name_th,patient_queue.pq_workday_id, "
				+ "treatment_patient.id,treatment_patient.status_work, "
				+ "patient_queue.pq_status "
				+ "FROM patient "
				+ "LEFT  JOIN patient_queue ON patient.hn = patient_queue.pq_hn  "
				+ "LEFT  JOIN treatment_patient ON patient.hn = treatment_patient.patient_hn "
				+ "INNER JOIN pre_name ON patient.pre_name_id = pre_name.pre_name_id "
				+ "WHERE patient_queue.pq_status = '4' AND treatment_patient.status_work = '2' ";
		
		List<TreatmentModel> treatList = new ArrayList<TreatmentModel>();
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		try {
			if(agent.size() > 0){
				while(agent.getRs().next()){
					rs = agent.getRs();
					TreatmentModel treatModel = new TreatmentModel();
					/**
					 * Patient 
					 */
					treatModel.setHn(rs.getString("patient.hn"));
					treatModel.setPreName(rs.getString("pre_name_th"));
					treatModel.setFirstNameTH(rs.getString("first_name_th"));
					treatModel.setLastNameTH(rs.getString("last_name_th"));

					/**
					 * Treatment queue.
					 */
					treatModel.setWorkdayId(rs.getInt("pq_workday_id"));
					/**
					 * Treatment patient 
					 */	
					treatModel.setTreatment_patient_ID(rs.getString("treatment_patient.id"));
					treatList.add(treatModel);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			agent.disconnectMySQL();
		}
		return treatList;
	}
	public TreatmentModel  getTreatmentPatient(String treatPatId) throws Exception{
		TreatmentModel treatModel = new TreatmentModel();
		String SQL = "SELECT "
				+ "treatment_patient.id,treatment_patient.patient_hn,treatment_patient.room_id, "
				+ "treatment_patient.status_work,treatment_patient.doctor_id,treatment_patient.start_time,room_id.room_name "
				+ "FROM treatment_patient "
				+ "INNER JOIN room_id ON treatment_patient.room_id = room_id.room_id "
				+ "WHERE treatment_patient.id = '"+treatPatId+"' ";


		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					treatModel.setTreatment_patient_ID(rs.getString("treatment_patient.id"));
					treatModel.setTreatment_patient_hn(rs.getString("treatment_patient.patient_hn"));
					treatModel.setTreatment_patient_roomID(rs.getString("treatment_patient.room_id"));
					treatModel.setTreatment_patient_docID(rs.getString("treatment_patient.doctor_id"));
					treatModel.setTreatment_patient_status(rs.getString("treatment_patient.status_work"));
					treatModel.setTreatment_patient_startTime(rs.getString("treatment_patient.start_time"));
					treatModel.setTreatment_patient_roomName(rs.getString("room_id.room_name"));
				}
				return treatModel;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}
	public void AddTreatmentPatientLine(TreatmentModel treatModel){

		String SQL ="INSERT INTO treatment_patient_line "
				+ "(treatment_id,treatment_patient_id,treatment_price ";
				if(!treatModel.getSurface().equals("")){
					SQL +=",surf ";
				}
				if(!treatModel.getTooth().equals("")){
					SQL +=",tooth ";
				}else if(!treatModel.getSurface_tooth().equals("")){
					SQL +=",tooth ";
				}else if(!treatModel.getToothRange().equals("")){
					SQL +=",tooth ";
				}else if(treatModel.getQuadrant()!=null){
					SQL +=",tooth ";
				}else if(treatModel.getArch()!=null){
					SQL +=",tooth ";
				} 
				if(!treatModel.getTreatmentplandetailid().equals("")){
					SQL +=",treatment_plandetail_id ";
				}
				SQL+= ",tooth_type_id) "
				+ "VALUES "
				+ "('"+treatModel.getTreatment_ID()+"','"+treatModel.getTreatment_patient_ID()+"' "
				+ ",(SELECT amount FROM treatment_pricelist WHERE treatment_id = '"+treatModel.getTreatment_ID()+"' "
						+ "AND price_typeid = '1' AND brand_id = (SELECT brand_id FROM branch WHERE branch_id = '"+Auth.user().getBranchID()+"')) ";
				if(!treatModel.getSurface().equals("")){
					SQL +=",'"+treatModel.getSurface()+"' ";
				}
				if(!treatModel.getTooth().equals("")){
					SQL +=",'"+treatModel.getTooth()+"' ";
				}else if(!treatModel.getSurface_tooth().equals("")){
					SQL +=",'"+treatModel.getSurface_tooth()+"' ";
				}else if(!treatModel.getToothRange().equals("")){
					SQL +=",'"+treatModel.getToothRange()+"' ";
				}else if(treatModel.getQuadrant()!=null){
					SQL +=",'"+treatModel.getQuadrant()+"' ";
				}else if(treatModel.getArch()!=null){
					SQL +=",'"+treatModel.getArch()+"' ";
				}
				if(!treatModel.getTreatmentplandetailid().equals("")){
					SQL +=",'"+treatModel.getTreatmentplandetailid()+"' ";
				}
				SQL += ",'"+treatModel.getTooth_types()+"')";
				
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

					
			if (!pStmt.isClosed())
				pStmt.close();
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
	public void addMedicineAfterAddtreatpatline(TreatmentModel treatModel , String treatPatID){
		
		TreatmentModel treatMedicine =	TreatMentPatientMedicineIsNew(treatPatID,treatModel.getPro_id());

		String SQL ="";
			if(treatMedicine == null){
					SQL += "INSERT INTO treatment_patient_medicine "
						+ "(product_id,treatment_patient_id,amount,amount_free) "
						+ "VALUES "
						+ "("+treatModel.getPro_id()+","
						+ "'"+treatPatID+"',"
						+ ""+treatModel.getTreatPro_amount()+", "
						+ ""+treatModel.getTreatPro_amountfree()+") ";

			}else{
				int amountall = treatMedicine.getTreatPatMedicine_amount()+treatModel.getTreatPro_amount();
				int amountfreeall =treatMedicine.getTreatPatMedicine_amountfree()+treatModel.getTreatPro_amountfree();
					SQL +="UPDATE treatment_patient_medicine "
						+ "SET "
						+ "amount = "+amountall+" "
						+ ",amount_free = "+amountfreeall+" "
						+ "WHERE id = "+treatMedicine.getTreatPatMedicine_id()+"";
			}
		
				

				
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

					
			if (!pStmt.isClosed())
				pStmt.close();
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
	public void insertMedicineAfterAddtreatpatline(TreatmentModel treatModel){
		
		String SQL ="INSERT INTO treatment_patient_medicine "
						+ "(product_id,treatment_patient_id,amount,amount_free) "
						+ "VALUES "
						+ "("+treatModel.getTreatPatMedicine_ProID()+","
						+ "'"+treatModel.getTreatment_patient_ID()+"',"
						+ ""+treatModel.getTreatPatMedicine_amount()+", "
						+ "0) ";
		
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

					
			if (!pStmt.isClosed())
				pStmt.close();
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
	public void updateMedicineAfterAddtreatpatline(TreatmentModel treatModel){
		
		String SQL ="UPDATE  treatment_patient_medicine "
						+ "SET "
						+ "amount ="+treatModel.getTreatPatMedicine_amount()+" "
						+ "WHERE id="+treatModel.getTreatPatMedicine_id();

		
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

					
			if (!pStmt.isClosed())
				pStmt.close();
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
	public void deleteMedicineAfterAddtreatpatline(TreatmentModel treatModel){
		
		String SQL ="DELETE FROM treatment_patient_medicine "
					+ "WHERE id = "+treatModel.getTreatPatMedicine_id();
				
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

					
			if (!pStmt.isClosed())
				pStmt.close();
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
	public TreatmentModel TreatMentPatientMedicineIsNew(String treatpatID,String  proID){
		
		String SQL = "SELECT treatment_patient_medicine.id,treatment_patient_medicine.product_id, "
					+ "treatment_patient_medicine.treatment_patient_id,treatment_patient_medicine.amount, "
					+ "treatment_patient_medicine.amount_free "
					+"FROM	treatment_patient_medicine "
					+ "WHERE treatment_patient_id = '"+treatpatID+"' AND product_id = '"+proID+"' ";
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			ResultSet res = Stmt.executeQuery(SQL);
			TreatmentModel treatmodel = new TreatmentModel();
			while(res.next()){
				
				treatmodel.setTreatPatMedicine_id(res.getString("treatment_patient_medicine.id"));
				treatmodel.setTreatPatMedicine_amount(res.getInt("treatment_patient_medicine.amount"));
				treatmodel.setTreatPatMedicine_amountfree(res.getInt("treatment_patient_medicine.amount_free"));
				return treatmodel;
			}
			if (!res.isClosed())
				res.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}	
	
	public void deleteTreatMentPatMedicine(TreatmentModel treatModel,String treatpatid){
		
		String SQL ="DELETE FROM treatment_patient_medicine "
				+ "where product_id = '"+treatModel.getPro_id()+"' "
				+ "AND treatment_patient_id = '"+treatpatid+"' ";		
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

					
			if (!pStmt.isClosed())
				pStmt.close();
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
	public void deleteTreatMentPatline(TreatmentModel treatModel){
		
		String SQL ="DELETE FROM treatment_patient_line "
				+ "where treatment_id = '"+treatModel.getTreatment_ID()+"' "
				+ "AND treatment_patient_id = '"+treatModel.getTreatment_patient_ID()+"' ";		
		
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

					
			if (!pStmt.isClosed())
				pStmt.close();
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
	
	public List<TreatmentModel>  getTreatmentLine(String treatpatID) throws Exception{
		
		String SQL = "SELECT "
				+ "treatment_master.`code`,treatment_master.nameth,treatment_patient_line.treatment_price, "
				+ "treatment_patient_line.id,pre_name.pre_name_th,doctor.first_name_th,doctor.last_name_th,treatment_patient_line.treatment_patient_id "
				+ ",treatment_master.is_continue,treatment_patient_line.treatment_id,treatment_patient_line.treatment_plandetail_id "
				+ "FROM treatment_patient_line "
				+ "INNER JOIN treatment_master ON treatment_patient_line.treatment_id = treatment_master.id "
				+ "INNER JOIN treatment_patient ON treatment_patient.id = treatment_patient_line.treatment_patient_id "
				+ "INNER JOIN doctor ON treatment_patient.doctor_id = doctor.doctor_id "
				+ "INNER JOIN pre_name ON pre_name.pre_name_id = doctor.pre_name_id "
				+ "WHERE treatment_patient_line.treatment_patient_id = '"+treatpatID+"' ";

		List<TreatmentModel> treatList = new ArrayList<TreatmentModel>(); 
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					TreatmentModel treatModel = new TreatmentModel();
					treatModel.setTreatpatLine_id(rs.getInt("treatment_patient_line.id"));
					treatModel.setTreatmentplandetailid(rs.getString("treatment_patient_line.treatment_plandetail_id"));
					treatModel.setTreatment_patient_id(rs.getInt("treatment_patient_line.treatment_patient_id"));
					treatModel.setTreatment_price(rs.getDouble("treatment_patient_line.treatment_price"));
					treatModel.setTreatment_ID(rs.getString("treatment_patient_line.treatment_id"));
					treatModel.setPreName(rs.getString("pre_name.pre_name_th"));
					treatModel.setFirstNameTH(rs.getString("doctor.first_name_th"));
					treatModel.setLastNameTH(rs.getString("doctor.last_name_th"));
					treatModel.setTreatMent_code(rs.getString("treatment_master.code"));
					treatModel.setTreatMent_name(rs.getString("treatment_master.nameth"));
					treatModel.setTreat_line_iscon(rs.getString("treatment_master.is_continue"));
					treatList.add(treatModel);
				}
				return treatList;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}
	public boolean TreatMentPatientLineCheck(TreatmentModel treatModel){
		
		String SQL = "SELECT treatment_patient_line.treatment_id,treatment_patient_line.treatment_patient_id "
						+"FROM	treatment_patient_line "
						+ "INNER JOIN treatment_master ON treatment_patient_line.treatment_id = treatment_master.id AND treatment_master.is_repeat = '1' "
						+ "WHERE treatment_patient_line.treatment_patient_id = '"+treatModel.getTreatment_patient_ID()+"' AND  "
						+ "treatment_patient_line.treatment_id ='"+treatModel.getTreatment_ID()+"' ";
						if(!treatModel.getTooth().equals("") || !treatModel.getSurface().equals("") ||
						  !treatModel.getSurface_tooth().equals("") || !treatModel.getToothRange().equals("") || 
						  treatModel.getQuadrant()!=null || treatModel.getArch()!=null){
							if(!treatModel.getSurface().equals("")){
								/*String [] surfcheck = treatModel.getSurface().split(",");
								int checksize =0;
								SQL += "AND treatment_patient_line.surf in ( ";
								for(String surfall : surfcheck){
									if(checksize >1){
										SQL +=",";	
									}
									SQL +="'"+surfall+"'";
									checksize++;
								}
								SQL += ")";	*/
								SQL += "AND  treatment_patient_line.surf = '"+treatModel.getSurface()+"' ";
							}
							if(!treatModel.getTooth().equals("")){
								SQL += "AND  treatment_patient_line.tooth = '"+treatModel.getTooth()+"' ";
							}else if(!treatModel.getSurface_tooth().equals("")){
								SQL += "AND  treatment_patient_line.tooth = '"+treatModel.getSurface_tooth()+"' ";
							}else if(treatModel.getQuadrant()!=null){
								SQL += "AND treatment_patient_line.tooth = '"+treatModel.getQuadrant()+"' ";
							}else if(treatModel.getArch()!=null){
								SQL += "AND treatment_patient_line.tooth = '"+treatModel.getArch()+"' ";
							}
						}else{
							SQL += "AND  treatment_patient_line.tooth = '' ";
						}
							
		boolean newTreatpatline = true;
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			ResultSet res = Stmt.executeQuery(SQL);
			
			while(res.next()){
				newTreatpatline = false;
			}
			if (!res.isClosed())
				res.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			return newTreatpatline;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return newTreatpatline;
	}
	public List<TreatmentModel>  getTreatPatMedicineList(String treatmentID,String treatpatid) throws Exception{
		
		String SQL = "SELECT "
				+ "treatment_product.treatment_id,treatment_product.id, "
				+ "treatment_product.product_id,treatment_product.amount,treatment_product.amount_free,IFNULL(patient_beallergic.product_id,'nu') "
				+ "FROM treatment_product "
				+ "LEFT  JOIN patient_beallergic ON treatment_product.product_id = patient_beallergic.product_id "
				+ "AND patient_beallergic.hn =(SELECT patient_hn FROM treatment_patient WHERE treatment_patient.id = '"+treatpatid+"') "
				+ "WHERE treatment_id = '"+treatmentID+"' ";

		List<TreatmentModel> treatList = new ArrayList<TreatmentModel>(); 
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					TreatmentModel treatModel = new TreatmentModel();
					treatModel.setTreatPro_id(rs.getString("treatment_product.treatment_id"));
					treatModel.setPro_id(rs.getString("treatment_product.product_id"));
					treatModel.setTreatPro_amount(rs.getInt("treatment_product.amount"));
					treatModel.setTreatPro_amountfree(rs.getInt("treatment_product.amount_free"));
					treatList.add(treatModel);
				}
				return treatList;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}
	public List<TreatmentModel>  getTreatmentpatMedicine(String treatpatID) throws Exception{
		
		String SQL = "SELECT "
				+ "IFNULL(treatment_patient_medicine.product_id,'nu') AS checkall, "
				+ "treatment_patient_medicine.id,pro_product.product_id,treatment_patient_medicine.treatment_patient_id, "
				+ "treatment_patient_medicine.amount,treatment_patient_medicine.amount_free,pro_product.product_name, "
				+ "pro_productunit.productunit_name "
				+ "FROM pro_product "
				+ "LEFT JOIN treatment_patient_medicine ON pro_product.product_id = treatment_patient_medicine.product_id "
				+ "AND treatment_patient_medicine.treatment_patient_id = '"+treatpatID+"' "
				+ "INNER JOIN pro_productunit ON pro_product.productunit_id = pro_productunit.productunit_id "
				+ "WHERE pro_product.product_id != 1 AND pro_product.producttype_id = '0001' ";

		List<TreatmentModel> treatList = new ArrayList<TreatmentModel>(); 
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					TreatmentModel treatModel = new TreatmentModel();
					treatModel.setTreatPatMedicine_id(rs.getString("id"));
					treatModel.setTreatPatMedicine_ProID(rs.getString("pro_product.product_id"));
					treatModel.setTreatPatMedicine_amount(rs.getInt("amount"));
					treatModel.setTreatPatMedicine_amountfree(rs.getInt("amount_free"));
					treatModel.setTreatPro_name(rs.getString("pro_product.product_name"));
					treatModel.setProunitname(rs.getString("pro_productunit.productunit_name"));
					treatModel.setIsCheck(rs.getString("checkall"));
					treatList.add(treatModel);
				}
				return treatList;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}
	public void chengstatustreatmentplandetail(TreatmentModel treatModel,int statu){
		
		String SQL ="UPDATE treatment_plandetail "
				+ "SET detail_status ="+statu+" "
				+ "WHERE id = '"+treatModel.getTreatmentplandetailid()+"' ";

		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

					
			if (!pStmt.isClosed())
				pStmt.close();
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
	
	
}
