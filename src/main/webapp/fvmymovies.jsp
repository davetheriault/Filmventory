
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="Movies | filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<main class="w3-main w3-roboto" >

    <div class="w3-card w3-margin">
        <h3 class="w3-red w3-padding-left w3-margin-0 w3-roboto">
            <% out.print(session.getAttribute("fname"));%>&apos;s Movies ${genr}</h3>

        <div class="w3-container w3-row w3-padding w3-row">
            <div class="w3-container w3-twothird" id="results">

                <c:forEach var="movie" items="${movies}">
                    <div class="w3-card w3-margin">
                        <ul class="w3-ul">
                            <li><strong><a href="/MovieDetails?title=${movie.title}&year=${movie.year}" >${movie.title}</a></strong> (${movie.year}) </li>
                        </ul>
                    </div>
                </c:forEach>
            </div>
            <div class="w3-third">
                <ul class="w3-ul">

                    <li class="w3-padding">
                        <form id="bygenre" action="MyMovies" method="get">
                            <input value="${movies}" type="hidden" name="movies" />

                            <label class="w3-label w3-text-red" for="genreList" style="display: inline-block">Genre:</label>

                            <select id="genreList" name="genre" class="w3-select w3-dark-grey" style="display: inline-block; width: 100%;">
                                <c:choose>
                                    <c:when test="${empty genr}">
                                        <option selected>All </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option>All</option>
                                    </c:otherwise>
                                </c:choose>

                                <c:forEach var="genre" items="${genres}">
                                    <c:choose>
                                        <c:when test="${genre == genr}" >
                                            <option value="${genre}" selected>${genre}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${genre}" >${genre}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                            </select>

                        </form>
                    </li>
                    <li class="w3-padding">
                        <form id="sortby" action="MyMovies" method="get">
                            <input value="${movies}" type="hidden" name="movies" />

                            <label class="w3-label w3-text-red" for="sortList" style="display: inline-block">Sort By:</label>
                            <select name="sort" class="w3-select w3-dark-grey" id="sortList" style="display: inline-block; width: 100%;">

                                <option selected value="az">A to Z &nbsp;&nbsp; &uarr;</option>
                                <option value="za">Z to A &nbsp;&nbsp; &darr;</option>
                                <option value="y09">Date &nbsp;&nbsp;&nbsp;&nbsp; &uarr;</option>
                                <option value="y90">Date &nbsp;&nbsp;&nbsp;&nbsp; &darr;</option>
                            </select>

                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <br>

</main>
</body>

<script type="text/javascript">
    $(document).ready(function () {
        $("#genreList").change(function () {
            if ($(this).val() !== null) {
                sort();
            }
        });
        $("#sortList").change(function () {
            if ($(this).val() !== null) {
                sort();
            }
        });

        sort();
    });
    function sort() {
        var genre = $("#genreList").val();
        var sort = $("#sortList").val();

        if (window.XMLHttpRequest) {
            // code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        xmlhttp.open("GET", "SortGenre?genre=" + genre + "&sort=" + sort, true);

        xmlhttp.send();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4) {
                var inner = document.getElementById("results");
                inner.innerHTML = xmlhttp.responseText;

            }
        };

    }
</script>
</html>
