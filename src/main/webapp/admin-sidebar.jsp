<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="abr.bean.User"%>
<!-- Sidebar -->
<div class="sidebar">
	<div class="sidebar-collapse">
		<!-- Sidebar Header Logo-->
		<div class="sidebar-header">
			<img src="assets/img/logo.png" class="img-responsive" alt="" />
		</div>
		<!-- Sidebar Menu-->
		<div class="sidebar-menu">
			<nav id="menu" class="nav-main" role="navigation">
				<ul class="nav nav-sidebar">
					<div class="panel-body text-center">
						<div class="bk-avatar">
							<img src="assets/img/avatar.jpg" class="img-circle bk-img-60"
								alt="" />
						</div>
						<div class="bk-padding-top-10">
							<i class="fa fa-circle text-success"></i> <small>Administrator</small>
						</div>
					</div>
					<div class="divider2"></div>

					<li class="nav-parent"><a> <i class="fa fa-copy"
							aria-hidden="true"></i><span>WebPages Management</span></a>
						<ul class="nav nav-children">
							<li><a href="admin/webpageManage/list"><span
									class="text"> </span>WebPages List</a></li>
						</ul></li>
				</ul>
			</nav>
		</div>
		<!-- End Sidebar Menu-->
	</div>
	<!-- Sidebar Footer-->
	<div class="sidebar-footer">
		<center>Website Background Management System</center>
	</div>
	<!-- End Sidebar Footer-->
</div>
<!-- End Sidebar -->