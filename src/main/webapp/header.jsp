<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML>
<html lang="en">
<head>

    <base href="<%=basePath%>" />

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <link rel="icon" href="images/favicon.ico" type="image/x-icon" />

    <!-- Bootstrap Plugins -->
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
    <!-- Custom Theme files -->
    <link href="css/style.css" rel='stylesheet' type='text/css' />
    <!-- Page Style File -->
    <link href="css/index.css" rel='stylesheet' type='text/css' />
    <!-- Font Style File -->
    <link href="css/font.css" rel='stylesheet' type='text/css' />

</head>
<body>
<!-- header -->
<div class="top-line clearfix">
    <a class="lang" href="cn/">中文</a>
    <span class="lang">English</span>
    <form action="search">
        <div class="search-box">
            <input type="text" name="keyword" class="form-control input-sm search-box-input" placeholder="Search for..."><button class="btn btn-primary btn-sm search-box-button" type="button">Search</button>
        </div>
    </form>
</div>
<div class="header-bottom">
    <div class="logo">
        <a href=""><img width="100%" height="100%" src="images/logo2.jpg"></img></a>
    </div>
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
        </div>
        <!-- .navbar-header-->
        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a data-suffix="" href="">Home</a></li>
                <li><a data-suffix="technology" href="technology">Technology</a></li>
                <li>
                    <a data-suffix="products" href="javascript:void(0)">Products</a>
                    <ul>
                        <li><a data-suffix="products-cri" href="products-cri">Imedos Retina Camera</a></li>
                        <li><a data-suffix="products-supersesi" href="products-supersesi">SUPER SESI</a></li>
                    </ul>
                </li>
                <li><a data-suffix="download" href="download">Download</a></li>
                <li><a data-suffix="news" href="news">News</a></li>
                <li><a data-suffix="links" href="links">Links</a></li>
                <li><a data-suffix="about" href="about">About</a></li>
                <li><a data-suffix="contact" href="contact">Contact</a></li>
            </ul>
        </div>
        <!--/.navbar-collapse-->
    </nav>
     <div class="clearfix"></div>

</div>
<!-- /.header-bottom -->
<!-- /header -->

