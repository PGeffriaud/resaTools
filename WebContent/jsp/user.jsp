<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${currentSessionUser.isAdmin}">
	<div id="body" class="panel panel-default">
		<div id="tabs" class="panel-body">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#tabs-1">Liste des utilisateurs</a></li>
				<li><a href="#tabs-2">Ajouter un utilisateur</a></li>
			</ul>
			<div id="tabs-1">
				<%@ include file="../jspf/users/listUser.jspf"%>
			</div>
			<div id="tabs-2">
				<%@ include file="../jspf/users/createUser.jspf"%>
			</div>
		</div>
	</div>
</c:if>
<c:if test="${!currentSessionUser.isAdmin}">
	<div class="alert alert-danger">
		<span class="glyphicon glyphicon-remove"></span> Page réservée aux
		administrateurs
	</div>
</c:if>