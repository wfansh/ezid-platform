<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>

    <title>登录</title>
    <link rel="icon" href="<c:url value="/img/favicon.ico" />" type="image/x-icon" />
	<link rel="shortcut icon" href="<c:url value="/img/favicon.ico" />" type="image/x-icon" />  
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="">
	<meta name="author" content="" />
	<link rel="stylesheet" href="./css/font-awesome.min.css" type="text/css" />		
	<link rel="stylesheet" href="./css/bootstrap.min.css" type="text/css" />	
	<link rel="stylesheet" href="./js/libs/css/ui-lightness/jquery-ui-1.9.2.custom.css" type="text/css" />	
	<link rel="stylesheet" href="./css/App.css" type="text/css" />
	<link rel="stylesheet" href="./css/Login.css" type="text/css" />
	<link rel="stylesheet" href="./css/custom.css" type="text/css" />
</head>

<body>
%2F  
<div id="login-container">

	<div id="logo">
		<a href="./login.html">
			<img src="./img/logos/logo-login.png" alt="Logo" />
		</a>
	</div>

	<div id="login">

		<h3>葆诚身份审核平台</h3>
		<form id="login-form" action="<c:url value="/dologin"/>" class="form" method="post">
			<c:if test="${param.error == 0}">
			<div class="alert alert-danger">
				<a class="close" data-dismiss="alert" href="#" aria-hidden="true">&times;</a>
				验证码有误.
			</div>
			</c:if>
			<c:if test="${param.error == 1}">
			<div class="alert alert-danger">
				<a class="close" data-dismiss="alert" href="#" aria-hidden="true">&times;</a>
				用户名密码有误.
			</div>
			</c:if>
			<c:if test="${param.error == 2}">
			<div class="alert alert-danger">
				<a class="close" data-dismiss="alert" href="#" aria-hidden="true">&times;</a>
				您的帐号在别处登陆.
			</div>
			</c:if>
			<c:if test="${not empty param.logout}">
			<div class="alert alert-success">
				<a class="close" data-dismiss="alert" href="#" aria-hidden="true">&times;</a>
				您已经成功退出.
			</div>
			</c:if>
			
			<div class="form-group">
				<label for="login-username">用户名</label>
				<input type="text" class="form-control" id="login-username" name="j_username" placeholder="用户名" autofocus="autofocus">
			</div>

			<div class="form-group">
				<label for="login-password">密码</label>
				<input type="password" class="form-control" id="login-password" name="j_password" placeholder="密码">
			</div>
			
			<!--div class="form-group text-center">
				<div class="row">
					<div class="col-sm-6">
						<label for="login-captcha">验证码</label>
						<input type="text" class="form-control" id="login-captcha" name="j_captcha"  placeholder="验证码" />
					</div> 
					<div class="col-sm-6">
						<img id="captchaImg" src="jcaptcha.jpg" style="vertical-align: top;padding-top:6px"/>
						<a href="#" onclick="changeCaptcha()" style="display: inline-block;">看不清<br>换一张</a>  
					</div>
				</div> 
            </div  -->  
            
			<div class="form-group">
				<button type="submit" id="login-btn" class="btn btn-primary btn-block">登录 &nbsp; <i class="fa fa-play-circle"></i></button>
			</div>
		</form>


		<!-- a href="javascript:;" class="btn btn-default">忘记密码?</a -->

	</div> <!-- /#login -->


</div> <!-- /#login-container -->

<script src="./js/libs/jquery-1.9.1.min.js"></script>
<script src="./js/libs/jquery-ui-1.9.2.custom.min.js"></script>
<script src="./js/libs/bootstrap.min.js"></script>

<script src="./js/App.js"></script>

<script src="./js/Login.js"></script>

</body>
</html>