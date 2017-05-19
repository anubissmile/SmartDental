<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.person.data.*" %>
<%@ page import="com.smict.person.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:ลงเวลาการทำงานแพทย์</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div> 
			<div class="uk-width-9-10 ">
				<%@include file="doctor-nav.jsp" %>
				<div class="uk-grid uk-grid-collapse">

				<!-- Table  -->
				<table class="hidden">
			        <tr id="tr-temp" class="hidden">
			        	<td class="uk-width-1-10 "><button type="button" class=" uk-button uk-button-danger uk-button-mini btn-close-tr"><i class="uk-icon-close"></i></button></td>
			            <td class="uk-text-center uk-width-2-10 ">
			            	<input class="uk-form-small uk-width-1-1 month-picker" 
			            		name="docTimeM.work_month"  type="text" placeholder="เลือกเดือน/ปี"/>	 
			            </td>
			            <td class=" uk-text-center uk-width-7-10 ">  
			            	<div class="uk-grid uk-grid-small">
			            		<div class=" uk-text-center"> จันทร์
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00"  name="docTimeM.time_in_mon" placeholder="เข้างาน">
									</div> 
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00"  name="docTimeM.time_out_mon" placeholder="ออกงาน">
									</div>
			            		</div>
			            		<div class=" uk-text-center"> อังคาร
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00"  name="docTimeM.time_in_tue" placeholder="เข้างาน">
									</div> 
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00"  name="docTimeM.time_out_tue" placeholder="ออกงาน">
									</div>
			            		</div>
			            		<div class=" uk-text-center"> พุธ
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00"  name="docTimeM.time_in_wed" placeholder="เข้างาน">
									</div> 
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00"  name="docTimeM.time_out_wed" placeholder="ออกงาน">
									</div>
			            		</div>
			            		<div class=" uk-text-center">พฤหัสบดี
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00"  name="docTimeM.time_in_thu" placeholder="เข้างาน">
									</div> 
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00"  name="docTimeM.time_out_thu" placeholder="ออกงาน">
									</div>
			            		</div> 
			            		<div class=" uk-text-center"> ศุกร์
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00" name="docTimeM.time_in_fri" placeholder="เข้างาน">
									</div> 
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00"  name="docTimeM.time_out_fri" placeholder="ออกงาน">
									</div>
			            		</div>
			            		<div class=" uk-text-center"> เสาร์
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00"  name="docTimeM.time_in_sat" placeholder="เข้างาน">
									</div> 
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00"  name="docTimeM.time_out_sat" placeholder="ออกงาน">
									</div>
			            		</div>
			            		<div class=" uk-text-center"> อาทิตย์
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00" name="docTimeM.time_in_sun" placeholder="เข้างาน">
									</div> 
									<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
										<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="00:00" name="docTimeM.time_out_sun" placeholder="ออกงาน">
									</div>
			            		</div>
			            	</div>   
			            </td>   
			        </tr> 
				</table>
					<s:form  class="uk-width-1-1" method="post" 
						action="add-doctor-monthly-schedule-%{docModel.DoctorID}-%{docModel.branchStandID}"> 
					<input type="hidden" name="docTimeM.doctorID" value="<s:property value="docTimeM.doctorID"/>"/>
					
						<div class=" uk-panel-box uk-form">
						<a class="add-elements uk-button " href="GetDoctor?d=<s:property value="docTimeM.doctorID"/>"><i class="uk-icon-chevron-left"></i> กลับ</a>
						<h2 class="hd-text uk-text-primary padding5 uk-text-center">ลงเวลาการทำงาน แพทย์</h2>
						<div class="uk-grid uk-grid-small">
							
					    	<div class="uk-width-1-1 uk-form hidden time-doc">
					    	<button type="button" class="add-elements uk-button uk-button-success ">เพิ่มเวลาทำงาน</button>
							
							<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray uk-text-nowrap">
							    <caption>ตาราง เวลาการทำงานแพทย์</caption>
							    <thead>
							        <tr class="hd-table"> 
							        	<th class="uk-text-center uk-width-1-10"></th>
							            <th class="uk-text-center uk-width-1-10">เดือน</th>
							            <th class="uk-text-center uk-width-8-10">วัน - เวลา</th> 
							        </tr>
							    </thead> 
							    <tbody class="tbody">
							       
							    </tbody>
							</table>
						</div>
						<div class="uk-width-1-1 uk-text-center">
							<button class="uk-button uk-button-success " 
							type="submit"><i class="uk-icon-floppy-o"></i> SAVE</button>
						</div>
						</div>
						</div> 
					</s:form>
				 
			</div>
		</div>
		</div>
 
	<script>
	$(document).ready(function() {		
		
		$( ".m-setting" ).addClass( "uk-active" );
		load_doc_time();
		$(document).on('click','.add-elements', function(){
			var cloneTR = $("#tr-temp").clone();
			cloneTR.removeClass("hidden");
			cloneTR.appendTo(".tbody");
			load_picker();
		});
		$(document).on('click','.btn-close-tr', function(){
			$(this).closest('tr').remove();
		});
		
		$('#branch_select').select2()
		.on('select2:select', function () {
			if($('#branch_select').val()!=""){
				load_doc_time();
			}else{
				$(".time-doc").addClass("hidden");
			}
			
	     });
		if($('#branch_select').val()!=""){
			load_doc_time();
			$(".time-doc").removeClass("hidden");
		}
	});	 
	function load_doc_time(){
		$(".tbody").empty();
		$.ajax({
	        type: "post",
	        url: "ajax/ajax-select-doctor-time.jsp", //this is my servlet 
	        data: {branch_id:$('#branch_select').val(),"doctor_id":<%=(request.getParameter("d")!=null)? request.getParameter("d"):request.getAttribute("d")%>},
	        async:false, 
	        success: function(result){
	        	var obj = jQuery.parseJSON(result);
	        	for(var i = 0 ;  i < obj.length;i++){
	        		var cloneTR = $("#tr-temp").clone();
	        		cloneTR.appendTo(".tbody");
	        		cloneTR.find('.month-picker').val(obj[i].work_month);
	        			
					cloneTR.find('input[name="docTimeM.time_in_mon"]').val(obj[i].time_in_mon);
	        		cloneTR.find('input[name="docTimeM.time_out_mon"]').val(obj[i].time_out_mon);
	        		cloneTR.find('input[name="docTimeM.time_in_tue"]').val(obj[i].time_in_tue);
	        		cloneTR.find('input[name="docTimeM.time_out_tue"]').val(obj[i].time_out_tue);
	        		cloneTR.find('input[name="docTimeM.time_in_wed"]').val(obj[i].time_in_wed);
	        		cloneTR.find('input[name="docTimeM.time_out_wed"]').val(obj[i].time_out_wed);
	        		cloneTR.find('input[name="docTimeM.time_in_thu"]').val(obj[i].time_in_thu);
	        		cloneTR.find('input[name="docTimeM.time_out_thu"]').val(obj[i].time_out_thu);
	        		cloneTR.find('input[name="docTimeM.time_in_fri"]').val(obj[i].time_in_fri);
	        		cloneTR.find('input[name="docTimeM.time_out_fri"]').val(obj[i].time_out_fri);
	        		cloneTR.find('input[name="docTimeM.time_in_sat"]').val(obj[i].time_in_sat);
	        		cloneTR.find('input[name="docTimeM.time_out_sat"]').val(obj[i].time_out_sat);
	        		cloneTR.find('input[name="docTimeM.time_in_sun"]').val(obj[i].time_in_sun);
	        		cloneTR.find('input[name="docTimeM.time_out_sun"]').val(obj[i].time_out_sun);
	        		
					cloneTR.removeClass("hidden");
					cloneTR.appendTo(".tbody");
	        	}
	        	
		    } 
	     });
		$(".time-doc").removeClass("hidden");
		load_picker();
	}
	function load_picker(){
		$('.month-picker').datepicker( {
			viewMode: "months", 
		    minViewMode: "months",
	        format: 'mm/yyyy'
		});
		$('.clockpicker').clockpicker();
	}
	</script>		
	</body>
</html>