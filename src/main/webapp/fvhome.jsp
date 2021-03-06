
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="Home | filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>


<main class="w3-main w3-roboto" >

    <div class="w3-card w3-margin">
        <h3 class="w3-red w3-padding-left w3-margin-0 w3-roboto">
            Welcome <% out.print(session.getAttribute("fname"));%> To Filmventory</h3>
        <div class="w3-container w3-padding">
            <p>Welcome to Filmventory! This application allows you to create your personal inventory list of
                movies that you own. Simply select 'Add Movies' to search for titles to add to your inventory. 
                Select 'My Movies' to view your list. </p>
            <div
                class="w3-card w3-padding w3-center fb-like"
                data-share="true"
                data-width="100"
                data-show-faces="true">
            </div>
        </div>
    </div>
    <br>

</main>
</body>
</html>
