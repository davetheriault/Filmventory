
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Sign Up</h1>
        <div>
            <form method="POST" action="CreateUser">
                Username: <input type="text" name="username" /><br />
                Password: <input type="text" name="password" /><br />
                Confirm Password: <input type="password" name="confpass" />
                <br />
                <input type="submit" value="Submit" />
            </form>
        </div>
    </body>
</html>