<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental :  Gift Card</title>
		<link href="css/style-promotion.css"rel="stylesheet">	
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	<style>
	
		.errorMessage{
			color :red;
			position : absolute;
			top : 25px;
		}
	</style>
	</head> 
	<body>

	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="backend-giftcard-top.jsp" %>
				<form id="service" action="addGiftCard" method="post" >
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">					
						<div class="uk-panel uk-panel-box">
							<div class="uk-panel-header">
								<h3 class ="uk-panel-title"><i class="uk-icon-th-list"></i> Gift Card</h3>
							</div>
					<div class="uk-grid">
						<div class="uk-width-1-1 uk-overflow-container">
							<div class="uk-grid">
								<div class="uk-width-2-10"></div>
								<div class="uk-width-6-10 ridge">									
										<div class="uk-grid uk-grid-collapse mt-0">
											<div class="uk-width-1-3 uk-form uk-text-right">
												<h3>ชื่อชุด Gift Card : </h3>
											</div>
											<div class="uk-width-1-3 uk-form">
												<s:textfield autocomplete="off" cssClass="uk-width-1-1" 
												required="required" name="giftcardModel.giftcard_name" value="" />
											</div>	
										</div><hr>
										<div class="uk-grid">
											<h3>รายละเอียด Gift Card</h3>			
										</div>
										<div class="uk-grid mt-1 uk-grid-collapse">
											<div class="uk-width-1-3 uk-form uk-text-right">
												<span>คำนำหน้า : </span>
											</div>
											<div class="uk-width-1-3 uk-form ">
												<s:textfield autocomplete="off" cssClass="uk-width-1-1 uk-form-small" 
												 name="giftcardModel.giftcard_prefix" value="" />
											</div>											
										</div>
										<div class="uk-grid mt-1 uk-grid-collapse">
											<div class="uk-width-1-3 uk-form uk-text-right">
												<span>จำนวนตัวเลข : </span>
											</div>
											<div class="uk-width-1-3 uk-form ">
												<s:textfield autocomplete="off" cssClass="uk-width-1-1 uk-form-small  " pattern="[0-9]{1,10}"  
												required="required" name="giftcardModel.giftcard_numberlenght" value="" />
											</div>
											<div class="uk-width-1-3 uk-form ">
												<span>หลัก </span>
											</div>											
										</div>
										<div class="uk-grid mt-1 uk-grid-collapse">
											<div class="uk-width-1-3 uk-form uk-text-right">
												<span>เริ่มที่เลข : </span>
											</div>
											<div class="uk-width-1-3 uk-form ">
												<s:textfield autocomplete="off" cssClass="uk-width-1-1 uk-form-small " pattern="[0-9]{1,10}"  
												required="required" name="giftcardModel.giftcard_start_number" value="" />
											</div>											
										</div>
										<div class="uk-grid mt-1 uk-grid-collapse">
											<div class="uk-width-1-3 uk-form uk-text-right">
												<span>จำนวน GiftCard : </span>
											</div>
											<div class="uk-width-1-3 uk-form ">
												<s:textfield autocomplete="off" cssClass="uk-width-1-1 uk-form-small" pattern="[0-9]{1,10}" 
												required="required" name="giftcardModel.giftcard_run_count" value="" />
											</div>
											<div class="uk-width-1-3 uk-form ">
												<span>ใบ </span>
											</div>												
										</div>
										<div class="uk-grid mt-1 uk-grid-collapse">
											<div class="uk-width-1-3 uk-form uk-text-right">
												<span>คำต่อท้าย : </span>
											</div>
											<div class="uk-width-1-3 uk-form ">
												<s:textfield autocomplete="off" cssClass="uk-width-1-1 uk-form-small"  name="giftcardModel.giftcard_suffix" value="" />
											</div>											
										</div>
										<div class="uk-grid mt-1 uk-grid-collapse">
											<div class="uk-width-1-3 uk-form uk-text-right">
												<span>จำนวนเงินในบัตร : </span>
											</div>
											<div class="uk-width-1-3 uk-form ">
												<s:textfield autocomplete="off" cssClass="uk-width-1-1 uk-form-small uk-text-right numeric" 
												required="required" name="giftcarddefaultamount" value="" />
											</div>
											<div class="uk-width-1-3 uk-form ">
												<span>บาท </span>
											</div>												
										</div>
										<div class="uk-grid mt-1 uk-grid-collapse">
											<div class="uk-width-1-3 uk-form uk-text-right">
												<span>วันที่เริ่มใช้ : </span>
											</div>
											<div class="uk-width-1-3 uk-form ">
												<input type="text" autocomplete="off" name="startdate_eg" id="startdate_eg" 
												pattern="[0-9]{1,2}-[0-9]{1,2}-[0-9]{1,4}" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" >
												<input type="text" autocomplete="off" name="startdate_th" id="startdate_th" 
												pattern="[0-9]{1,2}-[0-9]{1,2}-[0-9]{1,4}" class="uk-form-small uk-width-1-1">
											</div>
											<div class="uk-width-1-3">
											<button id="startdate" type="button" 
											class="btn uk-button uk-button-primary uk-button-small" >Thai Year
											</button>
											</div>											
										</div>
										<div class="uk-grid mt-1 uk-grid-collapse">
											<div class="uk-width-1-3 uk-form uk-text-right">
												<span>วันหมดอายุ : </span>
											</div>
											<div class="uk-width-1-3 uk-form ">
												<input type="text" autocomplete="off" name="expiredate_eg" id="expiredate_eg" 
												pattern="[0-9]{1,2}-[0-9]{1,2}-[0-9]{1,4}" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" >
												<input type="text" autocomplete="off" name="expiredate_th" id="expiredate_th" 
												pattern="[0-9]{1,2}-[0-9]{1,2}-[0-9]{1,4}" class="uk-form-small uk-width-1-1">
											</div>
											<div class="uk-width-1-3">
											<button id="expiredate" type="button" 
											class="btn uk-button uk-button-primary uk-button-small" >Thai Year
											</button>
											</div>											
										</div><br>
										<div class="uk-grid mt-1 uk-grid-collapse">
											<div class="uk-width-1-3 uk-form uk-text-right">
												<span>คำอธิบาย : </span>
											</div>
											<div class="uk-width-1-3 uk-form ">
												<s:textarea autocomplete="off" cssClass="uk-width-1-1 uk-form-small " 
												 name="giftcardModel.giftcard_description" value="" />
											</div>											
										</div>
									</div>
									<div class="uk-width-2-10"></div>
								</div>
																																																							
						</div>
					</div>
					<div class="uk-grid">
						<div class="uk-width-1-1  uk-text-center">	
							<div class="uk-form-icon">	
								<s:submit cssClass=" uk-button uk-button-success"  value="บันทึก" />
							</div>
							<div class="uk-form-icon">
		                        <s:reset  cssClass=" uk-button uk-button-danger"  value="ยกเลิก" />
		                    </div>
		                </div>    
	                </div>	
				</div>
	
			</div>
		
			</form>
	</div>
