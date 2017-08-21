<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : โปรโมชั่น</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<body>
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
					<div class="uk-grid"></div>
					<form id="service" action="addPromotion" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
						</div>
					</div>
					<div class=" ">
						<div class="uk-width-1-1 ">
							<div class="uk-panel uk-panel-box" style="min-height: 99vh;">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">list</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> โปรโมชั่น
								    <div class="uk-form-icon uk-width-4-10">
	                            		<button class="uk-button uk-button-success " type="submit">เพื่มโปรโมชั่น</button>
	                            	</div>
								    </h3>
								</div>
						<div class="uk-width-10-10 uk-overflow-container uk-form">
							<div class="uk-width-1-1 uk-margin-medium-bottom">
			 					<ul class="uk-tab" data-uk-switcher="{
			 							connect:'#Gift-active',
			 							animation: 'fade'
			 						}">
								    <li class="uk-active"><a href="#">Active</a></li>
								    <li><a href="#">Inactive</a></li>
								</ul>
			 				</div>
			 				<ul class="uk-width-1-1 uk-switcher" id="Gift-active">  
							 	<li class="uk-active">
									<table id="listpromotiontable" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ชื่อโปรโมชั่น</th>
									            <th class="uk-text-center">วันที่</th>
									            <th class="uk-text-center">ช่วงเวลา</th> 
									            <th class="uk-text-center">จัดการ</th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="promotionModel">
									    	<s:if test="status_pro == 0">									    	
									    	<tr>
									    		<td class="uk-text-center"><s:property value="name"/></td>
									    		<td class="uk-text-center"><s:property value="start_date"/> - <s:property value="end_date"/></td>
									    		<s:if test="is_alltime == 1">
									    			<td class="uk-text-center">ทั้งวัน</td>
									    		</s:if>
									    		<s:else>
									    			<td class="uk-text-center"><s:property value="start_time"/> - <s:property value="end_time"/></td>
									    		</s:else>
									    		<td class="uk-text-center"><a href="promotionManagement-<s:property  value="promotion_id"/>" class="uk-button uk-button-primary uk-button-small">
									    			<i class="uk-icon-pencil"></i> จัดการ</a>
									    			<a href="" class="uk-button uk-button-success uk-button-small">
									    			<i class="uk-icon-list"></i> คำอธิบาย</a>
									    		</td>
									    	</tr>
									    	</s:if>
						    				</s:iterator>			    
									    </tbody>   
									</table>
								</li>
								<li>
									<table id="listpromotiontable-in" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ชื่อโปรโมชั่น</th>
									            <th class="uk-text-center">วันที่</th>
									            <th class="uk-text-center">ช่วงเวลา</th> 
									            <th class="uk-text-center">จัดการ</th>
									        </tr>
									    </thead>
									    <tbody>
									    	<s:iterator value="promotionModel">
									    	<s:if test="status_pro == 1">									    	
									    	<tr>
									    		<td class="uk-text-center"><s:property value="name"/></td>
									    		<td class="uk-text-center"><s:property value="start_date"/> - <s:property value="end_date"/></td>
									    		<s:if test="is_alltime == 1">
									    			<td class="uk-text-center">ทั้งวัน</td>
									    		</s:if>
									    		<s:else>
									    			<td class="uk-text-center"><s:property value="start_time"/> - <s:property value="end_time"/></td>
									    		</s:else>
									    		<td class="uk-text-center"><a href="promotionManagement-<s:property  value="promotion_id"/>" class="uk-button uk-button-primary uk-button-small">
									    			<i class="uk-icon-pencil"></i> จัดการ</a>
									    			<a href="" class="uk-button uk-button-success uk-button-small">
									    			<i class="uk-icon-list"></i> คำอธิบาย</a>
									    		</td>
									    	</tr>
									    	</s:if>
						    				</s:iterator>			    
									    </tbody>   
									</table>
								</li>
							</ul>	
									</div>									
							</div>
						</div> 
					</div>

				</div>	
					</form>
			</div>
					
					
		</div>

		<script>
			$(document).ready(function(){
			    $('#listpromotiontable').DataTable();
			    $('#listpromotiontable-in').DataTable();
			});
		</script>
	
</body>
</html>