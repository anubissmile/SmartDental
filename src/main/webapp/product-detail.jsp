<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : แก้ไขสินค้า</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				
				<%@include file="backend-lab-top.jsp" %>

					<div class="uk-grid"></div>
					<form id="service" action="addProductInsert" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">แก้ไข</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i>แก้ไข</h3>
								</div>
								<div class="uk-grid uk-grid-small uk-form "> 
								<!-- 	<div class="uk-form-icon uk-width-1-10"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" name="labModel.lab_id" placeholder="รหัส บริษัท" class="uk-width-1-1"
		                             			pattern="(?=.*\d).{4,}" title="กรุณาใส่รหัสให้ครบ 4 หลัก" maxlength="4" >
		                             		
	                            	</div>
	                             -->
	                             	<div class="uk-form-icon uk-width-3-10"> 
										 <p>รหัสสินค้า</p>
	                            	</div>
	                            	<div class="uk-form-icon uk-width-3-10"> 
										 <p>ชื่อสินค้า</p>
	                            	</div>
	                            	<div class="uk-form-icon uk-width-3-10"> 
										 <p>ชื่อสินค้า En</p>
	                            	</div>
	                            	</div>
	                            	<div class="uk-grid uk-grid-small uk-form ">
	                             	<div class="uk-width-3-10"> 
										<s:textfield cssClass="uk-width-1-1" pattern="{1,}" name="productModel.product_id" value="%{productModel.product_id}"/>
		               
	                            	</div> 
	                            	<div class="uk-width-3-10"> 
										<s:textfield cssClass="uk-width-1-1" pattern="{1,}" name="productModel.product_name" value="%{productModel.product_name}"/>
		                             		
	                            	</div>
	                            	<div class="uk-width-3-10"> 
										<s:textfield cssClass="uk-width-1-1" pattern="{1,}" name="productModel.product_name_en" value="%{productModel.product_name_en}"/>
		                             
	                            	</div>
	                            	</div>
	                            	<div class="uk-grid uk-grid-small uk-form ">
	                            	<div class="uk-form-icon uk-width-3-10"> 
										 <p>ราคา</p>
	                            	</div>
	                            	<div class="uk-form-icon uk-width-7-10"> 
										 <p>หน่วยนับ</p>
	                            	</div>
	                            	</div>
	                            	<div class="uk-grid uk-grid-small uk-form ">
	                            	<div class="uk-width-3-10"> 
		                             		<s:textfield cssClass="uk-width-1-1" pattern="{1,}" name="productModel.price" value="%{productModel.price}"/>
		                              
	                            	</div>
	                            	<div class="uk-form-icon uk-width-6-10"> 
										  <s:select cssClass="uk-width-1-2" list="prounitList" name="productModel.productunit_id"
								      	  required="true" headerKey="" headerValue = "กรุณาเลือก"/> 
	                            	</div>
	                            	</div>
	                            	<div class="uk-grid uk-grid-small uk-form ">
	                            	<div class="uk-form-icon uk-width-3-10 uk-hidden"> 
										 <p>ประเภท</p>
	                            	</div>
	                            	<div class="uk-form-icon uk-width-3-10"> 
										 <p>กลุ่มสินค้า</p>
	                            	</div>
	                            	 <div class="uk-form-icon uk-width-3-10"> 
										 <p>ยี่ห้อสินค้า</p>
	                            	</div>
	                            	</div>
	                            	
	                            	<div class="uk-grid uk-grid-small uk-form ">
	                            	
	                            	
	                            	
	                            	<div class="uk-form-icon uk-width-3-10"> 
										 <s:select cssClass="uk-width-1-1" list="progroupList" name="productModel.productgroup_id"
								      	  required="true" headerKey="" headerValue = "กรุณาเลือก"/> 
	                            	</div> 
	                            	
	                            	<div class="uk-form-icon uk-width-3-10"> 
										 <s:select cssClass="uk-width-1-1" list="probrandList" name="productModel.productbrand_id"
								      	  required="true" headerKey="" headerValue = "กรุณาเลือก"/>
	                            	</div>
	                            	</div>
	                            	
							 	<br>
							 	
							 	<div class="uk-grid">
	                            	<div class="uk-width-1-2">
							 			<button class="uk-button uk-button-success uk-align-right " type="submit">บันทึก</button>
	                            	</div>
	                            	<div class="uk-width-1-2">
	                            		<a class="uk-button uk-button-danger " href="getProductList">ยกเลิก</a>
	                            	</div>
	                            	</div>
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
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="id_de" name="id_de" readonly=""> 
					         	</div>
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="name_de" name="name_de" readonly=""> 
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
				$( ".m-setting" ).addClass( "uk-active" );
				 
				
				$("#deleteg").click(function(){
					$("#service").submit();
				}); 
				$("#updateg").click(function(){
					$("#service").submit();
				}); 
				
			});
			
			function update(id, name) { 
				 $("#hdid_up").val(id);
				 $("#id_up").val(id);
				 $("#name_up").val(name);  
			};
			function delete_group(id, name) { 
				 $("#id_de").val(id);
				 $("#name_de").val(name);  
			};
			
			
			
		</script>
	</body>
</html>