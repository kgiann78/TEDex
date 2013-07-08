<%-- 
    Document   : addRole
    Created on : Jul 4, 2013, 3:12:35 PM
    Author     : Constantine
--%>
<%@page import="entities.User"%>
<%@page import="entities.Role"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ArrayList<Role> roles;
    ArrayList<Role> userRoles;
    int userId = 0;
    String personal = "";
if (sessionUsername.isEmpty()) {
        response.sendRedirect("/TEDex");
    }
else if (request.getAttribute("roles") != null) {
        roles = (ArrayList<Role>) request.getAttribute("roles");
        userRoles = (ArrayList<Role>) request.getAttribute("userRoles");
        userId = Integer.parseInt((String) request.getParameter("userId"));
        personal = (String) request.getAttribute("personal");

    }
    %>

    <div class="alert_placeholder"></div>
<div class="hero-unit span6">
    <legend>
        Διαχείριση ρόλων χρήστη <%=personal%>
    </legend>
<br>
<form class="form-horizontal" name="manageRoles" action="<%=Service.ROLE.toString()%>?userId=<%=userId%>" method="post">
<table id="userRolesTable" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" width="70%" >
    <thead>
        <tr>
            <th><strong>Κωδικός</strong></th>
            <th><strong>Όνομα Ρόλου</strong></th>
            <th><strong>Περιγραφή</strong></th>
            <th><strong>Επιλογή</strong></th>
        </tr>
    </thead>
    <tbody>      
        <c:forEach var="role" items="${roles}">
            <c:choose>
                <c:when test="${userRoles.contains(role)}">
            <tr>
                <td onclick="window.location.href = '<%= Service.ROLE.toString()%>?roleId=${role.getId()}'"><c:out value="${role.getId()}"/></td>
                <td onclick="window.location.href = '<%= Service.ROLE.toString()%>?roleId=${role.getId()}'"><c:out value="${role.getName()}"/></td>
                <td onclick="window.location.href = '<%= Service.ROLE.toString()%>?roleId=${role.getId()}'"><c:out value="${role.getDescription()}"/></td>
                <td><input type="checkbox" name="${role.getName()}" checked="true"></td>
            </tr>
            </c:when>
            <c:otherwise>
                <tr>
                <td onclick="window.location.href = '<%= Service.ROLE.toString()%>?roleId=${role.getId()}'"><c:out value="${role.getId()}"/></td>
                <td onclick="window.location.href = '<%= Service.ROLE.toString()%>?roleId=${role.getId()}'"><c:out value="${role.getName()}"/></td>
                <td onclick="window.location.href = '<%= Service.ROLE.toString()%>?roleId=${role.getId()}'"><c:out value="${role.getDescription()}"/></td>
                <td><input type="checkbox" name="${role.getName()}"></td>
            </tr>
            </c:otherwise>
            </c:choose>
        </c:forEach>
    </tbody>
</table>
<div class="control-group">
            <button type="reset" class="btn" onclick="history.go(-1);">Άκυρο</button>
            <button type="submit" class="btn btn-primary" style="float:right;">Καταχώρηση</button>
        </div>
</form>

</div>