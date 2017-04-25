<%@page import="com.smict.product.model.ProductModel"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="com.smict.person.data.ContactData" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%@ page import="com.smict.product.data.ProductData" %>
<%@ page import="com.smict.person.data.FamilyData" %>
<%@page import="com.smict.person.data.CongenitalData"%>
<%@page import="com.smict.person.model.CongenitalDiseaseModel"%>
<%@page import="com.smict.person.data.PatientRecommendedData"%>
<%@page import="com.smict.person.model.RecommendedModel"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:ค้นหารายชื่อครอบครัว</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head>
	<body>
	
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<!-- START-FORM -->
				<div class="uk-grid uk-grid-collapse uk-margin-large-top">
					<div class="uk-width-1-10 uk-text-center"></div>
					<div class="uk-width-8-10">
						<s:if test="%{#request.alertMSG != null}">
						<div class="uk-alert uk-alert-warning" data-uk-alert>
							<li class="uk-alert-close uk-close"></li>
							<p><s:property value="#request.alertMSG" /></p>
						</div>
						</s:if>
						<!-- Family table list -->
						<strong><h2>ครอบครัว คนรู้จัก</h2></strong>
						<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed uk-text-center">
							<thead>
								<tr class="uk-text-center">
									<th class="uk-text-center">#</th>
									<th class="uk-text-center">ชื่อ</th>
									<th class="uk-text-center">หมายเลขโทรศัพท์</th>
									<th class="uk-text-center">ความสัมพันธ์</th>
									<th class="uk-text-center">ประเภท</th>
									<th class="uk-text-center">รายละเอียด</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="familyList" var="fam">
								<tr>
									<td><s:property value="#fam.count" /></td>
									<td>
										<s:property value="#fam.firstname_th" /> 
										<s:property value="#fam.lastname_th" />	
									</td>
									<td><s:property value="#fam.tel_number" /></td>
									<td><s:property value="#fam.relativeDescription" /></td>
									<td><s:property value="#fam.user_type_name" /></td>
									<td>
										<s:a href="family-%{#fam.famIdentication}-view-%{#fam.user_type_id}" 
											class="uk-button view-detail"
											data-uk-modal="{target: '#relative-details'}">
											<li class="uk-icon-list-alt"></li>
										</s:a>

										<a href="#modalFamUser" 
											id="removeFamUser" 
											class="uk-button uk-button-danger" 
						            		data-fam_id='<s:property value="#fam.family_id" />' 
						            		data-uk-modal>ลบ</a>
										</a>

									</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>
						<!-- Family table list -->
						<br>
						<strong>
							<h2>ค้นหา</h2>
						</strong>
						<form class="uk-form uk-grid" action="find-family" method="post">
							<div class="uk-width-5-6">
								<input type="text" class="uk-form-large uk-form-success uk-width-1-1"
									placeholder="ชื่อ,นามสกุล,รหัสประจำตัวประชาชน"
									name="search" autofocus="autofocus">
							</div>
							<div class="uk-width-1-6 uk-text-right">
								<button class="uk-button uk-button-primary uk-button-large">
									<li class="uk-icon-plus-circle"></li>
									ค้นหา
								</button>
							</div>
						</form>
					</div>
					<div class="uk-width-1-10 uk-text-center"></div>
				</div>
				<!-- END-FORM -->
				
			</div>
		</div>
		<!-- Modal Zone -->
		<div id="modalFamUser" class="uk-modal">
			<form action="deleteFamily" method="post"> 
		    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
		        <a class="uk-modal-close uk-close"></a>
	         	<div class="uk-modal-header"><i class="uk-icon-user"></i> ลบสมาชิกในครอบครัว</div>
	         	<div class="uk-width-1-1 uk-overflow-container">
					<h3 class="hd-text padding5 uk-text-primary"> ลบสมาชิกในครอบครัว</h3>
					<input type="hidden" id="famId" name="famModel.family_id" >
					<button type="submit" class="uk-button uk-button-success"><i class="uk-icon-check"></i> ยืนยันการลบ</button>
				</div>
		    </div>
		    </form>
		</div>

		<div class="uk-modal" id="relative-details">
		    <div class="uk-modal-dialog uk-modal-dialog-blank uk-height-viewport">
				<a class="uk-modal-close uk-close"></a>
		    	<div class="uk-grid" data-uk-grid-match>
		    		<div class="uk-width-1-1">
			    		<br><br><br><br>
		    		</div>
		    		<div class="uk-width-1-6"></div>
		    		<div class="uk-width-1-6 uk-text-right bdr1 pr5">
		    			<img src="http://tmssl.akamaized.net//images/portrait/originals/73491-1406794781.jpg" 
		    				width="200">
		    		</div>
		    		<div class="uk-width-3-6 uk-text-left">
		    			<h2>รายละเอียด</h2>
		    			<h3>นาย โชคชัย ค้นทองคำ</h3>
						<dl>
							<dt><strong>อายุ</strong></dt>
							<dd><small>27 ปี</small></dd>
							<dt><strong>hn กลาง</strong></dt>
							<dd><small>0923748372</small></dd>
							<dt><strong>รหัสประชาชน</strong></dt>
							<dd><small>110234783947</small></dd>
							<dt><strong>อาชีพ</strong></dt>
							<dd><small>developer</small></dd>
						</dl>
						<h2>ข้อมูลติดต่อ</h2>
						<dl>
							<dt><strong>อีเมล์</strong></dt>
							<dd><small>wesarut.khm@gmail.com</small></dd>
							<dt><strong>โทรศัพท์</strong></dt>
							<dd>
								<small>0923748372 มือถือ,</small>
								<small>0347348738 บ้าน,</small>
								<small>028383644 ที่ทำงาน,</small>
							</dd>
							<dt><strong>ที่อยู่</strong></dt>
							<dd>
								<small>
									93/3 หมู่ 4 ต.คลองมะเดื่อ อ.กระทุ่มแบน จ.สมุทรสาคร 74110
								</small>
							</dd>
						</dl>
		    		</div>
		    		<div class="uk-width-1-6"></div>
		    		<div class="uk-width-1-1">
			    		<br><br><br><br>
		    		</div>
		    	</div>
		    </div>
		</div>
		<!-- Modal Zone -->

		<script>
			$(document).on('click', '#removeFamUser', fn_buttonmodal_habndler).ready(function(){
				
			}).on('click', '.view-detail', function(event) {
				event.preventDefault();
				/* Act on the event */
				var url = $(this).attr('href');
				$.ajax({
					url: url,
					type: 'GET',
					dataType: 'json',
					data: {val: Math.random()},
				})
				.done(function(data, xhr, status) {
					console.log(data);
					console.log(xhr);
					console.log(status);
					console.log("success");
				})
				.fail(function() {
					console.log("error");
				})
				.always(function() {
					console.log("complete");
				});
				
			});
			
			function fn_buttonmodal_habndler(e)
			{
				
			    console.log('In Function');
			    var fam_id = $(e.target).data('fam_id');
			    $('#modalFamUser').on({
			        'uk.modal.show':function(){
			        	$("#famId").val(fam_id);
			        },
			        'uk.modal.hide':function(){
			                    //hide modal
			        }
			    }).trigger('uk.modal.show');
			}
		</script>
			
	</body>
</html>