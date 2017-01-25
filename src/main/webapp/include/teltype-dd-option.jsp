<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.person.data.*" %>
<%
	TelephoneData telData = new TelephoneData();
	List <TelephoneModel> telModel = telData.select_telType();
	for(TelephoneModel pnmd : telModel){%>
		<option value="<%=pnmd.getTel_typeid()%>"><%=pnmd.getTel_typename()%></option>
<% 	} %>