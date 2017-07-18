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
							<form action="addContype" method="post">
								<div class="uk-grid uk-grid-collapse">
								
								<div class="uk-width-1-1 uk-form">
									<div class="uk-panel uk-panel-box padding5 ">
									<h4  class="hd-text uk-text-primary">เพิ่มข้อมูลประเภทสมาชิก  </h4>
										<div class="uk-grid">
											<div class="uk-width-1-1 uk-overflow-container">
								         		<ul class="uk-subnav uk-subnav-pill" data-uk-switcher="{connect:'#subnav-pill-content-1'}">
								         		<%
								         		ContactData contact_Data = new ContactData();
								         		List<JSONObject> List_contactname = contact_Data.getContactnameList("", "");
								         		int runround = 0;
								         		for(JSONObject jsobListContactName : List_contactname){
								         		%>
								         			<li <%if(runround == 0){ %>class="uk-active" aria-expanded="true"<%}else{%>class="" aria-expanded="false"<%}%> ><a href="#"><%=jsobListContactName.get("contact_name").toString() %></a></li>
								         		<%
								         		runround++;
								         		}
								         		%>
					                            </ul>
					                            
					                            <!-- Start -->
					                            <ul id="subnav-pill-content-1" class="uk-switcher">
					                            <%
								         		int round = 0;
					                            
					                            for(JSONObject jsonListContactName : List_contactname){
								         		%>
								         			<li <%if(round == 0){%>class="uk-active" aria-hidden="false"<%}else{%>class="" aria-hidden="true"<% } %>>
														<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " >
														    <thead>
														        <tr class="hd-table"> 
														            <th class="uk-text-center">คลิก</th> 
														            <th class="uk-text-center">ชื่อ</th> 
														        </tr>
														    </thead> 
														    <tbody>
														    
														    <%
														    List<JSONObject> ListSubContact = contact_Data.getSubContactnameList(jsonListContactName.get("contact_id").toString(), "", "", "");
														    if(!ListSubContact.isEmpty()){
														    	
														    	for(JSONObject jsonobj : ListSubContact){
														    %>
														    	<tr> 
															        <td class="uk-text-center">
															        	<div class="uk-form-controls">	
								                                            <input type="radio"  class="contypeall" id="patient_contypeid" name="patContypeModel.sub_contact_id" value="<%=jsonobj.get("sub_contact_id")%>" <%if(jsonobj.get("sub_contact_id").equals("1"))%>  > <label for="form-s-c"></label>
					                                        			</div>
					                                        		</td>
															        <td class="uk-text-center patient_typename"><%=jsonobj.get("sub_contact_name")%></td>
																</tr>
														    <%
														    	}
														    }
														    %>
															</tbody>
														</table>
													</li>
								         		<%
								         		round++;
								         		}
								         		%>
								         		</ul>
					                           	<!-- End -->
					                            
												</div>
								         	 
									         <div class="uk-width-1-1 uk-text-center">
									         	<button class="uk-button uk-button-success" name="btn_submit_patienttype" id="btn_submit_patienttype">ตกลง</button>
									         	<button class="uk-button uk-button-danger" name="btn_submit_patienttype" id="btn_submit_patienttype">ยกเลิก</button>
									         </div>
										</div>
										
									</div>
								</div>
								
							</div>
				<div id="companydefault" class="uk-modal ">
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-pencil"></i> ตั้งค่า จำนวนเงิน</div>
					         <div class="uk-grid uk-grid-small"> 
					         	<div class="uk-width-2-10">
					         	</div>
					         	<div class="uk-width-8-10 uk-form-icon">
					         	จำนวนเงิน : <i class="uk-icon-money"></i>
		                             <input type="text"  name="totalamountall" 
		                             		value="<s:property value="protionModel.sub_contact_amount" />"   
		                             		class="uk-width-1-2 numeric amountallis uk-text-right" > บาท
					         	</div>
					         </div>	  
					         <div class="uk-modal-footer uk-text-right">
					         <input type="hidden"  name="protionModel.sub_contact_type_id" 
		                             		value=""   
		                             		class="typeID   " >
					         	<button class="uk-button uk-button-success uk-modal-close">ยืนยัน</button> 
					         </div>
					    </div>
					</div> 
						</form>
						
					</div>
				</div>
				
				
			</div>
		</div>
		<script src="js/autoNumeric.min.js"></script>
		<script>
			$(document).ready(function(){
				$( ".m-patient" ).addClass( "uk-active" );
				$(".numeric").autoNumeric('init');
				$('.contypeall').change(function () {
					
					var contactid = $(this).val();
					$.ajax({  //   
					    type: "post",
					    url: "ajax_json_contact", //this is my servlet group
					    data: {contactid:contactid},
					    async:false, 
					    success: function(result){ 
						    if (result != '') {	
						    	$.each(result, function(i, value) { 
						    		if(value.contypeID == 3){
										var modal = UIkit.modal("#companydefault");
						        		if ( modal.isActive() ) {
						        		    modal.hide();
						        		} else {
						        		    modal.show();
						        		}
						        		$('.amountallis').val(value.amountdefault);
						        		
						    		}
						    		$('.typeID').val(value.contypeID);
						    	});          
						    	
						    }
					    }
					});
					
					
				});
			});
		</script>
		
	</body>
</html>