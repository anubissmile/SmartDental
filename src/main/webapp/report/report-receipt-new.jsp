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
	 
		String receipt_id = request.getParameter("receipt_id").toString(); 
		String receive_id_text = receipt_id;
		
		if(receive_id_text.length()==1) receive_id_text = "00000"+receive_id_text;
		else if(receive_id_text.length()==2) receive_id_text = "0000"+receive_id_text;
		else if(receive_id_text.length()==3) receive_id_text = "000"+receive_id_text;
		else if(receive_id_text.length()==4) receive_id_text = "00"+receive_id_text;
		else if(receive_id_text.length()==5) receive_id_text = "0"+receive_id_text;
		 
		File reportFile = null;
		 reportFile = new File(application.getRealPath("report/receiptnew.jasper"));  

	 if (!reportFile.exists())
			throw new JRRuntimeException("File not found. The report design must be compiled first.");
 
	 	JasperPrint jasperPrint = new JasperPrint();
		List<JasperPrint> list = new ArrayList<JasperPrint>();
	 
		Map prm = new HashMap();
		prm.put("prmid", Integer.parseInt(receipt_id));
		prm.put("receive_id_text", receive_id_text); 
		
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
