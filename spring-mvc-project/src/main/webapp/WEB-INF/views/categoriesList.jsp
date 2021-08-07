<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lista de Categor�as</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<header>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">

			<a class="navbar-brand" href="#">${sessionScope.user.login }</a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarText"
				aria-controls="navbarText" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"></li>

					<c:if test="${sessionScope.user.isAdmin == true}">
						<li class="nav-item"><a class="nav-link"
							aria-current="page" href="/spring-mvc-project/users/list">Administrar Usuarios</a></li>
					</c:if>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/spring-mvc-project/categories/list">Administrar
							Categorias</a></li>
				
				</ul>
				<a class="nav-link active text-light" aria-current="page"
					href="/spring-mvc-project/tasks/list#">Home</a>
				<button type="button" class="btn btn-success" data-bs-toggle="modal"
					data-bs-target="#staticBackdrop2">Crear Categoria</button>
					
				<a class="btn btn-danger m-1" href="/spring-mvc-project/logout">Cerrar Sesion</a>
			</div>
		</div>
	</nav>
</header>

<body class="row d-flex align-items-center justify-content-center">
	<div class="col-10 mt-3">

		<div class="modal fade" id="staticBackdrop2" data-bs-backdrop="static"
			data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="staticBackdropLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel">Modal</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">

						<form:form action="add" method="post" modelAttribute="category">

							<div class="d-flex flex-column justify-content-center aling-items-center">
								<h5 class="form-label">Nombre de la categoria</h5>
								<div class="md-form mb-5">
									<i class="fas fa-envelope prefix grey-text"></i>
									 <form:input type="text" class="form-control" name="name" path="name" />
									 <form:errors type="text" class="form-control" name="name" path="name" />  
									 <input type="submit" class="btn btn-dark w-100 mt-1" value="Anadir categoria" />
								</div>
							</div>


						</form:form>
					</div>
				</div>
			</div>
		</div>

		<h1>Lista de Categorias</h1>
		<hr>
		<table class="table table-striped table-hover text-center">
			<thead>
				<tr class="text-center">
					<th>ID</th>
					<th>Nombre Categoria</th>
					<th>Borrar</th>
					<th>Editar</th>
					<th>Ver Tareas</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${categories}">

					<tr class="text-center">

						<td><c:out value="${c.id}" /></td>
						<td><c:out value="${c.name}" /></td>
						<td><a href=delete/${c.id} type="button"
							class="btn btn-outline-dark">Borrar</a></td>
						<td>
							<button type="button" hidden class="btn btn-light"
								data-bs-toggle="modal" data-bs-target="#staticBackdrop"
								id="botonEditar">Editar</button>

							<button type="button" class="btn btn-outline-dark"
								onclick="categoria(${c.id})">Editar</button>
						</td>
						<td><a href=${c.id} type="button"
							class="btn btn-outline-dark">Ver tareas</a></td>
					</tr>

				</c:forEach>

			</tbody>
		</table>


		<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
			data-bs-keyboard="false" tabindex="-1"
			aria-labelledby="staticBackdropLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="staticBackdropLabel">Editar categoria</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">

						<form:form action="edit" method="post" modelAttribute="categoryEdit">
							<div class="d-flex flex-column justify-content-center aling-items-center">
								<form:input type="text" name="name" path="name" class="form-control m-1" placeholder="Escribe el nombre" />
								<input type="submit" class="btn btn-dark w-100 mt-1" value="Editar">
								<input type="hidden" name="id" id="categoria">
							</div>
						</form:form>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/script.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
</html>