<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:คนไข้</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head>
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div> 
			<div class="uk-width-9-10 ">
				<%@include file="nav-top.jsp" %>
				<div class="uk-grid uk-grid-collapse">
					<%@include file="fullpatient-leftside.jsp" %>
					<div class="uk-width-6-10" id="right-content">
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
							<s:if test="alertStatus != null ">
								<div class="uk-alert uk-alert-<s:property value='alertStatus' /> uk-width-1-1" data-uk-alert>
								    <a href="" class="uk-alert-close uk-close"></a>
								    <p><s:property value="alertMessage"/> </p>
								</div>
							</s:if>
							<div class="uk-widthn-1-1 uk-form">
								<p class="uk-text-muted uk-width-1-1">ข้อมูลประเภทสมาชิก <a href="beginAddContype" class="uk-button uk-button-primary"><i class="uk-icon-plus"></i> เพิ่มประเภทสมาชิก</a></p> 
								<div class="uk-grid uk-grid-collapse ">
									<div class="uk-width-1-1">
										<table class="uk-table uk-table-hover uk-table-condensed border-gray " >
											    <thead>
											        <tr class="hd-table"> 
											            <th class="uk-text-center">รูปแบบประเภทสมาชิก</th> 
											            <th class="uk-text-center">ประเภทสมาชิก</th>
											            <th class="uk-text-center">ระยะเวลาที่ใช้ได้</th> 
											            <th class="uk-text-center">วันที่สร้าง</th> 
											            <th class="uk-text-center">วันที่หมดอายุ</th>
											            <th class="uk-text-center">ต่ออายุสมาชิก</th>  
											            <th class="uk-text-center">ลบข้อมูล</th>  
											        </tr>
											    </thead> 
											    <tbody>
										    		<s:iterator value="servicePatModel.contypeList">
											    		<s:if test="dayOutBalance < 10">
											    			<tr bgcolor="#ff9999">
											    		</s:if>
											    		<s:else> 
											    			<tr>
											    		</s:else>
											    				<td class="uk-text-center"><s:property value="contact_name"/> <s:property value="conTypeArrayList.dayOutBalance"/> </td> 
													            <td class="uk-text-center"><s:property value="sub_contact_name"/></td>
													            <td class="uk-text-center"><s:property value="dayOutBalance"/></td> 
													            <td class="uk-text-center"><s:property value="create_datetime"/></td> 
													            <td class="uk-text-center"><s:property value="expire_datetime"/></td>
													            <td class="uk-text-center">
													            	<a href="#renewalMember" id="btn_renewal" class="uk-button uk-button-primary" 
													            	data-patient_contypeid='<s:property value="patient_contypeid"/>' data-uk-modal><i class="uk-icon-plus"></i> ต่ออายุ </a>
													            </td>
													            <td>
													            	<a href="#removeContype" id="btn_rmContype" class="uk-button uk-button-danger" 
													            	data-patient_contypeid='<s:property value="patient_contypeid"/>' data-contypename='<s:property value="sub_contact_name"/>' data-uk-modal>ลบ</a>
													            </td> 
													        </tr>
													</s:iterator>
											    </tbody>
										</table>
									</div >
								</div>
							</div>
						</div>
						<div class="uk-overflow-container treatment-table uk-grid uk-grid-collapse">
							<div class="uk-width-1-1">
								<form id="deleteTransection" action="treatmentDeleteTran" method="post">
								<div class="uk-panel uk-panel-box padding5 ">
									<h4 class="hd-text uk-text-primary padding5">ประวัติการรักษา</h4>
									<hr class="margin5">
									<div class="treatment-bill">
									
									<input type="hidden" id="treatment_id" name="servicePatModel.treatment_id" >
									<input type="hidden" id="count" name="treatment_code_delete" >
									
									<table class="uk-table uk-table-condensed ">
										<thead>
									        <tr class="hd-table">
												
												<th class="uk-text-center">รหัสการรักษา</th>
												<th class="uk-text-center">การรักษา</th>
												<th class="uk-text-center">วัสดุ</th>
												<th class="uk-text-center">ราคา</th>
												
											</tr>
										</thead> 
									    <tbody>
