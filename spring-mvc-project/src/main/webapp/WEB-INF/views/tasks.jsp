<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de tareas</title>
</head>
<body>

<h1>Lista de Tareas</h1>

		<table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Comentarios</th>
                    <th>Fecha de creación</th>
                    <th>Fecha de fin</th>
                    <th>Fecha planeada</th>
                   
                </tr>
            </thead>
            <tbody>
                <c:forEach var="task" items="${finishedTasks}" >
                    
                   <tr>
                   
                   		 <td>
            				<c:out value="${task.id}" />
        				</td>
        				
        				<td>
            				<c:out value="${task.title}" />
        				</td>
        				
        				<td>
            				<c:out value="${task.comments}" />
        				</td>
        				
        				<td>
            				<c:out value="${task.created}" />
        				</td>
        				
        				
        				<td>
        			
    						<c:out value="${task.finished}"/>
            		
        				</td>
        			
        				<td>
            				<c:out value="${task.planned}" />
        				</td>
        				
        				
        				
						
        				<td>
        					<c:if test="${task.finished == null}">
            					<a href=finish/${task.id} >Finalizar</a>
            				</c:if>
        				</td>
        				
        				
               
                   </tr>
                    
                </c:forEach>
            </tbody>
        </table>
        
        <form action="add/2/2" method="post">

         <input type="text" name ="title"/>
         <input type="submit" value ="Añadir Tarea"/>
         
		</form>



</body>
</html>