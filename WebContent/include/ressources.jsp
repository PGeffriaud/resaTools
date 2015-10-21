<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="body" class="panel panel-default">
<div class="panel-heading">Gestion des Ressources</div>
<div class="panel-body">
<div class="panel">
	<div class="row">
	<form action="${pageContext.request.contextPath}/action/ressources/addtype" method="post" class="form" id="form-addtype">
		<div class="col-md-2"><label for="typeName">Create new type</label></div>
		<div class="form-group col-md-6">
			<input type="text" name="typeName" id="typeName" class="form-control" placeholder="Name"/>
		</div>
		<div class="form-group col-md-3">
			<input type="submit" class="btn btn-primary" value="Create">
		</div>
	</form>
	</div>
	<div class="row">
		<div class="col-md-12">
		<c:forEach items="${listType}" var="i">
			<span class="label label-default"><c:out value='${i[1]}'/>
				<a title="remove this type" href="${pageContext.request.contextPath}/action/ressources/deltype?id=${i[0]}"><span class="glyphicon glyphicon-remove" style="color:black"></span></a>
			</span>&nbsp;
		</c:forEach>
		</div>
	</div>
</div>
</div>
</div>