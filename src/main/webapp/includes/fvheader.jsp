<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% if (request.getSession().getAttribute("facebook") == null || request.getSession().getAttribute("facebook") == "") {
        if (request.getAttribute("title") != "filmventory") {
            response.sendRedirect("filmventory.jsp");
        }
    } else {
        if (request.getAttribute("title") == "filmventory") {
            response.sendRedirect("fvhome.jsp");
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
        <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Play' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    </head>
    <body style="background-color: #1a1a1a">
        <c:if scope="session" test="${title != 'filmventory'}" >
            <%@include file="sidenav.html" %>
        </c:if>

        <header class="w3-topbar w3-red">
            <nav class="w3-roboto w3-xlarge">
                <ul class="w3-navbar">
                    <li class="w3-white"><a href="filmventory.jsp" style="padding: 8px 13px"><img src="images/filmventory300.png" width="274" alt="filmventory home" /></a></li>
                    <li class="vertcenter" style="padding: 8px 16px; line-height: 42px;">The Movie Inventory Web App</li>

                </ul>
            </nav>
        </header>
