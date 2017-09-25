<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:นัดหมาย</title> 
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body> 
		<% String hong = request.getParameter("hong"); %> 
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<div class="calendar-conf">	 	 
					<div class="uk-grid" >
						<div class="uk-width-1-5 uk-text-center">
							<input type="hidden" id="hong" value="<%=hong%>" />
							<div class="uk-panel uk-panel-box uk-form">
								<h3 class="text-header-room ">เพิ่มนัดหมาย </h3>
								<input class="uk-width-1-1" type="text" id="event" name="event" placeholder="หัวเรื่อง">
								<hr/>
								<div class=" clockpicker pull-center" data-placement="right" data-align="top" data-autoclose="true">
									<input type="text" class="uk-width-1-1" value="" id="time1" name="time1" placeholder="เวลาเริ่ม">
								</div>
								ถึง
								<div class=" clockpicker pull-center" data-placement="right" data-align="top" data-autoclose="true">
									<input type="text" class="uk-width-1-1" value="" id="time2" name="time2" placeholder="เวลาจบ">
								</div>
								<hr/>
								<button id="btn1" class="uk-button uk-button-primary uk-width-1-1">ตกลง</button> 
							</div>
							
						</div>
						<div class="uk-width-3-5">
							<div id='calendar'></div>
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
		<script type="text/javascript">
 
 $(document).ready(function() {
	   
 $('#btn1').click(function () { 	
	 		var event = $('#event').val();
			var time1 = $('#time1').val();
			var time2 = $('#time2').val();
			var hong = $('#hong').val();
			alert(event+" "+time1+" "+time2);
			
			 var letters = '0123456789ABCDEF'.split('');
			    var color = '#';
			    for (var i = 0; i < 6; i++ ) {
			        color += letters[Math.floor(Math.random() * 16)];
			    }
			
			var obj; 
			$.ajax({  // select history
				 
		        type: "post",
		        url: "ajax/ajax-save.jsp", //this is my servlet 
		        data: {event:event,time1:time1,time2:time2,color:color,hong:hong},
		        async:false, 
		        success: function(result){
		        	  
		        	obj = JSON.parse(result); 
			     } 
		     });   
			$('#calendar').fullCalendar( 'removeEvents');
			$('#calendar').fullCalendar( 'addEventSource', obj);
			
			//$('#calendar').fullCalendar( 'removeEvent')
	});  
});

$('.clockpicker').clockpicker()
	.find('input').change(function(){
		console.log(this.value);
	});
var input = $('#single-input').clockpicker({
	placement: 'bottom',
	align: 'left',
	autoclose: true,
	'default': 'now'
});

$('.clockpicker-with-callbacks').clockpicker({
		donetext: 'Done',
		init: function() { 
			console.log("colorpicker initiated");
		},
		beforeShow: function() {
			console.log("before show");
		},
		afterShow: function() {
			console.log("after show");
		},
		beforeHide: function() {
			console.log("before hide");
		},
		afterHide: function() {
			console.log("after hide");
		},
		beforeHourSelect: function() {
			console.log("before hour selected");
		},
		afterHourSelect: function() {
			console.log("after hour selected");
		},
		beforeDone: function() {
			console.log("before done");
		},
		afterDone: function() {
			console.log("after done");
		}
	})
	.find('input').change(function(){
		console.log(this.value);
	});

