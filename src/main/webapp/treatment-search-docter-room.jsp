<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.smict.all.model.*" %>
<%@ page import="com.smict.treatment.data.*" %>
<!DOCTYPE html>
<html>
	<head> 
		<link href="css/uikit.gradient.css"rel="stylesheet"/>
		<link href="css/jquery.dataTables.min.css"rel="stylesheet">
		<link href="css/style.css"rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/components/form-advanced.gradient.min.css">
		<link rel="stylesheet" type="text/css" href="css/sweetalert2.min.css">
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body> 
		<div class="uk-grid uk-grid-small">
			<div class="uk-width-1-1 uk-form"> 
					<div class="uk-panel uk-panel-box uk-width-medium-1-1">
                                <div class="uk-panel-badge uk-badge uk-badge-danger" onclick="closeWin()">Close</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-medkit"></i> เลือก ห้อง</h3>
								</div> 
								<s:form id="treatmentP" action="treatmentProcess" method="post">
								<div class="uk-width-1-1 uk-overflow-container uk-form">
					         	 <div class="uk-grid uk-grid-collapse">
									<div class="uk-width-1-1 ">
									<s:hidden id="room_id" name="servicePatModel.room_id" /><s:hidden id="doctor_id" name="servicePatModel.doctor_id" />
									<table class="display nowrap compact stripe hover cell-border order-column" id="table_treatment">
									    <thead>
									        <tr class="hd-table"> 
									        	<th class="uk-text-center">เลือก</th>
									            <th class="uk-text-center">ห้อง</th>  
									            <th class="uk-text-center">ผู้ใช้งาน</th> 
									            <th class="uk-text-center">สถานะห้อง</th>  
									        </tr>
									    </thead> 
									    <tbody>
									    		<% 
									    			TreatmentData treatmentDB = new TreatmentData(); 
									    			List<ServicePatientModel> tmList = treatmentDB.select_doctor_room(null);
										    		for(ServicePatientModel tmModel : tmList){  
										    		 
										    	%> 
									    	<tr>   
									    		<td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" name="r11" onclick="getElementById('room_id').value='<%=tmModel.getRoom_id()%>',
	                                            			 getElementById('doctor_id').value='<%=tmModel.getDoctor_id()%>';onSave();">
		                                      			</div>
		                                      		</td>
									    		<td class="uk-text-center"><%=tmModel.getRoom_id()%> - <%=tmModel.getRoom_name()%></td> 
		                                      	<td class="uk-text-center"><%=tmModel.getDoctor_id()%> - <%=tmModel.getDoctor_name()%></td>
		                                      	<td class="uk-text-center"><%=tmModel.getRoom_status()%></td>
											</tr> 
											<% } %>
											 
										</tbody>
									</table>
									</div>
									</div>
								</div> 
							</s:form>
                     </div> 
			</div> 
		</div>	 
		
		<script src="js/jquery-2.2.4.min.js"></script>
		<script src="js/uikit.min.js"></script> 
		<script src="js/jquery.dataTables.min.js"></script> 
		<script src="js/sweetalert2.min.js"></script>
		<!-- js ในการทำรูปภาพ <script type="text/javascript" src="js/html2canvas.js"></script> --> 
		<script> 
			
			
			$(document).ready(function() {
				 $('#table_treatment').DataTable({
			    	// "scrollX": true,
			    	// scrollY:        '50vh',
			        // scrollCollapse: true
			    });
			});
		
			function closeWin() {
				window.close();
			}
			
			function onSave() {	
				
			var	room_id	= $("#room_id").val();
			swal({
  			  title: 'คุณต้องการเลือกห้อง นี้หรือไม่?',
  			  text: "หากต้องการให้กดตกลง!",
  			  type: 'warning',
  			  showCancelButton: true,
  			  confirmButtonColor: '#3085d6',
  			  cancelButtonColor: '#d33',
  			  confirmButtonText: 'ตกลง!',
  			  cancelButtonText: 'ยกเลิก',
  			}).then(function(isConfirm) {
  			  if (isConfirm) {
			 
					swal({
						title: 'สำเร็จ!',
						text: 'ได้ทำการเลือกห้องเรียบร้อยแล้ว.',
						timer: 2000
					}
					 
					);  
					
					$.ajax({
				        type: "post",
				        url: "ajax/ajax-treatment-process.jsp", //this is my servlet 
				        data: {room_id:room_id,status:"P"},
				        async:false, 
				        success: function(result){
				        	var timerId = setInterval(function() { 
				        		
								window.onunload = refreshParent;
						        function refreshParent() { 
						            window.opener.location.reload();
						        } 
						        window.close();
								
								clearInterval(timerId);
							}, 2000);
					    } 
				     }); 
					
	    		 }else{
	    				  swal(
			    			'ไม่ได้ทำการเลือกห้อง!',
			    			'กรุณาทำการเลือกห้องอีกครั้ง',
			    			'error'
			    		);  
	    			  }
	    		})
			} 
		</script>
	</body>
</html>