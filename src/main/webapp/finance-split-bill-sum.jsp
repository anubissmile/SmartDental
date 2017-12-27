<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*" %>
<%@ page import="com.smict.treatment.data.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:การเงิน</title>
		<link href="css/uikit.gradient.css"rel="stylesheet"/>
		<link href="css/style.css"rel="stylesheet">
		<link href="css/jquery.dataTables.min.css"rel="stylesheet">
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div  class="uk-grid ">
			<div class="uk-width-1-1 uk-text-center">
				<h2  style="margin-top: 10px;">ออกใบเสร็จแบบกำหนดจำนวนเงิน</h2>
			</div>
			<div  class="uk-width-1-2">
				<blockquote>
					<h4 class="uk-text-primary" style="margin-top: 10px;">เลือกประเภทใบเสร็จ</h4>
					<label><input type="radio" name="doc_type1" checked="checked">ใบเสร็จ</label>
	           		<label><input type="radio" name="doc_type1">ใบแจ้งหนี้</label>
	           		<label><input type="radio" name="doc_type1">แบบบริษัท</label>
				</blockquote>
			</div>
			<div  class="uk-width-1-2">
				<blockquote>
					<h4 class="uk-text-primary" style="margin-top: 10px;">เลือกภาษา</h4>
                    <label><input type="radio" name="doc_lang" checked="checked">ไทย</label>
                    <label><input type="radio" name="doc_lang">English</label>
				</blockquote>
			</div>
			
		</div>
        
        <div  class="uk-grid  uk-text-center">	
			<div class="uk-width-1-2">
				<h5 class="hd-text uk-text-primary margin5">ราคาค่าใช้จ่าย</h5>
				<ul class="uk-form uk-list chanel-pay padding5 border-gray uk-text-right">
		            <li class="uk-grid"><div class="uk-width-1-3">ราคารวม </div>
						<input type="text" dir="rtl" size="20" readonly="readonly" placeholder="0.00" class="uk-form-small">
					</li>
	             </ul>
			</div>
			<div class="uk-width-1-2">
				<h5 class="hd-text uk-text-primary margin5">วิธีการชำระเงิน</h5>
				<ul class="uk-form uk-list chanel-pay padding5 border-gray">
					<li class="uk-grid"><label class="uk-width-1-3  uk-text-left"><input type="checkbox"> เงินสด </label>
						<input type="text" dir="rtl" size="20" placeholder="0.00" class="uk-form uk-width-1-3 uk-form-small">
					</li>
					<li class="uk-grid"><label class="uk-width-1-3  uk-text-left">
						<input type="checkbox"> เครดิตการ์ด </label>
						<input type="text" dir="rtl" size="20" placeholder="0.00" class="uk-form uk-width-1-3 uk-form-small">
						<select class="uk-width-1-3 uk-form-small">
							<option>กรุณาเลือกข้อมูลบัตรเครดิต</option>
							<option>Visa Master Card</option>
						</select>
					</li>
					<li class="uk-grid"><label class="uk-width-1-3 uk-text-left"><input type="checkbox"> LinePay</label>
						<input type="text" dir="rtl" size="20" placeholder="0.00" class="uk-form uk-width-1-3 uk-form-small">
					</li>
					<li class="uk-grid"><label class="uk-width-1-3 uk-text-left"><input type="checkbox"> เงินฝาก </label>
						<input type="text" dir="rtl" size="20" placeholder="0.00" class="uk-form uk-width-1-3 uk-form-small">
					</li>
				</ul>
			</div>
		</div>   	 
        <div  class=" uk-text-center " style="margin:10px auto">
        	<button class="uk-button uk-button-success"><i class="uk-icon-print"></i> พิมพ์ใบเสร็จ</button>
        </div>   	 
		<script src="js/jquery-2.2.4.min.js"></script>
		<script src="js/uikit.min.js"></script>
		<script src="js/jquery.dataTables.min.js"></script> 
		<script>
			$('#treTable').DataTable({
		        "ordering": false,
		        "searching": false,
		        "lengthChange": false,
		        "paging": false,
		        "info": false
		    });
			$('#proTable').DataTable({
		        "ordering": false,
		        "searching": false,
		        "lengthChange": false,
		        "paging": false,
		        "info": false
		    });
			$(document).on('click','tr',function(){
				var trthis = $(this).parent().find('input[type="checkbox"]');
				trthis.prop('checked',!trthis.prop("checked"));
			});
		</script>
	</body>
</html>