/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Role;
import extensions.Permission;
import extensions.Service;
import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RoleModel;
import model.UserModel;

/**
 *
 * @author Constantine
 */
public class UserController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsersServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsersServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
//        processRequest(request, response);
        String errorRedirectionPath = "/";
        try {
            request.setCharacterEncoding("UTF-8");
            

            UserModel uModel = new UserModel();
            RoleModel rModel = new RoleModel();

            ArrayList<Permission> permissions = new ArrayList<Permission>();

            if (request.getSession().getAttribute("permissions" + request.getSession().getId()) != null) {
                permissions = (ArrayList<Permission>) request.getSession().getAttribute("permissions"  + request.getSession().getId());
                if (permissions.contains(Permission.administrator)) {
                    errorRedirectionPath = "/users";
                }
                else {
                    response.setHeader("error", "Δεν έχετε δικαιώματα πρόσβασης στους χρήστες.");
                    request.getRequestDispatcher("/").forward(request, response);
                }
            }
            
            


            if (request.getParameter("personal") != null) {

                String personal = (String) request.getParameter("personal");
                User user = new User();
                
                if (!personal.isEmpty()) {
                    user = uModel.getUserByName(request.getParameter("personal"));
                }
                
                ArrayList<Role> roles = rModel.getRoles();
                
                request.setAttribute("user", user);
                request.setAttribute("new_password", "");
                request.setAttribute("ver_new_password", "");
                request.setAttribute("roles", roles);

                if (request.getParameter("delete") != null) {
                    int delete = Integer.parseInt((String) request.getParameter("delete"));

                    switch (delete) {
                        case 0:
                            String confirm = "<a href=\"/TEDex/" + Service.USER.toString() + "?personal=" + personal + "&amp;delete=1\">Επιβεβαίωση</a>";
                            response.setHeader("warning", "Πρόκειται να διαγράψετε τον χρήστη " + user.getUsername() + ". Είστε σίγουροι γιαυτό?  " + confirm);
                            break;
                        case 1:
                            uModel.deleteUser(user.getId());
                            response.sendRedirect("/TEDex/" + Service.USER.toString());
                            return;
                        default:
                            throw new AssertionError();
                    }
                }

                request.getRequestDispatcher("editUser").forward(request, response);


            } else {
                ArrayList<User> users = (ArrayList<User>) uModel.getUsers();
                request.setAttribute("users", users);

                request.getRequestDispatcher("/users").forward(request, response);
            }
        } catch (SQLException sqlEx) {
            response.setHeader("error", sqlEx.getMessage());
            request.getRequestDispatcher(errorRedirectionPath).forward(request, response);
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
            Boolean hasChanges = false;
            Boolean hasRoleChanges = false;
            String redirectPath = "/TEDex/";

            request.setCharacterEncoding("UTF-8");

            UserModel uModel = new UserModel();
            RoleModel rModel = new RoleModel();

            User user = uModel.getUserByName(request.getParameter("personal"));
            
            User sessionUser = uModel.getUserByName((String) request.getSession().getAttribute("username" + request.getSession().getId()));

//            ArrayList<Role> userRoles = (ArrayList<Role>)request.getSession().getAttribute("userRoles" + request.getSession().getId());
            
            User newUser = new User();
            newUser.setId(user.getId());
            
            newUser.setName((String) request.getParameter("name"));
            newUser.setSurname((String) request.getParameter("surname"));
            newUser.setUsername((String) request.getParameter("username"));
            newUser.setPassword(user.getPassword());
            newUser.setEmail((String) request.getParameter("email"));


            if (!user.getName().equals(newUser.getName())) {
                hasChanges = true;
            }

            if (!user.getSurname().equals(newUser.getSurname())) {
                hasChanges = true;
            }

            if (!user.getUsername().equals(newUser.getUsername())) {
                hasChanges = true;
            }

            if (!user.getEmail().equals(newUser.getEmail())) {
                hasChanges = true;
            }

            if (request.getParameter("new_password") != null && request.getParameter("ver_new_password") != null) {
                String new_password = ((String) request.getParameter("new_password"));
                String ver_new_password = (String) request.getParameter("ver_new_password");

                if (!new_password.isEmpty()) {
                    if (!new_password.equals(ver_new_password)) {
                        response.setHeader("error", "Ελέξτε των κωδικό που έχετε ορίσει");
                        request.setAttribute("user", newUser);
                        request.setAttribute("new_password", new_password);
                        request.setAttribute("ver_new_password", "");
                        request.getRequestDispatcher("/editUser").forward(request, response);
                    } else {
                        if (!user.getPassword().equals(new_password)) {
                            newUser.setPassword(new_password);
                            hasChanges = true;
                        }
                    }
                }
            }

            if (hasChanges) {
                uModel.updateUser(newUser);
            }

            if (hasChanges || hasRoleChanges) {
                response.setHeader("message", "Οι αλλαγες σας αποθηκεύτηκαν με επιτυχία!");
            }
            
            
            
            for (Role role : sessionUser.getRoles())
            {
                if (role.getPermissions().contains(Permission.administrator)) {
                    redirectPath = "/TEDex/" + String.valueOf(Service.USER.toString());
                }
            }

            response.sendRedirect(redirectPath);

        } catch (SQLException sqlEx) {
            response.setHeader("error", sqlEx.getMessage());
            request.getRequestDispatcher("/administrator").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
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
