<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<jsp:include page="cn_header.jsp" />
<head>
<title>申轲贸易有限公司 | 联系我们</title>
<!-- Style of Slide -->
<link href="css/lightslider.css" rel="stylesheet" type="text/css" />
<style>
li img {
	width: 100%;
}
</style>
</head>
<!-- main content -->
<div class="main">
	<div>
		<h1>联系我们</h1>
		<h6>* 必填</h6>
	</div>
	<form class="form-horizontal contact-form" method="post" >
		<fieldset>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">邮箱*</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="email-field"
						name="email" placeholder="Email">
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">名字</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name-field" name="name"
						placeholder="Name">
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">姓氏</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="firstname-field"
						name="firstname" placeholder="First Name">
				</div>
			</div>
			<div class="form-group">
				<label for="titel" class="col-sm-2 control-label">电话</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="titel-field"
						name="titel" placeholder="Tel">
				</div>
			</div>
			<div class="form-group">
				<label for="company" class="col-sm-2 control-label">公司</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="company-field"
						name="company" placeholder="Company">
				</div>
			</div>
			<div class="form-group">
				<label for="address" class="col-sm-2 control-label">地址</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="address-field"
						name="address" placeholder="Address">
				</div>
			</div>
			<div class="form-group">
				<label for="city" class="col-sm-2 control-label">城市</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="city-field" name="city"
						placeholder="City">
				</div>
			</div>
			<div class="form-group">
				<label for="zipcode" class="col-sm-2 control-label">邮编</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="zipcode-field"
						name="zipcode" placeholder="ZIP Code">
				</div>
			</div>
			<div class="form-group">
				<label for="content" class="col-sm-2 control-label">内容*</label>
				<div class="col-sm-10">
					<textarea class="form-control" id="content-field" rows="6"
						name="content" placeholder="Please white......"></textarea>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">发送</button>
				</div>
			</div>

		</fieldset>
	</form>
	<div class="clearfix"></div>
</div>
<br>
<br>
<!-- end main content -->
<jsp:include page="cn_footer.jsp" />
<script>
	$(document).ready(function(){
		$(".contact-form").submit(function(e){
			e.preventDefault();
			
			var email = $('#email-field').val();
			var content = $('#content-field').val()
			var name = $('#name-field').val();
			var firstname = $('#firstname-field').val()
			var titel = $('#titel-field').val();
			var company = $('#company-field').val()
			var city = $('#city-field').val();
			var zipcode = $('#zipcode-field').val()
			var address = $('#address-field').val();
			if((email === null||email==='')||(content === null||content==='')){
				alert("Your Email and Content field cannot be empty!");
			}else{
				$.ajax({
					url : "sendemail",
					dataType : "json",
					method:"post",
					data : {
						email:email,
						name:name,
						firstname:firstname,
						titel:titel,
						address:address,
						company:company,
						content:content,
						zipcode:zipcode,
						city:city
					},
					success : function(data) {
						data = data.code;
						if(data === -1){
							alert("ERROR:无法发送您的信息!请用自己的邮箱尝试.")
						}else if(data === 1){
							alert("您的信息已被发送!")
						}else if(data === 0){
							alert("您发送信息的次数太频繁！")
						}
					}
				});
			}
		});
	});
</script>
<%
    String otherpageurl = (String) request.getAttribute("otherpageurl");
%>
<script>
    $(document).ready(function(){
        $('.top-line a.lang').attr('href','<%= otherpageurl%>')
    });
</script>
