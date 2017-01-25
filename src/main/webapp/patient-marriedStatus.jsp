<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:Married Status</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
					<div class="uk-grid"></div>
					<form id="formType" action=SettingPersonMarriedExcute method="post">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">master</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> Married Status</h3>
								</div>
								
								<div class="uk-grid uk-form "> 
									<div class="uk-form-icon uk-width-1-5"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" id="txt_id" name="person.married_statusid" placeholder="รหัสสถานะสมรส" class="uk-width-1-1"
		                             			pattern="[0-9]{1,4}" title="ใส่ได้เฉพาะตัวเลขเท่านั้น" maxlength="4" required>
	                            	</div>
	                            	<div class="uk-form-icon uk-width-1-5"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" id="txt_name" pattern="[ก-๙A-Za-z]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทยและภาษาอังกฤษเท่านั้น" name="person.married_statusname" placeholder="สถานะสมรส" class="uk-width-1-1" required> 
	                            	</div>
	                            	<div class="uk-form-icon uk-width-1-5">
	                            		<button class="uk-button uk-button-success uk-button-small" type="submit" name="saveType">เพิ่ม</button>
	                            	</div>
							 	</div>
							</div>
						</div>
					</div>
					<% if(request.getAttribute("del_error") != null) {%>
					 <h3 class="red "><%=request.getAttribute("del_error").toString()%></h3>
					<% } %>
					<% if(request.getAttribute("del_success") != null) {%>
					 <h3 class="uk-text-success"><%=request.getAttribute("del_success").toString()%></h3>
					<% } %>
					<div class="uk-grid ">
						<div class="uk-width-1-1 ">
							<div class="uk-panel uk-panel-box">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">list</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> Detail</h3>
								</div>
									<div class="uk-width-7-10 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table">
									            <th>รหัสสถานะสมรส</th>
									            <th>สถานะสมรส</th>
									            <th></th> 
									        </tr>
									    </thead> 
									    <tbody>
									    	 <%   
											    if(request.getAttribute("MarriedList")!=null)	{
												    List MarriedList = (List) request.getAttribute("MarriedList");
				                                	List <Person> person = MarriedList;
				                                	int x=0;
					            	         	 	for(Person pnmd : person){ 
				            	         	 %>
										        <tr>
										            
										            <td class="uk-text-left"><%=pnmd.getMarried_statusid()%></td>
										            <td class="uk-text-left"><%=pnmd.getMarried_statusname()%></td>
										            <td class="uk-text-right">
										            	<a href="#update" 
										            	onclick="update('<%=pnmd.getMarried_statusid()%>','<%=pnmd.getMarried_statusname()%>')" 
										            	class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
										            	<a href="#delete_prename" 
										            	onclick="delete_prename('<%=pnmd.getMarried_statusid()%>','<%=pnmd.getMarried_statusname()%>')" 
										            	class="uk-button uk-button-danger uk-button-small" data-uk-modal>
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
					         <div class="uk-grid">
					         	<div class="uk-form-icon uk-width-1-3">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         		<input class="uk-width-1-1" type="text" id="id_up" name="id_up" readonly=""> 
					         	</div>
					         	<div class="uk-form-icon uk-width-1-3">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         		<input class="uk-width-1-1" type="text" id="name_up" name="name_up" autofocus="autofocus"> 
					         	</div>
					         </div>
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-button uk-button-success" id="upType" name="upType">ตกลง</button>
					         	<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
					         </div>
					    </div>
					</div>
					
					<div id="delete_prename" class="uk-modal ">
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
					         	<button class="uk-button uk-button-success" id="delType" name="delType">ตกลง</button>
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
				 
				
				$("#delType").click(function(){
					$("#txt_id").removeAttr("required");
					$("#txt_name").removeAttr("required");
					
					$("#formType").submit();
				}); 
				$("#upType").click(function(){
					 
					var id_up 	= $("#id_up").val(); 
					$("#txt_id").removeAttr("required");
					$("#txt_name").removeAttr("required");
					
					if(id_up!=''){ 
					$("#formType").submit();
					}
				}); 
				
			});
			
			function update(id, name_th) { 
				 $("#id_up").val(id);
				 $("#name_up").val(name_th);
			};
			function delete_prename(id,name_th) { 
				 $("#id_de").val(id);
				 $("#name_de").val(name_th);
				  
			};
			
			
			
		</script>
	</body>
</html>