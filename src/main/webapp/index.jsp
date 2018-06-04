<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<head>
    <title>Schenker Trading Co.,ltd</title>
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
        <div class="">
            <ul id="imageGallery">
                <li>
                    <img src="images/ba.jpg"/>
                </li>
                <li>
                    <img src="images/ma2.jpeg"/>
                </li>

            </ul>
        </div>
    </div>
    <div class="col-md-4">
        <h4>Schenker Trading Co.,ltd</h4>
        <p>Schenker Trading (Beijing) is committed to offer advanced diagnostic tools for the early detection of various
            diseases. Our new technologies will allow scientists and doctors to detect specific diseases at an early
            stage – when treatment is easier and offers a much better prognosis.</p>
    </div>
    <div class="clearfix"></div>
</div>
<!-- //Slide and Intro -->
<!-- main content -->
<div class="main">
    <div class="row">
        <div class="content-mid-top">
            <h3>Our Products</h3>
            <p>Micro blood-vessel analysis and breath analysis are the
                methods we invest for the development of early disease detection.</p>
        </div>
        <br>
        <div class="col-md-6">
            <div class="thumbnail">
                <img src="images/ma.jpg" alt="Micro Blood-Vessel Analysis">
                <div class="caption method">
                    <h4>Micro Blood-Vessel Analysis</h4>
                    <p>It has been developed in Germany and Switzerland and widely
                        used for general diagnostics and preventive medicine. In addition
                        to detecting eye problem, precise prediction about cardiovascular
                        problems can be made 5-15 years ahead of actual breakout of the
                        illness, giving ample time to implement corrective measures...<a href="technology#rmbba"
                                                                                         class="btn-sm btn-main"
                                                                                         role="button">more</a></p>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="thumbnail">
                <img src="images/ma3.jpg" alt="Breath Analysis">
                <div class="caption method">
                    <h4>Breath Analysis</h4>
                    <p>Breath analysis has been mainly developed at ETH Zurich
                        (Eidgenössische Technische Hochschule Zürich) and has now been
                        declared a flagship project between ETH, University of Zurich and
                        other institutions. Our company is closely collaborating with the
                        head scientist to further develop the general technology and special
                        applications...<a href="technology#cba" class="btn-sm btn-main" role="button">more</a></p>
                </div>
            </div>
        </div>

        <div class="clearfix"></div>
    </div>
    <div class="clearfix"></div>
</div>
<br>
<br>
<!-- end main content -->
<jsp:include page="footer.jsp"/>
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