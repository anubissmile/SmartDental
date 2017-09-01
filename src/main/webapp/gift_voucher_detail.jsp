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
				
				<%@include file="backend-giftvoucher.jsp" %>

					<div class="uk-grid"></div>
					<form  action="updateGiftVoucher" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid uk-grid-collapse">
						<div class="uk-width-1-1 uk-overflow-container ">
							<div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Gift Voucher</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> Gift Voucher</h3>
								</div>
								<s:iterator value="getgiftVoucherList" begin="0" end="1" status="check">
								<s:if test="1>#check.index ">
								<div class="uk-grid uk-grid-collapse mt-0">
											<div class="uk-width-1-5 uk-form uk-text-right">
												<h3>ชื่อชุด Gift Voucher : </h3>
											</div>
											<div class="uk-width-1-5 uk-form">
											<input type="hidden" value="<s:property value="gv_id"/>" name="giftvoucherModel.gv_id" >
												<input type="text" autocomplete="off" class="uk-width-1-1" 
												 name="giftvoucherModel.gv_name" 
												value='<s:property value="gv_name"/>' >
											</div>
											<div class="uk-width-1-5 uk-form uk-text-center">
												<button type="submit" class="uk-button uk-button-success">บันทึก</button>
											</div>													
								</div>
								<div class="uk-grid uk-grid-collapse mt-1">
								<div class="uk-width-1-5 uk-form uk-text-right">
									<span>คำอธิบาย : </span>
								</div>
								<div class="uk-width-1-5 uk-form ">
									<textarea  class="uk-width-1-1 uk-form-small " 
									name="giftvoucherModel.gv_description" 
									 ><s:property value="gv_description"/></textarea>
								</div>
								</div>
								</s:if>
								</s:iterator>
							</div>
						</div>
								
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
								<div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-list"></i> Detail</h3>
								</div>
								<div class="uk-width-1-1 uk-overflow-container uk-form">
									<div class="uk-width-1-1 uk-margin-medium-bottom">
			 					<ul class="uk-tab" data-uk-switcher="{
			 							connect:'#Gift-active',
			 							animation: 'fade'
			 						}">
								    <li class="uk-active"><a href="#">ยังไม่ถูกใช้งาน</a></li>
								    <li><a href="#">ถูกใช้งานแล้ว</a></li>
								</ul>
			 				</div>
			 				<ul class="uk-width-1-1 uk-switcher" id="Gift-active">  
							 	<li class="uk-active">
							 	<table id="tb-ac" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">เลขบัตร</th>
									            <th class="uk-text-center">สถานะ</th>
									            <th class="uk-text-center">จัดการ</th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="getgiftVoucherList">
									    	<s:if test="gvl_status == \"t\"">
									    	<tr>
									    		<td class="uk-text-center"><s:property value="gvl_name"/></td>									    											    		
									    		<td class="uk-text-center">ยังไม่ถูกใช้งาน</td>
									    		<td class="uk-text-center">
									                    <a href="getGiftVoucherUsed-<s:property value="gvl_id"/>-<s:property value="gv_id"/>" class="uk-button uk-button-success" >
									                    	ดูข้อมูล
								                    	</a>													
													</td>
									    	</tr>
									    	</s:if>
						    				</s:iterator>			    
									    </tbody>   
									</table>
							 	</li>
							 	<li>
							 	<table id="tb-ac-1" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">เลขบัตร</th>
									            <th class="uk-text-center">สถานะ</th>
									            <th class="uk-text-center">จัดการ</th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="getgiftVoucherList">
									    	<s:if test="gvl_status == \"f\"">
									    	<tr>
											<td class="uk-text-center"><s:property value="gvl_name"/></td>									    											    		
									    		<td class="uk-text-center uk-text-danger">ถูกใช้งานแล้ว</td>
									    		<td class="uk-text-center">
									                    <a href="getGiftVoucherUsed-<s:property value="gvl_id"/>-<s:property value="gv_id"/>" class="uk-button uk-button-success" >
									                    	ดูข้อมูล
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

		<script>
			$(document).ready(function(){
				$("#tb-ac").dataTable();
				$("#tb-ac-1").dataTable();
			});

			
			
			
		</script>
	</body>
</html>