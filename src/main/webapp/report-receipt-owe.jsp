<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.all.model.*" %>
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
					<div class="uk-grid uk-grid-collapse">
						 
						<div class="uk-width-1-2 ">
							<div class="uk-panel uk-panel-box">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">list</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> รายการใบเสร็จ</h3>
								</div>
									<div class="uk-width-9-10 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									        	<th class="uk-text-center">เลขที่ใบเสร็จ</th>
									            <th class="uk-text-center">วันที่</th>
									            <th class="uk-text-center">พิมพ์เอกสาร</th> 
									        </tr>
									    </thead> 
									    <tbody>
									    	 <%   
											    if(request.getAttribute("receiptList")!=null)	{
												    List receiptList = (List) request.getAttribute("receiptList");
												    List <ReportReceiptOweModel> fnModel = receiptList;
				                                	int x=0;
				                                	for(ReportReceiptOweModel dpm : fnModel){ 
					            	         	 	x++; 
				            	         	 %>
										        <tr>
										            <td class="uk-text-center"><%=x%></td>
										            <td class="uk-text-center"><%=dpm.getReceipt_no()%></td>
										            <td class="uk-text-center"><%=dpm.getCreate_date_receipt()%></td> 
										            <td class="uk-text-center"><button type="button" class="uk-button uk-button-small uk-button-primary" onclick="printReceipt('<%=dpm.getReceipt_id()%>')" > พิมพ์ใบเสร็จ</button></td>
										        </tr> 
									        <% } %> 
										        
									        <% }else{ %>
									        	 <tr>
										            <td class="uk-text-center" colspan="4">ไม่พบข้อมูล</td> 
										        </tr> 
									        <% } %> 
									    </tbody>
									</table>
								</div>
							</div>
						</div> 
						<div class="uk-width-1-2 ">
							<div class="uk-panel uk-panel-box">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">list</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> รายการใบค้างชำระ</h3>
								</div>
									<div class="uk-width-9-10 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									        	<th class="uk-text-center">เลขที่ค้างชำระ</th>
									            <th class="uk-text-center">วันที่</th>
									            <th class="uk-text-center">พิมพ์เอกสาร</th> 
									        </tr>
									    </thead> 
									    <tbody>
									    	 <%   
											    if(request.getAttribute("oweList")!=null)	{
												    List oweList = (List) request.getAttribute("oweList");
												    List <ReportReceiptOweModel> fnModel = oweList;
				                                	int x=0;
				                                	for(ReportReceiptOweModel dpm : fnModel){ 
					            	         	 	x++; 
				            	         	 %>
										        <tr>
										            <td class="uk-text-center"><%=x%></td>
										            <td class="uk-text-center"><%=dpm.getOwe_no()%></td>
										            <td class="uk-text-center"><%=dpm.getCreate_date_receipt()%></td> 
										            <td class="uk-text-center"><button type="button" class="uk-button uk-button-small uk-button-primary" onclick="printOwe('<%=dpm.getOwe_id()%>')" > พิมพ์ใบเสร็จ</button></td>
										        </tr> 
									        <% } %> 
										        
									        <% }else{ %>
									        	 <tr>
										            <td class="uk-text-center" colspan="4">ไม่พบข้อมูล</td> 
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
			
			function printReceipt(receipt_id){ 
				
				window.open('report/report-receipt-new.jsp?receipt_id='+receipt_id+ //drugname='+encodeURI(drugname)+ 
						''
						, '_blank', ''); 
			};
			function printOwe(owe_id){ 
				
				window.open('report/report-owe.jsp?owe_id='+owe_id+ //drugname='+encodeURI(drugname)+ 
						''
						, '_blank', ''); 
			};
			
		</script>
	</body>
</html>