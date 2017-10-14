

//http://stackoverflow.com/questions/16245767/creating-a-blob-from-a-base64-string-in-javascript
//Original Author: Jeremy Banks
//Made this a JQuery plugin
//Converts a base64 String to byteArray to be consumed in a Blob.
//E.g., 
//var binaryArray = $.base64ToBinary({data: "Base64 Encoded String"});
//var blob = new Blob(byteArrays, {type: "application/pdf"});

(function($) {

	$.base64ToBinary = function(opts) {
		if (opts && opts.data) {
			var sliceSize = opts.sliceSize || 512;
			var b64Data = opts.data;

			var byteCharacters = atob(b64Data);
			var byteArrays = [];

			for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
				var slice = byteCharacters.slice(offset, offset + sliceSize);

				var byteNumbers = new Array(slice.length);
				for (var i = 0; i < slice.length; i++) {
					byteNumbers[i] = slice.charCodeAt(i);
				}

				var byteArray = new Uint8Array(byteNumbers);

				byteArrays.push(byteArray);
			}

			return byteArrays;
			
		} else {
			console.error("data must be provided, e.g., {data:\"Base64 encoded string.\"}")
		}

	};

}(jQuery));
