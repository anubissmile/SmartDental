<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.product.model.*" %>
<%@ page import="com.smict.person.model.*" %>
 
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:งานLab</title> 
		<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
	</head> 
	 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div> 
			<div class="uk-width-9-10 ">
				<%@include file="nav-top.jsp" %>
				<form action="sendLab" method="post">
				<div class="uk-grid uk-grid-collapse">
				<!-- Table  -->
					<div class="uk-width-1-1 "> 
						<div class=" uk-panel-box uk-form">
						<h4 class="hd-text uk-text-primary padding5">ตาราง lab</h4>
						<div class="uk-grid uk-grid-small" > 
							<div class="uk-width-2-10">  
								<a href="#add_lab" class="uk-button uk-button-success btnlab" data-uk-modal>
								<i class="uk-icon-flask"></i> ส่งงาน Lab</a>
								
								<div id="add_lab" class="uk-modal ">
								    <div class="uk-modal-dialog uk-modal-dialog-large uk-form " >
								        <a class="uk-modal-close uk-close"></a>
								         <div class="uk-modal-header"><i class="uk-icon-flask"></i> ส่งงาน Lab</div>  
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-4-10"> 
											        <select class="uk-width-1-1" id="service_id" name="sendLabModel.service_id" required="required">
													   <option value="" >เลือก งาน Lab</option>
													   <%
													   
													    if(request.getAttribute("servicelist")!=null){ 
													    	List servicelist = (List) request.getAttribute("servicelist");
													     
											        		for (Iterator iterA = servicelist.iterator(); iterA.hasNext();) {
											        			ServiceModel service = (ServiceModel) iterA.next();
									      				%>  
										      			<option value="<%=service.getService_id()%>" >
										       			 	<%=service.getService_id()%> - <%=service.getService_name()%>
										       			</option>
														<%		} 
															}
														%>
											   		</select> 
										        </div>
										        <div class="uk-width-2-10 uk-text-right">
										        	<input type="checkbox" id="checkBranch" name="checkBranch" > เฉพาะสาขา
										        </div>
										        <div class="uk-width-4-10">  
											        <select class="uk-width-1-1" id="lab_id" name="sendLabModel.lab_id" required="required">
														<option value="">เลือกบริษัท</option>
													</select>
										        </div> 
										    </div> 
											<hr/> 
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-1-1">  
													<table class="display nowrap compact stripe hover cell-border order-column" id="table_lab_doctor">
													    <thead>
													        <tr class="hd-table">  
													        	<th class="uk-text-center">รหัส HN-ชื่อคนไข้</th>
													            <th class="uk-text-center">รหัส-ชื่อ การรักษา</th> 
													            <th class="uk-text-center">รหัส-ชื่อ แพทย์</th>
													            <th class="uk-text-center">วันที่ทำการรักษา</th>  
													        </tr>
													    </thead> 
													    <tfoot>
        													<tr>  
        														<th class="uk-text-center">รหัส HN-ชื่อคนไข้</th>
        														<th class="uk-text-center">รหัส-ชื่อ การรักษา</th> 
													            <th class="uk-text-center">รหัส-ชื่อ แพทย์</th>
													            <th class="uk-text-center">วันที่ทำการรักษา</th>  
        													</tr> 
        												</tfoot>
													    <tbody> 
													    	<%   
																    if(request.getAttribute("treatmentlist")!=null)	{
																	    List treatmentlist = (List) request.getAttribute("treatmentlist");
									                                	List <SendLabModel> sendlabModel = treatmentlist;
									                                	int x=0;
										            	         	 	for(SendLabModel trm : sendlabModel){ 
										            	         	 	x++; 
									            	         %>
													    	<tr>   
						                                      	<td class="uk-text-left"><input class="uk-form-controls" type="radio" id="radioSelect" name="radioSelect" required="required"
							                                            onclick="getElementById('treatment_code').value='<%=trm.getTreatment_code()%>';
								                                            getElementById('doctor_id').value='<%=trm.getDoctor_id()%>';
								                                            getElementById('surf').value='<%=trm.getSurf()%>';
								                                            getElementById('tooth').value='<%=trm.getTooth()%>';
								                                            getElementById('tooth_range').value='<%=trm.getTooth_range()%>'" > <%=trm.getHn()%> - <%=trm.getPatientname()%></td>
													    		<td class="uk-text-left"><%=trm.getTreatment_code()%> - <%=trm.getTreatment_name()%></td>
														        <td class="uk-text-left"><%=trm.getDoctor_id()%> - <%=trm.getDoctor_name()%></td> 
						                                      	<td class="uk-text-center"><%=trm.getTreatment_date()%></td>
															</tr> 
															 <% } %>
									            	         <% }else{ %>
														        	 <tr>
															            <td class="uk-text-center" colspan="4">ไม่พบข้อมูล</td> 
															        </tr> 
														   	 <% } %>
															 
														</tbody>
													</table>
													<input type="hidden" id="treatment_code" name="sendLabModel.treatment_code" >
													<input type="hidden" id="doctor_id" name="sendLabModel.doctor_id" >
												</div>
											</div>
											<hr/>
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-1-3">   
													Surface
													<input type="text" id="surf" name="sendLabModel.surf" autocomplete="off" class=" uk-width-1-1" readonly="readonly" >
												</div> 
												<div class="uk-width-1-3"> 
													Tooth
													<input type="text" id="tooth" name="sendLabModel.tooth"  autocomplete="off" class="uk-width-1-1" readonly="readonly"> 
												</div> 
												<div class="uk-width-1-3"> 
													Tooth Range
													<input type="text" id="tooth_range" name="sendLabModel.tooth_range" autocomplete="off"  class="uk-width-1-1" readonly="readonly"> 
												</div>
											</div>
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-1-3">
													ค่า lab โดยประมาณ
													<div class="uk-form-icon uk-width-1-1">
										        	<i class="uk-icon-money">
    												</i>
													<input type="text" id="est_fee" name="sendLabModel.est_fee" class=" uk-width-1-1 uk-text-center" required="required">
													</div>
												</div>
												<div class="uk-width-1-3">  
													วันที่คาดว่าจะได้รับ
													<div class="uk-form-icon uk-width-1-1">
										        	<i class="uk-icon-calendar">
    												</i>
													<input type="text" id="required_date" name="sendLabModel.required_date" autocomplete="off" class=" uk-width-1-1 uk-text-center" data-uk-datepicker="{format:'DD-MM-YYYY'}" required="required">
												 	</div>
												 </div>
											</div>
											<div class="uk-grid uk-grid-small"> 
												<div class="uk-width-1-1"> 
													หมายเหตุ
													<input type="text" id="remark" name="sendLabModel.remark" class="uk-width-1-1"> 
												</div> 
											</div>
											<hr/>
								         <div class="uk-modal-footer uk-text-right"> 
								         	<button class="uk-button uk-button-success save" type="submit" name="save">ตกลง</button>
					         				<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>  
								         </div>
								    </div>
								</div>
							</div> 
							<div class="uk-width-8-10 uk-text-right"> 
							<!--  
							  <span class="uk-text-bottom">ค้นหา - งาน Lab : </span>  
						        <input class="uk-form-small uk-width-1-10" type="text" id="s_service_name" pattern="[A-zก-๙]{0,}" title="ภาษา ไทย-อังกฤษ เท่านั้น" name="sendLabModel.s_service_name">
								<span class="uk-text-bottom">ชื่อบริษัท : </span>
						        <input class="uk-form-small uk-width-1-10" type="text" id="s_lab_name" pattern="[A-zก-๙\s]{0,}" title="ภาษา ไทย-อังกฤษ เท่านั้น" name="sendLabModel.s_lab_name">
						        <span class="uk-text-bottom">ชื่อแพทย์ : </span>
						        <input class="uk-form-small uk-width-1-10" type="text" id="s_doctor_name" pattern="[A-zก-๙]{0,}" title="ภาษา ไทย-อังกฤษ เท่านั้น" name="sendLabModel.s_doctor_name">
						        <span class="uk-text-bottom">รหัสการรักษา : </span>
						        <input class="uk-form-small uk-width-1-10" type="text" id="s_treatment_code" pattern="[A-z0-9]{0,}" title="ตัว อักษร-เลข เท่านั้น" name="sendLabModel.s_treatment_code"> 
						    --> 
						     	<span class="uk-text-bottom">ค้นหา - วันที่ส่ง Lab : </span>
						        <input class="uk-form-small uk-width-1-10" type="text" id="req_date_from" name="sendLabModel.req_date_from" autocomplete="off" class=" uk-width-1-1 uk-text-center" data-uk-datepicker="{format:'DD-MM-YYYY'}">
								<span class="uk-text-bottom">ถึง</span>
						        <input class="uk-form-small uk-width-1-10" type="text" id="req_date_to" name="sendLabModel.req_date_to" autocomplete="off" class=" uk-width-1-1 uk-text-center" data-uk-datepicker="{format:'DD-MM-YYYY'}">
						        <span class="uk-text-bottom">วันที่รับ Lab : </span>
						        <input class="uk-form-small uk-width-1-10" type="text" id="upd_date_from" name="sendLabModel.upd_date_from" autocomplete="off" class=" uk-width-1-1 uk-text-center" data-uk-datepicker="{format:'DD-MM-YYYY'}">
						        <span class="uk-text-bottom">ถึง</span>
						        <input class="uk-form-small uk-width-1-10" type="text" id="upd_date_to" name="sendLabModel.upd_date_to" autocomplete="off" class=" uk-width-1-1 uk-text-center" data-uk-datepicker="{format:'DD-MM-YYYY'}"> 
						       	<button class="uk-button uk-button-success uk-button-small searchlab" type="submit" id="search" name="search">ค้นหา</button> 
							</div>
						</div> 
						<hr>
						<div class="uk-grid uk-grid-small"> 
							<div class="uk-width-1-1 ">
							<table class="display nowrap compact stripe hover cell-border order-column" id="table_lab"> 
							    <thead>
							        <tr class="hd-table">
							        	<th class="uk-text-center uk-text-middle" rowspan="2">รหัส Lab</th>
							            <th class="uk-text-center" colspan="4">ชื่อ</th>
							            <th class="uk-text-center uk-text-middle" rowspan="2">รหัสการรักษา</th> 
							            <th class="uk-text-center" colspan="3">วันที่</th> 
							            <th class="uk-text-center" colspan="2">จำนวนเงิน</th>
							            <th class="uk-text-center uk-text-middle" rowspan="2"> </th>
							            <th class="uk-text-center uk-text-middle" rowspan="2">สถานะ</th>  
							            <th class="uk-text-center uk-text-middle" rowspan="2"> </th>
							            <th class="uk-text-center uk-text-middle" rowspan="2"> </th>
							            <th class="uk-text-center uk-text-middle" rowspan="2">กำหนดนัดหมาย</th>
							            <th class="uk-text-center uk-text-middle" rowspan="2">วันที่</th>
							            <th class="uk-text-center" colspan="2">เวลา</th>
							            <th class="uk-text-center uk-text-middle" rowspan="2">แก้ไข งาน Lab</th>
							            <th class="uk-text-center uk-text-middle" rowspan="2">อ้างอิง Lab</th>
							        </tr>
							        <tr class="hd-table">  
							        	<th class="uk-text-center">งาน Lab</th>
							        	<th class="uk-text-center">บริษัท</th>
							        	<th class="uk-text-center">แพทย์</th>
							        	<th class="uk-text-center">คนไข้</th>
							        	<th class="uk-text-center">คาดว่าจะได้รับ</th>
							        	<th class="uk-text-center">ส่ง Lab</th>
							        	<th class="uk-text-center">รับ Lab</th>
							        	<th class="uk-text-center">คาดว่าจะได้รับ</th>
							        	<th class="uk-text-center">ที่ได้รับจริง</th>
							        	<th class="uk-text-center">เริ่ม</th>
							        	<th class="uk-text-center">สิ้นสุด</th>
							        </tr>
							    </thead>  
							   
							    <tbody>
							    	<%   	String checkStatus = "";
										    if(request.getAttribute("sendlablist")!=null)	{
											    List sendlablist = (List) request.getAttribute("sendlablist");
			                                	List <SendLabModel> sendlabModel = sendlablist;
			                                	int x=0;
				            	         	 	for(SendLabModel slm : sendlabModel){ 
				            	         	 	x++; 
			            	         %> 
							          <tr class="">  
							          	<td class="uk-text-center"><%=slm.getId()%></td>
							            <td class="uk-text-left"><%=slm.getService_name()%></td> 
							            <td class="uk-text-left"><%=slm.getLab_name()%></td>
							            <td class="uk-text-left"><%=slm.getFirst_name_th()%></td>
							            <td class="uk-text-left"><%=slm.getPatientname()%></td> 
							            <td class="uk-text-center"><%=slm.getTreatment_code()%></td> 
							            <td class="uk-text-center"><%=slm.getCreate_date()%></td>
							            <td class="uk-text-center"><%=slm.getRequired_date()%></td>  
							            <td class="uk-text-center"><%=slm.getUpdate_date()%></td>
							            <td class="uk-text-center"><%=slm.getEst_fee()%></td>
							            <td class="uk-text-center"><%=slm.getLab_fee()%></td>
							            <td class="uk-text-center"> <a href="#premark" onclick="getElementById('textRemark').innerHTML='<%=slm.getRemark()%>'" class="uk-button uk-button-small" data-uk-modal>
									          	 หมายเหตุ</a>  
									    </td>
							            <td class="uk-text-center">
							            <% if(slm.getLab_status().equals("W")){ %>
							            	<p class="uk-text-bold uk-text-danger">รอรับ</p>
							            <% } else { %>
							            	<p class="uk-text-bold uk-text-success">รับแล้ว</p>
							            <% } %>
							            </td>  
							            <td class="uk-text-center"> <a href="#rev_lab" 
							            	onclick="getElementById('update_date').value='<%=slm.getUpdate_date()%>';
							            			 getElementById('ref_invoice').value='<%=slm.getRef_invoice()%>';
							            			 getElementById('lab_fee').value='<%=slm.getLab_fee()%>'; 
							            			 getElementById('id').value='<%=slm.getId()%>';checkRadio('<%=slm.getLab_status()%>')" 
							            	class="uk-button uk-button-primary uk-button-small receiveLab" data-uk-modal>
									          	 รับ Lab</a>  
									    </td>
									    <td class="uk-text-center">
							            <% if(slm.getStatus_end().equals("N")){ %>
							            	<a href="#" class="uk-icon-medium uk-icon-hover uk-icon-times-circle-o uk-text-danger"></a>
							            <% } else if(slm.getStatus_end().equals("Y")) { %>
							            	<a href="#" class="uk-icon-medium uk-icon-hover uk-icon-check-circle-o uk-text-success"></a>
							            <% } %>
							             </td>      	  
									    <td class="uk-text-center"> <a href="#appoint_lab" onclick="getElementById('id').value='<%=slm.getId()%>';
									    															getElementById('hn').value='<%=slm.getHn()%>';
									    															getElementById('doctor_id').value='<%=slm.getDoctor_id()%>'" 
									    							class="uk-button uk-button-danger uk-button-small" data-uk-modal>
									          	 รักษา ลูกค้า</a>  </td> 
									    <td class="uk-text-center"><%=slm.getReturn_lab()%></td>
									    <td class="uk-text-center"><%=slm.getTimebegin()%></td>
									    <td class="uk-text-center"><%=slm.getTimeend()%></td>
									    <td class="uk-text-center"> 
									    	<%if(slm.isCheckuse()==false){ %>
									    	<a href="#update_lab" onclick="getElementById('show_ref_id').value='<%=slm.getRef_id()%>';getElementById('ref_id').value='<%=slm.getRef_id()%>';
									    									getElementById('id').value='<%=slm.getId()%>';" 
									    	class="uk-button uk-button-danger uk-button-small btnupdatelab" data-uk-modal>
											<%=slm.getId()%>-<%=slm.getService_name()%></a>
											<%}else{ %> 
												 
											<%}%>
									    </td>
									    <td class="uk-text-center"> 
									    	<button onclick="RefLab('<%=slm.getId()%>');" class="uk-button uk-button-success uk-button-small">เลขที่ <%=slm.getRef_id()%></button> 
									    </td>
							         </tr>
			            	         
			            	         <% } %>
			            	         <% }else{ %>
								        	 <tr>
									            <td class="uk-text-center" colspan="16">ไม่พบข้อมูล</td> 
									        </tr> 
								     <% } %>
							         
							    </tbody>
							</table>
							</div>
						</div>
						<div class="uk-overflow-container">
						<input type="hidden" id="id" name="sendLabModel.id"> <input type="hidden" id="hn" name="sendLabModel.hn">
							
							<div id="premark" class="uk-modal ">
							    <div class="uk-modal-dialog uk-form " >
							        <a class="uk-modal-close uk-close"></a>
							         <div class="uk-modal-header">หมายเหตุ <i class="uk-icon-commenting"></i></div>  
										<div class="uk-grid uk-grid-small">
											<div class="uk-width-1-1">
							         		  	<p id="textRemark" class="uk-text-bold"> </p> 
									        </div>  
									    </div>  
								         <div class="uk-modal-footer uk-text-right"> 
								         	<button class="uk-button uk-button-danger uk-modal-close">กลับ</button> 
								         </div>
							    </div>
							</div>
						
							<div id="rev_lab" class="uk-modal ">
								    <div class="uk-modal-dialog uk-form " >
								        <a class="uk-modal-close uk-close"></a>
								         <div class="uk-modal-header"><i class="uk-icon-flask"></i> รับ งาน Lab</div>  
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-4-10">
								         		<input type="radio" id="lab_status1" name="sendLabModel.lab_status" value="W" > รอรับงาน <input type="radio" id="lab_status2" name="sendLabModel.lab_status" value="R" > รับงานเรียบร้อยแล้ว 
										        </div> 
										        <div class="uk-width-2-10 uk-text-right">
										        	วันที่รับ Lab :
										        </div> 
										        <div class="uk-width-4-10"> 
										        	<div class="uk-form-icon uk-width-1-1">
										        	<i class="uk-icon-calendar">
    												</i> 
								         			<input class="uk-form-small uk-width-6-10  uk-text-center" type="text" required="required" autocomplete="off" data-uk-datepicker="{format:'DD-MM-YYYY'}"   
												        id="update_date" name="sendLabModel.update_date"  >  
												    </div>
										        </div>
										    </div> 
											<hr/>
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-1-3">
													<div class="uk-width-1-1">
								    				เลขที่ Invoice
													<input type="text" id="ref_invoice" name="sendLabModel.ref_invoice" required="required" class="uk-form-small uk-width-1-1" >
													</div>
												</div> 
												<div class="uk-width-1-3">
													<div class="uk-width-1-1">
								    				จำนวนเงิน ค่า Lab
													<input type="text" id="lab_fee" name="sendLabModel.lab_fee" required="required" class="uk-form-small uk-width-1-1" >
													</div>
												</div>
											</div>
											<hr/>
								         <div class="uk-modal-footer uk-text-right"> 
								         	<button class="uk-button uk-button-success savereceivelab" type="submit" name="savereceivelab">บันทึก</button>
					         				<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>  
								         </div>
								    </div>
								</div>
								
								<div id="appoint_lab" class="uk-modal ">
								    <div class="uk-modal-dialog uk-form " >
								        <a class="uk-modal-close uk-close"></a>
								         <div class="uk-modal-header"><i class="uk-icon-flask"></i> นัดหมาย งาน Lab</div>  
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-1-3">
													<div class="uk-form-icon uk-width-1-1">
										        	<i class="uk-icon-calendar">
    												</i>
													<input type="text" id="return_lab" name="sendLabModel.return_lab" autocomplete="off" required="required"  placeholder="กำหนดวันที่รับ lab" class=" uk-width-1-1 uk-text-center" data-uk-datepicker="{format:'DD-MM-YYYY'}">
													</div> 
												</div>
												<div class="uk-width-2-10"> 
													<div class=" clockpicker pull-center" data-placement="right" data-align="top" data-autoclose="true">
														<input type="text" class="uk-width-1-1 uk-text-center"  autocomplete="off" value="" id="timebegin" required="required" name="sendLabModel.timebegin" placeholder="เวลาเริ่ม">
													</div> 
												</div> 
												<div class="uk-width-2-10"> 
													<div class=" clockpicker pull-center" data-placement="right" data-align="top" data-autoclose="true">
														<input type="text" class="uk-width-1-1 uk-text-center"  autocomplete="off" value="" id="timeend" required="required" name="sendLabModel.timeend" placeholder="เวลาสิ้นสุด">
													</div>
												</div>
											</div>
											<hr>
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-3-10">
													หัวข้อ
													<input type="text" id="title" name="sendLabModel.title" required="required" class=" uk-width-1-1" >
												</div>
												<div class="uk-width-1-10"> 
													ห้องที่
													<input type="text" id="room1" name="sendLabModel.room" required="required" class=" uk-width-1-1" > 
												</div> 
												<div class="uk-width-6-10"> 
													สถานะ
													<input type="radio" id="cradio1" name="sendLabModel.confirm_status" value="Y" checked="checked"> <label> ยืนยันนัดหมาย </label> 
												</div>
											</div> 
								         <div class="uk-modal-footer uk-text-right">
								         	<button class="uk-button uk-button-success savetime" type="submit" name="savetime">ตกลง</button>
					         				<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>  
								         </div>
								    </div>
								</div>
								
								<div id="update_lab" class="uk-modal ">
								    <div class="uk-modal-dialog uk-modal-dialog-large uk-form " >
								        <a class="uk-modal-close uk-close"></a>
								         <div class="uk-modal-header"><i class="uk-icon-flask"></i> ส่งงาน Lab</div>  
								         	<div class="uk-grid uk-grid-small">
												<div class="uk-width-4-10"> 
													เลขอ้างอิง Lab
													<input type="text" id="show_ref_id" class=" uk-width-1-1" readonly="readonly" >
													<input type="hidden" id="ref_id" name="sendLabModel.ref_id" class=" uk-width-1-1" readonly="readonly" >
								         		</div>
								         	</div>
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-4-10"> 
											        <select class="uk-width-1-1" id="up_service_id" name="sendLabModel.up_service_id" required="required">
													   <option value="" >เลือก งาน Lab</option>
													   <%
													   
													    if(request.getAttribute("servicelist")!=null){ 
													    	List servicelist = (List) request.getAttribute("servicelist");
													     
											        		for (Iterator iterA = servicelist.iterator(); iterA.hasNext();) {
											        			ServiceModel service = (ServiceModel) iterA.next();
									      				%>  
										      			<option value="<%=service.getService_id()%>" >
										       			 	<%=service.getService_id()%> - <%=service.getService_name()%>
										       			</option>
														<%		} 
															}
														%>
											   		</select> 
										        </div>
										        <div class="uk-width-2-10 uk-text-right">
										        	<input type="checkbox" id="checkBranchUp" name="checkBranchUp" > เฉพาะสาขา
										        </div>
										        <div class="uk-width-4-10">  
											        <select class="uk-width-1-1" id="up_lab_id" name="sendLabModel.up_lab_id" >
														<option value="">เลือกบริษัท</option>
													</select>
										        </div> 
										    </div> 
											<hr/>
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-1-1">  
													<table class="display nowrap compact stripe hover cell-border order-column"  id="table_lab_doctor2">
													    <thead>
													        <tr class="hd-table">  
													        	<th class="uk-text-center">รหัส HN-ชื่อคนไข้</th>
													            <th class="uk-text-center">รหัส-ชื่อ การรักษา</th> 
													            <th class="uk-text-center">รหัส-ชื่อ แพทย์</th>
													            <th class="uk-text-center">วันที่ทำการรักษา</th>  
													        </tr>
													    </thead>
													    <tfoot>
        													<tr>  
        														<th class="uk-text-center">รหัส HN-ชื่อคนไข้</th>
        														<th class="uk-text-center">รหัส-ชื่อ การรักษา</th> 
													            <th class="uk-text-center">รหัส-ชื่อ แพทย์</th>
													            <th class="uk-text-center">วันที่ทำการรักษา</th>  
        													</tr> 
        												</tfoot> 
													    <tbody>
													    	<%   
																    if(request.getAttribute("treatmentlist")!=null)	{
																	    List treatmentlist = (List) request.getAttribute("treatmentlist");
									                                	List <SendLabModel> sendlabModel = treatmentlist;
									                                	int x=0;
										            	         	 	for(SendLabModel trm : sendlabModel){ 
										            	         	 	x++; 
									            	         %>
													    	<tr>  
													    		<td class="uk-text-left"><input class="uk-form-controls" type="radio" id="radioSelect1" name="radioSelect1" 
							                                            onclick="getElementById('up_treatment_code').value='<%=trm.getTreatment_code()%>';
								                                            getElementById('up_doctor_id').value='<%=trm.getDoctor_id()%>';
								                                            getElementById('up_surf').value='<%=trm.getSurf()%>';
								                                            getElementById('up_tooth').value='<%=trm.getTooth()%>';
								                                            getElementById('up_tooth_range').value='<%=trm.getTooth_range()%>'" > <%=trm.getHn()%> - <%=trm.getPatientname()%></td>
													    		<td class="uk-text-left"><%=trm.getTreatment_code()%> - <%=trm.getTreatment_name()%></td>
														        <td class="uk-text-left"><%=trm.getDoctor_id()%> - <%=trm.getDoctor_name()%></td> 
						                                      	<td class="uk-text-center"><%=trm.getTreatment_date()%></td>
															</tr> 
															 <% } %>
									            	         <% }else{ %>
														        	 <tr>
															            <td class="uk-text-center" colspan="4">ไม่พบข้อมูล</td> 
															        </tr> 
														   	 <% } %>
															 
														</tbody>
													</table>
													<input type="hidden" id="up_treatment_code" name="sendLabModel.up_treatment_code" >
													<input type="hidden" id="up_doctor_id" name="sendLabModel.up_doctor_id" >
												</div>
											</div>
											<hr/>
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-1-3">   
													Surface
													<input type="text" id="up_surf" name="sendLabModel.up_surf" autocomplete="off" class=" uk-width-1-1"  >
												</div> 
												<div class="uk-width-1-3"> 
													Tooth
													<input type="text" id="up_tooth" name="sendLabModel.up_tooth"  autocomplete="off" class="uk-width-1-1" > 
												</div> 
												<div class="uk-width-1-3"> 
													Tooth Range
													<input type="text" id="up_tooth_range" name="sendLabModel.up_tooth_range" autocomplete="off"  class="uk-width-1-1" > 
												</div>
											</div>
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-1-3">
													ค่า lab โดยประมาณ
													<div class="uk-form-icon uk-width-1-1">
										        	<i class="uk-icon-money">
    												</i>
													<input type="text" id="up_est_fee" name="sendLabModel.up_est_fee" class=" uk-width-1-1 uk-text-center" >
													</div>
												</div>
												<div class="uk-width-1-3">  
													วันที่คาดว่าจะได้รับ
													<div class="uk-form-icon uk-width-1-1">
										        	<i class="uk-icon-calendar">
    												</i>
													<input type="text" id="up_required_date" name="sendLabModel.up_required_date" autocomplete="off" class=" uk-width-1-1 uk-text-center" data-uk-datepicker="{format:'DD-MM-YYYY'}" >
												 	</div>
												 </div>
											</div>
											<div class="uk-grid uk-grid-small"> 
												<div class="uk-width-1-1"> 
													หมายเหตุ
													<input type="text" id="up_remark" name="sendLabModel.up_remark" class="uk-width-1-1"> 
												</div> 
											</div>
											<hr/>
								         <div class="uk-modal-footer uk-text-right"> 
								         	<button class="uk-button uk-button-success saveedit" type="submit" name="saveedit">ตกลง</button>
					         				<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>  
								         </div>
								    </div>
								</div>
						
						</div>
						</div> 
						
					</div>
				 
			</div>
			</form>
		</div>
		</div>
