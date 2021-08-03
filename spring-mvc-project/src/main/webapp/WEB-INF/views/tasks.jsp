<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<a class="navbar-brand" href="#">[Nombre de Usuaro]</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarText"
					aria-controls="navbarText" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarText">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">Home</a></li>

						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">Administrar Usuarios</a></li>
					</ul>
					<button type="button" class="btn btn-light" data-bs-toggle="modal"
						data-bs-target="#staticBackdrop">Crear Tarea</button>
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
						<h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">...</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Understood</button>
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
		<div class="my-3 p-3 bg-body rounded shadow-sm w-90">
			<h6 class="border-bottom pb-2 mb-0">Tareas ya acabadas</h6>
			<!--TASK-->
			<div class="d-flex text-muted pt-3 border-bottom">
				<svg class="bd-placeholder-img flex-shrink-0 me-2 rounded"
					width="32" height="32" xmlns="http://www.w3.org/2000/svg"
					role="img" aria-label="Placeholder: 32x32"
					preserveAspectRatio="xMidYMid slice" focusable="false">
					<title>Placeholder</title><rect width="100%" height="100%"
						fill="#6c5ce7" />
					<text x="50%" y="50%" fill="#6c5ce7" dy=".3em">32x32</text></svg>
				<p class="pb-3 mb-0 small lh-sm ">
					<strong class="d-block text-gray-dark align-items-center">Task</strong>
				</p>
				<div class="w-100 d-flex justify-content-around">
					<p>Fecha de inicio: 23/08/2018</p>
					<p>Fecha tope: 23/08/2018</p>
					<p>CATEGORIA</p>
				</div>
			</div>
			<!--TASK-->
			<div class="d-flex text-muted pt-3 border-bottom">
				<svg class="bd-placeholder-img flex-shrink-0 me-2 rounded"
					width="32" height="32" xmlns="http://www.w3.org/2000/svg"
					role="img" aria-label="Placeholder: 32x32"
					preserveAspectRatio="xMidYMid slice" focusable="false">
					<title>Placeholder</title><rect width="100%" height="100%"
						fill="#6c5ce7" />
					<text x="50%" y="50%" fill="#6c5ce7" dy=".3em">32x32</text></svg>
				<p class="pb-3 mb-0 small lh-sm ">
					<strong class="d-block text-gray-dark">Task</strong>
				</p>
				<div class="w-100 d-flex justify-content-around">
					<p>Fecha de inicio: 23/08/2018</p>
					<p>Fecha tope: 23/08/2018</p>
					<p>CATEGORIA</p>
				</div>
			</div>
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
		<div class="my-3 p-3 bg-body rounded shadow-sm w-90">
			<h6 class="border-bottom pb-2 mb-0">Tareas ya acabadas</h6>
			<!--TASK-->
			<div class="d-flex text-muted pt-3 border-bottom">
				<svg class="bd-placeholder-img flex-shrink-0 me-2 rounded"
					width="32" height="32" xmlns="http://www.w3.org/2000/svg"
					role="img" aria-label="Placeholder: 32x32"
					preserveAspectRatio="xMidYMid slice" focusable="false">
					<title>Placeholder</title><rect width="100%" height="100%"
						fill="#6c5ce7" />
					<text x="50%" y="50%" fill="#6c5ce7" dy=".3em">32x32</text></svg>
				<p class="pb-3 mb-0 small lh-sm ">
					<strong class="d-block text-gray-dark">Task</strong>
				</p>
				<div class="w-100 d-flex justify-content-around">
					<p>Fecha de inicio: 23/08/2018</p>
					<p>Fecha tope: 23/08/2018</p>
					<p>CATEGORIA</p>
				</div>
			</div>
			<!--TASK-->
			<div class="d-flex text-muted pt-3 border-bottom">
				<svg class="bd-placeholder-img flex-shrink-0 me-2 rounded"
					width="32" height="32" xmlns="http://www.w3.org/2000/svg"
					role="img" aria-label="Placeholder: 32x32"
					preserveAspectRatio="xMidYMid slice" focusable="false">
					<title>Placeholder</title><rect width="100%" height="100%"
						fill="#6c5ce7" />
					<text x="50%" y="50%" fill="#6c5ce7" dy=".3em">32x32</text></svg>
				<p class="pb-3 mb-0 small lh-sm ">
					<strong class="d-block text-gray-dark">Task</strong>
				</p>
				<div class="w-100 d-flex justify-content-around">
					<p>Fecha de inicio: 23/08/2018</p>
					<p>Fecha tope: 23/08/2018</p>
					<p>CATEGORIA</p>
				</div>
			</div>
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
		<div class="my-3 p-3 bg-body rounded shadow-sm w-90">
			<h6 class="border-bottom pb-2 mb-0">Tareas ya acabadas</h6>
			<!--TASK-->
			<div class="d-flex text-muted pt-3 border-bottom">
				<svg class="bd-placeholder-img flex-shrink-0 me-2 rounded"
					width="32" height="32" xmlns="http://www.w3.org/2000/svg"
					role="img" aria-label="Placeholder: 32x32"
					preserveAspectRatio="xMidYMid slice" focusable="false">
					<title>Placeholder</title><rect width="100%" height="100%"
						fill="#6c5ce7" />
					<text x="50%" y="50%" fill="#6c5ce7" dy=".3em">32x32</text></svg>
				<p class="pb-3 mb-0 small lh-sm ">
					<strong class="d-block text-gray-dark">Task</strong>
				</p>
				<div class="w-100 d-flex justify-content-around">
					<p>Fecha de inicio: 23/08/2018</p>
					<p>Fecha tope: 23/08/2018</p>
					<p>CATEGORIA</p>
				</div>
			</div>
			<!--TASK-->
			<div class="d-flex text-muted pt-3 border-bottom">
				<svg class="bd-placeholder-img flex-shrink-0 me-2 rounded"
					width="32" height="32" xmlns="http://www.w3.org/2000/svg"
					role="img" aria-label="Placeholder: 32x32"
					preserveAspectRatio="xMidYMid slice" focusable="false">
					<title>Placeholder</title><rect width="100%" height="100%"
						fill="#6c5ce7" />
					<text x="50%" y="50%" fill="#6c5ce7" dy=".3em">32x32</text></svg>
				<p class="pb-3 mb-0 small lh-sm ">
					<strong class="d-block text-gray-dark">Task</strong>
				</p>
				<div class="w-100 d-flex justify-content-around">
					<p>Fecha de inicio: 23/08/2018</p>
					<p>Fecha tope: 23/08/2018</p>
					<p>CATEGORIA</p>
				</div>
			</div>
		</div>
	</main>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>