<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="body" class="panel panel-default">
	<div class="panel-heading">Gestion des Utilisateurs</div>
	<div class="panel-body">
		<form action="${pageContext.request.contextPath}/action/user/addUser"
			method="post" class="form" id="form-addUser">
			<div class="row">
				<div class="form-group col-md-3">
					<input type="submit" class="btn btn-primary"
						value="Ajouter un utilisateur">
				</div>
			</div>
		</form>

		<h3>Liste des utilisateurs</h3>
		<table class="table">
			<thead>
				<tr>
					<th>Login</th>
					<th>Prénom</th>
					<th>Nom</th>
					<th>N° Téléphone</th>
					<th>EMail</th>
					<th>Admin</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="current" items="${listUser}">
					<tr>
						<td>${current.login}</td>
						<td>${current.firstname}</td>
						<td>${current.name}</td>
						<td>${current.phone}</td>
						<td>${current.mail}</td>
						<td>${current.isAdmin}</td>
						<td>
							<form
								action="${pageContext.request.contextPath}/action/user/delUser"
								method="post" class="form" id="form-delUser">
								<button type="submit" name="delUserButton" value="${current.id}"
									class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash"></span>
								</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</div>