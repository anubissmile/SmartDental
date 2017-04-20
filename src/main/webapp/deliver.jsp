<%@ page language="java" import="java.util.*,java.text.DecimalFormat"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:ส่งตัว</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head>
<body>
	<div class="uk-grid uk-grid-collapse">
		<div class="uk-width-1-10">
			<%@include file="nav-right.jsp"%>
		</div>

		<div class="uk-width-9-10">
			<%@include file="nav-top.jsp"%>
			<div class="uk-grid"></div>
			<div class="uk-grid">


				<body class="uk-height-1-1">
					<div class="uk-vertical-align uk-text-center uk-height-1-1">
						<div class="uk-vertical-align-middle" style="width: 750px;">

							<form class="uk-panel uk-panel-box uk-form">
								<div class="uk-form-row">
									<div class="uk-form-row">
										<div class="uk-grid uk-grid-small">
											<div class="uk-width-1-3">
												<div class="uk-h4 uk-text-left">HN</div>
												<input class="uk-width-1-1 uk-form-small" type="text"
													placeholder="HN">
											</div>
											<div class="uk-width-1-3">
												<div class="uk-h4 uk-text-left">วันที่ส่งตัว</div>
												<input class="uk-width-1-1 uk-form-small" type="text"
													placeholder="วันที่ส่งตัว">
											</div>
											<div class="uk-width-1-3">
												<div class="uk-h4 uk-text-left">เวลาส่งตัว</div>
												<input class="uk-width-1-1 uk-form-small" type="text"
													placeholder="เวลาส่งตัว">
											</div>
										</div>
									</div>

									<div class="uk-form-row">
										<div class="uk-h4 uk-text-left">ชื่อ-สกุล(ไทย)</div>
										<input class="uk-width-1-1 uk-form-small" type="text"
											placeholder="ชื่อ-สกุล(ไทย)">
									</div>

									<div class="uk-form-row">
										<div class="uk-h4 uk-text-left">ชื่อ-สกุล(ENG)</div>
										<input class="uk-width-1-1 uk-form-small" type="text"
											placeholder="ชื่อ-สกุล(ENG)">
									</div>

									<div class="uk-form-row">
										<div class="uk-grid uk-grid-small">
											<div class="uk-width-1-3">
												<div class="uk-h4 uk-text-left">อายุ</div>
												<input class="uk-width-1-1 uk-form-small" type="text"
													placeholder="อายุ">
											</div>
											<div class="uk-width-1-3">
												<div class="uk-h4 uk-text-left">เพศ</div>
												<input class="uk-width-1-1 uk-form-small" type="text"
													placeholder="เพศ">
											</div>
											<div class="uk-width-1-3">
												<div class="uk-h4 uk-text-left">เบอร์โทร</div>
												<input class="uk-width-1-1 uk-form-small" type="text"
													placeholder="เบอร์โทร">
											</div>
										</div>
									</div>



									<div class="uk-form-row">
										<div class="uk-h4 uk-text-left">ส่งตัวไปที่</div>
										<input class="uk-width-1-1 uk-form-small" type="text"
											placeholder="ส่งตัวไปที่">
									</div>

									<div class="uk-form-row">
										<div class="uk-h4 uk-text-left">สาเหตุการส่งตัว</div>
										<input class="uk-width-1-1 uk-form-small" type="text"
											placeholder="สาเหตุการส่งตัว">
									</div>


									<div class="uk-form-row">
										<div class="uk-grid uk-grid-small">
											<div class="uk-width-1-1">
												<div class="uk-h4 uk-text-left">การรักษา</div>
												<textarea name="" id="" cols="30" rows="5"
													class="uk-width-1-1 uk-form-small"></textarea>
											</div>
										</div>
									</div>




									<div class="uk-form-row">
										<div class="uk-h4 uk-text-left">แพทย์ผู้ส่งตัว</div>
										<div class="uk-grid uk-grid-large">
											<div class="uk-width-4-5">
												<input class="uk-width-1-1 uk-form-small" type="text"
													placeholder="แพทย์ผู้สั่ง">
											</div>
											<div class="uk-width-1-5">
												<a href="#my-id" class="uk-button uk-button-primary-small"
													data-uk-modal>Search</a>
											</div>



											<div id="my-id" class="uk-modal">
												<div class="uk-modal-dialog">
													<a class="uk-modal-close uk-close"></a>
													<div class="uk-modal-header">เลือกแพทย์</div>
													<table class="uk-table uk-table-hover uk-table-striped">
														<thead id="tblHead">
															<tr>
																<th>รหัสแพทย์</th>
																<th>รายชื่อแพทย์</th>
															</tr>
														</thead>

														<tbody>
															<tr>
																<td class="uk-text-left">74500</td>
																<td class="uk-text-left">แพทย์บอม</td>
															</tr>
															<tr>
																<td class="uk-text-left">74500</td>
																<td class="uk-text-left">แพทย์มาย</td>
															</tr>
															<tr>
																<td class="uk-text-left">74500</td>
																<td class="uk-text-left">แพทย์เจม</td>
															</tr>
														</tbody>

													</table>
													<div class="uk-modal-footer"></div>
													<div class="uk-width-1-5"></div>
													<a
														class="uk-width-1-5 uk-button uk-button-primary uk-button-small"
														href="#">ตกลง</a>
													<div class="uk-width-1-5"></div>
													<a
														class="uk-width-1-5 uk-button uk-button-default uk-button-small uk-button-danger"
														href="#">ยกเลิก</a>

												</div>
											</div>

										</div>
									</div>

									<div class="uk-form-row uk-text-right">
										<div class="uk-width-1-1">
											<div class="uk-grid uk-margin-small-left">
												<div class="uk-width-1-5"></div>
												<a
													class="uk-width-1-5 uk-button uk-button-primary uk-button-large"
													href="#">พิมพ์ใบส่งตัว</a>
												<div class="uk-width-1-5"></div>
												<a
													class="uk-width-1-5 uk-button uk-button-default uk-button-large uk-button-danger"
													href="#">ยกเลิก</a>
											</div>
										</div>
									</div>
				</body>
</body>
</html>