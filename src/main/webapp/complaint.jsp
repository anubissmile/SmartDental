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
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-width-1-3 uk-panel-box">
						<h4 class="hd-text uk-text-primary">ร้องเรียน </h4>
					</div>
					<div class="uk-width-2-3 uk-panel-box uk-form">
						<h4 class="hd-text uk-text-primary">รายละเอียด</h4>
						<input type="text" class="uk-form-small"/>
						<input type="text" class="uk-form-small"/>
					</div>
				</div>
			</div>
		</div>
		<script>
			$(document).ready(function(){
				$( ".m-complaint" ).addClass( "uk-active" );
			});
		</script>
	</body>
</html>