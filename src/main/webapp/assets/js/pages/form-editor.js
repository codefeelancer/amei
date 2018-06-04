$(document).ready(function() {
	$('.summernote').summernote({
		height : 300, // set editor height, ex) 300
		focus : false, // set focus to editable area after initializing summernote
		// language
		lang : 'zh-CN', // language 'en-US', 'ko-KR', 
		// callbacks
		oninit : null, // initialize
		onfocus : function() {
			$(this).closest('.note-editor').addClass('active');
		},
		onblur : function() {
			$(this).closest('.note-editor').removeClass('active');
		},
		onenter : null, // enter key pressed
		onkeyup : null, // keyup
		onkeydown : null, // keydown
		onImageUpload : function(files, editor, $editable) {
			// upload image to server and create imgNode...
			data = new FormData();
			data.append("file", files[0]);
			$.ajax({
				data : data,
				type : "POST",
				url : "saveimage",
				cache : false,
				contentType : false,
				processData : false,
				success : function(data) {
					var status = data.status;
					if(status === 0){
						editor.insertImage($editable, data.url);
					}else if(data.status === 1){
						alert("Type Error！File type can only be gif/jpg/jpeg/png");
						
					}else if(data.status === 2){
						alert("Fail！Background Error");
						
					}
				},
				error : function() {
					alert('Error！Your image size could not over 4M！');
				}
			});
		}
	});
	
	var validator = new FormValidator('addForm', [{
	    name: 'title',
	    rules: 'required'
	},{
	    name: 'path',
	    rules: 'required'
	}, {
	    name: 'content',
	    rules: 'required'
	}], function(errors, event) {
	    if (errors.length > 0) {
	    	var errorString = '';

	        for (var i = 0, errorLength = errors.length; i < errorLength; i++) {
	            errorString += errors[i].message + '<br />';
	        }

	        $('#error-panel .panel .panel-body').html(errorString);
	        $('#error-panel').show();
	    }
	});
	
	validator.setMessage('required', 'Every field cannot be empty！');
	
	$('#li-upload-attachment').hide();
	$('#loading').hide();
	$('#btn-add-att').click(function(){
		$('#li-upload-attachment').show();
		$('#li-add-attachment').hide();
	});

});



function submitArticle(){
	if(confirm("Sure to save？")){
		return true;
	}else{
		return false;
	}
	return false;
	
}
