<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<div id="content">
		<div id="content-header">
			<h1>当前任务管理</h1>
		</div> <!-- #content-header -->
		
		<div id="content-container">
			<div class="row">
				<div class="col-md-12">
					
					<div id="myTabContent" class="tab-content">
							
							<form id="search_form" method="post" class="breadcrumb form-search" action="" >							
								<input id="pageStart" name="pageStart" type="hidden" value="${taskQueryBean.pageStart}"/>
								<input id="pageSize" name="pageSize" type="hidden" value="${taskQueryBean.pageSize}"/>
								<div class="row" style="margin-bottom:5px">
									<div class="col-xs-3">
										<div class="form-group">
											<label for="text-input">认证号</label>
											<input type="text" placeholder="认证号"  id="id" name="id" value="${taskQueryBean.id}"class="form-control">
										</div>
									</div> <!-- /.col -->

									<div class="col-xs-7">
										<label for="text-input">创建日期</label> 
										<div class="row" style="margin-bottom:5px">
							                <div class="col-sm-6">
									    		<input class="form-control" type="text" placeholder="创建起始日期" id="timeCreateStart" name="timeCreateStart" value="${taskQueryBean.timeCreateStart}" data-date-format="yyyy-mm-dd" data-date-autoclose="true">
											</div>	
											<div class="col-sm-6">
											    <input class="form-control" type="text" placeholder="创建截止日期" id="timeCreateEnd" name="timeCreateEnd" value="${taskQueryBean.timeCreateEnd}" data-date-format="yyyy-mm-dd" data-date-autoclose="true">
											</div>	
										</div>	
									</div> <!-- /.col -->
                                    <div class="col-xs-2 right">
                                        <a id="btnSubmit" href="javascript:submitSearch()" class="btn btn-success" style="margin-top:25px"><i class="fa fa-search"></i> <s:message code="page.list.select"/></a>
                                    </div>

                                    <div class="col-xs-3">
                                        <div class="form-group">
                                            <label for="text-input">审核人</label>
                                            <%--<sec:authorize access="hasAnyRole('admin')">--%>
                                                <select class="form-control" id="assignee" name="assignee">
                                                    <option value=''></option>
                                                    <c:forEach items="${userList}" var="user">
                                                        <option value='${user.id}' <c:if test="${user.id==taskQueryBean.assignee}">selected</c:if>>${user.id}(${user.id})</option>
                                                    </c:forEach>
                                                </select>
                                            <%--</sec:authorize>--%>
                                            <%--<sec:authorize access="hasAnyRole('group_manual_certification', 'group_manual_review')">--%>
                                                <%--<input type="text" placeholder="审核人"  id="taskOwnerSelf" name="taskOwnerSelf" value="${certWorker.fullName}(${certWorker.uid})" readonly onfocus=this.blur() class="form-control">--%>
                                            <%--</sec:authorize>--%>
                                        </div>
                                    </div> <!-- /.col -->

                                    <div class="col-xs-7 ">
										<label for="text-input">截止日期</label> 
										<div class="row" style="margin-bottom:5px">
							                <div class="col-sm-6">
									    		<input class="form-control" type="text" placeholder="完成起始日期" id="dueDateStart" name="dueDateStart" value="${taskQueryBean.dueDateStart}" data-date-format="yyyy-mm-dd" data-date-autoclose="true">
											</div>	
											<div class="col-sm-6">
											    <input class="form-control" type="text" placeholder="完成截止日期" id="dueDateEnd" name="dueDateEnd" value="${taskQueryBean.dueDateEnd}" data-date-format="yyyy-mm-dd" data-date-autoclose="true">
											</div>	
										</div>	
									</div> <!-- /.col -->
                                       <div class="col-xs-2 right">
                                           <a id="btnSubmit" href="javascript:resetSearch()" class="btn btn-success" style="margin-top:25px"><i class="fa fa-trash-o"></i> 清空</a>
                                       </div>

								</div>
							  </form>							  
							<br>
							
							
							<c:choose>
							<c:when test="${taskSize==0}">
							<div class="alert alert-success">
									<strong>当前没有审核任务</strong> 
								</div>
							</c:when>
							
							<c:otherwise>
							<%--<sec:authorize access="hasAnyRole('admin')">--%>
							<div id="action_button">
								<a href="javascript:assignTask()" class="btn btn-info btn-sm">
									<i class="fa fa-share"></i> 指派任务
								</a>
							</div>	
							<%--</sec:authorize>					--%>
							<p></p>
							<form id="task_form" method="post" action="<c:url value="/task/assignTask"/>">
								<input type="hidden" name="assignee" id="assignee" value="">
							<table 
								class="table table-striped table-bordered table-hover table-highlight table-checkable" >
									<thead>
										<tr>
											<th class="checkbox-column">
												<input type="checkbox" class="icheck-input">
											</th>
											<th data-filterable="true" data-sortable="true">认证号</th>
											<th data-direction="asc" data-filterable="true" data-sortable="true">审核人</th>
											<th data-filterable="true" data-sortable="true">类别</th>
											<th data-filterable="true" data-sortable="true">创建日期</th>
											<th data-filterable="true" data-sortable="true">截止日期</th>
											<th data-filterable="false" data-sortable="false">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${manualTaskPage.list}" var="taskbean">
										<tr>
											<td class="checkbox-column">
												<input id="taskIds" name="taskIds" type="checkbox" class="icheck-input" value="${taskbean.id}">
											</td>
											<td>${taskbean.processInstanceId }</td>
											<td>${taskbean.assignee }</td>
											<td>${taskbean.name}</td>
											<td class="hidden-xs hidden-sm">
												<c:if test="${taskbean.createTime==null }">
													无
												</c:if>  
												<c:if test="${taskbean.createTime!=null }">
													<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${taskbean.createTime}" />
												</c:if>
											</td>
											<td class="hidden-xs hidden-sm">
												<c:if test="${taskbean.dueDate==null }">
													无
												</c:if>  
												<c:if test="${taskbean.dueDate!=null }">
													<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${taskbean.dueDate}" />
												</c:if>
											</td>
											<td>
												<a href="<c:url value="/task/view/${taskbean.processInstanceId}"/>" class="btn btn-xs btn-default">
													<i class="fa fa-search"></i> <s:message code="page.list.view"/>
												</a>
					                        </td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
								</form>
								${manualTaskPage}
								</c:otherwise>
								</c:choose>
					</div> <!-- /.myTabContent -->
				</div> <!-- /.col -->
			</div> <!-- /.row -->
			
		</div> <!-- /#content-container -->			
		
	</div> <!-- #content -->
	
	
	<div id="assignDialog" class="modal fade">
	  <div class="modal-dialog" style="width: 400px;">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h3 id="assignDialogTitle" class="modal-title">任务</h3>
		  </div>		  
		  <div id="assignDialogContent" class="modal-body">
		  		<div class="form-group">	
					<label for="select-input">选择指派人：</label>
					<select class="form-control" id="select-input">
						<option value=''></option>
						<c:forEach items="${userList}" var="user">
							<option value='${user.id}' <c:if test="${user.id==taskQueryBean.assignee}">selected</c:if>>${user.id}(${user.id})</option>
						</c:forEach>
					</select>
				</div>
		  </div>
		  <div class="modal-footer">
			<button id="submit_form_btn" type="button" class="btn btn-primary" onclick="submitAssign()" data-loading-text="提交中..."><s:message code="page.common.confirm"/></button>
			<button id="assignDialogCancelBtn" type="button" class="btn btn-default" data-dismiss="modal"><s:message code="page.common.cancel"/></button>
		  </div>
		</div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	