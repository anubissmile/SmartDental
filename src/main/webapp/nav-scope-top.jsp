<%@page import="com.smict.person.model.TelephoneModel"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.person.model.PatientModel" %>
<%@ page import="com.smict.person.data.PatientData" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link href="css/uikit.gradient.css" rel="stylesheet"/>
<link href="css/bootstrap-datepicker3.css" rel="stylesheet">
<link href="css/select2.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/rules.css" rel="stylesheet">
<link href='css/fullcalendar.css' rel='stylesheet' />
<link href="css/components/datepicker.gradient.css" rel="stylesheet">   
<link href="css/jquery.dataTables.min.css" rel="stylesheet">
<link rel="icon" href="img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="css/sweetalert2.min.css">
<link rel="stylesheet" type="text/css" href="css/components/form-advanced.gradient.min.css">
<link rel="stylesheet" type="text/css" href="css/components/form-select.gradient.css">
<link rel="stylesheet" type="text/css" href="css/components/sortable.gradient.css">
<link rel="stylesheet" type="text/css" href="css/components/autocomplete.gradient.css"> 
<link href="css/components/accordion.gradient.min.css" rel="stylesheet">
<link href="css/components/nestable.gradient.min.css" rel="stylesheet">
<link href="css/jquery-clockpicker.css" rel="stylesheet">  


<nav class="uk-panel uk-panel-box " style="padding:5px;"> 
	<div class="uk-grid">
		<div id="menu-top-left" class="uk-text-left uk-width-1-2"> 
			<a href="getScopeDentist" class="uk-button"><i class="uk-icon-mail-reply"></i> กลับสู่หน้า Scope งานแพทย์ </a>
		</div>
		
	</div>
</nav> 

<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap-datepicker-th.js"></script>
<script src="js/uikit.min.js"></script>
<script src="js/components/notify.js"></script>
<script src="js/components/datepicker.min.js"></script>
<script src="js/components/accordion.min.js"></script>
<script src="js/components/nestable.min.js"></script>
<script src="js/components/form-select.min.js"></script>
<script src="js/components/autocomplete.min.js"></script> 
<script src="js/core/tab.min.js"></script> 
<script src="js/moment.min.js"></script>
<script src="js/fullcalendar.min.js"></script>
<script src="js/th.js"></script>  
<script src="js/sweetalert2.min.js"></script>  
<script src="js/jquery-clockpicker.js"></script>
<script src="js/jquery.dataTables.min.js"></script> 
<script src="js/select2.min.js"></script>

