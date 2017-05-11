<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%@ page import="com.smict.person.data.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:เพิ่มแพทย์</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			
			<div class="uk-width-9-10">
			<%@include file="doctor-nav.jsp" %>
			<script type="text/javascript" src="js/webcam.min.js"></script>
			<form action="DoctorAddExcute" method="post"id="fpatient-quick">
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-width-4-10 padding5 uk-form" >
					<div id="my_camera2"></div>
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
							<p class="uk-text-muted uk-width-1-1">ข้อมูลส่วนตัว</p>
							<div class="uk-width-1-3 uk-text-right">รูปแพทย์: </div>
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
								<select class="uk-form-small uk-width-1-1" name="docModel.pre_name_id" required>
									<%@include file="include/prename-dd-option.jsp" %>
								</select>
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">ชื่อ : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.firstname_th"pattern="[ก-๙]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทย" class="uk-form-small uk-width-1-1">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">นามสกุล : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.lastname_th" pattern="[ก-๙]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทย"class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">ชื่อเล่น : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.nickname" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">ชื่อ EN : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.firstname_en" pattern="[A-z]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาอังกฤษ" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">นามสกุล EN : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.lastname_en" pattern="[A-z]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาอังกฤษ" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">
								<select class="uk-form-small" name="docModel.identification_type">
									<option value="1" selected>รหัสบัตรประจำตัวประชาชน</option>
									<option value="2">หมายเลขหนังสือเดินทาง</option>
								</select> :
							</div>
							<div class="uk-width-1-3">
								<input type="text" maxlength="13" name="docModel.identification" pattern="[0-9]{13}" title="ใส่ได้เฉพาะตัวเลข 0-9" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">เลขที่ใบประกอบวิชาชีพ : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.TMCLicense" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">เลขที่สัญญาจ้าง : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.contract_id" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">รหัสพนักงาน : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.emp_id" class="uk-form-small uk-width-1-1" >
							</div>
							
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">ประเภท : </div>
							<div class="uk-width-1-3">
								<select type="text" name="docModel.Title" class="uk-form-small uk-width-1-1" >
									<%@include file="include/docType-dd-option.jsp" %>
								</select>
							</div>
							<!-- <div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">Position : </div>
							<div class="uk-width-1-3">
								<select class="uk-form-small uk-width-1-1" >
									<option>ตำแหน่ง</option>
								</select>
							</div> -->
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>วันเกิด : </div>
							<div class="uk-width-1-3">
								<input type="text" name="birthdate_eng" id="birthdate_eng" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" >
								<input type="text" name="birthdate_th" id="birthdate_th" class="uk-form-small uk-width-1-1">
							</div>
							<div class="uk-width-1-3"><button id="birthdate_patient" type="button" class="btn uk-button uk-button-primary uk-button-small" >Thai Year</button></div>	
							
							<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>วันเริ่มทำงาน : </div>
							<div class="uk-width-1-3">
								<input type="text" name="hireddate" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" >
							</div>
						</div>

						<div class="uk-grid uk-grid-collapse padding5 border-gray div-telephone">
						 	<p class="uk-text-muted uk-width-1-1">ช่องทางติดต่อ </p>
						 	<div class="telephoneTemplate uk-grid uk-grid-collapse uk-width-1-1">
								
								<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>เบอร์โทรศัพท์ : </div>
								<div class="uk-width-1-3">
									<input type="text" autocomplete="off" name="telModel.multiTelNumber" id="tel_number_add" pattern="[0-9].{8,9}|(?=.*[0-9])(?=.*[-]).{8,}" title="กรอกเฉพาะตัวเลข" placeholder="เบอร์ติดต่อ" class="telnumber uk-form-small uk-width-1-1" > 
								</div>
								<div class="uk-width-1-3">
									<div class="uk-grid uk-grid-collapse">
										<div class="uk-width-2-3">
											<s:select list="telType" 
												name="telModel.multiTelTypeId" 
												class="uk-form-width-large" 
												id="branchModel_branch_code"
											/>
										</div>
										<div class="uk-width-1-3">
											<button class="uk-button uk-button-success uk-button-small add-elements" 
												type="button">
												<i class="uk-icon-plus"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
							<div id="telephonecontainer" class="div-container uk-grid uk-grid-collapse uk-width-1-1"></div>    
							<div class="uk-width-1-3 uk-text-right">Line ID : </div>
							<div  class="uk-width-1-3 uk-text-right">
								<input type="text" autocomplete="off" name="docModel.lineId" id="patline_id_add" pattern="[A-z0-9.]{1,}" placeholder="Line ID" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">E-mail : </div>
							<div  class="uk-width-1-3 uk-text-right">
								<input type="email" name="docModel.email" id="patemail_add" placeholder="E-mail" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">เบอร์โทรฉุกเฉิน: </div>
							<div class="uk-width-1-3">
								<input type="text" autocomplete="off" name="telModel.multiTelNumber" id="tel_number" pattern="[0-9]{8,10}" title="กรอกเฉพาะตัวเลข" placeholder="เบอร์ติดต่อฉุกเฉิน" class="telnumber uk-form-small uk-width-1-1"> 
							</div>
							<div class="uk-width-1-3">
								<input type="hidden" name="telModel.multiTelTypeId" value="5">
							</div>
							<div class="uk-width-1-3 uk-text-right">เจ้าของเบอร์ฉุกเฉิน: </div>
							<div class="uk-width-2-3">
								<input type="text" class="uk-form-small uk-width-1-1" 
									name="telModel.relevant_person" 
									placeholder="เจ้าของเบอร์ฉุกเฉิน">
							</div>
							<div class="uk-width-1-3 uk-text-right">ความสัมพันธ์: </div>
							<div class="uk-width-2-3">
								<input type="text" class="uk-form-small uk-width-1-1" 
									name="telModel.tel_relative" 
									placeholder="ความสัมพันธ์">
							</div>   
						</div>
						<div class="uk-grid uk-grid-collapse padding5 border-gray div-addr">
						<p class="uk-text-muted uk-width-1-1">ที่อยู่</p>
						 	<div class="addrTemplate uk-grid uk-grid-collapse uk-width-1-1">
								<div class="uk-panel uk-panel-box uk-width-1-1">
									<div class="uk-grid uk-grid-collapse uk-width-1-1">
	                           	    	<select name="docModel.addr_typeid" class="uk-form-small">
											<%@include file="include/addrtype-dd-option.jsp" %>
	                                	</select>
                                    </div>
                                    <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
	                                   <div class="uk-width-1-3"><small >เลขที่</small>
											<input type="text" maxlength="10" name="docModel.addr_no" class="uk-form-small uk-width-1-1">
	                                   </div>
	                                   <div class="uk-width-1-3"><small >หมู่บ้าน</small>
	                                   		<input type="text" maxlength="55" name="docModel.addr_village" class="uk-form-small uk-width-1-1">
	                                   </div>
	                                   <div class="uk-width-1-3"><small >ซอย</small>
	                                   		<input type="text" maxlength="100"  name="docModel.addr_alley" class="uk-form-small uk-width-1-1">
	                                   </div>
                                    </div> 
                                    <div class="uk-grid uk-grid-collapse uk-width-1-1">
                                    	<div class="uk-width-1-3"><small >หมู่</small>
	                                   		<input type="text" maxlength="10"  name="docModel.addr_bloc" class="uk-form-small uk-width-1-1">
	                                    </div>
	                                   <div class="uk-width-1-3"><small >ถนน</small>
	                                   		<input type="text" maxlength="100"  name="docModel.addr_road" class="uk-form-small uk-width-1-1">
	                                    </div>
	                                    <div class="uk-width-1-3"><small >รหัสไปรษณีย์</small>
	                                   		<input type="text" maxlength="5"  name="docModel.addr_zipcode" class="uk-form-small uk-width-1-1">
	                                    </div>
                                    </div>
                                    <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
                                    	<div class="uk-width-1-3"><small >จังหวัด</small>
	                                    	<select id="addr_provinceid" name="docModel.addr_provinceid" class="uk-form-small uk-width-1-1">
	                                    		<option value="">เลือกจังหวัด </option> 
	                                    	</select>
                                    	</div>
                                    	<div class="uk-width-1-3"><small >อำเภอ</small>
		                                   	<select id="addr_aumphurid" name="docModel.addr_aumphurid" class="uk-form-small uk-width-1-1">
		                                   		<option value="">เลือกอำเภอ</option> 
		                                   	</select>
	                                   	</div>
	                                   	<div  class="uk-width-1-3"><small >ตำบล</small>
		                                   	<select id="addr_districtid" name="docModel.addr_districtid" class="uk-form-small uk-width-1-1">
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
								<p class="uk-text-muted uk-width-1-1">ข้อมูลการศึกษา</p>
								<div class="border-gray padding5">
									<div class="div-edu">
										<div class="uk-grid eduTemplate uk-grid-collapse">
											<div class="uk-width-2-5"> ระดับการศึกษา  
												<select  class="uk-form-small  edu_id" name="docModel.education_vocabulary_id" >
													<%@include file="include/education-dd-option.jsp" %>
												</select>
											</div>
											<div class="uk-width-3-5"> ชื่อสถานศึกษา
												<input type="text" name="docModel.education_name" id="education_name" class="uk-form-small  education_name uk-width-1-2" >
												<button class="uk-button uk-button-success uk-button-small add-edu-elements" type="button"><i class="uk-icon-plus"></i></button>
											</div>
										</div>
									</div>
									<div id="educontainer" class="div-container "></div>  
								</div>
								
								<p class="uk-text-muted uk-width-1-1">ประวัติการทำงานที่ผ่านมา</p>
								<div class="border-gray padding5">
									<div class="div-work">
										<div class="uk-grid workTemplate ">
											<div class="uk-width-2-3">
												บริษัท
												<input type="text" name="docModel.location" id="location" class="uk-form-small  location uk-width-1-1" >
											</div>
											<div class="uk-width-1-3">
												ประเภทของงานที่ทำ
												<input type="text" name="docModel.work_type" id="work_type" class="uk-form-small  work_type uk-width-1-1" >
											</div>
											<div class="uk-width-1-3">
												ตำแหน่ง
												<input type="text" name="docModel.position" id="position" class="uk-form-small  position uk-width-1-1" >
											</div>
											<div class="uk-width-1-3">
												เงินเดือน
												<input type="text" name="docModel.salary" id="salary" class="uk-form-small  salary uk-width-1-1" >
											</div>
											<div class="uk-width-1-3">
												เริ่มงาน
												<input type="text" name="docModel.start_date" id="start_date" class="uk-form-small  start_date uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}">
											</div>
											<div class="uk-width-1-3">
												ออกงาน
												<input type="text" name="docModel.end_date" id="end_date" class="uk-form-small  end_date uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}">
											</div>
											<div class="uk-width-2-3">
												เหตุผลที่ออกงาน
												<input type="text" name="docModel.remark_message" id="remark_message" class="uk-form-small  remark_message uk-width-1-1" >
											</div>
											
											<div class="uk-width-1-1 uk-text-center">
												<button class="uk-button uk-button-success uk-button-small add-work-elements " 
												type="button"><i class="uk-icon-plus"></i></button>
											</div>
										</div>
									</div>
									<div id="workcontainer" class="div-container "></div>  
								</div>
								
								
								<p class="uk-text-muted uk-width-1-1">สาขาที่ลงตรวจ</p>
								<div class="uk-grid uk-grid-collapse padding5 border-gray">
									<div class="uk-width-2-10 "> 
										<a href="#select_saka" class="uk-button uk-button-primary" data-uk-modal>
											<i class="uk-icon-building"></i> เลือกสาขา
										</a>
									</div>
									<div class="uk-width-8-10">
										<div class="uk-grid uk-grid-collapse ">
											<select class="uk-width-1-1 pt" size="3" id="show_doctor_branch" name="show_doctor_branch"></select>
										</div>
									</div>
								</div>
								<!--  				modal					-->
								<div id="select_saka" class="uk-modal ">
								    <div class="uk-modal-dialog uk-form " >
								        <a class="uk-modal-close uk-close"></a>
								         <div class="uk-modal-header"><i class="uk-icon-building"></i> สาขา</div>
							         	<div class="uk-width-1-1 uk-overflow-container">
							         	 	<div class="uk-grid">
							         	 		<div class="uk-width-1-2"> </div>
							         	 		<div class="uk-width-1-2 uk-text-right">
							         	 			<div class="uk-form-icon">
												    <i class="uk-icon-search"></i>
												    <input type="text" name="t12"  placeholder="ค้นหา" class="uk-form-small uk-width-1-1">
												    </div>
												</div>
							         	 	</div>
											<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
											    <thead>
											        <tr class="hd-table"> 
											        	<th class="uk-text-center">เลือก</th>
											            <th class="uk-text-center">รหัสสาขา</th> 
											            <th class="uk-text-center">ชื่อสาขา</th>   
											        </tr>
											    </thead> 
											    <tbody>
											    	<%
											    	BranchData branchData = new BranchData();
											    	List <BranchModel> branchtList = branchData.select_branch("", "", "", "", 1);
							                        for(BranchModel branchModel : branchtList){
							                       	%>
													<tr>  
											    		<td class="uk-text-center">
												        	<div class="uk-form-controls">
					                                            <input value="<%= branchModel.getBranch_id() %>" type="checkbox" name="doctor_branch" >
				                                      		</div>
				                                      	</td>
											    		<td class="uk-text-center branch_id"><%= branchModel.getBranch_id() %></td>
												        <td class="uk-text-left branch_name"><%= branchModel.getBranch_name() %></td>  
													</tr>
													<%
							                        }
											    	%>
												</tbody>
											</table>
										</div>
								         <div class="uk-modal-footer uk-text-right">
								         	<button class="uk-modal-close">ปิด</button>
								         </div>
								    </div>
								</div>
								<!--  				modal					-->
								<p class="uk-text-muted uk-width-1-1">ผู้ดำเนินการ  </p>
								<div class="uk-grid uk-grid-collapse padding5 border-gray">
									<div class="uk-width-2-10">
										<a href="#select_branch" class="uk-button uk-button-primary" data-uk-modal>
											<i class="uk-icon-building"></i> เลือกสาขา
										</a>	
									</div>
									<div class="uk-width-8-10">
										<div class="uk-grid uk-grid-collapse ">
											<select class="uk-width-1-1 pt" size="3" id="show_doctor_boss_branch" name="show_doctor_boss_branch"> 
									        </select>
										</div>
									</div>
								</div>
								<!--  				modal					-->
								<div id="select_branch" class="uk-modal ">
								    <div class="uk-modal-dialog uk-form " >
								        <a class="uk-modal-close uk-close"></a>
								         <div class="uk-modal-header"><i class="uk-icon-building"></i> สาขา</div>
							         	<div class="uk-width-1-1 uk-overflow-container">
							         	 	<div class="uk-grid">
							         	 		<div class="uk-width-1-2"> </div>
							         	 		<div class="uk-width-1-2 uk-text-right">
							         	 			<div class="uk-form-icon">
												    <i class="uk-icon-search"></i>
												    <input type="text" name="t12"  placeholder="ค้นหา" class="uk-form-small uk-width-1-1">
												    </div>
												</div>
							         	 	</div>
											<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
											    <thead>
											        <tr class="hd-table"> 
											        	<th class="uk-text-center">เลือก</th>
											            <th class="uk-text-center">รหัสสาขา</th> 
											            <th class="uk-text-center">ชื่อสาขา</th>   
											        </tr>
											    </thead> 
											    <tbody>
											    	<%
							                        for(BranchModel branchModel : branchtList){
							                       	%>
													<tr>  
											    		<td class="uk-text-center">
												        	<div class="uk-form-controls">
					                                            <input value="<%= branchModel.getBranch_id() %>" type="checkbox" name="doctor_boss_branch" >
				                                      		</div>
				                                      	</td>
											    		<td class="uk-text-center branch_boss_id"><%= branchModel.getBranch_id() %></td>
												        <td class="uk-text-left branch_boss_name"><%= branchModel.getBranch_name() %></td>  
													</tr>
													<%
							                        }
											    	%>
												</tbody>
											</table>
										</div>
								         <div class="uk-modal-footer uk-text-right">
								         	<button class="uk-modal-close">ปิด</button> 
								         </div>
								    </div>
								</div>
								<!--  				modal					-->
								<p class="uk-text-muted uk-width-1-1">บัญชีธนาคาร</p>
								<div class="border-gray padding5">
									<div class="div-bank ">
										<div class="uk-grid bankTemplate uk-grid-collapse">
											<div class="uk-width-1-4"> เลขบัญชี  
												<input type="text" name="account_num" id="account_num" class="uk-form-small  account_num" >
											</div>
											<div class="uk-width-1-4"> ชื่อบัญชี  
												<input type="text" name="account_name" id="account_name" class="uk-form-small  account_name" >
											</div>
											<div class="uk-width-2-4"> ธนาคาร  
												<select  class="uk-form-small  bank_id" name="bank_id" >
													<%@include file="include/banktype-dd-option.jsp" %>
												</select>
												<button class="uk-button uk-button-success uk-button-small add-bank-elements" type="button"><i class="uk-icon-plus"></i></button>
											</div>
											
										</div>
									</div>
									
									<div id="bankcontainer" class="div-container "></div>    
								</div>
								
							</div>
						</div>
						<div class="uk-text-center">
							<button class="uk-button uk-button-success uk-button-large uk-icon-floppy-o" type="submit"> เพิ่มแพทย์</button>
							<a href="Doctor" class="uk-button uk-button-danger uk-button-large "><i class="uk-icon-close"></i> ยกเลิก</a>
						</div>
					</div>
				</div>
			</form>
				
			</div>
		</div>
		<script>
			$(document).on("change","select[name='docModel.addr_provinceid']",function(){
				var index = $("select[name='docModel.addr_provinceid']").index(this); //GetIndex
				$("select[name='docModel.addr_aumphurid']:eq("+index+") option[value!='']").remove();  //remove Option select amphur by index is not value =''
				$("select[name='docModel.addr_districtid']:eq("+index+") option[value!='']").remove();  //remove Option select amphur by index is not value =''
				if($(this).val() != ''){ 
					$("select[name='docModel.addr_aumphurid']:eq("+index+") option[value ='']").text("กรุณาเลือกอำเภอ");
					
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-addr-amphur.jsp", //this is my servlet 
				        data: {method_type:"get",addr_provinceid:$(this).val()},
				        async:false, 
				        success: function(result){
				        	var obj = jQuery.parseJSON(result);
				        	for(var i = 0 ;  i < obj.length;i++){
				        		$("select[name='docModel.addr_aumphurid']:eq("+index+")").append($('<option>').text(obj[i].amphur_name).attr('value', obj[i].addr_aumphurid));
				        	}
					    } 
				     });
				}else{
					$("select[name='docModel.addr_aumphurid']:eq("+index+")  option[value ='']").text("กรุณาเลือกจังหวัด");
					$("select[name='docModel.addr_districtid']:eq("+index+") option[value!='']").remove();
					$("select[name='docModel.addr_districtid']:eq("+index+") option[value ='']").text("กรุณาอำเภอ");
				}
			}).on("change","select[name='docModel.addr_aumphurid']",function(){
				var index = $("select[name='docModel.addr_aumphurid']").index(this); //GetIndex
				$("select[name='docModel.addr_districtid']:eq("+index+") option[value!='']").remove(); //remove Option select district by index is not value =''
				
				if($(this).val() != ''){
					$("select[name='docModel.addr_districtid']:eq("+index+") option[value ='']").text("กรุณาตำบล"); 
					
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-addr-district.jsp", //this is my servlet 
				        data: {method_type:"get",addr_aumphurid:$(this).val()},
				        async:false, 
				        success: function(result){
				        	var obj = jQuery.parseJSON(result);
				        	for(var i = 0 ;  i < obj.length;i++){
				        		
				        		$("select[name='docModel.addr_districtid']:eq("+index+")").append($('<option>').text(obj[i].district_name).attr('value', obj[i].district_id));
				        		
				        	}
					    } 
				     });
				}else{
					$("select[name='docModel.addr_districtid']:eq("+index+") option[value ='']").text("กรุณาอำเภอ");
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
			        	$("select[name='docModel.addr_provinceid']").append($('<option>').text(obj[i].province_name).attr('value', obj[i].addr_provinceid));
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
						
						'<input type="hidden" value="'+data_uri+'" name="docModel.profile_pic"/>';
					
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