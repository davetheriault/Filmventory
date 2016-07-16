
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="${listname} List | filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<main class="w3-main w3-roboto" >

    <div class="w3-card w3-margin">
        <h3 class="w3-red w3-padding-left w3-margin-0 w3-roboto">
            <em>${listname}</em>&nbsp; Movies</h3>

        <div class="w3-container w3-row w3-padding-0 w3-row">
            <div class="w3-container w3-padding w3-twothird" id="results">

                <c:forEach var="movie" items="${listmovies}">
                    <div class="w3-card w3-margin">
                        <ul class="w3-ul">
                            <li class="w3-padding"><strong><a href="/MovieDetails?title=${movie.title}&year=${movie.year}" >${movie.title}</a></strong> (${movie.year}) 
                                <a href="#" class="w3-right"><i class="fa fa-trash"></i></a></li>
                        </ul>
                    </div>
                </c:forEach>
            </div>
            <div class="w3-third">
                <div class="w3-container w3-padding">
                    <ul class="w3-ul w3-dark-grey w3-margin">
                        <li><a href="#" id="deleteList" data-title="${listname}" ><i class="fa fa-trash"></i>&nbsp; Delete <em>${listname}</em>&nbsp; List</a></li>
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
        $("#deleteList").click(function (event) {
            event.preventDefault();
            var t = $("#removeClick").attr("data-title");
            var c = confirm("Delete " + t + " List? Are you sure?");
            if (c) {
                var t2 = encodeURI(t);
                var link = "/RemoveList?name=" + t2 ;
                window.location = link;
            }
        });
    });



</script>
</html>
