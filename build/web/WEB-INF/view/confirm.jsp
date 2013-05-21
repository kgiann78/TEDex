<%-- 
    Document   : corfirm
    Created on : May 21, 2013, 4:58:38 PM
    Author     : Constantine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>Hello confirm!</h1>
<%
    if (request.getParameter("username") != null)
        out.println(request.getParameter("username").toString()); 
    else
        response.sendRedirect("/TEDex");
%>