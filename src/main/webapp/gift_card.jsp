<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : Gift Card</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				
				<%@include file="backend-lab-top.jsp" %>

					<div class="uk-grid"></div>
					<form id="service" action="labModeMaster" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid uk-grid-collapse">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Gift Card</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> Gift Card
								    <span class="uk-form-icon uk-width-2-10  uk-text-right">
	                            		<a href="create_giftcard.jsp" class="uk-button uk-button-success" >เพิ่ม Gift Card</a>
	                            	</span>
								    </h3>
								</div>
								<div class="uk-width-1-1 uk-overflow-container uk-form">
									<div class="uk-width-1-1 uk-margin-medium-bottom">
			 					<ul class="uk-tab" data-uk-switcher="{
			 							connect:'#Gift-active',
			 							animation: 'fade'
			 						}">
								    <li class="uk-active"><a href="#">Active</a></li>
								    <li><a href="#">Inactive</a></li>
								</ul>
			 				</div>
			 				<ul class="uk-width-1-1 uk-switcher" id="Gift-active">  
							 	<li class="uk-active">
									<table id="tb-ac" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ชื่อชุด Gift Card</th>
									            <th class="uk-text-center">จำนวนเงินภายในบัตร</th>
									            <th class="uk-text-center">จำนวน Gift Card ทั้งหมด</th> 
									            <th class="uk-text-center">วันเริ่มใช้งาน</th>
									            <th class="uk-text-center">วันหมดอายุ</th>
									            <th></th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="getgiftcardlist">
									    	<s:if test="giftcard_status == \"t\"">
									    	<tr>
									    		<td class="uk-text-center"><s:property value="giftcard_name"/></td>
									    		<td class="uk-text-center"><s:property value="getText('{0,number,#,##0.00}',{giftcard_default_amount})"/> บาท</td>
									    		<td class="uk-text-center"><s:property value="giftcard_run_count"/> ใบ</td>
									    		<td class="uk-text-center"><s:property value="giftcard_start_date"/></td>
									    		<td class="uk-text-center"><s:property value="giftcard_expiredate"/></td>
									    		<td class="uk-text-right"><a href="getProductDetail?pro_id=<s:property  value="product_id"/>" class="uk-button uk-button-primary uk-button-small">
									    			<i class="uk-icon-pencil"></i> แก้ไข</a>
									    			<a href="#delete_product" id="btn_del" class="uk-button uk-button-danger uk-button-small" data-Productdel='<s:property value="product_id"/>' data-uk-modal>
									    			<i class="uk-icon-eraser"></i> ลบ</a>
									    		</td>
									    	</tr>
									    	</s:if>
						    				</s:iterator>			    
									    </tbody>   
									</table>
									</li>
									<li >
									<table id="tb-in" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ชื่อชุด Gift Card</th>
									            <th class="uk-text-center">จำนวนเงินภายในบัตร</th>
									            <th class="uk-text-center">จำนวน Gift Card ทั้งหมด</th> 
									            <th class="uk-text-center">วันเริ่มใช้งาน</th>
									            <th class="uk-text-center">วันหมดอายุ</th>
									            <th></th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="getgiftcardlist">
									    	<s:if test="giftcard_status== \"f\"">
									    	<tr>
									    		<td class="uk-text-center"><s:property value="giftcard_name"/></td>
									    		<td class="uk-text-center"><s:property value="getText('{0,number,#,##0.00}',{giftcard_default_amount})"/> บาท</td>
									    		<td class="uk-text-center"><s:property value="giftcard_run_count"/> ใบ</td>
									    		<td class="uk-text-center"><s:property value="giftcard_start_date"/></td>
									    		<td class="uk-text-center"><s:property value="giftcard_expiredate"/></td>
									    		<td class="uk-text-right"><a href="getProductDetail?pro_id=<s:property  value="product_id"/>" class="uk-button uk-button-primary uk-button-small">
									    			<i class="uk-icon-pencil"></i> แก้ไข</a>
									    			<a href="#delete_product" id="btn_del" class="uk-button uk-button-danger uk-button-small" data-Productdel='<s:property value="product_id"/>' data-uk-modal>
									    			<i class="uk-icon-eraser"></i> ลบ</a>
									    		</td>
									    	</tr>
									    	</s:if>
						    				</s:iterator>			    
									    </tbody>   
									</table>
									</li>
									</ul>
									</div>
							</div>
						</div>
					</div>

					 
					<div id="update" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-pencil"></i> แก้ไข</div>
					         <div class="uk-grid uk-grid-small"> 
					         	<div class="uk-width-2-10">
					         		<input class="uk-width-1-1 uk-text-center" type="text" id="id_up" name="id_up" autofocus="autofocus"> 
					         		<input type="hidden" id="hdid_up" name="hdid_up" >
					         	</div>
					         	<div class="uk-width-8-10 uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="name_up" name="name_up" > 
					         	</div>
					         </div>	  
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-button uk-button-success" id="updateg" name="updateg">ตกลง</button>
					         	<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button> 
					         </div>
					    </div>
					</div>
					
					<div id="delete_group" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-eraser"></i> ลบ</div>
					         	<p class="uk-text-danger">ท่านต้องการลบข้อมูลใช่หรือไม่!</p>
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input  class="uk-width-1-1 hidden" type="text" id="id_de" name="id_de" readonly> 
					         	<input  class="uk-width-1-1 " type="text" id="code" name="code" readonly> 
					         	</div>
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="name_de" name="name_de" readonly> 
					         	</div>
					         	 
					         <div class="uk-modal-footer uk-text-right"> 
					         	<button class="uk-button uk-button-success" id="deleteg" name="deleteg">ตกลง</button>
					         	<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
					         </div>
					    </div>
					</div>
				</div>	
					</form> 
			</div>
					
					
		</div>

		<script>
			$(document).ready(function(){
				$("#tb-ac").dataTable();
				$("#tb-in").dataTable();
				
			});
			
			function update(id, code, name) { 
				 $("#hdid_up").val(id);
				 $("#id_up").val(code);
				 $("#name_up").val(name);  
			};
			function delete_group(id, name,code) { 
				 $("#id_de").val(id);
				 $("#name_de").val(name); 
				 $("#code").val(code);
			};
			
			
			
		</script>
	</body>
</html>