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
	<div class="uk-modal" id="ldc-modal-conf">
	    <div class="uk-modal-dialog uk-modal-dialog-large uk-form" >
	        <a class="uk-modal-close uk-close"></a>
	     	<div class="uk-modal-header">
	         	<h2><i class="uk-icon-product-hunt"></i> คุณต้องการเพิ่มการนัดหมายใช่หรือไม่?</h2>
	 		</div>
	     	<div class="uk-width-1-1 uk-overflow-container">
				div.uk-grid
	     	</div> 
	     	<div class="uk-modal-footer">FOOTER</div>
			<br>
	    </div>
	</div>
	<!-- Model Area -->

	<script>
	
	var pageStat = {
		events: [], 
		users: [],
		calendarInstance: null
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
    				pageStat.events[index].userId = v.length;
    			}else{
    				pageStat.events[index].userId = ind2;
    			}
    		});
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
        	callWeekCalendar(pageStat.calendarInstance);
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
	    overlapEventsSeparate: true,
	    totalEventsWidthPercentInOneColumn : 95,
	    hourLine: true,

        height: function($calendar){
          return $(window).height();//- $('h1').outerHeight(true);
        },
        eventRender : function(calEvent, $event) {
          if (calEvent.end.getTime() < new Date().getTime()) {
            $event.css('backgroundColor', '#aaa');
            $event.find('.wc-time').css({
              backgroundColor: '#999',
              border:'1px solid #888'
            });
          }
        },
        eventNew : function(calEvent, $event, FreeBusyManager, calendar) {
        	console.log('event New')
          	var isFree = true;
          	UIkit.modal('#ldc-modal-question').show();


          	/**
          	 * displayFreeBusys is : false.
          	 */
	        /*  $.each(FreeBusyManager.getFreeBusys(calEvent.start, calEvent.end), function() {
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
          	}*/

          /*alert('You\'ve added a new event. You would capture this event, add the logic for creating a new event with your own fields, data and whatever backend persistence you require.');

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
        	console.log(calEvent);
        	console.log(element);
        	console.log(dayFreeBusyManager);
        	console.log(calendar);
        	console.log(clickEvent);
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
                  free:true
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
			  events: pageStat.events
            });
          }
        },
        // users: ['<a href="">วีศรุต คุ้มวิไล</a>', 'ลมโชย เย็นจริง', 'สมจิตร ค้อนทองคำ', 'จักรวาล ดวงดาว'],
        users: pageStat.users,
        showAsSeparateUser: true,
        displayOddEven: true,
        displayFreeBusys: false,
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

      $('#data_source').change(function() {
        $calendar.weekCalendar('refresh');
        // updateMessage();
      });

      // updateMessage();
    }
	</script>
	</body>
</html>