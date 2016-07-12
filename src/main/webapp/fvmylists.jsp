
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="Lists | filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<main class="w3-main w3-roboto" >

    <div class="w3-card w3-margin">
        <h3 class="w3-red w3-padding-left w3-margin-0 w3-roboto">
            <% out.print(session.getAttribute("fname"));%>&apos;s Lists</h3>

        <div class="w3-container w3-row w3-padding-0 w3-row">
            <div class="w3-container w3-padding w3-col m7 l7" id="results">

                <c:forEach var="list" items="${lists}">
                    <div class="w3-card w3-margin">
                        <ul class="w3-ul w3-navbar w3-light-grey">
                            <li class="w3-xlarge w3-padding-0 w3-center" style="width: 100%;"><strong><a href="/ListDetails?listname=${list.name}&user=${list.user_id}" style="width: 100%;" >${list.name}</a></strong> </li>
                        </ul>
                    </div>
                </c:forEach>
            </div>
            <div class="w3-container w3-padding w3-col m5 l5">
                <div class="w3-container">
                    <ul class="w3-ul w3-margin w3-dark-grey w3-large">

                        <li class="lists w3-grey" id="newli"><a href="" id="newList"><i class="fa fa-plus"></i> Create New List</a></li>
                        <li class="nlist hidden w3-light-grey w3-padding-0">
                            <form id="newListForm" action="/AddList" method="get">
                                <input type="hidden" name="page" value="mylists"/>
                                <input class="w3-input w3-light-grey" type="text" name="listname" id="listname" placeholder="List Name..." required />
                                <input class="w3-input w3-text-black" type="submit" value="Create List" form="newListForm"/>
                            </form>
                        </li>


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
        $("#newList").click(function (event) {
            event.preventDefault();
            $(".nlist").toggleClass("hidden");
        });
    });
    
</script>
</html>
