<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="net.sf.jasperreports.engine.*" %> 
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.engine.type.WhenNoDataTypeEnum"%>
<%@ page import="java.util.*" %> 
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="ldc.util.DBConnect"%>  
<%@ page contentType="application/pdf;charset=UTF-8" %>
<%
		DBConnect dbcon = new DBConnect();
		Connection conn = dbcon.getConnectMYSql(); 
	 
		String date_report = request.getParameter("date_report").toString();  
		 
		File reportFile = null;
		 reportFile = new File(application.getRealPath("report/tf_shop_day.jasper"));  

	 if (!reportFile.exists())
			throw new JRRuntimeException("File not found. The report design must be compiled first.");
 
	 	JasperPrint jasperPrint = new JasperPrint();
		List<JasperPrint> list = new ArrayList<JasperPrint>();
	 
		Map prm = new HashMap();
		prm.put("prmdate_report", date_report); 
		
		 jasperPrint = JasperFillManager.fillReport(reportFile.getPath(),prm,conn); 
		 List<JRPrintPage> pages = jasperPrint.getPages();
		 boolean check_data = false;
		 if (pages.size()!=0){ 
			 list.add(jasperPrint);
			 check_data = true;
		 } 

		 if(check_data){
				ServletOutputStream ouputStream = response.getOutputStream(); 
				JRPdfExporter exporter = new JRPdfExporter();
			
				exporter.setParameter(JRPdfExporterParameter.JASPER_PRINT_LIST, list);
				exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM,ouputStream); 
				exporter.exportReport();
				response.setContentLength(ouputStream.toString().length());
				 
				ouputStream.flush();
				ouputStream.close(); 
		 }else{
			 response.sendRedirect("redirect-index.jsp");
		 }
		conn.close();
 %> 
