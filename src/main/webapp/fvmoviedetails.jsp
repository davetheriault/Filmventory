
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="${movie.title} | filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>
<%@include file="includes/sidenav.html" %>

<main class="w3-main w3-roboto" style="margin-left:200px">

    <div class="w3-card w3-margin">
        <h3 class="w3-red w3-padding-left w3-margin-0 w3-roboto">
            ${movie.title}</h3>
        <div class="w3-container w3-padding">

            <div class="w3-container w3-row">
                <div class="w3-col m8 l8">
                    <ul class="w3-ul">
                        <li> 
                            <div class="w3-row">
                                <div class="w3-half w3-center">
                                    <img style="width: 100%; height: auto; max-width: 200px;" src="/Poster?id=${movie.id}" />
                                </div>
                                <ul class="w3-ul w3-half">
                                    <li> <strong>Title:</strong><br> <a href="/MovieDetails?title=${movie.title}&year=${movie.year}" >${movie.title}</a> </li>
                                    <li> <strong>Rated:</strong><br> ${movie.rated} </li>
                                    <li> <strong>Released:</strong><br> ${movie.released} </li>
                                    <li> <strong>Runtime:</strong><br> ${movie.runtime} </li>
                                    <li> <strong>Metascore:</strong><br> ${movie.metascore} </li>
                                </ul>
                            </div>
                        </li>

                        <li> <strong>Plot:</strong><br> ${movie.plot} </li>

                        <li> <strong>Director:</strong><br> 
                            <c:forEach var="dir" items="${movie.director}">
                                &nbsp;<a href="">${dir}</a>&nbsp;
                            </c:forEach> 
                        </li>

                        <li> <strong>Writer:</strong><br> 
                            <c:forEach var="wri" items="${movie.writer}">
                                &nbsp;<a href="">${wri}</a>&nbsp;
                            </c:forEach> 
                        </li>

                        <li> <strong>Actors:</strong><br> 
                            <c:forEach var="act" items="${movie.actors}">
                                &nbsp;<a href="">${act}</a>&nbsp;
                            </c:forEach> </li>

                        <li> <strong>Genre:</strong><br> 
                            <c:forEach var="genre" items="${movie.genre}">
                                &nbsp;<a href="">${genre}</a>&nbsp;
                            </c:forEach> 
                        </li>

                        <li> <strong>Language:</strong><br> ${movie.language} </li>
                        <li> <strong>Country:</strong><br> ${movie.country} </li>
                    </ul>
                </div>
                <div class="w3-col m4 l4">
                    <div class="w3-container">
                        <ul class="w3-ul w3-dark-grey">
                            <li><a href="#" id="removeClick" data-title="${movie.title}" data-year="${movie.year}" >Remove from collection</a></li>
                            <li><a href="#" id="add2list">Add to list</a></li>
                            <li class="lists hidden w3-grey" id="newli"><a href="" id="newList"><i class="fa fa-plus"></i> Create New List</a></li>
                            <li class="nlist hidden w3-light-grey w3-padding-0">
                                <form id="newListForm" action="/AddList" method="get">
                                    <input type="hidden" name="title" value="${movie.title}" />
                                    <input type="hidden" name="year" value="${movie.year}" />
                                    <input class="w3-input w3-light-grey" type="text" name="listname" id="listname" placeholder="List Name..." required />
                                    <input class="w3-input w3-text-black" type="submit" value="Create List" form="newListForm"/>
                                </form>
                            </li>
                            <c:forEach var="list" items="${lists}">
                                <c:url var="url" value="/AddList?listname=${list.name}&title=${movie.title}&year=${movie.year}" />
                                <li class="lists hidden w3-grey">
                                    <a href="${url}"><i class="fa fa-plus"></i> ${list.name}</a>
                                </li>
                            </c:forEach>

                        </ul>
                    </div>    
                </div>
            </div>

        </div>

    </div>
    <br>

</main>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        $("#add2list").click(function (event) {
            event.preventDefault();
            $(".lists").toggleClass("hidden");
            if (!$(".nlist").hasClass("hidden")) {
                $(".nlist").toggleClass("hidden");
            }
        });

        $("#removeClick").click(function (event) {
            event.preventDefault();
            var c = confirm("Remove this movie from your collection?");
            if (c) {
                var t = $("#removeClick").attr("data-title");
                var t2 = encodeURI(t);
                var y = $("#removeClick").attr("data-year");
                var y2 = encodeURI(y);
                var link = "/RemoveMovie?title=" + t2 + "&year=" + y2;
                window.location = link;
            }
        });

        $("#newList").click(function (event) {
            event.preventDefault();
            $(".nlist").toggleClass("hidden");
        });
    });
</script>
</html>
