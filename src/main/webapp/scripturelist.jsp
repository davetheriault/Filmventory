<%-- 
    Document   : scripturelist
    Created on : Mar 2, 2016, 8:51:09 PM
    Author     : Theriault
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 


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

            <c:forEach var="scripture" items="${scriptures}">

                ${scripture}<br />

            </c:forEach>
        </div>
    </body>
</html>
