<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<script type="text/javascript">
		var rootPath = "${rootPath}";
	</script>
	<link rel="stylesheet" href="<%=basePath%>/resources/css/docs.min.css"/>
	<link rel="stylesheet" href="<%=basePath%>/resources/css/bootstrap.min.3.3.5.css" />
	<link rel="stylesheet" href="<%=basePath%>/resources/css/style.css" />
	<link rel="stylesheet" href="<%=basePath%>/resources/css/font-awesome.min.css" />
    <link rel="stylesheet" href="<%=basePath%>/resources/css/font.css" />

	<script src="<%=basePath%>/resources/js/jquery.min-2.0.0.js"></script>
	<script src="<%=basePath%>/resources/js/bootstrap.js"></script>
