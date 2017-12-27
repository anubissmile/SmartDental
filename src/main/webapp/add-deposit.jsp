<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:Deposit</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
<div class="uk-text-center preload hidden">
	<span><i class="uk-icon-spin uk-icon-large uk-icon-spinner "></i> กรุณารอสักครู่</span>
</div>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
					<div class="uk-grid"></div>
					<form id="addDeposit" action="depositAdd" method="post">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">master</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> เพิ่มเงินฝาก</h3>
								</div>
								<div class="uk-grid uk-form "> 
									<div class="uk-width-1-7"> </div>
									<div class="uk-form-icon uk-text-right uk-width-1-6"> 
                                            <label><input type="radio" name="depositModel.deposit_type" value="M" checked="checked" > เงินสด</label>
                                            <label><input type="radio" name="depositModel.deposit_type" value="C" > เครดิต</label>  
									</div>
									<div class="uk-form-icon uk-width-1-6"> 
										<i class="uk-icon-asterisk"></i>
		                             	<input type="text" id="transfer_money" name="depositModel.transfer_money" placeholder="จำนวนเงิน" 
		                             			class="uk-width-1-1 uk-form numeric uk-text-right" required> 
	                            	</div> 
	                            	<div class="uk-form-icon uk-width-1-4">
	                            		<button class="uk-button uk-button-success uk-button-small" id="save" type="button" name="save">บันทึก</button>
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
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> รายการการฝากเงิน</h3>
								</div>
									<div class="uk-width-8-10 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									        	<th class="uk-text-center">สาขาที่ฝากเงิน</th>
									            <th class="uk-text-center">วันที่ฝากเงิน</th>
									            <th class="uk-text-center">ประเภท</th>
									            <th class="uk-text-center">เงินก่อนฝาก</th>
									            <th class="uk-text-center">จำนวนเงิน</th>   
									            <th class="uk-text-center">เงินหลังฝาก</th> 
									            <th class="uk-text-center">สถานะ</th>
									        </tr>
									    </thead> 
									    <tbody>
									    	 <%   
											    if(request.getAttribute("depositList")!=null)	{
												    List depositList = (List) request.getAttribute("depositList");
				                                	List <DepositModel> dpModel = depositList;
				                                	int x=0;
					            	         	 	for(DepositModel dpm : dpModel){ 
					            	         	 	x++; 
				            	         	 %>
										        <tr>
										            <td class="uk-text-center"><%=x%></td>
										            <td class="uk-text-center"><%=dpm.getBranch_id()%></td>
										            <td class="uk-text-center"><%=dpm.getDeposit_date()%></td>
										            <td class="uk-text-center">
										            <% if(dpm.getDeposit_type().equals("M")){ %>
										            	เงินสด
										            <% }else{ %>
										            	เครดิต
										            <% } %>
										            </td>
										            <td class="uk-text-right numeric"><%=dpm.getOld_money()%></td>
										            <td class="uk-text-right numeric"><%=dpm.getTransfer_money()%></td> 
										            <td class="uk-text-right numeric"><%=dpm.getTotal_money()%></td>
										            <td class="uk-text-center">
										            <% if(dpm.getType_money().equals("DEP")){ %>
										            	เงินฝาก
										            <% }else{ %>
										            	จ่ายเงิน
										            <% } %>
										            </td>
										            
										            <%-- <td class="uk-text-right">
										            	<a href="#update" onclick="update('<%=pbm.getBrand_id()%>','<%=pbm.getBrand_name()%>')" class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
										            	<a href="#delete_brand" onclick="delete_brand('<%=pbm.getBrand_id()%>','<%=pbm.getBrand_name()%>')" class="uk-button uk-button-danger uk-button-small" data-uk-modal>
															<i class="uk-icon-eraser"></i> ลบ
														</a> 
													</td> --%>
										        </tr> 
									        <% } %> 
										        
									        <% }else{ %>
									        	 <tr>
										            <td class="uk-text-center" colspan="6">ไม่พบข้อมูล</td> 
										        </tr> 
									        <% } %> 
									    </tbody>
									</table>
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
				$( ".m-patient" ).addClass( "uk-active" );
				$(".numeric").autoNumeric('init');
				
				$("#save").click(function(){ 
					var transfer_money = $("#transfer_money").val().replace(/,/g,"");
					$("#transfer_money").val(transfer_money);
					
					$("#addDeposit").submit();
					$('.preload').removeClass('hidden');
				}); 
				
				/* $("#deleteb").click(function(){
					$("#brandid").removeAttr("required");
					$("#brandname").removeAttr("required");
					
					$("#brand").submit();
				}); 
				$("#updateb").click(function(){
					 
					var id_up 	= $("#id_up").val(); 
					$("#brandid").removeAttr("required");
					$("#brandname").removeAttr("required");
					
					if(id_up!=''){ 
					$("#brand").submit();
					}
				});  */
				
			});
			
			function update(id, name) { 
				 $("#id_up").val(id);
				 $("#name_up").val(name); 
			};
			function delete_brand(id, name) { 
				 $("#id_de").val(id);
				 $("#name_de").val(name); 
				  
			};
			
			
			
		</script>
	</body>
</html>