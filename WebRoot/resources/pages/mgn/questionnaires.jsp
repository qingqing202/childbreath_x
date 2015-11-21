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
				<div class="page-header">
				  <h1>管理回访问卷&nbsp;<a href="<%=request.getContextPath()%>/mgn/questionnaire/create">新建问卷</a></h1>	  
				</div>
			<table id="questionnaireTable" class="table table-striped table-hover">
				<thead>
					<tr>
						<th>编号</th>
						<th>名称</th>
						<th>操作</th>
					<tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	<%@ include file="../include/footer.jsp"%>
	<script
		src="<%=request.getContextPath()%>/resources/js/mgn/questionnaires.js"></script>
</body>
</html>