<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.person.data.*" %>

<%
	EducationData eduData = new EducationData();
	List <PatientModel> patModel = eduData.select_education_vocabulary("", "", "");
	for(PatientModel pnmd : patModel){%>
		<option value="<%=pnmd.getEducation_vocabulary_id()%>"><%=pnmd.getEducation_vocabulary_th() %></option>
<% 	} %>