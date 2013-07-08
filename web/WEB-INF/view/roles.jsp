<%-- 
    Document   : roles
    Created on : May 7, 2013, 10:42:31 AM
    Author     : Constantine
--%>
<%@page import="entities.Role"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
if (sessionUsername.isEmpty()) {
        response.sendRedirect("/TEDex");
    }
else if (request.getAttribute("roles") != null) {
        ArrayList<Role> roles = (ArrayList<Role>) request.getAttribute("roles");
    }
    %>

<h1>Διαχείριση Ρόλων</h1>
<br>
<table id="rolesTable" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" width="70%" >
    <thead>
        <tr>
            <th><strong>Κωδικός</strong></th>
            <th><strong>Όνομα Ρόλου</strong></th>
            <th><strong>Περιγραφή</strong></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="role" items="${roles}">
            <tr>
                <td><c:out value="${role.getId()}"/></td>
                <td><c:out value="${role.getName()}"/></td>
                <td><c:out value="${role.getDescription()}"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<a class="btn btn-primary" href="<%= Service.ROLE.toString()%>?roleId=-1">Προσθήκη Ρόλου</a>