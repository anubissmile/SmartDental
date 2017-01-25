<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:นัดหมาย</title> 
	</head>  
	<body>
		<% String hong = request.getParameter("hong"); %>
		<div class="uk-grid uk-grid-collapse ">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div> 
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
					<ul class="uk-breadcrumb">
						<li><a href="appoint.jsp"><span class="uk-article-lead uk-text-bold">ภาพรวม</span></a></li>
					    <li><a href="appoint-room.jsp?hong=1"><span class="uk-article-lead uk-text-primary">ห้อง 1</span></a></li>
					    <li><a href="appoint-room.jsp?hong=2"><span class="uk-article-lead uk-text-primary">ห้อง 2</span></a></li> 
					    <li><a href="appoint-room.jsp?hong=3"><span class="uk-article-lead uk-text-primary">ห้อง 3</span></a></li>
					    <li><a href="appoint-room.jsp?hong=4"><span class="uk-article-lead uk-text-primary">ห้อง 4</span></a></li>
					    <li><a href="appoint-room.jsp?hong=5"><span class="uk-article-lead uk-text-primary">ห้อง 5</span></a></li>
					    <li><a href="appoint-room.jsp?hong=6"><span class="uk-article-lead uk-text-primary">ห้อง 6</span></a></li> 
					    <li><a href="appoint-room.jsp?hong=7"><span class="uk-article-lead uk-text-primary">ห้อง 7</span></a></li>
					    <li><a href="appoint-room.jsp?hong=8"><span class="uk-article-lead uk-text-primary">ห้อง 8</span></a></li>
					    <li><a href="appoint-room.jsp?hong=9"><span class="uk-article-lead uk-text-primary">ห้อง 9</span></a></li>
					</ul>
				<hr> 
				<div class="calendar-conf">	 	 
					<div class="uk-grid" >
						<div class="uk-width-4-5">
							<div id='calendar'></div>
							<input type="hidden" id="hong" value="<%=hong%>" />
						</div>  
						<div class="uk-width-1-5">
							<h2>ห้องตรวจ</h2>
							<ul class="uk-nav uk-nav-side" >
							    <li><a class="uk-panel uk-panel-box" href="appoint-1.jsp?hong=1">
							    	<h3 class="text-header-room">ห้อง 1</h3> 
							    	ทพ.เศรษฐพงศ์ ธุรพันธ์กิจโชติ<br>
							    	08:00 น. - 21:30 น.
							    </a></li>
							    <li><a class="uk-panel uk-panel-box" href="appoint-1.jsp?hong=2">
							    	<h3 class="text-header-room">ห้อง 2</h3> 
							    	ทพ.เศรษฐพงศ์ ธุรพันธ์กิจโชติ<br>
							    	08:00 น. - 21:30 น.
							    </a></li>
							    <li><a class="uk-panel uk-panel-box" href="appoint-1.jsp?hong=3">
							    	<h3 class="text-header-room">ห้อง 3</h3> 
							    	ทพ.เศรษฐพงศ์ ธุรพันธ์กิจโชติ<br>
							    	08:00 น. - 21:30 น.
							    </a></li>
							    <li><a class="uk-panel uk-panel-box" href="appoint-1.jsp?hong=4">
							    	<h3 class="text-header-room">ห้อง 4</h3> 
							    	ทพ.เศรษฐพงศ์ ธุรพันธ์กิจโชติ<br>
							    	08:00 น. - 21:30 น.
							    </a></li>
							    <li><a class="uk-panel uk-panel-box" href="appoint-1.jsp?hong=5">
							    	<h3 class="text-header-room">ห้อง 5</h3> 
							    	ทพ.เศรษฐพงศ์ ธุรพันธ์กิจโชติ<br>
							    	08:00 น. - 21:30 น.
							    </a></li>
							    <li><a class="uk-panel uk-panel-box" href="appoint-1.jsp?hong=6">
							    	<h3 class="text-header-room">ห้อง 6</h3> 
							    	ทพ.เศรษฐพงศ์ ธุรพันธ์กิจโชติ<br>
							    	08:00 น. - 21:30 น.
							    </a></li>
							    <li><a class="uk-panel uk-panel-box" href="appoint-1.jsp?hong=7">
							    	<h3 class="text-header-room">ห้อง 7</h3> 
							    	ทพ.เศรษฐพงศ์ ธุรพันธ์กิจโชติ<br>
							    	08:00 น. - 21:30 น.
							    </a></li>
							    <li><a class="uk-panel uk-panel-box" href="appoint-1.jsp?hong=8">
							    	<h3 class="text-header-room">ห้อง 8</h3> 
							    	ทพ.เศรษฐพงศ์ ธุรพันธ์กิจโชติ<br>
							    	08:00 น. - 21:30 น.
							    </a></li>
							    <li><a class="uk-panel uk-panel-box" href="appoint-1.jsp?hong=9">
							    	<h3 class="text-header-room">ห้อง 9</h3> 
							    	ทพ.เศรษฐพงศ์ ธุรพันธ์กิจโชติ<br>
							    	08:00 น. - 21:30 น.
							    </a></li>
							</ul>
						</div> 
					</div>
				 </div>
			</div>
		</div> 
<script>
			$(document).ready(function(){
				$( ".m-appoint" ).addClass( "uk-active" );
				$("#savecalendar").attr("disabled", "true");
				
				var hong = $("#hong").val(); 
				var obj; 
				$.ajax({  // select history
					 
			        type: "post",
			        url: "ajax/ajax-calendar-month_room.jsp", //this is my servlet 
			        data: {hong:hong},
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
					eventLimitText : "รายการ",
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
						 
						var	frmHTML = '<div class="uk-grid">'+ 
			  			'<div class="uk-width-1-1">'+ 
					 	  	'<form class="uk-form">'+ 
		            				'<input type="radio" id="radio1" name="radio2" value="1" checked=""> <label> ย้ายห้อง event </label>'+ 
		            				'<input type="radio" id="radio2" name="radio2" value="2"> <label> ลบ event </label>'+
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
						    	}  
							     
							  }  
							})   
							 
					 } 
					    
				}); 
		});
</script>
	</body>
</html>