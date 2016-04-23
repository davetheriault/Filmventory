
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (request.getSession().getAttribute("facebook") == null || request.getSession().getAttribute("facebook") == "") {
        if (request.getAttribute("title") != "filmventory") {
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
        <link href='https://fonts.googleapis.com/css?family=Paytone+One' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Play' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

    </head>
    <body>
        <header class="w3-topbar w3-red">
            <nav class="w3-roboto w3-xlarge">
                <ul class="w3-navbar">
                    <li class="w3-white"><a href="filmventory.jsp"><img src="images/filmventory300.png" width="275" alt="filmventory home" /></a></li>
                    <li class="w3-medium w3-padding w3-bottom"><em>The Movie Inventory Web App</em></li>

                </ul>
            </nav>
            <div class="w3-padding w3-red" style="position: fixed; bottom: 0; right: 0;"><a href="index.html">Back to Java</a></div>
        </header>
