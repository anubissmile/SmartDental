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
		
		String sql = "SELECT dentist_appointment.id,dentist_appointment.hn, "
				+"pre_name.pre_name_th,patient.first_name_th, " 
				+"patient.last_name_th,dentist_appointment.datetime_start,dentist_appointment.isview "
				+"FROM "
				+"dentist_appointment "
				+"INNER JOIN patient ON dentist_appointment.hn = patient.hn " 
				+"INNER JOIN pre_name ON patient.pre_name_id = pre_name.pre_name_id "
				+"WHERE  (CURDATE() BETWEEN DATE_ADD(dentist_appointment.datetime_start,INTERVAL - dentist_appointment.reminder_date DAY) "
				+"AND dentist_appointment.datetime_start) AND (dentist_appointment.contact_status = '1' OR dentist_appointment.contact_status = '2') "
				+"AND dentist_appointment.branch_code = '"+Auth.user().getBranchCode()+"' AND appointment_status = '5' AND ( CURDATE() != isdayview OR isdayview IS NULL) "
				+"ORDER BY dentist_appointment.datetime_start  ";
		
		
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			obj.put("isview",rs.getString("dentist_appointment.isview"));			
			obj.put("pat_hn", rs.getString("dentist_appointment.hn"));
			obj.put("pat_name", rs.getString("pre_name.pre_name_th")+rs.getString("first_name_th")+" "+rs.getString("last_name_th"));  
			obj.put("appID", rs.getString("dentist_appointment.id"));
			obj.put("appDate", dateutil.convertDateSpecificationPattern("yyyy-MM-dd HH:mm:ss.S","dd/MM/yyyy HH:mm",rs.getString("datetime_start"),false));
			listjsontreatment_patient.add(obj);
				
		} 
	} 
	
	out.print(listjsontreatment_patient); 
	//System.out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>