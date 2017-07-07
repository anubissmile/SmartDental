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
 			<form class="uk-form" action="treatment-edit" method="post" id="ldc-frm-edt-trt">
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
								<div class="uk-form-controls">
									<s:select list="treatmentMap"
										headerKey="-1"
										headerValue="เลือกกลุ่มการรักษา"
										name="treatmentModel.treatmentGroupID"
										id="treatmentGroup"
										value="treatmentModel.treatmentGroupID"
									/>	
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-right">หมวดการรักษา : </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
									<s:select id="treatment-category" 
										list="categoryMap"
										headerKey="-1"
										headerValue="กรุณาเลือกกลุ่มการรักษาก่อน"
										name="treatmentModel.treatmentCategoryID" 
										required="required" 
										class="uk-width-1-2" 
										value="treatmentModel.treatmentCategoryID"
									/>
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-right">รหัสการรักษา : </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
									<s:textfield id="treatment_code" 
										maxlength="11" 
										name="treatmentModel.treatmentCode" 
										class="uk-width-1-2" 
										pattern="[A-Za-z0-9]{6}" 
										title="กรุณาใส่รหัสให้ครบ 6 หลัก" 
										required="required" 
									/>
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-right">ชื่อการรักษา (ไทย) : </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
									<s:textfield id="treatment_nameth" 
										name="treatmentModel.treatmentNameTH" 
										class="uk-width-1-2" 
										required="required" 
									/>
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-right">ชื่อการรักษา (อังกฤษ) : </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
									<s:textfield id="treatment_nameen" 
										name="treatmentModel.treatmentNameEN" 
										class="uk-width-1-2" 
										required="required" 
									/>
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-right">รูปแบบ : </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
									<s:select list="toothPicMap"
										headerKey="-1"
										headerValue="เลือกรูปแบบ"
										class="uk-width-1-2"
										name="treatmentModel.toothPicCode"
										id="toothPicList"
										value="treatmentModel.toothPicCode"
									/>
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-right">ประเภทที่ใช้ได้: </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
									<s:checkboxlist name="treatmentModel.toothTypeIDArr" 
										list="treatmentList" 
										listKey="toothTypeID" 
										listValue="toothTypeNameEN" 
										value="%{treatmentModel.toothTypeIDArr}" 
									/>
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-right">ประเภทที่การรักษา: </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
									<s:radio id="treatmentMode1" 
										name="treatmentModel.treatmentMode" 
										value="%{treatmentModel.treatmentMode}" 
										required="required"
										list="#{1:'ทั่วไป', 2:'จัดฟัน'}"
									/>
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-right">Homecall : </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
	                                <s:checkboxlist name="treatmentModel.autoHomeCall" 
	                                	list="#{1:'อัตโนมัติ'}"
	                                	value="%{treatmentModel.autoHomeCall}" 
	                                	id="autoHomeCall" 
	                                />
                                </div>
							</div>
							<div class="uk-width-1-3 uk-text-right">รักษาซ้ำ : </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
	                                <s:radio id="isRepeat1"
	                                	list="#{1:'ทำได้', 2:'ทำไม่ได้'}"
	                                	name="treatmentModel.isRepeat" 
	                                	value="%{treatmentModel.isRepeat}" 
	                                	required="required" 
	                                />
                                </div>
							</div>
							<div class="uk-width-1-3 uk-text-right">Recall : </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
                            		<s:radio id="recall1"
                            			list="#{1:'ปกติ', 2:'พิเศษ'}"
	                                	name="treatmentModel.recall" 
	                                	value="%{treatmentModel.recall}" 
	                                	required="required" 
	                                />
                                </div>
							</div>
							<div class="uk-width-1-3 uk-text-right">ชุดการรักษา : </div>
							<div class="uk-width-2-3">
								<div class="uk-form-controls">
	                                <!-- <input type="radio" 
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
                                	<label for="isContinue2">รักษาต่อเนื่อง</label>  -->
	                                <s:radio id="isContinue2"
	                                	list="#{1:'ปกติ', 2:'รักษาต่อเนื่อง'}"
	                                	name="treatmentModel.isContinue" 
	                                	value="%{treatmentModel.isContinue}" 
	                                	required="required"
	                                />
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
						    			<s:iterator value="treatmentModel.priceListModel" var="price">
						    			<s:if test="%{#price.brandID == brand_id && #price.priceTypeID == 1}">
						    			<s:textfield class="uk-form-large uk-form-width-large" 
						    				id="id1" 
						    				name="treatmentModel.amountPrice"
						    				value="%{#price.amountP}" 
						    			/>
									    <s:hidden name="treatmentModel.amountPriceType" 
									    	value="%{#price.priceTypeID}" 
								    	/>
									    </s:if>
									    </s:iterator>
						    		</div>
						    		<div class="uk-width-1-2">
						    			<label for="id1"><strong>ราคาสวัสดิการ</strong></label><br>
						    			<s:iterator value="treatmentModel.priceListModel" var="price" >
						    			<s:if test="%{#price.brandID == brand_id && #price.priceTypeID == 2}">
						    			<s:textfield class="uk-form-large uk-form-width-large" 
						    				id="id1" 
						    				name="treatmentModel.welfarePrice" 
						    				value="%{#price.amountP}" 
						    			/>
									    <s:hidden name="treatmentModel.welfarePriceType" 
									    	value="%{#price.priceTypeID}" 
								    	/>
									    </s:if>
									    </s:iterator>
						    		</div>
						    	</div>
						    </div>
							</s:iterator>
						</div>
					</div>
					<div class="uk-width-1-10">
						<s:hidden name="triggerStatus" value="" id="ldc-trig-stat" />
						<s:hidden name="treatmentModel.treatmentID" id="ldc-traet-id" />
					</div>
				</div>
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-container-center" > 
						<a class="uk-button uk-button-success uk-button-large ldc-call-conf-modal" 
							type="submit" 
							name="save-next" 
							data-uk-modal="{target:'#conf_chng'}">
							<i class="uk-icon-angle-right"></i> บันทึกและถัดไป
						</a>
						<a class="uk-button uk-button-danger uk-button-large ldc-call-conf-modal" 
							type="submit" 
							name="save-exit" 
							data-uk-modal="{target:'#conf_chng'}">
							<i class="uk-icon-save"></i> บันทึกและออก
						</a>
						<a href="setting.jsp" class="uk-button uk-button-danger uk-button-large">ยกเลิก</a> 
					</div>
				</div>
			</form>	
			</div>
		</div>



		<!-- MODAL ZONE -->
		<!-- Setting medicine -->
		<div id="conf_chng" class="uk-modal">
			<div class="uk-modal-dialog uk-modal-dialog-large uk-form">
				<a class="uk-modal-close uk-close"></a>
				<div class="uk-modal-header">
					<h2><i class="uk-icon-medkit"></i> <strong>โปรดยืนยันการเปลี่ยนแปลง</strong></h2>
				</div>
				<div class="uk-width-1-1 uk-overflow-container uk-panel">
					<div class="uk-grid uk-margin-remove">
						<a class="uk-width-1-2 uk-panel-hover uk-text-center" tabindex="2" id="ldc-modal-conf">
							<h1>
								<strong><i class="uk-icon-check-circle-o"></i><br><span>ยืนยัน</span></strong>
							</h1>
						</a>
						<a class="uk-width-1-2 uk-panel-hover uk-text-center" tabindex="1" id="ldc-modal-cancel">
							<h1>
								<strong><i class="uk-icon-times-circle-o"></i><br><span>ยกเลิก</span></strong>
							</h1>
						</a>
					</div>
				</div>
			</div>
		</div>
		<!-- Setting medicine -->
		<!-- MODAL ZONE -->
		<script>
		$(document).ready(function(){
			/**
			 * Create modal listener.
			 */
			modalListenerCreate(
				"#conf_chng",
				function(){
					$("#ldc-trig-stat").val("conf");
					$("#ldc-frm-edt-trt").trigger('submit');
				}, 
				function(){
					$("#ldc-trig-stat").val("cancel");
					$("#ldc-frm-edt-trt").trigger('submit');
				}
			);

			/**
			 * Set input form activity.
			 */
			onInputFocus(
				'input[type="text"]', 
				function(obj){
					obj.select();
				}
			);

			/**
			 * Load treatment category by AJAX on group change.
			 */
			$('#ldc-frm-edt-trt').on('change', '#treatmentGroup', function(event) {
				event.preventDefault();
				/* Act on the event */
				var groupID = $(this).val();
				fetchTreatmentCategoryByAJAX(groupID);
			});

			/**
			 * Data table
			 */
			$('#table_treatment').DataTable({
		    	// "scrollX": true,
		    	// scrollY:        '50vh',
		        // scrollCollapse: true
		    });
			$('#table_be_allergic').DataTable(); 
			
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

		var onInputFocus = function(obj, callBack){
			$("form").on('focus', obj, function(event) {
				event.preventDefault();
				callBack($(this));
			});
		}

		var modalListenerCreate = function(obj, callBackSuccess, callBackError){
			var mod = UIkit.modal(obj);
			$(obj).on('click', '#ldc-modal-conf', function(event) {
				event.preventDefault();
				callBackSuccess();
				if(mod.isActive()){
					mod.hide();
				}
			});

			$(obj).on('click', '#ldc-modal-cancel', function(event) {
				event.preventDefault();
				callBackError();
				if(mod.isActive()){
					mod.hide();
				}
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