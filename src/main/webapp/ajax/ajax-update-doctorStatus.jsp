<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<% 
DBConnect agent = new DBConnect();



	String docID = request.getParameter("doctorid"), statusname = request.getParameter("statusname"), workdayid = request.getParameter("workdayid");
	String SQL = "UPDATE doctor_workday  SET ";
				if(statusname.equals("Waiting")){
					SQL +="checkin_status = '2' "
						+",	checkin_datetime = now() ";
				}else{
					SQL +="checkin_status = '3' "
							+",	checkout_datetime = now() ";
				}
				SQL += "WHERE doctor_id = '"+docID+"' AND workday_id = '"+workdayid+"' AND DATE_FORMAT(start_datetime,'%Y-%m-%d') = CURDATE() ";
	agent.connectMySQL();
	JSONObject jsonOBJ = new JSONObject();
	jsonOBJ.put("status", agent.exeUpdate(SQL) > 0 ? "success" : "false");
	out.print(jsonOBJ);
	agent.disconnectMySQL();

%>