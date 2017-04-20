<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : โปรโมชั่น</title>
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
					<form id="service" action="addPromotion" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
						</div>
					</div>
					<div class=" ">
						<div class="uk-width-1-1 ">
							<div class="uk-panel uk-panel-box">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">list</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> โปรโมชั่น
								    <div class="uk-form-icon uk-width-4-10">
	                            		<button class="uk-button uk-button-success " type="submit" name="save">เพื่มโปรโมชั่น</button>
	                            	</div>
								    </h3>
								</div>
									<div class="uk-width-10-10 uk-overflow-container uk-form">
									<table id="listpromotiontable" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ชื่อโปรโมชั่น</th>
									            <th class="uk-text-center">วันที่</th>
									            <th class="uk-text-center">รายละเอียด</th> 
									            <th></th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="promotionModel">									    	
									    	<tr>
									    		<td><s:property value="name"/></td>
									    		<td class="uk-text-center"><s:property value="start_date"/> - <s:property value="end_date"/></td>
									    		<td>
									    			<s:iterator value="promotiondetailModel"  status="stat">
									    				<s:if test="#stat.count>1">
										    				<hr>
										    			</s:if>	
									    				<b><s:property  value="product_type"/></b>
									    				 <s:if test="discount_baht != '' & product_type == 'ส่วนลด'">
										    				<s:property  value="discount_baht"/>
										    				บาท
										    			</s:if>
										    			<s:elseif test="discount_percent != '' & product_type == 'ส่วนลด'">
							                                <s:property  value="discount_percent"/>
							                                	เปอร์เซนต์
							                            </s:elseif>	
									    				<s:property  value="type"/>
																							    				
												</s:iterator>
									    		</td>
									    		<td class="uk-text-right"><a href="getPromotionDetailList?pro_id=<s:property  value="promotion_id"/>" class="uk-button uk-button-primary uk-button-small">
									    			<i class="uk-icon-pencil"></i> แก้ไข</a>
									    			<a href="#delete_promotion" id="btn_del" class="uk-button uk-button-danger uk-button-small" data-Promotiondel='<s:property value="promotion_id"/>' data-uk-modal>
									    			<i class="uk-icon-eraser"></i> ลบ</a>
									    		</td>
									    	</tr>
						    				</s:iterator>			    
									    </tbody>   
									</table>
									</div>
							</div>
						</div> 
					</div>

				</div>	
					</form>
					<div id="delete_promotion" class="uk-modal ">
						<form action="PromotionDel" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-body"><i class="uk-icon-exclamation-circle"></i> ต้องการยืนยันการลบหรือไม่</div>
				         	<div class="uk-modal-footer uk-text-right">
			                    <button class="uk-button uk-button-default uk-modal-close">ยกเลิก</button>
			                    <input type="hidden" id="PromotionDel" name="protionModel.promotion_id"><button type="submit" class="uk-button uk-button-default uk-button-danger"> ยืนยัน</button>
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
			    var Promotionid = $(e.target).data('promotiondel');
			    console.log(Promotionid);
			    $('#delete_promotion').on({
			        'uk.modal.show':function(){
			        	$("#PromotionDel").val(Promotionid);
			        },
			        'uk.modal.hide':function(){
			                    //hide modal
			        }
			    }).trigger('uk.modal.show');
			}
			$(document).ready(function(){
			    $('#listpromotiontable').DataTable();
			});
		</script>
	
<div class="swal2-container"><div class="swal2-overlay" tabindex="-1"></div><div class="swal2-modal" style="display: none" tabindex="-1"><div class="swal2-icon swal2-error"><span class="x-mark"><span class="line left"></span><span class="line right"></span></span></div><div class="swal2-icon swal2-question">?</div><div class="swal2-icon swal2-warning">!</div><div class="swal2-icon swal2-info">i</div><div class="swal2-icon swal2-success"><span class="line tip"></span> <span class="line long"></span><div class="placeholder"></div> <div class="fix"></div></div><img class="swal2-image"><h2></h2><div class="swal2-content"></div><input class="swal2-input"><select class="swal2-select"></select><div class="swal2-radio"></div><label for="swal2-checkbox" class="swal2-checkbox"><input type="checkbox" id="swal2-checkbox"></label><textarea class="swal2-textarea"></textarea><div class="swal2-validationerror"></div><hr class="swal2-spacer"><button class="swal2-confirm">OK</button><button class="swal2-cancel">Cancel</button><span class="swal2-close">×</span></div></div></body>
	</body>
</html>