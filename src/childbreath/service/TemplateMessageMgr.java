package childbreath.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import weixin.utility.WeixinUtil;

public class TemplateMessageMgr {
	public TemplateMessageMgr(){
		
	}
	
	public final static String get_id_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	//public final static String templateid = "aZcHhXAEdXJof018RsOLD9vSdgSjtGDPZBBORyWEjMw"; //testing templateid
	public final static String templateid = "_CbbipPVr845g_zsIKvhV4C1TCwSWdIkdc0jfjFE2y0";

	public String send_template_message(String openid){
		try{

			JSONObject d1 = new JSONObject();
			JSONObject d2 = new JSONObject();
			JSONObject d3 = new JSONObject();
			JSONObject d4 = new JSONObject();
			JSONObject d5 = new JSONObject();
			JSONObject d6 = new JSONObject();
			JSONObject data = new JSONObject();

			d1.put("value","殷勇医师3月11日星期五呼吸哮喘特需门诊暂停1次，由张皓主任代替。\n");
			d1.put("color","#173177");

			d2.put("value","殷勇主任");
			d2.put("color","#173177");

			d3.put("value","呼吸哮喘专科门诊");
			d3.put("color","#173177");

			d4.put("value","2016年3月11日");
			d4.put("color","#173177");

			d5.put("value", "08:00 -- 16:00");
			d5.put("color","#173177");

			d6.put("value", "\n上海儿童医学中心");
			d6.put("color","#173177");

			data.put("first",    d1);
			data.put("keyword1", d2);
			data.put("keyword2", d3);
			data.put("keyword3", d4);
			data.put("keyword4", d5);
			data.put("remark", d6);

			JSONObject f1 = new JSONObject();

			f1.put("touser", openid);
			f1.put("template_id",templateid);
			f1.put("url","http://139.196.110.223/childbreath/resources/pages/detail_content.jsp?name=menzhenbiao");
			f1.put("data", data);

			System.out.println("post f ="+f1.toString());

			AccessTokenMgr mgr = AccessTokenMgr.getInstance();
			String token = mgr.getAccessToken();

			String requestUrl = get_id_url.replace("ACCESS_TOKEN", token);
			JSONObject jsonObject = WeixinUtil.HttpsRequest(requestUrl, "POST", f1.toString());
			if (jsonObject != null) {
				System.out.println("return = "+jsonObject.toString());
				return "";
			} else {
				System.out.println("Cannot find openids");
				return "";
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		return "";
	}
}
