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
	String amphur_id = request.getParameter("addr_aumphurid");
	
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		
		String sql = "SELECT "
				+"a.district_id, a.district_name, a.district_name_eng "
				+"FROM "
				+"districts AS a where ";
				
		if(!amphur_id.equals("")) sql+= "a.amphur_id = '"+amphur_id+"' and ";
		
		sql += "a.district_id != '' order by a.district_name ";
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			
			obj.put("district_id", rs.getString("district_id"));  
			obj.put("district_name", rs.getString("district_name"));
			obj.put("district_name_eng", rs.getString("district_name_eng"));
			
			listjson.add(obj);
				
		}
	}
	
	out.println(listjson); 
	//System.out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>