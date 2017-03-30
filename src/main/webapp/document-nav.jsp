<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="uk-width-3-10 uk-overflow-container">
	<div class="uk-panel uk-panel-box uk-width-medium-1-1">
		<div class="uk-panel-badge uk-badge uk-badge-primary">เอกสาร</div>  
		<div class="uk-panel-header">
		    <h3 class="uk-panel-title">เอกสาร <i class="uk-icon-file-pdf-o"></i> <i class="uk-icon-file-excel-o"></i> <i class="uk-icon-file-picture-o"></i></h3>
		</div>
		<div class="uk-accordion uk-grid uk-grid-collapse" > 
			<a id="a" class="uk-accordion-title uk-width-1-1">ทั้งหมด</a>
			
		    <a id="p" class="uk-accordion-title uk-width-1-1">ข้อมูลคนไข้</a>
		    	
		    <a id="personPicture" class="uk-accordion-title uk-width-1-1">ภาพถ่ายบุคคล</a>
		    	
		    <a id="tmp" class="uk-accordion-title uk-width-1-1">แผนการรักษา</a>
		    	
		    <a id="tmh" class="uk-accordion-title uk-width-1-1">การรักษาคนไข้</a>
		    	
		    <a id="xray" class="uk-accordion-title uk-width-1-1">ภาพถ่ายรังสี</a>
		    	
		    <a id="dv" class="uk-accordion-title uk-width-1-1">เอกสารการตรวจ</a>
		    
		</div>
		<script>
			$('#a').on('click',function(){
				window.location ='Document?dt=All';
			});
			$('#p').on('click',function(){
				window.location ='Document?dt=Patient';
			});
			$('#personPicture').on('click',function(){
				window.location ='Document?dt=personPicture';
			});
			$('#tmp').on('click',function(){
				window.location ='Document?dt=TreatMentPlan';
			});
			$('#tmh').on('click',function(){
				window.location ='Document?dt=TreatMentHistory';
			});
			$('#xray').on('click',function(){
				window.location ='Document?dt=Xray';
			});
			$('#dv').on('click',function(){
				window.location ='Document?dt=DocumentVerify';
			});
		</script>
	</div>
</div>