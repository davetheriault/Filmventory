

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" scope="request" value="Register"/>
<%@include file="includes/header.jsp" %>
<main class="w3-container">
    <h2 class="w3-lobster w3-amber"></h2>
    <div>
        <form class="w3-form" method="POST" action="CreateUser">
            Username: <input class="w3-input" type="text" name="username" /><br />
            Password: <input class="w3-input" type="password" name="password" /><br />
            Confirm Password: <input class="w3-input" type="password" name="confpass" />
            <br />
            <input type="submit" value="Submit" />
        </form>
    </div>
</main>
</body>
</html>