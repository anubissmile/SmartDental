<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="backend-nav-left.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="backend-lab-top.jsp" %>
			</div>
		</div>
		
<script>
	$(document).ready(function(){
		$( ".m-setting" ).addClass( "uk-active" );
	}); 
</script>
	</body>
</html>