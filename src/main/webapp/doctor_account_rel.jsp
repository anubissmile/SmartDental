<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Doctor_account</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head>
	<body> 	
	<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="doctor-nav-black.jsp" %>
				<script type="text/javascript" src="js/webcam.min.js"></script>
				<form action="insertBookbank" method="post">
					<div class="uk-grid uk-grid-collapse padding5 border-gray uk-panel uk-panel-box bg-gray">
						<div class="uk-width-1-1 uk-overflow-container ">
							 <div class="uk-panel uk-panel-box uk-width-medium-1-1">
							 	<div class="uk-panel-badge uk-badge uk-badge-primary">บัญชีธนาคาร</div>
                                <div class="uk-panel-header">
								    <h3 class="uk-panel-title"><i class="uk-icon-cog"></i> บัญชีธนาคาร</h3> 
								</div>
									<s:if test="hasActionErrors()"> 
								         <div class="uk-alert uk-alert-danger" data-uk-alert> 
								           <li class="uk-alert-close uk-close"></li> 
								             <s:actionerror/> 
								         </div> 
								     </s:if>
								<div class="uk-grid uk-form">
								<div class="hidden"> <s:textfield name="docModel.DoctorID" /></div>
									<div class="uk-width-1-4">
										เลขบัญชี  :
										<input type="text" name="bookModel.bookbank_no" maxlength="10" >
									</div>
									<div class="uk-width-1-4">
										ชื่อบัญชี  :
										<input type="text" name="bookModel.bookbank_name" >
									</div>
									<div class="uk-width-1-4">
										<button type="submit"  class="uk-button uk-button-success ">
										เพิ่มบัญชีธนาคาร
										</button>									
									</div>
								</div>
								<div class="uk-grid uk-form">

									<div class="uk-width-1-4">
										ธนาคาร :
										<select  class="uk-form  bank_id" name="bookModel.bank_id" >
													<%@include file="include/banktype-dd-option.jsp" %>
										</select>									
									</div>
									<div class="uk-width-1-4">										
										<h4 class="uk-width-1-1">เลือกสาขาที่ต้องการผูกกับบัญชี</h4>
												<s:if test="%{accountList.isEmpty()}">
													<div class="uk-width-1-1 uk-text-danger">
														ไม่มีสาขาที่ดำเนินการ
													</div>
												</s:if>
												<s:else>	
												<s:iterator value="accountList" status="acc">
												<s:if test="isCheck == 'nu'">
												<div class="uk-width-1-1 ">
													<label><input type="checkbox"  class="branchallclass" name="branchaccount" 
													value='<s:property value="branch_id" />' > <s:property value="branchName" /></label>
												</div>
												</s:if>																																
												</s:iterator>
												</s:else>
									</div>			
								</div>

			
							</div>
							<div class="uk-panel uk-panel-box">
                                <div class="uk-panel-header">
								    <div class="uk-panel-title uk-grid uk-grid-collapse ">
								    <div class="uk-width-2-10"><h3>
								    <i class="uk-icon-calendar"></i> ข้อมูลบัญชีธนาคาร </h3>
								    </div>
								    	<div class="uk-width-3-10 bor-rightAndleft uk-text-center">
								    		<s:iterator value="accountList" status="docbranch">
								    		<s:if test="1>#docbranch.index ">
								    		<s:property value="pre_name_th" /><s:property value="first_name_th" /> <s:property value="last_name_th" />
								    		</s:if>
								    		</s:iterator>
								    	</div>
								    </div>
								</div>
								<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-condensed uk-table-hover">
									<thead>
										<tr>
											<th class="uk-text-center">เลขบัญชี</th>
											<th class="uk-text-center">ชื่อบัญชี</th>
											<th class="uk-text-center">ธนาคาร</th>
											<th class="uk-text-center">สาขา</th>
											<th class="uk-text-center">จัดการ</th>
										</tr>
										
									</thead>
									<tbody>
										<s:if test="%{bookaccountlist.isEmpty()}">
												<tr>
												<th colspan="5" class="uk-text-center">ไม่มีข้อมูลบัญชีธนาคาร</th>
												</tr>
										</s:if>	
										<s:else>
										<s:iterator value="bookaccountlist">
										<tr>
											<th class="uk-text-center"><s:property value="bookbank_no" /></th>
											<th class="uk-text-center"><s:property value="bookbank_name" /></th>
											<th class="uk-text-center"><s:property value="bank_name_th" /></th>
											<s:if test="%{doctorModellist.isEmpty()}">
												<th class="uk-text-center">ไม่มีสาขาที่ผูกกับบัญชีนี้</th>
											</s:if>	
																					
											<s:else>
											<th class="uk-text-center">	
											<s:iterator value="doctorModellist" status="chkk">
												<s:if test="#chkk.count > 1  ">
													, 
												</s:if>
												<s:property value="account_branchName" />
											</s:iterator>
											</th>
											</s:else>
											
											<th class="uk-text-center">
												<a href="#update_salary"  onclick="update('<s:property value="docModel.DoctorID"/>'
												,'<s:property value="bookbank_no"/>','<s:property value="bookbank_name" />'
												,'<s:property value="bank_id"/>','<s:property value="bookbank_id"/>')"   
												class="uk-button uk-button-primary uk-button-small" data-uk-modal>
															<i class="uk-icon-pencil"></i> แก้ไข
														</a>
												<a href="#delete_branchMgr" onclick="deletebranchmgr('<s:property value="bookbank_id"/>')" 
												class="uk-button uk-button-danger uk-button-small"  data-uk-modal >
												<i class="uk-icon-eraser"></i> ลบ</a>		
											</th>			
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
					<div id="update_salary" class="uk-modal ">
						<form action="updoctorbookbank-<s:property value="docModel.DoctorID"/>" method="post"> 
						<div class="hidden"> <s:textfield name="docModel.DoctorID" /></div>
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
					    <div class="uk-modal-header"><i class="uk-icon-pencil"></i> แก้ไข</div>
				         	<div class="uk-modal-body">
								<div class="uk-grid uk-form">
								
									<div class="uk-width-1-2">
										เลขบัญชี  :
										<input type="text" id="numberacc" name="bookModel.bookbank_no" maxlength="10" >
									</div>
									<div class="uk-width-1-2">
										ชื่อบัญชี  :
										<input type="text" id="nameacc" name="bookModel.bookbank_name" >
									</div>
								</div>
								<div class="uk-grid uk-form">

									<div class="uk-width-1-2">
										ธนาคาร :
										<select  class="uk-form  bank_id" name="bookModel.bank_id" >
											<s:iterator value="banknamelist">
												<option class="book<s:property value="bank_id"/> bookall" value="<s:property value="bank_id"/>"><s:property value="bank_name_th"/></option>
											</s:iterator>
										</select>									
									</div>
									<div class="uk-width-1-2 ">										
										<h4 class="uk-width-1-1">เลือกสาขาที่ต้องการผูกกับบัญชี</h4>
												<s:if test="%{accountList.isEmpty()}">
													<div class="uk-width-1-1 uk-text-danger">
														ไม่มีสาขาที่ดำเนินการ
													</div>
												</s:if>
												<s:else>	
												<s:iterator value="accountList" status="acc">
												<s:if test="isCheck == 'nu'">
												<div class="uk-width-1-1 ">
													<label><input type="checkbox"   class="branchallclass" name="branchaccount1" 
													value='<s:property value="branch_id" />' > <s:property value="branchName" /></label>
												</div>
												</s:if>																																
												</s:iterator>
												</s:else>
												<div class="uk-width-1-1 allbranch">
												
												</div>
									</div>			
								</div>
				         		
				         	</div>
				         	<div class="uk-modal-footer uk-text-right">
				         	<input type="hidden" id="bookbankid" name="bookModel.bookbank_id">
			                    <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                     <button type="button"  id="upclose" class="uk-button uk-button-default uk-modal-close uk-button-danger">ยกเลิก</button>
                			</div>

					    </div>
					    </form>
					</div>
					<div id="delete_branchMgr" class="uk-modal ">
						<form action="delBookbank" method="post"> 
						<div class="hidden"> <s:textfield name="docModel.DoctorID" /></div>
					    <div class="uk-modal-dialog uk-modal-dialog-small uk-form" >
				         	<div class="uk-modal-body"><i class="uk-icon-exclamation-circle"></i> ต้องการยืนยันการลบหรือไม่</div>
				         	<div class="uk-modal-footer uk-text-right">
			                    
			                    <input type="hidden" id="Bookdel" name="bookModel.bookbank_id">
			                    <button type="submit" class="uk-button uk-button-default uk-button-success"> ยืนยัน</button>
			                    <button class="uk-button uk-button-default uk-modal-close uk-button-danger">ยกเลิก</button>
                			</div>

					    </div>
					    </form>
					</div>
			</div>
	</div>		
</body>
<script>
	$(document).ready(function () {
		$('#upclose').click(function () {
			$('.bookall').removeAttr('selected', 'selected');
		});

		
	});
	function update(docID,num,name,bankid,bookID) { 
		 $("#numberacc").val(num)
		 $("#nameacc").val(name);
		 $(".book"+bankid).attr('selected', 'selected');
		 $('#bookbankid').val(bookID);
		 var textvalue = '';
		 $.ajax({     
			    type: "POST",
			    url: "ajax/ajax-account-check.jsp", 
			    data: {bookID:bookID,docID:docID},
			    async:false, 
			    success: function(result){ 
			    	var obj = jQuery.parseJSON(result);
		        	for(var i = 0 ;  i < obj.length;i++){
		        		textvalue += '	<div class="uk-width-1-1 ">  '+
    								'		<label><input type="checkbox" class="branchallclass" name="branchaccount1" checked="checked" value="'+obj[i].branchID+'"> '+obj[i].branchName+'</label> '+
    								'	</div>  ';
		        		 
		        	}
		        	$(".allbranch").html(textvalue);  

			    }
			}); 
		
	};

function deletebranchmgr(bookid)
{
  $("#Bookdel").val(bookid);
};	

		
</script>		    
</html>