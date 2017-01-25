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
		GetTreatmentID getTreatmentID = new GetTreatmentID();
		treatment_id = getTreatmentID.Get_TreatmentID(hn);
	}  
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		 
		if(treatment_id!=null){
		
		String sql = "SELECT "
				+"a.product_id, b.product_name, a.product_free, a.product_transfer " 
				+"FROM "
				+"history_treatment_product a " 
				+"INNER JOIN pro_product b ON (b.product_id = a.product_id and b.producttype_id = '0001') where ";
		
		if(!treatment_id.equals("")) sql += "a.treatment_id = '"+treatment_id+"' ";
		  
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			obj.put("product_id", rs.getString("product_id"));
			obj.put("product_name", rs.getString("product_name"));  
			obj.put("product_free", rs.getString("product_free")); 
			obj.put("product_transfer", rs.getString("product_transfer"));
			
			listjson.add(obj);
				
		}
		rs.close();
		Stmt.close();
		
		out.println(listjson); 
		}
		
	} 
	 
	conn.close();
	  		
%>