<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:ประเภทสินค้า</title>
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
					<form id="productG" action="productTypeMaster" method="post">
						<div class="uk-grid ">
							<div class="uk-width-1-1 uk-overflow-container ">
								 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
								 	<div class="uk-panel-badge uk-badge uk-badge-primary">master</div>
	                                <div class="uk-panel-header">
									    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> ประเภทสินค้า</h3>
									</div>
									<div class="uk-grid uk-form "> 
										<div class="uk-form-icon uk-width-1-4"> 
											<i class="uk-icon-asterisk"></i>
		                             		<input id="as" type="text" name="ProducttypeModel.id" placeholder="รหัส" class="uk-width-1-1"
		                             			pattern="(?=.*\d).{4,}" title="กรุณาใส่รหัสให้ครบ 4 หลัก" maxlength="4" required>
		                            	</div>
		                            	<div class="uk-form-icon uk-width-1-3"> 
											 <i class="uk-icon-asterisk"></i>
			                             		<input id="ad" type="text" name="ProducttypeModel.name" placeholder="ชื่อ" class="uk-width-1-1" required> 
		                            	</div>
		                            	<div class="uk-form-icon uk-width-1-4">
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
										        	<th>ลำดับ</th>
										            <th>รหัส</th>
										            <th>ชื่อ</th>
										            <th> </th> 
										        </tr>
										    </thead> 
										    <tbody>
										    	 <%   
												    if(request.getAttribute("typelist")!=null)	{
													    List typelist = (List) request.getAttribute("typelist");
					                                	List <ProducttypeModel> ProductTypeModel = typelist;
					                                	int x=0;
						            	         	 	for(ProducttypeModel pgm : ProductTypeModel){ 
						            	         	 	x++; 
					            	         	 %>
											        <tr>
											            <td class="uk-text-center"><%=x%></td>
											            <td class="uk-text-left"><%=pgm.getId()%></td>
											            <td class="uk-text-left"><%=pgm.getName()%></td>
											            <td class="uk-text-right">
											            	<a href="#update" onclick="update('<%=pgm.getId()%>','<%=pgm.getName()%>')" class="uk-button uk-button-primary uk-button-small" data-uk-modal>
																<i class="uk-icon-pencil"></i> แก้ไข
															</a>
											            	<a href="#delete_group" onclick="delete_group('<%=pgm.getId()%>','<%=pgm.getName()%>')" class="uk-button uk-button-danger uk-button-small" data-uk-modal>
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
						         	<button class="uk-modal-close">ยกเลิก</button>
						         	<button id="updateg" name="updatet">ตกลง</button>
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
						         	<button class="uk-modal-close">ยกเลิก</button>
						         	<button id="deleteg" name="deletet">ตกลง</button>
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
					$("#as").removeAttr("required");
					$("#ad").removeAttr("required");
					$("#productG").submit();
				}); 
				$("#updateg").click(function(){
					$("#as").removeAttr("required");
					$("#ad").removeAttr("required");
					$("#productG").submit();
				}); 
				
			});
			
			function update(id, name) { 
				 $("#id_up").val(id);
				 $("#name_up").val(name); 
			};
			function delete_group(id, name) { 
				 $("#id_de").val(id);
				 $("#name_de").val(name); 
				  
			};
			
		</script>
	</body>
</html>