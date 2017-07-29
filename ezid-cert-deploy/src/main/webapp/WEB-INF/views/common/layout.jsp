<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<tiles:importAttribute name="jsFile" /> 
<!DOCTYPE html>
<html class="no-js">
	<head>
	    <title>葆诚系统管理模块</title>
	    <link rel="icon" href="<c:url value="/img/favicon.ico" />" type="image/x-icon" />
		<link rel="shortcut icon" href="<c:url value="/img/favicon.ico" />" type="image/x-icon" />  
		<meta charset="utf-8"/>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	    <meta name="description" content=""/>
		<meta name="author" content="" />
		
		<tiles:insertAttribute name="css" />			
		
	</head>
	<body>
		<div id="wrapper">	
			<tiles:insertAttribute name="header" />
			<tiles:insertAttribute name="menu" />
			<tiles:insertAttribute name="body" />	
		</div> <!-- #wrapper -->	
		
		<tiles:insertAttribute name="footer" />		
		<tiles:insertAttribute name="js" />
	
	</body>
</html>