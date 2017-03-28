<%@page import="com.smict.product.model.ProductModel"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="com.smict.person.data.ContactData" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%@ page import="com.smict.product.data.ProductData" %>
<%@ page import="com.smict.person.data.FamilyData" %>
<%@page import="com.smict.person.data.CongenitalData"%>
<%@page import="com.smict.person.model.CongenitalDiseaseModel"%>
<%@page import="com.smict.person.data.PatientRecommendedData"%>
<%@page import="com.smict.person.model.RecommendedModel"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	// ProductData product_Data = new ProductData();
	// CongenitalData congen_Data = new CongenitalData();
%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:ลงเวลาแพทย์</title>
	<body>
	
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<!-- START-FORM -->
				<div class="uk-grid uk-grid-collapse uk-margin-large-top">
					<div class="uk-width-1-10 uk-text-center"></div>
					<div class="uk-width-8-10">
						<!-- BREAD CRUMBS -->
						<ul class="uk-breadcrumb">
						    <li><a href="dentist-schedule">กำหนดเวลาแพทย์ประจำห้อง</a></li>
						    <li class="uk-active"><span>ดูตารางเวลา</span></li>
						</ul>
						<!-- BREAD CRUMBS -->

						<!-- ALERT -->
						<s:if test="%{#request.alertMSG != null}">
						<div class="uk-alert uk-alert-warning" data-uk-alert>
							<li class="uk-alert-close uk-close"></li>
							<p><s:property value="#request.alertMSG" /></p>
						</div>
						</s:if>

						<s:if test="hasActionErrors()">
						   <div class="uk-alert uk-alert-danger" data-uk-alert>
					   			<li class="uk-alert-close uk-close"></li>
						      	<s:actionerror/>
						   </div>
						</s:if>

						<s:if test="hasActionMessages()">
						   <div class="uk-alert uk-alert-success" data-uk-alert>
					   			<li class="uk-alert-close uk-close"></li>
						      	<s:actionmessage/>
						   </div>
						</s:if>
						<!-- ALERT -->

						<form action="view-dentist-schedules" method="post" class="uk-form">
							<fieldset>
								<legend><h2>ตารางเวลาแพทย์ประจำห้อง</h2></legend>
								<div class="uk-grid">
									<div class="uk-width-1-3 uk-margin-medium-top">
										<label for="" class="uk-form-label"><h3>วันที่</h3></label>
										<div class="uk-form-controls">
											<s:textfield 
												type="text" 
												id="work_date" 
												class="uk-width-1-1 uk-form-large"
												name="schModel.workDate" 
											/>
										</div>
									</div>
									<div class="uk-width-1-3 uk-margin-medium-top">
										<label for="" class="uk-form-label"><h3>ห้อง</h3></label>
										<div class="uk-form-controls">
											<s:select list="trMap" name="schModel.branchRoomId" 
												class="uk-width-1-1 uk-form-large" />
										</div>
									</div>
									<div class="uk-width-1-3 uk-text-right uk-margin-large-top">
										<label for="" class="uk-form-label"><h3></h3></label>
										<button class="uk-width-1-1 uk-button uk-button-large uk-button-success">เรียกดู</button>
									</div>
								</div>
							</fieldset>
						</form>
						<table class="uk-table uk-table-striped">
							<thead>
								<tr>
									<th>ทันตแพทย์</th>
									<th>ห้อง</th>
									<th>เวลา</th>
									<th>ถึง</th>
								</tr>
							</thead>
							<tbody>
							<s:if test="schList != null">
							<s:iterator value="schList" var="sch">
								<tr>
									<td>
										<s:property value="#sch.first_name_th" /> <s:property value="#sch.last_name_th" />
									</td>
									<td><s:property value="#sch.roomName" /></td>
									<td><s:property value="#sch.startDateTime" /></td>
									<td><s:property value="#sch.endDateTime" /></td>
								</tr>
							</s:iterator>
							</s:if>
							<s:else>
								<tr><td>ไม่พบข้อมูล</td></tr>
							</s:else>
							</tbody>
						</table>
					</div>
					<div class="uk-width-1-10 uk-text-center"></div>
				</div>
				<!-- END-FORM -->
			</div>
		</div>
		<script>
			$(document).ready(function() {
				/*SET CLOCKPICKER*/
				$('.clockpicker').clockpicker();

				/*SET DATEPICKER*/
				$("#work_date").datepicker({
				    format: "yyyy-m-d",
			        clearBtn: true,
			        autoclose: true,
			        todayHighlight: true
			    });
			});			
		</script>
			
	</body>
</html>