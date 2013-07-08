<%-- 
    Document   : supply
    Created on : Jul 1, 2013, 2:56:19 PM
    Author     : Constantine
--%>
<%@page import="entities.Supplier"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Supplier supplier = null;
    String readonly = "";
    String disabled = "";
    String no_perm = "";
    String legend = "";
    if (sessionUsername.isEmpty()) {
        response.sendRedirect("/TEDex");
    }
    else if (request.getAttribute("supplier") != null) {
        supplier = (Supplier) request.getAttribute("supplier");
        
        legend = (!supplier.getCompany().isEmpty())?supplier.getCompany():supplier.getName() + " " + supplier.getSurname();
        if (!permissions.contains(Permission.administrator) && !permissions.contains(Permission.supplier_write)) {
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
            <c:when test="${supplier.getId() != -1}">
                <%=legend%>
                <% if (no_perm.isEmpty()) {%>
                <a id="deleteAccount" class="pull-right" href="/TEDex/<%=Service.SUPPLIER.toString()%>?supplierId=<%= supplier.getId()%>&amp;delete=0">Διαγραφή</a>
                <%}%>
            </c:when>
            <c:otherwise>
                Νέος Προμηθευτής
            </c:otherwise>
        </c:choose>
    </legend>
    <form class="form-horizontal" name="editSupply" action="<%=Service.SUPPLIER.toString()%>?supplierId=<%= supplier.getId()%>" method="post">
        <div class="control-group">
            <label class="control-label" for="name">Όνομα</label>
            <div class="controls">
                <input type="text" name="name" placeholder="Όνομα" value="<%=supplier.getName()%>" <%=readonly%>>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="surname">Επίθετο</label>
            <div class="controls">
                <input type="text" name="surname" placeholder="Επίθετο" value="<%=supplier.getSurname()%>" <%=readonly%>>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="company">Επωνυμία Εταιρίας</label>
            <div class="controls">
                <input type="text" name="company" placeholder="Επωνυμία Εταιρίας" value="<%=supplier.getCompany()%>" <%=readonly%>>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="address">Διεύθυνση</label>
            <div class="controls">
                <input type="text" name="address" placeholder="Διεύθυνση" value="<%=supplier.getAddress()%>" <%=readonly%>>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="phone">Τηλέφωνο</label>
            <div class="controls">
                <input type="text" name="phone" placeholder="Τηλέφωνο" value="<%=supplier.getPhone()%>" <%=readonly%>>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="email">Email</label>
            <div class="controls">
                <input type="text" name="email" placeholder="Email" value="<%=supplier.getEmail()%>" <%=readonly%>>
            </div>
            <br><br>
                <c:if test="${storage.getId() != -1}">
                    <a href="<%= Service.PRODUCT.toString()%>?supplierId=${supplier.getId()}">Προβολή Προϊόντων</a>
                </c:if>
                <br><br>
        </div>
        <div class="control-group">
            <button type="reset" class="btn" onclick="window.location.href = '<%=Service.SUPPLIER.toString()%>';">Άκυρο</button>
            <button type="submit" class="btn btn-primary" style="float:right;" <%=disabled%>>Καταχώρηση</button>
        </div>
    </form>
</div>
            <script>
    var validator = new FormValidator('editSupply', [
        {
            name: 'email',
            display: 'Email',
            rules: 'valid_email'
        },
        {
            name: 'phone',
            display: 'Τηλέφωνο',
            rules: 'required|valid_phone'
        }], function(errors, event) {
        if (errors.length > 0) {
            var errorString = '';

            for (var i = 0, errorLength = errors.length; i < errorLength; i++) {
                errorString += errors[i].message + '<br />';
                bootstrap_alert.error(errorString);
            }
        }
    });
    validator.setMessage('required', 'Το πεδίο %s είναι απαραίτητο');
    validator.setMessage('valid_email', 'Το πεδίο %s δεν έχει τη σωστή μορφή');
    validator.setMessage('valid_phone', 'Το πεδίο %s δεν έχει τη σωστή μορφή');
</script>