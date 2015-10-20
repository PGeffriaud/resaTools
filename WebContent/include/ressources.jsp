<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="body" class="panel panel-default">
<div class="panel-heading">Ressources</div>
<div class="panel-body">
	<form action="${pageContext.request.contextPath}/addtype" method="post" class="form" id="form-addtype">
	<div class="row">
		<div class="col-md-3">Créer un type de ressources</div>
		<div class="form-group col-md-5">
			<input type="text" name="typeName" id="typeName" class="form-control" placeholder="Name"/>
		</div>
		<div class="form-group col-md-3">
			<input type="submit" class="btn btn-primary" value="Create">
		</div>
	</div>
	</form>
	<div class="row">
	<ul>
	<c:forEach items="${listType}" var="i">
		<li><c:out value='${i}'/></li>
	</c:forEach>
	</ul>
	</div>
</div>
</div>