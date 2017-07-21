<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctor_branch</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head>
	<body> 	
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="doctor-nav-black.jsp" %>
				<script type="text/javascript" src="js/webcam.min.js"></script>
				<form action="BranchStandard" method="post">
					<div class="uk-grid uk-grid-collapse padding5 border-gray uk-panel uk-panel-box bg-gray">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">สาขา</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> สาขาที่ลงตรวจ</h3> 
								</div>
									<s:if test="hasActionErrors()"> 
								         <div class="uk-alert uk-alert-danger" data-uk-alert> 
								           <li class="uk-alert-close uk-close"></li> 
								             <s:actionerror/> 
								         </div> 
								     </s:if>
								<div class="uk-grid uk-form">
									<div class="hidden"> <s:textfield name="docModel.DoctorID" /></div>
									<div class="uk-width-1-4">
										สาขา :
										<s:select list="branchlist" cssClass="uk-width-2-3" name="docModel.branch_id"  required="true" headerKey="" headerValue = "กรุณาเลือก"  />
									</div>
									<div class="uk-width-2-4">
										ค่าเวรทันตแพทย์ :
										<s:textfield  cssClass="uk-width-2-3" name="docModel.price" />	
									</div>
									<div class="uk-width-1-4">
										<button type="submit"  class="uk-button uk-button-success ">
										เพิ่มสาขา
										</button>									
									</div>
								</div>
							</div>
							<div class="uk-panel uk-panel-box">
                                <div class="uk-panel-header">
								    <div class="uk-panel-title uk-grid uk-grid-collapse ">
									    <div class="uk-width-2-10"><h3>
									    	<i class="uk-icon-calendar"></i> รายชื่อสาขา </h3>
									    </div>
								    	<div class="uk-width-3-10 bor-rightAndleft uk-text-center">
									    	<s:iterator value="branchStandardList" status="docbranch">
									    		<s:if test="1>#docbranch.index ">
									    		<s:property value="pre_name_th" /><s:property value="first_name_th" /> <s:property value="last_name_th" />
									    		</s:if>
									    	</s:iterator>
								    	</div>
								    	<div class="uk-width-3-10">
											<s:a href="doctor-schedule-calendar-%{docModel.DoctorID}" 
												class="uk-button uk-button uk-button-small uk-margin-small-left">
												<li class="uk-icon-calendar"></li>
												ดูตารางงานแพทย์
											</s:a>
								    	</div>
								    	<div class="uk-width-2-10"></div>
								    </div>
								</div>
								<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-condensed uk-table-hover">
									<thead>
										<tr>
											<th class="uk-text-center">ชื่อสาขา</th>
											<th class="uk-text-center">ค่าเวรทันตแพทย์</th>
											<th></th>
										</tr>
										
									</thead>
									<tbody>
										<s:iterator value="branchStandardList">
										<tr>
											<th class="uk-text-center"><a href=""><s:property value="branchName" /></a></th>
											<th class="uk-text-right">
												<s:property value="getText('{0,number,#,##0.00}',{price})" /> บาท
											</th>
											<th>
												<a href="#delete_branchStandard" 
													onclick="deleteBranchStant('<s:property value="branchStandID" />')"
													class="uk-button uk-button-danger uk-button-small" 
													 data-uk-modal><i class="uk-icon-eraser"></i> ลบ</a>
												<a href="#update_salary"  onclick="update('<s:property value="branchName"/>','<s:property value="price"/>','<s:property value="branchStandID" />')"   class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
												<s:a href="doctor-monthly-schedule-%{docModel.DoctorID}-%{branchStandID}" 
													class="uk-button uk-button-success uk-button-small">
													<li class="uk-icon-calendar-plus-o"></li>
													เพิ่มตารางเวลาประจำเดือน
												</s:a>
											</th>
										</tr>
										</s:iterator>
									</tbody>	
									</table>
								</div>
							</div>	
						</div>		
					</div>			
				</form>
					<div id="update_salary" class="uk-modal ">
						<form action="UpadteBranchStandard-<s:property value="docModel.DoctorID" />" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
					    <div class="uk-modal-header"><i class="uk-icon-pencil"></i> แก้ไข</div>
				         	<div class="uk-modal-body">
				         		<div class="uk-grid">
				         			<div class="uk-width-1-2">สาขา
				         				<input type="text" id="branchname" readonly /> 
				         			</div>
				         			<div class="uk-width-1-2">ค่าเวรทันตแพทย์
				         				<s:textfield id="salary" name="docModel.price" />
				         			</div>
				         		</div>
				         		
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">
				         				                    <input type="hidden" id="Branchupdate" name="docModel.branchStandID"><button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>

                			</div>

					    </div>
					    </form>
					</div>
					<div id="delete_branchStandard" class="uk-modal ">
						<form action="DeleteBranchStandard-<s:property value="docModel.DoctorID" />" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-body"><i class="uk-icon-exclamation-circle"></i> ต้องการยืนยันการลบหรือไม่</div>
				         	<div class="uk-modal-footer uk-text-right">
				         				                    <input type="hidden" id="Branchdel" name="docModel.branchStandID"><button type="submit" class="uk-button uk-button-success "> ยืนยัน</button>
			                    <button class="uk-button uk-button-danger uk-modal-close ">ยกเลิก</button>

                			</div>

					    </div>
					    </form>
					</div>
			</div>
	</div>		
</body>
<script>

		function update(branchname,salary,branchstant) { 
				 $("#branchname").val(branchname);
				 $("#salary").val(salary);
				 $("#Branchupdate").val(branchstant);
	}
		function deleteBranchStant(branchStantid)
		{
			$("#Branchdel").val(branchStantid);
		    
		}

		
</script>		    
</html>