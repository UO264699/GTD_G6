<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>
</head>
<body>

	<h1>Bienvenido a la página principal</h1>


	<p>La hora actual del servidor es ${serverTime}</p>

    

	<form action="categories/add" method="post">

         <input type="text" name ="name"/>
         <input type="submit" value ="add category"/>

</form>

</body>
</html>