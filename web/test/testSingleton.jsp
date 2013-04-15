<%-- 
    Document   : testSingleton
    Created on : 15 Απρ 2013, 5:30:40 μμ
    Author     : alkis
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entities.DataContext"%>
<%@page import="entities.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="model.ConnectionPool"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>mpla</p>
        <%
//            try 
//            {
//                Connection con = ConnectionPool.INSTANCE.getConnection();
//                Statement stmt = con.createStatement();
//                String strSQL = "SELECT * FROM user";
//                ResultSet rs = stmt.executeQuery(strSQL);
//                
//                while( rs.next() ) 
//                {
//                    out.println(rs.getInt("id"));
//                    out.println(rs.getString("username"));
//                    out.println(rs.getString("password")); 
//                }
//                con.close();
//                
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
            
            DataContext context = new DataContext();
            context.addUser(new User(0, "scheduler", "qwerty"));
            for (User item : context.getUsers(null)) {
                out.println(item.getId() + " " + item.getUsername() + " " + item.getPassword() + "<br/>");
            }
            
            out.println("<br/><br/>");
            for (User item : context.getUsers("WHERE username = 'manager'")) {
                out.println(item.getId() + " " + item.getUsername() + " " + item.getPassword() + "<br/>");
            }
            
            out.println("<br/><br/>");
            for (User item : context.getUsers("WHERE username = 'scheduler' AND password = 'qwerty'")) {
                out.println(item.getId() + " " + item.getUsername() + " " + item.getPassword() + "<br/>");
            }
                    
        %>
        
    </body>
</html>
