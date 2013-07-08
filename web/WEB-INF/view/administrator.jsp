<%-- 
    Document   : administrator
    Created on : Jun 2, 2013, 10:41:04 PM
    Author     : Constantine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit span5">
    <a href="/TEDex/<%=Service.USER.toString()%>">
        <h2>Διαχείριση Χρηστών</h2>
    </a>
</div>
<div class="hero-unit span5">
    <a href="/TEDex/<%=Service.STORAGE.toString()%>">
    <h2>Διαχείριση Αποθηκών</h2>
    </a>
</div>
<div class="hero-unit span5">
    <a href="/TEDex/<%=Service.PRODUCT.toString()%>">
    <h2>Διαχείριση Προϊόντων</h2>
    </a>
</div>
<div class="hero-unit span5">
    <a href="/TEDex/<%=Service.SUPPLIER.toString()%>">
    <h2>Διαχείριση Προμηθευτών</h2>
    </a>
</div>