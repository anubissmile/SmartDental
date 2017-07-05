<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*" %>
<%@ page import="com.smict.treatment.data.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% 	DecimalFormat df = new DecimalFormat("#,###,##0.## ฿");
	DecimalFormat df1 = new DecimalFormat("#,###,##0.## เม็ด");
	DecimalFormat df2 = new DecimalFormat("#,###,##0.##");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Smart Dental:DF</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				
				<form action="doctorDF" method="post" >
				<div class="uk-grid uk-grid-collapse uk-form">
				    <div class="uk-width-7-10 ">
				    	<h4 class="hd-text uk-text-primary margin5">รายการ DF</h4>
				    	<div class=" uk-panel-box">
				    	<div class="uk-overflow-container">
				    		<div class="uk-grid uk-grid-small">
                            	<div class="uk-width-1-2"> 
			    					<label class="hd-text uk-text-primary">รายการหมอ</label>
			    				</div> 
                            	<div class="uk-width-1-2">
			    					<label class="hd-text uk-text-primary">รายการสาขา</label>
			    				</div>
                             </div>
				    		<div class="uk-grid uk-grid-small">
                             		<div class="uk-width-1-2">
                             			<select id="mapDoctor" name="doctor_id" class="uk-width-1-1" >
											<option  value="">Select an option</option>
										</select>
                             		</div> 
                             		<div class="uk-width-1-2">
                             			<select id="mapBranch" name="branch_id" class="uk-width-1-1" > 
										</select>
                             		</div>
                             </div>
                             <div class="uk-grid uk-child-width-expand@s uk-text-center " uk-grid> 
										<div class="uk-width-1-1"> 
											<table id="table1" style="width:100%" class="uk-table uk-table-hover border-gray uk-table-small uk-width-1-1">
											 <thead> 
											<tr class="hd-table">   
					 							<th class="uk-text-center">รหัส-ชื่อ การรักษา</th>  
					 							<th class="uk-text-center">DF(%)</th>
					 							<th class="uk-text-center">DF(Baht)</th>
					 							<th class="uk-text-center">LAB(%)</th> 
				 							</tr> 
				 							</thead>
				 							<tbody class="selectTreatment"><!-- ajax --></tbody>
				 							</table> 
	 									</div>
							 </div>  
					  		<div class="uk-grid uk-grid-small">
	                       		<div class="uk-width-1-1">  
	                       			<label class="hd-text uk-text-danger">สาขา</label>
	                       		</div>
				            </div>
		                   	<div class="uk-grid uk-grid-small">
		                        	<div class="uk-width-1-1"> 
		                          	<ul class="uk-form uk-list chanel-pay padding5 border-gray selectBranch">
		                            	<!-- ajax -->
		                       		</ul>
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
					
					<div class="uk-width-3-10 uk-overflow-container">
						<h4 class="hd-text uk-text-primary margin5">จัดการค่า DF</h4>
						  
                             <div class="uk-panel uk-panel-box uk-panel-box">
                             	 <div class=" padding5">
	                             	<div class="uk-overflow-container">
							    		<div class="uk-grid uk-grid-small">
			                            	<div class="uk-width-1-1">  
		                             			<label class="hd-text uk-text-success">กลุ่มการรักษา</label>
		                             		</div>
		                             	</div>
		                         		<div class="uk-grid uk-grid-small">
			                            	<div class="uk-width-1-1"> 
				                             	<ul class="uk-form uk-list chanel-pay padding5 border-gray selectGroup">
						                             <!-- ajax -->
						                        </ul>
						                     </div>
						                </div>
						                
						                <div class="uk-grid uk-grid-small">
			                            	<div class="uk-width-1-1">  
		                             			<label class="hd-text uk-text-success">หมวดการรักษา</label>
		                             		</div>
		                             	</div>
		                         		<div class="uk-grid uk-grid-small">
			                            	<div class="uk-width-1-1"> 
			                            		<ul class="uk-form uk-list chanel-pay padding5 border-gray selectCategory">
			                            			 <!-- ajax -->
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
						                 
	    	 							<div class="uk-grid uk-grid-small">
			                            	<div class="uk-width-1-1"> 
			                        			<button type=button id="add" class="uk-button uk-button-success" ><i class="uk-icon-copy"></i> กรอกข้อมูล</button> 
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
	
	$( ".m-df" ).addClass( "uk-active" );
	$(".numeric").autoNumeric('init');
	var dtTable = $("#table1").DataTable({
        "scrollX": true,
        "paging":   false,
        "ordering": false
    });
	$("#mapDoctor").select2({
		ajax: {
		    url: "ajax_json",
		    delay: 1000,
		    data: function (params) {
		      return {
		        q: params.term, // search term
		        isData: 'd',
		       // isPurchase: 'f',
		        page: params.page
		      };
		    },
		    processResults: function (data, params) { 
		      params.page = params.page || 1;

		      return {
		        results: data.results,
		        pagination: {
		          more: (params.page * 30) < data.total_count
		        }
		      };
		    },
		    cache: true
	  	},
	  	minimumInputLength: 1
  	});
	
