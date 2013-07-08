<%-- 
    Document   : user
    Created on : Jun 20, 2013, 1:24:55 PM
    Author     : Constantine
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (sessionUsername.isEmpty()) {
        response.sendRedirect("/TEDex");
    }
    if (request.getParameter("personal") != null) {
        String personal = (String) request.getParameter("personal");
        User user = (User) request.getAttribute("user");
        String sessionUser = (String) request.getSession().getAttribute("username" + request.getSession().getId());
        ArrayList<Role> roles = (ArrayList<Role>) request.getAttribute("roles");

        String username = user.getUsername();
        String name = user.getName();
        String surname = user.getSurname();
        String email = user.getEmail();
        String new_password = (String) request.getAttribute("new_password");
        String ver_new_password = (String) request.getAttribute("ver_new_password");

        String cancelPath = "/TEDex/";
        String disabled = "";
        String hiddenAdminRole = "";
        String hiddenStorageRole = "";
        String hiddenProductRole = "";
        String hiddenSupplyRole = "";

        if (permissions.contains(Permission.administrator)) {
            cancelPath += Service.USER.toString();
        }

        if (user.getUsername().equals("admin")) {
            disabled = "disabled=\"disabled\"";
            hiddenAdminRole = "<input type=\"hidden\" name=\"roleAdmin\" value=\"on\" />";
        }
%>
<div class="alert_placeholder"></div>
<div class="hero-unit span5">
    <legend>Προσωπική σελίδα <%= username%>
        <%
            if (!sessionUser.equals(user.getUsername()) && !user.getUsername().equals("admin")) {
        %>
        <a id="deleteAccount" class="pull-right" href="/TEDex/<%=Service.USER.toString()%>?personal=<%= personal%>&amp;delete=0">Διαγραφή</a>
        <%
            }
        %>
    </legend>
    <form class="form-horizontal" name="editUser" action="<%=Service.USER.toString()%>?personal=<%= personal%>" method="post">
        <div class="control-group">
            <label class="control-label" for="name">Όνομα Χρήστη</label>
            <div class="controls">
                <input type="text" name="name" placeholder="Το όνομά σας…" value="<%=name%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="surname">Επίθετο</label>
            <div class="controls">
                <input type="text" name="surname" placeholder="Το επίθετό σας…" value="<%=surname%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="username">Όνομα Λογαριασμού</label>
            <div class="controls">
                <input type="text" name="username" placeholder="Το username σας…" value="<%=username%>" readonly="true">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="email">Email</label>
            <div class="controls">
                <input type="text" name="email" placeholder="Το email σας…" value="<%=email%>">
            </div>
        </div>


        <%
            if (permissions.contains(Permission.administrator)) {
        %>

        <%=hiddenAdminRole%>
        <div class="control-group">
            <div class="controls">
                <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered table-hover" width="70%">
                    <thead>
                        <tr>
                            <th colspan="2"><strong>Ρόλοι χρήστη</strong></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="role" items="${user.getRoles()}">
                            <tr onclick="window.location.href = '<%= Service.ROLE.toString()%>?roleId=${role.getId()}'"><td><c:out value="${role.getName()}"></c:out></td></tr>                            
                                </c:forEach>
                    </tbody>
                </table>
                <a href="<%= Service.ROLE.toString()%>?userId=${user.getId()}">Διαχείριση Ρόλων Χρήστη</a>
                <br><br>
            </div>
        </div>
        <%
        } else {
        %>
        <%=hiddenStorageRole%>
        <%=hiddenProductRole%>
        <%=hiddenSupplyRole%>
        <%
            }

            if (sessionUsername.equalsIgnoreCase(personal)) {
        %>            
        <div class="control-group">
            <label class="control-label" for="new_password">Νέος Κωδικός</label>
            <div class="controls">
                <input type="password" name="new_password" placeholder="Νέος κωδικός χρήστη…" value="<%=new_password%>">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="ver_new_password">Επιβεβαίωση Νέου Κωδικού</label>
            <div class="controls">
                <input type="password" name="ver_new_password" placeholder="Ξαναδώστε το νέο κωδικό σας…" value="<%=ver_new_password%>">
            </div>
        </div>
        <%
            }
        %>
        <div class="control-group">
            <button type="reset" class="btn" onclick="window.location.href = '<%=cancelPath%>';">Άκυρο</button>
            <button type="submit" class="btn btn-primary" style="float:right;">Καταχώρηση</button>
        </div>
    </form>
</div>
<script>
                                var validator = new FormValidator('editUser', [
                                    {
                                        name: 'name',
                                        display: 'Όνομα',
                                        rules: 'required'
                                    },
                                    {
                                        name: 'surname',
                                        display: 'Επίθετο',
                                        rules: 'required'
                                    },
                                    {
                                        name: 'email',
                                        display: 'Email',
                                        rules: 'required|valid_email'
                                    },
                                    {
                                        name: 'ver_new_password',
                                        display: 'Επιβεβαίωση Κωδικού',
                                        rules: 'matches[new_password]'
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
                                validator.setMessage('matches', 'Το πεδίο %s δεν είναι το ίδιο με το πεδίο Νέος Κωδικός');
</script>
<%
    }
%>