<%-- 
    Document   : register
    Created on : Apr 7, 2013, 5:09:43 PM
    Author     : Constantine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("username") != null) {
        out.println("<h1>Hello " + session.getAttribute("username") + "!</h1>");
    }
%>
