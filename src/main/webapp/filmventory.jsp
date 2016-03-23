
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>
<main class="w3-container w3-play w3-center w3-padding">
    <div style="border: thin; display: inline-block; width: 400px;">
        <h3 class="w3-red w3-padding w3-margin-0 w3-play">Log In To Filmventory</h3>
        <div class="w3-container w3-center">
            <form action="SignIn" id="fblogin" class="w3-center w3-padding-large">
                <button class="w3-blue" form="fblogin" type="submit"><i class="fa fa-facebook"></i> | Log In With Facebook</button>
            </form>
        </div>
    </div>
    <br>
    <br>
    <div
        class="fb-like"
        data-share="true"
        data-width="300"
        data-show-faces="true">
    </div>
</main>
</body>
</html>
