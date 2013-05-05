<%-- 
    Document   : testSingleton
    Created on : 15 Απρ 2013, 5:30:40 μμ
    Author     : alkis
--%>

<%@page import="model.UserModel"%>
<%@page import="java.util.ArrayList"%>
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
            
            UserModel uModel = new UserModel();
            uModel.addUser(new User(0, "manager", "qwerty"));
            uModel.addUser(new User(0, "scheduler", "qwerty"));
            uModel.addUser(new User(0, "seller", "qwerty"));
            uModel.addUser(new User(0, "inventory_man", "qwerty"));


            for (User item : uModel.getUsers()) {
                out.println(item.getId() + " " + item.getUsername() + " " + item.getPassword() + "<br/>");
            }
            
            out.println("<br/><br/>");
            User user = uModel.getUserByName("scheduler");
            out.println(user.getId() + " " + user.getUsername() + " " + user.getPassword() + "<br/>");
            
            // out.println("<br/><br/>");
            // uModel.deleteUser(user.getId());
            // for (User item : uModel.getUsers()) {
            //    out.println(item.getId() + " " + item.getUsername() + " " + item.getPassword() + "<br/>");
            // }
                    
        %>
        
    </body>
</html>
