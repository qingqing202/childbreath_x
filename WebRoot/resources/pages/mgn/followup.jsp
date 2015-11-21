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
			<div class="row">
			<div class="col-xs-12 col-sm-8 col-sm-offset-2">
			<form class="form-horizontal " onsubmit="return false;">
				<div class="form-group">
					<label class="col-sm-2 control_label">病人姓名</label>
					<div class="col-sm-10">
						<p class="form-control-static">${follower.name }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control_label">住院号</label>
					<div class="col-sm-10">
						<p id="admission_number" class="form-control-static">${follower.admission_number }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control_label">病症</label>
					<div class="col-sm-10">
						<p class="form-control-static">${follower.disease }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control_label">入院时间</label>
					<div class="col-sm-10">						
						<p class="form-control-static">${follower.inTime }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control_label">出院时间</label>
					<div class="col-sm-10">
						<p class="form-control-static">${follower.leaveTime }</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control_label">回访问卷</label>
					<div class="col-sm-10">
						<select class="form-control" id="selGroup">
							<c:forEach var="v" items="${questionnaires}" varStatus="s">
								<option value="${v.id}">${v.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<button id="followupbtn" class="btn btn-success btn-block" onclick="followup()">确定</button>
				<button id="cannelbtn" class="btn btn-warning btn-block">取消</button>
			</form>
			</div>
			</div>
		</div>
	</div>
	<%@ include file="../include/footer.jsp" %>
	<script src="<%=request.getContextPath()%>/resources/js/mgn/followup.js"></script>
</body>
</html>