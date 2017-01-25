<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ page import="com.smict.person.model.*" %> 
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : Patient Master</title>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			 
			<div class="uk-width-1-1">
				
				<%@include file="window-top-menu.jsp" %>

					<div class="uk-grid"></div>
					<form id="lab" action="labMaster" method="post">
					<div class="padding5 border-gray uk-panel uk-panel-box bg-gray"> 
					<div class="uk-grid ">
						<div class="uk-width-1-1 ">
							<div class="uk-panel uk-panel-box">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">list</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> Patient</h3>
								</div>
									<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray" id="table_patient">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">ลำดับ</th> 
									            <th class="uk-text-center">รหัส HN</th>
									            <th>ชื่อ-นามสกุล ภาษาไทย</th>
									            <th>ชื่อ-นามสกุล ภาษาอังกฤษ</th> 
									        </tr>
									    </thead> 
									    <tbody>
									    	 <%   
											    if(request.getAttribute("patientlist")!=null)	{
												    List patientlist = (List) request.getAttribute("patientlist");
				                                	List <PatientModel> patModel = patientlist;
				                                	int x=0;
					            	         	 	for(PatientModel pat : patModel){ 
					            	         	 	x++; 
				            	         	 %>
										        <tr>
										            <td class="uk-text-center"><%=x%></td> 
										            <td class="uk-text-center"><a onclick="Opener('<%=pat.getHn()%>')"><%=pat.getHn()%></a></td>
										            <td class="uk-text-left"><%=pat.getFirstname_th()%> - <%=pat.getLastname_th()%></td>
										            <td class="uk-text-left"><%=pat.getFirstname_en()%> - <%=pat.getLastname_en()%></td> 
									        <% } %> 
										        
									        <% }else{ %>
									        	 <tr>
										            <td class="uk-text-center" colspan="4">ไม่พบข้อมูล</td> 
										        </tr> 
									        <% } %> 
									    </tbody>
									</table>
									</div>
							</div>
						</div> 
					</div> 
					 
				</div>	
					</form> 
			</div>
					
					
		</div>

		<script>
		 	function Opener(hn) { 
	            window.opener.document.getElementById ("hnuse").value = hn;   
	            window.close();
	        } 
		
			$(document).ready(function(){
				$( ".m-setting" ).addClass( "uk-active" );  
				
				$("#table_patient").DataTable({ 
				//	"scrollX": true
				});
			});  
			
		</script>
	</body>
</html>