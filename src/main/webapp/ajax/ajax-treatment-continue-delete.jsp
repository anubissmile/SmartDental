<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<%@ page import="com.smict.all.model.*" %>
<% 
	DBConnect dbcon = new DBConnect(); 
	 
	String continue_id 		= request.getParameter("continue_id").trim();
	String continue_phase 	= request.getParameter("continue_phase").trim(); 
	String method_type 		= request.getParameter("method_type");
	continue_phase = String.valueOf(Integer.valueOf(continue_phase)+1);
	Connection conn = null;
	Statement Stmt = null;
	ResultSet rs = null; 
	if(method_type.equals("get")){
		conn = dbcon.getConnectMYSql();
		
		String sql = "DELETE FROM "  
				+"treatcontinue_transaction  " 
				+"where continue_id = "+continue_id+" and continue_phase = "+continue_phase+" ";
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
		Stmt.close();
		
		sql = "DELETE FROM "  
				+"treatcontinue_product  " 
				+"where continue_id = "+continue_id+" and continue_phase = "+continue_phase+" ";
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
		Stmt.close();
		
		sql = "DELETE FROM "  
				+"treatcontinue_treatmentcode " 
				+"where continue_id = "+continue_id+" and continue_phase = "+continue_phase+" ";
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
		Stmt.close();
		
		sql = "SELECT continue_id FROM "  
				+"treatcontinue_transaction " 
				+"where continue_id = "+continue_id+" ";
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		int i = 0;
		while (rs.next()){ 
			 i++;
		}
		rs.close();
		Stmt.close();
		if(i==0){
			sql = "DELETE FROM "  
					+"treatcontinue_setup  " 
					+"where continue_id = "+continue_id+" ";
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sql);
			Stmt.close();
		} 
	 
	}   
	conn.close();
	  		
	out.println(continue_id);
%>