<%-- 										<% 		 
											if(request.getAttribute("transectionTreatmentList")!=null){
							    				List<ServicePatientModel> transectionList = (List) request.getAttribute("transectionTreatmentList"); 
										    	for(ServicePatientModel ttModel : transectionList){   
										%> 

										<tr>
											<td><%=ttModel.getDoctor_name()%></td>
											<td><%=ttModel.getTreatment_code()%></td>
											<td><%=ttModel.getTreatment_name()%></td>
											<td></td>
											<td class="uk-text-right"><%=ttModel.getPrice_standard()%></td>
											<td>
												<button class="uk-button uk-button-danger uk-button-small" type="button"
												onclick="onDelete();
												getElementById('treatment_id').value='<%=ttModel.getTreatment_id()%>',
												getElementById('count').value='<%=ttModel.getTreatment_code()%>';">
												<i class="uk-icon-close"></i></button>
											</td>
										</tr>
										<% } %>
										<% } %> --%>
										<s:iterator value="listtreatmentModel">
											<tr>
												<td class="uk-text-center"><s:property value="treatMent_code" /></td>
												<td class="uk-text-center"><s:property value="treatMent_name" /></td>
												<td class="uk-text-center">-</td>
												<td class="uk-text-right"><s:property value="treatment_price" /></td>
											</tr>
										</s:iterator>
										</tbody>
										
									</table>
									</div>
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				
				<div id="point" class="uk-modal ">
					    <div class="uk-modal-dialog uk-modal-dialog-large uk-form" >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-product-hunt"></i> คะนนสะสม</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table"> 
									            <th class="uk-text-center">ลำดับ</th> 
									            <th class="uk-text-center">เลขที่ทำรายการ</th>
									            <th class="uk-text-center">วันที่ทำรายการ</th> 
									            <th class="uk-text-center">รายการ</th>
									            <th class="uk-text-center">จำนวนเงิน</th>
									            <th class="uk-text-center">ใช้ไป</th>
									            <th class="uk-text-center">ได้รับ</th>
									            <th class="uk-text-center">คงเหลือ</th>
									        </tr>
									    </thead> 
									    <tbody>
									    	<tr>  
										        <td class="uk-text-center">1</td>
										        <td class="uk-text-center">100011</td>
										        <td class="uk-text-center">06-05-2559</td>
										        <td class="uk-text-center">ขูดหินปูน</td>
										        <td class="uk-text-right">5,000</td>
										        <td class="uk-text-right">50</td>
										        <td class="uk-text-right">0</td>
										        <td class="uk-text-right">0</td>
											</tr>
											<tr>  
										        <td class="uk-text-center">2</td>
										        <td class="uk-text-center">100041</td>
										        <td class="uk-text-center">12-05-2559</td>
										        <td class="uk-text-center">จัดฟัน</td>
										        <td class="uk-text-right">45,000</td>
										        <td class="uk-text-right">0</td>
										        <td class="uk-text-right">450</td>
										        <td class="uk-text-right">450</td>
											</tr>  
										</tbody>
									</table>
								</div> 
								<br>
					    </div>
					</div>
					
					<div id="renewalMember" class="uk-modal ">
						<form action="renewalMember" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
					        <a class="uk-modal-close uk-close"></a>
					        
				         	<div class="uk-modal-header"><i class="uk-icon-user"></i> ต่ออายุสมาชิก</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
									<h3 class="hd-text padding5 uk-text-primary"> ต่ออายุสมาชิก </h3>
									 <input type="hidden" id="patient_contypeid" name="patContypeModel.patient_contypeid" >
									ต่ออายุ  <input type="text" id="renewalYear" pattern="[1-9]{1}" title="ใส่ได้เฉพาะตัวเลข  1 - 9" name="patContypeModel.renewalYear" > ปี <button type="submit" name="" id="" class="uk-button uk-button-success"> <i class="uk-icon-save"></i> ต่ออายุสมาชิก</button>
								</div>
					    </div>
					    </form>
					</div>
					
					<div id="removeContype" class="uk-modal ">
						<form action="deleteContype" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
					        <a class="uk-modal-close uk-close"></a>
					        
				         	<div class="uk-modal-header"><i class="uk-icon-user"></i> ลบประเภทสมาชิก</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
									<h3 class="hd-text padding5 uk-text-primary"> ลบประเภทสมาชิก</h3>
									 <input type="hidden" id="patient_contypeidRm" name="patContypeModel.patient_contypeid" >
									ลบประเภทสมาชิก <input type="text" id="contypeNameRm" name="" readonly="readonly">
									<button type="submit" class="uk-button uk-button-success"><i class="uk-icon-check"></i> ยืนยันการลบ</button>
								</div>
					    </div>
					    </form>
					</div>
			</div>
		</div>
		

