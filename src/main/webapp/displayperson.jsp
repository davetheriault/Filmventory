<%-- 
    Document   : displayperson
    Created on : Mar 15, 2016, 10:40:10 PM
    Author     : Theriault
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${firstName} ${lastName}</title>
        
        <link rel="stylesheet" type="text/css" href="css/styles.css" />
    </head>
    <body>
        <h1>Displaying ${firstName} ${lastName}'s Information</h1>
        <div>
            <p>Birthday: ${birthday}</p>
            <p><c:forEach var="parent" items="${parents}">
                    <c:choose>
                    <c:when test="${parent.gender == 'male'}" >
                        Father: ${parent.FirstName} ${parent.LastName} <br>
                    </c:when>
                    <c:otherwise>
                        Mother: ${parent.FirstName} ${parent.LastName} <br>
                    </c:otherwise>
                    </c:choose>
                </c:forEach></p>
        </div>
    </body>
</html>