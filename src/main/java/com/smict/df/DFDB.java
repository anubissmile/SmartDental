package com.smict.df;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import ldc.util.DBConnect;
import ldc.util.SmartSetParameter;

public class DFDB {
		
	DBConnect agent = new DBConnect();
	Connection conn;
	Statement Stmt;
	ResultSet rs;
	SmartSetParameter param = new SmartSetParameter();
	 
	public JSONArray getJsonArrayListDortor(String doctor_id){

		String sql ="SELECT dt.* "
				+ "FROM "
				+ "doctor dt " 
				+ "where dt.doctor_id is not NULL "; 
		
			sql +="and concat(dt.doctor_id,dt.first_name_th,dt.last_name_th,dt.first_name_en,dt.last_name_en) like '%"+doctor_id+"%'";
		
		System.out.println(sql);
		JSONArray jsonArray = new JSONArray();
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject(); 
				jsonOBJ.put("id", rs.getString("doctor_id"));
				jsonOBJ.put("text", rs.getString("first_name_th")+" "+rs.getString("last_name_th"));
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return jsonArray;
	}
	public JSONArray getJsonArrayListBranch(String doctor_id){
		 
		String sql ="SELECT * "
				+ "FROM "
				+ "branch_standard_rel_doctor br_dt inner join branch br on(br.branch_id = br_dt.branch_id) " 
				+ "where br_dt.doctor_id is not NULL "; 
		
			sql +="and br_dt.doctor_id = '"+doctor_id+"'";
		
		System.out.println(sql);
		JSONArray jsonArray = new JSONArray();
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject(); 
				jsonOBJ.put("id", rs.getString("branch_id"));
				jsonOBJ.put("text", rs.getString("branch_name"));
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return jsonArray;
	}
	public JSONArray getTreatmentList(String doctor_id, String branch_id){
		 
		StringBuilder sql = new StringBuilder("SELECT tm.id, tm.code,tm.nameth, "
				+ "IFNULL(dt_pil.df_percent,0)as df_percent,IFNULL(dt_pil.df_baht,0)as df_baht,IFNULL(dt_pil.price_lab,0)as price_lab "
				+ ",category_id,group_id "
				+ "FROM doctor_treatment dt_tm "
				+ "left join doctor_pricelist dt_pil on(dt_pil.doctor_id = dt_tm.doctor_id and dt_tm.treatment_id = dt_pil.treatment_id "
					+ "and dt_pil.branch_id = '"+branch_id+"') "
				+ "inner join treatment_master tm on(tm.id = dt_tm.treatment_id) "
				+ "left join treatment_category tm_cat on(tm_cat.id = tm.category_id) "
				+ "left join treatment_group tm_grp on(tm_grp.id = tm_cat.group_id) "
				+ "where dt_tm.doctor_id is not NULL ");
		sql.append("and dt_tm.doctor_id = '").append(doctor_id).append("'");
		if(branch_id.equals("")) sql.append("and dt_tm.doctor_id is NULL");
		sql.append("order by tm.id ");
			
		System.out.println(sql);
		JSONArray jsonArray = new JSONArray();
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql.toString());
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject(); 
				jsonOBJ.put("id", rs.getString("id"));
				jsonOBJ.put("code", rs.getString("code"));
				jsonOBJ.put("name", rs.getString("nameth"));
				jsonOBJ.put("df_percent", rs.getString("df_percent"));
				jsonOBJ.put("df_baht", rs.getString("df_baht"));
				jsonOBJ.put("price_lab", rs.getString("price_lab"));
				jsonOBJ.put("category_id", rs.getString("category_id"));
				jsonOBJ.put("group_id", rs.getString("group_id")); 
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return jsonArray;
	}
