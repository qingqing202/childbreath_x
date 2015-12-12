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
	
	private static AccessTokenMgr m_instance = new AccessTokenMgr();
	
	public static synchronized AccessTokenMgr getInstance(){
		return m_instance;
	}
	
	private String m_AccessToken = null;
	private long m_TokenTime = 0;
	// 凭证有效时间
    private long m_expiresIn;
	
	public synchronized String getAccessToken(){
		if( m_AccessToken == null || ( new Date().getTime() - m_TokenTime ) > m_expiresIn ){
			//重新取得凭证
			try{
				String requestUrl = access_token_url.replace("APPID", AppId).replace("APPSECRET", AppSecret);
				JSONObject jsonObject = WeixinUtil.HttpsRequest(requestUrl, "GET", null);
				if( jsonObject != null ){
					//请求成功
					m_AccessToken = jsonObject.getString("access_token");
					m_TokenTime = jsonObject.getLong("expires_in")*1000;
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
