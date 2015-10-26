<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="body" class="panel panel-default">
	<div id="tabs" class="panel-body">
		<!--  Affichage des messages d'erreur  -->
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger">${errorMessage}</div>
		</c:if>

		<!--  Affichage des messages de validation  -->
		<c:if test="${not empty validationMessage}">
			<div class="alert alert-success">${validationMessage}</div>
		</c:if>
		<ul class="nav nav-tabs">
			<c:if test="${currentSessionUser.isAdmin}">
				<li class="active"><a href="#tabs-1">Liste des utilisateurs</a></li>
				<li><a href="#tabs-2">Ajouter un utilisateur</a></li>
			</c:if>
		</ul>
		<div id="tabs-1">
			<%@ include file="../jspf/users/listUser.jspf"%>
		</div>
		<c:if test="${currentSessionUser.isAdmin}">
			<div id="tabs-2">
				<%@ include file="../jspf/users/createUser.jspf"%>
			</div>
		</c:if>
	</div>
</div>
