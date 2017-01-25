<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONValue"%>
<%
	
	//DateUtil dateUtil = new DateUtil();

	//String day = dateUtil.CnvToYYYYMMDDEngYear(dateUtil.curDateTH(), '-');

	String eventid = request.getParameter("eventid"); 
	String cradio = request.getParameter("cradio");
	String color = "";
	if(cradio.equals("1")){
		cradio = "Y"; color = "#00FF00"; // นัดหมายแล้ว
	}
	else if (cradio.equals("2")){
		cradio = "R";  color = "#FF3399"; // เลื่อนนัด
	}
	else if (cradio.equals("3")){
		cradio = "N"; color = "#FF0000"; // ติดต่อไม่ได้
	}

	List listjson = new LinkedList();
	DBConnect agent 	= new DBConnect(); 
	Connection conn		= null;
	ResultSet rs = null; 
	conn = agent.getConnectMYSql();
	
	String sql = "update fullcalendar set confirm_status = '"+cradio+"', color = '"+color+"'  "+
			"where id = '"+eventid+"'";
	Statement pStmt = conn.createStatement();
	pStmt.executeUpdate(sql);
	pStmt.close();
	
	sql = "SELECT a.id,a.start,a.title,a.end,a.color,a.mor,a.customer,a.hong,a.saka,confirm_status "+
			"FROM fullcalendar a "; 
	pStmt = conn.createStatement();
	rs = pStmt.executeQuery(sql);  
	
	String confirm_status = "";
	while(rs.next()){  
		confirm_status = rs.getString("confirm_status");
		if(confirm_status==null||confirm_status=="") confirm_status = "รอการยืนยัน";
		if(confirm_status.equals("Y")) confirm_status = "นัดหมายแล้ว"; // Y
		else if (confirm_status.equals("R")) confirm_status = "เลื่อนนัด"; // R
		else if (confirm_status.equals("N")) confirm_status = "ติดต่อไม่ได้"; // N
		
		JSONObject obj=new JSONObject();
			obj.put("id", rs.getString("id"));  
			obj.put("title", rs.getString("title")+" ห้อง "+rs.getString("hong")+"\n"+confirm_status);
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