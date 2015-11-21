package childbreath.mvc.mgn;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import datahandle.UserDataHandle;

@Controller
@RequestMapping("/mgn")
public class LoginController {
	
	public LoginController(){
		
	}
	
	@Inject
	private UserDataHandle userdatahandle;
	
	@RequestMapping(value="/login")
	public String login(@RequestParam("name") String userName,
						@RequestParam("pwd") String userPwd,Model model, HttpServletRequest request){
		try{						
			System.out.println("login method");
			
			String rootpath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+request.getContextPath();
			model.addAttribute("rootPath", rootpath);
			request.getSession().setAttribute("rootPath", rootpath );
			if( userdatahandle.VerifyUser(userName, userPwd)){
				System.out.println("login successed");
				return "redirect:/mgn/leavehospital/showinhospitals";
			}else{
				System.out.println("login failed");
				model.addAttribute("loginFail", true);
				return "login";
			}
			
		}catch( Exception e ){
			e.printStackTrace();			
			return "login";
		}
	}

}
