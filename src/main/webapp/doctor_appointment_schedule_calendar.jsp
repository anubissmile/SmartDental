<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
    	<meta charset="UTF-8">
		<title>Smart Dental:นัดหมาย</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head>
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div> 
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<h2>ตารางงานนายแพทย์ Wesarut Khm</h2>
				<!-- Action error & messages -->
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

				<hr>	 
				<div class="calendar-conf">	 	 
					<div class="uk-grid" >
						<div class="uk-width-4-4">
							<div id='calendar' data-doctor='<s:property value="docModel.DoctorID" />'></div>
						</div>
					</div>
				 </div>
			</div>
		</div> 

	
	<script>
		$(document).ready(function(){
			console.log('hello');
			$( ".m-setting" ).addClass( "uk-active" );
			$("#savecalendar").attr("disabled", "true");
			
			var obj; 
			var defView = {view: 'month'} //Define view type;
			$.ajax({
				url: 'ajax-doctor-appointment-calendar',
				type: 'POST',
				dataType: 'json',
				async:false,
				data: {
					'appointmentModel.doctorID': 1,
					'appointmentModel.branchID': 'CMI'
				},
				success: function(result){
					console.log("success");
					obj = JSON.parse(JSON.stringify(result));
					console.log(obj);	
				}
			});
		    //////////////////////////////////////////select event calendar
		
			$('#calendar').fullCalendar({
				header: {
					left: 'prev,next,today', // prev,next
					center: 'title',
					right: 'month, agendaWeek, agendaDay'  // month,agendaWeek,
				},
				editable: true,
				scrollTime: '08:00:00',
				defaultView: defView.view, 
				slotDuration: '00:05:00', 
				minTime: '08:00',
				maxTime: '21:00',
				nowIndicator : true,
				eventStartEditable :  false,
				allDaySlot: false,
			//	editable: true,
			//	theme: true,
			//	contentHeight:570,
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
				selectable: true,		 
				dayClick: function(date, jsEvent, view) {
				   defView.view = $("#calendar").fullCalendar('getView').name;
				   $('#calendar').fullCalendar('changeView', 'agendaDay');
				   $('#calendar').fullCalendar('gotoDate', date);
				},
				select: function(start, stop, jsEvent){
					console.log($("#calendar").fullCalendar('getView'));
					if(defView.view == 'agendaDay'){
						UIkit.modal('#addSchedule').show();
						$('#modalStartTime').val(start.format());
						$('#modalEndTime').val(stop.format());
						$('#modalHeaderAddSchedule').html('วันที่ ' + start.format('DD MMMM YYYY'));
						$('#startTimeLabel').html(start.format('HH:mm' + ' น.'));
						$('#endTimeLabel').html(stop.format('HH:mm' + ' น.'));
					}
				   	defView.view = $("#calendar").fullCalendar('getView').name;
				},	
				eventClick: function(calEvent, jsEvent, view) {
					UIkit.modal('#confDeleteSchedule').show();
					$("#mdWorkdayID").val(calEvent.id);
			    }
				    
			});
	}); 
</script>	
	</body>
</html>