public JSONArray getCategoryList(String treatment_code){
	 
	StringBuilder sql = new StringBuilder("SELECT tm_cat.id,tm_cat.code,tm_cat.name,tm_cat.group_id FROM treatment_master tm_mas "
			+ "inner join treatment_category tm_cat on(tm_cat.id = tm_mas.category_id) "  
			+ "where tm_cat.id is not NULL ");
	sql.append("and tm_mas.code in ")
		.append(treatment_code)
		.append(" GROUP BY tm_cat.id "); 
		
	System.out.println(sql);
	JSONArray jsonArray = new JSONArray();
	try {
		Connection conn = agent.getConnectMYSql();
		Statement stmt = conn.createStatement();
		ResultSet rs =  stmt.executeQuery(sql.toString());
		while(rs.next()){
			JSONObject jsonOBJ = new JSONObject(); 
			jsonOBJ.put("cat_id", rs.getString("id"));
			jsonOBJ.put("cat_name", rs.getString("code")+"-"+rs.getString("name"));
			jsonOBJ.put("group_id", rs.getString("group_id")); 
			jsonArray.put(jsonOBJ);
		}
		
		if(!rs.isClosed()) rs.close();
		if(!stmt.isClosed()) stmt.close();
		if(!conn.isClosed()) conn.close();				
	} catch (IOException e) {

		e.printStackTrace();
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return jsonArray;
}public JSONArray getGroupList(String group_id){
	 
	StringBuilder sql = new StringBuilder("SELECT tm_grp.id,tm_grp.code,tm_grp.name "
			+ "FROM treatment_group tm_grp "  
			+ "where tm_grp.id is not NULL ");
	sql.append("and tm_grp.id in ")
		.append(group_id)
		.append(" GROUP BY tm_grp.id "); 
		 
	JSONArray jsonArray = new JSONArray();
	try {
		Connection conn = agent.getConnectMYSql();
		Statement stmt = conn.createStatement();
		ResultSet rs =  stmt.executeQuery(sql.toString());
		while(rs.next()){
			JSONObject jsonOBJ = new JSONObject(); 
			jsonOBJ.put("grp_id", rs.getString("id"));
			jsonOBJ.put("grp_name", rs.getString("code")+"-"+rs.getString("name")); 
			jsonArray.put(jsonOBJ);
		}
		
		if(!rs.isClosed()) rs.close();
		if(!stmt.isClosed()) stmt.close();
		if(!conn.isClosed()) conn.close();				
	} catch (IOException e) {

		e.printStackTrace();
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return jsonArray;
}
public void AddTreatmentDoctorBranch(String treatment_id, String doctor_id, String branch_id
		, String df_percent, String df_baht, String price_lab){
	
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	
	String sql = "INSERT INTO doctor_pricelist "
			+ "(treatment_id, doctor_id, branch_id, df_percent, df_baht, price_lab) "
			+ "VALUES ('"+treatment_id+"','"+doctor_id+"','"+branch_id+"'"
					+ ",'"+df_percent.replace(",", "")+"','"+df_baht.replace(",", "")+"','"+price_lab.replace(",", "")+"')" ;
	
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
public boolean hasExistsTreatmentBranchDoctor(String treatment_id, String doctor_id, String branch_id){
	boolean check = false;
	StringBuilder sql = new StringBuilder("SELECT id "
			+ "FROM doctor_pricelist "  
			+ "where treatment_id = '"+treatment_id+"' and doctor_id = '"+doctor_id+"' and branch_id = '"+branch_id+"' "); 
		 
	try {
		Connection conn = agent.getConnectMYSql();
		Statement stmt = conn.createStatement();
		ResultSet rs =  stmt.executeQuery(sql.toString());
		while(rs.next()){
			check = true;
		}
		
		if(!rs.isClosed()) rs.close();
		if(!stmt.isClosed()) stmt.close();
		if(!conn.isClosed()) conn.close();				
	} catch (IOException e) {

		e.printStackTrace();
	} catch (Exception e) {

		e.printStackTrace();
	}
	
	return check;
}
public boolean hasUpdatedTreatmentDoctorBranch(String treatment_id, String doctor_id, String branch_id
		, String df_percent, String df_baht, String price_lab) throws Exception{
	String sql = "UPDATE doctor_pricelist "
				+ "set df_percent = IFNULL("+param.buildStringParam(df_percent)+",df_percent), "
				+ "df_baht = IFNULL("+param.buildStringParam(df_baht)+",df_baht), "
				+ "price_lab = IFNULL("+param.buildStringParam(price_lab)+",price_lab) "
				+ "where treatment_id = '"+treatment_id+"' and doctor_id = '"+treatment_id+"' and branch_id = '"+branch_id+"' ";
	
	conn = agent.getConnectMYSql();
	Stmt = conn.createStatement();
	Stmt.executeUpdate(sql);
	//boolean hasCreated = Stmt.executeUpdate(sql) > 0 ? true : false;
	boolean hasCreated = false;
	Stmt.close();
	conn.close(); 
	
	return hasCreated;
	}
	public JSONArray getCategoryListcheck(String groupid){
		 
		StringBuilder sql = new StringBuilder("SELECT tm_cat.id,tm_cat.code,tm_cat.name,tm_cat.group_id "
				+ "FROM treatment_category AS tm_cat "  
				+ "WHERE tm_cat.id is not NULL and tm_cat.group_id = '"+groupid+"' ");


		JSONArray jsonArray = new JSONArray();
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql.toString());
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject(); 
				jsonOBJ.put("cat_id", rs.getString("id")); 
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {
	
			e.printStackTrace();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	public JSONArray gettreatmentListcheckbyGroup(String groupid){
		 
		StringBuilder sql = new StringBuilder("SELECT treatment_category.group_id,treatment_master.id "
				+ "FROM treatment_master "
				+ "INNER JOIN treatment_category ON treatment_master.category_id = treatment_category.id "  
				+ "WHERE treatment_category.group_id = "+groupid+" ");


		JSONArray jsonArray = new JSONArray();
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql.toString());
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject(); 
				jsonOBJ.put("treat", rs.getString("treatment_master.id")); 
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {
	
			e.printStackTrace();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	public JSONArray getcontactlist(String contact_id){
		 
		StringBuilder sql = new StringBuilder("SELECT promotion_sub_contact.sub_contact_id,promotion_sub_contact.sub_contact_name, "
				+ "promotion_sub_contact.contact_id,promotion_sub_contact.sms_piority, "
				+ "promotion_sub_contact.sub_contact_type_id,promotion_sub_contact.address, "
				+ "promotion_sub_contact.amount_default,promotion_sub_contact.company_name,promotion_sub_contact.`status` "
				+ "FROM promotion_sub_contact "
				+ "WHERE promotion_sub_contact.sub_contact_id = "+contact_id+" ");


		JSONArray jsonArray = new JSONArray();
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql.toString());
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject(); 
				jsonOBJ.put("contypeID", rs.getString("promotion_sub_contact.sub_contact_type_id"));
				jsonOBJ.put("amountdefault", rs.getString("promotion_sub_contact.amount_default")); 
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {
	
			e.printStackTrace();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	public JSONArray getPromotionday(String proid){
		 
		StringBuilder sql = new StringBuilder("SELECT promotion_condition_day.id,promotion_condition_day.day_id,promotion_condition_day.promotion_id "
				+ "FROM promotion_condition_day "  
				+ "WHERE promotion_condition_day.promotion_id = '"+proid+"' ");


		JSONArray jsonArray = new JSONArray();
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql.toString());
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject(); 
				jsonOBJ.put("day_id", rs.getString("day_id")); 
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {
	
			e.printStackTrace();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	public JSONArray getPromotionContact(String promoid){
		 
		StringBuilder sql = new StringBuilder("SELECT promotion_condition_subcontact.id,promotion_condition_subcontact.sub_contact_id"
				+ ",promotion_sub_contact.sub_contact_name "
				+ "FROM promotion_condition_subcontact "
				+ "INNER JOIN promotion_sub_contact ON promotion_condition_subcontact.sub_contact_id = promotion_sub_contact.sub_contact_id "  
				+ "WHERE promotion_condition_subcontact.promotion_id = '"+promoid+"' ");


		JSONArray jsonArray = new JSONArray();
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql.toString());
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject(); 
				jsonOBJ.put("pro_sub_id", rs.getString("promotion_condition_subcontact.sub_contact_id")); 
				jsonOBJ.put("sub_name", rs.getString("promotion_sub_contact.sub_contact_name"));
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {
	
			e.printStackTrace();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	public JSONArray getPromotionContactLine(String promoid){
		 
		StringBuilder sql = new StringBuilder("SELECT promotion_points_line.points,promotion_points_line.contact_id,"
				+ "promotion_sub_contact.sub_contact_name "
				+ "FROM promotion_points "
				+ "INNER JOIN promotion_points_line ON promotion_points.id = promotion_points_line.points_id "
				+ "INNER JOIN promotion_sub_contact ON promotion_points_line.contact_id = promotion_sub_contact.sub_contact_id "  
				+ "WHERE promotion_points.promotion_id = '"+promoid+"' ");


		JSONArray jsonArray = new JSONArray();
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql.toString());
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject(); 
				jsonOBJ.put("pro_sub_id", rs.getString("promotion_points_line.contact_id")); 
				jsonOBJ.put("sub_name", rs.getString("promotion_sub_contact.sub_contact_name"));
				jsonOBJ.put("points", rs.getString("promotion_points_line.points"));
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {
	
			e.printStackTrace();
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	
}
