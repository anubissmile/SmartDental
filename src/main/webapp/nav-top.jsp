<%@page import="com.smict.person.model.TelephoneModel"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.person.model.PatientModel" %>
<%@ page import="com.smict.person.data.PatientData" %>
<link href="css/uikit.gradient.css" rel="stylesheet"/>
<link href="css/bootstrap-datepicker3.css" rel="stylesheet">
<link href="css/select2.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href='css/fullcalendar.css' rel='stylesheet' /> 
<link href="css/components/datepicker.gradient.css" rel="stylesheet">   
<link href="css/jquery.dataTables.min.css" rel="stylesheet">

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
			<a href="#add_patient" class="uk-button uk-button-success" data-uk-modal>
				<i class="uk-icon-user"></i> เลือกคนไข้
			</a>
			<div id="add_patient" class="uk-modal ">
			    <div class="uk-modal-dialog uk-modal-dialog-large uk-form " >
			        <a class="uk-modal-close uk-close"></a>
			         <div class="uk-modal-header"><i class="uk-icon-user-md"></i> คนไข้</div>
			         <form action="topPatient" method="post">
			         	<div class="uk-width-1-1 uk-overflow-container">
			         	
			         		<input type="hidden" id="hn" name="servicePatModel.hn">
			         		<input type="hidden" id="hnFormat" name="servicePatModel.hnFormat">
			         		<input type="hidden" id="addr_id" name="servicePatModel.addr_id">
			         		<input type="hidden" id="fam_id" name="servicePatModel.fam_id">
			         		<input type="hidden" id="be_allergic_id" name="servicePatModel.be_allergic_id">
			         		<input type="hidden" id="patneed_id" name="servicePatModel.patneed_id">
			         		<input type="hidden" id="pat_congenital_disease_id" name="servicePatModel.pat_congenital_disease_id">
			         		<input type="hidden" id="tel_id" name="servicePatModel.tel_id">
			         		
							<table id = "tablechoose_patient" class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
							    <thead>
							        <tr class="hd-table"> 
							        	<th class="uk-text-center">เลือก</th>
							            <th class="uk-text-center">รหัส</th> 
							            <th class="uk-text-center">ชื่อ ไทย</th>
							            <th class="uk-text-center">ชื่อ ต่างชาติ</th>
							        </tr>
							    </thead> 
							    <tbody>
							    	<% 
							    		PatientData patDB = new PatientData(); 
							    		List<PatientModel> patList = patDB.getListPatModelForTovNav(null); 
							    		for(PatientModel patModel : patList){
							    	%> 
									<tr>  
							    		<td class="uk-text-center">
								        	<div class="uk-form-controls"> 
	                                            <input type="radio" name="getHN" 
	                                            	onclick="getElementById('hnFormat').value='<%=patModel.getHnFormat()%>',
	                                            			getElementById('hn').value='<%=patModel.getHn()%>',
	                                            			getElementById('addr_id').value='<%=patModel.getAddr_id()%>',
	                                            			getElementById('fam_id').value='<%=patModel.getFam_id()%>',
	                                            			getElementById('be_allergic_id').value='<%=patModel.getBe_allergic_id()%>',
	                                            			getElementById('patneed_id').value='<%=patModel.getPatneed_id()%>',
	                                            			getElementById('pat_congenital_disease_id').value='<%=patModel.getPat_congenital_disease_id()%>',
	                                            			getElementById('tel_id').value='<%=patModel.getTel_id()%>';" >
                                   			</div>
                                   		</td>
							    		<td class="uk-text-center"><%=patModel.getHnFormat()%></td>
								        <td class="uk-text-left"><%=patModel.getFirstname_th()%> - <%=patModel.getLastname_th()%></td>
								        <td class="uk-text-left"><%=patModel.getFirstname_en()%> - <%=patModel.getLastname_en()%></td> 
									</tr>
									<% } %>
								</tbody>
							</table>
					</div>
			         <div class="uk-modal-footer uk-text-right">
			         	<button class="uk-button uk-button-success" type="submit">ตกลง</button>
			         	<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button> 
			         </div>
			         </form>
			    </div>
			</div>
			<div class="uk-button-dropdown" data-uk-dropdown="" aria-haspopup="true" aria-expanded="false">
                <a href="selectPatient" class="uk-button uk-button-primary"><i class="uk-icon-user-plus"></i> เพิ่มคนไข้ </a>
            </div>
            
            <div id="patient-quick" class="uk-modal ">
            <form action="#" id="fpatient-quick">
			    <div class="uk-modal-dialog uk-form " >
			        <a class="uk-modal-close uk-close"></a>
			         <div class="uk-modal-header"><i class="uk-icon-user-md"></i> คนไข้</div>
			         	<div class="uk-width-1-1 uk-overflow-container">
			         	 	<div class="uk-grid uk-grid-small">
			         	 		<div class="uk-width-1-3"> 
									<select class="uk-form-small uk-width-1-1" >
										<option>คำนำหน้าชื่อ</option>
										<option>นาย</option>
										<option>นาง</option>
										<option>นางสาว</option>
										<option>เด็กชาย</option>
										<option>เด็กหญิง</option>
									</select>  
								</div>
			         	 		<div class="uk-width-1-3"> 
			         	 			<div class="uk-form-icon">
								    <i class="uk-icon-asterisk"></i>
								    <input type="text" name="t12"  placeholder="ชื่อ" class="uk-form-small uk-width-1-1"> 
								    </div>
								</div>
								<div class="uk-width-1-3"> 
									<div class="uk-form-icon">
								    	<i class="uk-icon-asterisk"></i>
								    	<input type="text" name="t12"  placeholder="นามสกุล" class="uk-form-small uk-width-1-1"> 
								    </div>
								</div>
			         	 	</div>
			         	 	
			         	 	<div class="uk-grid uk-grid-small">
			         	 		<div class="uk-width-1-3">
			         	 			<div class="uk-form-icon">
								    <i class="uk-icon-calendar"></i>
								    <input type="text" name="t12"  placeholder="วันเกิด" data-uk-datepicker="{format:'DD-MM-YYYY'}" class="uk-form-small uk-width-1-1">
								    </div>
								</div>
								<div class="uk-width-1-3">
									<button type="button" class="uk-button uk-button-primary uk-button-small" id="birthdate">Thai year</button>
								</div>
			         	 	</div>
			         	 	<div class="uk-grid uk-grid-small">  
                                <div class="uk-width-1-3 uk-row-first"> 
                                    <ul class="uk-tab uk-tab-left" data-uk-tab="{connect:'#tab-left-content'}">
                                        <li class="uk-active" aria-expanded="true" onclick="tab1()"><a href="#" >Telephone</a></li>
                                        <li aria-expanded="false" class="" onclick="tab2()"><a href="#" >ID Line</a></li>
                                        <li aria-expanded="false" class="" onclick="tab3()"><a href="#" >E-Mail</a></li>
                                    <li class="uk-tab-responsive uk-active uk-hidden" aria-haspopup="true" aria-expanded="false"><a>Tab</a><div class="uk-dropdown uk-dropdown-small"><ul class="uk-nav uk-nav-dropdown"></ul><div></div></div></li></ul>
 							 	</div>
                                <div class="uk-width-1-3"> 
                                    <ul id="tab-left-content" class="uk-switcher">
                                        <li class="uk-active" aria-hidden="false">
                                        	<div class="uk-form-icon">
								    		<i class="uk-icon-asterisk"></i>
                                        	<input type="text" name="idtel" id="idtel" pattern="[0-9]" title="ข้อมูลที่กรอกไม่ใช่ตัวเลข" placeholder="เบอร์ติดต่อ" class="uk-form-small uk-width-1-1">
                                        	</div>
                                        </li>
                                        <li aria-hidden="true" class="">
											<div class="uk-form-icon">
								    		<i class="uk-icon-asterisk"></i>
                                        	<input type="text" name="idline" id="idline"  placeholder="รหัส ไอดี line" class="uk-form-small uk-width-1-1">
                                        	</div>
										</li>
                                        <li aria-hidden="true" class="">
											<div class="uk-form-icon">
								    		<i class="uk-icon-asterisk"></i>
                                        	<input type="email" name="email" id="email"  placeholder="อีเมลล์" class="uk-form-small uk-width-1-1">
                                        	</div>
										</li>
                                    </ul> 
                                </div> 
                                <div class="uk-width-1-3">
								</div>
								
			         	 	</div>
							<div class="uk-grid uk-grid-small">
			         	 		<div class="uk-width-1-3">  
			         	 			โรคประจำตัว
			         	 			<a href="#disease" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" data-uk-modal>
											<i class="uk-icon-plus"></i>
										</a>
										<button class="uk-button uk-button-danger uk-button-small"><i class="uk-icon-times"></i></button>
									<select size="5" class="uk-form-small uk-width-1-1" >
										<option>ไม่มี</option>
										<option></option>
										<option></option>
										<option></option>
										<option></option>
									</select> 
								</div>
			         	 		<div class="uk-width-1-3">  
			         	 			ประวัติการแพ้ยา
			         	 			<a href="#disease" class="uk-button uk-button-primary uk-width-2-10 uk-button-small" data-uk-modal>
											<i class="uk-icon-plus"></i>
										</a>
										<button class="uk-button uk-button-danger uk-button-small"><i class="uk-icon-times"></i></button>
									<select size="5" class="uk-form-small uk-width-1-1" >
										<option>Chlopheniramine</option>
										<option>Diphenhydramine</option>
										<option>Adrenaline</option>
										<option></option>
										<option></option>
									</select> 
								</div>
			         	 	</div>
					</div>
			         <div class="uk-modal-footer uk-text-right">
			         	<button type="submit">บันทึก</button>
			         	<button class="uk-modal-close">ยกเลิก</button> 
			         </div>
			    </div>
			    </form>
			</div>
			
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
							<i class="uk-icon-phone">
    						</i>
							<input type="text" pattern="[0-9]"  title="กกรอกเฉพาะตัวเลขเท่านั้น" id="room" name="room" placeholder="เบอร์โทรศัพท์"> 
						</div>
						-
						<input type="radio" name="r11" checked> N <input type="radio" name="r11" > NR <input type="radio" name="r11" > R
						<hr/>    
						<div class="uk-grid uk-grid-small">
							<div class="uk-width-1-2">
			         		<select class="uk-width-1-1 pt" size="5">
			         			<option selected value="0">เลือกแพทย์</option>
					            <option value="1">ทพ.วิจิต</option>
					            <option value="2">ทพ.สดใส</option>
					        </select> 
					        </div>
					        <div class="uk-width-1-2">
					        <select class="uk-width-1-1 month" size="5">
			         			<option value="0">เลือกเดือน</option> 
					        </select>  
					        </div>
					    </div> 
						<hr/>
						<div class="uk-grid uk-grid-small">
							<div class="uk-width-1-2">
							<select class="uk-width-1-1 day" size="5"> 
								<option value="0">เลือกวัน</option>
							</select>
							</div>
							<div class="uk-width-1-2">
							<select class="uk-width-1-1 time" size="5"> 
								<option value="0">เลือกเวลา</option>
							</select>
							</div>
						</div>
						<hr/>
						<!--
						<div class="uk-form-icon">
							<i class="uk-icon-stethoscope">
    						</i>
							<input type="text" id="date" name="date" placeholder="แพทย์"> 
						</div>
						-
						<div class="uk-form-icon">
							<i class="uk-icon-calendar">
    						</i> 
							<input class="uk-width-2-10" type="text" 
					        data-uk-datepicker="{format:'DD.MM.YYYY',minDate:0,maxDate:60,i18n:{months:['มกราคม','กุมภาพันธ์','มีนาคม','เมษายน','พฤษภาคม','มิถุนายน','กรกฎาคม','สิงหาคม','กันยายน','ตุลาคม','พฤศจิกายน','ธันวาคม'],weekdays:['อาทิตย์','จันทร์','อังคาร','พุธ','พฤหัสบดี','ศุกร์','เสาร์']}}" 
					        id="date" name="date" placeholder="วันที่">  
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
			         	<button id="savecalendar">บันทึก</button>
			         	<button class="uk-modal-close">ยกเลิก</button> 
			         </div> -->
			    </div>
			</div> 
			<a href="sendLabBegin" class="uk-button uk-button-primary">
				 งาน lab
			</a>
		</div>
		<div id="menu-top-right" class="uk-text-right uk-width-1-2">
			<div class="uk-button-dropdown" data-uk-dropdown="{mode:'click'}">
				 <!-- This is the button toggling the dropdown -->
				 <button class="uk-button">
					 <i class=" uk-icon-exclamation-triangle uk-icon-small"></i> 
					 <span class="uk-badge uk-badge-notification uk-badge-danger">2</span>
				 </button>				
				 <!-- This is the dropdown -->
			    <div class="uk-dropdown uk-dropdown-small list-stack-job">
			        <ul class="uk-nav uk-nav-dropdown ">
			        	<li class="uk-nav-header">คนไข้ที่ขาดการติดต่อ</li>
			            <li><a href="">HN002225 มานุวัฒน์ ชัยชนะ <small> <br> มารักษาล่าสุด 25/10/2012  :  5 ปีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">HN002225 มานุวัฒน์ ชัยชนะ <small> <br> มารักษาล่าสุด 25/10/2012  :  5 ปีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">HN002225 มานุวัฒน์ ชัยชนะ <small> <br> มารักษาล่าสุด 25/10/2012  :  5 ปีที่แล้ว</small></a></li>
			            <li class="uk-nav-divider"></li>
			            <li><a href="">HN002225 มานุวัฒน์ ชัยชนะ <small> <br> มารักษาล่าสุด 25/10/2012  :  5 ปีที่แล้ว</small></a></li>
			           
			        </ul>
			    </div>
			</div>
			<div class="uk-button-dropdown" data-uk-dropdown="{mode:'click'}">
				 <!-- This is the button toggling the dropdown -->
				 <button class="uk-button">
					 <i class=" uk-icon-stethoscope uk-icon-small"></i> 
					 <span class="uk-badge uk-badge-notification uk-badge-danger" id="countpatient">0</span>
				 </button>				
				 <!-- This is the dropdown -->
			    <div class="uk-dropdown uk-dropdown-small list-stack-job" >
			        <ul class="uk-nav uk-nav-dropdown" id="menu"> 
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
			            <li><a href="#confirmNad" data-uk-modal>1. มานุวัฒน์ ชัยชนะ <small>ถอนฟัน <br>วันที่ 06-05-2559</small></a></li>	            	
			            <li class="uk-nav-divider"></li>
			            <li><a href=""></a></li>
			        </ul>
			    </div>
			    <div id="confirmNad" class="uk-modal ">
				    <div class="uk-modal-dialog uk-form" >
				        <a class="uk-modal-close uk-close"></a>
				         <div class="uk-modal-header uk-text-left">ยืนยันการรักษา</div>
				         	<div class="uk-width-1-1 uk-overflow-container">
				         		
								<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
								    <thead>
								        <tr class="hd-table">  
								            <th class="uk-text-center">รหัสคนไข้</th>
								            <th class="uk-text-center">ชื่อคนไข้</th> 
								            <th class="uk-text-center">วันที่ก่ารรักษา</th>
								            <th class="uk-text-center"> </th>
								        </tr>
								    </thead> 
								    <tbody>
								    	<tr>  
									        <td class="uk-text-center">11001</td>
									        <td class="uk-text-center">มานุวัฒน์ ชัยชนะ</td>
									        <td class="uk-text-center">06-05-2559</td>
									        <td class="uk-text-right"><a href="#" class="uk-button uk-button-primary uk-button-small ">ยืนยันการรักษา</a></td>
										</tr>
									</tbody>
								</table>
							</div> 
							<br>
				    </div>
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
			            <li><a href="recall-all.jsp" class="uk-text-left">RE CALL 
			            	<span class="uk-badge uk-badge-notification uk-badge-danger uk-text-right noti2">1</span>
			            </a></li>
			            
			        </ul>
			    </div>
			</div>
			<a href="logout" class="uk-button">
				<i class="uk-icon-user-times uk-icon-small"></i>
				<span>ออกจากระบบ</span>
			</a>
		</div>
	</div>
</nav> 

<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap-datepicker-th.js"></script>
<script src="js/uikit.min.js"></script>
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
	   	patienShow();
		var timerId = setInterval(function() {  
			patienShow();
			//clearInterval(timerId);
		}, 5000);
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

