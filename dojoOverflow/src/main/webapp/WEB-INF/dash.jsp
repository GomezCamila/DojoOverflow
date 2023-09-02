<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formateo fechas (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Formulario form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- para errores de renderizado en rutas PUT -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AQUI EL TITULO DE TU PLNATILLA</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
<body>
	<div class="container mt-5">

		<div class="row m-2">
			<div class="col-md-12">
				<h1 class="text-center display-3">Questions Dashboard</h1>
				<table class="table table-striped m-3">
					<thead>
						<tr>
							<th>Question</th>
							<th>Tags</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="question" items="${questions}">
							<tr>
								<td><a href="/questions/${question.id}" class="text-primary"><c:out value="${question.pregunta}"></c:out></a></td>
								
								<c:forEach var="tag" items="${question.tags}">
			
										<td><c:out value="${tag.subject}"></c:out></td>
										
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="text-end">
			<a href="/questions/new" class="btn btn-outline-warning">Create New Question</a>
		</div>
	</div>
</body>
</html>