<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:ตั้งค่า</title>
	</head>
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<a class="uk-button uk-button-success" href="branchBegin">สาขา</a>
				<a class="uk-button uk-button-success" href="brandBegin">แบรนด์</a>
				<a class="uk-button " href="promotion-hd.jsp">โปรโมชั่น</a>
				<div class="uk-button-dropdown" data-uk-dropdown aria-haspopup="true" aria-expanded="false">
                   <button class="uk-button ">Gift Card <i class="uk-icon-caret-down"></i></button>
                   <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" style="top: -198px; left: 0px;">
                       <ul class="uk-nav uk-nav-dropdown"> 
                            <li><a href="">Gift Card</a></li> 
                            <li><a href="">Gift Voucher</a></li> 
                            <li><a href="">บัตรส่วนลดจากแพทย์</a></li> 
                       </ul>
                   </div>
               </div>
				<div class="uk-button-dropdown" data-uk-dropdown aria-haspopup="true" aria-expanded="false">
                   <button class="uk-button uk-button-success">การรักษา <i class="uk-icon-caret-down"></i></button>
                   <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" style="top: -198px; left: 0px;">
                       <ul class="uk-nav uk-nav-dropdown">
                       		<li class="uk-nav-header">ตั้งค่า</li>
                            <li><a href="treatmentBegin">เพิ่มการรักษา</a></li>
                            <li><a href="treatmentGroupBegin">หมวดการรักษา</a></li>
                            <li><a href="labModeBegin">กลุ่มการรักษา</a></li>
                       </ul>
                   </div>
               </div>
				<div class="uk-button-dropdown" data-uk-dropdown aria-haspopup="true" aria-expanded="false">
                   <button class="uk-button ">แพทย์ <i class="uk-icon-caret-down"></i></button>
                   <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" style="top: -198px; left: 0px;">
                       <ul class="uk-nav uk-nav-dropdown"> 
                       		<li class="uk-nav-header">ตั้งค่าแพทย์</li>
                            <li><a href="Doctor">ข้อมูลแพทย์</a></li> 
                            <li><a href="DoctorType">ประเภทแพทย์</a></li> 
                           
                           <!--  <li><a href="doctor-time.jsp">วันที่ทำงาน</a></li> 
                            <li><a href="doctor-time-edit.jsp">เวลาเข้างาน</a></li> 
                            <li><a href="doctor-standard.jsp">ค่า Standby</a></li>  
                             -->
                       </ul>
                   </div>
               </div>
               <div class="uk-button-dropdown" data-uk-dropdown aria-haspopup="true" aria-expanded="false">
                   <button class="uk-button uk-button-success">งาน Lab <i class="uk-icon-caret-down"></i></button>
                   <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" style="top: -198px; left: 0px;">
                       <ul class="uk-nav uk-nav-dropdown"> 
                       		<li class="uk-nav-header">ตั้งค่า Lab & บริการ</li>
                       		<li><a href="labserviceBegin">ตั้งค่างาน lab</a></li>
                            <li><a href="labBegin">ข้อมูลบริษัท(Lab)</a></li> 
                            <li><a href="labBranchBegin">ข้อมูลบริษัท(สาขา)</a></li> 
                            <li><a href="serviceBegin">บริการ Lab</a></li> 
                            
                       </ul>
                   </div>
               </div>
               <div class="uk-button-dropdown" data-uk-dropdown  aria-haspopup="true" aria-expanded="false">
                   <button class="uk-button uk-button-success">สินค้า <i class="uk-icon-caret-down"></i></button>
                   <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" style="top: -198px; left: 0px;">
                       <ul class="uk-nav uk-nav-dropdown"> 
                       		<li class="uk-nav-header">รายการสินค้าและยา</li>
                       		<li><a href="getProductList">สินค้า</a></li>
                       		<li><a href="getMedicineList">ยา</a></li>
                       		<li><a href="getMaterialList">วัสดุ</a></li>
                            
                            <li class="uk-nav-header">ตั้งค่าสินค้า</li>
                            <li><a href="pgmBegin">กลุ่มสินค้า</a></li>
                            <li><a href="pbmBegin">แบรนด์สินค้า</a></li>
                            <li><a href="ptmBegin">ประเภทสินค้า</a></li>
                            <li><a href="pumBegin">หน่วยสินค้า</a></li>
                       </ul>
                   </div>
               </div>
              <div class="uk-button-dropdown" data-uk-dropdown  aria-haspopup="true" aria-expanded="false">
                   <button class="uk-button uk-button-success">ตั้งค่าข้อมูลบุคคล<i class="uk-icon-caret-down"></i></button>
                   <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" style="top: -198px; left: 0px;">
                       <ul class="uk-nav uk-nav-dropdown"> 
                            <li class="uk-nav-header">ตั้งค่าข้อมูลคนไข้</li>
                            <li><a href="SettingPersonPreName">ตั้งคำนำหน้า</a></li>
                            <li><a href="SettingPersonTelType">ตั้งค่าเบอร์โทร</a></li>
                            <li><a href="SettingPersonAddrType">ตั้งค่าที่อยู่</a></li>
                            <li><a href="SettingPersonMarried">ตั้งค่าสถานะสมรส</a></li>
                            <li><a href="SettingPersonRecommended">ตั้งค่าช่องทางแนะนำ</a></li>
                           <!-- <li><a href="SettingEducationBegin">สถานศึกษา</a></li> --> 
                            <li><a href="SettingEducationVOBegin">ตั้งค่าวุฒิการศึกษา</a></li>
                       </ul>
                   </div>
              </div>
              <div class="uk-button-dropdown" data-uk-dropdown  aria-haspopup="true" aria-expanded="false">
                   <button class="uk-button uk-button-success">ตารางเวลา<i class="uk-icon-caret-down"></i></button>
                   <div class="uk-dropdown uk-dropdown-small uk-dropdown-top" style="top: -198px; left: 0px;">
                       <ul class="uk-nav uk-nav-dropdown">
                            <li class="uk-nav-header">แพทย์</li>
                            <li><a href="dentist-schedule">กำหนดเวลาแพทย์ประจำห้อง</a></li>
                            <li><a href="dentist-schedule-checkin">ลงเวลาแพทย์ประจำห้อง</a></li>
                       </ul>
                       <ul class="uk-nav uk-nav-dropdown">
                            <li class="uk-nav-header">พนักงานผู้ช่วยแพทย์</li>
                            <li><a href="assistance-schedules">กำหนดเวลาผู้ช่วยแพทย์</a></li>
                            <li><a href="assistance-schedules-checkin">ลงเวลาผู้ช่วยแพทย์</a></li>
                       </ul>
                   </div>
              </div>
			</div>
		</div>
		<script>
			$(document).ready(function(){
				$( ".m-setting" ).addClass( "uk-active" );
			});
		</script>
	</body>
</html>