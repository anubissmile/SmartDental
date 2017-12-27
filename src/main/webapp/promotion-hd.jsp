<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<title>Smart Dental:โปรโมชั่น</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top-promotion.jsp" %>
 
				<div class="uk-grid uk-grid-collapse">
					<div class="uk-width-1-1 padding5 uk-form" >
					
						<div class="uk-grid uk-grid-collapse padding5 border-gray">
						 	<p class="uk-text-muted uk-width-1-1">ข้อมูลโปรโมชั่น <a href="promotion.jsp" class="uk-button uk-button-success" ><i class=" uk-icon-plus"></i> เพิ่มข้อมูลโปรโมชั่น</a></p>
						 	
						 	<div class="uk-width-1-10 uk-text-right"></div>
							<div class="uk-width-9-10 uk-text-right">   
								<span class="uk-text-bottom">ค้นหา - รหัสโปรโมชั่น : </span>
						        <input class="uk-form-small uk-width-1-10" type="text" > 
								<span class="uk-text-bottom">ชื่อโปรโมชั่น : </span>
						        <input class="uk-form-small uk-width-1-10" type="text" > 
						        <span class="uk-text-bottom">สถานะโปรโมชั่น: </span>
						        <input class="uk-form-small uk-width-1-10" type="text" >
						       	<a class="uk-button uk-button-success uk-button-small" href="#">ค้นหา</a>
							</div>
						 	
						 	<table class="uk-table uk-table uk-table-hover uk-table-condensed border-gray">
						 		<thead>
						 			<tr class="hd-table">
						 				<th>ประเภทโปรโมชั่น</th>
						 				<th>รหัสโปรโมชั่น</th> 
							            <th>ชื่อโปรโมชั่น</th> 
							            <th>คำอธิบาย</th>
							            <th>กำหนดการ  (วัน)</th> 
							            <th>จัดการ</th>
							        </tr>
						 		</thead>
						 		<tbody>
						 			<tr>
						 				<td>ส่วนลด</td>
						 				<td>LDC-P001</td> 
							            <td>WEEKDAY SPECIAL</td> 
							            <td>วันจันทร์  –  ศุกร์ ขูดหินปูน,อุดฟัน,ถอนฟัน และผ่าฟันคุด รับส่วนลด 20%</td>
							            <td><input type="checkbox" checked/> จันทร์ <input type="checkbox" checked/> อังคาร <input type="checkbox" checked/> พุธ 
										<input type="checkbox" checked/> พฤหัสบดี <input type="checkbox" checked/> ศุกร์ <input type="checkbox" checked/> เสาร์ <input type="checkbox" checked/> อาทิตย์</td> 
							            <td><a href="promotion.jsp" class="uk-button uk-button-primary uk-button-small"><i class=" uk-icon-eye"></i></a></td>
							        </tr>
							        <tr>
							        	<td>ซื้อของแล้วแถม</td>
						 				<td>LDC-P002</td> 
							            <td>ส่วนลดน้ำยาบ้วนปาก AFTERDENT </td> 
							            <td>ซื้อน้ำยาบ้วนปาก AFTERDENT   1 ขวด  ฟรี 1 ขวด</td> 
							            <td><input type="checkbox" checked/> จันทร์ <input type="checkbox"/> อังคาร <input type="checkbox" checked/> พุธ 
										<input type="checkbox"/> พฤหัสบดี <input type="checkbox" checked/> ศุกร์ <input type="checkbox"/> เสาร์ <input type="checkbox"/> อาทิตย์</td>
							            <td><a href="promotion.jsp" class="uk-button uk-button-primary uk-button-small"><i class=" uk-icon-eye"></i></a></td>
							        </tr>
						 		</tbody>
						 	</table>
						 	
						</div>
						</div>
					</div>
					
				</div> 
				
			</div>
			
			 
		<script>
		$(document).ready(function(){
		    
			$( ".m-setting" ).addClass( "uk-active" );
			
			$(".province").change(function(){
				var province = $(".province").val(); 
				if(province==0){
					$('.amphur').children('option:not(:first)').remove();
					$('.district').children('option:not(:first)').remove(); 
				}else{
					$('.amphur').children('option:not(:first)').remove();
					$('.district').children('option:not(:first)').remove(); 
					if(province==1){
						$(".amphur").append( 
								"<option value='1'>เขตบางรัก</option>"+
					            "<option value='2'>เขตดุสิต</option>");
					}			
			        
				}
		    });
			$(".amphur").change(function(){
				var amphur = $(".amphur").val(); 
				if(amphur==0){
					$('.district').children('option:not(:first)').remove(); 
				}else{
					$('.district').children('option:not(:first)').remove(); 
					if(amphur ==1){
						$(".district").append( 
								"<option value='1'>สีลม</option>"+
					            "<option value='2'>สุริยวงศ์</option>");
					}else if(amphur ==2){
						$(".district").append( 
								"<option value='3'>ถนนนครไชยศรี</option>"+
					            "<option value='4'>สี่แยกมหานาค</option>");
					}
						
			        
				}
		    });
			$(".district").change(function(){
				var district = $(".district").val(); 
				if(district==1 || district==2){
					$("#postid").val(10500);
				}else if(district==3 || district==4){
					$("#postid").val(10300);
				}else{
					$("#postid").val("");
				}
		    });
		    $(".btn-reset").click(function(){
		    	$('.table-components tbody > tr:not(:first-child)').remove();
		    	$('.table-components-product tbody > tr:not(:first-child)').remove();
		    	$('.table-components-medicine tbody > tr:not(:first-child)').remove();
		    	});
		    
		    $('.table-components .add-elements').on('click', function(){ 
				var clone = $(".table-components tbody tr:first-child");
				clone.find('.btn').removeClass('add-elements uk-button-success').addClass('delete-elements uk-button-danger').html('<i class="uk-icon-times"></i>');
				clone.find('td:first-child').html($('.table-components tbody tr').length + 1); 
				clone.clone().appendTo('.table-components tbody');
				clone.find('td:first-child').html(1);
				clone.find('.btn').removeClass('delete-elements uk-button-danger').addClass('add-elements uk-button-success').html('<i class="uk-icon-plus"></i>');
				
				$('.table-components tbody tr:last-child td:nth-child(2)').on('change', function(event) {
					$(this).closest("tr").find('td:nth-child(3) input').focus();
				});
			});
		    
		    $('.table-components').on( "click", ".delete-elements", function() {
				$(this).closest("tr").remove();
				var n = 0;
				$('.table-components tbody tr').each(function(){
					n++;
					$(this).find('td:first-child').html(n);
				});
			});
		    
		    $('.table-components-product .add-elements').on('click', function(){ 
				var clone = $(".table-components-product tbody tr:first-child");
				clone.find('.btnproduct').removeClass('add-elements uk-button-success').addClass('delete-elements uk-button-danger').html('<i class="uk-icon-times"></i>');
				clone.find('td:first-child').html($('.table-components-product tbody tr').length + 1); 
				clone.clone().appendTo('.table-components-product tbody');
				clone.find('td:first-child').html(1);
				clone.find('.btnproduct').removeClass('delete-elements uk-button-danger').addClass('add-elements uk-button-success').html('<i class="uk-icon-plus"></i>');
				
				$('.table-components-product tbody tr:last-child td:nth-child(2)').on('change', function(event) {
					$(this).closest("tr").find('td:nth-child(3) input').focus();
				});
			});
		    
		    $('.table-components-product').on( "click", ".delete-elements", function() {
				$(this).closest("tr").remove();
				var n = 0;
				$('.table-components-product tbody tr').each(function(){
					n++;
					$(this).find('td:first-child').html(n);
				});
			});
		    
		    $('.table-components-medicine .add-elements').on('click', function(){ 
				var clone = $(".table-components-medicine tbody tr:first-child");
				clone.find('.btnmedicine').removeClass('add-elements uk-button-success').addClass('delete-elements uk-button-danger').html('<i class="uk-icon-times"></i>');
				clone.find('td:first-child').html($('.table-components-medicine tbody tr').length + 1); 
				clone.clone().appendTo('.table-components-medicine tbody');
				clone.find('td:first-child').html(1);
				clone.find('.btnmedicine').removeClass('delete-elements uk-button-danger').addClass('add-elements uk-button-success').html('<i class="uk-icon-plus"></i>');
				
				$('.table-components-medicine tbody tr:last-child td:nth-child(2)').on('change', function(event) {
					$(this).closest("tr").find('td:nth-child(3) input').focus();
				});
			});
		    
		    $('.table-components-medicine').on( "click", ".delete-elements", function() {
				$(this).closest("tr").remove();
				var n = 0;
				$('.table-components-medicine tbody tr').each(function(){
					n++;
					$(this).find('td:first-child').html(n);
				});
			});
		    
		    $('.table-components tbody').on('change', 'input', function() {
		    	var tr = $(this).closest("tr");
		    	
		    	var val1 = tr.find('td:nth-child(2) input').val().trim();
		    	var val2 = tr.find('td:nth-child(3) input').val().trim();
		    	
			});   
		       
		    
		    
		    $("#btnr").click(function(){
		    	$(".rl").first().clone().appendTo(".rs"); 
		    });
		    
		    
		    $(".btnrs").click(function(){ 
		    	 
		    	 $(this).parents(".rl").remove();
		    });
		    
		}); 
		
		</script>		
	</body>
</html>