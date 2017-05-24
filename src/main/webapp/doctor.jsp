<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.person.action.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<html>
	<head>
		<title>Smart Dental:ข้อมูลแพทย์</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="doctor-nav.jsp" %>
 
 			<form  action="Doctorsearch" method="post" >
				
					<div class="uk-width-1-1 padding5 uk-form" >
					
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
						 	<p class="uk-text-muted uk-width-1-1">ข้อมูลแพทย์ </p>
						 	
							<div class="uk-width-1s-1 uk-overflow-container uk-form">
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-4-6 ">
											 <div class="uk-grid uk-grid-collapse">
											 	<div class="uk-width-2-6 uk-text-right" ></div>
												<div class="uk-width-2-6 uk-text-right" >ผู้ดำเนินการสาขา
												<s:select cssClass="uk-form" list="branchlist" name="docModel.branch_id"
											      	   headerKey="" headerValue = "เลือกทั้งหมด"/> 
										      	
												</div>										
												<div class="uk-width-2-6 uk-text-right">สาขาที่ลงตรวจ
												<s:select cssClass="uk-form" list="branchlist" name="docModel.branchStandID"
											      	   headerKey="" headerValue = "เลือกทั้งหมด"/> 
										      	</div>
										      </div>	
											</div>
											
									      	<div class="uk-width-1-6 uk-text-right">สถานะ
									      		<select class="uk-form uk-width-2-3" name="docModel.work_status">
									      			<option value="1">Active</option>
									      			<option value="0">Inactive</option>
									      		</select>
									      	</div>
									      	<div class="uk-width-1-6 uk-text-center">
									      		<button class="uk-button uk-button-primary uk-button uk-width-1-2 uk-icon-search"  type="submit"> ค้นหา</button>
									      	</div>
										</div> <br>
						 	<table id="doc_table" class="uk-table uk-table uk-table-hover uk-table-condensed border-gray ">
						 		<thead>
						 			<tr class="hd-table">
						 				<th>รหัสแพทย์</th>
							            <th>ชื่อ-นามสกลุ TH</th> 
							            <th>ชื่อ-นามสกลุ EN</th> 
							            <th>ชื่อเล่น</th>
							            <th>เลขที่ใบประกอบวิชาชีพ</th>
							            <th>เลขที่สัญญา</th>
							            <th>รหัสพนักงาน</th>
							            <th>สถานะการทำงาน</th>
							            <th>จัดการ</th>
							        </tr>
						 		</thead>
						 		<tbody>
									    	<s:iterator value="doctorList">
									    	<tr>
									    		<td><s:property value="doctorID"/></td>
									    		<td><s:property value="pre_name"/><s:property value="firstname_th"/>&nbsp;<s:property value="lastname_th"/></td>
									    		<td><s:property value="pre_name_en"/><s:property value="firstname_en"/>&nbsp;<s:property value="lastname_en"/></td>
									    		<td><s:property value="nickname"/></td>
									    		<td><s:property value="TMCLicense"/></td>
									    		<td><s:property value="contract_id"/></td>
									    		<td><s:property value="emp_id"/></td>
									    		<s:if test="work_status == 1 ">								    		
									    			<td class="uk-text-center">Active</td>
									    		</s:if>
									    		<s:else>
									    			<td class="uk-text-center">Inactive</td>
									    		</s:else>
									    			
									    		<td class="uk-text-center">
									    			
													<div class="uk-button-dropdown" data-uk-dropdown>
									                    <button class="uk-button uk-button-success">
									                    	จัดการ<i class="uk-icon-caret-down"></i>
								                    	</button>
									                    <div class="uk-dropdown uk-dropdown-small uk-dropdown-top">
									                        <ul class="uk-nav uk-nav-dropdown">
									                            <li class="uk-nav-header">แพทย์</li>
									                            <li>
													    			<s:a href="GetDoctor?d=%{doctorID}"
													    				theme="simple">
													    				<i class="uk-icon-pencil"></i> แก้ไข
													    			</s:a>
																	<s:a href="doctor-schedule-calendar-%{DoctorID}"
																		theme="simple">
																		<i class="uk-icon-calendar"></i> ตารางงาน
																	</s:a>
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
				
			</form>
			</div> 	
		 </div>
			
			 
		<script>
		$(document).on('click', '#btn_del', fn_buttonmodal_habndler).ready(function(){
			$( ".m-setting" ).addClass( "uk-active" );
			 
			$("#doc_table").dataTable( {
				  "ordering": false
			} );

			$("#deleteg").click(function(){
				$("#service").submit();
			}); 
			$("#updateg").click(function(){
				$("#service").submit();
			});
			$("#branchcheck").click(function(){
				if(this.checked){
					$("select[name='docModel.branch_id']").removeAttr('required', false);
					$("select[name='docModel.branch_id']").attr('disabled', true);
				}else{
					$("select[name='docModel.branch_id']").attr('required', true);						
					$("select[name='docModel.branch_id']").removeAttr('disabled', false);
				}
			});

		})
		
		function update(id, name) { 
			 $("#hdid_up").val(id);
			 $("#id_up").val(id);
			 $("#name_up").val(name);  
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
		    $('#delete_product').on({
		        'uk.modal.show':function(){
		        	$("#Productdel").val(Productid);
		        },
		        'uk.modal.hide':function(){
		                    //hide modal
		        }
		    }).trigger('uk.modal.show');
		}
		
		</script>		
	</body>
</html>