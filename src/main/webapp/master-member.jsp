<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : Member</title>
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
				<%@include file="nav-member-top.jsp" %>
				<form id="service" action="addMember" method="post" >
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">					
						<div class="uk-panel uk-panel-box">
							<div class="uk-panel-header">
								<h3 class ="uk-panel-title"><i class="uk-icon-th-list"></i> Member</h3>
							</div>
					<div class="uk-grid">
						<div class="uk-width-1-1 uk-overflow-container">
							<div class="uk-grid">
								<div class="uk-width-2-10"></div>
								<div class="uk-width-6-10 ridge">									
										<div class="uk-grid">
											<span>ชื่อ</span>			
										</div>
										<div class="uk-grid mt-0">
											<div class="uk-width-2-5 uk-form">
												<s:textfield cssClass="uk-width-1-1" required="required" name="protionModel.sub_contactname" value="" />
											</div>	
										</div>
										<div class="uk-grid ">
											<div class="uk-width-1-5 uk-form">
												<label><input type="checkbox"  name="protionModel.sms_piority" value="1"  > ความสำคัญ</label>
											</div>
										</div>	
										<div class="uk-grid">
											<span>ประเภทสมาชิก</span>			
										</div>
										<div class="uk-grid mt-1">
											<div class="uk-width-1-5 uk-form">
												<label><input type="radio" class="checktype" name="protionModel.contact_id" 
												value="1" checked="checked" > คนไข้ทั่วไป</label>
											</div>
											<div class="uk-width-1-5 uk-form ">
												<label><input type="radio" class="checktype" name="protionModel.contact_id" 
												value="2" > คนไข้แบบบริษัท</label>
											</div>											
											<div class="uk-width-2-5 uk-form ">
												<label><input type="radio" class="checktype" name="protionModel.contact_id" 
												value="3" > คนไข้พนักงาน</label>																									
											</div>
										</div>
									</div>
									<div class="uk-width-2-10"></div>
								</div>
							
							<div class="uk-grid company hidden">
								<div class="uk-width-2-10"></div>
								<div class="uk-width-6-10 ridge">
									<div class="uk-grid">
										<span>ชื่อเต็มบริษัท</span>			
									</div>
									<div class="uk-grid mt-0">
										<div class="uk-width-2-5 uk-form">
											<s:textfield cssClass="uk-width-1-1" required="required" name="" value="" />
										</div>	
									</div><br>
									<div class="uk-grid">
										<span>ที่อยู่บริษัท</span>			
									</div>
									<div class="uk-grid mt-0">
										<div class="uk-width-2-5 uk-form">
											<s:textarea cssClass="uk-width-1-1" required="required" name="" value="" />
										</div>	
									</div><br>
								<div class="uk-panel-header">
									<h3 class ="uk-panel-title"> รูปแบบการวางเงิน</h3>
								</div>
									<div class="uk-grid mt-1">
										<div class="uk-width-1-5 uk-form">	
											<label><input type="radio" class="amounttype" name="protionModel.sub_contact_type_id" 
											value="1" > วางบิล	</label>
										</div>
										<div class="uk-width-1-5 uk-form">	
											<label><input type="radio" class="amounttype" name="protionModel.sub_contact_type_id" 
											value="2" > วงเงินทั้งบริษัท	</label>
										</div>
										<div class="uk-width-1-5 uk-form">	
											<label><input type="radio" class="amounttype" name="protionModel.sub_contact_type_id" 
											value="3" > วงเงินต่อบุคคล</label>	
										</div>																	
									</div>
									<div class="uk-grid amounttotal hidden">
										<div class="uk-width-1-2 uk-form">
											จำนวนเงิน : <s:textfield cssClass="uk-width-1-2 numeric uk-text-right" 
											id="amounttotal" name="protionModel.total_amount" value="" /> บาท	
										</div>						
									</div>
								</div>
								</div>																																																	
						</div>
					</div>
					<div class="uk-grid">
						<div class="uk-width-1-1  uk-text-center">	
							<div class="uk-form-icon">	
								<s:submit cssClass=" uk-button uk-button-success"  value="save" />
							</div>
							<div class="uk-form-icon">
		                        <s:reset onclick="allclean()" cssClass=" uk-button uk-button-danger"  value="reset" />
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
		$('.checktype').change(function () {
			if($(this).val()== '2'){
				$('.company').removeClass("hidden");
			}else{
				$('.company').addClass("hidden");
				$('.amounttype').prop('checked', false);
				$('.amounttotal').addClass("hidden");
				$('#amounttotal').val('');
			}
		});
		$('.amounttype').change(function () {
			if($(this).val()== '2'){
				$('.amounttotal').removeClass("hidden");
				$('#amounttotal').val('');
			}else if($(this).val()== '3'){
				$('.amounttotal').removeClass("hidden");
				$('#amounttotal').val('');
			}else{
				$('.amounttotal').addClass("hidden");
				$('#amounttotal').val('');
			}
		});

	});
	function allclean(){
		$('.company').addClass("hidden");
		$('.amounttotal').addClass("hidden");
		
	}
	</script>				
</body>
</html>