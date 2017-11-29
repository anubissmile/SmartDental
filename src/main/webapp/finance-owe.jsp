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
	<title>Smart Dental:Promotion</title>
	<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
</head> 
<body>
<div class="uk-text-center preload">
	<span><i class="uk-icon-spin uk-icon-large uk-icon-spinner "></i> กรุณารอสักครู่</span>
</div>
	<div class="uk-grid uk-grid-collapse">
		<div class="uk-width-1-10">
			<%@include file="nav-right.jsp" %>
		</div>
		<div class="uk-width-9-10">
			<%@include file="nav-top.jsp" %>
		<div class="uk-grid uk-grid-collapse">
			
			<div class="uk-width-1-1 uk-form">
			<form id="saveReceipt" action="addFinanceReceiptOwe" method="post"> 
		    	<div class=" uk-panel-box">
		    	<div class="uk-grid uk-grid-collapse">
		    		<div class="uk-width-1-2"><h2 class="uk-text-primary margin5"><b>รายการค้างชำระ</b>
		    		<input type="hidden" value="<s:property value="treatmentModel.treatment_patient_ID" />" name="treatmentModel.treatment_patient_ID">
					<input type="hidden" value="<s:property value="finanModel.order_ID" />" name="finanModel.order_ID">
					<input type="hidden" value="<s:property value="finanModel.order_Hn" />" name="finanModel.order_Hn">
					<input type="hidden" value="<s:property value="finanModel.order_pat_pname" />" name="finanModel.order_pat_pname">
					<input type="hidden" value="<s:property value="finanModel.order_pat_FnameTh" />" name="finanModel.order_pat_FnameTh">
					<input type="hidden" value="<s:property value="finanModel.order_pat_LnameTh" />" name="finanModel.order_pat_LnameTh">
					<input type="hidden" value="<s:property value="finanModel.order_pat_FnameEn" />" name="finanModel.order_pat_FnameEn">
					<input type="hidden" value="<s:property value="finanModel.order_pat_LnameEn" />" name="finanModel.order_pat_LnameEn">
					<input type="hidden" value="<s:property value="finanModel.order_roomName" />" name="finanModel.order_roomName">
					<input type="hidden" value="<s:property value="finanModel.order_docID" />" name="finanModel.order_docID">
					<input type="hidden" value="<s:property value="finanModel.order_doc_pname" />" name="finanModel.order_doc_pname">
					<input type="hidden" value="<s:property value="finanModel.order_doc_FnameTh" />" name="finanModel.order_doc_FnameTh">
					<input type="hidden" value="<s:property value="finanModel.order_doc_LnameTh" />" name="finanModel.order_doc_LnameTh">
					<input type="hidden" value="<s:property value="finanModel.order_doc_FnameEn" />" name="finanModel.order_doc_FnameEn">
					<input type="hidden" value="<s:property value="finanModel.order_doc_LnameEn" />" name="finanModel.order_doc_LnameEn">
		    	</h2></div>
		    		<div class="uk-width-1-2 uk-text-right">
		    		<span class="red  uk-width-6-10"><!-- รายการค้างชำระ  : 1,500 บาท --> </span>
		    		<a href="#howtopay" id="howto" data-uk-modal class="uk-button-success uk-button"><i class="uk-icon-money"></i> เลือกวิธีการชำระเงิน</a> 
		    		<button type="button" class="uk-button uk-button-primary" id="click-printreceipt" ><i class="uk-icon-print"></i> พิมพ์ใบเสร็จ</button>  
		    		  
		    		<div id="modal-printreceipt" class="uk-modal">
					    <div class="uk-modal-dialog uk-form">
					        
					        <div class="uk-modal-header">
					            <h2 class="uk-modal-title"><i class="uk-icon-print"></i> พิมพ์ใบเสร็จ</h2>
					        </div>
					        <div class="uk-modal-body">
					            <div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " id="tablemedicine">
									    <thead>
									        <tr class="hd-table"> 
									            <th class="uk-text-center">เลือก</th> 
									            <th class="uk-text-center">ครั้งที่</th>
									            <th class="uk-text-center">ประเภทเอกสาร</th> 
									        </tr>
									    </thead> 
									    <tbody class="showallreceipt">
											
										</tbody>
									</table>
								</div>
							</div>
					        <div class="uk-modal-footer uk-text-right">
					            <button type="button" class="uk-modal-close uk-button uk-button-success" name="btn_submit_be_allergic" id="printreceipt">ตกลง</button>
					        </div>
						</div>
					</div> 
		    		
		    		</div>
		    	</div><hr>
		    	
		    	<div class="uk-overflow-container">
		    	<h4 class="hd-text uk-text-primary margin5"><label><input type="checkbox" class="pay_type" name="pay_type" value="t" /> ครั้งสุดท้าย</label>  </h4>
		    	
		    	</div> 
		    	
		    	<div class="uk-overflow-container">
		    	<h4 class="hd-text uk-text-primary margin5">รายการรักษา </h4>
		    	<div class="new-table-scroll">
				<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray  uk-width-1-1 " >
				    <thead>
				        <tr class="hd-table">
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>เลือก</p></th>
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>จ่ายเงิน</p></th>
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนเงินที่จ่ายได้</p></th> 
				            <th class="uk-text-center uk-width-3-10" rowspan="2"><p>รายการรักษา</p></th>   		 
				        </tr>
				        <!-- <tr class="hd-table">					 
				            <th class="uk-text-center">Promotion</th> 
				        	<th class="uk-text-center ">แพทย์</th>
				            <th class="uk-text-center ">ร้าน</th>
				        </tr> -->
				    </thead> 
				    <tbody class="showalltreatment">			
				    	 <%-- <s:iterator value="orderlinelist">
								<tr>
									<td class="uk-text-left uk-width-3-10"><s:property value="orderLine_treatName" /></td>
									<td class="uk-text-left uk-width-1-10"><s:property value="orderLine_homecall" /><input type="checkbox"  name="orderLine_homecall" value="orderLine_homecall" /></td> 
									<td class="uk-text-left uk-width-1-10"><s:property value="orderLine_recall" /></td> 
									<td class="uk-text-left uk-width-1-10"><s:property value="discount" /></td> 
									<td class="uk-text-left uk-width-1-10"><s:property value="disdoc_disbaht" /></td> 
									<td class="uk-text-left uk-width-1-10"><s:property value="branch_disbaht" /></td>
									<td class="uk-text-left uk-width-1-10"><s:property value="branch_disbaht" /></td>  
								</tr> 
						 </s:iterator> --%> 
				    </tbody>
				</table>
				</div><hr>
				<h4 class="hd-text uk-text-primary margin5">ยารักษา </h4>
				<div class="new-table-scroll">
				<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray  uk-width-1-1" id="medicineTable">
				    <thead>
				        <tr class="hd-table"> 
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>เลือก</p></th>
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>จ่ายเงิน</p></th>
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนเงินที่จ่ายได้</p></th>
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>ชำระเงินแล้ว</p></th>
				            <th class="uk-text-center uk-width-3-10" rowspan="2"><p>ชื่อยา</p></th>
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนยา</p></th> 
				            <th class="uk-text-center uk-width-1-10" colspan="2">ราคายา</th>
				            <!-- <th class="uk-text-center uk-width-2-10" colspan="2">ส่วนลด</th> -->
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนเงินทั้งหมด</p></th>  
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>ค้างชำระ</p></th>
				        </tr>
				        <tr class="hd-table">  
				            <th class="uk-text-center">ต่อหน่วย</th>
				            <th class="uk-text-center">รวมทั้งหมด</th>
				            <!-- <th class="uk-text-center">Promotion</th>
				            <th class="uk-text-center">ร้าน</th> -->
				        </tr>
				    </thead> 						    
				    <tbody class="showallmedicine ">							
						
				    </tbody>
				</table>
				</div><hr> 
				<h4 class="hd-text uk-text-primary margin5">สินค้า </h4>
				<div class="new-table-scroll">
				<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray  uk-width-1-1" id="medicineTable">
				    <thead>
				        <tr class="hd-table"> 
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>เลือก</p></th>
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>จ่ายเงิน</p></th>
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนเงินที่จ่ายได้</p></th>
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>ชำระเงินแล้ว</p></th>
				            <th class="uk-text-center uk-width-3-10" rowspan="2"><p>ชื่อสินค้า</p></th>
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวน</p></th> 
				            <th class="uk-text-center uk-width-1-10" colspan="2">ราคา</th>
				            <!-- <th class="uk-text-center uk-width-2-10" colspan="2">ส่วนลด</th> -->
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนเงินทั้งหมด</p></th> 
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>ค้างชำระ</p></th>
				        </tr>
				        <tr class="hd-table">  
				            <th class="uk-text-center">ต่อหน่วย</th>
				            <th class="uk-text-center">รวมทั้งหมด</th>
				            <!-- <th class="uk-text-center">Promotion</th>
				            <th class="uk-text-center">ร้าน</th> -->
				        </tr>
				    </thead> 						    
				    <tbody class="showallproduct">							
						
				    </tbody>
				</table>
				</div><hr>
				
				</div>
				
				<div id="howtopay" class="uk-modal ">
			    <div class="uk-modal-dialog uk-form " >
			        <a class="uk-modal-close uk-close"></a>
			         <div class="uk-modal-header"><i class="uk-icon-money"></i> วิธีการชำระเงิน</div>
			         	<div class="uk-width-1-1 uk-overflow-container">
			         		 
			         		<label>ค้างชำระ</label>
			         		<input type="text"class="uk-form uk-width-1-3 uk-text-right numeric" id="owe_text" />
			         		 
			         		<ul class="uk-form uk-list chanel-pay padding5 border-gray">
	                           <li class="uk-grid"><label ><input type="checkbox" name="tik" value="1" class="tik"> เงินสด </label></li>
	                           <li>
	                           <input type="text" id="money" name="money" size="20" placeholder="0" disabled="disabled" autocomplete="off" class="uk-form uk-width-1-1 numeric uk-text-right amAll">
	                           </li>
	                           <li class="uk-grid"><label >
	                           	<input type="checkbox" name="tik" value="2" class="tik"> เครดิตการ์ด </label>    	
									
								</li>
								<li><input type="text" id="credit_card" name="credit_card" size="20" placeholder="0" disabled="disabled" autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right amAll">
									<select name="chose_credit_card" class="" disabled="disabled">
										<option>กรุณาเลือกข้อมูลบัตรเครดิต</option>
										<option value="1">Visa Master Card</option>
									</select></li>
	                           <li class="uk-grid"><label ><input type="checkbox" name="tik" value="3" class="tik"> LinePay</label>
									
								</li>
								<li><input type="text" id="line_pay" name="line_pay" size="20" placeholder="0" disabled="disabled" autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right amAll"></li>
	                           <li class="uk-grid"><label ><input type="checkbox" name="tik" value="4" class="tik"> เงินฝาก </label> <p id="textdeposit" />
									
								</li>
								<li><input type="text" id="deposit" name="deposit" size="20" placeholder="0" disabled="disabled" autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right amAll"></li>
								<li class="uk-grid gtc hidden">
								<label><input type="checkbox" name="tik" value="5" class="tik"> Gift Card</label>	
								</li>
								<li  class=" gtc hidden" ><input type="text" id="giftcard" name="giftcard" size="20" placeholder="0" disabled="disabled" autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right gall"></li>
								<li class="uk-grid gtv hidden">
								<label><input type="checkbox" name="tik" value="6" class="tik"> Gift Voucher</label>	
								</li>
								<li class="gtv hidden"><input type="text" id="giftv" name="giftv" size="20" placeholder="0" disabled="disabled" autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right gall"></li>
								<li class="uk-grid">
								<label ><input type="checkbox" name="tik" value="7" class="tik"> ประกันสังคม</label>	
								</li>
								<li><input type="text" id="sso" name="sso" size="20" placeholder="0" disabled="disabled" autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right amAll"></li>
								<li class="uk-grid">
								<label ><input type="checkbox" name="tik" value="8" class="tik"> ประเภท Contact</label>	
								</li>
								<li><input type="text" id="contact" name="contact" size="20" placeholder="0" 
								disabled="disabled" class="uk-form numeric uk-width-1-1 uk-text-right gall">
									<select name="subcontype" id="subcontype" class="uk-width-1-2 "   disabled="disabled">
										<option value="1" class=" suball">วางบิล</option>
										<option value="2" class=" suball">วงเงินทั้งบริษัท</option>
										<option value="3" class=" suball">วงเงินต่อบุคคล</option>
									</select>
									จำนวนเงิน : <input type="text" readonly="readonly" id="subamountis" autocomplete="off"
									class="uk-width-1-3 uk-text-right numeric" name="" value="" >
								</li>
								
	                       </ul>
						</div>
			         	 
			         <div class="uk-modal-footer uk-text-right">
			         	<button type="button" class="uk-modal-close uk-button uk-button-success" id="btn_submit">บันทึกข้อมูล</button>
			         	<button class="uk-modal-close uk-button uk-button-danger" >ปิด</button>
			         </div>
			    </div>
			</div>
				
				</div> 
			</form>
			</div> 
				
		</div> 
	</div>
	</div>	
