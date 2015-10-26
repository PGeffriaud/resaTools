<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
<title>resaTools</title>
</head>
<c:if test="${not empty currentSessionUser}">
<body>
<div class="container">
	<div class="row col-md-12"><%@ include file="jspf/index/header.jspf" %></div>
	<div class="row">
		<div class="col-md-3">
			<%@ include file="jspf/index/menu.jspf" %>
		</div>
		<div class="col-md-9">
			<%@ include file="jspf/index/infos.jspf" %>
			<c:if test="${not empty page}">
				<jsp:include page="jsp/${page}.jsp" />
			</c:if>
			<c:if test="${empty page}">
				<jsp:include page="jsp/accueil.jsp" />
			</c:if>
		</div>
	</div>
	<div class="row col-md-12"><%@ include file="jspf/index/footer.jspf" %></div>
</div>
</body>
</c:if>
<c:if test="${empty currentSessionUser}">
<jsp:forward page="/jsp/login.jsp"></jsp:forward>
</c:if>
</html>
