<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
    String html = (String) request.getAttribute("html");
    String title = (String) request.getAttribute("title");
    String otherpageurl = (String) request.getAttribute("otherpageurl");
    String lang = (String) request.getAttribute("lang");
    if(!"en".equals(lang)){
%>
<jsp:include page="cn_header.jsp" />
<head>
<title>申轲贸易有限公司 | <%= title%></title>
</head>
<div class="return"><a  href="admin/webpageManage/list">&larr;返回页面列表</a></div>
<!-- main content -->
<div class="main">
	<%= html%>
</div>
<!-- ./main content -->
<jsp:include page="cn_footer.jsp" />
<% }else{ %>
<jsp:include page="header.jsp" />
<head>
<title>Schenker Trading Co.,ltd | <%= title%></title>
</head>
<div class="return"><a  href="admin/webpageManage/list">&larr;Rturn to webpage list</a></div>
<!-- main content -->
<div class="main">
	<%= html%>
</div>
<!-- ./main content -->
<jsp:include page="footer.jsp" />
<% } %>

<script>
    $(document).ready(function(){
        $('.top-line a.lang').attr('href','<%= otherpageurl%>')
    });
</script>
