<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONValue"%>
<%
	List listjson = new LinkedList();
	JSONObject reObj=new JSONObject();
	DBConnect dbcon = new DBConnect();
	ResultSet rs = null; 
	
	Connection conn = null;
	Statement Stmt = null;

		String sql = "SELECT d.doctor_id,d.first_name_th,d.last_name_th,d.first_name_en,d.last_name_en,d.nickname,d.emp_id "
				+"FROM doctor AS d ";
		  
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
		
			obj.put("doc_id",  rs.getString("doctor_id"));  
			obj.put("name_th", rs.getString("first_name_th")+" "+rs.getString("first_name_th")); 
			obj.put("name_en", rs.getString("first_name_en")+" "+rs.getString("first_name_en")); 
			obj.put("nick", rs.getString("nickname")); 
			
			listjson.add(obj);
			
			
		}	
		reObj.put("data",listjson);
		out.println(reObj); 	
	rs.close();
	Stmt.close(); 
	conn.close();
		
%>