<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> 
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<tiles:importAttribute name="activeMenu" />
<div id="sidebar-wrapper" class="collapse sidebar-collapse">
		
		<div id="search">
<!-- 			<form> -->
<%-- 				<input class="form-control input-sm" type="text" name="search" placeholder="<s:message code="page.menu.select"/>" /> --%>

<!-- 				<button type="submit" id="search-btn" class="btn"><i class="fa fa-search"></i></button> -->
<!-- 			</form>		 -->
		</div> <!-- #search -->
	
		<nav id="sidebar">		
			
			<ul id="main-nav" class="open-active">	
				<li class="dropdown <c:if test="${activeMenu=='task'}">active</c:if>">				
					<a href="javascript:;">
						<i class="fa fa-tasks"></i>
						任务管理
						<span class="caret"></span>
					</a>				
					<ul class="sub-nav">
                        <sec:authorize access="hasAnyRole('admin')">
						<li>
							<a href="<c:url value="/task/" />">
								<i class="fa fa-dashboard"></i>
								任务统计
								<span id="total_task_count" class="badge task-count"></span>
							</a>
						</li>
						<li>
							<a href="<c:url value="/task/listTask" />">
								<i class="fa fa-inbox"></i>
								当前任务管理
								<span id="total_task_count" class="badge task-count"></span>
							</a>
						</li>
						</sec:authorize>
						<li>
							<a href="<c:url value="/task/historyTask" />">
								<i class="fa fa-hdd"></i>
								历史任务管理
							</a>
						</li>
						<li>
							<a href="<c:url value="/task/reviewTask" />">
								<i class="fa fa-legal"></i>
								开始审核任务
								<span id="certification_task_count" class="badge task-count"></span>
							</a>
						</li>
						<sec:authorize access="hasAnyRole('admin')">
						<li>
							<a href="<c:url value="/task/rejectReason" />">
								<i class="fa fa-ban"></i>
								未通过理由管理
							</a>				
						</li>
						</sec:authorize>
					</ul>
				</li>
			</ul>
					
		</nav> <!-- #sidebar -->

	</div> <!-- /#sidebar-wrapper -->