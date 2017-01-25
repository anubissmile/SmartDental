<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.person.data.*" %>

<%
	DoctorTypeData docData = new DoctorTypeData();
	List <DoctorModel> docModel = docData.select_DocType("", "", "", "");
	for(DoctorModel pnmd : docModel){%>
		<option value="<%=pnmd.getPosition_id()%>"><%=pnmd.getPosition_name_short()+" - "+pnmd.getPosition_name_en() %></option>
<% 	} %>