<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>上海儿童医学中心呼吸科微信公众号管理平台</title>
	<script type="text/javascript">
		var rootPath = "${rootPath}";
	</script>
	<link rel="stylesheet" href="<%=basePath%>/resources/css/docs.min.css"/>
	<link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" href="<%=basePath%>/resources/css/style.css" />
    <link rel="stylesheet" href="<%=basePath%>/resources/css/font.css" />

