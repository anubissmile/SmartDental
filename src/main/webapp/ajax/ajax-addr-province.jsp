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
	String addr_provinceid = request.getParameter("addr_provinceid");
	
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		
		String sql = "SELECT "
				+"a.PROVINCE_ID, a.PROVINCE_NAME, a.PROVINCE_NAME_ENG "
				+"FROM "
				+"provinces AS a where ";
				
		if(!addr_provinceid.equals("")) sql+= "a.province_id = '"+addr_provinceid+"' and ";
		
		sql += "a.province_id != '' order by a.province_name ";
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			
			obj.put("addr_provinceid", rs.getString("province_id"));  
			obj.put("province_name", rs.getString("province_name"));
			obj.put("province_name_eng", rs.getString("province_name_eng"));
			
			listjson.add(obj);
				
		}
	}
	
	out.println(listjson); 
	//System.out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>