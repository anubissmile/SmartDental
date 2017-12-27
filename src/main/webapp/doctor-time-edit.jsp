<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:แก้ไขเวลาการทำงานแพทย์</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div> 
			<div class="uk-width-9-10 ">
				<%@include file="doctor-nav.jsp" %>
				<div class="uk-grid uk-grid-collapse">
				<!-- Table  -->
					<div class="uk-width-1-1 "> 
						<div class=" uk-panel-box uk-form">
						<h4 class="hd-text uk-text-primary padding5">แก้ไขเวลาการทำงาน แพทย์</h4>
						<div class="uk-grid uk-grid-small uk-form" >  
							<div class="uk-width-1-10 uk-text-right"></div>
							<div class="uk-width-9-10 uk-text-right">   
								<span class="uk-text-bottom">ค้นหา - สาขา : </span>
								<div class="uk-autocomplete uk-shownoitems " id="autocomplete"> 
						            <input class="uk-form-small" type="text" id="autocomplete-input"> 
						            <script type="text/autocomplete">
              							<ul class="uk-nav uk-nav-autocomplete uk-autocomplete-results">
                							{{~items}}
                							<li data-value="{{ $item.value }}">
                  								<a>
                        						{{ $item.title }}
                        						<div>{{{ $item.text }}}</div>
                    							</a>
                							</li>
                							{{/items}}
              							</ul>
            						</script>
						       	</div> 
						       	<span class="uk-text-bottom">แพทย์ : </span>
								<div class="uk-autocomplete uk-shownoitems " id="autocompletept"> 
						            <input class="uk-form-small" type="text" id="autocomplete-inputpt"> 
						            <script type="text/autocomplete">
              							<ul class="uk-nav uk-nav-autocomplete uk-autocomplete-results">
                							{{~items}}
                							<li data-value="{{ $item.value }}">
                  								<a>
                        						{{ $item.title }}
                        						<div>{{{ $item.text }}}</div>
                    							</a>
                							</li>
                							{{/items}}
              							</ul>
            						</script>
						       	</div> 
						       	<span class="uk-text-bottom">เดือน : 
						       	<input type="radio" name="r11" checked> ทั้งหมด <input type="radio" name="r11" checked> เดือนปัจจุบัน <input type="radio" name="r11" > เดือนหน้า
						       	</span>
						       	<a class="uk-button uk-button-success uk-button-small" href="#">ค้นหา</a>
							</div>    
						</div> 
						<div class="uk-grid uk-grid-small"></div>
						<div class="uk-grid uk-grid-small">
				    	<div class="uk-width-1-1 uk-form">
						<table class="uk-table uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-text-nowrap ">
						    <caption>ตาราง เวลาการทำงานแพทย์</caption>
						    <thead>
						        <tr class="hd-table">
						            <th class="uk-text-center uk-width-1-10">เลขที่</th>
						            <th class="uk-text-center uk-width-2-10">สาขา</th>
						            <th class="uk-text-center uk-width-2-10">แพทย์</th>  
						            <th class="uk-text-center uk-width-1-10">เดือน</th>
						             <td class="uk-width-1-10 uk-text-center">วันที่</td>
						            <th class="uk-text-center uk-width-1-10">เวลาเข้างาน</th> 
						            <th class="uk-text-center uk-width-1-10">เวลาออกงาน</th>
						        </tr>
						    </thead> 
						    <tbody>
						        <tr>
						            <td class="uk-width-1-10 uk-text-center">1</td>
						            <td class="uk-width-2-10 uk-text-left">LDC ศรีนครินทร์</td> 
						            <td class="uk-width-2-10 uk-text-left">พท. นรง</td> 
						            <td class="uk-width-1-10 uk-text-center">เดือนปัจจุบัน</td>
						            <td class="uk-width-1-10 uk-text-center">06-06-2559</td>
						            <td class="uk-width-1-10 uk-text-center">
						            	<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
											<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="09:00" id="time1" name="time1">
										</div>
						            </td>
						            <td class="uk-width-1-10 uk-text-center">
						            	<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
											<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="12:00" id="time1" name="time1" >
										</div>
						            </td>    
						        </tr>  
						        <tr>
						            <td class="uk-width-1-10 uk-text-center">2</td>
						            <td class="uk-width-2-10 uk-text-left">LDC รามคำแหง</td> 
						            <td class="uk-width-2-10 uk-text-left">พท. นรง</td> 
						            <td class="uk-width-1-10 uk-text-center">เดือนปัจจุบัน</td>
						            <td class="uk-width-1-10 uk-text-center">08-06-2559</td>
						            <td class="uk-width-1-10 uk-text-center">
						            	<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
											<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="13:00" id="time1" name="time1">
										</div>
						            </td>
						            <td class="uk-width-1-10 uk-text-center">
						            	<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
											<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="17:00" id="time1" name="time1" >
										</div>
						            </td>    
						        </tr>
						        <tr>
						            <td class="uk-width-1-10 uk-text-center">3</td>
						            <td class="uk-width-2-10 uk-text-left">LDC ศรีนครินทร์</td> 
						            <td class="uk-width-2-10 uk-text-left">พท. วัตสัน</td> 
						            <td class="uk-width-1-10 uk-text-center">เดือนปัจจุบัน</td>
						            <td class="uk-width-1-10 uk-text-center">06-06-2559</td>
						            <td class="uk-width-1-10 uk-text-center">
						            	<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
											<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="09:00" id="time1" name="time1">
										</div>
						            </td>
						            <td class="uk-width-1-10 uk-text-center">
						            	<div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
											<input class="uk-form-small uk-width-1-1 uk-text-center" type="text"  value="17:00" id="time1" name="time1" >
										</div>
						            </td>    
						        </tr>
						       
						    </tbody>
						</table>
						</div>
							<div id="rev_lab" class="uk-modal ">
								    <div class="uk-modal-dialog uk-form " >
								        <a class="uk-modal-close uk-close"></a>
								         <div class="uk-modal-header"><i class="uk-icon-flask"></i> รับ งาน Lab</div>  
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-4-10">
								         		<input type="radio" name="r11" checked> รอรับงาน <input type="radio" name="r11" > รับงานเรียบร้อยแล้ว 
										        </div> 
										        <div class="uk-width-6-10">
										        	<div class="uk-form-icon uk-width-1-1">
										        	<i class="uk-icon-calendar">
    												</i> 
								         			<input class="uk-form-small uk-width-5-10" type="text" data-uk-datepicker="{format:'DD-MM-YYYY',minDate:0,maxDate:60,i18n:{months:['มกราคม','กุมภาพันธ์','มีนาคม','เมษายน','พฤษภาคม','มิถุนายน','กรกฎาคม','สิงหาคม','กันยายน','ตุลาคม','พฤศจิกายน','ธันวาคม'],weekdays:['อาทิตย์','จันทร์','อังคาร','พุธ','พฤหัสบดี','ศุกร์','เสาร์']}}" 
												        id="date" name="date" placeholder="วันที่คาดว่าจะได้รับ Lab">  
												    </div>
										        </div>
										    </div> 
											<hr/>
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-1-1">
													<div class="uk-form-icon uk-width-1-1">
								    				<i class="uk-icon-asterisk"></i>
													<input type="text" name="t12"  placeholder="หมายเหตุ" class="uk-form-small uk-width-1-1">
													</div>
												</div> 
											</div>
											<hr/>
								         <div class="uk-modal-footer uk-text-right">
								         	<button class="uk-modal-close">บันทึก</button>
								         	<button class="uk-modal-close">ยกเลิก</button> 
								         </div>
								    </div>
								</div>
								
								<div id="appoint_lab" class="uk-modal ">
								    <div class="uk-modal-dialog uk-form " >
								        <a class="uk-modal-close uk-close"></a>
								         <div class="uk-modal-header"><i class="uk-icon-flask"></i> นัดหมาย งาน Lab</div>  
											<div class="uk-grid uk-grid-small">
												<div class="uk-width-1-2">
												<select class="uk-width-1-1 day_lab" size="5"> 
													<option value="0">เลือกวัน</option> 
				            						<option value="1">วันอังคารที่ 2</option>
													<option value="2">วันศุกร์ที่ 3</option>
													<option value="3">วันอังคารที่ 9</option>
													<option value="4">วันศุกร์ที่ 10</option> 
												</select>
												</div>
												<div class="uk-width-1-2">
												<select class="uk-width-1-1 time_lab" size="5"> 
													<option value="0">เลือกเวลา</option>
												</select>
												</div>
											</div>
								         <div class="uk-modal-footer uk-text-right">
								         	<button class="uk-modal-close">บันทึก</button>
								         	<button class="uk-modal-close">ยกเลิก</button> 
								         </div>
								    </div>
								</div>
						
						</div>
						</div> 
						
					</div>
				 
			</div>
		</div>
		</div>
 
