<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="net.sf.jasperreports.engine.*" %> 
<%@ page import="java.util.*" %> 
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="ldc.util.DBConnect"%> 
<%@ page contentType="application/pdf" %>
<%@ page import="com.smict.all.model.*" %>
<%
	DBConnect dbcon = new DBConnect();
	Connection conn = dbcon.getConnectMYSql();
	String filejrxml = application.getRealPath("report/medicine.jrxml");
	System.out.println(filejrxml);
	 
	ServicePatientModel servicePatModel;
	servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
	String hn = "";
	if(session.getAttribute("ServicePatientModel")!=null){
		hn 	= servicePatModel.getHn();
	}
	
	String drugname = "";
	if(request.getAttribute("drugname")!=null) drugname = (String) request.getAttribute("drugname");
	
	String pill = "";
	if(request.getAttribute("pill")!=null) pill = (String) request.getAttribute("pill");
	
	String episode = "";
	if(request.getAttribute("episode")!=null) episode = (String) request.getAttribute("episode"); 
	
	String mealstatus = "";
	if(request.getAttribute("mealstatus")!=null) mealstatus = (String) request.getAttribute("mealstatus");
	
	String mealtime = "";
	if(request.getAttribute("mealtime")!=null) mealtime = (String) request.getAttribute("mealtime");
	
	File reportFile = new File(filejrxml);
	if (!reportFile.exists()){
		System.out.println("File Not found");
	}else{
		System.out.println("File is found");
	}
	    	
	
	JasperReport jr= JasperCompileManager.compileReport(filejrxml);
	
	Map prm = new HashMap();
	prm.put("hn", hn); 
	prm.put("drugname", drugname); 
	prm.put("pill", pill); 
	prm.put("episode", episode); 
	prm.put("mealstatus", mealstatus); 
	prm.put("mealtime", mealtime); 
	
	JasperPrint jasperPrint = JasperFillManager.fillReport(jr, prm, conn);
	 
	
	OutputStream outStream = response.getOutputStream();
	JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	
 %>
