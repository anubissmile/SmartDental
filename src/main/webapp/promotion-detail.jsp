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
					<form id="service" action="detail" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class=" uk-grid ">
						<div class="uk-width-7-10 ">
							<div class="uk-panel uk-panel-box">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">โปรโมชั่น</div>
                                <div class="uk-panel-header">
                                <h3 class="uk-panel-title"> โปรโมชั่น ..........
								    </h3>
								    <h3 class="uk-panel-title"> วันที่ ... ถึง ...
								    </h3>
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> รายละเอียดโปรโมชั่น
								    </h3>
									
								</div>
									<div class="uk-width-10-10 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ID</th>
									            <th class="uk-text-center">ชื่อ</th>
									            <th class="uk-text-center">ประเภท</th> 
									            <th class="uk-text-center">รายละเอียด 1</th>
									            <th class="uk-text-center">รายละเอียด 2</th>
									            <th class="uk-text-center">จำนวน</th>
									            <th class="uk-text-center"></th>
									            <th></th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="proModel">
									    	<tr>
									    		<td><s:property value="product_name"/></td>
									    		<td class="uk-text-right"><s:property value="price"/></td>
									    		<td class="uk-text-center"><s:property value="productunit_name"/></td>
									    		<td class="uk-text-center"><s:property value="producttype_name"/></td>
									    		<td class="uk-text-center"><s:property value="productgroup_name"/></td>
									    		<td class="uk-text-center"><a href="getMaterialDetail?pro_id=<s:property value="product_id"/>" class="uk-button uk-button-primary uk-button-small"> แก้ไข</a>
									    			<a href="#delete_product" id="btn_del" class="uk-button uk-button-danger uk-button-small" data-Productdel='<s:property value="product_id"/>' data-uk-modal>
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
										<input type="text" class="uk-form-small uk-width-4-10" name="patModel.firstname_th">
								</div><br>
								<div  class = "uk-form">
								<div class = "uk-form ridge" >
								<p>ส่วนลด</p>
									<input name="test1" type="radio" checked>
									<input type="text" class="uk-form-small uk-width-3-10 " name="patModel.firstname_th">
									บาท
									<br><br>
									<input name="test1" type="radio">
									<input type="text" class="uk-form-small uk-width-3-10" name="patModel.firstname_th">
									%<br><br>
									<label><input type="checkbox">	เลือกรายการ</label><br>
									<p>ประเภท
									<s:select cssClass="uk-width-1-2" list="protypeList" name="productModel.producttype_id"
								      	  required="true" headerKey="" headerValue = "กรุณาเลือก"/>
									</p>									
									<p>รายการ
									<input type="text" class="uk-form-small " name="patModel.firstname_th">
									</p>								
								</div><br>
								<div class = "uk-form ridge">
								<p><input name="test1" type="radio">	แถม</p>
								<div class = "uk-width-1-1">
									ประเภท
								<s:select cssClass="uk-width-1-2" list="protypeList" name="productModel.producttype_id"
								      	  required="true" headerKey="" headerValue = "กรุณาเลือก"/>
								</div><br>
								<div class = "uk-width-1-1">
								รายการ
									<input type="text" class="uk-form-small " name="patModel.firstname_th">
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
					<div id="delete_product" class="uk-modal ">
						<form action="MaterialDel" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-body"><i class="uk-icon-exclamation-circle"></i> ต้องการยืนยันการลบหรือไม่</div>
				         	<div class="uk-modal-footer uk-text-right">
			                    <button class="uk-button uk-button-default uk-modal-close">ยกเลิก</button>
			                    <input type="hidden" id="Productdel" name="productModel.product_id"><button type="submit" class="uk-button uk-button-default uk-button-danger"> ยืนยัน</button>
                			</div>
					    </div>
					    </form>
					</div> 					 
			</div>	
		</div>

		<script>
		$(document).on('click', '#btn_del', fn_buttonmodal_habndler).ready(function(){
			$( ".m-setting" ).addClass( "uk-active" );
			 
			
			$("#deleteg").click(function(){
				$("#service").submit();
			}); 
			$("#updateg").click(function(){
				$("#service").submit();
			}); 
			
		});
		
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
		    $('#delete_product').on({
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