<%-- 
    Document   : register
    Created on : Apr 7, 2013, 5:09:43 PM
    Author     : Constantine
--%>

<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.UserModel"%>
<%
//    if (request.getParameter("username") != null) {
//        UserModel uModel = new UserModel();
//        uModel.addUser(new User(0, request.getParameter("username"), request.getParameter("password")));
//        for (User item : uModel.getUsers()) {
//            out.println(item.getId() + " " + item.getUsername() + " " + item.getPassword() + "<br/>");
//        }
//        response.sendRedirect("/TEDex");
//    } else {
%>

<div>
    <form name="registration" action="registration" method="post">
        <table class="table table-bordered">
            <tr>
                <td colspan="4">
                    <h3>Συμπληρώστε τα στοιχεία σας</h3>
                </td>
            </tr>
            <tr>
                <td class="table_label">
                    Όνομα Χρήστη
                </td>
                <td>
                    <input class="span5" name="name" type="text" placeholder="Όνομα">
                </td>
                <td class="table_label">
                    Επώνυμο
                </td>
                <td>
                    <input class="span5" name="surname" type="text" placeholder="Επώνυμο">
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td class="table_label">
                    Όνομα Λογαριασμού
                </td>
                <td>
                    <input class="span5" name="username" type="text" placeholder="Username">
                </td>
                <td class="table_label">
                    Email
                    <br>
                </td>
                <td>
                    <input class="span5" name="email" type="text" placeholder="Email">
                </td>
            </tr>
            <tr>
                <td class="table_label">
                    Κωδικός
                </td>
                <td>
                    <input class="span5" name="password" type="text" placeholder="Κωδικός">
                </td>
                <td class="table_label">
                    Επαληθεύστε τον κωδικό σας
                </td>
                <td>
                    <input class="span5" name="password_ver" type="text" placeholder="Κωδικός">
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td colspan="2"></td>
                <td>
                    <button type="reset" class="btn btn-primary btn-danger" onclick="history.go(-1);">Άκυρο</button>
                </td>
                <td>
                    <button type="submit" class="btn btn-primary">OK</button>
                </td>
            </tr>
        </table>
    </form>

<%
//    }
%>
