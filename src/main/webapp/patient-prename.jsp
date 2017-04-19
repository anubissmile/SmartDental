<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:PreName</title>
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
					<form id="prenameFrm" action="SettingPersonPreNameExcute" method="post">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">master</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> Prename</h3>
								</div>
								
								<div class="uk-grid uk-form "> 
									<div class="uk-form-icon uk-width-1-5"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" id="pre_name_id" name="prenameModel.pre_name_id" placeholder="รหัส" class="uk-width-1-1"
		                             			pattern="[0-9]{1,}" title="ใส่ได้เฉพาะตัวเลขเท่านั้น"  maxlength="4" required>
	                            	</div>
	                            	<div class="uk-form-icon uk-width-1-5"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" id="pre_name_th" pattern="[ก-๙]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาไทยเท่านั้น"  name="prenameModel.pre_name_th" placeholder="คำนำหน้าชื่อ (ไทย)" class="uk-width-1-1" required> 
	                            	</div>
	                            	<div class="uk-form-icon uk-width-1-5"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" id="pre_name_en" pattern="[A-Z a-z]{1,}" title="ใส่ได้เฉพาะตัวอักษรภาษาอังกฤษเท่านั้น"  name="prenameModel.pre_name_en" placeholder="คำนำหน้าชื่อ (Eng)" class="uk-width-1-1" required> 
	                            	</div>
	                            	<div class="uk-form-icon uk-width-1-5">
	                            		<button class="uk-button uk-button-success uk-button-small" type="submit" name="savePre">เพิ่ม</button>
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
									            <th>รหัส</th>
									            <th>คำนำหน้า ไทย</th>
									            <th>คำนำหน้า Eng</th>
									            <th></th> 
									        </tr>
									    </thead> 
									    <tbody>
									    	 <%   
											    if(request.getAttribute("PreNameList")!=null)	{
												    List preNameList = (List) request.getAttribute("PreNameList");
				                                	List <Pre_nameModel> prenameModel = preNameList;
				                                	int x=0;
					            	         	 	for(Pre_nameModel pnmd : prenameModel){ 
				            	         	 %>
										        <tr>
										            
										            <td class="uk-text-left"><%=pnmd.getPre_name_id()%></td>
										            <td class="uk-text-left"><%=pnmd.getPre_name_th()%></td>
										            <td class="uk-text-left"><%=pnmd.getPre_name_en()%></td>
										            <td class="uk-text-right">
										            	<a href="#update" 
										            	onclick="update('<%=pnmd.getPre_name_id()%>','<%=pnmd.getPre_name_th()%>','<%=pnmd.getPre_name_en()%>')" 
										            	class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
										            	<a href="#delete_prename" 
										            	onclick="delete_prename('<%=pnmd.getPre_name_id()%>','<%=pnmd.getPre_name_th()%>','<%=pnmd.getPre_name_en()%>')" 
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
					         		<input class="uk-width-1-1" type="text" id="id_up" name="PreNameID" readonly=""> 
					         	</div>
					         	<div class="uk-form-icon uk-width-1-3">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         		<input class="uk-width-1-1" type="text" id="name_up" name="PreNameTH" autofocus="autofocus"> 
					         	</div>
					         	<div class="uk-form-icon uk-width-1-3">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         		<input class="uk-width-1-1" type="text" id="name_en_up" name="PreNameEN" autofocus="autofocus"> 
					         	</div>
					         </div>
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-button uk-button-success" id="updatPre" name="updatPre">ตกลง</button>
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
					         	<input class="uk-width-1-1" type="text" id="id_de" name="PreNameID_de" readonly=""> 
					         	</div>
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="name_de" name="PreName_de" readonly=""> 
					         	</div>
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="name_en_de" name="PreName_en_de" readonly=""> 
					         	</div>
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-button uk-button-success" id="deletPre" name="deletPre">ตกลง</button>
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
				 
				
				$("#deletPre").click(function(){
					$("#pre_name_id").removeAttr("required");
					$("#pre_name_th").removeAttr("required");
					$("#pre_name_en").removeAttr("required");
					
					$("#prenameFrm").submit();
				}); 
				$("#updatPre").click(function(){
					 
					var id_up 	= $("#id_up").val(); 
					$("#pre_name_id").removeAttr("required");
					$("#pre_name_th").removeAttr("required");
					$("#pre_name_en").removeAttr("required");
					
					if(id_up!=''){ 
					$("#prenameFrm").submit();
					}
				}); 
				
			});
			
			function update(id, name_th,name_en) { 
				 $("#id_up").val(id);
				 $("#name_up").val(name_th); 
				 $("#name_en_up").val(name_en); 
			};
			function delete_prename(id,name_th,name_en) { 
				 $("#id_de").val(id);
				 $("#name_de").val(name_th); 
				 $("#name_en_de").val(name_en); 
				  
			};
			
			
			
		</script>
	</body>
</html>