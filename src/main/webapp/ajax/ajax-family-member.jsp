<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONValue"%>
<%@page import="com.smict.person.data.FamilyData"%>
<%
	List listjson = new LinkedList();
	DBConnect dbcon = new DBConnect();
	ResultSet rs = null; 
	FamilyData famDB = new FamilyData();
	String method_type = request.getParameter("method_type");
	String family_id = request.getParameter("family_id");
	
	int int_family_id = 0;
	if(!family_id.equals("")){
		int_family_id = Integer.parseInt(family_id);
	}
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		
		//out.println(famDB.getJsonArrayUNION_FamilyList(int_family_id, "", "", "", "").toString());
		
	}
	
	  		
%>