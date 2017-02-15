<%@ page language="java" import="java.util.*,java.text.DecimalFormat"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*"%>
<%@ page import="com.smict.treatment.data.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
	<title>Smart Dental:รอคิว</title>
</head>
<body>
<div class="uk-grid uk-grid-collapse">
		<div class="uk-width-1-10">
			<%@include file="nav-right.jsp" %>
		</div> 
		<div class="uk-width-9-10">
			<%@include file="nav-top.jsp"%>
			<div class="uk-grid uk-grid-collapse">
				<div class="uk-width-1-10 padding5 "></div>
				<div class="uk-width-2-10 padding5" id="patient-list-col">
					<div class="uk-grid  padding5  border-gray uk-panel-box-primary">
						<div class="uk-width-1-1 padding5">
							<h3 class="uk-text-center bg-gray border-gray uk-text-primary uk-text-medium">
								คนไข้
							</h3>
						</div>
						<div class="uk-width-1-1 padding5">
							<a class="uk-button uk-button-success uk-button-mini uk-align-medium-right"
								title="เพิ่มคิวคนไข้">
								<i class="uk-icon-wheelchair margin5"></i>
								<span>เพิ่มคิวคนไข้</span>
							</a>
						</div>
						<ul class="uk-nestable uk-width-1-1 padding5" 
							data-uk-nestable="{group: 'patient-group', maxDepth: 1}">
							<li class="uk-nestable-item">
								<h4 class="hd-text border-gray bg-gray padding5 uk-nestable-panel">
									<small class=" uk-text-primary">ชื่อแพทย์ : </small>
									<s:property value="" />
									<li>
										<small class="uk-text-primary">ชื่อคนไข้ : </small>
										<s:property value="" />
									</li>
									<li>
										<small class="uk-text-primary">HN : </small> 
										<s:property value="" />
									</li>
									<li>
										<small class="uk-text-primary">ห้อง:</small>
										<s:property value="" />
										<a class="uk-button uk-button-danger uk-button-mini uk-align-medium-right">
											ยกเลิก 
										</a>
										<a class="uk-button uk-button-success uk-button-mini uk-align-medium-right">
											ตกลง
										</a>
										<a href="#my-id" class="uk-button uk-button-primary uk-button-mini uk-contaier-center" data-uk-modal="{target:'#my-id'}"> 
											<i class="uk-icon-sign-in"></i>
										</a>
									</li>
								</h4>
							</li>
							<li class="uk-nestable-item">
								<h4 class="hd-text border-gray bg-gray padding5 uk-nestable-panel">
									<small class=" uk-text-primary">ชื่อแพทย์ : </small>
									<s:property value="" />
									<li>
										<small class="uk-text-primary">ชื่อคนไข้ : </small>
										<s:property value="" />
									</li>
									<li>
										<small class="uk-text-primary">HN : </small> 
										<s:property value="" />
									</li>
									<li>
										<small class="uk-text-primary">ห้อง:</small>
										<s:property value="" />
										<a class="uk-button uk-button-danger uk-button-mini uk-align-medium-right">
											ยกเลิก 
										</a>
										<a class="uk-button uk-button-success uk-button-mini uk-align-medium-right">
											ตกลง
										</a>
										<a href="#my-id" class="uk-button uk-button-primary uk-button-mini uk-contaier-center" data-uk-modal="{target:'#my-id'}"> 
											<i class="uk-icon-sign-in"></i>
										</a>
									</li>
								</h4>
							</li>
							<li class="uk-nestable-item">
								<h4 class="hd-text border-gray bg-gray padding5 uk-nestable-panel">
									<small class=" uk-text-primary">ชื่อแพทย์ : </small>
									<s:property value="" />
									<li>
										<small class="uk-text-primary">ชื่อคนไข้ : </small>
										<s:property value="" />
									</li>
									<li>
										<small class="uk-text-primary">HN : </small> 
										<s:property value="" />
									</li>
									<li>
										<small class="uk-text-primary">ห้อง:</small>
										<s:property value="" />
										<a class="uk-button uk-button-danger uk-button-mini uk-align-medium-right">
											ยกเลิก 
										</a>
										<a class="uk-button uk-button-success uk-button-mini uk-align-medium-right">
											ตกลง
										</a>
										<a href="#my-id" class="uk-button uk-button-primary uk-button-mini uk-contaier-center" data-uk-modal="{target:'#my-id'}"> 
											<i class="uk-icon-sign-in"></i>
										</a>
									</li>
								</h4>
							</li>
						</ul>
					</div>
				</div>
				<div class="uk-width-1-10 padding5 "></div>
				<div class="uk-width-2-10 padding5" id="room-queue-col">
					<div class="uk-grid padding5 border-gray uk-panel-box-primary">
						<div class="uk-width-1-1 padding5">
							<h3 class="uk-text-center bg-gray border-gray uk-text-primary uk-text-medium">
								ห้อง
							</h3>
						</div>
						<div class="uk-width-1-1 padding5">
							<a class="uk-button uk-button-success uk-button-mini uk-align-medium-right"
								title="จัดการแพทย์ประจำห้อง">
								<i class="uk-icon-user-md margin5"></i>
								<span>จัดการแพทย์ประจำห้อง</span>
							</a>
						</div>
						<div class="uk-width-1-1 padding5  ">
							<div class="uk-margin-top dashed-line">
								<div class="uk-panel uk-panel-box uk-width-1-1">
									<div class="uk-panel-badge uk-badge uk-badge-success">Available</div>
									<h3 class="uk-panel-title uk-margin-top">ทพ.สงกรานต์ มาฆะบูชา</h3> 
									<h2 class="uk-text-center uk-margin-remove-top">ห้อง 2304</h2>
								</div>
								<ul class="uk-nestable uk-width-1-1 padding5" 
									data-uk-nestable="{group: 'patient-group', maxDepth: 1}">
									<!-- <li class="uk-nestable-item">
										<h4 class="uk-nestable-panel hd-text border-gray bg-gray padding5">
											<small class=" uk-text-primary">ชื่อแพทย์ : </small>
											<s:property value="" />
											<li><small class="uk-text-primary">ชื่อคนไข้ : </small> <s:property
													value="" /></li>
											<li><small class="uk-text-primary">HN : </small> <s:property
													value="" /></li>
											<li><small class="uk-text-primary">ห้อง:</small> <s:property
													value="" /> <a href="#my-id"
												class="uk-button uk-button-primary uk-button-mini uk-contaier-center"
												data-uk-modal="{target:'#my-id'}"> <i
													class="uk-icon-sign-in"></i>
											</a></li>
										</h4>
									</li>
									<li class="uk-nestable-item">
										<h4 class="uk-nestable-panel hd-text border-gray bg-gray padding5">
											<small class=" uk-text-primary">ชื่อแพทย์ : </small>
											<s:property value="" />
											<li><small class="uk-text-primary">ชื่อคนไข้ : </small> <s:property
													value="" /></li>
											<li><small class="uk-text-primary">HN : </small> <s:property
													value="" /></li>
											<li><small class="uk-text-primary">ห้อง:</small> <s:property
													value="" /> <a href="#my-id"
												class="uk-button uk-button-primary uk-button-mini uk-contaier-center"
												data-uk-modal="{target:'#my-id'}"> <i
													class="uk-icon-sign-in"></i>
											</a></li>
										</h4>
									</li>
									<li class="uk-nestable-item">
										<h4 class="uk-nestable-panel hd-text border-gray bg-gray padding5">
											<small class=" uk-text-primary">ชื่อแพทย์ : </small>
											<s:property value="" />
											<li><small class="uk-text-primary">ชื่อคนไข้ : </small> <s:property
													value="" /></li>
											<li><small class="uk-text-primary">HN : </small> <s:property
													value="" /></li>
											<li><small class="uk-text-primary">ห้อง:</small> <s:property
													value="" /> <a href="#my-id"
												class="uk-button uk-button-primary uk-button-mini uk-contaier-center"
												data-uk-modal="{target:'#my-id'}"> <i
													class="uk-icon-sign-in"></i>
											</a></li>
										</h4>
									</li> -->
								</ul>
							</div>
							<div class="dashed-line uk-margin-top">
								<div class="uk-panel uk-panel-box uk-width-1-1">
									<div class="uk-panel-badge uk-badge uk-badge-success">Available</div>
									<h3 class="uk-panel-title uk-margin-top">ทพ.วันวิสา ยิ้มสวย</h3> 
									<h2 class="uk-text-center uk-margin-remove-top">ห้อง 2305</h2>
								</div>
								<ul class="uk-nestable" data-uk-nestable="{group: 'patient-group', maxDepth: 1}"></ul>
							</div>
						</div>
	
					</div>
				</div>
				<div class="uk-width-1-10 padding5 "></div>
				<div class="uk-width-2-10 padding5" id="wait-outcomes-col">
					<div class="uk-grid  padding5  border-gray uk-panel-box-primary">
						<div class="uk-width-1-1 padding5">
							<h3 class="uk-text-center bg-gray border-gray uk-text-primary uk-text-medium">
								รอผลการรักษา
							</h3>
						</div>
						<div class="uk-width-1-1 padding5">
							<ul class="uk-nestable uk-width-1-1 padding5" 
								data-uk-nestable="{group:'patient-group', maxDepth: 1}">
								<!-- <li class="uk-nestable-item">
									<h4 class="uk-nestable-panel hd-text border-gray bg-gray padding5">
										<small class=" uk-text-primary">ชื่อแพทย์ : </small>
										<s:property value="" />
										<li><small class="uk-text-primary">ชื่อคนไข้ : </small> <s:property
												value="" /></li>
										<li><small class="uk-text-primary">HN : </small> <s:property
												value="" /></li>
										<li><small class="uk-text-primary">บันทึกผลการรักษา:</small>
											<s:property value="" /> <a> <i class="uk-icon-save"></i>
										</a></li>
									</h4>
								</li>
								<li class="uk-nestable-item">
									<h4 class="uk-nestable-panel hd-text border-gray bg-gray padding5">
										<small class=" uk-text-primary">ชื่อแพทย์ : </small>
										<s:property value="" />
										<li><small class="uk-text-primary">ชื่อคนไข้ : </small> <s:property
												value="" /></li>
										<li><small class="uk-text-primary">HN : </small> <s:property
												value="" /></li>
										<li><small class="uk-text-primary">บันทึกผลการรักษา:</small>
											<s:property value="" /> <a> <i class="uk-icon-save"></i>
										</a></li>
									</h4>
								</li>
								<li class="uk-nestable-item">
									<h4 class="uk-nestable-panel hd-text border-gray bg-gray padding5">
										<small class=" uk-text-primary">ชื่อแพทย์ : </small>
										<s:property value="" />
										<li><small class="uk-text-primary">ชื่อคนไข้ : </small> <s:property
												value="" /></li>
										<li><small class="uk-text-primary">HN : </small> <s:property
												value="" /></li>
										<li><small class="uk-text-primary">บันทึกผลการรักษา:</small>
											<s:property value="" /> <a> <i class="uk-icon-save"></i>
										</a></li>
									</h4>
								</li> -->
							</ul>
						</div>
					</div>
	
				</div>
			</div>
		</div>
