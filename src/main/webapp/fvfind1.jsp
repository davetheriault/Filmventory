<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="Search Results | filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>


<main class="w3-main w3-roboto" >

    <div class="w3-card w3-margin">
        <h3 class="w3-red w3-padding-left w3-margin-0 w3-roboto">
            Results for &quot;${query}&quot;</h3>
        <div class="w3-container">

            ${results}

        </div></div></main>
</body>
</html>