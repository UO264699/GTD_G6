<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>
</head>
<body>

	<h1>Bienvenido a la p�gina principal</h1>


	<p>La hora actual del servidor es ${serverTime}</p>

    

	<form action="categories/add" method="post">

         <input type="text" name ="name"/>
         <input type="submit" value ="A�adir categor�a"/>

</form>
<form action="add/2/2"
method="post">





<input type="text"
name ="title"/>


<input type="submit"
value ="A�adir Tarea"/>





</form>


</body>
</html>