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
	String addr_aumphurid = request.getParameter("addr_aumphurid");
	
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		
		String sql = "SELECT "
				+"a.district_id, a.district_code, a.district_name "
				+"FROM "
				+"districts AS a "
				+"where ";
				
		if(addr_aumphurid != null && !addr_aumphurid.equals("")) sql+= "a.amphur_id = '"+addr_aumphurid+"' and ";
		
		sql += "a.district_id != '' order by a.district_name ";
		
		System.out.println(sql);
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			
			obj.put("district_id", rs.getString("district_id"));  
			obj.put("district_name", rs.getString("district_name"));
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