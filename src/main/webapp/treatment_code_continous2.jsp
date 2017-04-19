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
				<%@include file="nav-top.jsp" %>
 
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-width-1-1 padding5 uk-form" >
					
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
						 	<p class="uk-text-muted uk-width-1-1">ข้อมูล</p>
						 	
						 	<div class="uk-width-1-2 uk-text-right">แบรนด์บรษัท : </div>
							<div class="uk-width-1-2">
								<select>
									<option>LDC</option>
									<option>ใส่ใจทันตแพทย์</option>
								</select>
							</div>
							<div class="uk-width-1-2 uk-text-right">กลุ่มการรักษา : </div>
							<div class="uk-width-1-2">
								<select>
									<option>กลุ่มการรักษา 1</option>
									<option>กลุ่มการรักษา 2</option>
									<option>กลุ่มการรักษา 3</option>
								</select>
							</div>
							<div class="uk-width-1-2 uk-text-right">รหัสการรักษา : </div>
							<div class="uk-width-1-2">
								<input type="text" class="uk-width-1-2" placeholder="RUS002" />
							</div> 
							<div class="uk-width-1-2 uk-text-right">ราคาสวัสดิการพนักงาน : </div>
							<div class="uk-width-1-2">
								<input type="text" pattern="[0-9]"  title="กกรอกเฉพาะตัวเลขเท่านั้น"class="uk-width-1-2" placeholder="18500" />
							</div> 
							<div class="uk-width-1-2 uk-text-right">ส่วนแบ่งแพทย์ : </div>
							<div class="uk-width-1-2">
								<input type="text" pattern="[0-9]"  title="กกรอกเฉพาะตัวเลขเท่านั้น"class="uk-width-1-2" placeholder="บาท" />
							</div>
							<div class="uk-width-1-2 uk-text-right">ค่า LAB : </div>
							<div class="uk-width-1-2">
								<input type="text" pattern="[0-9]"  title="กกรอกเฉพาะตัวเลขเท่านั้น"class="uk-width-1-2" placeholder="%" />
							</div>
							<div class="uk-width-1-2 uk-text-right">Homecall : </div>
							<div class="uk-width-1-2">
								<div class="uk-form-controls">
	                                <input type="checkbox" name="r11" > อัตโนมัติ 
                                </div>
							</div>
							<div class="uk-width-1-2 uk-text-right">Recall : </div>
							<div class="uk-width-1-2">
								<div class="uk-form-controls">
	                                <input type="radio" name="r11" checked> ปกติ <input type="radio" name="r11" > พิเศษ 
                                </div>
							</div>
							<div class="uk-width-1-2 uk-text-right">ชุดการรักษา : </div>
							<div class="uk-width-1-2">
								<ul class="uk-subnav uk-subnav-pill" data-uk-switcher="{connect:'#subnav-pill-content-1'}">
	                                <li aria-expanded="false" class=""><a href="#">ปกติ</a></li>
	                                <li class="uk-active" aria-expanded="true"><a href="#">ต่อเนื่อง</a></li>
	                            </ul>
							</div>
							<div class="uk-width-1-1 padding5 uk-form" >
							<ul id="subnav-pill-content-1" class="uk-switcher">
		                                <li class="uk-grid uk-grid-collapse" >
		                                	<div class="uk-width-1-2 uk-text-right">ค่ารักษา : </div>
											<div class="uk-width-1-2">
												<input type="text" pattern="[0-9]"  title="กกรอกเฉพาะตัวเลขเท่านั้น"class="uk-width-1-2" placeholder="28500" />
											</div> 
										</li>
										<li class="uk-active uk-grid uk-grid-collapse">
											<div class="uk-width-1-2 uk-text-right">จำนวนเงินที่จ่าย : </div>
											<div class="uk-width-1-2 ">
												<input type="text" pattern="[0-9]"  title="กกรอกเฉพาะตัวเลขเท่านั้น" class="uk-width-1-2" placeholder="5000" />
											</div>
											<div class="uk-width-1-2 uk-text-right">จำนวนครั้ง : </div>
											<div class="uk-width-1-2">
												<input type="text" pattern="[0-9]"  title="กกรอกเฉพาะตัวเลขเท่านั้น" class="uk-width-1-2" placeholder="3" />
											</div> 
											 
											  
												<div class="uk-panel uk-panel-box uk-width-medium-1-1 uk-container-center">
					                                <div class="uk-panel-badge uk-badge uk-badge-primary">ช่วงที่ 1</div> 
					                                <div class="uk-panel-header">
					                                	<div class="uk-grid uk-grid-small">
													    <h3 class="uk-panel-title uk-width-3-10 uk-text-left">
													    	<div class="uk-form-icon uk-width-1-1"> 
													    	<i class="uk-icon-money"></i> 
													    	<input type="text" pattern="[0-9]"  title="กกรอกเฉพาะตัวเลขเท่านั้น" class="uk-width-1-1" placeholder="จำนวนเงิน" />
													    	</div>
													    </h3>
													    <h3 class="uk-panel-title uk-width-7-10 uk-text-left">
													    	<div class="uk-form-icon uk-width-1-1">
													    	<i class="uk-icon-qrcode"></i> 
													    	<input type="text" pattern="[0-9]"  title="กกรอกเฉพาะตัวเลขเท่านั้น" class="uk-width-3-10" placeholder="จำนวน" /> 
													    	</div>
													    </h3>
													    </div>
													</div> 
													
													<div class="uk-grid uk-grid-small">
														<div class="uk-width-1-3"> 
															<div class="uk-grid uk-grid-small">
																<div class="uk-width-3-10">  
																	<h3>การรักษา</h3> 
																</div> 
															</div>
															<div class="uk-grid uk-grid-small ">
																<div class="uk-width-8-10 uk-container-center">
																<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1 table-components">
																	<thead>
																        <tr class="hd-table">
																        	<th class="uk-text-center">ลำดับ</th>
																        	<th class="uk-text-center">การรักษา</th>
																        	<th class="uk-text-center">จำนวน</th> 
																        	<th class="uk-text-center"> </th> 
																        </tr> 
																    </thead>
															        <tbody> 
																        <tr>
																        	<td class="uk-text-center uk-width-2-10">1</td>
																            <td class="uk-text-center uk-width-4-10"><input type="text" placeholder="การรักษา" class="uk-form-small uk-width-1-1" /></td>
																            <td class="uk-text-center uk-width-2-10"><input type="text" placeholder="0" class="uk-form-small uk-width-1-1 uk-text-right" /></td> 
																            <td class="uk-text-center uk-width-2-10"><button class="btn uk-button uk-button-success uk-button-small add-elements"><i class="uk-icon-plus"></i></button>
								                                			</td>
																        </tr> 
																    </tbody>
																</table>
															 	</div>
															</div>
														</div>
														<div class="uk-width-1-3"> 
															<div class="uk-grid uk-grid-small">
																<div class="uk-width-3-10">  
																	<h3>สินค้า</h3> 
																</div> 
															</div>
															<div class="uk-grid uk-grid-small ">
																<div class="uk-width-8-10 uk-container-center">
																<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1 table-components-product">
																	<thead>
																        <tr class="hd-table">
																        	<th class="uk-text-center">ลำดับ</th>
																        	<th class="uk-text-center">สินค้า</th>
																        	<th class="uk-text-center">จำนวน</th> 
																        	<th class="uk-text-center"> </th> 
																        </tr> 
																    </thead>
															        <tbody> 
																        <tr>
																        	<td class="uk-text-center uk-width-2-10">1</td>
																            <td class="uk-text-center uk-width-4-10"><input type="text" placeholder="สินค้า" class="uk-form-small uk-width-1-1" /></td>
																            <td class="uk-text-center uk-width-2-10"><input type="text" placeholder="0" class="uk-form-small uk-width-1-1 uk-text-right" /></td> 
																            <td class="uk-text-center uk-width-2-10"><button class="btnproduct uk-button uk-button-success uk-button-small add-elements"><i class="uk-icon-plus"></i></button>
								                                			</td>
																        </tr> 
																    </tbody>
																</table>
															 	</div> 
															</div>
														</div>
														<div class="uk-width-1-3"> 
															<div class="uk-grid uk-grid-small">
																<div class="uk-width-3-10">  
																	<h3>ยา</h3> 
																</div> 
															</div>
															<div class="uk-grid uk-grid-small ">
																<div class="uk-width-8-10 uk-container-center">
																<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1 table-components-medicine">
																	<thead>
																        <tr class="hd-table">
																        	<th class="uk-text-center">ลำดับ</th>
																        	<th class="uk-text-center">ยา</th>
																        	<th class="uk-text-center">จำนวน</th> 
																        	<th class="uk-text-center"> </th> 
																        </tr> 
																    </thead>
															        <tbody> 
																        <tr>
																        	<td class="uk-text-center uk-width-2-10">1</td>
																            <td class="uk-text-center uk-width-4-10"><input type="text" placeholder="ยา" class="uk-form-small uk-width-1-1" /></td>
																            <td class="uk-text-center uk-width-2-10"><input type="text" placeholder="0" class="uk-form-small uk-width-1-1 uk-text-right" /></td> 
																            <td class="uk-text-center uk-width-2-10"><button class="btnmedicine uk-button uk-button-success uk-button-small add-elements"><i class="uk-icon-plus"></i></button>
								                                			</td>
																        </tr> 
																    </tbody>
																</table>
															 	</div>  
															</div>
														</div>
													</div> 
													   
					                       		</div> 
					                       		
					                       		<div class="uk-width-1-1 "><br></div>
												<div class="uk-width-1-1  uk-text-center" >
													<button type="submit" class="uk-button uk-button-success uk-button-large uk-icon-floppy-o"> เพิ่มการรักษา</button>
													<button type="submit" class="uk-button uk-button-danger uk-button-large uk-icon-retweet btn-reset"> ยกเลิก</button> 
												</div> 
					                       		<div class="uk-width-1-1 "><br></div>
												
			 									<div class="uk-width-1-1 padding5 border-gray">
			 									ตารางแผนการรักษา
					                       		<div class="uk-panel uk-panel-box uk-width-medium-1-1 uk-container-center">
					                                <div class="uk-panel-badge uk-badge uk-badge-success">ช่วงที่ 1</div> 
					                                <div class="uk-panel-header">
					                                	<div class="uk-grid uk-grid-small">
													    <h3 class="uk-panel-title uk-width-3-10 uk-text-left">
													    	<div class="uk-form-icon uk-width-1-1"> 
													    	<i class="uk-icon-money"></i> 
													    	<input type="text" pattern="[0-9]"  title="กกรอกเฉพาะตัวเลขเท่านั้น" class="uk-width-1-1" value="65,000" />
													    	</div>
													    </h3>
													    <h3 class="uk-panel-title uk-width-7-10 uk-text-left">
													    	<div class="uk-form-icon uk-width-1-1">
													    	<i class="uk-icon-qrcode"></i> 
													    	<input type="text" pattern="[0-9]"  title="กกรอกเฉพาะตัวเลขเท่านั้น" class="uk-width-3-10" value="1" /> 
													    	</div>
													    </h3>
													    </div>
													</div> 
													
													<div class="uk-grid uk-grid-small">
														<div class="uk-width-1-3"> 
															<div class="uk-grid uk-grid-small">
																<div class="uk-width-3-10">  
																	<h3>การรักษา</h3> 
																</div> 
															</div>
															<div class="uk-grid uk-grid-small ">
																<div class="uk-width-8-10 uk-container-center">
																<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1">
																	<thead>
																        <tr class="hd-table">
																        	<th class="uk-text-center">ลำดับ</th>
																        	<th class="uk-text-center">การรักษา</th>
																        	<th class="uk-text-center">จำนวน</th> 
																        </tr> 
																    </thead>
															        <tbody> 
																        <tr>
																        	<td class="uk-text-center uk-width-2-10">1</td>
																            <td class="uk-text-center uk-width-4-10"><input type="text" value="รักษารากฟัน" class="uk-form-small uk-width-1-1" /></td>
																            <td class="uk-text-center uk-width-2-10"><input type="text" value="1" class="uk-form-small uk-width-1-1 uk-text-right" /></td> 
																        </tr> 
																        <tr>
																        	<td class="uk-text-center uk-width-2-10">2</td>
																            <td class="uk-text-center uk-width-4-10"><input type="text" value="ขูดหินปูน" class="uk-form-small uk-width-1-1" /></td>
																            <td class="uk-text-center uk-width-2-10"><input type="text" value="1" class="uk-form-small uk-width-1-1 uk-text-right" /></td> 
																        </tr>
																    </tbody>
																</table>
															 	</div>
															</div>
														</div>
														<div class="uk-width-1-3"> 
															<div class="uk-grid uk-grid-small">
																<div class="uk-width-3-10">  
																	<h3>สินค้า</h3> 
																</div> 
															</div>
															<div class="uk-grid uk-grid-small ">
																<div class="uk-width-8-10 uk-container-center">
																<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1">
																	<thead>
																        <tr class="hd-table">
																        	<th class="uk-text-center">ลำดับ</th>
																        	<th class="uk-text-center">สินค้า</th>
																        	<th class="uk-text-center">จำนวน</th> 
																        </tr> 
																    </thead>
															        <tbody> 
																        <tr>
																        	<td class="uk-text-center uk-width-2-10">1</td>
																            <td class="uk-text-center uk-width-4-10"><input type="text" value="ลวดเหล็กจัดฟัน" class="uk-form-small uk-width-1-1" /></td>
																            <td class="uk-text-center uk-width-2-10"><input type="text" value="1" class="uk-form-small uk-width-1-1 uk-text-right" /></td> 
																        </tr> 
																    </tbody>
																</table>
															 	</div> 
															</div>
														</div>
														<div class="uk-width-1-3"> 
															<div class="uk-grid uk-grid-small">
																<div class="uk-width-3-10">  
																	<h3>ยา</h3> 
																</div> 
															</div>
															<div class="uk-grid uk-grid-small ">
																<div class="uk-width-8-10 uk-container-center">
																<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1">
																	<thead>
																        <tr class="hd-table">
																        	<th class="uk-text-center">ลำดับ</th>
																        	<th class="uk-text-center">ยา</th>
																        	<th class="uk-text-center">จำนวน</th>  
																        </tr> 
																    </thead>
															        <tbody> 
																        <tr>
																        	<td class="uk-text-center uk-width-2-10">1</td>
																            <td class="uk-text-center uk-width-4-10"><input type="text" value="พารา" class="uk-form-small uk-width-1-1" /></td>
																            <td class="uk-text-center uk-width-2-10"><input type="text" value="20" class="uk-form-small uk-width-1-1 uk-text-right" /></td> 
																        </tr> 
																    </tbody>
																</table>
															 	</div>  
															</div>
														</div>
													</div> 
												</div>   
												<div class="uk-width-1-1 "><hr></div>
												<div class="uk-panel uk-panel-box uk-width-medium-1-1 uk-container-center">
					                                <div class="uk-panel-badge uk-badge uk-badge-success">ช่วงที่ 2</div> 
					                                <div class="uk-panel-header">
					                                	<div class="uk-grid uk-grid-small">
													    <h3 class="uk-panel-title uk-width-3-10 uk-text-left">
													    	<div class="uk-form-icon uk-width-1-1"> 
													    	<i class="uk-icon-money"></i> 
													    	<input type="text" pattern="[0-9]"  title="กกรอกเฉพาะตัวเลขเท่านั้น" class="uk-width-1-1" value="2,000" />
													    	</div>
													    </h3>
													    <h3 class="uk-panel-title uk-width-7-10 uk-text-left">
													    	<div class="uk-form-icon uk-width-1-1">
													    	<i class="uk-icon-qrcode"></i> 
													    	<input type="text" pattern="[0-9]"  title="กกรอกเฉพาะตัวเลขเท่านั้น" class="uk-width-3-10" value="2" /> 
													    	</div>
													    </h3>
													    </div>
													</div> 
													 
													<div class="uk-grid uk-grid-small">
														<div class="uk-width-1-3"> 
															<div class="uk-grid uk-grid-small">
																<div class="uk-width-3-10">  
																	<h3>การรักษา</h3> 
																</div> 
															</div>
															<div class="uk-grid uk-grid-small ">
																<div class="uk-width-8-10 uk-container-center">
																<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1">
																	<thead>
																        <tr class="hd-table">
																        	<th class="uk-text-center">ลำดับ</th>
																        	<th class="uk-text-center">การรักษา</th>
																        	<th class="uk-text-center">จำนวน</th> 
																        </tr> 
																    </thead>
															        <tbody> 
																        <tr>
																        	<td class="uk-text-center uk-width-2-10">1</td>
																            <td class="uk-text-center uk-width-4-10"><input type="text" placeholder="ตรวจปกติ" class="uk-form-small uk-width-1-1" /></td>
																            <td class="uk-text-center uk-width-2-10"><input type="text" placeholder="1" class="uk-form-small uk-width-1-1 uk-text-right" /></td> 
																        </tr> 
																    </tbody>
																</table>
															 	</div>
															</div>
														</div>
														<div class="uk-width-1-3"> 
															<div class="uk-grid uk-grid-small">
																<div class="uk-width-3-10">  
																	<h3>สินค้า</h3> 
																</div> 
															</div>
															<div class="uk-grid uk-grid-small ">
																<div class="uk-width-8-10 uk-container-center">
																<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1">
																	<thead>
																        <tr class="hd-table">
																        	<th class="uk-text-center">ลำดับ</th>
																        	<th class="uk-text-center">สินค้า</th>
																        	<th class="uk-text-center">จำนวน</th> 
																        </tr> 
																    </thead>
															        <tbody> 
																        <tr>
																        	<td class="uk-text-center uk-width-2-10">1</td>
																            <td class="uk-text-center uk-width-4-10"><input type="text" value="ลวดฟัน" class="uk-form-small uk-width-1-1" /></td>
																            <td class="uk-text-center uk-width-2-10"><input type="text" value="1" class="uk-form-small uk-width-1-1 uk-text-right" /></td> 
																        </tr> 
																    </tbody>
																</table>
															 	</div> 
															</div>
														</div>
														<div class="uk-width-1-3"> 
															<div class="uk-grid uk-grid-small">
																<div class="uk-width-3-10">  
																	<h3>ยา</h3> 
																</div> 
															</div>
															<div class="uk-grid uk-grid-small ">
																<div class="uk-width-8-10 uk-container-center">
																<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1">
																	<thead>
																        <tr class="hd-table">
																        	<th class="uk-text-center">ลำดับ</th>
																        	<th class="uk-text-center">ยา</th>
																        	<th class="uk-text-center">จำนวน</th>  
																        </tr> 
																    </thead>
															        <tbody> 
																        <tr>
																        	<td class="uk-text-center uk-width-2-10">1</td>
																            <td class="uk-text-center uk-width-4-10"><input type="text" value="แคลเซียม" class="uk-form-small uk-width-1-1" /></td>
																            <td class="uk-text-center uk-width-2-10"><input type="text" value="30" class="uk-form-small uk-width-1-1 uk-text-right" /></td> 
																        </tr> 
																    </tbody>
																</table>
															 	</div>  
															</div>
														</div>
													</div> 
												</div>
					                       	</div>
										</li>
										
										 
		                            </ul> 
						</div>
						</div>
						 
					</div>
					
				</div>  
				
			</div>
		</div>
		<script>
		$(document).ready(function(){
			$( ".m-setting" ).addClass( "uk-active" );
		    
		    $(".btn-reset").click(function(){
		    	$('.table-components tbody > tr:not(:first-child)').remove();
		    	$('.table-components-product tbody > tr:not(:first-child)').remove();
		    	$('.table-components-medicine tbody > tr:not(:first-child)').remove();
		    	});
		    
		    $('.table-components .add-elements').on('click', function(){ 
				var clone = $(".table-components tbody tr:first-child");
				clone.find('.btn').removeClass('add-elements uk-button-success').addClass('delete-elements uk-button-danger').html('<i class="uk-icon-times"></i>');
				clone.find('td:first-child').html($('.table-components tbody tr').length + 1); 
				clone.clone().appendTo('.table-components tbody');
				clone.find('td:first-child').html(1);
				clone.find('.btn').removeClass('delete-elements uk-button-danger').addClass('add-elements uk-button-success').html('<i class="uk-icon-plus"></i>');
				
				$('.table-components tbody tr:last-child td:nth-child(2)').on('change', function(event) {
					$(this).closest("tr").find('td:nth-child(3) input').focus();
				});
			});
		    
		    $('.table-components').on( "click", ".delete-elements", function() {
				$(this).closest("tr").remove();
				var n = 0;
				$('.table-components tbody tr').each(function(){
					n++;
					$(this).find('td:first-child').html(n);
				});
			});
		    
		    $('.table-components-product .add-elements').on('click', function(){ 
				var clone = $(".table-components-product tbody tr:first-child");
				clone.find('.btnproduct').removeClass('add-elements uk-button-success').addClass('delete-elements uk-button-danger').html('<i class="uk-icon-times"></i>');
				clone.find('td:first-child').html($('.table-components-product tbody tr').length + 1); 
				clone.clone().appendTo('.table-components-product tbody');
				clone.find('td:first-child').html(1);
				clone.find('.btnproduct').removeClass('delete-elements uk-button-danger').addClass('add-elements uk-button-success').html('<i class="uk-icon-plus"></i>');
				
				$('.table-components-product tbody tr:last-child td:nth-child(2)').on('change', function(event) {
					$(this).closest("tr").find('td:nth-child(3) input').focus();
				});
			});
		    
		    $('.table-components-product').on( "click", ".delete-elements", function() {
				$(this).closest("tr").remove();
				var n = 0;
				$('.table-components-product tbody tr').each(function(){
					n++;
					$(this).find('td:first-child').html(n);
				});
			});
		    
		    $('.table-components-medicine .add-elements').on('click', function(){ 
				var clone = $(".table-components-medicine tbody tr:first-child");
				clone.find('.btnmedicine').removeClass('add-elements uk-button-success').addClass('delete-elements uk-button-danger').html('<i class="uk-icon-times"></i>');
				clone.find('td:first-child').html($('.table-components-medicine tbody tr').length + 1); 
				clone.clone().appendTo('.table-components-medicine tbody');
				clone.find('td:first-child').html(1);
				clone.find('.btnmedicine').removeClass('delete-elements uk-button-danger').addClass('add-elements uk-button-success').html('<i class="uk-icon-plus"></i>');
				
				$('.table-components-medicine tbody tr:last-child td:nth-child(2)').on('change', function(event) {
					$(this).closest("tr").find('td:nth-child(3) input').focus();
				});
			});
		    
		    $('.table-components-medicine').on( "click", ".delete-elements", function() {
				$(this).closest("tr").remove();
				var n = 0;
				$('.table-components-medicine tbody tr').each(function(){
					n++;
					$(this).find('td:first-child').html(n);
				});
			});
		    
		    $('.table-components tbody').on('change', 'input', function() {
		    	var tr = $(this).closest("tr");
		    	
		    	var val1 = tr.find('td:nth-child(2) input').val().trim();
		    	var val2 = tr.find('td:nth-child(3) input').val().trim();
		    	 
		    /*	alert(val1+val2);
		    	
		    	$.ajax({  // update
		            type: "post",
			        url: "ajax/ajax-update.jsp", //this is my servlet 
			        data: {eventid:event.id,start:event.start.format(),end:event.end.format()},
			        async:false, 
			        success: function(result){ 
			        	obj = JSON.parse(result); 
				     } 
			     });
		    	
				if ((val1.length) == 0){
					tr.addClass('hd-table');
				}
				else{
					tr.removeClass('hd-table');
				}   */
			});   
		       
		    
		    
		    $("#btnr").click(function(){
		    	$(".rl").first().clone().appendTo(".rs"); 
		    });
		    
		    
		    $(".btnrs").click(function(){ 
		    	 
		    	 $(this).parents(".rl").remove();
		    });
		    
		}); 
		
		</script>		
	</body>
</html>