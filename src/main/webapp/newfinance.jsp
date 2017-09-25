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
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
			
				<div class="uk-grid uk-grid-collapse uk-form">
				    <div class="uk-width-7-10 ">
				    	<div class=" uk-panel-box">
				    		<h3 class="hd-text uk-text-primary margin5">รายการค่าใช้จ่าย</h3>
				    	</div>
				    	<div class=" uk-panel-box">
				    	<div class="uk-overflow-container">
				    	<h4 class="hd-text uk-text-primary margin5">รายการรักษา </h4>
				    	<div class="new-table-scroll">
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
								
									<s:iterator value="orderlinelist">
									<tr>
										<th class="uk-text-center"><s:property value="orderLine_treatName" /></th>
										<th class="uk-text-center"><s:property value="finanModel.order_doc_pname" /><s:property value="finanModel.order_doc_FnameTh" /><s:property value="finanModel.order_doc_LnameTh" /></th>
										<th class="uk-text-center"><s:property value="orderLine_homecall" /></th>
										<th class="uk-text-center"><s:property value="orderLine_recall" /></th>
										<th class="uk-text-center"><s:property value="orderLine_price" /></th>
										<th class="uk-text-center">-</th>
										<th class="uk-text-center">-</th>
										</tr>
									</s:iterator>								
								
						         
						    </tbody>
						</table>
						</div><hr>
						<h4 class="hd-text uk-text-primary margin5">รายการยา </h4>
						<div class="uk-form">
							<button class="uk-button uk-button-primary uk-button-small">
								<i class="uk-icon-search"></i>
							</button>
							<input class="uk-form-small" type="text" placeholder="รหัสสินค้า"/>
							<button class="uk-button uk-button-success uk-button-small">ตกลง</button>
						</div>
						<div class="new-table-scroll">
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
							
									<s:iterator value="listtreatpatmedicine">
									<s:if test="isCheck != 'nu'">
									<tr>
										<th class="uk-text-center"><s:property value="treatPatMedicine_ProID" /></th>
										<th class="uk-text-center"><s:property value="treatPro_name" /></th>
										<th class="uk-text-center"><s:property value="treatPatMedicine_amountfree" /></th>
										<th class="uk-text-center"><s:property value="treatPatMedicine_amount" /></th>
										<th class="uk-text-center"><s:property value="pro_price" /></th>
										<th class="uk-text-center">-</th>
									</tr>
									</s:if>	
									</s:iterator>								
								
						    </tbody>
						</table>
						</div>
						<hr>
						<h4 class="hd-text uk-text-primary margin5">สินค้าอื่นๆ</h4>
						
						<div class="uk-form">
							<button class="uk-button uk-button-primary uk-button-small">
								<i class="uk-icon-search"></i>
							</button>
							<input class="uk-form-small" type="text" placeholder="รหัสสินค้า"/>
							<button class="uk-button uk-button-success uk-button-small">ตกลง</button>
						</div>
						<div class="new-table-scroll">
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
							<tr>
								<th class="uk-text-center" colspan="6">No data available in table</th>
							</tr>
						    </tbody>
						</table>
						</div><hr>
						<h4 class="hd-text uk-text-primary margin5">รายการของแถม </h4>
						<div class="new-table-scroll">
						<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray  uk-width-1-1">
						    <thead>
						        <tr class="hd-table"> 
						        	<th class="uk-text-center" >รหัส</th>
						            <th class="uk-text-center" >ชื่อ</th>
						            <th class="uk-text-center" >ประเภท</th>
						            <th class="uk-text-center" >จำนวน</th> 
						        </tr>
						    </thead> 
						    <tbody>
							
									<s:iterator value="prodetailList">
									<s:if test="discount_type == 3">
									<tr>
										
											<th class="uk-text-center"><s:property value="product_id" /></th>
											<th class="uk-text-center"><s:property value="tname" /></th>
											<th class="uk-text-center"><s:property value="product_type" /></th>
											<th class="uk-text-center">1</th>
										
									</tr>
									</s:if>	
									</s:iterator>	
							
						    </tbody>
						</table>
						</div>
						</div>
						</div>
					</div> 
					
					<div class="uk-width-3-10 uk-overflow-container">
						<div class=" uk-panel-box">
							<h3 class="hd-text uk-text-primary margin5">รายละเอียดการชำระเงิน</h3>
						</div>  
                             <div class="uk-panel uk-panel-box uk-panel-box">
                             	<span class="red">รายการค้างชำระ  : 1,500 บาท</span> <a href="#remain" class="uk-button uk-button-danger" data-uk-modal>จ่ายค้างชำระ</a>
								<h3 class="hd-text uk-text-primary margin5">ประเภทสิทธิประโยชน์ </h3>
								<select  class="uk-from uk-width-1-2" 
								id="selectallprivilege" size="3">
	                             			<option value="1" selected="selected">โปรโมชั่น</option>
	                             			<option value="2">Gift Card</option>
	                             			<option value="3">Gift Voucher</option>
	                             </select>	                             		
								<div class="border-gray padding5 promo">
                             	<div class="uk-grid">
                             		<div class="uk-width-1-1">
	                             		<h5 class="hd-text uk-text-primary margin5">โปรโมชั่น</h5>
	                             		<select  class="uk-from uk-width-1-1" size="5">
	                             			<s:iterator value="finanModel.promoList">
	                             				<option value="promotion_id"><s:property value="name" /></option>
	                             			</s:iterator>
	                             		</select>
                             		</div>
                             		<div class="uk-width-1-1">
	                             		<div class="uk-form">
	                             			<h5 class="hd-text uk-text-primary margin5">ประเภทสมาชิก</h5>
											<select  class="uk-from uk-width-1-1" size="5">
		                             			<option>ทั่วไป</option>
		                             			<option>วีไอพี</option>
	                             			</select>
	                             		</div>
                             		</div>
                             		<div class="uk-width-1-1">
                             			<p><span class="uk-text-primary">คำอธิบายโปรโมชั่น</span> <a class="uk-button-primary uk-button">แสดงคำอธิบาย</a></p>
                             		</div>

                             	</div>	
                             		
                             	</div>
                             	<div class="border-gray padding5 giftcard hidden">
                             	<div class="uk-grid">
                             		<div class="uk-width-1-2">
	                             		<h5 class="hd-text uk-text-primary margin5">Gift Card</h5>
	                             		<input type="text"  class="uk-from uk-width-1-1" />
                             		</div>
                             	</div>	                             		
                             	</div>
                             	<div class="border-gray padding5 giftvoucher hidden">
                             	<div class="uk-grid">
                             		<div class="uk-width-1-2">
	                             		<h5 class="hd-text uk-text-primary margin5">Gift Voucher</h5>
	                             		<input type="text"  class="uk-from uk-width-1-1" />
                             		</div>
                             	</div>	                             		
                             	</div>
								<div class=" padding5">
	                             	<div class="uk-grid">
	                             		<div class="uk-width-3-5">
	                             			<h5 class="hd-text uk-text-primary margin5">ประกันสังคม</h5>
		                             		<label><input type="checkbox" name="social" id="social">ประกันสังคม</label>
		                             		<input type="text" id="tresst" name="doc_type"> 
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
    							<ul class="uk-form uk-list chanel-pay padding5 border-gray">
    								<li class="uk-grid"><div class="uk-width-1-3"> ยอดเงินที่ชำระ </div>
		 								<input type="text" size="20"readonly="readonly" id="amount_paid" name="amount_paid" placeholder="0" class="uk-form-small uk-text-right">
		 							</li>
		                            <li class="uk-grid"><div class="uk-width-1-3"> ค้างชำระ </div>
		 								<input type="text" size="20" readonly="readonly" id="owe" name="owe" placeholder="0" class="uk-form-small uk-text-right">
		 							</li>
    							</ul>
                             	
		                        <button type="submit" class="uk-button uk-button-success" onclick="printReceipt()"><i class="uk-icon-print"></i> พิมพ์ใบเสร็จ</button>
		                        <a href="finance-split-bill.jsp" class="uk-button uk-button-primary"data-lightbox-type="iframe" data-uk-lightbox><i class="uk-icon-copy"></i>  แยกใบเสร็จ</a>
		                        <button type="submit" class="uk-button uk-button-danger"><i class="uk-icon-history"></i> ประวัติการจ่ายเงิน</button>
                             </div>
                      
                        
					</div> 
					   
				</div>

				
			</div>
		</div>
		<script src="js/components/lightbox.js"></script>	
		<script> 
			$(document).ready(function(){				
				if(<s:property value="finanModel.lastPromotionID" /> != 0){
					
				}
			})
			$(document).on("change","#selectallprivilege",function(){					
					if($(this).val() == 1){
						addAndRemoveHidden('.promo',".giftcard",".giftvoucher")
					}else if($(this).val() == 2){
						addAndRemoveHidden('.giftcard',".promo",".giftvoucher")
					}else if($(this).val() == 3){
						addAndRemoveHidden('.giftvoucher',".promo",".giftcard")
					}
			})
			$(document).on("click","#tresst",function(){					

			})
			function addAndRemoveHidden(id1,id2,id3) {
				$(id1).removeClass(' hidden')
				$(id2).addClass('hidden')						
				$(id3).addClass('hidden')
			}
		</script>
		</div>
	</body>
</html>