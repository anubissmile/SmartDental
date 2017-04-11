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
	String district_id = request.getParameter("district_id");
	
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		
		String SQL = "SELECT districts.DISTRICT_ID, " 
			+ "districts.DISTRICT_CODE, "
			+ "zipcodes.ZIPCODE"
			+ " FROM districts "
			+ "LEFT JOIN zipcodes ON districts.DISTRICT_ID = zipcodes.DISTRICT_ID WHERE "; 
		
		if(district_id != null && !district_id.equals("")) SQL+= " districts.DISTRICT_ID = '" + district_id + "' AND ";
		
		SQL += "districts.DISTRICT_ID <> '' order by districts.DISTRICT_ID ";
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(SQL);
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			
			obj.put("zipcode", rs.getString("ZIPCODE"));  
			//obj.put("district_name", rs.getString("district_name"));
			//obj.put("district_name_eng", rs.getString("district_name_eng"));
			
			listjson.add(obj);
				
		}
	}
	
	out.println(listjson); 
	//System.out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>