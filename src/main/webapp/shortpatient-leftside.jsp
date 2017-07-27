<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
					<div class="uk-width-4-10 ">
						<div class="uk-grid bg-gray padding5  border-gray">
							<div class="uk-width-2-3 ">
								<h3 class="hd-text padding5 uk-text-primary">ประวัติคนไข้</h3>	
								<h4 class="hd-text " ><small class=" uk-text-primary">HN : </small> <s:property value="servicePatModel.hn"/></h4>
								<h4  class="hd-text"><small class=" uk-text-primary">ชื่อ-สกุลไทย : </small> <s:property value="servicePatModel.pre_name_th"/> <s:property value="servicePatModel.firstname_th"/> <s:property value="servicePatModel.lastname_th"/></h4>
								<h4  class="hd-text"><small class=" uk-text-primary">ชื่อ-สกุลต่างชาติ : </small> <s:property value="servicePatModel.pre_name_en"/> <s:property value="servicePatModel.firstname_en"/> <s:property value="servicePatModel.lastname_en"/></h4>
								<h4  class="hd-text"><small class=" uk-text-primary">อายุ : </small> <s:property value="servicePatModel.age"/> ปี</h4>
								<h4  class="hd-text"><small class=" uk-text-primary">เบอร์โทร: </small> 
									<s:iterator value="servicePatModel.ListTelModel" status="telStatus">
										<s:if test="%{#telStatus.index > 0}">,</s:if>
										<s:property value="tel_number"/> - <s:property value="tel_typename"/>
									</s:iterator>
								</h4>
								<h4  class="hd-text"><small class=" uk-text-primary">ประเภทการรักษา: <s:property value="servicePatModel.patient_type_name"/> </small> </h4>
								<h4  class="hd-text"><small class=" uk-text-primary">แผนการรักษา: </small><a href="viewAllTreatmentPlan" class="uk-button uk-button-primary">จัดการ</a></h4>
							</div>
							<div class="uk-width-1-3  ">
								<img src='<s:property value="servicePatModel.profile_pic"/>' alt="No Profile Picture" class="profile-pic">
							</div>
							
						</div>
						<div id="tooth-table-pic" class="uk-overflow-container">
		<table class="tooth-table border-gray ">
			<% if(request.getAttribute("toothListUp")!=null){ 
			List toothlist = (List) request.getAttribute("toothListUp"); %>
			<tr class="tooth-pic">
		    	<%	
		    	for (Iterator iterA = toothlist.iterator(); iterA.hasNext();) {
	       			ToothModel toothModel = (ToothModel) iterA.next();
	    				%>  
	    				<td id="tooth_<%=toothModel.getTooth_num()%>">
					<img src="img/tooth/<%=toothModel.getTooth_num()%>/<%=toothModel.getTooth_num()%>.jpg" />
			
				</td>
				<%}  %>
			</tr>
			
			<tr class="uk-text-center">
				<%	
		    	for (Iterator iterA = toothlist.iterator(); iterA.hasNext();) {
	       			ToothModel toothModel = (ToothModel) iterA.next();
	    				%> 
	    					<td><%=toothModel.getTooth_num()%></td>
	    				<%	} %>
			</tr>
		<%	}
		%>
		</table>
		
		<table class="tooth-table border-gray ">
			<% if(request.getAttribute("toothListLow")!=null){ 
			List toothlist = (List) request.getAttribute("toothListLow"); %>
			<tr class="uk-text-center">
				<%	
		    	for (Iterator iterA = toothlist.iterator(); iterA.hasNext();) {
	       			ToothModel toothModel = (ToothModel) iterA.next();
	    				%> 
	    					<td><%=toothModel.getTooth_num()%></td>
	    				<%	} %>
			</tr>
			<tr class="tooth-pic">
		    	<%	
		    	for (Iterator iterA = toothlist.iterator(); iterA.hasNext();) {
	       			ToothModel toothModel = (ToothModel) iterA.next();
	    				%>  
	    				<td id="tooth_<%=toothModel.getTooth_num()%>">
					<img src="img/tooth/<%=toothModel.getTooth_num()%>/<%=toothModel.getTooth_num()%>.jpg" />
				</td>
				<%	} %>
			</tr>
			
			
		<%	}
		%>
		</table>
	</div>	
					</div>