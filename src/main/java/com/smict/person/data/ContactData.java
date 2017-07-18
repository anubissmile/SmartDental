package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import ldc.util.DBConnect;
import ldc.util.DateUtil;

public class ContactData {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	public List<JSONObject> getContactnameList(String contact_id,String contact_name){
		String sql = "select * from promotion_contact where ";
		
				if(!contact_id.equals(""))
					sql += "contact_id = "+contact_id+" and ";
				
				if(!contact_name.equals(""))
					sql += "contact_name = "+contact_name+" and ";
				
				sql += "contact_id != ''";
		//System.out.println(sql);
		List contactnameList = new LinkedList<JSONObject>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			
			while (rs.next()) {
				JSONObject jsonobj = new JSONObject();
				
				jsonobj.put("contact_id", rs.getString("contact_id"));
				jsonobj.put("contact_name", rs.getString("contact_name"));
				
				contactnameList.add(jsonobj);
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
		
		return contactnameList;
	}
	
	public List<JSONObject> getSubContactnameList(String contact_id, String contact_name, String sub_contact_id, String sub_contact_name){
		String sql = "SELECT a.contact_id, a.contact_name, b.sub_contact_id, b.sub_contact_name "
				+ "FROM promotion_contact a "
				+ "INNER JOIN promotion_sub_contact b on (a.contact_id = b.contact_id) where b.status = 1 AND ";
				


		
				if(!contact_id.equals(""))
					sql += "a.contact_id = "+contact_id+" and ";
				
				if(!sub_contact_id.equals(""))
					sql += "b.sub_contact_id = "+sub_contact_id+" and ";
				
				if(!contact_name.equals(""))
					sql += "a.contact_name = "+contact_name+" and ";
				
				if(!sub_contact_name.equals(""))
					sql += "b.sub_contact_name = "+sub_contact_name+" and ";
				
				sql += "b.sub_contact_id != ''";
				
		
		List<JSONObject> sub_contactList = new ArrayList<JSONObject>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("contact_id", rs.getString("contact_id"));
				jsonobj.put("contact_name", rs.getString("contact_name"));
				jsonobj.put("sub_contact_id", rs.getString("sub_contact_id"));
				jsonobj.put("sub_contact_name", rs.getString("sub_contact_name"));
				sub_contactList.add(jsonobj);
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
		return sub_contactList;
	}
	
	
}
