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
		    	
		    <a id="g" class="uk-accordion-title uk-width-1-1">ทั่วไป</a>
		    	
		    <a id="tmp" class="uk-accordion-title uk-width-1-1">แผนการรักษา</a>
		    	
		    <a id="tmh" class="uk-accordion-title uk-width-1-1">การรักษาคนไข้</a>
		    	
		    <a id="mh" class="uk-accordion-title uk-width-1-1">ประวัติทางการแพทย์</a>
		    	
		    <a id="fc" class="uk-accordion-title uk-width-1-1">ข้อมูลทางการเงิน</a>
		    	
		    <a id="f" class="uk-accordion-title uk-width-1-1">การอนุมัติสินเชื่อ</a>
		    	
		    <a id="m"  class="uk-accordion-title uk-width-1-1">จดหมาย</a>
		    
		</div>
		<script>
			$('#a').on('click',function(){
				window.location ='Document?dt=All';
			});
			$('#p').on('click',function(){
				window.location ='Document?dt=Patient';
			});
			$('#g').on('click',function(){
				window.location ='Document?dt=General';
			});
			$('#tmp').on('click',function(){
				window.location ='Document?dt=TreatMentPlan';
			});
			$('#tmh').on('click',function(){
				window.location ='Document?dt=TreatMentHistory';
			});
			$('#mh').on('click',function(){
				window.location ='Document?dt=MedicalHistory';
			});
			$('#fc').on('click',function(){
				window.location ='Document?dt=Financial';
			});
			$('#f').on('click',function(){
				window.location ='Document?dt=Finance';
			});
			$('#m').on('click',function(){
				window.location ='Document?dt=Mail';
			});
		</script>
	</div>
</div>