<script type="text/javascript">
function RefLab(id) { 
		var load = window.open('/SmartDental/sendLabWindow.action?id='+id+' ','',
    	'scrollbars=yes,menubar=no,height=600,width=1024,resizable=yes,toolbar=no,location=yes,status=no');
	}
 </script>
<script type="text/javascript">		
$('.clockpicker').clockpicker()
	.find('input').change(function(){
		console.log(this.value);
	});
var input = $('#single-input').clockpicker({
	placement: 'bottom',
	align: 'left',
	autoclose: true,
	'default': 'now'
});

$('.clockpicker-with-callbacks').clockpicker({
		donetext: 'Done',
		init: function() { 
			console.log("colorpicker initiated");
		},
		beforeShow: function() {
			console.log("before show");
		},
		afterShow: function() {
			console.log("after show");
		},
		beforeHide: function() {
			console.log("before hide");
		},
		afterHide: function() {
			console.log("after hide");
		},
		beforeHourSelect: function() {
			console.log("before hour selected");
		},
		afterHourSelect: function() {
			console.log("after hour selected");
		},
		beforeDone: function() {
			console.log("before done");
		},
		afterDone: function() {
			console.log("after done");
		}
	})
	.find('input').change(function(){
		console.log(this.value);
	});

// Manually toggle to the minutes view
$('#check-minutes').click(function(e){
	// Have to stop propagation here
	e.stopPropagation();
	input.clockpicker('show')
			.clockpicker('toggleView', 'minutes');
});
if (/mobile/i.test(navigator.userAgent)) {
	$('input').prop('readOnly', true);
}
</script>		
<script>
function checkRadio(lab_status){  
	if(lab_status=='W'){   
		
		var x = document.getElementById("lab_status1");
		var z = document.getElementById("lab_status2");
	    x.checked = true;  
	    z.checked = false;
		
	 //   $('#lab_status2').attr("disabled", false);
	//	$('#lab_status1').attr("disabled", false);
	    
	}else if(lab_status=='R'){  

		var x = document.getElementById("lab_status1");
		var z = document.getElementById("lab_status2");
	    x.checked = false;
	    z.checked = true;
		
	//	$('#lab_status2').attr("disabled", true);
	//	$('#lab_status1').attr("disabled", true);
	} 
}

