<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:คนไข้</title>
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
					<div class="uk-width-1-1 ">
					
						<div class=" uk-panel-box">
						<h4 class="hd-text uk-text-primary padding5">ตาราง Recall</h4>
				    	<div class="uk-overflow-container">
						<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray uk-text-nowrap">
						    <caption>ตาราง recall</caption>
						    <thead>
						        <tr>
						            <th>No</th>
						            <th>รหัสคนไข้</th>  
						            <th>ชื่อ - นามสกุล</th>
						            <th>เบอร์คนไข้</th>
						            <th>กำหนดโทร</th>
						            <th>ครั้งที่</th>
						            <th>ครั้งล่าสุด</th>
						            <th>สถานะ</th>
						            <th class="uk-text-center"> </th>
						        </tr>
						    </thead> 
						    <tbody>
						        <tr class="uk-success">
						            <td class="uk-text-right">5</td>
						            <td class="uk-text-left">HN1150</td> 
						            <td class="uk-text-left"> นาย มานุวัฒน์ ชัยชนะ </td>
						            <td class="uk-text-left">0891744898</td> 
						            <td class="uk-text-right">02-06-2559</td>
						            <td class="uk-text-right">1</td>
						            <td class="uk-text-right">01-01-2559</td>
						            <td class="uk-text-left">ติดต่อเรียบร้อย</td>
						            <td class="uk-text-center"> <a href="recall-all.jsp" class="uk-button uk-button-primary uk-button-small">
								            <i class="uk-icon-volume-control-phone"></i> ติดต่อ</a>  </td>
						        </tr>
						        <tr class="uk-waiting">
						            <td class="uk-text-right">6</td>
						            <td class="uk-text-left">HN1151</td> 
						            <td class="uk-text-left"> นาย มานุวัฒน์ ชัยชนะ </td>
						            <td class="uk-text-left">0888685197</td> 
						            <td class="uk-text-right">02-06-2559</td>
						            <td class="uk-text-right">1</td>
						            <td class="uk-text-right">01-01-2559</td>
						            <td class="uk-text-left">รอการติดต่อ</td>
						            <td class="uk-text-center"> <a href="recall-all.jsp" class="uk-button uk-button-primary uk-button-small">
								            <i class="uk-icon-volume-control-phone"></i> ติดต่อ</a> </td>
						        </tr>
						        <tr class="uk-danger">
						            <td class="uk-text-right">7</td>
						            <td class="uk-text-left">HN1152</td> 
						            <td class="uk-text-left"> นาย มานุวัฒน์ ชัยชนะ </td>
						            <td class="uk-text-left">0865123215</td> 
						            <td class="uk-text-right">02-06-2559</td>
						            <td class="uk-text-right">1</td>
						            <td class="uk-text-right">01-01-2559</td>
						            <td class="uk-text-left">ติดต่อไม่ได้</td>
						            <td class="uk-text-center"> <a href="recall-all.jsp" class="uk-button uk-button-primary uk-button-small">
								            <i class="uk-icon-volume-control-phone"></i> ติดต่อ</a> </td>
						        </tr>
						    </tbody>
						</table>
						</div>
							<a href="recall-all.jsp"><i class="uk-icon-external-link"></i> รายละเอียดเพิ่มเติม</a>
						</div> 
						
					</div>
				 
			</div>
		</div>
		</div>
		
	</body>
</html>