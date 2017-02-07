<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.codehaus.jettison.json.*"%>
<% 
	if(request.getParameter("branch_code") != null){
		String branch_code = request.getParameter("branch_code");
		GeneratePatientBranchID genID = new GeneratePatientBranchID();
		genID.generateBranchHN(branch_code);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("BranchHN", genID.getResultID()[0]);
		jsonObj.put("NextNumber", genID.getResultID()[1]);
		response.setContentType("application/json");
		response.setHeader("cache-control", "no-cache");
		PrintWriter pw = response.getWriter();
		pw.println(jsonObj.toString());
		pw.flush();
	}

%>