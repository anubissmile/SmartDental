<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONValue"%>
<%@ page import="com.smict.treatment.data.*" %>
<%@ page import="com.smict.all.model.*" %>
<%
	TreatmentData tmDB = new TreatmentData();  
	ServicePatientModel servicePatModel;
	servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
	
	int room_id =  Integer.valueOf(request.getParameter("room_id"));
	String status = request.getParameter("status");

	tmDB.UpdateWaitingToTreatmenting(servicePatModel.getHn(), room_id, status); 
	
	servicePatModel = tmDB.select_TP(servicePatModel.getHn()); 
	session.setAttribute("ServicePatientModel", servicePatModel);
	  		
%>