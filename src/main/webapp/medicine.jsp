<%@ page language="java" import="java.util.*,java.text.DecimalFormat"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*"%>
<%@ page import="com.smict.treatment.data.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:ยา</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head>
<body>
	<div class="uk-grid uk-grid-collapse">
		<div class="uk-width-1-10">
			<%@include file="nav-right.jsp"%>
		</div>
		<div class="uk-width-9-10">
			<%@include file="nav-top.jsp"%>
			<div class="uk-grid uk-grid-collapse"></div>


			<div class="uk-width-6-10 border-gray padding5">

				<ul class="uk-breadcrumb">
					<li><a href="">ลูกค้า</a></li>
					<li><a href="">ไอดีการรักษา</a></li>
					<li class="uk-active"><span>ยา</span></li>
				</ul>


				<form action="treatmentDrug" method="post">
					<h4 class="hd-text uk-text-primary">
						รายการยา<small> <a href="#my-id"
							class="uk-button uk-button-primary uk-button-small" data-uk-modal>เพิ่มยา</a></small>
					</h4>


					<div id="my-id" class="uk-modal">
						<div class="uk-modal-dialog">
							<a class="uk-modal-close uk-close"></a>
							<div class="uk-modal-header">
								<i class="uk-icon-medkit"></i> ยา
							</div>

							
							<table class="uk-table uk-table-hover uk-table-striped">
								<thead>
									<tr class="hd-table">
										<th class="uk-text-center">รหัสยา</th>
										<th class="uk-text-center">ชื่อยา</th>
										<th class="uk-text-center">จำนวนยาฟรี</th>
										<th class="uk-text-center"></th>
										<th class="uk-text-center">เลือก</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="uk-text-center"></td>
										<td class="uk-text-left"></td>
										<td class="uk-text-center"></td>
										<td class='uk-text-center'><input type='text'
											name='qty_drug' placeholder='จำนวนเม็ด'
											class='uk-form-small uk-text-center uk-width-1-2'></td>
										<td class='uk-text-center'>
											<button class='uk-modal-close uk-button uk-button-success'
												value='"+i+"' onclick=\"add_drug(this,'"+obj[i].product_id+"','"+obj[i].product_name+"','"+obj[i].product_free+"','"+obj[i].product_transfer+"')\" >เลือก</button>
										</td>
									</tr>

								</tbody>

							</table>
							<div class="uk-modal-footer"></div>
						</div>
					</div>



					<div class="uk-width-1-1">
						<div class="uk-width-1-1uk-container-center">
							<table id="drugTable"
								class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-width-1-1 table-components-medicine">
								<thead>
									<tr class="hd-table">
										<th class="uk-text-center">ยา</th>
										<th class="uk-text-center">ฟรี</th>
										<th class="uk-text-center">จำนวน</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="uk-text-center"></td>
										<td class="uk-text-center"></td>
										<td class="uk-text-center uk-width-1-10"><input
											type="text" name="drug_qty" placeholder="0"
											class="uk-form-small uk-width-1-1 uk-text-right" /></td>
										
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<button
						class="uk-button uk-button-success uk-width-1-3 uk-button-small"
						name="saveproduct" type="submit">บันทึก รายการยา</button>
				</form>
				
				</div>
			</div>
		</div>
</body>
</html>