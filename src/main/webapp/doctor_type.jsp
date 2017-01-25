<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:ประเภทแพทย์</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
					<div class="uk-grid"></div>
					<form id="prenameFrm" action="DoctorTypeExcute" method="post">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">master</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> ประเภทแพทย์</h3>
								</div>
								
								<div class="uk-grid uk-form "> 
								
	                            	<div class="uk-form-icon uk-width-1-5"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" id="education_vocabulary_th" name="docModel.position_name_th" placeholder="ชื่อประเภทแพทย์(ไทย)" class="uk-width-1-1" required> 
	                            	</div>
	                            	<div class="uk-form-icon uk-width-1-5"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" id="education_vocabulary_en" name="docModel.position_name_en" placeholder="ชื่อประเภทแพทย์(Eng)" class="uk-width-1-1" required> 
	                            	</div>
	                            	<div class="uk-form-icon uk-width-1-5"> 
										 <i class="uk-icon-asterisk"></i>
		                             		<input type="text" id="education_vocabulary_short" name="docModel.position_name_short" placeholder="ชื่อย่อประเภทแพทย์" class="uk-width-1-1" required> 
	                            	</div>
	                            	<div class="uk-form-icon uk-width-1-5">
	                            		<button class="uk-button uk-button-success uk-button-small" type="submit" name="saveEdu">เพิ่ม</button>
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
									        	<th>ชื่อย่อ ตำแหน่งแพทย์ </th>
									            <th>ตำแหน่งแพทย์ ไทย</th>
									            <th>ตำแหน่งแพทย์  Eng</th>
									            <th></th> 
									        </tr>
									    </thead> 
									    <tbody>
									    	 <%   
											    if(request.getAttribute("doctorTypeList")!=null)	{
												    List docTypeList = (List) request.getAttribute("doctorTypeList");
				                                	List <DoctorModel> docModel = docTypeList;
				                                	int x=0;
					            	         	 	for(DoctorModel pnmd : docModel){ 
				            	         	 %>
										        <tr>
										            <td class="uk-text-left"><%=pnmd.getPosition_name_short()%></td>
										            <td class="uk-text-left"><%=pnmd.getPosition_name_th()%></td>
										            <td class="uk-text-left"><%=pnmd.getPosition_name_en()%></td>
										            <td class="uk-text-right">
										            	<a href="#update" 
										            	onclick="update('<%=pnmd.getPosition_id()%>','<%=pnmd.getPosition_name_th()%>','<%=pnmd.getPosition_name_en()%>','<%=pnmd.getPosition_name_short()%>')" 
										            	class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
										            	<a href="#delete_prename" 
										            	onclick="delete_prename('<%=pnmd.getPosition_id()%>','<%=pnmd.getPosition_name_th()%>','<%=pnmd.getPosition_name_en()%>','<%=pnmd.getPosition_name_short()%>')" 
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
					         		<input class="uk-width-1-1" type="text" id="id_up" name="EduIDUp" readonly=""> 
					         	</div>
					         	<div class="uk-form-icon uk-width-1-3">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         		<input class="uk-width-1-1" type="text" id="name_up" name="EduTHUp" autofocus="autofocus"> 
					         	</div>
					         	<div class="uk-form-icon uk-width-1-3">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         		<input class="uk-width-1-1" type="text" id="name_en_up" name="EduENUp" autofocus="autofocus"> 
					         	</div>
					         	<div class="uk-form-icon uk-width-1-3">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         		<input class="uk-width-1-1" type="text" id="name_short_up" name="EduShUp" autofocus="autofocus"> 
					         	</div>
					         </div>
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-button uk-button-success" id="updatPre" name="updatEdu">ตกลง</button>
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
					         	<input class="uk-width-1-1" type="text" id="id_de" name="EduIDdel" readonly=""> 
					         	</div>
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="name_de" name="EduTHdel" readonly=""> 
					         	</div>
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="name_en_de" name="EduENdel" readonly=""> 
					         	</div>
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	<input class="uk-width-1-1" type="text" id="name_short_del" name="EduShdel" readonly=""> 
					         	</div>
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-button uk-button-success" id="deletPre" name="deletEdu">ตกลง</button>
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
					$("#education_vocabulary_id").removeAttr("required");
					$("#education_vocabulary_th").removeAttr("required");
					$("#education_vocabulary_en").removeAttr("required");
					$("#education_vocabulary_short").removeAttr("required");
					$("#prenameFrm").submit();
				}); 
				$("#updatPre").click(function(){
					 
					var id_up 	= $("#id_up").val(); 
					$("#education_vocabulary_id").removeAttr("required");
					$("#education_vocabulary_th").removeAttr("required");
					$("#education_vocabulary_en").removeAttr("required");
					$("#education_vocabulary_short").removeAttr("required");
					if(id_up!=''){ 
					$("#prenameFrm").submit();
					}
				}); 
				
			});
			
			function update(id, name_th,name_en,name_short) { 
				 $("#id_up").val(id);
				 $("#name_up").val(name_th); 
				 $("#name_en_up").val(name_en); 
				 $("#name_short_up").val(name_short); 
			};
			function delete_prename(id,name_th,name_en,name_short) { 
				 $("#id_de").val(id);
				 $("#name_de").val(name_th); 
				 $("#name_en_de").val(name_en); 
				 $("#name_short_del").val(name_short); 
				  
			};
			
			
			
		</script>
	</body>
</html>