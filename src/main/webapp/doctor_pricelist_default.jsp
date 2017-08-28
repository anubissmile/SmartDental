<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctor_Pricelist_default</title>
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
				<form action="addDoctorPricelistDefault" method="post">
					<div class="uk-grid uk-grid-collapse padding5 border-gray uk-panel uk-panel-box bg-gray">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">default</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> ตั้งค่า DF พื้นฐานจากหมวดการรักษา</h3> 
								</div>
									<s:if test="hasActionErrors()"> 
								         <div class="uk-alert uk-alert-danger" data-uk-alert> 
								           <li class="uk-alert-close uk-close"></li> 
								             <s:actionerror/> 
								         </div> 
								     </s:if>
								<div class="uk-grid   uk-form">
									<div class="hidden"> <s:textfield name="docModel.DoctorID" /></div>
									    <div class="uk-width-1-1 uk-panel-header "><h3>
									    	<i class="uk-icon-list"></i> หมวดการรักษา</h3>
									    </div>
								<div class="uk-grid  uk-width-1-1">
									<div class="uk-width-1-6"></div>
								    <div class="uk-width-4-6 uk-overflow-container">
										<table class="uk-table uk-table-hover border-gray uk-table-small">
											<thead>
												<tr class="hd-table">
													<th class="uk-text-center uk-width-2-5">หมวดการักษา</th>
													<th class="uk-text-center uk-width-1-5">DF(%)</th>
													<th class="uk-text-center uk-width-1-5">DF(Baht)</th>
													<th class="uk-text-center uk-width-1-5">LAB(%)</th>
												</tr>
												
											</thead>
											<tbody>
												<s:iterator value="categoryList">
													<tr>
														<th class="uk-text-center">
														<input type="hidden" value="<s:property value="treatCategory_id" />" 
														name="cateID" >
														<s:property value="treatCategory_code" />-<s:property value="treatCategory_name" /></th>
														<th class="uk-text-center">
															<input type="text"  name="df_percent" value="<s:property value="doctor_price_list_default_df_percent" />" 
															class="uk-width-1-1 discountPercent uk-text-center  numeric" />
														</th>
														<th class="uk-text-center">
															<input type="text"  name="df_baht" value="<s:property value="doctor_price_list_default_df_baht" />" 
															class="uk-width-1-1  uk-text-center  numeric" />
														</th>
														<th class="uk-text-center">
															<input type="text"  name="df_lab" value="<s:property value="doctor_price_list_default_price_lab" />" 
															class="uk-width-1-1 discountPercent uk-text-center  numeric" />
														</th>
													</tr>
												</s:iterator>
											</tbody>	
										</table>
									</div>
								</div>	
								</div>
							<div class="uk-grid uk-grid-small">
	                       		<div class="uk-width-1-1 uk-text-center">  
	                       			<button type="submit" class="uk-button uk-button-success" ><i class="uk-icon-copy"></i> บันทึกข้อมูล</button> 
	                       		</div>
				            </div>	
								
							</div>
						</div>
								
					</div>			
				</form>

			</div>
	</div>		
</body>
<script src="js/autoNumeric.min.js"></script>
<script>

	$(document).ready(function () {
		$(".numeric").autoNumeric('init');
	});
	$(document).on("keyup",".discountPercent",function(){
		if($(this).autoNumeric('get')>100){
		    swal(
		    		  'WARNING!',
		    	      'ค่าข้อมูลไม่สามารถเกิน 100%ได้ :)',
		    	      'error'
		    	    )
		    	    $(this).val(0);  
		}
		
	})		
</script>		    
</html>