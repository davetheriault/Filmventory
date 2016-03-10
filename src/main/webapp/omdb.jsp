<%-- 
    Document   : omdp
    Created on : Mar 8, 2016, 9:28:45 PM
    Author     : Theriault
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OMDB Search</title>
    </head>
    <body>
        <h1>OMDB Search</h1>
        <form action="MovieSearch2" method="get" id="moviesearch">
            <input type="text" name="title" placeholder="Search Movies by Title..." required />
            <input type="submit" value="Search" form="moviesearch" name="movSubmit" />
        </form>
    </body>
</html>
