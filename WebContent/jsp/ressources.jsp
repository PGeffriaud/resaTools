<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="body" class="panel panel-default">
<div class="panel-heading">Gestion des Ressources</div>
<div id="tabs" class="panel-body">
  <ul class="nav nav-tabs">
    <li class="active"><a href="#tabs-1">Search</a></li>
    <li><a href="#tabs-2">Créer ressource</a></li>
    <li><a href="#tabs-3">Gestion des types</a></li>
  </ul>
  <div id="tabs-1">
   <%@ include file="../jspf/ressources/list.jspf" %>
  </div>
  <div id="tabs-2">
	<%@ include file="../jspf/ressources/createRess.jspf" %>
  </div>
  <div id="tabs-3">
  	<%@ include file="../jspf/ressources/types.jspf" %>
  </div>
</div>
<!-- <div id="tabs" class="panel-body"> -->
<!-- <ul class="nav nav-tabs"> -->
<!--     <li class="active"><a href="#list" data-toggle="tab">Search</a></li> -->
<!--     <li><a href="#form-ress" data-toggle="tab">Créer Ressource</a></li> -->
<!--     <li><a href="#types" data-toggle="tab">Gestion des types</a></li> -->
<!-- </ul> -->
<!-- <div class="body-content"> -->
<%-- 	<div id="list" class="tab-pane fade in active"><%@ include file="../jspf/ressources/list.jspf" %></div> --%>
<%-- 	<div id="form-ress" class="tab-pane fade"><%@ include file="../jspf/ressources/createRess.jspf" %></div> --%>
<%-- 	<div id="types" class="tab-pane fade"><%@ include file="../jspf/ressources/types.jspf" %></div> --%>
<!-- </div> -->
<!-- </div> -->
</div>
