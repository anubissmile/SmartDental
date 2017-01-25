<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ page import="com.smict.product.data.*" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : Service Lab</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				
				<%@include file="backend-lab-top.jsp" %>

					<div class="uk-grid"></div>
					<form id="service" action="labserviceMaster" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Lab</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i>บริการของบริษัท</h3>
								</div>
								<div class="uk-grid uk-grid-small uk-form ">   
	                            	<div class="uk-width-1-1"> 
										<select id="lab_id" name="labserviceModel.lab_id" required="required">
										   <option value="" >กรุณาเลือกบริษัท</option>
										   <%
										   
										    if(request.getAttribute("lablist")!=null){ 
										    	List lablist = (List) request.getAttribute("lablist");
										     
								        		for (Iterator iterA = lablist.iterator(); iterA.hasNext();) {
								        			LabModel lab = (LabModel) iterA.next();
						      				%>  
							      			<option value="<%=lab.getLab_id()%>" >
							       			 	<%=lab.getLab_id()%> - <%=lab.getLab_name()%>
							       			</option>
											<%		} 
												}
											%>
								   		</select>
								   		<select id="service_id" name="labserviceModel.service_id" required="required">
										   <option value="" >กรุณาเลือกบริการ</option>
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
								   		<input type="text" name="labserviceModel.est_fee" placeholder="จำนวนเงิน" size="6" maxlength="10" class="uk-text-right">
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
									<div class="uk-width-9-10 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th>
									            <th>ชื่อ บริษัท</th>
									            <th>ชื่อ บริการ</th> 
									            <th class="uk-text-center">จำนวนเงิน</th> 
									            <th> </th> 
									        </tr>
									    </thead> 
									    <tbody>
									    	 <%   
											    if(request.getAttribute("labservicelist")!=null)	{
												    List labservicelist = (List) request.getAttribute("labservicelist");
				                                	List <LabServiceModel> labserviceModel = labservicelist;
				                                	int x=0;
					            	         	 	for(LabServiceModel labservice : labserviceModel){ 
					            	         	 	x++; 
				            	         	 %>
										        <tr>
										            <td class="uk-text-center"><%=x%></td>
										            <td class="uk-text-left"><%=labservice.getLab_name()%></td>
										            <td class="uk-text-left"><%=labservice.getService_name()%></td> 
										            <td class="uk-text-right"><%=labservice.getEst_fee()%></td> 
										            <td class="uk-text-right">
										            	<a href="#update" onclick="update('<%=labservice.getLab_id()%>','<%=labservice.getLab_name()%>',
										            	'<%=labservice.getService_id()%>','<%=labservice.getService_name()%>','<%=labservice.getEst_fee()%>')" class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
										            	<a href="#delete_group" onclick="delete_group('<%=labservice.getLab_id()%>','<%=labservice.getLab_name()%>',
										            	'<%=labservice.getService_id()%>','<%=labservice.getService_name()%>')" class="uk-button uk-button-danger uk-button-small" data-uk-modal>
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
					         	<div class="uk-width-1-1">
					         	ข้อมูลเดิม
					         	</div>
					         </div> 
					         <div class="uk-grid uk-grid-small"> 
					         	<div class="uk-width-1-2">
					         		<input type="text" id="lab_name_up" name="lab_name_up" readonly="readonly" class="uk-width-1-1"> 
					         		<input type="hidden" id="lab_id_up_hd" name="lab_id_up_hd" readonly="readonly">
					         	</div>
					         	<div class="uk-width-1-2">
					         		<input type="text" id="service_name_up" name="service_name_up" readonly="readonly" class="uk-width-1-1">
					         		<input type="hidden" id="service_id_up_hd" name="service_id_up_hd" readonly="readonly">
					         	</div>
					         </div>
					         <hr>
					         <div class="uk-grid uk-grid-small"> 
					         	<div class="uk-width-1-1">
					         	แก้ไข
					         	</div>
					         </div>
					         <div class="uk-grid uk-grid-small"> 
					         	<div class="uk-width-1-1">
					         		 
					         		<select id="lab_id_up" name="lab_id_up" >
									   <option value="" >กรุณาเลือกบริษัท</option>
									   <%
									   
									    if(request.getAttribute("lablist")!=null){ 
									    	List lablist = (List) request.getAttribute("lablist");
									     
							        		for (Iterator iterA = lablist.iterator(); iterA.hasNext();) {
							        			LabModel lab = (LabModel) iterA.next();
					      				%>  
						      			<option value="<%=lab.getLab_id()%>" >
						       			 	<%=lab.getLab_id()%> - <%=lab.getLab_name()%>
						       			</option>
										<%		} 
											}
										%>
							   		</select>
							   		
							   		<select id="service_id_up" name="service_id_up" >
									   <option value="" >กรุณาเลือกบริการ</option>
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
							   		<input type="text" id="est_fee_up" name="est_fee_up" size="6" maxlength="10" class="uk-text-right">
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
					         	<div class="uk-width-1-2">
					         		<input type="text" id="lab_name_de" name="lab_name_de" readonly="readonly" class="uk-width-1-1"> 
					         		<input type="hidden" id="lab_id_de_hd" name="lab_id_de_hd" readonly="readonly">
					         	</div>
					         	<div class="uk-width-1-2">
					         		<input type="text" id="service_name_de" name="service_name_de" readonly="readonly" class="uk-width-1-1">
					         		<input type="hidden" id="service_id_de_hd" name="service_id_de_hd" readonly="readonly">
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
					
					$("#lab_id").removeAttr("required");
					$("#service_id").removeAttr("required");
					
					$("#service").submit();
				}); 
				$("#updateg").click(function(){
					var lab_id_up 		= $("#lab_id_up").val();
					var service_id_up 	= $("#service_id_up").val();
					 
					$("#lab_id").removeAttr("required");
					$("#service_id").removeAttr("required");
					
					if(lab_id_up!=''&&service_id_up!=''){  
						$("#service").submit();
					}
					
				}); 
				
			});
			
			function update(lab_id, lab_name, service_id, service_name, est_fee) { 
				 $("#lab_id_up_hd").val(lab_id);
				 $("#lab_name_up").val(lab_name);  
				 $("#service_id_up_hd").val(service_id);
				 $("#service_name_up").val(service_name);  
				 $("#est_fee_up").val(est_fee); 
			};
			function delete_group(lab_id, lab_name, service_id, service_name) { 
				 $("#lab_id_de_hd").val(lab_id);
				 $("#lab_name_de").val(lab_name);  
				 $("#service_id_de_hd").val(service_id);
				 $("#service_name_de").val(service_name);
			};
			
			
			
		</script>
	</body>
</html>