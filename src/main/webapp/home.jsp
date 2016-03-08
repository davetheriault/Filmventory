

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" scope="request" value="Welcome"/>
<%@include file="includes/header.jsp" %>
<main class="w3-container">
    <div class="w3-card-2 w3-padding-top-0">
        <h2 class="w3-lobster w3-amber w3-padding">Welcome ${username}!</h2>
        ${message}
        <div class="w3-card w3-padding w3-red">
            <h3>
                <a href="viewposts.jsp">View Posts</a>
            </h3>
        </div>
        <div class="w3-card">
            <h3 class="w3-red w3-padding">Create New Post</h3>
        <form class="w3-form" method="POST" action="CreatePost">
            Title: <input class="w3-input" type="text" name="title" />
            Post: <textarea class="w3-input" rows="5" cols="90" name="post" ></textarea>
            <input class="w3-input" type="submit" value="Post" />
        </form>
        </div>
        <form class="w3-form" action="Logout" method="post">
            <input class="w3-input" type="submit" value="Log Out">
        </form>
    </div>
</main>
</body>
</html>
