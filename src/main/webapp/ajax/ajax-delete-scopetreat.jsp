<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<% 
DBConnect agent = new DBConnect();



	String position_id = request.getParameter("position_id");
	
	  String sql = "DELETE FROM doctor_position_treatment "
		        + "WHERE doc_position_id = '"+position_id+"' ";
				
	agent.connectMySQL();
	JSONObject jsonOBJ = new JSONObject();
	jsonOBJ.put("status", agent.exeUpdate(sql) > 0 ? "success" : "false");	
	out.print(jsonOBJ);
	agent.disconnectMySQL();
	

%>