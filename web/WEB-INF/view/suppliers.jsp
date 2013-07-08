<%-- 
    Document   : suppliers
    Created on : May 7, 2013, 10:40:28 AM
    Author     : Constantine
--%>

<%@page import="entities.Supplier"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Διαχείριση Προμηθευτών</h1>
        <br>
        <%
            if (request.getAttribute("suppliers") != null) {
                ArrayList<Supplier> suppliers = (ArrayList<Supplier>) request.getAttribute("suppliers");
        %>
        <table id="suppliersTable" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" width="70%" >
            <thead>
                <tr>
                    <th><strong>Κωδικός</strong></th>
                    <th><strong>Όνομα</strong></th>
                    <th><strong>Επίθετο</strong></th>
                    <th><strong>Εταιρία</strong></th>
                    <th><strong>Διεύθυνση</strong></th>
                    <th><strong>Τηλέφωνο</strong></th>
                    <th><strong>Email</strong></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="supplier" items="${suppliers}">
                    <tr>
                        <td><c:out value="${supplier.getId()}" /></td>
                        <td><c:out value="${supplier.getName()}" /></td>
                        <td><c:out value="${supplier.getSurname()}" /></td>
                        <td><c:out value="${supplier.getCompany()}" /></td>
                        <td><c:out value="${supplier.getAddress()}" /></td>
                        <td><c:out value="${supplier.getPhone()}" /></td>
                        <td><c:out value="${supplier.getEmail()}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <%
            }
        %>
<a class="btn btn-primary" href="<%= Service.SUPPLIER.toString()%>?supplierId=-1">Προσθήκη Προμηθευτή</a>