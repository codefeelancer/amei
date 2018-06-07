<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="zh">
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
<!-- .top-line -->
<div class="top-line clearfix">
    <a class="lang" href="/">English</a>
    <span class="lang">中文</span>
    <form action="search">
        <div class="search-box">
            <input type="text" name="keyword" class="form-control input-sm search-box-input" placeholder="搜索..."><button class="btn btn-primary btn-sm search-box-button" type="button">搜索</button>
        </div>
    </form>
</div>
<!-- /.top-line -->
<div class="header-bottom">
    <div class="logo">
        <a href="cn/"><img width="100%" height="100%" src="images/logo2.jpg"></img></a>
    </div>
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only"></span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
        </div>
        <!-- .navbar-header-->
        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a data-suffix="" href="cn/">首页</a></li>
                <li><a data-suffix="technology" href="cn/technology">技术</a></li>
                <li>
                		<a data-suffix="products" href="javascript:void(0)">产品</a>
                    <ul>
                        <li><a data-suffix="products-cri" href="cn/products-cri">眼底照相机</a></li>
                        <li><a data-suffix="products-supersesi" href="cn/products-supersesi">SUPER SESI</a></li>
                    </ul>
                </li>
                <li><a data-suffix="download" href="cn/download">下载</a></li>
                <li><a data-suffix="news" href="cn/news">新闻</a></li>
                <li><a data-suffix="links" href="cn/links">链接</a></li>
                <li><a data-suffix="about" href="cn/about">关于</a></li>
                <li><a data-suffix="contact" href="cn/contact">联系我们</a></li>
            </ul>
        </div>
        <!--/.navbar-collapse-->
    </nav>
    <div class="clearfix"></div>
</div>
<!-- /.header-bottom -->
<!-- /header -->