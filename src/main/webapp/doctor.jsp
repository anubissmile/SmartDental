<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.person.model.*" %>
<%@ page import="com.smict.person.action.*" %>
<!DOCTYPE html>

<html>
	<head>
		<title>Smart Dental:ข้อมูลแพทย์</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="doctor-nav.jsp" %>
 
 			<form id="branch" action="branchM" method="post" >
				
					<div class="uk-width-1-1 padding5 uk-form" >
					
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
						 	<p class="uk-text-muted uk-width-1-1">ข้อมูลแพทย์ </p>
						 	
							<div class="uk-width-1-1">
						 	<table id="doc_table" class="uk-table uk-table uk-table-hover uk-table-condensed border-gray ">
						 		<thead>
						 			<tr class="hd-table">
						 				<th>รหัสแพทย์</th>
							            <th>ชื่อ-นามสกลุ TH</th> 
							            <th>ชื่อ-นามสกลุ EN</th> 
							            <th>ชื่อเล่น</th>
							            <th>เลขที่ใบประกอบวิชาชีพ</th>
							            <th>เลขที่สัญญา</th>
							            <th>รหัสพนักงาน</th>
							            <th>จัดการ</th>
							        </tr>
						 		</thead>
						 		<tbody>
						 			<%   
									    if(request.getAttribute("doctorList")!=null)	{
										    List doctorList = (List) request.getAttribute("doctorList");
		                                	List <DoctorModel> doctorModel = doctorList;
		                                	int x=0;
			            	         	 	for(DoctorModel pbm : doctorModel){ 
			            	         	 		
			            	         	 	x++; 
		            	         	%>
						 			<tr>
						 				<td><%=pbm.getDoctorID()%><input type="hidden" id="hdDoctorD" name="hdDoctorD" value="<%=pbm.getDoctorID()%>"></td> 
							            <td><%=pbm.getPre_name()%> <%=pbm.getFirstname_th()%> <%=pbm.getLastname_th()%></td> 
							            <td><%=pbm.getPre_name_en()%> <%=pbm.getFirstname_en()%> <%=pbm.getLastname_en()%></td>
							            <td><%=pbm.getNickname()%> </td>
							            <td><%=pbm.getTMCLicense()%> </td>
							            <td><%=pbm.getContract_id()%> </td>
							            <td><%=pbm.getEmp_id()%> </td>
							            <td>
							            <a href="GetDoctor?d=<%=pbm.getDoctorID()%>" class="uk-button uk-button-primary uk-button-small"><i class=" uk-icon-eye"></i></a>
							             <% } %> 
										        
							        <% }else{ %>
							        	 <tr>
								            <td class="uk-text-center" colspan="7">ไม่พบข้อมูล</td> 
								        </tr> 
							        <% } %> 
						 		</tbody>
						 	</table>
						 	</div>
						
						</div>
					</div>
				
			</form>
			</div> 	
		 </div>
			
			 
		<script>
		
		$(document).ready(function(){
			$( ".m-setting" ).addClass( "uk-active" );
			$("#doc_table").DataTable();
		}); 
		
		</script>		
	</body>
</html>