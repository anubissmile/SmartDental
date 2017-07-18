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
				
				<%@include file="nav-member-top.jsp" %>

					<div class="uk-grid uk-grid-collapse"></div>
					<form  action="" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid uk-grid-collapse">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">บริษัท</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> ข้อมูลบริษัทแบบบิลล์</h3>
								</div>
								<div class="uk-grid uk-grid-small uk-form "> 
	                             	<div class=" uk-width-2-10"> 
		                             		ชื่อบริษัท : <s:textfield  name="protionModel.sub_contactname"
		                             		readonly="true"  cssClass="uk-width-1-2" /> 
	                            	</div> 
							 	</div>
							</div>
						</div>
					</div>
					<div class="uk-grid uk-grid-collapse">
						<div class="uk-width-1-1 ">
							<div class="uk-panel uk-panel-box">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">list</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> Detail</h3>
								</div>
									<div class="uk-width-1-1 uk-form uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " id="tablemember">
									    <thead>
									        <tr class="hd-table">
									            <th class="uk-text-center">วันที่-เวลา</th>
									            <th class="uk-text-center">จำนวนเงิน</th>
									            <th class="uk-text-center">การจัดการ</th> 
									            <th class="uk-text-center">รหัสพนักงาน</th>  
									        </tr>
									    </thead> 
										<tbody>
											<s:iterator value="">
												<tr>
													<td class="uk-text-center"><s:property value="" /></td>
													<td class="uk-text-center"><s:property value="" /></td>
													<td class="uk-text-center"></td>													
													<td class="uk-text-center"><s:property value="" /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
									</div>
							</div>
						</div> 
					</div>					 				
				</div>	
					</form> 					
			</div>										
		</div>
			
		
<script src="js/autoNumeric.min.js"></script>	
		<script>
			$(document).ready(function(){
				$(".numeric").autoNumeric('init');
				$('#tablemember').dataTable();
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