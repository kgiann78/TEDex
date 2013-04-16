<%-- 
    Document   : transaction
    Created on : 16 Απρ 2013, 6:53:54 μμ
    Author     : alkis
--%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
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
            Connection con = null;
            PreparedStatement statement = null;
            
            try 
            {
                con = ConnectionPool.INSTANCE.getConnection();
     
                con.setAutoCommit(false);
     
                String query = "INSERT INTO user"
                             + "(username, password) VALUES"
                             + "(?,?)";
                statement = con.prepareStatement(query);
                
                statement.setString(1, "alk");
                statement.setString(2, "11111111");
                statement.addBatch();
                
                statement.setString(1, "alkk");
                statement.setString(2, "11111111");               
                statement.addBatch();
                
                statement.executeBatch();
                con.commit();
                out.println(con.getAutoCommit());   //See if changes automatically or not.
                /*
                String query = "SELECT * FROM user";
                statement = con.prepareStatement(query);
                ResultSet rs = statement.executeQuery();
                
                while( rs.next() ) 
                {
                    out.println(rs.getInt("id"));
                    out.println(rs.getString("username"));
                    out.println(rs.getString("password")); 
                }
                */
            } catch (SQLException ex) {
                ex.printStackTrace();
                out.println("SQLEXCEPTION");
                con.rollback();
            } finally {
            
                if (statement != null) 
                {
                    statement.close();
                }

                if (con != null) 
                {
                    con.close();
                }
            }
        %>
    </body>
</html>
