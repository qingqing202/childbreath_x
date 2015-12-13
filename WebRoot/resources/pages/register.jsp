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


<div class="container ">
	<form onsubmit="return validLogin();" action="<%=request.getContextPath()%>/mgn/register" method="POST">
		<div align="center">
			<br/>
			<fieldset style="width:80%">
				<legend>请填写患儿个人信息</legend><br/>
                <span id="tipMsg"></span>

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
					<div align="left" class="leftDiv">出生年：</div>
					<div align="left" class="rightDiv">
						<input id="birthyear" type="text" name="birthyear" class="text" />
					</div>
				</div>

				<div class="line">
					<div align="left" class="leftDiv">出生月：</div>
					<div align="left" class="rightDiv">
						<input id="birthmonth" type="text" name="birthmonth" class="text" />
					</div>
				</div>

				<div class="line">
					<div align="left" class="leftDiv">入院年：</div>
					<div align="left" class="rightDiv">
						<input id="hospitalizedYear" type="text" name="hospitalizedYear" class="text" />
					</div>
				</div>

				<div class="line">
					<div align="left" class="leftDiv">入院月：</div>
					<div align="left" class="rightDiv">
						<input id="hospitalizedMonth" type="text" name="hospitalizedMonth" class="text" />
					</div>
				</div>

				<div class="line">
					<div align="left" class="leftDiv">入院日：</div>
					<div align="left" class="rightDiv">
						<input id="hospitalizedDay" type="text" name="hospitalizedDay" class="text" />
					</div>
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

<!--
	<div id="content" class="gradient1">
		<div class="container ">
			<div class="loginFormBox center-block-form" id="loginform">
				<form class="form" onsubmit="return validLogin();" action="<%=request.getContextPath()%>/mgn/login" method="post">
					<div class="form-group input-group input-group-lg col-xs-10 col-xs-offset-1">
						<label id="tipMsg" >
							用户名或密码错误,请重新输入
						</label>
					</div>					
					<div class="form-group input-group input-group-lg col-xs-10 col-xs-offset-1">
						<label class="control-label sr-only" for="name">name</label>
						<span class="input-group-addon glyphicon glyphicon-user"></span>			
						<input type="text" id="name" name="name" autofocus="autofocus" class="form-control" placeholder="请输入用户名" />
					</div>
					<div class="form-group input-group input-group-lg col-xs-10 col-xs-offset-1 ">
						<label class="control-label sr-only" for="pwd">password</label>
						<span class="input-group-addon glyphicon glyphicon-lock"></span>
						<input type="password" id="pwd" name="pwd" class="form-control" placeholder="请输入密码" />
					</div>	
					<div class="form-group input-group input-group-lg col-xs-10 col-xs-offset-1">
						<button id="submitBtn" type="submit" class="btn btn-primary btn-lg btn-block">登录</button>
			  		</div>	
				</form>
			</div>	
		</div>
	</div>
	
  	-->
	<%@ include file="include/footer.jsp" %>
  	<script src="<%=request.getContextPath()%>/resources/js/register/register.js"></script>
</body>
</html>