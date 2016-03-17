
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="Ancestor Database"/>
<%@include file="includes/header.jsp" %>
<main class="w3-container">
    <h3 class="w3-lobster w3-padding-left w3-aqua">${firstName} ${lastName}</h3>
    <ul class="w3-ul">
        <li>Birthday: ${birthday}</li>
        <c:forEach var="parent" items="${parents}">
                <c:choose>
                    <c:when test="${parent.gender == 'male'}" >
                        <li>Father: <a href="DisplayPerson?firstName=${parent.firstName}&lastName=${parent.lastName}&birthday=${parent.birthday}&id=${parent.id}">${parent.firstName} ${parent.lastName}</a> </li>
                    </c:when>
                    <c:otherwise>
                        <li>Mother: <a href="DisplayPerson?firstName=${parent.firstName}&lastName=${parent.lastName}&birthday=${parent.birthday}&id=${parent.id}">${parent.firstName} ${parent.lastName}</a> </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        <li>Children:</li>
        <ul class="w3-ul">
            <c:forEach var="kid" items="${kids}">
                <li><a href="DisplayPerson?firstName=${kid.firstName}&lastName=${kid.lastName}&birthday=${kid.birthday}&id=${kid.id}">${kid.firstName} ${kid.lastName}</a> Born: ${kid.birthday} </li>
            </c:forEach>
        </ul>
    </ul>
</main>
</body>
</html>