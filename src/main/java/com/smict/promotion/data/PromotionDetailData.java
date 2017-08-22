package com.smict.promotion.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.smict.product.model.ProductModel;
import com.smict.promotion.model.PromotionDetailModel;
import com.smict.promotion.model.PromotionModel;

import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class PromotionDetailData {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();	
	
	public void addpromotiondetailinsert(PromotionDetailModel protiondetailModel) throws IOException, Exception{
		
		String SQL = "INSERT INTO promotion_detail(name,discount_amount,discount_type,product_type,product_id,"
				+ "treatment_id,treatment_type,promotion_id) VALUES "
					+ "('"+protiondetailModel.getName()
					+"',"+protiondetailModel.getDiscount_amount()
					+","+protiondetailModel.getDiscount_type()
					+",'"+protiondetailModel.getProduct_type()
					+"','"+protiondetailModel.getProduct_id()
					+"','"+protiondetailModel.getPro_treatmentID()
					+"','"+protiondetailModel.getPro_treatmentType()
					+"',"+protiondetailModel.getPromotion_id()
					+")";
			
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		
		}
	
	public List<PromotionDetailModel> getListPromotionDetail(int idpro){
		
		String sql = "SELECT "
				+ "promotion_detail.id,promotion_detail.`name`,promotion_detail.discount_amount, "
				+ "promotion_detail.discount_type,promotion_detail.product_type, "
				+ "promotion_detail.product_id,promotion_detail.treatment_id, "
				+ "promotion_detail.treatment_type,promotion_detail.promotion_id, "
				+ "pro_product.product_name AS allname "
				+ "FROM "
				+ "promotion_detail "
				+ "INNER JOIN pro_product ON promotion_detail.product_id = pro_product.product_id "
				+ "WHERE promotion_detail.promotion_id ="+idpro
				
				+ " UNION ALL "
				
				+"SELECT "
				+ "promotion_detail.id,promotion_detail.`name`,promotion_detail.discount_amount, "
				+ "promotion_detail.discount_type,promotion_detail.product_type, "
				+ "promotion_detail.product_id,promotion_detail.treatment_id, "
				+ "promotion_detail.treatment_type,promotion_detail.promotion_id, "
				+ "treatment_master.nameth "
				+ "FROM "
				+ "promotion_detail "
				+ "INNER JOIN treatment_master ON treatment_master.id = promotion_detail.treatment_id "
				+ "WHERE promotion_detail.treatment_type = 4 AND promotion_detail.promotion_id ="+idpro
				
				+ " UNION ALL "
				
				+"SELECT "
				+ "promotion_detail.id,promotion_detail.`name`,promotion_detail.discount_amount, "
				+ "promotion_detail.discount_type,promotion_detail.product_type, "
				+ "promotion_detail.product_id,promotion_detail.treatment_id, "
				+ "promotion_detail.treatment_type,promotion_detail.promotion_id, "
				+ "treatment_category.`name` "
				+ "FROM "
				+ "promotion_detail "
				+ "INNER JOIN treatment_category ON promotion_detail.treatment_id = treatment_category.id "
				+ "WHERE promotion_detail.treatment_type = 3 AND  promotion_detail.promotion_id ="+idpro
				
				+ " UNION ALL "
				
				+"SELECT "
				+ "promotion_detail.id,promotion_detail.`name`,promotion_detail.discount_amount, "
				+ "promotion_detail.discount_type,promotion_detail.product_type, "
				+ "promotion_detail.product_id,promotion_detail.treatment_id, "
				+ "promotion_detail.treatment_type,promotion_detail.promotion_id, "
				+ "treatment_group.`code` "
				+ "FROM "
				+ "promotion_detail "
				+ "INNER JOIN treatment_group ON promotion_detail.treatment_id = treatment_group.id "
				+ "WHERE promotion_detail.treatment_type = 2 AND promotion_detail.promotion_id ="+idpro
				+ " UNION ALL "
				
				+"SELECT "
				+ "promotion_detail.id,promotion_detail.`name`,promotion_detail.discount_amount, "
				+ "promotion_detail.discount_type,promotion_detail.product_type, "
				+ "promotion_detail.product_id,promotion_detail.treatment_id, "
				+ "promotion_detail.treatment_type,promotion_detail.promotion_id, "
				+ "IFNULL('-','-') "
				+ "FROM "
				+ "promotion_detail "
				+ "WHERE promotion_detail.treatment_type = 1 AND promotion_detail.promotion_id ="+idpro;
		
		List<PromotionDetailModel> promotiondetailList = new LinkedList<PromotionDetailModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				PromotionDetailModel promotiondetailModel = new PromotionDetailModel();
				
				promotiondetailModel.setId(rs.getInt("id"));
				promotiondetailModel.setName(rs.getString("name"));
				promotiondetailModel.setProduct_type(rs.getInt("product_type"));
				promotiondetailModel.setDiscount_type(rs.getInt("discount_type"));
				promotiondetailModel.setTname(rs.getString("allname"));
				promotiondetailModel.setDiscount_amount(rs.getDouble("discount_amount"));
				promotiondetailModel.setPro_treatmentType(rs.getInt("treatment_type"));
				promotiondetailModel.setPro_treatmentID(rs.getInt("treatment_id"));
				
				
				promotiondetailList.add(promotiondetailModel);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return promotiondetailList;
		}
	
	
public List<PromotionDetailModel> getListPromotionDetail2(String idpro1){
		
		String sql = "SELECT "
				+ "promotion_detail.id, promotion_detail.`name`, treatment_master.treatment_nameth, "
				+ "CASE promotion_detail.type WHEN '3' THEN 'การรักษา' END AS type, "
				+ "promotion_detail.discount_baht, "
				+ "promotion_detail.discount_percent "
				+ "FROM "
				+ "promotion_detail "
				+ "INNER JOIN treatment_master ON treatment_master.treatment_code = promotion_detail.product_id "
				+ "WHERE promotion_id ="+idpro1
				;
		
		System.out.println(sql);
		List<PromotionDetailModel> promotiondetailList = new LinkedList<PromotionDetailModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				PromotionDetailModel promotiondetailModel = new PromotionDetailModel();
				
				promotiondetailModel.setId(rs.getInt("id"));
				promotiondetailModel.setName(rs.getString("name"));
				promotiondetailModel.setProduct_type(rs.getInt("treatment_nameth"));
				promotiondetailModel.setType(rs.getString("type"));
				promotiondetailModel.setDiscount_baht(rs.getDouble("discount_baht"));
				promotiondetailModel.setDiscount_percent(rs.getDouble("discount_percent"));
				promotiondetailModel.setDiscount_percent(rs.getDouble("discount_percent"));
				
				promotiondetailList.add(promotiondetailModel);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return promotiondetailList;
		}
	
	
	
	
	
	
	public void PromotionDetailDelete(PromotionDetailModel protiondetailModel) throws IOException, Exception{
		
		String SQL = "DELETE FROM promotion_detail  "
				+ " where id = '"+protiondetailModel.getId()+"'";
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			 pStmt.executeUpdate();
			
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		
		}
	

	public PromotionModel getNameDetail(int id){
		PromotionModel returnPromotionModel = new PromotionModel();
		String SQL="SELECT "
				+ "promotion.id, "
				+ "promotion.`name`, "
				+ "promotion.start_date, "
				+ "promotion.end_date "
				+ "FROM "
				+ "promotion "
				+ "where id="+id;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(SQL);
			
			while(rs.next()){
				returnPromotionModel.setId(rs.getInt("id"));
				returnPromotionModel.setPromotion_id(rs.getInt("id"));
				returnPromotionModel.setName(rs.getString("name"));
				returnPromotionModel.setStart_date(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("start_date"),false));
				returnPromotionModel.setEnd_date(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("end_date"),false));
			
			}
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnPromotionModel;
		
		
	}
	
	public JSONArray getJsonArrayProduct(String productName){
		Validate classValidatev= new Validate();
		String sql ="SELECT * FROM pro_product where ";
		
		if(classValidatev.Check_String_notnull_notempty(productName)) sql += "product_name LIKE '%"+productName+"%' and  ";
		
		sql += "producttype_id LIKE '%0002%' and ";
		
		sql += "product_id != 0 order by producttype_id";
		
		System.out.println(sql);
		
		JSONArray jsonArray = new JSONArray();
		try {
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject();
				jsonOBJ.put("id", rs.getString("product_id"));
				jsonOBJ.put("text", rs.getString("product_name"));
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	
	
	public JSONArray getJsonArrayMedicine(String MedicineName){
		Validate classValidatev= new Validate();
		String sql ="SELECT * FROM pro_product where ";
		
		if(classValidatev.Check_String_notnull_notempty(MedicineName)) sql += "product_name LIKE '%"+MedicineName+"%' and ";
		
		sql += "producttype_id LIKE '%0001%' and ";
		
		sql += "product_id != 0 AND product_id !=1 order by producttype_id";
		
		System.out.println(sql);
		
		JSONArray jsonArray = new JSONArray();
		try {
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject();
				jsonOBJ.put("id", rs.getString("product_id"));
				jsonOBJ.put("text", rs.getString("product_name"));
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	
	public JSONArray getJsonArrayMaterial(String MedicineName){
		Validate classValidatev= new Validate();
		String sql ="SELECT * FROM pro_product where ";
		
		if(classValidatev.Check_String_notnull_notempty(MedicineName)) sql += "product_name LIKE '%"+MedicineName+"%' and ";
		
		sql += "producttype_id LIKE '%0003%' and ";
		
		sql += "product_id != 0 order by producttype_id";
		
		System.out.println(sql);
		
		JSONArray jsonArray = new JSONArray();
		try {
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject();
				jsonOBJ.put("id", rs.getString("product_id"));
				jsonOBJ.put("text", rs.getString("product_name"));
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	
	public JSONArray getJsonArrayTreatment(String TreatmentName ,String treattypeID){
		Validate classValidatev= new Validate();
		String sql="";
		if(treattypeID.equals("2")){
			sql +="SELECT * FROM treatment_group where ";			
			if(classValidatev.Check_String_notnull_notempty(TreatmentName)) sql += "code LIKE '%"+TreatmentName+"%' and ";			
			sql += "code != '' order by code";
		}else if(treattypeID.equals("3")){
			sql +="SELECT * FROM treatment_category where ";			
			if(classValidatev.Check_String_notnull_notempty(TreatmentName)) sql += "name LIKE '%"+TreatmentName+"%' and ";			
			sql += "name != '' order by name";
		}else if(treattypeID.equals("4")){
			sql +="SELECT * FROM treatment_master where ";			
			if(classValidatev.Check_String_notnull_notempty(TreatmentName)) sql += "nameth LIKE '%"+TreatmentName+"%' and ";			
			sql += "nameth != '' order by code";
		}
	
		System.out.println(sql);
		
		JSONArray jsonArray = new JSONArray();
		try {
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject();
				if(treattypeID.equals("2")){
					jsonOBJ.put("id", rs.getString("id"));
					jsonOBJ.put("text", rs.getString("code"));
				}else if(treattypeID.equals("3")){
					jsonOBJ.put("id", rs.getString("id"));
					jsonOBJ.put("text", rs.getString("name"));
				}else if(treattypeID.equals("4")){
					jsonOBJ.put("id", rs.getString("id"));
					jsonOBJ.put("text", rs.getString("nameth"));
				}
				
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonArray;
	}
	
	
	
	public PromotionDetailModel getDetail(String idpro){
		PromotionDetailModel returnPromotionDetailModel = new PromotionDetailModel();
		String SQL = "SELECT "
				+ "promotion_detail.id, promotion_detail.`name`, treatment_master.treatment_nameth, "
				+ "CASE promotion_detail.type WHEN '3' THEN 'การรักษา' END AS type, "
				+ "promotion_detail.discount_baht, "
				+ "promotion_detail.discount_percent "
				+ "FROM "
				+ "promotion "
				+ "INNER JOIN promotion_detail ON promotion.id = promotion_detail.promotion_id "
				+ "INNER JOIN treatment_master ON treatment_master.treatment_code = promotion_detail.product_id "
				+ "WHERE promotion_detail.promotion.id ="+idpro;
				;
		List<PromotionDetailModel> promotiondetailList = new LinkedList<PromotionDetailModel>();
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(SQL);
			
			while(rs.next()){

				PromotionDetailModel promotiondetailModel = new PromotionDetailModel();
				
				promotiondetailModel.setId(rs.getInt("id"));
				promotiondetailModel.setName(rs.getString("name"));
				promotiondetailModel.setProduct_type(rs.getInt("product_name"));
				promotiondetailModel.setType(rs.getString("producttype_name"));
				promotiondetailModel.setDiscount_baht(rs.getDouble("discount_baht"));
				promotiondetailModel.setDiscount_percent(rs.getDouble("discount_percent"));
				promotiondetailModel.setDiscount_percent(rs.getDouble("discount_percent"));
				
				promotiondetailList.add(promotiondetailModel);
				
				
				
			}
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnPromotionDetailModel;
		
		
	}
	
	

}



