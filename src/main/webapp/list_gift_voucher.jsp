<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : Gift Voucher</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				
				<%@include file="backend-lab-top.jsp" %>

					<div class="uk-grid"></div>
					<form  action="" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid uk-grid-collapse">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Gift Voucher</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> Gift Voucher
								    <span class="uk-form-icon uk-width-2-10  uk-text-right">
	                            		<a href="create_giftvoucher.jsp" class="uk-button uk-button-success" >เพิ่ม Gift Voucher</a>
	                            	</span>
								    </h3>
								</div>
								<div class="uk-width-1-1 uk-overflow-container uk-form">
									<div class="uk-width-1-1 uk-margin-medium-bottom">
			 					<ul class="uk-tab" data-uk-switcher="{
			 							connect:'#Gift-active',
			 							animation: 'fade'
			 						}">
								    <li class="uk-active"><a href="#">Active</a></li>
								    <li><a href="#">Inactive</a></li>
								</ul>
			 				</div>
			 				<ul class="uk-width-1-1 uk-switcher" id="Gift-active">  
							 	<li class="uk-active">
									<table id="tb-ac" style="margin-bottom:100px;"  class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ชื่อชุด Gift Voucher</th>
									            <th class="uk-text-center">จำนวน Gift Voucher ทั้งหมด</th> 
									            <th class="uk-text-center">วันเริ่มใช้งาน</th>
									            <th class="uk-text-center">วันหมดอายุ</th>
									            <th class="uk-text-center">จัดการ</th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="getgiftVoucherList">
									    	<s:if test="gv_status == \"t\"">
									    	<tr>
									    		<td class="uk-text-center"><s:property value="gv_name"/></td>
									    		<td class="uk-text-center"><s:property value="gv_run_count"/> ใบ</td>
									    		<td class="uk-text-center"><s:property value="gv_start_date"/></td>
									    		<td class="uk-text-center"><s:property value="gv_expiredate"/></td>
									    		<td class="uk-text-center">
													<div class="uk-button-dropdown" data-uk-dropdown>
									                    <button class="uk-button uk-button-success" type="button">
									                    	จัดการ<i class="uk-icon-caret-down"></i>
								                    	</button>
									                    <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" >
									                        <ul class="uk-nav uk-nav-dropdown">								                            	
									                            <li class="uk-text-left">
													    			<a href="getGiftVoucherLineList-<s:property value="gv_id" />">
													    				<i class="uk-icon-pencil"></i> แก้ไข
													    			</a>
								                            	</li>
								                            	<li class="uk-nav-divider"></li>
								                            	<li class="uk-text-left">
													    			<a href="" onclick="change_gift('<s:property value="gv_id" />'
													    			,'<s:property value="gv_status" />')" data-uk-modal>
													    				<i class="uk-icon-eye-slash"></i> เปลื่ยนสถานะ
													    			</a>
								                            	</li>
								                            	<li class="uk-nav-divider"></li>
								                            	<li class="uk-text-left">
													    			<a href="" 
													    			onclick="delete_gift('<s:property value="gv_id" />')" data-uk-modal>
													    				<i class="uk-icon-eraser"></i> ลบ
													    			</a>
								                            	</li>
									                        </ul>
									                    	</div>
									               		 </div>
									               		 <a href="" class="uk-button uk-button-primary">
													    				<i class="uk-icon-list"></i> ดูสิทธิประโยชน์
													    	</a>														
													</td>
									    	</tr>
									    	</s:if>
						    				</s:iterator>			    
									    </tbody>   
									</table>
									</li>
									<li >
									<table id="tb-in" style="margin-bottom:100px;" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ชื่อชุด Gift Voucher</th>
									            <th class="uk-text-center">จำนวน Gift Voucher ทั้งหมด</th> 
									            <th class="uk-text-center">วันเริ่มใช้งาน</th>
									            <th class="uk-text-center">วันหมดอายุ</th>
									            <th></th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="getgiftVoucherList">
									    	<s:if test="gv_status== \"f\"">
									    	<tr>
									    		<td class="uk-text-center"><s:property value="gv_name"/></td>									    		
									    		<td class="uk-text-center"><s:property value="gv_run_count"/> ใบ</td>
									    		<td class="uk-text-center"><s:property value="gv_start_date"/></td>
									    		<td class="uk-text-center"><s:property value="gv_expiredate"/></td>
									    		<td class="uk-text-center">
													<div class="uk-button-dropdown" data-uk-dropdown>
									                    <button class="uk-button uk-button-success" type="button">
									                    	จัดการ<i class="uk-icon-caret-down"></i>
								                    	</button>
									                    <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" >
									                        <ul class="uk-nav uk-nav-dropdown">								                            	
									                            <li class="uk-text-left">
													    			<a href="getGiftVoucherLineList-<s:property value="gv_id" />">
													    				<i class="uk-icon-pencil"></i> ดูรายการ
													    			</a>
								                            	</li>
								                            	<li class="uk-nav-divider"></li>
								                            	<li class="uk-text-left">
													    			<a href="" onclick="change_gift('<s:property value="gv_id" />'
													    			,'<s:property value="gv_status" />')" data-uk-modal>
													    				<i class="uk-icon-eye-slash"></i> เปลื่ยนสถานะ
													    			</a>
								                            	</li>
								                            	<li class="uk-nav-divider"></li>
								                            	<li class="uk-text-left">
													    			<a href="" 
													    			onclick="delete_gift('<s:property value="gv_id" />')" data-uk-modal>
													    				<i class="uk-icon-eraser"></i> ลบ
													    			</a>
								                            	</li>
									                        </ul>
									                    	</div>
									               		 </div>	
									               		 <a href="" id="chkprivilege" data-gvid='<s:property value="gv_id"/>'
									               		  class="uk-button uk-button-primary"  data-uk-modal>
													    				<i class="uk-icon-list"></i> ดูสิทธิประโยชน์
													    	</a>													
													</td>
									    	</tr>
									    	</s:if>
						    				</s:iterator>			    
									    </tbody>   
									</table>
									</li>
									</ul>
									</div>
							</div>
						</div>
					</div>					 
					
				</div>	
					</form> 
			</div>		
		</div>
										<div id="select_branch" class="uk-modal ">
								    <div class="uk-modal-dialog uk-modal-dialog-large uk-form " >
								        <a class="uk-modal-close uk-close"></a>
								         <div class="uk-modal-header"><i class="uk-icon-building"></i> สิทธิประโยชน์</div>
							         	<div class="uk-width-1-1 uk-overflow-container">
											<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
											    <thead>
											        <tr class=""> 
											        	<th class="uk-text-center">จำนวนเงิน</th>
											            <th class="uk-text-center">รูปแบบรายการ</th> 
											            <th class="uk-text-center">ชื่อ</th>
											            <th class="uk-text-center">ประเภทรายการ</th>     
											        </tr>
											    </thead> 
											    <tbody class="privatePrivilege">
													
												</tbody>
											</table>
										</div>
								         <div class="uk-modal-footer uk-text-right">
								         	<button class="uk-modal-close">ปิด</button> 
								         </div>
								    </div>
					</div>
					<form action="deleteGiftVoucher" id="delGiftCard">
					    <input class="uk-width-1-1 " type="hidden"  id="idgift" name="giftvoucherModel.gv_id" > 
					</form>
					<form action="changeStatusGiftVoucher" id="changeStatusGiftCard">
					    <input class="uk-width-1-1 hidden" type="text"  id="giftid" name="giftvoucherModel.gv_id" >
					    <input class="uk-width-1-1 hidden" type="text" id="statusgift" name="giftvoucherModel.gv_status" >  
					</form>

		<script>
			$(document).ready(function(){
				$("#tb-ac").dataTable();
				$("#tb-in").dataTable();
				
			});
			$(document).on("click","#chkprivilege",function(e){
				 var id = $(e.target).data('gvid');
				$.ajax({  //   
				    type: "post",
				    url: "ajax_json_GiftVoucherPrivilege", //this is my servlet group
				    data: {giftid:id},
				    async:true, 
				    success: function(result){ 
					    if (result != '') {	
					    	var selectGroup = "";
					    	let type1 = ""
					    	let type2 =""
					    	$.each(result, function(i, value) { 
					    		if(value.type == 1){
					    			type1 = 'กำหนดราคา'
		    					}else{
		    						type1 ='แทนเงินสด'
		    					} 
					    		if(value.pro_type == 5){
					    			type2 ='กลุ่มการรักษา'		
			    					}else if(value.pro_type == 6){
			    						type2 ='หมวดการรักษา'
			    					}else if(value.pro_type == 7){
			    						type2 ='รายการรักษา'
			    					}
					    		selectGroup += '<tr> '+
						    					'<th class="uk-text-center">'+value.amount+'</th>'+
						    					'<th class="uk-text-center">'+type1+'</th>'+
						    					'<th class="uk-text-center">'+value.name+'</th>'+
						    					'<th class="uk-text-center">'+type2+'</th>'+							    					
						    					'</tr>' 
					    	});          
					    	$(".privatePrivilege").html(selectGroup);  
					    }
				    }
				});
				var modal = UIkit.modal("#select_branch");
				if ( modal.isActive() ) {
				    modal.hide();
				} else {
				    modal.show();
				}
			}) 

	

			function delete_gift(id) { 
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
		   				$("#idgift").val(id);
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
		function change_gift(id, stus) { 

			swal({
   			  title: 'อนุมัติการทำงาน',
   			  text: "ท่านต้องการยืนยันการเปลื่ยนแปลงหรือไม่!",  		   				  			  
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
	   				$("#giftid").val(id);
					 $("#statusgift").val(stus);
					$("#changeStatusGiftCard").submit();
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