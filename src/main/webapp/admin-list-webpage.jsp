<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>Webpage List</title>

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
<link href="assets/plugins/summernote/css/summernote.css"
	rel="stylesheet" />
<link href="assets/plugins/summernote/css/summernote-bs3.css"
	rel="stylesheet" />

<!-- Theme CSS -->
<link href="assets/css/jquery.mmenu.css" rel="stylesheet" />

<!-- Page CSS -->
<link href="assets/css/style.css" rel="stylesheet" />

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
							<li><a href="#">Webpge Management</a></li>
							<li class="active">Webpge List</li>
						</ol>
					</div>
					<div class="pull-right">
						<h2>Webpge List</h2>
					</div>
				</div>
				<!-- End Page Header -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel">
							<div class="panel-heading bk-bg-primary">
								<h6>
									<i class="fa fa-table red"></i><span class="break"></span>List
								</h6>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<div class="row">
										<div class="col-xs-12">
											<table
												class="table table-striped table-bordered bootstrap-datatable datatable">
												<thead>
													<tr>
														<th class="col-xs-5">Path</th>
														<th class="col-xs-3">Title</th>
														<th class="col-xs-2">Operation</th>
													</tr>
												</thead>
												<tbody>
													<%
														List<Webpage> list = (List<Webpage>) request.getAttribute("list");
														if (list != null) {
															for (Webpage item : list) {
																String completePath = basePath + item.getPath();
													%>
													<tr >
														<td><a href="<%=completePath %>" target="_blank"><%=completePath %></a></td>
														 <td><%=item.getTitle()%></td>
														<td><a class="btn btn-success" title="view" target="_blank"
															href="admin/webpageManage/view?path=<%=item.getPath()%>"> <i
																class="fa fa-search-plus"></i>
														</a> <a class="btn btn-info" title="edit"
															href="admin/webpageManage/edit?path=<%=item.getPath()%>"> <i
																class="fa fa-edit "></i>
														</a></td>
													</tr>

													<%
														}
														}
													%>
													<%
														if (list == null || list.size() == 0) {
													%><tr>
														<td colspan="7"><center>
																<h5>无</h5>
															</center></td>
													</tr>
													<%
														}
													%>
												</tbody>
											</table>
											<div></div>
										</div>
									</div>
								</div>
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

	<!-- Theme JS -->
	<script src="assets/js/jquery.mmenu.min.js"></script>
	<script src="assets/js/core.min.js"></script>

	<script type="text/javascript">
		$('.deleteArticle').click(function(e) {
			e.preventDefault(e);
			if (confirm("确定要删除吗？")) {
				var action = $(this).attr("href");
				var data = new FormData();
				var id = action.substring(action.lastIndexOf('/') + 1);
				var tr_id = '#tr-' + id;
				$.ajax({
					data : null,
					type : "POST",
					url : action,
					cache : false,
					dataType : "text",
					success : function(msg) {
						if (msg === 'success')
							$(tr_id).fadeOut('slow');
						else
							alert('删除失败');
					}
				});
			} else {
			}

		});
	</script>
	<!-- end: JavaScript-->

</body>

</html>