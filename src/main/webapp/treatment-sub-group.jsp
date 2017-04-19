<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.all.model.*" %>
<%@ page import="com.smict.product.model.*" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : หมวดการรักษา</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>

					<div class="uk-grid"></div>
					<form id="service" action="treatmentGroupExcute" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> หมวดการรักษา</h3>
								</div>
								<div class="uk-grid uk-grid-small uk-form "> 
								<!-- 	<div class="uk-form-icon uk-width-1-10"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" name="labModel.lab_id" placeholder="รหัส บริษัท" class="uk-width-1-1"
		                             			pattern="(?=.*\d).{4,}" title="กรุณาใส่รหัสให้ครบ 4 หลัก" maxlength="4" >
		                             		
	                            	</div>
	                            -->
	                             	<div class="uk-form-icon uk-width-2-10">
										 <i class="uk-icon-asterisk"></i>
		                             		<input id="tid" type="text" name="teatmentModel.treatment_group_code"
		                             		 pattern="[A-Za-z0-9]{6}" title="ใส่ได้เฉพาะตัวอักษรภาษาอังกฤษและตัวเลขรวม6หลัก"  maxlength="6"
		                             		  placeholder="รหัสหมวดการรักษา" class="uk-width-1-1" required> 
	                            	</div>
	                            	 <div  class="uk-form-icon uk-width-2-10">
	                            	 	<select id="treatment_type" name="teatmentModel.labmode_id" required class="uk-width-1-1">
										   <option value="" >กรุณาเลือกกลุ่มการรักษา</option>
										   <%
										   
										    if(request.getAttribute("labmodelist")!=null){ 
										    	List labmodelist = (List) request.getAttribute("labmodelist");
										     
								        		for (Iterator iterA = labmodelist.iterator(); iterA.hasNext();) {
								        			LabModeModel labmode = (LabModeModel) iterA.next();
						      				%>  
							      			<option value="<%=labmode.getLabmode_id()%>" >
							       			 	<%=labmode.getLabmode_id()%> - <%=labmode.getLabmode_name()%>
							       			</option>
											<%		} 
												}
											%>
								   		</select>
	                            	 </div>
	                            	<div class="uk-form-icon uk-width-3-10"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" id="tgn" name="teatmentModel.treatment_group_name" pattern="[ก-๙A-Za-z]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทยและภาษาอังกฤษเท่านั้น" placeholder="ชื่อหมวดการรักษา" class="uk-width-1-1" required> 
	                            	</div> 
	                            	<div class="uk-form-icon uk-width-3-10">
	                            		<button class="uk-button uk-button-success uk-button-small" id="btnsave" type="submit" name="save">เพิ่มหมวดการรักษา</button>
	                            	</div>
							 	</div>
							</div>
						</div>
					</div>
					<% if(request.getAttribute("status_error") != null) {%>
					 <h3 class="red "><%=request.getAttribute("status_error").toString()%></h3>
					<% } %>
					<% if(request.getAttribute("status_success") != null) {%>
					 <h3 class="uk-text-success"><%=request.getAttribute("status_success").toString()%></h3>
					<% } %>
					<div class="uk-grid ">
						<div class="uk-width-1-1 ">
							<div class="uk-panel uk-panel-box">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">list</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> รายการ หมวดการรักษา</h3>
								</div>
									<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									        	<th class="uk-text-center">กลุ่มการรักษา</th>	
									            <th class="uk-text-center">รหัส หมวดการรักษา</th>
									            <th>ชื่อ  หมวดการรักษา</th> 
									            <th> </th> 
									        </tr>
									    </thead> 
									    <tbody>
									    	 <%   
											    if(request.getAttribute("treatmentGList")!=null)	{
												    List treatmentGList = (List) request.getAttribute("treatmentGList");
				                                	List <TreatmentMasterModel> treatmentGModel = treatmentGList;
				                                	int x=0;
					            	         	 	for(TreatmentMasterModel TGMode : treatmentGModel){ 
					            	         	 	x++; 
				            	         	 %>
										        <tr>
										            <td class="uk-text-center"><%=x%></td>
										            <td class="uk-text-center"><%=TGMode.getLabmode_id() %></td>
										            <td class="uk-text-center"><%=TGMode.getTreatment_group_code()%></td>
										            <td class="uk-text-left"><%=TGMode.getTreatment_group_name()%></td> 
										            <td class="uk-text-right">
										            	<a href="#update" onclick="update('<%=TGMode.getTreatment_group_code()%>','<%=TGMode.getTreatment_group_name()%>')" class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
										            	<a href="#delete_group" 
										            	onclick="delete_group('<%=TGMode.getTreatment_group_code()%>','<%=TGMode.getTreatment_group_name()%>','<%=TGMode.getLabmode_id()%>')" 
										            	class="uk-button uk-button-danger uk-button-small" data-uk-modal>
															<i class="uk-icon-eraser"></i> ลบ
														</a> 
										        </tr> 
									        <% } %> 
										        
									        <% }else{ %>
									        	 <tr>
										            <td class="uk-text-center" colspan="4">ไม่พบข้อมูล</td> 
										        </tr> 
									        <% } %> 
									    </tbody>
									</table>
									</div>
							</div>
						</div> 
					</div>
					 
					<div id="update" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-pencil"></i> แก้ไข</div>
					         <div class="uk-grid uk-grid-small"> 
					         	<div class="uk-width-2-10">
					         		<input class="uk-width-1-1 uk-text-center" type="text" id="id_up" name="id_up" autofocus="autofocus" > 
					         		<input type="hidden" id="hdid_up" name="hdid_up" >
					         	</div>
					         	<div class="uk-width-3-10">
					         		<select id="treatment_typeup"  name="type_up" required  class="uk-width-1-1">
					         		
										   <option value="">กรุณาเลือกกลุ่มการรักษา</option>
										   <%
										   
										    if(request.getAttribute("labmodelist")!=null){ 
										    	List labmodelist = (List) request.getAttribute("labmodelist");
										     
								        		for (Iterator iterA = labmodelist.iterator(); iterA.hasNext();) {
								        			LabModeModel labmode = (LabModeModel) iterA.next();
						      				%>  
							      			<option value="<%=labmode.getLabmode_id()%>" >
							       			 	<%=labmode.getLabmode_id()%> - <%=labmode.getLabmode_name()%>
							       			</option>
											<%		} 
												}
											%>
								   	</select>
					         	</div>
					         	<div class="uk-width-5-10 uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         		<input class="uk-width-1-1"  type="text" id="name_up" name="name_up" > 
					         	</div>
					         </div>	  
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-button uk-button-success" id="updateb" name="updateb">ตกลง</button>
					         	<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button> 
					         </div>
					    </div>
					</div>
					
					<div id="delete_group" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-eraser"></i> ลบ</div>
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="id_de" name="id_de" readonly=""> 
					         	</div>
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="name_de" name="name_de" readonly=""> 
					         	</div>
					         	 
					         <div class="uk-modal-footer uk-text-right"> 
					         	<button class="uk-button uk-button-success" id="deleteb" name="deleteb">ตกลง</button>
					         	<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
					         </div>
					    </div>
					</div>
				</div>	
					</form> 
			</div>
					
					
		</div>

		<script>
			$(document).ready(function(){
				$( ".m-setting" ).addClass( "uk-active" );
				 
				
				$("#deleteb").click(function(){
					$("#treatment_type").removeAttr("required");
					$("#tgn").removeAttr("required");
					$("#tid").removeAttr("required");
					$("#treatment_typeup").removeAttr("required");
					$("#service").submit();
				}); 
				$("#updateb").click(function(){
					$("#tgn").removeAttr("required");
					$("#tid").removeAttr("required");
					$("#treatment_type").removeAttr("required");
					$("#service").submit();
				}); 
				$("#btnsave").click(function(){
					$("#treatment_typeup").removeAttr("required");
				}); 
			});
			
			function update(id, name,type) { 
				 $("#hdid_up").val(id);
				 $("#id_up").val(id);
				 $("#name_up").val(name); 
				 $("#type_up").val(type);
			};
			function delete_group(id, name) { 
				 $("#id_de").val(id);
				 $("#name_de").val(name);  
			};
			
			
			
		</script>
	</body>
</html>