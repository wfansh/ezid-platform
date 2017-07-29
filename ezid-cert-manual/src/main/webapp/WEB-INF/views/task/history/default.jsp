<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<div id="content">		
		<div id="content-header">
			<h1>历史任务管理</h1>
		</div> <!-- #content-header -->	
		
		<div id="content-container">
			<div class="row">
				<div class="col-md-12">
					
					<div id="myTabContent" class="tab-content">
							
							<form id="search_form" method="post" class="breadcrumb form-search" action="" >
								<input id="pageStart" name="pageStart" type="hidden" value="${taskQueryBean.pageStart}"/>
								<input id="pageSize" name="pageSize" type="hidden" value="${taskQueryBean.pageSize}"/>
								<div class="row" style="margin-bottom:5px">

                                    <div class="col-sm-5">
                                        <label for="text-input">创建日期</label>
                                        <div class="row" style="margin-bottom:5px">
                                            <div class="col-sm-6">
                                                <input class="form-control" type="text" placeholder="创建起始日期" id="timeCreateStart" name="timeCreateStart" value="${taskQueryBean.timeCreateStart}" data-date-format="yyyy-mm-dd" data-date-autoclose="true">
                                            </div>
                                            <div class="col-sm-6">
                                                <input class="form-control" type="text" placeholder="创建截止日期" id="timeCreateEnd" name="timeCreateEnd" value="${taskQueryBean.timeCreateEnd}" data-date-format="yyyy-mm-dd" data-date-autoclose="true">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-sm-5">
									<div class="col-sm-6">
										<div class="form-group">
											<label for="text-input">认证号</label>
											<input type="text" placeholder="认证号"  id="id" name="id" value="${taskQueryBean.id}"class="form-control">
										</div>
									</div> <!-- /.col -->

									<div class="col-sm-6">
										<div class="form-group">
                                            <sec:authorize access="hasAnyRole('admin')">
                                            <label for="text-input">审核人</label>
											<select class="form-control" id="assignee" name="assignee">
												<option value=''></option>
												<c:forEach items="${userList}" var="user">
													<option value='${user.id}' <c:if test="${user.id==taskQueryBean.assignee}">selected</c:if>>${user.id}(${user.id})</option>
												</c:forEach>
											</select>
											</sec:authorize>
											<%--<sec:authorize access="hasAnyRole('group_manual_certification', 'group_manual_review')">--%>
												<%--<input type="text" placeholder="审核人"  id="taskOwnerSelf" name="taskOwnerSelf" value="" readonly onfocus=this.blur() class="form-control">--%>
											<%--</sec:authorize>--%>
										
										</div>
									</div> <!-- /.col -->
                                    </div>


                                    <div class="col-sm-2">
                                        <a id="btnSubmit" href="javascript:submitSearch()" class="btn btn-success" style="margin-top:25px"><i class="fa fa-search"></i> <s:message code="page.list.select"/></a>
                                    </div> <!-- /.col -->
								</div>
								<div class="row" style="margin-bottom:5px">
                                    <div class="col-sm-5">
                                        <label for="text-input">完成日期</label>
                                        <div class="row" style="margin-bottom:5px">
                                            <div class="col-sm-6">
                                                <input class="form-control" type="text" placeholder="完成起始日期" id="timeFinishStart" name="timeFinishStart" value="${taskQueryBean.timeFinishStart}" data-date-format="yyyy-mm-dd" data-date-autoclose="true">
                                            </div>
                                            <div class="col-sm-6">
                                                <input class="form-control" type="text" placeholder="完成截止日期" id="timeFinishEnd" name="timeFinishEnd" value="${taskQueryBean.timeFinishEnd}" data-date-format="yyyy-mm-dd" data-date-autoclose="true">
                                            </div>
                                        </div>
                                    </div>

									 <!-- /.col -->
                                    <div class="col-md-5">
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label for="text-input">结果</label>
                                            <select class="form-control" id="result" name="result">
                                                <option value=''></option>
                                                <option value='通过'>通过</option>
                                                <option value='未通过'>未通过</option>
                                            </select>
                                        </div>
                                    </div> <!-- /.col -->
                                        <div class="col-sm-6">
                                            <%--<div class="form-group">--%>
                                            <%--<label for="text-input">类别</label>--%>
                                            <%--<select class="form-control" id="taskDefinitionKey" name="taskDefinitionKey">--%>
                                            <%--<option value=''></option>--%>
                                            <%--<option value='manual_certification'>照片人工审核</option>--%>
                                            <%--<option value='manual_review'>照片人工复核</option>--%>
                                            <%--</select>--%>
                                            <%--</div>--%>
                                        </div> <!-- /.col -->
                                    </div>

									 <!-- /.col -->
                                    <div class="col-sm-2">
                                        <a id="btnSubmit" href="javascript:resetSearch()" class="btn btn-success" style="margin-top:25px"><i class="fa fa-trash-o"></i> 清空</a>
                                    </div> <!-- /.col -->
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
							<p></p>
							<form id="task_form" method="post" action="<c:url value="/task/assignTask"/>">								
							<table 
								class="table table-striped table-bordered table-hover table-highlight table-checkable" >
									<thead>
										<tr>
											<th class="col-md-3" data-filterable="true" data-sortable="true">认证号</th>
											<th data-filterable="true" data-sortable="true">审核人</th>
											<th data-filterable="true" data-sortable="true">结果</th>
											<th data-filterable="true" data-sortable="true">备注</th>
											<th data-filterable="true" data-sortable="true">创建日期</th>
											<th data-filterable="true" data-sortable="true">完成日期</th>
											<th data-filterable="false" data-sortable="false">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${manualTaskPage.list}" var="taskbean">
										<tr>
											<td>${taskbean.processInstanceId }</td>
											<td>${taskbean.assignee }</td>
											<td>${taskbean.result}</td>
											<td>${taskbean.resultDesc}</td>
											<td class="hidden-xs hidden-sm">
												<c:if test="${taskbean.startTime==null }">
													无
												</c:if>  
												<c:if test="${taskbean.startTime!=null }">
													<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${taskbean.startTime}" />
												</c:if>
											</td>
											<td class="hidden-xs hidden-sm">
												<c:if test="${taskbean.endTime==null }">
													无
												</c:if>  
												<c:if test="${taskbean.endTime!=null }">
													<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${taskbean.endTime}" />
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
	

	