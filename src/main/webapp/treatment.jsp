<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*" %>
<%@ page import="com.smict.treatment.data.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
 
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:การรักษา</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-width-4-10 ">
						
						<div class="uk-grid bg-gray padding5  border-gray">
							<div class="uk-width-2-3 ">
								<h3 class="hd-text padding5 uk-text-primary">ประวัติคนไข้</h3>	
								<h4 class="hd-text " ><small class=" uk-text-primary">HN : </small> <s:property value="servicePatModel.hn"/></h4>
								<h4  class="hd-text"><small class=" uk-text-primary">ชื่อ-สกุล (ไทย) : </small><s:property value="servicePatModel.pre_name_th"/> <s:property value="servicePatModel.firstname_th"/> <s:property value="servicePatModel.lastname_th"/></h4>
								<h4  class="hd-text"><small class=" uk-text-primary">ชื่อ-สกุล (ENG) : </small><s:property value="servicePatModel.pre_name_en"/> <s:property value="servicePatModel.firstname_en"/> <s:property value="servicePatModel.lastname_en"/></h4>
								<h4  class="hd-text"><small class=" uk-text-primary">อายุ : </small><s:property value="servicePatModel.age"/> ปี</h4>
								<h4  class="hd-text"><small class=" uk-text-primary">เบอร์โทร: </small>
									<s:iterator value="servicePatModel.ListTelModel" status="telStatus">
										<s:if test="%{#telStatus.index > 0}">,</s:if>
										<s:property value="tel_number"/> - <s:property value="tel_typename"/>
									</s:iterator>
								</h4>
								<h4  class="hd-text"><small class=" uk-text-primary">ค้างชำระ: </small><span class="red"><s:property value="servicePatModel.deposit_money"/> บาท</span></h4>
								<h4  class="hd-text"><small class=" uk-text-primary">แผนการรักษา: </small><a href="treatment-plan-1.jsp" class="uk-button uk-button-primary">จัดการ</a></h4>
							</div>
							<div class="uk-width-1-3  ">
								<img src='<s:property value="servicePatModel.profile_pic"/>' alt="No Profile Picture" class="profile-pic">
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
				      				<td id="tooth_<%=toothModel.getTooth_num()%>">
										<img src="img/tooth/<%=toothModel.getTooth_num()%>/<%=toothModel.getTooth_num()%>.jpg" />
								
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
				      				<td id="tooth_<%=toothModel.getTooth_num()%>">
										<img src="img/tooth/<%=toothModel.getTooth_num()%>/<%=toothModel.getTooth_num()%>.jpg" />
									</td>
									<%	} %>
								</tr>
								
								
							<%	}
							%>
							</table>
						</div>
						
						<div class="uk-grid uk-grid-collapse padding5 ">   
							<s:set name="statusCheckBTN" value="servicePatModel.status" />    
							<s:if test="%{#statusCheckBTN==\"W\"}"> 
								<button class="uk-button uk-button-success uk-button-large uk-width-1-4" disabled="disabled">
								<h1 class="white"><i class="uk-icon-clock-o"></i></h1>รอรักษา </button>   
								<button class="uk-button uk-button-success uk-button-large  uk-width-1-4 " type="button" onclick="click_treatment_it()" >
									<h1 class="white"><i class="uk-icon-medkit"></i></h1> ทำการรักษา</button> 
								<button class="uk-button uk-button-success uk-button-large  uk-width-1-4" type="button" disabled="disabled">
									<h1 class="white"><i class="uk-icon-sign-out"></i></h1> รักษาเสร็จ</button> 
								<a href="#" class="uk-button uk-button-large uk-button-danger uk-width-1-4" draggable="false">
									<h1 class="white"><i class="uk-icon-money"></i></h1> คิดเงิน
								</a> 
							</s:if>
							<s:elseif test="%{#statusCheckBTN==\"P\"}">
							<s:form action="treatmentSuccess" cssClass="uk-width-1-1" method="post">
							    <button class="uk-button uk-button-success uk-button-large uk-width-1-4" disabled="disabled">
								<h1 class="white"><i class="uk-icon-clock-o"></i></h1>รอรักษา </button>   
								<button class="uk-button uk-button-success uk-button-large  uk-width-1-4 " type="button" disabled="disabled">
									<h1 class="white"><i class="uk-icon-medkit"></i></h1> ทำการรักษา</button> 
								<button class="uk-button uk-button-success uk-button-large  uk-width-1-4" name="savesuccess" type="submit" type="button">
									<h1 class="white"><i class="uk-icon-sign-out"></i></h1> รักษาเสร็จ</button> 
								<a href="finance.jsp" class="uk-button uk-button-large uk-button-danger uk-width-1-4" draggable="false">
									<h1 class="white"><i class="uk-icon-money"></i></h1> คิดเงิน
								</a>
							</s:form>
							</s:elseif>
							<s:elseif test="%{#statusCheckBTN==\"S\"}"> 
							    <button class="uk-button uk-button-success uk-button-large uk-width-1-4" disabled="disabled">
								<h1 class="white"><i class="uk-icon-clock-o"></i></h1>รอรักษา </button>   
								<button class="uk-button uk-button-success uk-button-large  uk-width-1-4 " type="button" disabled="disabled">
									<h1 class="white"><i class="uk-icon-medkit"></i></h1> ทำการรักษา</button> 
								<button class="uk-button uk-button-success uk-button-large  uk-width-1-4" type="button" disabled="disabled">
									<h1 class="white"><i class="uk-icon-sign-out"></i></h1> รักษาเสร็จ</button> 
								<a href="finance.jsp" class="uk-button uk-button-large uk-button-danger uk-width-1-4" draggable="false">
									<h1 class="white"><i class="uk-icon-money"></i></h1> คิดเงิน
								</a> 
							</s:elseif>
							<s:elseif test="%{#statusCheckBTN==\"N\"}">
							<s:form action="treatmentStatus" cssClass="uk-width-1-1" method="post">
							    <button class="uk-button uk-button-success uk-button-large uk-width-1-4" name="savewaiting" type="submit" >
								<h1 class="white"><i class="uk-icon-clock-o"></i></h1>รอรักษา </button>   
								<button class="uk-button uk-button-success uk-button-large  uk-width-1-4 " type="button" disabled="disabled">
									<h1 class="white"><i class="uk-icon-medkit"></i></h1> ทำการรักษา</button> 
								<button class="uk-button uk-button-success uk-button-large  uk-width-1-4" type="button" disabled="disabled">
									<h1 class="white"><i class="uk-icon-sign-out"></i></h1> รักษาเสร็จ</button> 
								<a href="#" class="uk-button uk-button-large uk-button-danger uk-width-1-4" draggable="false">
									<h1 class="white"><i class="uk-icon-money"></i></h1> คิดเงิน
								</a>
							</s:form>
							</s:elseif>  
							<s:else>
							
							</s:else>
						</div> 
					</div>
					<div class="uk-width-6-10">
					 
						<div class="uk-grid uk-grid-collapse">
							
							<div class="uk-width-4-10 border-gray padding5">
								 
								<div class="uk-form"> 
									<h4 class="hd-text uk-text-primary">เพิ่มรายการรักษา</h4> 
									<input type="text" class="uk-form-small" id="treatment_code" placeholder="รหัสการรักษา" required>
									<a href="#treatment" class="uk-button uk-button-primary uk-button-small" data-uk-modal="{bgclose:false}">
										<i class="uk-icon-search"></i>
									</a>  
									 
                                    <ul class="uk-tab uk-tab-grid" data-uk-tab="{connect:'#tab-content'}"> 
                                        <li class="uk-active normal" ><a href="#">ธรรมดา</a></li>
                                        <li class="continus"><a href="#">ต่อเนื่อง</a></li> 
                                    </ul>

                                    <ul id="tab-content" class="uk-switcher uk-margin">
                                     
                                    <li class="normal">
									<form action="treatmentHistory" method="post">
									<input type="hidden" id="treatment_code_normal" name="servicePatModel.treatment_code">		
									<h4 class="hd-text uk-text-primary">แพทย์ผู้รักษา</h4>  
									<input type="text" class="uk-form-small uk-width-1-1" id="doctor_name" name="servicePatModel.doctor_name" placeholder="กรุณาเลือกแพทย์" required="required">
									<input type="hidden" id="doctor_id" name="servicePatModel.doctor_id" >
									<div class="margin5 ">
										<h4 class="hd-text uk-text-primary">ประเภท</h4>
										
										<div class="uk-button-dropdown" data-uk-dropdown="{mode:'click'}">
										 <button class="uk-button" >เลือกประเภท
										 	<i class="uk-icon-caret-down"></i>
										 </button>
										 <div class="uk-dropdown uk-dropdown-small" >
											<ul id="ul_hd" class="uk-nav uk-nav-dropdown uk-dropdown-close" data-uk-switcher="{connect:'#my-id-one', animation: 'fade'}">
											    <li class="uk-disabled"><a href="#">กรุณาเลือกการรักษาก่อน</a></li>
											</ul>
										</div>
										</div>
											<ul id="my-id-one" class="uk-switcher type-proced">    
												
											</ul>
									</div> 
									<button class="uk-button uk-button-success uk-width-1-1 uk-button-small" name="savehistory" type="submit" >เพิ่มการรักษา</button>
									  
									</form>
										</li>
                                        <li class="continus"> 
                                        <form action="treatmentContinue" method="post">
                                        	<h4 class="hd-text uk-text-primary">แพทย์ผู้รักษา</h4>
                                        	<div class="uk-grid uk-grid-small"> 
	                                        	<div class="uk-width-1-1">
	                                        		<input type="text" class="uk-form-small uk-width-1-1" id="doctor_name_contunus" name="doctor_name_contunus" placeholder="กรุณาเลือกแพทย์" required="required">
	                                        		<input type="hidden" id="doctor_id_contunus" name="doctor_id_contunus" > 
	                                        		<input type="hidden" id="continue_treatment_code" name="continue_treatment_code" >
	                                        		<input type="hidden" id="continue_treatment_id" name="continue_treatment_id" >
	                                        		<input type="hidden" id="continue_phase" name="continue_phase" > 
	                                        		<input type="hidden" id="continue_count" name="continue_count" >
	                                        		<input type="hidden" id="continue_count_all" name="continue_count_all" >
	                                        	</div>
									 		</div>
									 		<div class="uk-grid uk-grid-small">
									 			<div class="uk-width-1-1">
									 			<a href="#treatment_continoue" class="uk-button uk-button-primary uk-button-small loadajax" title="ตั้งค่า" data-uk-modal="{bgclose:false}">
													<i class="uk-icon-cog"></i> ตั้งค่าการรักษาต่อเนื่อง
												</a>
												</div>
											</div> 
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-1-1 uk-container-center treatment_continue">
													 <!-- ajax --> 
											 	</div>
											</div> 
											<button class="uk-button uk-button-success uk-width-1-1 uk-button-small" name="savehistory" type="submit" >เพิ่มการรักษา</button>
									 </form>
									 </li> 
                                     </ul>  
								</div> 
							</div>
							 
							<div class="uk-width-6-10 border-gray padding5">
							    <form action="treatmentDrug" method="post">
								<h4 class="hd-text uk-text-primary">รายการยา <small>
									<a href="#drug-list" data-uk-modal class="uk-button uk-button-primary uk-button-small" onclick="btnadddrug()">เพิ่มยา</a>
									 <button class="uk-button uk-button-success uk-width-1-3 uk-button-small" name="savedrug" type="submit" >บันทึก รายการยา</button>
								</small></h4>
								<div id="drug-list" class="uk-modal uk-form">
								    <div class="uk-modal-dialog">
								    	<a class="uk-modal-close uk-close"></a>
								    	 <div class="uk-modal-header"><i class="uk-icon-medkit"></i> ยา</div>
							         	 <div class="uk-width-1-1 uk-overflow-container adddrug"> 
											 
										</div>  
								         </div>
								 </div>
								 
								 <div class="drug-table selectDrug">
									<!--  ยา ajax -->
								 </div> 
								 </form>
								 <form action="treatmentProduct" method="post">
								 <h4 class="hd-text uk-text-primary uk-form">สินค้าหน้าร้าน<small>
								 	<a href="#product-list" data-uk-modal class="uk-button uk-button-primary uk-button-small" onclick="btnaddproduct()">เพิ่มสินค้า</a>
									<button class="uk-button uk-button-success uk-width-1-3 uk-button-small" name="saveproduct" type="submit" >บันทึก รายการสินค้า</button>
								 </small></h4>
								  <div id="product-list" class="uk-modal uk-form">
								    <div class="uk-modal-dialog">
								    	<a class="uk-modal-close uk-close"></a>
								    	 <div class="uk-modal-header"><i class="uk-icon-shopping-cart"></i> สินค้า</div>
							         	 <div class="uk-width-1-1 uk-overflow-container addproduct"> 
											 
										</div>  
								         </div>
								 </div>
								
								  <div class="product-table selectProduct">
									 
								 </div>
								 </form>
							</div>
							</div>
							
						<div class="uk-grid uk-grid-collapse"> 
							<div class="uk-width-1-1">
								<form id="deleteTransection" action="treatmentDeleteTran" method="post">
								<div class="uk-panel uk-panel-box padding5 ">
									<h4 class="hd-text uk-text-primary">รายการรักษา</h4>
									<hr class="margin5">
									<div class="treatment-bill">
									
									<input type="hidden" id="treatment_id" name="servicePatModel.treatment_id" >
									<input type="hidden" id="count" name="treatment_code_delete" >
									<input type="hidden" id="treatment_mode" name="servicePatModel.treatment_mode" >
									
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
									    <tbody>
										<% 		 
											if(request.getAttribute("transectionTreatmentList")!=null){
							    				List<ServicePatientModel> transectionList = (List) request.getAttribute("transectionTreatmentList"); 
										    	for(ServicePatientModel ttModel : transectionList){   
										%> 

										<tr>
											<td><%=ttModel.getDoctor_name()%></td>
											<td><%=ttModel.getTreatment_code()%></td>
											<td><%=ttModel.getTreatment_name()%></td>
											<td><%if(ttModel.getTreatment_mode().equals("2")){ %>
													<a href="#material" data-uk-modal class="material" 
													onclick="getElementById('treatment_id').value='<%=ttModel.getTreatment_id()%>',
														getElementById('count').value='<%=ttModel.getTreatment_code()%>';">แก้ไข</a>
												<%} %>
											</td>
											<td class="uk-text-right"><%=ttModel.getPrice_standard()%></td>
											<td>
												<button class="uk-button uk-button-danger uk-button-small" type="button"
												onclick="onDelete();
												getElementById('treatment_id').value='<%=ttModel.getTreatment_id()%>',
												getElementById('count').value='<%=ttModel.getTreatment_code()%>',
												getElementById('treatment_mode').value='<%=ttModel.getTreatment_mode()%>';">
												<i class="uk-icon-close"></i></button>
											</td>
										</tr>
										<% } %>
										<% } %> 
										</tbody>
										
									</table>
									</div>
								</div>
								</form>
							</div> 
						</div>
						<div id="material" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><h2><i class="uk-icon-cart-plus"></i> วัสดุ</h2></div>
					         	<div class="uk-width-1-1 uk-overflow-container">
					         		<h4 class="hd-text uk-text-primary">เพิ่มวัสดุ</h4>  
									<select id="mcode" name="mcode" class="uk-form-small uk-width-4-10 mcode">
										<option value="">เลือกวัสดุ</option> 
									</select>
									จำนวน <input type="text" id="m_qty" placeholder="0" class="uk-form-small uk-text-right uk-width-1-10">
									<button class="uk-button uk-button-success uk-button-small savematerial">บันทึก</button>  
									<table id="table_material" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table"> 
									            <th class="uk-text-center"></th> 
									            <th class="uk-text-center">วัสดุ</th>
									            <th class="uk-text-center">จำนวน</th> 
									        </tr>
									    </thead> 
									    <tbody class="material_list">
									    	 <!-- ajax -->
										</tbody>
									</table>
									
									</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button-danger" name="updateb">ปิด</button> 
					         </div>
					    </div>
					</div>	
					</div>
				</div>
				
				<div id="treatment" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form uk-modal-dialog-large" >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><h2><i class="uk-icon-medkit"></i> เพิ่มการรักษา</h2></div>
					         <div class="uk-grid">
						         	<div class="uk-width-3-4">
						         		<h3>รายการรักษา</h3>
										<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
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
						         	 		<h3 class="red"><small>คนไช้มีการระบุแพทย์ประจำไว้ </small><br> ทพ.ทันต</h3>
						         	 	</div>
					         	 	</div>
					         <div class="uk-modal-footer uk-text-right"> 
					         	<button class="uk-button uk-button-success uk-modal-close">ตกลง</button>
			         			<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button> 
					         </div>
					    </div>
					</div> 
				
					<div id="medicine" class="uk-modal ">
					    <div class="uk-modal-dialog uk-modal-dialog-large uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-medkit"></i> ยา</div>
					         	<div class="uk-width-1-1 uk-overflow-container"> 
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table"> 
									        	<th class="uk-text-center">ยา</th>
									            <th class="uk-text-center">รับประทาน ครั้งละ</th> 
									            <th class="uk-text-center">วันละ</th>
									            <th class="uk-text-center">รับประทาน</th> 
									            <th class="uk-text-center">เวลา</th>  
									            <th class="uk-text-center"> </th> 
									        </tr>
									    </thead> 
									    <tbody>
									    	<tr>  
									    		<td class="uk-text-left"><input type="text" name="drugname" class="uk-form-small uk-width-1-1" readonly="readonly"> </td>
										        <td class="uk-text-center"><input type="text" name="pill" class="uk-form-small uk-width-1-3"> เม็ด</td>
										        <td class="uk-text-center"><input type="text" name="episode" class="uk-form-small uk-width-1-3"> ครั้ง</td> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" name="mealstatus" value="ก่อนอาหาร" > ก่อน
			                                            <input type="radio" name="mealstatus" value="หลังอาหาร" > หลัง
                                        			</div>
                                        		</td>
                                        		<td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="checkbox" name="mealtime" value="เช้า"> เช้า
			                                            <input type="checkbox" name="mealtime" value="กลางวัน"> กลางวัน
			                                            <input type="checkbox" name="mealtime" value="เย็น"> เย็น
			                                            <input type="checkbox" name="mealtime" value="ก่อนนอน"> ก่อนนอน
                                        			</div>
                                        		</td>
                                        		<td class="uk-text-center uk-width-1-10"><a href="#" class="medicinemodel" onclick="printDrug()" data-uk-modal><i class="uk-icon-print"></i></a></td>
											</tr>  
										</tbody>
									</table> 
							</div>
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close" type="button">ปิด</button>  
					         </div>
					        
					    </div>
					</div>
					
					<div id="treatment_continoue" class="uk-modal ">
					    <div class="uk-modal-dialog uk-modal-dialog-large uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-circle-o-notch"></i> ตั้งค่าการรักษาต่อเนื่อง</div>
					         	<div class="uk-grid uk-grid-small uk-width-10-10">
									
									<div class="uk-width-4-10 uk-text-right">รหัสการรักษา</div>
									<div class="uk-width-6-10"><input type="text" id="treatment_code_continue" readonly="readonly">  </div>
									
									<div class="uk-width-4-10 uk-text-right">การรักษา</div>
									<div class="uk-width-6-10"><input type="text" id="treatment_name_continue" readonly="readonly">  </div>
									
									<div class="uk-width-4-10 uk-text-right">ค่ารักษาปกติ</div>
									<div class="uk-width-6-10"><input type="text" id="price_continue" readonly="readonly"> บาท</div>
									
									<div class="uk-width-4-10 uk-text-right">ส่วนลด</div>
									<div class="uk-width-6-10"><input type="text" id="discount" > บาท</div>
									<div class="uk-width-1-1"></div> 
									
									<div class="uk-width-4-10 uk-text-right">คงเหลือ</div>
									<div class="uk-width-6-10"><input type="text" id="balance" readonly="readonly"> บาท
									<input type="hidden" id="continue_id" ></div>
									<div class="uk-width-1-1"><br></div> 
								</div>
								<button class="uk-button uk-button-success uk-button-small addtemp" name="addtemp" type="button" onclick="addtemp()">เพิ่มช่วงการรักษา</button>
								<div class="uk-grid uk-grid-small uk-width-10-10 temp hidden" id="temp1"> 
									<div class="uk-width-1-1 uk-text-right"> 
									จำนวนครั้ง : <input type="text" name="phase_qty" placeholder="1" class="uk-form-small uk-text-right uk-width-1-10" /> 
									จำนวนเงิน : <input type="text" name="phase_amount" placeholder="0" class="uk-form-small uk-text-right uk-width-1-10 phase_amount" />
									<button class="uk-button uk-button-primary uk-button-small btnsavecontinue" type="button">เพิ่ม</button>
									</div>
									<div class="uk-width-1-2 ">
										<h3>การรักษา</h3>  
										<div class="uk-width-1-1 uk-container-center">
											<table id="treTable" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1 table-components">
												<thead>
											        <tr class="hd-table">
											        	<th class="uk-text-center">ลำดับ</th>
											        	<th class="uk-text-center">รหัสการรักษา</th>  
											        	<th class="uk-text-center"> </th> 
											        </tr> 
											    </thead>
										        <tbody> 
											        <tr >
											        	<td class="uk-text-center uk-width-2-10">1</td>
											            <td class="uk-text-center uk-width-6-10">
											            	<select id="tcode" name="tcode" class="uk-form-small uk-width-1-1 tcode">
																<option value="">เลือกการรักษา</option> 
															</select>
											            </td> 
											            <td class="uk-text-center uk-width-2-10"><button class="btnt uk-button uk-button-success uk-button-small add-elements"><i class="uk-icon-plus"></i></button>
			                                			</td>
											        </tr> 
											    </tbody>
											</table>
									 	</div>
									</div>  
									<div class="uk-width-1-2">   
										<h3>ยา</h3> 
										<div class="uk-width-1-1uk-container-center">
											<table id="drugTable" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1 table-components-medicine">
												<thead>
											        <tr class="hd-table">
											        	<th class="uk-text-center">ลำดับ</th>
											        	<th class="uk-text-center">รหัสยา</th>
											        	<th class="uk-text-center">จำนวน</th> 
											        	<th class="uk-text-center"> </th> 
											        </tr> 
											    </thead>
										        <tbody> 
											        <tr>
											        	<td class="uk-text-center uk-width-2-10">1</td>
											            <td class="uk-text-center uk-width-5-10">
															<select id="dcode" name="dcode" class="uk-form-small uk-width-1-1 dcode">
																<option value="">เลือกยา</option> 
															</select>
														</td>
											            <td class="uk-text-center uk-width-1-10"><input type="text" name="drug_qty" placeholder="0" class="uk-form-small uk-width-1-1 uk-text-right" /></td> 
											            <td class="uk-text-center uk-width-2-10"><button class="btnmedicine uk-button uk-button-success uk-button-small add-elements-drug"><i class="uk-icon-plus"></i></button>
			                                			</td>
											        </tr> 
											    </tbody>
											</table>
								 		</div> 
									</div> 
									
								</div>  
							  <div class="use_temp" id="use_temp">
							  </div>
					          
					          	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-button uk-button-success" onclick="reloadbegin();" >บันทึกการรักษา</button> 
					         </div>
					    </div>
					</div> 
			</div>
		</div>
		 
		<!-- js ในการทำรูปภาพ <script type="text/javascript" src="js/html2canvas.js"></script> --> 
		<script>
			function selectDoctor(treatment_code, treatment_mode, tooth, surface, mouth, quadrant, sextant, arch, tooth_range){
				 
				$("select[name='doctor'] option[value!='']").remove(); //remove Option select district by index is not value =''
				$.ajax({
			        type: "post",
			        url: "ajax/ajax-select-treatment-doctor.jsp", //this is my servlet 
			        data: {method_type:"get",treatment_code:treatment_code},
			        async:false, 
			        success: function(result){
			        	var obj = jQuery.parseJSON(result);
			        	for(var i = 0 ;  i < obj.length;i++){ 	
			        	$("select[name='doctor']").append($('<option>').text(obj[i].doctor_name).attr('value', obj[i].doctor_id));
			        	}	 
				    } 
			     }); 
				 
				$("#ul_hd").empty();
				$("#my-id-one").empty();
				 
				if(tooth == '1'){ 
					$("#ul_hd").append($('<li id="hd_tooth" class="hd_tooth"><a href="">Tooth</a></li>'));
					$("#my-id-one").append($(
					'<li class="dt_tooth"> '+
					'<div class="uk-grid"> '+
					'	<div class="uk-width-1-2"> '+
					'		<h5 class="hd-text uk-text-primary margin5">ซี่ฟัน</h5>  '+ 
					'		<input type="text" id="tooth_tooth" name="servicePatModel.tooth_tooth" pattern="[0-9].{0,2}" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" required="required" class="uk-form-small uk-width-1-1"  >'+
					'	</div> '+
					'</div> '+
					'</li>'));
				} 
 				if(surface == '1'){   
 					$("#ul_hd").append($('<li id="hd_surf" class="hd_surface"><a href="">Surface</a></li>')); 
 					$("#my-id-one").append($(  
 							'<li class="dt_surface" >'+ 
							'<div class="uk-grid">'+ 
							'	<div class="uk-width-1-2">'+ 
							'		<h5 class="hd-text uk-text-primary margin5">ซี่ฟัน</h5> '+ 
							'		<input type="text" id="surf_tooth" name="servicePatModel.surf_tooth" pattern="[0-9].{0,}" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" class="uk-form-small uk-width-1-1" >'+ 
							'	</div>'+ 
							'	<div class="uk-width-1-2">'+ 
							'		<h5 class="hd-text uk-text-primary margin5">ด้านฟัน</h5>'+ 
							'		<input type="text" id="surf" name="servicePatModel.surf" pattern="[A-Z].{0,6}" title="กรอกข้อมูล เป็นอักษณตัวใหญ่เท่านั้น" class="uk-form-small uk-width-1-1" >'+ 
							'	</div>'+ 
							'</div>'+ 
							'<table class="surface-table">'+
							'	<tr>'+ 
							'		<td></td>'+ 
							'		<td><button class="uk-button uk-button-small " id="B" onclick="btnFunction(this)" type="button" value="1">B</button></td>'+ 
							'		<td><button class="uk-button uk-button-small " id="F" onclick="btnFunction(this)" type="button" value="1">F</button></td>'+ 
							'		<td></td>'+ 
							'	</tr>'+ 
							'	<tr>'+ 
							'		<td><button class="uk-button uk-button-small "id="M" onclick="btnFunction(this)" type="button" value="1">M</button></td>'+ 
							'		<td><button class="uk-button uk-button-small "id="O" onclick="btnFunction(this)" type="button" value="1">O</button></td>'+ 
							'		<td><button class="uk-button uk-button-small "id="I" onclick="btnFunction(this)" type="button" value="1">I</button></td>'+ 
							'		<td><button class="uk-button uk-button-small "id="D" onclick="btnFunction(this)" type="button" value="1">D</button></td>'+ 
							'	</tr>'+ 
							'	<tr>'+ 
							'		<td></td>'+ 
							'		<td colspan="2"><button class="uk-button uk-button-small " id="L" onclick="btnFunction(this)" type="button" value="1">L</button></td>'+ 
							'		<td></td>'+ 
							'	</tr>'+ 
							'</table>'+ 
						'</li>')); 
				}
				if(mouth == '1'){ 
					$("#ul_hd").append($('<li id="hd_mouth" class="hd_mouth"><a href="">Mouth</a></li>')); 
					$("#my-id-one").append($(
							'<li class="hd_mouth">'+ 
							'<h5 class="hd-text uk-text-primary margin5">เลือกทั้งปาก</h5>'+ 
						'</li>')); 
				}
				if(quadrant == '1'){ 
					$("#ul_hd").append($('<li id="hd_quadrant" class="hd_quadrant"><a href="">Quadrant</a></li>')); 
					$("#my-id-one").append($(
						'<li class="hd_quadrant">'+
						'<div class="uk-grid"> '+
						'	<label class="uk-width-1-2 margin5">'+
						'		<input type="radio" name="servicePatModel.quadrant" value="21,22,23,24,25,26,27,28"/>UL(1)</label>'+
						'	<label class="uk-width-1-2 margin5">'+
						'		<input type="radio" name="servicePatModel.quadrant" value="11,12,13,14,15,16,17,18" />UR(2)</label>'+
						'	<label class="uk-width-1-2 margin5">'+
						'		<input type="radio" name="servicePatModel.quadrant" value="31,32,33,34,35,36,37,38" />LL(4)</label>'+
						'	<label class="uk-width-1-2 margin5">'+
						'		<input type="radio" name="servicePatModel.quadrant" value="41,42,43,44,45,46,47,48" />LR(3)</label>'+
						'</div> '+
						'</li>'));
				}
				if(sextant == '1'){ 
					$("#ul_hd").append($('<li id="hd_sextant" class="hd_sextant"><a href="">Sextant</a></li>')); 
					$("#my-id-one").append($(
					'<li class="hd_sextant">'+
					'<h5 class="hd-text uk-text-primary margin5">Sextant</h5>'+
					'</li>'));
				}
				if(arch == '1'){ 
					$("#ul_hd").append($('<li id="hd_arch" class="hd_arch"><a href="">Arch</a></li>')); 
					$("#my-id-one").append($(
					'	<li class="hd_arch">'+
					'	<div class="uk-grid">'+
					'		<label class="uk-width-1-1 margin5"><input type="radio" name="servicePatModel.arch" value="U"/>U</label>'+
					'		<label  class="uk-width-1-1 margin5"><input type="radio" name="servicePatModel.arch" value="L"/>L</label>'+
					'	</div> '+
					'</li>'));
							 
				}
				if(tooth_range == '1'){ 
					$("#ul_hd").append($('<li id="hd_toothrange" class="hd_toothrange"><a href="">Tooth Range</a></li>')); 
					$("#my-id-one").append($(
					'<li class="dt_toothrange" style="overflow-x: scroll;">'+
					'<table  class="tooth-table border-gray uk-width-1-1">'+
					'	<tr class="tooth-pic">'+
					'		<td>18</td>'+
					'		<td>17</td>'+
					'		<td>16</td>'+
					'		<td>15</td>'+
					'		<td>14</td>'+
					'		<td>13</td>'+
					'		<td>12</td>'+
					'		<td>11</td>'+
					'		<td>21</td>'+
					'		<td>22</td>'+
					'		<td>23</td>'+
					'		<td>24</td>'+
					'		<td>25</td>'+
					'		<td>26</td>'+
					'		<td>27</td>'+
					'		<td>28</td>'+
					'	</tr>'+
					'</table>'+
					'<table class="tooth-table border-gray uk-width-1-1">'+
					'	<tr  class="tooth-pic">'+
					'		<td>48</td>'+
					'		<td>47</td>'+
					'		<td>46</td>'+
					'		<td>45</td>'+
					'		<td>44</td>'+
					'		<td>43</td>'+
					'		<td>42</td>'+
					'		<td>41</td>'+
					'		<td>41</td>'+
					'		<td>32</td>'+
					'		<td>33</td>'+
					'		<td>34</td>'+
					'		<td>35</td>'+
					'		<td>36</td>'+
					'		<td>37</td>'+
					'		<td>38</td>'+
					'	</tr>'+
					'</table>'+
					'</li>'));
				}
				
				  var treatment_mode = treatment_mode;
				if(treatment_mode=='การรักษาแบบธรรมดา'){
					//alert(1)
					$(".continus").removeClass("uk-active");
					$(".continus").addClass("uk-disabled");
					
					$(".normal").removeClass("uk-disabled");
					$(".normal").addClass("uk-active");
				}else{
					//alert(2)
					$(".normal").removeClass("uk-active");
					$(".normal").addClass("uk-disabled");
					
					$(".continus").removeClass("uk-disabled");
					$(".continus").addClass("uk-active");
				}  
			}
			function btnadddrug(){
				 
				$.ajax({  // select drug
				  	 
                    type: "post",
                    url: "ajax/ajax-select-drug-add.jsp", //this is my servlet 
                    data: {method_type:"get"},
                    async:true, 
                    success: function(result){ 
                    	if(result)  {
                   		var obj = JSON.parse(result);
                   		//$(".selectDrug").html("");
                       	// alert(obj)
                        var header = ' <table class="display nowrap compact stripe hover cell-border order-column" id="table_drug"> '+ 
    					'   <thead> '+
    					'	<tr class="hd-table"> '+
    					'		<th class="uk-text-center">รหัสยา</th> '+
    					'		<th class="uk-text-center">ชื่อยา</th>  '+
    					'		<th class="uk-text-center">จำนวนยาฟรี</th> '+
    					'		<th class="uk-text-center"> </th> '+
    					'		<th class="uk-text-center">เลือก</th> '+ 
    					'	</tr> '+ 
    					'	</thead> '+ 
    					'	<tbody> ';
                        	var out = header;  
                        	for(var i = 0 ; i < obj.length; i++){  
    							out += 
    							'<tr>'+
    								'<td class="uk-text-center">'+obj[i].product_id+'</td>'+
    								'<td class="uk-text-left">'+obj[i].product_name+'</td>'+
    								'<td class="uk-text-center">'+obj[i].product_free+' เม็ด</td>'+
    								"<td class='uk-text-center'><input type='text' name='qty_drug' placeholder='จำนวนเม็ด' class='uk-form-small uk-text-center uk-width-1-2'></td>"+
    								"<td class='uk-text-center'> "+
    								"<button class='uk-modal-close uk-button uk-button-success' value='"+i+"' onclick=\"add_drug(this,'"+obj[i].product_id+"','"+obj[i].product_name+"','"+obj[i].product_free+"','"+obj[i].product_transfer+"')\" >เลือก</button>"+ 
    								'</td>'+
    							'</tr>';
    						}  
    						out +=  '</tbody>'+  
    							'</table>';		
    							//alert(out)
    						$(".adddrug").html(out);
    							
    						$('#table_drug').DataTable({
    					        "order": [[ 3, "asc" ]],
    					        "ordering": false
    					    });
                         }   
                    } 
                 });
			}
			
			function add_drug(drugindex,product_id,product_name,product_free,product_transfer) {   
				 
				var drugindex = drugindex.value; 
				var checkDrug = $("input[name='qty_drug']").eq(drugindex).val(); 
				var selectDrug = $(".selectDrug").html(); 
				//alert(selectDrug)
				selectDrug = selectDrug.replace("</tbody></table>", "");
				//alert(selectDrug)
				selectDrug += '<tr>'+
								'<td class="uk-text-center uk-width-1-10"><a href="#medicine" class="medicinemodel" data-id="'+product_name+'" data-uk-modal><i class="uk-icon-search-plus"></i></a></td>'+
								'<td class="uk-text-left uk-width-6-10">'+product_name+'<input type="hidden" name="product_id" value="'+product_id+'" /></td>'+
								'<td class="uk-text-center uk-width-2-10">'+product_free+'<input type="hidden" name="product_free" value="'+product_free+'" /></td>'+ 
								'<td class="uk-width-2-10"><input type="text" name="product_transfer" value="'+checkDrug+'" class="uk-form-small uk-text-right uk-width-1-1"></td>'+ 
								'<td class="uk-text-center uk-width-1-10" ><a class="deleteDrug" ><i class="uk-icon-times"></i></a></td>'+ 
							   '</tr>'+
							'</tbody>'+
							'</table>';
				$(".selectDrug").html(selectDrug);
			}; 
			
			function btnaddproduct(){
				  
				$.ajax({  // select productt
				  	 
                    type: "post",
                    url: "ajax/ajax-select-product-add.jsp", //this is my servlet 
                    data: {method_type:"get"},
                    async:true, 
                    success: function(result){ 
                    	if(result)  {
                   		var obj = JSON.parse(result);
                   		//$(".selectDrug").html("");
                       	// alert(obj)
                        var header = ' <table class="display nowrap compact stripe hover cell-border order-column" id="table_product"> '+ 
    					'   <thead> '+
    					'	<tr class="hd-table"> '+
    					'		<th class="uk-text-center">รหัสสินค้า</th> '+
    					'		<th class="uk-text-center">ชื่อสินค้า</th>  '+ 
    					'		<th class="uk-text-center">ราคา</th> '+
    					'		<th class="uk-text-center">เลือก</th> '+ 
    					'	</tr> '+ 
    					'	</thead> '+ 
    					'	<tbody> ';
                        	var out = header;  
                        	for(var i = 0 ; i < obj.length; i++){  
    							out += 
    							'<tr>'+
    								'<td class="uk-text-center">'+obj[i].product_id+'</td>'+
    								'<td class="uk-text-left">'+obj[i].product_name+'</td>'+
    								'<td class="uk-text-center">'+obj[i].price+' บาท</td>'+
    								"<td class='uk-text-center'> "+
    								"<button class='uk-modal-close uk-button uk-button-success' value='"+i+"' onclick=\"add_product(this,'"+obj[i].product_id+"','"+obj[i].product_name+"','"+obj[i].price+"')\" >เลือก</button>"+ 
    								'</td>'+
    							'</tr>';
    						}  
    						out +=  '</tbody>'+  
    							'</table>';		
    							//alert(out)
    						$(".addproduct").html(out);
    							
    						$('#table_product').DataTable({
    					        "order": [[ 3, "asc" ]],
    					        "ordering": false
    					    });
                         }   
                    } 
                 });
			} 
			function add_product(drugindex,product_id,product_name,price) {   
				  
				var selectProduct = $(".selectProduct").html();  
				
				  selectProduct = selectProduct.replace("</tbody></table>", ""); 
				  selectProduct += '<tr>'+
								'<td class="uk-text-left uk-width-6-10">'+product_name+'<input type="hidden" name="product_id_pro" value="'+product_id+'" /></td>'+
								'<td class="uk-width-1-10"><input type="text" name="qty_pro" value="1" class="uk-form-small uk-text-right uk-width-1-1"></td>'+ 
								'<td class="uk-text-center uk-width-1-10">'+price+'<input type="hidden" name="price" value="'+price+'" /></td>'+ 
								'<td class="uk-text-center uk-width-1-10" ><a class="deleteProduct" ><i class="uk-icon-times"></i></a></td>'+ 
							   '</tr>'+
							'</tbody>'+
							'</table>';
				 		
				$(".selectProduct").html(selectProduct);
			};
			
			function addtemp() {
				 
			 	
			 	/*	$("#pcode").select2();
				$("#dcode").select2(); */
				
			    var elmnt = document.getElementById('temp1');
			    var cln = elmnt.cloneNode(true, true);
			    var w = document.getElementById('use_temp');
			    var count = 0; // this will contain the total elements.
			    for (var i = 0; i < w.childNodes.length; i++) {
			        var node = w.childNodes[i];
			        if (node.nodeType == Node.ELEMENT_NODE && node.nodeName == "DIV") {
			            count++;
			        }
			    }
			    cln.id = count; 
			    cln.className = '';
			    cln.className = 'uk-grid uk-grid-small uk-width-10-10 temp';
			    w.appendChild(cln);
			    
			    $('table[id^="drugTable"]:last').prop('id', 'drugTable'+count );
			    $('table[id^="treTable"]:last').prop('id', 'treTable'+count );
			    $('#'+count).append('<div class="uk-width-1-1 uk-text-center"><button class="uk-button uk-button-danger uk-button-small deltemp" name="deltemp" type="button">ลบช่วงการรักษา</button></div>');
			
			    $("#"+count+" .tcode").select2();
			    $("#"+count+" .dcode").select2();
			}
			
			$(document).ready(function(){
				
				$( ".m-treatment" ).addClass( "uk-active" );
				    
				$(".btn-reset").click(function(){
			    	$('.table-components tbody > tr:not(:first-child)').remove(); 
			    	$('.table-components-medicine tbody > tr:not(:first-child)').remove();
			    });
				$(document).on('click','.add-elements', function(){
					var idDiv = $(this).parent().parent().parent().parent().parent().parent().parent().attr('id');//get parent id
					var idTable = $(this).parent().parent().parent().parent().attr('id');
					
					var clone = $("#temp1 #treTable tbody tr:first-child");
					clone.find('.btnt').removeClass('add-elements uk-button-success').addClass('delete-elements uk-button-danger').html('<i class="uk-icon-times"></i>');
					clone.find('td:first-child').html($("#"+idDiv+" #"+idTable+" tbody tr").length + 1); 
					clone.clone().appendTo("#"+idDiv+" #"+idTable+" tbody");
					clone.find('td:first-child').html(1);
					clone.find('.btnt').removeClass('delete-elements uk-button-danger').addClass('add-elements uk-button-success').html('<i class="uk-icon-plus"></i>');
					
					$('.table-components tbody tr:last-child td:nth-child(2)').on('change', function(event) {
						$(this).parent().closest("tr");
					}); 
					$("#"+idDiv+" #"+idTable+" tr:last-child td .tcode").select2();
				}); 
				$(document).on('click','.add-elements-drug', function(){
					 
					var idDiv = $(this).parent().parent().parent().parent().parent().parent().parent().attr('id');//get parent id
					var idTable = $(this).parent().parent().parent().parent().attr('id');
					
					var clone = $("#temp1 #drugTable tbody tr:first-child");
					clone.find('.btnmedicine').removeClass('add-elements-drug uk-button-success').addClass('delete-elements-drug uk-button-danger').html('<i class="uk-icon-times"></i>');
					clone.find('td:first-child').html($("#"+idDiv+" #"+idTable+" tbody tr").length + 1); 
					clone.clone().appendTo("#"+idDiv+" #"+idTable+" tbody");
					clone.find('td:first-child').html(1);
					clone.find('.btnmedicine').removeClass('delete-elements-drug uk-button-danger').addClass('add-elements-drug uk-button-success').html('<i class="uk-icon-plus"></i>');
					
					$('.table-components tbody tr:last-child td:nth-child(2)').on('change', function(event) {
						$(this).parent().closest("tr");
					});
					$("#"+idDiv+" #"+idTable+" tr:last-child td .dcode").select2();
				});	
					
				 $(document).on( "click",'.delete-elements', function() {
						$(this).closest("tr").remove();
						var idDiv = $(this).parent().parent().parent().parent().parent().parent().parent().attr('id');//get parent id
						var idTable = $(this).parent().parent().parent().parent().attr('id');
						var n = 0;
						$("#"+idDiv+" #"+idTable+" tbody tr").each(function(){
							n++;
							$(this).find('td:first-child').html(n);
						});
				 }); 
				 $(document).on( "click",'.delete-elements-drug', function() {
						$(this).closest("tr").remove();
						var idDiv = $(this).parent().parent().parent().parent().parent().parent().parent().attr('id');//get parent id
						var idTable = $(this).parent().parent().parent().parent().attr('id');
						var n = 0;
						$("#"+idDiv+" #"+idTable+" tbody tr").each(function(){
							n++;
							$(this).find('td:first-child').html(n);
						});
				 });
				  
				 	$.ajax({  // select treatment continue
					  	 
	                    type: "post",
	                    url: "ajax/ajax-treatment-continue-start.jsp", //this is my servlet 
	                    data: {method_type:"get"},
	                    async:true, 
	                    success: function(result){ 
	                    	if(result)  {
	                   		var obj = JSON.parse(result);
	                   		//$(".selectDrug").html("");
	                       	// alert(obj)
	                        var header = ' <table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1 table-components" id="treatmentcontinue"> '+ 
		                    '   <thead> '+
						    '   <tr class="hd-table"> '+
						    '    	<th class="uk-text-center">เลือก</th> '+
						    '    	<th class="uk-text-center">การรักษา</th>  '+
						    '    	<th class="uk-text-center">ช่วงที่</th>  '+
						    '    	<th class="uk-text-center">ครั้งที่</th>  '+
						    '    </tr>  '+
						    '	</thead> '+ 
	    					'	<tbody> ';
	                        	var out = header;  
	                        	for(var i = 0 ; i < obj.length; i++){  
	    							out += 
	    							'<tr>'+
	    								"<td class=\"uk-text-center uk-width-1-10\"><input type=\"radio\" name=\"continue_r\" onclick=\"getElementById('continue_treatment_code').value='"+obj[i].treatment_code+"'"+
	    								",getElementById('doctor_id_contunus').value='"+obj[i].doctor_id+"',getElementById('doctor_name_contunus').value='"+obj[i].doctor_name+"'"+
	    								",getElementById('continue_treatment_id').value='"+obj[i].continue_id+"',getElementById('continue_phase').value='"+obj[i].continue_phase+"'"+
	    								",getElementById('continue_count').value='"+obj[i].continue_count+"',getElementById('continue_count_all').value='"+obj[i].continue_count_all+"';\" ></td>"+
	    								'<td class="uk-text-left uk-width-5-10">'+obj[i].treatment_nameth+'</td>'+
	    								'<td class="uk-text-center uk-width-2-10">'+obj[i].continue_phase+'</td>'+
	    								'<td class="uk-text-center uk-width-2-10">'+obj[i].continue_count+'</td>'+
	    							'</tr>';
	    						}  
	    						out +=  '</tbody>'+  
	    							'</table>';		
	    							//alert(out)
	    						$(".treatment_continue").html(out);
	    							 
	                         }   
	                    } 
	                 });
				 	
				 	$(document).on('click','.deltemp',function (){
						 $(this).closest(".temp").remove();
					 });
				 	 
				 	$("#"+idDiv+" #"+idTable+" tbody tr:last-child td:nth-child(2)").on('change', function(event) {
						
						$(this).closest("tr").find('td:nth-child(3) input').focus();
					});
				 	
				}); // end ready
				 
				// continue treatment transection continue
				$(document).on('click','.savematerial', function(){ 
					var treatment_id 	= $("#treatment_id").val();
					var treatment_code 	= $("#count").val();
					var mcode 			= $("#mcode").val();
					var m_qty 			= $("#m_qty").val();
					if(mcode==''){
						swal({
							  title: 'ผิดพลาด!',
							  text: 'กรุณาเลือกวัสดุ',
							  timer: 2000
							})
					}else if(m_qty==''){
						swal({
							  title: 'ผิดพลาด!',
							  text: 'กรุณาใส่จำนวน',
							  timer: 2000
							})
					}else{
						$.ajax({
					        type: "post",
					        url: "ajax/ajax-treatment-continue-add-material.jsp", //this is my servlet 
					        data: {method_type:"get",treatment_id:treatment_id,treatment_code:treatment_code,mcode:mcode,qty:m_qty},
					        async:false, 
					        success: function(result){ 
					        	if(result)  {
			                   		var obj = JSON.parse(result);  
			                   		var out = '';  
		                        	for(var i = 0 ; i < obj.length; i++){  
		    							out += 
		    							'<tr>'+
			    							'<td class="uk-text-center">'+ 
		                            			"<button class='uk-button uk-button-danger uk-button-small' onclick=\"delete_material('"+obj[i].continue_product_id+"');\" >ลบ</button>"+ 
			                    			'</td>'+ 
											'<td class="uk-text-left">'+obj[i].product_name+'</td>'+
											'<td class="uk-text-center">'+obj[i].qty+'</td>'+  
		    							'</tr>';
		    						}
		                        	$(".material_list").html(out); 
		                        	  
		                        	swal({
		  							  title: 'สำเร็จ!',
		  							  text: 'ข้อมูลถูกเพิ่มแล้ว',
		  							  timer: 2000
		  							})
					        	}
						    } 
					     });
						select2_material();
					}
				});
				function delete_material(continue_product_id) { 
					var treatment_code 	= $("#count").val();
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-treatment-continue-delete-material.jsp", //this is my servlet 
				        data: {method_type:"get",continue_product_id:continue_product_id,treatment_code:treatment_code},
				        async:false, 
				        success: function(result){ 
				        	if(result)  {
				        		swal({
				  	  			  title: 'คุณต้องการลบหรือไม่?',
				  	  			  text: "หากต้องการให้กดตกลง!",
				  	  			  type: 'warning',
				  	  			  showCancelButton: true,
				  	  			  confirmButtonColor: '#3085d6',
				  	  			  cancelButtonColor: '#d33',
				  	  			  confirmButtonText: 'ตกลง!',
				  	  			  cancelButtonText: 'ยกเลิก',
				  	  			}).then(function(isConfirm) {
				  	  			  	if (isConfirm) { 
					  	  			  	var obj = JSON.parse(result);  
				                   		var out = '';  
			                        	for(var i = 0 ; i < obj.length; i++){  
			    							out += 
			    							'<tr>'+
				    							'<td class="uk-text-center">'+ 
			                                		"<button class='uk-button uk-button-danger uk-button-small' onclick=\"delete_material('"+obj[i].continue_product_id+"');\" >ลบ</button>"+ 
				                    			'</td>'+ 
												'<td class="uk-text-left">'+obj[i].product_name+'</td>'+
												'<td class="uk-text-center">'+obj[i].qty+'</td>'+ 
			    							'</tr>';
			    						}
			                        	$(".material_list").html(out); 
			                        	  
			                        	swal({
				  							  title: 'สำเร็จ!',
				  							  text: 'ข้อมูลถูกลบแล้ว',
				  							  timer: 2000
				  							})
				  		    		 }else{
				  		    				  swal(
				  				    			'ยกเลิกการลบรายการ!',
				  				    			'สามารถลบรายการใหม่อีกครั้ง',
				  				    			'error'
				  				    		);  
				  		    			 }
				  	  				}); 
				        	}
					    } 
				     });   
					select2_material();
				};
				$(document).on('click','.material', function(){ 
					select2_material(); 
					var treatment_code 	= $("#count").val();
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-treatment-continue-start-material.jsp", //this is my servlet 
				        data: {method_type:"get",treatment_code:treatment_code},
				        async:false, 
				        success: function(result){ 
				        	if(result)  {
		                   		var obj = JSON.parse(result);  
		                   		var out = '';  
	                        	for(var i = 0 ; i < obj.length; i++){  
	    							out += 
	    							'<tr>'+
		    							'<td class="uk-text-center">'+ 
	                                    	"<button class='uk-button uk-button-danger uk-button-small' onclick=\"delete_material('"+obj[i].continue_product_id+"');\" >ลบ</button>"+ 
	                        			'</td>'+ 
	    								'<td class="uk-text-left">'+obj[i].product_name+'</td>'+
	    								'<td class="uk-text-center">'+obj[i].qty+'</td>'+ 
	    							'</tr>';
	    						}
	                        	$(".material_list").html(out);
	                        	  
				        	}
					    } 
				     });
				});
				function select2_material(){
					
					$("#m_qty").val('');
					$(".mcode option[value!='']").remove();
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-select2-material.jsp", //this is my servlet 
				        data: {method_type:"get"},
				        async:false, 
				        success: function(result){
				        	var obj = jQuery.parseJSON(result);
				        	 
				        	for(var i = 0 ;  i < obj.length;i++){ 	
				        	$(".mcode").append($('<option>').text(obj[i].product_id+" "+obj[i].product_name).attr('value', obj[i].product_id));
				        	}
				        	$(".mcode").select2();
					    } 
				     });
				}
				
				// continue treatment transection continue 
				// continue treatment 
				$(document).on('click','.loadajax', function(){
					$(".tcode option[value!='']").remove();
					$(".dcode option[value!='']").remove();
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-select2-treatment.jsp", //this is my servlet 
				        data: {method_type:"get"},
				        async:false, 
				        success: function(result){
				        	var obj = jQuery.parseJSON(result);
				        	 
				        	for(var i = 0 ;  i < obj.length;i++){ 	
				        	$(".tcode").append($('<option>').text(obj[i].treatment_code+" "+obj[i].treatment_nameth).attr('value', obj[i].treatment_code));
				        	}	 
					    } 
				     }); 
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-select2-drug.jsp", //this is my servlet 
				        data: {method_type:"get"},
				        async:false, 
				        success: function(result){
				        	var obj = jQuery.parseJSON(result);
				        	 
				        	for(var i = 0 ;  i < obj.length;i++){ 	
				        	$(".dcode").append($('<option>').text(obj[i].product_id+" "+obj[i].product_name).attr('value', obj[i].product_id));
				        	}	 
					    } 
				     });
				}); 
				
				$(document).on('click','.btnsavecontinue', function(){ 
					var idDiv = $(this).parent().parent().attr('id');//get parent id
					// treatment 
					var size = $("#"+idDiv+" select[name='tcode']").length;
					var tcode = '';
					for(var i=0; i<size; i++){
						if(i>0){
							tcode += ',';
						}
						tcode += $("#"+idDiv+" select[name='tcode']").eq(i).val(); 
					}
					// treatment 
					// drug
					var size = $("#"+idDiv+" select[name='dcode']").length;
					var dcode = '', drug_qty = '';
					for(var i=0; i<size; i++){
						if(i>0){
							dcode += ',';
							drug_qty += ',';
						}
						dcode += $("#"+idDiv+" select[name='dcode']").eq(i).val(); 
						drug_qty += $("#"+idDiv+" input[name='drug_qty']").eq(i).val();
					} 
					// drug
					var phase_qty 		= $("#"+idDiv+" input[name='phase_qty']").val(); 
					var phase_amount 	= $("#"+idDiv+" input[name='phase_amount']").val(); 
					var treatment_code 	= $("#treatment_code").val();
					var doctor_id 		= $("#doctor_id").val();
					var price 			= $("#price_continue").val(); 
					var discount 		= $("#discount").val(); 
					var continue_id 	= $('#continue_id').val(); 
					var balance 		= $('#balance').val();
					
					if(treatment_code==''){
						swal({
							  title: 'ผิดพลาด!',
							  text: 'กรุณาเลือก การรักษาก่อนการตั้งค่า',
							  timer: 2000
							})
					}else if(discount==''){
						swal({
							  title: 'ผิดพลาด!',
							  text: 'กรุณาใส่ค่าส่วนลด',
							  timer: 2000
							})
					}else if(tcode==''){
						swal({
							  title: 'ผิดพลาด!',
							  text: 'กรุณาเลือกการรักษา',
							  timer: 2000
							})
					}else if(dcode==''){
						swal({
							  title: 'ผิดพลาด!',
							  text: 'กรุณาเลือกยา',
							  timer: 2000
							})
					}else if(drug_qty==''){
						swal({
							  title: 'ผิดพลาด!',
							  text: 'กรุณาใส่จำนวนของยา',
							  timer: 2000
							})
					}else if(phase_amount==''){
						swal({
							  title: 'ผิดพลาด!',
							  text: 'กรุณาใส่จำนวนเงิน',
							  timer: 2000
							})
					}else if(balance>=0){ 
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-treatment-continue-add.jsp", //this is my servlet 
				        data: {method_type:"get",treatment_code:treatment_code,doctor_id:doctor_id,price:price,discount:discount,
				        	phase_qty:phase_qty,phase_amount:phase_amount,tcode:tcode,dcode:dcode,drug_qty:drug_qty,
				        	continue_phase:idDiv,continue_id:continue_id},
				        async:false, 
				        success: function(result){  
				        	if(result){
				        	$('#continue_id').val(result);
				        	
				        	swal({
								  title: 'เพิ่มข้อมูลสำเร็จ!',
								  text: 'เพิ่มข้อมูลช่วงนี้สำเร็จ',
								  timer: 2000
								})
				        	}
					    } 
				     }); 
					}else{
						swal({
							  title: 'ผิดพลาด!',
							  text: 'จำนวนเงินเกิน ในการตั้งการรักษา',
							  timer: 2000
							})
					}
				}); 
				$(document).on('click','.deltemp', function(){
					var idDiv = $(this).parent().parent().attr('id');//get parent id
					var continue_id = $('#continue_id').val();
					
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-treatment-continue-delete.jsp", //this is my servlet 
				        data: {method_type:"get",continue_id:continue_id,continue_phase:idDiv},
				        async:false, 
				        success: function(result){  
				        	if(result){
				        	$('#continue_id').val(result); 
				        	swal({confirmButtonColor: '#3085d6',
								  title: 'ลบข้อมูลสำเร็จ!',
								  text: 'ลบข้อมูลช่วงนี้สำเร็จ',
								  timer: 2000
								}).then(function() {
									sumamount(); 
								})
				        	}
					    } 
				     });
				});
				$(document).on('keyup','input[name="phase_amount"]', function(){ 
						sumamount();
				});
				$(document).on('keyup','input[name="phase_qty"]', function(){
						sumamount();
				});
				$(document).on('keyup','#discount', function(){
						sumamount();
				});
			   
				function sumamount(){
					var phase_amount = 0, phase_qty = 0, suma = 0;
					for(var i=0; i<$('input[name="phase_amount"]').length; i++){
						phase_amount = Math.floor($('input[name="phase_amount"]').eq(i).val()); 
						 
						if($('input[name="phase_qty"]').eq(i).val()=='') phase_qty = 1;
						else phase_qty = Math.floor($('input[name="phase_qty"]').eq(i).val()); 
						suma += Math.floor(phase_amount)*Math.floor(phase_qty);
					} 
					var discount 	= $("#discount").val(); if(discount=='') discount = '0';   
					var price_continue = $("#price_continue").val();
					price_continue = price_continue.replace(".00", "");
					$("#balance").val((price_continue-discount)-(suma)); 
			    }
				// continue treatment 
				
				/* ทำ htmlเป็น canvas และแปลงcanvas เป็น base64เพื่อนำไปใช้ต่อ
				html2canvas($('#tooth-table-pic'),{
					onrendered: function(canvas) {
						var dataURL = canvas.toDataURL();
						$('#text-img').val(dataURL);
						document.body.appendChild(canvas);
						
					}
				}); */ 
				   
				// table drug
				$.ajax({  // select drug
				  	 
                    type: "post",
                    url: "ajax/ajax-select-drug-start.jsp", //this is my servlet 
                    data: {method_type:"get"},
                    async:true, 
                    success: function(result){ 
                    	if(result)  {
                   		var obj = JSON.parse(result);
                       	// alert(obj)
                        var header = '<table id="DrugTable" class="uk-table uk-form border-gray"> '+ 
    					"   <thead> "+
    					"	<tr> "+
    					"		<th> </th> "+
    					"		<th>ยา</th>  "+ 
    					"		<th>ฟรี</th> "+
    					"		<th>จำนวน</th> "+ 
    					"		<th> </th> "+
    					"	</tr> "+
    					"	</thead> "+
    					"		<tbody> ";
                        	var out = header;  
                        	for(var i = 0 ; i < obj.length; i++){  
    							out += 
    							'<tr>'+
    								'<td class="uk-text-center uk-width-1-10"><a href="#medicine" class="medicinemodel" data-id="'+obj[i].product_name+'" data-uk-modal><i class="uk-icon-search-plus"></i></a></td>'+
    								'<td class="uk-text-left uk-width-6-10">'+obj[i].product_name+'<input type="hidden" name="product_id" value="'+obj[i].product_id+'" /></td>'+
    								'<td class="uk-text-center uk-width-2-10">'+obj[i].product_free+'<input type="hidden" name="product_free" value="'+obj[i].product_free+'" /></td>'+ 
    								'<td class="uk-width-2-10"><input type="text" name="product_transfer" value="'+obj[i].product_transfer+'" class="uk-form-small uk-text-right uk-width-1-1"></td>'+ 
    								'<td class="uk-text-center uk-width-1-10" ><a class="deleteDrug" ><i class="uk-icon-times"></i></a></td>'+ 
    							'</tr>';
    						}  
    						out +=  '</tbody>'+  
    							'</table>';		
    							//alert(out)
    						$(".selectDrug").html(out);
                         }else{
                        	 
                        	 var out = "<table class=\"uk-table uk-form border-gray\"> "+ 
         					"   <thead> "+
         					"	<tr> "+
         					"		<th> </th> "+
         					"		<th>ยา</th>  "+ 
         					"		<th>ฟรี</th> "+
         					"		<th>จำนวน</th> "+ 
         					"		<th> </th> "+ 
         					"		<th> </th> "+
         					"	</tr> "+
         					"</thead><tbody></tbody></table>";
                     	 $(".selectDrug").html(out);
                        	 
                         }   
                    } 
                 }); 
				 
            	// table drug
            	// table product
            	$.ajax({  // select product
				  	 
                    type: "post",
                    url: "ajax/ajax-select-product-start.jsp", //this is my servlet 
                    data: {method_type:"get"},
                    async:true, 
                    success: function(result){ 
                   	 	if(result)  {
                   		var obj = JSON.parse(result);
						var header = '<table id=\"ProductTable\" class=\"uk-table uk-form border-gray\"> '+ 
						"   <thead> "+
						"	<tr> "+
						"		<th>สินค้า</th> "+
						"		<th>จำนวน</th> "+
						"		<th>ราคา</th> "+  
						"		<th> </th> "+
						"	</tr> "+
						"	</thead> "+
    					"		<tbody> ";
                        	var out = header;  
                        	for(var i = 0 ; i < obj.length; i++){  
    							out += 
    							'<tr>'+
    								'<td class="uk-text-left uk-width-6-10">'+obj[i].product_name+'<input type="hidden" name="product_id_pro" value="'+obj[i].product_id+'" /></td>'+
    								'<td class="uk-width-1-10"><input type="text" name="qty_pro" value="'+obj[i].qty+'" class="uk-form-small uk-text-right uk-width-1-1"></td>'+ 
    								'<td class="uk-text-center uk-width-1-10">'+obj[i].price+'<input type="hidden" name="price" value="'+obj[i].price+'" /></td>'+
    								'<td class="uk-text-center uk-width-1-10" ><a class="deleteProduct" ><i class="uk-icon-times"></i></a></td>'+ 
    							'</tr>';
    						}  
    						out +=  '</tbody>'+  
    							'</table>';
            			 $(".selectProduct").html(out);
                    	}else{
                       	 
                       	 var out = "<table class=\"uk-table uk-form border-gray\"> "+ 
        					"   <thead> "+
        					"	<tr> "+
        					"		<th>สินค้า</th> "+
    						"		<th>จำนวน</th> "+
    						"		<th>ราคา</th> "+  
    						"		<th> </th> "+
        					"	</tr> "+
        					"</thead><tbody></tbody></table>";
                       	$(".selectProduct").html(out); 
                        } 
                    }
                    
                    
            	}); 
            	// table product
            	$(document).on('click', '#hd_tooth',function() {    
					$("#tooth_tooth").prop('required',true);
					
					$("#surf_tooth").removeAttr("required");
					$("#surf").removeAttr("required");
					$("#quadrant").removeAttr("required");
					
					$("#surf_tooth").val('');
					$("#surf").val('');
					$("input[name='servicePatModel.quadrant']").prop('checked', false);
					$("input[name='servicePatModel.arch']").prop('checked', false);
				});
				$(document).on('click', '#hd_surf',function() {     
					$("#surf_tooth").prop('required',true);
					$("#surf").prop('required',true);  
					
					$("#tooth_tooth").removeAttr("required");    
					$("#tooth_tooth").val('');
					$("#quadrant").removeAttr("required");
					$("input[name='servicePatModel.quadrant']").prop('checked', false);
					$("input[name='servicePatModel.arch']").prop('checked', false);
				});  
				$(document).on('click', '#hd_quadrant',function() { 
					$("input[name='servicePatModel.quadrant']").prop('required',true); 
					
					$("#tooth_tooth").removeAttr("required");    
					$("#surf_tooth").removeAttr("required");
					$("#surf").removeAttr("required");
					
					$("#tooth_tooth").val('');
					$("#surf_tooth").val('');
					$("#surf").val('');
					$("input[name='servicePatModel.arch']").prop('checked', false);
				});
				$(document).on('click', '#hd_arch',function() {
					$("input[name='servicePatModel.arch']").prop('required',true);  
					
					$("#tooth_tooth").removeAttr("required");    
					$("#surf_tooth").removeAttr("required");
					$("#surf").removeAttr("required");
					
					$("#tooth_tooth").val('');
					$("#surf_tooth").val('');
					$("#surf").val('');
					$("input[name='servicePatModel.quadrant']").prop('checked', false);
				});

				
				<% if(request.getAttribute("toothHistory")!=null){ 
					
					List<ToothModel> toothHistory = (List) request.getAttribute("toothHistory"); 
					for(ToothModel tm :toothHistory){%>
					$('#tooth_<%=tm.getTooth_num()%>').prepend('<img class="case" onerror=this.style.display="none" src="img/tooth/<%=tm.getTooth_num()%>/<%=tm.getTooth_pic_code()%>/<%=tm.getSurface()%>.png" />');
					<%}
				}%>

			$(document).on('click', '.deleteDrug',function() { 
		        var tr = $(this).closest('tr');
		        tr.css("background-color","#FF3700");
		        tr.fadeOut(400, function(){
		            tr.remove();
		        });
		        return false;
		    });
			$(document).on('click', '.deleteProduct',function() { 
		        var tr = $(this).closest('tr');
		        tr.css("background-color","#FF3700");
		        tr.fadeOut(400, function(){
		            tr.remove();
		        });
		        return false;
		    });
			 
			$(document).on("click", ".medicinemodel", function () {
				 var medicine = $(this).data('id');   
			     $("input[name='drugname']").val(medicine);
			});
			$(document).on("change", "select[name='doctor']", function () {
				var doctor_name = $("select[name='doctor'] option:selected").text().trim(); 
				var doctor_id = $("select[name='doctor'] option:selected").val().trim();   
			    $("#doctor_id").val(doctor_id);
			    $("#doctor_name").val(doctor_name);
			    $("#doctor_id_contunus").val(doctor_id);
			    $("#doctor_name_contunus").val(doctor_name); 
			});  
			  
			function printDrug() {
				var drugname 	= $("input[name='drugname']").val();
				var pill 		= $("input[name='pill']").val();
				var episode 	= $("input[name='episode']").val();
				var mealstatus  = $("input[name='mealstatus']").val();
				 
				if ($("input[name='mealtime']:checked").length) {
			          var mealtime = '';
			          $("input[name='mealtime']:checked").each(function () { 
			        	  mealtime += $(this).val()+ " - "; 
			          });
			          mealtime = mealtime.slice(0, -3);
			          //alert(chkId);
			        }
			        else {
			          alert('Nothing Selected');
			    }    
				 
			    window.open('treatmentPrint?drugname='+encodeURI(drugname)+'&pill='+pill+'&episode='+episode+'&mealstatus='+mealstatus+'&mealtime='+mealtime+'', '_blank', 'toolbar=no,menubar=no,scrollbars=yes,resizable=yes,top=10,left=20,width=1280,height=600');
			}
			function click_treatment_it() {
				var myWindowt = window.open("treatment-search-docter-room.jsp", "", "toolbar=no,menubar=no,scrollbars=yes,resizable=yes,top=30,left=280,width=800,height=500");
			}
			function btnFunction(elem){
				
				 var suf = $("#surf").val();
				 var btn =  elem;
				 if(btn.value=='1'){
					 
					 suf += btn.id;
					 $("#surf").val(suf);
					 btn.value='2';
					 elem.className +=" uk-button-primary ";
					
				 }else if(btn.value=='2'){ 
					 var suf = suf.replace(btn.id, "");
					 $("#surf").val(suf);  
					 btn.value='1';
					 elem.className =" ";
					 elem.className +=" uk-button uk-button-small ";
				 }  
			}
			function onDelete() {	
				   
				swal({
	  			  title: 'คุณต้องการลบหรือไม่?',
	  			  text: "หากต้องการให้กดตกลง!",
	  			  type: 'warning',
	  			  showCancelButton: true,
	  			  confirmButtonColor: '#3085d6',
	  			  cancelButtonColor: '#d33',
	  			  confirmButtonText: 'ตกลง!',
	  			  cancelButtonText: 'ยกเลิก',
	  			}).then(function(isConfirm) {
	  			  	if (isConfirm) { 
	  				    $("#deleteTransection").submit();
						/* swal({
							title: 'กำลังทำการลบรายการ!',
							text: 'กรุณาสักครู่.',
							timer: 3000
						}); */    
						 
		    		 }else{
		    				  swal(
				    			'ยกเลิกการลบรายการ!',
				    			'สามารถลบรายการใหม่อีกครั้ง',
				    			'error'
				    		); 
		    				  
		    			 }
	  				});
				}
			function reloadbegin() {
				window.location.href = "/SmartDental/treatmentWaitingBegin";
			}
		</script>
	</body>
</html>