<div class="uk-container">
    <!-- MODAL ADD BRANCH -->
<div id="addBranch" class="uk-modal">
    <div class="uk-modal-dialog uk-modal-dialog-large uk-form">
        <a class="uk-modal-close uk-close"></a>
        <div class="uk-modal-header"><i class="uk-icon-user-md"></i> สาขา
        </div>
        <table id="tbBranch" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
            <thead id="tblHead">
                <tr>
                    <th class="text-right">Location</th>
                    <th class="text-right">Points</th>
                    <th class="text-right">Mean</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="text-right">Long Island, NY, USA</td>
                    <td class="text-right">3</td>
                    <td class="text-right">45001</td>
                </tr>
                <tr>
                    <td class="text-right">Chicago, Illinois, USA</td>
                    <td class="text-right">5</td>
                    <td class="text-right">76455</td>
                </tr>
                <tr>
                    <td class="text-right">New York, New York, USA</td>
                    <td class="text-right">10</td>
                    <td class="text-right">39097</td>
                </tr>
            </tbody>
        </table>
        <div class="uk-modal-footer uk-text-right">
            <button class="uk-button uk-button-primary">Enter</button>
            <button class="uk-button uk-modal-close"> Close </button>
        </div>
    </div>
</div>
    <!-- MODAL ADD BRANCH -->
<p>
    <br/>
</p>
<p>
    <br/>
</p>
</div>

		
		<script>

			$(document).on('click', '#btn_renewal', fn_buttonmodal_habndler).on('click', '#btn_rmContype', fn_buttonMinusContype_handler).ready(function(){
			    
				$( ".m-patient" ).addClass( "uk-active" );
				$("#file").on('click','.remove-tr',function(){
					$(this).closest('tr').remove();
				});
				
				
			});
			<% if(request.getAttribute("toothHistory")!=null){ 
				
				List<ToothModel> toothHistory = (List) request.getAttribute("toothHistory"); 
				for(ToothModel tm :toothHistory){%>
				$('#tooth_<%=tm.getTooth_num()%>').prepend('<img class="case" src="img/tooth/<%=tm.getTooth_num()%>/<%=tm.getTooth_pic_code()%>/<%=tm.getSurface()%>.png" />');
				<%}
			}%>
			function fn_buttonmodal_habndler(e)
			{
			    //get id from pressed button
			    var fn_patient_contypeid = $(e.target).data('patient_contypeid');
			    
			    $('#renewalMember').on({
			        'uk.modal.show':function(){
			        	$("#patient_contypeid").val(fn_patient_contypeid);
			        },
			        'uk.modal.hide':function(){
			                    //hide modal
			        }
			    }).trigger('uk.modal.show');
			}
			
			function fn_buttonMinusContype_handler(e)
			{
				
			    //get id from pressed button
			    var fn_patient_contypeid = $(e.target).data('patient_contypeid');
			    var fn_patient_contypeName = $(e.target).data('contypename');
			    $('#removeContype').on({
			        'uk.modal.show':function(){
			        	$("#patient_contypeidRm").val(fn_patient_contypeid);
			        	$("#contypeNameRm").val(fn_patient_contypeName);
			        },
			        'uk.modal.hide':function(){
			                    //hide modal
			        }
			    }).trigger('uk.modal.show');
			}
		</script>
	</body>
</html>