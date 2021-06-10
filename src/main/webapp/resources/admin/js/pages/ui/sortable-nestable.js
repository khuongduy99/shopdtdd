$(function () {
	    $('.dd').nestable({maxDepth:1});
	    $('.dd').on('change', function () {
	        var $this = $(this);
	        var serializedData = window.JSON.stringify($($this).nestable('serialize'));

	        $this.parents('div.body').find('textarea').val(serializedData);
	    });
	});