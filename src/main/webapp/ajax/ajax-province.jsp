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
	String province_id = request.getParameter("province_id");
	String amphur_id = request.getParameter("amphur_id");
	String district_id = request.getParameter("district_id");
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		String sql = "SELECT "
				+"a.PROVINCE_ID, a.PROVINCE_NAME, b.AMPHUR_ID, b.AMPHUR_NAME, c.DISTRICT_ID, c.DISTRICT_CODE, "
				+"c.DISTRICT_NAME "
				+"FROM "
				+"provinces AS a "
				+"INNER JOIN amphures AS b ON b.PROVINCE_ID = a.PROVINCE_ID "
				+"INNER JOIN districts AS c ON c.AMPHUR_ID = b.AMPHUR_ID where ";
		
		if(!province_id.equals("")) sql += "a.province_id = '"+province_id+"' and ";
		if(!amphur_id.equals("")) sql += "b.amphur_id = '"+amphur_id+"' and ";
		if(!district_id.equals("")) sql += "c.district_id = '"+district_id+"' and ";
		
				sql += "a.province_id != ''";
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			
			obj.put("province_id", rs.getString("province_id"));  
			obj.put("province_name", rs.getString("province_name"));
			obj.put("amphur_id", rs.getString("amphur_id"));
			obj.put("amphur_name", rs.getString("amphur_name"));
			obj.put("district_id", rs.getString("district_id"));
			obj.put("district_name", rs.getString("district_name"));
			
			listjson.add(obj);
				
		}
	}
	
		out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>