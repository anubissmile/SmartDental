<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.document.model.*" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:เอกสาร</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
				<div class="uk-grid"></div>
				<div class="uk-grid">
				    <%@include file="document-nav.jsp" %>
				    <div class="uk-width-7-10 uk-overflow-container">
				    	<div class="uk-panel uk-panel-box uk-width-medium-1-1">
			    			<div class="uk-panel-badge uk-badge uk-badge-primary">รายงาน</div>  
                            <div class="uk-panel-header">
							    <h3 class="uk-panel-title"><i class="uk-icon-print"></i> รายงาน
							    <% if(request.getAttribute("status_error") != null) {%>
									 <span class="red "><%=request.getAttribute("status_error").toString()%></span>
									<% } %>
									<% if(request.getAttribute("status_success") != null) {%>
									 <span class="uk-text-success"><%=request.getAttribute("status_success").toString()%></span>
									<% } %>
							    </h3>
							</div> 
							<form action="DocumentUpload" method="POST" enctype="multipart/form-data" class="uk-grid uk-form"> 
								<div class="uk-width-1-4">
									<p style="margin-bottom:5px;">ประเภทรายงานสรุปรายได้</p>
									<select name="modeDoc" class="uk-width-1-1">
										<option value="1">รายวัน</option>
										<option value="2">แบ่งตามทันตแพทย์</option> 
									</select>
								</div>
								<div class="uk-width-1-4">
									<p style="margin-bottom:5px;">วันที่เอกสาร</p>
									<input type="text" name="date_report" id="date_report" class=" uk-width-1-1" required>
								</div>
								<div class="uk-width-1-4">
									<p style="margin-bottom:5px;">พิมพ์รายงาน</p> 
									<button type="button" class="uk-button uk-button-primary print"><i class="uk-icon-print "></i> ตกลง</button>
								</div>
							</form>
			    		</div> 
			    		
			    		</div>
				    </div>
				</div>
			</div>
			
	<!-- MODAL ZONE -->
		<div class="uk-modal" id="reason-delete">
			<div class="uk-modal-dialog">
				<a class="uk-modal-close uk-close"></a>
				<form action="delete-file-by-user" method="post">
					<s:hidden type="hidden" value="" id="modal-doc-id" name="documentModel.document_id" />
					<div class="uk-modal-header">
						<h2>โปรดใส่เหตุผลในการลบ</h2>
					</div>
					<s:textfield type="text" value="" name="documentModel.reason" />
					<div class="uk-modal-footer uk-text-right">
						<button>ยืนยันการลบ</button>
					</div>
				</form>
		    </div>
		</div>	
	<!-- MODAL ZONE -->

		<script>
			$(document).ready(function(){
				/*Doc Delete*/
				$("td").on('click', '.doc-del', function(event) {
					event.preventDefault();
					$("#modal-doc-id").val($(this).data('doc-id'));
				});	
				/*Doc Delete*/

				$(".m-document").addClass( "uk-active" );
				$("#document-table").DataTable();
				var accordion = UIkit.accordion($('.uk-accordion'),{
					showfirst: false 
				});
				var defaultDate = new Date(); // = today
				$("#date_report").datepicker({
				    format: "dd-mm-yyyy",
			        clearBtn: true,
			        autoclose: true,
			        todayHighlight: true,
			        changeMonth: true,
			        changeYear: true,
			        showButtonPanel: true
			    }).datepicker('setDate', defaultDate);
			});
			$(".print").click(function(){
				 
				var modeDoc = $('select[name="modeDoc"] option:selected').val(); 
				var date_report = $('#date_report').val();
				
				if(modeDoc=='1'){
					window.open('report/report-tf.jsp?date_report='+date_report+   
							'' , '_blank', '');
				}else{
					window.open('report/report-df.jsp?date_report='+date_report+   
							'' , '_blank', '');
				} 
				 
			});
		</script>
	</body>
</html>