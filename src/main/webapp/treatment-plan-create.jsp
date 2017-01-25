<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:แผนการรักษา</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<div class="uk-grid uk-grid-collapse">
					<%@include file="shortpatient-leftside.jsp" %>
					<div class="uk-width-6-10">
							<form action="createTreatmentPlan" method="post">
							<div class="uk-grid uk-grid-collapse">
							
							<div class="uk-width-1-1 uk-form">
								<div class="uk-panel uk-panel-box padding5 ">
								<h4  class="hd-text uk-text-primary">แผนการรักษา  </h4>
									<div class="uk-grid">
										<div class="uk-width-1-5 uk-text-right">
											ชื่อแผนการรักษา
										</div>
										<div class="uk-width-4-5">
											<s:textfield name="treatPlanModel.treatmentPlanname" placeholder="ชื่อแผนการรักษา" required="true"/>
											<button type="submit" class="uk-button uk-button-success"><i class="uk-icon-plus"></i> สร้างแผนการรักษา</button>
											<a href="viewAllTreatmentPlan" class="uk-button uk-button-danger"><i class="uk-icon-close"></i> ยกเลิกแผนการรักษา</a>
										</div>
											
										
									</div>
									
								</div>
							</div>
							
						</div>
						</form>
						
					</div>
				</div>
				
				<div id="treatment" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-medkit"></i> การรักษา</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table"> 
									            <th class="uk-text-center">เลือก</th> 
									            <th class="uk-text-center">ประเภทการรักษา</th>
									            <th class="uk-text-center">รหัสการรักษา</th>
									            <th class="uk-text-center">ชื่อการรักษา</th> 
									            <th class="uk-text-center">df</th>
									            <th class="uk-text-center">lab</th>
									            <th class="uk-text-center">ราคา</th>
									        </tr>
									    </thead> 
									    <tbody>
									    	<tr> 
										       	<td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" id="form-s-c" checked name="r1"> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
                                        		<td class="uk-text-center">การรักษาปกติ</td>
										        <td class="uk-text-center">SUR020</td>
										        <td class="uk-text-center">อุดฟัน</td>
										        <td class="uk-text-center">43%</td>
										        <td class="uk-text-center">25%</td>
										        <td class="uk-text-right">1,000.00</td>
											</tr> 
											<tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" id="form-s-c" name="r1"> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
                                        		<td class="uk-text-center">การรักษาปกติ</td>
										        <td class="uk-text-center">SUR030</td>
										        <td class="uk-text-center">รากฟัน</td> 
										        <td class="uk-text-center">43%</td>
										        <td class="uk-text-center">25%</td>
										        <td class="uk-text-right">5,000.00</td>
											</tr>
											<tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" id="form-s-c" name="r1"> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
                                        		<td class="uk-text-center">การรักษาปกติ</td>
										        <td class="uk-text-center">SUR010</td>
										        <td class="uk-text-center">ถอนฟัน</td>
										        <td class="uk-text-center">43%</td>
										        <td class="uk-text-center">25%</td>
										        <td class="uk-text-right">3,000.00</td>
											</tr>
											<tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" id="form-s-c" name="r1"> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
                                        		<td class="uk-text-center">การรักษาปกติ</td>
										        <td class="uk-text-center">SUR025</td>
										        <td class="uk-text-center">ขุดหินปูน</td>
										        <td class="uk-text-center">43%</td>
										        <td class="uk-text-center">25%</td>
										        <td class="uk-text-right">2,000.00</td>
											</tr>
											<tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" id="form-s-c" name="r1"> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
                                        		<td class="uk-text-center">การรักษาต่อเนื่อง</td>
										        <td class="uk-text-center">ORT001</td>
										        <td class="uk-text-center">จัดฟันไม่ต้องถอนฟัน</td>
										        <td class="uk-text-center">43%</td>
										        <td class="uk-text-center">25%</td>
										        <td class="uk-text-right">2,000.00</td>
											</tr>
										</tbody>
									</table>
									</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close" id="updateb" onclick="click_treatment()" name="updateb">ตกลง</button>
					         	<button class="uk-modal-close">ยกเลิก</button>
					         </div>
					    </div>
					</div>
					
					<div id="docter" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-stethoscope"></i> แพทย์</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table"> 
									            <th class="uk-text-center">คลิก</th> 
									            <th class="uk-text-center">รหัสแพทย์</th>
									            <th class="uk-text-center">ชื่อแพทย์</th> 
									        </tr>
									    </thead> 
									    <tbody>
									    	<tr> 
										       	<td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" id="form-s-c" checked> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
										        <td class="uk-text-center">11001</td>
										        <td class="uk-text-center">ทพ.นพดล</td> 
											</tr> 
											<tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" id="form-s-c"> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
										        <td class="uk-text-center">11002</td>
										        <td class="uk-text-center">ทพ.จักรกิตติ</td> 
											</tr>
											<tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" id="form-s-c"> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
										        <td class="uk-text-center">11003</td>
										        <td class="uk-text-center">ทพ.พรทิตย์</td>  
											</tr>
											<tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" id="form-s-c"> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
										        <td class="uk-text-center">11004</td>
										        <td class="uk-text-center">ทพ.พงศ์ศาสตร์</td>
											</tr>
											<tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" id="form-s-c"> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
										        <td class="uk-text-center">11005</td>
										        <td class="uk-text-center">ทพ.อภัสรา</td>
											</tr>
											<tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" id="form-s-c"> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
										        <td class="uk-text-center">11006</td>
										        <td class="uk-text-center">ทพ.ณัญดา</td>
											</tr>
											<tr> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" id="form-s-c"> <label for="form-s-c"></label>
                                        			</div>
                                        		</td>
										        <td class="uk-text-center">11007</td>
										        <td class="uk-text-center">ทพ.สุจิตรา</td>
											</tr>
										</tbody>
									</table>
									</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close" id="updateb" onclick="click_docter()" name="updateb">ตกลง</button>
					         	<button class="uk-modal-close">ยกเลิก</button>
					         </div>
					    </div>
					</div>
				
					<div id="medicine" class="uk-modal ">
					    <div class="uk-modal-dialog uk-modal-dialog-large uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-medkit"></i> ยา</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
									    <thead>
									        <tr class="hd-table"> 
									        	<th class="uk-text-center">ยา</th>
									            <th class="uk-text-center">รับประทาน ครั้งละ</th> 
									            <th class="uk-text-center">วันละ</th>
									            <th class="uk-text-center">รับประทาน</th> 
									            <th class="uk-text-center">เวลา</th>  
									        </tr>
									    </thead> 
									    <tbody>
									    	<tr>  
									    		<td class="uk-text-left"><input type="text" id="ya" class="uk-form-small uk-width-1-1" disabled="disabled"> </td>
										        <td class="uk-text-center"><input type="text" id="t11" name="t11" class="uk-form-small uk-width-1-3"> เม็ด</td>
										        <td class="uk-text-center"><input type="text" name="t12" class="uk-form-small uk-width-1-3"> ครั้ง</td> 
										        <td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="radio" name="r11" > ก่อน
			                                            <input type="radio" name="r11" > หลัง
                                        			</div>
                                        		</td>
                                        		<td class="uk-text-center">
										        	<div class="uk-form-controls">
			                                            <input type="checkbox" name="c11" > เช้า
			                                            <input type="checkbox" name="c11" > กลางวัน
			                                            <input type="checkbox" name="c11" > เย็น
			                                            <input type="checkbox" name="c11" > ก่อนนอน
                                        			</div>
                                        		</td>
											</tr>  
										</tbody>
									</table>
									</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close" id="updateb" onclick="click_docter()" name="updateb">ตกลง</button>
					         	<button class="uk-modal-close">ยกเลิก</button> 
					         </div>
					    </div>
					</div>
					
					<div id="treatment_continous" class="uk-modal ">
					    <div class="uk-modal-dialog uk-modal-dialog-large uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-circle-o-notch"></i> ตั้งค่าการรักษาต่อเนื่อง</div>
					         	<div class="uk-grid uk-width-10-10">
									
									<div class="uk-width-1-3 uk-text-right">รหัสการรักษา</div>
									<div class="uk-width-2-3"><input type="text" value="RUS002" >  </div>
									
									<div class="uk-width-1-3 uk-text-right">การรักษา</div>
									<div class="uk-width-2-3"><input type="text" value="จัดฟันแบบไม่ถอน" >  </div>
									
									<div class="uk-width-1-3 uk-text-right">ค่ารักษาปกติ</div>
									<div class="uk-width-2-3"><input type="text" value="65,000" > บาท</div>
									
									<div class="uk-width-1-3 uk-text-right">โปรโมชั่นที่ใช้ได้</div>
									<div class="uk-width-1-3">
										<select size="5" style="width: 100%">
											<option selected="selected">จัดฟันเหมาจ่าย</option>
										</select>  
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">ได้ส่วนลด</div>
									<div class="uk-width-2-3"><input type="text" value="6,000"> บาท</div>
									
									<div class="uk-width-1-3 uk-text-right">ราคาที่ต้องจ่าย</div>
									<div class="uk-width-2-3"><input type="text" value="59,000" > บาท</div>
									
									<div class="uk-width-1-3 uk-text-right">ชำระไปแล้ว</div>
									<div class="uk-width-2-3"><input type="text" value="0" > บาท</div>
									
									<div class="uk-width-1-3 uk-text-right">ยอดคงเหลือที่ต้องชำระ</div>
									<div class="uk-width-2-3"><input type="text" value="59,000" > บาท</div>
									
									<div class="uk-width-1-3 uk-text-right">ราคาที่ต้องชำระช่วงต้น</div>
									<div class="uk-width-2-3"><input type="text" value="4,000" > บาท จำนวน  <input type="text" value="3" > ครั้ง</div>
									
									<div class="uk-width-1-3 uk-text-right">ราคาที่ต้องชำระจนจบ</div>
									<div class="uk-width-2-3"><input type="text" value="2,000" > บาท จำนวน  <input type="text" value="23" > ครั้ง</div>
								</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close" id="updateb" onclick="click_docter()" name="updateb">ตกลง</button>
					         	<button class="uk-modal-close">ยกเลิก</button> 
					         </div>
					    </div>
					</div>
					<div id="treatment_continous1" class="uk-modal ">
					    <div class="uk-modal-dialog uk-modal-dialog-large uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-circle-o-notch"></i> ตั้งค่าการรักษาต่อเนื่อง</div>
					         	<div class="uk-grid uk-width-10-10">
									
									<div class="uk-width-1-3 uk-text-right">รหัสการรักษา</div>
									<div class="uk-width-2-3"><input type="text" value="RUS002" >  </div>
									
									<div class="uk-width-1-3 uk-text-right">การรักษา</div>
									<div class="uk-width-2-3"><input type="text" value="จัดฟันแบบไม่ถอน" >  </div>
									
									<div class="uk-width-1-3 uk-text-right">ค่ารักษาปกติ</div>
									<div class="uk-width-2-3"><input type="text" value="65,000" > บาท</div>
									
									<div class="uk-width-1-3 uk-text-right">โปรโมชั่นที่ใช้ได้</div>
									<div class="uk-width-1-3">
										<select size="5" style="width: 100%">
											<option selected="selected">จัดฟันเหมาจ่าย</option>
										</select>  
									</div>
									<div class="uk-width-1-3"></div>
									
									<div class="uk-width-1-3 uk-text-right">ได้ส่วนลด</div>
									<div class="uk-width-2-3"><input type="text" value="6,000"> บาท</div>
									
									<div class="uk-width-1-3 uk-text-right">ราคาที่ต้องจ่าย</div>
									<div class="uk-width-2-3"><input type="text" value="59,000" > บาท</div>
									
									<div class="uk-width-1-3 uk-text-right">ชำระไปแล้ว</div>
									<div class="uk-width-2-3"><input type="text" value="12,000" > บาท</div>
									
									<div class="uk-width-1-3 uk-text-right">ยอดคงเหลือที่ต้องชำระ</div>
									<div class="uk-width-2-3"><input type="text" value="59,000" > บาท</div>
									
									<div class="uk-width-1-3 uk-text-right">ราคาที่ต้องชำระช่วงต้น</div>
									<div class="uk-width-2-3"><input type="text" value="4,000" > บาท จำนวน  <input type="text" value="3" > ครั้ง</div>
									
									<div class="uk-width-1-3 uk-text-right">ราคาที่ต้องชำระจนจบ</div>
									<div class="uk-width-2-3"><input type="text" value="2,000" > บาท จำนวน  <input type="text" value="23" > ครั้ง</div>
								</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close" id="updateb" onclick="click_docter()" name="updateb">ตกลง</button>
					         	<button class="uk-modal-close">ยกเลิก</button> 
					         </div>
					    </div>
					</div>
			</div>
		</div>
		<script>
			$(document).ready(function(){
				$( ".m-patient" ).addClass( "uk-active" );				
			});
		</script>
		
	</body>
</html>