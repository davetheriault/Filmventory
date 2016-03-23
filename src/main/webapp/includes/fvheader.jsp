
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (request.getSession().getAttribute("facebook") == null || request.getSession().getAttribute("facebook") == "") {
        if (request.getAttribute("title") != "filmventory" ) {
            response.sendRedirect("filmventory.jsp");
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
        <link rel="stylesheet" href="css/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lobster">
        <link href='https://fonts.googleapis.com/css?family=Play' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

    </head>
    <body>
        <header class="w3-topbar w3-red">
            <nav class="w3-play w3-xlarge">
                <ul class="w3-navbar">
                    <li class="w3-yellow w3-text-red"><a class="w3-text-red" href="filmventory.jsp">Filmventory</a></li>
                    <li><a href="">Options</a></li>

                </ul>
            </nav>
            <div class="w3-padding w3-red" style="position: fixed; bottom: 0; right: 0;"><a href="index.html">Back to Java</a></div>
        </header>
