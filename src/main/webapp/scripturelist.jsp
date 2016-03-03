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

        <%
            List<String> scriptures = new ArrayList<String>();
            scriptures.add("Proverbs 3:5");
            scriptures.add("Doc. &amp; Cov. 112:10");
            scriptures.add("John 7:17");
        %>

        <div>

            Scriptures:<br /><br />

            <%
                for (String scripture : scriptures) {
                    out.println(scripture + "<br />");
                }

            %>
        </div>
    </body>
</html>
