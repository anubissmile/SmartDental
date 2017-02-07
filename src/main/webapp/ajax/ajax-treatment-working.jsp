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
	
	String method_type = request.getParameter("method_type");
	String title_status = "";
	
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		
		String sql = "SELECT a.hn, a.room_id, a.status, TIMESTAMPDIFF(MINUTE, a.datetime, now()) as minute, "
				+"concat(b.first_name_th,' ',b.last_name_th) as hnname, c.pre_name_th " 
				+"FROM "
				+"treatment_working a inner join patient b on(b.hn = a.hn) inner join pre_name c on(c.pre_name_id = b.pre_name_id) " 
				+"where a.status != 'C' order by a.datetime ";
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			
			title_status = rs.getString("status");
			
			if(title_status.equals("W")) title_status = "รอการรักษา";
			else if (title_status.equals("P")) title_status = "ห้องตรวจ "+rs.getString("room_id");
			else if (title_status.equals("S")) title_status = "รักษาเสร็จเรียบร้อย";
			
			obj.put("hn", rs.getString("hn"));
			obj.put("hnname", rs.getString("pre_name_th")+" "+rs.getString("hnname"));  
			obj.put("title_status", title_status);
			obj.put("minute", rs.getString("minute")+" นาทีที่แล้ว");
			
			listjson.add(obj);
				
		} 
	} 
	
	out.print(listjson); 
	//System.out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>