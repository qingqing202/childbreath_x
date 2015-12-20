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
			  <h1>编辑问卷</h1>			  
			</div>
			<div>				
				<input type="hidden" id="qid" value="${q.id}"/>
				<form>
					<label class="">名称:</label>
					<input type="text" id="qname" placeholder="请输入问卷名称" value="${q.name}" />
				</form>
				
			</div>
			<div>
				<a href="" id="add" data-toggle="modal" data-target="#addQuestionModal">添加问题</a>
			</div>
			<table id="questionsTable" class="table table-striped table-hover">
				<tbody>
					<c:forEach var="question" items="${questions}">
						<tr>
							<td>${question.id }</td>
							<td>${question.q }</td>
							<td>
								<button id="remove" class="customBtn deleteBtn" onclick='removeQuestion(event)'></button>
								<button id="up"	class="customBtn upBtn" onclick='upQuestion(event)'></button>
								<button id="down"	class="customBtn downBtn" onclick='downQuestion(event)'></button>			
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<button id="okbtn" class="btn btn-success btn-block">确定</button>
			<button id="cancelbtn" class="btn btn-warning btn-block">取消</button>
		</div>
	</div>
	
	<div class="modal fade" id="addQuestionModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">添加疾病种类</h4>
	      </div>
	      <div class="modal-body">
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
				</div>
				<div id="questionsDiv" class="col-md-9">
					<table class="table table-striped table-hover">
						<tbody></tbody>
					</table>
				</div>
			</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button type="button" class="btn btn-primary" id="save" data-dismiss="modal">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<%@ include file="../include/footer.jsp"%>
	<script
		src="<%=request.getContextPath()%>/resources/js/mgn/editquestionnaire.js"></script>
</body>
</html>