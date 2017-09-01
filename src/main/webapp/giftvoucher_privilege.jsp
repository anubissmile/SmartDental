<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : สิทธิประโยชน์ของ Gift Voucher</title>
		<style>
			.ridge {border-style: ridge;
					padding:15px;
					}
			.pd-5{
				padding-left: 10px !important;
			}
		</style>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<body>
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="backend-giftvoucher-privilege.jsp" %>
					<div class="uk-grid"></div>
					<form id="service" action="insertGiftvoucher" method="post">					
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class=" uk-grid uk-grid-collapse">
						<div class="uk-width-1-1 uk-panel uk-panel-box" style="min-height: 99vh;" >
							<div class=" uk-push-1-5 uk-width-3-5 ridge" >
								<h3>สิทธิประโยชน์ของ Gift Voucher </h3>
								<div class="uk-grid uk-grid-collapse  uk-form uk-text-center">
									<p>ประเภทรายการ</p>
								</div>
								<div class="uk-grid uk-grid-collapse uk-form uk-text-center">
									<select class="uk-form uk-width-4-10" name="giftvoucherModel.gvp_type" required="required" id="alltypesel">
										<option value="">กรุณาเลือก</option>
										<option value="1">กำหนดราคา</option>
										<option value="2">แทนเงินสด</option>
									</select>
								</div>
								<div  class = "uk-grid uk-grid-collapse uk-form  alltype ">
							
								</div>
								<div class="uk-grid ">
	                            	<div class="uk-width-1-2 uk-text-right">
							 			<button class="uk-button uk-button-success   " id="checkbtn" type="button">บันทึก</button>
							 			<button class="uk-button uk-button-success hidden  " id="realsumbit" type="submit">บันทึก</button>
	                            	</div>
 	                            	<div class="uk-width-1-2 ">
	                            		<a href="getGiftVoucherList" class="uk-button uk-button-danger " >ยกเลิก</a>
	                         		</div>
	                        	</div>
					</div>
					</div>
				</div>
				</div>
		<!-- 		form gift voucher header 			-->
					<s:hidden name="giftvoucherModel.gv_name" value="%{giftvoucherModel.gv_name}" />
					<s:hidden name="giftvoucherModel.gv_prefix" value="%{giftvoucherModel.gv_prefix}" />
					<s:hidden name="giftvoucherModel.gv_numberlenght" value="%{giftvoucherModel.gv_numberlenght}" />
					<s:hidden name="giftvoucherModel.gv_start_number" value="%{giftvoucherModel.gv_start_number}" />
					<s:hidden name="giftvoucherModel.gv_run_count" value="%{giftvoucherModel.gv_run_count}" />
					<s:hidden name="giftvoucherModel.gv_suffix" value="%{giftvoucherModel.gv_suffix}" />
					<s:hidden name="giftvoucherModel.gv_description" value="%{giftvoucherModel.gv_description}" />
					<s:hidden name="giftvoucherModel.gv_start_date" value="%{giftvoucherModel.gv_start_date}" />
					<s:hidden name="giftvoucherModel.gv_expiredate" value="%{giftvoucherModel.gv_expiredate}" />
					</form>				 
			</div>	
		</div>
