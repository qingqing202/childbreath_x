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
				<input type="hidden" id="questionid" value="${question.id}" />
				<div class="form-group">
					<label class="col-sm-2 control_label">类型</label>
					<div class="col-sm-10">
						<select class="form-control" id="qtype">
						<option value="shortanswer" <c:if test="${question.type == 'shortanswer' }">selected</c:if>>简答题</option>
							<option value="select" <c:if test="${question.type == 'select' }">selected</c:if>>选择题</option>							
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control_label">适用病症</label>
					<div class="col-sm-10">
						<select class="form-control" id="qsuitability">
							<option value="common" <c:if test="${question.suitability == 'common' }">selected</c:if>>通用</option>
							<c:forEach var="v" items="${diseases}" varStatus="s">
								<option value="${v.name}" <c:if test="${question.suitability == v.name}">selected</c:if>>${v.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control_label">问题</label>
					<div class="col-sm-10">
						<textarea id="qcontent" class="form-control" rows="3" placeholder="请输入问题">${question.q}</textarea>
					</div>
				</div>
				<div class="form-group" id="optionsgroup" <c:if test="${question.type != 'select' }">style='display:none;'</c:if>>
					<label class="col-sm-2 control_label">选项</label>
					<div class="col-sm-10">
						<ol class="upper-alpha">
							<c:forEach var="v" items="${question.options}" varStatus="s">
								<li class="optionsli">${v} <button class="customBtn editBtn" data-toggle="modal" data-target="#editOptionModal" data-actiontype="edit" data-content="${v}"></button>&nbsp;&nbsp;<button class="customBtn deleteBtn" onclick="deleteOption(this)"></button></li>
							</c:forEach>
						</ol>
						<button id="addoption" class="btn btn-primary" data-toggle="modal" data-target="#editOptionModal" data-actiontype="add">添加选项</button>
					</div>
				</div>
				<button id="okbtn" class="btn btn-success btn-block">确定</button>
				<button id="cancelbtn" class="btn btn-warning btn-block">取消</button>
			</form>
			</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="editOptionModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">编辑选项内容</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	        	<div class="form-group">
	        		<label class="control-lable">内容:</label>
	        		<input type="text" class="form-control" id="optioncontent" placeholder="请输入选项内容">
	        	</div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="save" data-dismiss="modal">保存</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

	<%@ include file="../include/footer.jsp" %>
	<script src="<%=request.getContextPath()%>/resources/js/mgn/editquestion.js"></script>
</body>
</html>