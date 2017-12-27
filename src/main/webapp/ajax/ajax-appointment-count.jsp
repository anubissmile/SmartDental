<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONValue"%>
<%
	List listjsontreatment_patient = new LinkedList();
	DBConnect dbcon = new DBConnect();
	ResultSet rs = null; 
	DateUtil dateutil = new DateUtil();
	String method_type = request.getParameter("method_type");

	
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		
		String sql = "SELECT COUNT(dentist_appointment.id) AS countall "
				+"FROM "
				+"dentist_appointment "
				+"INNER JOIN patient ON dentist_appointment.hn = patient.hn " 
				+"INNER JOIN pre_name ON patient.pre_name_id = pre_name.pre_name_id "
				+"WHERE  (CURDATE() BETWEEN DATE_ADD(dentist_appointment.datetime_start,INTERVAL - dentist_appointment.reminder_date DAY) "
				+"AND dentist_appointment.datetime_start) AND (dentist_appointment.contact_status = '1' OR dentist_appointment.contact_status = '2') "
				+"AND dentist_appointment.branch_code = '"+Auth.user().getBranchCode()+"' AND appointment_status = '5' AND isview = '0' "
				+"ORDER BY dentist_appointment.datetime_start  ";
		
		
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
						
			obj.put("countall", rs.getString("countall"));
			listjsontreatment_patient.add(obj);
				
		} 
	} 
	
	out.print(listjsontreatment_patient); 
	//System.out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>