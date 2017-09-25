<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.product.model.*" %>
<%@ page import="com.smict.all.model.*" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%@ page import="com.smict.product.data.ProductData" %>
<%
	ProductData product_Data = new ProductData(); 
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Smart Dental:Treatment</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
			<!-- Action error & messages -->
			<s:if test="%{alertError.length() > 0}">
			<div class="uk-alert uk-alert-danger" data-uk-alert>
				<li class="uk-alert-close uk-close"></li>
				<p><s:property value="alertError" /></p>
			</div>
			</s:if>
			<s:if test="%{alertSuccess.length() > 0}">
			<div class="uk-alert uk-alert-success" data-uk-alert>
				<li class="uk-alert-close uk-close"></li>
				<p><s:property value="alertSuccess" /></p>
			</div>
			</s:if>
			<s:if test="%{alertMSG.length() > 0}">
			<div class="uk-alert uk-alert-warning" data-uk-alert>
				<li class="uk-alert-close uk-close"></li>
				<p><s:property value="alertMSG" /></p>
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
			<!-- Action error & messages -->
 			<s:form class="uk-form" 
 				action="add-treatment-continuous-preference-%{treatmentModel.treatmentID}" 
 				method="post" 
 				theme="simple"
 				id="frmTreatmentMaster">
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-width-1-1 uk-margin-large"></div>
					<div class="uk-width-1-10"></div>
					<div class="uk-width-8-10">
						<div class="uk-width-1-1">
							<h2>รายการการรักษา</h2><br>
						</div>
						<!-- Tab treatment list. -->
						<div class="uk-width-1-1 uk-text-center">
							<ul class="uk-tab" data-uk-tab="{connect:'#ldc-treat-list'}">
							    <li class="uk-active"><a href="">การรักษา</a></li>
							    <li><a href="">การรักษาต่อเนื่อง</a></li>
							</ul>
							<ui id="ldc-treat-list" class="uk-switcher uk-margin">
								<li id="ldc-treat-table" class="uk-margin-large-bottom">
									<table class="uk-table uk-table-striped uk-table-hover uk-table-condensed"
										id="ldc-tb-treatment">
										<thead>
											<tr>
												<th class="uk-text-center">#</th>
												<th class="uk-text-center">การรักษา</th>
												<th class="uk-text-center">จัดการ</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<td>#</td>
												<td class="uk-text-center">การรักษา</td>
												<td class="uk-text-center">จัดการ</td>
											</tr>
										</tfoot>
										<tbody>
											<s:iterator value="treatmentList">
											<tr>
												<td><s:property value="iterator" /></td>
												<td>
													<strong><s:property value="treatmentNameTH" /></strong>
													<br>
													<small><s:property value="treatmentNameEN" /></small>
												</td>
												<td class="uk-text-center">
													<div class="uk-button-dropdown" data-uk-dropdown="{mode:'click'}">

													    <button class="uk-button uk-button-primary">
													    	จัดการ
													    	<li class="uk-icon-sort-down"></li>
												    	</button>
													    <div class="uk-dropdown uk-dropdown-small">
													        <ul class="uk-nav uk-nav-dropdown">
													            <li><s:a href="">ดูรายละเอียด</s:a></li>
													            <li><s:a href="treatment%{treatmentID}-edit">แก้ไข</s:a></li>
													            <li><s:a href="">ลบ</s:a></li>
													        </ul>
													    </div>
													</div>
												</td>
											</tr>
											</s:iterator>
										</tbody>
									</table>
								</li>
								<li id="ldc-treat-continuous-table" class="uk-margin-large-bottom">
									<table id="ldc-table-continuous"
									 class="uk-table uk-table-striped uk-table-hover uk-table-condensed uk-margin-large-top"
										>
										<thead>
											<tr>
												<th class="uk-text-center">#</th>
												<th class="uk-text-center">การรักษา (ต่อเนื่อง)</th>
												<th class="uk-text-center">จัดการ</th>
											</tr>
										</thead>
										<tfoot>
											<tr>
												<td>#</td>
												<td class="uk-text-center">การรักษา</td>
												<th class="uk-text-center">จัดการ</th>
											</tr>
										</tfoot>
										<tbody>
											<s:iterator value="treatmentContinuousList">
											<tr>
												<td><s:property value="iterator" /></td>
												<td>
													<strong><s:property value="treatmentNameTH" /></strong>
													<br>
													<small><s:property value="treatmentNameEN" /></small>
												</td>
												<td class="uk-text-center">
													<div class="uk-button-dropdown" data-uk-dropdown="{mode:'click'}">
													    <button class="uk-button uk-button-primary">
													    	จัดการ
													    	<li class="uk-icon-sort-down"></li>
												    	</button>
													    <div class="uk-dropdown uk-dropdown-small">
													        <ul class="uk-nav uk-nav-dropdown">
													            <li><s:a href="">ดูรายละเอียด</s:a></li>
													            <li><s:a href="treatment%{treatmentID}-edit">แก้ไข</s:a></li>
													            <li><s:a href="">ลบ</s:a></li>
													        </ul>
													    </div>
													</div>
												</td>
											</tr>
											</s:iterator>
										</tbody>
									</table>
								</li>
							</ui>
						</div>
						<!-- Tab treatment list. -->
					</div>
					<div class="uk-width-1-10"></div>
					<div class="uk-width-1-1 uk-margin-large"></div>
				</div>
			</s:form>
			</div>
		</div>



		<!-- MODAL ZONE -->
		<!-- Setting treatment -->
		<div id="modal-treat" class="uk-modal">
			<div class="uk-modal-dialog uk-modal-dialog-large uk-form">
				<a class="uk-modal-close uk-close"></a>
				<div class="uk-modal-header"><i class="uk-icon-stethoscope"></i> การรักษา</div>
				<form action="" id="treatment-listmodal">
					<div class="uk-width-1-1 uk-overflow-container">
						<table class="display nowrap compact stripe hover cell-border order-column" 
							id="treatment-datatable">
							<thead>
								<tr class="hd-table treat-table">
									<th class="uk-text-center">#</th>
									<th class="uk-text-center">รหัส</th>
									<th class="uk-text-center">การรักษา</th>
									<th class="uk-text-center">จัดการ</th>
								</tr>
							</thead>
							<tbody id="treat-list" data-treatment-id='<s:property value="treatmentModel.treatmentID" />' >
								<s:iterator value="treatmentList">
								<tr class="ldc-tbrow-treat" id="">
									<td class="uk-text-center uk-width-1-10">
										<s:checkbox name="treatmentModel.treatmentID" 
											fieldValue="%{treatmentID}:#:%{treatmentNameTH}:#:%{treatmentNameEN}:#:%{treatmentCode}" 
											value="%{treatmentID}:#:%{treatmentNameTH}:#:%{treatmentNameEN}:#:%{treatmentCode}" 
											theme="simple" 
										/>
									</td>
									<td class="uk-text-center trat_code uk-width-2-10">
										<strong><s:property value="treatmentCode" /></strong>
									</td>
									<td class="uk-text-center treat_name uk-width-6-10">
										<strong><s:property value="treatmentNameTH" /></strong>
										<br><small><s:property value="treatmentNameEN" /></small>
									</td>
									<td class="uk-text-center uk-width-1-10">
										Manages
									</td>
								</tr>		
								</s:iterator>
							</tbody>
						</table>
					</div>
					<div class="uk-modal-footer uk-text-right">
						<button class="uk-modal-close uk-button uk-button-success" 
							name="btn_submit_be_allergic" 
							id="ldc-modal-btn-add-treat">
							ตกลง
						</button>
					</div>
				</form>
			</div>
		</div>
		<!-- Setting treatment -->
		<!-- MODAL ZONE -->
		
		<script>
		/**
		 * [pageStat = Global status variables.]
		 * @type {JSON Object}
		 * @author [wesarut | wesarut.khm@gmail.com]
		 */
		var pageStat = { }

		$(document).ready(function(){
			var tbTreatment = makeDataTable($('#ldc-tb-treatment'));
			var tbContinuous = makeDataTable($('#ldc-table-continuous'));
		}); // End ready; 



	/**
	 * ============================================================================ *
	 * 									FUNCTION.
	 * ============================================================================ *
	 */
	var makeDataTable = function(obj){
		return obj.DataTable();
	}

		</script>		
	</body>
</html>