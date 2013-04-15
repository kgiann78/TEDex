<%-- 
    Document   : login
    Created on : Apr 7, 2013, 5:01:37 PM
    Author     : Constantine
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
    out.println(request.getParameter("username"));
    
    //search database with user with such username and password
    //if found then search for its role
    //if any found then create an array of roles
    
    if(request.getParameter("username").equalsIgnoreCase("test")) {
        String username = request.getParameter("username");
        session.setAttribute("username", username);
        ArrayList roleList = new ArrayList();
        roleList.add("Manager");
        roleList.add("Warehouse");
        session.setAttribute("roles", roleList);
        String redirectURL = "register";//"http://localhost:8080/TEDex/";
        response.sendRedirect(redirectURL);
    }

 %>