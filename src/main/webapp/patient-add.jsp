<%@page import="com.smict.product.model.ProductModel"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="com.smict.person.data.ContactData" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%@ page import="com.smict.product.data.ProductData" %>
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
		<title>Smart Dental:เพิ่มคนไข้</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head>
	<body>
	
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10"> 
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<form action="addPatient" id="patient_form" method="post" enctype="multipart/form-data">
				<script type="text/javascript" src="js/webcam.min.js"></script>
				
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-width-4-10 padding5 uk-form" >
					<div id="my_camera2"></div>
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
						<div class="uk-width-1-1">
							<p class="uk-badge uk-badge-danger">ข้อมูลส่วนตัว</p>
						</div>
							<div class="uk-width-1-3 uk-text-right">รูปคนไข้: </div>
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
							<div class="uk-width-1-1 uk-margin-medium-top"></div>
							<div class="uk-width-1-3 uk-text-right">อัพโหลด:</div>
							<div class="uk-width-1-3 uk-text-right">
								<input type="file" name="picProfile">
							</div>
							<div class="uk-width-1-3"></div>
							<!-- <div class="uk-width-1-3 uk-text-right">HN : </div>
							<div class="uk-width-1-3"><input type="text" autocomplete="off" name="" pattern="[0-9]{1,}" class="uk-form-small uk-width-1-1" ></div>
							<div class="uk-width-1-3"></div> -->
							<div class="uk-width-1-3 uk-text-right">
								<select class="uk-form-small uk-width-1-1" name="patModel.identification_type" >
									<option value="1">รหัสประจำตัวประชาชน</option>
									<option value="2">Passport</option>
								</select></div>
							<div  class="uk-width-1-3 uk-text-right">
								<input type="text" autocomplete="off" name="patModel.identification" id="identification" pattern="[A-z 0-9]{1,}" title="ใส่ได้เฉพาะตัวเลข 0-9" maxlength="13" size="15" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3 uk-text-right">
							</div>
							<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>คำนำหน้าชื่อ : </div>
							<div class="uk-width-1-3">
								<select id="pre_name_th" class="uk-form-small uk-width-1-1" name="patModel.pre_name_id" >
										<%@include file="include/prename-dd-option.jsp" %>	
								</select>							
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>ชื่อ : </div>
							<div class="uk-width-1-3">
								<input type="text" autocomplete="off" class="uk-form-small uk-width-1-1" pattern="[ก-๙]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทย" id="first_name_th_add" name="patModel.firstname_th" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>นามสกุล : </div>
							<div class="uk-width-1-3">
								<input type="text" autocomplete="off" class="uk-form-small uk-width-1-1" pattern="[ก-๙]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทย" id="last_name_th_add" name="patModel.lastname_th">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">ชื่อเล่น : </div>
							<div class="uk-width-1-3">
								<input type="text" autocomplete="off" class="uk-form-small uk-width-1-1" pattern="[A-zก-๙]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทย" id="nickname_add" name="patModel.nickname">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">ชื่อ EN : </div>
							<div class="uk-width-1-3">
								<input type="text" autocomplete="off" class="uk-form-small uk-width-1-1" pattern="[A-z]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาอังกฤษ" id="first_name_en_add" name="patModel.firstname_en">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">นามสกุล EN : </div>
							<div class="uk-width-1-3">
								<input type="text" autocomplete="off" class="uk-form-small uk-width-1-1" id="last_name_en_add" pattern="[A-z]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาอังกฤษ" name="patModel.lastname_en">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">สถานะการแต่งงาน : </div>
							<div class="uk-width-1-3">
								<select class="uk-form-small uk-width-1-1"  name="patModel.status_married">
									<option value="1">โสด</option>
									<option value="2">แต่งงาน</option>
									<option value="3">หย่าร้าง</option>
								</select>
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>วันเกิด : </div>
							<div class="uk-width-1-3">
								<input type="text" autocomplete="off" name="birthdate_eng" id="birthdate_eng" pattern="[0-9]{1,2}-[0-9]{1,2}-[0-9]{1,4}" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" >
								<input type="text" autocomplete="off" name="birthdate_th" id="birthdate_th" pattern="[0-9]{1,2}-[0-9]{1,2}-[0-9]{1,4}" class="uk-form-small uk-width-1-1">
							</div>
							<div class="uk-width-1-3"><button id="birthdate_patient" type="button" class="btn uk-button uk-button-primary uk-button-small" >Thai Year</button></div>
							
							<div class="uk-width-1-3 uk-text-right">อายุ : </div>
							<div class="uk-width-1-3"><p id="pat_age"></p></div>
							<div class="uk-width-1-3">ปี <button type="button" id="calAge" class="uk-button uk-button-primary uk-button-small"> คำนวณอายุ</button> </div>
							<div class="uk-width-1-3 uk-text-right">อาชีพ : </div>
							<div class="uk-width-1-3"><s:textfield cssClass="uk-form-small" name="patModel.career" /></div>
						</div>
						<div class="uk-grid uk-grid-collapse padding5 border-gray div-telephone">
							<div class="uk-width-1-1">
								<p class="uk-badge uk-badge-danger">ช่องทางติดต่อ</p>
							</div>
						 	<div class="telephoneTemplate uk-grid uk-grid-collapse uk-width-1-1">
								
								<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>เบอร์โทรศัพท์ : </div>
								<div class="uk-width-1-3">
									<input type="text" autocomplete="off" name="tel_number" id="tel_number_add" pattern="[0-9].{8,9}|(?=.*[0-9])(?=.*[-]).{8,}" title="กรอกเฉพาะตัวเลข" placeholder="เบอร์ติดต่อ" class="telnumber uk-form-small uk-width-1-1" > 
								</div>
								<div class="uk-width-1-3">
									<select name="teltype" id="teltype" class="teltype uk-form-small">
									</select>
									<button class="uk-button uk-button-success uk-button-small add-elements" type="button"><i class="uk-icon-plus"></i></button>
								</div>
							</div>
							<div id="telephonecontainer" class="div-container uk-grid uk-grid-collapse uk-width-1-1"></div>    
							<div class="uk-width-1-3 uk-text-right">Line ID : </div>
							<div  class="uk-width-1-3 uk-text-right">
								<input type="text" autocomplete="off" name="patModel.line_id" id="patline_id_add" pattern="[A-z0-9.]{1,}" placeholder="Line ID" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">E-mail : </div>
							<div  class="uk-width-1-3 uk-text-right">
								<input type="email" name="patModel.email" id="patemail_add" placeholder="E-mail" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">เบอร์โทรฉุกเฉิน: </div>
							<div class="uk-width-1-3">
								<input type="text" autocomplete="off" name="patModel.emTellNumber" id="tel_number" pattern="[0-9]{8,10}" title="กรอกเฉพาะตัวเลข" placeholder="เบอร์ติดต่อฉุกเฉิน" class="telnumber uk-form-small uk-width-1-1"> 
							</div>
							<div class="uk-width-1-3">
								<input type="hidden" name="teltype" value="5">
							</div>
							<div class="uk-width-1-3 uk-text-right">เจ้าของเบอร์ฉุกเฉิน: </div>
							<div class="uk-width-2-3">
								<input type="text" class="uk-form-small uk-width-1-1" 
									name="patModel.emTellRelevantPerson" 
									placeholder="เจ้าของเบอร์ฉุกเฉิน">
							</div>
							<div class="uk-width-1-3 uk-text-right">ความสัมพันธ์: </div>
							<div class="uk-width-2-3">
								<input type="text" class="uk-form-small uk-width-1-1" 
									name="patModel.emRelative" 
									placeholder="ความสัมพันธ์">
							</div>
						</div>
						<div class="uk-grid uk-grid-collapse padding5 border-gray div-addr" id="address_wrap">
							<div class="uk-width-1-1">
								<p class="uk-badge uk-badge-danger">ที่อยู่</p>
							</div>
						 	<div class="addrTemplate uk-grid uk-grid-collapse uk-width-1-1">
								<div class="uk-panel uk-panel-box uk-width-1-1">
									<div class="uk-grid uk-grid-collapse uk-width-1-1">
	                           	    	<select name="addrModel.addr_typeid" class="uk-form-small">
											<%@include file="include/addrtype-dd-option.jsp" %>
	                                	</select>
                                    </div>
                                    <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
	                                   <div class="uk-width-1-3"><small >เลขที่</small>
											<input type="text" autocomplete="off" maxlength="10" name="addrModel.addr_no" pattern="[0-9].{0,}" class="uk-form-small uk-width-1-1">
	                                   </div>
	                                   <div class="uk-width-1-3"><small >หมู่บ้าน</small>
	                                   		<input type="text" autocomplete="off" maxlength="55" name="addrModel.addr_village" pattern="[A-zก-๙0-9].{1,}" class="uk-form-small uk-width-1-1">
	                                   </div>
	                                   <div class="uk-width-1-3"><small >ซอย</small>
	                                   		<input type="text" autocomplete="off" maxlength="100"  name="addrModel.addr_alley" pattern="[A-zก-๙0-9].{1,}" class="uk-form-small uk-width-1-1">
	                                   </div>
                                    </div> 
                                    <div class="uk-grid uk-grid-collapse uk-width-1-1">
                                    	<div class="uk-width-1-3"><small >หมู่</small>
	                                   		<input type="text" autocomplete="off" maxlength="10"  name="addrModel.addr_bloc" pattern="[0-9]"  class="uk-form-small uk-width-1-1">
	                                    </div>
	                                   <div class="uk-width-1-3"><small >ถนน</small>
	                                   		<input type="text" autocomplete="off" maxlength="100"  name="addrModel.addr_road" pattern="[A-zก-๙].{1,}" class="uk-form-small uk-width-1-1">
	                                    </div>
	                                    <div class="uk-width-1-3"><small >รหัสไปรษณีย์</small>
	                                   		<input type="text" autocomplete="off" maxlength="5"  name="addrModel.addr_zipcode" pattern="[0-9].{1,5}" class="uk-form-small uk-width-1-1" readonly>
	                                    </div>
                                    </div>
                                    <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
                                    	<div class="uk-width-1-3"><small >จังหวัด</small>
	                                    	<select id="addr_provinceid" name="addrModel.addr_provinceid" class="uk-form-small uk-width-1-1">
	                                    		<option value="0">กรุณาเลือกจังหวัด </option> 
	                                    	</select>
                                    	</div>
                                    	<div class="uk-width-1-3"><small >อำเภอ</small>
		                                   	<select id="addr_aumphurid" name="addrModel.addr_aumphurid" class="uk-form-small uk-width-1-1">
		                                   		<option value="0">กรุณาเลือกอำเภอ</option> 
		                                   	</select>
	                                   	</div>
	                                   	<div  class="uk-width-1-3"><small >ตำบล</small>
		                                   	<select id="addr_districtid" name="addrModel.addr_districtid" class="uk-form-small uk-width-1-1 selectdistrict">
		                                   		<option value="0">กรุณาเลือกตำบล</option> 
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
							<div class="uk-width-1-2 uk-form border-right">
								<div class="uk-width-1-1">
									<p class="uk-badge uk-badge-danger">ข้อมูลทางการแพทย์</p>
								</div>
								<div class="uk-grid uk-grid-collapse">
									<div class="uk-width-1-2 uk-text-right">น้ำหนัก : </div>
									<div class="uk-width-1-2">
										<input type="text" autocomplete="off" name="patModel.weight" class="uk-form-small uk-width-1-1" >
									</div>
								</div>
								<div class="uk-grid uk-grid-collapse">
									<div class="uk-width-1-2 uk-text-right">ส่วนสูง : </div>
									<div class="uk-width-1-2">
										<input type="text" autocomplete="off" name="patModel.height" class="uk-form-small uk-width-1-1" >
									</div>
								</div>
								<div class="uk-grid uk-grid-collapse">
									<div class="uk-width-1-2 uk-text-right">กรุ๊ปเลือด: </div>
									<div class="uk-width-1-2">
										<input type="text" autocomplete="off" name="patModel.bloodgroup" class="uk-form-small uk-width-1-1" >
									</div>
								</div>
								<div class="uk-grid uk-grid-collapse">
									
									<div class="template-congenital-disease uk-grid uk-grid-collapse uk-width-1-1">
										<div class="uk-width-1-2 uk-text-right">สิ่งที่คนไข้ต้องการเป็นพิเศษ </div>
										<div class="uk-width-1-2">
											<input type="text" autocomplete="off" class="uk-form-small" name="patModel.patneed_message">
											<button type="button" class="uk-button uk-button-success uk-button-small add-customer-need"><i class="uk-icon-plus"></i></button>
										</div>
										
									</div>
									<div id="container-congenital-disease" class="uk-grid uk-grid-collapse uk-width-1-1"></div>
									<div class="uk-width-1-2 uk-text-right">ประวัติแพ้ยา </div>
									<div class="uk-width-1-2">
										<a href="#lost" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" data-uk-modal>
											<i class="uk-icon-plus"></i>
										</a>
									</div>
									<select size="5" style="width:100%;" id="show_be_allergic" name="show_be_allergic">
										<s:iterator value="patModel.beallergic">
											<option value='<s:property value="product_id"/>' selected="selected"><s:property value="product_name"/> - <s:property value="product_name_en"/> </option>
										</s:iterator>
									</select>
									<p id="prg_beallergic">แพ้ยาอื่น ๆ</p><input type="text" autocomplete="off" class="uk-form-small" id="other_beallergic" name="other_beallergic" >
								</div>
								<div class="uk-grid uk-grid-collapse">
									<div class="uk-width-1-2 uk-text-right"><p>โน้ตการแพทย์</p></div>									
									<s:textarea rows="5" cols="5" name="patModel.remark" />
								</div>
							</div>
							<div class="uk-width-1-2 uk-form padding5">
								<div class="uk-grid uk-grid-collapse">
									<div class="uk-width-1-1">
										<p class="uk-badge uk-badge-danger">ประเภทการรักษา</p>
									</div>
									<div class="uk-width-1-3 uk-text-right">การรักษา : </div>
									<div class="uk-width-2-3">
										<div class="uk-grid uk-grid-collapse ">
											<select name="patModel.patient_type" size="2" style="width:100%;" >
												<option value="1">จัดฟัน</option>
												<option value="2">ทั่วไป</option> 
											</select>
										</div>
									</div>
									<div class="uk-width-1-3 uk-text-right">เวลาที่ต่อติดได้ : </div>
									<div class="uk-width-1-3">
										<div class="uk-grid uk-grid-collapse" >
											<input type="text" autocomplete="off" name="patModel.contact_time_start" data-placement="left" data-align="top" data-autoclose="true" placeholder="เริ่ม" class="uk-form-small uk-width-1-1 uk-text-center clockpicker" >
										</div>
									</div>
									<div class="uk-width-1-3">
										<div class="uk-grid uk-grid-collapse" >
											<input type="text" autocomplete="off" name="patModel.contact_time_end" data-placement="left" data-align="top" data-autoclose="true" placeholder="ถึง" class="uk-form-small uk-width-1-1 uk-text-center clockpicker" >
										</div>
									</div>
									<div class="uk-width-1-3 uk-text-right">ช่องทางแนะนำ : </div>
									<div class="uk-width-2-3">
										<div class="uk-grid uk-grid-collapse ">
											<select size="4" name="patModel.typerecommended" style="width:100%;" >
												<%  
													PatientRecommendedData recData = new PatientRecommendedData();
												    List<RecommendedModel> recommendedList = recData.select_Recommended(new RecommendedModel());
					            	         	 	for(RecommendedModel pnmd : recommendedList){
					            	         	 %>
					            	         	 	<option value="<%=pnmd.getTyperecommended()%>"><%=pnmd.getTyperecommended_name()%></option>
					            	         	 <%
					            	         	 	}
				            	         	 	 %>
												
											</select>
										</div>
									</div>
								</div> 
							</div>
						</div>
						<div class="uk-grid uk-grid-collapse padding5 border-gray uk-form">
							<div class="uk-width-1-1">
								<p class="uk-badge uk-badge-danger">การยืนยันข้อมูลก่อนทำการรักษา</p>
							</div>
							<div class="uk-width-1-3 uk-text-right padding-right10">ท่านคิดว่า ท่านแปรงฟันถูกวิธีหรือไม่ </div>
							<div class="uk-width-1-3 uk-text-left border-gray">
								<div class="uk-width-1-2 uk-text-left">
									<input type="radio" name="patModel.confirm_brush_teeth" value="1" class="uk-form-small uk-width-1-6" required="required" > ไม่ใช่
								</div>
								<div class="uk-width-1-2 uk-text-left">
									<input type="radio" name="patModel.confirm_brush_teeth" value="2" class="uk-form-small uk-width-1-6" > ใช่
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-center">
							</div>
							
							<div class="uk-width-1-3 uk-text-right padding-right10 ">ขณะนี้ท่านตั้งครรภ์หรือไม่ </div>
							<div class="uk-width-1-3 uk-text-left border-gray">
								<div class="uk-width-1-2 uk-text-left">
									<input type="radio" name="patModel.confirm_pregnant" value="1" class="uk-form-small uk-width-1-6" required="required" > ไม่ใช่
								</div>
								<div class="uk-width-1-2 uk-text-left">
									<input type="radio" name="patModel.confirm_pregnant" value="2"class="uk-form-small uk-width-1-6" > ใช่
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-center">
								<input type="text" autocomplete="off" name="patModel.week_of_pregent" pattern="[0-9]{1,3}" placeholder="จำนวนสัปดาห์" class="uk-form-small uk-width-1-1" >
							</div>
							
							<div class="uk-width-1-3 uk-text-right padding-right10 ">ยาที่ท่านได้รับอยู่ในขณะนี้ </div>
							<div class="uk-width-1-3 uk-text-left border-gray">
								<div class="uk-width-1-2 uk-text-left">
									<input type="radio" name="patModel.confirm_now_receive_drug" value="1" class="uk-form-small uk-width-1-6" required="required" > ไม่มี
								</div>
								<div class="uk-width-1-2 uk-text-left">
									<input type="radio" name="patModel.confirm_now_receive_drug" value="2" class="uk-form-small uk-width-1-6" > มี
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-center">
								<input type="text" autocomplete="off" name="patModel.drug_name" placeholder="ชื่อยา" class="uk-form-small uk-width-1-1" > 
							</div>
							
							<div class="uk-width-1-3 uk-text-right padding-right10 ">ขณะนี้ท่านได้รับการรักษาจากแพทย์ </div>
							<div class="uk-width-1-3 uk-text-left border-gray">
								<div class="uk-width-1-2 uk-text-left">
									<input type="radio" name="patModel.confirm_now_treatment" value="1" class="uk-form-small uk-width-1-6" required="required" > ไม่ใช่
								</div>
								<div class="uk-width-1-2 uk-text-left">
									<input type="radio" name="patModel.confirm_now_treatment" value="2" class="uk-form-small uk-width-1-6" > ใช่
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-center">
							</div>
							
							<div class="uk-width-1-3 uk-text-right padding-right10 ">ท่านมีแพทย์ / สถานพยาบาลประจำ ที่ให้การดูแล</div>
							<div class="uk-width-1-3 uk-text-left border-gray">
								<div class="uk-width-1-2 uk-text-left">
									<input type="radio" name="patModel.confirm_hospital_doctor_now_treatment" value="1" class="uk-form-small uk-width-1-6" required="required" > ไม่มี
								</div>
								<div class="uk-width-1-2 uk-text-left">
									<input type="radio" name="patModel.confirm_hospital_doctor_now_treatment" value="2" class="uk-form-small uk-width-1-6" > มี
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-center">
								<input type="text" autocomplete="off" name="patModel.doctor_hospital_name" placeholder="ชื่อแพทย์ / สถานพยาบาล " class="uk-form-small uk-width-1-1" >
							</div>
							
							<div class="uk-width-1-3 uk-text-right padding-right10 ">ท่านป่วยหรือมีโรคประจำตัว</div>
							<div class="uk-width-1-3 uk-text-left border-gray">
								<div class="uk-width-1-3 uk-text-left">
									<input type="radio" name="patModel.confirm_congenital" value="1" class="uk-form-small uk-width-1-6" required="required" > ไม่มี
								</div>
								<div class="uk-width-1-3 uk-text-left">
									<input type="radio" name="patModel.confirm_congenital" value="2" class="uk-form-small uk-width-1-6" > ไม่ทราบ
								</div>
								<div class="uk-width-1-3 uk-text-left">
									<input type="radio" name="patModel.confirm_congenital" value="3" class="uk-form-small uk-width-1-6" > ทราบ
								</div>
							</div>
							
							<div class="uk-width-1-2 uk-text-right padding-right10 ">โรคประจำตัว </div>
									<div class="uk-width-1-2">
										<a href="#md_congenital_disease" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" data-uk-modal>
											<i class="uk-icon-plus"></i>
										</a>
									</div>
									<select size="5" style="width:100%;" id="show_congenital_disease" name="show_congenital_disease">
									</select>
									<p id="prg_congenital_disease">โรคประจำตัวอื่น ๆ</p><input type="text" autocomplete="off" class="uk-form-small" id="other_congenital_disease" name="other_congenital_disease" >
						</div>
						<div class="uk-grid uk-grid-collapse padding5 border-gray uk-form">
							<div class="uk-width-1-1">
								<p class="uk-badge uk-badge-danger">ข้อมูลเอกสาร</p>
							</div>	
								<div class="uk-width-1-2 uk-text-right padding-right10">เอกสารที่คนไข้ต้องการ </div>
								<div class="uk-width-1-2">
										<a href="#document_need" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" data-uk-modal>
											<i class="uk-icon-plus"></i>
										</a>
								</div>
								<div class="uk-width-1-1">
									<select size="5" style="width:100%;" id="show_document_need" name="show_document_need">
									</select>
								</div>
							
						</div>
						<div class="uk-text-center">
							<button class="uk-button uk-button-success uk-button-large uk-icon-floppy-o" type="submit" id="save_addpatient"> บันทึกข้อมูล</button>
							<a href="patient.jsp" class="uk-button uk-button-danger uk-button-large "><i class="uk-icon-close"></i> ยกเลิก</a>
						</div>
						
					</div>
					
				</div>
                    <input type="hidden" id="patient_contypeid" name="contModel.sub_contact_id" value="1">
					<div id="lost" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-meh-o"></i> ประวัติการแพ้ยา</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " id="table_be_allergic">
									    <thead>
									        <tr class="hd-table"> 
									            <th class="uk-text-center">คลิก</th> 
									            <th class="uk-text-center">ชื่อ</th>
									            <th class="uk-text-center">ชื่อ ENG</th>  
									        </tr>
									    </thead> 
									    <tbody>
									    	<%
									    	List<JSONObject> ProductList = product_Data.getProduct_Profile(new ProductModel());
					                        for(JSONObject jsonProductList : ProductList){
					                       	%>
					                       	<tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="checkbox" id="form-s-c" name="patModel.be_allergic" value="<%=jsonProductList.get("product_id")%>_<%=jsonProductList.get("product_name")%>_<%=jsonProductList.get("product_name_en")%>"> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
										        <td class="uk-text-center product_name"><%=jsonProductList.get("product_name")%></td>
										        <td class="uk-text-center product_name_en"><%=jsonProductList.get("product_name_en")%></td>
											</tr> 
					                       	<%
					                        }
									    	%>
										</tbody>
									</table>
									</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" name="btn_submit_be_allergic" id="btn_submit_be_allergic">ตกลง</button>
					         </div>
					    </div>
					</div>
					
					<div id="md_congenital_disease" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-meh-o"></i> โรคประจำตัว</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " id="table_congenital_disease">
									    <thead>
									        <tr class="hd-table"> 
									            <th class="uk-text-center">คลิก</th> 
									            <th class="uk-text-center">ชื่อ</th>
									            <th class="uk-text-center">ชื่อ ENG</th>  
									        </tr>
									    </thead> 
									    <tbody>
									    	<%
									    	List<CongenitalDiseaseModel> ListCongen = congen_Data.getMasterConginentalDisease(new CongenitalDiseaseModel());
					                        for(CongenitalDiseaseModel congenModel : ListCongen){
					                       	%>
					                       	<tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="checkbox" id="congenital_disease" name="congenital_disease" value="<%=congenModel.getCongenital_id()%>_<%=congenModel.getCongenital_name_th()%>_<%=congenModel.getCongenital_name_en()%>"> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
										        <td class="uk-text-center congenital_disease_th"><%=congenModel.getCongenital_name_th()%></td>
										        <td class="uk-text-center congenital_disease_en"><%=congenModel.getCongenital_name_en()%></td>
											</tr> 
					                       	<%
					                        }
									    	%>
										</tbody>
									</table>
									</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" name="btn_submit_be_allergic" id="btn_submit_be_allergic">ตกลง</button>
					         </div>
					    </div>
					</div>
					<div id="document_need" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-meh-o"></i> เอกสารที่คนไข้ต้องการ</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " id="table_document_need">
									    <thead>
									        <tr class="hd-table"> 
									            <th class="uk-text-center">คลิก</th> 
									            <th class="uk-text-center">ชื่อ</th> 
									        </tr>
									    </thead> 
									    <tbody>
											<s:iterator value="docuList" >
												<tr>
													<td class="uk-text-center "><s:checkbox name="patModel.document_need" fieldValue="%{document_id}"  theme="simple"  /></td>
													<td class="uk-text-center doc_name"><s:property  value="doc_name" /> </td>
												</tr>
											
											</s:iterator>
										</tbody>
									</table>
									</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" name="btn_submit_be_allergic" id="btn_submit_be_allergic">ตกลง</button>
					         </div>
					    </div>
					</div>					
					
					</form>	
			</div>
		</div>
		<script>
			$(document).on("click",".remove-elements",function(){
				
				$(this).closest(".telephoneTemplate").remove();
				
			}).on("click",".remove-congenital-disease",function(){
				
				$(this).closest(".template-congenital-disease").remove();
				
			}).on("change","input[name='patModel.be_allergic']",function(){
				
				var index = $("input[name='patModel.be_allergic']").index(this);
				var product_name = $(".product_name:eq("+index+")").text();
				var product_name_en = $(".product_name_en:eq("+index+")").text();
				if(this.checked){
						if(product_name_en == "Other"){
							$("#prg_beallergic").show();
							$("#other_beallergic").show();
						}
					
					$("select[name='show_be_allergic']").append($('<option>').text(product_name+" - "+product_name_en).attr('value', $(this).val()));
				}else{
					if(product_name_en == "Other"){
						$("#prg_beallergic").hide();
						$("#other_beallergic").hide();
						$("#other_beallergic").val("");
					}
					
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
				
			}).on("change","input[name='patModel.document_need']",function(){
				
				var index = $("input[name='patModel.document_need']").index(this);
				var docuname = $(".doc_name:eq("+index+")").text();
				if(this.checked){
					$("select[name='show_document_need']").append($('<option>').text(docuname).attr('value', $(this).val()));
				}else{
					$("select[name='show_document_need'] option[value='"+$(this).val()+"']").remove();
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
					// alert($(this).val());
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
				$("#prg_beallergic").hide();
				$("#other_beallergic").hide();
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
					 
				})
				$(document).on('change', '.selectdistrict', function(event) {
					event.preventDefault();
					/* Act on the event */
					var ind = $('.selectdistrict').index(this);
					$.ajax({
						url: 'ajax/ajax-addr-zipcode.jsp',
						type: 'post',
						dataType: 'json',
						data: {method_type:"get",'district_id': $(this).val()},
					})
					.done(function(data, xhr, status) {
						// console.log(data[0].zipcode);
						$('input[name="addrModel.addr_zipcode"]').eq(ind).val(data[0].zipcode);
						// alert($('.selectdistrict').index(this));
					})
					.fail(function() {
						console.log("error");
					})
					.always(function() {
						console.log("complete");
					});
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
				$("#table_document_need").DataTable();
				
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
					},calAgeByBirthDate: function(dob){
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
						  var days = Math.floor((now.getTime() - dob.getTime()) / 1000 / 60 / 60 / 24);
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