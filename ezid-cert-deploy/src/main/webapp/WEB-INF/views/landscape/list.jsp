<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<div id="wrapper">
	
	<div id="content">		
		
		<div id="content-header">
			<h1>应用模块管理</h1>
		</div> <!-- #content-header -->	

		<div id="content-container">
			<div class="row">
				<div class="col-md-12">
					<div id="action_button">
						<p>
							<a href="javascript:toAddEngine()" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i> 添加引擎</a>
						</p>
					</div>					
					<p></p>
										
					<table id="ListTable" class="table table-striped table-bordered table-checkable table-highlight">
						<thead>
							<tr>
								<th width="10%">引擎</th>
								<th width="10%">应用</th>
								<th width="10%">模块</th>
								<th width="10%">状态</th>
								<th width="40%">备注</th>
								<th width="10%">执行时间</th>
								<th width="10%">更新时间</th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${resourceList}" var="engine">
							<tr>
								<td rowspan="${engine.subSize}">
									<div class="dashboard-stat primary">		
										<div class="details">
											<span class="value">${engine.resourceName}</span>
											<span class="content">${engine.url}</span>		
											<a href="javascript:toAddApp(${engine.engineId});" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i> 添加应用</a>											
										</div> <!-- /.details -->		
										<i class="fa fa-wrench edit"></i>			
										<i onclick="javascript:deleteResource('${engine.engineId}','${engine.resourceType}','${engine.resourceName}')" class="fa fa-times del"></i>																											
									</div>
								</td>
							<c:choose>
							<c:when test="${engine.subSize>0}">
								<c:forEach items="${engine.subLandscapeViews}" var="module" varStatus="moduleIndex">
									<td rowspan="${module.subSize}">
											<div class="dashboard-stat secondary">
												<div class="details">
													<span class="value">${module.resourceName}</span>
													<span class="content">${module.url}</span>
													<c:if test="${module.resourceType == 'app'}">	
														<a href="javascript:toAddExecutor(${engine.engineId},${module.moduleId},'${module.resourceType}');" class="btn btn-secondary btn-sm"><i class="fa fa-plus"></i> 添加模块</a>	
													</c:if>														
												</div> <!-- /.details -->							
												<i class="fa fa-wrench edit"></i>			
												<i onclick="javascript:deleteResource('${module.moduleId}','${module.resourceType}','${module.resourceName}')" class="fa fa-times del"></i>	
											</div>
										</td>
									<c:forEach items="${module.subLandscapeViews}" var="executor" varStatus="executorIndex">											
											<td>
												<div class="dashboard-stat module <c:if test="${module.resourceType=='manual'}">tertiary</c:if>">
													<div class="details">
															<span class="value">${executor.resourceName}</span>
														<c:if test="${module.resourceType=='app'}">
															<span class="content">Name: ${executor.userName}<br>Pass: ${executor.password}</span>	
														</c:if>														
													</div> <!-- /.details -->							
														<i class="fa fa-wrench edit"></i>			
														<i onclick="javascript:deleteResource('${executor.id}','${executor.resourceType}','${executor.resourceName}')"class="fa fa-times del"></i>	
												</div>
											</td>
											<td>
												<input 
													id="${executor.id}"
													name="${engine.resourceName} / ${module.resourceName} / ${executor.resourceName}"
													type="checkbox" 
													class="status_box" 
													data-on-text="On"
													<c:if test="${executor.status=='ON'}">
														checked
														data-off-text="Off"
													</c:if>
													<c:if test="${executor.status=='OFF'}">
														data-off-text="Off"
													</c:if>
													<c:if test="${executor.status=='ERROR'}">
														data-off-color="danger"
														data-off-text="Error"
													</c:if>
												>
											</td>
											<td id="desc_${executor.id}" <c:if test="${executor.status=='ERROR'}">style="color:red"</c:if>>${executor.description}</td>
											<td>												
												<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${executor.timeExecuted}" /><br>
												<p style="color:blue;">${executor.lastTime}</p>
											</td>
											<td id="updateTime_${executor.id}">
												<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${executor.timeUpdated}" />
											</td>
										</tr>
									</c:forEach>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</c:otherwise>
							</c:choose>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div> <!-- /#content-container -->	
	</div> <!-- #content -->	
