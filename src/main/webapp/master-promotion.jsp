<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : promotion</title>
		<link href="css/style-promotion.css"rel="stylesheet">	
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
				<%@include file="nav-top.jsp" %>
				<form id="service" action="addPromotionInsert" method="post" >
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
										<div class="uk-grid">
											<span>ชื่อโปรโมชั่น</span>			
										</div>
										<div class="uk-grid mt-0">
											<div class="uk-width-2-5 uk-form">
												<s:textfield cssClass="uk-width-1-1" required="required" name="protionModel.name" value="" />
											</div>	
										</div>
										<div class="uk-grid">
											
											<div class="uk-width-1-2">
												<span>วันที่</span>
											</div>
														
										</div>
										<div class="uk-grid mt-0">
											<div class="uk-width-2-5 uk-form uk-form-icon">
												<i class="uk-icon-calendar"></i>
												<s:textfield data-uk-datepicker="{format:'YYYY/MM/DD'}" required="required" name="protionModel.start_date" cssClass="uk-width-1-1"  value="" />
											</div>
												<span class="mt-a">ถึง</span>
											<div class="uk-width-2-5 uk-form uk-form-icon">
												<i class="uk-icon-calendar"></i>
												<s:textfield data-uk-datepicker="{format:'YYYY/MM/DD'}" required="required" name="protionModel.end_date" cssClass="uk-width-1-1"  value="" />
											</div>	
										</div>
										<div class="uk-grid">
											<div class="uk-width-1-5 uk-form">
												<s:radio  name="protionModel.use_condition"  list="#{'REGISTER':' สมัครสมาชิก'}" checked="true" />
											</div>
											<div class="uk-width-1-5 uk-form ">
												<s:radio  name="protionModel.use_condition" list="#{'REUSE':' รับสิทธิ์ซ้ำได้'}" />	
											</div>											
											<div class="uk-width-2-5 uk-form ">
												<s:radio  name="protionModel.use_condition" list="#{'ONETIME':' ใช้สิทธิ์ได้ครั้งเดียว'}" />																									
											</div>
										</div>
									</div>
									<div class="uk-width-2-10"></div>
								</div>
							
							<div class="uk-grid">
								<div class="uk-width-2-10"></div>
								<div class="uk-width-6-10 ridge">
								<div class="uk-panel-header">
									<h3 class ="uk-panel-title"> ข้อมูลลูกค้า</h3>
								</div>
									<div class="uk-grid">
										<div class="uk-width-1-2 uk-form">	
											ประเภทลูกค้า
										</div>						
									</div>
									<div class="uk-grid mt-1">
										<div class="uk-width-1-5 uk-form">	
											<s:radio  name="protionModel.is_allsubcontact" list="#{'true':' ทุกประเภท'}" checked="true" />
										</div>
										<div class="uk-width-1-5 uk-form">	
											<s:radio  name="protionModel.is_allsubcontact" list="#{'false':' เลือกประเภท'}"  />
										</div>						
									</div>
									<div class="uk-grid mt-1">
										<div class="uk-width-1-4 uk-form groupcontact hidden">	
											<a href="#add_typepatient" class="uk-button uk-button-primary uk-button-small" data-uk-modal> เพิ่มประเภทลูกค้า</a>
										</div>						
									</div>
									<div class="uk-grid mt-0">
										<div class="uk-width-2-5 uk-form groupcontact hidden">	
											<select class="uk-width-1-1" size=5 id="show_promotionsubcontact" name="show_promotionsubcontact">

											</select>
										</div>						
									</div>
									<div class="uk-grid">
										<div class="uk-width-1-2 uk-form">
											<s:checkbox name="protionModel.is_birthmonth" fieldValue="true" label=" เดือนเกิด" />	
										</div>						
									</div>
									<div class="uk-grid">
										<div class="uk-width-1-2 uk-form ">
											<s:checkbox name="protionModel.is_allage" id="isgroupage" fieldValue="true" label="ช่วงอายุ" />
										</div>						
									</div>
									<div class="uk-grid mt-0 groupage hidden">
											<div class="uk-width-2-5 uk-form ">
												<s:textfield cssClass="uk-width-1-1" name="protionModel.from_age" value=""/>
											</div>
											<span class="mt-a">ถึง</span>
											<div class="uk-width-2-5 uk-form">
												<s:textfield cssClass="uk-width-1-1" name="protionModel.to_age" value=""/>
											</div>	
									</div>
									<div class="uk-grid">
										<div class="uk-width-1-2 uk-form ">
											<s:checkbox name="countmember" id="isgroupcounttime" fieldValue="true" label="จำนวนครั้งที่เข้ารับการรักษา" />	
										</div>						
									</div>
									<div class="uk-grid mt-0 groupcounttime hidden">
										<div class="uk-width-2-5 uk-form">
											<s:textfield  cssClass="uk-width-1-1" name="protionModel.is_treatmentcount" value=""/>
										</div>						
									</div>
								</div>
								</div>
								
								<div class="uk-grid">
								<div class="uk-width-2-10"></div>
								<div class="uk-width-6-10 ridge">
								<div class="uk-panel-header">
									<h3 class ="uk-panel-title">การทำรายการ</h3>
								</div>	
									<div class="uk-grid">
										<div class="uk-width-1-2 uk-form">
											<s:checkbox name="checkmember" id="isgroupbill" fieldValue="true" label="ยอดบิลล์ครบ" />
										</div>						
									</div>
									<div class="uk-grid mt-0 groupbill hidden">
										<div class="uk-width-2-5 uk-form ">
											<s:textfield name="protionModel.billcostover" cssClass=" uk-width-1-1 " value="" />
										</div>
										<div class="uk-width-1-2 mt-a">
											<span>บาท</span>
										</div>							
									</div>
									
									<div class="uk-grid ">
										<div class="uk-width-1-2 uk-form">
											<s:checkbox name="protionModel.is_allday" id="isgroupday" fieldValue="true" label="ภายในวัน/เวลา" />	
										</div>						
									</div>
									<div class="uk-grid mt-1 groupday hidden">
										<div class="uk-width-1-5 uk-form ">
											<s:checkbox  name="protionModel.ismonday"  fieldValue="true" label=" จันทร์" />
										</div>
										<div class="uk-width-1-5 uk-form">
											<s:checkbox name="protionModel.istuesday" fieldValue="true" label=" อังคาร" />
										</div>
										<div class="uk-width-1-5 uk-form">
											<s:checkbox name="protionModel.iswendesday" fieldValue="true" label=" พุธ" />
										</div>
										<div class="uk-width-1-5 uk-form">
											<s:checkbox name="protionModel.isthursday" fieldValue="true" label=" พฤหัส" />
										</div>						
									</div>
									<div class="uk-grid mt-1 groupday hidden">
										<div class="uk-width-1-5 uk-form">
											<s:checkbox name="protionModel.isfriday" fieldValue="true" label=" ศุกร์" />
										</div>
										<div class="uk-width-1-5 uk-form">
											<s:checkbox name="protionModel.issaturday" fieldValue="true" label=" เสาร์" />
										</div>
										<div class="uk-width-1-5 uk-form">
											<s:checkbox name="protionModel.issunday" fieldValue="true" label=" อาทิตย์" />
										</div>						
									</div>
									<div class="uk-grid mt-1">
										<div class="uk-width-1-5 uk-form">	
											<s:radio  name="protionModel.is_alltime"  list="#{'true':' ทั้งวัน'}" checked="true" />
										</div>
										<div class="uk-width-1-5 uk-form">	
											<s:radio  name="protionModel.is_alltime"  list="#{'false':' เลือกเวลา'}" />
										</div>						
									</div>
									<div class="uk-grid mt-1 grouptime hidden">
										<div class="uk-width-1-2 ">	
											<span>เริ่ม</span>
										</div>					
									</div>
									<div class="uk-grid mt-0 grouptime hidden">
											<div class="uk-width-2-5 uk-form uk-form-icon clockpicker pull-center" data-placement="right" data-align="top" data-autoclose="true">
												<i class="uk-icon-clock-o"></i>
												<s:textfield cssClass="uk-width-1-1 " name="protionModel.start_time" value="00:00" />
											</div>
											<span class="mt-a">ถึง</span>
											<div class="uk-width-2-5 uk-form uk-form-icon clockpicker pull-center" data-placement="right" data-align="top" data-autoclose="true">
												<i class="uk-icon-clock-o"></i>
												<s:textfield  cssClass="uk-width-1-1 "  name="protionModel.end_time" value="00:00"  />
											</div>	
									</div>
								</div>
								</div>
								
								<div class="uk-grid">
								<div class="uk-width-2-10"></div>
								<div class="uk-width-6-10 ridge">
									<div class="uk-panel-header">
									<h3 class ="uk-panel-title"> สาขา</h3>
									</div>
									<div class="uk-grid mt-1">
										<div class="uk-width-1-5 uk-form">	
											<s:radio  name="protionModel.is_allbranch"  list="#{'true':' ทุกสาขา'}" checked="true" />
										</div>
										<div class="uk-width-1-5 uk-form ">	
											<s:radio  name="protionModel.is_allbranch"  list="#{'false':' เลือกสาขา'}"  />
										</div>						
									</div>
									<div class="uk-grid mt-1 groupbranch hidden">
										<div class="uk-width-1-4 uk-form">	
											<a href="#add_typebranch" class="uk-button uk-button-primary uk-button-small" data-uk-modal> เพิ่มสาขา</a>
										</div>					
									</div>
									<div class="uk-grid mt-0 groupbranch hidden">
										<div class="uk-width-2-5 uk-form">	
											<select class="uk-width-1-1" size=5 id="show_promotionbranch" name="show_promotionbranch">
											</select>
										</div>						
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
		                        <s:reset cssClass=" uk-button uk-button-danger" value="cancel" />
		                    </div>
		                </div>    
	                    </div>	
			</div>
			<div id="add_typepatient" class="uk-modal ">
						    <div class="uk-modal-dialog  uk-form " >
						        <a class="uk-modal-close uk-close"></a>
						         <div class="uk-modal-header"><i class="uk-icon-user-md"></i> ประเภทลูกค้า</div>
						         	<div class="uk-width-1-1 uk-overflow-container">	         		
											<table id = "tablechoose_typepatient" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
											    <thead>
											        <tr class="hd-table"> 
											        	<th class="uk-text-center">เลือก</th>
											            <th class="uk-text-center">ชื่อประเภทลูกค้า</th> 
											        
											        </tr>
											    </thead> 
											    <tbody>
											    	<s:iterator value="promotionsubcontactModel">
													    	<tr>
													    		<td class="uk-text-center "><s:checkbox name="protionModel.sub_contact_id" fieldValue="%{sub_contact_id}"  theme="simple"  /></td>
													    		<td class="uk-text-center promotionsubcontact_name"> <s:property  value="sub_contact_name" /></td>
													    	</tr>
										    		</s:iterator>
												</tbody>
											</table>
									</div>
			         <div class="uk-modal-footer uk-text-right">
			         	<button class="uk-button uk-button-success uk-modal-close" >ตกลง</button>
			         </div>

			    </div>
			</div>
			<div id="add_typebranch" class="uk-modal ">
			    <div class="uk-modal-dialog  uk-form " >
			        <a class="uk-modal-close uk-close"></a>
			         <div class="uk-modal-header"><i class="uk-icon-user-md"></i> สาขา</div>
			         	<div class="uk-width-1-1 uk-overflow-container">
			        
			         		
							<table id = "tablechoose_branch" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
							    <thead>
							        <tr class="hd-table"> 
							        	<th class="uk-text-center">เลือก</th>
							            <th class="uk-text-center">ชื่อสาขา</th> 
				
							        
							        </tr>
							    </thead> 
							    <tbody>
							    	<s:iterator value="branchmodel">
									    	<tr>
									    		<td class="uk-text-center "><s:checkbox name="protionModel.promotion_branch_id" fieldValue="%{branch_id}"  theme="simple"  /></td>
									    		<td class="uk-text-center promotionbranch_name"><s:property  value="branch_name" /></td>
									    	</tr>
						    		</s:iterator>
							    
								</tbody>
							</table>
					</div>
			         <div class="uk-modal-footer uk-text-right">
			         	<button class="uk-button uk-button-success uk-modal-close" type="submit">ตกลง</button>
			         </div>
			    </div>
			</div>			
			</form>
	</div>
