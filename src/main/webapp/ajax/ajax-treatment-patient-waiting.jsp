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
	
	String method_type = request.getParameter("method_type");

	
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		
		String sql = "SELECT patient.hn,pre_name.pre_name_th,patient.first_name_th,patient.last_name_th, "
				+"treatment_patient.status_work,patient_queue.pq_status,treatment_patient.room_id, " 
				+"treatment_patient.doctor_id,treatment_patient.id,patient_queue.pq_workday_id,patient_queue.pq_id "
				+"FROM "
				+"patient "
				+"INNER JOIN patient_queue ON patient.hn = patient_queue.pq_hn " 
				+"INNER JOIN treatment_patient ON patient.hn = treatment_patient.patient_hn "
				+"INNER JOIN pre_name ON patient.pre_name_id = pre_name.pre_name_id "
				+"WHERE treatment_patient.status_work ='2' AND patient_queue.pq_status ='5' "
				+"AND patient_queue.pq_branch = '"+Auth.user().getBranchCode()+"' ";
		
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
						
			obj.put("patient_hn", rs.getString("patient.hn"));
			obj.put("patient_name", rs.getString("pre_name.pre_name_th")+rs.getString("first_name_th")+" "+rs.getString("last_name_th"));  
			obj.put("treatmentPatientID", rs.getString("treatment_patient.id"));
			listjsontreatment_patient.add(obj);
				
		} 
	} 
	
	out.print(listjsontreatment_patient); 
	//System.out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>