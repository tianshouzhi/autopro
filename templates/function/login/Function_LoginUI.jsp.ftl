<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<title>登录</title>
<meta charset="utf-8">
<title>博客后台管理系统</title>
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link type="text/css" rel="stylesheet"  href="<%=path%>/js/assets/plugins/font-awesome/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet"  href="<%=path%>/js/assets/plugins/bootstrap/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet"  href="<%=path%>/js/assets/plugins/uniform/css/uniform.default.css">
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME STYLES -->
<link type="text/css" rel="stylesheet"  href="<%=path%>/js/assets/css/style-metronic.css">
<link type="text/css" rel="stylesheet"  href="<%=path%>/js/assets/css/style.css">
<link type="text/css" rel="stylesheet"  href="<%=path%>/js/assets/css/style-responsive.css">
<link type="text/css" rel="stylesheet"  href="<%=path%>/js/assets/css/plugins.css">
<link id="style_color" type="text/css" rel="stylesheet"  href="<%=path%>/js/assets/css/themes/default.css">
<link href="<%=path%>/js/assets/css/pages/login-soft.css" rel="stylesheet" type="text/css"/>

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo">
	<a href="index.html" >
		<img src="<%=path%>/js/assets/img/logo.png"  alt=""/>
	</a>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
	<!-- BEGIN LOGIN FORM -->
	<form class="login-form" action="<%=path%>/loginController/login.action" method="post">
		<h3 class="form-title">${projectName}后台</h3>
		
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">登陆账号</label>
			<div class="input-icon">
				<i class="fa fa-user"></i>
				<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="登陆账号" name="${username}"/>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">密码</label>
			<div class="input-icon">
				<i class="fa fa-lock"></i>
				<input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="${password}"/>
			</div>
		</div>
		
		<div class="form-group">
			<div style="padding-left:0;">
				<input class="form-control placeholder-no-fix col-sm-4" type="text" autocomplete="off" placeholder="验证码" name="securityCode" style="width: 100px;display: inline"/>
				<img src="<%=path%>/loginController/securityCode.action"  id="verify" style="cursor:pointer;margin-left:10%" alt="验证码加载失败" title="看不清，换一张" />
			</div>
			
		</div>
		<div class="form-actions" >
			<button type="submit" class="btn blue pull-left" style="margin-top: 10px;">
			登录 <i class="m-icon-swapright m-icon-white"></i>
			</button>
		</div>
		<div class="alert">
			<span>
			${r"${"}message}
			</span>
		</div>
	</form>
	<!-- END LOGIN FORM -->

</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->

<!-- END COPYRIGHT -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
	<script src="<%=path%>/js/assets/plugins/respond.min.js"></script>
	<script src="<%=path%>/js/assets/plugins/excanvas.min.js"></script> 
	<![endif]-->
<script src="<%=path%>/js/assets/plugins/jquery-1.10.2.min.js"  type="text/javascript"></script>
<script src="<%=path%>/js/assets/plugins/jquery-migrate-1.2.1.min.js"  type="text/javascript"></script>
<script src="<%=path%>/js/assets/plugins/bootstrap/js/bootstrap.min.js"  type="text/javascript"></script>

<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="<%=path%>/js/assets/plugins/jquery-validation/dist/jquery.validate.min.js"  type="text/javascript"></script>
<script src="<%=path%>/js/assets/plugins/backstretch/jquery.backstretch.min.js"  type="text/javascript"></script>
<script type="text/javascript" src="<%=path%>/js/assets/plugins/select2/select2.min.js" ></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=path%>/js/assets/scripts/core/app.js"  type="text/javascript"></script>
<script src="<%=path%>/js/assets/scripts/custom/login-soft.js"  type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
		jQuery(document).ready(function() {     
		  Login.init();
		  	//点击图片更换验证码
			$("#verify").click(
					function() {
						$(this).attr("src","<%=path%>/loginController/securityCode.action?timestamp="+ new Date().getTime());
					});
		});
	</script>
<!-- END JAVASCRIPTS -->
<!-- END BODY -->
</html>