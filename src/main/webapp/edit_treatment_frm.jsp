<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.product.model.*" %>
<%@ page import="com.smict.all.model.*" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%@ page import="com.smict.product.data.ProductData" %>
<%
	ProductData product_Data = new ProductData(); 
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Smart Dental:Treatment</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
			<!-- Action error & messages -->
			<s:if test="%{alertError.length() > 0}">
			<div class="uk-alert uk-alert-danger" data-uk-alert>
				<li class="uk-alert-close uk-close"></li>
				<p><s:property value="alertError" /></p>
			</div>
			</s:if>
			<s:if test="%{alertSuccess.length() > 0}">
			<div class="uk-alert uk-alert-success" data-uk-alert>
				<li class="uk-alert-close uk-close"></li>
				<p><s:property value="alertSuccess" /></p>
			</div>
			</s:if>
			<s:if test="%{alertMSG.length() > 0}">
			<div class="uk-alert uk-alert-warning" data-uk-alert>
				<li class="uk-alert-close uk-close"></li>
				<p><s:property value="alertMSG" /></p>
			</div>
			</s:if>
			<s:if test="hasActionErrors()">
			   <div class="uk-alert uk-alert-danger" data-uk-alert>
		   			<li class="uk-alert-close uk-close"></li>
			      	<s:actionerror/>
			   </div>
			</s:if>
			<s:if test="hasActionMessages()">
			   <div class="uk-alert uk-alert-success" data-uk-alert>
		   			<li class="uk-alert-close uk-close"></li>
			      	<s:actionmessage/>
			   </div>
			</s:if>
			<!-- Action error & messages -->
 			<form class="uk-form" action="treatmentMaster" method="post" id="frmTreatmentMaster">
 					<% if(request.getAttribute("status_error") != null) {%>
					 <h3 class="red "><%=request.getAttribute("status_error").toString()%></h3>
					<% } %>
					<% if(request.getAttribute("status_success") != null) {%>
					 <h3 class="uk-text-success"><%=request.getAttribute("status_success").toString()%></h3>
					<% } %>
				<div class="uk-grid uk-grid-collapse">
				<div class="uk-width-1-3 padding5 ">
					<div id="tooth-table-pic" class="uk-overflow-container border-gray">
					<p class="uk-text-muted uk-width-1-1">ตัวอย่าง</p>
						<table class="tooth-table border-gray ">
						<% if(request.getAttribute("toothListUp")!=null){ 
							List toothlist = (List) request.getAttribute("toothListUp"); %>
							<tr class="tooth-pic">
						    	<%	
						    	for (Iterator iterA = toothlist.iterator(); iterA.hasNext();) {
				        			ToothModel toothModel = (ToothModel) iterA.next();
			      				%>  
			      				<td id="tooth_<%=toothModel.getTooth_num()%>">
									<img src="img/tooth/<%=toothModel.getTooth_num()%>/<%=toothModel.getTooth_num()%>.jpg" />
								</td>
								<%}  %>
							</tr>
							
							<tr class="uk-text-center">
								<%	
						    	for (Iterator iterA = toothlist.iterator(); iterA.hasNext();) {
				        			ToothModel toothModel = (ToothModel) iterA.next();
			      				%> 
			      					<td><%=toothModel.getTooth_num()%></td>
			      				<%	} %>
							</tr>
						<%	}
						%>
						</table>
						
						<table class="tooth-table border-gray ">
							<% if(request.getAttribute("toothListLow")!=null){ 
							List toothlist = (List) request.getAttribute("toothListLow"); %>
							<tr class="uk-text-center">
								<%	
						    	for (Iterator iterA = toothlist.iterator(); iterA.hasNext();) {
				        			ToothModel toothModel = (ToothModel) iterA.next();
			      				%> 
			      					<td><%=toothModel.getTooth_num()%></td>
			      				<%	} %>
							</tr>
							<tr class="tooth-pic">
						    	<%	
						    	for (Iterator iterA = toothlist.iterator(); iterA.hasNext();) {
				        			ToothModel toothModel = (ToothModel) iterA.next();
			      				%>  
			      				<td id="tooth_<%=toothModel.getTooth_num()%>">
									<img src="img/tooth/<%=toothModel.getTooth_num()%>/<%=toothModel.getTooth_num()%>.jpg" />
								</td>
								<%	} %>
							</tr>
							
							
						<%	}
						%>
						</table>
						</div>
						<input id="surf" type="hidden">
						<table class="surface-table hidden">
							<tr>
								<td></td>
								<td><button class="uk-button uk-button-small " id="B" onclick="btnFunction(this)" type="button" value="1">B</button></td>
								<td><button class="uk-button uk-button-small " id="F" onclick="btnFunction(this)" type="button" value="1">F</button></td>
								<td></td>
							</tr>
							<tr>
								<td><button class="uk-button uk-button-small "id="M" onclick="btnFunction(this)" type="button" value="1">M</button></td>
								<td><button class="uk-button uk-button-small "id="O" onclick="btnFunction(this)" type="button" value="1">O</button></td>
								<td><button class="uk-button uk-button-small "id="I" onclick="btnFunction(this)" type="button" value="1">I</button></td>
								<td><button class="uk-button uk-button-small "id="D" onclick="btnFunction(this)" type="button" value="1">D</button></td>
							</tr>
							<tr>
								<td></td>
								<td colspan="2"><button class="uk-button uk-button-small " id="L" onclick="btnFunction(this)" type="button" value="1">L</button></td>
								<td></td>
							</tr>
						</table>
						
				</div>
					<div class="uk-width-2-3 padding5 uk-form" >
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
						 	<p class="uk-text-muted uk-width-1-1">ข้อมูล</p>
							<div class="uk-width-1-3 uk-text-right">กลุ่มการรักษา : </div>
							<div class="uk-width-2-3">
								<s:select list="treatmentMap"
									headerKey="-1"
									headerValue="เลือกกลุ่มการรักษา"
									name="treatmentModel.treatmentGroupID"
									id="treatmentGroup"
									value="treatmentModel.treatmentGroupID"
								/>								
							</div>
							<div class="uk-width-1-3 uk-text-right">หมวดการรักษา : </div>
							<div class="uk-width-2-3">
								<s:select id="treatment-category2" 
									list="categoryMap"
									headerKey="-1"
									headerValue="กรุณาเลือกกลุ่มการรักษาก่อน"
									name="treatmentModel.treatmentCategoryID" 
									required="required" 
									class="uk-width-1-2" 
									value="treatmentModel.treatmentCategoryID"
								/>
							</div>
							<div class="uk-width-1-3 uk-text-right">รหัสการรักษา : </div>
							<div class="uk-width-2-3">
								<s:textfield id="treatment_code" 
									maxlength="11" 
									name="treatmentModel.treatmentCode" 
									class="uk-width-1-2" 
									pattern="[A-Za-z0-9]{6}" 
									title="กรุณาใส่รหัสให้ครบ 6 หลัก" 
									required="required" 
								/>
							</div>
							<div class="uk-width-1-3 uk-text-right">ชื่อการรักษา (ไทย) : </div>
							<div class="uk-width-2-3">
								<s:textfield id="treatment_nameth" 
									name="treatmentModel.treatmentNameTH" 
									class="uk-width-1-2" 
									required="required" 
								/>
							</div>
							<div class="uk-width-1-3 uk-text-right">ชื่อการรักษา (อังกฤษ) : </div>
							<div class="uk-width-2-3">
								<s:textfield id="treatment_nameen" 
									name="treatmentModel.treatmentNameEN" 
									class="uk-width-1-2" 
									required="required" 
								/>
							</div>
							<div class="uk-width-1-3 uk-text-right">รูปแบบ : </div>
							<div class="uk-width-2-3">
								<s:select list="toothPicMap"
									headerKey="-1"
									headerValue="เลือกรูปแบบ"
									class="uk-width-1-2"
									name="treatmentModel.toothPicCode"
									id="toothPicList"
									value="treatmentModel.toothPicCode"
								/>
							</div>
							<div class="uk-width-1-3 uk-text-right">ประเภทที่ใช้ได้: </div>
							<div class="uk-width-2-3">
								<s:checkboxlist name="treatmentModel.toothTypeIDArr" 
									list="treatmentList" 
									listKey="toothTypeID" 
									listValue="toothTypeNameEN"
									value="1"
								/>
							</div>
							<div class="uk-width-1-3 uk-text-right">ประเภทที่การรักษา: </div>
							<div class="uk-width-2-3">
							<input type="radio" 
								id="treatmentMode1" 
								name="treatmentModel.treatmentMode" 
								value="1" 
								required="required" checked 
							>
							<label for="treatmentMode1">ทั่วไป</label>
							<input type="radio" 
								id="treatmentMode2" 
								name="treatmentModel.treatmentMode" 
								value="2" 
								required="required">
							<label for="treatmentMode2">จัดฟัน</label>
							</div>
							<div class="uk-width-1-3 uk-text-right">Homecall : </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
	                                <input type="checkbox" 
	                                	name="treatmentModel.autoHomeCall" 
	                                	value="1" 
	                                	id="autoHomeCall" checked>
	                                <label for="autoHomeCall">อัตโนมัติ</label> 
                                </div>
							</div>
							<div class="uk-width-1-3 uk-text-right">รักษาซ้ำ : </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
	                                <input type="radio" 
	                                	id="isRepeat1"
	                                	name="treatmentModel.isRepeat" 
	                                	value="1" 
	                                	required="required" checked>
                                	<label for="isRepeat1">ทำได้</label> 
                                	<input type="radio" 
                                		id="isRepeat2"
                                		name="treatmentModel.isRepeat" 
                                		value="2" 
                                		required="required">
                            		<label for="isRepeat2">ทำไม่ได้</label> 
                                </div>
							</div>
							<div class="uk-width-1-3 uk-text-right">Recall : </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
	                                <input type="radio" 
	                                	id="recall1"
	                                	name="treatmentModel.recall" 
	                                	value="1" 
	                                	required="required" checked>
                                	<label for="recall1">ปกติ</label> 
                                	<input type="radio" 
                                		id="recall2"
                                		name="treatmentModel.recall" 
                                		value="2" 
                                		required="required">
                            		<label for="recall2">พิเศษ</label> 
                                </div>
							</div>
							<div class="uk-width-1-3 uk-text-right">ชุดการรักษา : </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
	                                <input type="radio" 
	                                	id="isContinue1"
	                                	name="treatmentModel.isContinue" 
	                                	value="1" 
	                                	required="required" checked>
                                	<label for="isContinue1">ปกติ</label> 
	                                <input type="radio" 
	                                	id="isContinue2"
	                                	name="treatmentModel.isContinue" 
	                                	value="2" 
	                                	required="required">
                                	<label for="isContinue2">รักษาต่อเนื่อง</label> 
                                </div>
							</div>
						</div>
					</div>
				</div> 
				<div class="uk-grid uk-grid-collapse uk-margin-large-top">
					<div class="uk-width-1-10"></div>
					<div class="uk-width-8-10">
						<h2>กำหนดค่ารักษา</h2>
					</div>
					<div class="uk-width-1-10"></div>
					<div class="uk-width-1-10"></div>
					<div class="uk-width-8-10">
						<div class="uk-accordion" data-uk-accordion>
							<s:iterator value="brandList" >
						    <h3 class="uk-accordion-title"><s:property value="brand_name" /></h3>
						    <s:hidden name="brandModel.brandIDArr" 
						    	value="%{brand_id}" 
						    	id="brand_id" 
					    	/>
						    <s:hidden name="brandModel.brandNameArr" 
						    	value="%{brand_name}" 
						    	id="brand_name" 
					    	/>
						    <div class="uk-accordion-content">
						    	<div class="uk-grid">
						    		<div class="uk-width-1-2">
						    			<label for="id1"><strong>ราคาการรักษา</strong></label><br>
						    			<s:textfield class="uk-form-large uk-form-width-large" 
						    				type="text" 
						    				id="id1" 
						    				name="treatmentModel.amountPrice"
						    				value="0" 
						    			/>
									    <s:hidden name="treatmentModel.amountPriceType" value="1" />
						    		</div>
						    		<div class="uk-width-1-2">
						    			<label for="id1"><strong>ราคาสวัสดิการ</strong></label><br>
						    			<s:textfield class="uk-form-large uk-form-width-large" 
						    				type="text" 
						    				id="id1" 
						    				name="treatmentModel.welfarePrice" 
						    				value="0" 
						    			/>
									    <s:hidden name="treatmentModel.welfarePriceType" value="2" />
						    		</div>
						    	</div>
						    </div>
							</s:iterator>
						</div>
					</div>
					<div class="uk-width-1-10"></div>
				</div>
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-container-center" > 
						<button  class="uk-button uk-button-success uk-button-large " type="submit" name="save-next">
							<i class="uk-icon-angle-right"></i> บันทึกและถัดไป
						</button>
						<button  class="uk-button uk-button-danger uk-button-large " type="submit" name="save-exit">
							<i class="uk-icon-save"></i> บันทึกและออก
						</button>
						<a href="setting.jsp" class="uk-button uk-button-danger uk-button-large">ยกเลิก</a> 
					</div>
				</div>
			</form>	
			</div>
		</div>



		<!-- MODAL ZONE -->
		<!-- Setting medicine -->
		<div id="lost" class="uk-modal">
			<div class="uk-modal-dialog uk-modal-dialog-large uk-form">
				<a class="uk-modal-close uk-close"></a>
				<div class="uk-modal-header"><i class="uk-icon-medkit"></i> ยาที่ใช้ในการรักษา</div>
				<div class="uk-width-1-1 uk-overflow-container">
					<table class="display nowrap compact stripe hover cell-border order-column" id="table_be_allergic">
						<thead>
							<tr class="hd-table">
								<th class="uk-text-center">คลิก</th>
								<th class="uk-text-center">ชื่อ</th>
								<th class="uk-text-center">ชื่อ ENG</th>
								<th class="uk-text-center">จำนวนยาที่ให้คนไข้</th>
								<th class="uk-text-center">จำนวนยาฟรี</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="uk-text-center">
									<div class="uk-form-controls">
										<input type="checkbox" name="arProduct" value="">
										<input type="hidden" name="product_id" value="" />
									</div>
								</td>
								<td class="uk-text-center product_name"></td>
								<td class="uk-text-center product_name_en"></td>
								<td class="uk-text-center"><input type="text" pattern="[0-9]{1,3}" maxlength="3" size="3" class="uk-text-right" name="product_transfer" /></td>
								<td class="uk-text-center"><input type="text" pattern="[0-9]{1,3}" maxlength="3" size="3" class="uk-text-right" name="product_free" /></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="uk-modal-footer uk-text-right">
					<button class="uk-modal-close uk-button uk-button-success" name="btn_submit_be_allergic" id="btn_submit_be_allergic">ตกลง</button>
				</div>
			</div>
		</div>
		<!-- Setting medicine -->
		
		<!-- Setting doctor -->
		<div id="settingdoctor" class="uk-modal">
			<div class="uk-modal-dialog uk-form" >
				<a class="uk-modal-close uk-close"></a>
				<div class="uk-modal-header"><i class="uk-icon-product-hunt"></i> รายชื่อแพทย์</div>
				<div class="uk-width-1-1 uk-overflow-container">
					<table class="display nowrap compact stripe hover cell-border order-column" id="table_treatment" >
						<thead>
							<tr class="hd-table">
								<th class="uk-text-center">เลือก</th>
								<th class="uk-text-center">แพทย์</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="uk-text-center"><input name="doctorid" value="" type="checkbox"></td>
								<td class="uk-text-center">TEST</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="uk-modal-footer uk-text-right">
					<button class="uk-button uk-button-success uk-modal-close">ตกลง</button>
					<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
				</div>
			</div>
		</div>
		<!-- Setting doctor -->
		<!-- MODAL ZONE -->
		<script>
		$(document).ready(function(){
			/**
			 * Load treatment category by AJAX.
			 */
			/*var groupID = $('#treatmentGroup').val();
			fetchTreatmentCategoryByAJAX(groupID);
			console.log("before");
			$("#treatment-category").find('option[value="7"]').prop('selected', true);
			console.log("after");*/


			/**
			 * Load treatment category by AJAX on group change.
			 */
			$('#frmTreatmentMaster').on('change', '#treatmentGroup', function(event) {
				event.preventDefault();
				/* Act on the event */
				var groupID = $(this).val();
				fetchTreatmentCategoryByAJAX(groupID);
			});
			
			$(document).ready(function() {
				$('#table_treatment').DataTable({
			    	// "scrollX": true,
			    	// scrollY:        '50vh',
			        // scrollCollapse: true
			    });
				$('#table_be_allergic').DataTable(); 
			});
			
			$( ".m-setting" ).addClass( "uk-active" );
		    $(".btn-reset").click(function(){
		    	$('.table-components tbody > tr:not(:first-child)').remove();
		    	$('.table-components-product tbody > tr:not(:first-child)').remove();
		    	$('.table-components-medicine tbody > tr:not(:first-child)').remove();
		    });
		    
		    $('.table-components .add-elements').on('click', function(){ 
				var clone = $(".table-components tbody tr:first-child");
				clone.find('.btn').removeClass('add-elements uk-button-success').addClass('delete-elements uk-button-danger').html('<i class="uk-icon-times"></i>');
				clone.find('td:first-child').html($('.table-components tbody tr').length + 1); 
				clone.clone().appendTo('.table-components tbody');
				clone.find('td:first-child').html(1);
				clone.find('.btn').removeClass('delete-elements uk-button-danger').addClass('add-elements uk-button-success').html('<i class="uk-icon-plus"></i>');
				
				$('.table-components tbody tr:last-child td:nth-child(2)').on('change', function(event) {
					$(this).closest("tr").find('td:nth-child(3) input').focus();
				});
			});
		    
		    $('.table-components').on( "click", ".delete-elements", function() {
				$(this).closest("tr").remove();
				var n = 0;
				$('.table-components tbody tr').each(function(){
					n++;
					$(this).find('td:first-child').html(n);
				});
			});
		    
		    $('.table-components-product .add-elements').on('click', function(){ 
				var clone = $(".table-components-product tbody tr:first-child");
				clone.find('.btnproduct').removeClass('add-elements uk-button-success').addClass('delete-elements uk-button-danger').html('<i class="uk-icon-times"></i>');
				clone.find('td:first-child').html($('.table-components-product tbody tr').length + 1); 
				clone.clone().appendTo('.table-components-product tbody');
				clone.find('td:first-child').html(1);
				clone.find('.btnproduct').removeClass('delete-elements uk-button-danger').addClass('add-elements uk-button-success').html('<i class="uk-icon-plus"></i>');
				
				$('.table-components-product tbody tr:last-child td:nth-child(2)').on('change', function(event) {
					$(this).closest("tr").find('td:nth-child(3) input').focus();
				});
			});
		    
		    $('.table-components-product').on( "click", ".delete-elements", function() {
				$(this).closest("tr").remove();
				var n = 0;
				$('.table-components-product tbody tr').each(function(){
					n++;
					$(this).find('td:first-child').html(n);
				});
			});
		    
		    $('.table-components-medicine .add-elements').on('click', function(){ 
				var clone = $(".table-components-medicine tbody tr:first-child");
				clone.find('.btnmedicine').removeClass('add-elements uk-button-success').addClass('delete-elements uk-button-danger').html('<i class="uk-icon-times"></i>');
				clone.find('td:first-child').html($('.table-components-medicine tbody tr').length + 1); 
				clone.clone().appendTo('.table-components-medicine tbody');
				clone.find('td:first-child').html(1);
				clone.find('.btnmedicine').removeClass('delete-elements uk-button-danger').addClass('add-elements uk-button-success').html('<i class="uk-icon-plus"></i>');
				
				$('.table-components-medicine tbody tr:last-child td:nth-child(2)').on('change', function(event) {
					$(this).closest("tr").find('td:nth-child(3) input').focus();
				});
			});
		    
		    $('.table-components-medicine').on( "click", ".delete-elements", function() {
				$(this).closest("tr").remove();
				var n = 0;
				$('.table-components-medicine tbody tr').each(function(){
					n++;
					$(this).find('td:first-child').html(n);
				});
			});
		    
		    $('.table-components tbody').on('change', 'input', function() {
		    	var tr = $(this).closest("tr");
		    	
		    	var val1 = tr.find('td:nth-child(2) input').val().trim();
		    	var val2 = tr.find('td:nth-child(3) input').val().trim();
		    	 
		    
			}); 
		    
		    
		    $("#btnr").click(function(){
		    	$(".rl").first().clone().appendTo(".rs"); 
		    });
		    
		    
		    $(".btnrs").click(function(){ 
		    	 
		    	 $(this).parents(".rl").remove();
		    });
		    
		    
		    $("#toothPicList").change(function(){
		    	var caseSelect =$("#toothPicList").val();
		    	<% if(request.getAttribute("toothListUp")!=null){ 
		    		
					List toothlist = (List) request.getAttribute("toothListUp"); 
					List toothlistLow = (List) request.getAttribute("toothListLow");
					toothlist.addAll(toothlistLow);
			    	for (Iterator iterA = toothlist.iterator(); iterA.hasNext();) {
	        			ToothModel toothModel = (ToothModel) iterA.next(); %>
	        			$('#tooth_<%=toothModel.getTooth_num()%>').empty();
	        			$('#tooth_<%=toothModel.getTooth_num()%>').prepend('<img src="img/tooth/<%=toothModel.getTooth_num()%>/<%=toothModel.getTooth_num()%>.jpg" />');
	        	
			        	<%if(toothModel.getB()){%>
			        	$('#tooth_<%=toothModel.getTooth_num()%>').prepend('<img class="case B hidden" src="img/tooth/<%=toothModel.getTooth_num()%>/'+caseSelect+'/B.png" />');
						<%}%>
						<%if(toothModel.getD()){%>
						$('#tooth_<%=toothModel.getTooth_num()%>').prepend('<img class="case D hidden" src="img/tooth/<%=toothModel.getTooth_num()%>/'+caseSelect+'/D.png" />');
						<%}%>
						<%if(toothModel.getL()){%>
						$('#tooth_<%=toothModel.getTooth_num()%>').prepend('<img class="case L hidden" src="img/tooth/<%=toothModel.getTooth_num()%>/'+caseSelect+'/L.png" />');
						<%}%>
						<%if(toothModel.getLi()){%>
						$('#tooth_<%=toothModel.getTooth_num()%>').prepend('<img class="case LI hidden" src="img/tooth/<%=toothModel.getTooth_num()%>/'+caseSelect+'/LI.png" />');
						<%}%>
						<%if(toothModel.getLa()){%>
						$('#tooth_<%=toothModel.getTooth_num()%>').prepend('<img class="case LA hidden" src="img/tooth/<%=toothModel.getTooth_num()%>/'+caseSelect+'/LA.png" />');
						<%}%>
						<%if(toothModel.getM()){%>
						$('#tooth_<%=toothModel.getTooth_num()%>').prepend('<img class="case M hidden" src="img/tooth/<%=toothModel.getTooth_num()%>/'+caseSelect+'/M.png" />');
						<%}%>
						<%if(toothModel.getO()){%>
						$('#tooth_<%=toothModel.getTooth_num()%>').prepend('<img class="case O hidden" src="img/tooth/<%=toothModel.getTooth_num()%>/'+caseSelect+'/O.png" />');
						<%}%>
						<%if(toothModel.getP()){%>
							$('#tooth_<%=toothModel.getTooth_num()%>').prepend('<img class="case P hidden " src="img/tooth/<%=toothModel.getTooth_num()%>/'+caseSelect+'/P.png" />');
						<%}%>
						<%if(toothModel.getI()){%>
							$('#tooth_<%=toothModel.getTooth_num()%>').prepend('<img class="case I hidden" src="img/tooth/<%=toothModel.getTooth_num()%>/'+caseSelect+'/I.png" />');
						<%}%>
						<%if(toothModel.getVn()){%>
							$('#tooth_<%=toothModel.getTooth_num()%>').prepend('<img class="case Vn hidden " src="img/tooth/<%=toothModel.getTooth_num()%>/'+caseSelect+'/Vn.png" />');
						<%}%>
						<%if(toothModel.getIN()){%>
							$('#tooth_<%=toothModel.getTooth_num()%>').prepend('<img class="case IN hidden" src="img/tooth/<%=toothModel.getTooth_num()%>/'+caseSelect+'/IN.png" />');
						<%}%>
						
		    	<%}}%>
		    	$(".surface-table").removeClass("hidden");
		    });
		    
		 	$("#treatment_type").change(function(){
		 		var treatment_type = $("#treatment_type").val();
		 		$.ajax({
			        type: "post",
			        url: "ajax/ajax-treatment-sub-group.jsp", //this is my servlet 
			        data: {treatment_type_id:treatment_type},
			        async:false, 
			        success: function(result){
			        	var obj = jQuery.parseJSON(result);
			        	$("select[name='treatmentMasterModel.treatment_group_code']").find('option').remove().end().append($('<option>').text("กรุณาเลือกหมวดการรักษา"));
			        	for(var i = 0 ;  i < obj.length;i++){ 	
			        	$("select[name='treatmentMasterModel.treatment_group_code']").append($('<option>').text(obj[i].treatment_group_name).attr('value', obj[i].treatment_group_code));
			        	}
				    } 
			     });
		 	});
		}); 

		var fetchTreatmentCategoryByAJAX = function(groupID){
				$.ajax({
					url: "ajax-get-treatment-category-by-" + groupID,
					type: 'GET',
					dataType: 'json',
				})
				.done(function(data, xhr, status) {
					console.log("success");
					/*console.log(data);
					console.log(xhr);
					console.log(status);
					alert(data.length);*/
					var opt;
					$.each(data, function(index, val) {
						 /* iterate through array or object */
						 console.log(index);
						 console.log(val);
						 opt += '<option value="'+ val.category_id +'">'+ val.category_code + ' ' + val.category_name +'</option>'
					});
					$("#treatment-category option:not(:first)").remove();
					$("#treatment-category").append(opt);
					
				})
				.fail(function(data, xhr, status) {
					console.log("error");
				})
				.always(function(data, xhr, status) {
					console.log("complete");
				});
		}

		function btnFunction(elem){
			
			 var suf = $("#surf").val();
			 var btn =  elem;
			 var x = document.getElementsByClassName(btn.id).length;
			 if(btn.value=='1'){
				 
				 suf += btn.id;
				 $("#surf").val(suf);
				 btn.value='2';
				 elem.className +=" uk-button-primary ";
				 var i=0;
				 for(i=0;i<x;i++){
					 var e = document.getElementsByClassName(btn.id)[i];
					 e.className =" ";
					 e.className +=" case "+btn.id;
				 }
				
				
			 }else if(btn.value=='2'){ 
				 var suf = suf.replace(btn.id, "");
				 $("#surf").val(suf);  
				 btn.value='1';
				 elem.className =" ";
				 elem.className +=" uk-button uk-button-small ";
				 var i=0;
				 for(i=0;i<x;i++){
					 var e = document.getElementsByClassName(btn.id)[i];
					 e.className =" ";
					 e.className +=" case "+btn.id+" hidden";
				 }
				 
			 }  
		}
		</script>		
	</body>
</html>