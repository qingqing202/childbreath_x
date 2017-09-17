<%--
  Created by IntelliJ IDEA.
  User: QQQ
  Date: 15/8/23
  Time: 上午10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<html>
<head>
    <%@ include file="include/html_head.jsp" %>
    <%@page import="java.util.*"%>
    <%@ page import="childbreath.service.TemplateMessageMgr" %>
    <%@ page import="childbreath.service.OpenIdMgr" %>
    <%@ page import="childbreath.service.UserGroupMgr" %>
    <%@ page import="com.alibaba.fastjson.JSONObject" %>
    <%@ page import="com.alibaba.fastjson.JSONArray" %>
    <link rel="stylesheet" href="<%=basePath%>/resources/css/weui/weui.css"/>
</head>
<body class="container">

<h2 class="text-center">
    用户分组

</h2>

<hr />



<%
    UserGroupMgr user_group_mgr = new UserGroupMgr();
    JSONArray groups = user_group_mgr.getGroups().getJSONArray("tags");
    System.out.println(groups.toString());

    JSONArray user_openids_all = new JSONArray();
    for (int i = 0; i < groups.size(); i++) {
        JSONObject group = groups.getJSONObject(i);

        int group_id = group.getInteger("id");
        JSONObject group_users = user_group_mgr.getGroupUsers(group_id, "");

        if (group_users.containsKey("data")) {
            JSONArray user_openids = group_users.getJSONObject("data").getJSONArray("openid");
            System.out.println("user_openids = " + user_openids.toString());
            for (int j = 0; j < user_openids.size(); j++) {
                user_openids_all.add(user_openids.getString(j));
            }
        }
    }

    System.out.println("user_openids_all = " + user_openids_all.toString());
    System.out.println("user_openids_all.size() = " + user_openids_all.size());

    String[] user_openids_list = new String[user_openids_all.size()];
    for (int i = 0; i < user_openids_all.size(); i++) {
        user_openids_list[i] = user_openids_all.getString(i);
    }

    System.out.println("user_openids_list = " + user_openids_list.toString());


    int test_group_id = -1;
    for(int i = 0; i < groups.size(); i++) {
        if (groups.getJSONObject(i).getString("name").equals("测试")) {
            System.out.println("alread has 测试");
            test_group_id = groups.getJSONObject(i).getInteger("id");
        }
    }

    if (test_group_id == -1) {
        System.out.println("create group 测试");
        JSONObject TEST = user_group_mgr.createGroup("测试");
        if (TEST.containsKey("tag")) {
            test_group_id = TEST.getJSONObject("tag").getInteger("id");
        }
    }

    System.out.println("test_group_id = " + test_group_id);
    if (test_group_id == -1)
        return;

    OpenIdMgr openid_mgr = OpenIdMgr.getInstance();

    if (user_group_mgr.setUserGroup(test_group_id, user_openids_list)) {
        System.out.println("add all user with tags to group test");
        JSONArray test_group_users_array = user_group_mgr.getGroupUsers(test_group_id, "").getJSONObject("data").getJSONArray("openid");

        System.out.println("test_group_users_array = " + test_group_users_array);
        System.out.println("users in test group: ");

        for (int i = 0; i < test_group_users_array.size(); i++) {
            System.out.println("\t" + openid_mgr.getNickNameFromOpenID(test_group_users_array.getString(i)));
        }

        String[] user_openids_vector = new String[test_group_users_array.size()];
        for (int i = 0; i < test_group_users_array.size(); i++) {
            user_openids_vector[i] = test_group_users_array.getString(i);
        }
        user_group_mgr.deleteUserGroup(test_group_id, user_openids_vector);

        user_group_mgr.deleteGroup(test_group_id);

        user_group_mgr.getGroups();
    }


    for (int i = 0; i < user_openids_all.size(); i++) {
        String openid = user_openids_all.getString(i);
        System.out.println(openid_mgr.getNickNameFromOpenID(openid));
        JSONArray group_ids = user_group_mgr.getUserGroups(openid);

        if (group_ids.size() > 0) {
            for (int j = 0; j < group_ids.size(); j++) {
                int group_id = group_ids.getInteger(j);
                System.out.println("\tgroup id = " + group_id);
            }
        }
    }

    for (int i = 0; i < groups.size(); i++) {
        JSONObject group = groups.getJSONObject(i);
        if (!group.getString("name").contains("星标")) {
            user_group_mgr.deleteGroup(group.getIntValue("id"));
        }
    }


    user_group_mgr.getGroups();


%>


</body>
</html>
