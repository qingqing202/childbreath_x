<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html class="no-js" lang="en">
<head>
	<%@ include file="../include/html_head.jsp" %>
</head>
<body>
	<%@ include file="../include/nav.jsp" %>
	<div id="content" class="content">
		<div class="container">
			<div class="page-header">
			  <h1>正在住院的用户</h1>
			</div>
			<table id="inHospitalTable" class="table table-striped table-hover">
				<thead>
					<tr>
						<th>病人姓名</th>
						<th>住院号</th>
						<th>入院时间</th>
						<th>住院天数</th>
						<th>出院</th>
					<tr>
				</thead>
				<tbody>
					<c:forEach var="follower" items="${followers}">
						<tr>
							<td>${follower.name }</td>
							<td>${follower.admission_number }</td>
							<td>${follower.inTime }</td>
							<td>${follower.inDays }</td>
							<td>
								<button id="leave" class="btn btn-primary btn-block" onclick="leave('${follower.admission_number }')">出院</button>				
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
	</div>
	<%@ include file="../include/footer.jsp" %>
	<script src="<%=request.getContextPath()%>/resources/js/mgn/inhospitals.js"></script>
</body>
</html>