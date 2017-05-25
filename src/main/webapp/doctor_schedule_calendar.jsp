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
				<h2>ตารางงานนายแพทย์ John Doe</h2>
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

	<!-- UIKIT MODAL Zone -->
	<!-- Add new event modal -->
	<div id="addSchedule" class="uk-modal">
	    <div class="uk-modal-dialog">
	        <a class="uk-modal-close uk-close"></a>
        	<p>
				<form class="uk-form" method="post" action="" class="frm-modal" id="frmAddSchedule">
				    <fieldset>
	        			<legend>
	        				<h1>เพิ่มรายการเวรแพทย์</h1>
	        				<h4 id="modalHeaderAddSchedule"></h4>
        				</legend>
				        <div class="uk-grid uk-grid-collapse">
				        	<div class="uk-width-1-4 uk-text-right uk-padding">
				        		<h2>สาขา : </h2>
				        	</div>
				        	<div class="uk-width-3-4 uk-text-left uk-padding">
								<s:select list="branchMap" 
									headerKey="-1"
									headerValue="รายการสาขา (LDC)"
									name="branchModel.branch_code" 
									class="uk-form-width-large" 
									id="branchModel_branch_code"
								/>
				        	</div>
				        	<div class="uk-width-1-4 uk-text-right uk-padding">
				        		<h2>เวลา เริ่ม : </h2>
				        	</div>
				        	<div class="uk-width-3-4 uk-text-left uk-padding">
				        		<h2 id="startTimeLabel"></h2>
				        		<input type="hidden" id="modalStartTime" disabled class="uk-width-1-1">
				        	</div>
				        	<div class="uk-width-1-4 uk-text-right uk-padding">
				        		<h2>ถึง : </h2>
				        	</div>
				        	<div class="uk-width-3-4 uk-text-left uk-padding">
				        		<h2 id="endTimeLabel"></h2>
				        		<input type="hidden" id="modalEndTime" disabled class="uk-width-1-1">
				        	</div>
				        	<div class="uk-width-1-1 uk-text-right uk-margin-top">
				        		<div class="uk-button uk-button">เพิ่ม</div>
				        		<div class="uk-button uk-button-danger">ยกเลิก</div>
				        	</div>
				        </div>
				    </fieldset>
				</form>
    		</p>
	    </div>
	</div>
	<!-- Add new event modal -->
	<!-- Confirm delete modal -->
	<div id="confDeleteSchedule" class="uk-modal">
	    <div class="uk-modal-dialog">
	        <a class="uk-modal-close uk-close"></a>
	        <h1>Confirm delete.</h1>
        	<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Facere repudiandae ex voluptates ab iusto saepe alias, beatae architecto labore quia unde quae nesciunt ipsum nam a! Asperiores magni soluta corrupti?</p>
	    </div>
	</div>
	<!-- Confirm delete modal -->
	<!-- UIKIT MODAL Zone -->
	<script>
		$(document).ready(function(){
			$( ".m-appoint" ).addClass( "uk-active" );
			$("#savecalendar").attr("disabled", "true");
			
			var obj; 
			var defView = {view: 'month'} //Define view type;
			var docId = $("#calendar").data('doctor');
			$.ajax({  // select history
				
		        type: "post",
		        // url: "ajax/ajax-calendar-month.jsp", //this is my servlet
		        url: "ajax-doctor-schedule-calendar-"+docId, //this is my servlet
		        // data: {projectCode:project_code},
		        async:false, 
		        success: function(result){
		        
		        obj = JSON.parse(JSON.stringify(result)); 
		        
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
			    }
				    
			});
	}); 
</script>	
	</body>
</html>