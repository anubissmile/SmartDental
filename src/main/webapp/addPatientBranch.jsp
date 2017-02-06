<%@page import="com.smict.person.model.BranchModel"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:คนไข้</title>
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
					<!-- RIGHT CONTENT -->
					<div class="uk-width-6-10">
						<div class="uk-grid">
							<div class="uk-width-1-1 uk-text-left uk-padding-large">
								<p><a href="#add_branch" class="uk-button uk-button-success" data-uk-modal><i class=" uk-icon-plus"></i> เพิ่มข้อมูลสาขา</a></p>
							</div>
							<p><br></p>
							<div class="uk-width-1-1 uk-text-center">
								<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray" id="table_branch">
									<thead>
										<tr class="hd-table uk-text-center">
											<th>รหัส</th>
											<th>สาขา</th>
											<th>วันที่</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>324</td>
											<td>LDC รัตนาธิเบศธ์</td>
											<td>17/1/60</td>
										</tr>
										<tr>
											<td>324</td>
											<td>LDC รัตนาธิเบศธ์</td>
											<td>17/1/60</td>
										</tr>
										<tr>
											<td>324</td>
											<td>LDC รัตนาธิเบศธ์</td>
											<td>17/1/60</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- RIGHT CONTENT -->
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

<!-- MODAL ZONE -->
<!-- LOAD ADD PATIENT BRANCH MODAL -->
<div class="uk-modal" id="add_branch">
    <div class="uk-modal-dialog">
       	<a class="uk-modal-close uk-close"></a>
		<div class="uk-modal-header uk-h2">
        	เลือกสาขา
       	</div>
		<form action="" class="uk-form uk-padding-remove-bottom">
			<div class="uk-grid">
				<div class="uk-width-1-1 uk-h3" id="wrap_chk_branch">
					<s:select list="chunkBranch" 
						label="เลือกสาขา (LDC)"
						headerKey="-1"
						headerValue="รายการสาขา (LDC)"
						name="branchModel.branch_code" 
						value="bm.branch_code" 
						class="uk-form-width-large" 
					/>
				</div>
			</div>
			<div class="uk-modal-footer uk-text-right">
				<button class="uk-button uk-button-success" type="submit" id="save" name="save">ตกลง</button>
				<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
			</div>
		</form>
    </div>
</div>
<!-- LOAD ADD PATIENT BRANCH MODAL -->
<!-- MODAL ZONE -->

		<script>

			$(document).ready(function() {
				$("#table_branch").DataTable();	

				// alert("hello");
				$("#wrap_chk_branch").on('change', '#branchModel_branch_code', function(event) {
					event.preventDefault();
					alert($(this).val() + " | " + $("#branchModel_branch_code option:selected").text());
					/* Act on the event */
				});;
			});

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