<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.person.data.*" %>
<%
	PatientData pData = new PatientData();
	List <PatientModel> pModel = pData.select_Identification_Type();
	for(PatientModel pnmd : pModel){%>
		<option value="<%=pnmd.getIdentification_type()%>"><%=pnmd.getIdentification()%></option>
<% 	} %>