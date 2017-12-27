<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctor-Treatment</title>
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
				<form action="" id="chk" method="post">
					<div class="uk-grid uk-grid-collapse padding5 border-gray uk-panel uk-panel-box bg-gray">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">list</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i>การรักษาที่แพทย์รักษาได้</h3> 
								</div>
									<s:if test="hasActionErrors()"> 
								         <div class="uk-alert uk-alert-danger" data-uk-alert> 
								           <li class="uk-alert-close uk-close"></li> 
								             <s:actionerror/> 
								         </div> 
								     </s:if>
								<div class="uk-grid uk-grid-collapse uk-form">
											<div class="uk-width-1-1 uk-panel-header  ">
													<h3 class="uk-width-1-1 uk-panel-title"><i class="uk-icon-th-list"></i> การรักษา <a href="#addTreatment" class="uk-button uk-button-success" data-uk-modal>เพิ่มการรักษา</a></h3>													
												</div>

									<div class="uk-width-3-3  "><br>											
											<div class="uk-width-1-1 uk-overflow-container">
												<table class="uk-table uk-table-condensed uk-table-hover  uk-table-striped uk-table-condensed border-gray" id="tbscope">
													<thead>
														<tr class="hd-table">
															<!-- <th class="uk-text-center">เลือก</th> -->
															<th class="uk-text-center uk-width-1-4">รหัสการรักษา</th>
															<th class="uk-text-center  uk-width-1-4">ชื่อการรักษา</th>
															<th class="uk-text-center  uk-width-1-4">สถานะการเปลื่ยนแปลง</th>
															<th class="uk-text-center  uk-width-1-4">จัดการสถานะ</th>
														</tr>
													</thead>
													<tbody>
														<s:iterator value="treatMentList">
														<tr>
															<th class="uk-text-center "><s:property value="treatment_codeName" /></th>
															<th class="uk-text-center  "><s:property value="treatment_nameth" /></th>
															<th class="uk-text-center  "><s:property value="can_change" /></th>
															<th class="uk-text-center  ">
																<a href="#update_status"  onclick="update('<s:property value="treatment_nameth"/>','<s:property value="doctorID"/>','<s:property value="treatment_Code"/>')" class="uk-button uk-button-primary uk-button-small"   data-uk-modal>
												    			<i class="uk-icon-pencil"></i> แก้ไข</a>
												    			<a href="#delete_treatment" onclick="delTreat('<s:property value="doctorID"/>','<s:property value="treatment_Code"/>')" class="uk-button uk-button-danger uk-button-small"  data-uk-modal>
												    			<i class="uk-icon-eraser"></i> ลบ</a>
															</th>
														</tr>
														</s:iterator>
													</tbody>
												</table>
												
											</div>
									</div>
								</div>
							</div>
							
						</div>
								
					</div>			
				</form>
				<div id="addTreatment" class="uk-modal ">
						<form action="addTreatmentdoctor-<s:property value="docModel.doctorID"/>" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
					    <div class="uk-modal-header"><i class="uk-icon-pencil"></i> เพิ่มการรักษา</div>
				         	<div class="uk-modal-body">
				         		<div class="uk-grid">

				         			<div class="uk-width-3-6 uk-grid-collapse">การรักษา
				         				 <s:select class="denMap" name="docModel.treatment_Code" list="dentistTreatmentMap" style="width:200px"  required="true" headerKey="" headerValue = "กรุณาเลือก" />	
				         			</div>
				         			<div class="uk-width-1-6 uk-text-right"></div>
				         			<div class="uk-width-2-6">สถานะ
				         				<select class="uk-width-1-2" name="docModel.can_change">
				         					<option value="t">True</option>
				         					<option value="f">False</option>
				         				</select>
				         			</div>
				         		</div>
				         		
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">
				         	 	<button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-default uk-modal-close uk-button-danger">ยกเลิก</button>
			                   
                			</div>

					    </div>
					    </form>
					</div>
					<div id="update_status" class="uk-modal ">
						<form action="updateTreatmentdoctor-<s:property value="docModel.doctorID"/>" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
					    <div class="uk-modal-header"><i class="uk-icon-pencil"></i> แก้ไข</div>
				         	<div class="uk-modal-body">
				         		<div class="uk-grid">
				         			<div class="uk-width-4-6">การรักษา
				         				<input type="text" class="uk-width-5-6" readonly="readonly" id="showtreatname" />
				         			</div>
				         			<div class="uk-width-2-6">สถานะ
				         				<select class="uk-width-1-2" name="docModel.can_change">
				         					<option value="t">True</option>
				         					<option value="f">False</option>
				         				</select>
				         			</div>
				         		</div>
				         		
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">	
				         		<input type="text" class="hidden" name="docModel.doctorID" id="doctorID" />	
				         		<input type="text" class="hidden" name="docModel.treatment_Code" id="tmentID" />	                   
			                    <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                     <button class="uk-button uk-button-default uk-modal-close uk-button-danger">ยกเลิก</button>
                			</div>

					    </div>
					    </form>
					</div>
					<div id="delete_treatment" class="uk-modal ">
						<form action="deleteTreatmentdoctor-<s:property value="docModel.doctorID"/>" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-body"><i class="uk-icon-exclamation-circle"></i> ต้องการยืนยันการลบหรือไม่</div>
				         	<div class="uk-modal-footer uk-text-right">
			                    <input type="text" class="hidden" name="docModel.doctorID" id="dentist" />
			                    <input type="text" class="hidden" name="docModel.treatment_Code" id="tmenID" />
			                   <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-default uk-modal-close uk-button-danger">ยกเลิก</button>
                			</div>

					    </div>
					    </form>
					</div>
			</div>
	</div>		
</body>
<script>
	$(document).ready(function(){

		$('#tbscope').dataTable();
 		$("select[class='denMap']").select2(); 


	});

		function update(showtreatname,doctorid,treatcode) { 
			 $("#showtreatname").val(showtreatname);
			  $("#doctorID").val(doctorid);
			  $("#tmentID").val(treatcode);
		};
		function delTreat(doid,trcode) { 

			  $("#dentist").val(doid);
			  $("#tmenID").val(trcode);
		};
		
		
</script>		    
</html>