</div> <!-- #wrapper -->

	<div id="addEngineDialog" class="modal fade">
	  <div class="modal-dialog">
		<div class="modal-content">
		
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h3 class="modal-title">添加引擎</h3>
		  </div>		  
		  <div id="modal-body" class="modal-body">
		  		<form id="add_engine_form" class="form-horizontal" data-parsley-validate role="form" method="post" action="<c:url value="/landscape/addLandscape"/>">
					<input type="hidden" name="resourceType" value="engine">
			  		<div class="form-group">
						<label class="col-md-2">名称</label>
						<div class="col-md-8">
						    <input type="text" class="form-control" required data-parsley-length="[1, 50]" data-parsley-trigger="change" id="resourceName" name="resourceName"  placeholder="名称"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2">URL</label>
						<div class="col-md-8">
						    <input type="text" class="form-control" required data-parsley-length="[1, 500]" data-parsley-trigger="change" id="url" name="url"  placeholder="URL"/>
						</div>
					</div>
				</form>
		  </div>
			
		  <div class="modal-footer">
			<button type="button" class="btn btn-primary" onclick="addEngine();"><s:message code="page.common.confirm"/></button>
			<button type="button" class="btn btn-default" data-dismiss="modal"><s:message code="page.common.cancel"/></button>
		  </div>	  
		</div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	
	<div id="addAppDialog" class="modal fade">
	  <div class="modal-dialog">
		<div class="modal-content">
		
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h3 class="modal-title">添加应用</h3>
		  </div>		  
		  <div id="modal-body" class="modal-body">
		  		<form id="add_app_form" class="form-horizontal" data-parsley-validate role="form" method="post" action="<c:url value="/landscape/addLandscape"/>">
		  			<input type="hidden" name="engineId" id="engineId" value="">
			  		<div class="form-group">
						<label class="col-md-2">名称</label>
						<div class="col-md-8">
						    <input type="text" class="form-control" required data-parsley-length="[1, 50]" data-parsley-trigger="change" id="resourceName" name="resourceName"  placeholder="名称"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2">机器名</label>
						<div class="col-md-8">
						    <input type="text" class="form-control" required data-parsley-length="[1, 500]" data-parsley-trigger="change" id="url" name="url" placeholder="机器名"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2">类型</label>
						<div class="col-md-8">
							<label class="radio-inline">
							  <input type="radio" name="resourceType" class="" value="app" checked> App
							</label>
							<label class="radio-inline">
							  <input type="radio" name="resourceType" class="" value="manual"> Manual
							</label>
						</div>
					</div>
					<div class="form-group app">
						<label class="col-md-2">账号</label>
						<div class="col-md-8">
						    <input type="text" class="form-control" required data-parsley-length="[1, 50]" data-parsley-trigger="change" id=userName name="userName" placeholder="账号"/>
						</div>
					</div>
					<div class="form-group app">
						<label class="col-md-2">密码</label>
						<div class="col-md-8">
						    <input type="text" class="form-control" required data-parsley-length="[1, 50]" data-parsley-trigger="change" id="password" name="password" placeholder="密码"/>
						</div>
					</div>
				</form>
		  </div>
			
		  <div class="modal-footer">
			<button id="addAppConfirmBtn" type="button" class="btn btn-primary" onclick="addApp();"><s:message code="page.common.confirm"/></button>
			<button id="addAppCancelBtn" type="button" class="btn btn-default" data-dismiss="modal"><s:message code="page.common.cancel"/></button>
		  </div>	  
		</div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id="addExecutorDialog" class="modal fade">
	  <div class="modal-dialog">
		<div class="modal-content">
		
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h3 class="modal-title">添加模块</h3>
		  </div>		  
		  <div id="modal-body" class="modal-body">
		  		<form id="add_executor_form" class="form-horizontal" data-parsley-validate role="form" method="post" action="<c:url value="/landscape/addLandscape"/>">
		  			<input type="hidden" name="engineId" id="engineId" value="">
		  			<input type="hidden" name="moduleId" id="moduleId" value="">
		  			<input type="hidden" name="resourceType" id="resourceType" value="task_executor">
					<div class="form-group">
						<label class="col-md-2">类型</label>
						<div class="col-md-8 app">
							<c:forEach items="${appList}" var="appEntity">
								<label class="radio-inline app">
								  <input type="radio" name="taskExecutorId" class="" value="${appEntity.id}" checked> ${appEntity.resourceName}
								</label>
							</c:forEach>
						</div>
<!-- 					<div class="col-md-8 manual">
							<c:forEach items="${manualList}" var="manualEntity">
								<label class="radio-inline manual">
								  <input type="radio" name="taskExecutorId" class="" value="${manualEntity.id}" checked> ${manualEntity.resourceName}
								</label>
							</c:forEach>
						</div> -->	
					</div>
					<div class="form-group app">
						<label class="col-md-2">账号</label>
						<div class="col-md-8">
						    <input type="text" class="form-control" required data-parsley-length="[1, 50]" data-parsley-trigger="change" id=userName name="userName" placeholder="账号"/>
						</div>
					</div>
					<div class="form-group app">
						<label class="col-md-2">密码</label>
						<div class="col-md-8">
						    <input type="text" class="form-control" required data-parsley-length="[1, 50]" data-parsley-trigger="change" id="password" name="password" placeholder="密码"/>
						</div>
					</div>
				</form>
		  </div>
			
		  <div class="modal-footer">
			<button id="addExecutorConfirmBtn" type="button" class="btn btn-primary" onclick="addExecutor();"><s:message code="page.common.confirm"/></button>
			<button id="addExecutorCancelBtn" type="button" class="btn btn-default" data-dismiss="modal"><s:message code="page.common.cancel"/></button>
		  </div>	  
		</div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
