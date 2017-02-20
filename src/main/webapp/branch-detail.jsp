<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.person.model.*" %>

<% 
	int brand_id 			= (Integer) request.getAttribute("brand_id"); 
	String branch_id 		= (String) request.getAttribute("branch_id"); 
	String branch_name 		= (String) request.getAttribute("branch_name");
	int doctor_id			= (Integer) request.getAttribute("doctor_id");
	int price_doctor		= (Integer) request.getAttribute("price_doctor");
	String addr_no			= (String) request.getAttribute("addr_no");
	String addr_bloc		= (String) request.getAttribute("addr_bloc");
	String addr_village 	= (String) request.getAttribute("addr_village");
	String addr_alley		= (String) request.getAttribute("addr_alley");
	String addr_road		= (String) request.getAttribute("addr_road");
	String addr_provincename	= (String) request.getAttribute("addr_provincename");
	String addr_aumphurname	= (String) request.getAttribute("addr_aumphurname");
	String addr_districtname	= (String) request.getAttribute("addr_districtname");
	String addr_zipcode		= (String) request.getAttribute("addr_zipcode"); 
	String tel_id			= (String) request.getAttribute("tel_id");
	String tels_id			= (String) request.getAttribute("tels_id"); 
	
%>
<!DOCTYPE html>

