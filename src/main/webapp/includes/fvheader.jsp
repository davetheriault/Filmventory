
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
        <link href='https://fonts.googleapis.com/css?family=Play' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

    </head>
    <body>
        <header class="w3-topbar w3-red">
            <nav class="w3-play w3-xlarge">
                <ul class="w3-navbar">
                    <li class="w3-yellow w3-text-red">
                        <span class="w3-opennav w3-hide-large w3-xlarge" onclick="w3_open()"><i class="fa fa-bars"></i></span>
                        <a class="w3-text-red" href="filmventory.jsp" style="margin-left: 50px;">Filmventory</a>
                    </li>
                    <li><a href="">Options</a></li>

                </ul>
            </nav>
            <div class="w3-padding w3-red" style="position: fixed; bottom: 0; right: 0;"><a href="index.html">Back to Java</a></div>
        </header>

        <div class="w3-sidenav w3-collapse w3-black w3-card-2 w3-animate-left">
            <a href="#" onclick="w3_close()" class="w3-closenav w3-hide-large">Close X</a> 
            <a href="mymovies.jsp">My Movies</a>
            <a href="addmovies.jsp">Add Movies</a>
            <a href="/LogOut">Log Out</a>
        </div>


        <script>
            function w3_open() {
                document.getElementsByClassName("w3-sidenav")[0].style.display = "block";
            }
            function w3_close() {
                document.getElementsByClassName("w3-sidenav")[0].style.display = "none";
            }
        </script>