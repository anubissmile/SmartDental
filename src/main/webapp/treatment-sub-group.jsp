<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.all.model.*" %>
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
	                             	<div class="uk-form-icon uk-width-2-10">
										 <i class="uk-icon-asterisk"></i>
		                             		<input id="tid" type="text" name="teatmentModel.treatCategory_code"
		                             		   
		                             		  placeholder="รหัสหมวดการรักษา" class="uk-width-1-1" required> 
	                            	</div>
	                            	 <div  class="uk-form-icon uk-width-2-10">
<%-- 	                            	 	<select id="treatment_type" name="teatmentModel.treatCategory_groupid" required class="uk-width-1-1">
	                            	 		<option value="">กรุณาเลือกกลุ่มการรักษา</option>
											<s:iterator value="treatGlist">
											<option value="<s:property value="treatG_id" />"><s:property value="treatG_code" /> - <s:property value="treatG_name" /></option>
											</s:iterator>
											
								   		</select> --%>
								   		<s:select list="treatGroupMap" name="teatmentModel.treatCategory_groupid" class="uk-width-1-1" required="true" headerKey="" headerValue = "กรุณาเลือก" />


								   		
	                            	 </div>
	                            	<div class="uk-form-icon uk-width-3-10"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" id="tgn" name="teatmentModel.treatCategory_name" pattern="[ก-๙A-Za-z]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทยและภาษาอังกฤษเท่านั้น" placeholder="ชื่อหมวดการรักษา" class="uk-width-1-1" required> 
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
									            <th class="uk-text-center">ชื่อ  หมวดการรักษา</th> 
									            <th> </th> 
									        </tr>
									    </thead> 
									    <tbody>
											<s:iterator value="categoryList">
												<tr>
													<td class="uk-text-center"><s:property value="treatCategory_id" /></td>
													<td class="uk-text-center"><s:property value="treatCategory_groupid" /></td>
													<td class="uk-text-center"><s:property value="treatCategory_code" /></td>
													<td class="uk-text-center"><s:property value="treatCategory_name" /></td>
													<td class="uk-text-center">
														<a href="#update"  onclick="update('<s:property value="treatCategory_id"/>','<s:property value="treatCategory_code"/>','<s:property value="treatCategory_groupid"/>','<s:property value="treatCategory_name" />')"   class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
														<a href="#delete_group" onclick="delete_group('<s:property value="treatCategory_id" />','<s:property value="treatCategory_code" />','<s:property value="treatCategory_name"/>')" class="uk-button uk-button-danger uk-button-small"  data-uk-modal ><i class="uk-icon-eraser"></i> ลบ</a>
														
													</td>
												</tr>
											</s:iterator>
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
<%-- 					         		<select id="treatment_typeup"  name="type_up" required  class="uk-width-1-1">
					         		
										   		<option value="">กรุณาเลือกกลุ่มการรักษา</option>
											<s:iterator value="treatGlist">
												<option value="<s:property value="treatG_id" />"><s:property value="treatG_code" /> - <s:property value="treatG_name" /></option>
											</s:iterator>
											
								   	</select> --%>
									<s:select list="treatGroupMap" name="teatmentModel.treatCategory_groupid" class="uk-width-1-1" required="true" headerKey="" headerValue = "กรุณาเลือก" />	   	
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
			
			function update(id, code, groupid, name) { 
				 $("#hdid_up").val(id);
				 $("#id_up").val(code);
				 $("#name_up").val(name); 
				 $("#type_up").val(groupid);
			};
			function delete_group(id, name) { 
				 $("#id_de").val(id);
				 $("#name_de").val(name);  
			};
			
			
			
		</script>
	</body>
</html>