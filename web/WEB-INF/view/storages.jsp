<%-- 
    Document   : warehouses
    Created on : May 7, 2013, 10:40:05 AM
    Author     : Constantine
--%>

<%@page import="entities.Storage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>Διαχείριση Αποθηκών</h1>
<br>
<%
    if (request.getAttribute("storages") != null) {
        ArrayList<Storage> storages = (ArrayList<Storage>) request.getAttribute("storages");
%>
<table id="storagesTable" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" width="70%" >
    <thead>
        <tr>
            <th><strong>Κωδικός</strong></th>
            <th><strong>Όνομα</strong></th>
            <th><strong>Περιοχή</strong></th>
            <th><strong>Χωρητικότητα</strong></th>
            <th><strong>Σύνολο Προϊόντων</strong></th>
            <th><strong>Ανοιχτή</strong></th>            
        </tr>
    </thead>
    <tbody>
    <c:forEach var="storage" items="${storages}">
        <tr>
            <td><c:out value="${storage.getId()}" /></td>
            <td><c:out value="${storage.getName()}" /></td>
            <td><c:out value="${storage.getLocation()}" /></td>
            <td><c:out value="${storage.getCapacity()}" /></td>
            <td><c:out value="${storage.getQuantity()}" /></td>
            <td>
                <c:choose>
                    <c:when test="${storage.getStandby() == 1}">
                        Ναί
                    </c:when>
                    <c:otherwise>
                        Όχι
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</tbody>
</table>
<%
    }
%>
<a class="btn btn-primary" href="<%= Service.STORAGE.toString()%>?storageId=-1">Προσθήκη Αποθήκης</a>