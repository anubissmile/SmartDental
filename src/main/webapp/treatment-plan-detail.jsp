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
							
							<form action="submitTreatmentPlanDetail" method="post">
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
										<input type="text" class="uk-form-small" id="treatment_code" name="servicePatModel.treatment_code" placeholder="รหัสการรักษา" required>
										<a href="#treatment" class="uk-button uk-button-primary uk-button-small" data-uk-modal>
											<i class="uk-icon-search"></i>
										</a>
										<h4 class="hd-text uk-text-primary">ประเภท</h4>
										
										<div class="uk-button-dropdown" data-uk-dropdown="{mode:'click'}">
										 <button class="uk-button" >เลือกประเภท
										 	<i class="uk-icon-caret-down"></i>
										 </button>
										 <div class="uk-dropdown uk-dropdown-small">
											<ul class="uk-nav uk-nav-dropdown uk-dropdown-close" data-uk-switcher="{connect:'#my-id-one', animation: 'fade'}">
											    <li id="hd_tooth"><a href="">Tooth</a></li>
											    <li id="hd_surf"><a href="">Surface</a></li>
											    <li><a href="">Mouth</a></li>
											    <li id="hd_quadrant"><a href="">Quadrant</a></li>
											    <li><a href="">Sextant</a></li>
											    <li id="hd_arch"><a href="">Arch</a></li>
											    <li><a href="">Tooth Range</a></li>
											</ul>
										</div>
										</div>
									<ul id="my-id-one"  class="uk-switcher type-proced"> 
										<li></li>
										<li><!-- Tooth  -->
											<div class="uk-grid">
												<div class="uk-width-1-2">
													<h5 class="hd-text uk-text-primary margin5">ซี่ฟัน</h5>   
													<input type="text" id="tooth_tooth" name="servicePatModel.tooth_tooth" pattern="[0-9].{0,2}" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" required="required" class="uk-form-small uk-width-1-1"  >
												</div>
											
											</div>
											
										</li>
										<li><!-- Surface  -->
											<div class="uk-grid">
												<div class="uk-width-1-2">
													<h5 class="hd-text uk-text-primary margin5">ซี่ฟัน</h5> 
													<input type="text" id="surf_tooth" name="servicePatModel.surf_tooth" pattern="[0-9].{0,2}" title="กรอกข้อมูล เป็นตัวเลขเท่านั้น" class="uk-form-small uk-width-1-1" >
												</div>
												<div class="uk-width-1-2">
													<h5 class="hd-text uk-text-primary margin5">ด้านฟัน</h5>
													<input type="text" id="surf" name="servicePatModel.surf" pattern="[A-Z].{0,6}" title="กรอกข้อมูล เป็นอักษณตัวใหญ่เท่านั้น" class="uk-form-small uk-width-1-1" >
												</div>
											</div>
											<table class="surface-table">
												<tr>
													<td></td>
													<td><button class="uk-button uk-button-small " id="B" onclick="btnFunction(this)" type="button" value="1">B</button></td>
													<td><button class="uk-button uk-button-small " id="F" onclick="btnFunction(this)" type="button" value="1">F</button></td>
													<td></td>
												</tr>
												<tr>
													<td><button class="uk-button uk-button-small "id="M" onclick="btnFunction(this)" type="button" value="1">M</button></td>
													<td><button class="uk-button uk-button-small "id="O" onclick="btnFunction(this)" type="button" value="1">O</button></td>
													<td><button class="uk-button uk-button-small "id="I" onclick="btnFunction(this)" type="button" value="1">I</button></td>
													<td><button class="uk-button uk-button-small "id="D" onclick="btnFunction(this)" type="button" value="1">D</button></td>
												</tr>
												<tr>
													<td></td>
													<td colspan="2"><button class="uk-button uk-button-small " id="L" onclick="btnFunction(this)" type="button" value="1">L</button></td>
													<td></td>
												</tr>
											</table>
										</li>
										<li><!-- Mouth  -->
											<h5 class="hd-text uk-text-primary margin5">เลือกทั้งปาก</h5>
										</li>
										<li><!-- Quadrant  -->
											<div class="uk-grid"> 
												<label class="uk-width-1-2 margin5">
													<input type="radio" name="servicePatModel.quadrant" value="UL"/>UL(1)</label>
												<label class="uk-width-1-2 margin5">
													<input type="radio" name="servicePatModel.quadrant" value="UR" />UR(2)</label>
												<label class="uk-width-1-2 margin5">
													<input type="radio" name="servicePatModel.quadrant" value="LL" />LL(4)</label>
												<label class="uk-width-1-2 margin5">
													<input type="radio" name="servicePatModel.quadrant" value="LR" />LR(3)</label>
											</div>
										</li>
										<li><!-- Sextant -->
											<h5 class="hd-text uk-text-primary margin5">Sextant</h5>
										</li>
										<li><!-- Arch -->
											<div class="uk-grid">
												<label class="uk-width-1-1 margin5"><input type="radio" name="servicePatModel.arch" value="U"/>U</label>
												<label  class="uk-width-1-1 margin5"><input type="radio" name="servicePatModel.arch" value="L"/>L</label>
											</div>
											
										</li>
										<li  style="overflow-x: scroll;"><!-- Tooth Range -->
											<table  class="tooth-table border-gray uk-width-1-1">
												<tr class="tooth-pic">
													<td>18</td>
													<td>17</td>
													<td>16</td>
													<td>15</td>
													<td>14</td>
													<td>13</td>
													<td>12</td>
													<td>11</td>
													<td>21</td>
													<td>22</td>
													<td>23</td>
													<td>24</td>
													<td>25</td>
													<td>26</td>
													<td>27</td>
													<td>28</td>
												</tr>
											</table>
											<table class="tooth-table border-gray uk-width-1-1">
												<tr  class="tooth-pic">
													<td>48</td>
													<td>47</td>
													<td>46</td>
													<td>45</td>
													<td>44</td>
													<td>43</td>
													<td>42</td>
													<td>41</td>
													<td>41</td>
													<td>32</td>
													<td>33</td>
													<td>34</td>
													<td>35</td>
													<td>36</td>
													<td>37</td>
													<td>38</td>
												</tr>
											</table>
										</li>
									</ul>
									</div>
									<button name="btnAdd" type="submit" class="uk-button uk-button-primary uk-icon-plus" > เพิ่มรายการรักษา</button>
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
													        	<div class="uk-form-controls">
						                                            <s:radio theme="simple" name="selectTreatmentCode" list="{treatment_code}" />
			                                        			</div>
			                                        		</td>
			                                        		<td class="uk-text-center"> <s:property value="treatment_mode"/> </td>
													        <td class="uk-text-left"><s:property value="treatment_nameth"/></td> 
													        <td class="uk-text-right"><s:property value="price_standard"/></td>
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
				$( ".m-patient" ).addClass( "uk-active" );
				$("input[name='selectTreatmentCode']").click(function(){
					$("#treatment_code").val($(this).val());
				});
				$('#hd_tooth').on('click',function(){
					$("#tooth_tooth").prop('required',true);
					
					$("#surf_tooth").removeAttr("required");
					$("#surf").removeAttr("required");
					$("#quadrant").removeAttr("required");
					
					$("#surf_tooth").val('');
					$("#surf").val('');
					$("input[name='servicePatModel.quadrant']").prop('checked', false);
					$("input[name='servicePatModel.arch']").prop('checked', false);
				});
				$('#hd_surf').on('click',function(){  
					$("#surf_tooth").prop('required',true);
					$("#surf").prop('required',true);  
					
					$("#tooth_tooth").removeAttr("required");    
					$("#tooth_tooth").val('');
					$("#quadrant").removeAttr("required");
					$("input[name='servicePatModel.quadrant']").prop('checked', false);
					$("input[name='servicePatModel.arch']").prop('checked', false);
				});  
				$('#hd_quadrant').on('click',function(){  
					$("input[name='servicePatModel.quadrant']").prop('required',true); 
					
					$("#tooth_tooth").removeAttr("required");    
					$("#surf_tooth").removeAttr("required");
					$("#surf").removeAttr("required");
					
					$("#tooth_tooth").val('');
					$("#surf_tooth").val('');
					$("#surf").val('');
					$("input[name='servicePatModel.arch']").prop('checked', false);
				});
				$('#hd_arch').on('click',function(){  
					$("input[name='servicePatModel.arch']").prop('required',true);  
					
					$("#tooth_tooth").removeAttr("required");    
					$("#surf_tooth").removeAttr("required");
					$("#surf").removeAttr("required");
					
					$("#tooth_tooth").val('');
					$("#surf_tooth").val('');
					$("#surf").val('');
					$("input[name='servicePatModel.quadrant']").prop('checked', false);
				});
				
				$(".submitform").on('click',function(){
					$("#treatment_code").removeAttr("required"); 
					$("#tooth_tooth").removeAttr("required");    
					$("#surf_tooth").removeAttr("required");
					$("#surf").removeAttr("required");
					$("input[name='servicePatModel.arch']").removeAttr("required");
					
				})
				
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
		</script>
	</body>
</html>