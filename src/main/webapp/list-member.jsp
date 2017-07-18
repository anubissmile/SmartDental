<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : Member</title>
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
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1 uk-form">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Member</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> ข้อมูล Member
								    <div class="uk-form-icon uk-width-4-10">
	                            		<a href="master-member.jsp" class="uk-button uk-button-success " >เพิ่ม Member</a>
	                            	</div>
								    </h3>
								</div>
							<div class="uk-width-1-1 uk-margin-medium-bottom">
			 					<ul class="uk-tab" data-uk-switcher="{
			 							connect:'#member-active',
			 							animation: 'fade'
			 						}">
								    <li class="uk-active"><a href="#">คนไข้ทั่วไป</a></li>
								    <li><a href="#">คนไข้แบบบริษัท</a></li>
								    <li><a href="#">คนไข้พนักงาน</a></li>
								</ul>
			 				</div>
			 				<ul class="uk-width-1-1 uk-switcher" id="member-active">  
							 	<li class="uk-active">
							<div class="uk-width-1-1 uk-margin-medium-bottom">
			 					<ul class="uk-tab" data-uk-switcher="{
			 							connect:'#member-genaral-active',
			 							animation: 'fade'
			 						}">
								    <li class="uk-active"><a href="#">Active</a></li>
								    <li><a href="#">Inactive</a></li>
								</ul>
			 				</div>
			 				<ul class="uk-width-1-1 uk-switcher" id="member-genaral-active">  
							 	<li class="uk-active">
									<table class="uk-table  uk-table-hover uk-table-striped uk-table-condensed border-gray " 
									id="tablemember-genaral-active" >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									            <th class="uk-text-center">ชื่อ </th>
									            <th class="uk-text-center">ประเภทสมาชิก </th>								            
									            <th class="uk-text-center">จัดการ / ปิดการใช้งาน</th>
									        </tr>
									    </thead> 
										<tbody>
											<s:iterator value="promotionModel">
												<s:if test="contact_id == 1 && status_subcontact == 1">
												<tr>
													<td class="uk-text-center"><s:property value="sub_contactid" /></td>
													<td class="uk-text-center"><s:property value="sub_contactname" /></td>
													<td class="uk-text-center"><s:property value="name" /></td>
													<td class="uk-text-center">
														<a href="getEditMember-<s:property value="sub_contactid" />-<s:property value="sub_contact_type_id" />" 
													    	class="uk-button uk-button-primary uk-button-small">
													    	<i class="uk-icon-pencil"></i> แก้ไข
													    </a>
														<a href="" onclick="delete_group('<s:property value="sub_contactid" />',
														'<s:property value="status_subcontact" />')"  
														class="uk-button uk-button-danger uk-button-small" data-uk-modal  >
														<i class=" uk-icon-eye-slash"></i> </a>
														
													</td>													
												</tr>
												</s:if>
											</s:iterator>
										</tbody>
									</table>							 		
							 	</li>
							 	<li class="">
									<table class="uk-table  uk-table-hover uk-table-striped uk-table-condensed border-gray " 
									id="tablemember-genaral-inactive" >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									            <th class="uk-text-center">ชื่อ </th>
									            <th class="uk-text-center">ประเภทสมาชิก </th>								            
									            <th class="uk-text-center">จัดการ / เปิดการใช้งาน</th>
									        </tr>
									    </thead> 
										<tbody>
											<s:iterator value="promotionModel">
												<s:if test="contact_id == 1 && status_subcontact == 0">
												<tr>
													<td class="uk-text-center"><s:property value="sub_contactid" /></td>
													<td class="uk-text-center"><s:property value="sub_contactname" /></td>
													<td class="uk-text-center"><s:property value="name" /></td>
													<td class="uk-text-center">
														<a class="uk-button uk-button-primary uk-button-small"
														 href="getEditMember-<s:property value="sub_contactid" />-<s:property value="sub_contact_type_id" />">
													    	<i class="uk-icon-pencil"></i> แก้ไข
													    </a>
														<a href="" onclick="delete_group('<s:property value="sub_contactid" />',
														'<s:property value="status_subcontact" />')"  
														class="uk-button uk-button-success uk-button-small" data-uk-modal  >
														<i class=" uk-icon-eye-slash"></i> </a>
														
													</td>													
												</tr>
												</s:if>
											</s:iterator>
										</tbody>
									</table>							 		
							 	</li>							 	
							 </ul>	
 									
							</li>
							 	<li class="">
								<div class="uk-width-1-1 uk-margin-medium-bottom">
				 					<ul class="uk-tab" data-uk-switcher="{
				 							connect:'#member-company-active',
				 							animation: 'fade'
				 						}">
									    <li class="uk-active"><a href="#">Active</a></li>
									    <li><a href="#">Inactive</a></li>
									</ul>
			 					</div>
			 					<ul class="uk-width-1-1 uk-switcher" id="member-company-active">  
							 		<li class="uk-active">
									<table style="margin-bottom:50px;" class="uk-table  uk-table-hover uk-table-striped uk-table-condensed border-gray " 
									id="tablemember-company-active" >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									            <th class="uk-text-center">ชื่อ </th>
									            <th class="uk-text-center">ประเภทสมาชิก </th>
									            <th class="uk-text-center">รูปแบบการวางเงิน</th>  								            
									            <th class="uk-text-center">จัดการ / ปิดการใช้งาน</th>
									        </tr>
									    </thead> 
										<tbody>
											<s:iterator value="promotionModel">
											<s:if test="contact_id == 2 && status_subcontact == 1">
												<tr>
													<td class="uk-text-center"><s:property value="sub_contactid" /></td>
													<td class="uk-text-center"><s:property value="sub_contactname" /></td>
													<td class="uk-text-center"><s:property value="name" /></td>
													<td class="uk-text-center"><s:property value="contypeName" /></td>
													<td class="uk-text-center">
													<div class="uk-button-dropdown" data-uk-dropdown>
									                    <button class="uk-button uk-button-success" type="button">
									                    	จัดการ<i class="uk-icon-caret-down"></i>
								                    	</button>
									                    <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" >
									                        <ul class="uk-nav uk-nav-dropdown">								                            	
									                            <li class="uk-text-left">
													    			<a href="getEditMember-<s:property value="sub_contactid" />-<s:property value="sub_contact_type_id" />">
													    				<i class="uk-icon-pencil"></i> แก้ไข
													    			</a>
								                            	</li>
								                            	<li class="uk-nav-divider"></li>
								                            	<li class="uk-text-left">
													    			<a href="getcompanyMember-<s:property value="sub_contactid" />-<s:property value="sub_contact_type_id" />">
													    				<i class="uk-icon-institution"></i> ดูข้อมูล
													    			</a>
								                            	</li>
									                        </ul>
									                    	</div>
									               		 </div>
														<a href="" onclick="delete_group('<s:property value="sub_contactid" />',
														'<s:property value="status_subcontact" />')" 
														 class="uk-button uk-button-danger uk-button-small"  data-uk-modal >
														 <i class=" uk-icon-eye-slash"></i> </a>
														
													</td>
												</tr>
											</s:if>	
											</s:iterator>
										</tbody>
									</table>							 		
							 		</li>
							 		<li class="">
									<table class="uk-table  uk-table-hover uk-table-striped uk-table-condensed border-gray " 
									id="tablemember-company-inactive" >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									            <th class="uk-text-center">ชื่อ </th>
									            <th class="uk-text-center">ประเภทสมาชิก </th>
									            <th class="uk-text-center">รูปแบบการวางเงิน</th>  									            
									            <th class="uk-text-center">จัดการ / เปิดการใช้งาน</th>
 
									        </tr>
									    </thead> 
										<tbody>
											<s:iterator value="promotionModel">
											<s:if test="contact_id == 2 && status_subcontact == 0">
												<tr>
													<td class="uk-text-center"><s:property value="sub_contactid" /></td>
													<td class="uk-text-center"><s:property value="sub_contactname" /></td>
													<td class="uk-text-center"><s:property value="name" /></td>
													<td class="uk-text-center"><s:property value="contypeName" /></td>
													<td class="uk-text-center">
													<div class="uk-button-dropdown" data-uk-dropdown>
									                    <button class="uk-button uk-button-success" type="button">
									                    	จัดการ<i class="uk-icon-caret-down"></i>
								                    	</button>
									                    <div class="uk-dropdown uk-dropdown-small uk-dropdown-top">
									                        <ul class="uk-nav uk-nav-dropdown">								                            	
									                            <li class="uk-text-left">
													    			<a href="getEditMember-<s:property value="sub_contactid" />-<s:property value="sub_contact_type_id" />">
													    				<i class="uk-icon-pencil"></i> แก้ไข
													    			</a>
								                            	</li>
								                            	<li class="uk-nav-divider"></li>
								                            	<li class="uk-text-left">
													    			<a href="getcompanyMember-<s:property value="sub_contactid" />-<s:property value="sub_contact_type_id" />">
													    				<i class="uk-icon-institution"></i> ดูข้อมูล
													    			</a>
								                            	</li>
									                        </ul>
									                    	</div>
									               		 </div>
														<a href="" onclick="delete_group('<s:property value="sub_contactid" />',
														'<s:property value="status_subcontact" />')"  
														class="uk-button uk-button-success uk-button-small"  data-uk-modal >
														<i class=" uk-icon-eye-slash"></i> </a>
														
													</td>
												</tr>
											</s:if>	
											</s:iterator>
										</tbody>
									</table>							 		
							 		</li>							 									 		
							 	</ul>
 
							</li>
							 	<li class="">
							<div class="uk-width-1-1 uk-margin-medium-bottom">
			 					<ul class="uk-tab" data-uk-switcher="{
			 							connect:'#member-empolyee-active',
			 							animation: 'fade'
			 						}">
								    <li class="uk-active"><a href="#">Active</a></li>
								    <li><a href="#">Inactive</a></li>
								</ul>
			 				</div>
			 				<ul class="uk-width-1-1 uk-switcher" id="member-empolyee-active">  
							 	<li class="uk-active">
 									<table class="uk-table  uk-table-hover uk-table-striped uk-table-condensed border-gray " 
 									id="tablemember-empolyee-active" >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									            <th class="uk-text-center">ชื่อ </th>
									            <th class="uk-text-center">ประเภทสมาชิก </th>								            
									            <th class="uk-text-center">จัดการ / เปิดการใช้งาน</th> 
									        </tr>
									    </thead> 
										<tbody>
											<s:iterator value="promotionModel">
											<s:if test="contact_id == 3 && status_subcontact == 1">
												<tr>
													<td class="uk-text-center"><s:property value="sub_contactid" /></td>
													<td class="uk-text-center"><s:property value="sub_contactname" /></td>
													<td class="uk-text-center"><s:property value="name" /></td>
													<td class="uk-text-center">
														<a class="uk-button uk-button-primary uk-button-small" 
														 href="getEditMember-<s:property value="sub_contactid" />-<s:property value="sub_contact_type_id" />">
													    	<i class="uk-icon-pencil"></i> แก้ไข
													    </a>
														<a href="" onclick="delete_group('<s:property value="sub_contactid" />',
														'<s:property value="status_subcontact" />')"  
														class="uk-button uk-button-danger uk-button-small"  data-uk-modal >
														<i class=" uk-icon-eye-slash"></i></a>
														
													</td>
												</tr>
											</s:if>
											</s:iterator>
										</tbody>
									</table>							 	
							 	</li>
							 	<li class="">
 									<table class="uk-table  uk-table-hover uk-table-striped uk-table-condensed border-gray " 
 									id="tablemember-empolyee-inactive" >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									            <th class="uk-text-center">ชื่อ </th>
									            <th class="uk-text-center">ประเภทสมาชิก </th>								            
									            <th class="uk-text-center">จัดการ </th>
									            <th class="uk-text-center">เปิดการใช้งาน </th> 
									        </tr>
									    </thead> 
										<tbody>
											<s:iterator value="promotionModel">
											<s:if test="contact_id == 3 && status_subcontact == 0">
												<tr>
													<td class="uk-text-center"><s:property value="sub_contactid" /></td>
													<td class="uk-text-center"><s:property value="sub_contactname" /></td>
													<td class="uk-text-center"><s:property value="name" /></td>
													<td class="uk-text-center">
														<a class="uk-button uk-button-primary uk-button-small"
														 href="getEditMember-<s:property value="sub_contactid" />-<s:property value="sub_contact_type_id" />">
													    	<i class="uk-icon-pencil"></i> แก้ไข
													    </a>
														<a href="" onclick="delete_group('<s:property value="sub_contactid" />',
														'<s:property value="status_subcontact" />')" 
														class="uk-button uk-button-success uk-button-small"  data-uk-modal >
														<i class=" uk-icon-eye-slash"></i></a>
														
													</td>
												</tr>
											</s:if>
											</s:iterator>
										</tbody>
									</table>							 	
							 	</li>							 	
							</ul> 								 	
							</li>														
							</ul>		
							</div>
						</div>
					</div>					 					
				</div>	
					</form> 
			</div>
					
					
		</div>
					<form action="updatestatusChange" id="updatememberstus">
					    <input class="uk-width-1-1 hidden" type="text"  id="idsub" name="protionModel.sub_contactid" >
					    <input class="uk-width-1-1 hidden" type="text" id="statussub" name="protionModel.status_subcontact" >  
					</form>
		<script>
			$(document).ready(function(){
				$("#tablemember-empolyee-active").dataTable();
				$("#tablemember-empolyee-inactive").dataTable();
				$("#tablemember-company-active").dataTable();
				$("#tablemember-company-inactive").dataTable()
				$("#tablemember-genaral-active").dataTable();
				$("#tablemember-genaral-inactive").dataTable()				
				$( ".m-setting" ).addClass( "uk-active" );
				
				 
			});
			function delete_group(id, stus) { 

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
			   				$("#idsub").val(id);
							 $("#statussub").val(stus);
							$("#updatememberstus").submit();
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