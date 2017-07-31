<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:แผนการรักษา</title>
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
					<%@include file="shortpatient-leftside.jsp" %>
					<div class="uk-width-6-10">
							
							<form action="submitTreatmentPlanDetail" id="suball"  method="post">
							<div class="uk-grid uk-grid-collapse">
							
							<div class="uk-width-1-1 uk-form">
								<div class="uk-panel uk-panel-box padding5 ">
								
								<s:if test="alertStatus != null ">
									<div class='uk-alert uk-alert-<s:property value="alertStatus"/>' data-uk-alert>
									    <a href="" class="uk-alert-close uk-close"></a>
									    <p><s:property value="alertMessage"/> </p>
									</div>
								</s:if>
								
								<h4 class="hd-text uk-text-primary" >ข้อมูลแผนการรักษา </h4> 
								<div class="uk-grid">
									<div class="uk-width-1-2">
									<div class="uk-grid">
										<s:hidden class="uk-form-small" name="treatPlanModel.treatment_planid"/>
										<div class="uk-width-5-10">ชื่อแผนการรักษา</div>
										<div class="uk-width-5-10"><s:textfield class="uk-form-small" name="treatPlanModel.treatmentPlanname"/></div>
									</div>
									</div>
									<div class="uk-width-1-2">
										<div class="uk-grid">
											<div class="uk-width-3-10">สถานะ</div>
											<div class="uk-width-7-10">
												<s:if test="treatPlanModel.headerStatusName == 'ใช้งาน' ">
													<span class="uk-text-success"><s:property value="treatPlanModel.headerStatusName"/></span> 
												</s:if>
												<s:elseif test="treatPlanModel.headerStatusName == 'ไม่ใช้งาน' ">
													<span class="uk-text-danger"><s:property value="treatPlanModel.headerStatusName"/></span> 
												</s:elseif>
											</div>
										</div>
									</div>
								</div>
								<button name="btnUpdate" type="submit" class="uk-button uk-button-small uk-button-success  submitform"><i class="uk-icon-plus"></i> บันทึกการเปลี่ยนแปลงชื่อแผนการรักษา</button>
								<button name="btnDelete" type="submit" class="uk-button uk-button-small uk-button-danger submitform"><i class="uk-icon-minus"></i> ลบแผนการรักษา</button>
						        <button name="btnChangeStatus" type="submit" class="uk-button uk-button-small uk-button-primary  submitform"><i class="uk-icon-check"></i> ใช้งานแผนการรักษา </button>
								<hr/>
									<div class="margin5 ">
										<h4 class="hd-text uk-text-primary">เพิ่มรายการรักษา</h4>
										<input type="text" class="uk-form-small" readonly="readonly" id="treatment_code"  placeholder="ชื่อการรักษา" >
										<input type="hidden" class="uk-form-small"  name="treatModel.treatment_ID"  >
										<a href="#treatment" class="uk-button uk-button-primary uk-button-small" data-uk-modal>
											<i class="uk-icon-search"></i>
										</a>
								<div class="uk-width-1-1  ">
									<h3 >ประเภท</h3>
									<div class="uk-button-dropdown" data-uk-dropdown="{mode:'click'}">
										 <button class="uk-button" type="button" >เลือกประเภท
										 	<i class="uk-icon-caret-down"></i>
										 </button>
										 <div class="uk-dropdown uk-dropdown-small">
											<ul class="uk-nav uk-nav-dropdown uk-dropdown-close" data-uk-switcher="{connect:'#my-id-one', animation: 'fade'}">
											    <li id="hd_tooth" class="select-type " value="1"><a href="">Tooth</a></li>
											    <li id="hd_surf" class="select-type " value="2"><a href="">Surface</a></li>
											    <li id="hd_Mouth" class="select-type " value="3"><a href="">Mouth</a></li>
											    <li id="hd_quadrant" class="select-type " value="4"><a href="" >Quadrant</a></li>
											    <li id="hd_Sextant" class="select-type " value="5"><a href="">Sextant</a></li>
											    <li id="hd_arch" class="select-type " value="6"><a href="">Arch</a></li>
											    <li id="hd_toothRange" class="select-type " value="7"><a href="">Tooth Range</a></li>
											</ul>
										</div>										
									</div>
								</div>
									<div class="uk-width-1-2 uk-panel uk-panel-box padding5 ">
									<ul id="my-id-one"  class="uk-switcher type-proced " style="min-height: 25vh;"> 
										<li class="show-type-all" id="show_Tooth"><!-- Tooth  -->
											<div class="uk-grid">
												<h3 class="uk-text-center uk-width-1-1 uk-text-primary">Tooth</h3>
												<div class="uk-width-1-2">
													<h5 class="hd-text uk-text-primary margin5">ซี่ฟัน</h5>   
													<input type="text" autocomplete="off" class="show-type" id="tooth_tooth" 
													name="treatModel.tooth" pattern="[0-9].{0,}" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น"  class="uk-form-small uk-width-1-1"  >
												</div>
											
											</div>
											
										</li>
										<li class="show-type-all" id="show_Surface"><!-- Surface  -->
											<div class="uk-grid">
											<h3 class="uk-text-center uk-width-1-1 uk-text-primary">Surface</h3>
												<div class="uk-width-1-2">
													<h5 class="hd-text uk-text-primary margin5">ซี่ฟัน</h5> 
													<input type="text" autocomplete="off" class="show-type" id="surf_tooth" name="treatModel.surface_tooth" pattern="[0-9].{0,}" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" class="uk-form-small uk-width-1-1" >
												</div>
												<div class="uk-width-1-2">
													<h5 class="hd-text uk-text-primary margin5">ด้านฟัน</h5>
													<input type="text" autocomplete="off" class="show-type" id="surf" readonly="readonly" name="treatModel.surface" pattern="[A-Z].{0,}" title="กรอกข้อมูล เป็นอักษณตัวใหญ่เท่านั้น" class="uk-form-small uk-width-1-1" >
												</div>
											</div>
											<table class="surface-table uk-width-1-1">
												<tr>
													<td></td>
													<td><button class="uk-button uk-button-small " id="B," onclick="btnFunction(this)" type="button" value="1">B</button></td>
													<td><button class="uk-button uk-button-small " id="F," onclick="btnFunction(this)" type="button" value="1">F</button></td>
													<td></td>
												</tr>
												<tr>
													<td><button class="uk-button uk-button-small "id="M," onclick="btnFunction(this)" type="button" value="1">M</button></td>
													<td><button class="uk-button uk-button-small "id="O," onclick="btnFunction(this)" type="button" value="1">O</button></td>
													<td><button class="uk-button uk-button-small "id="I," onclick="btnFunction(this)" type="button" value="1">I</button></td>
													<td><button class="uk-button uk-button-small "id="D," onclick="btnFunction(this)" type="button" value="1">D</button></td>
												</tr>
												<tr>
													<td></td>
													<td colspan="2"><button class="uk-button uk-button-small " id="L" onclick="btnFunction(this)" type="button" value="1">L</button></td>
													<td></td>
												</tr>
											</table>
										</li>
										<li class="show-type-all" id="show_Mouth"><!-- Mouth  -->
										<h3 class="uk-text-center uk-width-1-1 uk-text-primary">Mouth</h3>
											<h5 class="hd-text uk-text-primary margin5">เลือกทั้งปาก</h5>
										</li>
										<li class="show-type-all" id="show_Quadrant"><!-- Quadrant  -->
											<div class="uk-grid"> 
											<h3 class="uk-text-center uk-width-1-1 uk-text-primary">Quadrant</h3>
												<label class="uk-width-1-2 margin5">
													<input type="radio" class="check-type-radio req-Qua" name="treatModel.quadrant" value="UL"/> UL(1)</label>
												<label class="uk-width-1-2 margin5">
													<input type="radio" class="check-type-radio req-Qua" name="treatModel.quadrant" value="UR" /> UR(2)</label>
												<label class="uk-width-1-2 margin5">
													<input type="radio" class="check-type-radio req-Qua" name="treatModel.quadrant" value="LL" /> LL(4)</label>
												<label class="uk-width-1-2 margin5">
													<input type="radio" class="check-type-radio req-Qua" name="treatModel.quadrant" value="LR" /> LR(3)</label>
											</div>
										</li>
										<li class="show-type-all" id="show_Sextant"><!-- Sextant -->
										<h3 class="uk-text-center uk-width-1-1 uk-text-primary">Sextant</h3>
											<h5 class="hd-text uk-text-primary margin5">Sextant</h5>
										</li>
										<li class="show-type-all" id="show_Arch"><!-- Arch -->
											<div class="uk-grid">
											<h3 class="uk-text-center uk-width-1-1 uk-text-primary">Arch</h3>
												<label class="uk-width-1-1 margin5"><input type="radio" class="check-type-radio req-Arch" name="treatModel.arch" value="U"/> U</label>
												<label  class="uk-width-1-1 margin5"><input type="radio" class="check-type-radio req-Arch" name="treatModel.arch" value="L"/> L</label>
											</div>
											
										</li>
										<li  class="show-type-all" style="overflow-x: scroll;" id="show_ToothRange"><!-- Tooth Range -->
										<h3 class="uk-text-center uk-width-1-1 uk-text-primary">Tooth Range</h3>
											<table  class="tooth-table border-gray uk-width-1-1">
												<tr class="tooth-pic-upper">
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper0' type="button" value="0">18</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper1' type="button" value="1">17</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper2' type="button" value="2">16</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper3' type="button" value="3">15</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper4' type="button" value="4">14</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper5' type="button" value="5">13</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper6' type="button" value="6">12</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper7' type="button" value="7">11</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper8' type="button" value="8">21</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper9' type="button" value="9">22</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper10' type="button" value="10">23</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper11' type="button" value="11">24</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper12' type="button" value="12">25</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper13' type="button" value="13">26</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper14' type="button" value="14">27</button></td>
													<td><button class="uk-button uk-button-small select-upper" onclick="tooth_range_button(this)" id='check-upper15' type="button" value="15">28</button></td>
												</tr>
											</table>
											<input type="hidden" class="tooth-upper checkall" value="" />
											<input type="hidden" class="tooth-lower checkall" value="" />
											<input type="hidden" class="tooth-keep checkall"  value="" />
											<table class="tooth-table border-gray uk-width-1-1">
												<tr  class="tooth-pic-lower" >
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower0' type="button" value="0">38</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower1' type="button" value="1">37</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower2' type="button" value="2">36</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower3' type="button" value="3">35</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower4' type="button" value="4">34</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower5' type="button" value="5">33</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower6' type="button" value="6">32</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower7' type="button" value="7">31</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower8' type="button" value="8">41</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower9' type="button" value="9">42</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower10' type="button" value="10">43</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower11' type="button" value="11">44</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower12' type="button" value="12">45</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower13' type="button" value="13">46</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower14' type="button" value="14">47</button></td>
													<td><button class="uk-button uk-button-small select-lower"  onclick="tooth_range_button(this)" id='check-lower15' type="button" value="15">48</button></td>
												</tr>
											</table>
											<input type="hidden" class="tooth-rangekeep checkall" name="treatModel.toothRange" value="" />
										</li>
									</ul>
									</div>
									</div>
									<button  type="button" id="submittreatment" class="uk-button uk-button-primary uk-icon-plus" > เพิ่มรายการรักษา</button>
									<input type="hidden" value='' name="treatModel.tooth_types" id="tooth_typeName" />
									<hr/>
									<h4 class="hd-text uk-text-primary">รายการรักษา</h4>
									<hr class="margin5">
									<div class="treatment-bill">
									<table class="uk-table uk-table-condensed ">
										<thead>
											<tr class="hd-table">
												<th>ลำดับ</th>
												<th>รหัสการรักษา</th>
												<th>ชื่อรายการรักษาไทย</th>
												<th>ชื่อรายการรักษาอังกฤษ</th>
												<th>ราคา</th>
												<th>สถานะ</th>
												<th>ลบ</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="listTreatPlanDetail == null">
												<tr>
													<td colspan="7">ไม่พบรายการรักษา</td>
												</tr>
											</s:if>
											<s:else>
												<s:iterator value="listTreatPlanDetail" status="planDetailStatus">
													<tr>
														<td> <s:property value="#planDetailStatus.count"/> </td>
														<td> <s:property value="treatment_code"/> </td>
														<td> <s:property value="treatment_nameth"/> </td>
														<td> <s:property value="treatment_nameen"/> </td>
														<td> <s:property value="price"/> </td>
														<td> <s:property value="detailStatusName"/> </td>
														<s:url escapeAmp="false" action="deleteDetailTreatmentPlan" var="link">
												        	<s:param name="treatPlanModel.treatment_planid">
												        		<s:property value="treatment_planid"/>
												        	</s:param>
												        	<s:param name="treatPlanModel.treatment_code">
												        		<s:property value="treatment_code"/>
												        	</s:param>
												        	<s:param name="treatPlanModel.treatmentPlanname">
												        		<s:property value="treatmentPlanname"/>
												        	</s:param>
												        </s:url>
														<td><a href='<s:property value="link"/>' class="uk-button uk-button-danger uk-button-small" > <i class="uk-icon-trash"></i> </a>  </td>
													</tr>
												</s:iterator>
												
											</s:else>
											
										</tbody>
										
									</table>
									</div>
								</div>
							</div>
						</div>
						</form>
						
					</div>
				</div>
				<div id="treatment" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form uk-modal-dialog-large" >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><h2><i class="uk-icon-medkit"></i> เพิ่มการรักษา</h2></div>
					         <div class="uk-grid">
						         	<div class="uk-width-4-4">
						         		<h3>รายการรักษา</h3>
										<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
										    <thead>
										        <tr class="hd-table"> 
										            <th class="uk-text-center">เลือก</th> 
										            <th class="uk-text-center">ประเภทการรักษา</th>
										            <th class="uk-text-center">ชื่อการรักษา</th>  
										            <th class="uk-text-center">ราคา</th>
										        </tr>
										    </thead> 
										    <tbody>
										    	<s:if test="listTreatmentModel == null">
										    		<tr>
										    			<td class="uk-text-center" colspan="5"> ไม่พบข้อมูลการรักษา </td>
										    		</tr>
										    	</s:if>
										    	<s:else>
										    		<s:iterator value="listTreatmentModel">
										    			<tr>
												    		<td class="uk-text-center">
						                                     <input type="radio" class="call-all "   name="selectTreatmentCode" value="<s:property value="treatment_id"/>" />
			                                        		</td>
			                                        		<s:if test="treatment_iscon == 1">
			                                        		<td class="uk-text-center">การรักษาธรรมดา</td>
			                                        		</s:if>
			                                        		<s:else>
			                                        		<td class="uk-text-center">การรักษาต่อเนื่อง</td>
			                                        		</s:else>
													        <td class="uk-text-left treatName"><s:property value="treatment_nameth"/></td> 
													        <td class="uk-text-right"><s:property value="price"/></td>
														</tr>
										    		</s:iterator>
										    	</s:else>
											</tbody>
										</table>
										</div>
					         	 	</div>
					         <div class="uk-modal-footer uk-text-right"> 
					         	<button class="uk-button uk-button-success uk-modal-close">ตกลง</button>
			         			<button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button> 
					         </div>
					    </div>
					</div>
				</div>
				</div>			
		<!-- js ในการทำรูปภาพ <script type="text/javascript" src="js/html2canvas.js"></script> --> 
		<script>
			$(document).ready(function(){
				<% if(request.getAttribute("toothHistory")!=null){ 
					
					List<ToothModel> toothHistory = (List) request.getAttribute("toothHistory"); 
					for(ToothModel tm :toothHistory){%>
					$('#tooth_<%=tm.getTooth_num()%>').prepend('<img class="case" onerror=this.style.display="none" src="img/tooth/<%=tm.getTooth_num()%>/<%=tm.getTooth_pic_code()%>/<%=tm.getSurface()%>.png" />');
					<%}
				}%>
				
				$(document).on("keyup","#tooth_tooth",function(e){									
					$("#tooth_tooth").val(checktooth($(this).val()));
				});
				$('#tooth_tooth').focusout(function(){
					checktoothnumber($(this).val());
				});
				$('#submittreatment').click(function () {
					var typeall = $('#tooth_typeName').val();
					if(typeall != ''){
					if(typeall == 1){
						var chk = checktoothnumber($('#tooth_tooth').val());
						if(chk == 0){
							$('#suball').submit();
						}else{
							
						}
					}else{
						$('#suball').submit();
					}
				}else{
					 swal(
							 'ไม่มีรายการการรักษา',
							 'กรุณาเลือกการรักษา',  
							  'error'
							);
				}
				});
				$('.select-type').on('click', function() {
					var checktype = $(this).text();
					var type_values =	$(this).val();
					$('#tooth_typeName').val(type_values);
					$('.checkall').val('');
					$('.show-type').val('');
					$('.show-type').removeAttr('required');
					$('.surface-table').find("tr").find("td").find("button").removeClass(" uk-button-primary ");
					$('.check-type-radio').removeAttr("required");
					$('.check-type-radio').prop('checked', false);
					$('.select-upper').removeClass(' uk-button-primary');
					$('.select-lower').removeClass(' uk-button-primary');
			 		$('.checkall').val('');
/* 						if(checktype == 'Tooth'){
							$("#tooth_tooth").attr('required',true);
						}else if(checktype == 'Surface'){
							$("#surf_tooth").attr('required',true);
							$("#surf").attr('required',true);  	
						}else if(checktype == 'Mouth'){
							
						}else if(checktype == 'Quadrant'){
							$('.req-Qua').attr('required',true);
						}else if(checktype == 'Sextant'){
							
						}else if(checktype == 'Arch'){
							$('.req-Arch').attr('required',true);
						}else{
							
						} */
			});
			$('.req-Qua').on('click', function() {
				$('.req-Qua').removeAttr('required');
			});
			$('.req-Arch').on('click', function() {
				$('.req-Arch').removeAttr('required');
			});		
			/* treatment_patient select treatment and choose type */
			$(".call-all").click( function () {
				var treatID = $(this).val();
				var index = $(".call-all").index(this);
				var treatName = $(".treatName:eq("+index+")").text();
					$("input[name='treatModel.treatment_ID']").val(treatID);
					$("#treatment_code").val(treatName);
					$('.checkall').val('');
					$('.select-type').removeClass( "hidden uk-active" );
					$('.select-type').attr( "aria-expanded", false );
					$('.show-type-all').removeClass( " uk-active" );
					$('.show-type').val('');
					$('.show-type').removeAttr('required');
					$('.surface-table').find("tr").find("td").find("button").removeClass(" uk-button-primary ");
					$('.check-type-radio').removeAttr("required");
					$('.check-type-radio').prop('checked', false);
					$('.select-upper').removeClass(' uk-button-primary');
					$('.select-lower').removeClass(' uk-button-primary');
			 		$('.checkall').val('');
				$.ajax({
			        type: "post",
			        url: "ajax/ajax-treatment-check-type.jsp", //this is my servlet 
			        data: {treatID: treatID},
			        async:false, 
			        success: function(result){
			        	var obj = jQuery.parseJSON(result);
			        	var check = 0;
			        	for(var i = 0 ;  i < obj.length;i++){
		
				    			if(obj[i].treatmentcheck == 'nu' && obj[i].treatmentName == "Tooth" ){
				    				$('#hd_tooth').addClass( "hidden" );
				    			}else if(obj[i].treatmentcheck == 'nu' && obj[i].treatmentName == "Surface"){
				    				$('#hd_surf').addClass( "hidden" );
				    			}else if(obj[i].treatmentcheck == 'nu' && obj[i].treatmentName == "Mouth"){
				    				$("#hd_Mouth").addClass( "hidden" );
				    			}else if(obj[i].treatmentcheck == 'nu' && obj[i].treatmentName == "Quadrant"){
				    				$('#hd_quadrant').addClass( "hidden" );
				    			}else if(obj[i].treatmentcheck == 'nu' && obj[i].treatmentName == "Sextant"){
				    				$('#hd_Sextant').addClass( "hidden" );
				    			}else if(obj[i].treatmentcheck == 'nu' && obj[i].treatmentName == "Arch"){
				    				$('#hd_arch').addClass( "hidden" );
				    			}else if(obj[i].treatmentcheck == 'nu' && obj[i].treatmentName == "ToothRang"){
				    				$('#hd_toothRange').addClass( "hidden" );
				    			}							
								if(check == 0){
									if(obj[i].treatmentcheck != 'nu'){
										if(obj[i].treatmentName == "Tooth"){
											setFirstSelect($('#hd_tooth'),$('#show_Tooth'));
					    					/* $("#tooth_tooth").attr('required',true); */
					    					$('#tooth_typeName').val('1');
					    					check++;
					    				}else if(obj[i].treatmentName == "Surface"){
					    					setFirstSelect($('#hd_surf'),$('#show_Surface'));
					    					/* $("#surf_tooth").attr('required',true);
											$("#surf").attr('required',true);   */
											$('#tooth_typeName').val('2');
					    					check++;
					    				}else if(obj[i].treatmentName == "Mouth"){
					    					setFirstSelect($('#hd_Mouth'),$('#show_Mouth'));
					    					$('#tooth_typeName').val('3');
					    					check++;
					    				}else if(obj[i].treatmentName == "Quadrant"){
					    					setFirstSelect($('#hd_quadrant'),$('#show_Quadrant'));
					    					/* $('.req-Qua').attr('required',true); */
					    					$('#tooth_typeName').val('4');
					    					check++;
					    				}else if(obj[i].treatmentName == "Sextant"){
					    					setFirstSelect($('#hd_Sextant'),$('#show_Sextant'));
					    					$('#tooth_typeName').val('5');
					    					check++;
					    				}else if(obj[i].treatmentName == "Arch"){
					    					setFirstSelect($('#hd_arch'),$('#show_Arch'));
					    					$('#tooth_typeName').val('6');
					    					/* $('.req-Arch').attr('required',true); */
					    					check++;
					    				}else{
					    					setFirstSelect($('#hd_toothRange'),$('#show_ToothRange'));
					    					$('#tooth_typeName').val('7');
					    					check++;
					    				}
									}
								}
				    							    							    			
				    		}		        			        	
			        }
			     });
			});
				
	});
			function btnFunction(elem){
				
				 var suf = $("#surf").val();
				 var btn =  elem;
				 if(btn.value=='1'){
					 
					 suf += btn.id;
					 $("#surf").val(suf);
					 btn.value='2';
					 elem.className +=" uk-button-primary ";
					
				 }else if(btn.value=='2'){ 
					 var suf = suf.replace(btn.id, "");
					 $("#surf").val(suf);  
					 btn.value='1';
					 elem.className =" ";
					 elem.className +=" uk-button uk-button-small ";
				 }  
			}
			function tooth_range_button(elem){
				var keep = elem;
				var checkTRup = $('.tooth-upper').val();
				var checkTRlow = $('.tooth-lower').val();
				var checkTRange = $('.tooth-keep').val();
				var keppalltooth = $('.tooth-rangekeep').val();
				if(keep.className == "uk-button uk-button-small select-upper" && checkTRlow == ''){
					if(checkTRup < 1){
						elem.className +=' uk-button-primary';
			 			$('.tooth-keep').val(elem.value);	 			
			 			 checkTRup =  1;
			 			$('.tooth-upper').val(checkTRup);
					}else if(checkTRup < 2){
						if(checkTRange < elem.value){		 			
				 			for(var i = checkTRange;i<=elem.value ; i++){		 				
				 				$('.select-upper').eq(i).addClass(' uk-button-primary');
				 				keppalltooth += $('#check-upper'+i).text()+',';
				 			}
				 			checkTRup = parseInt(checkTRup) + 1;
				 			$('.tooth-rangekeep').val(keppalltooth);
				 			$('.tooth-upper').val(checkTRup);
				 		}else{

				 			for(var i = elem.value ;i <= checkTRange ; i++){		 				
				 				$('.select-upper').eq(i).addClass(' uk-button-primary');
				 				keppalltooth += $('#check-upper'+i).text()+',';
				 			}
				 			checkTRup = parseInt(checkTRup) + 1;
				 			$('.tooth-rangekeep').val(keppalltooth);
				 			$('.tooth-upper').val(checkTRup);
				 		}
					}else{
						$('.select-upper').removeClass(' uk-button-primary');
				 		$('.checkall').val('');
					}			
				}else if(keep.className == "uk-button uk-button-small select-upper uk-button-primary" && checkTRlow == ''){
					$('.select-upper').removeClass(' uk-button-primary');
			 		$('.checkall').val('');
				}else if(keep.className == "uk-button uk-button-small select-lower" && checkTRup == ''){
					if(checkTRlow < 1){
						elem.className +=' uk-button-primary';		 			
			 			$('.tooth-keep').val(elem.value);
			 			checkTRlow =  1;
			 			$('.tooth-lower').val(checkTRlow);
					}else if(checkTRlow < 2){
				 		if(checkTRange < elem.value){		 			
				 			for(var i = checkTRange;i<=elem.value ; i++){		 				
				 				$('.select-lower').eq(i).addClass(' uk-button-primary');
				 				keppalltooth += $('#check-lower'+i).text()+',';
				 			}
				 			checkTRlow = parseInt(checkTRlow) + 1;
				 			$('.tooth-lower').val(checkTRlow);
				 			$('.tooth-rangekeep').val(keppalltooth);
				 		}else{

				 			for(var i = elem.value ;i <= checkTRange ; i++){		 				
				 				$('.select-lower').eq(i).addClass(' uk-button-primary');
				 				keppalltooth += $('#check-lower'+i).text()+',';
				 			}
				 			checkTRlow = parseInt(checkTRlow) + 1;
				 			$('.tooth-lower').val(checkTRlow);
				 			$('.tooth-rangekeep').val(keppalltooth);
				 		}
					}else{
						$('.select-lower').removeClass(' uk-button-primary');
				 		$('.checkall').val('');
					}
				}else if(keep.className == "uk-button uk-button-small select-lower uk-button-primary" && checkTRup == ''){
					$('.select-lower').removeClass(' uk-button-primary');
			 		$('.checkall').val('');
				}else{
					$('.select-upper').removeClass(' uk-button-primary');
					$('.select-lower').removeClass(' uk-button-primary');
			 		$('.checkall').val('');
				}
			 		
			}
			function setFirstSelect(select,show_div){
				$(select).addClass( "uk-active" ).attr( "aria-expanded", true );
				$(show_div).addClass( "uk-active" ).attr( "aria-expanded", true );
				
			}
			function checktooth(num) {
/* 				var intRegex = new RegExp('[0-9 -()+]+$');
				var strcheck = $(this).val();
					strcheck = strcheck.replace(/,/g, "")
					var arr =	strcheck.split("");
					strcheck = '';
				for(var pk = 0 ; pk<arr.length;pk++){							 
					if(intRegex.test(arr[pk])){

						if(pk%2==0&&pk!=0){
							strcheck +=	",";		 
						} 
						strcheck +=	arr[pk];
					}
				} */
				var intRegex = new RegExp('[0-9 -()+]+$');
				var strcheck = num;
					strcheck = strcheck.replace(/,/g, "")
					var arr =	strcheck.split("");
					strcheck = '';
				for(var pk = 0 ; pk<arr.length;pk++){							 
					if(intRegex.test(arr[pk])){

						if(pk%2==0&&pk!=0){
							strcheck +=	",";		 
						} 
						strcheck +=	arr[pk];
					}
				}
				return strcheck;
			}
			function checktoothnumber(num) {
				var ch = num;
				var arr2 = ch.split(",");
				var sendback = 0;
				ch = '';
				jQuery.each( arr2, function( i, val ) {
					if(val != '' ){ 
					if(val.length == 2 ){
						if(val > '10' && val <'19' || val > '20' && val <'29' ||val > '30' && val <'39' || val > '40' && val <'49'){
							 if(i>'0'){
								 ch +=	",";
							} 
							 ch += val;
							 jQuery.each( arr2, function( g, val1 ) {
								 if(i != g){
								 if(val == val1){
									 swal(
											  'ข้อมูลฟันไม่ถูกต้อง',
											  'มีซี่ฟันที่ซ้ำกันอยู่ในช่อง '+ch+' เลขที่ซ้ำคือ '+val+'',
											  'error'
											);
									 sendback=1;
								 }
								 }
							 });
						 }else{
							 swal(
									  'ข้อมูลฟันไม่ถูกต้อง',
									  'กรุณาตรวจสอบเลขซี่ฟัน  ไม่มีซี่ฟันเลขที่ '+val+'',
									  'error'
									);sendback=1;
						 }
					 }else{
						 swal(
								  'ข้อมูลฟันไม่ครบ',
								  'กรุณาตรวจสอบเลขซี่ฟัน',
								  'error'
								);sendback=1;
					 }
					}
				});
				return sendback;
			}
		</script>
	</body>
</html>