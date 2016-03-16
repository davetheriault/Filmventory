<%-- 
    Document   : displayperson
    Created on : Mar 15, 2016, 10:40:10 PM
    Author     : Theriault
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${firstName} ${lastName}</title>
        
        <link rel="stylesheet" type="text/css" href="css/styles.css" />
    </head>
    <body>
        <h1>Displaying ${firstName} ${lastName}'s Information</h1>
        <div>
            <p>Birthday: ${birthday}</p>
        </div>
    </body>
</html>