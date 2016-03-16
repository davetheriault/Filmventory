<%-- 
    Document   : ancestors
    Created on : Mar 15, 2016, 9:28:57 PM
    Author     : Theriault
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ancestors</title>

        <link rel="stylesheet" type="text/css" href="css/styles.css" />
    </head>
    <body>
        <h1>Ancestors</h1>
        <div>
            <c:forEach var="person" items="${people}">
                <p>
                    <a href="DisplayPerson?firstName=${person.firstName}&lastName=${person.lastName}&birthday=${person.birthday}&id=${person.id}">${person.firstName} - ${person.lastName}</a>
                </p>
            </c:forEach>
        </div>
    </body>
</html>