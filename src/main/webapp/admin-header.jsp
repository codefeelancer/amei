<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="abr.bean.User"%>
<div class="navbar" role="navigation">
	<div class="container-fluid container-nav">
		<!-- Navbar Right -->
		<div class="navbar-right">
			<!-- Notifications -->
			<!-- End Notifications -->
			<!-- Userbox -->
			<div class="userbox">
				<div class="profile-info">
					<%User user= (User)request.getSession().getAttribute("user"); %>
					<span class="name"><%=user.getUsername()%></span>
				</div>
				<i class="fa custom-caret"></i> <a href="user/logout"><i
					class="fa fa-power-off"></i> Logout</a>
			</div>
			<!-- End Userbox -->
		</div>
		<!-- End Navbar Right -->
	</div>
</div>