
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="${movie.title} | filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>
<%@include file="includes/sidenav.html" %>

<main class="w3-main w3-roboto" style="margin-left:200px">

    <div class="w3-card-4 w3-margin">
        <h3 class="w3-red w3-padding-left w3-margin-0 w3-roboto">
            ${movie.title}</h3>
        <div class="w3-container w3-padding">

            <div class="w3-container w3-row">
                <div class="w3-col m8 l8">
                    <ul class="w3-ul">
                        <li> Title: <a href="/MovieDetails?title=${movie.title}&year=${movie.year}" >${movie.title}</a> </li>
                        <li> Year: ${movie.year} </li>
                        <li> Rated: ${movie.rated} </li>
                        <li> Released: ${movie.released} </li>
                        <li> Runtime: ${movie.runtime} </li>
                        <li> Genre: ${movie.genre} </li>
                        <li> Director: ${movie.director} </li>
                        <li> Writer: ${movie.writer} </li>
                        <li> Actors: ${movie.actors} </li>
                        <li> Plot: ${movie.plot} </li>
                        <li> Language: ${movie.language} </li>
                        <li> Country: ${movie.country} </li>
                        <li> Metascore: ${movie.metascore} </li>
                    </ul>
                </div>
                <div class="w3-col m4 l4">
                    <div class="w3-container">
                        Remove from collection 
                    </div>    
                </div>
            </div>


        </div>

    </div>
    <br>

</main>
</body>
</html>
