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
				
				<%@include file="backend-giftvoucher-line.jsp" %>

					<div class="uk-grid"></div>
					<form  action="addgiftlinewithpatient" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid uk-grid-collapse">
						<div class="uk-width-1-1 uk-overflow-container ">
							<div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Gift Voucher</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> Gift Voucher</h3>
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
									            <th class="uk-text-center">วันที่ใช้งาน</th>
									            <th class="uk-text-center">พนักงาน</th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="">
									    	<tr>
									    		<td class="uk-text-center"><s:property value="giftcard_line_rel_patient_hn"/></td>
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
				</div>	
					</form> 
			</div>		
		</div>

		<script>
			$(document).ready(function(){
				$("#tb-ac").dataTable();
				$("#tb-pat").dataTable();
			})
			
			
			
		</script>
	</body>
</html>