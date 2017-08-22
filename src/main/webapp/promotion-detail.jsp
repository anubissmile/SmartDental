<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : รายละเอียดโปรโมชั่น</title>
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
				<%@include file="backend-promotion-manage-top.jsp" %>
					<div class="uk-grid"></div>
					<form id="service" action="addPromotionDetailInsert" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class=" uk-grid ">
						<div class="uk-width-7-10 ">
							<div class="uk-panel uk-panel-box" style="min-height: 99vh;">
                                <div class="uk-panel-header">
                                <h3 class="uk-panel-title uk-form ridge"> โปรโมชั่น 
                                <s:textfield cssClass="uk-width-4-10" readonly="true" name="protionModel.name" value="%{protionModel.name}"/>
                                 <s:textfield cssClass="uk-width-2-10 hidden" readonly="true" name="proDetailModel.promotion_id" value="%{protionModel.id}"/>
								    </h3>
								    <h3 class="uk-panel-title uk-form ridge">
								    วันที่
									<s:textfield cssClass="uk-width-2-10" readonly="true" name="protionModel.start_date" value="%{protionModel.start_date}"/>
									ถึง
									<s:textfield cssClass="uk-width-2-10" readonly="true" name="protionModel.end_date" value="%{protionModel.end_date}"/>
								    
								    </h3>
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> รายละเอียดโปรโมชั่น
								    </h3>
									<input type="hidden" name="protionModel.promotion_id" value="<s:property value='protionModel.id' />">
								</div>
									<div class="uk-width-1-1 uk-overflow-container uk-form">
									<table id="tbdetail" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									            <th class="uk-text-center">ชื่อ</th>
									            <th class="uk-text-center">รายการ</th>
									            <th class="uk-text-center">ประเภทรายการ</th> 
									            <th class="uk-text-center">จำนวน</th>
									            <th class="uk-text-center">ประเภทส่วนลด</th> 
									            
									            <th class="uk-text-center">จัดการ</th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="promotiondetailModel">
									    	<tr>
									    	
									    		<td class="uk-text-center"><s:property value="name"/></td>
									    		<td class="uk-text-center"><s:property value="tname"/></td>
									    		<s:if test="product_type != 0">
									    			<s:if test="product_type == 1">
									    			<td class="uk-text-center">ยา</td>
									    			</s:if>
									    			<s:elseif test="product_type == 2">
									    			<td class="uk-text-center">สินค้า</td>
									    			</s:elseif>
									    			<s:else>
									    			<td class="uk-text-center">วัสดุ</td>
									    			</s:else>
									    		</s:if>
									    		<s:else>
									    			<s:if test="pro_treatmentType == 1">
									    			<td class="uk-text-center">การรักษาทั้งหมด</td>
									    			</s:if>
									    			<s:elseif test="pro_treatmentType == 2">
									    			<td class="uk-text-center">กลุ่มการรักษา</td>
									    			</s:elseif>
									    			<s:elseif test="pro_treatmentType == 3">
									    			<td class="uk-text-center">หมวดการรักษา</td>
									    			</s:elseif>
									    			<s:else>
									    			<td class="uk-text-center">รายการรักษา</td>
									    			</s:else>
									    		</s:else>
									    		
									    		<td class="uk-text-center"><s:property value="discount_amount"/></td>
									    		<s:if test="discount_type == 1">
									    		<td class="uk-text-center">บาท</td>
									    		</s:if>
									    		<s:elseif test="discount_type == 2">
									    		<td class="uk-text-center">เปอร์เซ็นต์</td>
									    		</s:elseif>
									    		<s:else>
									    		<td class="uk-text-center">แถม</td>
									    		</s:else>
									    		
									    		<td class="uk-text-right">
									    			<a href="#delete_promotiondetail" id="btn_del" class="uk-button uk-button-danger uk-button-small" data-Productdel='<s:property value="id"/>' data-uk-modal>
									    			<i class="uk-icon-eraser"></i> ลบ</a>
									    		</td>
									    	</tr>
									    	</s:iterator>

									    
									    </tbody>   
									</table>
									</div>
							</div>
						</div>
						<div class="uk-width-3-10"  style = "padding-left: 6px;">
							<div class="uk-panel uk-panel-box" style="min-height: 99vh;">
								<h3>Product Promotion Detail</h3>
								<div class="uk-grid uk-grid-collapse  uk-form uk-text-center">
									<p>ชื่อ</p>
								</div>
								<div class="uk-grid uk-grid-collapse uk-form uk-text-center">
									<input type="text" class="uk-form uk-width-4-10" name="proDetailModel.name">
								</div>
								<div class="uk-grid uk-grid-collapse  uk-form uk-text-center">
									<p>ประเภทรายการ</p>
								</div>
								<div class="uk-grid uk-grid-collapse uk-form uk-text-center">
									<select class="uk-form uk-width-4-10" name="proDetailModel.discount_type" required="required" id="alltypesel">
										<option value="">กรุณาเลือก</option>
										<option value="1">ส่วนลด/บาท</option>
										<option value="2">ส่วนลด/เปอร์เซ็นต์</option>
										<option value="3">แถม</option>
									</select>
								</div>
								<div  class = "uk-grid uk-grid-collapse uk-form  alltype ">
							
								</div>
								<div class="uk-grid ">
	                            	<div class="uk-width-1-1 uk-text-center">
							 			<button class="uk-button uk-button-success   " type="submit">บันทึก</button>
	                            	</div>
