<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="com.smict.all.model.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="uk-width-4-10 ">

<div class="uk-grid bg-gray padding5  border-gray">
	<div class="uk-width-2-3 ">
		<h3 class="hd-text padding5 uk-text-primary">ประวัติคนไข้</h3>	
		<!-- <h4 class="hd-text" >
			<small class=" uk-text-primary">HN : </small> 
			<s:property value="servicePatModel.hnFormat" />
		</h4> -->
		<h4 class="hd-text" >
			<small class=" uk-text-primary">HN (สาขา) : </small> 
			<s:if test="servicePatModel.hnBranch == null">
				<a href="generate-hn-branch" class="uk-button uk-button-success uk-button-small">
					<i class="uk-icon-cogs"></i> 
					Generate Branch HN
				</a>
				<br />
				<buton id="btn-show-content" class="uk-button uk-button-primary">แสดงทุกสาขา</buton>
			</s:if>
			<s:else>
				<s:property value="servicePatModel.hnBranch"/>
				<buton id="btn-show-content" class="uk-button uk-button-primary">แสดงทุกสาขา</buton>
			</s:else>
		</h4>
		<h4  class="hd-text"><small class=" uk-text-primary">ชื่อ-สกุลไทย : </small> <s:property value="servicePatModel.pre_name_th"/> <s:property value="servicePatModel.firstname_th"/> <s:property value="servicePatModel.lastname_th"/></h4>
		<h4  class="hd-text"><small class=" uk-text-primary">ชื่อ-สกุลต่างชาติ : </small> <s:property value="servicePatModel.pre_name_en"/> <s:property value="servicePatModel.firstname_en"/> <s:property value="servicePatModel.lastname_en"/></h4>
		<h4  class="hd-text"><small class=" uk-text-primary">อายุ : </small> <s:property value="servicePatModel.age"/> ปี</h4>
		
		<h4  class="hd-text"><small class=" uk-text-primary">เบอร์โทร: </small> 
			<s:iterator value="servicePatModel.ListTelModel" status="telStatus">
				<s:if test="%{#telStatus.index > 0}">,</s:if>
				<s:property value="tel_number"/> - <s:property value="tel_typename"/>
			</s:iterator>
		</h4>
		<h4  class="hd-text"><small class=" uk-text-primary">แผนการรักษา: </small><a href="viewAllTreatmentPlan" class="uk-button uk-button-primary">จัดการ</a></h4>
		<h4  class="hd-text"><small class=" uk-text-primary">ประเภทการรักษา: <s:property value="servicePatModel.patient_type_name"/> </small> </h4>
		
		<h4  class="hd-text"><small class=" uk-text-primary">เงินฝาก : </small>
			<b class="uk-text-primary"> <s:property value="servicePatModel.deposit_money"/> บาท</b> - <a href="#">เพิ่มเงินฝาก</a>
		</h4>
		<h4  class="hd-text"><small class=" uk-text-primary">ค้างชำระ: </small><span class="red"> 1,500 บาท</span></h4>
		<h4  class="hd-text"><small class=" uk-text-primary">คะแนนสะสม: </small>  
			<b class="uk-text-success"> 450 คะแนน</b> - <a href="#point" data-uk-modal>ดูคะแนนสะสม</a>
		</h4>
		<s:url action="entranchEditPatient" var="entranchEditPatient">
		</s:url>
		<a href='<s:property value="entranchEditPatient"/>' class="uk-button uk-button-primary uk-button-small "><i class="uk-icon-pencil-square-o"></i> แก้ไขข้อมูลคนไข้</a> 
		<button class="uk-button uk-button-primary uk-button-small "><i class="uk-icon-print"></i> Print</button>
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
<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
	<h4 class="hd-text uk-text-primary">โน๊ตการแพทย์</h4>
	<textarea class="boxsizingBorder" rows="5"></textarea>
	<div class="uk-grid">
		<div class="uk-width-1-2">
			<h4  class="hd-text uk-text-primary">โรคประจำตัว </h4>
			<select size="5" style="width:100%;" disabled="true">
				<s:if test="%{servicePatModel.congenList.isEmpty()}">
					<option>ไม่มีโรคประจำตัว</option>
				</s:if>
				<s:else>
					<s:iterator value="servicePatModel.congenList"> 
						<option class="uk-text-danger"><s:property value="congenital_name_th"/></option>
					</s:iterator>
				</s:else>
			</select>
		</div>
		<div class="uk-width-1-2">
			<h4 class="hd-text uk-text-primary">ประวัติแพ้ยา</h4>
			<select size="5" style="width:100%;" disabled="true">
				<s:if test="%{servicePatModel.beallergic.isEmpty()}">
					<option>ไม่มีประวัติแพ้ยา</option>
				</s:if>
				<s:else>
					<s:iterator value="servicePatModel.beallergic"> 
						<option class="uk-text-danger"><s:property value="beallergic_name_th"/></option>
					</s:iterator>
				</s:else>
			</select>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		$('#btn-show-content').click(function(e){
			$('#right-content').load("branch-hn-list");
		});
	});
</script>