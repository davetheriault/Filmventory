

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" scope="request" value="Register"/>
<%@include file="includes/header.jsp" %>
<main class="w3-container">
    <div class="w3-card-2 w3-padding-top-0">
        <h2 class="w3-amber w3-lobster w3-padding">Login Page</h2> 
        ${message}
        <form class="w3-form" action="Login" method="post"> 
            Username:<input class="w3-input" type="text" name="username"> 
            Password:<input class="w3-input" type="password" name="password"> 
            <input type="submit" value="Submit"> 
        </form> 
        <div class="w3-padding w3-text-blue w3-lobster w3-large"><a href="register.jsp">Register</a></div>
    </div>
</main>
</body> 
</html>
