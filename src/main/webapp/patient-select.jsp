<%@page import="com.smict.product.model.ProductModel"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="com.smict.person.data.ContactData" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%@ page import="com.smict.product.data.ProductData" %>
<%@ page import="com.smict.person.data.FamilyData" %>
<%@page import="com.smict.person.data.CongenitalData"%>
<%@page import="com.smict.person.model.CongenitalDiseaseModel"%>
<%@page import="com.smict.person.data.PatientRecommendedData"%>
<%@page import="com.smict.person.model.RecommendedModel"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	ProductData product_Data = new ProductData();
	CongenitalData congen_Data = new CongenitalData();
%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:ค้นหารายชื่อ</title>
	<body>
	
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<!-- START-FORM -->
				<div class="uk-grid uk-grid-collapse uk-margin-large-top">
					<div class="uk-width-1-10 uk-text-center"></div>
					<div class="uk-width-8-10">
						<s:if test="%{#request.alertMSG != null}">
						<div class="uk-alert uk-alert-warning" data-uk-alert>
							<li class="uk-alert-close uk-close"></li>
							<p><s:property value="#request.alertMSG" /></p>
						</div>
						</s:if>
						<form action="searchPatient" method="post" class="uk-form">
							<div class="uk-grid uk-grid-collapse">
								<div class="uk-width-1-1">
									<label for="searchPat">
										<h2>ค้นหาคนไข้</h2>
										<h3>รหัส,ชื่อ,นามสกุล,รหัสประชาชน</h3>
									</label>
								<s:textfield id="searchPat" placeholder="Jane Doe" name="patModel.searchPat"
									class="uk-form-large uk-form-success uk-width-7-10 uk-margin-large-right"
									autofocus="autofocus" />
								<button class="uk-button uk-button-success uk-button-large uk-width-2-10">
									ค้นหา
								</button>
							</div>
						</form>
						<table class="uk-table uk-table-condensed uk-table-hover uk-text-center" id="patientList">
							<thead>
								<tr>
									<th class="uk-text-center">รหัส</th>
									<th class="uk-table-expand uk-text-center">ชื่อ-นามสกุล</th>
									<th class="uk-table-expand uk-text-center">รหัสประชาชน</th>
									<th class="uk-table-shrink uk-text-center">เลือก</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="patList" var="pat">
								<tr>
									<td>
										<s:property value="#pat.hn" /><br>
										<small><s:property value="#pat.hnBranch" /></small>
									</td>
									<td>
										<s:property value="#pat.firstname_th" />&nbsp;&nbsp;
										<s:property value="#pat.lastname_th" /> <br>
										<small>
											<s:property value="#pat.firstname_en" />&nbsp;&nbsp;
											<s:property value="#pat.lastname_en" />
										</small>
									</td>
									<td>
										<s:property value="#pat.identification" />
									</td>
									<td>
										<a href="selectPatient/view/<s:property value='#pat.hn' />" 
											class="uk-button uk-button-success">
											เลือก
											<li class="uk-icon-angle-right"></li>
										</a>
									</td>
								</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="uk-width-1-1 uk-text-right">
							<a href="beginAddPatient" class="uk-button uk-button-primary uk-button-large">
								<li class="uk-icon-plus-circle"></li>
								เพิ่มคนไข้
							</a>
						</div>
					</div>
					<div class="uk-width-1-10 uk-text-center"></div>
				</div>
				<!-- END-FORM -->
			</div>
		</div>
		<script>
			$(document).on("click",".remove-elements",function(){
				
				$(this).closest(".telephoneTemplate").remove();
				
			}).on("click",".remove-congenital-disease",function(){
				
				$(this).closest(".template-congenital-disease").remove();
				
			}).on("change","input[name='family_id']",function(){
				
				var index = $("input[name='family_id']").index(this);
				$("select[name='family_member'] option[value!='0']").remove();
				
				if(fn.hasNameThaiFamilyValue(index)){
					$("#ref_family_name").val($(".family_first_name_th:eq("+index+")").text()+" "+$(".family_last_name_th:eq("+index+")").text());
				}else{
					$("#ref_family_name").val($(".family_first_name_en:eq("+index+")").text()+" "+$(".family_last_name_en:eq("+index+")").text());
				}
				
				//$("select[name='family_member'] option[value='"+$(this).val()+"']").remove();
				$.ajax({
			        type: "post",
			        url: "ajax/ajax-family-member.jsp", //this is my servlet 
			        data: {method_type:"get",family_id:$(this).val()},
			        async:false, 
			        success: function(result){
			        	var obj = jQuery.parseJSON(result);
			        	for(var i = 0 ;  i < obj.length;i++){
			        		
			        		if(obj[i].first_name_th != ""){
			        			$("select[name='family_member']").append($('<option>').text(obj[i].first_name_th+" "+obj[i].last_name_th));
			        		}else{
			        			$("select[name='family_member']").append($('<option>').text(obj[i].first_name_en+" "+obj[i].last_name_en));
			        		}
			        		
			        	}
				    } 
			     });
				
			}).on("click","#remove_family",function(){
				
				$("input[name='family_id']").prop('checked', false);
				$("select[name='family_member'] option[value!='0']").remove();
				$("#ref_family_name").val("");
				
			}).on("change","input[name='be_allergic']",function(){
				
				var index = $("input[name='be_allergic']").index(this);
				var product_name = $(".product_name:eq("+index+")").text();
				var product_name_en = $(".product_name_en:eq("+index+")").text();
				if(this.checked){
					$("select[name='show_be_allergic']").append($('<option>').text(product_name+" - "+product_name_en).attr('value', $(this).val()));
				}else{
					
					$("select[name='show_be_allergic'] option[value='"+$(this).val()+"']").remove();
				}
				
			}).on("change","input[name='congenital_disease']",function(){
				
				var index = $("input[name='congenital_disease']").index(this);
				var product_name = $(".congenital_disease_th:eq("+index+")").text();
				var product_name_en = $(".congenital_disease_en:eq("+index+")").text();
				if(this.checked){
					
					if(product_name_en == "Other"){
						$("#prg_congenital_disease").show();
						$("#other_congenital_disease").show();
					}
					$("select[name='show_congenital_disease']").append($('<option>').text(product_name+" - "+product_name_en).attr('value', $(this).val()));
				}else{
					if(product_name_en == "Other"){
						$("#prg_congenital_disease").hide();
						$("#other_congenital_disease").hide();
						$("#other_congenital_disease").val("");
					}
					
					$("select[name='show_congenital_disease'] option[value='"+$(this).val()+"']").remove();
				}
				
			}).on("click","#remove_patient_contype",function(){
				
				$("input[name='patient_contypeid']").prop('checked', false);
				$("select[name='show_patient_type'] option[value!='0']").remove();
				
			}).on("change","input[name='patient_contypeid']",function(){
				
				var index = $("input[name='patient_contypeid']").index(this);
				var patient_typename = $(".patient_typename:eq("+index+")").text();
				
				$("select[name='show_patient_type']").append($('<option>').text(patient_typename).attr('value', $(this).val()));
				$("select[name='show_patient_type'] option[value!='"+$(this).val()+"']").remove();
				
			}).on("change","select[name='addrModel.addr_provinceid']",function(){
				
				var index = $("select[name='addrModel.addr_provinceid']").index(this); //GetIndex
				//alert($(this).val());
				$("select[name='addrModel.addr_aumphurid']:eq("+index+") option[value!='0']").remove();  //remove Option select amphur by index is not value =''
				if($(this).val() != '0'){
					
					$("select[name='addrModel.addr_aumphurid']:eq("+index+") option[value ='0']").text("กรุณาเลือกอำเภอ");
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-addr-amphur.jsp", //this is my servlet 
				        data: {method_type:"get",addr_provinceid:$(this).val()},
				        async:false, 
				        success: function(result){
				        	var obj = jQuery.parseJSON(result);
				        	for(var i = 0 ;  i < obj.length;i++){
				        		
				        		$("select[name='addrModel.addr_aumphurid']:eq("+index+")").append($('<option>').text(obj[i].amphur_name).attr('value', obj[i].addr_aumphurid));
				        		
				        	}
					    } 
				     });
				}else{
					
					$("select[name='addrModel.addr_aumphurid']:eq("+index+") option[value ='0']").text("กรุณาเลือกอำเภอ");
					$("select[name='addrModel.addr_districtid']:eq("+index+") option[value!='0']").remove();
					$("select[name='addrModel.addr_districtid']:eq("+index+") option[value ='0']").text("กรุณาเลือกตำบล");
				}
			}).on("change","select[name='addrModel.addr_aumphurid']",function(){
				//alert(123456);
				var index = $("select[name='addrModel.addr_aumphurid']").index(this); //GetIndex
				
				$("select[name='addrModel.addr_districtid']:eq("+index+") option[value!='0']").remove(); //remove Option select district by index is not value =''
				
				if($(this).val() != '0'){
					$("select[name='addrModel.addr_districtid']:eq("+index+") option[value ='0']").text("กรุณาเลือกตำบล");
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-addr-district.jsp", //this is my servlet 
				        data: {method_type:"get",addr_aumphurid:$(this).val()},
				        async:false, 
				        success: function(result){
				        	var obj = jQuery.parseJSON(result);
				        	for(var i = 0 ;  i < obj.length;i++){
				        		
				        		$("select[name='addrModel.addr_districtid']:eq("+index+")").append($('<option>').text(obj[i].district_name).attr('value', obj[i].district_id));
				        		
				        	}
					    } 
				     });
				}else{
					$("select[name='addrModel.addr_districtid']:eq("+index+") option[value ='0']").text("กรุณาเลือกตำบล");
				}
			}).on("click",".remove-addr-elements",function(){
				
				$(this).closest(".addrTemplate").remove();
				
			}).ready(function(){
				/*DATATABLE #patientList*/
				/*DATATABLE #patientList*/
				
				$("select[name='show_patient_type']").append($('<option>').text("ทั่วไป").attr('value', "1"));
				$("#prg_congenital_disease").hide();
				$("#other_congenital_disease").hide();
				
				$('select[name="patModel.identification_type"]').change(function(){
					
					if($(this).val() == '1'){

						$('#identification').attr({
			                'pattern': '[0-9]{13}',
			                'title' : "สามารถใช้ได้เฉพาะตัวเลข 0 - 9 จำนวน 13 หลักเท่านั้น",
			                'maxlength':'13'
			            });
					
					 }else{
						 
						 $('#identification').attr({
			                'pattern': '[0-9A-z]{15}',
			                'title' : "สามารถใช้ได้เฉพาะตัวอักษรภาษอังกฤษและตัวเลข รวมทั้งหมดจำนวน 15 หลักเท่านั้น",
			                'maxlength':'15'
			            });
					}
					 
				});
				
				$.ajax({
			        type: "post",
			        url: "ajax/ajax-telephone-type.jsp", //this is my servlet 
			        data: {method_type:"get",tel_typeid:"",tel_typename:""},
			        async:false, 
			        success: function(result){
			        	var obj = jQuery.parseJSON(result);
			        	for(var i = 0 ;  i < obj.length;i++){
			        		
			        		$(".teltype").append($('<option>').text(obj[i].tel_typename).attr('value', obj[i].tel_typeid));
			        		
			        	}
				    } 
			     });
				
				$.ajax({
			        type: "post",
			        url: "ajax/ajax-addr-type.jsp", //this is my servlet 
			        data: {method_type:"get",addr_typeid:"",addr_typename:""},
			        async:false, 
			        success: function(result){
			        	var obj = jQuery.parseJSON(result);
			        	for(var i = 0 ;  i < obj.length;i++){
			        		
			        		$("select[name='addr_typeid']").append($('<option>').text(obj[i].addr_typename).attr('value', obj[i].addr_typeid));
			        		
			        	}
				    } 
			     });
				
				$.ajax({
			        type: "post",
			        url: "ajax/ajax-addr-province.jsp", //this is my servlet 
			        data: {method_type:"get",addr_provinceid:""},
			        async:false, 
			        success: function(result){
			        	var obj = jQuery.parseJSON(result);
			        	for(var i = 0 ;  i < obj.length;i++){
			        		
			        		$("select[name='addrModel.addr_provinceid']").append($('<option>').text(obj[i].province_name).attr('value', obj[i].addr_provinceid));
			        		
			        	}
				    } 
			     });
				
				$(".add-customer-need").click(function(){
					var clone = $(".template-congenital-disease").eq(0);
					clone.find('.uk-button').removeClass('uk-button-success add-customer-need').addClass('uk-button-danger remove-congenital-disease').html('<i class="uk-icon-minus"></i>');
					clone.clone().appendTo("#container-congenital-disease");
					clone.find('.uk-button').removeClass('uk-button-danger remove-congenital-disease').addClass('uk-button-success add-customer-need').html('<i class="uk-icon-plus"></i>');
				});
				
				$(".add-addr-elements").click(function(){
					var clone = $(".div-addr .addrTemplate:first");
					clone.find('.uk-button').removeClass('uk-button-success add-addr-elements').addClass('uk-button-danger remove-addr-elements ').html('<i class="uk-icon-minus"></i>');					
					clone.clone().appendTo("#addrContainer");					
					clone.find('.uk-button').removeClass('uk-button-danger remove-addr-elements').addClass('uk-button-success add-addr-elements').html('<i class="uk-icon-plus"></i>');
				});
				
				$("#birthdate_eng").hide();
				$("#birthdate_th").datepicker({
				    format: "dd-mm-yyyy",
			        clearBtn: true,
			        autoclose: true,
			        todayHighlight: true
			    });
				$(".add-elements").click(function(){
					var clone = $(".div-telephone .telephoneTemplate:first");
					clone.find('.uk-button').removeClass('uk-button-success add-elements').addClass('uk-button-danger remove-elements').html('<i class="uk-icon-minus"></i>');
					clone.clone().appendTo("#telephonecontainer");
					clone.find('.uk-button').removeClass('uk-button-danger remove-elements').addClass('uk-button-success add-elements').html('<i class="uk-icon-plus"></i>');
				});
				
				$( ".m-patient" ).addClass( "uk-active" );
				
				$("#birthdate_patient").click(function(){
					if($("#birthdate_patient").text() == "Thai Year"){
						$("#birthdate_patient").text("English Year");
						$("#birthdate_th").val("");
						$("#birthdate_th").hide();
						$("#birthdate_eng").show();
						
					}else{
						$("#birthdate_patient").text("Thai Year");	
						$("#birthdate_eng").val("");
						$("#birthdate_eng").hide();
						$("#birthdate_th").show();
						
					}
				});
				
				$("#calAge").click(function(){
					var dob_th = $("#birthdate_th").val();
					var dob_en = $("#birthdate_eng").val();
					
					if(dob_th != ""){
						
						var year = dob_th.substr(6, 4);
						year = year - 543;
						dob_th = dob_th.substr(0,5)+"-"+year;
						
						fn.calAgeByBirthDate(dob_th);
					}else{
						
						fn.calAgeByBirthDate(dob_en);
					}
				});
				
				$("#birthdate_th").keypress(function(e){
					if(e.which == 13){
						e.preventDefault();
						var dob = $("#birthdate_th").val();
						var year = dob.substr(6, 4);
						year = year - 543;
						dob = dob.substr(0,5)+"-"+year;
						fn.calAgeByBirthDate(dob);
					}
				});
				
				$("#birthdate_eng").keypress(function(e){
					if(e.which == 13){
						e.preventDefault();
						var dob = $("#birthdate_eng").val();
						fn.calAgeByBirthDate(dob);
					}
				});
				
				$("#table_be_allergic").DataTable();
				$("#table_congenital_disease").DataTable();
				$("#family_table").DataTable();
				
				$('.clockpicker').clockpicker();
				
				$("#fpatient-quick").submit(function(event){
					if($("#idtel").val().length === 0 && $("#idline").val().length === 0 && $("#email").val().length === 0){
						swal(
								'ผิดพลาด!',
								'กรุณาระบุ กรอกข้อมูล เบอร์โทรศัพท์ IDLINE หรือ Email อย่างใดอย่างหนึ่ง',
								'error'
							)
						event.preventDefault();
					}
				});
				
				$("#patient_form").submit(function(e){
					
					if(!fn.hasValuePatientName()){
						e.preventDefault();
						swal({
			    			title: 'กรอกข้อมูลไม่ครบ',
			    			text: "คุณจำเป็นต้องกรอกข้อมูล ชื่อ - นามสกุล ",
			    			type: 'warning'
			    		})
					}else if(!fn.hasValueBirthDate()){
						e.preventDefault();
						swal({
			    			title: 'กรอกข้อมูลไม่ครบ',
			    			text: "คุณจำเป็นต้องกรอกข้อมูล  วัน - เดือน - ปีเกิด ",
			    			type: 'warning'
			    		})
					}else if(!fn.hasValueContact()){
						
						e.preventDefault();
						swal({
			    			title: 'กรอกข้อมูลไม่ครบ',
			    			text: "คุณจำเป็นต้องกรอกข้อมูล เบอรโทรศัพท์ หรือ IDLINE หรือ Email",
			    			type: 'warning'
			    		})
					}
				})
				
				fn = {
					hasValuePatientName: function() {
						
						var first_name_th = $("#first_name_th_add").val();
						var last_name_th = $("#last_name_th_add").val();
						var first_name_en = $("#first_name_en_add").val();
						var last_name_en = $("#last_name_en_add").val();
						var hasValue = false;
						
						if(($.trim(first_name_th) != '' && $.trim(last_name_th) != '') || ($.trim(first_name_en) != '' && $.trim(last_name_en) != '')){
							hasValue = true;
						}
						return hasValue;
					},
					hasValueBirthDate: function(){
						var birthdate_eng = $("#birthdate_eng").val();
						var birthdate_th = $("#birthdate_th").val();
						var hasValue = false;
						if(birthdate_eng != "" || birthdate_th != ""){
							hasValue = true;
						}
						return hasValue;
					},
					hasValueContact : function(){
						var tel_number = $("#tel_number_add").val();
						var patline_id = $("#patline_id_add").val();
						var patemail = $("#patemail_add").val();
						var hasValue = false;
						if(tel_number != "" || patline_id != "" || patemail != ""){
							hasValue = true;
						}
						return hasValue;
					},
					hasNameThaiFamilyValue: function(index){
						
						var family_first_name_th = $(".family_first_name_th:eq("+index+")").text();
						var family_last_name_th = $(".family_last_name_th:eq("+index+")").text();
						
						if(family_first_name_th != "" && family_last_name_th != ""){
							return true;
						}else{
							return false;
						}
					},
					calAgeByBirthDate: function(dob){
						/* var str = dob.substr(6, 4)+"-"+dob.substr(3, 2)+"-"+dob.substr(0, 2);
						
						dob = new Date(str);
						
						var today = new Date();
						//alert(dob+"-"+today);
						var age = Math.ceil((today.getTime()-dob.getTime())) / (1000 * 60 * 60 * 24 * 365);
						alert(parseInt(age));
						$("#pat_age").text(age); */
						var str = dob.substr(6, 4)+"-"+dob.substr(3, 2)+"-"+dob.substr(0, 2);
						dob = new Date(str);
						var now = new Date();
						
						  

						  // days since the birthdate    
						  var days = Math.floor((now.getTime() - dob.getTime())/1000/60/60/24);
						  var age = 0;
						  // iterate the years
						  for (var y = dob.getFullYear(); y <= now.getFullYear(); y++){
							
						    var daysInYear = fn.isLeap(y) ? 366 : 365;
						    if (days >= daysInYear){
						      days -= daysInYear;
						      age++;
						      
						      // increment the age only if there are available enough days for the year.
						    }
						  }
						  $("#pat_age").text(age);
					},
					isLeap:function (year) {
					    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
					 }
				}
				
			});
			
			function setup() {
				
				Webcam.set({
					height: 150,
					dest_width: 640,
		    		dest_height: 480,
		    		crop_width: 480,
					crop_height: 480,
					image_format: 'jpeg',
					jpeg_quality: 100
				});
				document.getElementById('my_camera').innerHTML = '';
				Webcam.attach( '#my_camera' );
			}
			
			function preview_snapshot() {
				// freeze camera so user can preview pic
				Webcam.snap( function(data_uri) {
					// display results in page
					document.getElementById('my_camera2').innerHTML = 
						
						'<input type="hidden" value="'+data_uri+'" name="patModel.profile_pic"/>';
					
				} );
				Webcam.freeze();
				
				// swap button sets
				document.getElementById('pre_take_buttons').style.display = 'none';
				document.getElementById('post_take_buttons').style.display = '';
				
				
			}
			
			function cancel_preview() {
				// cancel preview freeze and return to live camera feed
				Webcam.unfreeze();
				
				// swap buttons back
				document.getElementById('pre_take_buttons').style.display = '';
				document.getElementById('post_take_buttons').style.display = 'none';
			}
			
		</script>
			
	</body>
</html>