$(function() {
	/* Onglets */
	$('#tabs li').click(function (e) {
		$('#tabs li.active').toggleClass('active');
		$(this).toggleClass('active');
	});
	$( "#tabs" ).tabs();
	
	/* Datepicker */
    $( ".datepicker" ).datepicker({
        changeMonth: true,
        changeYear: true,
      });
    $( ".datepicker" ).datepicker("option", "dateFormat", "dd/mm/yy");
    $( "#datepicker" ).datepicker( "option", "showAnim", "slideDown");
});