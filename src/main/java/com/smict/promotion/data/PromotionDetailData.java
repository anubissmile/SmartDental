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
	
	public boolean addpromotiondetailinsert(PromotionDetailModel protiondetailModel) throws IOException, Exception{
		
		String SQL = "INSERT INTO promotion_detail(name,discount_baht,discount_percent,type,product_type,product_id,promotion_id) VALUES "
					+ "('"+protiondetailModel.getName()
					+"',"+protiondetailModel.getDiscount_baht()
					+","+protiondetailModel.getDiscount_percent()
					+",'"+protiondetailModel.getType()
					+"','"+protiondetailModel.getProduct_type()
					+"','"+protiondetailModel.getProduct_id()
					+"',"+protiondetailModel.getPromotion_id()
					+")";
			System.out.println(SQL);
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			int sStmt = pStmt.executeUpdate();
			
			
			if(sStmt>0){
				return true;
			}
		
				return false;
		
		}
	
	public List<PromotionDetailModel> getListPromotionDetail(String idpro){
		
		String sql = "SELECT "
				+ "promotion_detail.id, promotion_detail.`name`, treatment_master.treatment_nameth, "
				+ "CASE promotion_detail.type WHEN '3' THEN 'การรักษา' END AS type, "
				+ "promotion_detail.product_type, "
				+ "promotion_detail.discount_baht, "
				+ "promotion_detail.discount_percent "
				+ "FROM "
				+ "promotion_detail "
				+ "INNER JOIN treatment_master ON treatment_master.treatment_code = promotion_detail.product_id "
				+ "WHERE promotion_id ="+idpro
				
				+ " union ALL "
				
				+"SELECT "
				+ "promotion_detail.id, promotion_detail.`name`, pro_product.product_name, "
				+ "pro_producttype.producttype_name,promotion_detail.product_type, promotion_detail.discount_baht, "
				+ "promotion_detail.discount_percent "
				+ "FROM "
				+ "promotion_detail "
				+ "INNER JOIN pro_product ON promotion_detail.product_id = pro_product.product_id "
				+ "INNER JOIN pro_producttype ON pro_product.producttype_id = pro_producttype.producttype_Id "
				+ "WHERE promotion_id ="+idpro
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
			//	promotiondetailModel.setPname(rs.getString("product_name"));
			//	promotiondetailModel.setType(rs.getString("producttype_name"));
				promotiondetailModel.setProduct_type(rs.getString("treatment_nameth"));
				promotiondetailModel.setType(rs.getString("type"));
				promotiondetailModel.setTname(rs.getString("product_type"));
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
				promotiondetailModel.setProduct_type(rs.getString("treatment_nameth"));
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
	
	
	
	
	
	
	public boolean PromotionDetailDelete(PromotionDetailModel protiondetailModel) throws IOException, Exception{
		
		String SQL = "DELETE FROM promotion_detail  "
				+ " where id = '"+protiondetailModel.getId()+"'";
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			int sStmt = pStmt.executeUpdate();
			
			
			if(sStmt>0){
				return true;
			}
		
				return false;
		
		}
	

	public PromotionModel getNameDetail(String id){
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
				returnPromotionModel.setName(rs.getString("name"));
				returnPromotionModel.setStart_date(rs.getString("start_date"));
				returnPromotionModel.setEnd_date(rs.getString("end_date"));
			
			}
			
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
	
	
	public JSONArray getJsonArrayTreatment(String TreatmentName){
		Validate classValidatev= new Validate();
		String sql ="SELECT * FROM treatment_master where ";
		
		if(classValidatev.Check_String_notnull_notempty(TreatmentName)) sql += "treatment_nameth LIKE '%"+TreatmentName+"%' and ";
		
		sql += "treatment_nameth != '' order by treatment_code";
		
		System.out.println(sql);
		
		JSONArray jsonArray = new JSONArray();
		try {
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject();
				jsonOBJ.put("id", rs.getString("treatment_code"));
				jsonOBJ.put("text", rs.getString("treatment_nameth"));
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
				promotiondetailModel.setProduct_type(rs.getString("product_name"));
				promotiondetailModel.setType(rs.getString("producttype_name"));
				promotiondetailModel.setDiscount_baht(rs.getDouble("discount_baht"));
				promotiondetailModel.setDiscount_percent(rs.getDouble("discount_percent"));
				promotiondetailModel.setDiscount_percent(rs.getDouble("discount_percent"));
				
				promotiondetailList.add(promotiondetailModel);
				
				
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnPromotionDetailModel;
		
		
	}
	
	

}



