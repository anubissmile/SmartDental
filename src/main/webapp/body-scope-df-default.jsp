<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scope</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
<style>
	div.dataTables_filter { display: none !important; }
</style>	
	</head>	
	<body> 	
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-scope-top.jsp" %>
				<script type="text/javascript" src="js/webcam.min.js"></script>
				<form action="UpdateScopeLineDFdefault-<s:property value="scopeModel.position_id" />" id="chk" method="post">
					<div class="uk-grid uk-grid-collapse padding5 border-gray uk-panel uk-panel-box bg-gray">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">list</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i>  DF Scope พื้นฐาน 
								    	
								    </h3> 
								</div>
									<s:if test="hasActionErrors()"> 
								         <div class="uk-alert uk-alert-danger" data-uk-alert> 
								           <li class="uk-alert-close uk-close"></li> 
								             <s:actionerror/> 
								         </div> 
								     </s:if>
								<div class="uk-grid uk-grid-collapse uk-form">
											<div class="uk-width-1-1 uk-panel-header uk-grid uk-grid-collapse  uk-panel-title ">
													<h3 class="uk-width-1-3 "><i class="uk-icon-th-list"></i> จัดการค่า DF Scope พื้นฐาน
													
													</h3>
													<h3 class="uk-width-1-3 ">
														<button type="button" id="saveallset" class="uk-button uk-form uk-button-success "  >บันทึกการตั้งต่า DF พื้นฐาน</button>
													</h3>
												</div>
									<div class="uk-width-3-3 uk-grid uk-grid-collapse uk-form "><br>											
											<div class="uk-width-8-10 uk-overflow-container">
												<table  class="uk-table uk-table-condensed uk-table-hover  uk-table-striped  border-gray" id="tbscope">
													<thead>
														<tr class="">
															<td class="uk-text-center "><input type="text" class="selectsearch" placeholder="Search รหัสการรักษา" ></td>
															<td class="uk-text-center "><input type="text" class="selectsearch" placeholder="Search กลุ่มการรักษา" ></td>
								 							<td class="uk-text-center "><input type="text" class="selectsearch" placeholder="Search หมวดการรักษา" ></td>
								 							<td class="uk-text-center"></td>
								 							<td class="uk-text-center"></td>
								 							<td class="uk-text-center"></td>
														</tr>
														<tr class="hd-table">
															<th class="uk-text-center ">รหัส-ชื่อการรักษา</th>
															<th class="uk-text-center ">กลุ่มการรักษา</th>
															<th class="uk-text-center ">หมวดการรักษา</th>
															<th class="uk-text-center">DF(%)</th>
								 							<th class="uk-text-center">DF(Baht)</th>
								 							<th class="uk-text-center">LAB(%)</th>
														</tr>
													</thead>
													<tbody>
													<s:iterator value="positionTreatmentList" >
															
															<s:if test="isCheck != 'nu' ">
															<tr>															
																<th class="uk-text-center"><s:property value="positontreatmentCode"/>-<s:property value="treatment_nameth"/>
																<input type="hidden" name="docpositiontreatmentallid" value="<s:property value="doctor_position_treatmentID"/>" >
																</th>
																<th class="uk-text-center"><s:property value="groupCode"/></th>
																<th class="uk-text-center"><s:property value="catCode"/></th>
																<th class="uk-text-center"><input type="text"  name="df_percent" 
																value="<s:property value="dfpercent"/>" class="uk-width-1-1 discountPercent 
																uk-text-center dfp_cat<s:property value="catid"/> 
																dfp_grp<s:property value="groupid"/> numeric" /></th>
																<th class="uk-text-center"><input type="text"  name="df_baht" 
																value="<s:property value="dfbaht"/>" class="uk-width-1-1  
																uk-text-center dfb_cat<s:property value="catid"/>
																dfb_grp<s:property value="groupid"/> numeric" /></th>
																<th class="uk-text-center"><input type="text"  name="price_lab" value="<s:property value="dflap"/>" 
																class="uk-width-1-1 discountPercent uk-text-center labb_cat<s:property value="catid"/>
																labb_grp<s:property value="groupid"/> numeric" /></th>
															</tr>
															</s:if>
														</s:iterator>

													</tbody>
												</table>
												
											</div>
											<div class="uk-width-2-10 padding5 border-gray uk-panel uk-panel-box  ">
												<h3 class="uk-width-1-1">
													จัดการค่า DF
												</h3><hr>
																								
													<div class="uk-grid uk-grid-small">
					                            		<div class="uk-width-1-1">  
						                             		<label class="hd-text ">กลุ่มการรักษา</label>
						                             	</div>
						                             </div>
						                         	<div class="uk-grid uk-grid-small">
							                            <div class="uk-width-1-1"> 
								                             <ul class="uk-form uk-list chanel-pay padding5 border-gray selectGroup">
										                       <s:iterator value="listgrouptreatment" >
																<li>																
																			<label><s:checkbox name="checkbox_group"  cssClass="groupCheck" 
																			fieldValue="%{treatG_id}"  theme="simple"  />
																			<s:property value="treatG_code"/>-<s:property value="treatG_name"/>
																			</label>
																</li>																
																</s:iterator>      
										                     </ul>
										                </div>
										            </div>
										                
										            <div class="uk-grid uk-grid-small">
							                        	<div class="uk-width-1-1">  
						                             		<label class="hd-text ">หมวดการรักษา</label>
						                             	</div>
						                            </div>
						                         	<div class="uk-grid uk-grid-small">
							                            <div class="uk-width-1-1"> 
							                            	<ul class="uk-form uk-list chanel-pay padding5 border-gray selectCategory">
							                            		<s:iterator value="listcategoryScope" >
																<li>																
																			<label><s:checkbox name="checkbox_category"  
																			cssClass="check_category%{categoryID}" 
																			fieldValue="%{categoryID}"  theme="simple"  />
																			<s:property value="categoryCode"/>-<s:property value="categoryName"/>
																			</label>
																</li>																
																</s:iterator> 	
							                            	</ul> 
										                </div>
										            </div>
										<div class="uk-grid uk-grid-small">
			                            	<div class="uk-width-1-1">  
		                             			<label class="hd-text uk-text-success">กรอกรายละเอียด</label>
		                             		</div>
		                             	</div> 
		                             	<div class="uk-grid uk-grid-small">
			                            	<div class="uk-width-1-1"> 
								                <div class="uk-form uk-list chanel-pay padding5 border-gray uk-text-right">
						                            <div class="uk-grid uk-grid-small">
						                            	<div class="uk-width-4-10 uk-text-right">  
						                            		DF (%) 
						 								</div>
						 								<div class="uk-width-6-10 uk-text-left">  
						                            		<input type="text"  size="10" id="df_p" placeholder="0" class="uk-form-small discountPercent uk-text-right numeric">
						 								</div>
						 							</div>
						                            <div class="uk-grid uk-grid-small">
						                            	<div class="uk-width-4-10 uk-text-right">  
						                            		DF (Baht)
						 								</div>
						 								<div class="uk-width-6-10 uk-text-left">  
						                            		<input type="text" size="10" id="df_b" placeholder="0" class="uk-form-small uk-text-right numeric">
						 								</div>
						 							</div>
						 							<div class="uk-grid uk-grid-small">
						                            	<div class="uk-width-4-10 uk-text-right">  
						                            		LAB (%)
						 								</div>
						 								<div class="uk-width-6-10 uk-text-left">  
						                            		<input type="text" size="10" id="lab_b" placeholder="0" class="uk-form-small discountPercent uk-text-right numeric">
						 								</div>
						 							</div>

						                        </div> 
				                        	</div>
		                             	</div>
						                 
	    	 							<div class="uk-grid uk-grid-small ">
			                            	<div class="uk-width-1-1"> 
			                        			<button type=button id="add" class="uk-text-center uk-button uk-button-success" ><i class="uk-icon-copy"></i> กรอกข้อมูล</button> 
                             				</div>
						                </div>
											</div>
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
	$(document).ready(function(){
		$(".numeric").autoNumeric('init');
	      var oTable = $('#tbscope').DataTable({
	    	  "lengthChange": false,
	    	  aLengthMenu: [
		                      [-1],
		                      ["All" ]
		                  ]
	      });
	 $("#tbscope thead input").on( 'keyup change', function () {
		 oTable
	             .column( $(this).parent().index()+':visible' )
	             .search( this.value )
	             .draw();
	 });
	 $('#add').click(function() {		 
			$('.selectsearch').val('');
			oTable
			.search( '' )
			 .columns().search( '' )
			 .draw();

			var df_p = $("#df_p").autoNumeric('get');
			var df_b = $("#df_b").autoNumeric('get');
			var lab_b = $("#lab_b").autoNumeric('get');
			  
		    $('input[name="checkbox_group"]:checked').each(function() {
		    	var val_dfp_grp = ".dfp_grp";
		    	var val_dfb_grp = ".dfb_grp";
		    	var val_labb_grp = ".labb_grp";
		    	// group
		    	val_dfp_grp = val_dfp_grp+$(this).val(); 
		    	val_dfb_grp = val_dfb_grp+$(this).val();
		    	val_labb_grp = val_labb_grp+$(this).val();
		    	 
		    	$(val_dfp_grp).autoNumeric('set', df_p);  
		    	$(val_dfb_grp).autoNumeric('set', df_b);
		    	$(val_labb_grp).autoNumeric('set', lab_b);
		    	// group 
		    }); 
			$('input[name="checkbox_category"]:checked').each(function() {
				var val_dfp_cat = ".dfp_cat";
				var val_dfb_cat = ".dfb_cat";
				var val_labb_cat = ".labb_cat";
			  	// category
			  	val_dfp_cat = val_dfp_cat+$(this).val(); 
			  	val_dfb_cat = val_dfb_cat+$(this).val();
			  	val_labb_cat = val_labb_cat+$(this).val();
			  	 
			  	$(val_dfp_cat).autoNumeric('set', df_p);  
		    	$(val_dfb_cat).autoNumeric('set', df_b);
		    	$(val_labb_cat).autoNumeric('set', lab_b);
			  	// category 
			 }); 
		});			
		$('#saveallset').click(function () {
			$('.selectsearch').val('');
			oTable
			.search( '' )
			 .columns().search( '' )
			 .draw();
			swal({
	   			  title: 'อนุมัติการทำงาน',
	   			  text: "ท่านต้องการยืนยันการเปลื่ยนแปลงหรือไม่!",  		   				  			  
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
		 
		   				$('#chk').submit();
		   			 }else{
			   			    swal(
			   			      'ยกเลิกการทำรายการแล้ว',
			   			      'ข้อมูลจะไม่มีการเปลี่ยนแปลง)',
			   			      'error'
			   			    )
			   			   
		   			 }
	   			})
		});
		
		
		


	}).on("click","input[name='checkbox_group']",function(){
		var checkcate = $(this).val();
		if($(this).is(':checked')){
			$.ajax({  //   
			    type: "post",
			    url: "ajax_json_groupcheck", //this is my servlet group
			    data: {group_id:checkcate},
			    async:false, 
			    success: function(result){ 
				    if (result != '') {	
				    	$.each(result, function(i, value) { 
				    		$(".check_category"+value.cat_id).prop('checked',true);
				    	});          
				    	
				    }
			    }
			});
		}else{
			$.ajax({  //   
			    type: "post",
			    url: "ajax_json_groupcheck", //this is my servlet group
			    data: {group_id:checkcate},
			    async:false, 
			    success: function(result){ 
				    if (result != '') {	
				    	$.each(result, function(i, value) { 
				    		$(".check_category"+value.cat_id).prop('checked',false);
				    	});          
				    	
				    }
			    }
			});
		}


	});

		$(document).on("keyup",".discountPercent",function(){
			if($(this).autoNumeric('get')>101){
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