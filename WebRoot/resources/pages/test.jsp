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

    <% String param = request.getParameter("sub");
        String CONTENT_FILE = getClass().getResource("/").getPath() + "resources/pages/content/" + param + ".xml";
        HashMap<String, Object> hash_for_all= new HashMap<>();
        XmlReader xmlReader = new XmlReader();
        xmlReader.main(CONTENT_FILE, hash_for_all);
        HashMap<String, String> hash_for_title = (HashMap)hash_for_all.get("title");
        HashMap<String, Object> hash_for_content_all= (HashMap)hash_for_all.get("content");
    %>
    <title><%=hash_for_title.get("name")%></title>
</head>
<body>

    <table class="table-bordered">
        <tr>
            <td>
    <ul class="nav nav-tabs nav-stacked">
        <li><a tabindex="1" href="#">Regular link<span class="glyphicon glyphicon-search"></span></a></li>
        </ul>
        <ul class="nav nav-tabs nav-stacked">
        <li><a tabindex="2" href="#">Regular link<span class="glyphicon glyphicon-search"></span></a></li>
        </ul>
            <ul class="nav nav-tabs nav-stacked">
        <li><a tabindex="3" href="#">Regular link<span class="glyphicon glyphicon-search"></span></a></li>
    </ul>
        </td>
        </tr>
        </table>
</body>

</html>
