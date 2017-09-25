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
	String tel_typeid = request.getParameter("tel_typeid");
	String tel_typename = request.getParameter("tel_typename");
	System.out.println("Step Telephone Type");
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		
		String sql = "SELECT * FROM `tel_teltype` where ";
				
		if(!tel_typeid.equals("")) sql +="tel_typeid = "+tel_typeid+" and ";
		
		if(!tel_typename.equals("")) sql +="tel_typename like '%"+tel_typename+"%' and ";
		
		sql+="tel_typeid != '5' ";
		
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			
			obj.put("tel_typeid", rs.getString("tel_typeid"));  
			obj.put("tel_typename", rs.getString("tel_typename"));
			
			listjson.add(obj);
				
		}
	}
	
	out.println(listjson); 
	//System.out.println(listjson);
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>