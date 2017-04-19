<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			
				<div class="uk-grid uk-grid-collapse">
				    <div class="uk-width-6-10 ">
				    	<h4 class="hd-text uk-text-primary margin5">รายการค่าใช้จ่าย</h4>
				    	<div class=" uk-panel-box">
				    	<div class="uk-overflow-container">
				    	<h4 class="hd-text uk-text-primary margin5">รายการรักษา </h4>
						<table class="uk-table  uk-table-hover uk-table-condensed uk-width-1-1 border-gray " >
						    <thead>
						        <tr class="hd-table">
						            <th>รายการรักษา</th>
						            <th>ทันตแพทย์</th> 
						            <th class="uk-text-right">จำนวนเงิน</th>
						        </tr>
						    </thead> 
						    <tbody>
						        <tr>
						            <td >ถอนฟัน</td>
						            <td >ทพ ทดสอบ</td>
						            <td class="uk-text-right">600.00</td>
						            
						        </tr>
						        <tr>
						            <td>อุดฟัน</td>
						            <td>ทพ ทดสอบ</td>
						            <td class="uk-text-right">900.00</td>
						            
						        </tr>
						    </tbody>
						</table>
						<h4 class="hd-text uk-text-primary margin5">รายการยา</h4>
						<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray  uk-width-1-1">
						    <thead>
						        <tr class="hd-table">
						        	<th>ลำดับ</th>
						        	<th>รหัสสินค้า</th>
						            <th>ชื่อยา</th>
						            <th  class="uk-text-right">จำนวน</th>
						            <th class="uk-text-right">จำนวนเงิน</th>
						        </tr>
						    </thead> 
						    <tbody>
						    	
						        <tr>
						            <td class="uk-text-center" colspan="5">ไม่มีรายการ</td>
						        </tr>
						    </tbody>
						</table>
						
						<h4 class="hd-text uk-text-primary margin5">สินค้าอื่นๆ</h4>
						<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed uk-width-1-1 border-gray ">
						    <thead>
						        <tr class="hd-table">
						        	<th>ลำดับ</th>
						        	<th>รหัสสินค้า</th>
						            <th>รายการรักษา</th>
						            <th class="uk-text-right">จำนวน</th>
						            <th class="uk-text-right">ราคา</th>
						            <th class="uk-text-right">ราคารวม</th>
						            <th class="uk-text-right">แก้ไข</th>
						        </tr>
						    </thead> 
						    <tbody>
						    	
						        <tr>
						            <td class="uk-text-center" colspan="7">ไม่มีรายการ</td>
						        </tr>
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
	                             		<div class="uk-width-3-5">
		                             		<label>เลขที่ใบค้างชำระ</label><input type="text" name="remain_no" value="16062559-000111">
	                             		</div>
	                             	</div>
                             	</div>
                             	<div class="border-gray padding5">
	                             	<div class="uk-grid">
	                             		<div class="uk-width-3-5">
	                             			<h5 class="hd-text uk-text-primary margin5">ประเภทเอกสาร</h5>
		                             		<label><input type="radio" name="doc_type" checked="checked">ใบเสร็จ</label>
	                             		</div>
	                             		<div class="uk-width-2-5">
	                             			<h5 class="hd-text uk-text-primary margin5">ภาษา</h5>
		                             		<label><input type="radio" name="doc_lang" checked="checked">ไทย</label>
		                             		<label><input type="radio" name="doc_lang">English</label>
	                             		</div>
	                             	</div>
                             	</div>
                             	<div class="border-gray padding5">
                             	<div class="uk-grid">
                             		
                             		<div class="uk-width-1-1">
                             			<p><span class="uk-text-primary">ข้อความอ้างอิง</span></p>
                             		</div>
                             		<div class="uk-width-1-1">
                             			<textarea placeholder="Remark"></textarea>
                             		</div>
                             	</div>	
                             		
                             	</div>
                             	<ul class="uk-form uk-list chanel-pay padding5 border-gray">
		                            <li class="uk-grid"><label class="uk-width-1-3"><input type="checkbox"> เงินสด </label>
		 								<input type="text" dir="rtl" size="20" placeholder="0.00" class="uk-form uk-width-1-3">
		 							</li>
		                            <li class="uk-grid"><label class="uk-width-1-3">
		                            	<input type="checkbox"> เครดิตการ์ด </label>
		 								<input type="text" dir="rtl" size="20" placeholder="0.00" class="uk-form uk-width-1-3">
		 								<select class="uk-width-1-3">
		 									<option>กรุณาเลือกข้อมูลบัตรเครดิต</option>
		 									<option>Visa Master Card</option>
		 								</select>
		 							</li>
		                            <li class="uk-grid"><label class="uk-width-1-3"><input type="checkbox"> LinePay</label>
		 								<input type="text" dir="rtl" size="20" placeholder="0.00" class="uk-form uk-width-1-3">
		 							</li>
		                            <li class="uk-grid"><label class="uk-width-1-3"><input type="checkbox"> เงินฝาก </label>
		 								<input type="text" dir="rtl" size="20" placeholder="0.00" class="uk-form uk-width-1-3">
		 							</li>
		                        </ul>
    	
                             	<ul class="uk-form uk-list chanel-pay padding5 border-gray uk-text-right">
		                            <li class="uk-grid"><div class="uk-width-1-3"> สุทธิ </div>
		 								<input type="text" dir="rtl" size="20" readonly="readonly" placeholder="0.00" class="uk-form-small">
		 							</li>
		 							<li class="uk-grid"><div class="uk-width-1-3"> ยอดเงินที่ชำระ </div>
		 								<input type="text" dir="rtl" size="20"readonly="readonly" placeholder="0.00" class="uk-form-small">
		 							</li>
		                            <li class="uk-grid"><div class="uk-width-1-3"> ค้างชำระ </div>
		 								<input type="text" dir="rtl" size="20" readonly="readonly" placeholder="0.00" class="uk-form-small">
		 							</li>
		                        </ul>
		                        <button type="submit" class="uk-button uk-button-success"><i class="uk-icon-print"></i> พิมพ์ใบเสร็จ</button>
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

		<script>
			$(document).ready(function(){
				$( ".m-finance" ).addClass( "uk-active" );
			});
		</script>
	</body>
</html>