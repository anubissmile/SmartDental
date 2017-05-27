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
		<title>Smart Dental:Check In แพทย์</title>
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
						<!-- BREAD CRUMBS -->
						<ul class="uk-breadcrumb">
						    <li class="uk-active"><span>กำหนดการเข้างานแพทย์</span></li>
						    <li><a href="DentistScheduleCheckinRoom">กำหนดแพทย์ประจำห้อง</a></li>
						</ul>
						<!-- BREAD CRUMBS -->
						<!-- Action error & messages -->
						<s:if test="%{#request.alertMSG != null}">
						<div class="uk-alert uk-alert-warning" data-uk-alert>
							<li class="uk-alert-close uk-close"></li>
							<p><s:property value="#request.alertMSG" /></p>
						</div>
						</s:if>
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

						<form id="chk" action="DentiStscheduleCheck" method="post" class="uk-form">
							<fieldset>
								<div class="uk-grid">
								<h2 class="uk-width-1-2">
								<div class="uk-width-1-1 uk-margin-medium-bottom">
			 					<ul class="uk-tab" data-uk-switcher="{
			 							connect:'#branch-active',
			 							animation: 'fade'
			 						}">
								    <li class="uk-active"><a href="#">Waiting</a></li>
								    <li><a href="#">Check IN</a></li>
								    <li><a href="#">Check OUT</a></li>
								</ul>
								</div>
								</h2>
								<h2 class="uk-width-1-2 uk-text-right">เพิ่มแพทย์ฉุกเฉิน
								<a href="#doctor_emergency" class="uk-button uk-button-success" data-uk-modal><i class=" uk-icon-plus"></i></a></h2>
								</div>
								<ul class="uk-width-1-1 uk-switcher" id="branch-active"> 
								<li class="uk-active">								
								<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
									<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-condensed">
										<thead>
											<tr>
												<th class="uk-text-center">ชื่อ</th>
												<th class="uk-text-center">เวลา</th>
												<th class="uk-text-center">สถานะ</th>
												<th class="uk-text-center">จัดการ</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="schList">
												<tr>
													<s:if test="checkInStatus == 'Waiting' ">
													<th class="uk-text-center"><s:property value="pre_name_th" /><s:property value="first_name_th" /> <s:property value="last_name_th" /></th>
													<th class="uk-text-center"><s:property value="startDateTime" /> ถึง <s:property value="endDateTime" /></th>
													<th class="uk-text-center"><s:property value="checkInStatus" /></th>	
													<th class="uk-text-center "><button type="button" data-workdayid='<s:property value="workDayId" />'  data-doctorid='<s:property value="doctorId" />' data-statusname='<s:property value="checkInStatus" />' class="uk-button uk-button-success login">Login</button></th>
													</s:if>												
												</tr>
											</s:iterator>
										</tbody>
									</table>
									</div>
								</div>
								</li>
								<li>								
								<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
									<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-condensed">
										<thead>
											<tr>
												<th class="uk-text-center">ชื่อ</th>
												<th class="uk-text-center">เวลา</th>
												<th class="uk-text-center">สถานะ</th>
												<th class="uk-text-center">จัดการ</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="schList">
												<tr>
													<s:if test="checkInStatus == 'CheckIn' ">
													<th class="uk-text-center"><s:property value="pre_name_th" /><s:property value="first_name_th" /> <s:property value="last_name_th" /></th>
													<th class="uk-text-center"><s:property value="startDateTime" /> ถึง <s:property value="endDateTime" /></th>
													<th class="uk-text-center"><s:property value="checkInStatus" /></th>													
													<th class="uk-text-center"><button type="button" data-workdayid='<s:property value="workDayId" />'  data-doctorid='<s:property value="doctorId" />' data-statusname='<s:property value="checkInStatus" />' class="uk-button uk-button-success login">Logout</button></th>
													</s:if>												
												</tr>
											</s:iterator>
										</tbody>
									</table>
									</div>
								</div>
								</li>
								<li>								
								<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
									<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-condensed">
										<thead>
											<tr>
												<th class="uk-text-center">ชื่อ</th>
												<th class="uk-text-center">เวลา</th>
												<th class="uk-text-center">สถานะ</th>
												<th class="uk-text-center">จัดการ</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="schList">
												<tr>
													<s:if test="checkInStatus == 'CheckOut' ">												
													<th class="uk-text-center"><s:property value="pre_name_th" /><s:property value="first_name_th" /> <s:property value="last_name_th" /></th>
													<th class="uk-text-center"><s:property value="startDateTime" /> ถึง <s:property value="endDateTime" /></th>
													<th class="uk-text-center"><s:property value="checkInStatus" /></th>
													<th class="uk-text-center"></th>
													</s:if>											
												</tr>
											</s:iterator>
										</tbody>
									</table>
									</div>
								</div>
								</li>								
								</ul>								
							</fieldset>
						</form>
					</div>
				</div>
				<!-- END-FORM -->
					<div id="doctor_emergency" class="uk-modal ">
						<form action="AddDentistEmergency" method="post"> 
					    <div class="uk-modal-dialog uk-form" >
					    	<div class="uk-modal-header">เพิ่มแพทย์ฉุกเฉิน</div>
				         	<div class="uk-modal-body uk-grid">				         	
				  				<div class="uk-width-2-4">
				  				<span>ชื่อแพทย์</span><br>
								<s:select  style="width:200px" list="doctorWorkList" name="schModel.doctorId" required="true" headerKey="" headerValue = "กรุณาเลือก" />
				         		</div>
				         		<div class="uk-width-1-4 dpicker-wrap">
				  				<span>เริ่มเวลา</span>
				         		<s:textfield value="00:00" name="schModel.startDateTime" id="start" cssClass="clockpicker" class="dpicker" data-placement="left" data-autoclose="true" data-align="top"  /> 
				         		</div>
				         		<div class="uk-width-1-4 dpicker-wrap">
				  				<span>ถึงเวลา</span>
				         		<s:textfield value="00:00" name="schModel.endDateTime" id="end" cssClass="clockpicker" data-placement="left" class="dpicker" data-autoclose="true" data-align="top" /> 
				         		</div>
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">
			                    <button class="uk-button uk-button-default uk-modal-close">ยกเลิก</button>
			                    <button  type="submit" class=" uk-button uk-button-default uk-button-danger"> ยืนยัน</button>
                			</div>

					    </div>
					    </form>
					</div>

			</div>
		</div>
		<script>
			$(document).ready(function() {
			
				/*SET CLOCKPICKER*/
				$('#start').clockpicker();
				$('#end').clockpicker();

				$("select").select2();
			});
			$('.login').click(function(){
				var doctorid=$(this).data("doctorid"); 
				var statusname=$(this).data("statusname");
				var workdayid=$(this).data("workdayid");
				swal({
		   			  title: 'อนุมัติการทำงาน',
		   			  text: "กดอนุมัติเมื่อท่านต้องการอนุมัติ",
		   			  type: 'warning',
		   			  showCancelButton: true,
		   			  confirmButtonColor: '#3085d6',
		   			  cancelButtonColor: '#d33',
		   			  confirmButtonText: 'อนุมัติ',
		   			  cancelButtonText: 'ยกเลิก',
		   			  confirmButtonClass: 'uk-button uk-button-primary',
		   			  cancelButtonClass: 'uk-button uk-button-danger',
		   			  buttonsStyling: false
		   			}).then(function (isConfirm) {
		   			 if (isConfirm) {
		   				$.ajax({
		   			        type: "POST",
		   			        url: "ajax/ajax-update-doctorStatus.jsp",
		   			        data: {
		   			        	doctorid:doctorid,
		   						statusname:statusname,
		   						workdayid:workdayid
		   			        },
		   			        async:true,
		                    success: function(result){
	                    	var obj = JSON.parse(result);
		                     if(obj.status = 'success'){
		                               $("#chk").submit();
		                     }else{
		                         swal(
		                                 'การอนุมัติสำเร็จ',
		                                 'คลิกตกลงเพื่อทำรายการใหม่',
		                                 'success'
		                               );
		                              } 
		                     }
		   			        
		   			     })}else{
		   			    	swal(
		  		   			      'ยกเลิกการทำรายการแล้ว',
		  		   			      'ข้อมูลจะไม่มีการเปลี่ยนแปลง)',
		  		   			      'error'
		  		   			    )
		   			     }
		   			}, function (dismiss) {
		   			  // dismiss can be 'cancel', 'overlay',
		   			  // 'close', and 'timer'
		   			  if (dismiss === 'cancel') {
		   			    swal(
		   			      'ยกเลิกการทำรายการแล้ว',
		   			      'ข้อมูลจะไม่มีการเปลี่ยนแปลง)',
		   			      'error'
		   			    )
		   			  }
		   			})
				});
				




		</script>
			
	</body>
</html>