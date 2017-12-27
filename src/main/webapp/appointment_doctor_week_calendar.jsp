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
				<!-- Action error & messages -->
				<s:if test="%{#request.alertMSG != null}">
				<div class="uk-alert uk-alert-warning" data-uk-alert>
					<li class="uk-alert-close uk-close"></li>
					<p><s:property value="#request.alertMSG" /></p>
				</div>
				</s:if>
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

				<div class="uk-grid">
					<div class="uk-width-1-1 uk-margin-large"></div>
					<div class="uk-width-1-1">
						<h1 class="uk-margin-medium-left" id="ldc-header-title" 
							data-reference-code="<s:property value='appointmentModel.appointCode' />"
							data-reason="<s:property value='appointmentModel.reason' />"
							data-appointment-id="<s:property value='appointmentModel.appointmentID' />" >
							รายการนัดหมายของ 
							<span id="ldc-doctor-name" 
								data-doctor-id="<s:property value='appointmentModel.doctorID' />"
								data-branch-host-id="<s:property value='branchModel.branch_id' />"
								data-branch-host-code="<s:property value='branchModel.branch_code' />">
								<s:property value='doctorModel.pre_name' /> 							
								<s:property value='doctorModel.firstname_th' /> 							
								<s:property value='doctorModel.lastname_th' /> 							
							</span>
						</h1>
					</div>
					<div class="uk-width-1-1 uk-margin-medium uk-padding-remove-bottom" id="ldc-item-nav-list-view">
						<div class="uk-grid uk-padding-remove-bottom">
							<div class="uk-width-1-2">
								<ul class="uk-subnav uk-subnav-line uk-margin-left">
									<li><a href="appointment-week-calendar" 
									class="uk-icon-small uk-icon-calendar uk-divider-icon"> ปฏิทิน</a></li>
									<li><a href="getAppointmentList" 
									class="uk-icon-small uk-icon-list-ul uk-divider-icon"> รายการนัดหมาย</a></li>
								</ul>
							</div>
							<div class="uk-width-1-2">
								<div class="uk-form uk-grid" id="ldc-select-date-wrap">
									<div class="uk-width-2-4">
										<input type="text"
											name="datepicker" 
											placeholder="เลือกวัน"
											data-uk-datepicker="{format:'YYYY-MM-DD'}"
											id="selectDate"
											class="uk-form-medium uk-width-1-1">
									</div>
									<div class="uk-width-2-4" id="ldc-btn-date-wrap">
										<button class="uk-form-medium uk-button" id="ldc-btn-date-yesterday">
											<i class="uk-icon uk-icon-chevron-left"></i>
										</button>
										<button class="uk-form-medium uk-button" id="ldc-btn-date-today">
											<i class="uk-icon uk-icon-circle"></i>
										</button>
										<button class="uk-form-medium uk-button" id="ldc-btn-date-tomorrow">
											<i class="uk-icon uk-icon-chevron-right"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="uk-width-1-1 uk-padding-small">
						<div class="uk-panel uk-panel-box"
							data-uk-sticky="{top: -100, getWidthFrom: 'body', animation: 'uk-animation-fade'}">
							<ul class="uk-subnav uk-subnav-line uk-margin-left" id="ldc-doctor-detail"></ul>
						</div>
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
		<div id="ldc-modal-doonclick" class="uk-modal">
			<div class="uk-modal-dialog uk-modal-dialog-large uk-form">
				<a class="uk-modal-close uk-close"></a>
				<div class="uk-modal-header">
					<h2><i class="uk-icon-info"></i> <strong>คุณต้องการทำสิ่งใด</strong></h2>
				</div>
				<div class="uk-width-1-1 uk-overflow-container uk-panel">
					<div class="uk-grid uk-margin-remove uk-grid-divider">
						<!-- <a class="uk-width-1-3 uk-panel-hover uk-text-center" 
							tabindex="2" 
							id="ldc-modal-detail">
							<h1>
								<strong><i class="uk-icon-check-circle-o"></i><br><span>ดูรายละเอียด</span></strong>
							</h1>
						</a> -->
						<a class="uk-width-1-3 uk-panel-hover uk-text-center" 
							tabindex="1" 
							id="ldc-modal-edit-status" 
							href="getAppointmentpatient-">
							<h1>
								<strong><i class="uk-icon-headphones"></i><br>
									<span>สถานะการติดต่อ</span>
								</strong>
							</h1>
						</a>
						<a class="uk-width-1-3 uk-panel-hover uk-text-center" 
							tabindex="1" 
							id="ldc-modal-appointment-delete" 
							href="getAppointmentList-">
							<h1>
								<strong><i class="uk-icon-times-circle-o"></i><br>
									<span>รายงานตัวนัดหมาย</span>
								</strong>
							</h1>
						</a>
						</a>
						<a class="uk-width-1-3 uk-panel-hover uk-text-center" 
							tabindex="1" 
							id="ldc-modal-appointment-edit"
							data-uk-modal="{target:'#ldc-modal-editevent'}">
							<h1>
								<strong><i class="uk-icon-sliders"></i><br>
									<span>แก้ไขรายละเอียดนัดหมาย</span>
								</strong>
							</h1>
						</a>
					</div>
				</div>
			</div>
		</div>

		<!-- Edit appointment modal -->
		<div id="ldc-modal-editevent" class="uk-modal">
			<div class="uk-modal-dialog uk-modal-dialog-large uk-form">
				<!-- <a class="uk-modal-close uk-close"></a> -->
				<s:form action="post-edit-appointment" method="post" class="uk-form" theme="simple">
					<div class="uk-modal-header">
						<h2><i class="uk-icon-calendar-plus-o"></i> <strong>แก้ไขการนัดหมาย</strong></h2>
					</div>
					<div class="uk-width-1-1 uk-overflow-container uk-panel">
						<div class="uk-grid uk-margin-remove">
							<div class="uk-width-1-3 uk-padding-remove">
								<h4 class="uk-margin-remove">วันที่</h4>
								<s:textfield type="text" 
									class="uk-form-large uk-form-width-large"
									id="ldc-edit-inp-date"
									name="appointmentModel.date" 
									readonly="true" />
							</div>
							<div class="uk-width-1-3">
								<h4 class="uk-margin-remove">เวลา</h4>
								<s:textfield type="text" 
									class="uk-form-large uk-form-width-large"
									id="ldc-edit-inp-starttime"
									name="appointmentModel.timeStart" 
									readonly="true" />
							</div>
							<div class="uk-width-1-3">
								<h4 class="uk-margin-remove">ถึง</h4>
								<s:textfield type="text" 
									class="uk-form-large uk-form-width-large" 
									id="ldc-edit-inp-endtime" 
									name="appointmentModel.timeEnd" 
									readonly="true" />
							</div>
							<div class="uk-width-1-3 uk-padding-remove uk-margin-medium-top">
								<h4 class="uk-margin-remove">เลือกอาการ</h4>
								<!-- <select name="symptom-select" 
									id="ldc-edit-select-symptom" class="uk-form-large uk-form-width-large">
									<option value="0">เคลือบฟลูออไรด์</option>
									<option value="1">ตรวจสุขภาพฟัน</option>
									<option value="2">ขูดฟัน</option>
								</select> -->
								<s:select list="symptomMap" 
									label="เลือกอาการ"
									headerKey="-1"
									headerValue="รายการอาการ"
									name="symptom-select" 
									class="uk-form-large uk-form-width-large"
									id="ldc-edit-select-symptom"
								/>
							</div>
							<div class="uk-width-1-3 uk-margin-medium-top">
								<h4 class="uk-margin-remove">อาการ</h4>
								<s:textfield type="text" 
									id="ldc-edit-inp-symptom" 
									class="uk-form-large uk-form-width-large"
									name="appointmentModel.symptom" />
							</div>
							<div class="uk-width-1-3 uk-margin-medium-top">
								<h4 class="uk-margin-remove">เตือนล่วงหน้า / วัน</h4>
								<s:select class="uk-form-large uk-form-width-large"
									id="ldc-edit-inp-remind" 
									name="appointmentModel.remindDateCount" 
									list="{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}" />
							</div>
							<div class="uk-width-1-1 uk-padding-remove uk-margin-medium-top ">
								<h4 class="uk-margin-remove">คำแนะนำในการเตรียมตัวก่อนพบแพทย์</h4>
								<s:textarea class="uk-form-large" 
									maxlength="100"
									id="ldc-edit-reccommend"
									name="appointmentModel.description" />
								<s:textfield type="hidden" 
									id="ldc-edit-hid-inp-symptom-id" 
									class="uk-form-large uk-form-width-large" 
									name="appointmentModel.symptomID" />
								<s:textfield type="hidden" 
									id="ldc-edit-hid-inp-startdatetime" 
									name="appointmentModel.dateStart" 
									value="" />
								<s:textfield type="hidden" 
									id="ldc-edit-hid-inp-enddatetime" 
									name="appointmentModel.dateEnd" 
									value="" />
								<s:textfield type="hidden" 
									id="ldc-edit-hid-inp-startdatetimezone" 
									name="appointmentModel.dateTimeZoneStart" 
									value="" />
								<s:textfield type="hidden" 
									id="ldc-edit-hid-inp-enddatetimezone" 
									name="appointmentModel.dateTimeZoneEnd" 
									value="" />
								<s:textfield type="hidden" 
									id="ldc-edit-hid-inp-doctor-id" 
									name="appointmentModel.doctorID" 
									value="" />
								<s:textfield type="hidden" 
									id="ldc-edit-hid-inp-patient-hn" 
									name="appointmentModel.HN" />
								<s:textfield type="hidden" 
									id="ldc-edit-hid-inp-appointment-id" 
									name="appointmentModel.appointmentID" />
								<s:textfield type="hidden" 
									id="ldc-edit-hid-inp-appointment-code" 
									name="appointmentModel.appointmentCode" />
							</div>
						</div>
					</div>
					<div class="uk-modal-footer">
						<div class="uk-grid uk-margin-remove uk-grid-divider">
							<button class="uk-width-1-2 uk-panel-hover uk-text-center" 
								tabindex="2" 
								id="ldc-edit-appointment">
								<h1>
									<strong><i class="uk-icon-check-circle-o"></i><br><span>แก้ไขนัดหมาย</span></strong>
								</h1>
							</button>
							<a class="uk-width-1-2 uk-panel-hover uk-text-center" 
								tabindex="1" 
								id="ldc-cancel-edit-frm">
								<h1>
									<strong><i class="uk-icon-times-circle-o"></i><br><span>ยกเลิก</span></strong>
								</h1>
							</a>
						</div>
					</div>
				</s:form>
			</div>
		</div>
		<!-- Edit appointment modal -->

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
				<s:form action="post-add-appointment" method="post" class="uk-form" theme="simple">
					<div class="uk-modal-header">
						<h2>
							<i class="uk-icon-calendar-plus-o"></i>&nbsp;&nbsp;
							<strong id="ldc-modal-title-name">เพิ่มรายการนัดหมาย</strong>
						</h2>
					</div>
					<div class="uk-width-1-1 uk-overflow-container uk-panel">
						<div class="uk-grid uk-margin-remove">
							<div class="uk-width-1-3 uk-padding-remove">
								<h4 class="uk-margin-remove">วันที่</h4>
								<s:textfield type="text" 
									class="uk-form-large uk-form-width-large"
									id="ldc-inp-date"
									name="appointmentModel.date" 
									readonly="true" />
							</div>
							<div class="uk-width-1-3">
								<h4 class="uk-margin-remove">เวลา</h4>
								<s:textfield type="text" 
									class="uk-form-large uk-form-width-large"
									id="ldc-inp-starttime"
									name="appointmentModel.timeStart" 
									readonly="true" />
							</div>
							<div class="uk-width-1-3">
								<h4 class="uk-margin-remove">ถึง</h4>
								<s:textfield type="text" 
									class="uk-form-large uk-form-width-large"
									id="ldc-inp-endtime"
									name="appointmentModel.timeEnd" 
									readonly="true" />
							</div>
							<div class="uk-width-1-3 uk-padding-remove uk-margin-medium-top">
								<h4 class="uk-margin-remove">เลือกอาการ</h4>
								<!-- <select name="symptom-select" 
									id="ldc-select-symptom" class="uk-form-large uk-form-width-large">
									<option value="0">เคลือบฟลูออไรด์</option>
									<option value="1">ตรวจสุขภาพฟัน</option>
									<option value="2">ขูดฟัน</option>
								</select> -->
								<s:select list="symptomMap" 
									label="เลือกอาการ"
									headerKey="-1"
									headerValue="รายการอาการ"
									name="symptom-select" 
									class="uk-form-large uk-form-width-large"
									id="ldc-select-symptom"
								/>
							</div>
							<div class="uk-width-1-3 uk-margin-medium-top">
								<h4 class="uk-margin-remove">อาการ</h4>
								<s:textfield type="text" 
									id="ldc-inp-symptom" 
									class="uk-form-large uk-form-width-large"
									name="appointmentModel.symptom" />
							</div>
							<div class="uk-width-1-3 uk-margin-medium-top">
								<h4 class="uk-margin-remove">เตือนล่วงหน้า / วัน</h4>
								<s:select class="uk-form-large uk-form-width-large"
									id="ldc-inp-remind" 
									name="appointmentModel.remindDateCount" 
									list="{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}"
									headerKey="1"
									headerValue="เลือกวันเตือนล่วงหน้า" />
							</div>
							<div class="uk-width-1-1 uk-padding-remove uk-margin-medium-top ">
								<h4 class="uk-margin-remove">คำแนะนำในการเตรียมตัวก่อนพบแพทย์</h4>
								<s:textarea class="uk-form-large" 
									maxlength="100"
									name="appointmentModel.description"
									id="ldc-txtarea-description" />
								<s:textfield type="hidden" 
									id="ldc-hid-inp-symptom-id" 
									class="uk-form-large uk-form-width-large"
									name="appointmentModel.symptomID" />
								<s:textfield type="hidden" 
									id="ldc-hid-inp-startdatetime" 
									name="appointmentModel.dateStart" 
									value="" />
								<s:textfield type="hidden" 
									id="ldc-hid-inp-enddatetime" 
									name="appointmentModel.dateEnd" 
									value="" />
								<s:textfield type="hidden" 
									id="ldc-hid-inp-startdatetimezone" 
									name="appointmentModel.dateTimeZoneStart" 
									value="" />
								<s:textfield type="hidden" 
									id="ldc-hid-inp-enddatetimezone" 
									name="appointmentModel.dateTimeZoneEnd" 
									value="" />
								<s:textfield type="hidden" 
									id="ldc-hid-inp-doctor-id" 
									name="appointmentModel.doctorID" 
									value="" />
								<s:textfield type="hidden" 
									id="ldc-hid-inp-lab-id" 
									name="appointmentModel.lab_id" />
								<s:textfield type="hidden" 
									id="ldc-hid-inp-patient-hn" 
									name="appointmentModel.HN"
									value="%{servicePatModel.hn}"/>
								<!-- postpone -->
								<s:hidden id="ldc-hid-inp-postpone-reason" 
									name="appointmentModel.reason" />
								<s:hidden id="ldc-hid-inp-postpone-refcode" 
									name="appointmentModel.postponeReferenceID" />
								<s:hidden id="ldc-hid-inp-postpone-appoint-id" 
									name="appointmentModel.appointmentID" />
								<!-- postpone -->
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
				</s:form>
			</div>
		</div>
		
		<!-- postpone details modal -->
		<div class="uk-modal uk-animation-scale-down" id="ldc-modal-postpone-detail">
		    <div class="uk-modal-dialog uk-modal-dialog-large uk-form" >
		        <a class="uk-modal-close uk-close"></a>
		     	<div class="uk-modal-header">
		         	<h2>
		         		<i class="uk-icon-paper-plane"></i>&nbsp;&nbsp;
		         		รายละเอียดนัดหมายที่ถูกเลื่อน
		         	</h2>
		 		</div>
		     	<div class="uk-width-1-1 uk-overflow-container">
			     	<div class="uk-grid">
			     		<div class="uk-width-1-2 uk-text-right uk-h3">hn : </div>
			     		<div class="uk-width-1-2 uk-text-left uk-h3" id="ldc-postpone-hn"></div>
			     		<div class="uk-width-1-2 uk-text-right uk-h3">รหัส : </div>
			     		<div class="uk-width-1-2 uk-text-left uk-h3" id="ldc-postpone-appoint-code"></div>
			     		<div class="uk-width-1-2 uk-text-right uk-h3">วันที่ : </div>
			     		<div class="uk-width-1-2 uk-text-left uk-h3" id="ldc-postpone-date"></div>
			     		<div class="uk-width-1-2 uk-text-right uk-h3">เวลา : </div>
			     		<div class="uk-width-1-2 uk-text-left uk-h3" id="ldc-postpone-time-range"></div>
			     		<div class="uk-width-1-2 uk-text-right uk-h3">อาการ : </div>
			     		<div class="uk-width-1-2 uk-text-left uk-h3" id="ldc-postpone-symptom"></div>
			     		<div class="uk-width-1-2 uk-text-right uk-h3">คำแนะนำ : </div>
			     		<div class="uk-width-1-2 uk-text-left uk-h3" id="ldc-postpone-recommend"></div>
			     	</div>
			    </div> 
				<br>
		    </div>
		</div>
		<!-- postpone details modal -->
	</div>
	<!-- Model Area -->

	<script type="text/javascript" src="js/weekcalendarscheduler/custom.doctor.weekcalendar.js"></script>
	<script>
    $(document).ready(function() {


    	/**
    	 * Checking date default
    	 * - If doesn't exist then set it.
    	 * - Setting date button (yesterday|today|tomorrow)
    	 */
    	initDate({
    		wrap: "#ldc-btn-date-wrap", 
    		yesterday: {
    			id: "#ldc-btn-date-yesterday", 
    			act: function(){
					if(isStorageSupport()){
						let date = new Date(sessionStorage.dateDefault);
						date.setDate(date.getDate() - 1);
						setDateDefault(date);
						setGotoDate(pageStat.calendarInstance, sessionStorage.dateDefault);
					}
    			}
    		}, 
    		tomorrow: {
    			id: "#ldc-btn-date-tomorrow", 
    			act: function(){
					let date = new Date(sessionStorage.dateDefault);
					date.setDate(date.getDate() + 1);
					setDateDefault(date);
					setGotoDate(pageStat.calendarInstance, sessionStorage.dateDefault);
    			}
    		}, 
    		today: {
    			id: "#ldc-btn-date-today", 
    			act: function(){
					let date = new Date();
					setDateDefault(date);
					setGotoDate(pageStat.calendarInstance, sessionStorage.dateDefault);
    			}
    		}
       	});

    	/**
    	 * if is postpone set the local storage.
    	 */
    	isPostpone();

    	/**
    	 * Load freeBusy.
    	 * 
    	 */
    	/*Find date default.*/
    	let onLoadDate = new Date();
    	if(isStorageSupport()){
    		onLoadDate = new Date(sessionStorage.dateDefault);
    	}


    	/**
    	 * Load freeBusy.
    	 */
    	loadFreeBusy({
			startDateTime: onLoadDate.toString('yyyy-MM-dd'), 
			endDatetime: onLoadDate.toString('yyyy-MM-dd'),
			doctorID: $("#ldc-doctor-name").data('doctor-id'),
    		onSuccess: false, 
    		onFail: false, 
    		onAlways: function(){
	    		/**
	    		 * Generate doctor detail button.
	    		 */
	    		/*loopDoctorButton({
	    			target: "#ldc-doctor-detail",
	    			callBack: false
	    		});*/

    			/**
	    		 * Get agenda list.
	    		 */
	    		loadAppointment({
	    			onSuccess: false,
	    			onFail: false,
	    			onAlways: function(){
	    				callWeekCalendar();
	    			},
					doctorID: $("#ldc-doctor-name").data('doctor-id'),
	    			date: onLoadDate.toString('yyyy-MM-dd')
	    		});
    		}
    	});

    	/**
    	 * Select date listener.
    	 */
    	selectDateListener(function(thisObj){
    		/**
    		 * Clear pageStat
    		 */
    		clearPageStat();

    		/**
    		 * Set date default.
    		 */
    		setDateDefault(thisObj.val());

    		/**
    		 * Change page to the selected date.
    		 */
			pageStat.calendarInstance.weekCalendar('gotoDate', thisObj.val());

			/**
			 * Update freeBusy & appointment
			 */
			// console.log("thisObj Date format : ", new Date(thisObj.val()).toString('yyyy-MM-dd'));
			loadFreeBusy({
				startDateTime: new Date(thisObj.val()).toString('yyyy-MM-dd'), 
				endDatetime: new Date(thisObj.val()).toString('yyyy-MM-dd'), 
				doctorID: $("#ldc-doctor-name").data('doctor-id'),
	    		onSuccess: false, 
	    		onFail: false,
	    		onAlways: function(){
		    		/**
		    		 * Generate doctor detail button.
		    		 */
		    		/*loopDoctorButton({
		    			target: "#ldc-doctor-detail"
		    		});*/
	    		
	    			/**
		    		 * Get agenda list.
		    		 */
		    		loadAppointment({
						doctorID: $("#ldc-doctor-name").data('doctor-id'),
		    			date: new Date(thisObj.val()).toString('yyyy-MM-dd'),
		    			onSuccess: false,
		    			onFail: false,
		    			onAlways: function(){
		    				/**
		    				 * Recall Weekcalendar for update column details
		    				 */
		    				callWeekCalendar();

		    				/**
		    				 * Refresh appointment.
		    				 * (It generate event & appointment dataset in json type already.
		    				 * - pageStat.events & pageStat.agenda
		    				 * You just refresh it!)
		    				 */
		    				pageStat.calendarInstance.weekCalendar('refresh');

		    				/**
		    				 * Generate doctor detail nav.
		    				 */
		    				loopDoctorButton({
		    					navID: "#ldc-doctor-detail"
		    				});
		    			}
		    		});
	    		}
			});

    	});

    	/**
    	 * Set prevent drag agenda outside the lane and itself.
    	 */
    	$("#calendar").on('mousemove', '.wc-cal-event.ui-corner-all.ui-draggable', function(event) {
    		event.preventDefault();
    		$(this).draggable({axis: 'y', containment: $(this)});
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
    		$("#ldc-hid-inp-doctor-id").val($("#ldc-doctor-name").data('doctor-id'));
    		// $("#ldc-hid-inp-doctor-id").val(pageStat.userId[pageStat.calEvent.userId]);

    		/*start postpone*/
    		let reason = "", refcode = "", appID = "";
    		if(typeof(Storage) !== "undefined"){
    			if(typeof(localStorage.postpone) !== "undefined"){
    				reason = JSON.parse(localStorage.postpone).reason;
    				refcode = JSON.parse(localStorage.postpone).refCode;
    				appID = JSON.parse(localStorage.postpone).appID;
    				hn = JSON.parse(localStorage.postpone).hn;
    				console.log("HN", hn);

		    		/*Set title*/
		            let title = "เลื่อนนัดหมายจาก " 
		            	+ '<a href="#" id="ldc-show-postpone-detail" title="ดูรายละเอียด" data-uk-modal="{target:\'#ldc-modal-postpone-detail\', modal: false}">' 
		            	+ refcode + '</a>';
		            $("#ldc-modal-title-name").html(title);
		            $("button#ldc-add-appointment").find('span').html("เลื่อนนัดหมาย");

		            /*Set hn value*/
		            $("#ldc-hid-inp-patient-hn").val(hn);

		            setModalEditAppointment({
		            	id: appID,
		            	fail: false,
		            	always: false,
		            	done: function(data){
		            		console.log("DATA", data);
		            		let dateEnd = new Date(data.dateEnd);
		            		let dateStart = new Date(data.dateStart);
		            		$("#ldc-postpone-hn").text(data.HN);
		            		$("#ldc-postpone-appoint-code").text(data.appointmentCode);
		            		$("#ldc-postpone-time-range").text(
		            			dateStart.toString("HH:mm") + 
		            			" - " + 
		            			dateEnd.toString("HH:mm")
	            			);
		            		$("#ldc-postpone-date").text(dateStart.toString("dd/MM/yyyy"));
		            		$("#ldc-postpone-symptom").text(data.description);
		            		$("#ldc-postpone-recommend").text(data.recommend);

		            		/*Cloning info put into edit modal.*/
		            		$("#ldc-select-symptom").val(data.symptomID);
		            		$("#ldc-inp-symptom").val(data.description);
		            		$("#ldc-inp-remind").val(data.remindDate);
		            		/*Hidden input.*/
		            		$("#ldc-hid-inp-symptom-id").val(data.symptomID);
		            		$("#ldc-txtarea-description").val(data.recommend);
		            	}
		            });

		            /*Set on postpone details showning up*/
		            /*$("#ldc-modal-title-name").on('click', '#ldc-show-postpone-detail', function(event) {
		            	event.preventDefault();
		            });*/
    			}
    		}
    		$("#ldc-hid-inp-postpone-reason").val(reason);
    		$("#ldc-hid-inp-postpone-refcode").val(refcode);
    		$("#ldc-hid-inp-postpone-appoint-id").val(appID);
    		/*end postpone*/

    		removeEventListener(
    			function(){
    				UIkit.modal('#ldc-modal-add-frm').hide();
    			}, 
    			'#ldc-calcel-add-frm'
			);
    	});

    	/**
    	 * Set symptom in select2
    	 */
    	setModalSelect2(function(){
    		$("#ldc-modal-add-frm").on('change', '#ldc-select-symptom', function(event) {
    			event.preventDefault();
    			var txt = $(this).select2('data');
    			$("#ldc-inp-symptom").val(txt[0].text);
    			$("#ldc-hid-inp-symptom-id").val(txt[0].id);

    			/**
    			 * Set input activities.
    			 */
    			if(txt[0].id == 1){
    				$("#ldc-inp-symptom").removeProp('readonly');
    				$("#ldc-inp-symptom").val("");
    				$("#ldc-inp-symptom").focus();
    			}else{
    				$("#ldc-inp-symptom").prop('readonly', 'readonly');
    			}
    		});

    	}, '#ldc-select-symptom');



    	/**
    	 * Set edit appointment modal.
    	 */
    	$("#modal-group").on('click', '#ldc-modal-appointment-edit', function(event) {
    		event.preventDefault();
	    	setModalEditAppointment({
	    		done: function(data){
    				console.log("DATA", data);
	    			let dateEnd = new Date(data.dateEnd);
	    			let dateStart = new Date(data.dateStart);
	    			$("#ldc-edit-inp-date").val(dateStart.toString("dd/MM/yyyy"));
	    			$("#ldc-edit-inp-starttime").val(dateStart.toString("HH:mm"));
	    			$("#ldc-edit-inp-endtime").val(dateEnd.toString("HH:mm"));
	    			$("#ldc-edit-reccommend").val(data.recommend);
	    			$("#ldc-edit-inp-symptom").val(data.description);
	    			$("#ldc-edit-inp-remind").val(data.remindDate);
					$("#ldc-edit-select-symptom").val(data.symptomID);

	    			// For hidden input.
	    			$("#ldc-edit-hid-inp-symptom-id").val(data.symptomID);
	    			$("#ldc-edit-hid-inp-startdatetime").val(data.dateStart);
	    			$("#ldc-edit-hid-inp-startdatetimezone").val(data.dateStart);
	    			$("#ldc-edit-hid-inp-enddatetime").val(data.dateEnd);
	    			$("#ldc-edit-hid-inp-enddatetimezone").val(data.dateEnd);
	    			$("#ldc-edit-hid-inp-patient-hn").val(data.HN);
	    			$("#ldc-edit-hid-inp-doctor-id").val(data.doctorID);
	    			$("#ldc-edit-hid-inp-appointment-id").val(data.appointmentID);
	    			$("#ldc-edit-hid-inp-appointment-code").val(data.appointmentCode);

	    			/**
	    			 * On cancel
	    			 */
	    			$("#ldc-modal-editevent").on('click', '#ldc-cancel-edit-frm', function(event) {
	    				event.preventDefault();
	    				UIkit.modal('#ldc-modal-editevent').hide();
	    			});

	    			/**
	    			 * Set symptom select2
	    			 */
	    			setModalSelect2(
						function(){
							$("#ldc-modal-editevent").on('change', '#ldc-edit-select-symptom', function(event) {
								event.preventDefault();
								// Get data from select2.
				    			var txt = $(this).select2('data');
				    			$("#ldc-edit-inp-symptom").val(txt[0].text);
								$("#ldc-edit-hid-inp-symptom-id").val(txt[0].id);

				    			/**
				    			 * Set input activities.
				    			 */
				    			if(txt[0].id == 1){
				    				$("#ldc-edit-inp-symptom").removeProp('readonly');
				    				$("#ldc-edit-inp-symptom").val("");
				    				$("#ldc-edit-inp-symptom").focus();
				    			}else{
				    				$("#ldc-edit-inp-symptom").prop('readonly', 'readonly');
				    			}
							});
						},
						"#ldc-edit-select-symptom"
					);
	    		},
	    		fail : false,
	    		always : false,
	    		id : pageStat.calEvent.id
	    	});
    	});

    	
    });

	</script>
	</body>
</html>