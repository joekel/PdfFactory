
(function($) {

	$.fn.radioButton = function(callback) {
		//http://stackoverflow.com/questions/306583/this-selector-and-children
		
		//Setting up default value for hidden input element
		var defaultSel = jQuery(this).children('a:first-child').data('title');
		var defaultTog = jQuery(this).children('a:first-child').data('toggle');
		jQuery('#' + defaultTog).prop('value', defaultSel);
		
		//Setting up click event on the radio button
		jQuery(this).children('a').on(
				'click',
				function() {
					//console.log("clicked: " + $(this).prop('id'));
					var sel = jQuery(this).data('title');
					var tog = jQuery(this).data('toggle');
					jQuery('#' + tog).prop('value', sel);

					//console.log("sel: " + sel + ", tog: " + tog);
					
					jQuery('a[data-toggle="' + tog + '"]').not(
							'[data-title="' + sel + '"]').removeClass('active')
							.addClass('notActive');
					jQuery('a[data-toggle="' + tog + '"][data-title="' + sel + '"]')
							.removeClass('notActive').addClass('active');
					
					if(callback){
						callback();
					}
				});
		return this;
	};

}(jQuery));