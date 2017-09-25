<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : Scope งานทันตแพทย์</title>
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
					<form id="" action="" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Scope งานทันตแพทย์</div>
                                <div class="uk-panel-header">
                                	<div class="uk-grid uk-panel-title uk-grid-collapse">
								    <h3 class="uk-width-1-3"><i class="uk-icon-cog"></i> Scope งานทันตแพทย์</h3>
								    <h3 class="uk-width-1-3"><a href="#addScope" id="insert_scope" class="uk-button uk-button-success "  data-uk-modal><i class="uk-icon-plus"></i> เพิ่มScope</a></h3>
								    </div>
								</div>
									<div class="uk-width-1-1 uk-overflow-container uk-form">
									
									<table style="margin-bottom:50px;" class="uk-table uk-width-1-1 
									uk-table-hover uk-table-striped uk-table-condensed border-gray " id="scopetable">
									    <thead>
									        <tr class="hd-table">
									        	
									      		<th class="uk-text-center">Scope Name</th>
									            <th class="uk-text-center">การจัดการ</th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="scopeDentistlist">
									    	<tr>
									    		<th class="uk-text-center"><s:property value="position_name_th" /></th>
									    		<td class="uk-text-center">																							
												<div class="uk-button-dropdown" data-uk-dropdown>
									                    <button class="uk-button uk-button-success" type="button">
									                    	จัดการ<i class="uk-icon-caret-down"></i>
								                    	</button>
									                    <div class="uk-dropdown uk-dropdown-small uk-dropdown-top">
									                        <ul class="uk-nav uk-nav-dropdown">							                            	
									                            <li class="uk-text-left">
																	<a href="UpdateScopeDentist-<s:property value="position_id" />"    
																	class="  " >
																		<i class="uk-icon-pencil"></i> แก้ไขรายการรักษา
																	</a>
								                            	</li>
								                            	<li class="uk-nav-divider"></li>
								                            	 <li class="uk-text-left">
								                            	 <a href="updateScopedfDefault-<s:property value="position_id" />"    
																	class="  " >
																		<i class="uk-icon-pencil"></i> แก้ไขDfพื้นฐาน
																	</a>
								                            	 </li>
								                            	<li class="uk-nav-divider"></li>
								                            	<li class="uk-text-left">
																	<a href="#delete" id="del_scope" 
																	class="  " 
																	data-scopedel='<s:property value="position_id" />' 
																	data-uk-modal><i class="uk-icon-eraser"></i> ลบ</a>
								                            	</li>
									                        </ul>
									                    </div>
									                </div>
												</td>
									    	</tr>
									    	</s:iterator>		    
									    </tbody>   
									</table>
									</div>
							</div>
						</div>
					</div>

					 
				</div>	
					</form> 
				<div  class="uk-modal" id="addScope">
					<form action = "addScopeDentist"  method="post">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-plus"></i> เพิ่มScope</div>
					         			        					         
					         	<div class="uk-modal-body">
					         	  Scope Name<s:textfield name="scopeModel.position_name_th"  />
					         	</div>
					         <div class="uk-modal-footer uk-text-right" >					         	
					         	<button type = "submit" class="uk-button uk-button-success">ตกลง</button>
					         	<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
					         </div>
					    </div>
					    </form>
					</div>
					
					
					

					<div id="delete" class="uk-modal ">
						<form action="DeleteScopeDentist" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-body"><i class="uk-icon-exclamation-circle"></i> ต้องการยืนยันการลบหรือไม่</div>
				         	<div class="uk-modal-footer uk-text-right">
			                    
			                    <input type="hidden" id="scopedel" name="scopeModel.position_id"><button type="submit" class="uk-button uk-button-default uk-button-danger"> ยืนยัน</button>
			                    <button class="uk-button uk-button-default uk-modal-close">ยกเลิก</button>
                			</div>
					    </div>
					    </form>
					</div>
			</div>
					
					
		</div>

		<script>
			$(document).on('click', '#del_scope', '#btn_update', fn_buttonmodal_habndler).ready(function(){
				$( ".m-setting" ).addClass( "uk-active" );
				 
				$("#updateb").click(function(){
					$("#productB").submit();
				}); 
				
				$("#deleteg").click(function(){
					$("#service").submit();
				}); 
				$("#updateg").click(function(){
					$("#service").submit();
				}); 
				$('#scopetable').DataTable();
				
				
			})
			
			function update(name1,doc_id) { 
				 $("#doc_up").val(doc_id);
				 $("#name1_up").val(name1);
				  
			};
			function delete_group(id, name) { 
				 $("#id_de").val(id);
				 $("#name_de").val(name);  
			};
		
			function fn_buttonmodal_habndler(e)
			{
			    //get id from pressed button
			    var scopeid = $(e.target).data('scopedel');
			    $('#delete').on({
			        'uk.modal.show':function(){
			        	$("#scopedel").val(scopeid);
			        },
			        'uk.modal.hide':function(){
			                    //hide modal
			        }
			    }).trigger('uk.modal.show');
			};

			
					

		</script>
	</body>
</html>