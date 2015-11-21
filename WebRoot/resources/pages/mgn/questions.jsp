<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html class="no-js" lang="en">
<head>
<%@ include file="../include/html_head.jsp"%>
</head>
<body>
	<%@ include file="../include/nav.jsp"%>
	<div id="content" class="content">
		<div class="container">
			<div class="row">
				<div class="col-md-3">
					<div id="filter">
						<form onsubmit="return false;">
							<div class="form-group">
								<label class="">类型</label> 
								<select class="form-control" id='filter_type'>
									<option value="all">所有</option>
									<option value="select">选择题</option>
									<option value="shortanswer">简答题</option>
								</select>
							</div>
							<div class="form-group">
								<label class="">适用病症</label> 
								<select class="form-control" id='filter_suit'>
									<option value="all">所有</option>
									<option value="common">通用</option>
									<c:forEach var="v" items="${diseases}" varStatus="s">
										<option value="${v.name}">${v.name}</option>
									</c:forEach>
								</select>
							</div>
						</form>
					</div>
					<div>
						<ul id="functionZone">
							<li><a href="<%=request.getContextPath()%>/mgn/question/create">新建问题</a>
						</ul>
					</div>
				</div>
				<div id="questionsDev" class="col-md-9"></div>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer.jsp"%>
	<script
		src="<%=request.getContextPath()%>/resources/js/mgn/questions.js"></script>
</body>
</html>