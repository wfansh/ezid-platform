<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %> 

<div id="content">		
	<div id="content-header">
		<h1>未通过理由管理</h1>
	</div> <!-- #content-header -->	
	<div id="content-container">
	
		<div class="row">
			<div class="col-md-12">
				<div id="action_button">
				<p>
					<a href="javascript:addRejectReason('0','')" class="btn btn-info btn-sm"><i class="fa fa-plus"></i> 添加父理由</a>
				</p>
				</div>

				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>理由名称</th>
							<th>添加时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${rejectReasonList}" var="reason">
						<tr>
							<td><strong>${reason.name}</strong></td>
							<td><fmt:formatDate value="${reason.timeCreated}" pattern="yyyy年MM月dd日 "/></td>
							<td>	
								<c:if test="${reason.subReasonList==null}">
							  	<a href="javascript:deleteRejectReason('${reason.id}')" class="btn btn-xs btn-primary" data-original-title="Approve">
									<i class="fa fa-times"></i> 删除
								</a>
								</c:if>		
								<a href="javascript:addRejectReason('${reason.id}','${reason.name}')" class="btn btn-xs btn-default" data-original-title="Approve">
									<i class="fa fa-plus"></i> 添加子理由
								</a>												
							</td>
						</tr>
						<c:if test="${not empty reason.subReasonList}">
						<c:forEach items="${reason.subReasonList}" var="subNode">
						<tr id="${subNode.parentId}" >
							<td class="col-md-7" style="padding-left:20px;word-break:break-all"> <i class="fa fa-caret-right"></i> ${subNode.name}</td>
							<td class="col-md-2" style="width: 250px"><fmt:formatDate value="${subNode.timeCreated}" pattern="yyyy年MM月dd日 "/></td>
							<td class="col-md-3">
								<a href="javascript:deleteRejectReason('${subNode.id}')" class="btn btn-xs btn-primary" data-original-title="Approve">
															<i class="fa fa-times"></i> 删除
							</td>
						</tr>
						<template:rejectReasonTree node="${subNode}"/>
						</c:forEach>
						</c:if>
						</c:forEach>						
					</tbody>
				</table>
			</div> <!-- /.col -->
		</div> <!-- /.row -->
	</div> <!-- /#content-container -->	
</div> <!-- #content -->

<div id="add_reject_reason_dialog" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h3 class="modal-title">添加未通过理由</h3>
			</div>
			<div class="modal-body">
				<form id="reason_form" class="form-horizontal" data-parsley-validate role="form" method="post" action="<c:url value="/task/rejectReason/add"/>">
					<input id="parentId" name="parentId" type="hidden">
					<div class="form-group">
						<label class="col-md-2">上级理由</label>
						<div class="col-md-10">
							<input type="text" class="form-control" id="parentName" name="parentName" readonly onfocus=this.blur()>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2">理由名称</label>
						<div class="col-md-10">
							<input type="text" class="form-control" required data-parsley-length="[1, 100]" data-parsley-trigger="change" id="name" name="name" placeholder="理由名称" autofocus="autofocus">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button id="submit_form_btn" type="button" class="btn btn-primary">确认</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
