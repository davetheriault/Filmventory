
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="title" scope="request" value="filmventory"/>
<%@include file="includes/fvheader.jsp" %>
<%@include file="includes/fbsdk.html" %>
<main class="w3-container w3-roboto w3-center w3-padding" style="margin-left: 0;">
    <div class="w3-half" style="border: grey thin solid; display: inline-block;">
        <h3 class="w3-red w3-padding w3-margin-0 w3-roboto">Log In To Filmventory</h3>
        <div class="w3-container w3-center w3-padding-0">
            <form action="SignIn" id="fblogin" class="w3-center">
                <button class="w3-blue w3-large w3-padding" form="fblogin" type="submit" style="width: 100%; height: 100%;"><i class="w3-xlarge fa fa-facebook"></i> &nbsp;&nbsp; | &nbsp; Log In With Facebook</button>
            </form>
        </div>
    </div>
    <br>
    <br>
    <div
        class="fb-like"
        data-share="true"
        data-width="100"
        data-show-faces="true">
    </div>
</main>
</body>
</html>
