<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : ประเภทลูกค้า</title>
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
				<form  action="updateMember" method="post" >
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">					
						<div class="uk-panel uk-panel-box">
							<div class="uk-panel-header">
								<h3 class ="uk-panel-title"><i class="uk-icon-th-list"></i> ประเภทลูกค้า</h3>
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
												<s:textfield cssClass="uk-width-1-1" required="required" 
												name="protionModel.sub_contactname"  />
											</div>	
										</div>
										<div class="uk-grid ">
											<div class="uk-width-1-5 uk-form">
												<s:if test="protionModel.sms_piority == 1 ">
													<label><input type="checkbox"  name="protionModel.sms_piority" 
													value="1" checked="checked" > ความสำคัญ</label>
												</s:if>
												<s:else>
													<label><input type="checkbox"  name="protionModel.sms_piority" 
													value="1"  > ความสำคัญ</label>
												</s:else>
											</div>
										</div>	
										<div class="uk-grid">
											<span>ประเภทลูกค้า</span>			
										</div>
										<div class="uk-grid mt-1">
											<div class="uk-width-1-5 uk-form">
												<label><input type="radio" class="checktype editcontact1" 
												name="protionModel.contact_id" 
												value="1"  > Member</label>
											</div>
											<div class="uk-width-1-5 uk-form ">
												<label><input type="radio" class="checktype editcontact2" 
												name="protionModel.contact_id" 
												value="2" > Contact</label>
											</div>											
											<div class="uk-width-2-5 uk-form ">
												<label><input type="radio" class="checktype editcontact3" 
												name="protionModel.contact_id" 
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
											<s:textfield cssClass="uk-width-1-1 companyName"  
											name="protionModel.sub_contact_companyName"  />
										</div>	
									</div><br>
									<div class="uk-grid">
										<span>ที่อยู่บริษัท</span>			
									</div>
									<div class="uk-grid mt-0">
										<div class="uk-width-2-5 uk-form">
											<s:textarea cssClass="uk-width-1-1 companyName"  
											name="protionModel.sub_contact_addr"  />
										</div>	
									</div><br>
								<div class="uk-panel-header">
									<h3 class ="uk-panel-title"> รูปแบบการวางเงิน</h3>
								</div>
									<div class="uk-grid mt-1">
										<div class="uk-width-1-5 uk-form">	
											<label><input type="radio" class="amounttype edittype1" name="protionModel.sub_contact_type_id" 
											value="1" > วางบิล	</label>
										</div>
										<div class="uk-width-1-5 uk-form">	
											<label><input type="radio" class="amounttype edittype2" name="protionModel.sub_contact_type_id" 
											value="2" > วงเงินทั้งบริษัท	</label>
										</div>
										<div class="uk-width-1-5 uk-form">	
											<label><input type="radio" class="amounttype edittype3" name="protionModel.sub_contact_type_id" 
											value="3" > วงเงินต่อบุคคล</label>	
										</div>																	
									</div>
									<div class="uk-grid amounttotal hidden">
										<div class="uk-width-1-2 uk-form">
											จำนวนเงิน : <input type="text" class="uk-width-1-2 numeric uk-text-right" 
											id="amounttotal" name="totalamount" 
											value="<s:property value="protionModel.total_amount" />" /> บาท	
										</div>						
									</div>
									<div class="uk-grid amountdefault hidden">
										<div class="uk-width-1-2 uk-form">
											จำนวนเงิน : <input type="text" class="uk-width-1-2 numeric uk-text-right" 
											id="amountdefault" name="subcontactamount" 
											value="<s:property value="protionModel.sub_contact_amount" />" /> บาท	
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
		                </div>    
	                </div>	
				</div>
	
			</div>
		<input type="hidden" name="protionModel.sub_contactid" value="<s:property value='protionModel.sub_contactid' />" >
			</form>
	</div>
</div>
<script src="js/autoNumeric.min.js"></script>			
	<script>
	$(document).ready(function(){
		$(".editcontact"+<s:property value='protionModel.contact_id' />+"").prop('checked',true);
			$('.checktype').attr("disabled",true);
		if(<s:property value='protionModel.contact_id' /> == 1 ){						
			$(".editcontact"+<s:property value='protionModel.contact_id' />+"").attr("disabled",false);
		}else if(<s:property value='protionModel.contact_id' /> == 2){
				$('.company').removeClass("hidden");
				$(".edittype"+<s:property value='protionModel.sub_contact_type_id' />+"").prop('checked',true);
				$(".editcontact"+<s:property value='protionModel.contact_id' />+"").attr("disabled",false);
				if(<s:property value='protionModel.sub_contact_type_id' /> == 2){
					$('.amounttotal').removeClass("hidden");
					$('#amounttotal').attr('readonly',true);
				}else if(<s:property value='protionModel.sub_contact_type_id' /> == 3){
					$('.amountdefault').removeClass("hidden");
				}
				
		}else{
			$(".editcontact"+<s:property value='protionModel.contact_id' />+"").attr("disabled",false);
		}
		

		$(".numeric").autoNumeric('init');
		$('.checktype').change(function () {
			if($(this).val()== '2'){
				$('.company').removeClass("hidden");
			}else{
				$('.company').addClass("hidden");
				$('.amounttype').prop('checked', false);
				$('.amounttotal').addClass("hidden");
				$('.amountdefault').addClass("hidden");
				$('#amounttotal').val('');
				$('.companyName').val('');
				$('#amountdefault').val('');
			}
		});
		$('.amounttype').change(function () {
			if(<s:property value='protionModel.sub_contact_type_id' /> != 2){
				if($(this).val()== '2'){
					$('.amounttotal').removeClass("hidden");
					$('.amountdefault').addClass("hidden");
					$('#amounttotal').val('');
					$('#amountdefault').val('');
					$('#amounttotal').removeAttr("disabled",true);
				}else if($(this).val()== '3'){
					$('.amounttotal').addClass("hidden");
					$('.amountdefault').removeClass("hidden");
					$('#amounttotal').val('');
					$('#amountdefault').val('');
	
				}else{
					$('.amounttotal').addClass("hidden");
					$('.amountdefault').addClass("hidden");
					$('#amounttotal').val('');
					$('#amountdefault').val('');
	
				}
			}else{
				$('.amounttotal').addClass("hidden");
				$('#amounttotal').attr("disabled",true);
				if($(this).val()== '2'){
					$('.amounttotal').removeClass("hidden");
					$('.amountdefault').addClass("hidden");
					$('#amountdefault').val('');
					$('#amounttotal').removeAttr("disabled",true);
				}else if($(this).val()== '3'){
					$('.amounttotal').addClass("hidden");
					$('.amountdefault').removeClass("hidden");
					$('#amountdefault').val('');
					$('#amounttotal').attr("disabled",true);
				}else{
					$('.amounttotal').addClass("hidden");
					$('.amountdefault').addClass("hidden");
					$('#amountdefault').val('');
					$('#amounttotal').attr("disabled",true);
				}
			}	
		});

	});

	</script>				
</body>
</html>