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
				<li class="<c:if test="${activeMenu=='landscape'}">active</c:if>">				
					<a href="<c:url value="/landscape" />">
						<i class="fa fa-sitemap"></i>
						应用模块管理
					</a>				
				</li>
			</ul>
					
		</nav> <!-- #sidebar -->

	</div> <!-- /#sidebar-wrapper -->