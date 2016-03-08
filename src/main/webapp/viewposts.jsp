
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" scope="request" value="View Posts"/>
<%@include file="includes/header.jsp" %>
<main class="w3-container">
    <div class="w3-card-2 w3-padding-top-0">
        <h2 class="w3-lobster w3-amber w3-padding">View Posts</h2>
        ${message}<br>
        Title: ${title} - Post: ${post} - User: ${user} - Year: ${year} - Month: ${month} - Day: ${day}
    </div>
</main>
</body>
</html>
