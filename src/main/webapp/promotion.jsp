<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:Promotion</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top-promotion.jsp" %>
				<div class="uk-grid uk-grid-collapse">
					
						<p class="uk-text-muted uk-width-1-1">กำหนดโปรโมชั่น</p>
						<ul class="uk-tab" data-uk-tab="{connect:'#my-id', animation: 'fade'}">
						    <li><a href="">ได้รับส่วนลด</a></li>
						    <li><a href="">เมื่อบิลถึงราคาที่กำหนด</a></li>
						    <li><a href="">ซื้อของแล้วแถม</a></li>
						</ul>
						<ul id="my-id" class="uk-switcher uk-width-1-1">
						    <li> 
						    	<!-- Setup 1 -->
						    <div class="uk-grid uk-grid-collapse"> 
						    	<div class="uk-width-5-10 padding5 uk-form" >
									<div class="uk-grid uk-grid-collapse padding5 border-gray">
							    	<div class="uk-width-1-3 uk-text-right">รหัส : </div>
									<div class="uk-width-1-3"><input type="text" class="uk-form-small uk-width-1-1" ></div> 
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">ชื่อ : </div>
									<div class="uk-width-1-3"><input type="text" class="uk-form-small uk-width-1-1" ></div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">ประเภทโปรโมชั่น: </div>
									<div class="uk-width-1-3"> 
										<div class="uk-button-group" data-uk-switcher="{connect:'#promotion_type',active:0}">
										    <button class="uk-button uk-button-primary" type="button">ได้รับส่วนลด</button>
										    <button class="uk-button uk-button-primary" type="button">เมื่อบิลถึงราคาที่กำหนด</button>
										    <button class="uk-button uk-button-primary" type="button">ซื้อของแล้วแถม</button>
										</div> 
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">รูปแบบแต้มสมาชิก : </div>
									<div class="uk-width-2-3">
										<ul class="uk-subnav uk-subnav-pill" data-uk-switcher="{connect:'#inputmemberpoint1', animation: 'fade'}">
			                                <li class="uk-active" aria-expanded="true"><a href="#">ไม่ได้รับแต้มสมาชิก</a></li>
			                                <li aria-expanded="false" class=""><a href="#">ได้รับแต้มสมาชิก</a></li>
			                            </ul>
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-1">
										<ul id="inputmemberpoint1"  class="uk-switcher">
										    <li></li>
										    <li><div class="uk-grid uk-grid-collapse">
										    
										    
											    <div class="uk-width-1-3 uk-text-right">อัตราการคูณ :</div>
												<div class="uk-width-2-3"> <input type="text" value="2"> </div>
												</div>
											</li>
										</ul>
									</div>
									
									<div class="uk-width-1-3 uk-text-right">ใช้ได้เฉพาะเดือนเกิด : </div>
									<div class="uk-width-1-3">
										<label ><input type="checkbox" name="pro_birthdate1"></label> 
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">รูปแบบโปรโมชั่น : </div>
									<div class="uk-width-1-3">
										<label ><input type="radio" name="rd"> แบบทั่วไป </label> 
										<label ><input type="radio" name="rd"> แบบบัตร</label>
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">สาขาที่ร่วมรายการ : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>ศรีนครินทร์</option>
											<option>งามวงศ์วาน</option>
											<option>รังสิต</option>
											<option>ราชพฤกษ์</option> 
											<option>อ่อนนุช</option>
											<option>แจ้งวัฒนะ</option>
											<option>รามอินทรา</option>
											<option>รามคำแหง</option>
										</select> 
									</div >
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">ช่วงระยะเวลาโปรโมชั่น : </div>
									<div class="uk-width-1-3">
										<input type="text" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" placeholder="เริ่ม"> 
										<input type="text" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" placeholder="ถึง">
									</div>
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">เพศ : </div>
									<div class="uk-width-1-3">
										<input type="radio" name="rd"> <label >ชาย</label>
										<input type="radio" name="rd"> <label >หญิง</label>
										<input type="radio" name="rd"> <label >ชาย - หญิง</label>
									</div>
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">รับสิทธิ์ผ่อน : </div>
									<div class="uk-width-2-3"><input type="text" class="uk-form-small" placeholder="0"> % นาน <input type="text" class="uk-form-small" placeholder="10"> เดือน</div>
						
									<div class="uk-width-1-3 uk-text-right">ช่องทางการจ่าย : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>เงินสด</option>
											<option>บัตรเครดิต</option> 
										</select> 
									</div > 
									<div class="uk-width-1-3"></div>
									<!-- Draw -->
									
									<div class="uk-width-1-3 uk-text-right">กำหนดสัดส่วนการหักรายได้ : </div>
									<div class="uk-width-1-3">
										<a class="uk-button uk-button-primary uk-button-small" data-uk-modal><i class="uk-icon-plus"></i> </a>
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-1 uk-overflow-container">
											<table id="price_list" class="border-gray uk-table uk-table-hover uk-table-striped uk-table-condensed">
									 		<thead>
									 			<tr class="hd-table">
									 				<th class="uk-text-center">ส่วนส่วนหักรายได้ร้าน (%)</th>
									 				<th class="uk-text-center">ส่วนส่วนหักรายได้แพทย์ (%)</th>
										        </tr>
									 		</thead>
									 		<tbody>
									 			<tr >
									 				<td class="uk-text-center">10</td> 
										            <td class="uk-text-center">10</td> 
										        </tr>
										        <tr >
									 				<td class="uk-text-center">10</td> 
										            <td class="uk-text-center">10</td> 
										        </tr>
									 		</tbody>
									 	</table>
									</div>
									<div class="uk-width-1-3 uk-text-right">การรักษาที่ร่วมรายการ : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>จัดฟัน</option>
											<option>ขูดหินปูน</option> 
											<option>รากฟัน</option>
										</select> 
									</div >  
									<div class="uk-width-1-3"></div> 
									<!-- ProductCover -->
									<div class="uk-width-1-3 uk-text-right">สินค้าที่ร่วมรายการ : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>น้ำยาบ้วนปาก</option>
											<option>แปลงสีฟัน</option> 
											<option>ยาสีฟัน</option>
										</select> 
									</div >  
									<div class="uk-width-1-3"></div>
									</div>
								 </div>
								 <div class="uk-width-5-10 padding5 uk-form" >
								<div class="uk-grid uk-grid-collapse padding5 border-gray">
									<div class="uk-width-1-3 uk-text-right">วงเงิน : </div>
									<div class="uk-width-1-3"><input type="text" class="uk-form-small uk-width-1-1" ></div> 
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">ส่วนลด : </div>
									<div class="uk-width-1-3">
										<input type="radio" name="rd"> <label ></label> <input type="text" placeholder="เงิน" class="uk-form-small uk-width-1-2" ><br>
										<input type="radio" name="rd"> <label ></label> <input type="text" placeholder="%" class="uk-form-small uk-width-1-2" >
									</div>
									<div class="uk-width-1-3"></div>  
									<div class="uk-width-1-3 uk-text-right">โปรโมชั่นในส่วน : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>สมัครสมาชิก</option>
											<option>ออกใบเสร็จ</option>  
										</select> 
									</div > 
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">สามารถใช้ร่วมกับโปรโมชั่น : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>คนไข้ทั่วไป</option>
											<option>คนไข้ติดต่อ</option> 
											<option>คนไข้พนักงาน</option> 
										</select> 
									</div >
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">ช่วงอายุที่เข้าร่วมโปรโมชั่น : </div>
									<div class="uk-width-2-3"><input type="text" class="uk-form-small" placeholder="10"> - <input type="text" class="uk-form-small" placeholder="75"></div> 
									<div class="uk-width-1-3 uk-text-right">คำอธิบายโปรโมชั่น : </div>
									<div class="uk-width-2-3"> <textarea placeholder="โปรโมชั่นสำหรับการ . . ."></textarea> </div>
									<div class="uk-width-1-3 uk-text-right"> กำหนดการใช้โปรโมชั่น</div>
									<div class="uk-width-1-3">
										<ul class="uk-subnav uk-subnav-pill" data-uk-switcher="{connect:'#subnav-pill-content-1'}">
			                                <li class="uk-active" aria-expanded="true"><a href="#">ทุกวัน</a></li>
			                                <li aria-expanded="false" class=""><a href="#">เฉพาะวันที่ต้องการ</a></li>
			                            </ul>
									</div>
									<div class="uk-width-1-1 padding5 uk-form" >
										<ul id="subnav-pill-content-1" class="uk-switcher">
			                                <li  aria-hidden="false">
											</li>
											<li  aria-hidden="false">
												<input type="checkbox"/> วันจันทร์ <input type="checkbox"/> วันอังคาร <input type="checkbox"/> วันพุธ 
												<input type="checkbox"/> วันพฤหัสบดี <input type="checkbox"/> วันศุกร์ <input type="checkbox"/> วันเสาร์ <input type="checkbox"/> วันอาทิตย์ 
											</li>
			                            </ul> 
									</div>
								
								</div>
								</div>
								</div>
						
						    	<!-- End Setup 1 -->
							</li>
							<li> 
								<div class="uk-grid uk-grid-collapse"> 
						    	<div class="uk-width-5-10 padding5 uk-form" >
									<div class="uk-grid uk-grid-collapse padding5 border-gray">
							    	<div class="uk-width-1-3 uk-text-right">รหัส : </div>
									<div class="uk-width-1-3"><input type="text" class="uk-form-small uk-width-1-1" ></div> 
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">ชื่อ : </div>
									<div class="uk-width-1-3"><input type="text" class="uk-form-small uk-width-1-1" ></div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">ประเภทโปรโมชั่น: </div>
									<div class="uk-width-1-3"> 
										<div class="uk-button-group" data-uk-switcher="{connect:'#promotion_type',active:0}">
										    <button class="uk-button uk-button-primary" type="button">ได้รับส่วนลด</button>
										    <button class="uk-button uk-button-primary" type="button">เมื่อบิลถึงราคาที่กำหนด</button>
										    <button class="uk-button uk-button-primary" type="button">ซื้อของแล้วแถม</button>
										</div> 
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">รูปแบบแต้มสมาชิก : </div>
									<div class="uk-width-2-3">
										<ul class="uk-subnav uk-subnav-pill" data-uk-switcher="{connect:'#inputmemberpoint2', animation: 'fade'}">
			                                <li class="uk-active" aria-expanded="true"><a href="#">ไม่ได้รับแต้มสมาชิก</a></li>
			                                <li aria-expanded="false" class=""><a href="#">ได้รับแต้มสมาชิก</a></li>
			                            </ul>
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-1">
										<ul id="inputmemberpoint2"  class="uk-switcher">
										    <li></li>
										    <li><div class="uk-grid uk-grid-collapse">
										    
										    
											    <div class="uk-width-1-3 uk-text-right">อัตราการคูณ :</div>
												<div class="uk-width-2-3"> <input type="text" value="2"> </div>
												</div>
											</li>
										</ul>
									</div>
									
									<div class="uk-width-1-3 uk-text-right">ใช้ได้เฉพาะเดือนเกิด : </div>
									<div class="uk-width-1-3">
										<label ><input type="checkbox" name="pro_birthdate2"></label> 
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">รูปแบบโปรโมชั่น : </div>
									<div class="uk-width-1-3">
										<label ><input type="radio" name="rd"> แบบทั่วไป </label> 
										<label ><input type="radio" name="rd"> แบบบัตร</label>
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">สาขาที่ร่วมรายการ : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>ศรีนครินทร์</option>
											<option>งามวงศ์วาน</option>
											<option>รังสิต</option>
											<option>ราชพฤกษ์</option> 
											<option>อ่อนนุช</option>
											<option>แจ้งวัฒนะ</option>
											<option>รามอินทรา</option>
											<option>รามคำแหง</option>
										</select> 
									</div >
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">ช่วงระยะเวลาโปรโมชั่น : </div>
									<div class="uk-width-1-3">
										<input type="text" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" placeholder="เริ่ม"> 
										<input type="text" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" placeholder="ถึง">
									</div>
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">เพศ : </div>
									<div class="uk-width-1-3">
										<input type="radio" name="rd"> <label >ชาย</label>
										<input type="radio" name="rd"> <label >หญิง</label>
										<input type="radio" name="rd"> <label >ชาย - หญิง</label>
									</div>
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">รับสิทธิ์ผ่อน : </div>
									<div class="uk-width-2-3"><input type="text" class="uk-form-small" placeholder="0"> % นาน <input type="text" class="uk-form-small" placeholder="10"> เดือน</div>
						
									<div class="uk-width-1-3 uk-text-right">ช่องทางการจ่าย : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>เงินสด</option>
											<option>บัตรเครดิต</option> 
										</select> 
									</div > 
									<div class="uk-width-1-3"></div>
									<!-- Draw -->
									
									<div class="uk-width-1-3 uk-text-right">กำหนดสัดส่วนการหักรายได้ : </div>
									<div class="uk-width-1-3">
										<a class="uk-button uk-button-primary uk-button-small" data-uk-modal><i class="uk-icon-plus"></i> </a>
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-1 uk-overflow-container">
											<table id="price_list" class="border-gray uk-table uk-table-hover uk-table-striped uk-table-condensed">
									 		<thead>
									 			<tr class="hd-table">
									 				<th class="uk-text-center">ส่วนส่วนหักรายได้ร้าน (%)</th>
									 				<th class="uk-text-center">ส่วนส่วนหักรายได้แพทย์ (%)</th>
										        </tr>
									 		</thead>
									 		<tbody>
									 			<tr >
									 				<td class="uk-text-center">10</td> 
										            <td class="uk-text-center">10</td> 
										        </tr>
										        <tr >
									 				<td class="uk-text-center">10</td> 
										            <td class="uk-text-center">10</td> 
										        </tr>
									 		</tbody>
									 	</table>
									</div>
									<div class="uk-width-1-3 uk-text-right">การรักษาที่ร่วมรายการ : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>จัดฟัน</option>
											<option>ขูดหินปูน</option> 
											<option>รากฟัน</option>
										</select> 
									</div >  
									<div class="uk-width-1-3"></div> 
									<!-- ProductCover -->
									<div class="uk-width-1-3 uk-text-right">สินค้าที่ร่วมรายการ : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>น้ำยาบ้วนปาก</option>
											<option>แปลงสีฟัน</option> 
											<option>ยาสีฟัน</option>
										</select> 
									</div >  
									<div class="uk-width-1-3"></div>
									</div>
								 </div>
								 <div class="uk-width-5-10 padding5 uk-form" >
								<div class="uk-grid uk-grid-collapse padding5 border-gray">
									<div class="uk-width-1-3 uk-text-right">วงเงิน : </div>
									<div class="uk-width-1-3"><input type="text" class="uk-form-small uk-width-1-1" ></div> 
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">ส่วนลด : </div>
									<div class="uk-width-1-3">
										<input type="radio" name="rd"> <label ></label> <input type="text" placeholder="เงิน" class="uk-form-small uk-width-1-2" ><br>
										<input type="radio" name="rd"> <label ></label> <input type="text" placeholder="%" class="uk-form-small uk-width-1-2" >
									</div>
									<div class="uk-width-1-3"></div>  
									<div class="uk-width-1-3 uk-text-right">โปรโมชั่นในส่วน : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>สมัครสมาชิก</option>
											<option>ออกใบเสร็จ</option>  
										</select> 
									</div > 
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">สามารถใช้ร่วมกับโปรโมชั่น : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>คนไข้ทั่วไป</option>
											<option>คนไข้ติดต่อ</option> 
											<option>คนไข้พนักงาน</option> 
										</select> 
									</div >
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">ช่วงอายุที่เข้าร่วมโปรโมชั่น : </div>
									<div class="uk-width-2-3"><input type="text" class="uk-form-small" placeholder="10"> - <input type="text" class="uk-form-small" placeholder="75"></div> 
									<div class="uk-width-1-3 uk-text-right">คำอธิบายโปรโมชั่น : </div>
									<div class="uk-width-2-3"> <textarea placeholder="โปรโมชั่นสำหรับการ . . ."></textarea> </div>
									<div class="uk-width-1-3 uk-text-right"> กำหนดการใช้โปรโมชั่น</div>
									<div class="uk-width-1-3">
										<ul class="uk-subnav uk-subnav-pill" data-uk-switcher="{connect:'#subnav-pill-content-2'}">
			                                <li class="uk-active" aria-expanded="true"><a href="#">ทุกวัน</a></li>
			                                <li aria-expanded="false" class=""><a href="#">เฉพาะวันที่ต้องการ</a></li>
			                            </ul>
									</div>
									<div class="uk-width-1-1 padding5 uk-form" >
										<ul id="subnav-pill-content-2" class="uk-switcher">
			                                <li aria-hidden="false">
											</li>
											<li aria-hidden="false">
												<input type="checkbox"/> วันจันทร์ <input type="checkbox"/> วันอังคาร <input type="checkbox"/> วันพุธ 
												<input type="checkbox"/> วันพฤหัสบดี <input type="checkbox"/> วันศุกร์ <input type="checkbox"/> วันเสาร์ <input type="checkbox"/> วันอาทิตย์ 
											</li>
			                            </ul> 
									</div>
									<div class="uk-width-1-3 uk-text-right">เริ่มโปรโมชั่นเมื่อบิลถึงราคา</div>
									<div class="uk-width-1-3"><input type="text" class="uk-form-small uk-width-1-1" ></div>
									<div class="uk-width-1-3"></div>
								</div>
								</div>
								</div>
							</li>
							<li>  
							
							<!-- Start 3 -->
						    	<div class="uk-grid uk-grid-collapse"> 
						    	<div class="uk-width-5-10 padding5 uk-form" >
									<div class="uk-grid uk-grid-collapse padding5 border-gray">
							    	<div class="uk-width-1-3 uk-text-right">รหัส : </div>
									<div class="uk-width-1-3"><input type="text" class="uk-form-small uk-width-1-1" ></div> 
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">ชื่อ : </div>
									<div class="uk-width-1-3"><input type="text" class="uk-form-small uk-width-1-1" ></div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">ประเภทโปรโมชั่น: </div>
									<div class="uk-width-1-3"> 
										<div class="uk-button-group" data-uk-switcher="{connect:'#promotion_type',active:0}">
										    <button class="uk-button uk-button-primary" type="button">ได้รับส่วนลด</button>
										    <button class="uk-button uk-button-primary" type="button">เมื่อบิลถึงราคาที่กำหนด</button>
										    <button class="uk-button uk-button-primary" type="button">ซื้อของแล้วแถม</button>
										</div> 
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">รูปแบบแต้มสมาชิก : </div>
									<div class="uk-width-2-3">
										<ul class="uk-subnav uk-subnav-pill" data-uk-switcher="{connect:'#inputmemberpoint3', animation: 'fade'}">
			                                <li class="uk-active" aria-expanded="true"><a href="#">ไม่ได้รับแต้มสมาชิก</a></li>
			                                <li aria-expanded="false" class=""><a href="#">ได้รับแต้มสมาชิก</a></li>
			                            </ul>
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-1">
										<ul id="inputmemberpoint3"  class="uk-switcher">
										    <li></li>
										    <li><div class="uk-grid uk-grid-collapse">
										    
										    
											    <div class="uk-width-1-3 uk-text-right">อัตราการคูณ :</div>
												<div class="uk-width-2-3"> <input type="text" value="2"> </div>
												</div>
											</li>
										</ul>
									</div>
									
									<div class="uk-width-1-3 uk-text-right">ใช้ได้เฉพาะเดือนเกิด : </div>
									<div class="uk-width-1-3">
										<label ><input type="checkbox" name="pro_birthdate3"></label> 
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">รูปแบบโปรโมชั่น : </div>
									<div class="uk-width-1-3">
										<label ><input type="radio" name="rd"> แบบทั่วไป </label> 
										<label ><input type="radio" name="rd"> แบบบัตร</label>
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">สาขาที่ร่วมรายการ : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>ศรีนครินทร์</option>
											<option>งามวงศ์วาน</option>
											<option>รังสิต</option>
											<option>ราชพฤกษ์</option> 
											<option>อ่อนนุช</option>
											<option>แจ้งวัฒนะ</option>
											<option>รามอินทรา</option>
											<option>รามคำแหง</option>
										</select> 
									</div >
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">ช่วงระยะเวลาโปรโมชั่น : </div>
									<div class="uk-width-1-3">
										<input type="text" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" placeholder="เริ่ม"> 
										<input type="text" class="uk-form-small uk-width-1-1" data-uk-datepicker="{format:'DD-MM-YYYY'}" placeholder="ถึง">
									</div>
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">เพศ : </div>
									<div class="uk-width-1-3">
										<input type="radio" name="rd"> <label >ชาย</label>
										<input type="radio" name="rd"> <label >หญิง</label>
										<input type="radio" name="rd"> <label >ชาย - หญิง</label>
									</div>
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">รับสิทธิ์ผ่อน : </div>
									<div class="uk-width-2-3"><input type="text" class="uk-form-small" placeholder="0"> % นาน <input type="text" class="uk-form-small" placeholder="10"> เดือน</div>
						
									<div class="uk-width-1-3 uk-text-right">ช่องทางการจ่าย : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>เงินสด</option>
											<option>บัตรเครดิต</option> 
										</select> 
									</div > 
									<div class="uk-width-1-3"></div>
									<!-- Draw -->
									
									<div class="uk-width-1-3 uk-text-right">กำหนดสัดส่วนการหักรายได้ : </div>
									<div class="uk-width-1-3">
										<a class="uk-button uk-button-primary uk-button-small" data-uk-modal><i class="uk-icon-plus"></i> </a>
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-1 uk-overflow-container">
											<table id="price_list" class="border-gray uk-table uk-table-hover uk-table-striped uk-table-condensed">
									 		<thead>
									 			<tr class="hd-table">
									 				<th class="uk-text-center">ส่วนส่วนหักรายได้ร้าน (%)</th>
									 				<th class="uk-text-center">ส่วนส่วนหักรายได้แพทย์ (%)</th>
										        </tr>
									 		</thead>
									 		<tbody>
									 			<tr >
									 				<td class="uk-text-center">10</td> 
										            <td class="uk-text-center">10</td> 
										        </tr>
										        <tr >
									 				<td class="uk-text-center">10</td> 
										            <td class="uk-text-center">10</td> 
										        </tr>
									 		</tbody>
									 	</table>
									</div>
									<div class="uk-width-1-3 uk-text-right">การรักษาที่ร่วมรายการ : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>จัดฟัน</option>
											<option>ขูดหินปูน</option> 
											<option>รากฟัน</option>
										</select> 
									</div >  
									<div class="uk-width-1-3"></div> 
									<!-- ProductCover -->
									<div class="uk-width-1-3 uk-text-right">สินค้าที่ร่วมรายการ : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>น้ำยาบ้วนปาก</option>
											<option>แปลงสีฟัน</option> 
											<option>ยาสีฟัน</option>
										</select> 
									</div >  
									<div class="uk-width-1-3"></div>
									</div>
								 </div>
								 <div class="uk-width-5-10 padding5 uk-form" >
								<div class="uk-grid uk-grid-collapse padding5 border-gray">
									<div class="uk-width-1-3 uk-text-right">วงเงิน : </div>
									<div class="uk-width-1-3"><input type="text" class="uk-form-small uk-width-1-1" ></div> 
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">ส่วนลด : </div>
									<div class="uk-width-1-3">
										<input type="radio" name="rd"> <label ></label> <input type="text" placeholder="เงิน" class="uk-form-small uk-width-1-2" ><br>
										<input type="radio" name="rd"> <label ></label> <input type="text" placeholder="%" class="uk-form-small uk-width-1-2" >
									</div>
									<div class="uk-width-1-3"></div>  
									<div class="uk-width-1-3 uk-text-right">โปรโมชั่นในส่วน : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>สมัครสมาชิก</option>
											<option>ออกใบเสร็จ</option>  
										</select> 
									</div > 
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">สามารถใช้ร่วมกับโปรโมชั่น : </div>
									<div class="uk-width-1-3"> 
										<button class="uk-button uk-button-primary uk-width-2-10 uk-button-small"><i class="uk-icon-plus"></i></button>
										<button class="uk-button uk-button-danger uk-width-2-10 uk-button-small"><i class="uk-icon-times"></i></button>
										<select class="uk-width-1-1" size="5">
											<option>คนไข้ทั่วไป</option>
											<option>คนไข้ติดต่อ</option> 
											<option>คนไข้พนักงาน</option> 
										</select> 
									</div >
									<div class="uk-width-1-3"></div>
									<div class="uk-width-1-3 uk-text-right">ช่วงอายุที่เข้าร่วมโปรโมชั่น : </div>
									<div class="uk-width-2-3"><input type="text" class="uk-form-small" placeholder="10"> - <input type="text" class="uk-form-small" placeholder="75"></div> 
									<div class="uk-width-1-3 uk-text-right">คำอธิบายโปรโมชั่น : </div>
									<div class="uk-width-2-3"> <textarea placeholder="โปรโมชั่นสำหรับการ . . ."></textarea> </div>
									<div class="uk-width-1-3 uk-text-right"> กำหนดการใช้โปรโมชั่น</div>
									<div class="uk-width-1-3">
										<ul class="uk-subnav uk-subnav-pill" data-uk-switcher="{connect:'#subnav-pill-content-3'}">
			                                <li class="uk-active" aria-expanded="true"><a href="#">ทุกวัน</a></li>
			                                <li aria-expanded="false" class=""><a href="#">เฉพาะวันที่ต้องการ</a></li>
			                            </ul>
									</div>
									<div class="uk-width-1-1 padding5 uk-form" >
										<ul id="subnav-pill-content-3" class="uk-switcher">
			                                <li aria-hidden="false">
											</li>
											<li aria-hidden="false">
												<input type="checkbox"/> วันจันทร์ <input type="checkbox"/> วันอังคาร <input type="checkbox"/> วันพุธ 
												<input type="checkbox"/> วันพฤหัสบดี <input type="checkbox"/> วันศุกร์ <input type="checkbox"/> วันเสาร์ <input type="checkbox"/> วันอาทิตย์ 
											</li>
			                            </ul> 
									</div>
									<div class="uk-width-1-3 uk-text-right">ซื้อสินค้า</div>
										<div class="uk-width-1-3"><input type="text" class="uk-form-small" value="RSS0005"> 
											<a href="#protype3_add" class="uk-button uk-button-primary"  data-uk-modal><i class="uk-icon-search"></i></a>
										</div>
										<div class="uk-width-1-3"> <input type="text" value="2" class="uk-form-small"> ชิ้น</div>
										
										<div class="uk-width-1-3 uk-text-right">แถมสินค้า</div>
										<div class="uk-width-1-3"><input type="text" class="uk-form-small" value="RSS0006"> 
											<a href="#protype3_add" class="uk-button uk-button-primary"  data-uk-modal><i class="uk-icon-search"></i></a>
										</div>
										<div class="uk-width-1-3"> <input type="text" value="1" class="uk-form-small"> ชิ้น</div>
										
										<div class="uk-width-1-1">
											<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
												    <thead>
												        <tr class="hd-table"> 
												            <th class="uk-text-center">ซื้อสินค้า</th> 
												            <th class="uk-text-center">จำนวน</th>
												            <th class="uk-text-center">แถมสินค้า</th>
												            <th class="uk-text-center">จำนวน</th> 
												        </tr>
												    </thead> 
												    <tbody>
												    	<tr> 
													        <td class="uk-text-center">ยาสีฟัน</td>
													        <td class="uk-text-center">2</td>
													        <td class="uk-text-center">แปรงสีฟัน</td>
													        <td class="uk-text-center">1</td>
														</tr>
													</tbody>
												</table>
										</div>
								</div>
								</div>
								</div>
						    </li>
						</ul> 
						</div>
					</div>
					
				</div> 
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-container-center" >
						<button class="uk-button uk-button-success uk-button-large uk-icon-floppy-o"> เพิ่มโปรโมชั่น</button>
						<a href="#" class="uk-button uk-button-danger uk-button-large "><i class="uk-icon-close"></i> ยกเลิก</a>
					</div>
				</div>
				
		<script>
			$(document).ready(function(){
				$( ".m-setting" ).addClass( "uk-active" );
			});
			 
		</script>		
	</body>
</html>