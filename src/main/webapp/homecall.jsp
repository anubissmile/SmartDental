<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:คนไข้</title>
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
						<h4 class="hd-text uk-text-primary padding5">ตาราง Homecall</h4>
				    	<div class="uk-overflow-container">
							<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray uk-text-nowrap">
							    <caption>ตาราง homecall</caption>
							    <thead>
							        <tr>
							            <th class="uk-text-right">No</th>
							            <th>รหัสคนไข้</th> 
							            <th>ชื่อ - นามสกุล</th>
							            <th>เบอร์คนไข้</th>
							            <th class="uk-text-right">วันที่</th>
							            <th class="uk-text-right">ครั้งที่</th>
							            <th>สถานะ</th>
							            <th class="uk-text-center"></th>
							        </tr>
							    </thead> 
							    <tbody>
							        <tr class="uk-success">
							            <td class="uk-text-right">5</td>
							            <td class="uk-text-left">HN1150</td> 
							            <td> นาย มานุวัฒน์ ชัยชนะ </td>
							            <td class="uk-text-left">0891744898</td>
							            <td class="uk-text-right">02-06-2559</td>
							            <td class="uk-text-right">1</td>
							            <td class="uk-text-left">ติดต่อเรียบร้อย</td>
							            <td class="uk-text-center"> 
								            <a href="homecall-detail.jsp" class="uk-button uk-button-primary uk-button-small">
								            <i class="uk-icon-volume-control-phone"></i> ติดต่อ</a> 
							            </td>
							        </tr>
							        <tr class="uk-waiting">
							            <td class="uk-text-right">6</td>
							            <td class="uk-text-left">HN1151</td>
							            <td> นาย มานุวัฒน์ ชัยชนะ </td>
							            <td class="uk-text-left">0888685197</td>
							            <td class="uk-text-right">02-06-2559</td>
							            <td class="uk-text-right">2</td>
							            <td class="uk-text-left">รอการติดต่อ</td>
							            <td class="uk-text-center"> 
								            <a href="homecall-detail.jsp" class="uk-button uk-button-primary uk-button-small">
								            <i class="uk-icon-volume-control-phone"></i> ติดต่อ</a> 
							            </td>
							        </tr>
							        <tr class="uk-danger">
							            <td class="uk-text-right">7</td>
							            <td class="uk-text-left">HN1152</td> 
							            <td> นาย มานุวัฒน์ ชัยชนะ </td>
							            <td class="uk-text-left">0865123215</td>
							            <td class="uk-text-right">02-06-2559</td>
							            <td class="uk-text-right">2</td>
							            <td class="uk-text-left">ติดต่อไม่สำเร็จ</td>
							             <td class="uk-text-center"> 
								            <a href="homecall-detail.jsp" class="uk-button uk-button-primary uk-button-small">
								            <i class="uk-icon-volume-control-phone"></i> ติดต่อ</a> 
							            </td>
							        </tr>
							         <tr >
							            <td class="uk-text-right">8</td>
							            <td class="uk-text-left">HN1152</td> 
							            <td> นาย มานุวัฒน์ ชัยชนะ </td>
							            <td class="uk-text-left">0865123215</td>
							            <td class="uk-text-right">02-06-2559</td>
							            <td class="uk-text-right">2</td>
							            <td class="uk-text-left">ติดต่อไม่สำเร็จ</td>
							             <td class="uk-text-center"> 
								            <a href="homecall-detail.jsp" class="uk-button uk-button-primary uk-button-small">
								            <i class="uk-icon-volume-control-phone"></i> ติดต่อ</a> 
							            </td>
							        </tr>
							         <tr>
							            <td class="uk-text-right">9</td>
							            <td class="uk-text-left">HN1152</td> 
							            <td> นาย มานุวัฒน์ ชัยชนะ </td>
							            <td class="uk-text-left">0865123215</td>
							            <td class="uk-text-right">02-06-2559</td>
							            <td class="uk-text-right">2</td>
							            <td class="uk-text-left">ติดต่อไม่สำเร็จ</td>
							             <td class="uk-text-center"> 
								            <a href="homecall-detail.jsp" class="uk-button uk-button-primary uk-button-small">
								            <i class="uk-icon-volume-control-phone"></i> ติดต่อ</a> 
							            </td>
							        </tr>
							    </tbody>
							</table>
						</div>
						</div>
				
				</div>
			</div>
		</div>
		</div>
		
	</body>
</html>