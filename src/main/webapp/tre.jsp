<%@ page language="java" import="java.util.*,java.text.DecimalFormat"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*"%>
<%@ page import="com.smict.treatment.data.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:การรักษา</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head>
<body>
	<div class="uk-grid uk-grid-collapse">
		<div class="uk-width-1-10">
			<%@include file="nav-right.jsp"%>
		</div>
		<div class="uk-width-9-10">
			<%@include file="nav-top.jsp"%>
			<div class="uk-grid uk-grid-collapse">
				<div class="uk-width-4-10 ">

					<div class="uk-grid bg-gray padding5  border-gray">
						<div class="uk-width-2-3 ">
							<h3 class="hd-text padding5 uk-text-primary">ประวัติคนไข้</h3>
							<h4 class="hd-text ">
								<small class=" uk-text-primary">HN : </small>
								<s:property value="servicePatModel.hn" />
							</h4>
							<h4 class="hd-text">
								<small class=" uk-text-primary">ชื่อ-สกุล (ไทย) : </small>
								<s:property value="servicePatModel.pre_name_th" />
								<s:property value="servicePatModel.firstname_th" />
								<s:property value="servicePatModel.lastname_th" />
							</h4>
							<h4 class="hd-text">
								<small class=" uk-text-primary">ชื่อ-สกุล (ENG) : </small>
								<s:property value="servicePatModel.pre_name_en" />
								<s:property value="servicePatModel.firstname_en" />
								<s:property value="servicePatModel.lastname_en" />
							</h4>
							<h4 class="hd-text">
								<small class=" uk-text-primary">อายุ : </small>
								<s:property value="servicePatModel.age" />
								ปี
							</h4>
							<h4 class="hd-text">
								<small class=" uk-text-primary">เบอร์โทร: </small>
								<s:iterator value="servicePatModel.ListTelModel"
									status="telStatus">
									<s:if test="%{#telStatus.index > 0}">,</s:if>
									<s:property value="tel_number" /> - <s:property
										value="tel_typename" />
								</s:iterator>
							</h4>
							<h4 class="hd-text">
								<small class=" uk-text-primary">ค้างชำระ: </small><span
									class="red"><s:property
										value="servicePatModel.deposit_money" /> บาท</span>
							</h4>
							<h4 class="hd-text">
								<small class=" uk-text-primary">แผนการรักษา: </small><a
									href="treatment-plan-1.jsp" class="uk-button uk-button-primary">จัดการ</a>
							</h4>
						</div>
						<div class="uk-width-1-3  ">
							<img src='<s:property value="servicePatModel.profile_pic"/>'
								alt="No Profile Picture" class="profile-pic">
						</div>
					</div>
					<div id="tooth-table-pic" class="uk-overflow-container">
						<table class="tooth-table border-gray ">
							<% if(request.getAttribute("toothListUp")!=null){ 
								List toothlist = (List) request.getAttribute("toothListUp"); %>
							<tr class="tooth-pic">
								<%	
							    	for (Iterator iterA = toothlist.iterator(); iterA.hasNext();) {
					        			ToothModel toothModel = (ToothModel) iterA.next();
				      				%>
								<td id="tooth_<%=toothModel.getTooth_num()%>"><img
									src="img/tooth/<%=toothModel.getTooth_num()%>/<%=toothModel.getTooth_num()%>.jpg" />

								</td>
								<%}  %>
							</tr>

							<tr class="uk-text-center">
								<%	
							    	for (Iterator iterA = toothlist.iterator(); iterA.hasNext();) {
					        			ToothModel toothModel = (ToothModel) iterA.next();
				      				%>
								<td><%=toothModel.getTooth_num()%></td>
								<%	} %>
							</tr>
							<%	}
							%>
						</table>

						<table class="tooth-table border-gray ">
							<% if(request.getAttribute("toothListLow")!=null){ 
								List toothlist = (List) request.getAttribute("toothListLow"); %>
							<tr class="uk-text-center">
								<%	
							    	for (Iterator iterA = toothlist.iterator(); iterA.hasNext();) {
					        			ToothModel toothModel = (ToothModel) iterA.next();
				      				%>
								<td><%=toothModel.getTooth_num()%></td>
								<%	} %>
							</tr>
							<tr class="tooth-pic">
								<%	
							    	for (Iterator iterA = toothlist.iterator(); iterA.hasNext();) {
					        			ToothModel toothModel = (ToothModel) iterA.next();
				      				%>
								<td id="tooth_<%=toothModel.getTooth_num()%>"><img
									src="img/tooth/<%=toothModel.getTooth_num()%>/<%=toothModel.getTooth_num()%>.jpg" />
								</td>
								<%	} %>
							</tr>


							<%	}
							%>
						</table>
					</div>


				</div>
				<div class="uk-width-6-10">

					<div class="uk-grid uk-grid-collapse">

						<div class="uk-width-4-10 border-gray padding5">

							<div class="uk-form">
								<h4 class="hd-text uk-text-primary">แพทย์ผู้รักษา</h4>
								<input type="text" class="uk-form-small uk-width-1-1"
									id="doctor_name" name="servicePatModel.doctor_name"
									placeholder="กรุณาเลือกแพทย์" required="required"> <input
									type="hidden" id="doctor_id" name="servicePatModel.doctor_id">

								<h4 class="hd-text uk-text-primary">ห้อง</h4>
								<input type="text" class="uk-form-small" id="treatment_code"
									placeholder="ห้องที่รักษา" required>


								<ul id="tab-content" class="uk-switcher uk-margin">

									<li class="normal">
										<form action="treatmentHistory" method="post">
											<input type="hidden" id="treatment_code_normal"
												name="servicePatModel.treatment_code">
										</form>
									</li>



									<li class="continus">
										<form action="treatmentContinue" method="post">

											<div class="uk-grid uk-grid-small">
												<div class="uk-width-1-1">

													<input type="hidden" id="doctor_id_contunus"
														name="doctor_id_contunus"> <input type="hidden"
														id="continue_treatment_code"
														name="continue_treatment_code"> <input
														type="hidden" id="continue_treatment_id"
														name="continue_treatment_id"> <input type="hidden"
														id="continue_phase" name="continue_phase"> <input
														type="hidden" id="continue_count" name="continue_count">
													<input type="hidden" id="continue_count_all"
														name="continue_count_all">
												</div>
											</div>

											<div class="uk-grid uk-grid-small">
												<div
													class="uk-width-1-1 uk-container-center treatment_continue">
													<!-- ajax -->
												</div>
											</div>
											<button
												class="uk-button uk-button-success uk-width-1-1 uk-button-small"
												name="savehistory" type="submit">เพิ่มการรักษา</button>
										</form>
									</li>
								</ul>
							</div>
						</div>

						<div class="uk-width-6-10 border-gray padding5">


							<div class="uk-text-center uk-width-1-1 padding10">

								<a href="medicine.jsp"><button
										class="uk-button uk-button-success uk-button-large uk-width-1-4"
										name="savewaiting" type="submit">
										<h1 class="white">
											<i class="uk-icon-medkit"></i>
										</h1>
										ยา
									</button></a>

								<a href="product.jsp"><button
										class="uk-button uk-button-primary uk-button-large uk-width-1-4"
										name="savewaiting" type="submit">
										<h1 class="white">
											<i class="uk-icon-shopping-cart"></i>
										</h1>
										สินค้า
									</button></a>
							</div>


						</div>
					</div>

					<div class="uk-grid uk-grid-collapse">
						<div class="uk-width-1-1">
							<form id="deleteTransection" action="treatmentDeleteTran"
								method="post">
								<div class="uk-panel uk-panel-box padding5 ">
									<h4 class="hd-text uk-text-primary">
										รายการรักษา <a href="#treatment"
											class="uk-button uk-button-primary uk-button-small"
											data-uk-modal="{bgclose:false}">เพิ่มการรักษา</a>
									</h4>

									<hr class="margin5">
									<div class="treatment-bill">

										<input type="hidden" id="treatment_id"
											name="servicePatModel.treatment_id"> <input
											type="hidden" id="count" name="treatment_code_delete">
										<input type="hidden" id="treatment_mode"
											name="servicePatModel.treatment_mode">

										<table class="uk-table uk-table-condensed ">
											<thead>
												<tr class="hd-table">
													<th>แพทย์</th>
													<th>รหัสการรักษา</th>
													<th>การรักษา</th>
													<th>วัสดุ</th>
													<th class="uk-text-right">ราคา</th>
													<th>การเงิน</th>
												</tr>
											</thead>


										</table>
									</div>
								</div>
							</form>
						</div>
					</div>

				</div>
			</div>

			<div id="treatment" class="uk-modal ">
				<div class="uk-modal-dialog uk-form uk-modal-dialog-large">
					<a class="uk-modal-close uk-close"></a>
					<div class="uk-modal-header">
						<h2>
							<i class="uk-icon-medkit"></i> เพิ่มการรักษา
						</h2>
					</div>
					<div class="uk-grid">
						<div class="uk-width-3-4">
							<h3>รายการรักษา</h3>
							<table
								class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
								<thead>
									<tr class="hd-table">
										<th class="uk-text-center">เลือก</th>
										<th class="uk-text-center">ประเภทการรักษา</th>
										<th class="uk-text-center">รหัสการรักษา</th>
										<th class="uk-text-center">ชื่อการรักษา</th>
										<th class="uk-text-center">ราคา</th>
									</tr>
								</thead>
								<tbody>
									<% 
										    		if(request.getAttribute("treatmentMasterList")!=null){
									    			List<TreatmentMasterModel> treatmentMasterList = (List) request.getAttribute("treatmentMasterList");
									    			int i=0;
										    		for(TreatmentMasterModel tmModel : treatmentMasterList){  
										    		 
										    	%>
									<tr>
										<td class="uk-text-center">
											<div class="uk-form-controls">
												<input type="radio" id="selectDoctor" name="selectDoctor"
													onclick="selectDoctor('<%=tmModel.getTreatment_code()%>','<%=tmModel.getTreatment_mode()%>'
				                                            ,'<%=tmModel.getType_tooth()%>','<%=tmModel.getType_surface()%>','<%=tmModel.getType_mouth()%>'
				                                            ,'<%=tmModel.getType_quadrant()%>','<%=tmModel.getType_sextant()%>','<%=tmModel.getType_arch()%>','<%=tmModel.getType_tooth_range()%>')
				                                            ;getElementById('treatment_code').value='<%=tmModel.getTreatment_code()%>'
				                                            ;getElementById('treatment_code_normal').value='<%=tmModel.getTreatment_code()%>'
				                                            ;getElementById('treatment_code_continue').value='<%=tmModel.getTreatment_code()%>'
				                                            ;getElementById('treatment_name_continue').value='<%=tmModel.getTreatment_nameth()%>'
				                                            ;getElementById('price_continue').value='<%=tmModel.getPrice_standard()%>'">
											</div>
										</td>
										<td class="uk-text-center"><%=tmModel.getTreatment_mode()%></td>
										<td class="uk-text-center"><%=tmModel.getTreatment_code()%></td>
										<td class="uk-text-left"><%=tmModel.getTreatment_nameth()%></td>
										<td class="uk-text-right"><%=tmModel.getPrice_standard()%></td>
									</tr>
									<%i++; } %>

									<%} %>
								</tbody>
							</table>
						</div>
						<div class="uk-width-1-4">
							<h3>รายชื่อแพทย์</h3>
							<select name="doctor" class="uk-width-1-1" size="10">

							</select>
							<h3 class="red">
								<small>คนไช้มีการระบุแพทย์ประจำไว้ </small><br> ทพ.ทันต
							</h3>
						</div>
					</div>
					<div class="uk-modal-footer uk-text-right">
						<button class="uk-button uk-button-success uk-modal-close">ตกลง</button>
						<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
					</div>
				</div>
			</div>




		</div>
	</div>


</body>
</html>