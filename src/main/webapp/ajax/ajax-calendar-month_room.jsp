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
	
	String hong = request.getParameter("hong");
	
	String sql = "SELECT a.id,a.start,a.title,a.end,a.color,a.mor,a.customer,a.hong,a.saka "+
			",concat(b.first_name_th,' ',b.last_name_th) as doctorname, concat(c.first_name_th,' ',c.last_name_th) as patientname " +
			"FROM fullcalendar a left join doctor b on(b.doctor_id = a.doctor_id) left join patient c on(c.hn = a.hn) "+
			"WHERE hong = '"+hong+"'";
	Connection conn = dbcon.getConnectMYSql();
	Statement pStmt = conn.createStatement();
	rs = pStmt.executeQuery(sql); 
	 
	while(rs.next()){  
		JSONObject obj=new JSONObject();
			obj.put("id", rs.getString("id"));  
			obj.put("title", rs.getString("title")+"\n แพทย์ : "+rs.getString("doctorname")+"\n คนไข้ : "+rs.getString("patientname"));
			//obj.put("resourceId", rs.getString("resourceid"));
			obj.put("start", rs.getString("start"));
			obj.put("end", rs.getString("end"));
			obj.put("backgroundColor", rs.getString("color"));
			
			listjson.add(obj);
			
	} 
		out.println(listjson); 
	rs.close();
	pStmt.close(); 
	conn.close();
	  		
%>