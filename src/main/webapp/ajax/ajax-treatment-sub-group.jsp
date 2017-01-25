<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONValue"%>
<%@page import="com.smict.treatment.data.*"%>
<%@page import="com.smict.all.model.*"%>
<%
	List listjson = new LinkedList();
	
	String treatment_type_id = "";

	if(request.getParameter("treatment_type_id")!=null) treatment_type_id = request.getParameter("treatment_type_id");
	
	
	
	TreatmentGroupData tGroupData = new TreatmentGroupData();
	List<TreatmentMasterModel> treatmentGList = tGroupData.Select_treatment_group("", "",treatment_type_id);
		 
		for(TreatmentMasterModel tgm: treatmentGList){  
			
			JSONObject obj=new JSONObject();
			
			obj.put("treatment_group_code", tgm.getTreatment_group_code());  
			obj.put("treatment_group_name", tgm.getTreatment_group_name());
			
			listjson.add(obj);
				
		}
	
	
	out.println(listjson); 
	//System.out.println(listjson); 
	  		
%>