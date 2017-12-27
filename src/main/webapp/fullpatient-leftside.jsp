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
		<h4 class="hd-text"><small class=" uk-text-primary">อาชีพ : </small> <s:property value="servicePatModel.career"/></h4>
		<!-- Phone Number -->
		<h4  class="hd-text"><small class=" uk-text-primary">เบอร์โทร: </small> 
			<s:iterator value="servicePatModel.ListTelModel" status="telStatus">
				<s:if test="%{#telStatus.index > 0}">,</s:if>
				<s:property value="tel_number"/> - <s:property value="tel_typename"/> 
			</s:iterator>
		</h4>
		<h4 class="hd-text"><small class=" uk-text-primary">เบอร์โทรฉุกเฉิน: </small> 
			เบอร์ <s:property value="servicePatModel.emTellNumber" />
			ชื่อ <s:property value="servicePatModel.emTellRelevantPerson" />
			ความสัมพันธ์ <s:property value="servicePatModel.emRelative" />
		</h4>
		<!-- Phone Number -->
		<!-- Family -->
		<h4  class="hd-text">
			<a class="uk-button uk-button-primary" href="family">ครอบครัว & คนรู้จัก</a>
		</h4>
		<!-- Family -->
		<h4  class="hd-text"><small class=" uk-text-primary">แผนการรักษา: </small><a href="viewAllTreatmentPlan" class="uk-button uk-button-primary">จัดการ</a></h4>
		<h4  class="hd-text"><small class=" uk-text-primary">ประเภทการรักษา: <s:property value="servicePatModel.patient_type_name"/> </small> </h4>
		
		<h4  class="hd-text"><small class=" uk-text-primary">เงินฝาก : </small>
			<b class="uk-text-primary numeric "> <s:property value="servicePatModel.deposit_money_text" /> บาท</b> - <a href="depositBegin">เพิ่มเงินฝาก</a>
		</h4>
		<h4  class="hd-text"><small class=" uk-text-primary">ค้างชำระ: </small>
			<span class="red numeric"> <s:property value="servicePatModel.owe_money_text" /> บาท</span> - <a href="#" id="owelist" >ดูรายละเอียด</a>
		</h4>
		<h4  class="hd-text"><small class=" uk-text-primary">คะแนนสะสม: </small>  
			<b class="uk-text-success"> 450 คะแนน</b> - <a href="#point" data-uk-modal>ดูคะแนนสะสม</a>
		</h4>
		<h4  class="hd-text"><small class=" uk-text-primary">รายการจ่ายเงิน : </small>
			<span class="red numeric"></span> - <a href="getFinancePay" >ดูรายละเอียด</a>
		</h4>
		<h4  class="hd-text"><small class=" uk-text-primary">รายการใบเสร็จและใบค้างชำระ: </small>
			<a href="reportReceiptOweBegin" id="reportReceiptOweBegin" >ดูรายละเอียด</a>
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
<div class="padding5 border-gray uk-panel uk-panel-box bg-gray">
	<h4 class="hd-text uk-text-primary">โน๊ตการแพทย์</h4>
	<s:textarea class="boxsizingBorder" readonly="true" rows="5" name="servicePatModel.remark" />
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
	<!-- model -->
	<div id="my-id" class="uk-modal ">
	    <div class="uk-modal-dialog uk-form " >
	        <a class="uk-modal-close uk-close"></a>
	         <div class="uk-modal-header"><h3><i class="uk-icon-warning"></i> แจ้งเตือนข้อมูลคนไข้</h3></div>
	         	<div class="uk-width-1-1 uk-overflow-container">
	         	<h4 class="uk-text-primary">ประวัติแพ้ยา</h4>
	         		<ul>
		         		<s:if test="%{servicePatModel.beallergic.isEmpty()}">
							<li>ไม่มีประวัติแพ้ยา</li>
						</s:if>	
						<s:else>		         	
							<s:iterator value="servicePatModel.beallergic"> 
								<li class="uk-text-danger textallergic"><s:property value="beallergic_name_th"/></li>
							</s:iterator>
						</s:else>
					</ul>	
				</div>
				<hr>								
				<div class="uk-width-1-1 uk-overflow-container">
	         	<h4 class="uk-text-primary">โรคประจำตัว</h4>
	         		<ul>
		         		<s:if test="%{servicePatModel.congenList.isEmpty()}">
							<li>ไม่มีโรคประจำตัว</li>
						</s:if>	
						<s:else>		         	
							<s:iterator value="servicePatModel.congenList"> 
								<li class="uk-text-danger textcon"><s:property value="congenital_name_th"/></li>
							</s:iterator>
						</s:else>
					</ul>	
				</div>
	         	 
	         <div class="uk-modal-footer uk-text-right">
	         	<button class="uk-modal-close uk-button uk-button-success">ยืนยัน</button>
	         </div>
	    </div>
	</div>	
	<!-- model -->
	<div id="model-owelist" class="uk-modal ">
	    <div class="uk-modal-dialog uk-form " >
	        <a class="uk-modal-close uk-close"></a>
	         <div class="uk-modal-header"><i class="uk-icon-money"></i> รายการค้างชำระ</div>
	         	<div class="uk-width-1-1 uk-overflow-container">
					<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " id="tablemedicine">
					    <thead>
					        <tr class="hd-table"> 
					        	<th class="uk-text-center">เลือก</th>
					            <th class="uk-text-center">สาขาที่ค้างชำระ</th> 
					            <th class="uk-text-center">เลขที่อ้างอิง</th>
					            <th class="uk-text-center">วันที่</th> 
					            <!-- <th class="uk-text-center">ใบเสร็จรับเงิน</th> -->
					        </tr>
					    </thead> 
					    <tbody class="showowe">
							
						</tbody>
					</table>
				</div>         	
	         <div class="uk-modal-footer uk-text-right">
	         	<button class="uk-modal-close uk-button uk-button-danger"  id="btn_checkGiftv">ยกเลิก</button>
	         </div>
	    </div>
	</div>
	<!-- model -->
	
