<%@page import="com.smict.product.action.SendLabAction"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONValue"%>
<%
	List listjson = new LinkedList();
	DBConnect dbcon = new DBConnect();
	ResultSet rs = null; 
	 
	String doctor_id = request.getParameter("doctor_id"); 
	String userHN = request.getParameter("hn"); 
	
	String sql = "";
	Connection conn = null;
	Statement Stmt = null;
 
	sql = "SELECT "
			+"dwd.workday_id, "
			+"dwd.doctor_id, "
			+"SUBSTRING(dwd.start_datetime, 1, 10)as start_datetime, "
			+"dwd.end_datetime, "
			+"dwd.work_hour, "
			+"dwd.branch_id, "
			+"dwd.branch_room_id, "
			+"dwd.checkin_status, "
			+"dwd.checkin_datetime, "
			+"dwd.checkout_datetime "
			+"FROM "
			+"doctor_workday dwd where ";
			
	if(!doctor_id.equals("")) sql+= "dwd.doctor_id  = '"+doctor_id+"' and "; 
	
	sql += "dwd.doctor_id != '' order by dwd.start_datetime DESC LIMIT 0, 5 ";
		  
	conn = dbcon.getConnectMYSql();
	Stmt = conn.createStatement();
	rs = Stmt.executeQuery(sql); 
	
	String start_datetime = "";
	
	while(rs.next()){  
		
		JSONObject obj=new JSONObject();
		
		obj.put("workday_id", rs.getString("workday_id"));  
		obj.put("start_datetime", rs.getString("start_datetime"));
		
		listjson.add(obj);
			
	} 
	
	SendLabAction sla = new SendLabAction();
	sla.makePatientSession(userHN);

	out.println(listjson);  
	//System.out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>