
//Escape HTML solution from Mustache JS
//http://stackoverflow.com/questions/24816/escaping-html-strings-with-jquery
//Made this a JQuery plugin
//Usage: $.escapeHtml(html string);
//Example: $.escapeHtml($('#htmlInput').val());
(function($) {
	$.escapeHtml = function(string) {
		var entityMap = {
				'&' : '&amp;',
				'<' : '&lt;',
				'>' : '&gt;',
				'"' : '&quot;',
				"'" : '&#39;',
				'/' : '&#x2F;',
				'`' : '&#x60;',
				'=' : '&#x3D;'
			};
		
		if(string){
			return String(string).replace(/[&<>"'`=\/]/g, function fromEntityMap(s) {
				return entityMap[s];
			});
		}
	};

}(jQuery));
////////