<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : กลุ่มการรักษา</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				
				<%@include file="backend-lab-top.jsp" %>

					<div class="uk-grid"></div>
					<form id="service" action="labModeMaster" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">การรักษา</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> กลุ่มการรักษา</h3>
								</div>
								<div class="uk-grid uk-grid-small uk-form "> 
								<!-- 	<div class="uk-form-icon uk-width-1-10"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" name="labModel.lab_id" placeholder="รหัส บริษัท" class="uk-width-1-1"
		                             			pattern="(?=.*\d).{4,}" title="กรุณาใส่รหัสให้ครบ 4 หลัก" maxlength="4" >
		                             		
	                            	</div>
	                             -->
	                             	<div class="uk-form-icon uk-width-1-10"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" name="labModeModel.labmode_id" pattern="[A-z]{1,}" title="กรุณาใส่รหัสให้ครบ 1 หลัก" maxlength="1" placeholder="กลุ่มการรักษา" class="uk-width-1-1"> 
	                            	</div> 
	                            	<div class="uk-form-icon uk-width-4-10"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" name="labModeModel.labmode_name" placeholder="คำอธิบาย" class="uk-width-1-1"> 
	                            	</div> 
	                            	<div class="uk-form-icon uk-width-4-10">
	                            		<button class="uk-button uk-button-success uk-button-small" type="submit" name="save">บันทึก</button>
	                            	</div>
							 	</div>
							</div>
						</div>
					</div>
					<div class="uk-grid ">
						<div class="uk-width-1-1 ">
							<div class="uk-panel uk-panel-box">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">list</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> Detail</h3>
								</div>
									<div class="uk-width-8-10 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									            <th class="uk-text-center">กลุ่ม</th>
									            <th>ชื่อ กลุ่ม</th> 
									            <th> </th> 
									        </tr>
									    </thead> 

									</table>
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