<script src="js/autoNumeric.min.js"></script>
<script src="js/components/lightbox.js"></script>
<script> 
	  
	 
$(document).ready(function(){ 
	      
    window.productOBJ = {"treatment": [],
    		 			 "medicine": [],
    		 			"product": []}
   
    <s:iterator value="orderlinelist">
	productOBJ.treatment.push({ 
		"receipt_id":<s:property value="receipt_id" />,
		"order_ID":<s:property value="order_ID" />,
		"orderLine_ID":<s:property value="orderLine_ID" />,
		"treatID":<s:property value="orderLine_TreatID" />,
		"treatName":'<s:property value="orderLine_treatName" />',
		/* "treatName":parseFloat(<s:property value="orderLine_price" />).toFixed(2),
		"treatName":parseFloat(<s:property value="orderLine_price" />).toFixed(2),
		"treatName":parseFloat(<s:property value="orderLine_price" />).toFixed(2), */
		"surf":'<s:property value="orderLine_surf" />',
		"tooth":'<s:property value="orderLine_tooth" />',
		"tooth_type_id":'<s:property value="orderLine_toothTypeID" />',
		"treat_price":parseFloat(<s:property value="orderLine_price" />).toFixed(2),
		"treat_dis":parseFloat(<s:property value="discount" />).toFixed(2),
		"treat_dis_branch":parseFloat(<s:property value="branch_disbaht" />).toFixed(2),
		"treat_dis_doctor":parseFloat(<s:property value="disdoc_disbaht" />).toFixed(2),
		"treat_total":parseFloat(<s:property value="or_branch_disbaht_total" />).toFixed(2), 
		"can_payment":parseFloat(<s:property value="can_payment" />).toFixed(2),
		"pay_amount":parseFloat(<s:property value="or_pay_amount_total" />).toFixed(2),
		
		"homecall":'<s:property value="orderLine_homecall" />',
		"recall":'<s:property value="orderLine_recall" />'
	});
	</s:iterator>	
	
	<s:iterator value="ordermedicinelist">
	productOBJ.medicine.push({
		"med_order_ID":<s:property value="order_ID" />,
		"med_orderLine_ID":<s:property value="orderLine_ID" />,
		"medID":<s:property value="product_id" />,
		"medName":'<s:property value="product_name" />', 
		"qty":parseFloat(<s:property value="or_qty" />).toFixed(2),
		"price_per_unit":parseFloat(<s:property value="orderLine_price" />).toFixed(2),
		"med_total":parseFloat(<s:property value="med_total" />).toFixed(2), 
		"med_dis":parseFloat(<s:property value="discount" />).toFixed(2),
		"med_dis_branch":parseFloat(<s:property value="branch_disbaht" />).toFixed(2),
		"or_branch_disbaht_total":parseFloat(<s:property value="or_branch_disbaht_total" />).toFixed(2),
		"med_owetotal":parseFloat(<s:property value="or_owe" />).toFixed(2),
		"can_payment":parseFloat(<s:property value="can_payment" />).toFixed(2),
		"pay_amount":parseFloat(<s:property value="or_pay_amount_total" />).toFixed(2)
	});
	</s:iterator>	
	
	<s:iterator value="orderproductlist">
	productOBJ.product.push({
		"pro_order_ID":<s:property value="order_ID" />,
		"pro_orderLine_ID":<s:property value="orderLine_ID" />,
		"proID":'<s:property value="product_id" />',
		"proName":'<s:property value="product_name" />', 
		"qty":parseFloat(<s:property value="or_qty" />).toFixed(2),
		"price_per_unit":parseFloat(<s:property value="orderLine_price" />).toFixed(2),
		"pro_total":parseFloat(<s:property value="med_total" />).toFixed(2), 
		"pro_dis":parseFloat(<s:property value="discount" />).toFixed(2),
		"pro_dis_branch":parseFloat(<s:property value="branch_disbaht" />).toFixed(2),
		"or_branch_disbaht_total":parseFloat(<s:property value="or_branch_disbaht_total" />).toFixed(2),
		"pro_owetotal":parseFloat(<s:property value="or_owe" />).toFixed(2),
		"can_payment":parseFloat(<s:property value="can_payment" />).toFixed(2),
		"pay_amount":parseFloat(<s:property value="or_pay_amount_total" />).toFixed(2)
	});
	</s:iterator>
	 
	readall();
	
	// checkall treatment medicine product
	$('.pay_type').prop('checked', true); 
	 
    $('.medicinecheckbok').prop('checked', true);
    $('.productcheckbok').prop('checked', true);
	
	for (let i = 0; i < productOBJ.treatment.length; i++) {  
    	var can_pay_treat = productOBJ.treatment[i].can_payment; 
    	$('.treatment_pay').eq(i).val(can_pay_treat);
    }
	for (let i = 0; i < productOBJ.medicine.length; i++) {  
    	var can_pay_med = productOBJ.medicine[i].can_payment; 
    	$('.medicine_pay').eq(i).val(can_pay_med);
    }
	for (let i = 0; i < productOBJ.product.length; i++) {  
    	var can_pay_pro = productOBJ.product[i].can_payment; 
    	$('.product_pay').eq(i).val(can_pay_pro);
    }
	
}).on('click','.treatmentcheckbok',function (e){ 
	var tik = $(this).val(); 
	var index = $('.treatmentcheckbok').index(this);
	 
	 	if ($('.pay_type').is(":checked")) { 
	    	e.preventDefault();
	        return false; 
	    }else{ 
			if (this.checked) {
				$(".treatment_pay").eq(index).attr("readonly", false);
				$(".treatment_pay").eq(index).val('');
			}else{
				$(".treatment_pay").eq(index).attr("readonly", true);
				$(".treatment_pay").eq(index).val(""); 
			}
	    }
}).on('click','.medicinecheckbok',function (e){ 
		var tik = $(this).val(); 
		var index = $('.medicinecheckbok').index(this); 
	   
	    if ($('.pay_type').is(":checked")) { 
	    	e.preventDefault();
	        return false; 
	    }else{ 
	    	if (this.checked) {
				$(".medicine_pay").eq(index).attr("readonly", false);
				$(".medicine_pay").eq(index).val('');
			}else{
				$(".medicine_pay").eq(index).attr("readonly", true);
				$(".medicine_pay").eq(index).val(""); 
			}
	    }  
		
}).on('click','.productcheckbok',function (e){ 
		var tik = $(this).val(); 
		var index = $('.productcheckbok').index(this); 
	   
	    if ($('.pay_type').is(":checked")) { 
	    	e.preventDefault();
	        return false; 
	    }else{ 
	    	if (this.checked) {
				$(".product_pay").eq(index).attr("readonly", false);
				$(".product_pay").eq(index).val('');
			}else{
				$(".product_pay").eq(index).attr("readonly", true);
				$(".product_pay").eq(index).val(""); 
			}
	    }  
	
}).on('keyup','.treatment_pay',function (){ 
	
	var treatment_pay = $(this).val().replace(/,/g,""); 
	var index = $('.treatment_pay').index(this);
	 
	var amount = $("input[name='treatment_can_payment']").eq(index).val().replace(/,/g,"");
	
	if(parseFloat(treatment_pay)>parseFloat(amount)){
		$('.treatment_pay').eq(index).val('');
	}
	
}).on('keyup','.medicine_pay',function (){ 
	
	var medicine_pay = $(this).val().replace(/,/g,""); 
	var index = $('.medicine_pay').index(this);
	 
	var amount = $("input[name='medicine_can_payment']").eq(index).val().replace(/,/g,"");
	
	if(parseFloat(medicine_pay)>parseFloat(amount)){
		$('.medicine_pay').eq(index).val('');
	}
	
}).on("click","#btn_submit",function(){	
	var pay_sum = 0;
	var pay_money = 0;
	var pay_credit = 0;
	var pay_deposit = 0;
	 	  
	pay_money = $('#money').val().replace(/,/g,""); 
	if(pay_money!='') pay_sum = parseFloat(pay_sum)+parseFloat(pay_money);  
	 
	pay_credit = $('#credit_card').val().replace(/,/g,"");
	if(pay_credit!='') pay_sum = parseFloat(pay_sum)+parseFloat(pay_credit);  
	 
	pay_deposit = $('#deposit').val().replace(/,/g,"");
	if(pay_deposit!='') pay_sum = parseFloat(pay_sum)+parseFloat(pay_deposit);   
	 
	var amount_pay_total = $('#owe_text').val().replace(/,/g,""); 
	 
	if(parseFloat(pay_sum)==parseFloat(amount_pay_total)){ 
		$('#saveReceipt').submit();   
		$('.preload').removeClass('hidden');
	}else{ 
		swal(
			  'ผิดพลาด...',
			  'จำนวนเงินไม่เท่ากับที่จ่าย!',
			  'error'
			)
	} 
	 
}).on("click",".pay_type",function(){
    $('.treatmentcheckbok').not(this).prop('checked', this.checked);
    $('.medicinecheckbok').not(this).prop('checked', this.checked);
    $('.productcheckbok').not(this).prop('checked', this.checked);
    
    if($(this).is(':checked')){ 
    	for (let i = 0; i < productOBJ.treatment.length; i++) {  
        	var can_pay_treat = productOBJ.treatment[i].can_payment; 
        	$('.treatment_pay').eq(i).val(can_pay_treat);
        }
    	for (let i = 0; i < productOBJ.medicine.length; i++) {  
        	var can_pay_med = productOBJ.medicine[i].can_payment; 
        	$('.medicine_pay').eq(i).val(can_pay_med);
        }
    	for (let i = 0; i < productOBJ.product.length; i++) {  
        	var can_pay_pro = productOBJ.product[i].can_payment; 
        	$('.product_pay').eq(i).val(can_pay_pro);
        }
    }else{
    	for (let i = 0; i < productOBJ.treatment.length; i++) {  
        	$('.treatment_pay').eq(i).val('');
        }
    	for (let i = 0; i < productOBJ.medicine.length; i++) {   
        	$('.medicine_pay').eq(i).val('');
        }
    	for (let i = 0; i < productOBJ.product.length; i++) {  
        	$('.product_pay').eq(i).val('');
        }
    } 
   	  
}).on("click","#howto",function(){		
	var sumall = 0;
	
	for (let i = 0; i < productOBJ.treatment.length; i++) {  
    	var treatment_pay = $('.treatment_pay').eq(i).val().replace(/,/g,""); 
    	sumall = sumall+parseFloat(treatment_pay);
    }
	for (let i = 0; i < productOBJ.medicine.length; i++) {  
    	var medicine_pay = $('.medicine_pay').eq(i).val().replace(/,/g,"");  
    	sumall = sumall+parseFloat(medicine_pay);
    }
	for (let i = 0; i < productOBJ.product.length; i++) {  
    	var product_pay = $('.product_pay').eq(i).val().replace(/,/g,"");  
    	sumall = sumall+parseFloat(product_pay);
    }
	$("#owe_text").val(sumall);   
	
	let modal = UIkit.modal('#howtopay');
	$(".numeric").autoNumeric('init');
	modal.show();
}).on('change','.tik',function (){ 
	var tik = $(this).val();
	if(tik==1){
		if (this.checked) {
			$("#money").attr("disabled", false);
			$("#money").val('');
		}else{
			$("#money").attr("disabled", true);
			$("#money").val("");
			sumamt_money()
		}
		
	}else if(tik==2){
		if (this.checked) { 
			$("#credit_card").attr("disabled", false);
			$('select[name="chose_credit_card"]').attr("disabled", false);
			$("#credit_card").val('');
		}else{
			$("#credit_card").attr("disabled", true);
			$('select[name="chose_credit_card"]').attr("disabled", true);
			$("#credit_card").val("");
			sumamt_money()
		}
	}else if(tik==3){
		if (this.checked) {
			$("#line_pay").attr("disabled", false);
			$("#line_pay").val('');
		}else{
			$("#line_pay").attr("disabled", true);
			$("#line_pay").val("");
			sumamt_money()
		}
		
	}else if(tik==4){
		if (this.checked) {
			$("#deposit").attr("disabled", false);
			//$("#deposit").val('');
			var amountdeposit = getAmountDeposit();
			$("#deposit").val(amountdeposit);
			sumamt_money();
		}else{
			$("#deposit").attr("disabled", true);
			$("#deposit").val("");
			sumamt_money();
			$('#textdeposit').text("");
		}
		
	}else if(tik==5){
		if($('#selectallprivilege').val() == 2){
			if(this.checked){
				$("#giftcard").attr("disabled", false);
				$("#giftcard").val('');
			}else{
				$("#giftcard").attr("disabled", true);
				$("#giftcard").val("");
				sumamt_money()
			}
		}else{
			this.checked = false;
		}
		 
	}else if(tik==6){
		if($('#selectallprivilege').val() == 3){
			if(this.checked){
				$("#giftv").attr("disabled", false);
				$("#giftv").val('');
			}else{
				$("#giftv").attr("disabled", true);
				$("#giftv").val("");
				sumamt_money()
			}
		}else{
			this.checked = false;
		}
		
	}else if(tik==7){
			if(checksocialSecurity()){
				if(this.checked){
					$("#sso").attr("disabled", false);
					$("#sso").val('');
				}else{
					$("#sso").attr("disabled", true);
					$("#sso").val("");
					sumamt_money()
				}
			}else{
				this.checked = false;
			}

	}else if(tik==8){
		if(checkContact()){
			if(this.checked){
				$("#contact").attr("disabled", false);
				$("#contact").val('');
				$("#subcontype").attr("disabled", false);
				$(".suball").prop("disabled", true);
				$("#subcontype option[value='"+productOBJ.subcontacttype+"']").prop('selected', true);
				$("#subcontype option[value='"+productOBJ.subcontacttype+"']").prop('disabled', false);
				$("#subamountis").val(productOBJ.subamount);
			}else{
				$("#contact").attr("disabled", true);
				$("#contact").val("");
				$("#subcontype").attr("disabled", true);
				$(".suball").prop("disabled", true);
				$("#subamountis").val(0);
				sumamt_money()
			}
		}else{
			this.checked = false;
		}
	}
	
}); 
$(document).on("keyup","#money",function(){
	var net = $('#owe_text').val().replace(/,/g,"");
	var sumall = 0; 
	sumall = sumamountall(); 
	
	if(parseFloat(net)<parseFloat(sumall)){
		$('#money').val('');
	}
});
$(document).on("keyup","#credit_card",function(){
	var net = $('#owe_text').val().replace(/,/g,"");
	var sumall = 0; 
	sumall = sumamountall(); 
	 
	if(parseFloat(net)<parseFloat(sumall)){
		$('#credit_card').val('');
	}
});
$(document).on("keyup","#deposit",function(){
	var net = $('#owe_text').val().replace(/,/g,"");
	var sumall = 0; 
	sumall = sumamountall(); 
	
	if(parseFloat(net)<parseFloat(sumall)){
		$('#deposit').val('');
	}
});
$(document).on("keyup","#sso",function(){ 
	var net = $('#owe_text').val().replace(/,/g,"");
	var sumall = 0; 
	sumall = sumamountall(); 
	
	if(parseFloat(net)<parseFloat(sumall)){
		$('#sso').val('');
	}
});

