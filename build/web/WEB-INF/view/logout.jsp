<%-- 
    Document   : logout
    Created on : May 7, 2013, 11:36:40 AM
    Author     : Constantine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("username") != null)
        session.setAttribute("username", null);
    if(session.getAttribute("roles") != null)
        session.setAttribute("roles", null);
    
    response.sendRedirect("http://localhost:8080/TEDex/");
%>