<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body>
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

						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/spring-mvc-project/users/list">Administrar Usuarios</a></li>

						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/spring-mvc-project/categories/list">Administrar Categorias</a></li>
					</ul>
					<a class="nav-link active text-light"aria-current="page" href="/spring-mvc-project/tasks/list#">Home</a>
					<button type="button" class="btn btn-light" data-bs-toggle="modal"
						data-bs-target="#staticBackdrop">Anadir</button>
				</div>
			</div>
		</nav>
	</header>

	<main class="container">
		<!--MODAL-->
		<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
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

						<form action="add/38/6" method="post">

							<div class="d-flex flex-column justify-content-center aling-items-center">
									<h5 class="form-label">Nombre de la tarea</h5>
									<div class="md-form mb-5">
										<i class="fas fa-envelope prefix grey-text"></i>
										<input type="text" class="form-control" name="title" />
										<input type="submit" class="btn btn-dark w-100 mt-1" value="Anadir Tarea" />
									</div>
							</div>

						</form>

						
						<form action="categories/add" method="post">
			
							<div class="d-flex flex-column justify-content-center aling-items-center">
								<h5 class="form-label">Nombre de la categoria</h5>
								<div class="md-form mb-5">
									<i class="fas fa-envelope prefix grey-text"></i>
									<input type="text" class="form-control" name="name" />
									<input type="submit" class="btn btn-dark w-100 mt-1" value="Anadir categoria" />
								</div>
							</div>
							 

						</form>
					</div>
				</div>
			</div>
		</div>

		<!--TITTLE-->
		<div
			class="d-flex align-items-center p-3 my-3 text-white rounded shadow-sm"
			style="background-color: #74b9ff">
			<div
				class="lh-1 d-flex justify-content-between align-items-center w-100">
				<h1 class="h6 mb-0 text-white lh-1">Inbox</h1>
				<span class="material-icons" style="cursor: pointer;">expand_more</span>
			</div>
		</div>



		<!--CARD-->
		<div class="my-3 p-3 bg-body rounded shadow-sm w-90 text-center">
			<div class="w-100 row  border-bottom">
				<h6 class="pb-2 mb-0 col-2">Tareas</h6>
				<h6 class="pb-2 mb-0 col-2">Fecha de creacion</h6>
				<h6 class="pb-2 mb-0 col-2">Fecha planeada</h6>
				<h6 class="pb-2 mb-0 col-2">Comentarios</h6>
				<h6 class="pb-2 mb-0 col-2">Id</h6>
				<h6 class="col-2">Finalizar tarea</h6>
			</div>

			<!--TASK-->
			<c:forEach var="task" items="${tasks}">
				<div class="d-flex text-muted pt-3 border-bottom">
					<div class="w-100 row">
						<!--TITULO-->
						<p class="pb-3 mb-0 small lh-sm col-2 ">
							<strong class="d-block text-gray-dark"><c:out value="${task.title}" /></strong>
						</p>
						<!--FECHA CREACION-->
						<p class="col-2">
							<c:if test="${task.created == null}">?</c:if>
							<c:out value="${task.created}" />
						</p>
						<!--FECHA PLANEADA-->
						<p class="col-2">
							<c:if test="${task.planned == null}">?</c:if>
							<c:out value="${task.planned}" />
						</p>
						<!--COMENTARIO-->
						<p class="col-2">
							<c:if test="${task.comments == null}">?</c:if>
							<c:out value="${task.comments}" />
						</p class="col-2">
						<!--ID-->
						<p class="col-2">
							<c:if test="${task.category_id == null}">?</c:if>
							<c:out value="${task.category_id}" />
						</p>
						<!--FINALIZAR TAREA-->
						<p class="col-2">
							<c:if test="${task.finished == null}">
								<a href=finish/${task.id} type="button" class="btn btn-danger">Finalizar</a>
							</c:if>

							<c:if test="${task.finished == task.created}">
								Tarea Finalizada
							</c:if>
						</p>
					</div>
				</div>
			</c:forEach>

		</div>

		<!--TITTLE-->
		<div
			class="d-flex align-items-center p-3 my-3 text-white rounded shadow-sm"
			style="background-color: #00b894">
			<div
				class="lh-1 d-flex justify-content-between align-items-center w-100">
				<h1 class="h6 mb-0 text-white lh-1">Tareas de hoy</h1>
				<span class="material-icons" style="cursor: pointer;">expand_more</span>
			</div>
		</div>



		<!--CARD-->
		<div class="my-3 p-3 bg-body rounded shadow-sm w-90 text-center">
			<div class="w-100 row  border-bottom">
				<h6 class="pb-2 mb-0 col-2">Tareas</h6>
				<h6 class="pb-2 mb-0 col-2">Fecha de creacion</h6>
				<h6 class="pb-2 mb-0 col-2">Fecha planeada</h6>
				<h6 class="pb-2 mb-0 col-2">Comentarios</h6>
				<h6 class="pb-2 mb-0 col-2">Categoria</h6>
				<h6 class="col-2">Finalizar tarea</h6>
			</div>
			<!--TASK-->
			<c:forEach var="task" items="${todayTasks}">
				<div class="d-flex text-muted pt-3 border-bottom">
					<div class="w-100 row">
						<!--TITULO-->
						<p class="pb-3 mb-0 small lh-sm col-2">
							<strong class="d-block text-gray-dark"><c:out value="${task.title}" /></strong>
						</p>
						<!--FECHA CREACION-->
						<p class="col-2">
							<c:if test="${task.created == null}">?</c:if>
							<c:out value="${task.created}" />
						</p>
						<!--FECHA PLANEADA-->
						<p class="col-2">
							<c:if test="${task.planned == null}">?</c:if>
							<c:out value="${task.planned}" />
						</p>
						<!--COMENTARIO-->
						<p class="col-2">
							<c:if test="${task.comments == null}">?</c:if>
							<c:out value="${task.comments}" />
						</p class="col-2">
						<!--ID-->
						<p class="col-2">
							<c:if test="${task.category_id == null}">?</c:if>
							<c:out value="${task.category_id}" />
						</p>
						<!--FINALIZAR TAREA-->
						<p class="col-2">
							<c:if test="${task.finished == null}">
								<a href=finish/${task.id} type="button" class="btn btn-danger">Finalizar</a>
							</c:if>

							<c:if test="${task.finished == task.created}">
								Tarea Finalizada
							</c:if>
						</p>
					</div>
				</div>
			</c:forEach>

		</div>





		<!--TITTLE-->
		<div
			class="d-flex align-items-center p-3 my-3 text-white rounded shadow-sm"
			style="background-color: #ff7675;">
			<div
				class="lh-1 d-flex justify-content-between align-items-center w-100">
				<h1 class="h6 mb-0 text-white lh-1">Tareas</h1>
				<span class="material-icons" style="cursor: pointer;">expand_more</span>
			</div>
		</div>



		<!--CARD-->
		<div class="my-3 p-3 bg-body rounded shadow-sm w-90 text-center">
			<div class="w-100 row  border-bottom">
				<h6 class="pb-2 mb-0 col-2">Tareas</h6>
				<h6 class="pb-2 mb-0 col-2">Fecha de creacion</h6>
				<h6 class="pb-2 mb-0 col-4">Fecha finalizada</h6>
				<h6 class="pb-2 mb-0 col-2">Comentarios</h6>
				<h6 class="pb-2 mb-0 col-2">Categoria</h6>
			</div>
			<!--TASK-->
			<c:forEach var="task" items="${finishedTasks}">
			<div class="d-flex text-muted pt-3 border-bottom">
				<div class="w-100 row">
					<!--TITULO-->
					<p class="pb-3 mb-0 small lh-sm col-2">
						<strong class="d-block text-gray-dark"><c:out value="${task.title}" /></strong>
					</p>
					<!--FECHA CREACION-->
					<p class="col-2">
						<c:if test="${task.created == null}">?</c:if>
						<c:out value="${task.created}" />
					</p>
					<!--FECHA PLANEADA-->
					<p class="col-4">
						<c:if test="${task.finished == null}">?</c:if>
						<c:out value="${task.finished}" />
					</p>
					<!--COMENTARIO-->
					<p class="col-2">
						<c:if test="${task.comments == null}">?</c:if>
						<c:out value="${task.comments}" />
					</p>
					<!--ID-->
					<p class="col-2">
						<c:if test="${task.category_id == null}">?</c:if>
						<c:out value="${task.category_id}" />
					</p>
				</div>
			</div>
			</c:forEach>

		</div>

	</main>


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/user.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>