<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:Recall All</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div> 
			<div class="uk-width-9-10 ">
				<%@include file="nav-top.jsp" %>
				<div class="uk-grid uk-grid-collapse">
				<!-- Table  -->
					<div class="uk-width-7-10 ">
					
						<div class=" uk-panel-box">
						<h4 class="hd-text uk-text-primary padding5">ตาราง Recall</h4>
						 
						<ul class="uk-form uk-list chanel-pay padding5 border-gray uk-text-right">
	                    	<li class="uk-grid uk-grid-small">
	                    		<div class="uk-width-1-10 uk-text-right">ค้นหา :</div>  
	                    		<div class="uk-width-2-10 uk-text-left"><input type="text" placeholder="การรักษา" autofocus="autofocus" class="uk-form-small"></div>
	                    		<div class="uk-width-1-10 uk-text-left"><input type="text" placeholder="กำหนดโทร" data-uk-datepicker="{format:'DD-MM-YYYY'}" class="uk-form-small"></div>
	 							<div class="uk-width-1-10 uk-text-left"><input type="text" placeholder="ครั้งที่" class="uk-form-small"></div>
	 							<div class="uk-width-1-10 uk-text-left"><input type="text" placeholder="ครั้งล่าสุด" class="uk-form-small"></div>
	 							<div class="uk-width-2-10 uk-text-left"><button class="uk-button uk-button-primary uk-button-small uk-icon-search"> ค้นหา</button></div>
	 						</li>
	 					</ul> 
						<hr>
				    	<div class="uk-overflow-container">  
				    	<ul class="uk-tab" data-uk-tab="{connect:'#my-id', animation: 'fade'}">
						    <li><a href="">รอการติดต่อ</a></li>
						    <li><a href="">รอการติดต่อพิเศษ</a></li>
						    <li><a href="">ติดต่อเรียบร้อยแล้ว</a></li>
						    <li><a href="">ติดต่อไม่ได้</a></li>
						</ul>
				    	<ul id="my-id" class="uk-switcher">
						    
						    <li> 
								<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray uk-text-nowrap">
								    <caption>รอการติดต่อ</caption>
								    <thead>
								        <tr>
								            <th>No</th> 
								            <th>การรักษา</th>
								            <th>กำหนดโทร</th>
								            <th>ครั้งที่</th>
								            <th>ครั้งล่าสุด</th>
								            <th>สถานะ</th>
								            <th>ทำรายการ</th>
								        </tr>
								    </thead> 
								    <tbody> 
								        <tr class="uk-waiting">
								            <td class="uk-text-right"><input type="checkbox"> 6</td> 
								            <td class="uk-text-left">อุดฟัน</td>
								            <td class="uk-text-right">02-06-2559</td>
								            <td class="uk-text-right">1</td>
								            <td class="uk-text-right">01-01-2559</td>
								            <td class="uk-text-left">รอการติดต่อ</td>
								            <td class="uk-text-center"><input type="text" placeholder="Remark"> <button class="uk-button uk-button-success uk-icon-check"></button> <button class="uk-button uk-button-danger uk-icon-close"></button> <button class="uk-button uk-button-primary uk-icon-search"></button> </td>
								        </tr> 
								    </tbody>
								</table>
								<div class="uk-text-center">
		                        	<button class="uk-button uk-button-success uk-icon-check"> โทรเรียบร้อย</button>
		                        	<button class="uk-button uk-button-danger uk-icon-minus"> ติดต่อไม่ได้</button>
		                        </div>
							</li>
							<li> 
								<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray uk-text-nowrap">
								    <caption>รอการติดต่อพิเศษ</caption>
								    <thead>
								        <tr>
								            <th>No</th> 
								            <th>การรักษา</th>
								            <th>กำหนดโทร</th>
								            <th>ครั้งที่</th>
								            <th>ครั้งล่าสุด</th>
								            <th>สถานะ</th>
								            <th>ทำรายการ</th>
								        </tr>
								    </thead> 
								    <tbody> 
								        <tr class="uk-waiting">
								            <td class="uk-text-right"><input type="checkbox"> 6</td> 
								            <td class="uk-text-left">อุดฟัน</td>
								            <td class="uk-text-right">02-06-2559</td>
								            <td class="uk-text-right">1</td>
								            <td class="uk-text-right">01-01-2559</td>
								            <td class="uk-text-left">รอการติดต่อ</td>
								            <td class="uk-text-center"><input type="text" placeholder="Remark"> <button class="uk-button uk-button-success uk-icon-check"></button> <button class="uk-button uk-button-danger uk-icon-close"></button> <button class="uk-button uk-button-primary uk-icon-search"></button> </td>
								        </tr> 
								    </tbody>
								</table>
								<div class="uk-text-center">
		                        	<button class="uk-button uk-button-success uk-icon-check"> โทรเรียบร้อย</button>
		                        	<button class="uk-button uk-button-danger uk-icon-minus"> ติดต่อไม่ได้</button>
		                        </div>
							</li>
							<li>  
						    	<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray uk-text-nowrap">
								    <caption>ติดต่อเรียบร้อยแล้ว</caption>
								    <thead>
								        <tr>
								            <th>No</th> 
								            <th>การรักษา</th>
								            <th>กำหนดโทร</th>
								            <th>ครั้งที่</th>
								            <th>ครั้งล่าสุด</th>
								            <th>สถานะ</th>
								            <th>ทำรายการ</th>
								        </tr>
								    </thead> 
								    <tbody>
								        <tr class="uk-success">
								            <td class="uk-text-right">5</td> 
								            <td class="uk-text-left">อุดฟัน</td>
								            <td class="uk-text-right">02-06-2559</td>
								            <td class="uk-text-right">1</td>
								            <td class="uk-text-right">01-01-2559</td>
								            <td class="uk-text-left">ติดต่อเรียบร้อย</td>
								            <td class="uk-text-center"> Remark </td>
								        </tr>
								    </tbody>
								</table>
								<div class="uk-text-center">
		                        	<a href="#sms" data-uk-modal class="uk-button uk-button-success uk-icon-envelope-o">ส่ง sms</a>
		                        </div>
						    </li>
						    <li> 
						    	<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray uk-text-nowrap">
								    <caption>ติดต่อไม่ได้</caption>
								    <thead>
								        <tr>
								            <th>No</th> 
								            <th>การรักษา</th>
								            <th>กำหนดโทร</th>
								            <th>ครั้งที่</th>
								            <th>ครั้งล่าสุด</th>
								            <th>สถานะ</th>
								            <th>ทำรายการ</th>
								        </tr>
								    </thead> 
								    <tbody> 
								        <tr class="uk-danger">
								            <td class="uk-text-right">7</td> 
								            <td class="uk-text-left">อุดฟัน</td>
								            <td class="uk-text-right">02-06-2559</td>
								            <td class="uk-text-right">1</td>
								            <td class="uk-text-right">01-01-2559</td>
								            <td class="uk-text-left">ติดต่อไม่ได้</td>
								            <td class="uk-text-center"> Remark </td>
								        </tr>
								    </tbody>
								</table>
								<div class="uk-text-center">
		                        	<a href="#sms" data-uk-modal class="uk-button uk-button-success uk-icon-envelope-o">ส่ง sms</a>
		                        </div>
							</li>
						</ul>
						</div>
						</div>
						<div id="sms" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><h2><i class="uk-icon-stethoscope"></i> ส่งSMS</h2></div>
					         	<div class="uk-width-1-1 uk-overflow-container">
					         		<h4 class="hd-text uk-text-primary">ข้อความที่ส่ง</h4>
									<textarea rows="" cols="" style="width:100%;"></textarea>
									  
									<button class="uk-button uk-button-small">แก้ไข</button>  
									
								</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-button uk-button-success uk-button-small">ตกลง</button>
					         	<button class="uk-modal-close">ยกเลิก</button>
					         </div>
					    </div>
					</div>
					</div>
				<!-- Right Form -->
					<div class="uk-width-3-10">
					<div class=" uk-panel-box">
						<div class="uk-overflow-container ">
							<h4 class="hd-text uk-text-primary padding5">เปลี่ยนแปลงสถานะ</h4>
							<ul class="uk-form uk-list chanel-pay padding5 border-gray uk-text-right">
	                            <li class="uk-grid"><div class="uk-width-1-2 uk-text-left">รหัสทำรายการ </div>
	 								<input type="text" dir="rtl" size="20" readonly="readonly" placeholder="5" class="uk-form-small">
	 							</li>
	                            <li class="uk-grid"><div class="uk-width-1-2 uk-text-left">รหัสคนไข้  </div>
	 								<input type="text" dir="rtl" size="20" readonly="readonly" placeholder="HN1150" class="uk-form-small">
	 							</li>
	                            <li class="uk-grid"><div class="uk-width-1-2 uk-text-left">ชื่อคนไข้ </div>
	 								นาย มานุวัฒน์ ชัยชนะ
	 							</li>
	 							<li class="uk-grid"><div class="uk-width-1-2 uk-text-left">เบอร์คนไข้ </div>
	 								0891744898
	 							</li>
	                            <li class="uk-grid"><div class="uk-width-1-2 uk-text-left">หมายเลขบิลอ้างอิง </div>
	 								11100506
	 							</li>
	 							<li class="uk-grid"><div class="uk-width-1-2 uk-text-left">การรักษา </div>
	 								อุดฟัน
	 							</li>
	 							<li class="uk-grid"><div class="uk-width-1-2 uk-text-left">ครั้งที่ </div>
	 								1
	 							</li>
	 							<li class="uk-grid"><div class="uk-width-1-2 uk-text-left">ครั้งล่าสุด </div>
	 								01-01-2559
	 							</li>
	                        </ul>	
						</div>	
					</div>
				</div>
			</div>
		</div>
		</div>
		
	</body>
</html>