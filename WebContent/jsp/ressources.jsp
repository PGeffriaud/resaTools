<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="body" class="panel panel-default">
<div id="tabs" class="panel-body">
  <ul class="nav nav-tabs">
    <li class="active"><a href="#tabs-1">Search</a></li>
    <c:if test="${currentSessionUser.isAdmin}">
	    <li><a href="#tabs-2">Créer ressource</a></li>
	    <li><a href="#tabs-3">Gestion des types</a></li>
    </c:if>
  </ul>
  <div id="tabs-1">
   <%@ include file="../jspf/ressources/list.jspf" %>
  </div>
  <c:if test="${currentSessionUser.isAdmin}">
  <div id="tabs-2">
	<%@ include file="../jspf/ressources/createRess.jspf" %>
  </div>
  <div id="tabs-3">
  	<%@ include file="../jspf/ressources/types.jspf" %>
  </div>
  </c:if>
</div>
</div>
