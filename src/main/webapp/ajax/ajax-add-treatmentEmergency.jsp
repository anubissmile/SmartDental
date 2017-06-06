<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<% 
DBConnect agent = new DBConnect();

	

	String docID = request.getParameter("docid"), 
			treatid = request.getParameter("treatid");	
	String sql = "SELECT doctor_id,treatment_id  FROM doctor_treatment  "
			 +"WHERE doctor_id = '"+docID+"' AND treatment_id = '"+treatid+"' ";
	agent.connectMySQL();
	agent.exeQuery(sql);
	int check = agent.size();
	agent.disconnectMySQL();
	if(check == 0){
	String SQL = "INSERT INTO doctor_treatment (doctor_id,treatment_id,can_change_from_scope,is_temporary)  "
				 +"VALUES ('"+docID+"','"+treatid+"','t','t') ";
	
	agent.connectMySQL();
	JSONObject jsonOBJ = new JSONObject();
	jsonOBJ.put("status", agent.exeUpdate(SQL) > 0 ? "success" : "false");
	out.print(jsonOBJ);
	agent.disconnectMySQL();
	}else{
		JSONObject jsonOBJ = new JSONObject();
		jsonOBJ.put("status", check == 0 ? "success" : "false");
		out.print(jsonOBJ);
	}
%>