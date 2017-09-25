<%@page import="org.apache.struts2.ServletActionContext"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="com.smict.auth.AuthAction" %>
<%
	AuthAction auth = new AuthAction();
	auth.authCheck(true);
%>