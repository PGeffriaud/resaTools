<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Login</title>
</head>
<body>
	<div class="container">
		<div class="row col-md-12"><%@ include
				file="../include/header.jspf"%></div>
		<c:if test="${empty currentSessionUser}">
			<div class="modal-header">
				<h1 class="text-center">Connexion</h1>
			</div>
			<div class="modal-body">
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
						<input type="submit" class="btn btn-primary btn-lg btn-block"
							name="buttonConnexion" value="Connexion" />
					</div>
				</form>
			</div>
		</c:if>
	</div>
	<div class="row col-md-12"><%@ include
			file="../include/footer.jspf"%></div>
</body>
</html>