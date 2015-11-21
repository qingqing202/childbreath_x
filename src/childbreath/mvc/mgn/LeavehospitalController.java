package childbreath.mvc.mgn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import childbreath.bean.record;
import datahandle.RecordDataHandle;

@Controller
@RequestMapping("/mgn/leavehospital")
public class LeavehospitalController {
	
	private static Logger logger = Logger.getLogger(LeavehospitalController.class);

	@Inject
	private RecordDataHandle handle;
	
	@RequestMapping(value="/showinhospitals")
	public String showInHospitals( Model model ){
		try{
			model.addAttribute("followers", handle.GetInhospitalRecords());
			return "/mgn/inhospitals";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value="/leavehospital")
	public void leaveHospital(@RequestParam("admission_number") String admission_number,
							HttpServletResponse response){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", handle.leaveHospital(admission_number));
			String finalJSON = JSON.toJSONString(map);
			System.out.println(finalJSON);
			response.getWriter().write(finalJSON);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
}
