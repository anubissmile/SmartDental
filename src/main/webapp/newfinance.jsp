<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smict.all.model.*" %>
<%@ page import="com.smict.treatment.data.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% 	DecimalFormat df = new DecimalFormat("#,###,##0.## ฿");
	DecimalFormat df1 = new DecimalFormat("#,###,##0.## เม็ด");
	DecimalFormat df2 = new DecimalFormat("#,###,##0.##");
%>
<!DOCTYPE html>
<html>
	<head>
		<title>Smart Dental:การเงิน</title>
		<link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
	</head> 
	<body>
		<div class="uk-text-center  preload ">
		<span><i class="uk-icon-spin uk-icon-large uk-icon-spinner "></i> กรุณารอสักครู่</span>
		</div>
		<div class="uk-grid uk-grid-collapse">
			<div class="uk-width-1-10">
				<%@include file="nav-right.jsp" %>
			</div>
			<div class="uk-width-9-10">
				<%@include file="nav-top.jsp" %>
			
				<div class="uk-grid uk-grid-collapse uk-form">
				    <div class="uk-width-8-10 ">
				    	<div class=" uk-panel-box">
				    		<h3 class="hd-text uk-text-primary margin5">รายการค่าใช้จ่าย</h3>
				    	</div>
				    	<div class=" uk-panel-box">
				    	<div class="uk-overflow-container">
				    	<h4 class="hd-text uk-text-primary margin5">รายการรักษา </h4>
				    	<div class="new-table-scroll">
						<table class="uk-table  uk-table-hover uk-table-condensed uk-width-1-1 border-gray " >
						    <thead>
						        <tr class="hd-table">
						            <th class="uk-text-center uk-width-2-10" rowspan="2"><p>รายการรักษา</p></th>
						            <th class="uk-text-center uk-width-2-10" colspan="2">ติดตามผล</th>
						            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>ค่ารักษา</p></th> 														             						            
						            <th class="uk-text-center uk-width-4-10" colspan="3">ส่วนลด</th>
						            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนเงินทั้งหมด</p></th>	
						        </tr>
						        <tr class="hd-table">						        	
						        	<th class="uk-text-center">HomeCall</th>
						            <th class="uk-text-center">ReCall</th>
						            <th class="uk-text-center">Promotion</th> 
						        	<th class="uk-text-center ">แพทย์</th>
						            <th class="uk-text-center ">ร้าน</th>
						        </tr>
						    </thead> 
						    <tbody class="showalltreatment">																						
								
						         
						    </tbody>
						</table>
						</div><hr>
						<h4 class="hd-text uk-text-primary margin5">รายการยา
						<a class="uk-button uk-button-primary uk-button-small" id="medicinelist" data-uk-modal>
								<i class="uk-icon-cart-plus"></i> เพิ่มรายการยา
							</a>
						 </h4>
						<div class="new-table-scroll">
						<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray  uk-width-1-1" id="medicineTable">
						    <thead>
						        <tr class="hd-table"> 
						            <th class="uk-text-center uk-width-2-10" rowspan="2"><p>ชื่อยา</p></th>
						            <th class="uk-text-center uk-width-2-10" colspan="2">จำนวนยา</th> 
						            <th class="uk-text-center uk-width-2-10" colspan="2">ราคายา</th>
						            <th class="uk-text-center uk-width-2-10" colspan="2">ส่วนลด</th>
						            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนเงินทั้งหมด</p></th>
						            <th class="uk-text-center uk-width-1-10" rowspan="2"></th>
						        </tr>
						        <tr class="hd-table">
						        	<th class="uk-text-center">ฟรี</th>
						            <th class="uk-text-center">จ่าย</th>
						            <th class="uk-text-center">ต่อหน่วย</th>
						            <th class="uk-text-center">รวมทั้งหมด</th>
						            <th class="uk-text-center">Promotion</th>
						            <th class="uk-text-center">ร้าน</th>
						        </tr>
						    </thead> 						    
						    <tbody class="showallmedicine ">							
								
						    </tbody>
						</table>
						</div>
						<hr>
						<h4 class="hd-text uk-text-primary margin5">สินค้าอื่นๆ
							<a class="uk-button uk-button-primary uk-button-small"data-uk-modal id="productlist">
								<i class="uk-icon-cart-plus"></i> เพิ่มสินค้า
							</a>
						</h4>
						<div class="new-table-scroll">
						<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed uk-width-1-1 border-gray " id="productTable">
						    <thead>
						        <tr class="hd-table"> 
						            <th class="uk-text-center  uk-width-3-10"  rowspan="2"><p>ชื่อสินค้า</p></th>
						            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวน</p></th>
						            <th class="uk-text-center uk-width-2-10" colspan="2">ราคาสินค้า</th>
						            <th class="uk-text-center uk-width-2-10" colspan="2">ส่วนลด</th> 
						            <th class="uk-text-center uk-width-1-10" rowspan="2"><p>จำนวนเงินทั้งหมด</p></th>
						             <th class="uk-text-center  uk-width-1-10" rowspan="2"></th>
						        </tr>
						        <tr class="hd-table">
						            <th class="uk-text-center">ต่อหน่วย</th>
						            <th class="uk-text-center">รวมทั้งหมด</th>
						            <th class="uk-text-center">Promotion</th>
						            <th class="uk-text-center">ร้าน</th>
						        </tr>
						    </thead> 
						    <tbody class="showpro">

						    </tbody>
						</table>
						</div><hr>
						<h4 class="hd-text uk-text-primary margin5">รายการของแถม </h4>
						<div class="new-table-scroll">
						<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray  uk-width-1-1">
						    <thead>
						        <tr class="hd-table"> 
						            <th class="uk-text-center  uk-width-3-5" >ชื่อ</th>
						            <th class="uk-text-center  uk-width-1-5" >ประเภท</th>
						            <th class="uk-text-center  uk-width-1-5" >จำนวน</th> 
						        </tr>
						    </thead> 
						    <tbody class="freeProduct">
							
							
						    </tbody>
						</table>
						</div>
						</div>
						</div>
					</div> 
					
					<div class="uk-width-2-10 uk-overflow-container">
						<div class=" uk-panel-box">
							<h3 class="hd-text uk-text-primary margin5">รายละเอียดการชำระเงิน</h3>
						</div>  
                             <div class="uk-panel uk-panel-box uk-panel-box">
                             	<span class="red">รายการค้างชำระ  : 1,500 บาท</span> <a href="#remain" class="uk-button uk-button-danger" data-uk-modal>จ่ายค้างชำระ</a>
								<h3 class="hd-text uk-text-primary margin5">ประเภทสิทธิประโยชน์ </h3>
								<select  class="uk-from uk-width-1-1" 
								id="selectallprivilege" size="3">
	                             			<option value="1" selected="selected">โปรโมชั่น</option>
	                             			<option value="2">Gift Card</option>
	                             			<option value="3">Gift Voucher</option>
	                             </select>	                             		
								<div class="border-gray padding5 promo">
                             	<div class="uk-grid">
                             		<div class="uk-width-1-1">
	                             		<h5 class="hd-text uk-text-primary margin5">โปรโมชั่น</h5>
	                             		<select  class="uk-from uk-width-1-1" size="5" id="promosel">
                             			                             				
	                             				<s:iterator value="finanModel.promoList" status="finan">
	                             				<s:if test="finanModel.promoList[#finan.index] != null">
		                             			<option value="<s:property value="promotion_id" />"><s:property value="name" /></option>
	                             				</s:if>	 			                             				
		                             			</s:iterator>
		                             			<s:else>
		                             			<option disabled="disabled" >ไม่มีโปรโมชั่น</option>	
	                             				</s:else>
	                             		</select>
                             		</div>
                             		<div class="uk-width-1-1 hidden">
	                             		<div class="uk-form">
	                             			<h5 class="hd-text uk-text-primary margin5">ประเภทสมาชิก</h5>
											<select  class="uk-from uk-width-1-1" size="5" id="showContype">
		                             			<option selected="selected" value="<s:property value='finanModel.contypeModel.sub_contact_id' />">
		                             			<s:property value='finanModel.contypeModel.sub_contact_name' />
		                             			</option>
	                             			</select>
	                             		</div>
                             		</div>
                             		<div class="uk-width-1-1">
                             			<p><span class="uk-text-primary">คำอธิบายโปรโมชั่น</span> 
                             			<a href="#ShowpromotionDetail" id="prodetailexpan" data-uk-modal class="uk-button-primary uk-button">แสดงคำอธิบาย</a></p>
                             		</div>

                             	</div>	
                             		
                             	</div>
                             	<div class="border-gray padding5 giftcard hidden">
                             	<div class="uk-grid">
                             		<div class="uk-width-1-1">
	                             		<h5 class="hd-text uk-text-primary margin5">Gift Card</h5>
	                             		<h5 class="hd-text uk-text-primary margin5"> หมายเลขบัตร</h5>
	                             		<input type="text" readonly="readonly" class="uk-from uk-width-1-1  " id="gcardID" value="" />
	                             		<h5 class="hd-text uk-text-primary margin5"> จำนวนเงินในบัตร</h5>
	                             		<input type="text" readonly="readonly" class="uk-from uk-width-1-1 numeric uk-text-right" id="giftamount" value="0" />
	                             		<a class="uk-button uk-button-primary uk-button-small"data-uk-modal id="checkGc">ตรวจสอบจำนวนเงินในบัตร</a>
                             		</div>
                             	</div>	                             		
                             	</div>
                             	<div class="border-gray padding5 giftvoucher hidden">
                             	<div class="uk-grid">
                             		<div class="uk-width-1-1">
	                             		<h5 class="hd-text uk-text-primary margin5">Gift Voucher</h5>
	                             		<h5 class="hd-text uk-text-primary margin5"> หมายเลขบัตร</h5>
	                             		<input type="text" readonly="readonly" class="uk-from uk-width-1-1  " id="giftvocID" value="" />
	                             		<h5 class="hd-text uk-text-primary margin5"> จำนวนเงินในบัตร</h5>
	                             		<input type="text" readonly="readonly" class="uk-from uk-width-1-1 numeric uk-text-right" id="gvamount" value="0" />
	                             		<a class="uk-button uk-button-primary uk-button-small"data-uk-modal id="checkGv">ตรวจสอบสิทธิประโยชน์ในบัตร</a>
                             		</div>
                             	</div>	                             		
                             	</div>
								<div class=" padding5">
