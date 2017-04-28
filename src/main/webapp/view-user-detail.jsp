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
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:ค้นหารายชื่อครอบครัว</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head>
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
						<strong><h2>ข้อมูลผู้ใช้งานระบบ</h2></strong>
						<s:iterator value="authList" var="al">
						<div class="uk-grid uk-grid-collapse">
							<div class="uk-width-2-6 uk-text-right bdr1 pr10 mr10">
								<img src="<s:property value='#al.picture' />" alt="" width="200">
							</div>
							<div class="uk-width-2-4 uk-text-left">
								<h3>
									<s:property value="#al.pre_name" /> 
									<s:property value="#al.name" /> 
									<s:property value="#al.lastname" />
								</h3>
								<h2>ข้อมูลทั่วไป</h2>
								<dt><strong>username</strong></dt>
								<dd><small><s:property value="#al.empUsr" /></small></dd>
								<dt><strong>ไอดี</strong></dt>
								<dd><small><s:property value="#al.empId" /></small></dd>
								<dt><strong>วันเกิด</strong></dt>
								<dd><small><s:property value="#al.birth" /></small></dd>
								<dt><strong>อายุ</strong></dt>
								<dd><small><s:property value="#al.age" /></small></dd>
								<dt><strong>รหัสประชาชน</strong></dt>
								<dd><small><s:property value="#al.identification" /></small></dd>
								<dt><strong>ที่อยู่</strong></dt>
								<dd><small><s:property value="#al.strAddr" /></small></dd>
							</div>
						</div>
						<div class="uk-grid uk-grid-collapse">
							<div class="uk-width-2-6 uk-text-right bdr1 pr10 mr10">
								<h2>หมายเลขติดต่อ</h2>
								<dt><strong>โทรศัพท์</strong></dt>
								<dd><small><s:property value="#al.phone" /></small></dd>

								<h2>สถานะใช้งานระบบ</h2>
								<dt><strong>สถานะ</strong></dt>
								<dd><small><s:property value="#al.roleNameTH" /></small></dd>
							</div>
							<div class="uk-width-2-4 uk-text-left">
								<h2>สถานะการทำงาน</h2>
								<dt><strong>สาขา</strong></dt>
								<dd><small><s:property value="#al.branchName" /></small></dd>
								<dt><strong>บริษัท</strong></dt>
								<dd><small><s:property value="#al.brandName" /></small></dd>
								<dt><strong>วันเริ่มจ้างงาน</strong></dt>
								<dd><small><s:property value="#al.hireDate" /></small></dd>
								<dt><strong>หมายเหตุ</strong></dt>
								<dd><small><s:property value="#al.remark" /></small></dd>
							</div>
						</div>
						</s:iterator>
					</div>
					<div class="uk-width-1-10 uk-text-center"></div>
				</div>
				<!-- END-FORM -->
				<br>
				<br>
				<br>
				<br>
				<br>
			</div>
		</div>
		<script>
			$(document).on('click', '#removeFamUser', fn_buttonmodal_habndler).ready(function(){
				/* Do any thing. */
			});
			
			function fn_buttonmodal_habndler(e)
			{
				
			    console.log('In Function');
			    var fam_id = $(e.target).data('fam_id');
			    $('#modalFamUser').on({
			        'uk.modal.show':function(){
			        	$("#famId").val(fam_id);
			        },
			        'uk.modal.hide':function(){
			                    //hide modal
			        }
			    }).trigger('uk.modal.show');
			}
		</script>
			
	</body>
</html>