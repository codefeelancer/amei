<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="cn_header.jsp" />
<head>
    <title>申轲贸易有限公司</title>
    <!-- Style of Slide -->
    <link href="css/lightslider.css" rel="stylesheet" type="text/css" />
    <style>
        li img {
            width: 100%;
        }
    </style>
</head>
<!-- Slide and Intro -->
<div class="about">
    <div class="col-md-8">
        <div class="slide">
            <ul id="imageGallery">
                <li>
                    <img src="images/ba.jpg" />
                </li>
                <li>
                    <img src="images/ma2.jpeg" />
                </li>

            </ul>
        </div>
    </div>
    <div class="col-md-4">
        <h4>申轲北京</h4>
        <p>致力于提供先进的疾病早期侦察诊断工具。通过我们的新技术，科研及医护人员能够使用更加便捷的检测手段，在疾病早期及时有效的预测疾病。
        </p>
    </div>
    <div class="clearfix"></div>
</div>
<!-- //Slide and Intro -->
<!-- main content -->
<div class="main">
    <!-- <div class="row">
        <div class="content-mid-top">
            <h3>目标</h3>
            <p>现阶段，我们主要想在中国做以下三件事</p>
        </div>
        <div class="news">
            <div class="col-md-4 about1-bottom1">
                <span>01</span>
                <p>找到一到两个有兴趣的机构进行概念验证运行试验，大学附属医院为佳。</p>
            </div>
            <div class="col-md-4 about1-bottom1">
                <span>02</span>
                <p>在每一个选定的中国科研机构安装试验设备，并培训中国操作人员和科研人员。</p>
            </div>
            <div class="col-md-4 about1-bottom1">
                <span>03</span>
                <p>为对此感兴趣的中国科学家和医疗工作者组织一个介绍研讨会；瑞士和德国的研发人员和科学家将会出席。</p>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>-->
    <div class="row">
        <div class="content-mid-top">
            <h3>产品</h3>
            <p>我们研究疾病早期诊断的方法主要是微血管分析和呼吸分析</p>
        </div>
        <br>
        <div class="col-md-6">
            <div class="thumbnail">
                <img src="images/ma.jpg" alt="Micro Blood-Vessel Analysis">
                <div class="caption method">
                    <h4>微血管分析</h4>
                    <p>德国和瑞士已经对微血管分析技术进行了深度研发，并且，这项技术现在越来越广泛的应用在普通诊断和预防医疗上。除了探测眼部疾病外，在心血管问题上，早在疾病突发的5年到15年前，就可以做出精确的预测，从而给实施治疗方案留出了充裕的时间。<a href="technology#rmbba" class="btn-sm btn-main" role="button">more</a> </p>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="thumbnail">
                <img src="images/ma3.jpg" alt="Breath Analysisl">
                <div class="caption method">
                    <h4>呼吸分析</h4>
                    <p>呼吸分析的研发主要是在苏黎世联邦理工学院进行，并且现在已经发布了一个由苏黎世联邦理工学院，苏黎世大学和其他研究机构共同进行的旗舰项目。我们公司与首席科学家密切合作，深入研发其基本技术及其特定应用。<a href="technology#cba" class="btn-sm btn-main" role="button">more</a></p>
                </div>
            </div>
        </div>

        <div class="clearfix"></div>
    </div>
    <br>
    <br>
</div>
<!-- end main content -->
<jsp:include page="cn_footer.jsp" />
<script type="text/javascript" src="js/lightslider.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#imageGallery').lightSlider({
            adaptiveHeight: true,
            item: 1,
            slideMargin: 0,
            loop: true
        });
    });
</script>