<script>
$(document).ready(function() {	
		
		$( ".m-setting" ).addClass( "uk-active" );
		$(".lab").change(function(){
			var lab = $(".lab").val(); 
			if(lab==0){ 
				$('.company').children('option:not(:first)').remove(); 
			}else{ 
				$('.company').children('option:not(:first)').remove();
				if(lab==1){
					$(".company").append( 
							"<option value='1'>ก การแพทย์ จำกัด</option>"+
							"<option value='2'>ข การแพทย์ จำกัด</option>"+
							"<option value='3'>ค การแพทย์ จำกัด</option>"+
				            "<option value='4'>จ การแพทย์ จำกัด</option>");
				}else{
					$(".company").append( 
							"<option value='1'>จ การแพทย์ จำกัด</option>"+
							"<option value='2'>ง การแพทย์ จำกัด</option>");
				}
				
		        
			}
		 });
		
		$(".day_lab").change(function(){
			var day_lab = $(".day_lab").val(); 
			if(day_lab==0){
				$('.time_lab').children('option:not(:first)').remove(); 
			}else if(day_lab==1){
				$('.time_lab').children('option:not(:first)').remove();
		        $(".time_lab").append(
						"<option>8:00 น</option>"+
						"<option>9:00 น</option>"+
						"<option>10:00 น</option>"+
						"<option>11:00 น</option>"+
						"<option>12:00 น</option>");
			}else if(day_lab==2){
				$('.time_lab').children('option:not(:first)').remove();
		        $(".time_lab").append( 
						"<option>13:00 น</option>"+
						"<option>14:00 น</option>"+
						"<option>15:00 น</option>"+
						"<option>16:00 น</option>"+
						"<option>17:00 น</option>");
			}else if(day_lab==3){
				$('.time_lab').children('option:not(:first)').remove();
		        $(".time_lab").append(
		        		"<option>8:00 น</option>"+
						"<option>9:00 น</option>"+
						"<option>10:00 น</option>"+
						"<option>11:00 น</option>"+
						"<option>12:00 น</option>"+
						"<option>13:00 น</option>"+
						"<option>14:00 น</option>"+
						"<option>15:00 น</option>"+
						"<option>16:00 น</option>"+
						"<option>17:00 น</option>");
			}else if(day_lab==4){
				$('.time_lab').children('option:not(:first)').remove();
		        $(".time_lab").append(
		        		"<option>18:00 น</option>"+
						"<option>19:00 น</option>"+
						"<option>20:00 น</option>"+
						"<option>21:00 น</option>");
			}
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
		
});	 

(function ($, UI) {
	  //sample data
	  var data = [
	   	  	{"value":"LDC ศรีนครินทร์", "title":"LDC ศรีนครินทร์", "url":"#", "text":"โทร: 02-743-8725"},
	  	  	{"value":"LDC ลาดพร้าว 89", "title":"LDC ลาดพร้าว 89", "url":"#", "text":"โทร: 02-542-1360"},
	   	 	{"value":"LDC สาขารามคำแหง 135", "title":"LDC สาขารามคำแหง 135", "url":"#", "text":"โทร: 02-729-5240"},
	   	 	{"value":"LDC สาขารังสิต", "title":"LDC สาขารังสิต", "url":"#", "text":"โทร: 02-996-2580"},
	   	 	{"value":"LDC สาขาเพชรเกษม", "title":"LDC สาขาเพชรเกษม", "url":"#", "text":"โทร: 02-809-2252"},
	   	 	{"value":"LDC สาขางามวงศ์วาน", "title":"LDC สาขางามวงศ์วาน", "url":"#", "text":"โทร: 02-951-8199"}
			];
	  function getAutocompleteData (release) {
	  	var search = this.value, items = []; //the scope is autocomplete, value in input is stored there as this.value
	    //do some stuff with your data. this is the default search of UIkit autocomplete
	   	data.forEach(function(item){
	       if(item.value && item.value.toLowerCase().indexOf(search.toLowerCase())!=-1) {
	          items.push(item);
					}
	    });
	    //release the data to the renderer
	  	release(items);
		}
	  UIkit.on('domready.uk.dom', function(){
	    UI.autocomplete($('#autocomplete'), {
	      source: getAutocompleteData,
	      minLength:1
	    });
	  });
	  //this is the easy way to do it. Just pass all the options as JSON/js and let UIkit do the filtering
		UIkit.on('domready.uk.dom', function(){
	    UI.autocomplete($('#autocomplete2'), {
	      source: data, //pass in the array of unfiltered items or a functioncall returning an array of (unfiltered) items
	      renderer: function (data) { //optional: hide ac when no items
						if (data) {
	                        if (data.length) {
	                            this.dropdown.append(this.template({"items": data}));
	                            this.show();
	                            this.trigger('show.uk.autocomplete', [data, this]);
	                        } else {
	                            this.hide();
	                        }
	                    }
	                }
	    });
	  });
	}(jQuery, UIkit));
	//function in global namespace for data-attribute call
	var dataTagValues = function () {
	  console.log('function called');
	  return [
	   	  	{"value":"LDC ศรีนครินทร์", "title":"LDC ศรีนครินทร์", "url":"#", "text":"โทร: 02-743-8725"},
	  	  	{"value":"LDC ลาดพร้าว", "title":"LDC ลาดพร้าว", "url":"#", "text":"โทร: 02-542-1360"},
	   	 	{"value":"LDC สาขารามคำแหง 135", "title":"LDC สาขารามคำแหง 135", "url":"#", "text":"โทร: 02-729-5240"},
	   	 	{"value":"LDC สาขารังสิต", "title":"LDC สาขารังสิต", "url":"#", "text":"โทร: 02-996-2580"},
	   	 	{"value":"LDC สาขารังสิต", "title":"LDC สาขารังสิต", "url":"#", "text":"โทร: 02-996-2580"},
	   	 	{"value":"LDC สาขาเพชรเกษม", "title":"LDC สาขาเพชรเกษม", "url":"#", "text":"โทร: 02-809-2252"},
	   	 	{"value":"LDC สาขางามวงศ์วาน", "title":"LDC สาขางามวงศ์วาน", "url":"#", "text":"โทร: 02-951-8199"}
			];
	};
	//action when autocomplete clicked
	UIkit.ready(function() {
	  UIkit.$('#autocomplete4').on('selectitem.uk.autocomplete', function (e, data, ac) {
	    UIkit.$('#clickResult').text('You clicked ' + data.value + ' and I erased that again!').addClass('uk-alert');
	    ac.input.val('');
	    data.value = null;
	  });
	});
	//UIkit.ready(function() {UIkit.$body.prepend('<div class="uk-float-right uk-badge">UIkit version ' + UIkit.version + '</div>')});
 	
	(function ($, UI) {
	  //sample data
	  var data = [
	   	  	{"value":"นรง", "title":"พท. นรง", "url":"#", "text":"โทร: 02-743-8725"},
	  	  	{"value":"วัตสัน", "title":"พท. วัตสัน", "url":"#", "text":"โทร: 02-542-1360"} 
			];
	  function getAutocompleteData (release) {
	  	var search = this.value, items = []; //the scope is autocomplete, value in input is stored there as this.value
	    //do some stuff with your data. this is the default search of UIkit autocomplete
	   	data.forEach(function(item){
	       if(item.value && item.value.toLowerCase().indexOf(search.toLowerCase())!=-1) {
	          items.push(item);
					}
	    });
	    //release the data to the renderer
	  	release(items);
		}
	  UIkit.on('domready.uk.dom', function(){
	    UI.autocomplete($('#autocompletept'), {
	      source: getAutocompleteData,
	      minLength:1
	    });
	  });
	  //this is the easy way to do it. Just pass all the options as JSON/js and let UIkit do the filtering
		UIkit.on('domready.uk.dom', function(){
	    UI.autocomplete($('#autocomplete2'), {
	      source: data, //pass in the array of unfiltered items or a functioncall returning an array of (unfiltered) items
	      
	      renderer: function (data) { //optional: hide ac when no items
						if (data) {
	                        if (data.length) {
	                            this.dropdown.append(this.template({"items": data}));
	                            this.show();
	                            this.trigger('show.uk.autocomplete', [data, this]);
	                        } else {
	                            this.hide();
	                        }
	                    }
	                }
	    });
	  });
	}(jQuery, UIkit));
	//function in global namespace for data-attribute call
	var dataTagValues = function () {
	  console.log('function called');
	  return [
				{"value":"พท. นรง", "title":"พท. นรง", "url":"#", "text":"โทร: 02-743-8725"},
				{"value":"พท. วัตสัน", "title":"พท. วัตสัน", "url":"#", "text":"โทร: 02-542-1360"} 
			];
	};
	//action when autocomplete clicked
	UIkit.ready(function() {
	  UIkit.$('#autocomplete4').on('selectitem.uk.autocomplete', function (e, data, ac) {
	    UIkit.$('#clickResult').text('You clicked ' + data.value + ' and I erased that again!').addClass('uk-alert');
	    ac.input.val('');
	    data.value = null;
	  });
	});
 
</script>		
	</body>
</html>