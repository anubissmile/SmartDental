<%@ page language="java" import="java.util.*,java.text.DecimalFormat"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*"%>
<%@ page import="com.smict.treatment.data.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:รอคิว</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head>
<body>
<div class="uk-grid uk-grid-collapse">
		<div class="uk-width-1-10">
			<%@include file="nav-right.jsp" %>
		</div> 
		<div class="uk-width-9-10">
			<%@include file="nav-top.jsp" %>
			<div class="uk-grid uk-grid-collapse">
					
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
				
				<div class="uk-width-1-10 padding5 "></div>
				<div class="uk-width-2-10 padding5" id="patient-list-col">
					<div class="uk-grid  padding5  border-gray uk-panel-box-primary">
						<div class="uk-width-1-1 padding5">
							<h3 class="uk-text-center bg-gray border-gray uk-text-primary uk-text-medium">
								คนไข้
							</h3>
						</div>
						<div class="uk-width-1-1 padding5">
							<a class="uk-button uk-button-success uk-button-mini uk-align-medium-right"
								title="เพิ่มคิวคนไข้"
								href="selectPatient">
								<i class="uk-icon-wheelchair margin5"></i>
								<span>เพิ่มคิวคนไข้</span>
							</a>
						</div>
						<div class="uk-width-1-1 padding5">
							<s:iterator value="treatList" var="tl">
							<s:if test="#tl.qstatusKey == 1">
							<h4 class="hd-text border-gray bg-gray padding5">
								<li>
									<small class="uk-text-primary">ชื่อคนไข้ : </small>
									<s:property value="#tl.preName" /> 
									<s:property value="#tl.firstNameTH" /> 
									<s:property value="#tl.lastNameTH" /> 
								</li>
								<li>
									<small class="uk-text-primary">HN : </small> 
									<s:property value="#tl.hn" />
								</li>
								<li>
									<div class="uk-text-right wrap-pat-btn">
										<a class="uk-button uk-button-danger uk-button-mini"
											href="remove-queue-<s:property value='#tl.queueId' />" >
											ยกเลิก 
										</a>
										<a href="#mh-id" 
											class="uk-button uk-button-primary uk-button-mini select-room"
											data-queue="<s:property value='#tl.queueId' />" data-hn='<s:property value="#tl.hn" />'
											data-uk-modal="{target:'#my-id'}"> 
											<span>เลือกห้อง</span>
											<i class="uk-icon-sign-in"></i>
										</a>
									</div>
								</li>
							</h4>
							</s:if>
							</s:iterator>
						</div>
					</div>
				</div>
				<div class="uk-width-1-10 padding5 "></div>
				<div class="uk-width-2-10 padding5" id="room-queue-col">
					<div class="uk-grid padding5 border-gray uk-panel-box-primary">
						<div class="uk-width-1-1 padding5">
							<h3 class="uk-text-center bg-gray border-gray uk-text-primary uk-text-medium">
								ห้อง
							</h3>
						</div>
						<div class="uk-width-1-1 padding5">
							<a class="uk-button uk-button-success uk-button-mini uk-align-medium-right"
								title="จัดการแพทย์ประจำห้อง"
								href="DentistScheduleCheckinRoom">
								<i class="uk-icon-user-md margin5"></i>
								<span>จัดการแพทย์ประจำห้อง</span>
							</a>
						</div>
						<div class="uk-width-1-1 padding5">
							<s:iterator value="schList">
							<div class="uk-margin-small-bottom dashed-line">								
								<div class="uk-panel uk-panel-box uk-width-1-1">
								<div class="uk-panel-badge uk-badge uk-badge-success">พร้อมใช้งาน</div>
									<h3 class="uk-panel-title uk-margin-top"><s:property value="pre_name_th" /><s:property value="first_name_th" /> <s:property value="last_name_th" /></h3>
									<div class="uk-grid uk-grid-collapse">
										<div class="uk-width-2-5">
										<small class=" uk-text-primary">ชื่อผู้ช่วยแพทย์ : </small>
										</div>
										<div class="uk-width-3-5">								
											<s:iterator value="employeeList">
												<small class=" uk-text-primary"><s:property value="pre_name_th" /><s:property value="empname_th" /> <s:property value="emplastname_th" /></small><br/>
											</s:iterator>
										</div>
									</div>	
									<h2 class="uk-text-center uk-margin-remove-top">ห้อง <s:property value="roomName" /></h2>										
								</div>
								<div class="uk-width-1-1 padding5">	
									<s:iterator value="treatList" var="tl">
									<s:if test="%{workDayId == #tl.workdayId && #tl.qstatusKey == 2}">
									<h4 class="hd-text border-gray bg-gray padding5">
										<li>
											<small class="uk-text-primary">ชื่อคนไข้ : </small> 
											<s:property value="#tl.preName" /> 
											<s:property value="#tl.firstNameTH" /> 
											<s:property value="#tl.lastNameTH" /> 
										</li>
										<li>
											<small class="uk-text-primary">HN : </small> 
											<s:property value="#tl.hn" /></li>
										<li>
											<div class="uk-text-right">
												<a class="uk-button uk-button-success uk-button-mini" onclick=""
												href="patient-treatmentDone-<s:property value='#tl.queueId' />-<s:property value='#tl.workdayId' />">
													เสร็จสิ้น 
												</a>
												<a class="uk-button uk-button-danger uk-button-mini"
													href="patient-backward-<s:property value='#tl.queueId' />-<s:property value='#tl.hn' />">
													ยกเลิก 
												</a>
												
											</div>
										</li>
									</h4>						
									</s:if>
									</s:iterator>	
								</div>
							</div>
							</s:iterator>
						</div>
	
					</div>
				</div>
				<div class="uk-width-1-10 padding5 "></div>
				<div class="uk-width-2-10 padding5 border-gray uk-panel-box-primary"  id="wait-outcomes-col">
					<div class="uk-grid   uk-grid-collapse ">
						<div class="uk-width-1-1 padding5">
							<h3 class="uk-text-center bg-gray border-gray uk-text-primary uk-text-medium">
								รอผลการรักษา
							</h3>
						</div>
						<s:iterator value="schList">
						<div class="uk-width-1-1 padding5">
							
							<h4 class="hd-text border-gray bg-gray padding5">
								<small class=" uk-text-primary">ชื่อแพทย์ : 
								<s:property value="pre_name_th" /><s:property value="first_name_th" /> <s:property value="last_name_th" /></small>
									<s:iterator value="treatmentpatAndqueuelist" var="tl">
									<s:if test="%{workDayId == #tl.workdayId}">
										<li>
											<small class="uk-text-primary">ชื่อคนไข้ : 
											<s:property value="#tl.preName" /> 
											<s:property value="#tl.firstNameTH" /> 
											<s:property value="#tl.lastNameTH" /> </small> 
										</li>
										<li>
											<small class="uk-text-primary">HN : 
											<s:property value="#tl.hn" /></small></li>
																
											<div class="uk-text-right">
												<a href="getPatientShowAfterSaveTreatment-<s:property value="#tl.treatment_patient_ID" />" class="uk-button uk-button-primary uk-button-mini uk-contaier-center"> 
													<span>บันทึกผลการรักษา</span>
													<i class="uk-icon-stethoscope"></i>
												</a>
											</div>
										<hr>
									</s:if>
									</s:iterator>								
								
							</h4>

						</div>
						</s:iterator>
					</div>
	
				</div>
			</div>
		</div>
