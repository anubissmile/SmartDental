<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:นัดหมาย</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse ">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div> 
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<!-- Action error & messages -->
				<s:if test="%{#request.alertMSG != null}">
				<div class="uk-alert uk-alert-warning" data-uk-alert>
					<li class="uk-alert-close uk-close"></li>
					<p><s:property value="#request.alertMSG" /></p>
				</div>
				</s:if>
				<s:if test="%{alertError.length() > 0}">
				<div class="uk-alert uk-alert-danger" data-uk-alert>
					<li class="uk-alert-close uk-close"></li>
					<p><s:property value="alertError" /></p>
				</div>
				</s:if>
				<s:if test="%{alertSuccess.length() > 0}">
				<div class="uk-alert uk-alert-success" data-uk-alert>
					<li class="uk-alert-close uk-close"></li>
					<p><s:property value="alertSuccess" /></p>
				</div>
				</s:if>
				<s:if test="%{alertMSG.length() > 0}">
				<div class="uk-alert uk-alert-warning" data-uk-alert>
					<li class="uk-alert-close uk-close"></li>
					<p><s:property value="alertMSG" /></p>
				</div>
				</s:if>
				<s:if test="hasActionErrors()">
				   <div class="uk-alert uk-alert-danger" data-uk-alert>
			   			<li class="uk-alert-close uk-close"></li>
				      	<s:actionerror/>
				   </div>
				</s:if>
				<s:if test="hasActionMessages()">
				   <div class="uk-alert uk-alert-success" data-uk-alert>
			   			<li class="uk-alert-close uk-close"></li>
				      	<s:actionmessage/>
				   </div>
				</s:if>
				<!-- Action error & messages -->

				<s:if test="servicePatModel == null">
					<div class="uk-alert uk-alert-warning" data-uk-alert>
						<li class="uk-alert-close uk-close"></li>
						<p>
							กรุณาเลือกคนไข้ก่อนสร้างการนัดหมาย 
							<a href="selectPatient">
								<button class="uk-button uk-button-success">
									<i class="uk-icon-user"></i> <span>เลือกคนไข้</span>
								</button>
							</a>
						</p>
					</div>
				</s:if>
				<s:else>
				<div class="uk-grid">
					<div class="uk-width-1-1 uk-margin-large"></div>
					<div class="uk-width-1-10"></div>
					<div class="uk-width-8-10">
						<div class="uk-grid">
							<!-- Header -->
							<div class="uk-width-1-1 uk-margin-large">
								<h1 class="uk-margin-medium-left">เลือกช่วงเวลา</h1>
							</div>
							<!-- Header -->
							<!-- Content -->
							<div class="uk-width-1-1">
								<s:form class="uk-form" action="appointment-make" method="post" theme="simple">
									<div class="uk-grid">
										<div class="uk-width-1-3">
											<h3>วันที่</h3>
											<s:textfield type="text" 
												class="uk-form-width-large uk-form-large" 
												placeholder="วันที่" 
												data-uk-datepicker="{format: 'YYYY-MM-DD'}"
												name="appointmentModel.date"
											/>
										</div>
										<div class="uk-width-1-3">
											<h3>เวลาจาก</h3>
											<s:textfield type="text" 
												class="uk-form-width-large uk-form-large t-picker" 
												id="t-start"
												placeholder="เวลาจาก" 
												data-autoclose="true" 
												data-placement="left" 
												data-align="top"
												name="appointmentModel.dateStart"
											/>
										</div>
										<div class="uk-width-1-3">
											<h3>ถึงเวลา</h3>
											<s:textfield type="text" 
												class="uk-form-width-large uk-form-large t-picker" 
												id="t-end"
												placeholder="ถึงเวลา" 
												data-autoclose="true" 
												data-placement="left" 
												data-align="top"
												name="appointmentModel.dateEnd"
											/>
										</div>
										<div class="uk-width-1-1 uk-margin-medium-top">
											<h3>คำอธิบาย</h3>
											<s:textfield type="text" 
												class="uk-width-1-1 uk-form-large" 
												placeholder="คำอธิบาย" 
												name="appointmentModel.description"
											/>
										</div>
										<div class="uk-width-1-1 uk-text-right uk-margin-medium-top">
											<button class="uk-button uk-button-success">เพิ่ม</button>
											<a href="appointment2"><button class="uk-button">ออก</button></a>
										</div>
									</div>
									<s:hidden name="appointmentModel.branchID" />
									<s:hidden name="appointmentModel.branchCode" />
									<s:hidden name="appointmentModel.doctorID" />
								</s:form>
							</div>
							<!-- Content -->
						</div>
					</div>
					<div class="uk-width-1-10"></div>
					<div class="uk-width-1-1 uk-margin-large"></div>
				</div>
				</s:else>
			</div>
		</div> 

	<script src="js/components/grid.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#t-start").clockpicker();
			$("#t-end").clockpicker();

			$( ".m-appoint" ).addClass("uk-active");
			$("#savecalendar").attr("disabled", "true");

		 
		var obj; 
		$.ajax({  // select history
			 
	        type: "post",
	        url: "ajax/ajax-calendar-month.jsp", //this is my servlet 
	       // data: {projectCode:project_code},
	        async:false, 
	        success: function(result){
	        
	        obj = JSON.parse(result); 
	        
		     } 
	     }); 
	    //////////////////////////////////////////select event calendar
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next,today', // prev,next 
				center: 'title',
				right: 'month'  // month,agendaWeek,
			},
			editable: true,
			scrollTime: '08:00:00',
			defaultView: 'month', 
			slotDuration: '00:05:00', 
			minTime: '08:00',
			maxTime: '21:00',
			nowIndicator : true,
			eventStartEditable :  false,
		//	editable: true,
		//	theme: true,
		//	contentHeight:570,
			selectable: true,
			eventLimit: true, // allow "more" link when too many events 
			lang : "th",
			slotEventOverlap : false,
			businessHours:
		    { 
		       start: '12:00',
		       end:   '13:00',
		       dow: [1,2,3,4]
		    },
			 
			events: obj,
			
			 
			dayClick: function(date, jsEvent, view) {
			   $('#calendar').fullCalendar('changeView', 'agendaDay');
			   $('#calendar').fullCalendar('gotoDate', date);
			},
			eventClick: function(calEvent, jsEvent, view) {
				  
			/*	swal({
					  title: 'โปรดเลือก',
					  input: 'radio',
					  inputOptions: {
					    '1': 'ย้ายห้อง event',
					    '2': 'ลบ event' 
					  },
					  inputValidator: function(result) {
					    return new Promise(function(resolve, reject) {
					      if (result) {
					        resolve();
					      } else {
					        reject('โปรดระบุความต้องการ!');
					      }
					    });
					  }
					}).then(function(result) {
					  if (result=='1') {
						  swal({
			    			  title: 'โปรดใส่เลขห้อง',
			    			  input: 'text',
			    			  showCancelButton: true,
			    			  inputValidator: function(value) {
			    			    return new Promise(function(resolve, reject) {
			    			      if (value) {
			    			        resolve();
			    			      } else {
			    			        reject('โปรดระบุ ห้อง ที่จะทำการย้าย!');
			    			      }
			    			    });
			    			  }
			    			}).then(function(result) {
			    			  if (result) {
			    				  
			    				  $.ajax({    
										 
								        type: "post",
								        url: "ajax/ajax-update-hong.jsp", //this is my servlet 
								        data: {eventid:calEvent.id,hong:result},
								        async:false, 
								        success: function(result){
								        	  
								        obj = JSON.parse(result); 
								        	 
									     } 
								     });
			    				
			    				$('#calendar').fullCalendar( 'removeEvents');
								$('#calendar').fullCalendar( 'addEventSource', obj);
								
								swal({
				    			      type: 'success',
				    			      html: 'คุณได้ทำการเปลี่ยนเป็นห้องที่ ' +result
				    			   });
			    			  }
			    			})
					  }else if(result=='2'){
						  swal({
			    			  title: 'คุณต้องการลบหรือไม่?',
			    			  text: "เมื่อลบแล้ว ข้อมูล event จะหายไปทันที!",
			    			  type: 'warning',
			    			  showCancelButton: true,
			    			  confirmButtonColor: '#3085d6',
			    			  cancelButtonColor: '#d33',
			    			  confirmButtonText: 'ต้องการลบ!',
			    			  cancelButtonText: 'ยกเลิก',
			    			}).then(function(isConfirm) {
			    			  if (isConfirm) {
			    				  
			    				  $.ajax({  // select history
										 
								        type: "post",
								        url: "ajax/ajax-delete.jsp", //this is my servlet 
								        data: {eventid:calEvent.id},
								        async:false, 
								        success: function(result){ 
								        	obj = JSON.parse(result); 
									     } 
								     });   
							                  
							      	$('#calendar').fullCalendar('removeEvents', calEvent._id); 
							      	
				    			    swal(
				    			      'ลบแล้ว!',
				    			      'ข้อมูล event ได้ถูกลบแล้ว.',
				    			      'success'
				    			    );
			    			  }else{
			    				  swal(
					    			'ข้อมูลไม่ถูกลบ!',
					    			'ข้อมูล event ยังไม่ถูกลบ.',
					    			'error'
					    		);  
			    			  }
			    			})
					  }
				})
				
			*/	
				var	frmHTML = '<div class="uk-grid">'+ 
	  			'<div class="uk-width-1-1">'+ 
			 	  	'<form class="uk-form">'+ 
            				'<input type="radio" id="radio1" name="radio2" value="1" checked=""> <label> ย้ายห้อง event </label>'+ 
            				'<input type="radio" id="radio2" name="radio2" value="2"> <label> ลบ event </label>'+
            				'<input type="radio" id="radio2" name="radio2" value="3"> <label> Confirm นัด </label>'+
            		'</form>'+
					  	'</div>'+  
			  '	</div>';
				
				swal({
					title:'โปรดเลือก',
					  html: frmHTML,
				      showCancelButton: true,
				      confirmButtonColor: '#3085d6',
				      cancelButtonColor: '#d33',
				      confirmButtonText: 'ตกลง',
				      cancelButtonText: 'ยกเลิก',
				      confirmButtonClass: 'confirm-class',
				      cancelButtonClass: 'cancel-class',
				      closeOnConfirm: false,
				      closeOnCancel: false
					}).then(function(isConfirm) {
					  if (isConfirm) {
						  swal.disableButtons();
				    	var radio = $('input[name=radio2]:checked', '.uk-form').val();
				    	//sweetAlert(radio+'Oops...','Something went wrong!','error'); 
				    	var obj;
				    	if(radio=='1'){
				    		swal({
				    			  title: 'โปรดใส่เลขห้อง',
				    			  input: 'text',
				    			  showCancelButton: true,
				    			  inputValidator: function(value) {
				    			    return new Promise(function(resolve, reject) {
				    			      if (value) {
				    			        resolve();
				    			      } else {
				    			        reject('โปรดระบุ ห้อง ที่จะทำการย้าย!');
				    			      }
				    			    });
				    			  }
				    			}).then(function(result) {
				    			  if (result) {
				    				  
				    				  $.ajax({    
											 
									        type: "post",
									        url: "ajax/ajax-update-hong.jsp", //this is my servlet 
									        data: {eventid:calEvent.id,hong:result},
									        async:false, 
									        success: function(result){
									        	  
									        obj = JSON.parse(result); 
									        	 
										     } 
									     });
				    				
				    				$('#calendar').fullCalendar( 'removeEvents');
									$('#calendar').fullCalendar( 'addEventSource', obj);
									
									swal({
					    			      type: 'success',
					    			      html: 'คุณได้ทำการเปลี่ยนเป็นห้องที่ ' +result
					    			   });
				    			  }
				    			})
				    	}else if(radio=='2'){
				    		swal({
				    			  title: 'คุณต้องการลบหรือไม่?',
				    			  text: "เมื่อลบแล้ว ข้อมูล event จะหายไปทันที!",
				    			  type: 'warning',
				    			  showCancelButton: true,
				    			  confirmButtonColor: '#3085d6',
				    			  cancelButtonColor: '#d33',
				    			  confirmButtonText: 'ต้องการลบ!',
				    			  cancelButtonText: 'ยกเลิก',
				    			}).then(function(isConfirm) {
				    			  if (isConfirm) {
				    				  
				    				  $.ajax({  // select history
											 
									        type: "post",
									        url: "ajax/ajax-delete.jsp", //this is my servlet 
									        data: {eventid:calEvent.id},
									        async:false, 
									        success: function(result){ 
									        	obj = JSON.parse(result); 
										     } 
									     });   
								                  
								      	$('#calendar').fullCalendar('removeEvents', calEvent._id); 
								      	
					    			    swal(
					    			      'ลบแล้ว!',
					    			      'ข้อมูล event ได้ถูกลบแล้ว.',
					    			      'success'
					    			    );
				    			  }else{
				    				  swal(
						    			'ข้อมูลไม่ถูกลบ!',
						    			'ข้อมูล event ยังไม่ถูกลบ.',
						    			'error'
						    		);  
				    			  }
				    			})
				    	}else if(radio=='3'){
				    		var dateS = calEvent.start.format().substring(8, 10)+"-"+calEvent.start.format().substring(5, 7)+"-"+calEvent.start.format().substring(0, 4); 
				    		var timeS = calEvent.start.format().substring(12, 19);
				    		var endS = calEvent.end.format().substring(12, 19);
				    		var name = calEvent.title;
				    		var res = calEvent.title.split(" ");
				    		var name = res[0];
				    		var room = res[3]; 
				    		var customer = res[4]+res[5]+" "+res[6];
				    		var status = res[7];
				    		var	frmHTML1 = '<div class="uk-grid uk-grid-small">'+ 
					  			'<div class="uk-width-1-2 uk-text-right">'+ 
					  				'แพทย์ :'+ 
					  			'</div>'+ 
					  			'<div class="uk-width-1-2 uk-text-left">'+ 
				  					'พิชิต จิตสุนทรี'+ 
				  				'</div>'+ 
				  			'</div>'+ 
				  			'<div class="uk-grid uk-grid-small">'+ 
					  			'<div class="uk-width-1-2 uk-text-right">'+ 
					  				'วันที่ :'+ 
					  			'</div>'+ 
					  			'<div class="uk-width-1-2 uk-text-left">'+ 
				  					dateS+ 
				  				'</div>'+ 
		  					'</div>'+
					  		'<div class="uk-grid uk-grid-small">'+ 
					  			'<div class="uk-width-1-2 uk-text-right">'+ 
					  				'เวลา :'+ 
					  			'</div>'+ 
					  			'<div class="uk-width-1-2 uk-text-left">'+ 
				  					timeS+' ถึง '+endS+ 
				  				'</div>'+ 
			  				'</div>'+ 
			  				'<div class="uk-grid uk-grid-small">'+ 
					  			'<div class="uk-width-1-2 uk-text-right">'+ 
					  				'การรักษา :'+ 
					  			'</div>'+ 
					  			'<div class="uk-width-1-2 uk-text-left">'+ 
					  				name+ 
				  				'</div>'+ 
		  					'</div>'+
			  					'<div class="uk-grid uk-grid-small">'+ 
					  			'<div class="uk-width-1-2 uk-text-right">'+ 
					  				'ชื่อคนไข้ :'+ 
					  			'</div>'+ 
					  			'<div class="uk-width-1-2 uk-text-left">'+ 
					  				customer+ 
				  				'</div>'+ 
	  						'</div>'+
		  					'<div class="uk-grid uk-grid-small">'+ 
					  			'<div class="uk-width-1-2 uk-text-right">'+ 
					  				'ห้อง :'+ 
					  			'</div>'+ 
					  			'<div class="uk-width-1-2 uk-text-left">'+ 
					  				room+ 
				  				'</div>'+ 
	  						'</div>'+
		  					'<div class="uk-grid uk-grid-small">'+ 
					  			'<div class="uk-width-1-2 uk-text-right">'+ 
					  				'สถานะปัจจุบัน :'+ 
					  			'</div>'+ 
					  			'<div class="uk-width-1-2 uk-text-left">'+ 
					  				status+ 
				  				'</div>'+ 
	  						'</div>'+
				  			'<div class="uk-grid">'+ 
				  			'<div class="uk-width-1-1">'+ 
						 	  	'<form class="uk-form">'+ 
			            				'<input type="radio" id="cradio1" name="cradio2" value="1" checked=""> <label> ยืนยันการนัดหมาย </label>'+ 
			            				'<input type="radio" id="cradio2" name="cradio2" value="2"> <label> เลือนนัด </label>'+
			            				'<input type="radio" id="cradio2" name="cradio2" value="3"> <label> ติดต่อไม่สำเร็จ </label>'+
			            		'</form>'+
								  	'</div>'+  
						  '	</div>';
				    		
						  
						  
				    		swal({
				    			   
				    			  title:'โปรดเลือก สถานะการนัดหมาย',
								  html: frmHTML1,
							      showCancelButton: true,
							      confirmButtonColor: '#3085d6',
							      cancelButtonColor: '#d33',
							      confirmButtonText: 'ตกลง',
							      cancelButtonText: 'ยกเลิก',
							      confirmButtonClass: 'confirm-class',
							      cancelButtonClass: 'cancel-class',
							      closeOnConfirm: false,
							      closeOnCancel: false
				    			  
				    		}).then(function(isConfirm) {
								  if (isConfirm) {
									  swal.disableButtons();
							    	var cradio = $('input[name=cradio2]:checked', '.uk-form').val();
							    	//sweetAlert(radio+'Oops...','Something went wrong!','error'); 
							    	var obj;
							    	
							    	if(cradio=='1'){
							    		swal({
							    			title: 'ยืนยันการนัดหมายหรือไม่?',
							    			  text: "หากต้องการยืนยันการนัดหมายให้กดตกลง!",
							    			  type: 'warning',
							    			  showCancelButton: true,
							    			  confirmButtonColor: '#3085d6',
							    			  cancelButtonColor: '#d33',
							    			  confirmButtonText: 'ตกลง!',
							    			  cancelButtonText: 'ยกเลิก',
							    			}).then(function(isConfirm) {
							    			  if (isConfirm) {
							    				  
							    				  $.ajax({    
														 
												        type: "post",
												        url: "ajax/ajax-update-confirm.jsp", //this is my servlet 
												        data: {eventid:calEvent.id,cradio:cradio},
												        async:false, 
												        success: function(result){
												        	  
												        obj = JSON.parse(result); 
												        	 
													     } 
												     });
							    				
							    				$('#calendar').fullCalendar( 'removeEvents');
												$('#calendar').fullCalendar( 'addEventSource', obj);
												
												swal({
								    			      type: 'success',
								    			      html: 'ได้ทำการยืนยันการนัดหมายแล้ว' +result
								    			   });
							    			  }
							    			})
							    	}else if(cradio=='2'){
							    		swal({
							    			title: 'ยืนยันการเลื่อนนัดหมายหรือไม่?',
							    			  text: "หากต้องการยืนยันการเลื่อนนัดหมายให้กดตกลง!",
							    			  type: 'warning',
							    			  showCancelButton: true,
							    			  confirmButtonColor: '#3085d6',
							    			  cancelButtonColor: '#d33',
							    			  confirmButtonText: 'ตกลง!',
							    			  cancelButtonText: 'ยกเลิก',
							    			}).then(function(isConfirm) {
							    			  if (isConfirm) {
							    				  
							    				  $.ajax({    
														 
												        type: "post",
												        url: "ajax/ajax-update-confirm.jsp", //this is my servlet 
												        data: {eventid:calEvent.id,cradio:cradio},
												        async:false, 
												        success: function(result){
												        	  
												        obj = JSON.parse(result); 
												        	 
													     } 
												     });
							    				
							    				$('#calendar').fullCalendar( 'removeEvents');
												$('#calendar').fullCalendar( 'addEventSource', obj);
												
												swal({
								    			      type: 'success',
								    			      html: 'ได้ทำการยืนยันการเลื่อนนัดหมายแล้ว' +result
								    			   });
							    			  }
							    			})
							    	}else if(cradio=='3'){
							    		swal({
							    			title: 'ยืนยัน ว่าติดต่อไม่สำเร็จหรือไม่?',
							    			  text: "หากต้องการยืนยัน ว่าติดต่อไม่สำเร็จให้กดตกลง!",
							    			  type: 'warning',
							    			  showCancelButton: true,
							    			  confirmButtonColor: '#3085d6',
							    			  cancelButtonColor: '#d33',
							    			  confirmButtonText: 'ตกลง!',
							    			  cancelButtonText: 'ยกเลิก',
							    			}).then(function(isConfirm) {
							    			  if (isConfirm) {
							    				  
							    				  $.ajax({    
														 
												        type: "post",
												        url: "ajax/ajax-update-confirm.jsp", //this is my servlet 
												        data: {eventid:calEvent.id,cradio:cradio},
												        async:false, 
												        success: function(result){
												        	  
												        obj = JSON.parse(result); 
												        	 
													     } 
												     });
							    				
							    				$('#calendar').fullCalendar( 'removeEvents');
												$('#calendar').fullCalendar( 'addEventSource', obj);
												
												swal({
								    			      type: 'success',
								    			      html: 'ได้ทำการยืนยัน ว่าติดต่อไม่สำเร็จแล้ว' +result
								    			   });
							    			  }
							    			})
							    	}
								  }
				    		}) 		  
				    				  
				    	} //if else 1
				    	
					     
					  }  
					})   
					 
			 } 
			    
		});
	}); 
</script>	
	</body>
</html>