<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : appointment</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
		<link href="css/style-promotion.css"rel="stylesheet">	
	</head> 
	<body>
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
			<div class="uk-grid"></div>

				<form id="service" action="getemployeelistsearch" method="post">
				<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
					<div class="uk-grid ">
						<div class="uk-width-1-1 uk-overflow-container "></div>
					</div>
					<div class=" ">
						<div class="uk-width-1-1 ">
							<div class="uk-panel uk-panel-box" style="min-height: 101.5vh;">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">Detail</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> Detail</h3>	
								</div>
					<div class="uk-grid">
						<div class="uk-width-1-1 uk-overflow-container">
							<div class="uk-grid">
							<div class="uk-width-1-10"></div>
							<div class="uk-width-8-10 ridge">
								<div class="uk-grid">
									<div class="uk-width-2-3 bor-right">

										<div class="uk-panel-header ">
									    	<h2 class="uk-badge fontAndsize uk-badge-primary"><b><i class="uk-icon-user"></i> ข้อมูลคนไข้</b></h2>	
										</div>
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-3 uk-text-right" >
											 <h3><b>HN : </b> </h3>
											</div>
											<div class="uk-width-2-3 " >
											 <h3>&nbsp;<s:property value="appointmentModel.HN" /> </h3>
											</div>
										</div>
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-3 uk-text-right" >
											 <h3><b>ชื่อ - นามสกุล :</b> </h3>
											</div>
											<div class="uk-width-2-3 " >
											 <h3>&nbsp;<s:property value="appointmentModel.patPrenameth" /><s:property value="appointmentModel.firstNameTH" /> <s:property value="appointmentModel.lastNameTH" /> </h3>
											</div>
										</div>
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-3 uk-text-right" >
											 <h3><b>เบอร์โทร :</b> </h3>
											</div>
											<div class="uk-width-2-3 " >
											<h3>&nbsp;
											 	<s:iterator value="telephoneList" status="telStatus">		
													<s:if test="%{#telStatus.index > 0}">,</s:if><s:property value="tel_number"/> - <s:property value="tel_typename"/> 
												</s:iterator>
											</h3>	
											</div>
										</div>
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-3 uk-text-right" >
											 <h3><b>ช่วงเวลาที่ติดต่อได้ :</b> </h3>
											</div>
											<div class="uk-width-1-5" >
											 <h3>&nbsp;<s:property value="appointmentModel.pattimestart" /> </h3>
											</div>
											<div class="uk-width-2-5" >
											 <h3><b>ถึง :</b>&nbsp;<s:property value="appointmentModel.pattimeend" /> </h3>
											</div>
										</div><hr>
										<div class="uk-panel-header ">
									    	<h2 class="uk-badge fontAndsize uk-badge-primary"><b><i class="uk-icon-user"></i> ข้อมูลนัดหมาย</b></h2>	
										</div>
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-3 uk-text-right" >
											 <h3><b>รหัสนัดหมาย : </b> </h3>
											</div>
											<div class="uk-width-2-3 " >
											 <h3>&nbsp;<s:property value="appointmentModel.appointCode" /> </h3>
											</div>
										</div>
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-3 uk-text-right" >
											 <h3><b>วันที่ :</b> </h3>
											</div>
											<div class="uk-width-2-3 " >
											 <h3>&nbsp;<s:property value="appointmentModel.date" /> </h3>
											</div>
										</div>
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-3 uk-text-right" >
											 <h3><b>เวลา :</b> </h3>
											</div>
											<div class="uk-width-1-5" >
											 <h3>&nbsp;<s:property value="appointmentModel.timeStart" /> </h3>
											</div>
											<div class="uk-width-2-5" >
											 <h3><b>ถึง :</b>&nbsp;<s:property value="appointmentModel.timeEnd" /> </h3>
											</div>
										</div>
										
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-3 uk-text-right" >
											 <h3><b>แพทย์ :</b> </h3>
											</div>
											<div class="uk-width-2-3 " >
											 <h3>&nbsp;<s:property value="appointmentModel.docprenameth" /><s:property value="appointmentModel.docfirstname" /> <s:property value="appointmentModel.doclastname" /> </h3>
											</div>
										</div>
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-3 uk-text-right" >
											 <h3><b>สาขา :</b> </h3>
											</div>
											<div class="uk-width-2-3 " >
											 <h3>&nbsp;<s:property value="appointmentModel.branchName" /> </h3>
											</div>
										</div>
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-3 uk-text-right" >
											 <h3><b>อาการ :</b> </h3>
											</div>
											<div class="uk-width-2-3 " >
											 <h3>&nbsp;
												 <s:iterator value="getSymptomRelatelist" status="sysStatus">												
													<s:if test="%{#sysStatus.index > 0}">,</s:if><s:property value="sympDescription"/> 
												</s:iterator>
											</h3>
											</div>
										</div>

										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-3 uk-text-right" >
											 <h3><b>อ้างอิง :</b> </h3>
											</div>
											<div class="uk-width-2-3 " >
											 <h3>&nbsp;<s:property value="appointmentModel.referID" /> </h3>
											</div>
										</div>
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-3 uk-text-right" >
											 <h3><b>เหตุผล:</b> </h3>
											</div>
											<div class="uk-width-2-3 " >
											 <h3>&nbsp;<s:property value="appointmentModel.description" /> </h3>
											</div>
										</div>
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-3 uk-text-right" >
											 <h3><b>คำแนะนำในการเตรียมตัวก่อนพบแพทย์:</b> </h3>
											</div>
											<div class="uk-width-2-3 " >
											 <h3>&nbsp;<s:property value="appointmentModel.recommend" /> </h3>
											</div>
										</div><br>									
										<div class="uk-grid uk-grid-collapse">
											<div class="uk-width-1-4 uk-text-center " >
												<a href="#isdayview" class="uk-button uk-button-danger  buttomall" data-uk-modal>ปิดแจ้งเตือนวันนี้</a>
											</div>
											<div class="uk-width-1-4 uk-text-center" >
												<a href="#confirmapp" class="uk-button uk-button-success buttomall" data-uk-modal>ยืนยันนัด</a>
											</div>
											<div class="uk-width-1-4 uk-text-center" >
												<a href="#postponeapp" class="uk-button uk-button-primary buttomall" data-uk-modal>เลื่อนนัด</a>
											</div>
											<div class="uk-width-1-4 uk-text-center" >
												<a href="#cancelapp" class="uk-button uk-button-danger buttomall" data-uk-modal>ยกเลิกนัด</a>
											</div>		
										</div>
									</div>
									<div class="uk-width-1-3">
										<div class="uk-grid uk-grid-collapse">										
											<div class="uk-width-1-1 uk-text-right" >
												<a href="#contact_log" class="uk-button uk-button-success buttomall addconagain" data-uk-modal>
												<i class="uk-icon-plus"></i> เพิ่มการติดต่อ</a>
											</div>		
										</div>
										<div class="uk-grid uk-grid-collapse">											
											<div class="uk-width-1-1" >
												<h2>ข้อมูลการติดต่อ</h2>
											</div>		
										</div><br>
										<div class="uk-width-1-1 uk-overflow-container treatment-table">
										<s:iterator value="contactLogList" status="conStatus">
										<s:if test="%{#conStatus.index > 0}"><hr></s:if>
										<div class="uk-grid uk-grid-collapse">											
											<div class="uk-width-1-1" >
												<small>ครั้งที่ <s:text name="%{#conStatus.count}" />
												<s:if test="contactStatus == 0">
												&nbsp;<span class=" uk-badge fontAndsize uk-badge-success">ติดต่อได้</span>
												</s:if>
												<s:else>
												&nbsp;<span class=" uk-badge fontAndsize uk-badge-danger">ติดต่อไม่ได้</span>
												</s:else> 
												<br> วันที่ <s:property value="contactdate" /> <br> เหตุผล : <s:property value="conractdes" />
												</small>
											</div>												
										</div>
										<s:if test="#conStatus.last== true">
									         <input type="hidden" id="conchack" value="<s:property value="contactStatus" />">
									     </s:if>
										</s:iterator>
										</div>	
									</div>
								</div>							
							</div>							
							</div>
						</div>
					</div>
							</div>
						</div> 
					</div>

				</div>	
				</form>					
			</div>	
		</div>
					<div id="contact_log" class="uk-modal ">
						<form action="updateContactlog" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-header">
				         	<i class="uk-icon-plus"></i> เพิ่มการติดต่อ
				         	</div>
				         	<div class="uk-modal-body uk-form">
				         		<div class="uk-grid ">
				         			<div class="uk-width-1-2 uk-text-right">
				         				<input type="radio" value="0" name="appointmentModel.contactStatus"> ติดต่อได้	
				         			</div>
				         			<div class="uk-width-1-2">
				         				<input type="radio" value="1" name="appointmentModel.contactStatus" checked="checked"> ติดต่อไม่ได้
				         			</div>
				         		</div><br>
				         		<div class="uk-grid uk-grid-collapse">
				         			<div class="uk-width-1-4 uk-text-right">
				         				เหตุผล :	
				         			</div>
				         			<div class="uk-width-3-4">
				         				<textarea rows="" name="appointmentModel.conractdes" cols=""></textarea>
				         			</div>
				         		</div>
				         		
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">			                    
			                    <s:hidden name="appointmentModel.appointmentID"></s:hidden>
			                    <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
                			</div>

					    </div>
					    </form>
					</div>
				<div id="isdayview" class="uk-modal ">
						<form action="updateStatusIsdayview" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-header uk-text-danger">
				         	<i class="uk-icon-plus "></i> ปิดการแจ้งเตือนนัดหมายวันนี้!
				         	</div>
				         	<div class="uk-modal-body uk-form">
				         		<div class="uk-grid uk-grid-collapse">
									<h3 class="uk-text-danger">ท่านต้องการปิดการแจ้งเตือนวันนี้หรือไม่!</h3>
				         		</div>				         		
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">			                    
			                    <s:hidden name="appointmentModel.appointmentID"></s:hidden>
			                    <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
                			</div>

					    </div>
					    </form>
					</div>
				<div id="postponeapp" class="uk-modal ">
						<form action="" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-header uk-text-danger">
				         	<i class="uk-icon-plus"></i> เลื่อนนัดหมาย!
				         	</div>
				         	<div class="uk-modal-body uk-form">
				         		<div class="uk-grid uk-grid-collapse">
				         			<div class="uk-width-1-4 uk-text-right">
				         				เหตุผล :	
				         			</div>
				         			<div class="uk-width-3-4">
				         				<textarea rows="" name="appointmentModel.description" cols=""></textarea>
				         			</div>
				         		</div>		
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">			                    
			                    <s:hidden name="appointmentModel.appointmentID"></s:hidden>
			                    <s:hidden name="appointmentModel.appointmentStatus" value="4"></s:hidden>
			                    <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
                			</div>

					    </div>
					    </form>
				</div>
				<div id="cancelapp" class="uk-modal ">
						<form action="updateAppStatuslog" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-header uk-text-danger">
				         	<i class="uk-icon-plus "></i> ยกเลิกนัดหมาย!
				         	</div>
				         	<div class="uk-modal-body uk-form">
				         		<div class="uk-grid uk-grid-collapse">
				         			<div class="uk-width-1-4 uk-text-right">
				         				เหตุผล :	
				         			</div>
				         			<div class="uk-width-3-4">
				         				<textarea rows="" name="appointmentModel.description" cols=""></textarea>
				         			</div>
				         		</div>
				         		
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">			                    
			                    <s:hidden name="appointmentModel.appointmentID"></s:hidden>
			                    <s:hidden name="appointmentModel.appointmentStatus" value="3"></s:hidden>
			                    <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
                			</div>

					    </div>
					    </form>
					</div>
					<div id="confirmapp" class="uk-modal ">
						<form action="updateAppStatuslog" method="post"> 
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-body">
				         	<h3>ยืนยันการนัดหมาย!</h3> 
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">	
				         			<div class="uk-width-3-4 hidden">
				         				<textarea rows="" name="appointmentModel.description" cols=""></textarea>
				         			</div>		                    
			                    <s:hidden name="appointmentModel.appointmentID"></s:hidden>
			                    <s:hidden name="appointmentModel.appointmentStatus" value="2"></s:hidden>
			                    <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-danger uk-modal-close">ยกเลิก</button>
                			</div>
					    </div>
					    </form>
					</div>    
<script>
			$(document).ready(function () {
				if(<s:property value="appointmentModel.appointmentStatus" /> != 5){
					$('.buttomall').addClass('hidden')					
				}
			});

			

</script>
	</body>
</html>