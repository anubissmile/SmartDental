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
		    		<button type="submit" class="uk-button uk-button-success" ><i class="uk-icon-save"></i> บันทึกข้อมูล</button> 
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
		    	<h4 class="hd-text uk-text-primary margin5">รายการรักษา </h4>
		    	<div class="new-table-scroll">
				<table class="uk-table  uk-table-hover uk-table-condensed uk-width-1-1 border-gray " >
				    <thead>
				        <tr class="hd-table">
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>เลือก</p></th>
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>จ่ายเงิน</p></th>
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนเงินที่จ่ายได้</p></th>
				        	<th class="uk-text-center uk-width-1-10" rowspan="2"><p>ชำระเงินแล้ว</p></th>
				            <th class="uk-text-center uk-width-3-10" rowspan="2"><p>รายการรักษา</p></th> 
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>ค่ารักษา</p></th> 														             						            
				            <th class="uk-text-center uk-width-2-10" colspan="3">ส่วนลด</th>
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนเงินทั้งหมด</p></th>	
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>ค้างชำระ</p></th>
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>ประกันสังคม</p></th>
				        </tr>
				        <tr class="hd-table">					 
				            <th class="uk-text-center">Promotion</th> 
				        	<th class="uk-text-center ">แพทย์</th>
				            <th class="uk-text-center ">ร้าน</th>
				        </tr>
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
				            <th class="uk-text-center uk-width-2-10" colspan="2">ส่วนลด</th>
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนเงินทั้งหมด</p></th>  
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>ค้างชำระ</p></th>
				        </tr>
				        <tr class="hd-table">  
				            <th class="uk-text-center">ต่อหน่วย</th>
				            <th class="uk-text-center">รวมทั้งหมด</th>
				            <th class="uk-text-center">Promotion</th>
				            <th class="uk-text-center">ร้าน</th>
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
				            <th class="uk-text-center uk-width-2-10" colspan="2">ส่วนลด</th>
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนเงินทั้งหมด</p></th> 
				            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>ค้างชำระ</p></th>
				        </tr>
				        <tr class="hd-table">  
				            <th class="uk-text-center">ต่อหน่วย</th>
				            <th class="uk-text-center">รวมทั้งหมด</th>
				            <th class="uk-text-center">Promotion</th>
				            <th class="uk-text-center">ร้าน</th>
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
}).on('change','.treatmentcheckbok',function (){ 
	var tik = $(this).val(); 
	var index = $('.treatmentcheckbok').index(this);
	 
		if (this.checked) {
			$(".treatment_pay").eq(index).attr("readonly", false);
			$(".treatment_pay").eq(index).val('');
		}else{
			$(".treatment_pay").eq(index).attr("readonly", true);
			$(".treatment_pay").eq(index).val(""); 
		}
}).on('change','.medicinecheckbok',function (){ 
	var tik = $(this).val(); 
	var index = $('.medicinecheckbok').index(this); 
	 
		if (this.checked) {
			$(".medicine_pay").eq(index).attr("readonly", false);
			$(".medicine_pay").eq(index).val('');
		}else{
			$(".medicine_pay").eq(index).attr("readonly", true);
			$(".medicine_pay").eq(index).val(""); 
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
		alert(receiptcheckbok.eq(i).val())
		
		window.open('addFinanceReceipt?treatment_pay='+treatment_pay+ //drugname='+encodeURI(drugname)+
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
			'<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_price+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_dis+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_dis_doctor+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_dis_branch+'</th>'+ 
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
			'<th class="hidden"><input type="text" name="pay_amount" value="'+productOBJ.treatment[i].pay_amount+'" /></th>'+
			'<th class="hidden"><input type="text" name="treat_pay_sso" value="'+productOBJ.treatment[i].treat_pay_sso+'" /></th>'+
			'<th class="hidden"><input type="text"  name="treatsurf"  value="'+productOBJ.treatment[i].surf+'"  /></th>'+
			'<th class="hidden"><input type="text"  name="treattooth"  value="'+productOBJ.treatment[i].tooth+'"  /></th>'+
			'<th class="hidden"><input type="text"  name="treattooth_type_id"  value="'+productOBJ.treatment[i].tooth_type_id+'"  /></th>'+
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
			'<th class="uk-text-center numeric">'+productOBJ.medicine[i].med_dis+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.medicine[i].med_dis_branch+'</th>'+ 
			'<th class="uk-text-center countall numeric meddistotal'+i+'">'+productOBJ.medicine[i].or_branch_disbaht_total+'</th>'+ 
			'<th class="uk-text-center numeric">'+productOBJ.medicine[i].med_owetotal+'</th>'+
			  
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
						  '<th class="uk-text-center numeric"><input type="text" id="medicine_pay" name="product_pay" '+
							' autocomplete="off" class="uk-form numeric uk-width-1-1 uk-text-right product_pay" readonly="readonly" /></th>';
			}
			appall += '<th class="uk-text-center numeric">'+productOBJ.product[i].can_payment+'</th>  '+	
			'<th class="uk-text-center numeric">'+productOBJ.product[i].pay_amount+'</th>  '+
			'<th class="uk-text-center hidden"><input name="proID" type="hidden" value="'+productOBJ.product[i].proID+'" />'+productOBJ.product[i].proID+'</th>  '+
			'<th class="uk-text-center">'+productOBJ.product[i].proName+'</th>'+ 
			'<th class="uk-text-center numeric">'+productOBJ.product[i].qty+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.product[i].price_per_unit+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.product[i].pro_total+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.product[i].pro_dis+'</th>'+
			'<th class="uk-text-center numeric">'+productOBJ.product[i].pro_dis_branch+'</th>'+ 
			'<th class="uk-text-center countall numeric prodistotal'+i+'">'+productOBJ.product[i].or_branch_disbaht_total+'</th>'+ 
			'<th class="uk-text-center numeric">'+productOBJ.product[i].pro_owetotal+'</th>'+
			
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

</script>		
</body>
</html>