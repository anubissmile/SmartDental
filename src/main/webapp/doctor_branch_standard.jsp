<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctor_branch</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head>
	<body> 	
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="doctor-nav-black.jsp" %>
				<script type="text/javascript" src="js/webcam.min.js"></script>
				<form action="BranchStandard" method="post">
					<div class="uk-grid uk-grid-collapse padding5 border-gray uk-panel uk-panel-box bg-gray">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">สาขา</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> สาขาที่ลงตรวจ</h3> 
								</div>
									<s:if test="hasActionErrors()"> 
								         <div class="uk-alert uk-alert-danger" data-uk-alert> 
								           <li class="uk-alert-close uk-close"></li> 
								             <s:actionerror/> 
								         </div> 
								     </s:if>
								<div class="uk-grid uk-form">
									<div class="hidden"> <s:textfield name="docModel.DoctorID" /></div>
									<div class="uk-width-3-10">
										สาขา :
										<s:select list="branchlist" cssClass="uk-width-2-3" name="docModel.branch_id"  required="true" headerKey="" headerValue = "กรุณาเลือก"  />
									</div>
									<div class="uk-width-2-10">
										ค่าเวรทันตแพทย์ :
										<s:textfield  cssClass="uk-width-1-2  uk-text-right" name="docModel.price" />	
									</div>
									<div class="uk-width-2-10">
										<button type="submit"  class="uk-button uk-button-success ">
										เพิ่มสาขา
										</button>									
									</div>
								</div>
								<h3>กำหนดประเภทช่วงเวลาเข้างานประจำสาขา</h3>
								<div class="uk-grid uk-form">
									<div class="uk-width-1-6 uk-text-right">
										<label><input type="radio" name="docModel.income_type" value="1" checked="checked" class="typeall" > ประเภทวัน</label>
									</div>
									<div class="uk-width-1-6">
										<label><input type="radio" name="docModel.income_type" value="2" class="typeall" > ประเภทชั่วโมง</label>	
									</div>
								</div>
								<div class="uk-grid  uk-form  allday">
									<div class="uk-width-1-6 ">
										เวลาเริ่ม  <input type="text"  name="docModel.start_datetime" id="start" 
										class="dpicker" data-placement="right"  data-autoclose="true" data-align="top" >
									</div>
									<div class="uk-width-1-6 ">
										สิ้นสุด <input type="text"  name="docModel.finish_datetime" id="end" 
										class="dpicker" data-placement="right" data-autoclose="true" data-align="top" >
									</div>
								</div>
								<div class="uk-grid uk-form  hidden justhour">
									<div class="uk-width-1-6 ">
										จำนวนชั่วโมง  <input type="text"  name="docModel.work_hour" class="hourtime" >
									</div>
								</div>	
								<div class="uk-grid  uk-form margintopshow">
									<div class="uk-width-1-6 ">
										Late minute  <input type="text"  name="docModel.late_min" value="15"  >
									</div>
									<div class="uk-width-1-6 ">
										Early minute <input type="text"  name="docModel.early_min" value="15"  >
									</div>
								</div>
								
							</div>
							<div class="uk-panel uk-panel-box">
                                <div class="uk-panel-header">
								    <div class="uk-panel-title uk-grid uk-grid-collapse ">
									    <div class="uk-width-2-10"><h3>
									    	<i class="uk-icon-calendar"></i> รายชื่อสาขา </h3>
									    </div>
								    	<div class="uk-width-3-10 bor-rightAndleft uk-text-center">
									    	<s:iterator value="branchStandardList" status="docbranch">
									    		<s:if test="1>#docbranch.index ">
									    		<s:property value="pre_name_th" /><s:property value="first_name_th" /> <s:property value="last_name_th" />
									    		</s:if>
									    	</s:iterator>
								    	</div>
								    	<div class="uk-width-3-10">
											<s:a href="doctor-schedule-calendar-%{docModel.DoctorID}" 
												class="uk-button uk-button uk-button-small uk-margin-small-left">
												<li class="uk-icon-calendar"></li>
												ดูตารางงานแพทย์
											</s:a>
								    	</div>
								    	<div class="uk-width-2-10"></div>
								    </div>
								</div>
								<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-condensed uk-table-hover">
									<thead>
										<tr>
											<th class="uk-text-center">ชื่อสาขา</th>
											<th class="uk-text-center">ค่าเวรทันตแพทย์</th>
											<th class="uk-text-center">ประเภทช่วงเวลาเข้างาน</th>
											<th class="uk-text-center">ช่วงเวลา</th>
											<th></th>
										</tr>
										
									</thead>
									<tbody>
										<s:iterator value="branchStandardList">
										<tr>
											<th class="uk-text-center"><a href=""><s:property value="branchName" /></a></th>
											<th class="uk-text-right">
												<s:property value="getText('{0,number,#,##0.00}',{price})" /> บาท
											</th>
											<s:if test="income_type == 1 ">
											<th class="uk-text-center">ประเภทวัน</th>
											<th class="uk-text-center"><s:property value="start_datetime" />-<s:property value="finish_datetime" /></th>
											</s:if>
											<s:else>
											<th class="uk-text-center">ประเภทชั่วโมง</th>
											<th class="uk-text-center"><s:property value="work_hour" /> ชั่วโมง</th>
											</s:else>
											
											
											<th>
												<a href="#delete_branchStandard" 
													onclick="deleteBranchStant('<s:property value="branchStandID" />')"
													class="uk-button uk-button-danger uk-button-small" 
													 data-uk-modal><i class="uk-icon-eraser"></i> ลบ</a>
												<a href="#update_salary"  onclick="update('<s:property value="branchName"/>'
												,'<s:property value="price"/>','<s:property value="branchStandID" />',
												'<s:property value="income_type" />',
												'<s:property value="start_datetime" />','<s:property value="finish_datetime" />',
												'<s:property value="work_hour" />',
												'<s:property value="late_min" />','<s:property value="early_min" />')"   
												class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
												<s:a href="doctor-monthly-schedule-%{docModel.DoctorID}-%{branchStandID}" 
													class="uk-button uk-button-success uk-button-small">
													<li class="uk-icon-calendar-plus-o"></li>
													เพิ่มตารางเวลาประจำเดือน
												</s:a>
											</th>
										</tr>
										</s:iterator>
									</tbody>	
									</table>
								</div>
							</div>	
						</div>		
					</div>			
				</form>
					<div id="update_salary" class="uk-modal ">
						<form action="UpadteBranchStandard-<s:property value="docModel.DoctorID" />" method="post"> 
					    <div class="uk-modal-dialog  uk-form" >
					    <div class="uk-modal-header"><i class="uk-icon-pencil"></i> แก้ไข</div>
				         	<div class="uk-modal-body">
				         		<div class="uk-grid">
				         			<div class="uk-width-1-2">สาขา
				         				<input type="text" id="branchname" readonly /> 
				         			</div>
				         			<div class="uk-width-1-2">ค่าเวรทันตแพทย์
				         				<s:textfield id="salary" cssClass="uk-width-2-5" name="docModel.price" />
				         			</div>
				         		</div>
				         		<h3>กำหนดประเภทช่วงเวลาเข้างานประจำสาขา</h3>
								<div class="uk-grid uk-form">
									<div class="uk-width-1-3 uk-text-right">
										<label><input type="radio" name="docModel.income_type" value="1"  class="typeallup type1" > ประเภทวัน</label>
									</div>
									<div class="uk-width-1-3">
										<label><input type="radio" name="docModel.income_type" value="2" class="typeallup type2" > ประเภทชั่วโมง</label>	
									</div>
								</div>
								<div class="uk-grid  uk-form  alldayup hidden">
									<div class="uk-width-1-2 ">
										เวลาเริ่ม  <input type="text"  name="docModel.start_datetime" id="start1" 
										class="dpickerup" data-placement="right"  data-autoclose="true" data-align="top" >
									</div>
									<div class="uk-width-1-2 ">
										สิ้นสุด <input type="text"  name="docModel.finish_datetime" id="end1" 
										class="dpickerup" data-placement="right" data-autoclose="true" data-align="top" >
									</div>
								</div>
								<div class="uk-grid uk-form  hidden justhourup">
									<div class="uk-width-1-2 ">
										จำนวนชั่วโมง  <input type="text"  id="workhour" name="docModel.work_hour" class="hourtimeup" >
									</div>
								</div>	
								<div class="uk-grid  uk-form margintopshow">
									<div class="uk-width-1-2 ">
										Late minute  <input type="text"  id="lateup" name="docModel.late_min" value="15"  >
									</div>
									<div class="uk-width-1-2 ">
										Early minute <input type="text"  id="earlyup" name="docModel.early_min" value="15"  >
									</div>
								</div>
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">
				         				                    <input type="hidden" id="Branchupdate" name="docModel.branchStandID"><button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>

                			</div>

					    </div>
					    </form>
					</div>
					<div id="delete_branchStandard" class="uk-modal ">
						<form action="DeleteBranchStandard-<s:property value="docModel.DoctorID" />" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-body"><i class="uk-icon-exclamation-circle"></i> ต้องการยืนยันการลบหรือไม่</div>
				         	<div class="uk-modal-footer uk-text-right">
				         				                    <input type="hidden" id="Branchdel" name="docModel.branchStandID"><button type="submit" class="uk-button uk-button-success "> ยืนยัน</button>
			                    <button class="uk-button uk-button-danger uk-modal-close ">ยกเลิก</button>

                			</div>

					    </div>
					    </form>
					</div>
			</div>
	</div>		
