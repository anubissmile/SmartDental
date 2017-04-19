<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:SMS</title>
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
					<div class="uk-width-6-10 ">
					
						<div class=" uk-panel-box">
						<h4 class="hd-text uk-text-primary padding5">ตาราง SMS</h4>
				    	<div class="uk-overflow-container">
						<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray uk-text-nowrap">
						    <caption>ตาราง homecall</caption>
						    <thead>
						        <tr>
						            <th>No</th>
						            <th>รหัสคนไข้</th> 
						            <th>เบอร์คนไข้</th>
						            <th>การรักษา</th>
						            <th>กำหนดโทร</th>
						            <th>ครั้งที่</th>
						            <th>สถานะ</th>
						            <th>ทำรายการ</th>
						        </tr>
						    </thead> 
						    <tbody>
						        <tr class="uk-success">
						            <td class="uk-text-right">5</td>
						            <td class="uk-text-left">HN1150</td> 
						            <td class="uk-text-left">0891744898</td>
						            <td class="uk-text-left">อุดฟัน</td>
						            <td class="uk-text-right">02-06-2559</td>
						            <td class="uk-text-right">1</td>
						            <td class="uk-text-left">ติดต่อเรียบร้อย</td>
						            <td class="uk-text-center"> <button class="uk-button uk-button-primary uk-icon-edit"> แก้ไขสถานะ</button> </td>
						        </tr>
						        <tr class="uk-waiting">
						            <td class="uk-text-right">6</td>
						            <td class="uk-text-left">HN1151</td> 
						            <td class="uk-text-left">0888685197</td>
						            <td class="uk-text-left">ถอนฟัน</td>
						            <td class="uk-text-right">02-06-2559</td>
						            <td class="uk-text-right">2</td>
						            <td class="uk-text-left">รอการติดต่อ</td>
						            <td class="uk-text-center"> <button class="uk-button uk-button-primary uk-icon-edit"> แก้ไขสถานะ</button> </td>
						        </tr>
						        <tr class="uk-danger">
						            <td class="uk-text-right">7</td>
						            <td class="uk-text-left">HN1152</td> 
						            <td class="uk-text-left">0865123215</td>
						            <td class="uk-text-left">ถอนฟัน</td>
						            <td class="uk-text-right">02-06-2559</td>
						            <td class="uk-text-right">2</td>
						            <td class="uk-text-left">ติดต่อไม่สำเร็จ</td>
						            <td class="uk-text-center"> <button class="uk-button uk-button-primary uk-icon-edit"> แก้ไขสถานะ</button> </td>
						        </tr>
						    </tbody>
						</table>
						</div>
						</div>
					</div>
				<!-- Right Form -->
					<div class="uk-width-4-10">
					<div class=" uk-panel-box">
						<div class="uk-overflow-container treatment-table">
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
	 							<li class="uk-grid"><div class="uk-width-1-2 uk-text-left">หมายเหตุของลูกค้า </div>
	 							</li>
	 							<li><textarea placeholder="" class="uk-form-small"></textarea></li>
	 							<li class="uk-grid"><div class="uk-width-1-2 uk-text-left">หมายเหตุของพนักงาน </div>
	 							</li>
	 							<li><textarea placeholder="" class="uk-form-small"></textarea></li>
	                        </ul>
	                        <div class="uk-text-center">
	                        	<button class="uk-button uk-button-success uk-icon-check"> โทรเรียบร้อย</button>
	                        	<button class="uk-button uk-button-danger uk-icon-minus"> ติดต่อไม่ได้</button>
	                        </div>
						</div>	
					</div>
				</div>
			</div>
		</div>
		</div>
		
	</body>
</html>