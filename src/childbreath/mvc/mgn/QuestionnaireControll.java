package childbreath.mvc.mgn;

import java.util.ArrayList;
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

import childbreath.bean.question;
import childbreath.bean.questionnaire;

import com.alibaba.fastjson.JSON;

import datahandle.DiseaseDataHandle;
import datahandle.QuestionDataHandle;
import datahandle.QuestionnaireDataHandle;

@Controller
@RequestMapping("/mgn/questionnaire")
public class QuestionnaireControll {
	private static Logger logger = Logger.getLogger(QuestionnaireControll.class);
	
	@Inject
	private QuestionnaireDataHandle handle;
	@Inject
	private QuestionDataHandle questionHandle;
	@Inject
	private DiseaseDataHandle diseaseHandle;
	
	@RequestMapping("/showquestionnaires")
	public String showquestionnaires(){
		try{
			return "/mgn/questionnaires";
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return "error";
		}
	}
	
	@RequestMapping("/getquestionnaires")
	public void getQuestionnaires(HttpServletResponse response){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("questionnaires", handle.findAll());
			String finalJSON = JSON.toJSONString(map);
			System.out.println(finalJSON);
			response.getWriter().write(finalJSON);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	@RequestMapping("/delete")
	public void delete(@RequestParam("id") String id, HttpServletResponse response){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", handle.delete(id));
			String finalJSON = JSON.toJSONString(map);
			System.out.println(finalJSON);
			response.getWriter().write(finalJSON);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	private List<question> getQuestions(questionnaire q){
		List<question> rs = questionHandle.findList(q.getQuestions());
		List<question> questions = new ArrayList<question>();
		for( int i = 0; i < q.getQuestions().size(); i++ ){
			question v = null;
			for( int k=0; k<rs.size(); k++ ){
				question d = rs.get(k);
				if( d.getId() == q.getQuestions().get(i) ){
					v = d;
					break;
				}
			}
			if( v != null )
				questions.add(v);
		}
		return questions;
	}
	
	@RequestMapping("/edit")
	public String edit(@RequestParam("id") String id, Model model){
		try{
			questionnaire q = handle.find(id);
			model.addAttribute("q", q);		
			model.addAttribute("questions", getQuestions(q));
			model.addAttribute("diseases", diseaseHandle.findAll());
			return "/mgn/editquestionnaire";
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}
	
	@RequestMapping("/create")
	public String create(Model model){
		try{
			model.addAttribute("diseases", diseaseHandle.findAll());
			return "/mgn/editquestionnaire";
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}
	
	@RequestMapping("/save")
	public void save(@RequestParam(value="id", required=false) Long id,
					@RequestParam(value="name") String name,
					@RequestParam(value="ids[]",required=false) List<Long> ids, 
					HttpServletResponse response){
		try{
			questionnaire q = new questionnaire();
			if( id != null ){
				q.setId( id.longValue() );
			}
			q.setName(name);
			q.setQuestions(ids);
			boolean result = false;
			
			if( id != null ){
				result = handle.edit(q);
			}else{
				result = handle.create(q);
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
