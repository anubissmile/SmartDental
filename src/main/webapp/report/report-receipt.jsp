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
	String filejrxml = application.getRealPath("report/receipt.jrxml");
	System.out.println(filejrxml);
	 
	String hn = request.getAttribute("hn").toString(); 
	String treatment_id = request.getAttribute("treatment_id").toString();
	String report_no = request.getAttribute("report_no").toString(); 
	String date = request.getAttribute("date").toString();
	String time = request.getAttribute("time").toString(); 
	float amounttotal = Float.valueOf(request.getAttribute("amounttotal").toString());
	float discount = Float.valueOf(request.getAttribute("discount").toString());
	float net = Float.valueOf(request.getAttribute("net").toString());
	float owe = Float.valueOf(request.getAttribute("owe").toString());
	 
	File reportFile = new File(filejrxml);
	if (!reportFile.exists()){
		System.out.println("File Not found");
	}else{
		System.out.println("File is found");
	}
	    	
	
	JasperReport jr= JasperCompileManager.compileReport(filejrxml);
	
	Map prm = new HashMap();
	prm.put("hn", hn);
	prm.put("treatment_id", treatment_id);
	prm.put("report_no", report_no);
	prm.put("date", date);
	prm.put("time", time);
	prm.put("total", amounttotal);
	prm.put("discount", discount);
	prm.put("net", net);
	prm.put("owe", owe);
	
	JasperPrint jasperPrint = JasperFillManager.fillReport(jr, prm, conn);
	 
	
	OutputStream outStream = response.getOutputStream();
	JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	
 %>
