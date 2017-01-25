<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.person.data.*" %>
<%
	AddressData addrData = new AddressData();
    AddressModel mo = new AddressModel();
	List <AddressModel> addrModel = addrData.getAddress_type(mo);
	for(AddressModel pnmd : addrModel){%>
		<option value="<%=pnmd.getAddr_typeid()%>"><%=pnmd.getAddr_typename()%></option>
<% 	} %>