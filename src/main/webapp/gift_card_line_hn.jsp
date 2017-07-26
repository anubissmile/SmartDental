<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : Gift Card</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				
				<%@include file="backend-giftcardline-top.jsp" %>

					<div class="uk-grid"></div>
					<form  action="addgiftlinewithpatient" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid uk-grid-collapse">
						<div class="uk-width-1-1 uk-overflow-container ">
							<div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Gift Card</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> Gift Card</h3>
								</div>
								<div class="uk-grid uk-grid-collapse mt-0">
											<div class="uk-width-1-5 uk-form uk-text-right">
												<h3>ชื่อคนไข้ : </h3>
											</div>
											<div class="uk-width-1-5 uk-form">
											<input type="hidden" value="<s:property value="giftcardModel.giftcard_id"/>" 
											name="giftcardModel.giftcard_id" >
											<input type="hidden" value="<s:property value="giftcardModel.giftcard_line_id"/>" 
											name="giftcardModel.giftcard_line_id" >
											
											<select id="patHN" size="0" style="width:100%;" name="giftcardModel.giftcard_line_rel_patient_hn"></select>
											</div>
											<div class="uk-width-1-5 uk-form ">
											<div class="uk-grid uk-grid-collapse mt-0">
												<div class="uk-width-1-2 uk-form ">
												<a href="#patient-all" class="uk-button uk-button-primary" data-uk-modal>
												<i class="uk-icon-plus" ></i>
												</a>
												</div>
												<div class="uk-width-1-2 uk-form ">
												<button type="submit" class="uk-button uk-button-success uk-text-right">บันทึก</button>
												</div>
											</div>
											</div>													
								</div>
							</div>
						</div>
								
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
								<div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-list"></i> Detail</h3>
								</div>
								<div class="uk-width-1-1 uk-overflow-container uk-form">
									<table id="tb-ac" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									            <th class="uk-text-center">HN </th>
									            <th class="uk-text-center">ชื่อ - นามสกุล</th>
									            <th class="uk-text-center">จัดการ</th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="getgiftcardlist">
									    	<tr>
									    		<td class="uk-text-center"><s:property value="giftcard_line_rel_patient_hn"/></td>
									    		<td class="uk-text-center"><s:property value="prename"/><s:property value="firstname"/> <s:property value="lastname"/></td>
									    		<td class="uk-text-center">
													<a href="" class="uk-button uk-button-danger uk-button-small"
													   onclick="delete_gift(
													   '<s:property value="giftcard_line_id" />',
													   '<s:property value="giftcard_line_rel_patient_id" />')" data-uk-modal>
													   <i class="uk-icon-eraser"></i> ลบ
													</a>												
												</td>
									    	</tr>
						    				</s:iterator>			    
									    </tbody>   
									</table>
								</div>						
							</div>
						</div>
					</div>					 
					<div id="patient-all" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-meh-o"></i> คนไข้</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " id="tb-pat">
									    <thead>
									        <tr class="hd-table"> 
									            <th class="uk-text-center">เลือก</th> 
									            <th class="uk-text-center">HN</th>
									            <th class="uk-text-center">ชื่อ - นามสกุล</th>
									        </tr>
									    </thead> 
									    <tbody>
					                       	 
											<s:iterator value="patientlist">
									    	<tr>
									    		<td class="uk-text-center"><input type="radio" name="patModel_HN" value="<s:property value="hn"/>"  ></td>
									    		<td class="uk-text-center"><s:property value="hn"/></td>
									    		<td class="uk-text-center pre_name"><s:property value="pre_name_th"/><s:property value="firstname_th"/> <s:property value="lastname_th"/></td>
									    	</tr>
						    				</s:iterator>
											
										</tbody>
									</table>
									</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" name="btn_submit_be_allergic" id="btn_submit_be_allergic">ตกลง</button>
					         </div>
					    </div>
					</div>
				</div>	
					</form> 
			</div>		
		</div>

					<form action="delGiftwithpatient" id="delGiftCard">
					    <input class="uk-width-1-1 " type="hidden"  id="idgift"
					     value="<s:property value="giftcardModel.giftcard_id"/>" name="giftcardModel.giftcard_id" >
					    <input class="uk-width-1-1 " type="hidden"  id="idgiftline" name="giftcardModel.giftcard_line_id" > 
					    <input class="uk-width-1-1 " type="hidden"  id="idgiftrel" name="giftcardModel.giftcard_line_rel_patient_id" >  
					</form>
		<script>
			$(document).ready(function(){
				$("#tb-ac").dataTable();
				$("#tb-pat").dataTable();
			}).on("change","input[name='patModel_HN']",function(){
				
				var index = $("input[name='patModel_HN']").index(this);
				var docuname = $(".pre_name:eq("+index+")").text();
				if(this.checked){
					$("select[name='giftcardModel.giftcard_line_rel_patient_hn']").html($('<option>').text(docuname).attr('value', $(this).val()));
				}else{
					$("select[name='giftcardModel.giftcard_line_rel_patient_hn'] option[value='"+$(this).val()+"']").remove();
				}
				
			})
			function delete_gift(giftline,giftrel) { 
				swal({
	   			  title: 'อนุมัติการทำงาน',
	   			  text: "ท่านต้องการยืนยันการลบหรือไม่!",  		   				  			  
	   			  type: 'warning',
	   			  showCancelButton: true,
	   			  confirmButtonColor: '#3085d6',
	   			  cancelButtonColor: '#d33',
	   			  confirmButtonText: 'อนุมัติ',
	   			  cancelButtonText: 'ยกเลิก',
	   			  confirmButtonClass: 'uk-button uk-button-primary',
	   			  cancelButtonClass: 'uk-button uk-button-danger',
	   			  buttonsStyling: false
	   			}).then(function (isConfirm){
		   			 if (isConfirm) {
		   				$("#idgiftline").val(giftline);
		   				$("#idgiftrel").val(giftrel);
						$("#delGiftCard").submit();
		   			 }else{
			   			    swal(
			   			      'ยกเลิกการทำรายการแล้ว',
			   			      'ข้อมูลจะไม่มีการเปลี่ยนแปลง)',
			   			      'error'
			   			    )
			   			   
		   			 }
	   			})
		};	
			
			
			
		</script>
	</body>
</html>