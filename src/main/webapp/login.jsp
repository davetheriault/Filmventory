

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" scope="request" value="Register"/>
<%@include file="includes/header.jsp" %>
<main class="w3-container">
    <div class="w3-card">
        <h2 class="w3-amber w3-lobster w3-padding">Login Page</h2> 
        
        ${message}<br>
        Hashed Form PW: ${cipher}<br>
        Hashed PW 2string: ${cipherString}<br>
        PW: ${pw}
        <form class="w3-form" action="Login" method="post"> 
            Username:<input class="w3-input" type="text" name="username"> 
            Password:<input class="w3-input" type="password" name="password"> 
            <input type="submit" value="Submit"> 
        </form> 
        <a href="register.jsp">Register</a>
    </div>
</main>
</body> 
</html>
