<% String title = "Register"; %>
<%@include file="includes/header.jsp" %>
        <h1>Sign Up</h1>
        <div>
            <form method="POST" action="CreateUser">
                Username: <input type="text" name="username" /><br />
                Password: <input type="password" name="password" /><br />
                Confirm Password: <input type="password" name="confpass" />
                <br />
                <input type="submit" value="Submit" />
            </form>
        </div>
    </body>
</html>