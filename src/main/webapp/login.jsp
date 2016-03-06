

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" scope="request" value="Register"/>
<%@include file="includes/header.jsp" %>
<main class="w3-container">
    <h2 class="w3-amber w3-lobster">Login Page</h2> 
    <div class="w3-card">
        ${message}
        <form class="w3-form" action="Login" method="post"> 
            <br/>Username:<input class="w3-input" type="text" name="username"> 
            <br/>Password:<input class="w3-input" type="password" name="password"> 
            <br/><input type="submit" value="Submit"> 
        </form> 
        <a href="register.jsp">Register</a>
    </div>
</main>
</body> 
</html>
