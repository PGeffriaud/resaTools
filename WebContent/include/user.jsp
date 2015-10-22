<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="body" class="panel panel-default">
	<div class="panel-heading">Gestion des Utilisateurs</div>
	<div class="panel-body">
		<%-- 		<form action="${pageContext.request.contextPath}/action/user/addUser" --%>
		<!-- 			method="post" class="form" id="form-addUser"> -->
		<!-- 			<div class="row"> -->
		<!-- 				<div class="form-group col-md-3"> -->
		<!-- 					<input type="submit" class="btn btn-primary" -->
		<!-- 						value="Ajouter un utilisateur"> -->
		<!-- 				</div> -->
		<!-- 			</div> -->
		<!-- 		</form> -->

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
		<h3>Ajouter un utilisateur</h3>
		<form class="form-horizontal"
			action="${pageContext.request.contextPath}/action/user/addUser"
			method="post" role="form">
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Nom:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						placeholder="Nom" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Prénom:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="firstname"
						placeholder="Prénom" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Email:</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" name="email"
						placeholder="Email">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">N°
					téléphone:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="phone"
						placeholder="phone">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Login:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="login"
						placeholder="Login" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Password:</label>
				<div class="col-sm-10">
					<input required type="password" class="form-control" name="pwd"
						placeholder="Password" required>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label><input type="checkbox" name="admin">Administrateur</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Valider</button>
				</div>
			</div>
		</form>
	</div>
</div>
</div>