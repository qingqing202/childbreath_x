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
			  <h1>管理病症种类&nbsp;<a href="" id="add" data-toggle="modal" data-target="#addDiseaseModal">新建病症</a></h1>			  
			</div>
			<table id="diseaseTable" class="table table-striped table-hover">
				<thead>
					<tr>
						<th>序号</th>
						<th>病症</th>
						<th>操作</th>
					<tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="modal fade" id="addDiseaseModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">添加疾病种类</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	        	<div class="form-group">
	        		<label class="control-lable">内容:</label>
	        		<input type="text" class="form-control" id="diseasename" placeholder="请输入疾病名">
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
	
	<%@ include file="../include/footer.jsp"%>
	<script
		src="<%=request.getContextPath()%>/resources/js/mgn/diseases.js"></script>
</body>
</html>