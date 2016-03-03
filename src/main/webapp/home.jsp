<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>Success</title> 
    </head> 
    <body> 
    <br/><br/><br/><br/><br/> 
    <center> 
        <h2> <% String a=session.getAttribute("username").toString(); out.println("Hello "+a); %> </h2> 
        <br/> <br/> <br/><br/><br/><br/><br/> 
        <form action="Logout" method="post">
            <input type="submit" value="Log Out">
        </form>
    </center>  
</body> 
</html>