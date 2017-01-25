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
	
	String method_type = request.getParameter("method_type");
	String treatment_code = request.getParameter("treatment_code"); 
	Connection conn = null;
	Statement Stmt = null;
	
	if(session.getAttribute("ServicePatientModel")!=null){
	
	GetTreatmentID getTreatmentID = new GetTreatmentID();
	ServicePatientModel servicePatModel;
	servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");		
		
	String treatment_id = ""; 
	String hn = servicePatModel.getHn();
	  
	treatment_id = getTreatmentID.Get_TreatmentID(hn); 
	
	if(method_type.equals("get")){
		String sql = "SELECT "
				+"a.product_id, b.product_name, " 
				+"(IFNULL(a.product_transfer,0)+IFNULL(c.product_transfer,0)) as product_transfer, "  
				+"(IFNULL(a.product_free,0)+IFNULL(c.product_free,0)) as product_free " 
				+"FROM treatment_product a " 
				+"INNER JOIN pro_product b ON (b.product_id = a.product_id) "
				+"LEFT JOIN history_treatment_product c ON (c.product_id = a.product_id and c.treatment_id = '"+treatment_id+"') "
				+"WHERE a.treatment_code = '"+treatment_code+"' "
				+"UNION "
				+"SELECT a.product_id, b.product_name, (IFNULL(a.product_transfer,0)+IFNULL(c.product_transfer,0)) as product_transfer, " 
				+"(IFNULL(a.product_free,0)+IFNULL(c.product_free,0)) as product_free FROM history_treatment_product a " 
				+"INNER JOIN pro_product b ON (b.product_id = a.product_id) "
				+"LEFT JOIN treatment_product c ON (c.product_id = a.product_id and c.treatment_code = '"+treatment_code+"') " 
				+"WHERE a.treatment_id = '"+treatment_id+"' ";
		  
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		if(!rs.next()){
			 
			sql = "SELECT "
					+"a.product_id, b.product_name, a.product_free, a.product_transfer " 
					+"FROM "
					+"history_treatment_product a " 
					+"INNER JOIN pro_product b ON (b.product_id = a.product_id and b.producttype_id = '0001') where ";
			
			if(!treatment_id.equals("")) sql += "a.treatment_id = '"+treatment_id+"' "; else sql += "a.treatment_id = ''";
			
			conn = dbcon.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql); 
		}
		rs.beforeFirst();
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			obj.put("product_id", rs.getString("product_id"));
			obj.put("product_name", rs.getString("product_name"));  
			obj.put("product_free", rs.getString("product_free")); 
			obj.put("product_transfer", rs.getString("product_transfer"));
			
			listjson.add(obj);
				
		} 
	}
	
	}else{
		
		String sql = "SELECT "
				+"a.product_id, b.product_name, " 
				+"IFNULL(a.product_transfer,0) as product_transfer, "  
				+"IFNULL(a.product_free,0) as product_free " 
				+"FROM treatment_product a " 
				+"INNER JOIN pro_product b ON (b.product_id = a.product_id) WHERE ";
		
		if(!treatment_code.equals("")) sql += "a.treatment_code = '"+treatment_code+"' ";
		  
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
	}
	
	out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>