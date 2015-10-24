<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="body" class="panel panel-default">
<div id="tabs" class="panel-body">
  <ul class="nav nav-tabs">
    <li class="active"><a href="#tabs-1">Réserver</a></li>
    <li><a href="#tabs-2">Réservations</a></li>
  </ul>
  <div id="tabs-1">
	<%@ include file="../jspf/reservations/createResa.jspf" %>
  </div>
  <div id="tabs-2">
  	<%@ include file="../jspf/reservations/list.jspf" %>
  </div>
</div>
</div>