</div>
<!-- MODAL ZONE -->
<div id="my-id" class="uk-modal">
	<div class="uk-modal-dialog">
		<a class="uk-modal-close uk-close"></a>
		<div class="uk-width-1-1">
			<div class="uk-width-1-1 uk-container-center">
				<form action="put-patient-into-room" method="post">
					<table id="QueueTable"
						class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1 table-components-medicine">
						<thead>
							<tr class="hd-table">
								<th class="uk-text-center"></th>
								<th class="uk-text-center">ชื่อแพทย์ / ห้อง</th>
								<th class="uk-text-center"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="uk-text-center uk-width-2-10">
								</td>
								<td class="uk-text-center uk-width-5-10">
									<select
										class="uk-form-small uk-width-1-1 dcode"
										name="treatModel.workdayId" >
										<s:iterator value="schList" >
										<option value="<s:property value='workDayId' />">											
											<s:property value="pre_name_th" /><s:property value="first_name_th" /> <s:property value="last_name_th" /> / <s:property value="roomName" />											
										</option>
										</s:iterator>
									</select>
								</td>
								<td>
									<input type="hidden" value="" id="inp-queueId" name="treatModel.queueId">
									<input type="hidden" value="" id="id_hn" name="treatModel.hn" /> 
									<input type="submit" 
										class="uk-button uk-button-success uk-button-mini"
										value="ตกลง">
									<a class="uk-button uk-button-danger uk-button-mini uk-modal-close">ยกเลิก</a>
								</td>
							</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- MODAL ZONE -->
<script>
	$(document).ready(function() {
		$('.uk-nestable-item').mouseup(function(event) {
			/* Act on the event */
			// alert('halo');
			console.log('halo');
		});
	}).on('click', '.select-room', function(event) {
		event.preventDefault();
		// alert("hello :" + $(this).data('queue'));
		// wrap-pat-btn select-room
		$("#id_hn").val($(this).data('hn'));
		$("#inp-queueId").val($(this).data('queue'));
		
	});

</script>
</body>
</html>