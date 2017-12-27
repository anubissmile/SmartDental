<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*" %>
<%@ page import="com.smict.treatment.data.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:การเงิน</title>
		<link href="css/uikit.gradient.css"rel="stylesheet"/>
		<link href="css/style.css"rel="stylesheet">
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div  class="uk-grid ">
			<div class="uk-width-1-1 uk-text-center">
			<h2 style="margin-top: 10px;">เลือกรูปแบบการออกใบเสร็จ</h2>
        	
			</div>
		</div>
		<div  class="uk-grid ">
			<div class="uk-width-1-1 uk-text-center">
			<label class=" uk-h3"><input type="checkbox"> ประกันสังคม<small>(ออกใบเสร็จสำหรับเบิกประกันสังคม)</small></label>
			</div>
		</div>
		<div  class="uk-grid uk-flex-middle "style="padding-top:5em">	
           	<div class="uk-width-1-2 uk-text-center">
           		<a href="finance-split-bill-list.jsp" class="uk-button uk-button-large uk-button-primary uk-width1-1 "><br>
           		<i class="uk-icon-list uk-h1"></i><br>
           		<span class="uk-h2">
           		เลือกจากรายการ
           		<br><small  class="uk-h3 uk-hidden-small">(เลือกออกใบเสร็จจากรายการรักษาทีละรายการ)</small></span><br>
           		</a>
           	</div>
           	<div class="uk-width-1-2 uk-text-center ">
           		<a href="finance-split-bill-sum.jsp" class="uk-button uk-button-large uk-button-primary uk-width1-1  "><br>
           		<i class="uk-icon-money uk-h1"></i><br>
           		<span class="uk-h2">
           		กำหนดจำนวนเงิน
           		<br><small  class="uk-h3 uk-hidden-small">(กำหนดจำนวนเงินในแต่ละใบเสร็จ)</small></span><br>
           		</a>
	       	</div>
        </div>	 
           	 
           	 
           	 
			<script src="js/jquery-2.2.4.min.js"></script>
			<script src="js/uikit.min.js"></script>
	</body>
</html>