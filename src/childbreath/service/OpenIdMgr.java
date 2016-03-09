package childbreath.service;

import com.alibaba.fastjson.*;
import weixin.utility.WeixinUtil;


public class OpenIdMgr {
	public OpenIdMgr(){
		
	}
	
	public final static String get_id_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	public final static String get_user_info_url= "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	public String get_all_openid(String next_openid){
		try{
			AccessTokenMgr mgr = AccessTokenMgr.getInstance();
			String token = mgr.getAccessToken();

			String requestUrl = get_id_url.replace("ACCESS_TOKEN", token).replace("NEXT_OPENID", next_openid);
			JSONObject jsonObject = WeixinUtil.HttpsRequest(requestUrl, "GET", null);
			if (jsonObject != null) {
				JSONObject data = jsonObject.getJSONObject("data");
				JSONArray idlist = data.getJSONArray("openid");
				return idlist.toString();
			} else {
				return "";
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		return "";
	}

	public String get_nickname_from_openid(String openid) {
		try {
			AccessTokenMgr mgr = AccessTokenMgr.getInstance();
			String token = mgr.getAccessToken();

			String requestUrl = get_user_info_url.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
			JSONObject jsonObject = WeixinUtil.HttpsRequest(requestUrl, "GET", null);
			return jsonObject.getString("nickname");
		}catch(Exception e ) {
			e.printStackTrace();
			return "";
		}
	}

}
