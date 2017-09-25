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
	
	String doctorID = request.getParameter("docid");
	
	Connection conn = null;
	Statement Stmt = null;

		//AND a.PROVINCE_ID = 14
		String sql = "SELECT "
				+"IFNULL(doctor_treatment.doctor_id,'nu') as checkdocid, "
				+"treatment_master.`code`, "
				+"treatment_master.nameth,treatment_master.id "
				+"FROM "
				+"doctor_treatment "
				+"right JOIN treatment_master ON treatment_master.id = doctor_treatment.treatment_id AND doctor_id = '"+doctorID+"' "
				+"WHERE IFNULL(doctor_treatment.doctor_id,'nu') = 'nu' ";
			

		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql);
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			obj.put("treatmentcheck", rs.getString("checkdocid"));
 			obj.put("treatmentID", rs.getString("treatment_master.id")); 
			listjson.add(obj);
			}
		
	
	
	out.println(listjson); 
	//System.out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>