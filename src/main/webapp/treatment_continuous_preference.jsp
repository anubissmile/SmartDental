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
 			<s:form class="uk-form" 
 				action="add-medicine-into-treatment-master" 
 				method="post" 
 				theme="simple"
 				id="frmTreatmentMaster">
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-width-1-1 uk-margin-large"></div>
					<div class="uk-width-1-10"></div>
					<div class="uk-width-8-10">
						<div class="uk-grid uk-grid-collapse">
							<div class="uk-width-1-1">
								<h2>ตั้งค่าการรักษาแบบต่อเนื่อง</h2><br>
							</div>
							<div class="uk-width-1-1 uk-text-left">
								<strong class="uk-form-label">จำนวนระยะการรักษา</strong><br>
								<s:textfield class="uk-form-large uk-text-center p-volumn" 
									theme="simple"
									name=""
									value="0" 
									id="ldc-txt-treat-num"
								/>
								<a class="uk-button uk-button-primary uk-button-large" 
									id="ldc-btn-add-elem">
									<i class="uk-icon-refresh"></i>
								</a>
							</div>
							<div class="uk-width-1-1 uk-margin-top" id="ldc-wrap-accordion">
								<!-- Accordion -->
								<div class="uk-accordion" 
									id="ldc-accordion"
									data-uk-observe >
									<h3 class="uk-accordion-title">ระยะการรักษา #1</h3>
									<div class="uk-accordion-content" id="ldc-acc-content">
										<div class="uk-grid uk-grid-collapse">
											<!-- Start setting price form -->
											<div class="uk-width-1-5">
												<strong class="uk-form-label">จำนวนรอบการรักษา</strong><br>
												<small class="i-count">#1</small>
												<div class="uk-form-icon">
													<i class="uk-icon-stethoscope"></i>
													<s:textfield class="uk-form-small uk-text-center p-volumn" 
														theme="simple"
														name=""
														value="0" 
													/>
												</div>
											</div>
											<div class="uk-width-1-5 uk-padding-remove-horizontal">
												<strong class="uk-form-label">ราคา</strong><br>
												<div class="uk-form-icon">
													<i class="uk-icon-money"></i>
													<s:textfield class="uk-form-small uk-text-right p-volumn" 
														theme="simple"
														name=""
														value="0" 
													/>
												</div>
											</div>
											<div class="uk-width-1-5 uk-text-center">
												<br><i class="uk-icon-expand"></i>&nbsp;&nbsp;&nbsp;<Strong>หรือช่วงราคา</Strong>
											</div>
											<div class="uk-width-1-5">
												<strong class="uk-form-label">จาก</strong><br>
												<div class="uk-form-icon">
													<i class="uk-icon-money"></i>
													<s:textfield class="uk-form-small uk-text-right p-volumn" 
														theme="simple"
														name=""
														value="0" 
													/>
												</div>
											</div>
											<div class="uk-grid-divider"></div>
											<div class="uk-width-1-5">
												<strong class="uk-form-label">ถึง</strong><br>
												<div class="uk-form-icon">
													<i class="uk-icon-money"></i>
													<s:textfield class="uk-form-small uk-text-right p-volumn" 
														theme="simple"
														name=""
														value="0" 
													/>
												</div>
											</div>
											<!-- End setting price form -->
											<!-- Start setting med & treatment form -->
											<div class="uk-width-1-1">
												<hr class="uk-grid-divider">
											</div>
											<div class="uk-width-1-1">
												<div class="uk-grid uk-grid-collapse uk-margin-medium-top uk-grid-divider">
													<div class="uk-width-1-2 uk-padding-large">
														<div class="uk-grid uk-grid-collapse">
															<div class="uk-width-1-2">
																<h2>รายการยา</h2>
															</div>
															<div class="uk-width-1-2 uk-text-right">
																<a data-uk-modal="{target : '#modal-med'}" 
																	class="uk-button uk-button-success">
																	<l class="uk-icon-plus"></l>
																</a>
															</div>
														</div>
														<table class="uk-table uk-table-condensed">
															<thead>
																<tr>
																	<th class="uk-text-center">#</th>
																	<th class="uk-text-center">ยา</th>
																	<th class="uk-text-center">จำนวนที่ให้</th>
																	<th class="uk-text-center">จำนวนยาฟรี</th>
																</tr>
															</thead>
															<tfoot>
																<tr>
																	<td class="uk-text-center">#</td>
																	<td class="uk-text-center">ยา</td>
																	<td class="uk-text-center">จำนวนที่ให้</td>
																	<td class="uk-text-center">จำนวนยาฟรี</td>
																</tr>
															</tfoot>
															<tbody id="treatment-med-list">
																<tr class="data-row" id="instance-elem">
																	<td class="uk-text-center num-list">0</td>
																	<td class="uk-text-left">
																		<strong class="p-name"></strong><br>
																		<small class="p-name-en"></small>
																		<s:hidden value="#" 
																			name="productModel.product_id_arr"
																			class="p-id-val"
																			theme="simple"
																		/>
																	</td>
																	<td class="uk-text-center">
																		<s:textfield class="uk-form-width-mini uk-text-center p-volumn" 
																			theme="simple"
																			name="productModel.product_volumn"
																			value="0" 
																		/>
																	</td>
																	<td class="uk-text-center">
																		<s:textfield class="uk-form-width-mini uk-text-center p-volumn-free" 
																			theme="simple"
																			name="productModel.product_volumn_free"
																			value="0" 
																		/>
																	</td>
																</tr>
															</tbody>
														</table>
													</div>
													<div class="uk-width-1-2 uk-padding-large">
														<div class="uk-grid uk-grid-collapse">
															<div class="uk-width-1-2">
																<h2>รายการการรักษา</h2>
															</div>
															<div class="uk-width-1-2 uk-text-right">
																<a data-uk-modal="{target : '#modal-treat'}" class="uk-button uk-button-success">
																	<l class="uk-icon-plus"></l>
																</a>
															</div>
														</div>
														<table class="uk-table uk-table-condensed">
															<thead>
																<tr>
																	<th class="uk-text-center">#</th>
																	<th class="uk-text-center">การรักษา</th>
																</tr>
															</thead>
															<tfoot>
																<tr>
																	<td class="uk-text-center">#</td>
																	<td class="uk-text-center">การรักษา</td>
																</tr>
															</tfoot>
															<tbody id="treatment-med-list">
																<tr class="data-row" id="instance-elem">
																</tr>
															</tbody>
														</table>
													</div>
												</div>
											</div>
											<!-- End setting med & treatment form -->
										</div>
									</div>
								</div>
								<!-- Accordion -->
							</div>
							<div class="uk-width-1-1 uk-margin-medium-top uk-text-right">
								<button type="sucmit" class="uk-button uk-button-success"> 
									<i class="uk-icon-medkit"></i> บันทึก
								</button >
								<a href="" class="uk-button"> 
									<i class="uk-icon-sign-out"></i> ออก
								</a>
							</div>
						</div>
					</div>
					<div class="uk-width-1-10"></div>
					<div class="uk-width-1-1 uk-margin-large"></div>
				</div>
			</s:form>
			</div>
		</div>



		<!-- MODAL ZONE -->
		<!-- Setting treatment -->
		<div id="modal-treat" class="uk-modal">
			<div class="uk-modal-dialog uk-modal-dialog-large uk-form">
				<a class="uk-modal-close uk-close"></a>
				<div class="uk-modal-header"><i class="uk-icon-stethoscope"></i> การรักษา</div>
				<form action="" id="treatment-listmodal">
					<div class="uk-width-1-1 uk-overflow-container">
						<table class="display nowrap compact stripe hover cell-border order-column" 
							id="treatment-datatable">
							<thead>
								<tr class="hd-table treat-table">
									<th class="uk-text-center">#</th>
									<th class="uk-text-center">รหัส</th>
									<th class="uk-text-center">การรักษา</th>
								</tr>
							</thead>
							<tbody id="treat-list" data-treatment-id='<s:property value="treatmentModel.treatmentID" />' >
								<s:iterator value="treatmentList">
								<tr>
									<td class="uk-text-center uk-width-1-10">
										<s:checkbox name="treatmentModel.treatmentID" 
											fieldValue="%{treatmentID}(#:)%{treatmentNameTH}(#:)%{treatmentNameEN}(#:)%{treatmentCode}" 
											theme="simple" 
										/>
									</td>
									<td class="uk-text-center trat_code uk-width-2-10">
										<strong><s:property value="treatmentCode" /></strong>
									</td>
									<td class="uk-text-center treat_name uk-width-7-10">
										<strong><s:property value="treatmentNameTH" /></strong>
										<br><small><s:property value="treatmentNameEN" /></small>
									</td>
								</tr>		
								</s:iterator>
							</tbody>
						</table>
					</div>
					<div class="uk-modal-footer uk-text-right">
						<button class="uk-modal-close uk-button uk-button-success" 
							name="btn_submit_be_allergic" 
							id="btn_submit_be_allergic">
							ตกลง
						</button>
					</div>
				</form>
			</div>
		</div>
		<!-- Setting treatment -->
		<!-- Setting medicine -->
		<div id="modal-med" class="uk-modal">
			<div class="uk-modal-dialog uk-modal-dialog-large uk-form">
				<a class="uk-modal-close uk-close"></a>
				<div class="uk-modal-header"><i class="uk-icon-medkit"></i> ยาที่ใช้ในการรักษา</div>
				<form action="" id="product-listmodal">
					<div class="uk-width-1-1 uk-overflow-container">
						<table class="display nowrap compact stripe hover cell-border order-column" 
							id="med-datatable">
							<thead>
								<tr class="hd-table med-table">
									<th class="uk-text-center">ทั้งหมด</th>
									<th class="uk-text-center">ยา/สินค้า</th>
								</tr>
							</thead>
							<tbody id="med-list" data-treatment-id='<s:property value="treatmentModel.treatmentID" />' >
								<s:iterator value="productList">
								<tr>
									<td class="uk-text-center uk-width-1-10">
										<s:checkbox name="productModel.product_id_arr" 
											fieldValue="%{product_id}(#:)%{product_name}(#:)%{product_name_en}" 
											theme="simple" 
										/>
									</td>
									<td class="uk-text-center product_name uk-width-9-10">
										<strong><s:property value="product_name" /></strong>
										<br><small><s:property value="product_name_en" /></small>
									</td>
								</tr>		
								</s:iterator>
							</tbody>
						</table>
					</div>
					<div class="uk-modal-footer uk-text-right">
						<button class="uk-modal-close uk-button uk-button-success" 
							name="btn_submit_be_allergic" 
							id="btn_submit_be_allergic">
							ตกลง
						</button>
					</div>
				</form>
			</div>
		</div>
		<!-- Setting medicine -->
		<!-- MODAL ZONE -->
		
		<script>
		/**
		 * [pageStat = Whole page status.]
		 * @type {JSON Object}
		 * @author [wesarut | wesarut.khm@gmail.com]
		 */
		var pageStat = {
			btnAddElem : true,
			focusIndex : 0
		}

		$(document).ready(function(){

			/**
			 * Set the page select all text on focus
			 */
			coverTxtOnFocus()

			/*ACCORDION*/
			addElemAccordion(pageStat);
			/*ACCORDION*/

			/*DATA TABLE*/
			/*Set instance data table row.*/
			var row = $("#instance-elem").clone();
			$("#instance-elem").remove();
			row.removeAttr('id');

			$('#treatment-datatable').DataTable();

			var data = $('#med-datatable').DataTable(); 
			/*When click OK on product modal*/
			$("#btn_submit_be_allergic").click(function(event) {
				/*Get product id.*/
				var product_id = data.$('input[name="productModel.product_id_arr"]')
					.serializeArray();
				data.$('input[name="productModel.product_id_arr"]').prop('checked', false);

				/**
				 * Put into the table med list.
				 * - Set volumn for zaro (0).
				 */
				$.each(product_id, function(index, val) {
					var tbID = $("#frmTreatmentMaster").find('input.p-id-val').serializeArray();
					/*console.log(tbID);
					console.log(product_id);*/
					console.log(tbID.length);
					/*Clone the element.*/
					if(tbID.length > 0){
						row = $('.data-row:last').clone();
					}

					var v = val.value.split("(#:)");
					/*Checking for duplicate item.*/
					var chk = tbID.some(function(id){
						return id.value == v[0];
					});
					/*console.log(chk);*/

					if(chk === false){
						/*Set content.*/
						row.find('input[type="text"]').val(0);
						row.find('.p-name').html(v[1]);
						row.find('.p-name-en').html(v[2]);
						var num = row.find('.num-list').html();
						row.find('.num-list').html(++num);
						row.find('.p-id-val').val(v[0]);

						/*Merge content.*/
						$('#treatment-med-list').append(row);
					}
				});

			});
			/*DATA TABLE*/


			$('#frmTreatmentMaster').on('change', '#treatmentGroup', function(event) {
				event.preventDefault();
				/* Act on the event */
				var groupID = $(this).val();
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

		
		/**
		 * Cover all text in the txt box on focus in.
		 * @return {[type]} [description]
		 */
		var coverTxtOnFocus = function(){
			$('html').on('focus', 'input[type="text"]', function(event) {
				$(this).select();
			});		
		}
		
		/**
		 * [addElemAccordion : Add the element into the accordion.]
		 * @param {[JSON]} pStat [Page status]
		 * @return {bool} [Always return false when this func was finish.]
		 */
		var addElemAccordion = function(pStat){
			var accTitle = $("#ldc-accordion").children('.uk-accordion-title').clone();
			var accContent = $("#ldc-accordion").children('.uk-accordion-content').clone();
			$("#ldc-accordion").children('.uk-accordion-title, .uk-accordion-content').remove();
			$("#ldc-btn-add-elem").click(function(event) {
				/* Act on the event */
				if(pStat.btnAddElem){
						var num  = $("#ldc-txt-treat-num").val();
						$("#ldc-wrap-accordion").empty()
						.append('<div id="ldc-accordion" class="uk-accordion"></div>');
						if(num > 0){
							for(i=1; i<=num; i++){
								var elem = accTitle.clone();
								elem.html("ระยะการรักษา #" + i);
								$("#ldc-accordion").append(elem).append(accContent.clone());
							}
							UIkit.accordion($("#ldc-accordion"), {collapse : true, showfirst: false});
							pStat.btnAddElem = true;
						}else{
							return false;
						}
				}else{
					return false;
				}
			});

			$("#ldc-accordion").on('click', 'h3.uk-accordion-title', function(event) {
				event.preventDefault();
				/* Act on the event */
				pStat.focusIndex = $(this).index();
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