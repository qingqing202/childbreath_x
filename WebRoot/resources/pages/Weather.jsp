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
    <%@page import="childbreath.service.HealthWeather2" language="java" %>
    <link rel="stylesheet" href="<%=basePath%>/resources/css/weui/weui.css"/>
</head>
<body class="container">

<h2 class="text-center">
    哮喘预警

</h2>
<hr />

<%
    HealthWeather2 hw = new HealthWeather2();
    String s = hw.getWeatherInfo();
%>

<p class="text-right"><small> <a href = "index.jsp">上海儿童医学中心呼吸科</a>
    <br/>
</small></p>

<p><%=s%></p>

</body>
</html>
