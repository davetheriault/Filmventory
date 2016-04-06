
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="Home | filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>
<main class="w3-container w3-play w3-center">
    <div class="w3-card-4 w3-margin">
        <h3 class="w3-red w3-padding-left w3-margin-0 w3-play">
            Welcome <% out.print(session.getAttribute("fname")); %> To Filmventory</h3>
        <div class="w3-container w3-center">

            <%@include file="includes/sidenav.html" %>
            
        </div>
    </div>
    <br>
    <div
        class="fb-like"
        data-share="true"
        data-width="450"
        data-show-faces="true">
    </div>
</main>
</body>
</html>
