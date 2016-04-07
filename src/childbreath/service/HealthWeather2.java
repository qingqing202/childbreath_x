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
			Vector<String> v = new Vector<String>();

			String tmp = "";
			for(int i = 0; i < s.length(); i++){
				if ( s.charAt(i) == ' ' || s.charAt(i) == '\n' ) {
					if ( tmp.equals(" ") || tmp.length() == 0 ) {
						continue;
					}
					v.add(tmp);
					tmp = "";
				} else {
					tmp += s.charAt(i);
				}
			}

			HashMap m=new HashMap<String, String>();
			for(int i = 0; i < v.size(); i++) {
				System.out.println("v"+i+"="+v.elementAt(i));
				String e = v.elementAt(i);
				if (e.contains("ID") && e.contains("2")) {
					map1=m;
				} else if (e.contains("Date")) {
					m.put("Date", ignore_brease(e));
				} else if (e.contains("WarningLevel")) {
					m.put("WarningLevel", ignore_brease(e));
				} else if (e.contains("WarningDesc")) {
					m.put("WarningDesc", ignore_brease(e));
				} else if (e.contains("Influ")) {
					m.put("Influ", ignore_brease(e));
				} else if (e.contains("Wat_guid")) {
					m.put("Wat_guid", ignore_brease(e));
				}
			}

			map2=m;


			System.out.println("map1="+map1.toString());
			System.out.println("map2=" + map2.toString());
			return;
		}catch(Exception e) {
			System.out.println(e.toString());
			return;
		}
	}

	private String ignore_brease(String s) {
		String e="";

		int read=0;
		for(int i = 0; i<s.length(); i++) {
			if(s.charAt(i)== '<') {
				read=0;
			} else if (s.charAt(i) =='>') {
				read=1;
			} else if (read==0) {
				continue;
			} else if (read==1) {
				e+=s.charAt(i);
			}
		}
		return e;
	}
}