</div>
<script src="js/autoNumeric.min.js"></script>			
	<script>
	$(document).ready(function(){
		$(".numeric").autoNumeric('init');
		$("#startdate_eg").hide();
		$("#startdate_th").datepicker({
		    format: "dd-mm-yyyy",
	        clearBtn: true,
	        autoclose: true,
	        todayHighlight: true
	    });
		$("#startdate").click(function(){
			if($("#startdate").text() == "Thai Year"){
				$("#startdate").text("English Year");
				$("#startdate_th").val("");
				$("#startdate_th").hide();
				$("#startdate_eg").show();
				
			}else{
				$("#startdate").text("Thai Year");	
				$("#startdate_eg").val("");
				$("#startdate_eg").hide();
				$("#startdate_th").show();
				
			}
		});
		$("#expiredate_eg").hide();
		$("#expiredate_th").datepicker({
		    format: "dd-mm-yyyy",
	        clearBtn: true,
	        autoclose: true,
	        todayHighlight: true
	    });
		$("#expiredate").click(function(){
			if($("#expiredate").text() == "Thai Year"){
				$("#expiredate").text("English Year");
				$("#expiredate_th").val("");
				$("#expiredate_th").hide();
				$("#expiredate_eg").show();
				
			}else{
				$("#expiredate").text("Thai Year");	
				$("#expiredate_eg").val("");
				$("#expiredate_eg").hide();
				$("#expiredate_th").show();
				
			}
		});
	});

	</script>				
</body>
</html>