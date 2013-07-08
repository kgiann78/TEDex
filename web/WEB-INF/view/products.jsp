<%-- 
    Document   : products
    Created on : May 7, 2013, 10:42:16 AM
    Author     : Constantine
--%>

<%@page import="entities.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Διαχείριση Προϊόντων</h1>
        <br>

        <%
            if (request.getAttribute("products") != null) {
                ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
        %>
        <table id="productsTable" cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" width="70%" >
            <thead>
                <tr>
                    <th><strong>Κωδικός</strong></th>
                    <th><strong>Κωδικός Προϊόντος</strong></th>
                    <th><strong>Όνομα</strong></th>
                    <th><strong>Περιγραφή</strong></th>
                    <th><strong>Ποσότητα</strong></th>
                    <th><strong>Τιμή Αγοράς</strong></th>
                    <th><strong>Τιμή Πώλησης</strong></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td><c:out value="${product.getId()}" /></td>
                    <td><c:out value="${product.getCode()}" /></td>
                    <td><c:out value="${product.getName()}" /></td>
                    <td><c:out value="${product.getDescription()}" /></td>
                    <td><c:out value="${product.getQuantity()}" /></td>
                    <td><c:out value="${product.getCost()}" /></td>
                    <td><c:out value="${product.getPrice()}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <%
        }
    %>
    <a class="btn btn-primary" href="<%= Service.PRODUCT.toString()%>?productId=-1">Προσθήκη Προϊόντος</a>

