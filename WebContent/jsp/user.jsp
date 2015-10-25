
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="body" class="panel panel-default">
	<div class="panel-heading">Gestion des Utilisateurs</div>
	<div class="panel-body">

		<c:choose>
			<c:when test="${currentSessionUser.isAdmin == true}">

				<!--  Affichage des messages d'erreur  -->
				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger">${errorMessage}</div>
				</c:if>

				<!--  Affichage des messages de validation  -->
				<c:if test="${not empty validationMessage}">
					<div class="alert alert-success">${validationMessage}</div>
				</c:if>

				<!--  On affiche la liste des utilisateurs qui sont enregistr�s en base  -->
				<h3>Liste des utilisateurs</h3>
				<table class="table">
					<thead>
						<tr>
							<th>Login</th>
							<th>Pr�nom</th>
							<th>Nom</th>
							<th>N� T�l�phone</th>
							<th>Mail</th>
							<th>Admin</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="current" items="${listUser}">
							<tr>
								<td>${current.value.login}</td>
								<td>${current.value.firstname}</td>
								<td>${current.value.name}</td>
								<td>${current.value.phone}</td>
								<td>${current.value.mail}</td>
								<td>${current.value.isAdmin}</td>
								<td>
									<div class="row">
										<div class="col-xs-3">
											<form action="" method="post" class="form"
												id="form-updateUser">
												<button type="submit" name="updateUserButton"
													value="${current.value.id}" class="btn btn-default btn-sm">
													<span class="glyphicon glyphicon-edit"></span>
												</button>
											</form>
										</div>
										<div class="col-xs-3">
											<form
												action="${pageContext.request.contextPath}/action/user/delUser"
												method="post" class="form" id="form-delUser">
												<button type="submit" name="delUserButton"
													value="${current.value.id}" class="btn btn-danger btn-sm">
													<span class="glyphicon glyphicon-trash"></span>
												</button>
											</form>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<!--  Si la variable userToUpdate n'est pas vide alors on affiche le formulaire de modification -->
				<c:if test="${not empty param.updateUserButton}">
					<c:set var="urlRedirection"
						value="${pageContext.request.contextPath}/action/user/updateUser" />
					<h3>Modifier un utilisateur</h3>
				</c:if>
				<c:if test="${empty param.updateUserButton}">
					<c:set var="urlRedirection"
						value="${pageContext.request.contextPath}/action/user/addUser" />
					<h3>Ajouter un utilisateur</h3>
				</c:if>

				<form class="form-horizontal" action="${urlRedirection}"
					method="post" role="form">
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Nom:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="name"
								placeholder="Nom"
								value="${listUser[param.updateUserButton].name}" required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Pr�nom:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="firstname"
								placeholder="Pr�nom"
								value="${listUser[param.updateUserButton].firstname}" required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Email:</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" name="email"
								placeholder="Email"
								value="${listUser[param.updateUserButton].mail}">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">N�
							t�l�phone:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="phone"
								placeholder="Phone"
								value="${listUser[param.updateUserButton].phone}">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Login:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="login"
								placeholder="Login"
								value="${listUser[param.updateUserButton].login}" required>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pwd">Password:</label>
						<div class="col-sm-10">
							<input required type="password" class="form-control" name="pwd"
								placeholder="Password"
								value="${listUser[param.updateUserButton].password}" required>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<c:choose>
									<c:when
										test="${listUser[param.updateUserButton].isAdmin == true}">
										<label><input type="checkbox" name="admin" checked>Administrateur</label>
									</c:when>
									<c:otherwise>
										<label><input type="checkbox" name="admin">Administrateur</label>
									</c:otherwise>
								</c:choose>

							</div>
						</div>
					</div>
					<c:if test="${not empty listUser[param.updateUserButton].id}">
						<input type="hidden" name="id"
							value="${listUser[param.updateUserButton].id}" />
					</c:if>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-primary">Valider</button>
						</div>
					</div>
				</form>
			</c:when>
			<c:otherwise>
				<div class="alert alert-danger">Cette page est r�serv�e aux administrateurs</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>