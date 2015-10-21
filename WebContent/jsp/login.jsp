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
		<c:if test="${empty currentSessionUser}">
			<div class="modal-header">
				<h1 class="text-center">ResaTools</h1>
			</div>
			<div class="modal-body">
			<div class="row">
			<div class="col-md-3"></div>
				<div class="col-md-6">
				<c:if test="${connectedTried == true}">
					<div class="alert alert-danger">Votre identifiant est inconnu
						ou votre mot de passe incorrect.</div>
				</c:if>
				<form id="formAuth" action="${pageContext.request.contextPath}/action/login" method="POST" class="form">
					<div class="form-group">
						<input type="text" name="login" id="login" class="form-control" placeholder="Login" />
					</div>
					<div class="form-group">
						<input type="password" name="pass" id="pass" class="form-control" placeholder="Password" />
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-primary btn-lg btn-block" value="Connexion" />
					</div>
				</form>
				</div>
				<div class="col-md-3"></div>
			</div>
			</div>
		</c:if>
	</div>
	<div class="row modal-footer">
		<div class="col-md-12"><%@ include file="../include/footer.jspf"%></div>
	</div>
</body>
</html>