<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : appointment</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
		<link href="css/style-promotion.css"rel="stylesheet">	
	</head> 
	<body>
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
			<div class="uk-grid"></div>

				<form id="service" action="getemployeelistsearch" method="post">
				<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container "></div>
					</div>
					<div class=" ">
						<div class="uk-width-1-1 ">
							<div class="uk-panel uk-panel-box" style="min-height: 101.5vh;">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Detail</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> รายการนัดหมาย</h3>	
								</div>
					<div class="uk-grid">
						<div class="uk-width-1-1 uk-overflow-container">
							<div class="uk-grid">
							<div class="uk-width-1-1 uk-form">
								<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-3-10 uk-text-right" ></div>
											<div class="uk-width-1-10 uk-text-right" >
											 <h3><b>วันที่ :</b> </h3>
											</div>
											<div class="uk-width-2-10 " >
											 <input data-uk-datepicker="{format:'DD-MM-YYYY'}" id="datesearchstart" autocomplete="off" type="text" >
											</div>
											<div class="uk-width-1-10 uk-text-right" >
											 <h3><b>ถึง :</b> </h3>
											</div>
											<div class="uk-width-2-10 " >
											 <input data-uk-datepicker="{format:'DD-MM-YYYY'}" id="datesearch" autocomplete="off" type="text" >
											</div>
											<div class="uk-width-1-10 " >
											 <button >ค้นหา</button>
											</div>
								</div><br>
								<table id="tbap" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">

									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ชื่อพนักงาน</th>
									            <th class="uk-text-center">Username</th>         
									            <th class="uk-text-center">สาขา</th>
									            <th class="uk-text-center">เบอร์โทรศัพท์</th>
									            <th class="uk-text-center">สถานะ</th>
									            <th class="uk-text-center">การจัดการ</th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="">
									    	<tr>
									    		<td><s:property value="pre_name_th"/><s:property value="firstname_th"/>&nbsp;<s:property value="lastname_th"/></td>
									    		<td><s:property value="empuser"/></td>
									    		<td class="uk-text"><s:property value="branch_id"/></td>
									    		<td class="uk-text">
									    			<s:iterator value="ListTelModel" status="stat">
									    				<s:if test="#stat.count>1">
										    				,
										    			</s:if>	
									    				<s:property value="tel_number"/>
									    			</s:iterator>		
									    		</td>
									    		<td class="uk-text-center"><s:property value="work_status"/></td>
									    		<td class="uk-text-center"><a href="editemployee?pro_id=<s:property  value="emp_id"/>" class="uk-button uk-button-primary uk-button-small">
									    			<i class="uk-icon-pencil"></i> แก้ไข</a>
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
						</div> 
					</div>

				</div>	
				</form>					
			</div>	
		</div>
					<div id="confirmapp" class="uk-modal ">
						<form action="updateAppStatuslog" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-body">
				         	<h3>ยืนยันการนัดหมาย!</h3> 
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">	
				         			<div class="uk-width-3-4 hidden">
				         				<textarea rows="" name="appointmentModel.description" cols=""></textarea>
				         			</div>		                    
			                    <s:hidden name="appointmentModel.appointmentID"></s:hidden>
			                    <s:hidden name="appointmentModel.appointmentStatus" value="2"></s:hidden>
			                    <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
                			</div>
					    </div>
					    </form>
					</div>    
<script>
			$(document).ready(function () {
				$('#tbap').dataTable();
				$("#datesearch").datepicker({
				    format: "dd-mm-yyyy",
			        clearBtn: true,
			        autoclose: true,
			        todayHighlight: true
			    });
				$("#datesearchstart").datepicker({
				    format: "dd-mm-yyyy",
			        clearBtn: true,
			        autoclose: true,
			        todayHighlight: true
			    });
			});

			

</script>
	</body>
</html>