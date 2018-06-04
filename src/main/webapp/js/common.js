$(document).ready(function() {
	var href = window.location.href;
	var navs = $(".nav > li > a");
	var count = 0;
	for (var i = 0; i < navs.length; i++) {
		var link = '/'+$(navs[i]).attr("data");
		if (link != null && link != "" && href.indexOf(link) != -1) {
			$(navs[i]).css({
				"color" : "#99ccff"
			});
			break;
		}
		count++;
	}
	if (count == 8) {
		$(navs[0]).css({
			"color" : "#99ccff"
		});
	}

});