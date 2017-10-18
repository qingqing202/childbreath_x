package childbreath.servlet;

import childbreath.service.AccessTokenMgr;
import com.alibaba.fastjson.JSONObject;
import weixin.utility.WeixinUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by QQQ on 17/10/16.
 */
@WebServlet(name = "wechatWebAuth")
public class wechatWebAuth extends HttpServlet {

    final static String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("code = " + request.getParameter("code"));
        System.out.println("state= " + request.getParameter("state"));

        AccessTokenMgr mgr = AccessTokenMgr.getInstance();

        String requestURL = access_token_url.replace("APPID", mgr.getAppId()).replace("SECRET", mgr.getAppSecret()).replace("CODE", "011ra1Mk1uDMjj0fxbLk1LYdMk1ra1MC");
        //String requestURL = access_token_url.replace("APPID", mgr.getAppId()).replace("SECRET", mgr.getAppSecret()).replace("CODE", request.getParameter("code"));

        //access_token_url.replace("CODE", "011ra1Mk1uDMjj0fxbLk1LYdMk1ra1MC");
        System.out.println("requestURL= " + requestURL);

        JSONObject js_object = WeixinUtil.HttpsRequest(requestURL, "GET", null);

        //System.out.println(js_object.toString());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
        return;
    }
}
