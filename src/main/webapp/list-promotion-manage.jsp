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
			    font-size: 24px !important;
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
	                            		<a href="getpromotionlist" class="uk-button  " ><i class="uk-icon-mail-reply"></i>กลับ</a>
	                            	</div>
								    </h3>
								</div>
								<div class="uk-grid uk-overflow-container">
								<div class="uk-width-1-3 uk-overflow-container uk-form"></div>
								<div class="uk-width-1-3 uk-overflow-container uk-form">
									<div class="uk-grid uk-grid-collapse bor-bto">
										<div class="uk-width-1-2  uk-form bor-cen">
											<a href="getPromotionEdit-<s:property  value='protionModel.promotion_id'/>" class="uk-width-1-1 uk-button uk-button-primary btn-prosty ">
											<i class="uk-icon-pencil"></i> แก้ไข</a>
										</div>
										<div class="uk-width-1-2  uk-form">
											<a href="" onclick="delete_group('<s:property value="protionModel.promotion_id" />')" 
														class="uk-width-1-1 uk-button uk-button-danger btn-prosty "data-uk-modal>
											<i class="uk-icon-pencil"></i> ลบ</a>
										</div>
									</div>
									<div class="uk-grid uk-grid-collapse ">
										<div class="uk-width-1-2  uk-form bor-cen">
											<a href="promotionManagementPoints-<s:property  value='protionModel.promotion_id'/>" 
											class="uk-width-1-1 uk-button uk-button-success btn-prosty ">
											<i class="uk-icon-list"></i> points</a>
										</div>
										<div class="uk-width-1-2  uk-form">
											<a href="getPromotionDetailList-<s:property  value='protionModel.promotion_id'/>" class="uk-width-1-1 uk-button uk-button-success btn-prosty ">
											<i class="uk-icon-list"></i> รายละเอียด</a>
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
			<form action="PromotionDel" id="deletepro">
					    <input class="uk-width-1-1 hidden" type="text"  id="idsub" name="protionModel.promotion_id" >
			</form>	
					
		</div>

		<script>
			$(document).ready(function(){
			    $('#listpromotiontable').DataTable();
			    $('#listpromotiontable-in').DataTable();
			});
			function delete_group(id) { 

				swal({
	   			  title: 'อนุมัติการทำงาน',
	   			  text: "ท่านต้องการยืนยันการลบหรือไม่!",  		   				  			  
	   			  type: 'warning',
	   			  showCancelButton: true,
	   			  confirmButtonColor: '#3085d6',
	   			  cancelButtonColor: '#d33',
	   			  confirmButtonText: 'อนุมัติ',
	   			  cancelButtonText: 'ยกเลิก',
	   			  confirmButtonClass: 'uk-button uk-button-primary',
	   			  cancelButtonClass: 'uk-button uk-button-danger',
	   			  buttonsStyling: false
	   			}).then(function (isConfirm){
		   			 if (isConfirm) {
		   				$("#idsub").val(id);
						$("#deletepro").submit();
		   			 }else{
			   			    swal(
			   			      'ยกเลิกการทำรายการแล้ว',
			   			      'ข้อมูลจะไม่มีการเปลี่ยนแปลง)',
			   			      'error'
			   			    )
			   			   
		   			 }
	   			})
		};
		</script>
	
</body>
</html>