<script src="js/autoNumeric.min.js"></script>
		<script>	
		$(document).on("change","#alltypesel",function(){			
	    	if($(this).val()== 0){
	    		$('.alltype').html('<div></div>');
	    	}else if($(this).val()== 1){
	    		var type1 = 
	    					'<div class = "uk-form ridge uk-width-1-1 pd-5 " >'+
							'<div class="">'+
							'<div class="uk-grid uk-grid-collapse uk-form ">'+
								'<p>ประเภท</p>'+
							'</div>'+
							'<div class="uk-grid uk-grid-collapse  uk-form ">'+
								'<div class="uk-width-1-1">'+
								'<select class = "uk-width-1-2" id="bigtype" name=""  >'+
									'<option value="0">เลือกประเภท</option>'+
									/* '<option value="1">สินค้า</option> '+ */
									'<option value="2">การรักษา</option>'+ 
								'</select>'+
								'</div>'+											
							'</div>'+
							'</div>'+
							'<div class="typeall2">'+
							'</div>'+    					
	    					'</div>';
	    		$(".alltype").html(type1);
	    	}else if($(this).val()== 2){
	    		var type2 = '<div class = "uk-form ridge uk-width-1-1 pd-5" >'+
							'<div class="percentdis">'+
								'<div class="uk-grid uk-grid-collapse uk-form ">'+
									'<p>จำนวนเงิน</p>'+
								'</div>'+
								'<div class="uk-grid uk-grid-collapse  uk-form ">'+
									'<div class="uk-width-1-1">'+
										'<input type="text"  required="required" class="uk-form uk-width-5-10  numeric" name="giftvoucherModel.gvp_amountString">บาท'+
									'</div>'+											
								'</div>'+
							'</div>'+
							'</div>';
				$(".alltype").html(type2);
	    	}
	    	$(".numeric").autoNumeric('init');
		});

		$(document).on("change","#bigtype",function(){			
	    	if($(this).val()== ''){
	    		$('.typeall2').html('<div></div>');
	    	}else if($(this).val()== 1){
	    		var typepro1 =	'<div class="protype">'+
								'<div class="uk-grid uk-grid-collapse uk-form ">'+
									'<p>ประเภทสินค้า</p>'+
								'</div>'+
								'<div class="uk-grid uk-grid-collapse  uk-form ">'+
									'<div class="uk-width-1-1">'+
									'<select class = "uk-width-1-2" id="product_type" required="required" name="proDetailModel.product_type"  >'+
										'<option value="">เลือกประเภทสินค้า</option> '+
										'<option value="1">ยา / Medicine</option> '+
										'<option value="2">สินค้า / Product</option> '+
										'<option value="3">วัสดุ / Material</option> '+
									'</select>'+
									'</div>'+											
								'</div>'+
							'</div>'+
							'<div class="list">'+								
							'</div>';
	    		$(".typeall2").html(typepro1);
	    	}else{
	    		var typetreat1 =	'<div class="treattype">'+
									'<div class="uk-grid uk-grid-collapse uk-form ">'+
										'<p>ประเภทการรักษา</p>'+
									'</div>'+
									'<div class="uk-grid uk-grid-collapse  uk-form ">'+
										'<div class="uk-width-1-1">'+
										'<select class = "uk-width-1-2" required="required" id="treatment_type" name="giftvoucherModel.gvp_product_type"  >'+
											'<option value="">เลือกประเภทการรักษา</option> '+
											'<option value="5">กลุ่มการรักษา</option>'+ 
											'<option value="6">หมวดการรักษา</option>'+
											'<option value="7">รายการรักษา</option>'+  
										'</select>'+
										'</div>'+											
									'</div>'+
									'</div>'+
									'<div class="listtreat">'+
									'</div>';
				$(".typeall2").html(typetreat1);
	    	}
		});
		$(document).on("change","#product_type",function(){			
	    	if($(this).val()== ''){
	    		$('.list').html('<div></div>');
	    	}else{
	    		var typepro2= 	'<div class="uk-grid uk-grid-collapse uk-form ">'+
									'<p>รายการ</p>'+
								'</div>'+
								'<div class="uk-grid uk-grid-collapse  uk-form ">'+
									'<div class="uk-width-1-1">'+
									'<select style="width:33vh" id="name" required="required" name="proDetailModel.product_id"  >'+
										'<option  value="">เลือกรายการ</option>'+
									'</select>'+
									'</div>'+											
								'</div>';
	    		$('.list').html(typepro2);
	    		$(document).ready(function () {
	    			$("#name").select2({
	    				ajax: {
	    				    url: "ajax/getPartner.jsp",
	    				    delay: 1000,
	    				    data: function (params) {
	    				      return {
	    				        q: params.term, // search term
	    				        productType:$('#product_type').val()
	    				      };
	    				    },
	    				    processResults: function (data, params) {
	    				      // parse the results into the format expected by Select2
	    				      // since we are using custom formatting functions we do not need to
	    				      // alter the remote JSON data, except to indicate that infinite
	    				      // scrolling can be used
	    				      params.page = params.page || 1;

	    				      return {
	    				        results: data.results,
	    				        pagination: {
	    				          more: (params.page * 30) < data.total_count
	    				        }
	    				      };
	    				    },
	    				    cache: true
	    			  	},
	    			  	minimumInputLength: 1
	    		  	});
	    			
				})
	    	}
		});
		$(document).on("change","#treatment_type",function(){			
	    	if($(this).val()== '' || $(this).val()== 1){
	    		$('.listtreat').html('<div></div>');
	    	}else{
	    		var typetreat2 ='<div class="uk-grid uk-grid-collapse uk-form ">'+
								'<p>รายการ</p>'+
								'</div>'+
								'<div class="uk-grid uk-grid-collapse  uk-form ">'+
									'<div class="uk-width-1-1">'+
									'<select style="width:33vh" id="name_treat"  name="proDetailModel.pro_treatmentID"  >'+
										'<option  value="">เลือกรายการ</option>'+
									'</select>'+							
									'</div>'+									
								'</div>'+
								'<div class="uk-grid uk-grid-collapse uk-form ">'+
								'<p>จำนวนเงิน</p>'+
								'</div>'+
								'<div class="uk-grid uk-grid-collapse  uk-form ">'+
									'<div class="uk-width-1-2">'+
									'<input type="text" class="numeric" id="numtreat" value="0">บาท'+									
									'</div>'+
								'</div>'+
								'<button class="uk-button uk-button-success uk-button-small addtreatis  uk-container-center " type="button" ><i class="uk-icon-plus"></i>เพิ่ม</button>'+
									'<div class="uk-width-1-1">'+
									'<table class="uk-table uk-table-condensed uk-table-hover">'+
									'<thead>'+
									'<tr>'+
										'<th class="uk-text-center">ชื่อรายการ</th>'+
										'<th class="uk-text-center">ประเภทรายการ</th>'+
										'<th class="uk-text-center">จำนวนเงิน</th>'+
										'<th class="uk-text-center"></th>'+
									'</tr>'+									
									'</thead>'+
									'<tbody class="bodytreat">'+										
									'</tbody>'+
									'</table>'+
								'</div>';
			$('.listtreat').html(typetreat2);
			$(document).ready(function () {
    			$("#name_treat").select2({
    				ajax: {
    				    url: "ajax/getPartnerTreatment.jsp",
    				    delay: 1000,
    				    data: function (params) {
    				      return {
    				        q: params.term, // search term
    				        treatmenttype:$('#treatment_type').val()
    				      };
    				    },
    				    processResults: function (data, params) {
    				      // parse the results into the format expected by Select2
    				      // since we are using custom formatting functions we do not need to
    				      // alter the remote JSON data, except to indicate that infinite
    				      // scrolling can be used
    				      params.page = params.page || 1;

    				      return {
    				        results: data.results,
    				        pagination: {
    				          more: (params.page * 30) < data.total_count
    				        }
    				      };
    				    },
    				    cache: true
    			  	},
    			  	minimumInputLength: 1

    		  	});
    			
			});
	    	}
	    	$(".numeric").autoNumeric('init');
		});

		$(document).on("click",".addtreatis",function(){			
			let chk = true
			let text = 'กรุณาเลือกรายการ'
			var treattable = '<tr><th class="uk-text-center"><input type="hidden" name="giftvoucherModel.gvp_productArr"  class="numall" value="'+$('#name_treat').val()+'">'+$('#name_treat option[value="'+$('#name_treat').val()+'"]').text()+'</th>'+
			'<th class="uk-text-center">'+$('#treatment_type option[value="'+$('#treatment_type').val()+'"]').text()+'</th>'+
			'<th class="uk-text-center"><input type="hidden" name="giftvoucherModel.gvp_amountArr" value="'+$('#numtreat').val()+'">'+$('#numtreat').val()+'</th>'+
			'<th><button class="uk-button uk-button-danger uk-button-small" id="deltreatsel" type="button" ><i class="uk-icon-eraser"></i>ลบ</button></th></tr>'

			if($('#name_treat').val() != ''){
				$(".numall").each(function() {
				    if($(this).val() == $('#name_treat').val()  ){			    	
				    	chk = false
				    	text = 'มีการซ้ำกันของรายการ'
				    	return false
				    }
				})
				
			}else{
				chk = false
			}
			if(chk){				
				$('.bodytreat').append(treattable)
			}else{
				swal(
		   			      'การทำงานไม่สำเร็จ',
		   			   		text,
		   			      'warning'
		   			    )
			}
		})
		$(document).on("click","#deltreatsel",function(){
			$(this).parents('tr').remove()
		})
		$(document).on("click","#checkbtn",function(){
			if($('#alltypesel').val() == 1){
				let chk = false
				$(".numall").each(function() {
					chk = true
					return false
				})
				if(chk){
					$('#realsumbit').trigger('click');
				}else{
					swal(
			   			      'การทำงานไม่สำเร็จ',
			   			   		'กรุณาเพิ่มข้อมูล',
			   			      'warning'
			   			    )
				}
			}else{
				$('#realsumbit').trigger('click');
			}
		})
		
		</script>
</body>
</html>