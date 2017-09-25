package com.smict.promotion.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.smict.promotion.model.Promotion_sub_contactModel;

import ldc.util.DBConnect;

public class Promotion_sub_contactdata{
	
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	
	public List<Promotion_sub_contactModel> getListPromotion_sub_contact(){

			String sql = "SELECT "
					+ "sub_contact_name, sms_piority, sub_contact_id, contact_id "
					+ "FROM "
					+ "promotion_sub_contact ";
					
			List<Promotion_sub_contactModel> promotionList = new LinkedList<Promotion_sub_contactModel>();
			try 
			{
				conn = agent.getConnectMYSql();
				Stmt = conn.createStatement();
				rs = Stmt.executeQuery(sql);
				
				while (rs.next()) {
					Promotion_sub_contactModel promotionModel = new Promotion_sub_contactModel();
					
					promotionModel.setSub_contact_name(rs.getString("sub_contact_name"));
					promotionModel.setSms_piority(rs.getString("sms_piority"));
					promotionModel.setSub_contact_id(rs.getInt("sub_contact_id"));
					promotionModel.setContact_id(rs.getInt("contact_id"));
	
					
					promotionList.add(promotionModel);
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
			
			return promotionList;
		}
	
	
	public Map<String,String> Get_sub_contactList() throws IOException, Exception {
		String sqlQuery = "select * from promotion_sub_contact";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		Map <String,String>ResultList = new HashMap<String,String>();
		
		while (rs.next()) {
			// vender_id,vender_name,create_by,create_datetime,update_by,update_datetime
			ResultList.put(rs.getString("sub_contact_id"), rs.getString("sub_contact_name"));
					
		}

		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return ResultList;
	}
	

}
