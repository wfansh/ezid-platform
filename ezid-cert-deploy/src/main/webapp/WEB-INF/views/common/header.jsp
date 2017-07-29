<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="contextPath_input" value="${contextPath}">
	<header id="header">

		<h1 id="site-logo">
			<a href="<c:url value="/"/>">
				<img src="<c:url value="/img/logos/logo.png" />" alt="葆诚身份认证平台" />
			</a>
		</h1>	

		<a href="javascript:;" data-toggle="collapse" data-target=".top-bar-collapse" id="top-bar-toggle" class="navbar-toggle collapsed">
			<i class="fa fa-cog"></i>
		</a>

		<a href="javascript:;" data-toggle="collapse" data-target=".sidebar-collapse" id="sidebar-toggle" class="navbar-toggle collapsed">
			<i class="fa fa-reorder"></i>
		</a>

	</header> <!-- header -->

	<nav id="top-bar" class="collapse top-bar-collapse">
		<ul class="nav navbar-nav pull-left">
			<li class="dropdown">
		    	<a>
		        	<i class="fa fa-home"></i> 葆诚系统管理模块
		    	</a>
		    </li>
		</ul>

		<ul class="nav navbar-nav pull-right">
			<li class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;">
					<i class="fa fa-user"></i>
		        	<security:authentication property="principal.username" />
		        	<span class="caret"></span>
		    	</a>

		    	<ul class="dropdown-menu" role="menu">
			        <li>
			        	<a href="<c:url value="/logout" />">
			        		<i class="fa fa-sign-out"></i> 
			        		&nbsp;&nbsp;退出登录
			        	</a>
			        </li>
                    

		    	</ul>
		    </li>
		</ul>
	</nav> <!-- /#top-bar -->