<html>
	<head>
		<title>Smart Dental:รายละเอียดข้อมูลสาขา</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				
				<form id="branch" action="branchD" method="post" >
					<div class="uk-width-1-1 uk-grid uk-grid-collapse padding5 uk-form" >
							
							<!-- Start Branch Detail-->
							<div class="uk-width-4-10 padding5 border-gray">
								<% if(request.getAttribute("alertMessage") != null) {%>
									 <h3 class="uk-text-success uk-text-center"><%=request.getAttribute("alertMessage").toString()%></h3> 
								<% }else{ %>  
								<% } %>
								<div class="uk-grid uk-grid-collapse">
							 	<p class="uk-text-muted uk-width-1-1">ข้อมูลสาขา </p>
							 	<div class="uk-width-1-2 uk-text-right">แบรนด์บรษัท : </div>
								<div class="uk-width-1-2">
									<select id="brand_id" name="branchModel.brand_id" required>
										<option <% if(brand_id==1){ %> selected <%} %> value="1">LDC</option>
										<option <% if(brand_id==2){ %> selected <%} %> value="2">ใส่ใจทันตแพทย์</option>
									</select>
									<input type="hidden" id="hdbrand_id" name="hdbrand_id" value="<%=brand_id%>"  maxlength="4"  >
								</div>
								<div class="uk-width-1-2 uk-text-right">รหัสสาขา : </div>
								<div class="uk-width-1-2">
									<input type="text" id="branch_id" name="branchModel.branch_id" pattern="[A-z]{1,4}" title="กรอกข้อมูล เป็นภาษาอังกฤษเท่านั้น" value="<%=branch_id%>"  maxlength="4"  required >
									<input type="hidden" id="hdbranch_id" name="hdbranch_id" value="<%=branch_id%>"  maxlength="4"  >
								</div>
								<div class="uk-width-1-2 uk-text-right">ชื่อสาขา : </div>
								<div class="uk-width-1-2">
									<input type="text" id="branch_name" name="branchModel.branch_name" pattern="[A-zก-๙].{1,}" title="กรอกข้อมูล เป็นภาษาอังกฤษ-ไทยเท่านั้น" value="<%=branch_name%>" required>
								</div>
								<div class="uk-width-1-2 uk-text-right">แพทย์ผู้ดำเนินการ : </div>
								<div class="uk-width-1-2">
									<select id="doctor_id" name="branchModel.doctor_id" required> 
										<option <% if(doctor_id==1){ %> selected <%} %> value="1">ทพ มานุวัฒน์ ชัยชนะ</option>
										<option <% if(doctor_id==2){ %> selected <%} %> value="2">ทพ เศรษฐพงศ์ ธุรพันธ์กิจโชติ</option>
									</select>
								</div> 
								<div class="uk-width-1-2 uk-text-right">ค่าตอบแทน : </div>
								<div class="uk-width-1-2">
									<input type="text" id="price_doctor" pattern="[0-9].{2,}" title="กรอกข้อมูล เป็นตัวเลขและต้องมากกว่า 3 หลักเท่านั้น" maxlength="10" name="branchModel.price_doctor" value="<%=price_doctor%>" required>
								</div>
								<div class="uk-width-1-1 padding5 border-gray">
							 		<p class="uk-text-muted uk-width-1-1">ข้อมูลที่อยู่</p>
							 		<div class="uk-grid uk-grid-collapse"> 
							 		
							 		<div class="uk-width-1-2 uk-text-right">เลขที่ : </div>
									<div class="uk-width-1-2">
										<input type="text" id="addr_no" name="branchModel.addr_no" pattern="[0-9].{0,}" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" value="<%=addr_no%>" required>
									</div>
									<div class="uk-width-1-2 uk-text-right">หมู่ : </div>
									<div class="uk-width-1-2">
										<input type="text" id="addr_bloc" name="branchModel.addr_bloc" pattern="[0-9]" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" value="<%=addr_bloc%>" >
									</div>
									<div class="uk-width-1-2 uk-text-right">หมู่บ้าน : </div>
									<div class="uk-width-1-2">
										<input type="text" id="addr_village" name="branchModel.addr_village" pattern="[A-zก-๙].{1,}" title="กรอกข้อมูล เป็นภาษา ไทย-อังกฤษเท่านั้น" value="<%=addr_village%>" required>
									</div>
									<div class="uk-width-1-2 uk-text-right">ซอย : </div>
									<div class="uk-width-1-2">
										<input type="text" id="addr_alley" name="branchModel.addr_alley" pattern="[A-zก-๙0-9].{1,}" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" value="<%=addr_alley%>" required>
									</div>
									<div class="uk-width-1-2 uk-text-right">ถนน : </div>
									<div class="uk-width-1-2">
										<input type="text" id="addr_road" name="branchModel.addr_road" pattern="[A-zก-๙].{1,}" title="กรอกข้อมูล เป็นภาษา ไทย-อังกฤษเท่านั้น" value="<%=addr_road%>" required>
									</div>
									 
									<div class="uk-width-1-2 uk-text-right ">จังหวัด - <%=addr_provincename%> : </div>
									<div class="uk-width-1-2 sele2">
										<select id="addr_provinceid" name="branchModel.addr_provinceid"  >
											<option value="">เลือกจังหวัด </option> 
										</select> 
									</div>
									<div class="uk-width-1-2 uk-text-right ">อำเภอ - <%=addr_aumphurname%> : </div>
									<div class="uk-width-1-2 sele2">
										<select id="addr_aumphurid" name="branchModel.addr_aumphurid" >
											<option value="">เลือกอำเภอ</option>
										</select>
									</div>
									<div class="uk-width-1-2 uk-text-right ">ตำบล - <%=addr_districtname%> : </div>
									<div class="uk-width-1-2 sele2">
										<select id="addr_districtid" name="branchModel.addr_districtid" >
											<option value="">เลือกตำบล</option>
										</select>
									</div>
									<div class="uk-width-1-2 uk-text-right">รหัสไปรษณีย์ : </div>
									<div class="uk-width-1-2">
										<input type="text" id="addr_zipcode" pattern="[0-9].{1,5}" maxlength="5" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" name="branchModel.addr_zipcode" value="<%=addr_zipcode%>" required>
									</div>
								</div>
								</div>
								<div class="uk-width-1-1 padding5 border-gray">
								
							 		<p class="uk-text-muted uk-width-1-1">ข้อมูลที่เบอร์โทรศัพท์</p>
							 		<div class="uk-grid uk-grid-collapse"> 
							 		
								 		<div class="uk-width-1-2 uk-text-right">เบอร์โทรศัพท์ : </div>
										<div class="uk-width-1-2">
											<input type="text" id="tel_id" name="branchModel.tel_id" pattern="[0-9]{1,9}" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น"  value="<%=tel_id%>" maxlength="9" required>
										</div>
										
										<div class="uk-width-1-2 uk-text-right">เบอร์โทรศัพท์มือถือ : </div>
										<div class="uk-width-1-2">
											<input type="text" id="tels_id" name="branchModel.tels_id" pattern="[0-9]{1,10}" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น"  value="<%=tels_id%>" maxlength="10" required>
										</div>
									</div>
								</div>
								<div class="uk-container-center"> 
									<button type="submit" class="uk-button uk-button-success uk-button-large "><i class="uk-icon-floppy-o"></i> บันทึกข้อมูล</button>
								</div>
								</div>
								
							</div>
							<!-- End Branch Detail-->
							<!-- Start Set up branch & doctor -->
							<div class="uk-width-6-10 padding5 border-gray">
								<div class="uk-grid uk-grid-collapse">
									<p class="uk-text-muted uk-width-1-1">จัดการข้อมูลสาขา </p>
									<div class="uk-width-1-3">
										<a class="uk-button uk-button-primary" href="doctor-standard.jsp"><i class="uk-icon-money uk-icon-medium"></i> <br/>จัดการค่า Standard</a>
									</div>
									<div class="uk-width-1-3">
										<a href="price-list.jsp" class="uk-button uk-button-primary"><i class="uk-icon-money uk-icon-medium"></i> <br/>จัดการรายได้แพทย์</a>
									</div>
								</div>
								<div class="uk-grid uk-grid-collapse uk-margin-medium-top">
									<p class="uk-text-muted uk-width-1-1">ห้องรักษา</p>
									<div class="uk-width-1-1 uk-text-left">
										<a class="uk-button uk-button-success uk-margin-medium" title="เพิ่มห้อง">
											<span>เพิ่มห้อง</span>
											<li class="uk-icon-plus"></li>
										</a>
									</div>
									<div class="uk-width-1-1">
										<table class="uk-table uk-table-hover uk-table-condensed border-gray" 
											id="tb_treatment_room">
											<thead>
												<tr class="hd-table">
													<th class="uk-width-9-10 uk-text-center">เลขที่ห้อง</th>
													<th class="uk-width-1-10">จัดการ</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td class="uk-text-center">#2301</td>
													<td>
														<div class="uk-button-dropdown" 
															data-uk-dropdown="{mode:'click'}"
															data-room-id="">
															<button class="uk-button uk-button-danger">จัดการ</button>
															<div class="uk-dropdown uk-dropdown-small">
																<ul class="uk-nav uk-nav-dropdown">
																	<li><a href="">แก้ไข</a></li>
																	<li><a href="">ลบ</a></li>
																</ul>
															</div>
														</div>
													</td>
												</tr>
												<tr>
													<td class="uk-text-center">#2302</td>
													<td>
														<div class="uk-button-dropdown" 
															data-uk-dropdown="{mode:'click'}"
															data-room-id="">
															<button class="uk-button uk-button-danger">จัดการ</button>
															<div class="uk-dropdown uk-dropdown-small">
																<ul class="uk-nav uk-nav-dropdown">
																	<li><a href="">แก้ไข</a></li>
																	<li><a href="">ลบ</a></li>
																</ul>
															</div>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<!-- End Set up branch & doctor -->
						</div>
						</form>
					</div>
				</div> 
			
		<script>
			$(document).on("change","select[name='branchModel.addr_provinceid']",function(){
			
			var index = $("select[name='branchModel.addr_provinceid']").index(this); //GetIndex
			$("select[name='branchModel.addr_aumphurid'] option[value!='']").remove();  //remove Option select amphur by index is not value =''
			$("select[name='branchModel.addr_districtid'] option[value!='']").remove();  //remove Option select amphur by index is not value =''
			if($(this).val() != ''){ 
				$("select[name='branchModel.addr_aumphurid'] option[value ='']").text("กรุณาเลือกอำเภอ");
				$("#addr_aumphurid").select2();
				$.ajax({
			        type: "post",
			        url: "ajax/ajax-addr-amphur.jsp", //this is my servlet 
			        data: {method_type:"get",addr_provinceid:$(this).val()},
			        async:false, 
			        success: function(result){
			        	var obj = jQuery.parseJSON(result);
			        	for(var i = 0 ;  i < obj.length;i++){
			        		
			        		$("select[name='branchModel.addr_aumphurid']").append($('<option>').text(obj[i].amphur_name+" "+obj[i].amphur_name_eng).attr('value', obj[i].addr_aumphurid));
			        		
			        	}
				    } 
			     });
			}else{
				$("select[name='branchModel.addr_aumphurid']  option[value ='']").text("กรุณาเลือกจังหวัด");
				$("select[name='branchModel.addr_districtid'] option[value!='']").remove();
				$("select[name='branchModel.addr_districtid'] option[value ='']").text("กรุณาอำเภอ");
			}
		}).on("change","select[name='branchModel.addr_aumphurid']",function(){
			 
			$("select[name='branchModel.addr_districtid'] option[value!='']").remove(); //remove Option select district by index is not value =''
			
			if($(this).val() != ''){
				$("select[name='branchModel.addr_districtid'] option[value ='']").text("กรุณาตำบล"); 
				$("#addr_districtid").select2();
				$.ajax({
			        type: "post",
			        url: "ajax/ajax-addr-district.jsp", //this is my servlet 
			        data: {method_type:"get",addr_aumphurid:$(this).val()},
			        async:false, 
			        success: function(result){
			        	var obj = jQuery.parseJSON(result);
			        	for(var i = 0 ;  i < obj.length;i++){
			        		
			        		$("select[name='branchModel.addr_districtid']").append($('<option>').text(obj[i].district_name+" "+obj[i].district_name_eng).attr('value', obj[i].district_id));
			        		
			        	}
				    } 
			     });
			}else{
				$("select[name='branchModel.addr_districtid'] option[value ='']").text("กรุณาอำเภอ");
			}
		}).ready(function(){
			$( ".m-setting" ).addClass( "uk-active" );
			
			$("#addr_provinceid").select2();
			$("#addr_aumphurid").select2();
			$("#addr_districtid").select2();
			
			$.ajax({
		        type: "post",
		        url: "ajax/ajax-addr-province.jsp", //this is my servlet 
		        data: {method_type:"get",addr_provinceid:""},
		        async:false, 
		        success: function(result){
		        	var obj = jQuery.parseJSON(result);
		        	for(var i = 0 ;  i < obj.length;i++){ 	
		        	$("select[name='branchModel.addr_provinceid']").append($('<option>').text(obj[i].province_name+" "+obj[i].province_name_eng).attr('value', obj[i].addr_provinceid));
		        	}	 
			    } 
		     });
			
			
			$(".province").change(function(){
				var province = $(".province").val(); 
				if(province==0){
					$('.amphur').children('option:not(:first)').remove();
					$('.district').children('option:not(:first)').remove(); 
				}else{
					$('.amphur').children('option:not(:first)').remove();
					$('.district').children('option:not(:first)').remove(); 
					if(province==1){
						$(".amphur").append( 
								"<option value='1'>เขตบางรัก</option>"+
					            "<option value='2'>เขตดุสิต</option>");
					}			
			        
				}
		    });
			$(".amphur").change(function(){
				var amphur = $(".amphur").val(); 
				if(amphur==0){
					$('.district').children('option:not(:first)').remove(); 
				}else{
					$('.district').children('option:not(:first)').remove(); 
					if(amphur ==1){
						$(".district").append( 
								"<option value='1'>สีลม</option>"+
					            "<option value='2'>สุริยวงศ์</option>");
					}else if(amphur ==2){
						$(".district").append( 
								"<option value='3'>ถนนนครไชยศรี</option>"+
					            "<option value='4'>สี่แยกมหานาค</option>");
					}
						
			        
				}
		    });
			$(".district").change(function(){
				var district = $(".district").val(); 
				if(district==1 || district==2){
					$("#postid").val(10500);
				}else if(district==3 || district==4){
					$("#postid").val(10300);
				}else{
					$("#postid").val("");
				}
		    });
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
		    
		}); 
		
		</script>		
	</body>
</html>