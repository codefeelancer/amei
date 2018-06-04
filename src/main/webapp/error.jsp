<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Error happened</title>
<link rel="shortcut icon" href="assets/ico/csri.ico" />
<!-- Vendor CSS-->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/vendor/skycons/css/skycons.css" rel="stylesheet" />
<link href="assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />

<!-- Plugins CSS-->

<!-- Theme CSS -->
<link href="assets/css/jquery.mmenu.css" rel="stylesheet" />

<!-- Page CSS -->
<link href="assets/css/style.css" rel="stylesheet" />
<link href="assets/css/add-ons.min.css" rel="stylesheet" />
</head>
<body>
	<div class="row box-error">
		<div class="col-lg-12 col-md-12 col-xs-12">
			<div class="text-center">
				<%
				String message = (String)request.getParameter("message");
				if(message != null){
					if(message.equals("add-fail")){
				%>

				<h2>Error：fail to add webpage</h2>
				<%}}else{ %>
				<h1>404</h1>
				<%} %>
				<h2>Oops! Bad request ...</h2>
				<p>Sorry, Error happened~~</p>
				<a href="javascript: history.go(-1)">
					<button type="button" class="btn btn-primary">
						<i class="fa fa-chevron-left"></i>return
					</button>
				</a>
			</div>
		</div>
	</div>
	<!-- start: JavaScript-->

	<!-- Vendor JS-->
	<script src="assets/vendor/js/jquery.min.js"></script>
	<script src="assets/vendor/js/jquery-2.2.4.min.js"></script>
	<script src="assets/vendor/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/skycons/js/skycons.js"></script>

	<!-- Plugins JS-->

	<!-- Theme JS -->
	<script src="assets/js/jquery.mmenu.min.js"></script>
	<script src="assets/js/core.min.js"></script>

	<!-- Pages JS -->
	<script src="assets/js/pages/page-404.js"></script>

	<!-- end: JavaScript-->
</body>
</html>