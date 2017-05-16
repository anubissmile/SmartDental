<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%@ page import="com.smict.person.data.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:เพิ่มพนักงาน</title>
		<link href="css/style-promotion.css"rel="stylesheet">
	</head> 
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			
			<div class="uk-width-9-10">
			<%@include file="employee-nav.jsp" %>
			<script type="text/javascript" src="js/webcam.min.js"></script>
			<form action="addemployeeinsert" method="post"id="fpatient-quick" onsubmit="return myFunction()">
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-width-4-10 padding5 uk-form" >
					<div id="my_camera2"></div>
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
						<p class="uk-text-muted uk-width-1-1">ข้อมูลส่วนตัว</p>
							<div class="uk-width-1-3 uk-text-right">รูปพนักงาน: </div>
							<div class="uk-width-1-3" ><div id="my_camera"><img src="img/IMG_0846.JPG" alt="No Profile Picture" class="profile-pic"></div></div>
							<div class="uk-width-1-3" >
								<div id="pre_take_buttons">
									<button type="button" id="access" class="uk-button uk-button-primary uk-icon-camera" onClick="setup(); $(this).hide().next().show();"> Access Camera</button>
									<button type="button" id="take" class="uk-button uk-button-success uk-icon-camera" onClick="preview_snapshot()"style="display:none"> Take Photo</button>
								
								</div>
								<div id="post_take_buttons" style="display:none">
									<button type="button"class="uk-button uk-button-primary uk-icon-refresh" onClick="cancel_preview()"> Take Again</button>
								</div>
							</div>
							
							<div class="uk-width-1-3 uk-text-right">คำนำหน้าชื่อ : </div>
							<div class="uk-width-1-3">
								<select class="uk-form-small uk-width-1-1" name="employeemodel.pre_name_id" required>
									<%@include file="include/prename-dd-option.jsp" %>
								</select>
							</div>
							<div class="uk-width-1-3"></div> 
							<div class="uk-width-1-3 uk-text-right">ชื่อ : </div>
							<div class="uk-width-1-3">
								<input type="text" name="employeemodel.firstname_th"pattern="[ก-๙]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทย" class="uk-form-small uk-width-1-1">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">นามสกุล : </div>
							<div class="uk-width-1-3">
								<input type="text" name="employeemodel.lastname_th" pattern="[ก-๙]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทย"class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">ชื่อ EN : </div>
							<div class="uk-width-1-3">
								<input type="text" name="employeemodel.firstname_en" pattern="[A-z]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาอังกฤษ" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">นามสกุล EN : </div>
							<div class="uk-width-1-3">
								<input type="text" name="employeemodel.lastname_en" pattern="[A-z]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาอังกฤษ" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">
								<select class="uk-form-small" name="employeemodel.identification_type">
									<option value="1" selected>รหัสบัตรประจำตัวประชาชน</option>
									<option value="2">หมายเลขหนังสือเดินทาง</option>
								</select> 
							</div>
							<div class="uk-width-1-3">
								<input type="text" maxlength="13" name="employeemodel.identification" pattern="[0-9]{13}" title="ใส่ได้เฉพาะตัวเลข 0-9" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">รหัสพนักงาน : </div>
							<div class="uk-width-1-3">
								<input type="text" name="employeemodel.emp_id" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>วันเกิด : </div>
							<div class="uk-width-1-3">
								<input type="text" name="birthdate_eng" id="birthdate_eng" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" >
								<input type="text" name="birthdate_th" id="birthdate_th" class="uk-form-small uk-width-1-1">
							</div>
							<div class="uk-width-1-3"><button id="birthdate_patient" type="button" class="btn uk-button uk-button-primary uk-button-small" >Thai Year</button></div>
							<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>วันที่เริ่มทำงาน : </div>
							<div class="uk-width-1-3">
								<input type="text" name="hiredate_eng" id="hiredate_eng" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" >
								<input type="text" name="hiredate_th" id="hiredate_th" class="uk-form-small uk-width-1-1">
							</div>
							<div class="uk-width-1-3"><button id="hiredate_employee" type="button" class="btn uk-button uk-button-primary uk-button-small" >Thai Year</button></div>							
						</div>
						
						<div class="uk-grid uk-grid-collapse padding5 border-gray div-telephone">
							<p class="uk-text-muted uk-width-1-1">ช่องทางติดต่อ </p>
						 	<div class="telephoneTemplate uk-grid uk-grid-collapse uk-width-1-1">
								<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>เบอร์โทรศัพท์ : </div>
								<div class="uk-width-1-3">
									<s:textfield autocomplete="off" 
										name="telModel.multiTelNumber" 
										value="%{#tel.tel_number}" 
										id="tel_number_add" 
										pattern="[0-9].{8,9}|(?=.*[0-9])(?=.*[-]).{8,}" 
										title="กรอกเฉพาะตัวเลข" 
										placeholder="เบอร์ติดต่อ" 
										class="telnumber uk-form-small uk-width-1-1" 
									/>
								</div>
								<div class="uk-width-1-3">
									<div class="uk-grid uk-grid-collapse">
										<div class="uk-width-2-3">
											<s:select list="telType" 
												name="telModel.multiTelTypeId" 
												class="uk-form-width-large" 
												id="branchModel_branch_code"
												value="#tel.tel_typeid"
											/>
										</div>
										<div class="uk-width-1-3">
											<button class="uk-button uk-button-success uk-button-small add-elements" 	type="button">
												<i class="uk-icon-plus"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
							<!-- This element for form clone. -->
							<div id="telephonecontainer" class="div-container uk-grid uk-grid-collapse uk-width-1-1">
							</div>    
							<!-- This element for form clone. -->
							<div class="uk-width-1-3 uk-text-right">Line ID : </div>
							<div  class="uk-width-1-3 uk-text-right">
								<s:textfield autocomplete="off" 
									name="employeemodel.lineId" 
									id="patline_id_add" 
									pattern="[A-z0-9.]{1,}" 
									placeholder="Line ID" 
									class="uk-form-small uk-width-1-1" 
								/>
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">E-mail : </div>
							<div  class="uk-width-1-3 uk-text-right">
								<s:textfield name="employeemodel.email" 
									id="patemail_add" 
									placeholder="E-mail" 
									class="uk-form-small uk-width-1-1" 
								/>
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">เบอร์โทรฉุกเฉิน: </div>
							<div class="uk-width-1-3">
								<s:textfield autocomplete="off" 
									name="telModel.multiTelNumber" 
									value="%{emTelModel.tel_number}" 
									id="tel_number" 
									pattern="[0-9]{8,10}" 
									title="กรอกเฉพาะตัวเลข" 
									placeholder="เบอร์ติดต่อฉุกเฉิน" 
									class="telnumber uk-form-small uk-width-1-1"
								/> 
							</div>
							<div class="uk-width-1-3">
								<s:hidden name="telModel.multiTelTypeId" value="5" />
							</div>
							<div class="uk-width-1-3 uk-text-right">เจ้าของเบอร์ฉุกเฉิน: </div>
							<div class="uk-width-2-3">
								<s:textfield class="uk-form-small uk-width-1-1" 
									name="telModel.relevant_person" 
									value="%{emTelModel.relevant_person}" 
									placeholder="เจ้าของเบอร์ฉุกเฉิน"
								/>
							</div>
							<div class="uk-width-1-3 uk-text-right">ความสัมพันธ์: </div>
							<div class="uk-width-2-3">
								<s:textfield class="uk-form-small uk-width-1-1" 
									name="telModel.tel_relative" 
									value="%{emTelModel.tel_relative}" 
									placeholder="ความสัมพันธ์"
								/>
							</div>   
						</div>
						<div class="uk-grid uk-grid-collapse padding5 border-gray div-addr">
						<p class="uk-text-muted uk-width-1-1">ที่อยู่</p>
						 	<div class="addrTemplate uk-grid uk-grid-collapse uk-width-1-1">
								<div class="uk-panel uk-panel-box uk-width-1-1">
									<div class="uk-grid uk-grid-collapse uk-width-1-1">
	                           	    	<select name="employeemodel.addr_typeid" class="uk-form-small">
											<%@include file="include/addrtype-dd-option.jsp" %>
	                                	</select>
                                    </div>
                                    <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
	                                   <div class="uk-width-1-3"><small >เลขที่</small>
											<input type="text" maxlength="10" name="employeemodel.addr_no" class="uk-form-small uk-width-1-1">
	                                   </div>
	                                   <div class="uk-width-1-3"><small >หมู่บ้าน</small>
	                                   		<input type="text" maxlength="55" name="employeemodel.addr_village"class="uk-form-small uk-width-1-1">
	                                   </div>
	                                   <div class="uk-width-1-3"><small >ซอย</small>
	                                   		<input type="text" maxlength="100"  name="employeemodel.addr_alley" class="uk-form-small uk-width-1-1">
	                                   </div>
                                    </div> 
                                    <div class="uk-grid uk-grid-collapse uk-width-1-1">
                                    	<div class="uk-width-1-3"><small >หมู่</small>
	                                   		<input type="text" maxlength="10"  name="employeemodel.addr_bloc" class="uk-form-small uk-width-1-1">
	                                    </div>
	                                   <div class="uk-width-1-3"><small >ถนน</small>
	                                   		<input type="text" maxlength="100"  name="employeemodel.addr_road" class="uk-form-small uk-width-1-1">
	                                    </div>
	                                    <div class="uk-width-1-3"><small >รหัสไปรษณีย์</small>
	                                   		<input type="text" maxlength="5"  name="employeemodel.addr_zipcode" class="uk-form-small uk-width-1-1">
	                                    </div>
                                    </div>
                                    <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
                                    	<div class="uk-width-1-3"><small >จังหวัด</small>
	                                    	<select id="addr_provinceid" name="employeemodel.addr_provinceid" class="uk-form-small uk-width-1-1">
	                                    		<option value="">เลือกจังหวัด </option> 
	                                    	</select>
                                    	</div>
                                    	<div class="uk-width-1-3"><small >อำเภอ</small>
		                                   	<select id="addr_aumphurid" name="employeemodel.addr_aumphurid" class="uk-form-small uk-width-1-1">
		                                   		<option value="">เลือกอำเภอ</option> 
		                                   	</select>
	                                   	</div>
	                                   	<div  class="uk-width-1-3"><small >ตำบล</small>
		                                   	<select id="addr_districtid" name="employeemodel.addr_districtid" class="uk-form-small uk-width-1-1">
		                                   		<option value="">เลือกตำบล</option> 
		                                   	</select>
	                                   	</div>
                                    </div>
								</div>
									<button class="uk-button uk-button-success uk-button-small add-addr-elements uk-container-center " type="button" ><i class="uk-icon-plus"></i></button>
							</div>
							<div id="addrContainer" class="div-container uk-grid uk-grid-collapse uk-width-1-1"></div>    
						</div>
						
						
						
					</div>
					<div class="uk-width-6-10 padding5">
						
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
							<div class="uk-width-1-1 uk-form">
								<p class="uk-text-muted uk-width-1-1">รหัสเข้าสู่ระบบ</p>
								<div class="border-gray padding5">
									<div class="div-edu">
										<div class="uk-grid ">
											<div class="uk-width-1-1 "> 	
													User name
														<input type="text" Class="uk-width-1-1 uk-form-small" name="employeemodel.empuser" value="" required />
											</div>
												<div class="uk-width-1-2">
												Password
														<input type="password" id="pass1" Class="uk-width-1-1 uk-form-small" name="employeemodel.emppassword" value="" required />
												</div>
												<div class="uk-width-1-2">
												Confirm Password
														<input type="password" id="pass2" Class="uk-width-1-1 uk-form-small" name="confirmpass" value="" required />
												</div>					
											
										</div>
									</div> 
								</div>
							<div class="uk-grid uk-grid-collapse border-gray padding5">							
							<div class="uk-width-1-2 padding5 border-right">
								<p class="uk-text-muted uk-width-1-1">ข้อมูลการทำงาน</p>
								<div class="">
									<p>ผู้ช่วยแพทย์ </p>
									<div class="uk-grid">
										<div class="uk-width-1-2 uk-text-center"><input type="radio"  name="employeemodel.is_asistant" value="1">ใช่</div>
										<div class="uk-width-1-2"><input type="radio"  name="employeemodel.is_asistant" value="0" checked>ไม่ใช่</div>
									</div><hr>									
								</div>
								<p class="uk-text-muted uk-width-1-1">สาขาที่ทำงาน</p>
								<div class="">								
									<div class="uk-grid uk-grid-collapse">
										<div class="uk-width-1-3 uk-text-right">สาขา</div>											
										<div class="uk-width-2-3">
											<s:select cssClass=" uk-form-small" list="branchlist" name="employeemodel.branch_id"
									      	  required="true" headerKey="" headerValue = "กรุณาเลือก"/> 
								      	 </div>
									</div>									
								</div>
							</div>
							<div class="uk-width-1-2 padding5">
							</div>	
							</div>											
								<p class="uk-text-muted uk-width-1-1">หมายเหตุ</p>
								<div class="uk-grid uk-grid-collapse padding5">
									<textarea name="employeemodel.remark" ></textarea>
								</div>
							</div>
						</div>
						<div class="uk-text-center">
							<button class="uk-button uk-button-success uk-button-large uk-icon-floppy-o"  type="submit"> เพิ่มพนักงาน</button>
							<a href="getemployeelist" class="uk-button uk-button-danger uk-button-large "><i class="uk-icon-close"></i> ยกเลิก</a>
						</div>
					</div>
				</div>
				<div id="family" class="uk-modal ">
					    <div class="uk-modal-dialog uk-modal-dialog-large uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-users"></i> ครอบครัว</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
					         		<!-- ชื่อคนไข้ <div class="uk-form-icon">
					         				<i class="uk-icon-search"></i>
									    	<input type="text">
										</div>
									<button name="searchfam">ค้นหา</button> -->
									<table id="family_table" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table"> 
									        	<th class="uk-text-center">เลือก</th>
									        	<th class="uk-text-center">ประเภท</th>
									            <th class="uk-text-center">ชื่อไทย</th> 
									            <th class="uk-text-center">นามสกุลไทย</th>
									            <th class="uk-text-center">ชื่ออังกฤษ</th> 
									            <th class="uk-text-center">นามสกุลอังกฤษ</th>  
									        </tr>
									    </thead> 
									    <tbody>
									    	<%
									    	List<JSONObject> unionFamilyList = new FamilyData().getUNION_FamilyList(0,"", "", "", "");
			                                for(JSONObject family_json : unionFamilyList){
			                                %>
			                                <tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" name="family_id" value="<%=family_json.get("family_id")%>"> <label for="form-s-r"></label>
			                                        </div>
                                        		</td>
                                        		<td class="uk-text-center "><%=family_json.get("user_type_name")%></td>
                                        		<td class="uk-text-center family_first_name_th"><%=family_json.get("first_name_th")%></td>
										        <td class="uk-text-center family_last_name_th"><%=family_json.get("last_name_th")%></td>
										        <td class="uk-text-center"><%=family_json.get("first_name_en")%></td>
										        <td class="uk-text-center"><%=family_json.get("last_name_en")%></td>
											</tr> 
			                                <%			                                	
			                                }
									    	%>
										</tbody>
									</table>
									</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" name="btn_submit_family" id="btn_submit_family">ตกลง</button>
					         </div>
					    </div>
					</div>
			</form>
				
			</div>
		</div>
		<script>
			$(document).on("change","select[name='employeemodel.addr_provinceid']",function(){
				var index = $("select[name='employeemodel.addr_provinceid']").index(this); //GetIndex
				$("select[name='employeemodel.addr_aumphurid']:eq("+index+") option[value!='']").remove();  //remove Option select amphur by index is not value =''
				$("select[name='employeemodel.addr_districtid']:eq("+index+") option[value!='']").remove();  //remove Option select amphur by index is not value =''
				if($(this).val() != ''){ 
					$("select[name='employeemodel.addr_aumphurid']:eq("+index+") option[value ='']").text("กรุณาเลือกอำเภอ");
					
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-addr-amphur.jsp", //this is my servlet 
				        data: {method_type:"get",addr_provinceid:$(this).val()},
				        async:false, 
				        success: function(result){
				        	var obj = jQuery.parseJSON(result);
				        	for(var i = 0 ;  i < obj.length;i++){
				        		$("select[name='employeemodel.addr_aumphurid']:eq("+index+")").append($('<option>').text(obj[i].amphur_name).attr('value', obj[i].addr_aumphurid));
				        	}
					    } 
				     });
				}else{
					$("select[name='employeemodel.addr_aumphurid']:eq("+index+")  option[value ='']").text("กรุณาเลือกจังหวัด");
					$("select[name='employeemodel.addr_districtid']:eq("+index+") option[value!='']").remove();
					$("select[name='employeemodel.addr_districtid']:eq("+index+") option[value ='']").text("กรุณาอำเภอ");
				}
			}).on("change","select[name='employeemodel.addr_aumphurid']",function(){
				var index = $("select[name='employeemodel.addr_aumphurid']").index(this); //GetIndex
				$("select[name='employeemodel.addr_districtid']:eq("+index+") option[value!='']").remove(); //remove Option select district by index is not value =''
				
				if($(this).val() != ''){
					$("select[name='employeemodel.addr_districtid']:eq("+index+") option[value ='']").text("กรุณาตำบล"); 
					
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-addr-district.jsp", //this is my servlet 
				        data: {method_type:"get",addr_aumphurid:$(this).val()},
				        async:false, 
				        success: function(result){
				        	var obj = jQuery.parseJSON(result);
				        	for(var i = 0 ;  i < obj.length;i++){
				        		
				        		$("select[name='employeemodel.addr_districtid']:eq("+index+")").append($('<option>').text(obj[i].district_name).attr('value', obj[i].district_id));
				        		
				        	}
					    } 
				     });
				}else{
					$("select[name='employeemodel.addr_districtid']:eq("+index+") option[value ='']").text("กรุณาอำเภอ");
				}
			}).on("change","input[name='doctor_branch']",function(){
				
				var index = $("input[name='doctor_branch']").index(this);
				var branch_id = $(".branch_id:eq("+index+")").text();
				var ddbranch = $("select[name='show_doctor_branch']");
				var branch_name = $(".branch_name:eq("+index+")").text();
				
				if(this.checked){			
						ddbranch.append($('<option>').text(branch_id+" - "+branch_name).attr('value', $(this).val()));
				}else{
					$("select[name='show_doctor_branch'] option[value='"+$(this).val()+"']").remove();
				}
				
			}).on("change","input[name='family_id']",function(){
				
				var index = $("input[name='family_id']").index(this);
				$("select[name='family_member'] option[value!='0']").remove();
				
				if(fn.hasNameThaiFamilyValue(index)){
					$("#ref_family_name").val($(".family_first_name_th:eq("+index+")").text()+" "+$(".family_last_name_th:eq("+index+")").text());
				}else{
					$("#ref_family_name").val($(".family_first_name_en:eq("+index+")").text()+" "+$(".family_last_name_en:eq("+index+")").text());
				}
				
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
			
		}).on("change","input[name='doctor_boss_branch']",function(){
				
				var index = $("input[name='doctor_boss_branch']").index(this);
				var branch_id = $(".branch_boss_id:eq("+index+")").text();
				var ddbranch = $("select[name='show_doctor_boss_branch']");
				var branch_name = $(".branch_boss_name:eq("+index+")").text();
				
				if(this.checked){
					if(ddbranch.children('option').length <= 1 ){
						ddbranch.append($('<option>').text(branch_id+" - "+branch_name).attr('value', $(this).val()));
					}else{
						this.checked = false;
					}
				}else{
					$("select[name='show_doctor_boss_branch'] option[value='"+$(this).val()+"']").remove();
				}
				
			});
			
			$(document).ready(function(){
				
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
			        url: "ajax/ajax-addr-province.jsp", //this is my servlet 
			        data: {method_type:"get",addr_provinceid:""},
			        async:false, 
			        success: function(result){
			        	var obj = jQuery.parseJSON(result);
			        	for(var i = 0 ;  i < obj.length;i++){ 	
			        	$("select[name='employeemodel.addr_provinceid']").append($('<option>').text(obj[i].province_name).attr('value', obj[i].addr_provinceid));
			        	}	 
				    } 
			     });
				$( ".m-setting" ).addClass( "uk-active" );
				$("#birthdate").click(function(){
					if($("#birthdate").text() == "Thai year"){
						$("#birthdate").text("English year");
					}else{
						$("#birthdate").text("Thai year");	
					}
				});
				 
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
				$(".add-work-elements").click(function(){
					var clone = $(".div-work .workTemplate:first");
					clone.find('.uk-button').removeClass('uk-button-success add-work-elements').addClass('uk-button-danger remove-work-elements ').html('<i class="uk-icon-minus"></i>');					
					clone.clone().appendTo("#workcontainer");					
					clone.find('.uk-button').removeClass('uk-button-danger remove-work-elements').addClass('uk-button-success add-work-elements').html('<i class="uk-icon-plus"></i>');
				});
				$(".add-edu-elements").click(function(){
					var clone = $(".div-edu .eduTemplate:first");
					clone.find('.uk-button').removeClass('uk-button-success add-edu-elements').addClass('uk-button-danger remove-edu-elements ').html('<i class="uk-icon-minus"></i>');					
					clone.clone().appendTo("#educontainer");					
					clone.find('.uk-button').removeClass('uk-button-danger remove-edu-elements').addClass('uk-button-success add-edu-elements').html('<i class="uk-icon-plus"></i>');
				});
				$(".add-addr-elements").click(function(){
					var clone = $(".div-addr .addrTemplate:first");
					clone.find('.uk-button').removeClass('uk-button-success add-addr-elements').addClass('uk-button-danger remove-addr-elements ').html('<i class="uk-icon-minus"></i>');					
					clone.clone().appendTo("#addrContainer");					
					clone.find('.uk-button').removeClass('uk-button-danger remove-addr-elements').addClass('uk-button-success add-addr-elements').html('<i class="uk-icon-plus"></i>');
				});
				
				$(".add-elements").click(function(){
					var clone = $(".div-telephone .telephoneTemplate:first");
					clone.find('.uk-button').removeClass('uk-button-success add-elements').addClass('uk-button-danger remove-elements').html('<i class="uk-icon-minus"></i>');
					clone.clone().appendTo("#telephonecontainer");
					clone.find('.uk-button').removeClass('uk-button-danger remove-elements').addClass('uk-button-success add-elements').html('<i class="uk-icon-plus"></i>');
				});
				
				$(".add-bank-elements").click(function(){
					var clone = $(".div-bank .bankTemplate:first");
					clone.find('.uk-button').removeClass('uk-button-success add-bank-elements').addClass('uk-button-danger remove-bank-elements').html('<i class="uk-icon-minus"></i>');
					clone.clone().appendTo("#bankcontainer");
					clone.find('.uk-button').removeClass('uk-button-danger remove-bank-elements').addClass('uk-button-success add-bank-elements').html('<i class="uk-icon-plus"></i>');
				});
				$(document).on("click",".remove-addr-elements",function(){
					
					$(this).closest(".addrTemplate").remove();
					
				}).on("click",".remove-elements",function(){
					
					$(this).closest(".telephoneTemplate").remove();
					
				}).on("click",".remove-bank-elements",function(){
					
					$(this).closest(".bankTemplate").remove();
					
				}).on("click",".remove-edu-elements",function(){
					
					$(this).closest(".eduTemplate").remove();
					
				}).on("click",".remove-work-elements",function(){
					
					$(this).closest(".workTemplate").remove();
					
				});
				
				$("#birthdate_eng").hide();
				$("#birthdate_th").datepicker({
				    format: "dd-mm-yyyy",
			        clearBtn: true,
			        autoclose: true,
			        todayHighlight: true
			    });
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
				$("#hiredate_eng").hide();
				$("#hiredate_th").datepicker({
				    format: "dd-mm-yyyy",
			        clearBtn: true,
			        autoclose: true,
			        todayHighlight: true
			    });
				$("#hiredate_employee").click(function(){
					if($("#hiredate_employee").text() == "Thai Year"){
						$("#hiredate_employee").text("English Year");
						$("#hiredate_th").val("");
						$("#hiredate_th").hide();
						$("#hiredate_eng").show();
					}else{
						$("#hiredate_employee").text("Thai Year");	
						$("#hiredate_eng").val("");
						$("#hiredate_eng").hide();
						$("#hiredate_th").show();
					}
				});
				
				
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
						
						'<input type="hidden" value="'+data_uri+'" name="employeemodel.profile_pic"/>';
					
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
			fn = {hasNameThaiFamilyValue: function(index){
				
				var family_first_name_th = $(".family_first_name_th:eq("+index+")").text();
				var family_last_name_th = $(".family_last_name_th:eq("+index+")").text();
				
				if(family_first_name_th != "" && family_last_name_th != ""){
					return true;
				}else{
					return false;
				}
			}
			}
			function myFunction() {
			    var pass1 = document.getElementById("pass1").value;
			    var pass2 = document.getElementById("pass2").value;
			    if (pass1 != pass2) {
			        //alert("Passwords Do not match");
			        document.getElementById("pass1").style.borderColor = "#E34234";
			        document.getElementById("pass2").style.borderColor = "#E34234";
			        
			        return false;
			    }
			    return true;
			}

		</script>		
	</body>
</html>