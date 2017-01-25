<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap-clockpicker.min.css">
<link href="css/uikit.gradient.css"rel="stylesheet"/>
<link href="css/style.css"rel="stylesheet">
<link href='css/fullcalendar.css' rel='stylesheet' /> 
<link href="css/components/datepicker.gradient.css"rel="stylesheet">    
<link rel="stylesheet" type="text/css" href="css/github.min.css"> 
<link rel="stylesheet" type="text/css" href="css/sweetalert2.min.css">
<link rel="stylesheet" type="text/css" href="css/components/form-advanced.gradient.min.css">
<link rel="stylesheet" type="text/css" href="css/components/form-select.gradient.css">
<link rel="stylesheet" type="text/css" href="css/components/sortable.gradient.css">
<link href="css/components/accordion.gradient.min.css"rel="stylesheet">
<link href="css/components/nestable.gradient.min.css"rel="stylesheet">
<link href="css/dataTables.uikit.min.css"rel="stylesheet">
<nav class="uk-panel uk-panel-box " style="padding:5px;"> 
	<div class="uk-grid">
		<div id="menu-top-left" class="uk-text-left uk-width-1-2">
			<button class="uk-button uk-button-success"><i class="uk-icon-user"></i> เลือกคนไข้</button>
			<a href="patient-add.jsp" class="uk-button uk-button-primary"><i class="uk-icon-user-plus"></i> เพิ่มคนไข้</a>
			<a href="#add_app" class="uk-button uk-button-primary" data-uk-modal>
				<i class="uk-icon-calendar-plus-o"></i> เพิ่มนัดหมาย
			</a>
			<div id="add_app" class="uk-modal ">
			    <div class="uk-modal-dialog uk-form " >
			        <a class="uk-modal-close uk-close"></a>
			         <div class="uk-modal-header"><i class="uk-icon-group"></i> เพิ่มนัดหมาย</div>
			         	<div class="uk-form-icon">
    						<i class="uk-icon-user">
    						</i>
			         	<input class="uk-width-1-1" type="text" id="event" name="event" placeholder="ชื่อลูกค้า"> 
			         	</div>
			         	-
			         	<div class="uk-form-icon">
							<i class="uk-icon-sign-in">
    						</i>
							<input type="number" id="room" name="room" placeholder="ห้อง"> 
						</div>
						<hr/>  
						<div class="uk-form-icon">
							<i class="uk-icon-calendar">
    						</i>
							<input type="text" data-uk-datepicker="{format:'DD.MM.YYYY'}" id="date" name="date" placeholder="วันที่"> 
						</div>
						<hr/>
						<div class=" clockpicker pull-center uk-form-icon" data-placement="right" data-align="top" data-autoclose="true">
							<i class="uk-icon-clock-o">
    						</i>
							<input type="text" class="uk-width-1-1" value="" id="time1" name="time1" placeholder="เวลาเริ่ม"> 
						</div>
						-
						<div class=" clockpicker pull-center uk-form-icon" data-placement="right" data-align="top" data-autoclose="true">
							<i class="uk-icon-clock-o">
    						</i>
							<input type="text" class="uk-width-1-1" value="" id="time2" name="time2" placeholder="เวลาจบ"> 
						</div>
			         <div class="uk-modal-footer uk-text-right">
			         	<button class="uk-modal-close">ยกเลิก</button>
			         	<button id="savecalendar">บันทึก</button>
			         </div>
			    </div>
			</div> 
		</div>
		<div id="menu-top-right" class="uk-text-right uk-width-1-2">
			<div class="uk-button-dropdown" data-uk-dropdown="{mode:'click'}">
				 <!-- This is the button toggling the dropdown -->
				 <button class="uk-button">
					 <i class=" uk-icon-stethoscope uk-icon-small"></i> 
					 <span class="uk-badge uk-badge-notification uk-badge-danger">2</span>
				 </button>				
				 <!-- This is the dropdown -->
			    <div class="uk-dropdown uk-dropdown-small list-stack-job">
			        <ul class="uk-nav uk-nav-dropdown ">
			        	<li class="uk-nav-header">รายการงานที่ทำงานค้างอยู่</li>
			            <li><a href="">1. มานุวัฒน์ ชัยชนะ <small>ห้องตรวจ 5 <br>20นาทีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">2. ไวยวิทย์ เนียมขุน <small>ห้องตรวจ 2 <br>10นาทีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">1. มานุวัฒน์ ชัยชนะ <small>ห้องตรวจ 5 <br>20นาทีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">2. ไวยวิทย์ เนียมขุน <small>ห้องตรวจ 2 <br>10นาทีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">1. มานุวัฒน์ ชัยชนะ <small>ห้องตรวจ 5 <br>20นาทีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">2. ไวยวิทย์ เนียมขุน <small>ห้องตรวจ 2 <br>10นาทีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">1. มานุวัฒน์ ชัยชนะ <small>ห้องตรวจ 5 <br>20นาทีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">2. ไวยวิทย์ เนียมขุน <small>ห้องตรวจ 2 <br>10นาทีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">1. มานุวัฒน์ ชัยชนะ <small>ห้องตรวจ 5 <br>20นาทีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">2. ไวยวิทย์ เนียมขุน <small>ห้องตรวจ 2 <br>10นาทีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">1. มานุวัฒน์ ชัยชนะ <small>ห้องตรวจ 5 <br>20นาทีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">2. ไวยวิทย์ เนียมขุน <small>ห้องตรวจ 2 <br>10นาทีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">1. มานุวัฒน์ ชัยชนะ <small>ห้องตรวจ 5 <br>20นาทีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">2. ไวยวิทย์ เนียมขุน <small>ห้องตรวจ 2 <br>10นาทีที่แล้ว</small></a></li>
			        </ul>
			    </div>
			</div>
			<div class="uk-button-dropdown" data-uk-dropdown="{mode:'click'}">
				 <!-- This is the button toggling the dropdown -->
				 <button class="uk-button">
					 <i class=" uk-icon-calendar-check-o uk-icon-small"></i> 
					 <span class="uk-badge uk-badge-notification uk-badge-danger">1</span>
				 </button>				
				 <!-- This is the dropdown -->
			    <div class="uk-dropdown uk-dropdown-small">
			        <ul class="uk-nav uk-nav-dropdown">
			        	<li class="uk-nav-header">การนัดหมายที่ใกล้จะถึง</li>
			            <li><a href=""></a></li>
			            <li><a href=""></a></li>
			        </ul>
			    </div>
			</div>
			<div class="uk-button-dropdown" data-uk-dropdown="{mode:'click'}">
				 <!-- This is the button toggling the dropdown -->
				 <button class="uk-button">
					 <i class=" uk-icon-phone uk-icon-small"></i> 
					 <span class="uk-badge uk-badge-notification uk-badge-danger">3</span>
				 </button>				
				 <!-- This is the dropdown -->
			    <div class="uk-dropdown uk-dropdown-small">
			        <ul class="uk-nav uk-nav-dropdown ">
			        	<li class="uk-nav-header">แจ้งเตือนการโทร</li>
			        	<li><a href="homecall.jsp" class="uk-text-left">HOME CALL 
			        		<span class="uk-badge uk-badge-notification uk-badge-danger uk-text-right noti">2</span>
			        	</a></li>
			            <li><a href="recall.jsp" class="uk-text-left">RE CALL 
			            	<span class="uk-badge uk-badge-notification uk-badge-danger uk-text-right noti2">1</span>
			            </a></li>
			            
			        </ul>
			    </div>
			</div>
		</div>
	</div>
