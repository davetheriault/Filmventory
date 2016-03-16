
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" scope="request" value="View Posts"/>
<%@include file="includes/header.jsp" %>
<main class="w3-container">
    <div class="w3-card-2 w3-padding-top-0">
        <h2 class="w3-lobster w3-amber w3-padding">View Posts</h2>
        ${message}
        <div>

            Posts:<br /><br />

            <c:forEach var="post" items="${posts}">

                <div class="w3-card-2" >
                    <h3 class="w3-blue w3-padding w3-lobster" style="display: inline; width: 100%;"> &quot;${post.title}&quot; </h3>
                    <div class="w3-padding w3-large">
                        <div class="w3-leftbar w3-padding">
                        ${post.post}
                        </div>
                        <p class="w3-small">Posted by: ${post.user} ${post.month} ${post.day}, ${post.year} - ${post.hour}:<c:if test="${post.min < 10}" >0</c:if>${post.min} ${post.ampm} </p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>
</body>
</html>
