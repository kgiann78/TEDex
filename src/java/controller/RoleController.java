/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Role;
import entities.User;
import extensions.Permission;
import extensions.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class RoleController extends HttpServlet {

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
            out.println("<title>Servlet RoleController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RoleController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="uncollapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        request.setCharacterEncoding("UTF-8");
        ArrayList<Permission> permissions = (ArrayList<Permission>) request.getSession().getAttribute("permissions" + request.getSession().getId());

        try {
            
            if (!permissions.contains(Permission.administrator)) {
                response.setHeader("error", "Δεν έχετε δικαιώματα πρόσβασης στους ρόλους.");
                request.getRequestDispatcher("/").forward(request, response);
            }
            
            
            RoleModel rModel = new RoleModel();
            if (request.getParameter("roleId") != null) {
                Role role = new Role();

                int roleId = Integer.parseInt((String) request.getParameter("roleId"));
                if (roleId != -1) {
                    role = rModel.getRoleById(roleId);
                }

                request.setAttribute("role", role);


                if (request.getParameter("delete") != null) {
                    int delete = Integer.parseInt((String) request.getParameter("delete"));

                    switch (delete) {
                        case 0:
                            String confirm = "<a href=\"/TEDex/" + Service.ROLE.toString() + "?roleId=" + roleId + "&amp;delete=1\">Επιβεβαίωση</a>";
                            response.setHeader("warning", "Πρόκειται να διαγράψετε το ρόλο " + role.getName() + ". Είστε σίγουροι γιαυτό?  " + confirm);
                            break;
                        case 1:
                            rModel.deleteRole(roleId);
                            response.sendRedirect("/TEDex/" + Service.ROLE.toString());
                            return;
                        default:
                            throw new AssertionError();
                    }
                }

                request.getRequestDispatcher("/editRole").forward(request, response);
            } else {
                int userId;
                ArrayList<Role> userRoles = new ArrayList<Role>();
                ArrayList<Role> roles = (ArrayList<Role>) rModel.getRoles();
                String personal = "";
                String path = "/roles";


                if (request.getParameter("userId") != null) {
                    UserModel uModel = new UserModel();
                    userId = Integer.parseInt((String) request.getParameter("userId"));
                    User user = uModel.getUserById(userId);
                    userRoles = rModel.getRolesByUser(userId);
                    request.setAttribute("userId", userId);
                    request.setAttribute("personal", user.getUsername());
                    request.setAttribute("userRoles", userRoles);
                    path = "/manageRoles";
                }

                request.setAttribute("roles", roles);
                request.getRequestDispatcher(path).forward(request, response);
            }

        } catch (SQLException sqlEx) {
            response.setHeader("error", sqlEx.getMessage());
            request.getRequestDispatcher("/").forward(request, response);
        } catch (Exception ex) {
            response.setHeader("error", ex.getMessage());
            request.getRequestDispatcher("/").forward(request, response);
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
//        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        RoleModel rModel = new RoleModel();
        try {


            if (request.getParameter("userId") != null) {
                UserModel uModel = new UserModel();

                int userId = Integer.parseInt((String) request.getParameter("userId"));
                String username = uModel.getUserById(userId).getUsername();

                ArrayList<Role> roles = rModel.getRoles();

                for (Role role : roles) {
                    if (request.getParameter(role.getName()) != null) {
                        rModel.setRole(userId, role.getId());
                    } else {
                        rModel.removeRole(userId, role.getId());
                    }
                }

                response.sendRedirect("/TEDex/" + Service.USER.toString() + "?personal=" + username);
            } else {
                int roleId = Integer.parseInt((String) request.getParameter("roleId"));

                Role role = rModel.getRoleById(Integer.parseInt((String) request.getParameter("roleId")));
                ArrayList<Permission> permissions = rModel.getPermissions();

                Role newRole = new Role();
                newRole.setName((String) request.getParameter("name"));
                newRole.setDescription((String) request.getParameter("description"));

                if (roleId == -1) {
                    rModel.addRole(newRole);
                    roleId = rModel.getRoleId((String) request.getParameter("name"));
                } else {
                    newRole.setId(roleId);
                }

                for (Permission permission : permissions) {
                    if (request.getParameter(permission.toString()) != null) {
                        rModel.setPermission(roleId, permission);
                    } else {
                        rModel.removePermission(roleId, permission);
                    }
                }

                rModel.updateRole(newRole);
                response.setHeader("message", "Οι αλλαγες σας αποθηκεύτηκαν με επιτυχία!");

                response.sendRedirect("/TEDex/" + Service.ROLE.toString());
            }
        } catch (SQLException sqlEx) {
            response.setHeader("error", sqlEx.getMessage());
            request.getRequestDispatcher("/").forward(request, response);
        } catch (Exception ex) {
            response.setHeader("error", ex.getMessage());
            request.getRequestDispatcher("/error").forward(request, response);
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
