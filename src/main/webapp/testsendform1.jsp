<%@page import="com.smict.person.data.CongenitalData"%>
<%@page import="com.smict.person.model.CongenitalDiseaseModel"%>
<%@page import="com.smict.product.model.ProductModel"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="com.smict.person.data.ContactData" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%@ page import="com.smict.product.data.ProductData" %>
<%@ page import="com.smict.person.data.FamilyData" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	ProductData product_Data = new ProductData();
	CongenitalData congen_Data = new CongenitalData();
%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:เพิ่มคนไข้</title>
	</head> 
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<form action="addPatient" id="patient_form" method="post">
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-width-4-10 padding5 uk-form" >
					
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
						<p class="uk-text-muted uk-width-1-1">ข้อมูลส่วนตัว</p>
							<div class="uk-width-1-3 uk-text-right">รูปคนไข้ : </div>
							<div class="uk-width-1-3"><img src="img/IMG_0846.JPG" alt="No Profile Picture" class="profile-pic"></div>
							<div class="uk-width-1-3">
								<button class="uk-button uk-button-primary uk-icon-camera"> Take Photo</button>
								<input type="file" class="uk-width-1-1">
							</div>
							<div class="uk-width-1-3 uk-text-right">HN : </div>
							<div class="uk-width-1-3">60022200</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">รหัสประจำตัวคนไข้ : </div>
							<div  class="uk-width-1-3 uk-text-right">
								<input type="text" name="patModel.identification" pattern="[0-9]{1,}" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"> 
								<select class="uk-form-small uk-width-1-1" name="patModel.identification_type" >
									<option value="1">รหัสประจำตัวประชาชน</option>
									<option value="2">Passport</option>
								</select>
							</div>
							<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>คำนำหน้าชื่อ : </div>
							<div class="uk-width-1-3">
								<select class="uk-form-small uk-width-1-1" name="patModel.pre_name_id" >
									<option value="1">นาย</option>
									<option value="2">นาง</option>
									<option value="3">นางสาว</option>
									<option value="4">เด็กชาย</option>
									<option value="5">เด็กหญิง</option>
								</select>
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>ชื่อ : </div>
							<div class="uk-width-1-3">
								<input type="text" class="uk-form-small uk-width-1-1" pattern="[ก-๙]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทย" id="first_name_th" name="patModel.first_name_th" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>นามสกุล : </div>
							<div class="uk-width-1-3">
								<input type="text" class="uk-form-small uk-width-1-1" pattern="[ก-๙]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทย" id="last_name_th" name="patModel.last_name_th">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">ชื่อ EN : </div>
							<div class="uk-width-1-3">
								<input type="text" class="uk-form-small uk-width-1-1" pattern="[A-z]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาอังกฤษ" id="first_name_en" name="patModel.first_name_en">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">นามสกุล EN : </div>
							<div class="uk-width-1-3">
								<s:textfield placeholder="รหัสประจำตัวประชาชน" name="patModel.last_name_en" id="citizen_id"/>
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">สถานะการแต่งงาน : </div>
							<div class="uk-width-1-3">
								<select class="uk-form-small uk-width-1-1"  name="patModel.status_married">
									<option>โสด</option>
									<option>แต่งงาน</option>
									<option>หย่าร้าง</option>
								</select>
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>วันเกิด : </div>
							<div class="uk-width-1-3">
								<input type="text" name="birthdate_eng" id="birthdate_eng" pattern="[0-9]{1,2}-[0-9]{1,2}-[0-9]{1,4}" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" >
								<input type="text" name="birthdate_th" id="birthdate_th" pattern="[0-9]{1,2}-[0-9]{1,2}-[0-9]{1,4}" class="uk-form-small uk-width-1-1">
							</div>
							<div class="uk-width-1-3"><button id="birthdate_patient" type="button" class="btn uk-button uk-button-primary uk-button-small" >Thai Year</button></div>
							
						</div>
						<div class="uk-grid uk-grid-collapse padding5 border-gray div-telephone">
						<p class="uk-text-muted uk-width-1-1 ">ที่อยู่และเบอร์โทรศัพท์</p>
						 	<div class="telephoneTemplate uk-grid uk-grid-collapse uk-width-1-1">
								<div class="uk-width-1-3 uk-text-right"><span class="red">*</span>เบอร์โทรศัพท์ : </div>
								<div class="uk-width-1-3">
									<input type="text" name="patModel.tel_number" id="tel_number" pattern="[0-9]{8,10}" title="กรอกข้อมูลไม่ถูกต้อง" placeholder="เบอร์ติดต่อ" class="telnumber uk-form-small uk-width-1-1" > 
								</div>
								<div class="uk-width-1-3">
									<select name="patModel.teltype" id="teltype" class="teltype uk-form-small">
										<option value="0">ประเภทเบอร์โทร</option>
									</select>
									<button class="uk-button uk-button-success uk-button-small add-elements" type="button"><i class="uk-icon-plus"></i></button>
								</div>
							</div>
							<div id="telephonecontainer" class="div-container uk-grid uk-grid-collapse uk-width-1-1"></div>    
							<div class="uk-width-1-3 uk-text-right">Line ID : </div>
							<div  class="uk-width-1-3 uk-text-right">
								<input type="text" name="patModel.line_id" id="patline_id" placeholder="Line ID" class="uk-form-small uk-width-1-1" >
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">E-mail : </div>
							<div  class="uk-width-1-3 uk-text-right">
								<input type="email" name="patModel.email" id="patemail" placeholder="E-mail" class="uk-form-small uk-width-1-1" >
							</div>
					 		<div class="uk-width-1-3"></div>
							<div class="uk-width-3-3 uk-text-center"> 
									<a href="#address" class="uk-button uk-button-primary uk-button-small" data-uk-modal>
										<i class="uk-icon-pencil"></i> แก้ไขที่อยู่
									</a>
							</div>
						</div>
					</div>
					<div class="uk-width-6-10 padding5">
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
							<div class="uk-width-1-2 uk-form">
								<p class="uk-text-muted uk-width-1-1">ข้อมูลสมาชิก</p>
								<div class="uk-grid uk-grid-collapse ">
									<div class="uk-width-1-3 uk-text-right">ประเภทสมาชิก : </div>
									<div class="uk-width-2-3"> 
										<a href="#member" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" data-uk-modal>
											<i class="uk-icon-plus"></i>
										</a>
										<button id="remove_patient_contype" class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select id="show_patient_type" name="show_patient_type" class="uk-width-1-1" size="5">
										</select>
									</div >
								</div>
							</div>
						</div>
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
							<div class="uk-width-1-2 uk-form border-right">
								<p class="uk-text-muted uk-width-1-1">ข้อมูลทางการแพทย์</p>
								<div class="uk-grid uk-grid-collapse">
									<div class="uk-width-1-2 uk-text-right">น้ำหนัก : </div>
									<div class="uk-width-1-2">
										<input type="text" name="patModel.weight" class="uk-form-small uk-width-1-1" >
									</div>
								</div>
								<div class="uk-grid uk-grid-collapse">
									<div class="uk-width-1-2 uk-text-right">ส่วนสูง : </div>
									<div class="uk-width-1-2">
										<input type="text" name="patModel.height" class="uk-form-small uk-width-1-1" >
									</div>
								</div>
								<div class="uk-grid uk-grid-collapse">
									<div class="uk-width-1-2 uk-text-right">กรุ๊ปเลือด: </div>
									<div class="uk-width-1-2">
										<input type="text" name="patModel.bloodgroup" class="uk-form-small uk-width-1-1" >
									</div>
								</div>
								<div class="uk-grid uk-grid-collapse">
									
									<div class="template-congenital-disease uk-grid uk-grid-collapse uk-width-1-1">
										<div class="uk-width-1-2 uk-text-right">สิ่งที่คนไข้ต้องการเป็นพิเศษ </div>
										<div class="uk-width-1-2">
											<input type="text" class="uk-form-small" name="customer_need" >
											<button type="button" class="uk-button uk-button-success uk-button-small add-customer-need"><i class="uk-icon-plus"></i></button>
										</div>
										
									</div>
									<div class="uk-width-1-2 uk-text-right">ประวัติแพ้ยา </div>
									<div class="uk-width-1-2">
										<a href="#lost" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" data-uk-modal>
											<i class="uk-icon-plus"></i>
										</a>
									</div>
									<select size="5" style="width:100%;" id="show_be_allergic" name="show_be_allergic">
									</select>
									
								</div>
							</div>
							<div class="uk-width-1-2 uk-form padding5">
								<div class="uk-grid uk-grid-collapse">
									<p class="uk-text-muted uk-width-1-1">ครอบครัว</p>
									<div class="uk-width-1-3 uk-text-right">สมาชิกในครอบครัว </div>
									<div class="uk-width-2-3">
										<div class="uk-grid uk-grid-collapse ">
											<input type="text" id="ref_family_name" name="ref_family_name" class="uk-form-small uk-width-6-10" >
											<div class="uk-width-4-10"> 
												<a href="#family" id="btn_call_modalFamily" class="uk-button uk-button-primary uk-width-4-10 uk-button-small" data-uk-modal>
													<i class="uk-icon-search"></i>
												</a>
												<a id="remove_family" class="uk-button uk-button-danger uk-width-4-10 uk-button-small">
													<i class="uk-icon-close"></i>
												</a>
											</div>
										</div>
									</div>
								</div>
								<div class="uk-grid uk-grid-collapse ">
								<p class="uk-text-muted uk-width-1-1">สมาชิกในครอบครัว</p>
								<select size="5" style="width:100%;" id="family_member" name="family_member" >
								</select>
								</div> 
								<hr>
								<div class="uk-grid uk-grid-collapse">
									<p class="uk-text-muted uk-width-1-1">ประเภทการรักษา</p>
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
											<input type="text" name="patModel.contact_time_start" placeholder="เริ่ม" class="uk-form-small uk-width-1-1 uk-text-center" >
										</div>
									</div>
									<div class="uk-width-1-3">
										<div class="uk-grid uk-grid-collapse" >
											<input type="text" name="patModel.contact_time_end" placeholder="ถึง" class="uk-form-small uk-width-1-1 uk-text-center" >
										</div>
									</div>
									<div class="uk-width-1-3 uk-text-right">ช่องทางแนะนำ : </div>
									<div class="uk-width-2-3">
										<div class="uk-grid uk-grid-collapse ">
											<select size="4" name="patModel.typerecommended" style="width:100%;" >
												<option value="1">อินเทอร์เน็ต</option>
												<option value="2">โฆษณา</option> 
												<option value="3">ลูกค้าท่านอื่น</option>
												<option value="4">ใบปลิว</option>
											</select>
										</div>
									</div>
								</div> 
							</div>
						</div>
						<div class="uk-grid uk-grid-collapse padding5 border-gray uk-form">
							<p class="uk-text-muted uk-width-1-1">การยืนยันข้อมูลก่อนทำการรักษา</p>
							<div class="uk-width-1-3 uk-text-right ">ท่านคิดว่า ท่านแปลงฟันถูกวิธีหรือไม่ </div>
							<div class="uk-width-1-3 uk-text-center">
								
								<input type="radio" name="patModel.confirm_brush_teeth" value="1" class="uk-form-small uk-width-1-6" > ไม่ใช่
								<input type="radio" name="patModel.confirm_brush_teeth" value="2" class="uk-form-small uk-width-1-6" > ใช่
							</div>
							<div class="uk-width-1-3 uk-text-center">
							</div>
							
							<div class="uk-width-1-3 uk-text-right ">ขณะนี้ท่านตั้งครรภ์หรือไม่ </div>
							<div class="uk-width-1-3 uk-text-center">
								<input type="radio" name="patModel.confirm_pregnant" value="1" class="uk-form-small uk-width-1-6" > ไม่ใช่
								<input type="radio" name="patModel.confirm_pregnant" value="2"class="uk-form-small uk-width-1-6" > ใช่
							</div>
							<div class="uk-width-1-3 uk-text-center">
								<input type="text" name="patModel.week_of_pregent" pattern="[0-9]{1,3}" placeholder="จำนวนสัปดาห์" class="uk-form-small uk-width-1-1" >
							</div>
							
							<div class="uk-width-1-3 uk-text-right ">ยาที่ท่านได้รับอยู่ในขณะนี้ </div>
							<div class="uk-width-1-3 uk-text-center">
								<input type="radio" name="patModel.confirm_now_receive_drug" value="1" class="uk-form-small uk-width-1-6" > ไม่มี
								<input type="radio" name="patModel.confirm_now_receive_drug" value="2" class="uk-form-small uk-width-1-6" > มี
							</div>
							<div class="uk-width-1-3 uk-text-center">
								<input type="text" name="patModel.drug_name" placeholder="ชื่อยา" class="uk-form-small uk-width-1-1" > 
							</div>
							
							<div class="uk-width-1-3 uk-text-right ">ขณะนี้ท่านได้รับการรักษาจากแพทย์ </div>
							<div class="uk-width-1-3 uk-text-center">
								<input type="radio" name="patModel.confirm_brush_teeth" value="1" class="uk-form-small uk-width-1-6" > ไม่ใช่
								<input type="radio" name="patModel.confirm_brush_teeth" value="2" class="uk-form-small uk-width-1-6" > ใช่
							</div>
							<div class="uk-width-1-3 uk-text-center">
							</div>
							
							<div class="uk-width-1-3 uk-text-right ">ท่านมีแพทย์ / สถานพยาบาลประจำ ที่ให้การดูแล</div>
							<div class="uk-width-1-3 uk-text-center">
								<input type="radio" name="patModel.confirm_hospital_doctor_now_treatment" value="1" class="uk-form-small uk-width-1-6" > ไม่มี
								<input type="radio" name="patModel.confirm_hospital_doctor_now_treatment" value="2" class="uk-form-small uk-width-1-6" > มี
							</div>
							<div class="uk-width-1-3 uk-text-center">
								<input type="text" name="patModel.doctor_hospital_name" placeholder="ชื่อแพทย์ / สถานพยาบาล " class="uk-form-small uk-width-1-1" >
							</div>
							
							<div class="uk-width-1-3 uk-text-right ">ท่านป่วยหรือมีโรคประจำตัว</div>
							<div class="uk-width-1-3 uk-text-center">
								<input type="radio" name="patModel.confirm_congenital" value="1" class="uk-form-small uk-width-1-6" > ไม่มี
								<input type="radio" name="patModel.confirm_congenital" value="2" class="uk-form-small uk-width-1-6" > ไม่ทราบ
								<input type="radio" name="patModel.confirm_congenital" value="3" class="uk-form-small uk-width-1-6" > ทราบ
							</div>
							
							<div class="uk-width-1-2 uk-text-right">โรคประจำตัว </div>
									<div class="uk-width-1-2">
										<a href="#md_congenital_disease" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" data-uk-modal>
											<i class="uk-icon-plus"></i>
										</a>
									</div>
									<select size="5" style="width:100%;" id="show_congenital_disease" name="show_congenital_disease">
									</select>
									<p id="prg_congenital_disease">โรคประจำตัวอื่น ๆ</p><input type="text" class="uk-form-small" id="other_congenital_disease" name="other_congenital_disease" >
						</div>
						<div class="uk-text-center">
							<button class="uk-button uk-button-success uk-button-large uk-icon-floppy-o" type="submit"> เพิ่มคนไข้</button>
							<a href="patient.jsp" class="uk-button uk-button-danger uk-button-large "><i class="uk-icon-close"></i> ยกเลิก</a>
						</div>
						
					</div>
					
				</div>
				
				
				<div id="member" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-group"></i> ประเภทสมาชิก</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
					         		<ul class="uk-subnav uk-subnav-pill" data-uk-switcher="{connect:'#subnav-pill-content-1'}">
					         		<%
					         		ContactData contact_Data = new ContactData();
					         		List<JSONObject> List_contactname = contact_Data.getContactnameList("", "");
					         		int runround = 0;
					         		for(JSONObject jsobListContactName : List_contactname){
					         		%>
					         			<li <%if(runround == 0){ %>class="uk-active" aria-expanded="true"<%}else{%>class="" aria-expanded="false"<%}%> ><a href="#"><%=jsobListContactName.get("contact_name").toString() %></a></li>
					         		<%
					         		runround++;
					         		}
					         		%>
		                            </ul>
		                            
		                            <!-- Start -->
		                            <ul id="subnav-pill-content-1" class="uk-switcher">
		                            <%
					         		int round = 0;
		                            
		                            for(JSONObject jsonListContactName : List_contactname){
					         		%>
					         			<li <%if(round == 0){%>class="uk-active" aria-hidden="false"<%}else{%>class="" aria-hidden="true"<% } %>>
											<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
											    <thead>
											        <tr class="hd-table"> 
											            <th class="uk-text-center">คลิก</th> 
											            <th class="uk-text-center">ชื่อ</th> 
											        </tr>
											    </thead> 
											    <tbody>
											    
											    <%
											    List<JSONObject> ListSubContact = contact_Data.getSubContactnameList(jsonListContactName.get("contact_id").toString(), "", "", "");
											    if(!ListSubContact.isEmpty()){
											    	
											    	for(JSONObject jsonobj : ListSubContact){
											    %>
											    	<tr> 
												        <td class="uk-text-center">
												        	<div class="uk-form-controls">	
					                                            <input type="radio" id="patient_contypeid" name="patient_contypeid" value="<%=jsonobj.get("sub_contact_id")%>"> <label for="form-s-c"></label>
		                                        			</div>
		                                        		</td>
												        <td class="uk-text-center patient_typename"><%=jsonobj.get("sub_contact_name")%></td>
													</tr>
											    <%
											    	}
											    }
											    %>
												</tbody>
											</table>
										</li>
					         		<%
					         		round++;
					         		}
					         		%>
					         		</ul>
		                           	<!-- End -->
		                            
									</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" name="btn_submit_patienttype" id="btn_submit_patienttype">ตกลง</button>
					         </div>
					    </div>
					</div> 
					
					<div id="address" class="uk-modal ">
					    <div class="uk-modal-dialog uk-modal-dialog-large uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><h3><i class="uk-icon-street-view"></i> ที่อยู่</h3></div>
					         	<div class="uk-grid uk-width-1-1 uk-overflow-container"> 
		                                <div class="uk-width-1-2">
		                                    <div class="uk-panel uk-panel-box">
		                                        <h3 class="uk-panel-title">ที่อยู่ 1</h3> 
		                                        <select name="addr_typeid">
		                                        	<option>กรุณาเลือกประเภทที่อยู่คนไข้</option>
		                                        </select>
		                                        <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
				                                     <div class="uk-width-1-3"><input type="text" name="addr_no" placeholder="เลขที่" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><input type="text" name="addr_bloc" placeholder="หมู่" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><input type="text" name="addr_village" placeholder="หมู่บ้าน" class="uk-form-small"></div>
		                                        </div> 
		                                        <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
				                                     <div class="uk-width-1-3"><input type="text" name="addr_alley" placeholder="ซอย" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><input type="text" name="addr_road" placeholder="ถนน" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><select name="addr_districtid" class="uk-form-small" ><option value="0">กรุณาเลือกอำเภอ</option> </select></div>
				                                      
		                                        </div>
		                                        <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
		                                        	 <div class="uk-width-1-3"><select name="addr_aumphurid" class="uk-form-small"> <option value="0">กรุณาเลือกจังหวัด</option> </select></div>
				                                     <div class="uk-width-1-3"><select name="addr_provinceid" class="uk-form-small"><option value="0">กรุณาเลือกจังหวัด</option> </select></div>
				                                     <div class="uk-width-1-3"><input type="text" name="zipcode" placeholder="รหัสไปรษณีย์" class="uk-form-small"></div> 
		                                        </div>
		                                    </div>
		                                </div> 
		                                <div class="uk-width-1-2">
		                                    <div class="uk-panel uk-panel-box">
		                                        <h3 class="uk-panel-title">ที่อยู่ 2</h3>
		                                        <select name="addr_typeid">
		                                        	<option>กรุณาเลือกประเภทที่อยู่คนไข้</option>
		                                        </select>
		                                        <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
				                                     <div class="uk-width-1-3"><input type="text" name="addr_no" placeholder="เลขที่" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><input type="text" name="addr_bloc" placeholder="หมู่" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><input type="text" name="addr_village" placeholder="หมู่บ้าน" class="uk-form-small"></div>
		                                        </div> 
		                                        <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
				                                     <div class="uk-width-1-3"><input type="text" name="addr_alley" placeholder="ซอย" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><input type="text" name="addr_road" placeholder="ถนน" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><select name="addr_districtid" class="uk-form-small" ><option value="0">กรุณาเลือกอำเภอ</option> </select></div>
				                                      
		                                        </div>
		                                        <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
		                                        	 <div class="uk-width-1-3"><select name="addr_aumphurid" class="uk-form-small"> <option value="0">กรุณาเลือกจังหวัด</option> </select></div>
				                                     <div class="uk-width-1-3"><select name="addr_provinceid" class="uk-form-small"><option value="0">กรุณาเลือกจังหวัด</option> </select></div>
				                                     <div class="uk-width-1-3"><input type="text" name="zipcode" placeholder="รหัสไปรษณีย์" class="uk-form-small"></div> 
		                                        </div>
		                                    </div>
		                                </div>
								</div>       
		                        <div class="uk-grid uk-width-1-1 uk-overflow-container"> 
		                                <div class="uk-width-1-2">
		                                    <div class="uk-panel uk-panel-box">
		                                        <h3 class="uk-panel-title">ที่อยู่ 3</h3>
		                                        <select name="addr_typeid">
		                                        	<option>กรุณาเลือกประเภทที่อยู่คนไข้</option>
		                                        </select>
		                                        <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
				                                     <div class="uk-width-1-3"><input type="text" name="addr_no" placeholder="เลขที่" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><input type="text" name="addr_bloc" placeholder="หมู่" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><input type="text" name="addr_village" placeholder="หมู่บ้าน" class="uk-form-small"></div>
		                                        </div> 
		                                        <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
				                                     <div class="uk-width-1-3"><input type="text" name="addr_alley" placeholder="ซอย" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><input type="text" name="addr_road" placeholder="ถนน" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><select name="addr_districtid" class="uk-form-small" ><option value="0">กรุณาเลือกอำเภอ</option> </select></div>
				                                      
		                                        </div>
		                                        <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
		                                        	 <div class="uk-width-1-3"><select name="addr_aumphurid" class="uk-form-small"> <option value="0">กรุณาเลือกจังหวัด</option> </select></div>
				                                     <div class="uk-width-1-3"><select name="addr_provinceid" class="uk-form-small"><option value="0">กรุณาเลือกจังหวัด</option> </select></div>
				                                     <div class="uk-width-1-3"><input type="text" name="zipcode" placeholder="รหัสไปรษณีย์" class="uk-form-small"></div> 
		                                        </div>
		                                    </div>
		                                </div>
		                                <div class="uk-width-1-2">
		                                    <div class="uk-panel uk-panel-box">
		                                        <h3 class="uk-panel-title">ที่อยู่ 4</h3>
		                                        <select name="addr_typeid">
		                                        	<option>กรุณาเลือกประเภทที่อยู่คนไข้</option>
		                                        </select>
		                                        <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
				                                     <div class="uk-width-1-3"><input type="text" name="addr_no" placeholder="เลขที่" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><input type="text" name="addr_bloc" placeholder="หมู่" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><input type="text" name="addr_village" placeholder="หมู่บ้าน" class="uk-form-small"></div>
		                                        </div> 
		                                        <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
				                                     <div class="uk-width-1-3"><input type="text" name="addr_alley" placeholder="ซอย" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><input type="text" name="addr_road" placeholder="ถนน" class="uk-form-small"></div>
				                                     <div class="uk-width-1-3"><select name="addr_districtid" class="uk-form-small" ><option value="0">กรุณาเลือกอำเภอ</option> </select></div>
				                                      
		                                        </div>
		                                        <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
		                                        	 <div class="uk-width-1-3"><select name="addr_aumphurid" class="uk-form-small"> <option value="0">กรุณาเลือกจังหวัด</option> </select></div>
				                                     <div class="uk-width-1-3"><select name="addr_provinceid" class="uk-form-small"><option value="0">กรุณาเลือกจังหวัด</option> </select></div>
				                                     <div class="uk-width-1-3"><input type="text" name="zipcode" placeholder="รหัสไปรษณีย์" class="uk-form-small"></div> 
		                                        </div>
		                                    </div>
		                                </div>
								</div> 
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" name="btn_submit_addr" id="btn_submit_addr">ตกลง</button>
					         </div>
					    </div>
					</div>
					
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
			                                            <input type="checkbox" id="form-s-c" name="be_allergic" value="<%=jsonProductList.get("product_id")%>"> <label for="form-s-c"></label>
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
									    	List<CongenitalDiseaseModel> ListCongen = congen_Data.getConginentalDisease(new CongenitalDiseaseModel());
					                        for(CongenitalDiseaseModel congenModel : ListCongen){
					                       	%>
					                       	<tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="checkbox" id="congenital_disease" name="congenital_disease" value="<%=congenModel.getCongenital_id()%>"> <label for="form-s-c"></label>
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
				//congenital_disease
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
				//
				var index = $("input[name='patient_contypeid']").index(this);
				var patient_typename = $(".patient_typename:eq("+index+")").text();
				
				$("select[name='show_patient_type']").append($('<option>').text(patient_typename).attr('value', $(this).val()));
				$("select[name='show_patient_type'] option[value!='"+$(this).val()+"']").remove();
				
			}).on("change","select[name='addr_provinceid']",function(){
				
				var index = $("select[name='addr_provinceid']").index(this); //GetIndex
				
				$("select[name='addr_aumphurid']:eq("+index+") option[value!='0']").remove();  //remove Option select amphur by index is not value =''
				if($(this).val() != '0'){
					$("select[name='addr_aumphurid']:eq("+index+") option[value ='0']").text("กรุณาเลือกอำเภอ");
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-addr-amphur.jsp", //this is my servlet 
				        data: {method_type:"get",province_id:$(this).val()},
				        async:false, 
				        success: function(result){
				        	var obj = jQuery.parseJSON(result);
				        	for(var i = 0 ;  i < obj.length;i++){
				        		
				        		$("select[name='addr_aumphurid']:eq("+index+")").append($('<option>').text(obj[i].amphur_name).attr('value', obj[i].amphur_id));
				        		
				        	}
					    } 
				     });
				}else{
					$("select[name='addr_aumphurid']:eq("+index+") option[value ='0']").text("กรุณาเลือกจังหวัด");
					$("select[name='addr_districtid']:eq("+index+") option[value!='0']").remove();
					$("select[name='addr_districtid']:eq("+index+") option[value ='0']").text("กรุณาเลือกอำเภอ");
				}
			}).on("change","select[name='addr_aumphurid']",function(){
				
				var index = $("select[name='addr_aumphurid']").index(this); //GetIndex
				
				$("select[name='addr_districtid']:eq("+index+") option[value!='0']").remove(); //remove Option select district by index is not value =''
				
				if($(this).val() != '0'){
					$("select[name='addr_districtid']:eq("+index+") option[value ='0']").text("กรุณาเลือกตำบล");
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-addr-district.jsp", //this is my servlet 
				        data: {method_type:"get",amphur_id:$(this).val()},
				        async:false, 
				        success: function(result){
				        	var obj = jQuery.parseJSON(result);
				        	for(var i = 0 ;  i < obj.length;i++){
				        		
				        		$("select[name='addr_districtid']:eq("+index+")").append($('<option>').text(obj[i].district_name).attr('value', obj[i].district_id));
				        		
				        	}
					    } 
				     });
				}else{
					$("select[name='addr_districtid']:eq("+index+") option[value ='0']").text("กรุณาเลือกอำเภอ");
				}
			}).ready(function(){
				$("#prg_congenital_disease").hide();
				$("#other_congenital_disease").hide();
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
			        data: {method_type:"get",province_id:""},
			        async:false, 
			        success: function(result){
			        	var obj = jQuery.parseJSON(result);
			        	for(var i = 0 ;  i < obj.length;i++){
			        		
			        		$("select[name='addr_provinceid']").append($('<option>').text(obj[i].province_name).attr('value', obj[i].province_id));
			        		
			        	}
				    } 
			     });
				
				$(".add-customer-need").click(function(){
					var clone = $(".template-congenital-disease").eq(0);
					clone.find('.uk-button').removeClass('uk-button-success add-customer-need').addClass('uk-button-danger remove-congenital-disease').html('<i class="uk-icon-minus"></i>');
					clone.clone().appendTo("#container-congenital-disease");
					clone.find('.uk-button').removeClass('uk-button-danger remove-congenital-disease').addClass('uk-button-success add-customer-need').html('<i class="uk-icon-plus"></i>');
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
				
				$("#table_be_allergic").DataTable();
				$("#table_congenital_disease").DataTable();
				$("#family_table").DataTable();
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
					
					if(!fn.hasValuePatientName() && !fn.hasValueBirthDate() && !fn.hasValueContact()){
						e.preventDefault();
						swal({
			    			title: 'กรอกข้อมูลไม่ครบ',
			    			text: "คุณจำเป็นต้องกรอกข้อมูล ชื่อ - นามสกุล , วัน - เดือน - ปีเกิด , เบอรโทรศัพท์ หรือ IDLINE หรือ Email",
			    			type: 'warning'
			    		})
			    		
					}
					
				})
				
				fn = {
					hasValuePatientName: function() {
						var first_name_th = $("#first_name_th").val();
						var last_name_th = $("#last_name_th").val();
						var first_name_en = $("#first_name_en").val();
						var last_name_en = $("#last_name_en").val();
						var hasValue = false;
						if(first_name_th != "" || last_name_th != "" && first_name_en != "" || last_name_en != ""){
							hasValue = true;
						}
						return hasValue;
					},
					hasValueBirthDate: function(){
						var birthdate_eng = $("#birthdate_eng").val();
						var birthdate_th = $("#birthdate_th").val();
						var hasValue = false;
						if(birthdate_eng != "" && birthdate_th != ""){
							hasValue = true;
						}
						return hasValue;
					},
					hasValueContact : function(){
						var tel_number = $("#tel_number").val();
						var patline_id = $("#patline_id").val();
						var patemail = $("#patemail").val();
						var hasValue = false;
						if(tel_number != "" && patline_id != "" && patemail != ""){
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
					}
				
				
				}
				
				
				
			});
		</script>		
	</body>
</html>