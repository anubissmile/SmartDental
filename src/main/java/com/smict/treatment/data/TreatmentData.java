package com.smict.treatment.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.smict.all.model.ServicePatientModel;
import com.smict.person.data.TelephoneData;
import com.smict.person.model.BrandModel;
import com.smict.person.model.PatientModel;
import com.smict.person.model.Person;
import com.smict.person.model.TelephoneModel;
import com.smict.schedule.model.ScheduleModel;
import com.smict.treatment.model.TreatmentModel;

import ldc.util.Auth;
import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;
import net.sf.jasperreports.components.sort.actions.AddSortFieldCommand;

public class TreatmentData
{
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
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
			agent.begin();
			rec = agent.exeUpdate(SQL);
		} catch (Exception e) {
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
		} finally {
			agent.disconnectMySQL();
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
					personModel.setEmp_id(rss.getString("emp_id"));
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

		
}
