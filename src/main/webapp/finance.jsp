<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*" %>
<%@ page import="com.smict.treatment.data.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% 	DecimalFormat df = new DecimalFormat("#,###,##0.## ฿");
	DecimalFormat df1 = new DecimalFormat("#,###,##0.## เม็ด");
	DecimalFormat df2 = new DecimalFormat("#,###,##0.##");
%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:การเงิน</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
			
				<div class="uk-grid uk-grid-collapse uk-form">
				    <div class="uk-width-6-10 ">
				    	<h4 class="hd-text uk-text-primary margin5">รายการค่าใช้จ่าย</h4>
				    	<div class=" uk-panel-box">
				    	<div class="uk-overflow-container">
				    	<h4 class="hd-text uk-text-primary margin5">รายการรักษา - <a href="#">แก้ไข</a></h4>
						<table class="uk-table  uk-table-hover uk-table-condensed uk-width-1-1 border-gray " >
						    <thead>
						        <tr class="hd-table">
						            <th class="uk-text-center" rowspan="2"><p>รายการรักษา</p></th>
						            <th class="uk-text-center" rowspan="2"><p>ทันตแพทย์</p></th>  
						            <th class="uk-text-center" colspan="2">ติดตามผล</th> 
						            <th class="uk-text-center" rowspan="2"><p>จำนวนเงิน</p></th>
						            <th class="uk-text-center" colspan="2">ส่วนลด</th>
						        </tr>
						        <tr class="hd-table">
						        	<th class="uk-text-center">HomeCall</th>
						            <th class="uk-text-center">ReCall</th> 
						        	<th class="uk-text-center">แพทย์</th>
						            <th class="uk-text-center">ร้าน</th>
						        </tr>
						    </thead> 
						    <tbody>
						    	<% 
						    	if(request.getAttribute("transectionTreatmentList")!=null){
				    				List<ServicePatientModel> transectionList = (List) request.getAttribute("transectionTreatmentList");  
							    	for(ServicePatientModel ttModel : transectionList){    
								%> 
								<tr>
									<td class="uk-text-left uk-width-2-10"><%=ttModel.getTreatment_name()%></td>
									<td class="uk-text-left uk-width-2-10"><%=ttModel.getDoctor_name()%></td> 
									<td class="uk-text-center uk-width-1-10"> 
                                        	<input type="checkbox" id="form-s-c"> <label for="form-s-c"></label>
                                        	<input type="text" class="uk-form-small" placeholder="หมายเหตุ"> 
									</td>
									<td class="uk-text-center uk-width-1-10"><a href="#re-call" data-uk-modal class="uk-icon-hover uk-icon-phone-square uk-icon-small"></a></td>
						        	<td class="uk-text-right uk-width-2-10"><%=df.format(Float.valueOf(ttModel.getPrice_standard()))%></td>
						        	<td class="uk-text-center uk-width-1-10"> 
						        		<input type="hidden" class="price" name="price" value="<%=ttModel.getPrice_standard().replace(".00", "")%>">
						        		<input type="number" min="0" max="100" class="uk-form-small uk-text-center uk-width-1-1 percent" name="percent">
						        		<input type="text" class="uk-form-small uk-text-center uk-width-1-1 amount" name="amount" placeholder="บาท">
						        	</td>
						        	<td class="uk-text-center uk-width-1-10"> 
						        		<input type="text" class="uk-form-small uk-text-center uk-width-1-1 amt_store_treatment" name="amt_store_treatment" placeholder="บาท">
						        	</td>
								</tr>
									<%} %> 
								<%} %> 
						         
						    </tbody>
						</table>
						<h4 class="hd-text uk-text-primary margin5">รายการยา - <a href="#">แก้ไข</a></h4>
						<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray  uk-width-1-1">
						    <thead>
						        <tr class="hd-table"> 
						        	<th class="uk-text-center" rowspan="2"><p>รหัสสินค้า</p></th>
						            <th class="uk-text-center" rowspan="2"><p>ชื่อยา</p></th>
						            <th class="uk-text-center" colspan="2">จำนวนยา</th> 
						            <th class="uk-text-center" colspan="2">จำนวนเงิน</th>
						            <th class="uk-text-center" rowspan="2"><p>ส่วนลดร้าน</p></th>
						        </tr>
						        <tr class="hd-table">
						        	<th class="uk-text-center">ฟรี</th>
						            <th class="uk-text-center">จ่าย</th>
						            <th class="uk-text-center">ต่อหน่วย</th>
						            <th class="uk-text-center">รวมทั้งหมด</th>
						        </tr>
						    </thead> 
						    <tbody>
						    	<% 
						    	if(request.getAttribute("drugList")!=null){
				    				List<FinanceModel> drugList = (List) request.getAttribute("drugList");  
							    	for(FinanceModel financeDrug : drugList){   
								%> 
								<tr>
									<td class="uk-text-center uk-width-1-10"><%=financeDrug.getProduct_id()%></td>
									<td class="uk-text-left uk-width-2-10"><%=financeDrug.getProduct_name()%></td>
									<td class="uk-text-right uk-width-1-10"><%=df1.format(Float.valueOf(financeDrug.getProduct_free()))%></td>
									<td class="uk-text-right uk-width-1-10"><%=df1.format(Float.valueOf(financeDrug.getProduct_transfer()))%></td>
									<td class="uk-text-right uk-width-1-10"><%=df.format(Float.valueOf(financeDrug.getAmount()))%></td>
									<td class="uk-text-right uk-width-1-10"><%=df.format(Float.valueOf(financeDrug.getAmountTotal()))%></td>
									<td class="uk-text-center uk-width-1-10"> 
										<input type="hidden" name="price_drug" value="<%=financeDrug.getAmountTotal().replace(".00", "")%>">
						        		<input type="text" class="uk-form-small uk-text-center uk-width-1-1" name="amt_store_drug" placeholder="บาท">
						        	</td>
								</tr>
									<%} %> 
								<%} %> 
						    </tbody>
						</table>
						
						<h4 class="hd-text uk-text-primary margin5">สินค้าอื่นๆ</h4>
						
						<div class="uk-form">
							<button class="uk-button uk-button-primary uk-button-small">
								<i class="uk-icon-search"></i>
							</button>
							<input class="uk-form-small" type="text" placeholder="รหัสสินค้า"/>
							<button class="uk-button uk-button-success uk-button-small">ตกลง</button>
						</div>
						<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed uk-width-1-1 border-gray ">
						    <thead>
						        <tr class="hd-table"> 
						        	<th class="uk-text-center">รหัสสินค้า</th>
						            <th class="uk-text-center">รายการรักษา</th>
						            <th class="uk-text-center">จำนวน</th>
						            <th class="uk-text-center">ราคา</th>
						            <th class="uk-text-center">ราคารวม</th> 
						            <th class="uk-text-center">ส่วนลดร้าน</th>
						        </tr>
						    </thead> 
						    <tbody>
						    	<% 
						    	if(request.getAttribute("productList")!=null){
				    				List<FinanceModel> productList = (List) request.getAttribute("productList");  
							    	for(FinanceModel financeProduct : productList){   
								%> 
								<tr>
									<td class="uk-text-center uk-width-1-10"><%=financeProduct.getProduct_id()%></td>
									<td class="uk-text-left uk-width-2-10"><%=financeProduct.getProduct_name()%></td> 
									<td class="uk-text-right uk-width-1-10"><%=df2.format(Float.valueOf(financeProduct.getProduct_transfer()))%></td>
									<td class="uk-text-right uk-width-1-10"><%=df.format(Float.valueOf(financeProduct.getAmount()))%></td>
									<td class="uk-text-right uk-width-1-10"><%=df.format(Float.valueOf(financeProduct.getAmountTotal()))%></td>
									<td class="uk-text-center uk-width-1-10"> 
										<input type="hidden" name="price_product" value="<%=financeProduct.getAmountTotal().replace(".00", "")%>">
						        		<input type="text" class="uk-form-small uk-text-center uk-width-1-1" name="amt_store_product" placeholder="บาท">
						        	</td>
								</tr>
									<%} %> 
								<%} %>  
						    </tbody>
						</table>
						</div>
						</div>
					</div> 
					
					<div class="uk-width-4-10 uk-overflow-container">
						<h4 class="hd-text uk-text-primary margin5">รายละเอียดการชำระเงิน</h4>
						  
                             <div class="uk-panel uk-panel-box uk-panel-box">
                             	<span class="red">รายการค้างชำระ  : 1,500 บาท</span> <a href="#remain" class="uk-button uk-button-danger" data-uk-modal>จ่ายค้างชำระ</a>
                             	<div id="remain" class="uk-modal ">
							    <div class="uk-modal-dialog uk-modal-dialog-large uk-form" >
						        	<a class="uk-modal-close uk-close"></a>
							         <div class="uk-modal-header"><i class="uk-icon-money"></i> รายการค้างชำระ</div>
							         	<div class="uk-width-1-1 uk-overflow-container">
											<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
											    <thead>
											        <tr class="hd-table"> 
											            <th class="uk-text-center">เลขใบค้างชำระ</th>
											            <th class="uk-text-center">วันที่ค้างชำระ</th>
											            <th class="uk-text-center">ยอดทั้งหมด</th> 
											            <th class="uk-text-center">ยอดค้างคงเหลือ</th>
											        </tr>
											    </thead> 
											    <tbody>
											    	<tr>  
												        <td class="uk-text-center"><a href="finance-remain.jsp">16062559-000111</a></td>
												        <td class="uk-text-center">16-06-2559</td>
												        <td class="uk-text-center">5,000</td>
												        <td class="uk-text-center">1,500</td>
													</tr>
												</tbody>
											</table>
										</div> 
										<br>
							    	</div>
								</div>
								<div class="border-gray padding5">
                             	<div class="uk-grid">
                             		<div class="uk-width-1-2">
	                             		<h5 class="hd-text uk-text-primary margin5">โปรโมชั่น</h5>
	                             		<select  size="5" style="width:100%;">
	                             			<option></option>
	                             		</select>
                             		</div>
                             		<div class="uk-width-1-2">
	                             		<div class="uk-form">
	                             			<h5 class="hd-text uk-text-primary margin5">ส่วนลด(ร้าน)</h5>
	                             			<label>
	                             				<input type="text" id="discount_all" name="discount_all" placeholder="0" maxlength="3" size="3"class="uk-form-small uk-text-right"> %
	                             			</label><br>
	                             			<label>
	                             				<input type="text" id="amount_discount_all" name="amount_discount_all" placeholder="0" maxlength="7" size="7"class="uk-form-small uk-text-right">บาท
	                             			</label>
	                             		</div>
                             		</div>
                             		<div class="uk-width-1-1">
                             			<p><span class="uk-text-primary">คำอธิบาย</span> <span class="uk-text-muted">คำอธิบายโปรโมชั่น</span></p>
                             		</div>
                             		<div class="uk-width-1-1">
                             			<p><span class="uk-text-primary">ข้อความอ้างอิง</span></p>
                             		</div>
                             		<div class="uk-width-1-1">
                             			<textarea placeholder="Remark"></textarea>
                             		</div>
                             	</div>	
                             		
                             	</div>
								<div class=" padding5">
	                             	<div class="uk-grid">
	                             		<div class="uk-width-3-5">
	                             			<h5 class="hd-text uk-text-primary margin5">ประกันสังคม</h5>
		                             		<label><input type="checkbox" name="social" id="social">ประกันสังคม</label>
		                             		<input type="text" name="doc_type"> 
		                             		<a id="cantuse_social" class="red"> การแจ้งเตือนถ้าไม่สามารถใช้ประกันสังคมได้</a>
	                             		</div>
                             	</div>
                             	<h5 class="hd-text uk-text-primary margin5">ราคาค่าใช้จ่าย</h5>
                             	<ul class="uk-form uk-list chanel-pay padding5 border-gray uk-text-right">
		                            <li class="uk-grid"><div class="uk-width-1-3">ราคารวม </div>
		 								<input type="text" size="20" readonly="readonly" id="amounttotal" name="amounttotal" placeholder="0" class="uk-form-small uk-text-right">
		 							</li>
		                            <li class="uk-grid"><div class="uk-width-1-3">ส่วนลด  </div>
		 								<input type="text" size="20" readonly="readonly" id="discount" name="discount" placeholder="0" class="uk-form-small uk-text-right">
		 							</li>
		                            <li class="uk-grid"><div class="uk-width-1-3"> สุทธิ </div>
		 								<input type="text" size="20" readonly="readonly" id="net" name="net" placeholder="0" class="uk-form-small uk-text-right">
		 							</li>
		 							<li class="uk-grid"><div class="uk-width-1-3"> ยอดเงินที่ชำระ </div>
		 								<input type="text" size="20"readonly="readonly" id="amount_paid" name="amount_paid" placeholder="0" class="uk-form-small uk-text-right">
		 							</li>
		                            <li class="uk-grid"><div class="uk-width-1-3"> ค้างชำระ </div>
		 								<input type="text" size="20" readonly="readonly" id="owe" name="owe" placeholder="0" class="uk-form-small uk-text-right">
		 							</li>
		                        </ul>
		                        <h5 class="hd-text uk-text-primary margin5">วิธีการชำระเงิน</h5>
                             	<ul class="uk-form uk-list chanel-pay padding5 border-gray">
		                            <li class="uk-grid"><label class="uk-width-1-3"><input type="checkbox" value="0" class="tik"> เงินสด </label>
		 								<input type="text" id="money" name="money" size="20" placeholder="0" disabled="disabled" class="uk-form uk-width-1-3 uk-text-right">
		 							</li>
		                            <li class="uk-grid"><label class="uk-width-1-3">
		                            	<input type="checkbox" value="1" class="tik"> เครดิตการ์ด </label>
		 								<input type="text" id="credit_card" name="credit_card" size="20" placeholder="0" disabled="disabled" class="uk-form uk-width-1-3 uk-text-right">
		 								<select name="chose_credit_card" class="uk-width-1-3" disabled="disabled">
		 									<option>กรุณาเลือกข้อมูลบัตรเครดิต</option>
		 									<option>Visa Master Card</option>
		 								</select>
		 							</li>
		                            <li class="uk-grid"><label class="uk-width-1-3"><input type="checkbox" name="tik" value="2" class="tik"> LinePay</label>
		 								<input type="text" id="line_pay" name="line_pay" size="20" placeholder="0" disabled="disabled" class="uk-form uk-width-1-3 uk-text-right">
		 							</li>
		                            <li class="uk-grid"><label class="uk-width-1-3"><input type="checkbox" name="tik" value="3" class="tik"> เงินฝาก </label>
		 								<input type="text" id="deposit" name="deposit" size="20" placeholder="0" disabled="disabled" class="uk-form uk-width-1-3 uk-text-right">
		 							</li>
		                        </ul>
    	
                             	
		                        <button type="submit" class="uk-button uk-button-success" onclick="printReceipt()"><i class="uk-icon-print"></i> พิมพ์ใบเสร็จ</button>
		                        <a href="finance-split-bill.jsp" class="uk-button uk-button-primary"data-lightbox-type="iframe" data-uk-lightbox><i class="uk-icon-copy"></i>  แยกใบเสร็จ</a>
		                        <button type="submit" class="uk-button uk-button-danger"><i class="uk-icon-history"></i> ประวัติการจ่ายเงิน</button>
                             </div>
                      
                        
					</div> 
					   
				</div>
				
				<div id="home-call" class="uk-modal uk-form">
				    <div class="uk-modal-dialog">
				    	<a class="uk-modal-close uk-close"></a>
				    	 <div class="uk-modal-header"><i class="uk-icon-phone"></i> Home call</div> 
			         	 <div class="uk-grid uk-grid-small ">
			         	 		<div class="uk-width-3-10 ">
			         	 			<div class="uk-form-icon uk-width-1-1">
    								<i class="uk-icon-calendar"></i>
			         	 			<input type="text"  placeholder="วันที่" autocomplete="off" data-uk-datepicker="{weekstart:0, format:'DD-MM-YYYY'}" class="uk-form-small uk-width-1-1">
			         	 			</div>
			         	 		</div>
			         	 		<div class="uk-width-6-10">
			         	 			<div class="uk-form-icon uk-width-1-1">
								    <i class="uk-icon-hashtag"></i>
								    <input type="text"  placeholder="หมายเหตุ" class="uk-form-small uk-width-1-1">
								    </div>
								</div> 
						</div> 
				        <div class="uk-modal-footer  uk-text-right">
				        	<button class="uk-modal-close uk-button uk-button-success" id="updateb" onclick="click_treatment()" name="updateb">ตกลง</button>
				         	<button class="uk-modal-close uk-button uk-button-danger">ยกเลิก</button>
				         </div> 
		         	</div>
				</div>
				<div id="re-call" class="uk-modal uk-form">
				    <div class="uk-modal-dialog">
				    	<a class="uk-modal-close uk-close"></a>
				    	 <div class="uk-modal-header"><i class="uk-icon-phone-square"></i> Recall</div> 
			         	 <div class="uk-grid uk-grid-small ">
			         	 		<div class="uk-width-1-2">
			         	 			<label><input type="radio" name="recall-month">1เดือน </label>
			         	 			<label><input type="radio" name="recall-month">3เดือน </label>
			         	 			<label><input type="radio" name="recall-month">6ดือน </label>
			         	 		</div>
			         	 		<div class="uk-width-1-2">
			         	 			<div class="uk-form-icon uk-width-1-1">
								    <i class="uk-icon-hashtag"></i>
								    <input type="text"  placeholder="หมายเหตุ" class="uk-form-small uk-width-1-1">
								    </div>
								</div> 
						</div> 
				        <div class="uk-modal-footer  uk-text-right">
				        	<button class="uk-modal-close uk-button uk-button-success" id="updateb" onclick="click_treatment()" name="updateb">ตกลง</button>
				         	<button class="uk-modal-close uk-button uk-button-danger">ยกเลิก</button>
				         </div> 
		         	</div>
				</div>
				
			</div>
		</div>
		<script src="js/components/lightbox.js"></script>	
		<script> 
			function printReceipt() { 
				var amounttotal = $("#amounttotal").val().replace(/,/g,"");
				var discount = $("#discount").val().replace(/,/g,"");
				var net = $("#net").val().replace(/,/g,"");
				var owe = $("#owe").val().replace(/,/g,"");   
				window.open('financePrint?amounttotal='+amounttotal+'&discount='+discount+'&net='+net+'&owe='+owe+'', 
			    		'_blank', ''); 
				
				var delay=2000; 
				setTimeout(function() {
				//	location.reload();
					window.location.assign("/SmartDental/viewPatientDetail")
				}, delay);
				
			}
		
			$(document).ready(function(){
				 
				$( ".m-finance" ).addClass( "uk-active" );
				$("#social").click(function(){
					if($(this).prop("checked")){
						$("#bill-1").toggle( "fast" );
						$("#check1").prop('checked', false);
					}else{
						$("#bill-1").toggle( "fast" );
						$("#check1").prop('checked', true);
					}
				});
				$("#cantuse_social").click(function(){
					swal(
			    			'เลือกประกันสังคมไม่สำเร็จ',
			    			'ไม่มีรายการที่สามารถใช้ประกันสังคมได้',
			    			'error'
			    		);  
				});
				// amounttotal begin
				var valueamt=0; var price_ar = 0; var price_ar_drug = 0; var price_ar_product = 0; 
				for(var i=0; i<$('input[name="amount"]').length; i++){
					price_ar = $('input[name="price"]').eq(i).val(); 
					valueamt += Math.floor(price_ar); 
				}
				for(var i=0; i<$('input[name="amt_store_drug"]').length; i++){ 
					price_ar_drug = $('input[name="price_drug"]').eq(i).val();
					valueamt += Math.floor(price_ar_drug); 
				}
				for(var i=0; i<$('input[name="amt_store_product"]').length; i++){ 
					price_ar_product = $('input[name="price_product"]').eq(i).val();
					valueamt += Math.floor(price_ar_product); 
				}
				$("#amounttotal").val(valueamt.toLocaleString("en-US"));
				$("#discount").val(0);
				$("#net").val(valueamt.toLocaleString("en-US"));
				$("#owe").val(valueamt.toLocaleString("en-US"));
				// amounttotal begin 
			});
			
			$(document).on('keyup','input[name="percent"]',function (){
				var price = $(this).closest("td").find('input.price').val();
				var percent = $(this).val(); 
				var amount = ((Math.floor(percent)/100)*Math.floor(price));
				$(this).closest("td").find('input.amount').val(Math.round(amount));  
				var amounttotal = $("#amounttotal").val().replace(/,/g,""); 
				sumamt();
				sumamt_money();
			});
			$(document).on('keyup','input[name="amount"]',function (){
				$(this).closest("td").find('input.percent').val("");
				sumamt();
				sumamt_money();
			});
			$(document).on('keyup','input[name="amt_store_treatment"]',function (){
				sumamt();
				sumamt_money();
			}); 
			$(document).on('keyup','input[name="amt_store_drug"]',function (){
				sumamt();
				sumamt_money();
			}); 
			$(document).on('keyup','input[name="amt_store_product"]',function (){
				sumamt();
				sumamt_money();
			}); 
			$(document).on('keyup','input[name="discount_all"]',function (){
				var amounttotal = $("#amounttotal").val().replace(/,/g,"");  
				var percent = $(this).val(); 
				var amount = ((Math.floor(percent)/100)*Math.floor(amounttotal));
				$("#amount_discount_all").val(Math.round(amount));   
				 
				sumamt();
				sumamt_money();
			});
			$(document).on('keyup','input[name="amount_discount_all"]',function (){ 
				sumamt();
			});
			$(document).on('keyup','input[name="money"]',function (){  
				sumamt_money();
				$('input[name="money"]').val(Math.floor($(this).val().replace(/,/g,"")).toLocaleString("en-US"));
			}); 
			$(document).on('keyup','input[name="credit_card"]',function (){  
				sumamt_money();
				$('input[name="credit_card"]').val(Math.floor($(this).val().replace(/,/g,"")).toLocaleString("en-US"));
			}); 
			$(document).on('keyup','input[name="line_pay"]',function (){  
				sumamt_money();
				$('input[name="line_pay"]').val(Math.floor($(this).val().replace(/,/g,"")).toLocaleString("en-US"));
			});
			$(document).on('keyup','input[name="deposit"]',function (){  
				sumamt_money();
				$('input[name="deposit"]').val(Math.floor($(this).val().replace(/,/g,"")).toLocaleString("en-US"));
			});
			// money
			function sumamt_money(){
				var net = $("#net").val().replace(/,/g,""); 
				var money = $("#money").val().replace(/,/g,""); 
				var credit_card = $("#credit_card").val().replace(/,/g,"");
				var line_pay = $("#line_pay").val().replace(/,/g,"");
				var deposit = $("#deposit").val().replace(/,/g,"");
				
				var textvar = 0;
				if(money!='') textvar += Math.floor(money);
				if(credit_card!='') textvar += Math.floor(credit_card);
				if(line_pay!='') textvar += Math.floor(line_pay);
				if(deposit!='') textvar += Math.floor(deposit);
				
				var amount_paid = Math.floor(net)-Math.floor(textvar);
				if(Math.floor(net)<Math.floor(textvar)){
					amount_paid = 0;
				} 
				
				$("#owe").val(amount_paid.toLocaleString("en-US")); 
				$("#amount_paid").val(Math.floor(textvar).toLocaleString("en-US"));  
			}
			// money
			function sumamt(){
				// treatment 
				var i=0; var price_ar = 0; var percent_ar = 0; var amount_ar = 0; var valueamt = 0; var discount = 0; 
				for(var i=0; i<$('input[name="amount"]').length; i++){
					amount_ar = $('input[name="amount"]').eq(i).val();
					percent_ar = $('input[name="percent"]').eq(i).val();
					price_ar = $('input[name="price"]').eq(i).val();
					if(amount_ar!=''){ 
						if(percent_ar!=''){ 
							amount_ar = ((Math.floor(percent_ar)/100)*Math.floor(price_ar)); 
							valueamt += (Math.floor(price_ar)-Math.floor(amount_ar)); 
							discount += Math.floor(amount_ar);
						}else{
							valueamt += (Math.floor(price_ar)-Math.floor(amount_ar));  
						}
					}else{ 
						valueamt += Math.floor(price_ar);
					}
				}
				var i=0; var amt_store_treatment = 0;
				for(var i=0; i<$('input[name="amt_store_treatment"]').length; i++){
					amount_ar = $('input[name="amt_store_treatment"]').eq(i).val();
					if(amount_ar!=''){
						valueamt -= Math.floor(amount_ar);
						discount += Math.floor(amount_ar);
					}
				}
				// drug 
				var i=0; var amt_store_drug = 0, price_ar_drug = 0;
				for(var i=0; i<$('input[name="amt_store_drug"]').length; i++){
					amt_store_drug = $('input[name="amt_store_drug"]').eq(i).val();
					price_ar_drug = $('input[name="price_drug"]').eq(i).val();
					valueamt += Math.floor(price_ar_drug);
					if(amt_store_drug!=''){
						valueamt -= Math.floor(amt_store_drug);
						discount += Math.floor(amt_store_drug);
					} 
				}
				// product 
				var i=0; var amt_store_product = 0, price_ar_product = 0;
				for(var i=0; i<$('input[name="amt_store_product"]').length; i++){
					amt_store_product = $('input[name="amt_store_product"]').eq(i).val();
					price_ar_product = $('input[name="price_product"]').eq(i).val();
					valueamt += Math.floor(price_ar_product);
					if(amt_store_product!=''){
						valueamt -= Math.floor(amt_store_product);
						discount += Math.floor(amt_store_product);
					} 
				}
				// discount all
				   
				var amount_discount_all = $("#amount_discount_all").val();
				if(amount_discount_all!=0){
					valueamt -= Math.floor(amount_discount_all);
					discount += Math.floor(amount_discount_all);
				}
				// discount all
				
				$("#discount").val(discount.toLocaleString("en-US"));
				$("#net").val(valueamt.toLocaleString("en-US"));
			}
			
			$(document).on('change','.tik',function (){ 
				var tik = $(this).val();
				if(tik==0){
					if (this.checked) {
						$("#money").attr("disabled", false);
						$("#money").val('');
					}else{
						$("#money").attr("disabled", true);
						$("#money").val(0);
						sumamt_money();
					}
				}else if(tik==1){
					if (this.checked) { 
						$("#credit_card").attr("disabled", false);
						$('select[name="chose_credit_card"]').attr("disabled", false);
						$("#credit_card").val('');
					}else{
						$("#credit_card").attr("disabled", true);
						$('select[name="chose_credit_card"]').attr("disabled", true);
						$("#credit_card").val(0);
						sumamt_money();
					}
				}else if(tik==2){
					if (this.checked) {
						$("#line_pay").attr("disabled", false);
						$("#line_pay").val('');
					}else{
						$("#line_pay").attr("disabled", true);
						$("#line_pay").val(0);
						sumamt_money();
					}
				}else if(tik==3){
					if (this.checked) {
						$("#deposit").attr("disabled", false);
						$("#deposit").val('');
					}else{
						$("#deposit").attr("disabled", true);
						$("#deposit").val(0);
						sumamt_money();
					}
				}
				
			});
			
		</script>
		</div>
	</body>
</html>