$(document).on('change','#mapDoctor', function() {
	clearTreatment(); 
	var optVal= $("#mapDoctor option:selected").val();  
	$("select[name='branch_id'").empty().append('<option selected="selected" value="">โปรดเลือก</option>');
	$.ajax({
        type: "post",
        url: "ajax_json", //this is my servlet 
        data: {isData:'b',doctor_id:optVal},
        async:false, 
        success: function(result){ 
        		var selectBranch = "";
               $.each(result.results, function(i, value) {  
                   $("select[name='branch_id']").append($('<option>').text(value.text).attr('value', value.id));  
                   
                   selectBranch += '<li class="uk-grid"> '+
					'	<div class="uk-width-1-1 branch-all">  '+
					'		<label><input class="branch-'+value.id+'" type="checkbox" name="checkbox_branch" value="'+value.id+'"> '+value.text+'</label> '+
					'	</div>  '+
					'</li>'; 
               });  
               $(".selectBranch").html(selectBranch);
	    } 
     });
})
$(document).on('change','#mapBranch', function() {		 
	   
	var doctor_id = $("#mapDoctor option:selected").val();
	var branch_id = $("#mapBranch option:selected").val(); 
	var brac = ".branch-";
		brac = brac+branch_id;  
		$('input[name="checkbox_branch"]:checked').prop('checked',false).removeAttr('onClick','return false'); 
		$(brac).prop('checked',true).attr('onClick','return false');
		$.ajax({  //   
		    type: "post",
		    url: "ajax_json_treatment", //this is my servlet treatment
		    data: {doctor_id:doctor_id,branch_id:branch_id,type:"main"},
		    async:false, 
		    
		    success: function(result){   
		      
		    		var codeAr = "('"; 
		    		var groupidAr = "";
					if (result != '') {	
						dtTable.clear().draw();
						$.each(result, function(i, value) { 
							dtTable.row.add([
								value.code+'-'+value.name+'<input type="hidden" name="treatment_id" value="'+value.id+'" />',
								'<input type="text"  name="df_percent" value="'+value.df_percent+'" class="uk-width-1-1 discountPercent uk-text-center dfp_cat'+value.category_id+' dfp_grp'+value.group_id+' numeric" />',
								'<input type="text" name="df_baht" value="'+value.df_baht+'" class="uk-width-1-1 uk-text-center dfb_cat'+value.category_id+' dfb_grp'+value.group_id+' numeric" />',
								'<input type="text"  name="price_lab" value="'+value.price_lab+'" class="uk-width-1-1 discountPercent uk-text-center labb_cat'+value.category_id+' labb_grp'+value.group_id+' numeric" />'
							]).draw( false );
							
							codeAr += value.code+"','";
						});
							codeAr += ")"; 
							codeAr = codeAr.replace(",')",")"); 
					}
					$.ajax({  //   
					    type: "post",
					    url: "ajax_json_treatment", //this is my servlet category
					    data: {treatment_code:codeAr,type:"cat"},
					    async:false, 
					    success: function(result){ 
						    if (result != '') {	
						    	var selectCategory = "";
						    	groupidAr += "('";
						    	$.each(result, function(i, value) { 
						    		groupidAr += value.group_id+"','";
						    		
						    		selectCategory += '<li class="uk-grid"> '+
							    					'	<div class="uk-width-1-1 ">  '+
							    					'		<label><input type="checkbox" class="check_category'+value.cat_id+'" name="checkbox_category" value="'+value.cat_id+'"> '+value.cat_name+'</label> '+
							    					'	</div>  '+
							    					'</li>'; 
						    	});          
						    	$(".selectCategory").html(selectCategory); 
						    	groupidAr += ")"; 
						    	groupidAr = groupidAr.replace(",')",")"); 
						    }
					    }
					});
					$.ajax({  //   
					    type: "post",
					    url: "ajax_json_treatment", //this is my servlet group
					    data: {group_id:groupidAr,type:"group"},
					    async:false, 
					    success: function(result){ 
						    if (result != '') {	
						    	var selectGroup = "";
						    	$.each(result, function(i, value) { 
							    	selectGroup += '<li class="uk-grid"> '+
							    					'	<div class="uk-width-1-1">  '+
							    					'		<label><input type="checkbox" name="checkbox_group" value="'+value.grp_id+'"> '+value.grp_name+'</label> '+
							    					'	</div>  '+
							    					'</li>'; 
						    	});          
						    	$(".selectGroup").html(selectGroup);  
						    }
					    }
					});
		    	}  
		}); 
		$(".numeric").autoNumeric('init');
});
		
function clearTreatment(){ 
	$(".selectTreatment").html(""); 
	$(".selectCategory").html(""); 
	$(".selectGroup").html("");
}
	$(document).on('click','#add', function() {		 
	
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
	
	$(".numeric").autoNumeric('init');
	
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