<%-- 
    Document   : index
    Created on : Apr 6, 2013, 11:20:44 PM
    Author     : Constantine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="hero-unit">
	<h3>Warehouse Management Web Application</h3>
        
        <%
            if (request.getSession().getAttribute("username") != null) { %>
                <p>Καλώς ήρθατε! Έχετε συνδεθεί επιτυχώς στο σύστημα διαχείρισης αποθηκών και οργάνωσης πωλήσεων. 
                    Από το μενού επιλέξτε τη λειτουργία που επιθυμείτε να εκτελέσετε.</p>
        <% }
            else { %>
                <p>Εφαρμογή ∆ιαδικτύου ∆ιαχείρισης Αποθήκων του μαθήματος 'Τεχνολογίες Εφαρμογών ∆ιαδικτύου' <br>του Η’ εξαμήνου σπουδών του Τμήματος Πληροφορικής &amp; Τηλ/νιών. Αν δεν έχετε ήδη λογαριασμό δημιουργήστε ένα!</p>
                <p><a href="register" class="btn btn-primary btn-large">Εγγραφή »</a></p>
                <form class="navbar-form pull-right" method="post" action="login">
            	<input class="span4" name="username" type="text" placeholder="Username">
                <input class="span4" name="password" type="password" placeholder="Password">
              	<button type="submit" class="btn">Είσοδος</button>
	        </form>
        <%        
            }
        %>
        
</div>