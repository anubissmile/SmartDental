<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : เอกสารคนไข้</title>
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
					<form id="service" action="addDocumentNeed" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">เอกสารคนไข้</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> เอกสารคนไข้</h3>
								</div>
								<div class="uk-grid uk-grid-small uk-form "> 
									<div class="uk-form-icon uk-width-3-10"> 
									<p>ชื่อเอกสาร</p>
									</div>
									<div class="uk-form-icon uk-width-3-10"> 
									<p></p>
									</div>
									<div class="uk-form-icon uk-width-3-10"> 
									<p></p>
									</div>
	                            	<div class="uk-form-icon uk-width-3-10" style="margin-top : 8px"> 
										<i class="uk-icon-asterisk"></i>
		                             	<input type="text"  name="documentModel.doc_name" class="uk-width-1-1"> 
	                            	</div> 

	                            	
	                            	<div class="uk-form-icon uk-width-6-10" style="margin-top : 8px">
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
									<div class="uk-width-1s-1 uk-overflow-container uk-form">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">
									    <thead>
									        <tr class="hd-table">
									      		<th>&nbsp;&nbsp;&nbsp;ชื่อเอกสาร</th>
									            <th></th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="docModel">
									    	<tr>
									    		<td>&nbsp;&nbsp;&nbsp;<s:property value="doc_name"/></td>
									    		<td class="uk-text-right">
									    		
									    			<a href="#update1" onclick="update('<s:property value="doc_name"/>','<s:property value="document_id"/>')"   class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>

									    			
									    			<a href="#delete" id="btn_del" class="uk-button uk-button-danger uk-button-small" data-Productdel='<s:property value="document_id"/>' data-uk-modal>
									    			<i class="uk-icon-eraser"></i> ลบ</a>
									    			
									    			
									    			
									    		
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
				<div  class="uk-modal" id="update1">
					<form action = "updateDocumentNeed"  method="post">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-pencil"></i> แก้ไข</div>
					         			        					         
					         	<div class="uk-form-icon">
		    						<i class="uk-icon-asterisk">
		    						</i>
					         	 <input type="hidden" id="doc_up" name="documentModel.document_id"><input class="uk-width-1-1" type="text" id="name1_up" name="documentModel.doc_name" autofocus="autofocus"> 
					         	</div>
					         <div class="uk-modal-footer uk-text-right" >
					         	<button class="uk-modal-close">ยกเลิก</button>
					         	<button type = "submit">ตกลง</button>
					         </div>
					    </div>
					    </form>
					</div>
					
					
					

					<div id="delete" class="uk-modal ">
						<form action="delDocumentNeed" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-body"><i class="uk-icon-exclamation-circle"></i> ต้องการยืนยันการลบหรือไม่</div>
				         	<div class="uk-modal-footer uk-text-right">
			                    <button class="uk-button uk-button-default uk-modal-close">ยกเลิก</button>
			                    <input type="hidden" id="Productdel" name="documentModel.document_id"><button type="submit" class="uk-button uk-button-default uk-button-danger"> ยืนยัน</button>
                			</div>
					    </div>
					    </form>
					</div>
			</div>
					
					
		</div>

		<script>
			$(document).on('click', '#btn_del', '#btn_update', fn_buttonmodal_habndler).ready(function(){
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
				
				
				
			}).ready(function(){
				$("#tbProduct").dataTable();
			});
			
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
			    var Productid = $(e.target).data('productdel');
			    console.log(Productid);
			    $('#delete').on({
			        'uk.modal.show':function(){
			        	$("#Productdel").val(Productid);
			        },
			        'uk.modal.hide':function(){
			                    //hide modal
			        }
			    }).trigger('uk.modal.show');
			};

			
					

		</script>
	</body>
</html>