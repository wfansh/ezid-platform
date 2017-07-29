<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>500 页面错误</title>
    <link rel="icon" href="<c:url value="/img/favicon.ico" />" type="image/x-icon" />
	<link rel="shortcut icon" href="<c:url value="/img/favicon.ico" />" type="image/x-icon" />  
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="">
	<meta name="author" content="" />

	<link rel="stylesheet" href="<c:url value="/css/font-awesome.min.css" />" type="text/css" />		
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" type="text/css" />	
	<link rel="stylesheet" href="<c:url value="/js/libs/css/ui-lightness/jquery-ui-1.9.2.custom.css" />" type="text/css" />
	<link rel="stylesheet" href="<c:url value="/css/App.css" />" type="text/css" />
</head>
<body style="background:none;">

					<div class="error-container">

						<div class="error-code">
							500
						</div> <!-- /.error-code -->

						<div class="error-details">
							<h3>错误描述：</h3>
							<h4>很抱歉看来您当前的操作出现了某种问题。</h4>
							<h4>${error}</h4>
							<a class="btn btn-primary" href="<c:url value="/" />">返回首页</a>
						</div> <!-- /.error-details -->

					</div> <!-- /.error -->


</body>
</html>