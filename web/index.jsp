<%-- 
    Document   : index
    Created on : Apr 6, 2013, 11:20:44 PM
    Author     : Constantine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


            <div id="indexRightColumn">
                <div class="categoryBox">
                    <form name="loginForm" action="login">
                        <div id="login">
                            <div class="loginWidget">
                                Αν έχετε λογαριασμό τότε κάντε εισαγωγή χρησιμοποιώντας τα στοιχεία σας
                            </div>
                            <div class="loginInputWidget">
                                Username
                            </div>
                            <div class="loginInputWidget">
                                <input type="text"
                                       name="username" 
                                       value=""/>
                            </div>
                            <div class="loginInputWidget">
                                Password
                            </div>
                            <div class="loginInputWidget">
                                <input type="password" 
                                        name="password" 
                                        value="" />
                            </div>
                            <div class="loginInputWidget">
                                <input type="reset" value="Cancel" name="reset" />
                            </div>
                            <div class="loginInputWidget">
                                <input type="submit" value="OK" name="submit" />
                            </div>
                        </div>
                    </form>
                </div>
                <div class="categoryBox">
                    <div id="register" class="loginWidget">
                        Αν δεν έχετε λογαριασμό τότε μεταβείτε στην σελίδα εγγραφής νέου χρήστη 
                            για να δημιουργήσετε έναν
                            <br><br>
                            <a href="register" class="categoryLabelText">
                                Δημιουργία Νέου Χρήστη
                            </a>
                    </div>
                    
                </div>
            </div>