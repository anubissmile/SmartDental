<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:คนไข้</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div> 
			<div class="uk-width-9-10 ">
				<%@include file="nav-top.jsp" %>
				<div class="uk-grid uk-grid-collapse">
					<%@include file="fullpatient-leftside.jsp" %>
					<div class="uk-width-6-10" id="right-content">
					<!-- <form action="viewDetailTreatmentPlan" method="post"> -->
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
							<s:if test="alertStatus == 'success' ">
								<div class="uk-alert uk-alert-success uk-width-1-1" data-uk-alert>
								    <a href="" class="uk-alert-close uk-close"></a>
								    <p><s:property value="alertMessage"/> </p>
								</div>
							</s:if>
							<h4  class="hd-text uk-text-primary">แผนการรักษา  <a href="entranchCreateTreatmentPlan" class="uk-button uk-button-primary"><i class="uk-icon-plus"></i> สร้างแผนการรักษา</a> </h4>
							<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray uk-text-nowrap" >
							    <thead>
							        <tr class="hd-table"> 
							            <th class="uk-text-center">ลำดับ</th> 
							            <th class="uk-text-center">ชื่อแผนการรักษา</th>
							            <th class="uk-text-center">วันที่สร้าง</th>
							            <th class="uk-text-center">อัพเดทเมื่อ</th>
							            <th class="uk-text-center">แพทย์</th>
							            <th class="uk-text-center">สถานะ</th>
							            <th class="uk-text-center">จัดการ</th> 
							        </tr>
							    </thead> 
							    <tbody>
							    	<s:if test="listTreatmentPlanModel == null">
							    		<tr class="uk-danger">
								    		<td class="uk-text-center" colspan="5"> ไม่มีการสร้างแผนการรักษา </td>
								        </tr>
							    	</s:if>
							    	<s:else>
								    	<s:iterator value="listTreatmentPlanModel" status="statusList">
								    		<s:if test="headerStatus == 1">
								    			<tr class="uk-success">
								    		</s:if>
								    		<s:elseif test="headerStatus == 2">
								    			<tr class="uk-danger">
								    		</s:elseif>
								    		<td class="uk-text-center"> <s:property value="#statusList.count" /> </td>
									        <td class="uk-text-center"> <s:property value="treatmentPlanname"/></td>
									        <td class="uk-text-center"> <s:property value="createDatetime"/></td>
									        <td class="uk-text-center"> <s:property value="updateDatetime"/></td>
									        <td class="uk-text-center"> 
									        	<s:property value="firstNameTH"/>&nbsp;<s:property value="lastNameTH"/>
								        	</td>
									        <td class="uk-text-center"> <s:property value="headerStatusName"/></td>
									        <td class="uk-text-center"> 
									        <s:url action="viewDetailTreatmentPlan" var="link" escapeAmp="false">
									        	<s:param name="treatPlanModel.treatment_planid"><s:property value="treatment_planid"/></s:param>
									        </s:url>
									        <a href='<s:property value="link"/>' class="uk-button uk-button-primary uk-button-small" > <i class="uk-icon-eye"></i> </a>
									        </td>
									        </tr>
								    	</s:iterator>
							    	</s:else>
								</tbody>
							</table>
						</div>
						<!-- </form> -->
					</div>
					
				</div>
				
				
			</div>
		</div>
		
		<script>
			$(document).ready(function(){
			    
				$( ".m-patient" ).addClass( "uk-active" );
				$("#file").on('click','.remove-tr',function(){
					$(this).closest('tr').remove();
				});
			});
		</script>
	</body>
</html>