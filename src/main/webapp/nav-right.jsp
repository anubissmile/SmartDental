<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="uk-panel uk-panel-box uk-text-center"> 
	<h4 style="margin:5px auto 35px;" class="uk-panel-title "><img alt="LDC" src="img/logo.png"></h4>		
	<ul class="uk-nav uk-nav-side menu-right" data-uk-nav>
		<li class="uk-nav-divider"></li>
		<li class="m-patient"><a href="viewPatientDetail"><i class="uk-icon-users uk-icon-medium"></i><br>คนไข้</a></li>
		<li class="uk-nav-divider"></li>
		<li class="m-appoint"><a href="appointment"><i class="uk-icon-calendar uk-icon-medium"></i><br>นัดหมาย</a></li>
		<li class="uk-nav-divider"></li>
		<li class="m-treatment"><a href="treatmentWaitingBegin"><i class="uk-icon-medkit uk-icon-medium"></i><br>การรักษา</a></li>
		<li class="uk-nav-divider"></li>
		<li class="m-finance"><a href="financeBegin"><i class="uk-icon-calculator uk-icon-medium"></i><br>การเงิน</a></li>
		<li class="uk-nav-divider"></li>
		<li class="m-document"><a href="Document?dt=All"><i class="uk-icon-file-o uk-icon-medium"></i><br>เอกสาร</a></li>
		<li class="uk-nav-divider"></li>
		<li class="m-deliver"><a href="deliver.jsp"><i class="uk-icon-ambulance uk-icon-medium"></i><br>ส่งตัว</a></li>
		<li class="uk-nav-divider"></li>
		<li class="m-checkin"><a href="checkin.jsp"><i class="uk-icon-calendar-check-o uk-icon-medium"></i><br>ลงเวลา</a></li>
		<li class="uk-nav-divider"></li>
		<li class="m-complaint"><a href="complaint.jsp"><i class="uk-icon-exclamation-circle uk-icon-medium"></i><br>ร้องเรียน</a></li>
		<li class="uk-nav-divider"></li>
		<li class="m-setting"><a href="setting.jsp"><i class="uk-icon-cog uk-icon-medium"></i><br>ตั้งค่า</a></li>
	</ul>
</nav>