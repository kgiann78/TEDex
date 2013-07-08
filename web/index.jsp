<%-- 
    Document   : index
    Created on : Apr 6, 2013, 11:20:44 PM
    Author     : Constantine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit">
	<h3>Warehouse Management Web Application</h3>
        
        <%
            if (request.getSession().getAttribute("username"+request.getSession().getId()) != null) {
                if (permissions.contains(Permission.administrator)) {
                    response.sendRedirect("/TEDex/administrator");
                }
                else { 
        %>
                <p>Καλώς ήρθατε! Έχετε συνδεθεί επιτυχώς στο σύστημα διαχείρισης αποθηκών και οργάνωσης πωλήσεων.<br>
                    Από το μενού επιλέξτε τη λειτουργία που επιθυμείτε να εκτελέσετε. Αν το μενού επιλογών δεν εμφανίζεται σημαίνει οτι ο administrator δεν έχει ορίσει ακόμα δικαιώματα για σας</p>
        <%      }
            }
            else { %>
                <p>Εφαρμογή ∆ιαδικτύου ∆ιαχείρισης Αποθήκων του μαθήματος 'Τεχνολογίες Εφαρμογών ∆ιαδικτύου' <br> Η’ εξαμήνου σπουδών Τμήματος Πληροφορικής &amp; Τηλ/νιών.
                    <br>Για να εισέλθετε στην εφαρμογή δώστε τα στοιχεία σας στα πεδία. Αν δεν έχετε ήδη λογαριασμό δημιουργήστε έναν κάνοντας εγγραφή!</p>
                <p><a href="registration?source=" class="btn btn-primary btn-large">Εγγραφή »</a></p>
                <form class="navbar-form pull-right" method="post" action="login">
            	<input class="span4" name="username" type="text" placeholder="Username">
                <input class="span4" name="password" type="password" placeholder="Password">
              	<button type="submit" class="btn">Είσοδος</button>
	        </form>
        <%        
            }
        %>
        
</div>