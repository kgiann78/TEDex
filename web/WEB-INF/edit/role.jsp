<%-- 
    Document   : role
    Created on : Jul 3, 2013, 9:00:25 PM
    Author     : Constantine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%
    Role role = null;
    String readonly = "";
    String cancelPath = "";
        String disabled = "";
        String hiddenAdmin = "";
        
        String checkStoreWr = "";
        String checkStoreRd = "";
        String checkProdWr = "";
        String checkProdRd = "";
        String checkSupWr = "";
        String checkSupRd = "";
    
    if (sessionUsername.isEmpty()) {
        response.sendRedirect("/TEDex");
    }
    else if (request.getAttribute("role") != null) {
        role = (Role) request.getAttribute("role");

        if (role.getId() == 1) {
            disabled = "disabled=\"disabled\"";
            readonly = "readonly=\"readonly\"";
        }
        
        if (role.getPermissions().contains(Permission.administrator)) {
            hiddenAdmin = "<input type=\"hidden\" name=\"" + Permission.administrator.toString() + "\" value=\"on\" />";
            readonly = "readonly=\"readonly\"";
            checkStoreWr = "checked=\"true\"";
            checkStoreRd = "checked=\"true\"";
            checkProdWr = "checked=\"true\"";
            checkProdRd = "checked=\"true\"";
            checkSupWr = "checked=\"true\"";
            checkSupRd = "checked=\"true\"";
        }
        
        if (role.getPermissions().contains(Permission.storage_write)) {
            checkStoreWr = "checked=\"true\"";
        }

        if (role.getPermissions().contains(Permission.storage_read)) {
            checkStoreRd = "checked=\"true\"";
        }

        if (role.getPermissions().contains(Permission.product_write)) {
            checkProdWr = "checked=\"true\"";
        }
        
        if (role.getPermissions().contains(Permission.product_read)) {
            checkProdRd = "checked=\"true\"";
        }

        if (role.getPermissions().contains(Permission.supplier_write)) {
            checkSupWr = "checked=\"true\"";
        }
        
        if (role.getPermissions().contains(Permission.supplier_read)) {
            checkSupRd = "checked=\"true\"";
        }
    }
%>
<div class="alert_placeholder"></div>
<div class="hero-unit span6">
    <legend>
        <c:choose>
            <c:when test="${(role.getId() != -1)}">
                <%= role.getName()%>
                <c:if test="${role.getId() != 1}">
                    <a id="deleteAccount" class="pull-right" href="/TEDex/<%=Service.ROLE.toString()%>?roleId=<%= role.getId()%>&amp;delete=0">Διαγραφή</a>
                </c:if>
            </c:when>
            <c:otherwise>
                    Νέος Ρόλος
            </c:otherwise>
        </c:choose>
    </legend>
    <form class="form-horizontal" name="editRole" action="<%=Service.ROLE.toString()%>?roleId=<%= role.getId()%>" method="post">
        <%= hiddenAdmin %>
        
        <div class="control-group">
            <label class="control-label" for="name">Όνομα</label>
            <div class="controls">
                <input type="text" name="name" placeholder="Όνομα" value="<%=role.getName()%>" <%=readonly%>>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="description">Περιγραφή</label>
            <div class="controls">
                <textarea rows="4" name="description" placeholder="Περιγραφή"><%=role.getDescription()%></textarea>
            </div>
        </div>
            <div class="control-group">
                <label class="control-label" for="description">Δικαιώματα Ρόλου</label>
            <div class="controls">
                <table cellpadding="0" cellspacing="0" border="0" class="table table-condensed">
                    <thead>
                        <tr>
                            <th>&nbsp;</th>
                            <th><strong>Αποθήκες</strong></th>
                            <th><strong>Προϊόντα</strong></th>
                            <th><strong>Προμηθευτές</strong></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Ανάγνωση</td>
                            <td><input name="<%=Permission.storage_read %>" type="checkbox" <%=checkStoreRd %> <%=disabled%>></td>
                            <td><input name="<%=Permission.product_read %>" type="checkbox" <%=checkProdRd %> <%=disabled%>></td>
                            <td><input name="<%=Permission.supplier_read %>" type="checkbox" <%=checkSupRd %> <%=disabled%>></td>
                        </tr>
                        <tr>
                            <td>Εγγραφή</td>
                            <td><input name="<%=Permission.storage_write %>" type="checkbox" <%=checkStoreWr %> <%=disabled%> ></td>
                            <td><input name="<%=Permission.product_write %>" type="checkbox" <%=checkProdWr %> <%=disabled%>></td>
                            <td><input name="<%=Permission.supplier_write %>" type="checkbox" <%=checkSupWr %> <%=disabled%>></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
                        <div class="control-group">
            <button type="reset" class="btn" onclick="history.go(-1);">Άκυρο</button>
            <button type="submit" class="btn btn-primary" style="float:right;">Καταχώρηση</button>
        </div>
    </form>
</div>
<script>
    var validator = new FormValidator('editRole', [
        {
            name: 'name',
            display: 'Όνομα',
            rules: 'required'
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
</script>