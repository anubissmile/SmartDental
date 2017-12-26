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
	 
		String owe_id = request.getParameter("owe_id").toString(); 
		String owe_id_text = owe_id;
		
		if(owe_id_text.length()==1) owe_id_text = "00000"+owe_id_text;
		else if(owe_id_text.length()==2) owe_id_text = "0000"+owe_id_text;
		else if(owe_id_text.length()==3) owe_id_text = "000"+owe_id_text;
		else if(owe_id_text.length()==4) owe_id_text = "00"+owe_id_text;
		else if(owe_id_text.length()==5) owe_id_text = "0"+owe_id_text;
		 
		File reportFile = null;
		 reportFile = new File(application.getRealPath("report/owe.jasper"));  

	 if (!reportFile.exists())
			throw new JRRuntimeException("File not found. The report design must be compiled first.");
 
	 	JasperPrint jasperPrint = new JasperPrint();
		List<JasperPrint> list = new ArrayList<JasperPrint>();
	 
		Map prm = new HashMap();
		prm.put("prmid", Integer.parseInt(owe_id));
		prm.put("owe_id_text", owe_id_text); 
		
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
