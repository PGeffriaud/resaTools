<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>resaTools</title>
</head>
<body>
<div class="container">
	<div class="row col-md-12"><%@ include file="include/header.jspf" %></div>
	<div class="row">
		<div class="col-md-3">
			<%@ include file="include/menu.jspf" %>
		</div>
		<div class="col-md-9">
			<c:if test="${not empty query and query }">
				<div class="alert alert-success">
					<span class="glyphicon glyphicon-ok"></span>
					Modification effectuée avec succès</div>
			</c:if>
			<c:if test="${not empty query and not query }">
				<div class="alert alert-warning">
					<span class="glyphicon glyphicon-remove"></span>
					Le type existe déjà</div>
			</c:if>
			<c:if test="${not empty page}">
				<td class="page"><jsp:include page="include/${page}.jsp" /></td>
			</c:if>
			<c:if test="${empty page}">
				<td class="page"><jsp:include page="include/accueil.jsp" /></td>
			</c:if>
		</div>
	</div>
	<div class="row col-md-12"><%@ include file="include/footer.jspf" %></div>
</div>

</body>
</html>