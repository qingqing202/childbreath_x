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
			  <h1>需回访的已出院用户</h1>
			</div>
			<table id="leaveHospitalTable" class="table table-striped table-hover">
				<thead>
					<tr>
						<th>病人姓名</th>
						<th>住院号</th>
						<th>出院时间</th>
						<th>已出院天数</th>
						<th>回访</th>
					<tr>
				</thead>
				<tbody>
					<c:forEach var="follower" items="${followers}">
						<tr>
							<td>${follower.name }</td>
							<td>${follower.admission_number }</td>
							<td>${follower.leaveTime }</td>
							<td>${follower.leaveDays }</td>
							<td>
								<button id="followup" class="btn btn-primary btn-block" onclick="followup('${follower.admission_number }')">回访</button>				
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<%@ include file="../include/footer.jsp" %>
	<script src="<%=request.getContextPath()%>/resources/js/mgn/leavehospitals.js"></script>
</body>
</html>