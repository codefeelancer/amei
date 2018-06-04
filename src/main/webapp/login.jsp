<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>

<head>

<!-- Basic -->
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>管理员登陆</title>

<link rel="shortcut icon" href="assets/ico/csri.ico" />
<!-- Import google fonts -->
<link href="assets/css/font.css" rel="stylesheet" />

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.ico"
	type="image/x-icon" />

<!-- start: CSS file-->
<!-- Vendor CSS-->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />

<!-- Plugins CSS-->
<link href="assets/plugins/bootkit/css/bootkit.css" rel="stylesheet" />

<!-- Theme CSS -->
<link href="assets/css/jquery.mmenu.css" rel="stylesheet" />

<!-- Page CSS -->
<link href="assets/css/style.css" rel="stylesheet" />

<!-- end: CSS file-->
</head>

<body>
	<!-- Start: Content -->
	<div class="container-fluid content">
		<div class="row">
			<!-- Main Page -->
			<div class="body-login">
				<div class="center-login">
					<a class="logo pull-left hidden-xs"> <img
						src="assets/img/logo.png" height="45" alt="NADHIF Admin" />
					</a>

					<div class="panel panel-login">
						<div class="panel-title-login text-right">
							<h2 class="title">
								<i class="fa fa-user"></i>管理员登陆
							</h2>
						</div>
						<div class="panel-body">
							<form action="user/login" method="post" id="loginForm">
								<div class="form-group">
									<label>用户名</label>
									<div class="input-group input-group-icon">
										<input name="username" type="text"
											class="form-control bk-noradius" /> <span
											class="input-group-addon"> <span class="icon">
												<i class="fa fa-user"></i>
										</span>
										</span>
									</div>
								</div>

								<div class="form-group">
									<label>密码</label>
									<div class="input-group input-group-icon">
										<input name="password" type="password"
											class="form-control bk-noradius" /> <span
											class="input-group-addon"> <span class="icon">
												<i class="fa fa-lock"></i>
										</span>
										</span>
									</div>
								</div>
								<div class="form-group">
									<label>验证码</label>
									<div class="input-group input-group-icon">
										<input name="checkCode" type="text" style="width:75%"
											class="form-control bk-noradius" />
										<img style="width:25%" src="createCheckCode" onclick="this.src='createCheckCode?'+ Math.random()" title="点击图片刷新验证码"/><br>
										
									</div>
　　　　　
        
								</div>
								<br />
								<div class="row">
									<div class="col-sm-8">
										<div
											class="checkbox-custom checkbox-default bk-margin-bottom-10">
										</div>
									</div>
									<div class="col-sm-4 text-right">
										<button type="button" onclick="ajaxSubmit()" class="btn btn-primary hidden-xs">登陆</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- End Main Page -->
		</div>
	</div>
	<!--/container-->


	<!-- start: JavaScript-->

	<!-- Vendor JS-->
	<script src="assets/vendor/skycons/js/skycons.js"></script>
	<script src="assets/vendor/js/jquery-2.2.4.min.js"></script>
	<script src="assets/js/pages/page.js"></script>
	<!-- end: JavaScript-->

</body>

</html>