<script>
$(document).ready(function() {

		/*TABLE ADD BRANCH #addBranch*/
		$("#tbBranch").DataTable();


	   	// patient alert
	   	/* patienShow();
		var timerId = setInterval(function() {  
			patienShow();
			//clearInterval(timerId);
		}, 5000); */
		function patienShow(){
			// show patient 
			var textvalue = '<li class="uk-nav-header">รายการงานที่ทำงานค้างอยู่</li>';
			$.ajax({
		        type: "post",
		        url: "ajax/ajax-treatment-working.jsp", //this is my servlet 
		        data: {method_type:"get"},
		        async:true, 
		        success: function(result){
		        	var obj = jQuery.parseJSON(result);
		        	for(var i = 0 ;  i < obj.length;i++){
		        		textvalue += '<li><a href="treatmentAlert?hn='+obj[i].hn+'">'+obj[i].hnname+'<small> '+obj[i].title_status+'<br>'+obj[i].minute+'</small></a></li><li class="uk-nav-divider"></li>'
		        		 
		        	}
		        	$("#menu").html(textvalue); 
			    } 
		     }); 
			
			// count patient
			$.ajax({
		        type: "post",
		        url: "ajax/ajax-treatment-working-count.jsp", //this is my servlet 
		        data: {method_type:"get"},
		        async:true, 
		        success: function(result){
		        	var obj = jQuery.parseJSON(result); 
		        	for(var i = 0 ;  i < obj.length;i++){
		        		$("#countpatient").html(obj[i].counthn);
		        	}
			    } 
		    });
		} 
		  
		$("#conallis").ready(function(){
			var conpatneed = parseInt($("#patneed").text());
			var condocneed = parseInt($("#docneed").text());
			var conall =condocneed+conpatneed;			
			if(conall > 0 ){
			$("#countall").text(conall);
			}
			
		});
		$("#countallconall").ready(function(){
			var beallerC = parseInt($("#beallerC").text());
			var congenC = parseInt($("#congenC").text());
			var conallC =congenC+beallerC;			
			if(conallC > 0 ){
			$("#countallcon").text(conallC);
			}
			
		});
		$("#tablechoose_patient").DataTable();
		// นัดหมาย
		$(".pt").change(function(){
			var pt = $(".pt").val(); 
			if(pt==0){
				$('.month').children('option:not(:first)').remove(); 
				$('.day').children('option:not(:first)').remove();
				$('.time').children('option:not(:first)').remove(); 
			}else{
				$('.month').children('option:not(:first)').remove();
				$('.day').children('option:not(:first)').remove();
				$('.time').children('option:not(:first)').remove();
				if(pt==1){
					$(".month").append( 
							"<option value='1'>เดือนนี้</option>"+
				            "<option value='2'>เดือนหน้า</option>");
				}else{
					$(".month").append( 
							"<option value='1'>เดือนนี้</option>");
				}
				
		        
			}
	    });	
		$(".month").change(function(){
			var month = $(".month").val(); 
			if(month==0){
				$('.day').children('option:not(:first)').remove(); 
				$('.time').children('option:not(:first)').remove();
			}else{
				$('.day').children('option:not(:first)').remove();
				$('.time').children('option:not(:first)').remove();
				var month = $(".month").val(); 
				if(month=='1'){
					 $(".day").append(
								"<option>วันจันทร์ที่ 1</option>"+
								"<option>วันพุธที่ 3</option>"+
								"<option>วันพฤหัสบดีที่ 4</option>"+
								"<option>วันจันทร์ที่ 8</option>"+
								"<option>วันพุธที่ 10</option>"+
								"<option>วันพฤหัสบดีที่ 11</option>"+
								"<option>วันจันทร์ที่ 15</option>"+
								"<option>วันพุธที่ 17</option>"+
								"<option>วันพฤหัสบดีที่ 18</option>"+
								"<option>วันจันทร์ที่ 22</option>"+
								"<option>วันพุธที่ 24</option>"+
								"<option>วันพฤหัสบดีที่ 25</option>");
				}else{
					 $(".day").append(
								"<option>วันอังคารที่ 2</option>"+
								"<option>วันศุกร์ที่ 3</option>"+
								"<option>วันอังคารที่ 9</option>"+
								"<option>วันศุกร์ที่ 10</option>"+
								"<option>วันอังคารที่ 16</option>"+
								"<option>วันศุกร์ที่ 17</option>"+
								"<option>วันอังคารที่ 23</option>"+
								"<option>วันศุกร์ที่ 24</option>"+
								"<option>วันอังคารที่ 30</option>");
				}
		       
			}
	    });
		$(".day").change(function(){
			var month = $(".day").val(); 
			if(month==0){
				$('.time').children('option:not(:first)').remove(); 
			}else{
				$('.time').children('option:not(:first)').remove();
		        $(".time").append(
						"<option>8:00 น</option>"+
						"<option>9:00 น</option>"+
						"<option>10:00 น</option>"+
						"<option>11:00 น</option>"+
						"<option>12:00 น</option>"+
						"<option>13:00 น</option>"+
						"<option>14:00 น</option>"+
						"<option>15:00 น</option>"+
						"<option>16:00 น</option>"+
						"<option>17:00 น</option>"+
						"<option>18:00 น</option>"+
						"<option>19:00 น</option>"+
						"<option>20:00 น</option>"+
						"<option>21:00 น</option>");
			}
	    });
		 
		// นัดหมาย
	
		$("#birthdate").click(function(){
			if($("#birthdate").text() == "Thai year"){
				$("#birthdate").text("English year");
			}else{
				$("#birthdate").text("Thai year");	
			}
		});
		$("#fpatient-quick").submit(function(event){
			if($("#idtel").val().length === 0 && $("#idline").val().length === 0 && $("#email").val().length === 0){
				swal(
						'ผิดพลาด!',
						'กรุณาระบุ กรอกข้อมูล เบอร์โทรศัพท์ IDLINE หรือ Email อย่างใดอย่างหนึ่ง',
						'error'
					)
				event.preventDefault();
			}
		});
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

function tab1() { 
	
	 $("#idline").val(""); 
	 $("#email").val("");
};
function tab2() { 
	 
	 $("#idno").val(""); 
	 $("#email").val("");
};
function tab3() { 
	 
	 $("#idno").val(""); 
	 $("#idline").val("");
};

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

//Manually toggle to the minutes view
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

