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
			<form id="saveReceipt" action="addFinanceReceipt" method="post"> 
		    	<div class=" uk-panel-box">
		    	<div class="uk-grid uk-grid-collapse">
		    		<div class="uk-width-1-2"><h2 class="uk-text-primary margin5"><b>รายการค่าใช้จ่าย</b>
		    		<input type="hidden" value="<s:property value="treatmentModel.treatment_patient_ID" />" name="treatmentModel.treatment_patient_ID">
					<input type="hidden" value="<s:property value="finanModel.order_ID" />" name="finanModel.order_treatpatID">
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
		    		<button type="button" class="uk-button uk-button-success" id="click-save" ><i class="uk-icon-save"></i> บันทึกข้อมูล</button> 
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
					
					<div id="modal-save" class="uk-modal">
					    <div class="uk-modal-dialog uk-form">
					        
					        <div class="uk-modal-header">
					            <h2 class="uk-modal-title"><i class="uk-icon-save"></i> บันทึกการจ่ายเงิน</h2>
					        </div>
					        <div class="uk-modal-body">
					            <div class="uk-width-1-1 uk-overflow-container">
					            	<label>จำนวนเงินที่จ่าย</label>
			         				<input type="text"class="uk-form uk-width-1-3 uk-text-right numeric" id="amount_pay_total" />
									 <ul class="uk-form uk-list chanel-pay padding5 border-gray getchannel_id">
			                           	<!-- list jquery  -->
									</ul>
								</div>
							</div>
					        <div class="uk-modal-footer uk-text-right">
					            <button type="button" class="uk-modal-close uk-button uk-button-success" id="btn_submit">ตกลง</button>
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
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>ชำระเงินแล้ว</p></th>
				            <th class="uk-text-center uk-width-3-10" rowspan="2"><p>รายการรักษา</p></th>  
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>ค่ารักษา</p></th> 														             						            
				            <!-- <th class="uk-text-center uk-width-2-10" colspan="3">ส่วนลด</th> -->
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนเงินทั้งหมด</p></th>	
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>ค้างชำระ</p></th>
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>ประกันสังคม</p></th>
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
		"treat_owetotal":parseFloat(<s:property value="or_owe" />).toFixed(2),
		"treat_pay_sso":parseFloat(<s:property value="pay_sso" />).toFixed(2),
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
	
}).on('click','#printreceipt',function (){
	  
	var receiptcheckbok = $('input[name="receiptcheckbok"]:checked');
	 
	for (var i = 0; i < receiptcheckbok.length; i++) {  
		
		var receipt_id = receiptcheckbok.eq(i).val();
		
		window.open('report/report-receipt-new.jsp?receipt_id='+receipt_id+ //drugname='+encodeURI(drugname)+
				/* '&pill='+pill+
				'&episode='+episode+
				'&mealstatus='+mealstatus+
				'&mealtime='+mealtime+ */
				''
				, '_blank', '');
	} 
    
   // window.setTimeout('location.reload()', 3000);
}).on("click","#click-printreceipt",function(){			
	 
	$('.preload').addClass('hidden');
	
	 window.receiptOBJ = {"printreceipt": []}
	
	<s:iterator value="orderreceiptlist">
	receiptOBJ.printreceipt.push({
		"order_ID":<s:property value="order_ID" />,
		"receipt_id":<s:property value="receipt_id" />, 
		"countrow":<s:property value="countrow" />, 
		"receipt_typename":<s:property value="receipt_typename" />
	});
	</s:iterator>
	
	readReceipt();
	
	let modal = UIkit.modal('#modal-printreceipt');
	$(".numeric").autoNumeric('init');
	modal.show(); 
	
}).on("click","#click-save",function(){			
	  
	 window.receiptOBJ = {"savereceipt": []}
		
		<s:iterator value="channelpaylist">
		receiptOBJ.savereceipt.push({
			"channel_id":<s:property value="channel_id" />,
			"amount_channel":<s:property value="amount_channel" />
		});
		</s:iterator>
		
		$('.getchannel_id').empty();
		 
		var sumamount = 0;
		var pay_money = 0;
		var pay_credit = 0;
		var pay_deposit = 0;
		 
		 	for (let j= 0; j < receiptOBJ.savereceipt.length; j++) {  
		 		let appchannel = ' ';
		 		 
				if(receiptOBJ.savereceipt[j].channel_id=='1'){     
					appchannel += '<li><label > เงินสด  <input type="text" value="'+receiptOBJ.savereceipt[j].amount_channel+'" '+
								'class="uk-form uk-width-1-3 numeric uk-text-right" /></label></li> '+
								'<li><label> จำนวนเงิน <input type="text" id="channel_money" name="channel_money" placeholder="0" autocomplete="off" '+
								'class="uk-form uk-width-1-3 numeric uk-text-right"></label></li> '; 
					pay_money = parseFloat(pay_money)+parseFloat(receiptOBJ.savereceipt[j].amount_channel);
				}else if(receiptOBJ.savereceipt[j].channel_id=='2'){    
					appchannel += '</br> '+
							  '<li><label > เครดิต<input type="text" value="'+receiptOBJ.savereceipt[j].amount_channel+'" '+
							  'class="uk-form uk-width-1-3 numeric uk-text-right" /></label></li> '+
							  '<li><label> จำนวนเงิน <input type="text" id="channel_credit" name="channel_credit" placeholder="0" autocomplete="off" '+
							  'class="uk-form uk-width-1-3 numeric uk-text-right" /></label></li> '; 
					pay_credit = parseFloat(pay_credit)+parseFloat(receiptOBJ.savereceipt[j].amount_channel);
				}
				else if(receiptOBJ.savereceipt[j].channel_id=='4'){    
					appchannel += '</br> '+
							  '<li><label > เงินฝาก <input type="text" value="'+receiptOBJ.savereceipt[j].amount_channel+'" '+
							  'class="uk-form uk-width-1-3 numeric uk-text-right" /></label></li> '+
							  '<li><label> จำนวนเงิน <input type="text" id="channel_deposit" name="channel_deposit" placeholder="0" autocomplete="off" '+
							  'class="uk-form uk-width-1-3 numeric uk-text-right" /></label></li> '; 
					pay_deposit = parseFloat(pay_deposit)+parseFloat(receiptOBJ.savereceipt[j].amount_channel);
				}
				
				appchannel += '</li>';
				
				$('.getchannel_id').append(appchannel);
			}   
		 	
		 	sumamount = sumAmountPayTotal(); // getamounttotal pay row 772
		 	var d = 0; 
		 	do{ 
		 		if(receiptOBJ.savereceipt[d].channel_id=='1'){   
			 		if(parseFloat(sumamount)>=parseFloat(pay_money)){ 
			 			$('#channel_money').val(pay_money);
			 		}else{
			 			$('#channel_money').val(sumamount);
			 		} 
			 		sumamount = parseFloat(sumamount)-parseFloat(pay_money);	
			 		
		 		}else if(receiptOBJ.savereceipt[d].channel_id=='2'){  
		 			if(parseFloat(sumamount)>=parseFloat(pay_credit)){
			 			$('#channel_credit').val(pay_credit);
			 		}else{
			 			$('#channel_credit').val(sumamount);
			 		} 
		 			sumamount = parseFloat(sumamount)-parseFloat(pay_credit);
		 			
		 		}else if(receiptOBJ.savereceipt[d].channel_id=='4'){  
		 			if(parseFloat(sumamount)>=parseFloat(pay_deposit)){
			 			$('#channel_deposit').val(pay_deposit);
			 		}else{
			 			$('#channel_deposit').val(sumamount);
			 		} 
		 			sumamount = parseFloat(sumamount)-parseFloat(pay_deposit); 
		 		}   
		 		d++;
		 	}while (0<parseFloat(sumamount));   
		 	
	let modal = UIkit.modal('#modal-save');
	$(".numeric").autoNumeric('init');
	modal.show(); 
	
}).on("click","#btn_submit",function(){	
	var pay_sum = 0;
	var pay_money = 0;
	var pay_credit = 0;
	var pay_deposit = 0;
	for (let j= 0; j < receiptOBJ.savereceipt.length; j++) { 
		 
		if(receiptOBJ.savereceipt[j].channel_id=='1'&&$('#channel_money').val()!=''){   
			pay_money = $('#channel_money').val().replace(/,/g,""); 
			pay_sum = parseFloat(pay_sum)+parseFloat(pay_money);  
		}else if(receiptOBJ.savereceipt[j].channel_id=='2'&&$('#channel_credit').val()!=''){   
			pay_credit = $('#channel_credit').val().replace(/,/g,"");
			pay_sum = parseFloat(pay_sum)+parseFloat(pay_credit); 
		}else if(receiptOBJ.savereceipt[j].channel_id=='4'&&$('#channel_deposit').val()!=''){     
			pay_deposit = $('#channel_deposit').val().replace(/,/g,"");
			pay_sum = parseFloat(pay_sum)+parseFloat(pay_deposit); 
		}  
		
	}
	var amount_pay_total = $('#amount_pay_total').val().replace(/,/g,""); 
	
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
   	 
    
});
 
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
			'<th class="uk-text-center numeric">'+productOBJ.treatment[i].pay_amount+'</th>  '+
			'<th class="uk-text-center">'+productOBJ.treatment[i].treatName+'</th>  '+	 
			/* '<th class="uk-text-center numeric"><input type="text" id="treatment_pay_bill" name="treatment_pay_bill" '+
	  		  ' autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right treatment_pay" readonly="readonly" /></th>'; */
			'<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_price+'</th>'+
			/* '<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_dis+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_dis_doctor+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_dis_branch+'</th>'+  */
			'<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_total+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_owetotal+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_pay_sso+'</th>'+
			 
			'<th class="hidden"><input type="text" name="treathomecall" value="'+productOBJ.treatment[i].homecall+'" /></th>'+
			'<th class="hidden"><input type="text" name="treatrecall" value="'+productOBJ.treatment[i].recall+'" /></th>'+
			
			'<th class="hidden"><input type="text" name="order_id" value="'+productOBJ.treatment[i].order_ID+'" /></th>'+
			'<th class="hidden"><input type="text" name="orderLine_ID" value="'+productOBJ.treatment[i].orderLine_ID+'" /></th>'+ 
			'<th class="hidden"><input type="text" name="treat_id" value="'+productOBJ.treatment[i].treatID+'" /></th>'+
			'<th class="hidden"><input type="text" name="orderline_price" value="'+productOBJ.treatment[i].treat_price+'" /></th>'+
			'<th class="hidden"><input type="text" name="or_branch_disbaht_total" value="'+productOBJ.treatment[i].treat_total+'" /></th>'+
			 
			'<th class="hidden"><input type="text" name="treat_paid_amount" value="'+productOBJ.treatment[i].pay_amount+'" /></th>'+
			
			'<th class="hidden"><input type="text" name="treat_pay_sso" value="'+productOBJ.treatment[i].treat_pay_sso+'" /></th>'+
			'<th class="hidden"><input type="text" name="treatsurf"  value="'+productOBJ.treatment[i].surf+'"  /></th>'+
			'<th class="hidden"><input type="text" name="treattooth"  value="'+productOBJ.treatment[i].tooth+'"  /></th>'+
			'<th class="hidden"><input type="text" name="treattooth_type_id"  value="'+productOBJ.treatment[i].tooth_type_id+'"  /></th>'+
			'<th class="hidden"><input type="text" name="disdoctorall" value="'+productOBJ.treatment[i].treat_dis_doctor+'" /></th>'+
			'<th class="hidden"><input type="text" name="disbranchall" value="'+productOBJ.treatment[i].treat_dis_branch+'" /></th>'+
			'<th class="hidden"><input type="text" name="treat_dis" value="'+productOBJ.treatment[i].treat_dis+'" /></th>'+
			
			'<th class="hidden"><input type="text" name="treatment_can_payment" value="'+productOBJ.treatment[i].can_payment+'" /></th>'+
			/* 
			'<th class="hidden"><input type="text" name="financeModel.or_owe" value="'+productOBJ.treatment[i].treat_owetotal+'" /></th>'+
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

</script>		
</body>
</html>