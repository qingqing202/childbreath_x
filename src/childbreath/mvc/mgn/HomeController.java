package childbreath.mvc.mgn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mgn")
public class HomeController {
	
	public HomeController(){
		
	}
	
	@RequestMapping(value="/home")
	public String showHomePage(){
		System.out.println("run to the show home page");
		return "login";
	}
}
