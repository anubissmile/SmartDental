<%@page import="org.apache.log4j.Logger"%>
<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="com.smict.promotion.data.PromotionDetailData"%>
<%@page import="ldc.util.Validate"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.codehaus.jettison.json.JSONException" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%
		
		Validate clsValidate = new Validate();
		String Name = "";
		if(clsValidate.Check_String_notnull_notempty(request.getParameter("q").toString())) Name =request.getParameter("q").toString();
		
		String treatmenttype = "";
		if(clsValidate.Check_String_notnull_notempty(request.getParameter("treatmenttype").toString())) treatmenttype =request.getParameter("treatmenttype").toString();
		
		
		PromotionDetailData promotionDB = new PromotionDetailData();
		JSONObject jOBJ = new JSONObject();
		JSONArray jsonResponse = null;

			//Is treatment
			jsonResponse =	promotionDB.getJsonArrayTreatment(Name,treatmenttype);
		
		
		jOBJ.put("more", false);
		jOBJ.put("results", jsonResponse);
		response.setContentType("application/json");
		response.setHeader("cache-control", "no-cache");
		PrintWriter pw = response.getWriter();
		pw.println(jOBJ.toString());
		pw.flush();
%>