

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" scope="request" value="Register"/>
<%@include file="includes/header.jsp" %>
<main class="w3-container">
    <div class="w3-card w3-padding-top-0">
    <h2 class="w3-lobster w3-amber w3-padding">Register</h2>
    ${message}
        <form class="w3-form" method="POST" action="CreateUser">
            Username: <input class="w3-input" type="text" name="username" />
            Password: <input class="w3-input" type="password" name="password" />
            Confirm Password: <input class="w3-input" type="password" name="confpass" />
            <input type="submit" value="Submit" />
        </form>
    </div>
</main>
</body>
</html>