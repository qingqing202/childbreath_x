package childbreath.servlet;

import childbreath.service.UserGroupMgr;
import com.alibaba.fastjson.JSONObject;
import weixin.utility.WeixinUtil;


import childbreath.service.AccessTokenMgr;
import childbreath.service.AccessTokenMgrHOPE;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.alibaba.fastjson.JSONArray;
/**
 * Created by QQQ on 17/10/16.
 */
@WebServlet(name = "setUsrGroup")
public class setUsrGroup extends HttpServlet {

    final static String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Translational//EN\">");
        out.println("<HTML>");
        out.println("<meta http-equiv=\"content-type\" content=\"test/html; charset=UTF-8\">");
        out.println("<HEAD><TITLE>A servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        */


        System.out.println("code = " + request.getParameter("code"));
        System.out.println("state= " + request.getParameter("state"));

        String state = request.getParameter("state");
        AccessTokenMgr mgr = AccessTokenMgrHOPE.getInstance();

        String requestURL = access_token_url.replace("APPID", mgr.getAppId()).replace("SECRET", mgr.getAppSecret()).replace("CODE", request.getParameter("code"));

        System.out.println("requestURL= " + requestURL);
        JSONObject js_object = WeixinUtil.HttpsRequest(requestURL, "GET", null);
        System.out.println("js_object = " + js_object.toString());

        String[] open_id = new String[1];
        open_id[0] = js_object.getString("openid");

        System.out.println("open_id = " + open_id[0]);


        if (open_id.equals(null)) {
            System.out.println("open id not found");
            response.sendRedirect("http://projecthope.flzhan.com/text398.html");
            //out.println("系统错误，请稍后再试");
        } else {
            UserGroupMgr user_group_mgr = new UserGroupMgr(mgr);

            JSONArray old_group_ids = user_group_mgr.getUserGroups(open_id[0]);
            if (!old_group_ids.isEmpty()) {
                for (int i = 0; i < old_group_ids.size(); i++) {
                    user_group_mgr.deleteUserGroup(old_group_ids.getInteger(i), open_id);
                }
            }

            JSONArray groups = user_group_mgr.getGroups().getJSONArray("tags");

            System.out.println("groups = " + groups.toString());

            Integer tag_id = -1;

            System.out.println("groups.size = " + groups.size());

            if (state.equals("1")) {
                for (int i = 0; i < groups.size(); i++) {
                    System.out.println("1group " + i + " name " + groups.getJSONObject(i).getString("name"));
                    if (groups.getJSONObject(i).getString("name").equals("医护人员")) {
                        tag_id = groups.getJSONObject(i).getInteger("id");
                        System.out.println("tag_id = " + tag_id);
                    }
                }
            } else {
                for (int i = 0; i < groups.size(); i++) {
                    System.out.println("2group " + i + " name " + groups.getJSONObject(i).getString("name"));
                    if (groups.getJSONObject(i).getString("name").equals("非医护人员")) {
                        tag_id = groups.getJSONObject(i).getInteger("id");
                    }
                }
            }

            System.out.println("tag_id = " + tag_id);
            if (tag_id.equals(-1)) {
                System.out.println("tag id not found");
                response.sendRedirect("http://projecthope.flzhan.com/text398.html");
                //out.println(" set tag failed");
            } else {
                if (user_group_mgr.setUserGroup(tag_id.intValue(), open_id)) {
                    System.out.println("set tag succeed");
                    response.sendRedirect("http://projecthope.flzhan.com/text408.html");
                    //out.println(" set tag succeed");
                } else {
                    System.out.println("set tag failed");
                    response.sendRedirect("http://projecthope.flzhan.com/text408.html");
                    //out.println(" set tag failed");
                }
            }
        }

        /*
        out.println(" </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
        */
        return;

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
        return;
    }
}
