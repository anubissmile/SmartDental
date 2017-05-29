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
	String addr_provinceid = request.getParameter("addr_provinceid");
	String province_id = request.getParameter("province_id");
	
	Connection conn = null;
	Statement Stmt = null;
	if(method_type.equals("get")){
		//AND a.PROVINCE_ID = 14
		String sql = "SELECT "
				+"a.amphur_id, a.amphur_name "
				+"FROM "
				+"amphures AS a where ";

		if(province_id != null & !province_id.equals("")){
			sql += " a.PROVINCE_ID = '" + province_id + "' AND ";
		}
				
		if(addr_provinceid != null && !addr_provinceid.equals("")){
			sql+= "a.province_id = '"+addr_provinceid+"' and ";
		}
		
		sql += "a.amphur_id != '' order by a.amphur_name ";
		System.out.println(sql);
		conn = dbcon.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql);
		 
		while(rs.next()){  
			
			JSONObject obj=new JSONObject();
			
			obj.put("addr_aumphurid", rs.getString("amphur_id"));  
			obj.put("amphur_name", rs.getString("amphur_name"));
			//obj.put("amphur_name_eng", rs.getString("amphur_name_eng"));
			
			listjson.add(obj);
				
		}
	}
	
	out.println(listjson); 
	//System.out.println(listjson); 
	rs.close();
	Stmt.close(); 
	conn.close();
	  		
%>