<!-- 	                            	<div class="uk-width-1-2 ">
	                            		<a class="uk-button uk-button-danger " >ยกเลิก</a>
	                         		</div> -->
	                        	</div>
					</div>
					</div>
				</div>	
					</form>
					<div id="delete_promotiondetail" class="uk-modal ">
						<form action="PromotionDetailDel" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-body"><i class="uk-icon-exclamation-circle"></i> ต้องการยืนยันการลบหรือไม่</div>
				         	<div class="uk-modal-footer uk-text-right">
				         	<button type="submit" class="uk-button uk-button-success  "> ยืนยัน</button>
			                    <button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
			                    <input type="hidden" id="Productdel" name="proDetailModel.id">
			                    <input type="hidden" name="protionModel.promotion_id" value="<s:property value='protionModel.id' />">
                			</div>

					    </div>
					    </form>
					</div> 					 
			</div>	
		</div>
<script src="js/autoNumeric.min.js"></script>
		<script>
		$(document).on('click', '#btn_del', fn_buttonmodal_habndler).ready(function(){

		}).ready(function(){
	   		 $('#tbdetail').DataTable();	   		
		});;
		
		function update(id, name) { 
			 $("#hdid_up").val(id);
			 $("#id_up").val(id);
			 $("#name_up").val(name);  
		};
		function delete_group(id, name) { 
			 $("#id_de").val(id);
			 $("#name_de").val(name);  
		};
	
		function fn_buttonmodal_habndler(e)
		{
		    //get id from pressed button
		    var Productid = $(e.target).data('productdel');
		    
		    $('#delete_promotiondetail').on({
		        'uk.modal.show':function(){
		        	$("#Productdel").val(Productid);
		        },
		        'uk.modal.hide':function(){
		                    //hide modal
		        }
		    }).trigger('uk.modal.show');
		}
		
		$(document).on("change","#alltypesel",function(){			
	    	if($(this).val()== 0){
	    		$('.alltype').html('<div></div>');
	    	}else if($(this).val()== 1){
	    		var type1 = '<div class = "uk-form ridge uk-width-1-1 pd-5" >'+
	    					'<div class="bahtdis">'+
								'<div class="uk-grid uk-grid-collapse uk-form ">'+
									'<p>จำนวนเงิน</p>'+
								'</div>'+
								'<div class="uk-grid uk-grid-collapse  uk-form ">'+
									'<div class="uk-width-1-1">'+
										'<input type="text" class="uk-form uk-width-5-10 numeric" name="proDetailModel.dis_amountbaht">บาท'+
									'</div>'+											
								'</div>'+
							'</div>'+
							'<div class="">'+
							'<div class="uk-grid uk-grid-collapse uk-form ">'+
								'<p>ประเภท</p>'+
							'</div>'+
							'<div class="uk-grid uk-grid-collapse  uk-form ">'+
								'<div class="uk-width-1-1">'+
								'<select class = "uk-width-1-2" id="bigtype" name=""  >'+
									'<option value="0">เลือกประเภท</option>'+
									'<option value="1">สินค้า</option> '+
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
									'<p>จำนวนเปอร์เซ็นต์</p>'+
								'</div>'+
								'<div class="uk-grid uk-grid-collapse  uk-form ">'+
									'<div class="uk-width-1-1">'+
										'<input type="text" class="uk-form uk-width-5-10 discountPercent numeric" name="proDetailModel.dis_amountpercent">เปอร์เซ็นต์'+
									'</div>'+											
								'</div>'+
							'</div>'+
							'<div class="">'+
							'<div class="uk-grid uk-grid-collapse uk-form ">'+
								'<p>ประเภท</p>'+
							'</div>'+
							'<div class="uk-grid uk-grid-collapse  uk-form ">'+
								'<div class="uk-width-1-1">'+
								'<select class = "uk-width-1-2" id="bigtype" name=""  >'+
									'<option value="0">เลือกประเภท</option>'+
									'<option value="1">สินค้า</option> '+
									'<option value="2">การรักษา</option>'+ 
								'</select>'+
								'</div>'+											
							'</div>'+
							'</div>'+
							'<div class="typeall2">'+
							'</div>'+
							'</div>';
				$(".alltype").html(type2);
	    	}else{
	    		var type3 = '<div class = "uk-form ridge uk-width-1-1 pd-5" >'+
							'<div class="">'+
							'<div class="uk-grid uk-grid-collapse uk-form ">'+
								'<p>ประเภท</p>'+
							'</div>'+
							'<div class="uk-grid uk-grid-collapse  uk-form ">'+
								'<div class="uk-width-1-1">'+
								'<select class = "uk-width-1-2" id="bigtype" name=""  >'+
									'<option value="0">เลือกประเภท</option>'+
									'<option value="1">สินค้า</option> '+
									'<option value="2">การรักษา</option>'+ 
								'</select>'+
								'</div>'+											
							'</div>'+
							'</div>'+
							'<div class="typeall2">'+
							'</div>'+
							'</div>';	    		
	    		$(".alltype").html(type3);
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
										'<select class = "uk-width-1-2" required="required" id="treatment_type" name="proDetailModel.pro_treatmentType"  >'+
											'<option value="">เลือกประเภทการรักษา</option> '+
											'<option value="1">ทุกการรักษา</option> '+
											'<option value="2">กลุ่มการรักษา</option>'+ 
											'<option value="3">หมวดการรักษา</option>'+
											'<option value="4">รายการรักษา</option>'+  
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
									'<select style="width:33vh" id="name_treat" required="required" name="proDetailModel.pro_treatmentID"  >'+
										'<option  value="">เลือกรายการ</option>'+
									'</select>'+
									'</div>'+											
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
		});
		$(document).on("keyup",".discountPercent",function(){
			if($(this).autoNumeric('get')>101){
			    swal(
			    		  'WARNING!',
			    	      'ค่าข้อมูลไม่สามารถเกิน 100%ได้ :)',
			    	      'error'
			    	    )
			    	    $(this).val(0);  
			} 
		})	
		</script>
</body>
</html>