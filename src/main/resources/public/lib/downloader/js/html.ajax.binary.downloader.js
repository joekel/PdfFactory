
//Usage: $.htmlAjaxFileDownload(url, requestObject); or $.htmlAjaxFileDownload(url, requestObject, 'GET');

//default enctype="application/x-www-form-urlencoded"
//enctype="application/json" is browser dependent and may not be available on all browsers.

(function($) {
	$.htmlAjaxFileDownload = function(url, reqObj, method) {
//		var form = $('<form method="POST" target="_blank" action="' + url + '">');
		var form = $('<form method="'+(method?method:'POST')+'" target="_blank" action="' + url + '">');
		
		$.each(reqObj, function(k, v) {
	        form.append($('<input type="hidden" name="' + k +'" value="' + v + '">'));
	    });
		
		$('body').append(form);
	    form.submit();
	};

}(jQuery));

//(function($) {
//	$.htmlAjaxJsonFileDownload = function(url, reqObj, method) {
//		var form = $('<form method="'+(method?method:'POST')+'" target="_blank" action="' + url + '" accept-charset="UTF-8" enctype="application/json">');
//		
//		$.each(reqObj, function(k, v) {
//	        form.append($('<input type="hidden" name="' + k +'" value="' + v + '">'));
//	    });
//		
//		$('body').append(form);
//	    form.submit();
//	};
//
//}(jQuery));

