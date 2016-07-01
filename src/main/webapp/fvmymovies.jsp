
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="Movies | filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>
<%@include file="includes/sidenav.html" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<main class="w3-main w3-roboto" style="margin-left:200px">

    <div class="w3-card w3-margin">
        <h3 class="w3-red w3-padding-left w3-margin-0 w3-roboto">
            <% out.print(session.getAttribute("fname"));%>&apos;s Movies</h3>
        <ul class="w3-navbar w3-dark-grey">

            <li class="w3-right w3-padding">
                <form id="bygenre" action="MyMovies" method="get">
                    <input value="${movies}" type="hidden" name="movies" />
                    <div class="w3-row">
                        <div class="w3-col"><label class="w3-label" for="genreList">Genre:</label></div>
                        <div class="w3-col">
                            <select id="genreList" name="genre" class="w3-select w3-dark-grey">
                                <option selected>All </option>
                                <c:forEach var="mov" items="${movies}">
                                    <c:forEach var="genre" items="${mov.genre}">
                                        <option value="${genre}">${genre}</option>
                                    </c:forEach>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </form>
            </li>
            <li class="w3-right w3-padding">
                <form id="sortby" action="MyMovies" method="get">
                    <input value="${movies}" type="hidden" name="movies" />
                    <div class="w3-row">
                        <label class="w3-label w3-col" for="sortList">Sort By:</label>
                        <select name="sort" class="w3-select w3-dark-grey w3-col" id="sortList" >

                            <option selected value="az">A to Z &nbsp;&nbsp; &uarr;</option>
                            <option value="za">Z to A &nbsp;&nbsp; &darr;</option>
                            <option value="y09">Date &nbsp;&nbsp;&nbsp;&nbsp; &uarr;</option>
                            <option value="y90">Date &nbsp;&nbsp;&nbsp;&nbsp; &darr;</option>
                        </select>
                    </div>
                </form>
            </li>


        </ul>
        <div class="w3-container w3-padding" id="results">


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
