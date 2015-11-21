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

import childbreath.bean.disease;

import com.alibaba.fastjson.JSON;

import datahandle.DiseaseDataHandle;

@Controller
@RequestMapping("/mgn/disease")
public class DiseaseController {
	private static Logger logger = Logger.getLogger(DiseaseController.class);
	
	@Inject
	private DiseaseDataHandle handle;
	
	@RequestMapping("/showdiseases")
	public String showDiseases(){
		try{
			return "/mgn/diseases";
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}
	
	@RequestMapping("/getdiseases")
	public void getDiseases(HttpServletResponse response){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("diseases", handle.findAll());
			String finalJSON = JSON.toJSONString(map);
			System.out.println(finalJSON);
			response.getWriter().write(finalJSON);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	@RequestMapping("/create")
	public void create(@RequestParam("name") String name, HttpServletResponse response){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			int rt = 0;
			if( handle.exist(name) ){
				rt = 2;//已存在
			}else{
				disease d = new disease();
				d.setName(name);
				if( handle.create(d)){
					rt=1;
				}					
			}
			map.put("result", rt);
			String finalJSON = JSON.toJSONString(map);
			System.out.println(finalJSON);
			response.getWriter().write(finalJSON);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	@RequestMapping("/delete")
	public void delete(@RequestParam("name") String name, HttpServletResponse response){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", handle.delete(name));
			String finalJSON = JSON.toJSONString(map);
			System.out.println(finalJSON);
			response.getWriter().write(finalJSON);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
}
