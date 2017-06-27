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
	
	String treatID = request.getParameter("treatID");
	
	Connection conn = null;
	Statement Stmt = null;

		String sql = "SELECT "
				+"IFNULL(treatment_type.tooth_type_id,'nu') AS typecheck,tooth_type.name_th "
				+"FROM "
				+"treatment_type "
				+"RIGHT   JOIN tooth_type ON   tooth_type.id = treatment_type.tooth_type_id AND treatment_type.treatment_id = '"+treatID+"' "
/* 				+"WHERE IFNULL(treatment_type.tooth_type_id,'nu') = 'nu' " */
				+"ORDER BY tooth_type.id ";
			

		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql);
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			obj.put("treatmentcheck", rs.getString("typecheck"));
			obj.put("treatmentName", rs.getString("tooth_type.name_th"));
			listjson.add(obj);
			}
		
	
	out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>