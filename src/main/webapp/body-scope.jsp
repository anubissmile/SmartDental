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
	</head>
	<body> 	
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-scope-top.jsp" %>
				<script type="text/javascript" src="js/webcam.min.js"></script>
				<form action="UpdateScopeDentist-<s:property value="scopeModel.position_id" />" id="chk" method="post">
					<div class="uk-grid uk-grid-collapse padding5 border-gray uk-panel uk-panel-box bg-gray">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">list</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> เลือก Scope การรักษา</h3> 
								</div>
									<s:if test="hasActionErrors()"> 
								         <div class="uk-alert uk-alert-danger" data-uk-alert> 
								           <li class="uk-alert-close uk-close"></li> 
								             <s:actionerror/> 
								         </div> 
								     </s:if>
								<div class="uk-grid uk-grid-collapse uk-form">
											<div class="uk-width-1-1 uk-panel-header  ">
													<h3 class="uk-width-1-1 uk-panel-title"><i class="uk-icon-th-list"></i> การรักษา</h3>
												</div>
									<div class="uk-width-3-3  "><br>											
											<div class="uk-width-1-1 uk-overflow-container">
												<table class="uk-table uk-table-condensed uk-table-hover  uk-table-striped uk-table-condensed border-gray" id="tbscope">
													<thead>
														<tr class="hd-table">
															<!-- <th class="uk-text-center">เลือก</th> -->
															<th class="uk-text-center uk-width-1-5"><input type="checkbox" id="selectAll" class="uk-form " style="background-color: white"  />
          													 เลือก</th>
															<th class="uk-text-center  uk-width-2-5">รหัสการรักษา</th>
															<th class="uk-text-center  uk-width-2-5">ชื่อการรักษา</th>
														</tr>
													</thead>
													<tbody>
													<s:iterator value="positionTreatmentList" >
															<tr>															
															<s:if test="isCheck == 'nu' ">
																<th class="uk-text-center "><s:checkbox name="test"  cssClass="call-checkbox" fieldValue="%{positontreatmentCode}"  theme="simple"  /></th>
															</s:if>
															<s:else>
																<th class="uk-text-center "><s:checkbox name="test" checked="checked" cssClass="call-checkbox" fieldValue="%{positontreatmentCode}"  theme="simple"  /></th>
															</s:else>
																<th class="uk-text-center"><s:property value="positontreatmentCode"/></th>
																<th class="uk-text-center"><s:property value="treatment_nameth"/></th>
															</tr>
														</s:iterator>

													</tbody>
												</table>
												
											</div>
									</div>
										<div class="uk-width-1-1 uk-text-center ">
											<a href="#addTreatment" class="uk-button uk-form uk-button-success uk-button-large" id="saveScope" data-uk-modal>บันทึก</a>
										</div>
								</div>
							</div>
							
						</div>
								
					</div>			
				</form>
			</div>
				<div id="addTreatment" class="uk-modal ">
						<form action="insertScopeDentist-<s:property value="scopeModel.position_id" />" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
					    <div class="uk-modal-header"><i class="uk-icon-pencil"></i> ยืนยันการทำการ</div>
				         	<div class="uk-modal-body">
				         		<div class="uk-grid">
				         			<div class="uk-width-1-1  "><h3 class="uk-text-warning">ต้องการยืนยันการทำการหรือไม่!</h3></div>
				         		</div>				         		
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">
				         		<input type="text" class="hidden" name="testadd" id="hidden" />
				         	 	<button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-default uk-modal-close uk-button-danger">ยกเลิก</button>
			                   
                			</div>

					    </div>
					    </form>
					</div>
	</div>		
