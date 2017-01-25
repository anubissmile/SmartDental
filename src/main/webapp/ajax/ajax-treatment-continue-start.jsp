<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONValue"%> 
<%@ page import="com.smict.all.model.*" %>
<%
	List listjson = new LinkedList();
	DBConnect dbcon = new DBConnect();
	ResultSet rs = null; 
	
	ServicePatientModel servicePatModel;
	servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
	 
	String hn = "", treatment_id = null;
	String method_type 	= request.getParameter("method_type");
	if(session.getAttribute("ServicePatientModel")!=null){
		hn 			= servicePatModel.getHn();  
	}  
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){ 
		
		String sql = "SELECT MIN(continuetrans_id), a.continue_id, a.continue_phase, a.continue_count, a.continue_count_all, "
				+"a.setup_price, a.price, b.treatment_code, b.doctor_id, c.treatment_nameth, concat(e.pre_name_th,' ',d.first_name_th,' ',d.last_name_th) doctor_name " 
				+"FROM treatcontinue_transaction a INNER JOIN treatcontinue_setup b on(b.continue_id = a.continue_id) "
				+"INNER JOIN treatment_master  c on(c.treatment_code = b.treatment_code) "
				+"INNER JOIN doctor d on(d.doctor_id = b.doctor_id) "
				+"INNER JOIN pre_name e on(e.pre_name_id = d.pre_name_id) "
				+"WHERE a.price = 0 and a.treatment_id IS NULL "
				+"and b.hn = '"+hn+"' GROUP BY a.continue_id ORDER BY a.continuetrans_id";
		
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			obj.put("treatment_code", rs.getString("treatment_code"));
			obj.put("treatment_nameth", rs.getString("treatment_nameth"));
			obj.put("doctor_id", rs.getInt("doctor_id"));
			obj.put("doctor_name", rs.getString("doctor_name"));
			obj.put("continue_id", rs.getString("continue_id")); 
			obj.put("continue_phase", rs.getString("continue_phase"));  
			obj.put("continue_count", rs.getString("continue_count"));   
			obj.put("continue_count_all", rs.getString("continue_count_all")); 
			listjson.add(obj);
				
		}
		rs.close();
		Stmt.close();
		
		out.println(listjson);  
		
	} 
	 
	conn.close();
	  		
%>