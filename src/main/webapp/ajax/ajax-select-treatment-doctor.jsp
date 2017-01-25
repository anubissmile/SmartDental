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
	
	String method_type = request.getParameter("method_type");
	String treatment_code = request.getParameter("treatment_code"); 
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		String sql = "SELECT "
				+"a.doctor_id, concat(c.pre_name_th,' ',b.first_name_th,' ',b.last_name_th) as doctor_name " 
				+"FROM "
				+"treatment_doctor a " 
				+"INNER JOIN doctor b ON (b.doctor_id = a.doctor_id) "
				+"INNER JOIN pre_name c ON (c.pre_name_id = b.pre_name_id) where ";
		
		if(!treatment_code.equals("")) sql += "a.treatment_code = '"+treatment_code+"' ";
		  
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			
			obj.put("doctor_id", rs.getString("doctor_id"));  
			obj.put("doctor_name", rs.getString("doctor_name")); 
			
			listjson.add(obj);
				
		}
	}
	
		out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>