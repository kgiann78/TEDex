<%-- 
    Document   : index
    Created on : Apr 6, 2013, 11:20:44 PM
    Author     : Constantine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="content">
    <div id="login">
        <form name="loginForm" action="login">
            <p>
                Αν έχετε λογαριασμό τότε κάντε εισαγωγή χρησιμοποιώντας τα στοιχεία σας
            </p>
            <table>
                <tr>
                    <td>Username</td>
                    <td><input type="text"
                               name="username" 
                               value=""/>
                    </td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" 
                               name="password" 
                               value="" /></td>
                </tr>
                <tr>
                    <td>
                        <input type="reset" value="Cancel" name="reset" />
                    </td>
                    <td>
                        <input type="submit" value="OK" name="submit" />
                    </td>                       
                </tr>
            </table>
        </form>
    </div>
    <div id="register">
    <p>Αν δεν έχετε λογαριασμό <a href="register">δημιουργήστε έναν</a>.</p>
    </div>
</div>
