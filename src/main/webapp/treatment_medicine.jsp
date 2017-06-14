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
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-width-1-1 uk-margin-large"></div>
					<div class="uk-width-1-10"></div>
					<div class="uk-width-8-10">
						<div class="uk-grid uk-grid-collapse">
							<div class="uk-width-1-1">
								<h2>เลือกรายการยาสำหรับการรักษา</h2><br>
								<a class="uk-button" data-uk-modal="{target:'#modal-med'}"> 
									<i class="uk-icon-medkit"></i> เลือกยา
								</a>
							</div>
							<div class="uk-width-1-1">
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
									<tbody>
										<tr>
											<td class="uk-text-center">1</td>
											<td class="uk-text-left">
												<strong>ยาเม็ดวิตามินรวม</strong><br>
												<small>Multivitamin Tablets</small>
											</td>
											<td class="uk-text-center">
												<input type="text" class="uk-form-width-mini uk-text-center" value="10">
											</td>
											<td class="uk-text-center">
												<input type="text" class="uk-form-width-mini uk-text-center" value="5">
											</td>
										</tr>
										<tr>
											<td class="uk-text-center">2</td>
											<td class="uk-text-left">
												<strong>ยาเม็ดวิตามินซี</strong><br>
												<small>Vitamin C Tablets</small>
											</td>
											<td class="uk-text-center">
												<input type="text" class="uk-form-width-mini uk-text-center" value="18">
											</td>
											<td class="uk-text-center">
												<input type="text" class="uk-form-width-mini uk-text-center" value="15">
											</td>
										</tr>
										<tr>
											<td class="uk-text-center">3</td>
											<td class="uk-text-left">
												<strong>ยาแก้ปวดฟัน</strong><br>
												<small>Toothache Drops</small>
											</td>
											<td class="uk-text-center">
												<input type="text" class="uk-form-width-mini uk-text-center" value="25">
											</td>
											<td class="uk-text-center">
												<input type="text" class="uk-form-width-mini uk-text-center" value="5">
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="uk-width-1-1 uk-margin-medium-top uk-text-right">
								<div class="uk-button uk-button-success"> 
									<i class="uk-icon-medkit"></i> บันทึก
								</div>
								<a href="" class="uk-button"> 
									<i class="uk-icon-sign-out"></i> ออก
								</a>
							</div>
						</div>
					</div>
					<div class="uk-width-1-10"></div>
					<div class="uk-width-1-1 uk-margin-large"></div>
				</div>
			</form>	
			</div>
		</div>



		<!-- MODAL ZONE -->
		<!-- Setting medicine -->
		<div id="modal-med" class="uk-modal">
			<div class="uk-modal-dialog uk-modal-dialog-large uk-form">
				<a class="uk-modal-close uk-close"></a>
				<div class="uk-modal-header"><i class="uk-icon-medkit"></i> ยาที่ใช้ในการรักษา</div>
				<form action="" id="product-listmodal">
					<div class="uk-width-1-1 uk-overflow-container">
						<table class="display nowrap compact stripe hover cell-border order-column" id="table_be_allergic">
							<thead>
								<tr class="hd-table">
									<th class="uk-text-center">ทั้งหมด</th>
									<th class="uk-text-center">ยา/สินค้า</th>
								</tr>
							</thead>
							<tbody id="med-list" data-treatment-id='<s:property value="treatmentModel.treatmentID" />' >
								<s:iterator value="productList">
								<tr>
									<td class="uk-text-center uk-width-1-10">
										<s:checkbox name="productModel.product_id_arr" 
											fieldValue="%{product_id}" 
											theme="simple" 
										/>
										<!-- <s:hidden name="productModel.product_name_arr" 
											value="%{product_name}" 
											theme="simple"
										/> -->
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
						<button class="uk-modal-close uk-button uk-button-success" name="btn_submit_be_allergic" id="btn_submit_be_allergic">ตกลง</button>
					</div>
				</form>
			</div>
		</div>
		<!-- Setting medicine -->
		<!-- MODAL ZONE -->
		
		<script>
		$(document).ready(function(){
			/*DATA TABLE*/
			$('#table_treatment').DataTable({
		    	// "scrollX": true,
		    	// scrollY: '50vh',
		        // scrollCollapse: true
		    });

			var data = $('#table_be_allergic').DataTable(); 
			$('#product-listmodal').click(function(event) {
				/* Act on the event */
				var product_id = data.$('input[name="productModel.product_id_arr"]').serializeArray();
				console.log(product_id);
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