package childbreath.service;

import com.alibaba.fastjson.JSONObject;
import weixin.utility.WeixinUtil;

import java.util.Date;

public class HealthWeather2 {
	public HealthWeather2(){
		
	}
	
	public final static String access_token_url = "http://222.66.83.21:808/ScreenDisplay/HealthWeather2/webservice/Publish.asmx/GetCrows?authCode=shjkqxxc";

	private static HealthWeather2 m_instance = new HealthWeather2();
	
	public static synchronized HealthWeather2 getInstance(){
		return m_instance;
	}
	
	private String m_WeatherInfo= null;
	private long m_TokenTime = 0;
	// 凭证有效时间
    private long m_expiresIn = 0;
	
	public synchronized String getWeatherInfo(){
		if( m_WeatherInfo == null  || ( new Date().getTime() - m_TokenTime ) > m_expiresIn ) {
			//重新取得凭证
			try{
				String requestUrl = access_token_url;

				System.out.println("acess_token_url = "+ access_token_url);
				JSONObject jsonObject = WeixinUtil.HttpRequest(requestUrl, "GET", null);
				if( jsonObject != null ){
					//请求成功
					System.out.println("jsonobject not equals to null");
					m_WeatherInfo= jsonObject.toString();
					m_TokenTime = jsonObject.getLong("expires_in")*1000;
				}else{
					System.out.println("jsonobject equals to null");
					m_WeatherInfo= null;
					m_TokenTime = 0;
				}
			}catch(Exception e ){
				System.out.println("jsonobject exception");
				e.printStackTrace();
				m_WeatherInfo= null;
			}
			return m_WeatherInfo;
		}
		return m_WeatherInfo;
	}
	
}
