<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<head>
	<%@ include file="include/html_head.jsp" %>
	<title>上海儿童医院中心呼吸科微信公众号平台</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/normalize.css" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css" />
</head>
<body>

<script language="javaScript">
function closeWindow()
{
window.opener = null;
window.open('', '_top', '');
window.parent.close();
}
</script>



<div class="container ">
	<h1>register succeed!</h1>
	<button class="btn btn-default" type="submit" onclick="CloseWindow()">关闭</button>

</div>

	<%@ include file="include/footer.jsp" %>
  	<script src="<%=request.getContextPath()%>/resources/js/register/register.js"></script>
</body>
</html>