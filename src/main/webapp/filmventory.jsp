
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="filmventory"/>
<%@include file="includes/header.jsp" %>
<%@include file="includes/fbsdk.html" %>
<main class="w3-container">
    <div class="w3-card" style="max-width: 500px;">
        <h3 class="w3-green w3-padding-left w3-margin-0 w3-play">Log In To Filmventory</h3>
        <form action="SignIn" id="fblogin">
            <button class="w3-blue" form="fblogin" type="submit"><i class="fa fa-facebook"></i> | Log In With Facebook</button>
        </form>
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
