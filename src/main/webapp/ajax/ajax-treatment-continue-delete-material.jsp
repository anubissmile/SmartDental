<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<%@ page import="com.smict.all.model.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONValue"%> 
<% 
	List listjson = new LinkedList();
	String method_type 	= request.getParameter("method_type");  
	
	Connection conn = null;
	Statement Stmt = null;
	ResultSet rs = null; 
	if(method_type.equals("get")){ 
		ServicePatientModel servicePatModel;
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		String hn 				= servicePatModel.getHn();
		GetTreatmentID getTreatmentID = new GetTreatmentID();
		String treatment_id 		= getTreatmentID.Get_TreatmentID(hn); 
		String treatment_code 		= request.getParameter("treatment_code");
		String continue_product_id	= request.getParameter("continue_product_id");
		  
		int continue_id 			= 0; 
		int continue_phase 			= 0;
		int continue_count 			= 0; 
		int continue_count_all 		= 0; 
		
		DBConnect dbcon = new DBConnect(); 
		conn = dbcon.getConnectMYSql();
		
		String sql = "DELETE FROM "  
				+"treatcontinue_product  " 
				+"where continue_product_id = "+continue_product_id+" ";
		
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sql);
			Stmt.close();
		 
			sql = "select a.continue_id,a.continue_phase,a.continue_count,a.continue_count_all from treatcontinue_transaction a "
					+ "inner join treatcontinue_setup b on(b.continue_id = a.continue_id) "
					+ "WHERE a.treatment_id = "+treatment_id+" and b.treatment_code = '"+treatment_code+"' ";
			
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql); 
			while (rs.next()){ 
				continue_id 		= rs.getInt("continue_id"); 
				continue_phase 		= rs.getInt("continue_phase");
				continue_count 		= rs.getInt("continue_count");
				continue_count_all 	= rs.getInt("continue_count_all");
			}
			rs.close();
			Stmt.close();	
			
			sql = "SELECT "
					+"a.continue_product_id, a.product_id, b.product_name, b.product_name_en, a.qty "
					+"FROM "
					+"treatcontinue_product a inner join pro_product b on(b.product_id = a.product_id) "
					+"where b.producttype_id = '0003' "
					+"and a.continue_id = "+continue_id+" " 
					+"and a.continue_phase = "+continue_phase+" " 
					+"and a.continue_count = "+continue_count+" " 
					+"and a.continue_count_all = "+continue_count_all+" "; 
			
			sql += "and a.product_id != '' order by a.product_id ";
			conn = dbcon.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql); 
			 
			while(rs.next()){   
				JSONObject obj=new JSONObject();
				obj.put("continue_product_id", rs.getString("continue_product_id")); 
				obj.put("product_id", rs.getString("product_id"));  
				obj.put("product_name", rs.getString("product_name"));
				obj.put("product_name_en", rs.getString("product_name_en"));
				obj.put("qty", rs.getString("qty"));
				
				listjson.add(obj);
					
			}
			conn.close();  
	}    
	  		
	out.println(listjson);
%>