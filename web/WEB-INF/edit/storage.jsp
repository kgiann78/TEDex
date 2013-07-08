<%-- 
    Document   : storage
    Created on : Jul 1, 2013, 2:32:35 PM
    Author     : Constantine
--%>
<%@page import="entities.Storage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Storage storage = null;
    String checked = "";
    String readonly = "";
    String disabled = "";
    String no_perm = "";
    String hiddenStandby = "";

    if (sessionUsername.isEmpty()) {
        response.sendRedirect("/TEDex");
    } else if (request.getAttribute("storage") != null) {
        storage = (Storage) request.getAttribute("storage");
        if (storage.getStandby() == 1) {
            checked = "checked='true'";
            hiddenStandby = "<input type='hidden' name='standby' value='on'/>";
        }

        if (!permissions.contains(Permission.administrator) && !permissions.contains(Permission.storage_write)) {
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
            <c:when test="${storage.getId() != -1}">
                <%= storage.getName()%>
                <% if (no_perm.isEmpty()) {%>
                <a id="deleteAccount" class="pull-right" href="/TEDex/<%=Service.STORAGE.toString()%>?storageId=<%= storage.getId()%>&amp;delete=0">Διαγραφή</a>
                <%}%>

            </c:when>
            <c:otherwise>
                Νέα Αποθήκη
            </c:otherwise>
        </c:choose>
    </legend>
    <form class="form-horizontal" name="editStorage" action="<%=Service.STORAGE.toString()%>?storageId=<%= storage.getId()%>" method="post">
        <div class="control-group">
            <label class="control-label" for="name">Όνομα</label>
            <div class="controls">
                <c:choose>
                    <c:when test="${storage.getId() != -1}">
                        <input type="text" name="name" placeholder="Όνομα" value="<%=storage.getName()%>" readonly="true">
                    </c:when>
                    <c:otherwise>
                        <input type="text" name="name" placeholder="Όνομα" value="<%=storage.getName()%>" <%=readonly%>>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="location">Τοποθεσία</label>
            <div class="controls">
                <input type="text" name="location" placeholder="Τοποθεσία" value="<%=storage.getLocation()%>" <%=readonly%>>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="capacity">Χωρητικότητα</label>
            <div class="controls">
                <input type="text" name="capacity" placeholder="Χωρητικότητα" value="<%=storage.getCapacity()%>" <%=readonly%>>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="quantity">Σύνολο Προϊόντων</label>
            <div class="controls">
                <input type="text" name="quantity" placeholder="Κυβικά" value="<%=storage.getQuantity()%>" readonly="true">
            </div>
        </div>
        <%=hiddenStandby%>
        <div class="control-group">
            <label class="control-label" for="standby">Ανοιχτή</label>
            <div class="controls">
                <input type="checkbox" name="standby" placeholder="Ανοιχτή" value="<%=storage.getStandby()%>" <%=checked%> <%=disabled%>>
                <br><br>
                <c:if test="${storage.getId() != -1}">
                    <a href="<%= Service.PRODUCT.toString()%>?storageId=${storage.getId()}">Προβολή Προϊόντων</a>
                </c:if>
                <br><br>
            </div>
        </div>

        <div class="control-group">
            <button type="reset" class="btn" onclick="window.location.href = '<%=Service.STORAGE.toString()%>';">Άκυρο</button>
            <button type="submit" class="btn btn-primary" style="float:right;" <%=disabled%>>Καταχώρηση</button>
        </div>
    </form>
</div>
<script>
                var validator = new FormValidator('editStorage', [
                    {
                        name: 'name',
                        display: 'Όνομα',
                        rules: 'required'
                    },
                    {
                        name: 'location',
                        display: 'Τοποθεσία',
                        rules: 'required'
                    },
                    {
                        name: 'capacity',
                        display: 'Χωρητικότητα',
                        rules: 'numeric'
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
                validator.setMessage('numeric', 'Το πεδίο %s πρέπει να περιέχει μόνο αριθμούς');

</script>