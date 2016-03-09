package childbreath.service;

import java.util.Date;

import weixin.utility.WeixinUtil;

import com.alibaba.fastjson.JSONObject;

public class AccessTokenMgr {
	public AccessTokenMgr(){
		
	}
	
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static String AppId = "wx5172afd52167d85a";
	private static String AppSecret = "407e7f79f4c5992dca7b29c64a1bf1bb";


	//webcat id for testing
	//private static String AppId = "wxf7622c6c9856841c";
	//private static String AppSecret = "d4624c36b6795d1d99dcf0547af5443d";

	private static AccessTokenMgr m_instance = new AccessTokenMgr();
	
	public static synchronized AccessTokenMgr getInstance(){
		return m_instance;
	}
	
	private String m_AccessToken = null;
	private long m_TokenTime = 0;
	// 凭证有效时间
    private long m_expiresIn;
	
	public synchronized String getAccessToken(){
		long currentTime= new Date().getTime();
		if( m_AccessToken == null || ( currentTime - m_TokenTime ) > m_expiresIn ){
			//重新取得凭证
			try{
				String requestUrl = access_token_url.replace("APPID", AppId).replace("APPSECRET", AppSecret);
				JSONObject jsonObject = WeixinUtil.HttpsRequest(requestUrl, "GET", null);
				if( jsonObject != null ){
					//请求成功
					m_AccessToken = jsonObject.getString("access_token");
					m_TokenTime = new Date().getTime();
					m_expiresIn = jsonObject.getLong("expires_in")*1000;
				}else{
					m_AccessToken = null;
					m_TokenTime = 0;
				}
			}catch(Exception e ){
				e.printStackTrace();
				m_AccessToken = null;
			}
			return m_AccessToken;
		}
		return m_AccessToken;
	}
	
}
