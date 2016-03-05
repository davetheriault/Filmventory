<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Login</title> 
    </head> 
    <body> 
        <h1>Login Page</h1> 
        <center> 
            <h2>Sign in Details</h2> 
            ${message}<br>
            ${valid}<br>
            <c:forEach var="user" items="${users}" >
                ${user}<br>
            </c:forEach>
            <c:forEach var="pw" items="${pws}" >
                ${pw}<br>
            </c:forEach>
            <form action="Login" method="post"> 
                <br/>Username:<input type="text" name="username"> 
                <br/>Password:<input type="password" name="password"> 
                <br/><input type="submit" value="Submit"> 
            </form> 
        </center> 
    </body> 
</html>