function sumamountall() { 
	var amountDeposit = $('#deposit').val().replace(/,/g,"");
		if(amountDeposit=='') amountDeposit = 0; 
	var amountCredit = $('#credit_card').val().replace(/,/g,"");
		if(amountCredit=='') amountCredit = 0; 
	var amountMoney = $('#money').val().replace(/,/g,"");
		if(amountMoney=='') amountMoney = 0; 
	var amountSso = $('#sso').val().replace(/,/g,"");
		if(amountSso=='') amountSso = 0;
	
	var sumall = 0;
	
	sumall = parseFloat(amountDeposit)+parseFloat(amountMoney)+parseFloat(amountCredit)+parseFloat(amountSso); 
	
	return sumall;
}
 
function readall() {
	 $('.preload').removeClass('hidden'); 
	$(document).ready(function(){					
			
	if($('#selectallprivilege').val() == 1){
		calAndFindPromotion()
		if(productOBJ.theBest != 0){
			$("#promosel option[value='"+productOBJ.theBest+"']").prop('selected', true);
		}else{
			$("#promosel option:eq(0)").prop('selected', true)
		}
	}else if($('#selectallprivilege').val() == 2){
		calAndFindPromotion()
	}else if($('#selectallprivilege').val() == 3){
		productOBJ.giftVoucher = $('#giftvocID').val()
		calAndFindPromotion()					
	}

	readtreatTable();
	readMedTable();
	readProTable();
	/* 
	readFreeTable()
	/* readContype() */
	/*readtotalall()
	sumamt_money() */
	$(".numeric").autoNumeric('init')
	})
	$('.preload').addClass('hidden');
	
}
function readtreatTable(){ 
	 
	$('.showalltreatment').empty()	
		for (let i = 0; i < productOBJ.treatment.length; i++) {  
			 
			let appall = '<tr > '+
			'<th class="uk-text-center"><input type="checkbox" class="treatmentcheckbok" name="treatmentcheckbok" value='+i+' /></th>';
			
			if(productOBJ.treatment[i].can_payment<=0.00){
				appall += 
				  		  '<th class="uk-text-center numeric"><input type="text" id="treatment_pay" name="treatment_pay" '+
				  		  ' autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right treatment_pay" readonly="readonly" /></th>';
				  		   
			}else{
				appall += 
						  '<th class="uk-text-center numeric"><input type="text" id="treatment_pay" name="treatment_pay" '+
							' autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right treatment_pay" readonly="readonly" /></th>';
			}
			
			appall += '<th class="uk-text-center numeric">'+productOBJ.treatment[i].can_payment+'</th>  '+	
			//'<th class="uk-text-center numeric">'+productOBJ.treatment[i].pay_amount+'</th>  '+
			'<th class="uk-text-center">'+productOBJ.treatment[i].treatName+'</th>  '+	 
			/* '<th class="uk-text-center numeric"><input type="text" id="treatment_pay_bill" name="treatment_pay_bill" '+
	  		  ' autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right treatment_pay" readonly="readonly" /></th>'; 
			'<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_price+'</th>'+ */
			'<th class="hidden"><input type="text" name="receipt_id" value="'+productOBJ.treatment[i].receipt_id+'" /></th>'+
			'<th class="hidden"><input type="text" name="treathomecall" value="'+productOBJ.treatment[i].homecall+'" /></th>'+
			'<th class="hidden"><input type="text" name="treatrecall" value="'+productOBJ.treatment[i].recall+'" /></th>'+
			
			'<th class="hidden"><input type="text" name="order_id" value="'+productOBJ.treatment[i].order_ID+'" /></th>'+
			'<th class="hidden"><input type="text" name="orderLine_ID" value="'+productOBJ.treatment[i].orderLine_ID+'" /></th>'+ 
			'<th class="hidden"><input type="text" name="treat_id" value="'+productOBJ.treatment[i].treatID+'" /></th>'+
			'<th class="hidden"><input type="text" name="orderline_price" value="'+productOBJ.treatment[i].treat_price+'" /></th>'+
			'<th class="hidden"><input type="text" name="or_branch_disbaht_total" value="'+productOBJ.treatment[i].treat_total+'" /></th>'+
			 
			//'<th class="hidden"><input type="text" name="treat_paid_amount" value="'+productOBJ.treatment[i].pay_amount+'" /></th>'+
			 
			'<th class="hidden"><input type="text" name="treatsurf"  value="'+productOBJ.treatment[i].surf+'"  /></th>'+
			'<th class="hidden"><input type="text" name="treattooth"  value="'+productOBJ.treatment[i].tooth+'"  /></th>'+
			'<th class="hidden"><input type="text" name="treattooth_type_id"  value="'+productOBJ.treatment[i].tooth_type_id+'"  /></th>'+
			'<th class="hidden"><input type="text" name="disdoctorall" value="'+productOBJ.treatment[i].treat_dis_doctor+'" /></th>'+
			'<th class="hidden"><input type="text" name="disbranchall" value="'+productOBJ.treatment[i].treat_dis_branch+'" /></th>'+
			'<th class="hidden"><input type="text" name="treat_dis" value="'+productOBJ.treatment[i].treat_dis+'" /></th>'+
			
			'<th class="hidden"><input type="text" name="treatment_can_payment" value="'+productOBJ.treatment[i].can_payment+'" /></th>'+ 
			/* 
			
			'<th class="hidden"><input type="text" name="financeModel.pay_sso" value="'+productOBJ.treatment[i].treat_pay_sso+'" /></th>'+
			 */
			 
			'</tr>';
				$('.showalltreatment').append(appall) 
		}
		$('.treatmentcheckbok').prop('checked', true);
		if($('.treatmentcheckbok').is(':checked')){ 
	    	for (let i = 0; i < productOBJ.treatment.length; i++) {  
	        	var can_pay_treat = productOBJ.treatment[i].can_payment;  
	        	$('.treatment_pay').eq(i).val(can_pay_treat);
	        }
	    	for (let i = 0; i < productOBJ.medicine.length; i++) {  
	        	var can_pay_med = productOBJ.medicine[i].can_payment; 
	        	$('.medicine_pay').eq(i).val(can_pay_med);
	        }
	    	for (let i = 0; i < productOBJ.product.length; i++) {  
	        	var can_pay_pro = productOBJ.product[i].can_payment; 
	        	$('.product_pay').eq(i).val(can_pay_pro);
	        }
	    }
		
		var check_use_button_pay = productOBJ.treatment.length;
		if(check_use_button_pay==0){
			$('#howto').hide();
		}
}
function readMedTable(){
	$('.showallmedicine').empty()	
		for (let i = 0; i < productOBJ.medicine.length; i++) {  
			 
			let appall = '<tr > '+
			'<th class="uk-text-center"><input type="checkbox" class="medicinecheckbok" name="medicinecheckbok" value='+i+' /></th>';
			
			if(productOBJ.medicine[i].can_payment<=0.00){
				appall += 
				  		  '<th class="uk-text-center numeric"><input type="text" id="medicine_pay" name="medicine_pay" '+
				  		  ' autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right medicine_pay" readonly="readonly" /></th>';
				  		   
			}else{
				appall += 
						  '<th class="uk-text-center numeric"><input type="text" id="medicine_pay" name="medicine_pay" '+
							' autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right medicine_pay" readonly="readonly" /></th>';
			}
			appall += '<th class="uk-text-center numeric">'+productOBJ.medicine[i].can_payment+'</th>  '+	
			'<th class="uk-text-center numeric">'+productOBJ.medicine[i].pay_amount+'</th>  '+
			'<th class="uk-text-center hidden"><input name="medID" type="hidden" value="'+productOBJ.medicine[i].medID+'" />'+productOBJ.medicine[i].medID+'</th>  '+
			'<th class="uk-text-center">'+productOBJ.medicine[i].medName+'</th>'+ 
			'<th class="uk-text-center numeric">'+productOBJ.medicine[i].qty+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.medicine[i].price_per_unit+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.medicine[i].med_total+'</th>'+
			/* '<th class="uk-text-center numeric">'+productOBJ.medicine[i].med_dis+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.medicine[i].med_dis_branch+'</th>'+  */
			'<th class="uk-text-center countall numeric meddistotal'+i+'">'+productOBJ.medicine[i].or_branch_disbaht_total+'</th>'+ 
			'<th class="uk-text-center numeric">'+productOBJ.medicine[i].med_owetotal+'</th>'+
			
			'<th class="hidden"><input type="text" name="med_paid_amount" value="'+productOBJ.medicine[i].pay_amount+'" /></th>'+
			  
			'<th class="hidden"><input type="text" name="med_orderLine_ID" value="'+productOBJ.medicine[i].med_orderLine_ID+'" /></th>'+ 
			'<th class="hidden"><input type="text" name="product_id" value="'+productOBJ.medicine[i].medID+'" /></th>'+
			'<th class="hidden"><input type="text" name="or_qty" value="'+productOBJ.medicine[i].qty+'" /></th>'+
			'<th class="hidden"><input type="text" name="price_per_unit" value="'+productOBJ.medicine[i].price_per_unit+'" /></th>'+
			'<th class="hidden"><input type="text" name="med_dis" value="'+productOBJ.medicine[i].med_dis+'" /></th>'+
			'<th class="hidden"><input type="text" name="med_dis_branch" value="'+productOBJ.medicine[i].med_dis_branch+'" /></th>'+
			'<th class="hidden"><input type="text" name="medicine_can_payment" value="'+productOBJ.medicine[i].can_payment+'" /></th>'+
			
			'</tr>';
				$('.showallmedicine').append(appall)
		} 
		$('.medicinecheckbok').prop('checked', true);
		if($('.medicinecheckbok').is(':checked')){  
	    	for (let i = 0; i < productOBJ.medicine.length; i++) {  
	        	var can_pay_med = productOBJ.medicine[i].can_payment; 
	        	$('.medicine_pay').eq(i).val(can_pay_med);
	        } 
	    }
}
function readProTable(){
	$('.showallproduct').empty()	
		for (let i = 0; i < productOBJ.product.length; i++) { 
			let appall = '<tr > '+
			'<th class="uk-text-center"><input type="checkbox" class="productcheckbok" name="productcheckbok" value='+i+' /></th>';
			
			if(productOBJ.product[i].can_payment<=0.00){
				appall += 
				  		  '<th class="uk-text-center numeric"><input type="text" id="product_pay" name="product_pay" '+
				  		  ' autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right product_pay" readonly="readonly" /></th>';
				  		   
			}else{
				appall += 
						  '<th class="uk-text-center numeric"><input type="text" id="product_pay" name="product_pay" '+
							' autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right product_pay" readonly="readonly" /></th>';
			}
			appall += '<th class="uk-text-center numeric">'+productOBJ.product[i].can_payment+'</th>  '+	
			'<th class="uk-text-center numeric">'+productOBJ.product[i].pay_amount+'</th>  '+
			'<th class="uk-text-center hidden"><input name="proID" type="hidden" value="'+productOBJ.product[i].proID+'" />'+productOBJ.product[i].proID+'</th>  '+
			'<th class="uk-text-center">'+productOBJ.product[i].proName+'</th>'+ 
			'<th class="uk-text-center numeric">'+productOBJ.product[i].qty+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.product[i].price_per_unit+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.product[i].pro_total+'</th>'+
			/* '<th class="uk-text-center numeric">'+productOBJ.product[i].pro_dis+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.product[i].pro_dis_branch+'</th>'+  */
			'<th class="uk-text-center countall numeric prodistotal'+i+'">'+productOBJ.product[i].or_branch_disbaht_total+'</th>'+ 
			'<th class="uk-text-center numeric">'+productOBJ.product[i].pro_owetotal+'</th>'+
			
			'<th class="hidden"><input type="text" name="pro_paid_amount" value="'+productOBJ.product[i].pay_amount+'" /></th>'+
			
			'<th class="hidden"><input type="text" name="pro_orderLine_ID" value="'+productOBJ.product[i].pro_orderLine_ID+'" /></th>'+ 
			'<th class="hidden"><input type="text" name="product_id" value="'+productOBJ.product[i].medID+'" /></th>'+
			'<th class="hidden"><input type="text" name="or_qty" value="'+productOBJ.product[i].qty+'" /></th>'+
			'<th class="hidden"><input type="text" name="price_per_unit" value="'+productOBJ.product[i].price_per_unit+'" /></th>'+
			'<th class="hidden"><input type="text" name="pro_dis" value="'+productOBJ.product[i].pro_dis+'" /></th>'+
			'<th class="hidden"><input type="text" name="pro_dis_branch" value="'+productOBJ.product[i].pro_dis_branch+'" /></th>'+
			/* '<th class="hidden"><input type="text" name="financeModel.product_id" value="'+productOBJ.treatment[i].medID+'" /></th>'+
			'<th class="hidden"><input type="text" name="financeModel.or_qty" value="'+productOBJ.treatment[i].qty+'" /></th>'+
			'<th class="hidden"><input type="text" name="financeModel.orderLine_price" value="'+productOBJ.treatment[i].price_per_unit+'" /></th>'+
			'<th class="hidden"><input type="text" name="financeModel.or_branch_disbaht_total" value="'+productOBJ.treatment[i].or_branch_disbaht_total+'" /></th>'+ */
			
			'</tr>';
				$('.showallproduct').append(appall);
		} 
		$('.productcheckbok').prop('checked', true);
		if($('.productcheckbok').is(':checked')){  
	    	for (let i = 0; i < productOBJ.product.length; i++) {  
	        	var can_pay_pro = productOBJ.product[i].can_payment; 
	        	$('.product_pay').eq(i).val(can_pay_pro);
	        }
	    }
}
function readReceipt(){ 
	
	$('.showallreceipt').empty()	
	 
		for (let i = 0; i < receiptOBJ.printreceipt.length; i++) { 
			 
			let appall = '<tr > '+
			'<th class="uk-text-center"><input type="checkbox" class="receiptcheckbok" name="receiptcheckbok" value='+receiptOBJ.printreceipt[i].receipt_id+' /></th>'+ 
			'<th class="uk-text-center">'+receiptOBJ.printreceipt[i].countrow+'</th>  '+  
			'<th class="uk-text-center">';
			if(receiptOBJ.printreceipt[i].receipt_typename==1){
				appall +=	'ประกันสังคม';
			}else{
				appall +=	'ปกติ';
			}
			appall += '</th>  '+ 
			'</tr>';
			$('.showallreceipt').append(appall);
			 
		} 
}
 function sumAmountPayTotal(){  
	 var sum_all = 0;
	 var sum_treatment = 0;    
		 $.each($('.treatment_pay'), function (index, value) { 
			 var treatment_pay_value = 0; 
			 if($(value).val()!=''){
				 treatment_pay_value = $(value).val().replace(/,/g,"");
				 sum_treatment = (parseFloat(sum_treatment)+parseFloat(treatment_pay_value));
			 }else{
				 sum_treatment = sum_treatment;
			 }
			 
		});   
	sum_all = parseFloat(sum_all)+parseFloat(sum_treatment);
		 
	var sum_medicine = 0;	 
		$.each($('.medicine_pay'), function (index, value) { 
			 var medicine_pay_value = 0;  
			 
			 if($(value).val()!=''){ 
				 medicine_pay_value = $(value).val().replace(/,/g,"");
				 sum_medicine = (parseFloat(sum_medicine)+parseFloat(medicine_pay_value));
			 }else{ 
				 sum_medicine = sum_medicine;
			 }
		}); 
	sum_all = parseFloat(sum_all)+parseFloat(sum_medicine);
		
	var c = 0;
	var sum_product = 0;	 
		$.each($('.product_pay'), function (index, value) { 
			 var product_pay_value = 0;  
			 if($(value).val()!=''){
				 product_pay_value = $(value).val().replace(/,/g,"");
				 sum_product = (parseFloat(sum_product)+parseFloat(product_pay_value));
			 }else{
				 sum_product = sum_product;
			 }
		});  
	 sum_all = parseFloat(sum_all)+parseFloat(sum_product);
		 
	$('#amount_pay_total').val(sum_all);
	
	return sum_all;
} 
 function getAmountDeposit() {
		let check = 0;

		var net = $('#owe_text').val().replace(/,/g,""); 
		var amountCredit = $('#credit_card').val().replace(/,/g,"");
			if(amountCredit=='') amountCredit = 0; 
		var amountMoney = $('#money').val().replace(/,/g,"");
			if(amountMoney=='') amountMoney = 0; 
		var amountSso = $('#sso').val().replace(/,/g,"");
			if(amountSso=='') amountSso = 0;
		var sumall = 0; 
		
		$.ajax({  //   
		    type: "post",
		    url: "ajax_json_getdeposit", 
		    data: {hn:'<s:property value="finanModel.order_Hn" />'},
		    async:false, 
		    success: function(result){ 
		    	  if (result != ''){ 
		    		  check = result.totalamountall;
		    		  $('#textdeposit').text("("+check+")");
				  }
			}  
		})
		
		sumall = parseFloat(amountMoney)+parseFloat(amountCredit)+parseFloat(amountSso);   
		
		if(parseFloat(net)>parseFloat(sumall)){
			var csumall = parseFloat(sumall)+parseFloat(check); 
			if(parseFloat(net)>parseFloat(csumall)){ 
				check = check;
			}else{  
				check = parseFloat(net)-parseFloat(sumall); 
			} 
		}else{
			check = parseFloat(net)-parseFloat(sumall);
		}  
		
		return check;
}

</script>		
</body>
</html>