</body>
<script>
	$(document).on('click', '#del_BN_SD', fn_buttonmodal_habndler).ready(function(){



		
	}).ready(function(){
		/* $("#tbscope").dataTable(); */

			    var oTable = $('#tbscope').dataTable({            

			        aLengthMenu: [
			                      [ 100,-1],
			                      [100,"All" ]
			                  ],
			                  iDisplayLength: 100
			    });
			    $('body').on('click', '#selectAll', function () {


			        if ($(this).hasClass('allChecked')) {
			            $('input[class="call-checkbox"]', allPages).prop('checked', false);
			        } else {
			            $('input[class="call-checkbox"]', allPages).prop('checked', true);
			        }
			        $(this).toggleClass('allChecked');
			    })
    			var allPages = oTable.fnGetNodes();

    			$("#saveScope").click(function() {
    				var checkbox_value ="";
/*     				swal({
  		   			  title: 'อนุมัติการทำงาน',
  		   			  text: "กดอนุมัติเมื่อท่านต้องการอนุมัติ",
  		   			  type: 'warning',
  		   			  showCancelButton: true,
  		   			  confirmButtonColor: '#3085d6',
  		   			  cancelButtonColor: '#d33',
  		   			  confirmButtonText: 'อนุมัติ',
  		   			  cancelButtonText: 'ยกเลิก',
  		   			  confirmButtonClass: 'uk-button uk-button-primary',
  		   			  cancelButtonClass: 'uk-button uk-button-danger',
  		   			  buttonsStyling: false
  		   			}).then(function (isConfirm) {
  			   			 if (isConfirm) { */
/*   			   			 $.ajax({
	        			        type: "POST",
	        			        url: "ajax/ajax-delete-scopetreat.jsp?rand=" + Math.random(),
	        			        data: {	        			        	
	        			        	position_id:<s:property value="scopeModel.position_id"/>
	        			        },
	        			        async:false,
	        	            success: function(result){
	        	        	var obj = JSON.parse(result);
	        	             if(obj.status = 'success'){

	        	            	 }
	        	             }
	        			        
	        			     }) */
  			   				 
  			   				 var rowcollection =  oTable.$(".call-checkbox:checked", {"page": "all"}); 			                 	
  			                   rowcollection.each(function(index,elem){
  			    					 checkbox_value += $(elem).val()+",";
  			                 }); 	
  			                 $("#hidden").val(checkbox_value);  
/*   			    	   				 $.ajax({
  			        			        type: "POST",
  			        			        url: "ajax/ajax-insert-scopetreat.jsp?rand=" + Math.random(),
  			        			        data: {
  			        			        	checkbox_value:checkbox_value,
  			        			        	position_id:<s:property value="scopeModel.position_id"/>
  			        			        },
  			        			        async:true,
  			        	            success: function(result){
  			        	        	var obj = JSON.parse(result);
  			        	             if(obj.status = 'success'){
  			        	            	 swal(
  	  			                                 'การอนุมัติสำเร็จ',
  	  			                                 'คลิกตกลงเพื่อทำรายการใหม่',
  	  			                                 'success'
  	  			                               );
  			        	            	 }else{
		  			   		   			    	swal(
		  					  		   			      'ยกเลิกการทำรายการแล้ว',
		  					  		   			      'ข้อมูลจะไม่มีการเปลี่ยนแปลง)',
		  					  		   			      'error'
		  					  		   			    )
  					   			     		}
  			        	             }
  			        			        
  			        			     }) 
  			   					
  			   			 }else{
  			   			    	swal(
  			  		   			      'ยกเลิกการทำรายการแล้ว',
  			  		   			      'ข้อมูลจะไม่มีการเปลี่ยนแปลง)',
  			  		   			      'error'
  			  		   			    )
  			   			     }
  			   			}, function (dismiss) {
  			   			  // dismiss can be 'cancel', 'overlay',
  			   			  // 'close', and 'timer'
  			   			  if (dismiss === 'cancel') {
  			   			    swal(
  			   			      'ยกเลิกการทำรายการแล้ว',
  			   			      'ข้อมูลจะไม่มีการเปลี่ยนแปลง)',
  			   			      'error'
  			   			    )
  			   			  }
  			   			})*/
    				   
    			 	
    				

 
  			   		
    			});


	});
	
		function update(branchname,salary) { 
				 $("#branchname").val(branchname);
				 $("#salary").val(salary);
				  
			}
		function fn_buttonmodal_habndler(e)
		{
		    //get id from pressed button
		    var branchid = $(e.target).data('branchdel');
		    $('#delete_branchMgr').on({
		        'uk.modal.show':function(){
		        	$("#Branchdel").val(branchid);
		        },
		        'uk.modal.hide':function(){
		                    //hide modal
		        }
		    }).trigger('uk.modal.show');
		    $('#update_salary').on({
		        'uk.modal.show':function(){
		        	$("#Branchdel").val(branchid);
		        },
		        'uk.modal.hide':function(){
		                    //hide modal
		        }
		    }).trigger('uk.modal.show');
	}
	
		
</script>		    
</html>