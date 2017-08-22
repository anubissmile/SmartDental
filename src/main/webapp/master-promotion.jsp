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
				<%@include file="backend-promotion-top.jsp" %>
				<form id="createPro" action="addPromotionInsert" method="post" >
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
												<s:textfield data-uk-datepicker="{format:'DD-MM-YYYY'}" id="dstart" pattern="[0-9]{1,2}-[0-9]{1,2}-[0-9]{1,4}" autocomplete="off"
												required="required" name="protionModel.start_date" cssClass="uk-width-1-1"  value="" />
											</div>
												<span class="mt-a">ถึง</span>
											<div class="uk-width-2-5 uk-form uk-form-icon">
												<i class="uk-icon-calendar"></i>
												<s:textfield data-uk-datepicker="{format:'DD-MM-YYYY'}" id="dend" pattern="[0-9]{1,2}-[0-9]{1,2}-[0-9]{1,4}" autocomplete="off"
												required="required" name="protionModel.end_date" cssClass="uk-width-1-1"  value="" />
											</div>	
										</div>
										<div class="uk-grid">
											<div class="uk-width-1-5 uk-form ">
												<s:radio  name="protionModel.use_condition" list="#{'REUSE':' รับสิทธิ์ซ้ำได้'}" checked="true" />	
											</div>											
											<div class="uk-width-2-5 uk-form ">
												<s:radio  name="protionModel.use_condition" list="#{'ONETIME':' ใช้สิทธิ์ได้ครั้งเดียว'}" />																									
											</div>
										</div>
										<div class="uk-grid">
											<span>คำอธิบายโปรโมชั่น</span>			
										</div>
										<div class="uk-grid mt-0">											
											<div class="uk-width-1-1 uk-form ">
												<s:textarea name="protionModel.promotion_description"  />	
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
											<s:radio  name="protionModel.is_allsubcontact" list="#{'1':' ทุกประเภท'}" checked="true" />
										</div>
										<div class="uk-width-1-5 uk-form">	
											<s:radio  name="protionModel.is_allsubcontact" list="#{'0':' เลือกประเภท'}"  />
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
											<s:checkbox name="protionModel.is_birthmonth" fieldValue="1" label=" เดือนเกิด" />	
										</div>						
									</div>
									<div class="uk-grid">
										<div class="uk-width-1-2 uk-form ">
											<s:checkbox name="protionModel.is_allage" id="isgroupage" fieldValue="0" label="ช่วงอายุ" />
										</div>						
									</div>
									<div class="uk-grid mt-0 groupage hidden">
											<div class="uk-width-2-5 uk-form ">
												<s:textfield cssClass="uk-width-1-1 " pattern="[0-9]" maxLength="2" name="protionModel.from_age" value=""/>
											</div>
											<span class="mt-a">ถึง</span>
											<div class="uk-width-2-5 uk-form">
												<s:textfield cssClass="uk-width-1-1 " pattern="[0-9]" maxLength="2" name="protionModel.to_age" value=""/>
											</div>	
									</div>
									<div class="uk-grid">
										<div class="uk-width-1-2 uk-form ">
											<s:checkbox name="countmember" id="isgroupcounttime" fieldValue="1" label="จำนวนครั้งที่เข้ารับการรักษา" />	
										</div>						
									</div>
									<div class="uk-grid mt-0 groupcounttime hidden">
										<div class="uk-width-2-5 uk-form">
											<s:textfield  cssClass="uk-width-1-1 " pattern="[0-9]" maxLength="4" name="protionModel.is_treatmentcount" value=""/>
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
											<s:checkbox name="checkmember" id="isgroupbill" fieldValue="1" label="ยอดบิลล์ครบ" />
										</div>						
									</div>
									<div class="uk-grid mt-0 groupbill hidden">
										<div class="uk-width-2-5 uk-form ">
											<s:textfield name="protionModel.pro_amountbill" cssClass=" uk-width-1-1 numeric"  />
										</div>
										<div class="uk-width-1-2 mt-a">
											<span>บาท</span>
										</div>							
									</div>
									<div class="uk-grid">
										<div class="uk-width-1-2 uk-form">	
											กำหนดการโปรโมชั่น
										</div>						
									</div>
									<div class="uk-grid mt-1">
										<div class="uk-width-1-5 uk-form">	
											<s:radio  name="protionModel.is_allday" cssClass="isgroupday" list="#{'1':' ทุกวัน'}" checked="true" />
										</div>
										<div class="uk-width-1-5 uk-form">	
											<s:radio  name="protionModel.is_allday"  cssClass="isgroupday" list="#{'0':' เลือกวัน'}" />
										</div>						
									</div>
									<div class="uk-grid mt-1 groupday hidden">
										<div class="uk-width-1-5 uk-form ">
											<label><input type="checkbox" name="protionModel.dayAll" value="1" > จันทร์</label>
										</div>
										<div class="uk-width-1-5 uk-form">
											<label><input type="checkbox" name="protionModel.dayAll" value="2" > อังคาร</label>
										</div>
										<div class="uk-width-1-5 uk-form">
											<label><input type="checkbox" name="protionModel.dayAll" value="3" > พุธ</label>
										</div>
										<div class="uk-width-1-5 uk-form">
											<label><input type="checkbox" name="protionModel.dayAll" value="4" > พฤหัส</label>
										</div>						
									</div>
									<div class="uk-grid mt-1 groupday hidden">
										<div class="uk-width-1-5 uk-form">
											<label><input type="checkbox" name="protionModel.dayAll" value="5" > ศุกร์</label>
										</div>
										<div class="uk-width-1-5 uk-form">
											<label><input type="checkbox" name="protionModel.dayAll" value="6" > เสาร์</label>
										</div>
										<div class="uk-width-1-5 uk-form">
											<label><input type="checkbox" name="protionModel.dayAll" value="7" > อาทิตย์</label>
										</div>						
									</div>
									<div class="uk-grid">
										<div class="uk-width-1-2 uk-form">	
											ช่วงเวลา
										</div>						
									</div>
									<div class="uk-grid mt-1">
										<div class="uk-width-1-5 uk-form">	
											<s:radio  name="protionModel.is_alltime"  list="#{'1':' ทั้งวัน'}" checked="true" />
										</div>
										<div class="uk-width-1-5 uk-form">	
											<s:radio  name="protionModel.is_alltime"  list="#{'0':' เลือกเวลา'}" />
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
												<s:textfield cssClass="uk-width-1-1 sandetime" id="timestart" name="protionModel.start_time" value="00:00" />
											</div>
											<span class="mt-a">ถึง</span>
											<div class="uk-width-2-5 uk-form uk-form-icon clockpicker pull-center" data-placement="right" data-align="top" data-autoclose="true">
												<i class="uk-icon-clock-o"></i>
												<s:textfield  cssClass="uk-width-1-1 sandetime" id="timeend"  name="protionModel.end_time"  value="00:00" />
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
											<s:radio  name="protionModel.is_allbranch" cssClass="branchradio"  list="#{'1':' ทุกสาขา'}" checked="true" />
										</div>
										<div class="uk-width-1-5 uk-form ">	
											<s:radio  name="protionModel.is_allbranch"  cssClass="branchradio" list="#{'0':' เลือกสาขา'}"  />
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
								<button class=" uk-button uk-button-success" type="button" id="allsave" >บันทึก</button>
							</div>
							<div class="uk-form-icon">
		                        <s:a href="getpromotionlist" cssClass=" uk-button uk-button-danger" >ยกเลิก</s:a>
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
													    		<td class="uk-text-center "><s:checkbox cssClass="call-checkbox-con" name="protionModel.sub_contact_id" fieldValue="%{sub_contact_id}"  theme="simple"  /></td>
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
									    		<td class="uk-text-center "><s:checkbox cssClass="call-checkbox-beall" name="protionModel.promotion_branch_id" fieldValue="%{branch_id}"  theme="simple"  /></td>
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
			<div class="hidden sentbranchall">
						
			</div>
			<div class="hidden sentcontypeall">
						
			</div>			
			</form>
	</div>
