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
														name="treatmentModel.round"
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
														name="treatmentModel.price"
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
														name="treatmentModel.startPriceRange"
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
														name="treatmentModel.endPriceRange"
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
														<table class="uk-table uk-table-condensed ldc-tb-med">
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
															<tbody id="treatment-med-list" class="ldc-med-list">
																<tr class="ldc-tr-med" id="med-instance-elem">
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
														<table class="uk-table uk-table-condensed ldc-tb-treat">
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
															<tbody id="treatment-list" class="ldc-treat-list">
																<tr class="ldc-tr-treat" id="treat-instance-elem">
																	<td class="uk-text-center treat-num-list">1</td>
																	<td class="uk-text-center ldc-tbcol-detail">
																		<strong class="treat-name">การพักฟื้น</strong><br>
																		<small class="treat-name-en">recuperate</small>
																		<s:hidden value="#" 
																			name="treatmentModel.treatmentID"
																			class="treat-id-val"
																			theme="simple"
																		/>
																	</td>
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
								<button type="submit" 
									class="uk-button uk-button-success"
									id="ldc-btn-save"> 
									<i class="uk-icon-medkit"></i> บันทึก
								</button >
								<a href="" class="uk-button"
									id="ldc-btn-quit"> 
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
								<tr class="ldc-tbrow-treat" id="">
									<td class="uk-text-center uk-width-1-10">
										<s:checkbox name="treatmentModel.treatmentID" 
											fieldValue="%{treatmentID}(#:)%{treatmentNameTH}(#:)%{treatmentNameEN}(#:)%{treatmentCode}" 
											value="%{treatmentID}(#:)%{treatmentNameTH}(#:)%{treatmentNameEN}(#:)%{treatmentCode}" 
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
							id="ldc-modal-btn-add-treat">
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
											value="%{product_id}(#:)%{product_name}(#:)%{product_name_en}" 
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
							id="ldc-modal-btn-add-med">
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
			focusIndex : 0,
			accTitle : '',
			accContent : '',
			selectedMedCount : [],
			selectedTreatCount : [],
		}

		$(document).ready(function(){
			/**
			 * Set the page select all text on focus
			 */
			coverTxtOnFocus();

			/*ACCORDION*/
			/**
			 * Add new element.
			 */
			addElemAccordion();

			/**
			 * -Prepare the modal activity.
			 */
			prepareModalActivity();
			/*ACCORDION*/

			



		}); // End ready; 



	/**
	 * ============================================================================ *
	 * 									FUNCTION.
	 * ============================================================================ *
	 */
	

		/*DATA TABLE FUNC*/

		/**
		 * Medicine data table activity.
		 */
		var medDataTable = function(){
			/*DATA TABLE*/
			/*Set instance data table row.*/
			console.log(pageStat.accContent);
			let row = $(pageStat.accContent).find('#treatment-med-list').html();

			/*Load DataTable Class*/
			pageStat.medDataTable = $('#med-datatable').DataTable(); 
			serializeDataTable(
				{
					dataTableObj : pageStat.medDataTable,
					wrap : '#modal-med',
					event : 'click',
					trigger : '#ldc-modal-btn-add-med',
					inputName : 'input[name="productModel.product_id_arr"]'
				},
				function(dataSet){
					/*Counte old item*/
					let countItem = $('.uk-accordion-content:eq(' + pageStat.focusIndex + ')')
						.find('.ldc-tr-med').length;
					let selectedItem = $('.uk-accordion-content:eq(' + pageStat.focusIndex + ')')
						.find('.ldc-med-list input[name="productModel.product_id_arr"]').serializeArray();

					/*Iterate for retrieve value.*/
					let status = true;
					$.each(dataSet, function(index, val) {
						let ext = val.value.split('(#:)');
						if(val.name == 'productModel.product_id_arr'){
							if(countItem > 0){
								/*Check item exists.*/
								$.each(selectedItem, function(ind, v) {
									if(v.value == ext[0]){
										status = false;
									}
								});								
							}

							if(status){
								/*Prepare element.*/
								// 2(#:)แอสไพริน(#:)Aspirin Tablets 
								let elem = pageStat.accContent.find('#med-instance-elem').clone();
								elem.find('.p-id-val').val(ext[0]);
								elem.find('.p-name').html(ext[1]);
								elem.find('.p-name-en').html(ext[2]);
								elem.find('.num-list').html((countItem++)+1);

								/*Add new item*/
								$('.uk-accordion-content:eq(' + pageStat.focusIndex + ')')
									.find('.ldc-med-list')
									.append(elem.clone());
							}
							status = true;

							/*Clear checked item in the modal*/
							pageStat.medDataTable.$('input[name="productModel.product_id_arr"]').prop('checked', false);
						}
					});
				}
			);
			

			/*DATA TABLE*/
		}

		/**
		 * Serialize array the medicine data table.
		 */
		var serializeDataTable = function(obj, func){
			$(obj.wrap).on(obj.event, obj.trigger, function(event) {
				event.preventDefault();
				let chkItem = obj.dataTableObj.$(obj.inputName).serializeArray();
				func(chkItem);
			});
		}


		/**
		 * Prepare treatment list in data table.
		 */
		var treatDataTable = function(){
			/*DATA TABLE*/
			/*Set instance data table row.*/
			console.log(pageStat.accContent);
			let row = $(pageStat.accContent).find('#treatment-list').html();

			/*Load DataTable Class*/
			pageStat.treatDataTable = $('#treatment-datatable').DataTable(); 
			serializeDataTable(
				{
					dataTableObj : pageStat.treatDataTable,
					wrap : '#modal-treat',
					event : 'click',
					trigger : '#ldc-modal-btn-add-treat',
					inputName : 'input[name="treatmentModel.treatmentID"]'
				},
				function(dataSet){
					/*Counte old item*/
					let countItem = $('.uk-accordion-content:eq(' + pageStat.focusIndex + ')')
						.find('.ldc-tr-treat').length;
					let selectedItem = $('.uk-accordion-content:eq(' + pageStat.focusIndex + ')')
						.find('.ldc-tbcol-detail input[name="treatmentModel.treatmentID"]').serializeArray();

					/*Iterate for retrieve value.*/
					let status = true;
					$.each(dataSet, function(index, val) {
						let ext = val.value.split('(#:)');
						if(val.name == 'treatmentModel.treatmentID'){
							if(countItem > 0){
								/*Check item exists.*/
								$.each(selectedItem, function(ind, v) {
									if(v.value == ext[0]){
										status = false;
									}
								});								
							}

							if(status){
								/*Prepare element.*/
								// 2(#:)แอสไพริน(#:)Aspirin Tablets 
								let elem = pageStat.accContent.find('#treat-instance-elem').clone();
								elem.find('.treat-id-val').val(ext[0]);
								elem.find('.treat-name').html(ext[1]);
								elem.find('.treat-name-en').html(ext[2]);
								elem.find('.treat-num-list').html((countItem++)+1);

								/*Add new item*/
								$('.uk-accordion-content:eq(' + pageStat.focusIndex + ')')
									.find('.ldc-treat-list')
									.append(elem.clone());
							}
							status = true;

							/*Clear checked item in the modal*/
							pageStat.treatDataTable.$('input[name="treatmentModel.treatmentID"]').prop('checked', false);
						}
					});
				}
			);
			

			/*DATA TABLE*/
		}
		/*DATA TABLE FUNC*/

		/**
		 * Add table row element at medicine list.
		 */
		var addMedTBRow = function(elem){
			$('.uk-accordion-content:eq(' + pageStat.focusIndex + ')').find('')
		}
		
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
		 * Prepare modals activities.
		 */
		var prepareModalActivity = function(){
			/**
			 * Medical data table activity.
			 */
			medDataTable();

			/**
			 * Treatment data table activity.
			 */
			treatDataTable();
		}
		
		/**
		 * [addElemAccordion : Add the element into the accordion.]
		 * @param {JSON} pStat [Page status]
		 * @return {bool} [Always return false when this func was finish.]
		 */
		var addElemAccordion = function(){
			/**
			 * Load default element
			 */
			var accTitle = pageStat.accTitle = $("#ldc-accordion").children('.uk-accordion-title').clone();
			var accContent = pageStat.accContent = $("#ldc-accordion").children('.uk-accordion-content').clone();
			$("#ldc-accordion").children('.uk-accordion-title, .uk-accordion-content').remove();
			$("#ldc-btn-add-elem").click(function(event) {
				/**
				 * Get the count of element.
				 */
				var num  = $("#ldc-txt-treat-num").val();

				/**
				 * Clear the old element.
				 */
				$("#ldc-wrap-accordion")
					.empty()
					.append('<div id="ldc-accordion" class="uk-accordion"></div>');
				


				if(num > 0){
					/**
					 * Add the new element by amount that specified.
					 */
					for(i=1; i<=num; i++){
						var elem = accTitle.clone();
						elem.html("ระยะการรักษา #" + i);
						$("#ldc-accordion").append(elem).append(accContent.clone());
					}

					/**
					 * Clear old treatment & medicine table lise element.
					 */
					$(".ldc-med-list").empty();
					$(".ldc-treat-list").empty(); 

					/**
					 * Reload UIkit accordion
					 */
					UIkit.accordion($("#ldc-accordion"), {collapse : true, showfirst: false});
				}else{
					return false;
				}
			});

			/**
			 * Retrieving the accordion index.
			 */
			$("#ldc-wrap-accordion").on('click', 'h3.uk-accordion-title', function(event) {
				event.preventDefault();
				/* Act on the event */
				pageStat.focusIndex = $(this).index()/2;
				console.log(pageStat.focusIndex);
			});
		}

		</script>		
	</body>
</html>