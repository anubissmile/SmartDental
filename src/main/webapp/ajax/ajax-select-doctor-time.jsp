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
	
	String branch_id = request.getParameter("branch_id"); 
	String doctor_id = request.getParameter("doctor_id"); 
	Connection conn = null;
	Statement Stmt = null;

		String sql = "SELECT doctor_id,branch_id, TIME_FORMAT(time_in_mon,'%H:%i')as time_in_mon,TIME_FORMAT(time_out_mon,'%H:%i') as time_out_mon,TIME_FORMAT(time_in_tue,'%H:%i')as time_in_tue "
				+",TIME_FORMAT(time_out_tue,'%H:%i')as time_out_tue,TIME_FORMAT(time_in_wed,'%H:%i')as time_in_wed,TIME_FORMAT(time_out_wed,'%H:%i') as time_out_wed,TIME_FORMAT(time_in_thu,'%H:%i') as time_in_thu,TIME_FORMAT(time_out_thu,'%H:%i') as time_out_thu "
				+",TIME_FORMAT(time_in_fri,'%H:%i')as time_in_fri,TIME_FORMAT(time_out_fri,'%H:%i')as time_out_fri,TIME_FORMAT(time_in_sat,'%H:%i') as time_in_sat,TIME_FORMAT(time_out_sat,'%H:%i')as time_out_sat, "
				+"TIME_FORMAT(time_in_sun,'%H:%i')as time_in_sun ,TIME_FORMAT(time_out_sun,'%H:%i')as time_out_sun,DATE_FORMAT(work_month,'%m/%Y')as work_month_new "
				+"  FROM doctor_workday_month  WHERE branch_id ='"+branch_id+"' AND doctor_id="+doctor_id;
		  
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			 
			String d = rs.getString("work_month_new");
			if (d.contains("/")) {
			String[] part = d.split("/");
			int y = Integer.parseInt(part[1])+543;
				d = part[0]+"/"+String.valueOf(y);
			}
			obj.put("work_month", d);  
			obj.put("time_in_mon", rs.getString("time_in_mon")); 
			obj.put("time_out_mon", rs.getString("time_out_mon")); 
			obj.put("time_in_tue", rs.getString("time_in_tue")); 
			obj.put("time_out_tue", rs.getString("time_out_tue")); 
			obj.put("time_in_wed", rs.getString("time_in_wed")); 
			obj.put("time_out_wed", rs.getString("time_out_wed")); 
			obj.put("time_in_thu", rs.getString("time_in_thu")); 
			obj.put("time_out_thu", rs.getString("time_out_thu")); 
			obj.put("time_in_fri", rs.getString("time_in_fri")); 
			obj.put("time_out_fri", rs.getString("time_out_fri")); 
			obj.put("time_in_sat", rs.getString("time_in_sat")); 
			obj.put("time_out_sat", rs.getString("time_out_sat")); 
			obj.put("time_in_sun", rs.getString("time_in_sun")); 
			obj.put("time_out_sun", rs.getString("time_out_sun")); 
			obj.put("doctor_id", rs.getString("doctor_id")); 
			obj.put("branch_id", rs.getString("branch_id")); 
			listjson.add(obj);
				
		}	
	rs.close();
	Stmt.close(); 
	conn.close();
	
	out.println(listjson); 	
%>