<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="body" class="panel panel-default">
<div class="panel-heading">Gestion des Ressources</div>
<div class="panel-body">
<div class="panel">
<div class="row">
	<h4>Liste des ressources</h4>
	<form class="form" method="post" id="form-ressource-search" action="${pageContext.request.contextPath}/action/ressources/search">
	    <div class="row">
	    <div class="col-md-5">
	        <input type="text" class="form-control" name="ressSearch" placeholder="Search">
	    </div>
	    <div class="col-md-2">
	        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span></button>
	    </div>
	    </div>
	</form>
	<table class="table">
			<thead>
				<tr>
					<th>Nom</th>
					<th>Description</th>
					<th>Types</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="current" items="${listRess}">
					<tr>
						<td>${current.name}</td>
						<td>${current.description}</td>
						<td>
							<c:forEach items="${current.type}" var="t"><span class="label label-default">${t.name}</span>&nbsp;</c:forEach>
						</td>
						<c:if test="${currentSessionUser.isAdmin}">
						<td>
							<form
								action="${pageContext.request.contextPath}/action/ressources/delress"
								method="post" class="form" id="form-delUser">
								<button type="submit" name="delRessButton" value="${current.id}"
									class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash"></span>
								</button>
							</form>
						</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<c:if test="${currentSessionUser.isAdmin}">
	<div class="row">
	<hr>
	<h4>Créer un type</h4>
	<form action="${pageContext.request.contextPath}/action/ressources/addtype" method="post" class="form" id="form-addtype">
		<div class="row">
		<div class="col-md-5">
			<input type="text" name="typeName" id="typeName" class="form-control" placeholder="Name"/>
		</div>
		<div class="col-md-7">
			<input type="submit" class="btn btn-primary" value="Create">
		</div>
		</div>
	</form>
	</div>
	<div class="row col-md-12">
		<c:forEach items="${listType}" var="i">
			<span class="label label-default"><c:out value='${i.name}'/>
				<a title="remove this type" href="${pageContext.request.contextPath}/action/ressources/deltype?id=${i.id}"><span class="glyphicon glyphicon-remove" style="color:black"></span></a>
			</span>&nbsp;
		</c:forEach>
	</div>
	<div class="row">
	<br/>
	<hr>
	<h4>Créer une ressource</h4>
	<form action="${pageContext.request.contextPath}/action/ressources/address" method="post">
		<div class="row">
			<div class="col-md-6">
				<input type="text" name="nameRess" placeholder="Name" class="form-control" required/>
				<textarea name="textRess" placeholder="Description" class="form-control" maxlength="255"></textarea>
			</div>
			<div class="col-md-5">
				<select required multiple class="form-control" name="selectType">
				<option disabled>Choose associated types</option>
				<c:forEach items="${listType}" var="i">
					<option value="${i.id}">${i.name}</option>
				</c:forEach>
				</select>
			</div>
			<div class="col-md-1"></div>
		</div>
		<div class="row col-md-12">
			<input type="submit" value="Create" class="btn btn-primary"/>
		</div>
	</form>
	</div>
	</c:if>
</div>
</div>
</div>