</div>
<script src="js/autoNumeric.min.js"></script>				
	<script>
		
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
	    	if($(this).val()== 1){
	    	$("select[name='show_promotionbranch'] option").remove();
	    	$("input[name='protionModel.promotion_branch_id']").prop('checked',false)
	    	}
		}).on("change","input[name='protionModel.is_allsubcontact']",function(){
	    	
	    	$(".groupcontact").toggle();
	    	if($(this).val()== 1){
		    	$("select[name='show_promotionsubcontact'] option").remove();
		    	$("input[name='protionModel.sub_contact_id']").prop('checked',false)
		    	}
		}).on("change","input[name='protionModel.is_alltime']",function(){
	    	
	    	$(".grouptime").toggle();
	    	if($(this).val()== 1){
	    		$('.sandetime').val('00:00');
	    	}
	    	
		}).ready(function(){
			$('.clockpicker').clockpicker();
			$(".numeric").autoNumeric('init');
			$('.isgroupday').change(function(){
		        $(".groupday").toggle();
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

		    var tbranch = $('#tablechoose_branch').dataTable();
		    var tcontype = $('#tablechoose_typepatient').dataTable();
			$('#allsave').click(function () {
				var timestart = new Date($('#dstart').val()+" "+$('#timestart').val());
				var timeend = new Date($('#dstart').val()+" "+$('#timeend').val());
				var datetimeend = new Date($('#dend').val()+" "+$('#timeend').val());
				if(timestart.getTime() > timeend.getTime() ){
					swal(
							  'Error!',
							  'ช่วงเวลาเริ่มน้อยกว่าเวลาจบ!',
							  'error'
							)
					return false;
				}
				if(timestart > datetimeend ){
					swal(
							  'Error!',
							  'ช่วงวันเริ่มน้อยกว่าวันจบ!',
							  'error'
							)
					return false;
				}	
				
				var checkbox_value ="";
				var tball =	tbranch.$(".call-checkbox-beall:checked", {"page": "all"}); 	
						tball.each(function(index,elem){
	  						checkbox_value += '<input type="hidden" name="protionModel.probranchID" value="'+$(elem).val()+'" >'	  					
	               		});
						$(".sentbranchall").html(checkbox_value);
				var checkbox_value1 ="";
				var tbcon =	tcontype.$(".call-checkbox-con:checked", {"page": "all"}); 	
						tbcon.each(function(index,elem){
			  				checkbox_value1 += '<input type="hidden" name="protionModel.subConID" value="'+$(elem).val()+'" >'	  					
			            });
					$(".sentcontypeall").html(checkbox_value1);		
				 	 $('#createPro').submit();  
			
			});
		});   
/* 		$(document).on("click",".plancallall",function(){
			
		}); */
		
		
	</script>				
	</body>
</html>