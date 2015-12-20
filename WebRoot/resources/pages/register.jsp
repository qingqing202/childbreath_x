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

<script type="text/javascript">
	function disp_alert()
	{
		alert("我是警告框！！")
	}
</script>

<div class="container ">
	<form onsubmit="return validLogin();" action="<%=request.getContextPath()%>/mgn/register" method="POST">
		<div align="center">
			<br/>
			<fieldset style="width:80%">
				<!--
				<legend>请填写患儿个人信息</legend><br/>
				-->
                <label id="tipMsg">请填写个人信息：</label>
				<c:choose>
					<c:when test="${registerFailed > 0}"> confirm("注册成功") </c:when>
					<c:when test="${registerFailed < 0}"> confirm("注册失败")</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>

				<div class="line">
					<div align="left" class="leftDiv">姓名：</div>
					<div align="left" class="rightDiv">
						<input id="name" type="text" name="name" class="text" />
					</div>
				</div>

				<div class="line">
					<div align="left" class="leftDiv">住院号：</div>
					<div align="left" class="rightDiv">
						<input id="hospitalId" type="text" name="hospitalId" class="text" />
					</div>
				</div>

				<div class="line">
					<div align="left" class="leftDiv">微信号：</div>
					<div align="left" class="rightDiv">
						<input id="webchatId" type="text" name="webchatId" class="text" />
					</div>
				</div>

				<div class="line">
					<div align="left" class="leftDiv">电话号码：</div>
					<div align="left" class="rightDiv">
						<input id="telNum" type="text" name="telNum" class="text" />
					</div>
				</div>

				<div class="line">
					<div align="left" class="leftDiv">出生日期：</div>
					<input type="date" name="birthDay" id="birthDay"/>
				</div>


				<div class="line">
					<div align="left" class="leftDiv">入院日期：</div>
					<input type="date" id="inDate" name="inDate" />
				</div>

				<div class="line">
					<div align="left" class="leftDiv">性别：</div>
					<div align="left" class="rightDiv">
						<input id="sexMale" type="radio" name="sex" value="男" id="sexMale">
						<lable for="sexMale">男</lable>
						<input id="sexFemale" type="radio" name="sex" value="女" id="sexFemale">
						<lable for="sexFemale">女</lable>
					</div>
				</div>

				<div class="line">
					<div align="left" class="leftDiv"></div>
					<div align="left" class="rightDiv">
						<br/><input type="submit" name="btn" value="提交信息"
									class="bottom"><br/>
					</div>
				</div>
			</fieldset>
		</div>
	</form>

</div>

	<%@ include file="include/footer.jsp" %>
  	<script src="<%=request.getContextPath()%>/resources/js/register/register.js"></script>
</body>
</html>