<%-- 
    Document   : corfirm
    Created on : May 21, 2013, 4:58:38 PM
    Author     : Constantine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getParameter("username") != null) {
%>
        <h1>Επιβεβαίωση Εγγραφής</h1>
        <br><br>
        <h3><p class="lead">Συγχαρητήρια, <% out.println(request.getParameter("username").toString()); %>! Η αίτησή σου έχει αποσταλεί στον andministrator για έγκριση!</p></h3>
<%
    } 
    else {
%> 
    kati allo

<%
    }
%>