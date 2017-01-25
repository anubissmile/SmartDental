<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ page import="com.smict.person.model.*" %> 
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : Lab</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				
				<%@include file="backend-lab-top.jsp" %>

					<div class="uk-grid"></div>
					<form id="lab" action="labBranchMaster" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Lab</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i>  ข้อมูลสาขา</h3>
								</div>
								<div class="uk-grid uk-grid-small uk-form "> 
								<!-- 	<div class="uk-form-icon uk-width-1-10"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" name="labModel.lab_id" placeholder="รหัส บริษัท" class="uk-width-1-1"
		                             			pattern="(?=.*\d).{4,}" title="กรุณาใส่รหัสให้ครบ 4 หลัก" maxlength="4" >
		                             		
	                            	</div>
	                             -->
	                             	<div class="uk-form-icon uk-width-2-10">
	                             		<select id="branch_id" name="labBranchModel.branch_id" class="uk-width-1-1" required="required">
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
	                            	<div class="uk-form-icon uk-width-2-10">  
										 	<select id="lab_id" name="labBranchModel.lab_id" class="uk-width-1-1" required="required">
											   <option value="" >กรุณาเลือกบริษัท</option>
											   <%
											   
											    if(request.getAttribute("lablist")!=null){ 
											    	List lablist = (List) request.getAttribute("lablist");
											     
									        		for (Iterator iterA = lablist.iterator(); iterA.hasNext();) {
									        			LabModel labModel = (LabModel) iterA.next();
							      				%>  
								      			<option value="<%=labModel.getLab_id()%>" >
								       			 	<%=labModel.getLab_id()%> - <%=labModel.getLab_name()%>
								       			</option>
												<%		} 
													}
												%>
									   		</select>  
	                            	</div>
	                            	<div class="uk-form-icon uk-width-3-10"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" id="addr_id" name="labBranchModel.addr_id" placeholder="ที่อยู่ บริษัท" class="uk-width-1-1"required> 
	                            	</div>
	                            	<div class="uk-form-icon uk-width-3-10">
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
									        	<th class="uk-text-center">รหัส-ชื่อ สาขา</th>
									            <th class="uk-text-center">รหัส บริษัท</th>
									            <th>ชื่อ บริษัท</th>
									            <th>ที่อยู่ บริษัท</th>
									            <th> </th> 
									        </tr>
									    </thead> 
									    <tbody>
									    	 <%   
											    if(request.getAttribute("labBranchlist")!=null)	{
												    List labBranchlist = (List) request.getAttribute("labBranchlist");
				                                	List <LabBranchModel> LabBranchModel = labBranchlist;
				                                	int x=0;
					            	         	 	for(LabBranchModel labbranch : LabBranchModel){ 
					            	         	 	x++; 
				            	         	 %>
										        <tr>
										            <td class="uk-text-center"><%=x%></td>
										            <td class="uk-text-center"><%=labbranch.getBranch_id()%>-<%=labbranch.getBranch_name()%></td>
										            <td class="uk-text-center"><%=labbranch.getLab_id()%></td>
										            <td class="uk-text-left"><%=labbranch.getLab_name()%></td>
										            <td class="uk-text-left"><%=labbranch.getAddr_id()%></td>
										            <td class="uk-text-right">
										            	<a href="#update" onclick="update('<%=labbranch.getBranch_id()%>','<%=labbranch.getLab_id()%>','<%=labbranch.getLab_name()%>','<%=labbranch.getAddr_id()%>')" class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
										            	<a href="#delete_group" onclick="delete_group('<%=labbranch.getLab_id()%>','<%=labbranch.getLab_name()%>','<%=labbranch.getAddr_id()%>')" class="uk-button uk-button-danger uk-button-small" data-uk-modal>
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
					         	<select id="branch_idup" name="branch_idup" class="uk-width-1-1" > 
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
						   		<input type="hidden" id="branch_idhd" name="branch_idhd" >
						   		</div>
					         </div>
					         <div class="uk-grid uk-grid-small"> 
					         	<div class="uk-width-1-1">  
					         		<select id="id_up" name="id_up" class="uk-width-1-1" >
									   <option value="" >กรุณาเลือกบริษัท</option>
									   <%
									   
									    if(request.getAttribute("lablist")!=null){ 
									    	List lablist = (List) request.getAttribute("lablist");
									     
							        		for (Iterator iterA = lablist.iterator(); iterA.hasNext();) {
							        			LabModel labModel = (LabModel) iterA.next();
					      				%>  
						      			<option value="<%=labModel.getLab_id()%>" >
						       			 	<%=labModel.getLab_id()%> - <%=labModel.getLab_name()%>
						       			</option>
										<%		} 
											}
										%>
							   		</select>  
									<input type="hidden" id="lab_idhd" name="lab_idhd" >
					         	</div> 
					         </div>	 
					         <div class="uk-grid uk-grid-small"> 
					         	<div class="uk-width-1-1 uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="addr_up" name="addr_up"> 
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
					
					$("#branch_id").removeAttr("required");
					$("#lab_id").removeAttr("required");
					$("#addr_id").removeAttr("required");
					$("#lab").submit();
				}); 
				$("#updateg").click(function(){
					
					$("#branch_id").removeAttr("required");
					$("#lab_id").removeAttr("required");
					$("#addr_id").removeAttr("required");
					$("#lab").submit();
				}); 
				
			});
			
			function update(branchid, id, name, addr) { 
				 $("#branch_idup").val(branchid);
				 $("#id_up").val(id);
				 $("#branch_idhd").val(branchid); // edit use where
				 $("#lab_idhd").val(id);  // edit use where
				 $("#name_up").val(name); 
				 $("#addr_up").val(addr); 
			};
			function delete_group(id, name, addr) { 
				 $("#id_de").val(id);
				 $("#name_de").val(name); 
				 $("#addr_up").val(addr); 
			};
			
			
			
		</script>
	</body>
</html>