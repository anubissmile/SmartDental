package com.smict.person.action;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.DoctTimeModel;
import com.smict.person.data.AddressData;
import com.smict.person.data.BookBankData;
import com.smict.person.data.BranchData;
import com.smict.person.data.DoctorData;
import com.smict.person.data.DoctorTypeData;
import com.smict.person.data.EducationData;
import com.smict.person.data.EmployeeData;
import com.smict.person.data.PatientData;
import com.smict.person.data.Pre_nameData;
import com.smict.person.data.TelephoneData;
import com.smict.person.data.WorkHistoryData;
import com.smict.person.model.AddressModel;
import com.smict.person.model.BookBankModel;
import com.smict.person.model.BranchModel;
import com.smict.person.model.DoctorModel;
import com.smict.person.model.PatientModel;
import com.smict.person.model.Person;
import com.smict.person.model.Pre_nameModel;
import com.smict.person.model.TelephoneModel;

import ldc.util.Auth;
import ldc.util.DateUtil;
import ldc.util.Encrypted;
import ldc.util.Servlet;
import ldc.util.Storage;
import ldc.util.Validate;

@SuppressWarnings("serial")
public class DoctorAction extends ActionSupport {
	
	/**
	 * GETTER & SETTER.
	 */
	private DoctorModel docModel,scopeModel;
	private DoctTimeModel docTimeM;
	private TelephoneModel telModel;
	private BranchModel branchModel;
	private HashMap<String, String> telType = new HashMap<String, String>();
	HashMap<String, String> branchMap = new HashMap<String, String>();
	private List<BranchModel> branchList = new ArrayList<BranchModel>();
	private List<BranchModel> branchMGRList = new ArrayList<BranchModel>();
	private List<AddressModel> AddrList = new ArrayList<AddressModel>();
	private List<TelephoneModel> telList = new ArrayList<TelephoneModel>();
	private TelephoneModel emTelModel = new TelephoneModel();
	private List<BookBankModel>	bankList = new ArrayList<BookBankModel>();
	private List<PatientModel> pList = new ArrayList<PatientModel>();
	private List<Pre_nameModel> pnameList = new ArrayList<Pre_nameModel>();
	private List<DoctorModel> workList = new ArrayList<DoctorModel>();
	private List <DoctorModel> eduList = new ArrayList<DoctorModel>();
	private int doctor_id;
	
	/**
	 * DEBUGGIN
	 */
	private String propertyInStack;
	
	/**
	 * Alert Messages.
	 */
	private String alertMSG = null, alertSuccess = null, alertError = null;
	
	/**
	 * FILE UPLOADING
	 */
	private File picProfile;
	private String picProfileContentType;
	private String picProfileFileName;
	Map<String,String> branchlist;
	Map<String,String> dentistTreatmentMap;
	Map<String,String> scopeTreatmentMap;
	String docID,branchID;
	List<DoctorModel> branchStandardList, branchMgrList,doctorList,scopeDentistlist,positionTreatmentList,treatMentList;

	/**
	 * CONSTRUCTOR
	 */
	public DoctorAction(){
		Auth.authCheck(false);
	}
	
	
	/**
	 * Delete doctor's schedule workday from calendar.
	 * @author anubissmile
	 * @return String | Action result.
	 */
	public String delScheduleFromCalendar(){
		DoctorData docData = new DoctorData();
		int rec = docData.delScheduleFromCalendar(docTimeM);
		if(rec > 0){
			setAlertSuccess("Deletion was success!");
		}else{
			setAlertMSG("Your item not found!\nPlease checking out your item.");
			return INPUT;
		}
		return SUCCESS;
	}
	
	
	/**
	 * Add new doctor's schedule from calendar.
	 * @author anubissmile
	 * @return String | Action result.
	 */
	public String calendarAddNewSchedule(){
		BranchData branchData = new BranchData();
		if(docTimeM.getBranch_id().equals("-1")){
			setAlertMSG("You must select your branch.");
			return INPUT;
		}else{
			/**
			 * Parsing datetime
			 */
			DateTime timeIn = DateTime.parse(docTimeM.getTime_in()), timeOut = DateTime.parse(docTimeM.getTime_out());
			DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			
			/**
			 * Calculate the minutes.
			 */
			docTimeM.setMinutes(Minutes.minutesBetween(timeIn, timeOut).getMinutes());
			
			/**
			 * Get time in-out.
			 */
			docTimeM.setTime_in(df.print(timeIn));
			docTimeM.setTime_out(df.print(timeOut));
			
			/**
			 * Get branch code.
			 */
			HashMap<String, String> branchMap = branchData.getBranchCode(docTimeM.getBranch_id());
			docTimeM.setBranch_id(branchMap.get("branch_code"));
			
			/*System.out.println("Time in : " + df.print(timeIn) + "\nTime out : " + df.print(timeOut));
			System.out.println(Minutes.minutesBetween(timeIn, timeOut).getMinutes());
			System.out.println(docTimeM.getBranch_id());*/
			/**
			 * Check duplicates.
			 */
			DoctorData docData = new DoctorData();
			int rec = 0;
			rec = docData.checkDoctorWorkDayDuplicate(docTimeM);
			if(rec < 1){
				/**
				 * Insert new one.
				 */
				if(docData.addDoctorWorkdayFromCalendar(docTimeM) > 0){
					setAlertSuccess("Add new schedule success");
				}else{
					setAlertError("We can't insert your new schedule becuase something went wrong.\nPlease try again later.");
					return INPUT;
				}
			}else{
				/**
				 * Cannot insert cause of duplicate records.
				 */
				setAlertMSG("Some of time range was overlap, Please checking out again!");
				return INPUT;
			}
		}
		return SUCCESS;
	}


	/**
	 * Displaying the doctor's calendar schedule.
	 * @author anubissmile
	 * @return String | Action result.
	 */
	public String doctorScheduleCalendar(){
		/**
		 * Fetch doctor's standard branch.
		 */
		DoctorData docData = new DoctorData();
		
		List<DoctorModel> branchList = docData.getBranchStandard(docModel.getDoctorID());
		for(DoctorModel branch : branchList){
			branchMap.put(branch.getBranchStandID(), branch.getBranchName());
		}
		
		if(branchMap.size() < 1){
			addActionMessage("ไม่พบรายการสาขาที่ลงตรวจ (ควรเพิ่มสาขาที่ลงตรวจก่อนที่จะเพิ่มเวรลงตรวจ)");
		}
		return SUCCESS;
	}
	
