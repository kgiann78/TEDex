/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.jws.WebMethod;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserModel;

/**
 *
 * @author Constantine
 */
public class RegistrationController extends HttpServlet {

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
//            String contextPath = "ELA ALEEEKEOEKOOEKOEOEOEKO";//request.getContextPath();
//            request.setAttribute("contextPath", contextPath);
//            request.getRequestDispatcher("register").forward(request, response);
            response.sendRedirect("/TEDex/register");
        } finally {
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
//            String source = (String) request.getParameter("source");
//            request.setAttribute("source", source);
            request.getRequestDispatcher("register").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        String contextPath = request.getContextPath();
        String source = "/";
        
        try {
            UserModel uModel = new UserModel();
            if (uModel.getUserByName(request.getParameter("username")) != null) {
                throw new Exception("Ο χρήστης υπάρχει ήδη!");
            }
            
            if (!request.getParameter("password").equals(request.getParameter("ver_password"))) {
                throw new Exception("Παρακαλώ ελέγξτε το κωδικό σας!");
            }
            
            User newUser = new User();
            newUser.setName(request.getParameter("name"));
            newUser.setSurname(request.getParameter("surname"));
            newUser.setEmail(request.getParameter("email"));
            newUser.setUsername(request.getParameter("username"));
            newUser.setPassword(request.getParameter("password"));
            
            uModel.addUser(newUser);

            request.getRequestDispatcher("confirm").forward(request, response);
        }
        catch (Exception ex) {
            response.setHeader("error", ex.getMessage());
            
            request.getRequestDispatcher("register").forward(request, response);
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
