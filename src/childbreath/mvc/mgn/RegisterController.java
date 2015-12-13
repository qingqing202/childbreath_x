package childbreath.mvc.mgn;
import java.util.Date;

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
import java.text.SimpleDateFormat;

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
			System.out.println("register method begin");
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
			int birthyear= 0;
			try {
				birthyear=Integer.parseInt(request.getParameter("birthyear"));
			}catch(Exception e){}
			int birthmonth= 0;
			try {
				birthmonth=Integer.parseInt(request.getParameter("birthmonth"));
			}catch(Exception e){}
			int birthday = 0;
			try {
				birthmonth=Integer.parseInt(request.getParameter("birthday"));
			}catch(Exception e){}
			System.out.println("register method begin 2");

			String dateString = birthyear+"-"+birthmonth+"-"+birthday;
			System.out.println("datestring = " + dateString);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("gen sdf finished");
			Date inTime = null;
			try { inTime= sdf.parse(dateString); } catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			System.out.println("register method begin 3");


			String telNum = request.getParameter("telNum");
			String admission_number = request.getParameter("hospitalId");
			String sex = request.getParameter("sex");
			String weixin_openid = request.getParameter("webchatId");

			System.out.println("name            = " + name);
			System.out.println("admission_number= " + admission_number);
			System.out.println("weixin_openid   = " + weixin_openid);
			System.out.println("brithyear       = " + birthyear);
			System.out.println("brithmonth      = " + birthmonth);
			System.out.println("telephone_number= " + telNum);
			System.out.println("sex        = " + sex);

			record cur_record = new record();
			cur_record.setAdmission_number(admission_number);
			cur_record.setName(name);
			cur_record.setWeixin_openid(weixin_openid);
			cur_record.setInTime(inTime);
			cur_record.setTelNumber(telNum);


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
