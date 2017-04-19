<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<title>Smart Dental:รายละเอียดข้อมูลสาขา</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				
					<div class="uk-width-1-1 uk-grid uk-grid-collapse padding5 uk-form" >
							
							<!-- Start Branch Detail-->
							<div class="uk-width-7-10 padding5 border-gray">
								<div class="uk-grid uk-grid-collapse">
								
							 	<p class="uk-text-muted uk-width-1-1">ข้อมูลราคาสินค้าและรายได้แพทย์ </p>
							 		<table id="price_list" class="uk-table uk-table uk-table-hover uk-table-condensed border-gray">
								 		<thead>
								 			<tr>
								 				<th>กลุ่มการรักษา</th>
									            <th>กลุ่มย่อยสินค้า</th>
								 				<th>รหัสสินค้า</th> 
									            <th>ชื่อสินค้า</th> 
									            <th>ราคา </th>
									            <th>DF</th>
									            <th>LAB</th>
									        </tr>
								 		</thead>
								 		<tbody>
								 			<tr >
								 				<td>P</td> 
									            <td>1</td> 
									            <td>RUS220</td>
									            <td>ถอนฟัน</td>
									            <td>1500</td>
									            <td><input type="text" value="43">%</td>
									            <td><input type="text" value="43">%</td>
									        </tr>
									        <tr >
								 				<td>I</td> 
									            <td>6</td> 
									            <td>RUS223</td>
									            <td>อุดฟัน</td>
									            <td>2500</td>
									            <td><input type="text" value="43">%</td>
									            <td><input type="text" value="100">%</td>
									        </tr>
								 		</tbody>
								 	</table>
								 	<div class="uk-width-1-1 uk-text-center">
								 		<button class="uk-button uk-button-success"><i class="uk-icon-check"></i> บันทึกการตั้งค่า</button>
								 	</div>
								</div>
							</div>
							<!-- End Branch Detail-->
							<!-- Start Set up branch & doctor -->
							<div class="uk-width-3-10 padding5 border-gray">
								<div class="uk-grid uk-grid-collapse">
								<p class="uk-text-muted uk-width-1-1">เลือกข้อมูล </p>
									<div class="uk-width-1-1">
										เลือกแบรนด์ 
										<select class="brand" size="2" style="width:100%;">
											<option value="1">LDC</option>
											<option value="2">ใส่ใจทันตแพทย์</option>
										</select>
									</div>
									<div class="uk-width-1-1">
										เลือกสาขา  
										<select class="branch" size="5" style="width:100%;">
										</select>
									</div>
									<div class="uk-width-1-1">
										เลือกแพทย์
										<select class="doctor" size="5" style="width:100%;">
											<option value="1">ทพ เศรษฐพงศ์ ธุรพันธ์กิจโชติ</option>
											<option value="2">ทพ มานุวัฒน์ ชัยชนะ</option>
										</select>
									</div>
									<div class="uk-width-1-1">
										กลุ่มการรักษา
										<select class="doctor">
											<option value="o">O</option>
											<option value="p">P</option>
											<option value="i">I</option>
											<option value="g">g</option>
										</select>
									</div>
									<div class="uk-width-1-1">
										กลุ่มย่อยการรักษา
										<select class="doctor">
											<option value="o">1</option>
											<option value="p">2</option>
											<option value="i">3</option>
											<option value="g">4</option>
										</select>
									</div>
									<div class="uk-width-1-1">
										DF
										<input type="text" value="43">%
									</div>
									<div class="uk-width-1-1">
										LAB
										<input type="text" value="43">%
									</div>
									<div class="uk-width-1-1 uk-text-center">
										<button class="uk-button uk-button-primary"><i class="uk-icon-search"></i> ค้นหา</button>
										<button class="uk-button uk-button-success"><i class="uk-icon-check"></i> แก้ไขการตั้งค่า</button>
									</div>
								</div>
							</div>
							<!-- End Set up branch & doctor -->
						</div>
					</div>
				</div> 
			<script type="text/javascript">
			$(document).ready(function(){
				$( ".m-setting" ).addClass( "uk-active" );
				$(".brand").change(function(){
					$('.branch').children('option').remove();
					if($(".brand").val() == 1){
						$(".branch").append( 
								"<option value='1'>LDC สาขาลาดพร้าว 89</option>"+
			            		"<option value='2'>LDC สาขารามคำแหง 135</option>");
					}else{
						$(".branch").append( 
								"<option value='3'>ใส่ใจทันตแพทย์ สายไหม</option>");
					}
					
						
				});
				
			});
			</script>
		
	</body>
</html>