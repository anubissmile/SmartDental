<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="com.smict.product.model.*" %>
<%@ page import="com.smict.person.model.*" %> 
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental : Reference Lab</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
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
								    <h3 class="uk-panel-title"><i class="uk-icon-th-list"></i> Reference No : <%=request.getAttribute("id") %></h3>
								</div>
									<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray" id="table_reflab">
									    <thead>
									        <tr class="hd-table">
									        	<th class="uk-text-center">รหัส Lab</th>
									        	<th class="uk-text-center">งาน Lab</th>
									        	<th class="uk-text-center">บริษัท</th>
									        	<th class="uk-text-center">แพทย์</th>
									        	<th class="uk-text-center">คนไข้</th>
									        	<th class="uk-text-center">รหัสการรักษา</th>
									        </tr>
									    </thead> 
									    <tbody>
									    	 <%   
									    	 if(request.getAttribute("refList")!=null)	{
												    List refList = (List) request.getAttribute("refList");
				                                	List <SendLabModel> sendlabModel = refList;
				                                	int x=0;
					            	         	 	for(SendLabModel trm : sendlabModel){ 
					            	         	 	x++; 
				            	         	 %>
										        <tr>
										            <td class="uk-text-center"><%=trm.getId()%></td>
										            <td class="uk-text-left"><%=trm.getService_name()%></td> 
										            <td class="uk-text-left"><%=trm.getLab_name()%></td>
										            <td class="uk-text-left"><%=trm.getFirst_name_th()%></td>
										            <td class="uk-text-left"><%=trm.getPatientname()%></td> 
										            <td class="uk-text-center"><%=trm.getTreatment_code()%></td>
									        <% } %> 
										        
									        <% }else{ %>
									        	 <tr>
										            <td class="uk-text-center" colspan="6">ไม่พบข้อมูล</td> 
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
				
				$("#table_reflab").DataTable({ 
				//	"scrollX": true
				});
			});  
			
		</script>
	</body>
</html>