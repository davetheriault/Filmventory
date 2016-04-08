
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="${movie.title} | filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>
<%@include file="includes/sidenav.html" %>

<main class="w3-main w3-play" style="margin-left:200px">

    <div class="w3-card-4 w3-margin">
        <h3 class="w3-red w3-padding-left w3-margin-0 w3-play">
            ${movie.title}</h3>
        <div class="w3-container">

            <div class="w3-card-2">
                <ul class="w3-ul">
                    <li> Title: <a href="/MovieDetails?title=${movie.title}&year=${movie.year}" >${movie.title}</a> </li>
                    <li> Year: ${movie.year} </li>
                    <li> Rated: ${movie.rated} </li>
                    <li> Released: ${movie.released} </li>
                    <li> Runtime: ${movie.runtime} </li>
                </ul>
            </div>


        </div>

    </div>
    <br>

</main>
</body>
</html>
