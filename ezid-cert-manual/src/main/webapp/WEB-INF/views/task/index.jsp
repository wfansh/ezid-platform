<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<div id="wrapper">

	<div id="content">

		<div id="content-header">
			<h1>任务统计</h1>
		</div>
		<!-- #content-header -->
		<div id="content-container">
			<sec:authorize access="hasAnyRole('admin')">		
			<div class="row">
				<div class="col-md-3">
					<a class="dashboard-stat primary" href="javascript:;">
						<div class="visual">
							<i class="fa fa-star"></i>
						</div> <!-- /.visual -->
						<div class="details">
							<span class="content">已超时</span> <span class="value">${summaryMap['overDue']}</span>
						</div> <!-- /.details --> <i class="fa fa-play-circle more"></i>
					</a>
					<!-- /.dashboard-stat -->
				</div>
				<!-- /.col-md-3 -->
				<div class="col-md-3">
					<a class="dashboard-stat secondary" href="javascript:;">
						<div class="visual">
							<i class="fa fa-shopping-cart"></i>
						</div> <!-- /.visual -->
						<div class="details">
							<span class="content">即将超时</span> <span class="value">${summaryMap['threeDaysDue']}</span>
						</div> <!-- /.details --> <i class="fa fa-play-circle more"></i>
					</a>
					<!-- /.dashboard-stat -->
				</div>
				<!-- /.col-md-3 -->
				<div class="col-md-3">
					<a class="dashboard-stat tertiary" href="javascript:;">
						<div class="visual">
							<i class="fa fa-clock-o"></i>
						</div> <!-- /.visual -->
						<div class="details">
							<span class="content">待完成</span> <span
								class="value">${summaryMap['todo']}</span>
						</div> <!-- /.details --> <i class="fa fa-play-circle more"></i>
					</a>
					<!-- /.dashboard-stat -->
				</div>
				<!-- /.col-md-3 -->
				<div class="col-md-3">
					<a class="dashboard-stat" href="javascript:;">
						<div class="visual">
							<i class="fa fa-money"></i>
						</div> <!-- /.visual -->
						<div class="details">
							<span class="content">今日完成</span> <span class="value">${summaryMap['finishToday']}</span>
						</div> <!-- /.details --> <i class="fa fa-play-circle more"></i>
					</a>
					<!-- /.dashboard-stat -->
				</div>
				<!-- /.col-md-9 -->
			</div>
			</sec:authorize>
			<div class="row">
				<div class="col-md-12">
				<table 
				class="table table-striped table-bordered table-hover table-highlight table-checkable" >
					<thead>
						<tr>
							<th data-sortable="true">审核人</th>
							<th data-filterable="true" data-sortable="true">审核中</th>
							<th data-filterable="true" data-sortable="true">今日完成</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${userList}" var="user">
						<tr>
							<td>${user.firstName}</td>
							<td>${user.url}</td>
							<td>${user.email}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
				<!-- /.col-md-9 -->
			</div>
		</div>
		<!-- /#content-container -->
	</div>
	<!-- #content -->
</div>
<!-- #wrapper -->

