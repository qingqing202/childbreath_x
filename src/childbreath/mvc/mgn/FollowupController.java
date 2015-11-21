package childbreath.mvc.mgn;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import childbreath.bean.record;
import datahandle.QuestionnaireDataHandle;
import datahandle.RecordDataHandle;

@Controller
@RequestMapping("/mgn/followup")
public class FollowupController {
	private static Logger logger = Logger.getLogger(FollowupController.class);

	@Inject
	private RecordDataHandle handle;
	@Inject
	private QuestionnaireDataHandle questionnaireHandle;
	
	@RequestMapping(value="/showleavehospitals")
	public String showLeaveHospitals( Model model ){
		try{
			model.addAttribute("followers", handle.GetleavehospitalRecords());
			return "/mgn/leavehospitals";
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return "error";
		}
	}
	
	@RequestMapping(value="/followup")
	public String followup( @RequestParam("admission_number") String admission_number,
						Model model ){
		try{
			record rc = handle.GetRecord(admission_number);
			if( rc != null ){
				model.addAttribute("follower", rc);
				model.addAttribute("questionnaires", questionnaireHandle.findAll());
				return "/mgn/followup";
			}else{
				return "error";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return "error";
		}
	}
	
	@RequestMapping(value="/savefollowup")
	public void saveFollowup( @RequestParam("admission_number") String admission_number,
							@RequestParam("questionnaireId") Long qid,
							HttpServletResponse response){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", handle.followup(admission_number, qid));
			String finalJSON = JSON.toJSONString(map);
			System.out.println(finalJSON);
			response.getWriter().write(finalJSON);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
}
