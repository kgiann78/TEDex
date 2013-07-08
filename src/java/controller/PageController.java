/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Constantine
 */
@WebServlet(name="PageController",
            loadOnStartup = 1,
            urlPatterns = {"/administrator",
                            "/register",
                            "/confirm",
                            "/storages",
                            "/suppliers",
                            "/roles",
                            "/users",
                            "/products",
                            "/manageRoles",
                            "/error",
                            "/editUser",
                            "/editRole",
                            "/editStorage",
                            "/editProduct",
                            "/editSupply"})
public class PageController extends HttpServlet {

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
            out.println("<title>Servlet ControllerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerServlet at " + request.getContextPath() + "</h1>");
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
        String userPath = request.getServletPath();
        String url = "";
        
        if (userPath.equals("/administrator")) {
            userPath = "view/administrator";
        }
        else if (userPath.equals("/register")) {
            userPath = "view/register";
        }
        else if (userPath.equals("/confirm")) {
            userPath = "view/confirm";
        }
        else if (userPath.equals("/storages")) {
            userPath = "view/storages";
        }
        else if (userPath.equals("/suppliers")) {
            userPath = "view/suppliers";
        } 
        else if (userPath.equals("/roles")) {
            userPath = "view/roles";
        }
        else if (userPath.equals("/products")) {
            userPath = "view/products";
        }
        else if (userPath.equals("/users")) {
            userPath = "view/users";
        }
        else if (userPath.equals("/manageRoles")) {
            userPath = "view/manageRoles";
        }
        else if (userPath.equals("/error")) {
            userPath = "others/error";
        }
        else if (userPath.equals("/editUser")) {
            userPath = "edit/user";
        }
        else if (userPath.equals("/editRole")) {
            userPath = "edit/role";
        }
        else if (userPath.equals("/editStorage")) {
            userPath = "edit/storage";
        }
        else if (userPath.equals("/editProduct")) {
            userPath = "edit/product";
        }
        else if (userPath.equals("/editSupply")) {
            userPath = "edit/supply";
        }
        
        url ="/WEB-INF/" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
        String userPath = request.getServletPath();

        if (userPath.equals("/administrator")) {
            userPath = "view/administrator";
        }
        else if (userPath.equals("/register")) {
            userPath = "view/register";
        }
        else if (userPath.equals("/confirm")) {
            userPath = "view/confirm";
        }
        else if (userPath.equals("/users")) {
            userPath = "view/users";
        }
        else if (userPath.equals("/manageRoles")) {
            userPath = "view/manageRoles";
        }
        else if (userPath.equals("/error")) {
            userPath = "others/error";
        }
        else if (userPath.equals("/editUser")) {
            userPath = "edit/user";
        }
        else if (userPath.equals("/editRole")) {
            userPath = "edit/role";
        }
        else if (userPath.equals("/editStorage")) {
            userPath = "edit/storage";
        }
        else if (userPath.equals("/editProduct")) {
            userPath = "edit/product";
        }
        else if (userPath.equals("/editSupply")) {
            userPath = "edit/supply";
        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
