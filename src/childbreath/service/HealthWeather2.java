package childbreath.service;

import com.alibaba.fastjson.JSONObject;
import utility.XmlReader;
import weixin.utility.WeixinUtil;

import java.util.Date;
import java.util.Vector;
import java.util.HashMap;

public class HealthWeather2 {
	public HealthWeather2(){
	}
	
	public final static String weather_info_url= "http://222.66.83.21:808/ScreenDisplay/HealthWeather2/webservice/Publish.asmx/GetCrows";
	public final static String authcode = "authCode=shjkqxxc";

	public String getWeatherInfo(){
		try {
			String requestUrl = weather_info_url;

			System.out.println("acess_token_url = " + requestUrl);
			String w = WeixinUtil.HttpRequestString(requestUrl, "POST", authcode);
			HashMap<String, String> map1=null;
			HashMap<String, String> map2=null;
			parse_weather_string(w, map1, map2);
			return w;
		}catch(Exception e ){
			System.out.println("jsonobject exception");
			e.printStackTrace();
			return null;
		}
	}

	public void parse_weather_string(String s, HashMap<String, String> map1, HashMap<String, String> map2) {
		if ( s.length() == 0) return;
		try {
			System.out.println("weather_string = "+s);




			return;
		}catch(Exception e) {
			System.out.println(e.toString());
			return;
		}

	}

}
