<%-- 
    Document   : product
    Created on : Jun 20, 2013, 12:10:21 PM
    Author     : Constantine
--%>

<%@page import="entities.Supplier"%>
<%@page import="entities.Storage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entities.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Product product = null;
    String readonly = "";
    String disabled = "";
    String no_perm = "";
    ArrayList<Storage> storages = null;
    ArrayList<Supplier> suppliers = null;
    Storage storage = null;
    Supplier supplier = null;
    
    if (sessionUsername.isEmpty()) {
        response.sendRedirect("/TEDex");
    }
    if (request.getAttribute("product") != null) {
        product = (Product) request.getAttribute("product");
        storages = (ArrayList<Storage>) request.getAttribute("storages");
        suppliers = (ArrayList<Supplier>) request.getAttribute("suppliers");
        
        storage = (Storage) request.getAttribute("storage");
        supplier = (Supplier) request.getAttribute("supplier");
        
        if (!permissions.contains(Permission.administrator) && !permissions.contains(Permission.product_write)) {
            readonly = "readonly='true'";
            disabled = "disabled='disabled'";
            no_perm = "no_perm";
        }
    }
%>
<div class="alert_placeholder"></div>
<div class="hero-unit span5">
    <legend>
        <c:choose>
            <c:when test="${product.getId() != -1}">
                <%= product.getName()%>
                <% if (no_perm.isEmpty()) {%>
                    <a id="deleteAccount" class="pull-right" href="/TEDex/<%=Service.PRODUCT.toString()%>?productId=<%= product.getId()%>&amp;delete=0">Διαγραφή</a>
                <%}%>
            </c:when>
            <c:otherwise>
                Νέο Προϊόν
            </c:otherwise>
        </c:choose>
    </legend>
    
    <form class="form-horizontal" name="editProduct" action="<%=Service.PRODUCT.toString()%>?productId=<%=product.getId()%>" method="post">
        
        <div class="control-group">
            <label class="control-label" for="code">Κωδικός Προϊόντος</label>
            <div class="controls">
            <c:choose>
                    <c:when test="${product.getId() != -1}">
                        <input type="text" name="code" placeholder="Κωδικός Προϊόντος" value="<%=product.getCode()%>" readonly="true">
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="code" placeholder="Κωδικός Προϊόντος" value="<%=product.getCode() %>" <%=readonly%>>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="name">Όνομα</label>
            <div class="controls">
            <c:choose>
                    <c:when test="${product.getId() != -1}">
                        <input type="text" name="name" placeholder="Όνομα" value="<%=product.getName()%>" readonly="true">
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="name" placeholder="Όνομα" value="<%=product.getName()%>" <%=readonly%>>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="description">Περιγραφή</label>
            <div class="controls">
                <textarea rows="4" name="description" placeholder="Περιγραφή" <%=readonly%>><%=product.getDescription()%></textarea>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="quantity">Ποσότητα</label>
            <div class="controls">
                <input type="text" name="quantity" placeholder="Ποσότητα" value="<%=product.getQuantity()%>" <%=readonly%>>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="cost">Τιμή Αγοράς</label>
            <div class="controls">
                <input type="text" name="cost" placeholder="Τιμή Αγοράς" value="<%=product.getCost()%>" <%=readonly%>>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="price">Τιμή Πώλησης</label>
            <div class="controls">
                <input type="text" name="price" placeholder="Τιμή Πώλησης" value="<%=product.getPrice()%>" <%=readonly%>>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="supplierId">Προμηθευτής</label>
            <div class="controls">
                <select name="supplierId" placeholder="Προμηθευτής" <%=disabled%>>
                    <c:forEach var="supply" items="${suppliers}">
                        <c:choose>
                            <c:when test="${supply.getId() == supplier.getId()}">
                                <option value="${supply.getId()}" selected="selected">
                                    <c:if test="${!supply.getCompany().isEmpty()}">
                                        <c:out value="${supply.getCompany()}"></c:out>
                                    </c:if>
                                    <c:if test="${supply.getCompany().isEmpty()}">
                                        <c:out value="${supply.getName()}"></c:out> <c:out value="${supply.getSurname()}"></c:out>
                                    </c:if>
                                </option>
                            </c:when>
                            <c:otherwise>
                                <option value="${supply.getId()}" >
                                    <c:if test="${!supply.getCompany().isEmpty()}">
                                        <c:out value="${supply.getCompany()}"></c:out>
                                    </c:if>
                                    <c:if test="${supply.getCompany().isEmpty()}">
                                        <c:out value="${supply.getName()}"></c:out> <c:out value="${supply.getSurname()}"></c:out>
                                    </c:if>                              
                                </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <input type="hidden" name="supplierId" placeholder="Προμηθευτής" value="<%=product.getSupplierId()%>" >
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="storageId">Αποθήκη</label>
            <div class="controls">
                <select name="storageId" placeholder="Αποθήκη" <%=disabled%>>
                    <c:forEach var="store" items="${storages}">
                        <c:choose>
                            <c:when test="${store.getId() == storage.getId()}">
                                <option value="${store.getId()}" selected="selected"><c:out value="${store.getName()}"/></option>
                            </c:when>
                            <c:otherwise>
                                <option value="${store.getId()}" ><c:out value="${store.getName()}"/></option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <input type="hidden" name="storageId" placeholder="Αποθήκη" value="<%=product.getStorageId()%>" >
            </div>
        </div>

        <div class="control-group">
            <button type="reset" class="btn" onclick="window.location.href = '<%=Service.PRODUCT.toString()%>';">Άκυρο</button>
            <button type="submit" class="btn btn-primary" style="float:right;" <%=disabled%>>Καταχώρηση</button>
        </div>
    </form>
</div>
<script>
                var validator = new FormValidator('editProduct', [
                    {
                        name: 'name',
                        display: 'Όνομα',
                        rules: 'required'
                    },
                    {
                        name: 'code',
                        display: 'Κωδικός',
                        rules: 'required'
                    },
                    {
                        name: 'description',
                        display: 'Περιγραφή',
                        rules: 'required'
                    },
                    {
                        name: 'quantity',
                        display: 'Ποσότητα',
                        rules: 'numeric'
                    },
                    {
                        name: 'cost',
                        display: 'Τιμή Αγοράς',
                        rules: 'numeric'
                    },
                    {
                        name: 'price',
                        display: 'Τιμή Πώλησης',
                        rules: 'numeric'
                    }
                ], function(errors, event) {
                    if (errors.length > 0) {
                        var errorString = '';

                        for (var i = 0, errorLength = errors.length; i < errorLength; i++) {
                            errorString += errors[i].message + '<br />';
                            bootstrap_alert.error(errorString);
                        }
                    }
                });
                validator.setMessage('required', 'Το πεδίο %s είναι απαραίτητο');
                validator.setMessage('numeric', 'Το πεδίο %s πρέπει να περιέχει μόνο αριθμούς');

</script>