<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.product.model.*" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:Treatment Master</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
					<div class="uk-grid"></div>
					<form id="brand" action="treatmentMaster" method="post">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Master</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> Treatment</h3>
								</div>
								<div class="uk-grid uk-grid-small uk-form "> 
									<div class="uk-width-1-10">
										รหัสการรักษา 
			                             		<input type="text" id="brandid" name="brandModel.brand_id" class="uk-width-1-1"
			                             			pattern="(?=.*\d).{4,}" title="กรุณาใส่รหัสให้ครบ 4 หลัก" maxlength="4" required> 
	                            	</div>
	                            	<div class="uk-width-4-10">
	                            		ชื่อการรักษา (ไทย) 
			                             		<input type="text" id="brandname" name="brandModel.brand_name" class="uk-width-1-1" required>  
		                            </div>
		                            <div class="uk-width-4-10">
		                            	ชื่อการรักษา (อังกฤษ) 
			                             		<input type="text" id="brandname" name="brandModel.brand_name" class="uk-width-1-1" required> 
		                            </div> 
							 	</div>
							 	<div class="uk-grid uk-grid-small uk-form "> 
									<div class="uk-width-2-10">
										สาขา 
			                             <select id="branch_id" name="treatmentMasterModel.branch_id" class="uk-width-1-1" required="required">
										   <option value="" >กรุณาเลือกสาขา</option>
										   <%
										   
										    if(request.getAttribute("branchlist")!=null){ 
										    	List branchlist = (List) request.getAttribute("branchlist");
										     
								        		for (Iterator iterA = branchlist.iterator(); iterA.hasNext();) {
								        			BranchModel branchModel = (BranchModel) iterA.next();
						      				%>  
							      			<option value="<%=branchModel.getBranch_id()%>" >
							       			 	<%=branchModel.getBranch_id()%> - <%=branchModel.getBranch_name()%>
							       			</option>
											<%		} 
												}
											%>
								   		</select> 
	                            	</div>
	                            	<div class="uk-width-1-10">
	                            		ค่าส่วนแบ่ง แพทย์
	                            		<div class="uk-form-icon uk-width-1-1"> 
										 <i class="uk-icon-money"></i> 
			                             		<input type="text" id="doctor_revenue_sharing" name="treatmentMasterModel.doctor_revenue_sharing" 
			                             		pattern="(0-9){1,9}" title="กรุณาใส่ ตัวเลข เท่านั้น" class="uk-width-1-1 uk-text-right" required>  
		                            	</div>
		                            </div>
		                            <div class="uk-width-1-10">
		                            	ส่วนแบ่ง ค่า Lab
		                            	<div class="uk-form-icon uk-width-1-1"> 
										 <i class="uk-icon-percent"></i>
			                             		<input type="text" id="lab_percent" name="treatmentMasterModel.lab_percent"
			                             		pattern="(0-9){1,2}" title="กรุณาใส่ ตัวเลข เท่านั้น" class="uk-width-1-1 uk-text-right" required>  
		                            	</div>
		                            </div> 
		                            <div class="uk-width-1-10">
		                            	Home Call
		                            	<select id="autohomecall" name="treatmentMasterModel.autohomecall" class="uk-width-1-1" >
								            <option value="">โปรดเลือก</option>
								            <option value="1"></option>
								            <option value="2">...</option>
								        </select>
		                            </div>
		                            <div class="uk-width-1-10">
		                            	Re Call
		                            	<select id="recall_typeid" name="treatmentMasterModel.recall_typeid" class="uk-width-1-1" >
								            <option>...</option>
								            <option>...</option>
								        </select>
		                            </div> 
							 	</div>
							 	<div class="uk-grid uk-grid-small uk-form "> 
							 		<div class="uk-width-2-10">
		                            	โหมดการรักษา
		                            	<select id="treatment_type" name="treatmentMasterModel.treatment_type" class="uk-width-1-1">
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
		                            <div class="uk-width-1-10">
	                            		ราคาปกติ
	                            		<div class="uk-form-icon uk-width-1-1"> 
										 <i class="uk-icon-money"></i> 
			                             		<input type="text" id="price_standard" name="treatmentMasterModel.price_standard" 
			                             		pattern="(0-9){1,9}" title="กรุณาใส่ ตัวเลข เท่านั้น" class="uk-width-1-1 uk-text-right" required>  
		                            	</div>
		                            </div>
		                            <div class="uk-width-1-10">
		                            	ราคาพิเศษ
		                            	<div class="uk-form-icon uk-width-1-1"> 
										 <i class="uk-icon-percent"></i>
			                             		<input type="text" id="price_benefit" name="treatmentMasterModel.price_benefit"
			                             		pattern="(0-9){1,2}" title="กรุณาใส่ ตัวเลข เท่านั้น" class="uk-width-1-1 uk-text-right" required>  
		                            	</div>
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
									        	<th>ลำดับ</th>
									            <th>รหัส</th>
									            <th>ชื่อ</th>
									            <th> </th> 
									        </tr>
									    </thead> 
									    <tbody>
									    	 <%   
											    if(request.getAttribute("brandlist")!=null)	{
												    List brandlist = (List) request.getAttribute("brandlist");
				                                	List <BrandModel> brandModel = brandlist;
				                                	int x=0;
					            	         	 	for(BrandModel pbm : brandModel){ 
					            	         	 	x++; 
				            	         	 %>
										        <tr>
										            <td class="uk-text-center"><%=x%></td>
										            <td class="uk-text-left"><%=pbm.getBrand_id()%></td>
										            <td class="uk-text-left"><%=pbm.getBrand_name()%></td>
										            <td class="uk-text-right">
										            	<a href="#update" onclick="update('<%=pbm.getBrand_id()%>','<%=pbm.getBrand_name()%>')" class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
										            	<a href="#delete_brand" onclick="delete_brand('<%=pbm.getBrand_id()%>','<%=pbm.getBrand_name()%>')" class="uk-button uk-button-danger uk-button-small" data-uk-modal>
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
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="id_up" name="id_up" readonly=""> 
					         	</div>
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="name_up" name="name_up" autofocus="autofocus"> 
					         	</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-button uk-button-success" id="updateb" name="updateb">ตกลง</button>
					         	<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
					         </div>
					    </div>
					</div>
					
					<div id="delete_brand" class="uk-modal ">
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
					
					</form>
			</div>
					
					
		</div>

		<script>
			$(document).ready(function(){
				$( ".m-setting" ).addClass( "uk-active" );
				 
				
				$("#deleteb").click(function(){
					$("#brandid").removeAttr("required");
					$("#brandname").removeAttr("required");
					
					$("#brand").submit();
				}); 
				$("#updateb").click(function(){
					 
					var id_up 	= $("#id_up").val(); 
					$("#brandid").removeAttr("required");
					$("#brandname").removeAttr("required");
					
					if(id_up!=''){ 
					$("#brand").submit();
					}
				}); 
				
			});
			
			function update(id, name) { 
				 $("#id_up").val(id);
				 $("#name_up").val(name); 
			};
			function delete_brand(id, name) { 
				 $("#id_de").val(id);
				 $("#name_de").val(name); 
				  
			};
			
			
			
		</script>
	</body>
</html>