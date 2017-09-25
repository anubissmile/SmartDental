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
							<li><a href="DentiStscheduleCheck">กำหนดการเข้างานแพทย์</a></li>
						    <li class="uk-active"><span>กำหนดแพทย์ประจำห้อง</span></li>						    
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

						<form id="chk" action="DentistScheduleCheckinRoom" method="post" class="uk-form">
							<fieldset>
								<div class="uk-grid">
								<h2 class="uk-width-1-2">
								<div class="uk-width-1-1 uk-margin-medium-bottom">
			 					<ul class="uk-tab" data-uk-switcher="{
			 							connect:'#branch-active',
			 							animation: 'fade'
			 						}">
								    <li class="uk-active"><a href="#">Waiting Room</a></li>
								    <li><a href="#">Check-IN Room</a></li>
								</ul>
								</div>
								</h2>
								</div>
								<ul class="uk-width-1-1 uk-switcher" id="branch-active"> 
								<li class="uk-active uk-width-2-3">								
								<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
									<div class="uk-width-1-1 uk-overflow-container">
										<div class="uk-grid">
											<div class="uk-width-1-2 uk-text-center"><h2>ชื่อแพทย์</h2></div>
											<div class="uk-width-1-2 uk-text-center"><h2>จัดการ</h2></div>
										</div><hr>
										
										<s:iterator value="schList">
											<div class="uk-grid">
												<div class="uk-width-1-2 uk-text-center"><s:property value="pre_name_th" /><s:property value="first_name_th" /> <s:property value="last_name_th" /></div>												
												<div class="uk-width-1-2 uk-text-center"><a id="btn" href="#choose-assistant" data-workdayid='<s:property value="workDayId" />'  data-doctorid='<s:property value="doctorId" />'  data-uk-modal class="uk-button uk-button-success ">เข้าห้อง</a></div>
											</div><hr>																																																																							
										</s:iterator>	
									</div>
								</div>
								</li>
								<li>								
								<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
									<div class="uk-width-1-1 uk-overflow-container">
										<div class="uk-grid">
											<div class="uk-width-1-3 uk-text-center"><h2>ชื่อแพทย์</h2></div>
											<div class="uk-width-1-3 uk-text-center"><h2>ห้อง</h2></div>
											<div class="uk-width-1-3 uk-text-center"><h2>จัดการ</h2></div>
										</div><hr>										
										<s:iterator value="doctorListRoom">
											<div class="uk-grid">
												<div class="uk-width-1-3 uk-text-center"><s:property value="pre_name_th" /><s:property value="first_name_th" /> <s:property value="last_name_th" /></div>
												<div class="uk-width-1-3 uk-text-center"><s:select  list="doctorRoom" value="branchRoomId"  disabled="true" required="true" headerKey="" headerValue = "กรุณาเลือก" />	</div>
												<div class="uk-width-1-3 uk-text-center"><button type="button" data-workdayid='<s:property value="workDayId" />'  data-doctorid='<s:property value="doctorId" />'  class="uk-button uk-button-danger login">ออกห้อง</button></div>
											</div><hr>																																																																							
										</s:iterator>	
									</div>
								</div>
								</li>								
								</ul>								
							</fieldset>
						</form>
					</div>
				</div>
				<!-- END-FORM -->
		<!-- Choose assistant -->
		<div class="uk-modal" id="choose-assistant">
		    <div class="uk-modal-dialog ">
		       <form action="DentistCheckinRoomWithEmpolyee" id="modal-checkin">
		       	 <div class="uk-modal-header uk-grid uk-grid-collapse">
		       	 <div class="uk-width-1-3">
		       	 <h2 class="uk-text-left">เลือกผู้ช่วยแพทย์</h2>
		       	 </div>
		       	 <div class="uk-width-1-3 uk-text-right uk-form">
		       	  <h2>
		       	 	เลือกห้อง
		       	 </h2>
		       	 </div >
		       	 <div class="uk-width-1-3 uk-text-right uk-form">
		       	 <s:select  list="doctorRoom" name="schModel.roomId" required="true" headerKey="" headerValue = "กรุณาเลือก" />	
		       	 </div>
		       	 </div>
		       	 <div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-width-1-1 uk-overflow-container">
						<table class="uk-table uk-table-condensed" id="emplist">
						<thead>
							<tr>
								<th class="uk-text-center">เลือก</th>
								<th class="uk-text-center">ชื่อ</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="personList">
							<tr>
								<th  class="uk-text-center checkemp"><s:checkbox required='required' name="person.emp_ID_arr" fieldValue="%{emp_id}"  theme="simple"  /></th>
								<th class="uk-text-center"><s:property value="firstname_th" /> <s:property value="lastname_th" /></th>
							</tr>	
							</s:iterator>
						</tbody>	
						</table>
						</div>
						</div>	       	 	
					
		       	<div class="uk-modal-footer uk-text-right">
		       		<input type="hidden" id="workdayID" name="schModel.workDayId">
		       		<input type="hidden" id="doctorID" name="schModel.doctorId">
		       		<button class="uk-button uk-button-success">ยืนยัน</button>
					<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
		       	</div>
		       </form>
		    </div>
		</div>
			</div>
		</div>
		<script>
		$('.login').click(function(){
			var doctorid=$(this).data("doctorid"); 
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
	   			        url: "ajax/ajax-update-doctorRoomStatus.jsp",
	   			        data: {
	   			        	doctorid:doctorid,
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
		$(document).on('click', '#btn', fn_buttonmodal_habndler).ready(function(){
			$("input[name='person.emp_ID_arr']").change(function(){
				var numberOfChecked = $('input:checkbox:checked').length;
				if(numberOfChecked == '0'){
					$("input:checkbox").attr("required", 'required' );
				}
			if(this.checked){
				if(numberOfChecked == '1' || numberOfChecked == '2'){	
					$("input:checkbox").removeAttr ("required", 'required' );
				}
				else{
					this.checked = false;
				}	
			}			

			}); 
		});
		function fn_buttonmodal_habndler(e)
		{

		    //get id from pressed button
		    var doctorid = $(e.target).data('doctorid');
		    var workdayid = $(e.target).data('workdayid');
		    $('#choose-assistant').on({
		        'uk.modal.show':function(){
		        	$("#doctorID").val(doctorid);
		        	$("#workdayID").val(workdayid);
		        },
		        'uk.modal.hide':function(){
		                    //hide modal
		        }
		    }).trigger('uk.modal.show');
		}

		</script>
			
	</body>
</html>