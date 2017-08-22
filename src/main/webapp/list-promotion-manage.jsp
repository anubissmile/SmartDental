<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : โปรโมชั่น</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
		<style type="text/css">
		.btn-prosty{
				min-height: 90px !important;
			    padding: 30px !important;
			    line-height: 60px !important;
			    font-size: 13px !important;
			    border-radius: 10px !important;
			} 
		.bor-cen{
				border-right: 5px solid #cccccc ;
			}
			.bor-bto{
				border-bottom: 5px solid #cccccc ;
			}	 
		</style>
	</head> 
	<body>
		<body>
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="backend-promotion-top.jsp" %>
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
								   
								    </h3>
								</div>
								<div class="uk-grid uk-overflow-container">
								<div class="uk-width-1-3 uk-overflow-container uk-form"></div>
								<div class="uk-width-1-3 uk-overflow-container uk-form">
									<div class="uk-grid uk-grid-collapse bor-bto">
										<div class="uk-width-1-2  uk-form bor-cen">
											<a href="getPromotionEdit-<s:property  value='protionModel.promotion_id'/>" class="uk-width-1-1 uk-button uk-button-primary btn-prosty ">
											<i class="uk-icon-list"></i> เงื่อนไขโปรโมชั่น</a>
										</div>
										<div class="uk-width-1-2  uk-form">
											<a href="promotionManagementdivideamount-<s:property value="protionModel.promotion_id" />" 
														class="uk-width-1-1 uk-button uk-button-primary btn-prosty ">
											<i class="uk-icon-list"></i> ส่วนแบ่งค่าใช้จ่าย</a>
										</div>
									</div>
									<div class="uk-grid uk-grid-collapse ">
										<div class="uk-width-1-2  uk-form bor-cen">
											<a href="promotionManagementPoints-<s:property  value='protionModel.promotion_id'/>" 
											class="uk-width-1-1 uk-button uk-button-primary btn-prosty ">
											<i class="uk-icon-list"></i> points</a>
										</div>
										<div class="uk-width-1-2  uk-form">
											<a href="getPromotionDetailList-<s:property  value='protionModel.promotion_id'/>" 
											class="uk-width-1-1 uk-button uk-button-primary btn-prosty ">
											<i class="uk-icon-list"></i> สิทธิประโยชน์</a>
										</div>
									</div>
								</div>
								<div class="uk-width-1-3 uk-overflow-container uk-form"></div>
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