<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.person.data.*" %>

<%
	Pre_nameData PreNameData = new Pre_nameData();
	List <Pre_nameModel> prenameModel = PreNameData.select_pre_name("", "", "");
	for(Pre_nameModel pnmd : prenameModel){%>
		<option value="<%=pnmd.getPre_name_id()%>"><%=pnmd.getPre_name_th()%>/<%=pnmd.getPre_name_en()%></option>
<% 	} %>