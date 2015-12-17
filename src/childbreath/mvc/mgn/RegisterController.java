package childbreath.mvc.mgn;
import java.lang.String;
import java.util.Date;
import java.text.SimpleDateFormat;


import datahandle.RecordDataHandle;
import datahandle.UserDataHandle;
import childbreath.bean.record;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/mgn")
public class RegisterController {

	public RegisterController(){
	}
	
	@Inject
	private RecordDataHandle recordDataHandle;
	
	@RequestMapping(value="/register")
	public String register(@RequestParam("name") String userName,
						Model model,HttpServletRequest request, HttpServletResponse response){
		try{
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");

			String indate = request.getParameter("inDate");
			System.out.print("in date1= " + indate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.print("in date2= " + indate);
			Date inTime = null;
			try { inTime= sdf.parse(indate); } catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			System.out.print("in date3= " + indate);

			String birthdate = request.getParameter("birthDay");
			System.out.print("in date4= " + indate);

			Date birthDay= null;
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			try { birthDay= sdf2.parse(birthdate); } catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			System.out.print("in date = " + indate);

			String telNum = request.getParameter("telNum");
			String admission_number = request.getParameter("hospitalId");
			String sex = request.getParameter("sex");
			String weixin_openid = request.getParameter("webchatId");

			record cur_record = new record();
			cur_record.setAdmission_number(admission_number);
			cur_record.setName(name);
			cur_record.setSex(sex);
			cur_record.setWeixin_openid(weixin_openid);
			cur_record.setInTime(inTime);
			cur_record.setBirthDay(birthDay);
			cur_record.setTelNumber(telNum);

			System.out.print(cur_record.toString());

			if (recordDataHandle.addNewRecord(cur_record)) {
				return "register";
			} else {
				return "register";
			}

		}catch( Exception e ){
			e.printStackTrace();			
			return "register";
		}
	}

}
