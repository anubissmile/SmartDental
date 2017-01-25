<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.person.data.*" %>
<%
	BookBankData bookData = new BookBankData();
	BookBankModel bookModel = new BookBankModel();
	List <BookBankModel> bankList = bookData.Get_banklist();
	for(BookBankModel pnmd : bankList){%>
		<option value="<%=pnmd.getBank_id()%>"><%=pnmd.getBank_name_th()%></option>
<% 	} %>