<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<a class="navbar-brand" href="#">[Nombre de Usuaro]</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarText"
				aria-controls="navbarText" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"></li>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/spring-mvc-project/users/list">Administrar
							Usuarios</a></li>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/spring-mvc-project/categories/list">Administrar
							Categorias</a></li>
				</ul>
				<a class="nav-link active text-light" aria-current="page"
					href="/spring-mvc-project/tasks/list#">Home</a>

			</div>
		</div>
	</nav>
</header>

<body class="row d-flex align-items-center justify-content-center">
	<div class="col-10 mt-3">
		<h1>Lista de Categorias</h1>
		<hr>
		<table class="table table-striped table-hover text-center">
			<thead>
				<tr class="text-center">
					<th>ID</th>
					<th>Nombre Categoria</th>
					<th>Borrar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${categories}">

					<tr class="text-center">

						<td><c:out value="${c.id}" /></td>
						<td><c:out value="${c.name}" /></td>
						<td><a href=delete/${c.id} type="button"
							class="btn btn btn-outline-dark">Borrar</a></td>

					
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>