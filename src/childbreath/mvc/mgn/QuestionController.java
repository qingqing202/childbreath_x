package childbreath.mvc.mgn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import childbreath.bean.question;
import datahandle.DiseaseDataHandle;
import datahandle.QuestionDataHandle;

@Controller
@RequestMapping("/mgn/question")
public class QuestionController {
	private static Logger logger = Logger.getLogger(QuestionController.class);
	
	@Inject
	private QuestionDataHandle handle;
	@Inject
	private DiseaseDataHandle diseaseHandle;
	
	@RequestMapping(value="/showquestions")
	public String showQuestions(Model model){
		try{
			model.addAttribute("diseases", diseaseHandle.findAll());
			return "/mgn/questions";
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return "error";
		}
	}
	
	@RequestMapping(value="/getquestions")
	public void getQuestions(@RequestParam("type") String type,
			@RequestParam("suitability") String suitability,
			HttpServletResponse response){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("questions", handle.findMulti(type, suitability));
			String finalJSON = JSON.toJSONString(map);
			System.out.println(finalJSON);
			response.getWriter().write(finalJSON);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	@RequestMapping(value="/create")
	public String create(Model model){
		try{
			model.addAttribute("diseases", diseaseHandle.findAll());
			return "/mgn/editquestion";
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return "error";
		}
	}
	
	@RequestMapping(value="/edit")
	public String edit(@RequestParam("id") String id,
					Model model){
		try{
			model.addAttribute("question", handle.find(id));
			model.addAttribute("diseases", diseaseHandle.findAll());
			return "/mgn/editquestion";
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return "error";
		}
	}
	
	@RequestMapping(value="/delete")
	public void delete(@RequestParam("id") String id,
			HttpServletResponse response){
		try{				
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", handle.delete(id));
			response.getWriter().write(JSON.toJSONString(map));
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	@RequestMapping(value="/editquestion")
	public void editQuestion(@RequestParam(value="id", required=false) Integer id,
								@RequestParam("type") String type,
								@RequestParam("suitability") String suitability,
								@RequestParam("q") String q, 
								@RequestParam(value="options[]",required=false) List<String> options,
								HttpServletResponse response){
		try{
			question que = new question();
			if( id != null ){
				que.setId( id.longValue() );
			}
			que.setType(type);
			que.setSuitability(suitability);
			que.setQ(q);
			que.setOptions( options );
			boolean result = false;
			if( id != null ){
				result = handle.edit(que);
			}else{
				result = handle.create(que);
			}					
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			response.getWriter().write(JSON.toJSONString(map));			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
}
