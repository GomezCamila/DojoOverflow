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
<div class="container mt-5">
		<div class="row m-2">
			<div class="col-md-8">
			
				<h4>What is your question?</h4>
				
				<form:form action="/questions/new" method="POST"
					modelAttribute="question">
					
					<div class="form-group col-md-8">
						<form:label class="form-label" path="pregunta">Question:</form:label>
						<form:textarea class="form-control" path="pregunta" rows="4"
							placeholder="Enter your question" />
						<form:errors class="text-danger" path="pregunta" />
					</div>
					
					<div class="form-group col-md-8">
						<label class="form-label">Tag:</label> <input type="text"
							class="form-control" name="etiquetas" placeholder="Enter a tag" />
						<div class="text-danger">
							<c:out value="${ error}"></c:out>
							<c:out value="${ errores}"></c:out>
						</div>
					</div>
					
					<input type="submit" value="Create" class="btn btn-warning my-2" />
					
				</form:form>
				
			</div>
		</div>
	</div>
</body>
</html>