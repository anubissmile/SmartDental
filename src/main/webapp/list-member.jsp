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
					<form id="service" action="" method="post">
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
									            <th> </th> 
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
														<a href=""  onclick="update('<s:property value="treatG_id"/>','
														<s:property value="treatG_code"/>','<s:property value="treatG_name" />')"  
														 class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
														<a href="" onclick="delete_group('<s:property value="treatG_id" />','
														<s:property value="treatG_name" />','<s:property value="treatG_code"/>')" 
														class="uk-button uk-button-danger uk-button-small"  data-uk-modal >
														<i class="uk-icon-eraser"></i> ลบ</a>
														
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
									            <th> </th> 
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
														<a href=""  onclick="update('<s:property value="treatG_id"/>','
														<s:property value="treatG_code"/>','<s:property value="treatG_name" />')"  
														 class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
														<a href="" onclick="delete_group('<s:property value="treatG_id" />','
														<s:property value="treatG_name" />','<s:property value="treatG_code"/>')" 
														class="uk-button uk-button-danger uk-button-small"  data-uk-modal >
														<i class="uk-icon-eraser"></i> ลบ</a>
														
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
									<table class="uk-table  uk-table-hover uk-table-striped uk-table-condensed border-gray " 
									id="tablemember-company-active" >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									            <th class="uk-text-center">ชื่อ </th>
									            <th class="uk-text-center">ประเภทสมาชิก </th>
									            <th class="uk-text-center">รูปแบบการวางเงิน</th>  
									            <th class="uk-text-center">จำนวนเงิน</th>									            
									            <th> </th> 
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
													<td class="uk-text-center"><s:property value="total_amount" /></td>
													<td class="uk-text-center">
														<a href=""  onclick="update('<s:property value="treatG_id"/>',
														'<s:property value="treatG_code"/>','<s:property value="treatG_name" />')"  
														 class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
														<a href="" onclick="delete_group('<s:property value="treatG_id" />',
														'<s:property value="treatG_name" />','<s:property value="treatG_code"/>')"
														 class="uk-button uk-button-danger uk-button-small"  data-uk-modal >
														 <i class="uk-icon-eraser"></i> ลบ</a>
														
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
									            <th class="uk-text-center">จำนวนเงิน</th>									            
									            <th> </th> 
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
													<td class="uk-text-center"><s:property value="total_amount" /></td>
													<td class="uk-text-center">
														<a href=""  onclick="update('<s:property value="treatG_id"/>','<s:property value="treatG_code"/>','<s:property value="treatG_name" />')"   class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
														<a href="" onclick="delete_group('<s:property value="treatG_id" />','<s:property value="treatG_name" />','<s:property value="treatG_code"/>')" class="uk-button uk-button-danger uk-button-small"  data-uk-modal ><i class="uk-icon-eraser"></i> ลบ</a>
														
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
									            <th> </th> 
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
														<a href=""  onclick="update('<s:property value="treatG_id"/>','<s:property value="treatG_code"/>','<s:property value="treatG_name" />')"   class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
														<a href="" onclick="delete_group('<s:property value="treatG_id" />','<s:property value="treatG_name" />','<s:property value="treatG_code"/>')" class="uk-button uk-button-danger uk-button-small"  data-uk-modal ><i class="uk-icon-eraser"></i> ลบ</a>
														
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
									            <th> </th> 
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
														<a href=""  onclick="update('<s:property value="treatG_id"/>','<s:property value="treatG_code"/>','<s:property value="treatG_name" />')"   class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
														<a href="" onclick="delete_group('<s:property value="treatG_id" />','<s:property value="treatG_name" />','<s:property value="treatG_code"/>')" class="uk-button uk-button-danger uk-button-small"  data-uk-modal ><i class="uk-icon-eraser"></i> ลบ</a>
														
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
					 
					<div id="update" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-pencil"></i> แก้ไข</div>
					         <div class="uk-grid uk-grid-small"> 
					         	<div class="uk-width-2-10">
					         		<input class="uk-width-1-1 uk-text-center" type="text" id="id_up" name="id_up" autofocus="autofocus"> 
					         		<input type="hidden" id="hdid_up" name="hdid_up" >
					         	</div>
					         	<div class="uk-width-8-10 uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="name_up" name="name_up" > 
					         	</div>
					         </div>	  
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-button uk-button-success" id="updateg" name="updateg">ตกลง</button>
					         	<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button> 
					         </div>
					    </div>
					</div>
					
					<div id="delete_group" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-eraser"></i> ลบ</div>
					         	<p class="uk-text-danger">ท่านต้องการลบข้อมูลใช่หรือไม่!</p>
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input  class="uk-width-1-1 hidden" type="text" id="id_de" name="id_de" readonly> 
					         	<input  class="uk-width-1-1 " type="text" id="code" name="code" readonly> 
					         	</div>
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="name_de" name="name_de" readonly> 
					         	</div>
					         	 
					         <div class="uk-modal-footer uk-text-right"> 
					         	<button class="uk-button uk-button-success" id="deleteg" name="deleteg">ตกลง</button>
					         	<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
					         </div>
					    </div>
					</div>
				</div>	
					</form> 
			</div>
					
					
		</div>

		<script>
			$(document).ready(function(){
				$("#tablemember-empolyee-active").dataTable();
				$("#tablemember-empolyee-inactive").dataTable();
				$("#tablemember-company-active").dataTable();
				$("#tablemember-company-inactive").dataTable()
				$("#tablemember-genaral-active").dataTable();
				$("#tablemember-genaral-inactive").dataTable()				
				$( ".m-setting" ).addClass( "uk-active" );
				 
				tablemember
				$("#deleteg").click(function(){
					$("#service").submit();
				}); 
				$("#updateg").click(function(){
					$("#service").submit();
				}); 
				
			});
			
			function update(id, code, name) { 
				 $("#hdid_up").val(id);
				 $("#id_up").val(code);
				 $("#name_up").val(name);  
			};
			function delete_group(id, name,code) { 
				 $("#id_de").val(id);
				 $("#name_de").val(name); 
				 $("#code").val(code);
			};
			
			
			
		</script>
	</body>
</html>