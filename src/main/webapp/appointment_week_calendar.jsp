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
				<%@include file="nav-top-week-calendar.jsp" %>
				<div class="uk-grid">
					<div class="uk-width-1-1 uk-margin-large"></div>
					<div class="uk-width-1-1">
						<h1 class="uk-margin-medium-left">รายการแพทย์ลงตรวจวันนี้</h1>
					</div>
					<div class="uk-width-1-10"></div>
					<div class="uk-width-1-1">
						<div id="calendar"></div>
					</div>
					<div class="uk-width-1-1 uk-margin-large"></div>
				</div>
			</div>
		</div> 
	<!-- Model Area -->
	<div id="modal-group">
		<div id="ldc-modal-conf" class="uk-modal">
			<div class="uk-modal-dialog uk-modal-dialog-large uk-form">
				<!-- <a class="uk-modal-close uk-close"></a> -->
				<div class="uk-modal-header">
					<h2><i class="uk-icon-info"></i> <strong>โปรดยืนยันการเพิ่มรายการนัดหมาย</strong></h2>
				</div>
				<div class="uk-width-1-1 uk-overflow-container uk-panel">
					<div class="uk-grid uk-margin-remove uk-grid-divider">
						<a class="uk-width-1-2 uk-panel-hover uk-text-center" 
							tabindex="2" 
							id="ldc-modal-confirm">
							<h1>
								<strong><i class="uk-icon-check-circle-o"></i><br><span>เพิ่ม</span></strong>
							</h1>
						</a>
						<a class="uk-width-1-2 uk-panel-hover uk-text-center" 
							tabindex="1" 
							id="ldc-modal-cancel">
							<h1>
								<strong><i class="uk-icon-times-circle-o"></i><br><span>ไม่เพิ่ม</span></strong>
							</h1>
						</a>
					</div>
				</div>
			</div>
		</div>
		<div id="ldc-modal-add-frm" class="uk-modal">
			<div class="uk-modal-dialog uk-modal-dialog-large uk-form">
				<!-- <a class="uk-modal-close uk-close"></a> -->
				<form action="post-add-appointment" method="post" class="uk-form">
					<div class="uk-modal-header">
						<h2><i class="uk-icon-calendar-plus-o"></i> <strong>เพิ่มรายการนัดหมาย</strong></h2>
					</div>
					<div class="uk-width-1-1 uk-overflow-container uk-panel">
						<div class="uk-grid uk-margin-remove">
							<div class="uk-width-1-3 uk-padding-remove">
								<h4 class="uk-margin-remove">วันที่</h4>
								<input type="text" 
									class="uk-form-large uk-form-width-large"
									id="ldc-inp-date">
							</div>
							<div class="uk-width-1-3">
								<h4 class="uk-margin-remove">เวลา</h4>
								<input type="text" 
									class="uk-form-large uk-form-width-large"
									id="ldc-inp-starttime">
							</div>
							<div class="uk-width-1-3">
								<h4 class="uk-margin-remove">ถึง</h4>
								<input type="text" 
									class="uk-form-large uk-form-width-large"
									id="ldc-inp-endtime">
							</div>
							<div class="uk-width-1-1 uk-padding-remove">
								<h4 class="uk-margin-remove">คำอธิบาย</h4>
								<textarea class="uk-form-large" maxlength="100"></textarea>
								<input type="hidden" id="ldc-hid-inp-startdatetime" name="" value="">
								<input type="hidden" id="ldc-hid-inp-enddatetime" name="" value="">
								<input type="hidden" id="ldc-hid-inp-startdatetimezone" name="" value="">
								<input type="hidden" id="ldc-hid-inp-enddatetimezone" name="" value="">
							</div>
						</div>
					</div>
					<div class="uk-modal-footer">
						<div class="uk-grid uk-margin-remove uk-grid-divider">
							<button class="uk-width-1-2 uk-panel-hover uk-text-center" 
								tabindex="2" 
								id="ldc-add-appointment">
								<h1>
									<strong><i class="uk-icon-check-circle-o"></i><br><span>เพิ่มนัดหมาย</span></strong>
								</h1>
							</button>
							<a class="uk-width-1-2 uk-panel-hover uk-text-center" 
								tabindex="1" 
								id="ldc-calcel-add-frm">
								<h1>
									<strong><i class="uk-icon-times-circle-o"></i><br><span>ยกเลิก</span></strong>
								</h1>
							</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Model Area -->

	<script>
	
	/**
	 * Global variables.
	 */
	var pageStat = {
		events: [], 
		users: [],
		userId: [],
		calendarInstance: null,
		calEvent: []
	}

    $(document).ready(function() {

    	$.ajax({
			url: "ajax-get-doctor-appointment",
			type: 'POST',
			dataType: 'json',
			data: {param1: 'value1'},
    	})
    	.done(function(data, xhr, status) {
    		console.log("success");
    		pageStat.events = data;
    		let v = new Array();
    		pageStat.users = new Array();
    		$.each(data, function(index, value) {
    			let i = 0, ind2 = 0;
    			$.each(v, function(ind, val) {
    				if(val == value.doctor){
    					i = 1;
    					ind2 = ind;
    					return
    				}
    			});

    			if(i === 0){
    				v.push(value.doctor);
    				pageStat.events[index].userId = v.length - 1;
    				pageStat.userId[v.length - 1] = value.doctorId;
    			}else{
    				pageStat.events[index].userId = ind2;
    				pageStat.userId[ind2] = value.doctorId;
    			}
    		});
    		console.log("users", v);
    		pageStat.users = JSON.parse(JSON.stringify(v));
    		pageStat.events = JSON.parse(JSON.stringify(pageStat.events));
    		console.log(pageStat.users);
    		console.log(pageStat.events);
    	})
    	.fail(function(data, xhr, status) {
    		console.log("error");
    	})
    	.always(function(data, xhr, status) {
    		console.log("complete");
        	callWeekCalendar();
    	});

    	$("#calendar").on('mousemove', '.wc-cal-event', function(event) {
    		event.preventDefault();
    		console.log("Set draggable");
    		$(this).draggable({axis: 'y', containment: 'parent'});
    	});

    	/**
    	 * Remove event listener
    	 */
    	removeEventListener(
    		function(){
    			UIkit.modal('#ldc-modal-conf').hide();
    		}, 
    		"#ldc-modal-cancel"
		);

    	/**
    	 * Modal add form
    	 */
    	modalAddEventForm(function(){
    		var start = new Date(pageStat.calEvent.start);
    		var end = new Date(pageStat.calEvent.end);
    		$("#ldc-hid-inp-startdatetime").val(start.toString('yyyy-MM-dd HH:mm:ss'));
    		$("#ldc-hid-inp-enddatetime").val(end.toString('yyyy-MM-dd HH:mm:ss'));
    		$("#ldc-hid-inp-startdatetimezone").val(pageStat.calEvent.start);
    		$("#ldc-hid-inp-enddatetimezone").val(pageStat.calEvent.end);
    		$("#ldc-inp-date").val(start.toString('dd/MM/yyyy'));
    		$("#ldc-inp-starttime").val(start.toString('HH:mm:ss'));
    		$("#ldc-inp-endtime").val(end.toString('HH:mm:ss'));

    		removeEventListener(
    			function(){
    				UIkit.modal('#ldc-modal-add-frm').hide();
    			}, 
    			'#ldc-calcel-add-frm'
			);
    	});
    });



    /**
     * Function call week calendar
     */
    var callWeekCalendar = function(){
		var setDate = new Date();
      	pageStat.calendarInstance = $('#calendar').weekCalendar({
	      	date: setDate,
	        timeslotsPerHour: 12,
	        scrollToHourMillis : 0,
		    allowCalEventOverlap: true,
	        use24Hour: true,
		    overlapEventsSeparate: true,
		    totalEventsWidthPercentInOneColumn : 95,
		    hourLine: true,

	        height: function($calendar){
	          return $(window).height();//- $('h1').outerHeight(true);
	        },
	        eventRender : function(calEvent, $event) {
	        	console.log("event render", calEvent);
	        	pageStat.calEvent = calEvent;
	          if (calEvent.end.getTime() < new Date().getTime()) {
	            $event.css('backgroundColor', '#aaa');
	            $event.find('.wc-time').css({
	              backgroundColor: '#999',
	              border:'1px solid #888'
	            });
	          }
	        },
	        eventNew : function(calEvent, $event, FreeBusyManager, calendar) {
	        	console.log('event New');
	        	pageStat.calEvent = calEvent;
	          	var isFree = true;
	          	/*console.log("calEvent", calEvent);
	          	console.log("$event", $event);
	          	console.log("FreeBusyManager", FreeBusyManager);
	          	console.log("calendar", calendar);*/
	          	UIkit.modal("#ldc-modal-conf", {bgclose: false, keyboard: false}).show();

	          	/**
	          	 * displayFreeBusys is : false.
	          	 */
		         /* $.each(FreeBusyManager.getFreeBusys(calEvent.start, calEvent.end), function() {
		            if (
		              this.getStart().getTime() != calEvent.end.getTime()
		              && this.getEnd().getTime() != calEvent.start.getTime()
		              && !this.getOption('free')
		            ){
		              isFree = false;
		              return false;
		            }
		          });

	          	if (!isFree) {
		            alert('looks like you tried to add an event on busy part !');
		            $(calendar).weekCalendar('removeEvent',calEvent.id);
		            return false;
	          	}

	          alert('You\'ve added a new event. You would capture this event, add the logic for creating a new event with your own fields, data and whatever backend persistence you require.');

	          calEvent.id = calEvent.userId +'_'+ calEvent.start.getTime();
	          $(calendar).weekCalendar('updateFreeBusy', {
	            userId: calEvent.userId,
	            start: calEvent.start,
	            end: calEvent.end,
	            free:false
	          });*/
	        },
	        eventClick: function(calEvent, element, dayFreeBusyManager, calendar, clickEvent){
	        	console.log('event click');
	        	pageStat.calEvent = calEvent;
	        	console.log(calEvent);
	        	console.log(element);
	        	console.log(dayFreeBusyManager);
	        	console.log(calendar);
	        	console.log(clickEvent);
	        },
	        draggable: function(calEvent, element) {
	        	console.log("calEvent", calEvent);
	        	pageStat.calEvent = calEvent;
	        	return true;
	        },
	        data: function(start, end, callback) {
	 		  console.log('data');
	          var dataSource = $('#data_source').val();
	          if (dataSource === '1') {
	            callback(eventData1);
	          } else if(dataSource === '2') {
	            callback(eventData2);
	          } else {
	            callback({
	              options: {
	                defaultFreeBusy: {
	                  free:false
	                }
	              },
	              /*events: [
		        	{"id":734,"start":"2017-07-24:08:20:00.0","end":"2017-07-24:10:00:00.0","title":"เวรลงตรวจ","userId":3},
		        	{"id":735,"start":"2017-07-24:13:50:00.0","end":"2017-07-24:15:30:00.0","title":"เวรลงตรวจ","userId":2},
		        	{"id":736,"start":"2017-07-24:13:50:00.0","end":"2017-07-24:15:40:00.0","title":"เวรลงตรวจ","userId":1},
		        	{"id":737,"start":"2017-07-24:15:15:00.0","end":"2017-07-24:20:50:00.0","title":"เวรลงตรวจ","userId":1},
		        	{"id":738,"start":"2017-07-24:16:45:00.0","end":"2017-07-24:19:15:00.0","title":"เวรลงตรวจ","userId":3},
		        	{"id":739,"start":"2017-07-24:17:25:00.0","end":"2017-07-24:18:55:00.0","title":"เวรลงตรวจ","userId":2}
	    		]*/
	    			// freebusy: pageStat.events
				 	events: pageStat.events
	            });                                                                                                                                                                                                                    
	          }
	        },
	        // users: ['<a href="">วีศรุต คุ้มวิไล</a>', 'ลมโชย เย็นจริง', 'สมจิตร ค้อนทองคำ', 'จักรวาล ดวงดาว'],
	        users: pageStat.users,
	        showAsSeparateUser: true,
	        displayOddEven: true,
	        displayFreeBusys: true,
	        daysToShow: 1,
	        switchDisplay: {'1 day': 1, '3 next days': 3, 'work week': 5, 'full week': 7},
	        headerSeparator: ' ',
	        useShortDayNames: true,
	        // I18N
	        firstDayOfWeek: $.datepicker.regional['th'].firstDay,
	        shortDays: $.datepicker.regional['th'].dayNamesShort,
	        longDays: $.datepicker.regional['th'].dayNames,
	        shortMonths: $.datepicker.regional['th'].monthNamesShort,
	        longMonths: $.datepicker.regional['th'].monthNames,
	        dateFormat: 'd F y'

    	});

		console.log(pageStat.calendarInstance);

      $('#data_source').change(function() {
        $calendar.weekCalendar('refresh');
        // updateMessage();
      });

      // updateMessage();
    }

    /**
     * Remove event listener 
     */
    var removeEventListener = function(callBack, elem){
    	$("#modal-group").on('click', elem, function(event) {
    		event.preventDefault();
    		pageStat.calendarInstance.weekCalendar('removeEvent');
    		if(callBack){
	    		callBack();
    		}
    	});
    }

    /**
     * Modal add new event form.
     */
    var modalAddEventForm = function(callBack){
    	$("#modal-group").on('click', '#ldc-modal-confirm', function(event) {
    		event.preventDefault();
    		UIkit.modal('#ldc-modal-add-frm', {bgclose: false, keyboard: false}).show();
    		if(callBack){
    			callBack();
    		}
    	});
    }
	</script>
	</body>
</html>