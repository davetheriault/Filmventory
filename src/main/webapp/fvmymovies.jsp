
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="Movies | filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>
<%@include file="includes/sidenav.html" %>

<main class="w3-main w3-roboto" style="margin-left:200px">

    <div class="w3-card w3-margin">
        <h3 class="w3-red w3-padding-left w3-margin-0 w3-roboto">
            <% out.print(session.getAttribute("fname"));%>&apos;s Movies</h3>
        <ul class="w3-navbar w3-dark-grey">
            <li class="w3-right w3-padding">
                <select class="w3-select w3-dark-grey">
                    <option disabled selected>Sort By </option>
                    <option>A-Z</option>
                    <option>Date</option>
                </select>
            </li>
            <li class="w3-right w3-padding">
                <select class="w3-select w3-dark-grey">
                    <option disabled selected>Filter By </option>
                    <option>Genre</option>
                    <option></option>
                </select>
            </li>
        </ul>
        <div class="w3-container w3-padding">

            <c:forEach var="movie" items="${movies}">
                <div class="w3-card w3-margin">
                    <ul class="w3-ul">
                        <li><strong><a href="/MovieDetails?title=${movie.title}&year=${movie.year}" >${movie.title}</a></strong> (${movie.year}) </li>
                    </ul>
                </div>
            </c:forEach>


        </div>

    </div>
    <br>

</main>
</body>
</html>
