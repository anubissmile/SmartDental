<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : promotion</title>
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
				<%@include file="backend-promotion-manage-top.jsp" %>
				<form id="createPro" action="addPromotiondivideamount" method="post" >
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">					
						<div class="uk-panel uk-panel-box">
							<div class="uk-panel-header">
								<h3 class ="uk-panel-title"><i class="uk-icon-th-list"></i> โปรโมชั่น</h3>
							</div>
					<div class="uk-grid">
						<div class="uk-width-1-1 uk-overflow-container">
							<div class="uk-grid">
								<div class="uk-width-2-10"></div>
								<div class="uk-width-6-10 ridge">									
										<input type="hidden" name="protionModel.manage_id" 
										value="<s:property value='protionModel.manage_id' />" />
										<input type="hidden" name="protionModel.promotion_id" value="<s:property value='protionModel.promotion_id' />">
										<div class="uk-grid mt-0 hidden pointsnumber">
											<div class="uk-width-2-5 uk-form uk-form-icon">
												<i class="uk-icon-money"></i>
												<input type="text"   name="protionModel.point" id="pointsNum" class="uk-width-1-1 numeric"  value="0" />
											</div>
										</div>
										<div class="uk-grid">
											
											<div class="uk-width-1-2">
												<h3>ส่วนแบ่งค่าใช้จ่าย</h3>
											</div>
														
										</div>
										<div class="uk-grid  mt-1">											
											<div class="uk-width-1-2">
												<span>รูปแบบจำนวนเงิน</span>
											</div>														
										</div>
										<div class="uk-grid mt-1">
											<div class="uk-width-1-5 uk-form ">
												<s:radio  name="protionModel.type_cost" id="baht2" list="#{'1':' บาท'}" checked="true" />	
											</div>											
											<div class="uk-width-2-5 uk-form ">
												<s:radio  name="protionModel.type_cost" id="per2" list="#{'2':' เปอร์เซ็นต์'}" />																									
											</div>
										</div>
										
										<div class="uk-grid mt-0 ">
											<div class=" uk-width-1-2">
												<span>แพทย์</span>			
											</div>
											<div class=" uk-width-1-2">
												<span>บริษัท</span>			
											</div>											
										</div>
										<div class="uk-grid mt-0 baht">
											<div class="uk-width-1-2 uk-form uk-form-icon">
													<i class="uk-icon-money"></i>
													<input type="text"   name="protionModel.docbaht" id="docbaht" 
													class="uk-width-1-1 numeric"  value="0" />
											</div>
											<div class="uk-width-1-2 uk-form uk-form-icon">
													<i class="uk-icon-money"></i>
													<input type="text"   name="protionModel.combaht" id="combaht" 
													class="uk-width-1-1 numeric"  value="0" />
											</div>
										</div>
										<div class="uk-grid mt-0 percent hidden">
											<div class="uk-width-1-2 uk-form uk-form-icon">
													<i class="uk-icon-money"></i>
													<input type="text"   name="protionModel.doctorCost" id="docper" 
													class="uk-width-1-1 numeric discountPercent"  value="0" />
											</div>
											<div class="uk-width-1-2 uk-form uk-form-icon">
													<i class="uk-icon-money"></i>
													<input type="text"  name="protionModel.companyCost" id="comper" 
													class="uk-width-1-1 numeric discountPercent"  value="0" />
											</div>
										</div>
									</div>
									<div class="uk-width-2-10"></div>
								</div>																																																	
						</div>
						<div class="uk-width-1-1  uk-text-center">	
							<div class="uk-form-icon">	
								<button class=" uk-button uk-button-success" type="submit" id="allsave" >บันทึก</button>
							</div>
							<div class="uk-form-icon">
		                        <a href="promotionManagement-<s:property value='protionModel.promotion_id' />" class=" uk-button uk-button-danger" >ยกเลิก</a>
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
			if(<s:property value='protionModel.manage_id' />!= 0) {
				if(<s:property value='protionModel.type_cost' /> == 1){
					$('#baht2').prop('checked',true);
					checkHidden($('.baht'),$('.percent'));
					$('#docbaht').val(<s:property value='protionModel.doctor_cost' />);
					$('#combaht').val(<s:property value='protionModel.company_cost' />);
				}else if(<s:property value='protionModel.type_cost' /> == 2){
					$('#per2').prop('checked',true);
					checkHidden($('.percent'),$('.baht'));
					$('#docper').val(<s:property value='protionModel.doctor_cost' />);
					$('#comper').val(<s:property value='protionModel.company_cost' />);
				}
			}
				
		});
		function checkHidden(name1,name2) {
			$(name1).removeClass('hidden');
    		$(name2).addClass('hidden');
		}
		$(document).on("change","input[name='protionModel.type_cost']",function(){			
	    	if($(this).val()== 2){
	    		checkHidden($('.percent'),$('.baht'));
	    	}else{
	    		checkHidden($('.baht'),$('.percent'));
	    	}
	    	$('#discountPercent').val('0');
		});
		$(document).on("keyup",".discountPercent",function(){
			var docper1 = parseInt($('#docper').val());
			var comper1 = parseInt($('#comper').val());
			if($(this).autoNumeric('get')>101){
			    swal(
			    		  'WARNING!',
			    	      'ค่าข้อมูลไม่สามารถเกิน 100%ได้ :)',
			    	      'error'
			    	    )
			    	    $(this).val(0);  
			}
		 	 if(Math.abs(docper1 + comper1)>101){
				swal(
			    		  'WARNING!',
			    	      'ค่าข้อมูลไม่สามารถเกิน 100%ได้ :)',
			    	      'error'
			    	    )
			    	    $(this).val(0);  
			}  
		})	
/* 		$(document).on("click",".plancallall",function(){
			
		}); */
		
		
	</script>				
	</body>
</html>