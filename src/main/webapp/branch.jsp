<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.person.action.*" %>
<!DOCTYPE html>

<html>
	<head>
		<title>Smart Dental:เพิ่มข้อมูลสาขา</title>
	</head>  
	
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
 			<form id="branch" action="branchM" method="post" >
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-width-1-1 padding5 uk-form" >
					
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
						 	<p class="uk-text-muted uk-width-1-1">ข้อมูลสาขา <a href="#add_branch" class="uk-button uk-button-success" data-uk-modal><i class=" uk-icon-plus"></i> เพิ่มข้อมูลสาขา</a></p>
							<!-- <input type="hidden" id="chkDetail" name="chkDetail" >
							<input type="hidden" id="chkDelete" name="chkDelete" > -->
							<input type="hidden" id="modeAction" class="clsModeAction" 
								name="modeAction" value="none">

							<div class="uk-width-1-1"> 
									<% if(request.getAttribute("alertMessage") != null) {%>
									 <h3 class="uk-text-success uk-text-center"><%=request.getAttribute("alertMessage").toString()%></h3> 
									<% }else{ %> 
									 <br>
									<% } %>
							</div>
			 				<div class="uk-width-1-1 uk-margin-medium-bottom">
			 					<ul class="uk-tab" data-uk-switcher="{
			 							connect:'#branch-active',
			 							animation: 'fade'
			 						}">
								    <li class="uk-active"><a href="#">active</a></li>
								    <li><a href="#">inactive</a></li>
								</ul>
			 				</div>
							<ul class="uk-width-1-1 uk-switcher" id="branch-active">  
							 	<li class="uk-active">
							 		<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray" id="table_branch_active">
										<thead>
											<tr class="hd-table">
												<th>แบรนด์</th>
												<th>รหัสสาขา</th>
												<th>เลขรหัสสาขา</th>
												<th>ชื่อสาขา</th>
												<th>แพทย์ผู้ดำเนินการ</th>
												<th>เบอร์</th>
												<th>เบอร์มือถือ</th>
												<th class="uk-text-center">จัดการ</th>
												<th class="uk-text-center">ปิดใช้งาน</th>
											</tr>
										</thead>
										<tbody>
											<%
											if(request.getAttribute("branchActive")!=null)	{
											List branchActive = (List) request.getAttribute("branchActive");
											List <BranchModel> branchModel = branchActive;
											int x=0;
											for(BranchModel pbm : branchModel){
											x++;
											%>
											<tr>
												<td><%=pbm.getBrand_name()%></td>
												<td><%=pbm.getBranch_id()%></td>
												<td><%=pbm.getBranch_code()%></td>
												<td><%=pbm.getBranch_name()%></td>
												<td><%=pbm.getDoctor_name()%></td>
												<td><%=pbm.getTel_id()%></td>
												<td><%=pbm.getTels_id()%></td>
												<td class="uk-text-center">
													<button type="button" name="detail"
													class="uk-button uk-button-primary uk-button-small btn-view"
													data-branch-code="<%=pbm.getBranch_code()%>" >
													<i class=" uk-icon-eye"></i>
													</button>
												</td>
												<td class="uk-text-center">
													<button type="button" name="delete"
													class="uk-button uk-button-danger uk-button-small btn-delete"
													data-branch-code="<%=pbm.getBranch_code()%>" >
													<i class=" uk-icon-star-o"></i>
													</button>
												</td>
											</tr>
												<% } %>
												<% }else{ %>
											<tr>
												<td class="uk-text-center" colspan="7">ไม่พบข้อมูล</td>
											</tr>
												<% } %>
										</tbody>
							 		</table>
							 	</li>
							 	<li>
							 		<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray" id="table_branch_inactive">
										<thead>
											<tr class="hd-table">
												<th>แบรนด์</th>
												<th>รหัสสาขา</th>
												<th>เลขรหัสสาขา</th>
												<th>ชื่อสาขา</th>
												<th>แพทย์ผู้ดำเนินการ</th>
												<th>เบอร์</th>
												<th>เบอร์มือถือ</th>
												<th class="uk-text-center">จัดการ</th>
												<th class="uk-text-center">เปิดใช้งาน</th>
											</tr>
										</thead>
										<tbody>
											<%
											if(request.getAttribute("branchInactive")!=null)	{
											List branchInactive = (List) request.getAttribute("branchInactive");
											List <BranchModel> branchModel = branchInactive;
											int x=0;
											for(BranchModel pbm : branchModel){
											x++;
											%>
											<tr>
												<td><%=pbm.getBrand_name()%></td>
												<td><%=pbm.getBranch_id()%></td>
												<td><%=pbm.getBranch_code()%></td>
												<td><%=pbm.getBranch_name()%></td>
												<td><%=pbm.getDoctor_name()%></td>
												<td><%=pbm.getTel_id()%></td>
												<td><%=pbm.getTels_id()%></td>
												<td class="uk-text-center">
													<button type="button" name="detail"
													class="uk-button uk-button-primary uk-button-small btn-view"
													data-branch-code="<%=pbm.getBranch_code()%>" >
													<i class=" uk-icon-eye"></i>
													</button>
												</td>
												<td class="uk-text-center">
													<button type="button" name="delete"
													class="uk-button uk-button-success uk-button-small btn-delete"
													data-branch-code="<%=pbm.getBranch_code()%>" >
													<i class=" uk-icon-star"></i>
													</button>
												</td>
											</tr>
												<% } %>
												<% }else{ %>
											<tr>
												<td class="uk-text-center" colspan="7">ไม่พบข้อมูล</td>
											</tr>
												<% } %>
										</tbody>
							 		</table>
							 	</li>
						 	</ul>
						</div>
						</div>
					</div>
					
					<div id="add_branch" class="uk-modal"> 
			<div class="uk-modal-dialog uk-form " >
		        <a class="uk-modal-close uk-close"></a>
			        <div class="uk-grid uk-grid-collapse">
					<div class="uk-width-1-2 uk-text-right">แบรนด์บรษัท : </div>
					<div class="uk-width-1-2">
						<select id="brand_id" name="branchModel.brand_id" required>
							<option value="1">LDC</option>
							<option value="2">ใส่ใจทันตแพทย์</option>
						</select>
					</div>
					<div class="uk-width-1-2 uk-text-right">รหัสสาขา : </div>
					<div class="uk-width-1-2">
						<input type="text" id="branch_id" name="branchModel.branch_id" pattern="[A-z]{1,4}" title="กรอกข้อมูล เป็นภาษาอังกฤษเท่านั้น" maxlength="4" required >
					</div>
					<div class="uk-width-1-2 uk-text-right">เลขรหัสสาขา : </div>
					<div class="uk-width-1-2">
						<input type="number" id="branch_code" name="branchModel.branch_code" pattern="[0-9]{1,3}" title="กรอกข้อมูล เป็นภาษาอังกฤษเท่านั้น" maxlength="4" required >
					</div>
					<div class="uk-width-1-2 uk-text-right">ชื่อสาขา : </div>
					<div class="uk-width-1-2">
						<input type="text" id="branch_name" name="branchModel.branch_name" pattern="[A-zก-๙].{1,}" title="กรอกข้อมูล เป็นภาษาอังกฤษ-ไทยเท่านั้น"  placeholder="LDC" required>
					</div>
					<div class="uk-width-1-2 uk-text-right">แพทย์ผู้ดำเนินการ : </div>
					<div class="uk-width-1-2">
						<select id="doctor_id" name="branchModel.doctor_id" required>
							<option selected value="">เลือกแพทย์ </option>
							<option value="1">ทพ มานุวัฒน์ ชัยชนะ</option>
							<option value="2">ทพ เศรษฐพงศ์ ธุรพันธ์กิจโชติ</option>
						</select>
					</div>
				<!--	<div class="uk-width-1-2 uk-text-right">สถานะ : </div>
				 	<div class="uk-width-1-2">
						<select name="branchModel.doctor_id">
							<option selected value="0">เลือกสถานะ </option>
							<option value="1">ดำเนินการ</option>
							<option value="2">ยุติการดำเนินการ</option>
						</select>
					</div>  -->
					<div class="uk-width-1-2 uk-text-right">ค่าตอบแทน : </div>
					<div class="uk-width-1-2">
						<input type="text" id="price_doctor" pattern="[0-9].{2,}" title="กรอกข้อมูล เป็นตัวเลขและต้องมากกว่า 3 หลักเท่านั้น" maxlength="10" name="branchModel.price_doctor" placeholder="1000" required>
					</div>
					<div class="uk-width-1-1 padding5 border-gray">
				 		<p class="uk-text-muted uk-width-1-1">ข้อมูลที่อยู่</p>
				 		<div class="uk-grid uk-grid-collapse"> 
				 		
					 		<div class="uk-width-1-2 uk-text-right">เลขที่ : </div>
							<div class="uk-width-1-2">
								<input type="text" id="addr_no" name="branchModel.addr_no" pattern="[0-9].{0,}" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" placeholder="" required>
							</div>
							
							<div class="uk-width-1-2 uk-text-right">หมู่ : </div>
							<div class="uk-width-1-2">
								<input type="text" id="addr_bloc" name="branchModel.addr_bloc" pattern="[0-9]" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" placeholder="" >
							</div>
							
							<div class="uk-width-1-2 uk-text-right">หมู่บ้าน : </div>
							<div class="uk-width-1-2">
								<input type="text" id="addr_village" name="branchModel.addr_village" pattern="[A-zก-๙].{1,}" title="กรอกข้อมูล เป็นภาษา ไทย-อังกฤษเท่านั้น" placeholder="" required>
							</div>
							
							<div class="uk-width-1-2 uk-text-right">ซอย : </div>
							<div class="uk-width-1-2">
								<input type="text" id="addr_alley" name="branchModel.addr_alley" pattern="[A-zก-๙0-9].{1,}" title="กรอกข้อมูล เป็นภาษา ไทย-อังกฤษและตัวเลขเท่านั้น" placeholder="" required>
							</div>
							
							<div class="uk-width-1-2 uk-text-right">ถนน : </div>
							<div class="uk-width-1-2">
								<input type="text" id="addr_road" name="branchModel.addr_road" pattern="[A-zก-๙].{1,}" title="กรอกข้อมูล เป็นภาษา ไทย-อังกฤษเท่านั้น" placeholder="" required>
							</div>
							
							<div class="uk-width-1-2 uk-text-right ">จังหวัด : </div>
							<div class="uk-width-1-2 sele2">
								<select id="addr_provinceid" name="branchModel.addr_provinceid" required >
									<option value="">เลือกจังหวัด </option> 
								</select>
							</div>
							
							<div class="uk-width-1-2 uk-text-right ">อำเภอ : </div>
							<div class="uk-width-1-2 sele2">
								<select id="addr_aumphurid" name="branchModel.addr_aumphurid" required>
									<option value="">เลือกอำเภอ</option>
								</select>
							</div>
							
							<div class="uk-width-1-2 uk-text-right ">ตำบล : </div>
							<div class="uk-width-1-2 sele2">
								<select id="addr_districtid" name="branchModel.addr_districtid" required>
									<option value="">เลือกตำบล</option>
								</select>
							</div>
							
							<div class="uk-width-1-2 uk-text-right">รหัสไปรษณีย์ : </div>
							<div class="uk-width-1-2">
								<input type="text" id="addr_zipcode" pattern="[0-9].{1,5}" maxlength="5" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" maxlength="5" name="branchModel.addr_zipcode" placeholder="" required>
							</div>
							
						</div>
					</div>
					<div class="uk-width-1-1 padding5 border-gray">
					
				 		<p class="uk-text-muted uk-width-1-1">ข้อมูลที่เบอร์โทรศัพท์</p>
				 		<div class="uk-grid uk-grid-collapse"> 
				 		
					 		<div class="uk-width-1-2 uk-text-right">เบอร์โทรศัพท์ : </div>
							<div class="uk-width-1-2">
								<input type="text" id="tel_id" name="branchModel.tel_id" pattern="[0-9]{1,9}" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" placeholder="029022097" maxlength="9" required>
							</div>
							
							<div class="uk-width-1-2 uk-text-right">เบอร์โทรศัพท์มือถือ : </div>
							<div class="uk-width-1-2">
								<input type="text" id="tels_id" name="branchModel.tels_id" pattern="[0-9]{1,10}" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" placeholder="0891521789" maxlength="10" required>
							</div>
							
						</div>
					</div>
					<div class="uk-container-center" > 
						<button class="uk-button uk-button-success" type="submit" id="save" name="save">ตกลง</button>
					    <button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
					</div>
				</div>
			
				</div> 
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
			 
			$("#table_branch_active").DataTable();
			$("#table_branch_inactive").DataTable();
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
			  
			$("#saveb").click(function(){  
				 
				$("#branch").submit();
				 
			});  
			$("#search").click(function(){  
				//if($("#s_brand_name").val()!=''||$("#s_branch_id").val()!=0||$("#s_branch_name").val()!=''||$("#s_docter_name").val()!=''){
					
					$("#brand_id").removeAttr("required");
					$("#branch_id").removeAttr("required");
					$("#branch_name").removeAttr("required");
					$("#doctor_id").removeAttr("required");
					$("#price_doctor").removeAttr("required");
					$("#addr_no").removeAttr("required");
					$("#addr_bloc").removeAttr("required");
					$("#addr_village").removeAttr("required");
					$("#addr_alley").removeAttr("required");
					$("#addr_road").removeAttr("required"); 
					$("#addr_provinceid").removeAttr("required");
					$("#addr_aumphurid").removeAttr("required"); 
					$("#addr_districtid").removeAttr("required");
					$("#addr_zipcode").removeAttr("required"); 
					$("#tel_id").removeAttr("required");
					$("#tels_id").removeAttr("required"); 
			//	} 
			}); 
			$(".btn-view").click(function(){  
				//if($("#s_brand_name").val()!=''||$("#s_branch_id").val()!=0||$("#s_branch_name").val()!=''||$("#s_docter_name").val()!=''){
					 
					$("#branch_name").removeAttr("required");
					$("#doctor_id").removeAttr("required");
					$("#price_doctor").removeAttr("required");
					$("#addr_no").removeAttr("required");
				//	$("#addr_bloc").removeAttr("required");
					$("#addr_village").removeAttr("required");
					$("#addr_alley").removeAttr("required");
					$("#addr_road").removeAttr("required"); 
					$("#addr_provinceid").removeAttr("required");
					$("#addr_aumphurid").removeAttr("required"); 
					$("#addr_districtid").removeAttr("required");
					$("#addr_zipcode").removeAttr("required"); 
					$("#tel_id").removeAttr("required");
					$("#tels_id").removeAttr("required");
					
					var index = $("button[name='detail']").index(this); 
					var brand = $("input[name='hdBrandID']").eq(index).val();
					var branch = $("input[name='hdBranchID']").eq(index).val();
				 
					$("#brand_id").val(brand);
					$("#branch_id").val(branch);  
					$("#chkDetail").val("detail"); 
					
					$("#branch").submit();
					 
			}); 

			/*DELETE BTN*/
			$(".btn-delete").click(function(){  
				//if($("#s_brand_name").val()!=''||$("#s_branch_id").val()!=0||$("#s_branch_name").val()!=''||$("#s_docter_name").val()!=''){
					 
					var btn = $(this);

					swal({
			  			  title: 'คุณต้องการลบหรือไม่?',
			  			  text: "เมื่อลบแล้ว ข้อมูล event จะหายไปทันที",
			  			  type: 'warning',
			  			  showCancelButton: true,
			  			  confirmButtonColor: '#3085d6',
			  			  cancelButtonColor: '#d33',
			  			  confirmButtonText: 'ต้องการลบ',
			  			  cancelButtonText: 'ยกเลิก',
			  			}).then(function(isConfirm) {
			  			  if (isConfirm) {  
							  swal(
				    			    'รอสักครู่',
				    			    'ข้อมูล ได้ถูกลบแล้ว.',
				    			    'success',
				    			   {
				    			       timer: 2000  
				    			   }
				    		  );

							  $("#branch_name").removeAttr("required");
								$("#doctor_id").removeAttr("required");
								$("#price_doctor").removeAttr("required");
								$("#addr_no").removeAttr("required");
							//	$("#addr_bloc").removeAttr("required");
								$("#addr_village").removeAttr("required");
								$("#addr_alley").removeAttr("required");
								$("#addr_road").removeAttr("required"); 
								$("#addr_provinceid").removeAttr("required");
								$("#addr_aumphurid").removeAttr("required"); 
								$("#addr_districtid").removeAttr("required");
								$("#addr_zipcode").removeAttr("required"); 
								$("#tel_id").removeAttr("required");
								$("#tels_id").removeAttr("required");

								var index = $("button[name='delete']").index(this); 
								var brand = $("input[name='hdBrandID']").eq(index).val();
								var branch = $("input[name='hdBranchID']").eq(index).val();
							 
								$("#brand_id").val(brand);
								$("#branch_id").val(branch);  
								$("#chkDelete").val("delete"); 
								
								$("#modeAction").val("delete");

								$("#branch").attr(
									'action',
									'branchM' + '?branchCode=' + btn.data('branch-code')
								).submit();
			  			  }else{
			  				  swal(
					    			'ข้อมูลไม่ถูกลบ',
					    			'ข้อมูล event ยังไม่ถูกลบ.',
					    			'error',
					    			{
				    			       timer: 2000  
				    			   }
					    		);  
			  			  }
			  		})
					 
			});
			
			
			
			
		});  
		
		</script>		
	</body>
</html>