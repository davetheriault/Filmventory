<%-- 
    Document   : scripturelist
    Created on : Mar 2, 2016, 8:51:09 PM
    Author     : Theriault
--%>
<%@ page import="java.util.List" %>

<%@ page import="java.util.ArrayList" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Scripture List</title>
    </head>
    <body>
        <h1>Scripture List</h1>


        <div>

            Scriptures:<br /><br />

            <%
                for (String scripture : 
                        (List<String>) request.getAttribute("scriptures")) {
                    out.println(scripture + "<br />");
                }
            %>
        </div>
    </body>
</html>
