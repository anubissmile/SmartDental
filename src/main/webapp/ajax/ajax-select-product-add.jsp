<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONValue"%>  
<%@ page import="com.smict.all.model.*" %>
<%@ page import="com.smict.treatment.data.TreatmentData" %>
<%
	List listjson = new LinkedList();
	DBConnect dbcon = new DBConnect();
	ResultSet rs = null; 
  
	String method_type 	= request.getParameter("method_type");
	  
	ServicePatientModel servicePatModel;
	servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
	String hn 				= servicePatModel.getHn();
	TreatmentData treatmentdb = new TreatmentData();
	int treatment_id 	= treatmentdb.Select_Treatment_ID(hn); 
	String treatment_code = "";
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		  
		conn = dbcon.getConnectMYSql();
		 
		String sql = "SELECT "
				+"a.product_id, a.product_name, a.price  " 
				+"FROM pro_product a " 
				+"WHERE a.producttype_id = '0002' and "
				+"a.product_id not in (select product_id from history_treatment_product where treatment_id = '"+treatment_id+"') ";
		   
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql);  
		while(rs.next()){   
			
			JSONObject obj=new JSONObject();
			obj.put("product_id", rs.getString("product_id"));
			obj.put("product_name", rs.getString("product_name"));  
			obj.put("price", rs.getString("price"));
			
			listjson.add(obj);
				
		}
		rs.close();
		Stmt.close();
		
		out.println(listjson); 
		}
	 
	 
	conn.close();
	  		
%>