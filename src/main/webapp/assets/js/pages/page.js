/**
 * for login page
 */
function ajaxSubmit(){
	$.ajax({
		data : $('#loginForm').serialize(),
		type : "POST",
		url : "user/login",
		cache : false,
		dataType : "text",
		success : function(msg) {
			if(msg === 'success'){
				location.href="admin/index"; 
			}else if(msg === 'fail'){
				alert('用户名或密码错误！');
				window.location.reload(); 
			}else {
				//验证码错误
				alert("验证码错误！");
				location.reload(true); 
			}
		}
	});
}