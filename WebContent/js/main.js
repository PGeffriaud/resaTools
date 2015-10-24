$(function() {
	$('#tabs li').click(function (e) {
		$('#tabs li.active').toggleClass('active');
		$(this).toggleClass('active');
	});
	$( "#tabs" ).tabs();
});