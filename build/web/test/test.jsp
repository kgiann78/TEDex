<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : test
    Created on : Apr 15, 2013, 7:09:38 PM
    Author     : Constantine
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<sql:query var="rs" dataSource="jdbc/tedex">
select * from user
</sql:query>

<html>
  <head>
    <title>DB Test</title>
  </head>
  <body>

  <h2>Results</h2>

<c:forEach var="row" items="${rs.rows}">
    Foo ${row.foo}<br/>
    Bar ${row.bar}<br/>
</c:forEach>
    
    <%
        InitialContext initialContext = new InitialContext();

        DataSource ds = (DataSource) initialContext.lookup("jdbc/tedex");

        Connection connection = ds.getConnection();

 

        if (connection == null) {
            throw new SQLException("Error establishing connection!");
        }

        String query = "SELECT username FROM user";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            out.print(rs.getString("username") + "<br/>");
        }

%>

  </body>
</html>
