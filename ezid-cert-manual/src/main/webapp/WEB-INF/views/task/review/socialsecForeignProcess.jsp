<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="content">
	<div id="content-header">
		<div id="debug" style="position: fixed"></div>
		<h1>审核任务(海外认证流程)</h1>
	</div>
	<!-- #content-header -->

	<div id="content-container">
		<c:choose>
			<c:when test="${empty(taskList)}">
				<div class="alert alert-success">
					<strong>当前没有审核任务可以操作</strong>
				</div>
			</c:when>
			<c:otherwise>
				<div class="row">
					<div class="col-md-2" style="max-width:300px">
						<div class="portlet" style="">
							<div class="portlet-header">
								<h3>
									<i class="fa fa-bar-chart-o"></i>
									<h1></h1>
								</h3>
								<ul class="portlet-tools pull-right">
									<%--<li><span id="person_count" class="badge">0/${fn:length(taskList)}</span>--%>
									<%--</li>--%>
								</ul>
							</div>
							<form id="task_form"
								action="<c:url value="/task/completeTask" />" method="POST">
								<input id="nextAction" name="nextAction" type="hidden" value="">
								<input id="taskCount" name="taskCount" type="hidden" value="${fn:length(taskList)}">
								<div class="portlet-content">
									<ul id="person_list" class="nav nav-list nav-pills nav-stacked">
										<c:forEach items="${taskList}" var="item" varStatus="status"    >
											<li id="${item.processInstanceId}" videoFilePath="${item.videoFilePaths[0]}" class="not-done">
												<a href="javascript:getComment('${item.processInstanceId}','${item.videoFilePaths[0]}')">
													${status.index+1}
													<h4	class="pull-right"><i></i></h4>
												</a> 
												<input id="taskId" name="taskId" type="hidden" value="${item.taskId}">
                                                <input id="result" name="result" type="hidden" value="">
                                                <input id="comment" name="comment" type="hidden" value="">
											</li>
										</c:forEach>
									</ul>
									<div style="text-align: center; margin: 0 auto;">
                                        <div>
                                            <a id="submitTask_continue" class="btn btn-success submit_btn"
                                               href="javascript:submitTask('continue','end')"
                                               data-loading-text="提交中..." style="display: none" disabled>提交并继续</a>
                                        </div>
                                       <div style="margin-top: 10px">
                                           <a id="submitTask_end"
                                              class="btn btn-success submit_btn"
                                              href="javascript:submitTask('end','continue')"
                                              data-loading-text="提交中..." style="display: none">提交并结束</a>
                                       </div>
									</div>
								</div>
							</form>

						</div>

					</div>

					<div class="col-md-10">

                        <div class="buttons">
                                <%--<div class="btn-group" id="videobtn">--%>
                                <%--<a class="btn btn-info" href="javascript:getVideos()"> <i--%>
                                <%--class="fa fa-info 2x"></i> 查看视频--%>
                                <%--</a>--%>
                                <%--</div>--%>
                            <a id="approve_btn" class="btn btn-primary" href="javascript:;"><i
                                    class="fa fa-check 2x"></i> 通过</a>

                            <div class="btn-group">
                                <a id="other_reason_li" class="btn btn-primary dropdown-toggle"
                                   data-toggle="dropdown" href="javascript:;"> <i
                                        class="fa fa-ban 2x"></i> 不通过
                                </a>
                                <ul id="ban_btn" class="dropdown-menu">
                                    <c:forEach items="${taskRejectReasonList}" var="reason">
                                    <c:if test="${not empty(reason.subReasonList)}">
                                    <li class="dropdown-submenu"><a href="javascript:;">${reason.name}</a>
                                        <ul class="dropdown-menu">
                                            <c:forEach items="${reason.subReasonList}" var="subReason">
                                                <li><a class="selectReason" data-toggle="tooltip" data-placement="right" title="${subReason.name}"
                                                       href="javascript:;" style="width:400px; text-overflow:ellipsis; white-space:nowrap;overflow:hidden;"
                                                       id="${subReason.fullName}">${subReason.name}</a></li>
                                            </c:forEach>
                                        </ul></li>
                                    </c:if>
                                    </c:forEach>
                            </div>
                            <a id="unclaim_btn" class="btn btn-secondary" href="javascript:;"><i
                                    class="fa fa-mail-forward 2x"></i> 放弃</a>
                        </div>
                        <hr/>
                        <!-- buttons -->
                        <div>
                            <h4>
                                <span id="unpassComment"></span>
                            </h4>
                        </div>
                        <br>
                        <div id="submit_info" class="alert alert-success"
							style="display: none;">
							当前任务审核结果已都标注完成，您可以点击“提交并继续”自动开始下一批任务，点击“提交并结束”完成当前任务，也可以点击审核名单再次修改后确认提交
							。</div>
						<div id="person_pic">
							<c:forEach items="${taskList}" var="item">
								<div id="${item.processInstanceId}" class="row shenhe">
                                    <h2>${item.processInstanceId}</h2>
                                    <hr/>
									<h3>${item.sex} <c:if test="${not empty item.age}"> ${item.age}岁</c:if></h3>
									<h3><c:if test="${not empty item.idcardNum}"> ${item.idcardNum} (身份证)</c:if></h3>
									<h3><c:if test="${not empty item.passportNum}"> ${item.passportNum} (护照)</c:if></h3>
									
									<div>
										<c:choose>
											<c:when test="${!empty item.idcardPhotoFilePath}">
												<a class="thumbnail-view-hover" href="javascript:photoCompare('${item.processInstanceId}',0)">
													<img src="<c:url value="/res/${item.idcardPhotoFilePath}" />"
														class="thumbnail-view-hover img-responsive thumbnail" alt="Responsive image">
												</a>
												<span>身份证</span>
											</c:when>
											
											<c:when test="${!empty item.idcardPrintFilePath}">
												<a class="thumbnail-view-hover" href="javascript:photoCompare('${item.processInstanceId}',0)">
													<img src="<c:url value="/res/${item.idcardPrintFilePath}" />"
														class="thumbnail-view-hover img-responsive thumbnail" alt="Responsive image">
												</a>
												<span>身份证</span>
											</c:when>

											<c:when test="${!empty item.passportPrintFilePath}">
												<a class="thumbnail-view-hover" href="javascript:photoCompare('${item.processInstanceId}',0)">
													<img src="<c:url value="/res/${item.passportPrintFilePath}" />"
														class="thumbnail-view-hover img-responsive thumbnail" alt="Responsive image">
												</a>
												<span>护照</span>
											</c:when>
									</c:choose>
									</div>

									<c:forEach items="${item.photoFilePaths}" var="photoPath" varStatus="varStat">
										<div>
											<a class="thumbnail-view-hover"	href="javascript:photoCompare('${item.processInstanceId}', ${varStat.index})">
												<img id="photo_${varStat.index}" src="<c:url value='/res/${photoPath}'/>" style="display: none">
												<img src="<c:url value="/res/${photoPath}" />" class="thumbnail-view-hover img-responsive thumbnail" 
													alt="Responsive image" onerror="this.src='<c:url value="/img/default_photo.jpg'" />">
											</a> <span>采集照</span>
										</div>
									</c:forEach>
									
                                    <div id="person_video_${item.processInstanceId}">
                                    </div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<div class="modal fade" id="photoVideo" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 700px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel"></h4>
			</div>
			<div class="modal-body" id="detail_main">加载中...</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div id="photo_compare" class="modal modal-styled fade" id="styledModal"
	style="display: none;" aria-hidden="true">
	<div class="modal-dialog text-center"
		style="width: 100%; height: 100%;">
		<div class="row">
			<div class="col-md-6">
				<img id="left" style="float: right" src=""
					class="thumbnail-view-hover img-responsive thumbnail"
					alt="Responsive image">
			</div>
			<div class="col-md-6">
				<img id="right" src=""
					class="thumbnail-view-hover img-responsive thumbnail"
					alt="Responsive image">
			</div>
		</div>
		<br>
		<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
	</div>
	<!-- /.modal-dialog -->
</div>