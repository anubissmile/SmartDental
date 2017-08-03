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

				<s:if test="servicePatModel == null">
					<div class="uk-alert uk-alert-warning" data-uk-alert>
						<li class="uk-alert-close uk-close"></li>
						<p>
							กรุณาเลือกคนไข้ก่อนสร้างการนัดหมาย 
							<a href="selectPatient">
								<button class="uk-button uk-button-success">
									<i class="uk-icon-user"></i> <span>เลือกคนไข้</span>
								</button>
							</a>
						</p>
					</div>
				</s:if>
				<s:else>
				<div class="uk-grid">
					<div class="uk-width-1-1 uk-margin-large"></div>
					<div class="uk-width-1-1">
						<h1 class="uk-margin-medium-left">
							รายการนัดหมายของ 
							<span id="ldc-doctor-name" 
								data-doctor-id="<s:property value='appointmentModel.doctorID' />"></span>
						</h1>
					</div>
					<div class="uk-width-1-1 uk-form" id="ldc-select-date-wrap">
						 <input type="text"
							name="datepicker" 
							placeholder="เลือกวัน"
							data-uk-datepicker="{format:'YYYY-MM-DD'}"
							id="selectDate"
							class="uk-form-medium uk-width-1-1">
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
				</s:else>
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
				<s:form action="post-add-appointment" method="post" class="uk-form" theme="simple">
					<div class="uk-modal-header">
						<h2><i class="uk-icon-calendar-plus-o"></i> <strong>เพิ่มรายการนัดหมาย</strong></h2>
					</div>
					<div class="uk-width-1-1 uk-overflow-container uk-panel">
						<div class="uk-grid uk-margin-remove">
							<div class="uk-width-1-3 uk-padding-remove">
								<h4 class="uk-margin-remove">วันที่</h4>
								<s:textfield type="text" 
									class="uk-form-large uk-form-width-large"
									id="ldc-inp-date"
									name="appointmentModel.date" />
							</div>
							<div class="uk-width-1-3">
								<h4 class="uk-margin-remove">เวลา</h4>
								<s:textfield type="text" 
									class="uk-form-large uk-form-width-large"
									id="ldc-inp-starttime"
									name="appointmentModel.timeStart" />
							</div>
							<div class="uk-width-1-3">
								<h4 class="uk-margin-remove">ถึง</h4>
								<s:textfield type="text" 
									class="uk-form-large uk-form-width-large"
									id="ldc-inp-endtime"
									name="appointmentModel.timeEnd" />
							</div>
							<div class="uk-width-1-2 uk-padding-remove uk-margin-medium-top">
								<h4 class="uk-margin-remove">เลือกอาการ</h4>
								<select name="symptom-select" 
									id="ldc-select-symptom" class="uk-form-large uk-form-width-large">
									<option value="0">เคลือบฟลูออไรด์</option>
									<option value="1">ตรวจสุขภาพฟัน</option>
									<option value="2">ขูดฟัน</option>
								</select>
							</div>
							<div class="uk-width-1-2 uk-margin-medium-top">
								<h4 class="uk-margin-remove">อาการ</h4>
								<s:textfield type="text" 
									id="ldc-inp-symptom" 
									class="uk-form-large uk-form-width-large"
									name="appointmentModel.symptom" />
							</div>
							<div class="uk-width-1-1 uk-padding-remove uk-margin-medium-top ">
								<h4 class="uk-margin-remove">คำแนะนำในการเตรียมตัวก่อนพบแพทย์</h4>
								<s:textarea class="uk-form-large" 
									maxlength="100"
									name="appointmentModel.description" />
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
									id="ldc-hid-inp-patient-hn" 
									name="appointmentModel.HN"
									value="%{servicePatModel.hn}"/>
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
	</div>
	<!-- Model Area -->

	<script type="text/javascript" src="js/weekcalendarscheduler/custom.doctor.weekcalendar.js"></script>
	<script>
    $(document).ready(function() {

    	/**
    	 * Load freeBusy.
    	 */
    	loadFreeBusy({
			startDateTime: new Date().toString('yyyy-MM-dd'), 
			endDatetime: new Date().toString('yyyy-MM-dd'),
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
	    			date: new Date().toString('yyyy-MM-dd')
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
    		$("#ldc-hid-inp-doctor-id").val(pageStat.userId[pageStat.calEvent.userId]);

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
    		});

    	}, '#ldc-select-symptom');

    	
    });

	</script>
	</body>
</html>