</div>
			
	<script>
		$('.clockpicker').clockpicker();
		$(document).on("change","input[name='protionModel.sub_contact_id']",function(){
			
			var index = $("input[name='protionModel.sub_contact_id']").index(this);
			var promotionsubcontact_name = $(".promotionsubcontact_name:eq("+index+")").text();
			if(this.checked){
				$("select[name='show_promotionsubcontact']").append($('<option>').text(promotionsubcontact_name).attr('value', $(this).val()));
			}else{
				
				$("select[name='show_promotionsubcontact'] option[value='"+$(this).val()+"']").remove();
			}
			
		}).on("change","input[name='protionModel.promotion_branch_id']",function(){
			
			var index = $("input[name='protionModel.promotion_branch_id']").index(this);
			var promotionbranch_name = $(".promotionbranch_name:eq("+index+")").text();
			if(this.checked){
				$("select[name='show_promotionbranch']").append($('<option>').text(promotionbranch_name).attr('value', $(this).val()));
			}else{
				
				$("select[name='show_promotionbranch'] option[value='"+$(this).val()+"']").remove();
			}
			
		}).on("change","input[name='protionModel.is_allbranch']",function(){
	    	
	    	$(".groupbranch").toggle();
	    	
		}).on("change","input[name='protionModel.is_allsubcontact']",function(){
	    	
	    	$(".groupcontact").toggle();
	    	
		}).on("change","input[name='protionModel.is_alltime']",function(){
	    	
	    	$(".grouptime").toggle();
	    	
		}).ready(function(){
		    $('#isgroupday').click(function(){
		        $(".groupday").toggle(this.checked);
		        $(".groupday .uk-form ").find("input").attr('checked',false);
			});
		    $('#isgroupage').click(function(){
		        $(".groupage").toggle(this.checked);
		        $(".groupage .uk-form ").find("input").val('');
			});
		    $('#isgroupcounttime').click(function(){
		        $(".groupcounttime").toggle(this.checked);
		        $(".groupcounttime .uk-form ").find("input").val('');
			});
		    $('#isgroupbill').click(function(){
		        $(".groupbill").toggle(this.checked);
		        $(".groupbill .uk-form ").find("input").val('');
			});

		    $('#tablechoose_branch').DataTable();
		    $('#tablechoose_typepatient').DataTable();

		});   
		
		
		
	</script>				
<div class="swal2-container"><div class="swal2-overlay" tabindex="-1"></div><div class="swal2-modal" style="display: none" tabindex="-1"><div class="swal2-icon swal2-error"><span class="x-mark"><span class="line left"></span><span class="line right"></span></span></div><div class="swal2-icon swal2-question">?</div><div class="swal2-icon swal2-warning">!</div><div class="swal2-icon swal2-info">i</div><div class="swal2-icon swal2-success"><span class="line tip"></span> <span class="line long"></span><div class="placeholder"></div> <div class="fix"></div></div><img class="swal2-image"><h2></h2><div class="swal2-content"></div><input class="swal2-input"><select class="swal2-select"></select><div class="swal2-radio"></div><label for="swal2-checkbox" class="swal2-checkbox"><input type="checkbox" id="swal2-checkbox"></label><textarea class="swal2-textarea"></textarea><div class="swal2-validationerror"></div><hr class="swal2-spacer"><button class="swal2-confirm">OK</button><button class="swal2-cancel">Cancel</button><span class="swal2-close">×</span></div></div>
	</body>
</html>