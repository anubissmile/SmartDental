<%@ page language="java" import="java.util.*,java.text.DecimalFormat"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*"%>
<%@ page import="com.smict.treatment.data.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:ยา</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head>
<body>
	<div class="uk-grid uk-grid-collapse">
		<div class="uk-width-1-10">
			<%@include file="nav-right.jsp"%>
		</div>
		<div class="uk-width-9-10">
			<%@include file="nav-top.jsp"%>
			<div class="uk-grid uk-grid-collapse"></div>


			<div class="uk-width-6-10 border-gray padding5">


				<form action="" method="post">
					<h3 class="hd-text uk-text-primary">
						<i class="uk-icon-list-ul"></i> รายการยา
						<small> <a href="#my-id"
							class="uk-button uk-button-primary uk-button-small" data-uk-modal><i class="uk-icon-medkit"></i> เพิ่มยา</a></small>
					</h3>

					<div class="uk-width-1-1">
						<div class="uk-width-1-1uk-container-center">
							<table id="drugTable"
								class="uk-table uk-table-hover uk-form uk-table-striped uk-table-condensed border-gray uk-width-1-1 table-components-medicine">
								<thead>
									<tr class="hd-table">
										<th class="uk-text-center">ชื่อยา</th>																				
										<th class="uk-text-center">ฟรี</th>
										<th class="uk-text-center">จำนวน</th>
										<th class="uk-text-center">หน่วย</th>
										<th class="uk-text-center">จัดการ</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="listtreatpatmedicine" >
									<s:if test="isCheck != 'nu'">
											<tr>
												<td class="uk-text-center"><s:property value="treatPro_name" /></td>
												<td class="uk-text-center"><s:property value="treatPatMedicine_amountfree" /></td>
												<td class="uk-text-center"><s:property value="treatPatMedicine_amount" /></td>
												<td class="uk-text-center"><s:property value="prounitname" /></td>		
												<td class="uk-text-center">
												<a href="#updateMedicinetreatment" onclick="updateMedicine('<s:property value="treatPro_name" />','<s:property value="treatPatMedicine_id" />','<s:property value="treatPatMedicine_amountfree" />','<s:property value="treatPatMedicine_amount" />','<s:property value="prounitname" />')" 
												class="uk-button uk-button-small uk-button-primary" data-uk-modal ><i class="uk-icon-pencil"></i> แก้ไข</a>
												<a href="#deleteMedicinetreatment" onclick="delMedicine('<s:property value="treatPatMedicine_id" />','<s:property value="treatPro_name" />')" class="uk-button uk-button-small uk-button-danger" data-uk-modal ><i class="uk-icon-eraser"></i> ลบ</a>
												</td>								
											</tr>
									</s:if>		
									</s:iterator>
								</tbody>
							</table>
						</div>
					</div>
					<div class=" uk-text-center">
					<a href="getPatientShowAfterSaveTreatment-<s:property value='treatModel.treatment_patient_ID' />" class="uk-button uk-button-success  uk-button-small ">เสร็จสิ้น รายการยา</a>
					</div>
				</form>

				</div>
			</div>
		</div>
			<div id="my-id" class="uk-modal">
				<form action="addvalue0fmedicine" >
						<div class="uk-modal-dialog">
							<a class="uk-modal-close uk-close"></a>
							<div class="uk-modal-header">
								<h3><i class="uk-icon-medkit"></i> ยา</h3>
							</div>

							
							<table class="uk-table uk-table-hover uk-table-striped" id="medicineListTable">
								<thead>
									<tr class="hd-table">
										<th class="uk-text-center">เลือก</th>
										<th class="uk-text-center">ชื่อยา</th>																				
										<th class="uk-text-center">ฟรี</th>
										<th class="uk-text-center">จำนวน</th>	
										<th class="uk-text-center">หน่วย</th>									
									</tr>
								</thead>
								<tbody>
									<s:iterator value="listtreatpatmedicine" >
									<s:if test="isCheck == 'nu'">
											<tr>
												<td class="uk-text-center"><input type="radio" required="required" class="checked" name="treatModel.treatPatMedicine_ProID" value="<s:property value="treatPatMedicine_ProID" />" /></td>
												<td class="uk-text-center"><s:property value="treatPro_name" /></td>
												<td class="uk-text-center">0</td>												
												<td class="uk-text-center">
													<input type="text" name="treatModel.treatPatMedicine_amount"  disabled="disabled" placeholder='จำนวน' class='checked<s:property 
													value="treatPatMedicine_ProID" /> uk-form-small uk-text-right uk-width-1-2 ' value='' />
												</td>
												<td class="uk-text-center"><s:property value="prounitname" /></td>									
											</tr>
									</s:if>		
									</s:iterator>
								</tbody>

							</table>
							<div class="uk-modal-footer uk-text-right">

			                    <input type="hidden"  id="treatpatID" value="<s:property value='treatModel.treatment_patient_ID' />" name="treatModel.treatment_patient_ID">
			                    <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-default uk-button-danger uk-modal-close"> ยกเลิก</button>
                			</div>
						</div>
					</form>	
					</div>
			<div id="updateMedicinetreatment" class="uk-modal">
				<form action="updateMedicinetreatment" >
						<div class="uk-modal-dialog">
							<a class="uk-modal-close uk-close"></a>
							<div class="uk-modal-header">
								<h3><i class="uk-icon-medkit"></i> แก้ไขยา</h3>
							</div>
							<div class="uk-grid uk-grid-collapse uk-form">
								<table class="uk-table uk-table-hover uk-table-striped">
									<thead>
										<tr class="hd-table">
											<th class="uk-text-center">ชื่อยา</th>																				
											<th class="uk-text-center">ฟรี</th>
											<th class="uk-text-center">จำนวน</th>	
											<th class="uk-text-center">หน่วย</th>									
										</tr>
									</thead>
									<tbody>
										<tr>
											<th class="uk-text-center pronameup"></th>																				
											<th class="uk-text-center proamountfreeup"></th>
											<th class="uk-text-center "><input type="text" class="uk-form-small uk-text-right uk-width-1-2" id="proamountup" name="treatModel.treatPatMedicine_amount" value="" /></th>	
											<th class="uk-text-center prounitup"></th>									
										</tr>
									</tbody>
	
								</table>																
							</div>
							<div class="uk-modal-footer uk-text-right">
								<input type="hidden" id="midecineID" value="" name="treatModel.treatPatMedicine_id" />
			                    <input type="hidden"  id="treatpatID" value="<s:property value='treatModel.treatment_patient_ID' />" name="treatModel.treatment_patient_ID">
			                    <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-default uk-button-danger uk-modal-close"> ยกเลิก</button>
                			</div>
						</div>
					</form>	
			</div>
			<div id="deleteMedicinetreatment" class="uk-modal">
				<form action="deleteMedicinetreatment" >
						<div class="uk-modal-dialog uk-form">
							<a class="uk-modal-close uk-close"></a>
							<div class="uk-modal-header">
								<h3><i class="uk-icon-medkit"></i> ต้องการลบรายการยานี้หรือไม่!</h3>
							</div>
								<div class="uk-form">
								<input  type="text" id="pronamedel" value="" readonly="readonly" />
								</div>
							<div class="uk-modal-footer uk-text-right">
								<input type="hidden" id="midecineIDdel" value="" name="treatModel.treatPatMedicine_id" />
			                    <input type="hidden"  id="treatpatID" value="<s:property value='treatModel.treatment_patient_ID' />" name="treatModel.treatment_patient_ID">
			                    <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-default uk-button-danger uk-modal-close"> ยกเลิก</button>
                			</div>
						</div>
					</form>	
			</div>						
</body>
<script>
	$(document).ready(function () {
		$('#medicineListTable').dataTable();
	}).on('change', '.checked', function () {
		$('.checked').removeAttr('required');
		$("input[name='treatModel.treatPatMedicine_amount']").attr('disabled', 'disabled');
		$("input[name='treatModel.treatPatMedicine_amount']").removeAttr('required');
		var treatproID = $(this).val();
		$('.checked'+treatproID).removeAttr('disabled');
		$('.checked'+treatproID).attr('required','required');
		console.log(treatproID);
		
	});
	function updateMedicine(proname,proID,proamountfree,proamount,prounit) {
		$('.pronameup').text(proname);
		$('.proamountfreeup').text(proamountfree);
		$('.prounitup').text(prounit);
		$('#proamountup').val(proamount);
		$('#midecineID').val(proID);
	}
	function delMedicine(proID,proname) {
		$('#midecineIDdel').val(proID);
		$('#pronamedel').val(proname);
	}
	
</script>
</html>