<script src="js/autoNumeric.min.js"></script>
<script>
	$(document).ready(function() {
		$(".numeric").autoNumeric('init');
		
		<% if(request.getAttribute("toothHistory")!=null){ 
			
			List<ToothModel> toothHistory = (List) request.getAttribute("toothHistory"); 
			for(ToothModel tm :toothHistory){%>
			$('#tooth_<%=tm.getTooth_num()%>').prepend('<img class="case" onerror=this.style.display="none" src="img/tooth/<%=tm.getTooth_num()%>/<%=tm.getTooth_pic_code()%>/<%=tm.getSurface()%>.png" />');
			<%}
		}%>
		$('#btn-show-content').click(function(e){
			$('#right-content').load("branch-hn-list");
		});
		var modal = UIkit.modal("#my-id");
		var congen = $(".textcon").text();
		var bealler = $(".textallergic").text();
		if(congen != '' || bealler != '' ){		
			modal.show();
		}
		console.log(congen);
	});
	$(document).on("click","#owelist",function(){
		
		 window.oweOBJ = {"owelist": []}
			
			<s:iterator value="listOweModel">
		 	oweOBJ.owelist.push({
				"owe_id":<s:property value="owe_id" />,
				"hn":'<s:property value="hn" />', 
				"branch_id":'<s:property value="branch_id" />', 
				"receipt_id":<s:property value="receipt_id" />,
				"owe_date":'<s:property value="owe_date" />'
			});
			</s:iterator>
		readOweList()
			
		let modal = UIkit.modal('#model-owelist');
		modal.show();
	})
	
function readOweList(){ 
	
	$('.showowe').empty()	
	 
		for (let i = 0; i < oweOBJ.owelist.length; i++) { 
			 
			let appall = '<tr > '+ 
				'<th class="uk-text-center"><a type="button" href="oweBegin?owe_id='+oweOBJ.owelist[i].owe_id+'" class="uk-button uk-button-small uk-button-success" >ชำระเงิน</a></th>'+
				'<th class="uk-text-center">'+oweOBJ.owelist[i].branch_id+'</th>'+ 
				'<th class="uk-text-center">'+oweOBJ.owelist[i].receipt_id+'</th>'+ 
				'<th class="uk-text-center">'+oweOBJ.owelist[i].owe_date+'</th>  '+ 
				/* '<th class="uk-text-center"><button type="button" class="uk-button uk-button-small uk-button-primary" onclick="printReceipt('+oweOBJ.owelist[i].receipt_id+')" > พิมพ์ใบเสร็จ</button></th>  '+ */ 
			'<tr > '; 
			
			$('.showowe').append(appall);
			 
		} 
}
function printReceipt(receipt_id){ 
	
	window.open('report/report-receipt-new.jsp?receipt_id='+receipt_id+ //drugname='+encodeURI(drugname)+ 
			''
			, '_blank', ''); 
}

</script>