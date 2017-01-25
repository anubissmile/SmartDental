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
	String addr_typeid = request.getParameter("addr_typeid");
	String addr_typename = request.getParameter("addr_typename");
	
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		
		String sql = "select * from address_type where ";
				
		if(!addr_typeid.equals("")) sql+= "addr_typeid = '"+addr_typeid+"' and ";
		if(!addr_typename.equals("")) sql+= "addr_typename like '%"+addr_typename+"%' and ";
		
		sql += "addr_typeid != '' ";
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			
			obj.put("addr_typeid", rs.getString("addr_typeid"));  
			obj.put("addr_typename", rs.getString("addr_typename"));
			
			listjson.add(obj);
			
		}
	}
	
	out.println(listjson); 
		
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>