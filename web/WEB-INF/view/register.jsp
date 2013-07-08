<%-- 
    Document   : register
    Created on : Apr 7, 2013, 5:09:43 PM
    Author     : Constantine
--%>

<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.UserModel"%>

<%
    String source = (String) request.getParameter("source");
%>
<div class="alert_placeholder"></div>
<div class="hero-unit span5">
    <form class="form-horizontal" name="registration" action="registration" method="post">
        <legend>Δημιουργία Νέου Χρήστη</legend>
        <div class="control-group">
            <label class="control-label" for="name">Όνομα Χρήστη</label>
            <div class="controls">
                <input type="text" name="name" placeholder="Το όνομά σας…" value="<% if (request.getParameter("name") != null) {
                                out.print(request.getParameter("name").toString());
                            }%>">
            </div>
        </div>
            
        <div class="control-group">
            <label class="control-label" for="surname">Επίθετο</label>
            <div class="controls">
                <input type="text" name="surname" placeholder="Το επίθετό σας…" value="<% if (request.getParameter("surname") != null) {
                                out.print(request.getParameter("surname").toString());
                            }%>">
            </div>
        </div>
            
        <div class="control-group">
            <label class="control-label" for="username">Όνομα Λογαριασμού</label>
            <div class="controls">
                <input type="text" name="username" placeholder="Το username σας…" value="<% if (request.getParameter("username") != null) {
                                out.print(request.getParameter("username").toString());
                            }%>">
            </div>
        </div>
            
        <div class="control-group">
            <label class="control-label" for="email">Email</label>
            <div class="controls">
                <input type="text" name="email" placeholder="Το email σας…" value="<% if (request.getParameter("email") != null) {
                                out.print(request.getParameter("email").toString());
                            }%>">
            </div>
        </div>
            
        <div class="control-group">
            <label class="control-label" for="password">Κωδικός</label>
            <div class="controls">
                <input type="password" name="password" placeholder="Ο κωδικός χρήστη…" value="<% if (request.getParameter("password") != null) {
                                out.print(request.getParameter("password").toString());
                            }%>">
            </div>
        </div>
            
        <div class="control-group">
            <label class="control-label" for="ver_password">Επιβεβαίωση Κωδικού</label>
            <div class="controls">
                <input type="password" name="ver_password" placeholder="Ξαναδώστε το κωδικό σας…" value="<% if (request.getParameter("ver_password") != null) {
                                out.print(request.getParameter("ver_password").toString());
                            }%>">
            </div>
        </div>
        <div class="control-group">
            <input type="hidden" name="source" value="<%=source%>"/>
            <button type="reset" class="btn" onclick="window.location.href = '/TEDex/<%=source%>';">Άκυρο</button>
            <button type="submit" class="btn btn-primary" style="float:right;">Καταχώρηση</button>
        </div>
    </form>
</div>
            <script>
    var validator = new FormValidator('registration', [
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
            name: 'username',
            display: 'Όνομα Λογαριασμού',
            rules: 'required'
        },
        {
            name: 'password',
            display: 'Κωδικός',
            rules: 'required'
        }, {
            name: 'ver_password',
            display: 'Επιβεβαίωση Κωδικού',
            rules: 'required|matches[password]'
        },
        {
            name: 'email',
            display: 'Email',
            rules: 'required|valid_email'
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
    validator.setMessage('matches', 'Το πεδίο %s δεν είναι το ίδιο με το πεδίο %s');
</script>