<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<div class="uk-width-1-1">
					<s:if test="alertStatus != null ">
						<div class="uk-alert uk-alert-<s:property value="alertStatus"/> uk-width-1-1" data-uk-alert>
						    <a href="" class="uk-alert-close uk-close"></a>
						    <p><s:property value="alertMessage"/> </p>
						</div>
					</s:if>
				</div>
			</div>
		</div>
		
	</body>
</html>