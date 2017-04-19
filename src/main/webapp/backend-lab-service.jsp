<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : Service</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				
				<%@include file="backend-lab-top.jsp" %>

					<div class="uk-grid"></div>
					<form id="service" action="serviceMaster" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Lab</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i>  บริการของบริษัท</h3>
								</div>
								<div class="uk-grid uk-grid-small uk-form "> 
								<!-- 	<div class="uk-form-icon uk-width-1-10"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" name="labModel.lab_id" placeholder="รหัส บริษัท" class="uk-width-1-1"
		                             			pattern="(?=.*\d).{4,}" title="กรุณาใส่รหัสให้ครบ 4 หลัก" maxlength="4" >
		                             		
	                            	</div>
	                             -->
	                             	<div class="uk-form-icon uk-width-2-10">
	                             		<select id="labmode_id" name="serviceModel.labmode_id" class="uk-width-1-1">
										   <option value="" >กรุณาเลือกโหมด</option>
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
		                             		<input type="text" name="serviceModel.service_name" placeholder="ชื่อ บริการ" class="uk-width-1-1"> 
	                            	</div> 
	                            	<div class="uk-form-icon uk-width-5-10">
	                            		<button class="uk-button uk-button-success uk-button-small" type="submit" name="save">บันทึก</button>
	                            	</div>
							 	</div>
							</div>
						</div>
					</div>
					<div class="uk-grid ">
						<div class="uk-width-1-1 ">
							<div class="uk-panel uk-panel-box">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">list</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> Detail</h3>
								</div>
									<div class="uk-width-8-10 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									        	<th class="uk-text-center">ประเภท</th>
									            <th class="uk-text-center">รหัส บริการ</th>
									            <th>ชื่อ บริการ</th> 
									            <th> </th> 
									        </tr>
									    </thead> 
									    <tbody>
									    	 <%   
											    if(request.getAttribute("servicelist")!=null)	{
												    List servicelist = (List) request.getAttribute("servicelist");
				                                	List <ServiceModel> ServiceModel = servicelist;
				                                	int x=0;
					            	         	 	for(ServiceModel service : ServiceModel){ 
					            	         	 	x++; 
				            	         	 %>
										        <tr>
										            <td class="uk-text-center"><%=x%></td>
										            <td class="uk-text-center"><%=service.getLabmode_id()%></td>
										            <td class="uk-text-center"><%=service.getService_id()%></td>
										            <td class="uk-text-left"><%=service.getService_name()%></td> 
										            <td class="uk-text-right">
										            	<a href="#update" onclick="update('<%=service.getLabmode_id()%>','<%=service.getService_id()%>','<%=service.getService_name()%>')" class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
										            	<a href="#delete_group" onclick="delete_group('<%=service.getLabmode_id()%>','<%=service.getService_id()%>','<%=service.getService_name()%>')" class="uk-button uk-button-danger uk-button-small" data-uk-modal>
															<i class="uk-icon-eraser"></i> ลบ
														</a> 
										        </tr> 
									        <% } %> 
										        
									        <% }else{ %>
									        	 <tr>
										            <td class="uk-text-center" colspan="3">ไม่พบข้อมูล</td> 
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
					         		<input class="uk-width-1-1 uk-text-center" type="text" id="labid_up" name="labid_up" readonly=""> 
					         	</div>
					         	<div class="uk-width-2-10">
					         		<input class="uk-width-1-1 uk-text-center" type="text" id="id_up" name="id_up" readonly=""> 
					         	</div>
					         	<div class="uk-width-6-10 uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="name_up" name="name_up" autofocus="autofocus"> 
					         	</div>
					         </div>	  
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-button uk-button-success" id="updateg" name="updateg">ตกลง</button>
					         	<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button> 
					         </div>
					    </div>
					</div>
					
					<div id="delete_group" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-eraser"></i> ลบ</div>
					         <div class="uk-grid uk-grid-small">
					         	<div class="uk-width-2-10">
						         	<div class="uk-form-icon">
			    						<i class="uk-icon-asterisk">
			    						</i>
						         	<input class="uk-width-1-1" type="text" id="labid_de" name="labid_de" readonly="" > 
						         	</div>
					         	</div>
					         	<div class="uk-width-2-10">
						         	<div class="uk-form-icon">
			    						<i class="uk-icon-asterisk">
			    						</i>
						         	<input class="uk-width-1-1" type="text" id="id_de" name="id_de" readonly=""> 
						         	</div>
					         	</div>
					         	<div class="uk-width-6-10">
						         	<div class="uk-form-icon">
			    						<i class="uk-icon-asterisk">
			    						</i>
						         	<input class="uk-width-1-1" type="text" id="name_de" name="name_de" readonly=""> 
						         	</div>
					         	</div>
					         </div>	 
					         <div class="uk-modal-footer uk-text-right"> 
					         	<button class="uk-button uk-button-success" id="deleteg" name="deleteg">ตกลง</button>
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
				 
				
				$("#deleteg").click(function(){
					$("#service").submit();
				}); 
				$("#updateg").click(function(){
					$("#service").submit();
				}); 
				
			});
			
			function update(labid, id, name) { 
				 $("#labid_up").val(labid);
				 $("#id_up").val(id);
				 $("#name_up").val(name);  
			};
			function delete_group(labid, id, name) { 
				$("#labid_de").val(labid);
				 $("#id_de").val(id);
				 $("#name_de").val(name);  
			};
			
			
			
		</script>
	</body>
</html>