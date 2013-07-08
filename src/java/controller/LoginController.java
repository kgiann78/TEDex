/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Role;
import extensions.Permission;
import entities.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserModel;

/**
 *
 * @author Constantine
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Logout function
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("/TEDex");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getServletPath().equals("/logout")) {
            processRequest(request, response);
        } else {
            response.sendRedirect("/TEDex");
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(true);
            UserModel uModel = new UserModel();

            User user = uModel.getUserByName(request.getParameter("username"));
            String path = "/TEDex";

            if (user != null && user.getPassword().equals(request.getParameter("password"))) {
                ArrayList<Permission> permissions = new ArrayList<Permission>();

                for (Role role : user.getRoles()) {
                    for (Permission permission : role.getPermissions()) {
                        if (!permissions.contains(permission)) {
                            permissions.add(permission);
                        }
                    }
                }
                
                session.setAttribute("permissions" + session.getId(), permissions);
                session.setAttribute("userRoles" + session.getId(), user.getRoles());
                session.setAttribute("userId" + session.getId(), user.getId());
                session.setAttribute("username" + session.getId(), user.getUsername());
                
                response.setContentType("text/html;charset=UTF-8");

                for (Role role : user.getRoles()) {
                    if (role.getPermissions().contains(Permission.administrator)) {
                        path = "/TEDex/administrator";
                    }
                }


                response.sendRedirect(path);
            } else {
                throw new Exception("Ο κωδικός που βάλατε δεν είναι σωστός ή ο χρήστης δεν υπάρχει.");
            }

        } catch (SQLException sqlEx) {
            response.setHeader("error", "An SQL Exception happened with message:\n" + sqlEx.getMessage());
            request.getRequestDispatcher("/").forward(request, response);
        } catch (Exception ex) {
            response.setHeader("error", ex.getMessage());
            request.getRequestDispatcher("/").forward(request, response);
//            request.getRequestDispatcher("/").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
