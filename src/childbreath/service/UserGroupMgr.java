package childbreath.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import weixin.utility.WeixinUtil;

public class UserGroupMgr {

	// group operation
	public final static String get_group_url      = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
	public final static String create_group_url   = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
	public final static String update_group_url   = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";
	public final static String delete_group_url   = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=ACCESS_TOKEN";


	// user operation
	public final static String get_group_user_url    = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=ACCESS_TOKEN";
	public final static String set_group_user_url    = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";
	public final static String delete_group_user_url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN";
	public final static String get_user_group_url    = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=ACCESS_TOKEN";

	public AccessTokenMgr mgr_;

	public UserGroupMgr(AccessTokenMgr mgr) {
		mgr_ = mgr;
	}

	public JSONArray getUserGroups(String openid) {
		JSONArray tagids = new JSONArray();
		try {
			//AccessTokenMgr mgr = AccessTokenMgr.getInstance();
			String token = mgr_.getAccessToken();

			JSONObject js_in = new JSONObject();
			js_in.put("openid", openid);

			String requestUrl = get_user_group_url.replace("ACCESS_TOKEN", token);
			JSONObject js_object = WeixinUtil.HttpsRequest(requestUrl, "POST", js_in.toString());

			if (js_object != null) {
				System.out.println("return = " + js_object.toString());
			} else {
				System.out.println("httpsrequest failed in creategroup");
			}


			if (!js_object.containsKey("errcode")) {
				tagids = js_object.getJSONArray("tagid_list");
			}
			return tagids;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tagids;
	}

	public boolean deleteUserGroup(int tagid, String[] openids) {
		try {
			//AccessTokenMgr mgr = AccessTokenMgr.getInstance();
			String token = mgr_.getAccessToken();

			JSONObject js_in = new JSONObject();
			js_in.put("openid_list", openids);
			js_in.put("tagid", tagid);

			String requestUrl = delete_group_user_url.replace("ACCESS_TOKEN", token);
			JSONObject js_object = WeixinUtil.HttpsRequest(requestUrl, "POST", js_in.toString());
			if (js_object != null) {
				System.out.println("return = " + js_object.toString());
			} else {
				System.out.println("httpsrequest failed in creategroup");
			}

			if (js_object.getInteger("errcode") != 0) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean setUserGroup (int tagid, String[] openids) {
		try {
			//AccessTokenMgr mgr = AccessTokenMgr.getInstance();
			String token = mgr_.getAccessToken();

			JSONObject js_in = new JSONObject();
			js_in.put("openid_list", openids);
			js_in.put("tagid", tagid);

			String requestUrl = set_group_user_url.replace("ACCESS_TOKEN", token);
			JSONObject js_object = WeixinUtil.HttpsRequest(requestUrl, "POST", js_in.toString());
			if (js_object != null) {
				System.out.println("return = " + js_object.toString());
			} else {
				System.out.println("httpsrequest failed in creategroup");
			}

			if (js_object.getInteger("errcode") != 0) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public JSONObject getGroupUsers(int id, String next_openid) {
		JSONObject js_object = new JSONObject();
		try {
			//AccessTokenMgr mgr = AccessTokenMgr.getInstance();
			String token = mgr_.getAccessToken();

			JSONObject js_in = new JSONObject();
			js_in.put("tagid", id);
			js_in.put("next_openid", next_openid);

			String requestUrl = get_group_user_url.replace("ACCESS_TOKEN", token);
			js_object = WeixinUtil.HttpsRequest(requestUrl, "POST", js_in.toString());
			if (js_object != null) {
				System.out.println("return = " + js_object.toString());
			} else {
				System.out.println("httpsrequest failed in creategroup");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return js_object;
	}

	public boolean deleteGroup(int id) {
		try {
			//AccessTokenMgr mgr = AccessTokenMgr.getInstance();
			String token = mgr_.getAccessToken();

			JSONObject js_in = new JSONObject();
			js_in.put("id", String.valueOf(id));

			JSONObject data = new JSONObject();
			data.put("tag", js_in);

			String requestUrl = delete_group_url.replace("ACCESS_TOKEN", token);
			JSONObject js_object = WeixinUtil.HttpsRequest(requestUrl, "POST", data.toString());
			if (js_object != null) {
				System.out.println("return = " + js_object.toString());
			} else {
				System.out.println("httpsrequest failed in creategroup");
			}

			if (js_object.getInteger("errcode") != 0 ) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateGroup(int id, String name) {
		try {
			//AccessTokenMgr mgr = AccessTokenMgr.getInstance();
			String token = mgr_.getAccessToken();

			JSONObject js_in = new JSONObject();
			js_in.put("id", String.valueOf(id));
			js_in.put("name", name);

			JSONObject data = new JSONObject();
			data.put("tag", js_in);

			String requestUrl = update_group_url.replace("ACCESS_TOKEN", token);
			JSONObject js_object = WeixinUtil.HttpsRequest(requestUrl, "POST", data.toString());
			if (js_object != null) {
				System.out.println("return = " + js_object.toString());
			} else {
				System.out.println("httpsrequest failed in creategroup");
			}

			if (js_object.getInteger("errcode") != 0 ) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public JSONObject getGroups() {
		try {
			//AccessTokenMgr mgr = AccessTokenMgr.getInstance();
			String token = mgr_.getAccessToken();

			String requestUrl = get_group_url.replace("ACCESS_TOKEN", token);
			JSONObject jsonObject = WeixinUtil.HttpsRequest(requestUrl, "GET", null);

			if (jsonObject != null) {
				System.out.println("return = "+jsonObject.toString());
			} else {
				System.out.println("httpsrequest failed in getGroups");
			}
			return jsonObject;

		}catch(Exception e ){
			e.printStackTrace();
		}
		return null;
	}

	public JSONObject createGroup(String name) {
		JSONObject js_object = new JSONObject();
		try {

			//AccessTokenMgr mgr = AccessTokenMgr.getInstance();
			String token = mgr_.getAccessToken();
			JSONObject js_in = new JSONObject();
			js_in.put("name", name);

			JSONObject data = new JSONObject();
			data.put("tag", js_in);

			String requestUrl = create_group_url.replace("ACCESS_TOKEN", token);
			js_object = WeixinUtil.HttpsRequest(requestUrl, "POST", data.toString());
			if (js_object != null) {
				System.out.println("return = " + js_object.toString());
			} else {
				System.out.println("httpsrequest failed in creategroup");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return js_object;
	}
}