</body>
<script>
	$(document).ready(function () {
		$('#start').clockpicker();
		$('#end').clockpicker();
		$('#start1').clockpicker();
		$('#end1').clockpicker();
		$('.typeall').change(function () {
			if($(this).val()==1){
				$('.allday').removeClass('hidden');
				$('.justhour').addClass('hidden');
				$('.dpicker').val('');
				$('.hourtime').val('');
			}else{
				$('.allday').addClass('hidden');
				$('.justhour').removeClass('hidden');
				$('.dpicker').val('');
				$('.hourtime').val('');
			}
		});
		 $(".typeallup").change(function () {
			if($(this).val()==1){
				$('.alldayup').removeClass('hidden');
				$('.justhourup').addClass('hidden');
				$('.dpickerup').val('');
				$('.hourtimeup').val('');
			}else{
				$('.alldayup').addClass('hidden');
				$('.justhourup').removeClass('hidden');
				$('.dpickerup').val('');
				$('#workhour').val('');
			}
		});
	 });
		function update(branchname,salary,branchstant,income,start,finish,workhour,late,early) { 
				 $("#branchname").val(branchname);
				 $("#salary").val(salary);
				 $("#Branchupdate").val(branchstant);
				 if(income==1){
					 $(".type1").prop('checked', true);
					 $('.alldayup').removeClass('hidden');
					 $('#start1').val(start);
					 $('#end1').val(finish);
				 }else{
					 $(".type2").prop('checked', true);
					 $('.justhourup').removeClass('hidden');
					 $('#workhour').val(workhour);
				 }
				 $('#lateup').val(late);
				 $('#earlyup').val(early);
	}
		function deleteBranchStant(branchStantid)
		{
			$("#Branchdel").val(branchStantid);
		    
		}

		
</script>		    
</html>