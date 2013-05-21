<%-- 
    Document   : users
    Created on : May 7, 2013, 10:38:57 AM
    Author     : Constantine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users Page</title>
    </head>
    <body>
        <h1>This is the user's <% out.print(session.getAttribute("username")); %> page!</h1>
    </body>
</html>
