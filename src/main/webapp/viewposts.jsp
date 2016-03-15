
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" scope="request" value="View Posts"/>
<%@include file="includes/header.jsp" %>
<main class="w3-container">
    <div class="w3-card-2 w3-padding-top-0">
        <h2 class="w3-lobster w3-amber w3-padding">View Posts</h2>
        ${message}<br>
        Title: - Post: ${post} - User: ${user} - Year: ${year} - Month: ${month} - Day: ${day}<br>
        Hour: ${hour} - Min: ${min} - AM/PM: ${ampm}
        <div>

            Posts:<br /><br />

            <c:forEach var="post" items="${posts}">

                ${post.title}<br />${post.user}

            </c:forEach>
        </div>
    </div>
</main>
</body>
</html>
