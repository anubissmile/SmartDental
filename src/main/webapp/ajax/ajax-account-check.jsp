<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONValue"%>
<%
	List listjson = new LinkedList();
	DBConnect dbcon = new DBConnect();
	ResultSet rs = null; 
	
	String bookID = request.getParameter("bookID");
	String docID = request.getParameter("docID");
	Connection conn = null;
	Statement Stmt = null;

		String sql = "SELECT "
				+"branch_standard_rel_doctor.branch_id,branch_standard_rel_doctor.doctor_id, "
				+"branch.branch_name,IFNULL(account_rel_doctorbranch.id,'nu') AS dc, "
				+"account_rel_doctorbranch.bookbank_id,pre_name.pre_name_th,doctor.first_name_th,doctor.last_name_th "
				+"FROM "
				+"branch_standard_rel_doctor "
				+"INNER JOIN branch ON branch.branch_id = branch_standard_rel_doctor.branch_id "
				+"INNER JOIN doctor ON branch_standard_rel_doctor.doctor_id = doctor.doctor_id "
				+"INNER JOIN bookbank ON doctor.doctor_id = bookbank.doctor_id "
				+"LEFT JOIN account_rel_doctorbranch ON bookbank.bookbank_id = account_rel_doctorbranch.bookbank_id "
				+"AND branch_standard_rel_doctor.branch_id = account_rel_doctorbranch.doctor_branch_id  "
				+"INNER JOIN pre_name ON doctor.pre_name_id = pre_name.pre_name_id "
				+"WHERE doctor.doctor_id = "+docID+" AND account_rel_doctorbranch.bookbank_id = "+bookID+" "
				+"GROUP BY branch_standard_rel_doctor.branch_id "
				+"ORDER BY account_rel_doctorbranch.id DESC ";
			

		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql);
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			obj.put("branchID", rs.getString("branch_standard_rel_doctor.branch_id"));
			obj.put("branchName", rs.getString("branch.branch_name"));
			listjson.add(obj);
			}
		
	
	out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>