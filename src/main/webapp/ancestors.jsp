
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="Ancestor Database"/>
<%@include file="includes/header.jsp" %>
<main class="w3-container">
    <div class="w3-card-2 w3-padding-top-0">

        <h2 class="w3-amber w3-padding-left w3-lobster">Ancestors</h2>
        <ul class="w3-ul w3-large">
            <c:forEach var="person" items="${people}">
                <li>
                    <a href="DisplayPerson?firstName=${person.firstName}&lastName=${person.lastName}&birthday=${person.birthday}&id=${person.id}">${person.firstName} ${person.lastName}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</main>
</body>
</html>