<!-- 	                             	<div class="uk-grid">
	                             		<div class="uk-width-1-1">
	                             			<h5 class="hd-text uk-text-primary margin5">ประกันสังคม</h5>
		                             		<label><input type="checkbox" name="social" id="social">ประกันสังคม</label>
		                             		<input type="text" id="tresst" name="doc_type">
		                             		<div><a id="cantuse_social" class="red"> การแจ้งเตือนถ้าไม่สามารถใช้ประกันสังคมได้</a></div>        		
	                             		</div>
                             	</div> -->
                             	<h5 class="hd-text uk-text-primary margin5">ราคาค่าใช้จ่าย</h5>
                             	<ul class="uk-form uk-list chanel-pay padding5 border-gray ">
		                            <li>ราคารวม</li>
		 							<li><input type="text" size="20"  readonly="readonly" id="amounttotal" 
		 								name="amounttotal" placeholder="0" class="uk-form-small uk-width-1-1 uk-text-right numeric"
		 								value=''></li>
		                            <li>ส่วนลด  </li>
		                            <li><input type="text" size="20" readonly="readonly" id="discount" 
		 								name="discount" placeholder="0" class="uk-form-small uk-width-1-1 uk-text-right numeric"
		 								value=''></li>
		                            <li> สุทธิ </li>
		                            <li><input type="text" size="20" readonly="readonly" id="net" 
		 								name="net" placeholder="0" class="uk-form-small uk-width-1-1 uk-text-right numeric"
		 								value=''></li>
		                        </ul>
		                        <h5 class="hd-text uk-text-primary margin5">วิธีการชำระเงิน</h5>
                             	<ul class="uk-form uk-list chanel-pay padding5 border-gray">
		                            <li class="uk-grid"><label class="uk-width-1-1"><input type="checkbox" value="0" class="tik"> เงินสด </label></li>
		                            <li>
		                            <input type="text" id="money" name="money" size="20" placeholder="0" disabled="disabled" class="uk-form uk-width-1-1 numeric uk-text-right amAll">
		                            </li>
		                            <li class="uk-grid"><label class="">
		                            	<input type="checkbox" value="1" class="tik"> เครดิตการ์ด </label>    	
		 								
		 							</li>
		 							<li><input type="text" id="credit_card" name="credit_card" size="20" placeholder="0" disabled="disabled" class="uk-form numeric uk-width-1-1 uk-text-right amAll">
		 								<select name="chose_credit_card" class="" disabled="disabled">
		 									<option>กรุณาเลือกข้อมูลบัตรเครดิต</option>
		 									<option>Visa Master Card</option>
		 								</select></li>
		                            <li class="uk-grid"><label class="uk-width-1-1"><input type="checkbox" name="tik" value="2" class="tik"> LinePay</label>
		 								
		 							</li>
		 							<li><input type="text" id="line_pay" name="line_pay" size="20" placeholder="0" disabled="disabled" class="uk-form numeric uk-width-1-1 uk-text-right amAll"></li>
		                            <li class="uk-grid"><label class="uk-width-1-1"><input type="checkbox" name="tik" value="3" class="tik"> เงินฝาก </label>
		 								
		 							</li>
		 							<li><input type="text" id="deposit" name="deposit" size="20" placeholder="0" disabled="disabled" class="uk-form numeric uk-width-1-1 uk-text-right amAll"></li>
		 							<li class="uk-grid">
		 							<label class="uk-width-1-1"><input type="checkbox" name="tik" value="4" class="tik"> Gift Card</label>	
		 							</li>
		 							<li><input type="text" id="giftcard" name="giftcard" size="20" placeholder="0" disabled="disabled" class="uk-form numeric uk-width-1-1 uk-text-right gall"></li>
		 							<li class="uk-grid">
		 							<label class="uk-width-1-1"><input type="checkbox" name="tik" value="5" class="tik"> Gift Voucher</label>	
		 							</li>
		 							<li><input type="text" id="giftv" name="giftv" size="20" placeholder="0" disabled="disabled" class="uk-form numeric uk-width-1-1 uk-text-right gall"></li>
		 							<li class="uk-grid">
		 							<label class="uk-width-1-1"><input type="checkbox" name="tik" value="6" class="tik"> ประกันสังคม</label>	
		 							</li>
		 							<li><input type="text" id="sso" name="sso" size="20" placeholder="0" disabled="disabled" class="uk-form numeric uk-width-1-1 uk-text-right amAll"></li>
		                        </ul>
    							<ul class="uk-form uk-list chanel-pay padding5 border-gray">
    								<li> ยอดเงินที่ชำระ </li>
    								<li><input type="text" size="20"readonly="readonly" id="amount_paid" 
    								name="amount_paid" placeholder="0" class="uk-form-small numeric uk-width-1-1 uk-text-right"></li>
		                            <li>ค้างชำระ </li>
		                            <li><input type="text" size="20" readonly="readonly" id="owe" 
		                            name="owe" placeholder="0" class="uk-form-small uk-width-1-1 numeric uk-text-right"></li>
    							</ul>
                             	
		                        <button type="submit" class="uk-button uk-button-success" onclick="printReceipt()"><i class="uk-icon-print"></i> พิมพ์ใบเสร็จ</button>
		                        <a href="finance-split-bill.jsp" class="uk-button uk-button-primary"data-lightbox-type="iframe" data-uk-lightbox><i class="uk-icon-copy"></i>  แยกใบเสร็จ</a>
		                        <button type="submit" class="uk-button uk-button-danger"><i class="uk-icon-history"></i> ประวัติการจ่ายเงิน</button>
                             </div>
                      
                        
					</div> 
					   
				</div>

					<div id="medicineModal" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-meh-o"></i> ยา</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " id="tablemedicine">
									    <thead>
									        <tr class="hd-table"> 
									            <th class="uk-text-center">เลือก</th> 
									            <th class="uk-text-center">ชื่อ</th>
									            <th class="uk-text-center">ราคา</th>
									            <th class="uk-text-center">จำนวน</th> 
									        </tr>
									    </thead> 
									    <tbody class="medibodyModal">
											
										</tbody>
									</table>
									</div>
					         	 
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" name="btn_submit_be_allergic" id="btn_submit_be_allergic">ตกลง</button>
					         </div>
					    </div>
					</div>
					<div id="proModal" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-meh-o"></i> สินค้า</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
									<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray " id="tableproduct">
									    <thead>
									        <tr class="hd-table"> 
									            <th class="uk-text-center">เลือก</th> 
									            <th class="uk-text-center">ชื่อ</th>
									            <th class="uk-text-center">ราคา</th>
									            <th class="uk-text-center">จำนวน</th> 
									        </tr>
									    </thead> 
									    <tbody class="productbodyModal">
									    		
										</tbody>
									</table>
									</div>
					         	
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" name="btn_submit_pro" id="btn_submit_pro">ตกลง</button>
					         </div>
					    </div>
					</div>
					<div id="disdocModel" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-money"></i> ส่วนลดแพทย์</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
					         	<div class="uk-grid uk-grid-collapse">
					         		<div class="uk-width-1-2">
					         			<input type="radio" name="disalltypedoc" value="1" checked="checked" /> ลดบาท
					         		</div>
					         		<div class="uk-width-1-2">
					         			<input type="radio" name="disalltypedoc"  value="2"  /> ลดเปอร์เซ็น
					         		</div>
					         	</div>
					         	<div class="uk-grid uk-grid-collapse  ">
					         		<div class="uk-width-1-2">
					         			<input type="text"class="uk-text-right numeric clear dbaht" data-dcb="3" value="0" id="disbaht" />				         			
					         		</div>
					         		<div class="uk-width-1-2">
					         			<input type="text"  class="uk-text-right numeric clear dper"  data-dcb="1" value="0" maxlength="3" id="disper"  />				         			
					         		</div>
					         	</div>
								</div>         	
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" value="" id="btn_dis">ตกลง</button>
					         </div>
					    </div>
					</div>
					<div id="disbranchModel" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-money"></i> ส่วนลดร้าน</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
					         	<div class="uk-grid uk-grid-collapse">
					         		<div class="uk-width-1-2">
					         			<input type="radio" name="disalltypebranch" value="1" checked="checked" /> ลดบาท
					         		</div>
					         		<div class="uk-width-1-2">
					         			<input type="radio" name="disalltypebranch"  value="2"  /> ลดเปอร์เซ็น
					         		</div>
					         	</div>
					         	<div class="uk-grid uk-grid-collapse  ">
					         		<div class="uk-width-1-2">
					         			<input type="text"class="uk-text-right numeric clear dbaht" data-dcb="4" value="0" id="disbahtbranch" />				         			
					         		</div>
					         		<div class="uk-width-1-2">
					         			<input type="text"  class="uk-text-right numeric clear dper" value="0" data-dcb="2" maxlength="3" id="disperbranch"  />				         			
					         		</div>
					         	</div>
								</div>         	
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" value="" id="btn_disbranch">ตกลง</button>
					         </div>
					    </div>
					</div>
					<div id="disbranchMedicine" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-money"></i> ส่วนลดร้าน</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
					         	<div class="uk-grid uk-grid-collapse">
					         		<div class="uk-width-1-2">
					         			<input type="radio" name="disalltypebranchmedicine" value="1" checked="checked" /> ลดบาท
					         		</div>
					         		<div class="uk-width-1-2">
					         			<input type="radio" name="disalltypebranchmedicine"  value="2"  /> ลดเปอร์เซ็น
					         		</div>
					         	</div>
					         	<div class="uk-grid uk-grid-collapse  ">
					         		<div class="uk-width-1-2">
					         			<input type="text"class="uk-text-right numeric clear dbaht" data-dcb="7" value="0" id="disbahtbranchmedicine" />				         			
					         		</div>
					         		<div class="uk-width-1-2">
					         			<input type="text"  class="uk-text-right numeric clear dper" value="0" data-dcb="5" maxlength="3" id="disperbranchmedicine"  />				         			
					         		</div>
					         	</div>
								</div>         	
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" value="" id="btn_disbranchmedicine">ตกลง</button>
					         </div>
					    </div>
					</div>
					<div id="disbranchProduct" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-money"></i> ส่วนลดร้าน</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
					         	<div class="uk-grid uk-grid-collapse">
					         		<div class="uk-width-1-2">
					         			<input type="radio" name="disalltypebranchproduct" value="1" checked="checked" /> ลดบาท
					         		</div>
					         		<div class="uk-width-1-2">
					         			<input type="radio" name="disalltypebranchproduct"  value="2"  /> ลดเปอร์เซ็น
					         		</div>
					         	</div>
					         	<div class="uk-grid uk-grid-collapse  ">
					         		<div class="uk-width-1-2">
					         			<input type="text"class="uk-text-right numeric clear dbaht" data-dcb="8" value="0" id="disbahtbranchproduct" />				         			
					         		</div>
					         		<div class="uk-width-1-2">
					         			<input type="text"  class="uk-text-right numeric clear dper" value="0" data-dcb="6" maxlength="3" id="disperbranchproduct"  />				         			
					         		</div>
					         	</div>
								</div>         	
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" value="" id="btn_disbranchproduct">ตกลง</button>
					         </div>
					    </div>
					</div>
					
					
					<div id="checkgiftcard" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-money"></i> Gift Card</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
					         	<div class="uk-grid uk-grid-collapse">
					         		<div class="uk-width-1-1">
					         			รหัสGift Card :<input type="text"class="uk-width-1-2"  id="giftcardnumber" />	
					         		</div>
					         	</div>
								</div>         	
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success"  id="btn_checkGiftCard">ตกลง</button>
					         </div>
					    </div>
					</div>
						
					<div id="checkgv" class="uk-modal ">
					    <div class="uk-modal-dialog uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-money"></i> Gift voucher</div>
					         	<div class="uk-width-1-1 uk-overflow-container">
					         	<div class="uk-grid uk-grid-collapse">
					         		<div class="uk-width-1-1">
					         			รหัสGift voucher :<input type="text"class="uk-width-1-2"  id="giftvnumber" />	
					         		</div>
					         	</div>
								</div>         	
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success"  id="btn_checkGiftv">ตกลง</button>
					         </div>
					    </div>
					</div>					

					<div id="ShowpromotionDetail" class="uk-modal ">
					    <div class="uk-modal-dialog uk-modal-dialog-large uk-form " >
					        <a class="uk-modal-close uk-close"></a>
					         <div class="uk-modal-header"><i class="uk-icon-meh-o"></i> รายละเอียดโปรโมชั่น</div>
						         <div class="uk-width-1-1 uk-overflow-container">
						         	<div class="uk-width-1-1 uk-margin-medium-bottom ">
					 					<ul class="uk-tab tab-ac"  data-uk-switcher="{
					 							connect:'#Gift-active',
					 							animation: 'fade'
					 						}">
										    <!-- <li class="uk-active"><a href="#">กรุณาเลือก</a></li> -->
										    <s:iterator value="finanModel.promoList" status="finan">
	                             				<s:if test="finanModel.promoList[#finan.index] != null">
		                             			<li><a href="#" class="promotionid" data-proid="<s:property value="promotion_id" />"><s:property value="name" /></a></li>
	                             				</s:if>	 			                             				
		                             			</s:iterator>
		                             			<s:else>
		                             			<li>ไม่มีโปรโมชั่น</li>	
	                             				</s:else>											
										    
										</ul>
					 				</div>
					 				<ul class="uk-width-1-1 uk-switcher" id="Gift-active">  

									 	<s:iterator value="finanModel.promoList" status="finan">
	                             		<s:if test="finanModel.promoList[#finan.index] != null">
	                             		<li>
									 	<table class="uk-table uk-table-hover uk-table-striped uk-table-condensed border-gray uk-overflow-container" >
											    <thead>
											        <tr class="hd-table"> 
											            <th class="uk-text-center">ชื่อ</th>
											            <th class="uk-text-center">รายการ</th>
											            <th class="uk-text-center">ประเภทรายการ</th> 
											            <th class="uk-text-center">จำนวน</th>
											            <th class="uk-text-center">จำนวนเงิน</th>
											            <th class="uk-text-center">ประเภทส่วนลด</th> 
											        </tr>
											    </thead> 
											    <tbody class="prodetail<s:property value="promotion_id" />">
													
												</tbody>
										</table>
										</li>
										</s:if>	 			                             				
		                             	</s:iterator>
		                             	<s:else>
		                             	<li>ไม่มีโปรโมชั่น</li>	
	                             		</s:else>	
	
									</ul>
						         </div>
					         <div class="uk-modal-footer uk-text-right">
					         	<button class="uk-modal-close uk-button uk-button-success" name="btn_submit_be_allergic" id="btn_submit_be_allergic">ตกลง</button>
					         </div>
					    </div>
					</div>					
									
			</div>
		</div>
		<script src="js/autoNumeric.min.js"></script>
		<script src="js/components/lightbox.js"></script>	
		<script>
			
		function readtotalall() {

			let net = 0;
			let i = 0;
			let all=0;
			$('input[name=eveyamount]').each(function () {
				if(i == 0){
					net = parseFloat($(this).val())					
					all = net
				}else{					
					net =  (parseFloat(net) + parseFloat($(this).val())).toFixed(2)
					all = net
				}
				i++
			})
			productOBJ.finaldiscount = (parseFloat(productOBJ.sumtotal) - parseFloat(all)).toFixed(2)
			productOBJ.finalnet =  parseFloat(net).toFixed(2)
			$("#amounttotal").val(productOBJ.sumtotal)
			$("#discount").val(productOBJ.finaldiscount)
			$("#net").val(productOBJ.finalnet)

		}

		$(document).ready(function(){			
				
				 window.productOBJ = {
						    "treatment": [],
						    "medicine": [],
						    "product": [],
						    "promotion": [],
						    "chang_promotion":0,
						    "theBest":0,
						    "sumamount":0,
						 	"sumdiscount":0,
						 	"sumtotal":0,
						 	"hn":'<s:property value="finanModel.order_Hn" />',
						 	"freeproduct": [],
						 	"contype":[],
						 	"treatdocdis":0,
						 	"treatbranchdis":0,
						 	"finaldiscount":0,
						 	"finalnet":0,
						 	"meddistotal":0,
						 	"prodistotal":0,
						 	"chang_privilege":1,
						 	"giftVoucher":'0',
						 	"gvtype":0
						    
						  }

				 	<s:iterator value="finanModel.promoList">
					 productOBJ.promotion.push({
						 "promotionID":<s:property value="promotion_id" />,
					 	 "sumamount":0,
					 	 "sumdiscount":0,
					 	 "sumtotal":0
					 })
  					</s:iterator>
				 <s:iterator value="listtreatpatmedicine">
					<s:if test="isCheck != 'nu'">
						productOBJ.medicine.push({
							"medID":<s:property value="treatPatMedicine_ProID" />,
							"medName":'<s:property value="treatPro_name" />',
							"freeMed":parseFloat(<s:property value="treatPatMedicine_amountfree" />).toFixed(2),
							"qty":parseFloat(<s:property value="treatPatMedicine_amount" />).toFixed(2),
							"price_per_unit":parseFloat(<s:property value="pro_price" />).toFixed(2),
							"total_price_med":parseFloat(<s:property value="(treatPatMedicine_amount-treatPatMedicine_amountfree)*pro_price" />).toFixed(2),
							"med_dis":parseFloat(0.00).toFixed(2),
							"med_total":parseFloat(<s:property value="(treatPatMedicine_amount-treatPatMedicine_amountfree)*pro_price" />).toFixed(2),
							"med_dis_branch":parseFloat(0.00).toFixed(2)
						})
					</s:if>	
				</s:iterator>
				<s:iterator value="orderlinelist">
				productOBJ.treatment.push({
					"treatID":<s:property value="orderLine_TreatID" />,
					"treat_price":parseFloat(<s:property value="orderLine_price" />).toFixed(2),
					"treat_dis":parseFloat(0.00).toFixed(2),
					"treat_dis_branch":parseFloat(0.00).toFixed(2),
					"treat_dis_doctor":parseFloat(0.00).toFixed(2),
					"treat_total":parseFloat(<s:property value="orderLine_price" />).toFixed(2),
					"treatName":'<s:property value="orderLine_treatName" />',					
					"homecall":<s:property value="orderLine_homecall" />,
					"recall":<s:property value="orderLine_recall" />,
					"catID":<s:property value="orderLine_catID" />,
					"groupID":<s:property value="orderLine_groupID" />
					
					
				})
				</s:iterator>	

				readall()

			/* 	$(".numeric").autoNumeric('init') */

		})
		$(document).on("change","#selectallprivilege",function(){					
					if($(this).val() == 1){
						addAndRemoveHidden('.promo',".giftcard",".giftvoucher")
					}else if($(this).val() == 2){
						addAndRemoveHidden('.giftcard',".promo",".giftvoucher")
					}else if($(this).val() == 3){
						addAndRemoveHidden('.giftvoucher',".promo",".giftcard")
					}
					disallamount()
					productOBJ.chang_privilege = $(this).val()
					readall()
		})
		/* treatment table */
		function readtreatTable(){
			$('.showalltreatment').empty()	
				for (let i = 0; i < productOBJ.treatment.length; i++) { 
					let homecall = ''
					let recall = ''
					if(productOBJ.treatment[i].homecall != 0){
						homecall = 'checked="checked"'
					}
					if(productOBJ.treatment[i].recall != 0){
						recall = 'checked="checked"'
					}
					let appall = '<tr > '+
					'<th class="uk-text-center">'+productOBJ.treatment[i].treatName+'</th>  '+														
					'<th class="uk-text-center"> <input type="checkbox" '+homecall+' ></th>'+
					'<th class="uk-text-center"><input type="checkbox" '+recall+' ></th>'+
					'<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_price+'</th>'+
					'<th class="uk-text-center numeric">'+productOBJ.treatment[i].treat_dis+'</th>'+
					'<th class="uk-text-center "><input readonly="readonly" data-uk-modal="{center:true}" '+
					'value="'+productOBJ.treatment[i].treat_dis_doctor+'" type="text" class="uk-width-1-2 uk-text-right numeric disdoc disdoc'+i+'" data-tindex="'+i+'" /></th>'+
					'<th class="uk-text-center "><input readonly="readonly" data-tbindex="'+i+'" value="'+productOBJ.treatment[i].treat_dis_branch+'" '+
					'type="text" class="uk-width-1-2 uk-text-right numeric disbranch disbranch'+i+'" /></th>'+
					'<th class="uk-text-center  numeric treattotal'+i+'">'+productOBJ.treatment[i].treat_total+'</th>'+
					'<th class="hidden"><input type="text" class="ttotal'+i+'" name="eveyamount"  value="'+productOBJ.treatment[i].treat_total+'"  /></th'+
					'<th class="hidden"><input type="text"    value="'+productOBJ.treatment[i].treatID+'"  /></th'+
					'</tr>';
						$('.showalltreatment').append(appall)
				}

		}
	/* 	table medicine	 */
		/* delete medicine from table */
		$(document).on("click",".delmedicine",function(){
			productOBJ.medicine.splice($(this).data("index"), 1)
			readall()
		})	
			/* add medicine to table */
			$(document).on("click","#btn_submit_be_allergic",function(){
				
				let getproid =	parseInt($('input[name=medicine]:checked').val())
				if(!isNaN(getproid)){
					$('.preload').removeClass('hidden');
					let allval = $('input[name=medicine]:checked').parent().nextAll().map(function () {
				        return $(this).text();
				    }).get();
					let calmedicine = (parseFloat(allval[1])) * (parseFloat($('.qtymedi'+getproid).val().replace(/,/g,"")))
					
					productOBJ.medicine.push({
						"medID":getproid,
						"medName":allval[0],
						"freeMed":0,
						"qty":$('.qtymedi'+getproid).val().replace(/,/g,""),
						"price_per_unit":allval[1],
						"total_price_med":parseFloat(calmedicine).toFixed(2),
						"med_dis":parseFloat(0.00).toFixed(2),
						"med_total":parseFloat(calmedicine).toFixed(2),
						"med_dis_branch":parseFloat(0.00).toFixed(2)
					})
					/* readMedTable() */
					readall()
				}
			/* 	$(".numeric").autoNumeric('init') */
			})
		/* 	function read Object medicine into table */
			function readMedTable(){
			$('.showallmedicine').empty()	
				for (let i = 0; i < productOBJ.medicine.length; i++) { 
					let appall = '<tr > '+
					'<th class="uk-text-center hidden"><input name="medID" type="hidden" value="'+productOBJ.medicine[i].medID+'" />'+productOBJ.medicine[i].medID+'</th>  '+
					'<th class="uk-text-center">'+productOBJ.medicine[i].medName+'</th>'+
					'<th class="uk-text-center numeric">'+productOBJ.medicine[i].freeMed+'</th>'+
					'<th class="uk-text-center numeric">'+productOBJ.medicine[i].qty+'</th>'+
					'<th class="uk-text-center numeric">'+productOBJ.medicine[i].price_per_unit+'</th>'+
					'<th class="uk-text-center numeric">'+productOBJ.medicine[i].total_price_med+'</th>'+
					'<th class="uk-text-center numeric">'+productOBJ.medicine[i].med_dis+'</th>'+
					'<th class="uk-text-center "><input type="text" class="uk-width-1-2 numeric uk-text-right dismedicine dismedicine'+i+'" readonly="readonly" data-mbindex="'+i+'"'+
					'value="'+productOBJ.medicine[i].med_dis_branch+'" /></th>'+
					'<th class="uk-text-center countall numeric meddistotal'+i+'">'+productOBJ.medicine[i].med_total+'</th>'+
					'<th class="hidden"><input type="text" class="mtotal'+i+'" name="eveyamount"  value="'+productOBJ.medicine[i].med_total+'"  /></th>'+
					'<th><button data-index="'+i+'" class="uk-button uk-button-danger uk-button-small delmedicine"  type="button" >x</button></th>'+ 
					'</tr>';
						$('.showallmedicine').append(appall)
				}
			
			}
			$(document).on("change","#shmedi",function(){					
				$("input[name='mediqty']").attr('disabled', 'disabled');
				$('.qtymedi'+$(this).val()).removeAttr('disabled');
			})
			$(document).on("click","#medicinelist",function(){					
				$('#tablemedicine').dataTable().fnClearTable();
				$('#tablemedicine').dataTable().fnDestroy()
				let proid = 0
				let hn = "<s:property value="finanModel.order_Hn" />"
				let protype = "0001"
				
				$("input[name='medID']").each(function( i, val){
					if(i == 0){
						proid = $(this).val()
					}else{
						proid += ","+$(this).val()
					}					
				})

				$('.medibodyModal').html(productList(proid,hn,protype))
				$(document).ready(function () {
					$('#tablemedicine').dataTable()
				})
				$(".numeric").autoNumeric('init');
				let modal = UIkit.modal('#medicineModal');
				modal.show();
			})
		/*end 	table medicine	 */
		/* 	table product	 */
		/* delete product from table */
		$(document).on("click",".delproduct",function(){
			productOBJ.product.splice($(this).data("index1"), 1)
			readall()
		})
			$(document).on("change","#shpro",function(){					
				$("input[name='proqty']").attr('disabled', 'disabled');
				$('.qtypro'+$(this).val()).removeAttr('disabled');
			})
			$(document).on("click","#btn_submit_pro",function(){
				
				let getproid =	parseInt($('input[name=produc]:checked').val())				
				if(!isNaN(getproid)){
					$('.preload').removeClass('hidden');
				let allval = $('input[name=produc]:checked').parent().nextAll().map(function () {
				        return $(this).text();
				    }).get();
				let calpro = (parseFloat(allval[1])) * (parseFloat($('.qtypro'+getproid).val().replace(/,/g,"")))
				productOBJ.product.push({
						"proID":getproid,
						"proName":allval[0],
						"qty":$('.qtypro'+getproid).val().replace(/,/g,""),
						"price_per_unit":allval[1],
						"total_price_pro":parseFloat(calpro).toFixed(2),
						"pro_dis":parseFloat(0.00).toFixed(2),
						"pro_total":parseFloat(calpro).toFixed(2),
						"pro_dis_branch":parseFloat(0.00).toFixed(2)
					})
				
					readall()

				}
				/* $(".numeric").autoNumeric('init') */
			})
		/* 	function read Object Product into table */
			function readProTable(){
				$('.showpro').empty()	
				for (let i = 0; i < productOBJ.product.length; i++) { 
					let appall = '<tr> '+
					'<th class="uk-text-center hidden"><input name="pdID" type="hidden" value="'+productOBJ.product[i].proID+'" />'+productOBJ.product[i].proID+'</th>  '+
					'<th class="uk-text-center">'+productOBJ.product[i].proName+'</th>'+
					'<th class="uk-text-center numeric">'+productOBJ.product[i].qty+'</th>'+
					'<th class="uk-text-center numeric">'+productOBJ.product[i].price_per_unit+'</th>'+
					'<th class="uk-text-center numeric">'+productOBJ.product[i].total_price_pro+'</th>'+
					'<th class="uk-text-center numeric">'+productOBJ.product[i].pro_dis+'</th>'+
					'<th class="uk-text-center "><input type="text" class="uk-width-1-2 uk-text-right numeric disproduct disproduct'+i+'" readonly="readonly" data-pbindex="'+i+'" value="'+productOBJ.product[i].pro_dis_branch+'" /></th>'+
					'<th class="uk-text-center countall numeric prodistotal'+i+'">'+productOBJ.product[i].pro_total+'</th>'+
					'<th class="hidden"><input type="text" class="ptotal'+i+'" name="eveyamount"  value="'+productOBJ.product[i].pro_total+'"  /></th>'+
					'<th><button class="uk-button uk-button-danger uk-button-small delproduct" data-index1="'+i+'"  type="button" >x</button></th>'+
					'</tr>';
						$('.showpro').append(appall)
				}

			}
			$(document).on("click","#productlist",function(){					
				$('#tableproduct').dataTable().fnClearTable();
				$('#tableproduct').dataTable().fnDestroy()
				let proid = 0
				let hn = "<s:property value="finanModel.order_Hn" />"
				let protype = "0002"
						$("input[name='pdID']").each(function( i, val){
							if(i == 0){
								proid = $(this).val()
							}else{
								proid += ","+$(this).val()
							}					
						})
				
				$.ajax({  //   
				    type: "post",
				    url: "ajax_json_product", 
				    data: {proID:proid,protype:protype,hn:hn},
				    async:false, 
				    success: function(result){ 
				    	  if (result != '') {	
						    	var selectg = "";
 						    	$.each(result, function(i, val) { 							    	
 						    		selectg += '<tr> '+
							    					'<th class="uk-text-center productID"><input value="'+val.proid+'" type="radio" id="shpro" name="produc" class="uk-form"/></th>  '+
							    					'<th class="uk-text-center">'+val.proname+'</th>'+
							    					'<th class="uk-text-center">'+val.proprice+'</th>'+
							    					'<th class="uk-text-center"><input disabled="disabled" name="proqty" value="0" type="text" class="uk-form uk-text-center numeric qtypro'+val.proid+'"/></th>'+
							    					'</tr>'; 
						    	});  
 						    	$('.productbodyModal').html(selectg)
						    	
						    } 
				    }
				})
				
				$(document).ready(function () {
					$('#tableproduct').dataTable()
				})
				$(".numeric").autoNumeric('init');
				let modal = UIkit.modal('#proModal');
				modal.show();
			})
			function productList(proID,hn,protype) {
				var  showall = "";
				$.ajax({  //   
				    type: "post",
				    url: "ajax_json_product", 
				    data: {proID:proID,protype:protype,hn:hn},
				    async:false, 
				    success: function(result){ 
				    	  if (result != '') {	
						    	var selectg = "";
 						    	$.each(result, function(i, val) { 							    	
 						    		selectg += medicinetablelist(val.proid,val.proname,val.proprice,val.proid); 
						    	});  
						    	 showall = selectg
						    } 
				    }
				})
				 return showall ;
			}
			function medicinetablelist(proid,proname,proprice){
			 return 	'<tr> '+
				'<th class="uk-text-center medicineID"><input value="'+proid+'" type="radio" id="shmedi" name="medicine" class="uk-form"/></th>  '+
				'<th class="uk-text-center">'+proname+'</th>'+
				'<th class="uk-text-center">'+proprice+'</th>'+
				'<th class="uk-text-center"><input disabled="disabled" name="mediqty" value="0" type="text" class="uk-form uk-text-center numeric qtymedi'+proid+'"/></th>'+
				'</tr>'
			}
			function addAndRemoveHidden(id1,id2,id3) {
				$(id1).removeClass(' hidden')
				$(id2).addClass('hidden')						
				$(id3).addClass('hidden')
			}
			function calAndFindPromotion(){
				$.ajax({  //   
				    type: "post",
				    url: "ajax_json_calcuall", 
				    data: {productobj:JSON.stringify(productOBJ)},
				    async:false, 
				    success: function(result){ 
				    	  if (result != '') {	

				    		  productOBJ = result 
				    		  console.log(productOBJ)
						    } 
				    }
				})
			}
			function readFreeTable(){
				$('.freeProduct').empty()	
				
				for (let i = 0; i < productOBJ.freeproduct.length; i++) { 
					let type = ""
						if(productOBJ.freeproduct[i].freetype == 1)type = "ยา"
						else if(productOBJ.freeproduct[i].freetype == 2)type = "สินค้า"
						else if(productOBJ.freeproduct[i].freetype == 3)type = "วัสดุ"
						else if(productOBJ.freeproduct[i].freetype == 4)type = "ทุกการรักษา"
						else if(productOBJ.freeproduct[i].freetype == 5)type = "กลุ่มการรักษา"
						else if(productOBJ.freeproduct[i].freetype == 6)type = "หมวดการรักษา"
						else if(productOBJ.freeproduct[i].freetype == 7)type = "รายการรักษา"
					let appall = '<tr> '+
					'<th class="uk-text-center hidden"><input name="freeID" type="hidden" value="'+productOBJ.freeproduct[i].freeID+'" />'+productOBJ.freeproduct[i].freeID+'</th>  '+
					'<th class="uk-text-center">'+productOBJ.freeproduct[i].freename+'</th>'+
					'<th class="uk-text-center numeric">'+type+'</th>'+
					'<th class="uk-text-center numeric">'+productOBJ.freeproduct[i].qty+'</th>'+
					'</tr>';
						$('.freeProduct').append(appall)
				}

			}
			/* function readContype(){
				$('#showContype').empty()	
				for (let i = 0; i < productOBJ.contype.length; i++) { 
					if(i==0){
						let appall = '<option selected="selected" value="'+productOBJ.contype[i].conID+'">'+productOBJ.contype[i].conname+'</option>';					
						$('#showContype').append(appall)
					}else{
						let appall = '<option value="'+productOBJ.contype[i].conID+'">'+productOBJ.contype[i].conname+'</option>';					
						$('#showContype').append(appall)
					}
					
				}

			} */
			
			$(document).on("change","#promosel",function(){					
				disallamount()
				productOBJ.chang_promotion = $(this).val()
				readall()
				productOBJ.chang_promotion = 0
			})
			
		$(document).on("click",".disdoc",function(){
			$("#btn_dis").val($(this).data("tindex"))
			$(".numeric").autoNumeric('init');
			let modal = UIkit.modal('#disdocModel');
			$('.clear').val(0);
			modal.show();
		})
		$(document).on("click","#btn_dis",function(){			
			if($('input[name=disalltypedoc]:checked').val() == 1){
				$(".disdoc"+$(this).val()).val($('#disbaht').val())
			}else{			
				$(".disdoc"+$(this).val()).val(((parseFloat(productOBJ.treatment[$(this).val()].treat_total) * parseFloat($('#disper').val()))/100).toFixed(2))
			}
			productOBJ.treatdocdis = (parseFloat(productOBJ.treatment[$(this).val()].treat_total) - (parseFloat($(".disdoc"+$(this).val()).val().replace(/,/g,"")) + parseFloat($(".disbranch"+$(this).val()).val().replace(/,/g,"")) )).toFixed(2)
			
			$('.treattotal'+$(this).val()).text(addCommas(productOBJ.treatdocdis))
			$('.ttotal'+$(this).val()).val(productOBJ.treatdocdis)
			readtotalall()

		}) 
		$(document).on("change","input[name=disalltypedoc]",function(){
			if($(this).val() == 1)
				$('#disper').val(0)
			else
				$('#disbaht').val(0)	
		})
		/* end dis doc */
		$(document).on("click",".disbranch",function(){
			$("#btn_disbranch").val($(this).data("tbindex"))
			$(".numeric").autoNumeric('init');
			let modal = UIkit.modal('#disbranchModel');
			$('.clear').val(0);
			modal.show();
		})
		$(document).on("click","#btn_disbranch",function(){			
			if($('input[name=disalltypebranch]:checked').val() == 1){
				$(".disbranch"+$(this).val()).val($('#disbahtbranch').val())
			}else{			
				$(".disbranch"+$(this).val()).val(((parseFloat(productOBJ.treatment[$(this).val()].treat_total) * parseFloat($('#disperbranch').val()))/100).toFixed(2))
			}
			productOBJ.treatdocdis = (parseFloat(productOBJ.treatment[$(this).val()].treat_total) - (parseFloat($(".disbranch"+$(this).val()).val().replace(/,/g,"")) + parseFloat($(".disdoc"+$(this).val()).val().replace(/,/g,"")) ) ).toFixed(2)
			$('.treattotal'+$(this).val()).text(addCommas(productOBJ.treatdocdis))
			$('.ttotal'+$(this).val()).val(productOBJ.treatdocdis)
			readtotalall()

		}) 
		$(document).on("change","input[name=disalltypebranch]",function(){
			if($(this).val() == 1)
				$('#disperbranch').val(0)
			else
				$('#disbahtbranch').val(0)	
		})
		/* end dis branch treat */
		$(document).on("click",".dismedicine",function(){
			$("#btn_disbranchmedicine").val($(this).data("mbindex"))
			$(".numeric").autoNumeric('init');
			let modal = UIkit.modal('#disbranchMedicine');
			$('.clear').val(0);
			modal.show();
		})
		$(document).on("click","#btn_disbranchmedicine",function(){			
			if($('input[name=disalltypebranchmedicine]:checked').val() == 1){
				$(".dismedicine"+$(this).val()).val($('#disbahtbranchmedicine').val())
			}else{			
				$(".dismedicine"+$(this).val()).val(((parseFloat(productOBJ.medicine[$(this).val()].med_total) * parseFloat($('#disperbranchmedicine').val()))/100).toFixed(2))
			}
			productOBJ.meddistotal = (parseFloat(productOBJ.medicine[$(this).val()].med_total) - (parseFloat($(".dismedicine"+$(this).val()).val().replace(/,/g,"")))).toFixed(2)
			$('.meddistotal'+$(this).val()).text(addCommas(productOBJ.meddistotal))
			$('.mtotal'+$(this).val()).val(productOBJ.meddistotal)
			readtotalall()

		}) 
		$(document).on("change","input[name=disalltypebranchmedicine]",function(){
			if($(this).val() == 1)
				$('#disperbranchmedicine').val(0)
			else
				$('#disbahtbranchmedicine').val(0)	
		})		
		/* end dis branch medicine */
		$(document).on("click",".disproduct",function(){
			$("#btn_disbranchproduct").val($(this).data("pbindex"))
			$(".numeric").autoNumeric('init');
			let modal = UIkit.modal('#disbranchProduct');
			$('.clear').val(0);
			modal.show();
		})
		$(document).on("click","#btn_disbranchproduct",function(){			
			if($('input[name=disalltypebranchproduct]:checked').val() == 1){
				$(".disproduct"+$(this).val()).val($('#disbahtbranchproduct').val())
			}else{			
				$(".disproduct"+$(this).val()).val(((parseFloat(productOBJ.product[$(this).val()].pro_total) * parseFloat($('#disperbranchproduct').val()))/100).toFixed(2))
			}
			productOBJ.prodistotal = (parseFloat(productOBJ.product[$(this).val()].pro_total) - (parseFloat($(".disproduct"+$(this).val()).val().replace(/,/g,"")))).toFixed(2)
			
			$('.prodistotal'+$(this).val()).text(addCommas(productOBJ.prodistotal))
			$('.ptotal'+$(this).val()).val(productOBJ.prodistotal)
			readtotalall()
		}) 
		$(document).on("change","input[name=disalltypebranchproduct]",function(){
			if($(this).val() == 1)
				$('#disperbranchproduct').val(0)
			else
				$('#disbahtbranchproduct').val(0)	
		})		
		/* end dis branch product */
			$(document).on("keyup",".dper",function(){
			let dper1 = "";			
			let bper1 = "";
			if($(this).data("dcb") == 1){
				dper1 = parseFloat($(this).val())
				bper1 = parseFloat((parseFloat($('.disbranch'+$('#btn_dis').val()).val().replace(/,/g,"")) / parseFloat(productOBJ.treatment[$('#btn_dis').val()].treat_total))*100)
			}else if($(this).data("dcb") == 2){
				bper1 = parseFloat($(this).val())
				dper1 = parseFloat((parseFloat($('.disdoc'+$('#btn_disbranch').val()).val().replace(/,/g,"")) / parseFloat(productOBJ.treatment[$('#btn_disbranch').val()].treat_total))*100)
			}
			
 			if($(this).autoNumeric('get')>100){
				swalall()
			    $(this).val(0);  
			} 
			 if(Math.abs(dper1 + bper1)>100){
				 	swalall()
				   	$(this).val(0);  
				}  
		})

		$(document).on("keyup",".dbaht",function(){
			let db1 = "";			
			let bb1 = "";
			let cc1 = "";
			let total1= "";
			if($(this).data("dcb") == 3){
				db1 = parseFloat($(this).val())
				bb1 = parseFloat((parseFloat($('.disbranch'+$('#btn_dis').val()).val().replace(/,/g,""))))
				cc1 = $('#btn_dis').val()
				total1 = parseFloat(productOBJ.treatment[cc1].treat_total)
			}else if($(this).data("dcb") == 4){
				db1 = parseFloat($(this).val())
				bb1 = parseFloat((parseFloat($('.disdoc'+$('#btn_disbranch').val()).val().replace(/,/g,""))))
				cc1 = $('#btn_disbranch').val()
				total1 = parseFloat(productOBJ.treatment[cc1].treat_total)
			}else if($(this).data("dcb") == 7){
				cc1 = $('#btn_disbranchmedicine').val()
				total1 = parseFloat(productOBJ.medicine[cc1].med_total)
			}else if($(this).data("dcb") == 8){
				cc1 = $('#btn_disbranchproduct').val()
				total1 = parseFloat(productOBJ.product[cc1].pro_total)
			}
			
			if($(this).autoNumeric('get')>total1){
						swalall()
			    	    $(this).val(0);  
			}
			if(Math.abs(db1 + bb1)>total1 ){
				swalall()
	    	    $(this).val(0);
			}
		})
		function swalall() {
			swal(
		    		  'WARNING!',
		    	      'ค่าเงินไม่สามารถลดเกิน 100%ได้ :)',
		    	      'error'
		    	    )
		}
			function addCommas(nStr)
			{
				nStr += '';
				x = nStr.split('.');
				x1 = x[0];
				x2 = x.length > 1 ? '.' + x[1] : '';
				var rgx = /(\d+)(\d{3})/;
				while (rgx.test(x1)) {
					x1 = x1.replace(rgx, '$1' + ',' + '$2');
				}
				return x1 + x2;
			}

			$(document).on("click","#checkGc",function(){
				let modal = UIkit.modal('#checkgiftcard');
				modal.show();
			})
			$(document).on("click","#btn_checkGiftCard",function(){
				let giftnum = $('#giftcardnumber').val()
				let show = false
				$.ajax({  //   
				    type: "post",
				    url: "ajax_json_giftcardCheck", 
				    data: {giftnum:giftnum},
				    async:false, 
				    success: function(result){ 
				    	  if (result != '') {	
				    		  let amount = result.giftamount
				    		  if(result.type == 1){
				    			  $('#giftamount').val(amount);
					    		  $('#gcardID').val(giftnum);
					    		  show = true
				    		  }else{
				    			  $('#giftamount').val(0);				    		  
					    		  	$('#gcardID').val(0);
				    		  }
				    			
						    }  
				    }
				})
				if(show){
					swal(
			   			      'Gift Card นี้สามารถใช้งานได้',
			   			      'จำนวนเงินในบัตร '+$('#giftamount').val(),
			   			      'success'
			   			    )
				}else{
					 swal(
			   			      'Gift Card นี้ไม่สามารถใช้งานได้',
			   			      'ข้อมูลจะไม่มีการเปลี่ยนแปลง',
			   			      'error'
			   			    )
				}
				disallamount()
				readall()
			})
			$(document).on("click","#checkGv",function(){
				let modal = UIkit.modal('#checkgv');
				modal.show();
			})
			$(document).on("click","#btn_checkGiftv",function(){
				let giftnum = $('#giftvnumber').val()
				let show = false
				let word = '';
				$.ajax({  //   
				    type: "post",
				    url: "ajax_json_giftvCheck", 
				    data: {giftnum:giftnum},
				    async:false, 
				    success: function(result){ 
				    	  if (result != '') {	
				    		  let amount = result.amountGV
				    		  if(result.type == 1){
				    			  $('#gvamount').val(0);
				    			  $('#giftvocID').val(giftnum);
				    			  productOBJ.gvtype = 1
					    		  show = true
				    		  }else if(result.type == 2){			    		  
				    			  $('#gvamount').val(amount);
				    			  $('#giftvocID').val(giftnum);
				    			  show = true
				    			  productOBJ.gvtype = 2
				    			  word = amount
				    		  }else{
				    			  $('#gvamount').val(0);
				    		  }
						    }  
				    }
				})
				if(show){
					swal(
			   			      'Gift Voucher นี้สามารถใช้งานได้',
			   			      ''+word,
			   			      'success'
			   			    )
				}else{
					 swal(
			   			      'Gift Voucher นี้ไม่สามารถใช้งานได้',
			   			      'ข้อมูลจะไม่มีการเปลี่ยนแปลง',
			   			      'error'
			   			    )
				}
				readall()
				disallamount()
			})
			function readall() {
				 $('.preload').removeClass('hidden'); 
				$(document).ready(function(){					
						
				if($('#selectallprivilege').val() == 1){
					calAndFindPromotion()
					if(productOBJ.theBest != 0){
						$("#promosel option[value='"+productOBJ.theBest+"']").prop('selected', true);
					}else{
						$("#promosel option:eq(0)").prop('selected', true)
					}
				}else if($('#selectallprivilege').val() == 2){
					calAndFindPromotion()
				}else if($('#selectallprivilege').val() == 3){
					productOBJ.giftVoucher = $('#giftvocID').val()
					calAndFindPromotion()					
				}
			
				
				readMedTable()
				readtreatTable()
				readProTable()
				readFreeTable()
				/* readContype() */
				readtotalall()
				sumamt_money()
				$(".numeric").autoNumeric('init')
				})
				$('.preload').addClass('hidden');
				
			}
			function sumamt_money(){
				let net =  $("#net").val().replace(/,/g,""); 
				let money = $("#money").val().replace(/,/g,""); 
				let credit_card = $("#credit_card").val().replace(/,/g,"");
				let line_pay = $("#line_pay").val().replace(/,/g,"");
				let deposit = $("#deposit").val().replace(/,/g,"");
				let giftcard = $("#giftcard").val().replace(/,/g,""); 
				let giftvoucher = $("#giftv").val().replace(/,/g,"");
				let sso = $("#sso").val().replace(/,/g,"");
				let textvar = 0;
				if(money!='') textvar = parseFloat(textvar) + parseFloat(money);
				if(credit_card!='') textvar = parseFloat(textvar) + parseFloat(credit_card);
				if(line_pay!='') textvar = parseFloat(textvar) +  parseFloat(line_pay);
				if(deposit!='') textvar = parseFloat(textvar) +  parseFloat(deposit);
				if(giftcard!='') textvar = parseFloat(textvar) +  parseFloat(giftcard);
				if(giftvoucher!='') textvar = parseFloat(textvar) +  parseFloat(giftvoucher);
				if(sso!='') textvar = parseFloat(textvar) +  parseFloat(sso);
				let amount_paid =  parseFloat(parseFloat(net)-parseFloat(textvar)).toFixed(2);
				if(parseFloat(net)<parseFloat(textvar)){
					amount_paid = 0;
				} 
				$("#owe").val(amount_paid); 
				$("#amount_paid").val(textvar);  
			}
			$(document).on('keyup','.amAll',function (){ 
				$(this).val();
				sumamt_money()
			})
			$(document).on('keyup','.gall',function (){ 
				
				if($('#selectallprivilege').val() == 2){
					if(parseFloat($("#giftamount").val().replace(/,/g,"")) > parseFloat($(this).val().replace(/,/g,""))  ){
						sumamt_money()
					}else{
						swal(
				   			      'Gift Card error',
				   			      'จำนวนเงินเกินที่กำหนดใน Gift Card',
				   			      'error'
				   			    )
						$("#giftcard").val("");
						sumamt_money()
					}
					
				}

				if($('#selectallprivilege').val() == 3 && parseInt($("#gvamount").val()) != 0 ){
					if(parseFloat($(this).val().replace(/,/g,""))<=parseFloat($("#gvamount").val().replace(/,/g,""))){
						sumamt_money()
					}else{
						swal(
				   			      'Gift Voucher error',
				   			      'จำนวนเงินเกินที่กำหนดใน Gift Voucher',
				   			      'error'
				   			    )
						$("#giftv").val(0);
						sumamt_money()
					}
				}
				
			})
			function disallamount() {
				$("#money").attr("disabled", true);
				$("#money").val("");
				$("#credit_card").attr("disabled", true);
				$('select[name="chose_credit_card"]').attr("disabled", true);
				$("#credit_card").val("");
				$("#line_pay").attr("disabled", true);
				$("#line_pay").val("");
				$("#deposit").attr("disabled", true);
				$("#deposit").val("");
				$("#giftcard").attr("disabled", true);
				$("#giftcard").val("");
				$("#giftv").attr("disabled", true);
				$("#giftv").val("");
				$("#sso").attr("disabled", true);
				$("#sso").val("");
				$('.tik').prop( "checked", false )
				sumamt_money()
			}
			$(document).on('change','.tik',function (){ 
				var tik = $(this).val();
				if(tik==0){
					if (this.checked) {
						$("#money").attr("disabled", false);
						$("#money").val('');
					}else{
						$("#money").attr("disabled", true);
						$("#money").val("");
						sumamt_money()
					}
					
				}else if(tik==1){
					if (this.checked) { 
						$("#credit_card").attr("disabled", false);
						$('select[name="chose_credit_card"]').attr("disabled", false);
						$("#credit_card").val('');
					}else{
						$("#credit_card").attr("disabled", true);
						$('select[name="chose_credit_card"]').attr("disabled", true);
						$("#credit_card").val("");
						sumamt_money()
					}
				}else if(tik==2){
					if (this.checked) {
						$("#line_pay").attr("disabled", false);
						$("#line_pay").val('');
					}else{
						$("#line_pay").attr("disabled", true);
						$("#line_pay").val("");
						sumamt_money()
					}
					
				}else if(tik==3){
					if (this.checked) {
						$("#deposit").attr("disabled", false);
						$("#deposit").val('');
					}else{
						$("#deposit").attr("disabled", true);
						$("#deposit").val("");
						sumamt_money()
					}
					
				}else if(tik==4){
					if($('#selectallprivilege').val() == 2){
						if(this.checked){
							$("#giftcard").attr("disabled", false);
							$("#giftcard").val('');
						}else{
							$("#giftcard").attr("disabled", true);
							$("#giftcard").val("");
							sumamt_money()
						}
					}else{
						this.checked = false;
					}
					
					
				}else if(tik==5){
					if($('#selectallprivilege').val() == 3){
						if(this.checked){
							$("#giftv").attr("disabled", false);
							$("#giftv").val('');
						}else{
							$("#giftv").attr("disabled", true);
							$("#giftv").val("");
							sumamt_money()
						}
					}else{
						this.checked = false;
					}
					
				}else if(tik==6){
						if(checksocialSecurity()){
							if(this.checked){
								$("#sso").attr("disabled", false);
								$("#sso").val('');
							}else{
								$("#sso").attr("disabled", true);
								$("#sso").val("");
								sumamt_money()
							}
						}else{
							this.checked = false;
						}
		
				}
				
			});
			$(document).on('click','.promotionid',function (){ 
				let proid = $(this).data("proid")
				findpromotiondetail(proid);
				$(".numeric").autoNumeric('init');
			})
			$(document).on('click','#prodetailexpan',function (){ 
				let proid = productOBJ.promotion[0].promotionID;
				findpromotiondetail(proid);
			})
			function findpromotiondetail(proid) {
				$.ajax({  //   
				    type: "post",
				    url: "ajax_json_promotionDetail", 
				    data: {proid:proid},
				    async:false, 
				    success: function(result){ 
				    	  if (result != '') {	
						    	let selectg = "";
						    	let protype = "-";
						    	let distype = "-";
 						    	$.each(result, function(i, val) {
 						    		if(val.protypedetail != 0){
 						    			if(val.protypedetail == 1)protype="ยา"
 						    			else if(val.protypedetail == 2)protype="สินค้า"
 						    			else if(val.protypedetail == 3)protype="วัสดุ"
 						    			else if(val.protypedetail == 4)protype="การรักษาทั้งหมด"
 						    			else if(val.protypedetail == 5)protype="กลุ่มการรักษา"
 						    			else if(val.protypedetail == 6)protype="หมวดการรักษา"
 						    			else protype="รายการรักษา"
 						    		}
 						    			if(val.prodistypedetail == 1)distype="บาท"
 						    			else if(val.prodistypedetail == 2)distype="เปอร์เซ็นต์"
 						    			else if(val.prodistypedetail == 3)distype="แถม"
 						    		selectg += '<tr> '+
							    					'<th class="uk-text-center">'+val.namede+'</th>  '+
							    					'<th class="uk-text-center">'+val.namedetaill+'</th>'+
							    					'<th class="uk-text-center">'+protype+'</th>'+
							    					'<th class="uk-text-center numeric">'+val.qty+'</th>'+
							    					'<th class="uk-text-center numeric">'+val.prodisdetail+'</th>'+
							    					'<th class="uk-text-center numeric">'+distype+'</th>'+
							    					'</tr>'; 
						    	});  
 						    	$('.prodetail'+proid).html(selectg)
						    } 
				    }
				})
			}
			function checksocialSecurity() {
				let check = false
				$.ajax({  //   
				    type: "post",
				    url: "ajax_json_checksocialSecurity", 
				    data: {proid:""},
				    async:false, 
				    success: function(result){ 
				    	  if (result != '') {	
						    	if(result.check){
						    		check = true
						    	}
						    } 
				    }
				})
				return check
			}
		</script>
		</div>
	</body>
</html>