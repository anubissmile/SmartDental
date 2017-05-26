<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<% 
DBConnect agent = new DBConnect();



	String treatment_code = request.getParameter("checkbox_value"); 
	String [] treatment_coded = treatment_code.split(",");
	String position_id = request.getParameter("position_id");
	
	String SQL = "INSERT INTO doctor_position_treatment  (doc_position_id,treatment_code) "
				+"VALUES ";
				int i = 0;
				for(String treat_code : treatment_coded){
					
					if(i>0){
						SQL+=",";
					}
					SQL +="('"+position_id+"','"+treat_code+"')";
					i++;
				}
				
	agent.connectMySQL();
	JSONObject jsonOBJ = new JSONObject();
	jsonOBJ.put("status", agent.exeUpdate(SQL) > 0 ? "success" : "false");	
	out.print(jsonOBJ);
	agent.disconnectMySQL();
	

%>