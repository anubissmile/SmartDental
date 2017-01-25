<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONValue"%>
   
<% 
	DateUtil dateUtil = new DateUtil();

	String day = dateUtil.CnvToYYYYMMDDEngYear(dateUtil.curDateTH(), '-');

	String event = request.getParameter("event");
	String time1 = request.getParameter("time1").replace("T", " ");
	String time2 = request.getParameter("time2").replace("T", " ");
	String color = request.getParameter("color");
	String hong	 = request.getParameter("hong");

	List listjson = new LinkedList();
	DBConnect agent 	= new DBConnect(); 
	Connection conn		= null;
	ResultSet rs = null; 
	conn = agent.getConnectMYSql();
	
	String sql = "insert into fullcalendar (title,start,end,color,mor,customer, hong) "+
			"values('"+event+"','"+time1+"','"+time2+"','"+color+"','พ.เดโม ไกรสร','ด.ช. อรุนสักดิ์ ยัดสบด','"+hong+"') ";
	Statement pStmt = conn.createStatement();
	pStmt.executeUpdate(sql);
	pStmt.close();
	
	sql = "SELECT a.id,a.start,a.title,a.end,a.color,a.mor,a.customer "+
			"FROM fullcalendar a "+
			"WHERE hong = '"+hong+"'";
	pStmt = conn.createStatement();
	rs = pStmt.executeQuery(sql);  
	 
	while(rs.next()){  
		JSONObject obj=new JSONObject();
			obj.put("id", rs.getString("id"));  
			obj.put("title", rs.getString("title")+"\n"+rs.getString("mor")+"\n"+rs.getString("customer"));
			//obj.put("resourceId", rs.getString("resourceid"));
			obj.put("start", rs.getString("start"));
			obj.put("end", rs.getString("end"));
			obj.put("backgroundColor", rs.getString("color"));
			
			listjson.add(obj);
	} 
		out.println(listjson); 
	rs.close();
	pStmt.close(); 
	conn.close();
	  		
%>