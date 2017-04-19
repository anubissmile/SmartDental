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
				<%@include file="nav-top.jsp" %>
					<div class="uk-grid"></div>
					<form id="service" action="addPromotionDetailInsert" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class=" uk-grid ">
						<div class="uk-width-7-10 ">
							<div class="uk-panel uk-panel-box">
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
									
								</div>
									<div class="uk-width-1-1 uk-overflow-container uk-form">
									<table id="tbdetail" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									            <th class="uk-text-center">ชื่อ</th>
									            <th class="uk-text-center">รายการ</th>
									            <th class="uk-text-center">ประเภท</th> 
									            <th class="uk-text-center">ประเภทรายละเอียด</th> 
									            <th class="uk-text-center">บาท</th>
									            <th class="uk-text-center">เปอร์เซ็น</th>
									            <th></th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="promotiondetailModel">
									    	<tr>
									    	
									    		<td class="uk-text-center"><s:property value="name"/></td>
									    		<td class="uk-text-center"><s:property value="product_type"/></td>
									    		<td class="uk-text-center"><s:property value="type"/></td>
									    		<td class="uk-text-center"><s:property value="tname"/></td>
									    		<td class="uk-text-center"><s:property value="discount_baht"/></td>
									    		<td class="uk-text-center"><s:property value="discount_percent"/></td>
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
						<div class="uk-width-3-10" style = "padding-left: 6px;">
							<div class="uk-panel uk-panel-box">
								<h3>Product Promotion Detail</h3>
								<div class="uk-grid uk-grid-small uk-form uk-text-center">
									<p>ชื่อ&nbsp;</p>
										<input type="text" class="uk-form-small uk-width-4-10" name="proDetailModel.name">
								</div><br>
								<div  class = "uk-form">
								<div class = "uk-form ridge" >
								<p>ส่วนลด</p>
									<input name="proDetailModel.product_type" value="ส่วนลด" type="radio" checked>
									<input type="text" class="uk-form-small uk-width-3-10 " name="proDetailModel.discount_baht">
									บาท
									<br><br>
									<input name="proDetailModel.product_type" value="ส่วนลด" type="radio">
									<input type="text" class="uk-form-small uk-width-3-10" name="proDetailModel.discount_percent">
									%<br><br>
									<label><input type="checkbox">	เลือกรายการ</label><br>
									<p>ประเภท
										<select class = "uk-width-1-2" id="product_type" name="proDetailModel.type" required >
										<option value="">เลือกประเภท</option> 
										<option value="1">ยา / Medicine</option> 
										<option value="2">สินค้า / Product</option> 
										<option value="3">การรักษา / treatment</option> 
										</select></p>									
									<p>รายการ
										<select class = "uk-width-1-2" id="name" name="proDetailModel.product_id" required >
										<option  value="">เลิอกรายการ</option>
										</select></p>								
								</div><br>
								<div class = "uk-form ridge">
								<p><input name="proDetailModel.product_type" value="แถม"  type="radio">	แถม</p>
								<div class = "uk-width-1-1">
									ประเภท
								<select class = "uk-width-1-2" id="product_type_free" name="proDetailModel.type" required  disabled>
									<option value="">เลือกประเภท</option> 
									<option value="1">ยา / Medicine</option> 
									<option value="2">สินค้า / Product</option> 
									<option value="3">การรักษา / treatment</option> 
								</select>
								
								</div><br>
								<div class = "uk-width-1-1">
								รายการ
									<select class = "uk-width-1-2" id="name_free" name="proDetailModel.product_id" required disabled>
									<option  value="">เลิอกรายการ</option>
									</select>
								</div>
								</div><br>
							</div>
							<div class="uk-grid">
	                            	<div class="uk-width-1-2">
							 			<button class="uk-button uk-button-success uk-align-right  " type="submit">บันทึก</button>
	                            	</div>
	                            	<div class="uk-width-1-2">
	                            		<button class="uk-button uk-button-danger " type="reset">ยกเลิก</button>
	                            	</div>
	                            	</div>
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
			                    <button class="uk-button uk-button-default uk-modal-close">ยกเลิก</button>
			                    <input type="hidden" id="Productdel" name="proDetailModel.id"><button type="submit" class="uk-button uk-button-default uk-button-danger"> ยืนยัน</button>
                			</div>

					    </div>
					    </form>
					</div> 					 
			</div>	
		</div>

		<script>
		$(document)

		.on('click', '#btn_del', fn_buttonmodal_habndler).ready(function(){
			$( ".m-setting" ).addClass( "uk-active" );
			
		
			
			$("#deleteg").click(function(){
				$("#service").submit();
			}); 
			$("#updateg").click(function(){
				$("#service").submit();
			}); 
			
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
			  	}
		  	});
			
			
			$("#name_free").select2({
				ajax: {
				    url: "ajax/getPartner.jsp",
				    delay: 1000,
				    data: function (params) {
				      return {
				        q: params.term, // search term
				        productType:$('#product_type_free').val()
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
			  	}
		  	});
			
			
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
		    console.log(Productid);
		    $('#delete_promotiondetail').on({
		        'uk.modal.show':function(){
		        	$("#Productdel").val(Productid);
		        },
		        'uk.modal.hide':function(){
		                    //hide modal
		        }
		    }).trigger('uk.modal.show');
		}
		
		
		
		
		
		
		
		
		
		</script>
	
<div class="swal2-container"><div class="swal2-overlay" tabindex="-1"></div><div class="swal2-modal" style="display: none" tabindex="-1"><div class="swal2-icon swal2-error"><span class="x-mark"><span class="line left"></span><span class="line right"></span></span></div><div class="swal2-icon swal2-question">?</div><div class="swal2-icon swal2-warning">!</div><div class="swal2-icon swal2-info">i</div><div class="swal2-icon swal2-success"><span class="line tip"></span> <span class="line long"></span><div class="placeholder"></div> <div class="fix"></div></div><img class="swal2-image"><h2></h2><div class="swal2-content"></div><input class="swal2-input"><select class="swal2-select"></select><div class="swal2-radio"></div><label for="swal2-checkbox" class="swal2-checkbox"><input type="checkbox" id="swal2-checkbox"></label><textarea class="swal2-textarea"></textarea><div class="swal2-validationerror"></div><hr class="swal2-spacer"><button class="swal2-confirm">OK</button><button class="swal2-cancel">Cancel</button><span class="swal2-close">×</span></div></div></body>
	</body>
</html>