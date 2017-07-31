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
				
				<%@include file="backend-giftcard-top.jsp" %>

					<div class="uk-grid"></div>
					<form  action="updateGiftCard" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid uk-grid-collapse">
						<div class="uk-width-1-1 uk-overflow-container ">
							<div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Gift Card</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> Gift Card</h3>
								</div>
								<s:iterator value="getgiftcardlist" begin="0" end="1" status="check">
								<s:if test="1>#check.index ">
								<div class="uk-grid uk-grid-collapse mt-0">
											<div class="uk-width-1-5 uk-form uk-text-right">
												<h3>ชื่อชุด Gift Card : </h3>
											</div>
											<div class="uk-width-1-5 uk-form">
											<input type="hidden" value="<s:property value="giftcard_id"/>" name="giftcardModel.giftcard_id" >
												<input type="text" autocomplete="off" class="uk-width-1-1" 
												 name="giftcardModel.giftcard_name" 
												value='<s:property value="giftcard_name"/>' >
											</div>
											<div class="uk-width-1-5 uk-form uk-text-center">
												<button type="submit" class="uk-button uk-button-success">บันทึก</button>
											</div>													
								</div>
								<div class="uk-grid uk-grid-collapse mt-1">
								<div class="uk-width-1-5 uk-form uk-text-right">
									<span>Description : </span>
								</div>
								<div class="uk-width-1-5 uk-form ">
									<textarea  class="uk-width-1-1 uk-form-small " 
									name="giftcardModel.giftcard_description" 
									 ><s:property value="giftcard_description"/></textarea>
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
									<table id="tb-ac" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">เลขบัตร</th>
									            <th class="uk-text-center">จำนวนเงินภายในบัตร</th>
									            <th class="uk-text-center">HN คนไข้</th>
									            <th class="uk-text-center">จัดการ</th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="getgiftcardlist">
									    	<tr>
									    		<td class="uk-text-center"><s:property value="giftcard_line_name"/></td>
									    		<td class="uk-text-center"><s:property value="getText('{0,number,#,##0.00}',{giftcard_line_amount})"/> บาท</td>
									    		<td class="uk-text-center"><s:property value="giftcard_run_count"/> ใบ</td>
									    		<td class="uk-text-center">
													<div class="uk-button-dropdown" data-uk-dropdown>
									                    <button class="uk-button uk-button-success" type="button">
									                    	จัดการ<i class="uk-icon-caret-down"></i>
								                    	</button>
									                    <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" >
									                        <ul class="uk-nav uk-nav-dropdown">								                            	
									                            <li class="uk-text-left">
													    			<a href="getgiftlinewithpatient-<s:property value="giftcard_line_id"/>-<s:property value="giftcard_id"/>">
													    				<i class="uk-icon-pencil"></i> เพิ่มคนไข้
													    			</a>
								                            	</li>
								                            	<li class="uk-nav-divider"></li>
								                            	<li class="uk-text-left">
													    			<a href="gift_card_line_usage.jsp" >
													    				<i class="uk-icon-list"></i> ดูรายละเอียด
													    			</a>
								                            	</li>
									                        </ul>
									                    	</div>
									               		 </div>														
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
				
			});

			
			
			
		</script>
	</body>
</html>