$(document).on("click",".searchlab",function(){
	$("#service_id").removeAttr("required");
	$("#radioSelect").removeAttr("required");
	$("#required_date").removeAttr("required");
	$("#lab_id").removeAttr("required");
	$("#est_fee").removeAttr("required");
	
	$("#update_date").removeAttr("required");
	$("#ref_invoice").removeAttr("required");
	$("#lab_fee").removeAttr("required");
	
	$("#return_lab").removeAttr("required");
	$("#timebegin").removeAttr("required");
	$("#timeend").removeAttr("required");
	$("#title").removeAttr("required");
	$("#room1").removeAttr("required");
	
	$("#up_service_id").removeAttr("required");
	$("#up_radioSelect1").removeAttr("required");
	$("#up_required_date").removeAttr("required");
	$("#up_lab_id").removeAttr("required");
	$("#up_est_fee").removeAttr("required");
	
}).on("click",".savetime",function(){
	$("#service_id").removeAttr("required");
	$("#radioSelect").removeAttr("required");
	$("#required_date").removeAttr("required");
	$("#lab_id").removeAttr("required");
	$("#est_fee").removeAttr("required");
	
	$("#update_date").removeAttr("required");
	$("#ref_invoice").removeAttr("required");
	$("#lab_fee").removeAttr("required");
	
	$("#up_service_id").removeAttr("required");
	$("#up_radioSelect1").removeAttr("required");
	$("#up_required_date").removeAttr("required");
	$("#up_lab_id").removeAttr("required");
	$("#up_est_fee").removeAttr("required");
	
}).on("click",".savereceivelab",function(){ 
	
	$("#service_id").removeAttr("required");
	$("#radioSelect").removeAttr("required");
	$("#required_date").removeAttr("required");
	$("#lab_id").removeAttr("required");
	$("#est_fee").removeAttr("required");
	
	$("#return_lab").removeAttr("required");
	$("#timebegin").removeAttr("required");
	$("#timeend").removeAttr("required");
	$("#title").removeAttr("required");
	$("#room1").removeAttr("required");
	 
	$("#up_service_id").removeAttr("required");
	$("#up_radioSelect1").removeAttr("required");
	$("#up_required_date").removeAttr("required");
	$("#up_lab_id").removeAttr("required");
	$("#up_est_fee").removeAttr("required");
	
}).on("click",".save",function(){
	
	$("#return_lab").removeAttr("required");
	$("#timebegin").removeAttr("required");
	$("#timeend").removeAttr("required");
	$("#title").removeAttr("required");
	$("#room1").removeAttr("required");
	
	$("#update_date").removeAttr("required");
	$("#ref_invoice").removeAttr("required");
	$("#lab_fee").removeAttr("required");
	 
	$("#up_service_id").removeAttr("required");
	$("#up_radioSelect1").removeAttr("required");
	$("#up_required_date").removeAttr("required");
	$("#up_lab_id").removeAttr("required");
	$("#up_est_fee").removeAttr("required");
	
}).on("click",".saveedit",function(){ 
	$("#service_id").removeAttr("required");
	$("#radioSelect").removeAttr("required");
	$("#required_date").removeAttr("required");
	$("#lab_id").removeAttr("required");
	$("#est_fee").removeAttr("required");
	
	$("#return_lab").removeAttr("required");
	$("#timebegin").removeAttr("required");
	$("#timeend").removeAttr("required");
	$("#title").removeAttr("required");
	$("#room1").removeAttr("required");
	
	$("#update_date").removeAttr("required");
	$("#ref_invoice").removeAttr("required");
	$("#lab_fee").removeAttr("required"); 
	
}).on("change","select[name='sendLabModel.service_id']",function(){
	var service_id = $("select[name='sendLabModel.service_id']").val();
	if(service_id == ''){ 
		$("#checkBranch").attr("disabled", true);
		$('#checkBranch').attr('checked', false);
	}else{
		$("#checkBranch").removeAttr("disabled");
		$('#checkBranch').attr('checked', false);
	}
	var index = $("select[name='sendLabModel.service_id']").index(this); //GetIndex
	$("select[name='lab_id'] option[value!='']").remove();  //remove Option select amphur by index is not value ='' 
	if($(this).val() != ''){ 
		$("select[name='lab_id'] option[value ='']").text("กรุณาเลือกบริษัท"); 
		$.ajax({
	        type: "post",
	        url: "ajax/ajax-lab-company.jsp", //this is my servlet 
	        data: {method_type:"get",service_id:$(this).val()},
	        async:false, 
	        success: function(result){
	        	var obj = jQuery.parseJSON(result);
	        	for(var i = 0 ;  i < obj.length;i++){
	        		
	        		$("select[name='sendLabModel.lab_id']").append($('<option>').text(obj[i].lab_id+" "+obj[i].lab_name).attr('value', obj[i].lab_id));
	        		
	        	}
		    } 
	     });
	} 
}).on("change","select[name='sendLabModel.up_service_id']",function(){
	var service_id = $("select[name='sendLabModel.up_service_id']").val();
	if(service_id == ''){ 
		$("#checkBranchUp").attr("disabled", true);
		$('#checkBranchUp').attr('checked', false);
	}else{
		$("#checkBranchUp").removeAttr("disabled");
		$('#checkBranchUp').attr('checked', false);
	}
	var index = $("select[name='sendLabModel.up_service_id']").index(this); //GetIndex
	$("select[name='up_lab_id'] option[value!='']").remove();  //remove Option select amphur by index is not value ='' 
	if($(this).val() != ''){ 
		$("select[name='up_lab_id'] option[value ='']").text("กรุณาเลือกบริษัท"); 
		$.ajax({
	        type: "post",
	        url: "ajax/ajax-lab-company.jsp", //this is my servlet 
	        data: {method_type:"get",service_id:$(this).val()},
	        async:false, 
	        success: function(result){
	        	var obj = jQuery.parseJSON(result);
	        	for(var i = 0 ;  i < obj.length;i++){
	        		
	        		$("select[name='sendLabModel.up_lab_id']").append($('<option>').text(obj[i].lab_id+" "+obj[i].lab_name).attr('value', obj[i].lab_id));
	        		
	        	}
		    } 
	     });
	} 
}).on("click",".btnlab",function(){
	$("#service_id").select2();
	$("#lab_id").select2();
	$("#doctor_id").select2(); 
	 
}).on("click",".btnupdatelab",function(){ 
	$("#up_service_id").select2();
	$("#up_lab_id").select2();
	 
}).on("click","#checkBranch",function(){
	var service_id = $("select[name='sendLabModel.service_id']").val();
	$("select[name='sendLabModel.lab_id'] option[value!='']").remove();
	
	if($(this).prop("checked") == true){ 
		  
		$.ajax({
	        type: "post",
	        url: "ajax/ajax-lab-company.jsp", //this is my servlet 
	        data: {method_type:"get",service_id:service_id, branch_id:"SAWA"},
	        async:false, 
	        success: function(result){
	        	var obj = jQuery.parseJSON(result);
	        	for(var i = 0 ;  i < obj.length;i++){
	        		
	        		$("select[name='sendLabModel.lab_id']").append($('<option>').text(obj[i].lab_id+" "+obj[i].lab_name).attr('value', obj[i].lab_id));
	        		
	        	}
		    } 
	     });
        
    }
    else if($(this).prop("checked") == false){ 
		$.ajax({
	        type: "post",
	        url: "ajax/ajax-lab-company.jsp", //this is my servlet 
	        data: {method_type:"get",service_id:service_id},
	        async:false, 
	        success: function(result){
	        	var obj = jQuery.parseJSON(result);
	        	for(var i = 0 ;  i < obj.length;i++){
	        		
	        		$("select[name='sendLabModel.lab_id']").append($('<option>').text(obj[i].lab_id+" "+obj[i].lab_name).attr('value', obj[i].lab_id));
	        		
	        	}
		    } 
	     });
    }
	
	
}).on("click","#checkBranchUp",function(){
	 
	var service_id = $("select[name='sendLabModel.up_service_id']").val();
	$("select[name='sendLabModel.up_lab_id'] option[value!='']").remove();
	
	if($(this).prop("checked") == true){ 
		  
		$.ajax({
	        type: "post",
	        url: "ajax/ajax-lab-company.jsp", //this is my servlet 
	        data: {method_type:"get",service_id:service_id, branch_id:"SAWA"},
	        async:false, 
	        success: function(result){
	        	var obj = jQuery.parseJSON(result);
	        	for(var i = 0 ;  i < obj.length;i++){
	        		
	        		$("select[name='sendLabModel.up_lab_id']").append($('<option>').text(obj[i].lab_id+" "+obj[i].lab_name).attr('value', obj[i].lab_id));
	        		
	        	}
		    } 
	     });
        
    }
    else if($(this).prop("checked") == false){ 
		$.ajax({
	        type: "post",
	        url: "ajax/ajax-lab-company.jsp", //this is my servlet 
	        data: {method_type:"get",service_id:service_id},
	        async:false, 
	        success: function(result){
	        	var obj = jQuery.parseJSON(result);
	        	for(var i = 0 ;  i < obj.length;i++){
	        		
	        		$("select[name='sendLabModel.up_lab_id']").append($('<option>').text(obj[i].lab_id+" "+obj[i].lab_name).attr('value', obj[i].lab_id));
	        		
	        	}
		    } 
	     });
    }
	
	
}).ready(function() {		  
			$('#table_lab tfoot th').each( function () {
				
		        var title = $(this).text();
		        $(this).html( '<input type="text" placeholder="'+title+'" size="5" />'  );
		    } );
		 
		    // DataTable 
		    var table = $('#table_lab').DataTable({
		    	 "scrollX": true,
		    	// scrollY:        '50vh',
		         scrollCollapse: true
		    });
		    
		    // Apply the search
		    $("#table_lab tfoot input").on( 'keyup change', function () {
		        table
		            .column( $(this).parent().index()+':visible' )
		            .search( this.value )
		            .draw();
		    } );
		   
		    $('#table_lab_doctor tfoot th').each( function () {
		        var title = $(this).text();
		        $(this).html( '<input type="text" placeholder="'+title+'" />'  );
		    } );
		 
		    var table1 = $("#table_lab_doctor").DataTable({ 
		    }); 
			
			$("#table_lab_doctor tfoot input").on( 'keyup change', function () {
		        table1
		            .column( $(this).parent().index()+':visible' )
		            .search( this.value )
		            .draw();
		       
		    } );
			
			 $('#table_lab_doctor2 tfoot th').each( function () {
			        var title = $(this).text();
			        $(this).html( '<input type="text" placeholder="'+title+'" />'  );
			    } );
			 
			    var table2 = $("#table_lab_doctor2").DataTable({ 
			    }); 
				
				$("#table_lab_doctor2 tfoot input").on( 'keyup change', function () {
			        table2
			            .column( $(this).parent().index()+':visible' )
			            .search( this.value )
			            .draw();
			    } ); 
	
		$("#checkBranch").attr("disabled", true); 
		
		$("#required_date").datepicker({
		    format: "dd-mm-yyyy",
	        clearBtn: true,
	        autoclose: true,
	        todayHighlight: true
	    });
		$("#up_required_date").datepicker({
		    format: "dd-mm-yyyy",
	        clearBtn: true,
	        autoclose: true,
	        todayHighlight: true
	    });
		$("#return_lab").datepicker({
		    format: "dd-mm-yyyy",
	        clearBtn: true,
	        autoclose: true,
	        todayHighlight: true
	    }); 
		$("#update_date").datepicker({
		    format: "dd-mm-yyyy",
	        clearBtn: true,
	        autoclose: true,
	        todayHighlight: true
	    }); 
		$("#req_date_from").datepicker({
		    format: "dd-mm-yyyy",
	        clearBtn: true,
	        autoclose: true,
	        todayHighlight: true
	    });
		$("#req_date_to").datepicker({
		    format: "dd-mm-yyyy",
	        clearBtn: true,
	        autoclose: true,
	        todayHighlight: true
	    });
		$("#upd_date_from").datepicker({
		    format: "dd-mm-yyyy",
	        clearBtn: true,
	        autoclose: true,
	        todayHighlight: true
	    });
		$("#upd_date_to").datepicker({
		    format: "dd-mm-yyyy",
	        clearBtn: true,
	        autoclose: true,
	        todayHighlight: true
	    });
		  
		$(".day_lab").change(function(){
			var day_lab = $(".day_lab").val(); 
			if(day_lab==0){
				$('.time_lab').children('option:not(:first)').remove(); 
			}else if(day_lab==1){
				$('.time_lab').children('option:not(:first)').remove();
		        $(".time_lab").append(
						"<option>8:00 น</option>"+
						"<option>9:00 น</option>"+
						"<option>10:00 น</option>"+
						"<option>11:00 น</option>"+
						"<option>12:00 น</option>");
			}else if(day_lab==2){
				$('.time_lab').children('option:not(:first)').remove();
		        $(".time_lab").append( 
						"<option>13:00 น</option>"+
						"<option>14:00 น</option>"+
						"<option>15:00 น</option>"+
						"<option>16:00 น</option>"+
						"<option>17:00 น</option>");
			}else if(day_lab==3){
				$('.time_lab').children('option:not(:first)').remove();
		        $(".time_lab").append(
		        		"<option>8:00 น</option>"+
						"<option>9:00 น</option>"+
						"<option>10:00 น</option>"+
						"<option>11:00 น</option>"+
						"<option>12:00 น</option>"+
						"<option>13:00 น</option>"+
						"<option>14:00 น</option>"+
						"<option>15:00 น</option>"+
						"<option>16:00 น</option>"+
						"<option>17:00 น</option>");
			}else if(day_lab==4){
				$('.time_lab').children('option:not(:first)').remove();
		        $(".time_lab").append(
		        		"<option>18:00 น</option>"+
						"<option>19:00 น</option>"+
						"<option>20:00 น</option>"+
						"<option>21:00 น</option>");
			}
	    });
});	 

</script>		
	</body>
</html>