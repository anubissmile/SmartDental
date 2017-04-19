<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:ลงเวล</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<form id="service" action="" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-calendar-check-o"></i> ลงเวลาแพทย์</h3>
								    <div class="uk-grid uk-grid-small uk-form "> 
		                             	<div class="uk-width-2-10">
		                             		<div class="uk-grid uk-grid-small">
			                             	<input name="teatmentModel.treatment_group_code" placeholder="รหัสแพทย์" class="uk-width-5-6" type="text">
			                             	<a href="#doctor" class="uk-button uk-button-primary uk-width-1-6" id="openmodal" data-uk-modal="{bgclose:false}">
												<i class="uk-icon-search"></i>
											</a> 
											</div>
		                            	</div>
		                            	 <div class="uk-form-icon uk-width-2-10">
		                            	 	<select id="treatment_type" name="teatmentModel.labmode_id" required class="uk-width-1-1">
											   <option value="">เลือกการกระทำ</option>
											     
								      			<option value="1">เข้างาน</option>
												  
								      			<option value="0">ลางาน</option>
												  
								      			<option value="3">ฉุกเฉิน</option>
												
									   		</select>
		                            	 </div>
		                            	<div class="uk-form-icon uk-width-2-10"> 
											 <div class="clockpicker pull-center" data-placement="left" data-align="top" data-autoclose="true">
												<input class=" uk-width-1-1 uk-text-center" type="text"  value="00:00"  name="docTimeM.time_in_mon" placeholder="เข้างาน">
											</div> 
		                            	</div> 
		                            	<div class="uk-form-icon uk-width-3-10">
		                            		<button class="uk-button uk-button-success " id="btnsave" type="submit" name="save">เพิ่มหมวดการรักษา</button>
		                            	</div>
								 	</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="uk-grid ">
						<div class="uk-width-1-1 ">
							<div class="uk-panel uk-panel-box">
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-calendar"></i> รายชื่อแพทย์ลงตรวจ</h3>
								</div>
								<div class="uk-width-1-1 uk-overflow-container">
								
								</div>
							</div>
						</div> 
					</div>
					
					
				</div>	
					</form>
			</div>
		</div>
		<div id="doctor" class="uk-modal ">
		    <div class="uk-modal-dialog uk-form uk-modal-dialog-large" >
		        <a class="uk-modal-close uk-close"></a>
		         <div class="uk-modal-header"><h2><i class="uk-icon-medkit"></i> เพิ่มการรักษา</h2></div>
		         <div class="uk-grid">
			         	<div class="uk-width-1-1">
			         		<h3>รายการรักษา</h3>
							<table id="doctorTable" class="uk-table uk-table-hover uk-table-striped uk-table-condensed" >
							    <thead>
							        <tr class="hd-table"> 
							            <th>รหัสแพทย์</th>
							            <th>ชื่อ-นามสกลุ TH</th> 
							            <th>ชื่อ-นามสกลุ EN</th> 
							            <th>ชื่อเล่น</th>
							        </tr>
							    </thead>
							</table>
						</div>
			         	 	
		         	 </div>
		         <div class="uk-modal-footer uk-text-right"> 
		         	<button class="uk-button uk-button-success uk-modal-close">ตกลง</button>
	        			<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button> 
		         </div>
		    </div>
		</div> 
		
		<script src="js/jquery.nestable.js"></script>
		<script>
			
			$(document).ready(function(){
				var dttable 
				$( ".m-checkin" ).addClass( "uk-active" );
				$('.clockpicker').clockpicker();
			
			
			$("#openmodal").click(function(){
				$("#doctorTable").DataTable({
					   		"destroy": true,
					        "ajax": "ajax/ajax-getdoctor.jsp",
					        "columns": [
					            { data: "doc_id" },
					            { data: "name_th" },
					            { data: "name_en" },
					            { data: "nick" }
					        ]
				});
				$('#doctorTable tbody').on('click', 'tr', function () {
			        alert( this );
			    } );
			});
			 	
			});
		</script>
	</body>
</html>