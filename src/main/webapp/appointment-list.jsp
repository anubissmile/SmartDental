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

				<form id="service" action="getAppiontmentListSearch" method="post">
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
								
							<div class="uk-grid uk-grid-collapse">
							<div class="uk-width-1-1 uk-form">
							<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-2-10 uk-text-right" >
												 <h3><b>สาขา :</b> </h3>
											</div>
											<div class="uk-width-1-10" >
											<s:select cssClass="uk-form" list="branchlist" name="appointmentModel.authenBranchcode"
										      	   headerKey="0" headerValue = "ทุกสาขา"/> 
											</div>
											<div class="uk-width-1-10 uk-text-right" >
											 <h3><b>วันที่ :</b> </h3>
											</div>
											<div class="uk-width-2-10 " >
											<s:textfield data-uk-datepicker="{format:'DD-MM-YYYY'}" name="appointmentModel.dateToday"
											id="datesearchstart" autocomplete="off" />
											</div>
											<div class="uk-width-1-10 uk-text-right" >
											 <h3><b>ถึง :</b> </h3>
											</div>
											<div class="uk-width-2-10 uk-form" >
											 <s:textfield data-uk-datepicker="{format:'DD-MM-YYYY'}" name="appointmentModel.datetodayend"
											 id="datesearch" autocomplete="off"  />
											</div>
											<div class="uk-width-1-10 " >
											 <button class="uk-button uk-width-1-1 uk-button-primary uk-icon-search ">ค้นหา</button>
											</div>
								</div><br>
								<table id="tbap" style="margin-bottom:100px;" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray ">

									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">รหัสนัดหมาย</th>
									            <th class="uk-text-center">HN</th>
									            <th class="uk-text-center">HN สาขา</th>									                     
									            <th class="uk-text-center">ชื่อ - นามสกุล </th>
									            <th class="uk-text-center">วันที่ - ช่วงเวลา</th>
									            <th class="uk-text-center">สถานะ</th>
									            <th class="uk-text-center">การจัดการ</th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="appointmentList">
									    	<tr>
									    		<td class="uk-text-center"><s:property value="appointCode"/></td>
									    		<td class="uk-text-center"><s:property value="HN"/></td>
									    		<td class="uk-text-center"><s:property value="branch_hn"/></td>
									    		<td class="uk-text-center"><s:property value="patPrenameth"/><s:property value="firstNameTH"/>&nbsp;<s:property value="lastNameTH"/></td>
									    		<td class="uk-text-center"><s:property value="date"/> (<s:property value="timeStart"/> - <s:property value="timeEnd"/>)</td>
									    		<s:if test="appointmentStatus == 5">
									    			<td class="uk-text-center">รอการติดต่อ</td>
									    		</s:if>
												<s:elseif test="appointmentStatus == 4">
													<td class="uk-text-center">เลื่อนนัดหมาย</td>
												</s:elseif>
												<s:elseif test="appointmentStatus == 3">
													<td class="uk-text-center">ยกเลิกนัดหมาย</td>
												</s:elseif>
												<s:elseif test="appointmentStatus == 2">
													<td class="uk-text-center">ยืนยันการนัดหมาย</td>
												</s:elseif>
												<s:elseif test="appointmentStatus == 1">
													<td class="uk-text-center">ไม่มาตามนัดหมาย</td>
												</s:elseif>
												<s:elseif test="appointmentStatus == 6">
													<td class="uk-text-center">ติดต่อไม่ได้</td>
												</s:elseif>
												<s:else>
													<td class="uk-text-center">มาตามนัดหมาย</td>
												</s:else>
												<td class="uk-text-center">
												<div class="uk-button-dropdown" data-uk-dropdown="{pos:'bottom-right'}">
									                    <button class="uk-button uk-button-success" type="button">
									                    	จัดการ<i class="uk-icon-caret-down"></i>
								                    	</button>
									                    <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" >
									                        <ul class="uk-nav uk-nav-dropdown">
									                        	<li class="uk-text-left">
													    			<a href="getAppiontmentpatient-<s:property value="appointmentID"/>">
													    				<i class="uk-icon-institution"></i> ดูรายละเอียด
													    			</a>
								                            	</li>
								                            	<s:if test="branchCode == branchCodeCheck">
								                            	<s:if test="appointmentStatus == 5 || appointmentStatus == 2">
								                            	<li class="uk-nav-divider"></li>							                            	
									                            <li class="uk-text-left">
													    			<a href="#confirmapp" onclick="changestatus_app('<s:property value="appointmentID" />')"
													    			 data-uk-modal>
													    				<i class="uk-icon-check"></i> มาตามนัดหมาย
													    			</a>
								                            	</li>
								                            	</s:if>								                            	
								                            	<s:if test="appointmentStatus == 5">
								                            	<li class="uk-nav-divider"></li>
								                            	<li class="uk-text-left">
													    			<a href="#delAppointment" onclick="delete_app('<s:property value="appointmentID" />')" data-uk-modal>
													    				<i class="uk-icon-eraser"></i> ลบ
													    			</a>
								                            	</li>
								                            	</s:if>
								                            	</s:if>	
									                        </ul>
									                    </div>
									            </div>
									            </td>
									    	</tr>
						    				</s:iterator>			    
									    </tbody>   
									</table>
							</div>
							<div  class="uk-width-3-10 uk-form">
							
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
				         	<h3>ยืนยันคนไข้มาตามการนัดหมาย!</h3> 
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">	
				         			<div class="uk-width-3-4 hidden">
				         				<textarea rows="" name="appointmentModel.description" cols=""></textarea>
				         			</div>		                    
			                    <s:hidden name="appointmentModel.appointmentID" id="appchange" value=""></s:hidden>
			                    <s:hidden name="appointmentModel.appointmentStatus" value="0"></s:hidden>
			                    <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
                			</div>
					    </div>
					    </form>
					</div> 
					<div id="delAppointment" class="uk-modal ">
						<form action="deleteAppointment" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-body">
				         	<h3>ยืนยันลบการนัดหมาย!</h3> 
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">		                    
			                    <s:hidden name="appointmentModel.appointmentID" id="appdel" value="" ></s:hidden>
			                    <s:hidden name="appointmentModel.appointmentStatus" value="0"></s:hidden>
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
			function delete_app(id) { 
				$('#appdel').val(id);
			}
			function changestatus_app(id) { 
				$('#appchange').val(id);
			}

</script>
	</body>
</html>