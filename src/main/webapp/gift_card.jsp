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
				
				<%@include file="backend-lab-top.jsp" %>

					<div class="uk-grid"></div>
					<form  action="" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid uk-grid-collapse">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Gift Card</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> Gift Card
								    <span class="uk-form-icon uk-width-2-10  uk-text-right">
	                            		<a href="create_giftcard.jsp" class="uk-button uk-button-success" >เพิ่ม Gift Card</a>
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
									<table id="tb-ac" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ชื่อชุด Gift Card</th>
									            <th class="uk-text-center">จำนวนเงินภายในบัตร</th>
									            <th class="uk-text-center">จำนวน Gift Card ทั้งหมด</th> 
									            <th class="uk-text-center">วันเริ่มใช้งาน</th>
									            <th class="uk-text-center">วันหมดอายุ</th>
									            <th class="uk-text-center">จัดการ</th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="getgiftcardlist">
									    	<s:if test="giftcard_status == \"t\"">
									    	<tr>
									    		<td class="uk-text-center"><s:property value="giftcard_name"/></td>
									    		<td class="uk-text-center"><s:property value="getText('{0,number,#,##0.00}',{giftcard_default_amount})"/> บาท</td>
									    		<td class="uk-text-center"><s:property value="giftcard_run_count"/> ใบ</td>
									    		<td class="uk-text-center"><s:property value="giftcard_start_date"/></td>
									    		<td class="uk-text-center"><s:property value="giftcard_expiredate"/></td>
									    		<td class="uk-text-center">
													<div class="uk-button-dropdown" data-uk-dropdown>
									                    <button class="uk-button uk-button-success" type="button">
									                    	จัดการ<i class="uk-icon-caret-down"></i>
								                    	</button>
									                    <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" >
									                        <ul class="uk-nav uk-nav-dropdown">								                            	
									                            <li class="uk-text-left">
													    			<a href="getGiftCardline-<s:property value="giftcard_id" />">
													    				<i class="uk-icon-pencil"></i> แก้ไข
													    			</a>
								                            	</li>
								                            	<li class="uk-nav-divider"></li>
								                            	<li class="uk-text-left">
													    			<a href="" onclick="change_gift('<s:property value="giftcard_id" />'
													    			,'<s:property value="giftcard_status" />')" data-uk-modal>
													    				<i class="uk-icon-eye-slash"></i> เปลื่ยนสถานะ
													    			</a>
								                            	</li>
								                            	<li class="uk-nav-divider"></li>
								                            	<li class="uk-text-left">
													    			<a href="" 
													    			onclick="delete_gift('<s:property value="giftcard_id" />')" data-uk-modal>
													    				<i class="uk-icon-eraser"></i> ลบ
													    			</a>
								                            	</li>
									                        </ul>
									                    	</div>
									               		 </div>														
													</td>
									    	</tr>
									    	</s:if>
						    				</s:iterator>			    
									    </tbody>   
									</table>
									</li>
									<li >
									<table id="tb-in" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ชื่อชุด Gift Card</th>
									            <th class="uk-text-center">จำนวนเงินภายในบัตร</th>
									            <th class="uk-text-center">จำนวน Gift Card ทั้งหมด</th> 
									            <th class="uk-text-center">วันเริ่มใช้งาน</th>
									            <th class="uk-text-center">วันหมดอายุ</th>
									            <th></th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="getgiftcardlist">
									    	<s:if test="giftcard_status== \"f\"">
									    	<tr>
									    		<td class="uk-text-center"><s:property value="giftcard_name"/></td>
									    		<td class="uk-text-center"><s:property value="getText('{0,number,#,##0.00}',{giftcard_default_amount})"/> บาท</td>
									    		<td class="uk-text-center"><s:property value="giftcard_run_count"/> ใบ</td>
									    		<td class="uk-text-center"><s:property value="giftcard_start_date"/></td>
									    		<td class="uk-text-center"><s:property value="giftcard_expiredate"/></td>
									    		<td class="uk-text-center">
													<div class="uk-button-dropdown" data-uk-dropdown>
									                    <button class="uk-button uk-button-success" type="button">
									                    	จัดการ<i class="uk-icon-caret-down"></i>
								                    	</button>
									                    <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" >
									                        <ul class="uk-nav uk-nav-dropdown">								                            	
									                            <li class="uk-text-left">
													    			<a href="getGiftCardline-<s:property value="giftcard_id" />">
													    				<i class="uk-icon-pencil"></i> แก้ไข
													    			</a>
								                            	</li>
								                            	<li class="uk-nav-divider"></li>
								                            	<li class="uk-text-left">
													    			<a href="" onclick="change_gift('<s:property value="giftcard_id" />'
													    			,'<s:property value="giftcard_status" />')" data-uk-modal>
													    				<i class="uk-icon-eye-slash"></i> เปลื่ยนสถานะ
													    			</a>
								                            	</li>
								                            	<li class="uk-nav-divider"></li>
								                            	<li class="uk-text-left">
													    			<a href="" 
													    			onclick="delete_gift('<s:property value="giftcard_id" />')" data-uk-modal>
													    				<i class="uk-icon-eraser"></i> ลบ
													    			</a>
								                            	</li>
									                        </ul>
									                    	</div>
									               		 </div>														
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
					<form action="delGiftCard" id="delGiftCard">
					    <input class="uk-width-1-1 " type="hidden"  id="idgift" name="giftcardModel.giftcard_id" > 
					</form>
					<form action="changeStatusGiftCard" id="changeStatusGiftCard">
					    <input class="uk-width-1-1 hidden" type="text"  id="giftid" name="giftcardModel.giftcard_id" >
					    <input class="uk-width-1-1 hidden" type="text" id="statusgift" name="giftcardModel.giftcard_status" >  
					</form>

		<script>
			$(document).ready(function(){
				$("#tb-ac").dataTable();
				$("#tb-in").dataTable();
				
			});
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