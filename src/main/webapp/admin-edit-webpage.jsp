<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%@ page import="abr.bean.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- Basic -->
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Modify Webpage</title>

<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

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
<link href="assets/plugins/summernote/css/summernote.css"
	rel="stylesheet" />
<link href="assets/plugins/summernote/css/summernote-bs3.css"
	rel="stylesheet" />
<link
	href="assets/plugins/datetimepicker/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<!-- Theme CSS -->
<link href="assets/css/jquery.mmenu.css" rel="stylesheet" />

<!-- Page CSS -->
<link href="assets/css/style.css" rel="stylesheet" />
<link href="assets/css/uploadFile.css" rel="stylesheet" />
<!-- end: CSS file-->

</head>

<body>

	<!-- Start: Header -->
	<jsp:include page="admin-header.jsp"></jsp:include>
	<!-- End: Header -->

	<!-- Start: Content -->
	<div class="container-fluid content">
		<div class="row">

			<!-- Sidebar -->
			<jsp:include page="admin-sidebar.jsp"></jsp:include>
			<!-- End Sidebar -->

			<!-- Main Page -->
			<div class="main ">
				<!-- Page Header -->
				<div class="page-header">
					<div class="pull-left">
						<ol class="breadcrumb visible-sm visible-md visible-lg">
							<li><a href="#"><i class="icon fa fa-home"></i>Home</a></li>
							<li><a href="#">Webpage Management</a></li>
							<li><a href="#">Webpage List</a></li>
							<li class="active"><i class="fa fa-pencil-square-o"></i>Modify Webpage</li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>Modify Webpage</h2>
					</div>
				</div>
				<!-- End Page Header -->
				<div class="row">
					<div class="col-xs-12" id="error-panel">
						<div class="panel">
							<div class="panel-heading bk-bg-danger">
								<h6>
									<i class="fa fa-signal red"></i> Information of Error
								</h6>
								<div class="panel-actions">
									<a href="ui-panels.html#" class="btn-minimize"><i
										class="fa fa-chevron-up"></i></a>
								</div>
							</div>
							<div class="panel-body"></div>
						</div>
					</div>
					<div class="col-xs-12">
						<div class="panel">
							<div class="panel-heading bk-bg-primary">
								<h6>
									<i class="fa fa-edit red"></i>Modify Webpage
								</h6>
							</div>
							<div
								class="panel-body bk-bg-white bk-padding-top-30 bk-padding-bottom-20">
								<form class="form-horizontal form-bordered" name="addForm"
									method="post" action="admin/webpageManage/modify">
									<div class="form-group">
										<%
											Webpage webpage = (Webpage) request.getAttribute("webpage");
										%>
										<div class="col-md-12">
											<h4>Webpage Title</h4>
											<input type="hidden" class="form-control" name="path"
												value="<%=webpage.getPath()%>">
											<input type="text" class="form-control" name="title"
												value="<%=webpage.getTitle()%>">
											<h4>Webpage Content</h4>
											<textarea class="summernote" name="content">
												<%
													if (webpage.getHtml() != null) {
												%><%=webpage.getHtml()%>
												<%
													}
												%>
											</textarea>
											<div style="float: right;">
												<button type="submit" class="btn btn-primary"
													onclick="return submitArticle()">Save </button>
											</div>
										</div>
									</div>
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
			<!-- End Main Page -->
		</div>
	</div>
	<!--/container-->

	<div class="clearfix"></div>


	<!-- start: JavaScript-->

	<!-- Vendor JS-->
	<script src="assets/vendor/js/jquery.min.js"></script>
	<script src="assets/vendor/js/jquery-2.2.4.min.js"></script>
	<script src="assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/skycons/js/skycons.js"></script>

	<!-- Plugins JS-->
	<script src="assets/plugins/summernote/js/summernote.js"></script>
	<script src="assets/plugins/summernote/js/summernote-zh-CN.js"
		charset="UTF-8"></script>
	<script src="assets/plugins/validate.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/datetimepicker/bootstrap-datetimepicker.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="assets/plugins/datetimepicker/bootstrap-datetimepicker.zh-CN.js"
		charset="UTF-8"></script>

	<!-- Theme JS -->
	<script src="assets/js/jquery.mmenu.min.js"></script>
	<script src="assets/js/core.min.js"></script>

	<!-- Pages JS -->
	<script src="assets/js/pages/form-editor.js"></script>
	<!-- end: JavaScript-->

</body>

</html>