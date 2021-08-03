<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>


<body class="text-center row d-flex justify-content-center align-items-center" style="height: 90vh;">

    <main class="form-signin col-4">
        <form class=" d-flex flex-column justify-content-center" method="post" action="users/add">
          <h1 class="h3 mb-3 fw-normal w-90">Please register in</h1>
      
          <div class="form-floating m-1">
            <input type="email" class="form-control" id="floatingInput" name="email" placeholder="Email">
          </div>
          <div class="form-floating m-1">
            <input type="text" class="form-control" id="floatingInput" name="login" placeholder="User Name">
          </div>
          <div class="form-floating m-1">
            <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password">
          </div>
          
          <div class="form-check form-switch text-center">
            <input class="form-check-input" type="checkbox" name="isAdmin" id="flexSwitchCheckDefault" value="true">
            <label class="form-check-label" for="flexSwitchCheckDefault">Dale si eres administrador</label>
          </div> 

          <button class="btn btn-success m-1" type="submit">Register</button>
         
          <p class="mt-5 mb-3 text-muted">&copy; PM</p>
        </form>
      </main>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>