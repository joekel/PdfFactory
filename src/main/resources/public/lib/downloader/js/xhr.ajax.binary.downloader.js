
//Made this a JQuery plugin

(function($) {


	$.xhrAjaxFileDownload = function(opts){
		
		var constants = {
				method_get: 'GET',
				method_post: 'POST',
				reqType_param: 'reqParam',
				reqType_json: 'json',
				contentType_form: 'application/x-www-form-urlencoded',
				contentType_json: 'application/json'
		};
		
		var defaults = {
				method: constants.method_post,
				reqType: constants.reqType_json
		};
		
		var method = opts.method? opts.method: defaults.method;
		var reqType = opts.reqType? opts.reqType: defaults.reqType;
		var url = opts.url;
		var params = opts.data;
		
		
		var xhr = new XMLHttpRequest();
//		xhr.open('POST', url, true);
		xhr.open(method, url, true);
		xhr.responseType = 'arraybuffer';
		xhr.onload = function () {
		    if (this.status === 200) {
		        var filename = "";
		        var disposition = xhr.getResponseHeader('Content-Disposition');
		        if (disposition && disposition.indexOf('attachment') !== -1) {
		            var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
		            var matches = filenameRegex.exec(disposition);
		            if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
		        }
		        var type = xhr.getResponseHeader('Content-Type');
		
		        var blob = new Blob([this.response], { type: type });
		        if (typeof window.navigator.msSaveBlob !== 'undefined') {
		            // IE workaround for "HTML7007: One or more blob URLs were revoked by closing the blob for which they were created. These URLs will no longer resolve as the data backing the URL has been freed."
		            window.navigator.msSaveBlob(blob, filename);
		            setTimeout(function () { if(opts.done){opts.done();}}, 100); // cleanup
		        } else {
		            var URL = window.URL || window.webkitURL;
		            var downloadUrl = URL.createObjectURL(blob);
		
		            if (filename) {
		                // use HTML5 a[download] attribute to specify filename
		                var a = document.createElement("a");
		                // safari doesn't support this yet
		                if (typeof a.download === 'undefined') {
		                    window.location = downloadUrl;
		                } else {
		                    a.href = downloadUrl;
		                    a.download = filename;
		                    document.body.appendChild(a);
		                    a.click();
		                }
		            } else {
		                window.location = downloadUrl;
		            }
		
		            setTimeout(function () { URL.revokeObjectURL(downloadUrl); if(opts.done){opts.done();}}, 100); // cleanup
		        }
		    }
		};

//		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
//		xhr.send($.param(params));
//		xhr.setRequestHeader('Content-type', 'application/json');
//		xhr.send(JSON.stringify(params));
		
		if(reqType===constants.reqType_json){
			xhr.setRequestHeader('Content-type', constants.contentType_json);
			xhr.send(JSON.stringify(params));
		} else {
			xhr.setRequestHeader('Content-type', constants.contentType_form);
			xhr.send($.param(params));
		}
		
		
	};

}(jQuery));