// Manually toggle to the minutes view
$('#check-minutes').click(function(e){
	// Have to stop propagation here
	e.stopPropagation();
	input.clockpicker('show')
			.clockpicker('toggleView', 'minutes');
});
if (/mobile/i.test(navigator.userAgent)) {
	$('input').prop('readOnly', true);
}
</script>
<script type="text/javascript">
			$(document).ready(function(){
				$( ".m-appoint" ).addClass( "uk-active" );
				$("#savecalendar").attr("disabled", "true");
			
			/* initialize the external events
			-----------------------------------------------------------------*/
			
			$('#external-events .fc-event').each(function() {
	
				// store data so the calendar knows to render an event upon drop
				$(this).data('event', {
					title: $.trim($(this).text()), // use the element's text as the event title
					stick: true // maintain when user navigates (see docs on the renderEvent method)
				});
				 
				// make the event draggable using jQuery UI
				$(this).draggable({
					zIndex: 999,
					revert: true,      // will cause the event to go back to its
					revertDuration: 0  //  original position after the drag
				});
	
			}); 
			
			/* initialize the calendar
			-----------------------------------------------------------------*/
			var hong = $("#hong").val(); 
			//////////////////////////////////////////select event calendar
			var obj; 
			$.ajax({  // select history
				 
		        type: "post",
		        url: "ajax/ajax-calendar.jsp", //this is my servlet 
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
					right: 'agendaWeek,agendaDay'  // month,agendaWeek,
				},
				editable: true,
				scrollTime: '08:00:00',
				defaultView: 'agendaDay', 
				slotDuration: '00:05:00', 
				minTime: '08:00',
				maxTime: '21:00',
				nowIndicator : true,
				editable: true,
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
				droppable: true, // this allows things to be dropped onto the calendar
				drop: function(date, allDay) {
					// is the "remove after drop" checkbox checked?
						//	alert(date);
					if ($('#drop-remove').is(':checked')) {
						// if so, remove the element from the "Draggable Events" list
						$(this).remove();
					}
				}, 
				selectable: true,
				select: function(start, end) {
				 //	var title = prompt('Event');
				//	var start = prompt('Event Date Start:');
				 
					var title; 
					swal({
						  title: 'Input Event',
						  input: 'text',
						  showCancelButton: true
						}).then(function(result) {
							title = result;
							 
							var eventData;
						if (title) {
							eventData = {
								title: title,
								start: start,
								end: end
							};
					 	
							var letters = '0123456789ABCDEF'.split('');
						    var color = '#';
						    for (var i = 0; i < 6; i++ ) {
						        color += letters[Math.floor(Math.random() * 16)];
						    }
						
							var obj; 
							$.ajax({    
								 
						        type: "post",
						        url: "ajax/ajax-save-event.jsp", //this is my servlet 
						        data: {event:eventData.title,time1:eventData.start.format(),time2:eventData.end.format(),color:color,hong:hong},
						        async:false, 
						        success: function(result){
						        	  
						        	obj = JSON.parse(result); 
						        	 
							     } 
						     });  
							
						//	$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
							$('#calendar').fullCalendar( 'removeEvents');
							$('#calendar').fullCalendar( 'addEventSource', obj);
						}
						})
					
					
					$('#calendar').fullCalendar('unselect');
				},
				themeButtonIcons : {
				    prev: 'circle-triangle-w',
				    next: 'circle-triangle-e',
				    prevYear: 'seek-prev',
				    nextYear: 'seek-next'
				},  
				events: obj,  
		 
					    eventClick: function(calEvent, jsEvent, view) {
	 
					     if (confirm("Are you sure about this change?")) {	 
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
					      }
					    },
					    eventDrop: function(event, delta, revertFunc) {
	
					    	alert(event.title + " was dropped on " + event.start.format());
	
					        if (!confirm("Are you sure about this change?")) {
					          revertFunc();
					        }else{
					        	$.ajax({  // update
						            type: "post",
							        url: "ajax/ajax-update.jsp", //this is my servlet 
							        data: {eventid:event.id,start:event.start.format(),end:event.end.format()},
							        async:false, 
							        success: function(result){ 
							        	obj = JSON.parse(result); 
								     } 
							     });
					        }
	
					    },
					    resizable: true, 
					    eventResize: function (event, jsEvent, ui, view) {
					    	alert('RESIZE STOP ' + event.id+ event.title+ event.start.format()+ event.end.format());
					    	$.ajax({  // update
					            type: "post",
						        url: "ajax/ajax-update.jsp", //this is my servlet 
						        data: {eventid:event.id,start:event.start.format(),end:event.end.format()},
						        async:false, 
						        success: function(result){ 
						        	obj = JSON.parse(result); 
							     } 
						     });
					    }
			}); 
		}); 
	</script>
	</body>
</html>