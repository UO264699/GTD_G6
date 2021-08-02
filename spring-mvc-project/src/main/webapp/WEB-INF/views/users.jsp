<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de usuarios</title>
</head>
<body>

		<h1>Lista de Usuarios</h1>

		<table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Usuario</th>
                    <th>¿Es admin?</th>
                    <th>Estado</th>
                   
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}" >
                    
                   <tr>
                   
                   		 <td>
            				<c:out value="${user.id}" />
        				</td>
        				
        				<td>
            				<c:out value="${user.email}" />
        				</td>
        				
        				<td>
            				<c:out value="${user.login}" />
        				</td>
        				
        				
        				<td>
        			
    						<c:out value="${user.getAdmin()}"/>
            		
        				</td>
        			
        				<td>
            				<c:out value="${user.getStatus()}" />
        				</td>
        				
        				<td>
            				<a href=delete/${user.id} >Borrar</a>
        				</td>
        				
        				<td>
        				
        					<c:if test="${user.getStatus() == 'ENABLED'}">
									<a href=changeStatus/${user.id} >Bloquear</a>
							</c:if> 
							
							<c:if test="${user.getStatus() == 'DISABLED'}">
									<a href=changeStatus/${user.id} >Activar</a>
							</c:if> 
        				
            				
        				</td>
                   
                   </tr>
                    
                </c:forEach>
            </tbody>
        </table>




</body>
</html>