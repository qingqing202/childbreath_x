package childbreath.service;

import com.alibaba.fastjson.JSONObject;
import utility.XmlReader;
import weixin.utility.WeixinUtil;

import java.util.Date;
import java.util.Vector;
import java.util.HashMap;

public class HealthWeather2 {
	public final static String weather_info_url= "http://222.66.83.21:808/ScreenDisplay/HealthWeather2/webservice/Publish.asmx/GetCrows";
	public final static String authcode = "authCode=shjkqxxc";
	public HashMap<String, String> map1;
	public HashMap<String, String> map2;

	public HealthWeather2(){
		try {
			String requestUrl = weather_info_url;

			System.out.println("acess_token_url = " + requestUrl);
			String w = WeixinUtil.HttpRequestString(requestUrl, "POST", authcode);
			parse_weather_string(w);
		}catch(Exception e ){
			System.out.println("jsonobject exception");
			e.printStackTrace();
		}
	}

	public String getWarningLevelDay1() {
		return map1.get("WarningDesc");
	}

	public String getWarningLevelDay2() {
		return map1.get("WarningDesc");
	}

	public String getYear2() {
		String date = map2.get("Date");
		String year = "";
		for(int i = 0; i < date.length(); i++){
			if ( date.charAt(i) != '-' ) {
				year += date.charAt(i);
			} else {
				break;
			}
		}
		return year;
	}
	public String getMonth1() {
		String date = map1.get("Date");
		String month = "";
		int count = 0;
		for (int i = 0; i < date.length(); i++) {
			if (date.charAt(i) == '-') {
				count += 1;
			} else if (count == 1){
				month += date.charAt(i);
			} else if (count == 2) {
				break;
			}
		}
		return month;
	}
	public String getMonth2() {
		String date = map2.get("Date");
		String month = "";
		int count = 0;
		for (int i = 0; i < date.length(); i++) {
			if (date.charAt(i) == '-') {
				count += 1;
			} else if (count == 1){
				month += date.charAt(i);
			} else if (count == 2) {
				break;
			}
		}
		return month;
	}
	public String getDay1() {
		String date = map1.get("Date");
		String month = "";
		int count = 0;
		for (int i = 0; i < date.length(); i++) {
			if (date.charAt(i) == '-' || date.charAt(i) == 'T') {
				count += 1;
			} else if (count == 2){
				month += date.charAt(i);
			} else if (count == 3) {
				break;
			}
		}
		return month;
	}
	public String getDay2() {
		String date = map2.get("Date");
		String month = "";
		int count = 0;
		for (int i = 0; i < date.length(); i++) {
			if (date.charAt(i) == '-' || date.charAt(i) == 'T') {
				count += 1;
			} else if (count == 2){
				month += date.charAt(i);
			} else if (count == 3) {
				break;
			}
		}
		return month;
	}

	public String getYear1() {
		String date = map1.get("Date");
		String year = "";
		for(int i = 0; i < date.length(); i++){
			if ( date.charAt(i) != '-' ) {
				year += date.charAt(i);
			} else {
				break;
			}
		}
		return year;
	}
	public String getWarningDesc1(){
		return map1.get("WarningDesc");
	}
	public String getWarningDesc2(){
		return map2.get("WarningDesc");
	}

	public String getGuide1(){
		return map1.get("Wat_guid");
	}
	public String getGuide2(){
		return map2.get("Wat_guid");
	}

	public String getInflu1(){
		return map1.get("Influ");
	}
	public String getInflu2(){
		return map2.get("Influ");
	}

	public void parse_weather_string(String s) {
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
					map1=(HashMap)m.clone();
					System.out.println("1map1="+map1.toString());
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

