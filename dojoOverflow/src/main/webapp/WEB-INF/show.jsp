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
<div class="container mt-5" >

		<div class="row">
			<div class="col-12 text-end">
				<a href="/" class="btn btn-outline-warning">Volver</a>
			</div>
		</div>

		<div class="row mt-3">
			<div class="col-md-8 offset-md-2 text-center">
				<h1 class="display-5">
					<c:out value="${questionDetalles.pregunta}"></c:out>
				</h1>
			</div>
		</div>

		<div class="row m-2">
			<div class="col-md-12">
				<p>
					<strong>Tags:</strong>
				</p>
				<ul class="list-inline">
					<c:forEach items="${tagsRelacionados}" var="tag">
						<li class="list-inline-item">
							<span class="badge bg-secondary">
								<c:out value="${tag.subject}"></c:out>
							</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div class="card mb-3">
					<div class="card-header">Muro de respuestas</div>
					<div class="card-body message-wall">
						<ul class="list-group message-list">
							<c:forEach var="respuesta" items="${listaDeAnswers}">
								<li class="list-group-item"><strong>${respuesta.respuesta}</strong>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div class="card mb-3">
					<div class="card-header">Add your answer</div>
					<div class="card-body">
						<form:form action="/questions/${questionId}/answer" method="post"
							modelAttribute="answer">
							<div class="form-group m-3">
								<form:label path="respuesta">Answer:</form:label>
								<form:textarea path="respuesta" class="form-control"></form:textarea>
							</div>
							<button type="submit" class="btn btn-dark m-2">Answer it!</button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>