<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Test Week Calendar Scheduler</title>
	<!-- Week Calendar Scheduler CSS -->
   	<link rel="stylesheet" type="text/css" href="css/weekcalendarscheduler/libs/css/smoothness/jquery-ui-1.8.11.custom.css" />
   	<link rel="stylesheet" type="text/css" href="css/weekcalendarscheduler/jquery.weekcalendar.css" />
   	<link rel="stylesheet" type="text/css" href="css/weekcalendarscheduler/skins/default.css" />
   	<link rel="stylesheet" type="text/css" href="css/weekcalendarscheduler/skins/gcalendar.css" />
	<!-- Week Calendar Scheduler CSS -->

	<!-- Week Calendar Scheduler JS -->
   	<script type="text/javascript" src="js/weekcalendarscheduler/libs/jquery-1.4.4.min.js"></script>
  	<script type="text/javascript" src="js/weekcalendarscheduler/libs/jquery-ui-1.8.11.custom.min.js"></script>
  	<script type="text/javascript" src="js/weekcalendarscheduler/libs/jquery-ui-i18n.js"></script>
  	<script type="text/javascript" src="js/weekcalendarscheduler/libs/date.js"></script>
  	<script type="text/javascript" src="js/weekcalendarscheduler/jquery.weekcalendar.js"></script>
	<!-- Week Calendar Scheduler JS -->


	<script type="text/javascript">
  (function($) {
    var d = new Date();
    d.setDate(d.getDate() - d.getDay());
    var year = d.getFullYear();
    var month = d.getMonth();
    var day = d.getDate();

    var eventData1 = {
      options: {
        timeslotsPerHour: 4,
        timeslotHeight: 20,
        defaultFreeBusy: {free: true}
      },
      events : [
        {'id':1, 'start': new Date(year, month, day, 12), 'end': new Date(year, month, day, 13, 30), 'title': 'Lunch with Mike', userId: 0},
        {'id':2, 'start': new Date(year, month, day, 14), 'end': new Date(year, month, day, 14, 45), 'title': 'Dev Meeting', userId: 1},
        {'id':3, 'start': new Date(year, month, day+1, 18), 'end': new Date(year, month, day+1, 18, 45), 'title': 'Hair cut', userId: 1},
        {'id':4, 'start': new Date(year, month, day+2, 8), 'end': new Date(year, month, day+2, 9, 30), 'title': 'Team breakfast', userId: 0},
        {'id':5, 'start': new Date(year, month, day+1, 14), 'end': new Date(year, month, day+1, 15), 'title': 'Product showcase', userId: 1}
      ],
      freebusys: [
        {'start': new Date(year, month, day), 'end': new Date(year, month, day+3), 'free': false, userId: [0,1,2,3]},
        {'start': new Date(year, month, day, 8), 'end': new Date(year, month, day, 12), 'free': true, userId: [0,1,2,3]},
        {'start': new Date(year, month, day+1, 8), 'end': new Date(year, month, day+1, 12), 'free': true, userId: [0,1,2,3]},
        {'start': new Date(year, month, day+2, 8), 'end': new Date(year, month, day+2, 12), 'free': true, userId: [0,1,2,3]},
        {'start': new Date(year, month, day+1, 14), 'end': new Date(year, month, day+1, 18), 'free': true, userId: [0,1,2,3]},
        {'start': new Date(year, month, day+2, 8), 'end': new Date(year, month, day+2, 12), 'free': true, userId: [0,3]},
        {'start': new Date(year, month, day+2, 14), 'end': new Date(year, month, day+2, 18), 'free': true, userId: 1}
      ]
    };

    d = new Date();
    d.setDate(d.getDate() -(d.getDay() - 3));
    year = d.getFullYear();
    month = d.getMonth();
    day = d.getDate();

    var eventData2 = {
      options: {
        timeslotsPerHour: 3,
        timeslotHeight: 30,
        defaultFreeBusy: {free: false}
      },
      events : [
        {'id':1, 'start': new Date(year, month, day, 12), 'end': new Date(year, month, day, 13, 00), 'title': 'Lunch with Sarah', userId: [1, 2]},
        {'id':2, 'start': new Date(year, month, day, 14), 'end': new Date(year, month, day, 14, 40), 'title': 'Team Meeting', userId: 0},
        {'id':3, 'start': new Date(year, month, day+1, 18), 'end': new Date(year, month, day+1, 18, 40), 'title': 'Meet with Joe', userId: 1},
        {'id':4, 'start': new Date(year, month, day-1, 8), 'end': new Date(year, month, day-1, 9, 20), 'title': 'Coffee with Alison', userId: 1},
        {'id':5, 'start': new Date(year, month, day+1, 14), 'end': new Date(year, month, day+1, 15, 00), 'title': 'Product showcase', userId: 0}
      ],
      freebusys: [
        {'start': new Date(year, month, day-1, 8), 'end': new Date(year, month, day-1, 18), 'free': true, userId: [0,1,2,3]},
        {'start': new Date(year, month, day, 8), 'end': new Date(year, month, day+0, 18), 'free': true, userId: [0,1,2,3]},
        {'start': new Date(year, month, day+1, 8), 'end': new Date(year, month, day+1, 18), 'free': true, userId: [0,3]},
        {'start': new Date(year, month, day+2, 14), 'end': new Date(year, month, day+2, 18), 'free': true, userId: 1}
      ]
    };


    function updateMessage() {
      var dataSource = $('#data_source').val();

      $('#message').fadeOut(function() {
        if (dataSource === '1') {
          $('#message').text('Displaying event data set 1 with timeslots per hour of 4 and timeslot height of 20px. Moreover, the calendar is free by default.');
        } else if(dataSource === '2') {
          $('#message').text('Displaying event data set 2 with timeslots per hour of 3 and timeslot height of 30px. Moreover, the calendar is busy by default.');
        } else {
          $('#message').text('Displaying no events.');
        }

        $(this).fadeIn();
      });
    }

    $(document).ready(function() {
      var $calendar = $('#calendar').weekCalendar({
        timeslotsPerHour: 4,
        scrollToHourMillis : 0,
	    allowCalEventOverlap: true,
	    overlapEventsSeparate: true,
	    totalEventsWidthPercentInOneColumn : 95,
	    hourLine: true,

        height: function($calendar){
          return $(window).height() - $('h1').outerHeight(true);
        },
        eventRender : function(calEvent, $event) {
        	console.log('event render')
        	console.log(calEvent, $event)
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

          alert('You\'ve added a new event. You would capture this event, add the logic for creating a new event with your own fields, data and whatever backend persistence you require.');

          calEvent.id = calEvent.userId +'_'+ calEvent.start.getTime();
          $(calendar).weekCalendar('updateFreeBusy', {
            userId: calEvent.userId,
            start: calEvent.start,
            end: calEvent.end,
            free:false
          });
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
              events: []
            });
          }
        },
        users: ['Wesarut Khm', 'Manuwat Chi', 'J Kons', 'Benz Raccoon', 'Bomb Sup', 'Cake Knm', 'Bee gg'],
        showAsSeparateUser: true,
        displayOddEven: true,
        displayFreeBusys: false,
        daysToShow: 1,
        switchDisplay: {'1 day': 1, '3 next days': 3, 'work week': 5, 'full week': 7},
        headerSeparator: ' ',
        useShortDayNames: true,
        // I18N
        firstDayOfWeek: $.datepicker.regional['en-AU'].firstDay,
        shortDays: $.datepicker.regional['en-AU'].dayNamesShort,
        longDays: $.datepicker.regional['en-AU'].dayNames,
        shortMonths: $.datepicker.regional['en-AU'].monthNamesShort,
        longMonths: $.datepicker.regional['en-AU'].monthNames,
        dateFormat: 'd F y'
      });

      $('#data_source').change(function() {
        $calendar.weekCalendar('refresh');
        updateMessage();
      });

      updateMessage();
    });
  })(jQuery);
  </script>
</head>
<body>
	<div id="calendar"></div>
</body>
</html>