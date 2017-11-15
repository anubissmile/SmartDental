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
	String service_id = request.getParameter("service_id");
	
	String branch_id = "";
	if(request.getParameter("branch_id")!=null) branch_id = request.getParameter("branch_id");
	
	String sql = "";
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		
		if(branch_id.equals("")){
			sql = "SELECT "
					+"a.lab_id, b.lab_name "
					+"FROM "
					+"lab_service a left join lab b on(b.lab_id = a.lab_id) where ";
					
			if(!service_id.equals("")) sql+= "a.service_id = '"+service_id+"' and ";
			if(!branch_id.equals("")) sql+= "a.branch_id = '"+branch_id+"' and ";
			
			sql += "a.lab_id != '' order by a.lab_id ";
		}else{
			sql = "SELECT "
					+"a.lab_id, b.lab_name "
					+"FROM "
					+"lab_service a left join lab b on(b.lab_id = a.lab_id) "
					+"inner join lab_branch c on(c.lab_id = a.lab_id) "
					+"where ";
					
			if(!service_id.equals("")) sql+= "a.service_id = '"+service_id+"' and ";
			//if(!branch_id.equals("")) sql+= "c.branch_id = '"+branch_id+"' and ";
			
			sql += "a.lab_id != '' order by a.lab_id ";
		}
		
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			
			obj.put("lab_id", rs.getString("lab_id"));  
			obj.put("lab_name", rs.getString("lab_name"));
			
			listjson.add(obj);
				
		}
	}
	
	out.println(listjson); 
	//System.out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>