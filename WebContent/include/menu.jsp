<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="menu" class="panel panel-default">
	<div class="panel-body">
		<c:if test="${not empty currentSessionUser}">
			<ul class="list-group">
				<li class="list-group-item"><a
					href="${pageContext.request.contextPath}/page/accueil">Accueil</a></li>
				<li class="list-group-item"><a
					href="${pageContext.request.contextPath}/page/ressources">Ressources</a></li>
				<li class="list-group-item"><a
					href="${pageContext.request.contextPath}/page/reservations">Réservations</a></li>
			</ul>
			<div id="body" class="panel panel-default">
				<div class="panel-heading">Utilisateur</div>
				<div class="panel-body">
					<p>${currentSessionUser.firstname}.${currentSessionUser.name} (${currentSessionUser.login})</p>
					<form id="formAuth" action="" method="POST" class="form">
						<input type="submit" class="btn btn-primary"
							name="buttonDeconnexion" value="Déconnexion" />
					</form>
				</div>
			</div>
		</c:if>
		<c:if test="${empty currentSessionUser}">
			<c:if test="${connectedTried == true}">
				<div class="alert alert-danger">Votre identifiant est inconnu
					ou votre mot de passe incorrect.</div>
			</c:if>
			<form id="formAuth" action="" method="POST" class="form">
				<div class="form-group">
					<input type="text" name="login" id="login" class="form-control"
						placeholder="Login" />
				</div>
				<div class="form-group">
					<input type="password" name="pass" id="pass" class="form-control"
						placeholder="Password" />
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-primary" name="buttonConnexion"
						value="Connexion" />
				</div>
			</form>
		</c:if>
	</div>
</div>