</nav> 

<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/uikit.min.js"></script>
<script src="js/components/datepicker.min.js"></script>
<script src="js/components/accordion.min.js"></script>
<script src="js/components/nestable.min.js"></script>
<script src="js/components/form-select.min.js"></script>
<script src="js/moment.min.js"></script>
<script src="js/fullcalendar.min.js"></script>
<script src="js/th.js"></script>  
<script src="js/sweetalert2.min.js"></script>  
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-clockpicker.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.uikit.min.js"></script> 
<script>
$(document).ready(function() { 
			
		$("#savecalendar").click(function(){
			 
			var title = $("#event").val();
			var date = $("#date").val();
			var time1 = $("#time1").val();
			var time2 = $("#time2").val();
			var room = $("#room").val();
			
			if(title == ''){
				swal(
					'ผิดพลาด!',
					'กรุณาระบุ หัวข้อ!',
					'error'
				)
			}else if(room == ''){
				swal(
					'ผิดพลาด!',
					'กรุณาระบุ ห้อง!',
					'error'
				)
			}else if(date == ''){
				swal(
					'ผิดพลาด!',
					'กรุณาระบุ วันที่!',
					'error'
				)
			}else if(time1 == ''){
				swal(
					'ผิดพลาด!',
					'กรุณาระบุ เวลาเริ่ม!',
					'error'
				)
			}else if(time2 == ''){
				swal(
					'ผิดพลาด!',
					'กรุณาระบุ เวลาจบ!',
					'error'
				)
			}else{
				day = date.substring(0, 2);
				month = date.substring(3, 5);
				year = date.substring(6, 10);
				time1 = year+"-"+month+"-"+day+"-"+time1+":00";
				time2 = year+"-"+month+"-"+day+"-"+time2+":59"; 
				
				var letters = '0123456789ABCDEF'.split('');
			    var color = '#';
			    for (var i = 0; i < 6; i++ ) {
			        color += letters[Math.floor(Math.random() * 16)];
			    }
				
				swal({
	    			  title: 'คุณต้องการเพิ่ม event หรือไม่?',
	    			  text: "หากต้องการเพิ่มให้กดตกลง!",
	    			  type: 'warning',
	    			  showCancelButton: true,
	    			  confirmButtonColor: '#3085d6',
	    			  cancelButtonColor: '#d33',
	    			  confirmButtonText: 'คกลง!',
	    			  cancelButtonText: 'ยกเลิก',
	    			}).then(function(isConfirm) {
	    			  if (isConfirm) {
				
						$.ajax({    
							 
					        type: "post",
					        url: "ajax/ajax-save-event.jsp", //this is my servlet 
					        data: {event:title,time1:time1,time2:time2,color:color,hong:room},
					        async:false, 
					        success: function(result){
					        	  
					        	obj = JSON.parse(result); 
					        	 
						     } 
					     });
						//$('#calendar').fullCalendar( 'removeEvents');
						$('#calendar').fullCalendar( 'addEventSource', obj);
						
						swal(
			    			      'เพิ่ม!',
			    			      'ข้อมูล event ได้ถูกเพิ่มแล้ว.',
			    			      'success'
			    		);
						
						$("#event").val("");
						$("#date").val("");
						$("#time1").val("");
						$("#time2").val("");
						$("#room").val("");
						
		    		 }else{
		    				  swal(
				    			'ข้อมูล event ไม่ถูกเพิ่ม!',
				    			'ข้อมูล event ยังไม่ถูกเพิ่ม.',
				    			'error'
				    		);  
		    			  }
		    		})
			}
		});
});		
</script>

<script type="text/javascript">
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
<script type="text/javascript" src="js/highlight.min.js"></script>
<script type="text/javascript">
hljs.configure({tabReplace: '    '});
hljs.initHighlightingOnLoad();
</script>
