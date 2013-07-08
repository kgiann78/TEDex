<%-- 
    Document   : users
    Created on : May 7, 2013, 10:38:57 AM
    Author     : Constantine
--%>

<%@page import="entities.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (sessionUsername.isEmpty()) {
        response.sendRedirect("/TEDex");
    }

    if (request.getAttribute("users") != null) {
        ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
    }
%>

<h1>Διαχείριση Χρηστών</h1>
<br>
<table id="usersTable" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" width="70%" >
    <thead>
        <tr>
            <th><strong>Κωδικός</strong></th>
            <th><strong>Username</strong></th>
            <th><strong>Όνομα Χρήστη</strong></th>
            <th><strong>Επίθετο</strong></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.getId()}"/></td>
                <td><c:out value="${user.getUsername()}"/></td>
                <td><c:out value="${user.getName()}"/></td>
                <td><c:out value="${user.getSurname()}"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="registration?source=<%=Service.USER.toString()%>" class="btn btn-primary">Προσθήκη νέου χρήστη »</a>
    <!--<a href="<%=Service.USER.toString()%>?source=<%=Service.USER.toString()%>&amp;personal=" class="btn btn-primary">Προσθήκη νέου χρήστη »</a>-->

