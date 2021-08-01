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

		<table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Usuario</th>
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
                   
                   </tr>
                    
                </c:forEach>
            </tbody>
        </table>




</body>
</html>