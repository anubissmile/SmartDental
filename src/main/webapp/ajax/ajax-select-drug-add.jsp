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
		
		List product_id_treatment = new ArrayList();
		String sql = "SELECT "
				+"treatment_code from history_treatment where treatment_id = '"+treatment_id+"' ";
		   
		Stmt = conn.createStatement();
		ResultSet rsTd = Stmt.executeQuery(sql); 
		while(rsTd.next()){ 
			treatment_code = rsTd.getString("treatment_code");
			
			sql = "SELECT * from treatment_product where treatment_code = '"+treatment_code+"' ";
			Stmt = conn.createStatement();
			ResultSet rsTc = Stmt.executeQuery(sql);
			while(rsTc.next()){ 
				product_id_treatment.add(rsTc.getString("product_id"));
			}	
			rsTc.close();
		}
		rsTd.close();
		Stmt.close();
		
		sql = "SELECT "
				+"a.product_id, a.product_name " 
				+"FROM pro_product a " 
				+"WHERE a.producttype_id = '0001' and "
				+"a.product_id not in (select product_id from history_treatment_product where treatment_id = '"+treatment_id+"') ";
		   
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		String product_id = "", product_free = "0";
		while(rs.next()){  
			product_free = "0";
			product_id = rs.getString("product_id");
			for(int i=0; i<product_id_treatment.size(); i++){
				
				if(product_id.equals(product_id_treatment.get(i).toString())){
					
					sql = "SELECT SUM(product_free) as product_free from treatment_product where product_id = '"+product_id_treatment.get(i)+"' ";
					Stmt = conn.createStatement();
					ResultSet rsTc = Stmt.executeQuery(sql);
					while(rsTc.next()){ 
						product_free = rsTc.getString("product_free");
					}	
					rsTc.close();
				}else{
					if(Integer.valueOf(product_free)==0){
						product_free = "0";
					}
					
				}
			}
			
			JSONObject obj=new JSONObject();
			obj.put("product_id", rs.getString("product_id"));
			obj.put("product_name", rs.getString("product_name"));  
			obj.put("product_free", product_free); 
			obj.put("product_transfer", 0);
			
			listjson.add(obj);
				
		}
		rs.close();
		Stmt.close();
		
		out.println(listjson); 
		}
	 
	 
	conn.close();
	  		
%>