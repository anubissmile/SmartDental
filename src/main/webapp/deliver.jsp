<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:ส่งตัว</title>
		 
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">  
				<%@include file="nav-top.jsp" %> 
				<div class="uk-grid"></div>
				<div class="uk-grid">
				    <div class="uk-width-7-10 uk-overflow-container">
						<table class="uk-table uk-table uk-table-hover uk-table-striped uk-table-condensed">
						    <caption>ตารางการส่งตัว</caption>
						    <thead>
						        <tr>
						            <th>No</th>
						            <th>Room</th> 
						            <th>DN</th>
						            <th>PT Name</th>
						            <th>Time App</th>
						            <th>Ins</th>
						            <th>Group</th>
						            <th>Air Time</th>
						            <th>Process Time</th>
						            <th>Process Complete</th>
						            <th>EstBalance</th>
						            <th>Room-Dent-Note</th>
						            <th>Note-Remark</th>
						        </tr>
						    </thead> 
						    <tbody>
						        <tr>
						            <td class="uk-text-center">1</td>
						            <td class="uk-text-center">3</td> 
						            <td>112034</td>
						            <td>ด.ช.ดนัย ใจสดใส</td>
						            <td class="uk-text-center">27-05-2016 09:12:53</td>
						            <td>-</td>
						            <td>เด็ก</td>
						            <td class="uk-text-center">27-05-2016 12:45:20</td>
						            <td class="uk-text-center">27-05-2016 10:05:20</td>
						            <td class="uk-text-center">100%</td>
						            <td>-</td>
						            <td>Room-Dent-Note</td>
						            <td>Note-Remark</td>
						        </tr>
						        <tr>
						            <td class="uk-text-center">2</td>
						            <td class="uk-text-center">1</td> 
						            <td>112035</td>
						            <td>ด.ช.วิชัย ใจสดใส</td>
						            <td class="uk-text-center">27-05-2016 10:50:53</td>
						            <td>-</td>
						            <td>เด็ก</td>
						            <td class="uk-text-center">27-05-2016 11:10:20</td>
						            <td class="uk-text-center">27-05-2016 11:20:20</td>
						            <td class="uk-text-center">100%</td>
						            <td>-</td>
						            <td>Room-Dent-Note</td>
						            <td>Note-Remark</td>
						        </tr>
						    </tbody>
						</table>
					</div> 
					
					<div class="uk-width-3-10 uk-overflow-container">
						 
                        <div class="uk-panel uk-panel-box uk-width-medium-1-1">
                                <div class="uk-panel-badge uk-badge uk-badge-primary">วันที่</div>  
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-calendar"></i> วันที่</h3>
								</div>
                                <form class="uk-form">
								    <input type="text" data-uk-datepicker="{format:'DD.MM.YYYY'}"> 
								</form>
                        </div>
                        
                        <div class="uk-panel uk-panel-box uk-width-medium-1-1">
                                <div class="uk-panel-badge uk-badge uk-badge-primary">อธิบาย</div> 
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-sticky-note"></i> การส่งตัว</h3>
								</div> 
                               	<ul class="uk-list uk-list-line">
                               		<li>ต้องไม่มียอดค้างชำระ</li>
                               		<li>โปรดตรวจสอบเอกสาร</li>
                               	</ul> 
                        </div>
					</div> 
					  
					
				</div>
			</div>
		</div>

		<script>
			$(document).ready(function(){
				$( ".m-deliver" ).addClass( "uk-active" );
			});
		</script>
	</body>
</html>