	/**
	 * Get AJAX request and displaying doctor's schedule by JSON to displaying on calendar.
	 * @author anubissmile
	 * @return void
	 */
	public String ajaxDoctorScheduleCalendar(){
		DoctorData docData = new DoctorData();
		JSONArray jsonArr = new JSONArray();
		
		List<HashMap<String, String>> docList = docData.getDoctorWorkDayByID(String.valueOf(docModel.getDoctorID()));
		
		for(HashMap<String, String> docMap : docList){
			JSONObject jsonContent = new JSONObject();
			/**
			 * Prepare title.
			 */
			String title = "เข้าเวร ".concat("\nแพทย์ : " + docMap.get("pre_name_th") + " ")
					.concat(docMap.get("first_name_th") + " ")
					.concat(docMap.get("last_name_th") + " ")
					.concat("\n เวลา : ");
//					.concat(docMap.get("start_datetime").split(" ")[1].split(":00.")[0])
//					.concat(" - " + docMap.get("end_datetime").split(" ")[1].split(":00.")[0]);
			
			/**
			 * Fetch time 
			 */
			String startRange = docMap.get("start_datetime").split(" ")[1], endRange = docMap.get("end_datetime").split(" ")[1];
			startRange = startRange.split(":")[0].concat(":").concat(startRange.split(":")[1]).concat(" น.");
			endRange = endRange.split(":")[0].concat(":").concat(endRange.split(":")[1]).concat(" น.");
			title = title.concat(startRange)
				.concat(" - ")
				.concat(endRange)
				.concat("\n สาขา : ")
				.concat(docMap.get("branch_name"));

			
			
			/**
			 * Parsing into the JSON Object.
			 */
			try {
				jsonContent.put("backgroundColor", "#445353")
					.put("id", docMap.get("workday_id"))
					.put("start", docMap.get("start_datetime"))
					.put("end", docMap.get("end_datetime"))
					.put("title", title);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonArr.put(jsonContent);
		}
//		System.out.print(jsonArr);
		
		/**
		 * Return the JSON response as application/json content type.
		 */
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {
			response.getWriter().write(jsonArr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Prepare doctor monthly schedule page.
	 * @author anubissmile
	 * @return String | Action result.
	 */
	public String getDoctorMonthlySchedule(){
		return SUCCESS;
	}

	/**
	 * Insert doctor schedule for same pattern.
	 * @author anubissmile
	 * @return String | Action result string.
	 */
	public String doctorTimeExecute(){
		DoctorData docData = new DoctorData();
		BranchData branchData = new BranchData();
		
		/**
		 * Get branch id.
		 */
		HashMap<String, String> branchMap = branchData.getBranchCode(docModel.getBranchStandID());
		docModel.setBranch_id(branchMap.get("branch_code"));
		
		/**
		 * Add doctor's workday by pattern
		 */
		if(docData.addDoctorWorkdayPattern(docModel, docTimeM) <= 0){
			addActionMessage("ไม่พบรายการสำหรับลงเวลาแพทย์");
			return INPUT;
		}else{
			return SUCCESS;
		}
	}
	
	/**
	 * doctorTimeExecute()'s validation.
	 * @author anubissmile
	 * @return String (INPUT|SUCCESS) | Action result.
	 */
	public String validateDoctorTimeExecute(){
		Validate v = new Validate();
		DateUtil dt = new DateUtil();
		int key = 0;
		String result = SUCCESS;
		
		/**
		 * Checking whether month is duplicates.
		 */
		if(Validate.isDuplicate(docTimeM.getWork_month())){
			addActionError("คุณเลือกเดือนซ้ำ!");
			result = INPUT;
		}
		
		/**
		 * Checking for null & empty string.
		 */
		for(String month : docTimeM.getWork_month()){
			if(!v.Check_String_notnull_notempty(month)){
				addActionError("เดือนไม่ควรเป็นค่าว่าง");
				result = INPUT;
			}
			
			if(!v.Check_String_notnull_notempty(docTimeM.getTime_in_mon().get(key)) || 
					!v.Check_String_notnull_notempty(docTimeM.getTime_out_mon().get(key)) ||
					!v.Check_String_notnull_notempty(docTimeM.getTime_in_tue().get(key)) ||
					!v.Check_String_notnull_notempty(docTimeM.getTime_out_tue().get(key)) ||
					!v.Check_String_notnull_notempty(docTimeM.getTime_in_wed().get(key)) ||
					!v.Check_String_notnull_notempty(docTimeM.getTime_out_wed().get(key)) ||
					!v.Check_String_notnull_notempty(docTimeM.getTime_in_thu().get(key)) ||
					!v.Check_String_notnull_notempty(docTimeM.getTime_out_thu().get(key)) ||
					!v.Check_String_notnull_notempty(docTimeM.getTime_in_fri().get(key)) ||
					!v.Check_String_notnull_notempty(docTimeM.getTime_out_fri().get(key)) ||
					!v.Check_String_notnull_notempty(docTimeM.getTime_in_sat().get(key)) ||
					!v.Check_String_notnull_notempty(docTimeM.getTime_out_sat().get(key)) ||
					!v.Check_String_notnull_notempty(docTimeM.getTime_in_sun().get(key)) ||
					!v.Check_String_notnull_notempty(docTimeM.getTime_out_sun().get(key))){
				
				addActionError(" โปรดกรอกช่องลงเวลาให้สมบุรณ์ ไม่ควรเว้นว่าง (หากไม่ต้องการกรอก ให้ใส่เป็น 00:00) ");
				result = INPUT;
			}
			
			/**
			 * Checking for time range overlap
			 */
			//Mon
			int timeDiff = dt.getMinuteDiff(
				docTimeM.getTime_in_mon().get(key).concat(":00"), 
				docTimeM.getTime_out_mon().get(key).concat(":00")
			);
			if(timeDiff < 0){
				addActionMessage("โปรดตรวจสอบช่องลงเวลาในวันจันทร์ของเดือน " + month + " อาจมีช่วงเวลาที่ทับซ้อนกัน");
				result = INPUT;
			}

			//Tue
			timeDiff = dt.getMinuteDiff(
				docTimeM.getTime_in_tue().get(key).concat(":00"), 
				docTimeM.getTime_out_tue().get(key).concat(":00")
			);
			if(timeDiff < 0){
				addActionMessage("โปรดตรวจสอบช่องลงเวลาในวันอังคารของเดือน " + month + " อาจมีช่วงเวลาที่ทับซ้อนกัน");
				result = INPUT;
			}

			//Wed
			timeDiff = dt.getMinuteDiff(
				docTimeM.getTime_in_wed().get(key).concat(":00"), 
				docTimeM.getTime_out_wed().get(key).concat(":00")
			);
			if(timeDiff < 0){
				addActionMessage("โปรดตรวจสอบช่องลงเวลาในวันพุธของเดือน " + month + " อาจมีช่วงเวลาที่ทับซ้อนกัน");
				result = INPUT;
			}

			//Thu
			timeDiff = dt.getMinuteDiff(
				docTimeM.getTime_in_thu().get(key).concat(":00"), 
				docTimeM.getTime_out_thu().get(key).concat(":00")
			);
			if(timeDiff < 0){
				addActionMessage("โปรดตรวจสอบช่องลงเวลาในวันพฤหัสบดีของเดือน " + month + " อาจมีช่วงเวลาที่ทับซ้อนกัน");
				result = INPUT;
			}

			//Fri
			timeDiff = dt.getMinuteDiff(
				docTimeM.getTime_in_fri().get(key).concat(":00"), 
				docTimeM.getTime_out_fri().get(key).concat(":00")
			);
			if(timeDiff < 0){
				addActionMessage("โปรดตรวจสอบช่องลงเวลาในวันศุกร์ของเดือน " + month + " อาจมีช่วงเวลาที่ทับซ้อนกัน");
				result = INPUT;
			}

			//Sat
			timeDiff = dt.getMinuteDiff(
				docTimeM.getTime_in_sat().get(key).concat(":00"), 
				docTimeM.getTime_out_sat().get(key).concat(":00")
			);
			if(timeDiff < 0){
				addActionMessage("โปรดตรวจสอบช่องลงเวลาในวันเสาร์ของเดือน " + month + " อาจมีช่วงเวลาที่ทับซ้อนกัน");
				result = INPUT;
			}

			//Sun
			timeDiff = dt.getMinuteDiff(
				docTimeM.getTime_in_sun().get(key).concat(":00"), 
				docTimeM.getTime_out_sun().get(key).concat(":00")
			);
			if(timeDiff < 0){
				addActionMessage("โปรดตรวจสอบช่องลงเวลาในวันอาทิตย์ของเดือน " + month + " อาจมีช่วงเวลาที่ทับซ้อนกัน");
				result = INPUT;
			}
			
			++key;
		}
		
		if(result.equals(INPUT)){
			return result;
		}
		return SUCCESS;
		
	}
	
	public String addDoctor() throws IOException, Exception{
		DoctorData docDB = new DoctorData();
		/**
		 * Fetch telephone type. 
		 */
		setTelType(docDB.getTelephoneTypeList());
		setScopeTreatmentMap(docDB.GetSocpeTreatment());
		return SUCCESS;
	}
	
	public String begin() throws Exception{
		DoctorData docData = new DoctorData();
		setDoctorList(docData.Get_DoctorStatusList());
		
		EmployeeData empdata1 = new EmployeeData();
		setBranchlist(empdata1.Get_branchList());
		return SUCCESS;
	}
	public String excute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		/**
		 * ADDRESS.
		 */
		AddressData addrData = new AddressData();
		List <AddressModel>addrlist = new ArrayList<AddressModel>();
		String[] 	addr_no = request.getParameterValues("docModel.addr_no"),
					addr_bloc = request.getParameterValues("docModel.addr_bloc"),
					addr_village = request.getParameterValues("docModel.addr_village"),
					addr_alley = request.getParameterValues("docModel.addr_alley"),
					addr_road = request.getParameterValues("docModel.addr_road"),
					addr_provinceid = request.getParameterValues("docModel.addr_provinceid"),
					addr_aumphurid = request.getParameterValues("docModel.addr_aumphurid"),
					addr_districtid = request.getParameterValues("docModel.addr_districtid"),
					addr_typeid = request.getParameterValues("docModel.addr_typeid"),
					addr_zipcode = request.getParameterValues("docModel.addr_zipcode");	
		
		int i = 0;
		
		for(String addr_list : addr_no){
			if(!addr_list.equals("") || !addr_bloc[i].equals("")|| !addr_village[i].equals("")|| !addr_alley[i].equals("")
					|| !addr_road[i].equals("")|| !addr_provinceid[i].equals("")|| !addr_districtid[i].equals("")|| !addr_aumphurid[i].equals("")){
				AddressModel addrModel = new AddressModel();
				addrModel.setAddr_no(addr_list);
				addrModel.setAddr_bloc(addr_bloc[i]);
				addrModel.setAddr_village(addr_village[i]);
				addrModel.setAddr_alley(addr_alley[i]);
				addrModel.setAddr_road(addr_road[i]);
				addrModel.setAddr_provinceid(addr_provinceid[i]);
				addrModel.setAddr_aumphurid(addr_aumphurid[i]);
				addrModel.setAddr_districtid(addr_districtid[i]);
				addrModel.setAddr_typeid(addr_typeid[i]);
				addrModel.setAddr_zipcode(addr_zipcode[i]);
				addrlist.add(addrModel);
				i++;
			}
		}
		if(addrlist.size()>0){
			docModel.setAddr_id(addrData.add_multi_address(addrlist));
		}
		/**
		 * UPLOAD PICTURE FILE.
		 */
		if(getPicProfileFileName() != null){
			String time = new DateUtil().curTime();
			String fName = new Encrypted().encrypt(docModel.getFirstname_en() + "-" + docModel.getLastname_en() + "-" + time).replaceAll("[-+.^:=/\\,]","");
			docModel.setProfile_pic(
					new Storage().file(getPicProfile(), getPicProfileContentType(), getPicProfileFileName())
						.storeAs("../Document/picture/profile/", fName)
						.getDestPath()
			);
			
		}
		/**
		 * TELEPHONE
		 */
		TelephoneData telData = new TelephoneData();
		docModel.setTel_id(telData.add_multi_telephone(telModel));


		DoctorData docData = new DoctorData();
		List <BranchModel> branchlist = new ArrayList<BranchModel>();
		String[] docbranch = request.getParameterValues("doctor_branch");
		if(docbranch!=null){
			for(String branch_list : docbranch){
				String branch_id = branch_list;
				BranchModel branchModel = new BranchModel();
				branchModel.setBranch_id(branch_id);
				branchlist.add(branchModel);
			}
		}
		if(branchlist.size()>0){
			docModel.setBranchID(docData.AddDoctorBranch(branchlist));
		}

		BookBankData bankData = new BookBankData();
		List <BookBankModel>bankList = new ArrayList<BookBankModel>();
		String[] account_num = request.getParameterValues("account_num");
		String[] account_name = request.getParameterValues("account_name");
		String[] bank_id = request.getParameterValues("bank_id");
		i = 0;
		for(String account : account_num){
			if(!account.equals("")){
				BookBankModel bankMo = new BookBankModel();
				bankMo.setBank_id(bank_id[i]);
				bankMo.setBookbank_no(account);
				bankMo.setBookbank_name(account_name[i]);
				bankList.add(bankMo);
				i++;
			}
		}
		if(bankList.size()>0){
			docModel.setBookBankId(bankData.add_multi_bookbank(bankList));
		}

		EducationData eduData = new EducationData();
		List <Person> eduList = new ArrayList<Person>();	
		String[] education_vocabulary_id = request.getParameterValues("education_vocabulary_id");
		String[] education_name = request.getParameterValues("education_name");
		String[] education_background = request.getParameterValues("educational_background");
		i = 0;
		for(String edu_name : education_name){
			if(!edu_name.equals("")){
				Person perModel = new Person();
				perModel.setEducation_th(edu_name);
				perModel.setEducational_background(education_background[i]);
				perModel.setEducation_vocabulary_id(Integer.parseInt(education_vocabulary_id[i]));
				eduList.add(perModel);
			}
			i++;
		}
		if(eduList.size()>0){
			docModel.setEdu_id(eduData.add_multi_edu(eduList));
		} 
		
		WorkHistoryData workData = new WorkHistoryData();
		List <DoctorModel> workList = new ArrayList<DoctorModel>();
		String[] location = request.getParameterValues("docModel.location"),
				work_type = request.getParameterValues("docModel.work_type"),
				position = request.getParameterValues("docModel.position"),
				salary = request.getParameterValues("docModel.salary"),
				start_date = request.getParameterValues("docModel.start_date"),
				end_date = request.getParameterValues("docModel.end_date"),
				remark_message = request.getParameterValues("docModel.remark_message");	
		i=0;
		for(String lo : location){
			
			DoctorModel docM = new DoctorModel();
			
			String sdate = start_date[i];
			String edate = end_date[i];
			if(!sdate.equals("")){
				String[] parts = sdate.split("-");
				sdate = parts[2]+"-"+parts[1]+"-"+parts[0];
			}
			if(!edate.equals("")){
				String[] parts = edate.split("-");
				edate = parts[2]+"-"+parts[1]+"-"+parts[0];
			}
			
			docM.setLocation(lo);
			docM.setWork_type(work_type[i]);
			docM.setPosition(position[i]);
			docM.setSalary(salary[i]);
			docM.setRemark_message(remark_message[i]);
			docM.setStart_date(sdate);
			docM.setEnd_date(edate);
			
			workList.add(docM);
			i++;
		}
		
		if(workList.size()>0){
			docModel.setWork_history_id(workData.add_multi_work(workList));
		}
		
		/**
		 * Set birth date TH/EN.
		 */
		String birthDateEn = request.getParameter("birthdate_eng");
		String birthDateTh = request.getParameter("birthdate_th"),
				hireddate = request.getParameter("hireddate");
		System.out.println("hireddate : "+hireddate);
		String BirthDate="";
		if(!birthDateEn.equals("")){
			String[] parts = birthDateEn.split("-");
			BirthDate = parts[2]+"-"+parts[1]+"-"+parts[0];
		}else if(!birthDateTh.equals("")){
			String[] parts = birthDateTh.split("-");
			int convertDate =  Integer.parseInt(parts[2]);
			convertDate -= 543;
			BirthDate = convertDate+"-"+parts[1]+"-"+parts[0];
		}
		docModel.setBirth_date(BirthDate);
		
		/**
		 * Set hire date.
		 */
		String hireddateen = request.getParameter("hireddate");
		String hireddateth = request.getParameter("hireddate_th");
		String hiredDate ="";
		if(!hireddateen.equals("")){
			String[] parts = hireddateen.split("-");
			BirthDate = parts[2]+"-"+parts[1]+"-"+parts[0];
		}else if(!hireddateth.equals("")){
			String[] parts = hireddateth.split("-");
			int convertDate =  Integer.parseInt(parts[2]);
			convertDate -= 543;
			hiredDate = convertDate+"-"+parts[1]+"-"+parts[0];
		}
		docModel.setHireDate(hiredDate);
/*		String hireDate = request.getParameter("hireddate");
		DateUtil d = new DateUtil();
		if(new ldc.util.Validate().Check_String_notnull_notempty(hireDate)){
			hireDate = d.convertDateSpecificationPattern("dd-mm-YYYY", "YYYY-mm-dd", hireDate, false) + " 00:00:00";
			docModel.setHireDate(hireDate);
		}else{
			*//**
			 * Add to default & prevent to null.
			 *//*
			docModel.setHireDate("0000-00-00 00:00:00");
		}
		*/

		/**
		 * Add doctor
		 */
		int doc_id = docData.AddDoctor(docModel);
		setDocID(Integer.toString(doc_id));
		/**
		 * doctor_treatment
		 */
		docData.insertDoctorTreatment(docModel,doc_id);		
		/**
		 * Make MGR branch list
		 */
		if(doc_id>0){
			List <BranchModel> mgrbranchlist = new ArrayList<BranchModel>();
			String[] MngBranch = request.getParameterValues("doctor_boss_branch");
			
			if(MngBranch!=null){
				i = 0;
				for(String mgnBranch : MngBranch){ 
					i++;
					BranchModel branchModel = new BranchModel();
					branchModel.setDoctor_id(doc_id);
					branchModel.setBranch_id(mgnBranch);
					mgrbranchlist.add(branchModel);
				}
				docData.UpdateMgrBranch(mgrbranchlist);
			}		
			
			return SUCCESS;
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * Fetch doctor detail.
	 * @return String
	 */
	public String getDoctorDetail(){
		//DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
		//System.out.println("Start GetDoctorDetail ---------------------- "+ dateFormat.format(new Date())); 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if(request.getParameter("d")!=null){
			doctor_id = Integer.parseInt(request.getParameter("d"));
		}else if(getDocID()!=null){
			doctor_id = Integer.parseInt(getDocID());
		} else {
			doctor_id =  (Integer) session.getAttribute("doc_id");
			session.removeAttribute("doc_id");
		}
		BranchData branchData = new BranchData();
		DoctorData docData = new DoctorData();
		AddressData addrData = new AddressData();
		TelephoneData telData = new TelephoneData();
		BookBankData bankData = new BookBankData();
		PatientData pData = new PatientData();
		Pre_nameData pnameData = new Pre_nameData();
		WorkHistoryData workData = new WorkHistoryData();
		EducationData eduData = new EducationData();
		try {
			
			docModel = docData.Get_DoctorDetailStatus(doctor_id);
			docModel.setBirth_date_en(docModel.getBirth_date());
			String birthDateTh  = docModel.getBirth_date();
			if(new Validate().Check_String_notnull_notempty(birthDateTh)){
				String[] parts = birthDateTh.split("-");
				int convertDate =  Integer.parseInt(parts[0]);
				convertDate += 543;
				birthDateTh = parts[2]+"-"+parts[1]+"-"+convertDate;
				docModel.setBirth_date(birthDateTh);
			}
			String HireDateth  = docModel.getHireDate();
			if(new Validate().Check_String_notnull_notempty(HireDateth)){
				String[] parts = HireDateth.split("-");
				int convertDate =  Integer.parseInt(parts[0]);
				convertDate += 543;
				HireDateth = parts[2]+"-"+parts[1]+"-"+convertDate;
				docModel.setHired_date(HireDateth);
			}
			//System.out.println("-get data doctor success " + dateFormat.format(new Date()));
			
			
			setBranchStandardList(docData.getBranchStandard(doctor_id));
			docModel.setCheckSize(docData.branchMgrCheckSize(doctor_id));
			branchList = branchData.get_doctor_branch_detail(doctor_id);
			request.setAttribute("branchList", branchList);
			//System.out.println("-get branch success "+dateFormat.format(new Date()));
			
			branchMGRList = branchData.get_mgr_branch_detail(docModel.getBranchID());
			request.setAttribute("branchMGRList", branchMGRList);
			//System.out.println("-get mgr branch success "+dateFormat.format(new Date()));
			
			AddrList = addrData.getMultiAddr(docModel.getAddr_id());
			request.setAttribute("addressList", AddrList);
			//System.out.println("-get addrlist success "+dateFormat.format(new Date()));
			
			/**
			 * Get get multiple telephone list.
			 */
			telList = telData.get_telList(docModel.getTel_id());
			request.setAttribute("telList", telList);
			//System.out.println("-get tel success "+dateFormat.format(new Date()));

			/**
			 * Get emergency telephone.
			 */
			emTelModel = telData.getEmergencyTelById(docModel.getTel_id());
			
			bankList = bankData.get_bookBank_detail(docModel.getBookBankId());
			request.setAttribute("bankList", bankList);
			//System.out.println("-get bank success "+dateFormat.format(new Date()));
			
			pList = pData.get_identification_type(docModel.getIdentification_type());
			request.setAttribute("pList", pList);
		//	System.out.println("-get iden success "+dateFormat.format(new Date()));
			
			pnameList = pnameData.select_pre_name(docModel.getPre_name_id(), "", "");
			request.setAttribute("pnameList", pnameList);
			//System.out.println("-get pre name success "+dateFormat.format(new Date()));
			
			workList = workData.getMultiWork(docModel.getWork_history_id());
			request.setAttribute("workList", workList);
			//System.out.println("-get work success "+dateFormat.format(new Date()));
			
			eduList = eduData.get_multi_edu(docModel.getEdu_id());
			request.setAttribute("eduList", eduList);
			request.setAttribute("titleID", docModel.getTitle());
			//System.out.println("get finish ---------------------- "+dateFormat.format(new Date()));

			/**
			 * Fetch telephone type.
			 */
			DoctorData docDB = new DoctorData();
			setTelType(docDB.getTelephoneTypeList());
			setScopeTreatmentMap(docDB.GetSocpeTreatment());
			return SUCCESS;
		} catch (IOException e) {
			return ERROR;
		} catch (Exception e) {
			return ERROR;
		}
	}
	

	/**
	 * Update doctor.
	 * @return
	 * @throws Exception 
	 */
	public String updateDoctor() throws Exception{
		//DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
//		System.out.println("Start update ----------------"+ dateFormat.format(new Date())); 
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		AddressData addrData = new AddressData();
		TelephoneData telData = new TelephoneData();
		DoctorData docData = new DoctorData();
		BookBankData bankData = new BookBankData();
		WorkHistoryData workData = new WorkHistoryData();
		EducationData eduData = new EducationData();

		List <AddressModel>addrlist = new ArrayList<AddressModel>();
		List <BranchModel> branchlist = new ArrayList<BranchModel>();
		List <BranchModel> mgrbranchlist = new ArrayList<BranchModel>();
		List <BookBankModel>bankList = new ArrayList<BookBankModel>();
		List<DoctorModel> workList = new ArrayList<DoctorModel>();
		List <Person> eduList = new ArrayList<Person>();

		/**
		 * Address.
		 */
		String[]	addr_no = request.getParameterValues("docModel.addr_no"),
				addr_bloc = request.getParameterValues("docModel.addr_bloc"),
				addr_village = request.getParameterValues("docModel.addr_village"),
				addr_alley = request.getParameterValues("docModel.addr_alley"),
				addr_road = request.getParameterValues("docModel.addr_road"),
				addr_provinceid = request.getParameterValues("docModel.addr_provinceid"),
				addr_aumphurid = request.getParameterValues("docModel.addr_aumphurid"),
				addr_districtid = request.getParameterValues("docModel.addr_districtid"),
				addr_typeid = request.getParameterValues("docModel.addr_typeid"),
				addr_zipcode = request.getParameterValues("docModel.addr_zipcode");
	
		String[] account_num = request.getParameterValues("account_num");
		String[] account_name = request.getParameterValues("account_name");
		String[] bank_id = request.getParameterValues("bank_id");
		
		String[] docbranch = request.getParameterValues("doctor_branch");
		String[] MngBranch = request.getParameterValues("doctor_boss_branch");
		
		int i = 0;
		for(String addr_list : addr_no){
			if(!addr_list.equals("") || !addr_bloc[i].equals("")|| !addr_village[i].equals("")|| !addr_alley[i].equals("")
					|| !addr_road[i].equals("")|| !addr_provinceid[i].equals("")|| !addr_districtid[i].equals("")|| !addr_aumphurid[i].equals("")){
				AddressModel addrModel = new AddressModel();
				addrModel.setAddr_no(addr_list);
				addrModel.setAddr_bloc(addr_bloc[i]);
				addrModel.setAddr_village(addr_village[i]);
				addrModel.setAddr_alley(addr_alley[i]);
				addrModel.setAddr_road(addr_road[i]);
				addrModel.setAddr_provinceid(addr_provinceid[i]);
				addrModel.setAddr_aumphurid(addr_aumphurid[i]);
				addrModel.setAddr_districtid(addr_districtid[i]);
				addrModel.setAddr_typeid(addr_typeid[i]);
				addrModel.setAddr_zipcode(addr_zipcode[i]);
				addrlist.add(addrModel);
				
			}
			i++;
		}

		if(addrlist.size()>0){
			addrData.del_multi_address(docModel.getAddr_id());
			addrData.add_multi_address(addrlist,docModel.getAddr_id());
		}else{
			addrData.del_multi_address(docModel.getAddr_id());
		}
		//System.out.println("-addr updated"+dateFormat.format(new Date()));
		
		/**
		 * Telephone.
		 */
		telData.updateMultiTelephone(docModel.getTel_id(), telModel);
		
		/**
		 * Branch
		 */
		//System.out.println("-tel updated"+dateFormat.format(new Date()));
		if(docbranch!=null){
			for(String branch_list : docbranch){
				String branch_id = branch_list ;
				BranchModel branchModel = new BranchModel();
				branchModel.setBranch_id(branch_id);
				branchlist.add(branchModel);
			}
		}
		if(branchlist.size()>0){
			docData.del_doctor_branch(docModel.getBranchID());
			docData.AddDoctorBranch(branchlist,docModel.getBranchID());
		}else{
			docData.del_doctor_branch(docModel.getBranchID());
		}
		//System.out.println("-branch updated"+dateFormat.format(new Date()));
		if(MngBranch!=null){
			i = 0;
			for(String mgnBranch : MngBranch){ 
				i++;
				BranchModel branchModel = new BranchModel();
				branchModel.setDoctor_id(docModel.getDoctorID());
				branchModel.setBranch_id(mgnBranch);
				mgrbranchlist.add(branchModel);
			}
			docData.UpdateMgrBranch(mgrbranchlist,docModel.getDoctorID());
		}		
		
		i = 0;
		for(String account : account_num){
			if(!account.equals("")){
				BookBankModel bankMo = new BookBankModel();
				bankMo.setBank_id(bank_id[i]);
				bankMo.setBookbank_no(account);
				bankMo.setBookbank_name(account_name[i]);
				bankList.add(bankMo);
				
			}
			i++;
		}
		if(bankList.size()>0){
			bankData.del_multi_bookbank(docModel.getBookBankId());
			bankData.add_multi_bookbank(bankList, docModel.getBookBankId());
		}else{
			bankData.del_multi_bookbank(docModel.getBookBankId());
		}	
		//System.out.println("-bank updated"+dateFormat.format(new Date()));
		String[] education_vocabulary_id = request.getParameterValues("education_vocabulary_id");
		String[] education_name = request.getParameterValues("education_name");
		i = 0;
		for(String edu_name : education_name){
			if(!edu_name.equals("")){
				Person perModel = new Person();
				perModel.setEducation_th(edu_name);
				perModel.setEducation_vocabulary_id(Integer.parseInt(education_vocabulary_id[i]));
				eduList.add(perModel);
			}
			i++;
		}
		if(eduList.size()>0){
			eduData.del_multi_edu(docModel.getEdu_id());
			eduData.add_multi_edu(eduList, docModel.getEdu_id());
		} else{
			eduData.del_multi_edu(docModel.getEdu_id());
		}
		//System.out.println("-edu updated"+dateFormat.format(new Date()));
		
		String[] location = request.getParameterValues("docModel.location"),
				work_type = request.getParameterValues("docModel.work_type"),
				position = request.getParameterValues("docModel.position"),
				salary = request.getParameterValues("docModel.salary"),
				start_date = request.getParameterValues("docModel.start_date"),
				end_date = request.getParameterValues("docModel.end_date"),
				remark_message = request.getParameterValues("docModel.remark_message");	
		i=0;
		for(String lo : location){
			if(!lo.equals("")){
				String sdate = start_date[i];
				String edate = end_date[i];
				if(!sdate.equals("")){
					String[] parts = sdate.split("-");
					sdate = parts[2]+"-"+parts[1]+"-"+parts[0];
				}
				if(!edate.equals("")){
					String[] parts = edate.split("-");
					edate = parts[2]+"-"+parts[1]+"-"+parts[0];
				}
				DoctorModel docM = new DoctorModel();
				docM.setLocation(location[i]);
				docM.setWork_type(work_type[i]);
				docM.setPosition(position[i]);
				docM.setSalary(salary[i]);
				docM.setRemark_message(remark_message[i]);
				docM.setStart_date(sdate);
				docM.setEnd_date(edate);
				
				workList.add(docM);
			}
			i++;
			
		}
		if(workList.size()>0){
			workData.del_multi_work(docModel.getWork_history_id());
			workData.add_multi_work(workList,docModel.getWork_history_id());
		}else{
			workData.del_multi_work(docModel.getWork_history_id());
		}
		//System.out.println("-work updated"+dateFormat.format(new Date()));
		String birthDateEn = request.getParameter("birthdate_eng");
		String birthDateTh = request.getParameter("birthdate_th");
		String BirthDate="";
		
		if(!birthDateEn.equals("")){
			String[] parts = birthDateEn.split("-");
			BirthDate = parts[2]+"-"+parts[1]+"-"+parts[0];
		}else if(!birthDateTh.equals("")){
			String[] parts = birthDateTh.split("-");
			int convertDate =  Integer.parseInt(parts[2]);
			convertDate -= 543;
			BirthDate = convertDate+"-"+parts[1]+"-"+parts[0];
		}
		docModel.setBirth_date(BirthDate);
		docData.UpdateDoctor(docModel);

		session.setAttribute("doc_id", docModel.getDoctorID()); 
		//System.out.println("Update success ------------------"+dateFormat.format(new Date()));
		if(docModel.getDoctorID() > 0){
			return SUCCESS;
		}else{
			return INPUT;
		}
	}
	/**
	 * Update doctor by id.
	 * @return
	 * @throws Exception 
	 */
	public String updateDoctorById() throws Exception{
		//DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
//		System.out.println("Start update ----------------"+ dateFormat.format(new Date())); 
		
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		AddressData addrData = new AddressData();
		TelephoneData telData = new TelephoneData();
		DoctorData docData = new DoctorData();
		BookBankData bankData = new BookBankData();
		WorkHistoryData workData = new WorkHistoryData();
		EducationData eduData = new EducationData();
		Person doctorpicdel = new Person();
		DoctorData doctorData = new DoctorData();
		doctorpicdel = doctorData.editDoctor(Integer.toString(docModel.getDoctorID()));
		List <AddressModel>addrlist = new ArrayList<AddressModel>();
		List <BranchModel> branchlist = new ArrayList<BranchModel>();
		List <BranchModel> mgrbranchlist = new ArrayList<BranchModel>();
		List <BookBankModel>bankList = new ArrayList<BookBankModel>();
		List<DoctorModel> workList = new ArrayList<DoctorModel>();
		List <Person> eduList = new ArrayList<Person>();
		/**
		 * UPLOAD PICTURE FILE.
		 */
		if(getPicProfileFileName() != null){
			String time = new DateUtil().curTime();
			String fName = new Encrypted().encrypt(docModel.getFirstname_en() + "-" + docModel.getLastname_en() + "-" + time).replaceAll("[-+.^:=/\\,]","");
			docModel.setProfile_pic(
					new Storage().file(getPicProfile(), getPicProfileContentType(), getPicProfileFileName())
						.storeAs("../Document/picture/profile/", fName)
						.getDestPath()
			);
			
		}
		
		/**
		 * DELETE OLD FILE PICTURE WHEN HAVE NEW PROFILE PICTURE.
		 */
		if(!docModel.getProfile_pic().equals(doctorpicdel.getProfile_pic())){
			// Delete old file
			new Storage().delete(doctorpicdel.getProfile_pic());
		}
		/**
		 * Address.
		 */
		String[]	addr_no = request.getParameterValues("docModel.addr_no"),
				addr_bloc = request.getParameterValues("docModel.addr_bloc"),
				addr_village = request.getParameterValues("docModel.addr_village"),
				addr_alley = request.getParameterValues("docModel.addr_alley"),
				addr_road = request.getParameterValues("docModel.addr_road"),
				addr_provinceid = request.getParameterValues("docModel.addr_provinceid"),
				addr_aumphurid = request.getParameterValues("docModel.addr_aumphurid"),
				addr_districtid = request.getParameterValues("docModel.addr_districtid"),
				addr_typeid = request.getParameterValues("docModel.addr_typeid"),
				addr_zipcode = request.getParameterValues("docModel.addr_zipcode");
	
		String[] account_num = request.getParameterValues("account_num");
		String[] account_name = request.getParameterValues("account_name");
		String[] bank_id = request.getParameterValues("bank_id");
		
		String[] docbranch = request.getParameterValues("doctor_branch");
		String[] MngBranch = request.getParameterValues("doctor_boss_branch");
		
		int i = 0;
		for(String addr_list : addr_no){
			if(!addr_list.equals("") || !addr_bloc[i].equals("")|| !addr_village[i].equals("")|| !addr_alley[i].equals("")
					|| !addr_road[i].equals("")|| !addr_provinceid[i].equals("")|| !addr_districtid[i].equals("")|| !addr_aumphurid[i].equals("")){
				AddressModel addrModel = new AddressModel();
				addrModel.setAddr_no(addr_list);
				addrModel.setAddr_bloc(addr_bloc[i]);
				addrModel.setAddr_village(addr_village[i]);
				addrModel.setAddr_alley(addr_alley[i]);
				addrModel.setAddr_road(addr_road[i]);
				addrModel.setAddr_provinceid(addr_provinceid[i]);
				addrModel.setAddr_aumphurid(addr_aumphurid[i]);
				addrModel.setAddr_districtid(addr_districtid[i]);
				addrModel.setAddr_typeid(addr_typeid[i]);
				addrModel.setAddr_zipcode(addr_zipcode[i]);
				addrlist.add(addrModel);
				
			}
			i++;
		}

		if(addrlist.size()>0){
			addrData.del_multi_address(docModel.getAddr_id());
			addrData.add_multi_address(addrlist,docModel.getAddr_id());
		}else{
			addrData.del_multi_address(docModel.getAddr_id());
		}
		//System.out.println("-addr updated"+dateFormat.format(new Date()));
		
		/**
		 * Telephone.
		 */
		telData.updateMultiTelephone(docModel.getTel_id(), telModel);
		
		/**
		 * Branch
		 */
		//System.out.println("-tel updated"+dateFormat.format(new Date()));
		if(docbranch!=null){
			for(String branch_list : docbranch){
				String branch_id = branch_list ;
				BranchModel branchModel = new BranchModel();
				branchModel.setBranch_id(branch_id);
				branchlist.add(branchModel);
			}
		}
		if(branchlist.size()>0){
			docData.del_doctor_branch(docModel.getBranchID());
			docData.AddDoctorBranch(branchlist,docModel.getBranchID());
		}else{
			docData.del_doctor_branch(docModel.getBranchID());
		}
		//System.out.println("-branch updated"+dateFormat.format(new Date()));
		if(MngBranch!=null){
			i = 0;
			for(String mgnBranch : MngBranch){ 
				i++;
				BranchModel branchModel = new BranchModel();
				branchModel.setDoctor_id(docModel.getDoctorID());
				branchModel.setBranch_id(mgnBranch);
				mgrbranchlist.add(branchModel);
			}
			docData.UpdateMgrBranch(mgrbranchlist,docModel.getDoctorID());
		}		
		
		i = 0;
		for(String account : account_num){
			if(!account.equals("")){
				BookBankModel bankMo = new BookBankModel();
				bankMo.setBank_id(bank_id[i]);
				bankMo.setBookbank_no(account);
				bankMo.setBookbank_name(account_name[i]);
				bankList.add(bankMo);
				
			}
			i++;
		}
		if(bankList.size()>0){
			bankData.del_multi_bookbank(docModel.getBookBankId());
			bankData.add_multi_bookbank(bankList, docModel.getBookBankId());
		}else{
			bankData.del_multi_bookbank(docModel.getBookBankId());
		}	
		//System.out.println("-bank updated"+dateFormat.format(new Date()));
		String[] education_vocabulary_id = request.getParameterValues("education_vocabulary_id");
		String[] education_name = request.getParameterValues("education_name");
		String[] education_background = request.getParameterValues("educational_background");
		i = 0;
		for(String edu_name : education_name){
			if(!edu_name.equals("")){
				Person perModel = new Person();
				perModel.setEducation_th(edu_name);
				perModel.setEducational_background((education_background[i]));
				perModel.setEducation_vocabulary_id(Integer.parseInt(education_vocabulary_id[i]));
				eduList.add(perModel);
			}
			i++;
		}
		if(eduList.size()>0){
			eduData.del_multi_edu(docModel.getEdu_id());
			eduData.add_multi_edu(eduList, docModel.getEdu_id());
		} else{
			eduData.del_multi_edu(docModel.getEdu_id());
		}
		//System.out.println("-edu updated"+dateFormat.format(new Date()));
		
		String[] location = request.getParameterValues("docModel.location"),
				work_type = request.getParameterValues("docModel.work_type"),
				position = request.getParameterValues("docModel.position"),
				salary = request.getParameterValues("docModel.salary"),
				start_date = request.getParameterValues("docModel.start_date"),
				end_date = request.getParameterValues("docModel.end_date"),
				remark_message = request.getParameterValues("docModel.remark_message");	
		i=0;
		for(String lo : location){
			if(!lo.equals("")){
				String sdate = start_date[i];
				String edate = end_date[i];
				if(!sdate.equals("")){
					String[] parts = sdate.split("-");
					sdate = parts[2]+"-"+parts[1]+"-"+parts[0];
				}
				if(!edate.equals("")){
					String[] parts = edate.split("-");
					edate = parts[2]+"-"+parts[1]+"-"+parts[0];
				}
				DoctorModel docM = new DoctorModel();
				docM.setLocation(location[i]);
				docM.setWork_type(work_type[i]);
				docM.setPosition(position[i]);
				docM.setSalary(salary[i]);
				docM.setRemark_message(remark_message[i]);
				docM.setStart_date(sdate);
				docM.setEnd_date(edate);
				
				workList.add(docM);
			}
			i++;
			
		}
		if(workList.size()>0){
			workData.del_multi_work(docModel.getWork_history_id());
			workData.add_multi_work(workList,docModel.getWork_history_id());
		}else{
			workData.del_multi_work(docModel.getWork_history_id());
		}
		//System.out.println("-work updated"+dateFormat.format(new Date()));
		String birthDateEn = request.getParameter("birthdate_eng");
		String birthDateTh = request.getParameter("birthdate_th");
		String BirthDate="";
		
		if(!birthDateEn.equals("")){
			String[] parts = birthDateEn.split("-");
			BirthDate = parts[2]+"-"+parts[1]+"-"+parts[0];
		}else if(!birthDateTh.equals("")){
			String[] parts = birthDateTh.split("-");
			int convertDate =  Integer.parseInt(parts[2]);
			convertDate -= 543;
			BirthDate = convertDate+"-"+parts[1]+"-"+parts[0];
		}
		docModel.setBirth_date(BirthDate);
		/**
		 * Set hire date.
		 */
		String hireddateen = request.getParameter("hireddate");
		String hireddateth = request.getParameter("hireddate_th");
		String hiredDate ="";
		if(!hireddateen.equals("")){
			String[] parts = hireddateen.split("-");
			BirthDate = parts[2]+"-"+parts[1]+"-"+parts[0];
		}else if(!hireddateth.equals("")){
			String[] parts = hireddateth.split("-");
			int convertDate =  Integer.parseInt(parts[2]);
			convertDate -= 543;
			hiredDate = convertDate+"-"+parts[1]+"-"+parts[0];
		}
		

		docModel.setHireDate(hiredDate);
		docData.UpdateDoctor(docModel);
		if(docModel.getTitle() != doctorpicdel.getChecktitle()){
			docData.DeleteDoctorTreatmentWithUpdateDoctorScope(docModel.getDoctorID());
			docData.insertDoctorTreatmentWithUpdateDoctorScope(docModel.getTitle(),docModel.getDoctorID());	
			
		}
		session.setAttribute("doc_id", docModel.getDoctorID()); 
		//System.out.println("Update success ------------------"+dateFormat.format(new Date()));
		return SUCCESS;
	}
	
	public String DocTime_begin() {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession();
		int doctor_id = 0;
		docTimeM = new DoctTimeModel();
		if(request.getParameter("d")!=null){
			doctor_id = Integer.parseInt(request.getParameter("d"));
			docTimeM.setDoctorID(doctor_id);
		}else{
			doctor_id =  (Integer) session.getAttribute("doc_id");
			session.removeAttribute("doc_id");
		}
		BranchData branchData = new BranchData();
		List<BranchModel> branchList = new ArrayList<BranchModel>();
		
		branchList = branchData.get_doctor_branch_detail(doctor_id);
		request.setAttribute("branchList", branchList);
		
		return SUCCESS;
	}
	public String DocTime_excute() {
		HttpServletRequest request = ServletActionContext.getRequest(); 
		
		DoctorData docData = new DoctorData();
		
			docData.delDoctorTime(docTimeM.getDoctorID(), docTimeM.getBranch_id());
			docData.addDoctorTime(docTimeM);
		
		BranchData branchData = new BranchData();
		List<BranchModel> branchList = new ArrayList<BranchModel>();
		
		branchList = branchData.get_doctor_branch_detail(docTimeM.getDoctorID());
		request.setAttribute("branchList", branchList);
		request.setAttribute("d", docTimeM.getDoctorID());
		return SUCCESS;
	}
	public String Doctype_begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		DoctorTypeData docTypeData = new DoctorTypeData();
		List<DoctorModel> docTypeList = docTypeData.select_DocType("", "", "", "");
		request.setAttribute("doctorTypeList", docTypeList); 
		 
		return SUCCESS;
	}
	public String Doctype_excute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		DoctorTypeData docTypeData = new DoctorTypeData();
		String saveEdu 	= 	request.getParameter("saveEdu");
		String updatEdu 	=	request.getParameter("updatEdu");
		String deletEdu 	=	request.getParameter("deletEdu");
		
		if(saveEdu!=null){
			docTypeData.add_DocType(docModel);
		}
		if(updatEdu!=null){
			String position_id = request.getParameter("EduIDUp") ,
					position_name_th= request.getParameter("EduTHUp") ,
					position_name_en= request.getParameter("EduENUp"),
					position_name_short	= request.getParameter("EduShUp");
			docTypeData.UpdateDocType(position_id, position_name_th, position_name_en, position_name_short);
		}
		if(deletEdu!=null){
			String position_id = request.getParameter("EduIDdel");
			Boolean delStatus = docTypeData.DeleteDocType(position_id);
			if(delStatus){
				request.setAttribute("del_status", "Deleted !");
			}else { request.setAttribute("del_status", "Deleted Not Success!"); }
			
		}
		List<DoctorModel> docTypeList = docTypeData.select_DocType("", "", "", "");
		request.setAttribute("doctorTypeList", docTypeList); 
	return SUCCESS;
	}

	
	/**
	 * GETTER & SETTER ZONE.
	 */
	public DoctorModel getDocModel() {
		return docModel;
	}
	public void setDocModel(DoctorModel docModel) {
		this.docModel = docModel;
	}
	

	public DoctTimeModel getDocTimeM() {
		return docTimeM;
	}
	public void setDocTimeM(DoctTimeModel docTimeM) {
		this.docTimeM = docTimeM;
	}

	public HashMap<String, String> getTelType() {
		return telType;
	}

	public void setTelType(HashMap<String, String> telType) {
		this.telType = telType;
	}

	public TelephoneModel getTelModel() {
		return telModel;
	}

	public void setTelModel(TelephoneModel telModel) {
		this.telModel = telModel;
	}

	public String getBranchStandard() throws IOException, Exception{
		
		DoctorData docdata = new DoctorData();
		BranchData branchdata = new BranchData();
		setBranchlist(branchdata.Get_branchList());
		/**
		 * get doc id.
		 */
		setBranchStandardList(docdata.getBranchStandard(docModel.getDoctorID()));
		
		return SUCCESS;
	}
	public String addBranchStandard() throws IOException, Exception{
		DoctorData docdata = new DoctorData();
		if(docdata.branchStandardCheck(docModel)){
			docdata.addBranchStandard(docModel);
			BranchData branchdata = new BranchData();
			setBranchlist(branchdata.Get_branchList());
		}
		else{
			addActionError("สาขานี้ถูกเพิ่มไปแล้ว!");
			BranchData branchdata = new BranchData();
			setBranchlist(branchdata.Get_branchList());
			setBranchStandardList(docdata.getBranchStandard(docModel.getDoctorID()));
			return INPUT;
		}
		
		/**
		 * redirect
		 */
		HttpServletRequest request =  ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			new Servlet().redirect(request, response, "getBranchStandard-" + docModel.getDoctorID());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return INPUT;
	}
	public String DeleteBranchStandard(){
		DoctorData docdata = new DoctorData();
		docdata.DeleteBranchStandard(docModel);
		/**
		 * redirect
		 */
		HttpServletRequest request =  ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			new Servlet().redirect(request, response, "getBranchStandard-" + docModel.getDoctorID());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return INPUT;
	}
	public String UpadteBranchStandard(){
		DoctorData docdata = new DoctorData();
		docdata.UpdateBranchStandard(docModel);
		/**
		 * redirect
		 */
		HttpServletRequest request =  ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			new Servlet().redirect(request, response, "getBranchStandard-" + docModel.getDoctorID());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return INPUT;
	}	
	public String getBranchMgr() throws IOException, Exception{
		
		DoctorData docdata = new DoctorData();
		BranchData branchdata = new BranchData();
		setBranchlist(branchdata.Get_branchList());
		/**
		 * get doc id.
		 */
		setBranchMgrList(docdata.getBranchMgr(docModel.getDoctorID()));
		
		return SUCCESS;
	}
	public String addBranchMgr() throws IOException, Exception{
		DoctorData docdata = new DoctorData();
		int i = docdata.branchMgrCheckSize(docModel.getDoctorID());
		if( i<2 && docdata.branchMgrCheck(docModel) ){
			docdata.addBranchMgr(docModel);
			BranchData branchdata = new BranchData();
			setBranchlist(branchdata.Get_branchList());
		}else if(docdata.branchMgrCheck(docModel)){

						addActionError("จำนวนสาขาเต็มแล้ว");						
						BranchData branchdata = new BranchData();
						setBranchlist(branchdata.Get_branchList());
						setBranchMgrList(docdata.getBranchMgr(docModel.getDoctorID()));
						return INPUT;	
		}
		else{
			addActionError("สาขานี้ถูกเพิ่มไปแล้ว!");
			BranchData branchdata = new BranchData();
			setBranchlist(branchdata.Get_branchList());
			setBranchMgrList(docdata.getBranchMgr(docModel.getDoctorID()));
			return INPUT;
			
		}
		
		/**
		 * redirect
		 */
		HttpServletRequest request =  ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			new Servlet().redirect(request, response, "getBranchMgr-" + docModel.getDoctorID());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return INPUT;
	}
	public String DeleteBranchMgr(){
		DoctorData docdata = new DoctorData();
		docdata.DeleteBranchMgr(docModel);
		/**
		 * redirect
		 */
		HttpServletRequest request =  ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			new Servlet().redirect(request, response, "getBranchMgr-" + docModel.getDoctorID());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return INPUT;
	}
	public String UpadteBranchMgr(){
		DoctorData docdata = new DoctorData();
		docdata.UpadteBranchMgr(docModel);
		/**
		 * redirect
		 */
		HttpServletRequest request =  ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			new Servlet().redirect(request, response, "getBranchMgr-" + docModel.getDoctorID());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return INPUT;
	}	
	public String doctorsearch() throws IOException, Exception{		
		DoctorData docData = new DoctorData();
		if(docModel.getBranch_id().isEmpty() && docModel.getBranchStandID().isEmpty()){
			setDoctorList(docData.Get_DoctorSearchList(docModel.getWork_status()));
		}else{
			setDoctorList(docData.Get_DoctorSearchBranchList(docModel.getWork_status(),docModel.getBranch_id(),docModel.getBranchStandID()));

		}		
		EmployeeData empdata1 = new EmployeeData();
		setBranchlist(empdata1.Get_branchList());
		return SUCCESS;
	}
	/**
	 * Scope Dentist
	 */
	public String getScopeDentist(){
		DoctorData docData = new DoctorData();
		setScopeDentistlist(docData.getScopeDentist());
		return SUCCESS;
	}
	public String addScopeDentist(){
		DoctorData docData = new DoctorData();
		docData.addScopeDentist(scopeModel);
		return SUCCESS;
	}
	public String DeleteScopeDentist(){
		DoctorData docData = new DoctorData();
		docData.DelectScopeDentist(scopeModel);
		return SUCCESS;
	}
	public String UpdateScopeDentist(){
		DoctorData docData = new DoctorData();
/*		setTreatMentList(docData.getTreatmentList());*/
		setPositionTreatmentList(docData.getPositionTreatmentList(scopeModel.getPosition_id()));
		return SUCCESS;
	}
	public String insertScopeDentist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String treatment_code = request.getParameter("testadd");
		DoctorData docData = new DoctorData();	
		/**
		 * Scope Line
		 */	
		docData.DeleteTreatmentDentist(scopeModel);
		docData.insertTreatmentDentist(scopeModel,treatment_code);
		
		/**
		 * treatment Dentist
		 */	
		docData.DeleteDoctorTreatmentUpdateChange(scopeModel, treatment_code);
		docData.UpdateDoctorTreatmentScopeUpdateChange(scopeModel);
		
		
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			new Servlet().redirect(request, response, "UpdateScopeDentist-" + scopeModel.getPosition_id());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}		
	/**
	 * treatment Dentist
	 */	
	public String getDentistTreatmentList() throws IOException, Exception{
		DoctorData docData = new DoctorData();
		setDentistTreatmentMap(docData.GetDentistTreatment());
		setTreatMentList(docData.getdoctorTreatmentList(docModel));
		return SUCCESS;
	}	
	public String addTreatmentdoctor() throws IOException, Exception{
		DoctorData docData = new DoctorData();
		if(docData.DoctorTreatmentMoreCheck(docModel)){
			docData.insertDoctorTreatmentMore(docModel);	
		}else{
			
				addActionError("การรักษานี้ถูกเพิ่มไปแล้ว!");
				setDentistTreatmentMap(docData.GetDentistTreatment());
				setTreatMentList(docData.getdoctorTreatmentList(docModel));
				return INPUT;
			
		}
			
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			new Servlet().redirect(request, response, "getDentistTreatmentList-" + docModel.getDoctorID());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String updateTreatmentdoctor(){
		DoctorData docData = new DoctorData();
		docData.updateDoctorTreatmentMore(docModel);		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			new Servlet().redirect(request, response, "getDentistTreatmentList-" + docModel.getDoctorID());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String deleteTreatmentdoctor(){
		DoctorData docData = new DoctorData();
		docData.DeleteDoctorTreatmentMore(docModel);		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			new Servlet().redirect(request, response, "getDentistTreatmentList-" + docModel.getDoctorID());
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}	
	public TelephoneModel getEmTelModel() {
		return emTelModel;
	}

	public void setEmTelModel(TelephoneModel emTelModel) {
		this.emTelModel = emTelModel;
	}	
	

	public List<BranchModel> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<BranchModel> branchList) {
		this.branchList = branchList;
	}

	public List<BranchModel> getBranchMGRList() {
		return branchMGRList;
	}

	public void setBranchMGRList(List<BranchModel> branchMGRList) {
		this.branchMGRList = branchMGRList;
	}

	public List<AddressModel> getAddrList() {
		return AddrList;
	}

	public void setAddrList(List<AddressModel> addrList) {
		AddrList = addrList;
	}

	public List<TelephoneModel> getTelList() {
		return telList;
	}

	public void setTelList(List<TelephoneModel> telList) {
		this.telList = telList;
	}

	public List<BookBankModel> getBankList() {
		return bankList;
	}

	public void setBankList(List<BookBankModel> bankList) {
		this.bankList = bankList;
	}

	public List<PatientModel> getpList() {
		return pList;
	}

	public void setpList(List<PatientModel> pList) {
		this.pList = pList;
	}

	public List<Pre_nameModel> getPnameList() {
		return pnameList;
	}

	public void setPnameList(List<Pre_nameModel> pnameList) {
		this.pnameList = pnameList;
	}

	public List<DoctorModel> getWorkList() {
		return workList;
	}

	public void setWorkList(List<DoctorModel> workList) {
		this.workList = workList;
	}

	public List<DoctorModel> getEduList() {
		return eduList;
	}

	public void setEduList(List<DoctorModel> eduList) {
		this.eduList = eduList;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Map<String, String> getBranchlist() {
		return branchlist;
	}

	public List<DoctorModel> getBranchStandardList() {
		return branchStandardList;
	}

	public void setBranchStandardList(List<DoctorModel> branchStandardList) {
		this.branchStandardList = branchStandardList;
	}

	public List<DoctorModel> getBranchMgrList() {
		return branchMgrList;
	}

	public void setBranchMgrList(List<DoctorModel> branchMgrList) {
		this.branchMgrList = branchMgrList;
	}

	public String getDocID() {
		return docID;
	}

	public void setDocID(String docID) {
		this.docID = docID;
	}

	public String getBranchID() {
		return branchID;
	}

	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}

	public void setBranchlist(Map<String, String> branchlist) {
		this.branchlist = branchlist;
	}

	public File getPicProfile() {
		return picProfile;
	}

	public void setPicProfile(File picProfile) {
		this.picProfile = picProfile;
	}

	public String getPicProfileContentType() {
		return picProfileContentType;
	}

	public void setPicProfileContentType(String picProfileContentType) {
		this.picProfileContentType = picProfileContentType;
	}

	public String getPicProfileFileName() {
		return picProfileFileName;
	}

	public void setPicProfileFileName(String picProfileFileName) {
		this.picProfileFileName = picProfileFileName;
	}

	public String getPropertyInStack() {
		return propertyInStack;
	}

	public void setPropertyInStack(String propertyInStack) {
		this.propertyInStack = propertyInStack;
	}

	public List<DoctorModel> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(List<DoctorModel> doctorList) {
		this.doctorList = doctorList;
	}
	public HashMap<String, String> getBranchMap() {
		return branchMap;
	}

	public void setBranchMap(HashMap<String, String> branchMap) {
		this.branchMap = branchMap;
	}


	public BranchModel getBranchModel() {
		return branchModel;
	}


	public void setBranchModel(BranchModel branchModel) {
		this.branchModel = branchModel;
	}


	public String getAlertMSG() {
		return alertMSG;
	}


	public void setAlertMSG(String alertMSG) {
		this.alertMSG = alertMSG;
	}


	public String getAlertSuccess() {
		return alertSuccess;
	}


	public void setAlertSuccess(String alertSuccess) {
		this.alertSuccess = alertSuccess;
	}


	public String getAlertError() {
		return alertError;
	}


	public void setAlertError(String alertError) {
		this.alertError = alertError;
	}
	public List<DoctorModel> getScopeDentistlist() {
		return scopeDentistlist;
	}

	public void setScopeDentistlist(List<DoctorModel> scopeDentistlist) {
		this.scopeDentistlist = scopeDentistlist;
	}

	public DoctorModel getScopeModel() {
		return scopeModel;
	}

	public void setScopeModel(DoctorModel scopeModel) {
		this.scopeModel = scopeModel;
	}

	public List<DoctorModel> getPositionTreatmentList() {
		return positionTreatmentList;
	}

	public void setPositionTreatmentList(List<DoctorModel> positionTreatmentList) {
		this.positionTreatmentList = positionTreatmentList;
	}

	public List<DoctorModel> getTreatMentList() {
		return treatMentList;
	}

	public void setTreatMentList(List<DoctorModel> treatMentList) {
		this.treatMentList = treatMentList;
	}

	public Map<String, String> getDentistTreatmentMap() {
		return dentistTreatmentMap;
	}

	public void setDentistTreatmentMap(Map<String, String> dentistTreatmentMap) {
		this.dentistTreatmentMap = dentistTreatmentMap;
	}

	public Map<String, String> getScopeTreatmentMap() {
		return scopeTreatmentMap;
	}

	public void setScopeTreatmentMap(Map<String, String> scopeTreatmentMap) {
		this.scopeTreatmentMap = scopeTreatmentMap;
	}

}
