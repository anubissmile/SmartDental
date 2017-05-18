<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
				<form action="update-doctory-by-id" method="post" id="fpatient-quick" enctype="multipart/form-data">
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-width-4-10 padding5 uk-form" >
					<div id="my_camera2">
						<input type="hidden" value="<s:property value="docModel.profile_pic"/>" name="docModel.profile_pic"/>
					</div>
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
						<a class="uk-button uk-button_success" href="Doctor" >
						<i class="uk-icon-user-md"></i> เลือกแพทย์</a>
						<p class="uk-text-muted uk-width-1-1">ข้อมูลส่วนตัว</p>
							<div class="uk-width-1-3 uk-text-right">รูปแพทย์: </div>
							<div class="uk-width-1-3" ><div id="my_camera"><img src="<s:property value="docModel.profile_pic"/>" alt="No Profile Picture" class="profile-pic"></div></div>
							<div class="uk-width-1-3" >
								<div id="pre_take_buttons">
									<button type="button" id="access" class="uk-button uk-button-primary uk-icon-camera" onClick="setup(); $(this).hide().next().show();"> Access Camera</button>
									<button type="button" id="take" class="uk-button uk-button-success uk-icon-camera" onClick="preview_snapshot()"style="display:none"> Take Photo</button>
								</div>
								<div id="post_take_buttons" style="display:none">
									<button type="button"class="uk-button uk-button-primary uk-icon-refresh" onClick="cancel_preview()"> Take Again</button>
								</div>
							</div>
							<div class="uk-width-1-3 uk-text-right">อัพโหลด:</div>
							<div class="uk-width-1-3 uk-text-right">
								<input type="file" name="picProfile">
							</div>
							<div class="uk-width-1-3"></div>
							<input type="hidden" name="docModel.doctorID" value="<s:property value="docModel.doctorID"/>"/>
							<input type="hidden" name="docModel.tel_id" value="<s:property value="docModel.tel_id"/>"/>
							<input type="hidden" name="docModel.addr_id" value="<s:property value="docModel.addr_id"/>"/>
							<input type="hidden" name="docModel.branchID" value="<s:property value="docModel.branchID"/>"/>
							<input type="hidden" name="docModel.BookBankId" value="<s:property value="docModel.BookBankId"/>"/>
							<input type="hidden" name="docModel.work_history_id" value="<s:property value="docModel.work_history_id"/>"/>
							<input type="hidden" name="docModel.edu_id" value="<s:property value="docModel.edu_id"/>"/>
							<div class="uk-width-1-3 uk-text-right">คำนำหน้าชื่อ : </div>
							<div class="uk-width-1-3">
								<select class="uk-form-small uk-width-1-1" name="docModel.pre_name_id" required>
									<%
										Pre_nameData PreNameData = new Pre_nameData();
										List <Pre_nameModel> prenameModel = PreNameData.select_pre_name("", "", "");
										List<Pre_nameModel> prenameChk = new ArrayList<Pre_nameModel>();
										if(request.getAttribute("pnameList")!=null){
											prenameChk=(List)request.getAttribute("pnameList");
								    	} 
										int i =0;
										for(Pre_nameModel pnmd : prenameModel){%>
											<option <% Pre_nameModel CheckpName = new Pre_nameModel();
											CheckpName =(Pre_nameModel) prenameChk.get(i);
											   	 			if(pnmd.getPre_name_id().equals(CheckpName.getPre_name_id()) ){ %> selected <%}
												   	 	%> value="<%=pnmd.getPre_name_id()%>"><%=pnmd.getPre_name_th()%></option>
									<% 	} %>
								</select>
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">ชื่อ : </div>
							<div class="uk-width-1-3">
								
								<input type="text" name="docModel.firstname_th"pattern="[ก-๙]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทย" class="uk-form-small uk-width-1-1" 
								value="<s:property value="docModel.firstname_th"/>">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">นามสกุล : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.lastname_th" pattern="[ก-๙]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทย"class="uk-form-small uk-width-1-1" 
								value="<s:property value="docModel.lastname_th"/>">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">ชื่อเล่น : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.nickname" class="uk-form-small uk-width-1-1" 
								value="<s:property value="docModel.nickname"/>">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">ชื่อ EN : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.firstname_en" pattern="[A-z]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาอังกฤษ" class="uk-form-small uk-width-1-1" 
								value="<s:property value="docModel.firstname_en"/>">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">นามสกุล EN : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.lastname_en" pattern="[A-z]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาอังกฤษ" class="uk-form-small uk-width-1-1" 
								value="<s:property value="docModel.lastname_en"/>">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">
								<select id="identification_type" class="uk-form-small" name="docModel.identification_type" >
									
									<%  PatientData pData = new PatientData();
										List <PatientModel> pModel = pData.select_Identification_Type();
										List <PatientModel> pModelchk = new ArrayList <PatientModel>();
										if(request.getAttribute("pList")!=null){
											pModelchk=(List)request.getAttribute("pList");
								    	} 
										 i =0;
										for(PatientModel pnmd : pModel){%>
											<option <% PatientModel CheckidentType = new PatientModel();
											CheckidentType =(PatientModel) pModelchk.get(i);
											   	 			if(pnmd.getIdentification_type().equals(CheckidentType.getIdentification_type()) ){ %> selected <%}
												   	 	%> value="<%=pnmd.getIdentification_type()%>"><%=pnmd.getIdentification()%></option>
									<% 	} %>
								</select> :
							</div>
							<div class="uk-width-1-3">
								<input type="text" maxlength="13" name="docModel.identification" class="uk-form-small uk-width-1-1" 
								value="<s:property value="docModel.identification"/>">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">เลขที่ใบประกอบวิชาชีพ : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.TMCLicense" class="uk-form-small uk-width-1-1" 
									value="<s:property value="docModel.TMCLicense"/>">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">เลขที่สัญญาจ้าง : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.contract_id" class="uk-form-small uk-width-1-1" 
									value="<s:property value="docModel.contract_id"/>">
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">รหัสพนักงาน : </div>
							<div class="uk-width-1-3">
								<input type="text" name="docModel.emp_id" class="uk-form-small uk-width-1-1" 
									value="<s:property value="docModel.emp_id"/>">
							</div>
							
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">เฉพาะทาง : </div>
							<div class="uk-width-1-3">
								<select name="docModel.Title" class="uk-form-small uk-width-1-1" >
									<%
										String titleID = request.getAttribute("titleID").toString();
										DoctorTypeData docData = new DoctorTypeData();
										List <DoctorModel> docModel = docData.select_DocType("", "", "", "");
										for(DoctorModel pnmd : docModel){%>
											<option <% if(pnmd.getPosition_id().equals(titleID)){ %>selected<% } %> 
											value="<%=pnmd.getPosition_id()%>"><%=pnmd.getPosition_name_short()+" - "+pnmd.getPosition_name_en() %></option>
									<% 	} %>
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
								<input type="text" name="birthdate_eng" id="birthdate_eng" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" 
								value="">
								<input type="text" name="birthdate_th" id="birthdate_th" class="uk-form-small uk-width-1-1" 
								value="<s:property value="docModel.birth_date"/>">
							</div>
							<div class="uk-width-1-3"><button id="birthdate_patient" type="button" class="btn uk-button uk-button-primary uk-button-small" >Thai Year</button></div>							
						</div>
												<div class="uk-grid uk-grid-collapse padding5 border-gray div-telephone">
						 	<p class="uk-text-muted uk-width-1-1">ช่องทางติดต่อ </p>
						 	<s:iterator value="telList" var="tel" >
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
						 	</s:iterator>
							<div id="telephonecontainer" class="div-container uk-grid uk-grid-collapse uk-width-1-1"></div>    
							<div class="uk-width-1-3 uk-text-right">Line ID : </div>
							<div  class="uk-width-1-3 uk-text-right">
								<s:textfield autocomplete="off" 
									name="docModel.lineId" 
									id="patline_id_add" 
									pattern="[A-z0-9.]{1,}" 
									placeholder="Line ID" 
									class="uk-form-small uk-width-1-1" 
								/>
							</div>
							<div class="uk-width-1-3"></div>
							<div class="uk-width-1-3 uk-text-right">E-mail : </div>
							<div  class="uk-width-1-3 uk-text-right">
								<s:textfield name="docModel.email" 
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
						<p class="uk-text-muted uk-width-1-1">ที่อยู่</p><button id="openAddAddr" class="uk-button uk-button-success uk-button-small" type="button">เพิ่มที่อยู่</button>
						 	<div class="addrTemplate addrTemplate-add uk-grid uk-grid-collapse uk-panel-box uk-width-1-1 hidden">
								<div class="uk-panel  uk-width-1-1">
									<div class="uk-grid uk-grid-collapse uk-width-1-1">
	                           	    	<select name="docModel.addr_typeid" class="uk-form-small">
											<%@include file="include/addrtype-dd-option.jsp" %>
	                                	</select>
                                    </div>
                                    <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
	                                   <div class="uk-width-1-3"><small >เลขที่</small>
											<input type="text"  maxlength="10" name="docModel.addr_no" class="uk-form-small uk-width-1-1 addr" >
	                                   </div>
	                                   <div class="uk-width-1-3"><small >หมู่บ้าน</small>
	                                   		<input type="text"  maxlength="55" name="docModel.addr_village"class="uk-form-small uk-width-1-1 addr">
	                                   </div>
	                                   <div class="uk-width-1-3"><small >ซอย</small>
	                                   		<input type="text"  maxlength="100"  name="docModel.addr_alley" class="uk-form-small uk-width-1-1 addr">
	                                   </div>
                                    </div> 
                                    <div class="uk-grid uk-grid-collapse uk-width-1-1">
                                    	<div class="uk-width-1-3"><small >หมู่</small>
	                                   		<input type="text"  maxlength="10"  name="docModel.addr_bloc" class="uk-form-small uk-width-1-1 addr">
	                                    </div>
	                                   <div class="uk-width-1-3"><small >ถนน</small>
	                                   		<input type="text"   maxlength="100"  name="docModel.addr_road" class="uk-form-small uk-width-1-1 addr">
	                                    </div>
	                                    <div class="uk-width-1-3"><small >รหัสไปรษณีย์</small>
	                                   		<input type="text"  maxlength="5"  name="docModel.addr_zipcode" class="uk-form-small uk-width-1-1 addr">
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
		                                   	<select id="addr_districtid" name="docModel.addr_districtid" class="uk-form-small uk-width-1-1 selectdistrict">
		                                   		<option value="">เลือกตำบล</option> 
		                                   	</select>
	                                   	</div>
                                    </div>
								</div>
								<button id="closeAddAddr"  class="uk-button uk-button-danger  uk-button-small  uk-container-center " type="button" ><i class="uk-icon-close"></i> ลบ</button>
							</div>
							<% 
								
								List <AddressModel> addrModelList = new ArrayList<AddressModel>();
								if(request.getAttribute("addressList")!=null){
									addrModelList=(List)request.getAttribute("addressList");
						    	} 
								 i =0;
								for(AddressModel addressModel : addrModelList){%>
									
							<div id="addrContainer" class="div-container uk-grid uk-grid-collapse uk-width-1-1">
								<div class="addrTemplate uk-grid uk-grid-collapse  uk-panel-box  uk-width-1-1">
									<div class="uk-panel  uk-width-1-1">
										<div class="uk-grid uk-grid-collapse uk-width-1-1">
		                           	    	<select name="docModel.addr_typeid" class="uk-form-small">
											<%
												for(AddressModel pnmd : addrModel){%>
												<option <% AddressModel CheckaddrType = new AddressModel();
												CheckaddrType =(AddressModel) addrModelList.get(i);
											   	 			if(pnmd.getAddr_typeid().toString().equals(CheckaddrType.getAddr_typeid()) ){ %> selected <%}
												   	 	%>value="<%=pnmd.getAddr_typeid()%>"><%=pnmd.getAddr_typename()%></option>
											<% 	} %>
		                                	</select>
	                                    </div>
	                                    <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
		                                   <div class="uk-width-1-3"><small >เลขที่</small>
												<input type="text" maxlength="10" name="docModel.addr_no" class="uk-form-small uk-width-1-1" 
												value="<%=addressModel.getAddr_no()%>">
		                                   </div>
		                                   <div class="uk-width-1-3"><small >หมู่บ้าน</small>
		                                   		<input type="text" maxlength="55" name="docModel.addr_village"class="uk-form-small uk-width-1-1"
		                                   		value="<%=addressModel.getAddr_village()%>">
		                                   </div>
		                                   <div class="uk-width-1-3"><small >ซอย</small>
		                                   		<input type="text" maxlength="100"  name="docModel.addr_alley" class="uk-form-small uk-width-1-1"
		                                   		value="<%=addressModel.getAddr_alley()%>">
		                                   </div>
	                                    </div>
	                                    <div class="uk-grid uk-grid-collapse uk-width-1-1">
	                                    	<div class="uk-width-1-3"><small >หมู่</small>
		                                   		<input type="text" maxlength="10"  name="docModel.addr_bloc" class="uk-form-small uk-width-1-1"
		                                   		value="<%=addressModel.getAddr_bloc()%>">
		                                    </div>
		                                   <div class="uk-width-1-3"><small >ถนน</small>
		                                   		<input type="text" maxlength="100"  name="docModel.addr_road" class="uk-form-small uk-width-1-1"
		                                   		value="<%=addressModel.getAddr_road()%>">
		                                    </div>
		                                    <div class="uk-width-1-3"><small >รหัสไปรษณีย์</small>
		                                   		<input type="text" maxlength="5"  name="docModel.addr_zipcode" class="uk-form-small uk-width-1-1"
		                                   		value="<%=addressModel.getAddr_zipcode()%>">
		                                    </div>
	                                    </div>
	                                    <div class="uk-grid uk-grid-collapse uk-width-1-1"> 
	                                    	<div class="uk-width-1-3"><small >จังหวัด</small>
		                                    	<select id="addr_provinceid" name="docModel.addr_provinceid" class="uk-form-small uk-width-1-1">
		                                    		<option value="<%=addressModel.getAddr_provinceid()%>"><%=addressModel.getAddr_province_name()%></option>
		                                    	</select>
	                                    	</div>
	                                    	<div class="uk-width-1-3"><small >อำเภอ</small>
			                                   	<select id="addr_aumphurid" name="docModel.addr_aumphurid" class="uk-form-small uk-width-1-1">
			                                   	<option value="<%=addressModel.getAddr_aumphurid()%>"><%=addressModel.getAddr_aumphur_name()%></option>
			                                   	</select>
		                                   	</div>
		                                   	<div  class="uk-width-1-3"><small >ตำบล</small>
			                                   	<select id="addr_districtid" name="docModel.addr_districtid" class="uk-form-small uk-width-1-1 selectdistrict">
			                                   		<option value="<%=addressModel.getAddr_districtid()%>"><%=addressModel.getAddr_district_name()%></option>
			                                   	</select>
		                                   	</div>
	                                    </div>
									</div>
									<button class="uk-button uk-button-danger  uk-button-small remove-addr-elements uk-container-center " type="button" ><i class="uk-icon-close"></i> ลบ</button>
								</div>
							</div>
							
							<% 	i++;
								}
					    	%>
							
						</div>
						

					</div>
					<div class="uk-width-6-10 padding5">
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
							<div class="uk-width-1-1 uk-form">
								<p class="uk-text-muted uk-width-1-1">กำหนดส่วนแบ่ง</p>
								<div class="uk-grid uk-grid-collapse ">
									<div class="uk-width-1-1"> 
										<!-- <a href="" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" >
											<i class="uk-icon-stethoscope"></i><br>
											ส่วนแบ่งค่ารักษา
										</a>
										<a href="" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" >
											<i class="uk-icon-credit-card"></i><br>
											 Credit Card Setting
										</a> -->
										<a href="DoctorTimeBegin?d=<s:property value="docModel.doctorID"/>" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" >
											<i class="uk-icon-calendar-plus-o"></i><br>
											ตารางเข้างานแพทย์
										</a>
										<a href="getBranchStandard-<s:property value="docModel.doctorID"/>" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" >
											<i class="uk-icon-building"> <span class="uk-badge uk-badge-notification uk-badge-danger" id="countalldocbranch">0</span></i><br> 
											สาขาที่ลงตรวจ
										</a>
										<a href="getBranchMgr-<s:property value="docModel.doctorID"/>" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" >
											<i class="uk-icon-building"> <span class="uk-badge uk-badge-notification uk-badge-danger" ><s:property value="docModel.checkSize" /></span></i><br> 
											ผู้ดำเนินการ
										</a>
									</div >
								</div>
								<p class="uk-text-muted uk-width-1-1">ข้อมูลการศึกษา</p>
								<div class="border-gray padding5">
									<div class="div-edu">
										<button id="openAddEdu" class="uk-button uk-button-success uk-button-small" type="button">เพิ่มเบอร์เพิ่มข้อมูลการศึกษา</button>
										<div class="uk-grid eduTemplate eduTemplate-add uk-grid-collapse hidden">
											<div class="uk-width-2-5"> ระดับการศึกษา  
												<select  class="uk-form-small  edu_id" name="education_vocabulary_id" >
													<%@include file="include/education-dd-option.jsp" %>
												</select>
											</div>
											<div class="uk-width-3-5"> ชื่อสถานศึกษา
												<input type="text" name="education_name" id="education_name" class="uk-form-small  education_name uk-width-1-2" >
												<button id="closeAddEdu"  class="uk-button uk-button-small uk-button-danger " type="button"><i class="uk-icon-close"></i> ลบ</button>
											</div>
										</div>
									</div>
									<div id="educontainer" class="div-container ">
										<% 
								
										List <DoctorModel> eduList = new ArrayList<DoctorModel>();
										if(request.getAttribute("eduList")!=null){
											eduList=(List)request.getAttribute("eduList");
								    	} 
										 i =0;
										for(DoctorModel dmd : eduList){%>
										
										<div class="uk-grid eduTemplate uk-grid-collapse">
											<div class="uk-width-2-5"> ระดับการศึกษา 
												<select  class="uk-form-small  edu_id" name="education_vocabulary_id" >
													<%
														for(PatientModel pnmd : patModel){%>
															<option <% DoctorModel CheckType = new DoctorModel();
																CheckType =(DoctorModel) eduList.get(i);
												   	 			if(pnmd.getEducation_vocabulary_id() == CheckType.getEducation_vocabulary_id()){ %> selected <%}
													   	 	%> value="<%=pnmd.getEducation_vocabulary_id()%>"><%=pnmd.getEducation_vocabulary_th() %></option>
													<% 	} %>
												</select>
											</div>
											<div class="uk-width-3-5"> ชื่อสถานศึกษา
												<input type="text" name="education_name" value="<%=dmd.getEducation_name()%>" id="education_name" class="uk-form-small  education_name uk-width-1-2" >
												<button class="uk-button uk-button-small remove-edu-elements uk-button-danger " type="button"><i class="uk-icon-close"></i> ลบ</button>
											</div>
										</div>
										
										<% 	i++;
										}
								    	%>
									</div>  
								</div>
								
								<p class="uk-text-muted uk-width-1-1">ประวัติการทำงานที่ผ่านมา</p>
								<div class="border-gray padding5">
									<div class="div-work">
										<button id="openAddWork" class="uk-button uk-button-success uk-button-small" type="button">เพิ่มประวัติการทำงาน</button>
										<div class="uk-grid workTemplate workTemplate-add hidden">
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
												<button id="closeAddWork"  class="uk-button uk-button-danger uk-button-small "  type="button"><i class="uk-icon-close"></i> ลบ</button>
											</div>
										</div>
									</div>
									<div id="workcontainer" class="div-container ">
										<% 
								
									List <DoctorModel> workList = new ArrayList<DoctorModel>();
									if(request.getAttribute("workList")!=null){
										workList=(List)request.getAttribute("workList");
							    	} 
									 i =0;
									for(DoctorModel workModel : workList){%>
										<div class="uk-grid workTemplate">
											<div class="uk-width-2-3">
												บริษัท
												<input type="text" name="docModel.location" value="<%=workModel.getLocation()%>" class="uk-form-small  location uk-width-1-1" >
											</div>
											<div class="uk-width-1-3">
												ประเภทของงานที่ทำ
												<input type="text" name="docModel.work_type" value="<%=workModel.getWork_type() %>" class="uk-form-small  work_type uk-width-1-1" >
											</div>
											<div class="uk-width-1-3">
												ตำแหน่ง
												<input type="text" name="docModel.position" value="<%=workModel.getPosition() %>"   class="uk-form-small  position uk-width-1-1" >
											</div>
											<div class="uk-width-1-3">
												เงินเดือน
												<input type="text" name="docModel.salary"  value="<%=workModel.getSalary() %>" class="uk-form-small  salary uk-width-1-1" >
											</div>
											<div class="uk-width-1-3">
												เริ่มงาน
												<input type="text" name="docModel.start_date"  value="<%=workModel.getStart_date() %>" class="uk-form-small  start_date uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}">
											</div>
											<div class="uk-width-1-3">
												ออกงาน
												<input type="text" name="docModel.end_date"  value="<%=workModel.getEnd_date() %>"  class="uk-form-small  end_date uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}">
											</div>
											<div class="uk-width-2-3">
												เหตุผลที่ออกงาน
												<input type="text" name="docModel.remark_message"  value="<%=workModel.getRemark_message() %>" class="uk-form-small  remark_message uk-width-1-1" >
											</div>
											
											<div class="uk-width-1-1 uk-text-center">
												<button class="uk-button uk-button-danger uk-button-small remove-work-elements " 
												type="button"><i class="uk-icon-close"></i> ลบ</button>
											</div>
										</div>
									
									<%
										i++;
									}
									%>
									</div>  
								</div>
								
						 <!--	<p class="uk-text-muted uk-width-1-1">สาขาที่ลงตรวจ</p> 
								<div class="uk-grid uk-grid-collapse padding5 border-gray"> -->
									<div class="uk-width-2-10 "> 
								<!--	<a href="#select_saka" class="uk-button uk-button-primary" data-uk-modal>
										<i class="uk-icon-building"></i> เลือกสาขา
									</a>  	-->
									
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
													    <%	List branchList = null;
													    	
													    	List <BranchModel> branchModelList = new ArrayList<BranchModel>();
													    	
													   	 	if(request.getAttribute("branchList")!=null){
													   	 		branchList=(List)request.getAttribute("branchList");
							                                	branchModelList = branchList;
													    	}
													   	 	
													   	 
													    	BranchData branchData = new BranchData();
													    	List <BranchModel> branchtList = branchData.select_branch("", "", "", "", 1);
									                        for(BranchModel branchModel : branchtList){
																
									                    %>
															<tr>  
													    		<td class="uk-text-center">
														        	<div class="uk-form-controls">
							                                            <input value="<%= branchModel.getBranch_id() %>" <% for(BranchModel CheckboxBranch : branchModelList){
														   	 			if(branchModel.getBranch_id().equals(CheckboxBranch.getBranch_id())){
														   	 				%>checked="checked"<%
														   	 			}
															   	 	}%> type="checkbox" id="<%= branchModel.getBranch_id()%>" name="doctor_branch" >
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
									         	<button class="uk-modal-close">บันทึก</button>
									         	<button class="uk-modal-close">ยกเลิก</button> 
									         </div>
									    </div>
									</div>
									<!--  				modal					-->
									</div>
									
								<!--	<div class="uk-width-8-10">
										<div class="uk-grid uk-grid-collapse ">
											<select class="uk-width-1-1 pt" size="3" id="show_doctor_branch" name="show_doctor_branch"> 
									            <% for(BranchModel branchModel : branchModelList){%>
									            	<option value="<%= branchModel.getBranch_id()%>"><%= branchModel.getBranch_name() %></option>
									           <% }%>
									        </select>
										</div>
									</div> 
								</div> -->
							
								<div class="border-gray padding5">
									<p class="uk-text-muted uk-width-1-1">บัญชีธนาคาร</p>
									<button id="openAddBook" class="uk-button uk-button-success uk-button-small" type="button">เพิ่มบัญชี</button>
									<div class="div-bank ">
										<div class="uk-grid bankTemplate bankTemplate-add uk-grid-collapse add hidden">
											<div class="uk-width-1-4"> เลขบัญชี  
												<input type="text" name="account_num" maxlength="10" id="account_num" class="uk-form-small  account_num" >
											</div>
											<div class="uk-width-1-4"> ชื่อบัญชี  
												<input type="text" name="account_name" id="account_name" class="uk-form-small  account_name" >
											</div>
											<div class="uk-width-2-4"> ธนาคาร  
												<select  class="uk-form-small  bank_id" name="bank_id" >
													<%@include file="include/banktype-dd-option.jsp" %>
												</select>
												<button id="closeAddBank" class="uk-button uk-button-small uk-button-danger " type="button"><i class="uk-icon-close"></i> ลบ</button>
											</div>
											
										</div>
									</div>
									<div id="bankcontainer" class="div-container ">
									<% 
								
									List <BookBankModel> bankModelList = new ArrayList<BookBankModel>();
									if(request.getAttribute("bankList")!=null){
										bankModelList=(List)request.getAttribute("bankList");
							    	} 
									 i =0;
									for(BookBankModel bookbankModel : bankModelList){%>
										<div class="uk-grid bankTemplate uk-grid-collapse">
											<div class="uk-width-1-4"> เลขบัญชี  
												<input type="text" name="account_num"  class="uk-form-small  account_num" 
												value="<%=bookbankModel.getBookbank_no()%>">
											</div>
											<div class="uk-width-1-4"> ชื่อบัญชี  
												<input type="text" name="account_name"  class="uk-form-small  account_name" 
												value="<%=bookbankModel.getBookbank_name()%>">
											</div>
											<div class="uk-width-2-4"> ธนาคาร  
												<select  class="uk-form-small  bank_id" name="bank_id" >
												<%
													for(BookBankModel pnmd : bankList){%>
														<option <% BookBankModel CheckType = new BookBankModel();
															CheckType =(BookBankModel) bankModelList.get(i);
											   	 			if(pnmd.getBank_id().equals(CheckType.getBank_id())){ %> selected <%}
												   	 	%> value="<%=pnmd.getBank_id()%>"><%=pnmd.getBank_name_th()%></option>
												<% 	} %>
												</select>
												<button class="uk-button uk-button-small uk-button-danger remove-bank-elements" type="button"><i class="uk-icon-close"></i> ลบ</button>
											</div>
											
										</div>
										
									<% 
									i++;
									}%>
									</div>    
								</div>
								
							<!--  	<p class="uk-text-muted uk-width-1-1">ผู้ดำเนินการ  </p> 
								<div class="uk-grid uk-grid-collapse padding5 border-gray"> -->
								<!--  	<div class="uk-width-2-10">
										<a href="#select_branch" class="uk-button uk-button-primary" data-uk-modal>
											<i class="uk-icon-building"></i> เลือกสาขา
										</a>	
									</div>  -->
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
											    	List <BranchModel> branchMGRModelList = new ArrayList<BranchModel>();
											   	 	if(request.getAttribute("branchMGRList")!=null)	{
											   	 	branchMGRModelList=(List)request.getAttribute("branchMGRList");
											    	}
							                        for(BranchModel branchModel : branchtList){
							                       	%>
													<tr>  
											    		<td class="uk-text-center">
												        	<div class="uk-form-controls">
					                                            <input <% for(BranchModel CheckBranchMGR : branchMGRModelList){
														   	 			if(branchModel.getBranch_id().equals(CheckBranchMGR.getBranch_id())){
														   	 				%>checked="checked"<%
														   	 			}
															   	 	}%> value="<%= branchModel.getBranch_id() %>" type="checkbox" name="doctor_boss_branch" >
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
								<!--  	<div class="uk-width-8-10">
										<div class="uk-grid uk-grid-collapse ">
											<select class="uk-width-1-1 pt" size="3" id="show_doctor_boss_branch" name="show_doctor_boss_branch"> 
												<% for(BranchModel branchModel : branchMGRModelList){%>
													<option value="<%= branchModel.getBranch_id()%>"><%= branchModel.getBranch_name() %></option>
												<%}%>
									        </select>
										</div>
									</div> 
								</div> -->
							<!-- 	<p class="uk-text-muted uk-width-1-1">กำหนดส่วนแบ่ง</p>
								<div class="uk-grid uk-grid-collapse ">
									<div class="uk-width-3-3"> 
										<a href="price-list.jsp" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" >
											<i class="uk-icon-stethoscope"></i><br>
											ส่วนแบ่งค่ารักษา
										</a>
										<a href="" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" >
											<i class="uk-icon-credit-card"></i><br>
											 Credit Card Setting
										</a>
										<a href="doctor-time-edit.jsp" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" >
											<i class="uk-icon-calendar-plus-o"></i><br>
											ตารางเข้างานแพทย์
										</a>
									</div >
								</div>
								<p class="uk-text-muted uk-width-1-1">ค่า Stand by</p>
								<div class="uk-grid uk-grid-collapse padding5 border-gray">
									<div class="uk-width-1-1 uk-form">
									<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray uk-text-nowrap"> 
									    <thead>
									        <tr class="hd-table">
									            <th class="uk-text-center uk-width-1-10 uk-text-middle" rowspan="2">สาขา</th>
									            <th class="uk-text-center uk-width-1-10">ประเภทวัน</th>  
									            <th class="uk-text-center uk-width-2-10" colspan="2">ประเภทชั่วโมง</th>  
									        </tr>
									        <tr class="hd-table">  
									            <th class="uk-text-center uk-width-1-10">จำนวนเงินรวม</th> 
									            <th class="uk-text-center uk-width-1-10">จำนวนชั่วโมง</th>  
									            <th class="uk-text-center uk-width-1-10">จำนวนเงินรวม</th>   
									        </tr>
									    </thead>
									    <tbody>
									        <tr>
									            <td class="uk-width-1-10 uk-text-left">LDC ศรีนครินทร์</td> 
									            <td class="uk-width-1-10 uk-text-center"><input type="text" id="companyid" class="uk-form-small uk-width-6-10 uk-text-right" value="0" readonly></td>
									            <td class="uk-width-1-10 uk-text-center"><input type="text" id="companyid" class="uk-form-small uk-width-4-10 uk-text-right" value="1" readonly></td> 
									            <td class="uk-width-1-10 uk-text-center"><input type="text" id="companyid" class="uk-form-small uk-width-6-10 uk-text-right" value="3,500" readonly></td>
									        </tr>
									       
									</table>
									</div> 
								</div>  -->
							</div>
						</div>
						
						<div class="uk-text-center">
							<input type="submit" 
								value="บันทึก" 
								class="uk-button uk-button-success uk-button-large uk-icon-floppy-o">
							<a href="UpdateDoctor" class="uk-button uk-button-danger uk-button-large "><i class="uk-icon-close"></i> ยกเลิก</a>
						</div>
					</div>
				</div>
				</form>
				
			</div>
		</div>
		<!-- Count Branch Standard -->
		 <ul class="uk-nav uk-nav-dropdown hidden">
			        	<li class="uk-nav-header">เอกสารที่คนไข้ต้องการ</li>
			        	<s:iterator value="branchStandardList" status="docbranch">
				        	<s:if test="#docbranch.index>0">
				            	 <li class="uk-nav-divider"></li>
				            </s:if>
			           	 	<li><a><s:property value="branchName"/></a></li>
				           	 	<s:if test="#docbranch.last== true">
				           	 	<li class="hidden" id="docbranch"><s:text name="%{#docbranch.count}" /></li>
			           	 	</s:if>	
			            </s:iterator>
			            	<li class="hidden" id="docbranch">0</li>
		</ul>
		<script>
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
					$('input[name="docModel.addr_zipcode"]').eq(ind).val(data[0].zipcode);
					// alert($('.selectdistrict').index(this));
				})
				.fail(function() {
					console.log("error");
				})
				.always(function() {
					console.log("complete");
				});
			}).on("change","select[name='docModel.addr_provinceid']",function(){
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
						ddbranch.append($('<option>').text(branch_id+" "+branch_name).attr('value', $(this).val()));
					}else{
						this.checked = false;
					}
				}else{
					$("select[name='show_doctor_boss_branch'] option[value='"+$(this).val()+"']").remove();
				}
				
			});
			$(document).ready(function(){
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
					if($("#idtel").val().length === 0 && 
						$("#idline").val().length === 0 && 
						$("#email").val().length === 0){
						swal(
								'ผิดพลาด!',
								'กรุณาระบุ กรอกข้อมูล เบอร์โทรศัพท์ IDLINE หรือ Email อย่างใดอย่างหนึ่ง',
								'error'
							)
						event.preventDefault();
					}else{
						alert("hey submit");
					}
				});
				
				$(".add-addr-elements").click(function(){
					var clone = $(".div-addr .addrTemplate:first");
					clone.find('.uk-button').removeClass('uk-button-success add-addr-elements').addClass('uk-button-danger remove-addr-elements ').html('<i class="uk-icon-minus"></i>');					
					clone.clone().appendTo("#addrContainer:first");					
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
				$("#openAddAddr").click(function(){
					$(".addrTemplate-add").removeClass('hidden');
					$("#openAddAddr").addClass("hidden");
				});
				$("#closeAddAddr").click(function(){
					$(".addrTemplate-add").addClass('hidden');
					$("#openAddAddr").removeClass('hidden');
					$('.addr').val('');
				});
				$("#openAddTel").click(function(){
					$(".telephoneTemplate-add").removeClass('hidden');
					$("#openAddTel").addClass("hidden");
				});
				$("#closeAddTel").click(function(){
					$(".telephoneTemplate-add").addClass('hidden');
					$("#openAddTel").removeClass('hidden');
					$('.tel').val('');
				});
				
				$("#openAddBook").click(function(){
					$(".bankTemplate-add").removeClass('hidden');
					$("#openAddBook").addClass("hidden");
				});
				$("#closeAddBank").click(function(){
					$(".bankTemplate-add").addClass('hidden');
					$("#openAddBook").removeClass('hidden');
					$('#account_num').val('');
					$('#account_name').val('');
				});
				
				$("#openAddEdu").click(function(){
					$(".eduTemplate-add").removeClass('hidden');
					$("#openAddEdu").addClass("hidden");
				});
				$("#closeAddEdu").click(function(){
					$(".eduTemplate-add").addClass('hidden');
					$("#openAddEdu").removeClass('hidden');
					$('#education_name').val('');
				});
				
				$("#openAddWork").click(function(){
					$(".workTemplate-add").removeClass('hidden');
					$("#openAddWork").addClass("hidden");
				});
				$("#closeAddWork").click(function(){
					$(".workTemplate-add").addClass('hidden');
					$("#openAddWork").removeClass('hidden');
					$('#location').val('');
					$('#position').val('');
					$('#salary').val('');
					$('#start_date').val('');
					$('#end_date').val('');
					$('#remark_message').val('');
					$('#work_type').val('');
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
			$("#count").ready(function(){
				var docbranch = $("#docbranch").text();		
				if(docbranch != '' ){
				$("#countalldocbranch").text(docbranch);
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