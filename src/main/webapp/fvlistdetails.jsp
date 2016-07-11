
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="${listname} List | filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>
<%@include file="includes/sidenav.html" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<main class="w3-main w3-roboto" style="margin-left:200px">

    <div class="w3-card w3-margin">
        <h3 class="w3-red w3-padding-left w3-margin-0 w3-roboto">
            <em>${listname}</em> Movies</h3>

        <div class="w3-container w3-row w3-padding-0 w3-row">
            <div class="w3-container w3-padding w3-twothird" id="results">

                <c:forEach var="movie" items="${listmovies}">
                    <div class="w3-card w3-margin">
                        <ul class="w3-ul">
                            <li><strong><a href="/MovieDetails?title=${movie.title}&year=${movie.year}" >${movie.title}</a></strong> (${movie.year}) </li>
                        </ul>
                    </div>
                </c:forEach>
            </div>
            <div class="w3-third">
                    <div class="w3-container">
                        <ul class="w3-ul w3-dark-grey">
                            <li><a href="/RemoveList?name=${listname}" id="deleteList" data-title="${listname}" >Delete <em>${listname}</em> List</a></li>
                        </ul>
                    </div>
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