</div>
<!-- MODAL ZONE -->

<div id="my-id" class="uk-modal">
	<div class="uk-modal-dialog">
		<a class="uk-modal-close uk-close"></a>
		<div class="uk-width-1-1">
			<div class="uk-width-1-1uk-container-center">
				<table id="QueueTable"
					class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1 table-components-medicine">
					<thead>
						<tr class="hd-table">
							<th class="uk-text-center"></th>
							<th class="uk-text-center">ห้อง</th>
							<th class="uk-text-center"></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="uk-text-center uk-width-2-10"></td>
							<td class="uk-text-center uk-width-5-10"><select
								class="uk-form-small uk-width-1-1 dcode">
									<option value="">#เลือกห้อง</option>
									<option value="">#00001</option>
									<option value="">#00002</option>
									<option value="">#00003</option>
									<option value="">#00004</option>
									<option value="">#00005</option>
							</select></td>
							<td>
								<a class="uk-button uk-button-success uk-button-mini">
									ตกลง </a> <a
								class="uk-button uk-button-danger uk-button-mini">ยกเลิก</a>
							</td>
						</tr>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- MODAL ZONE -->
<script>
	$(document).ready(function() {
		$('.uk-nestable-item').mouseup(function(event) {
			/* Act on the event */
			// alert('halo');
			console.log('halo');
		});
	});
</script>
</body>
</html>