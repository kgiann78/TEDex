/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Storage;
import extensions.Permission;
import extensions.Service;
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
import model.StorageModel;

/**
 *
 * @author Constantine
 */
public class StorageController extends HttpServlet {

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
            out.println("<title>Servlet StorageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StorageServlet at " + request.getContextPath() + "</h1>");
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
        StorageModel sModel = new StorageModel();

        try {
            ArrayList<Permission> permissions = (ArrayList<Permission>) request.getSession().getAttribute("permissions" + request.getSession().getId());

            if (!permissions.contains(Permission.administrator)) {
                if (!permissions.contains(Permission.storage_write)
                        && !permissions.contains(Permission.storage_read)) {
                    response.setHeader("error", "Δεν έχετε δικαιώματα πρόσβασης στις αποθήκες.");
                    request.getRequestDispatcher("/").forward(request, response);
                }
            }


            if (request.getParameter("storageId") != null) {
                Storage storage = new Storage();

                int storageId = Integer.parseInt((String) request.getParameter("storageId"));
                if (storageId != -1) {
                    storage = sModel.getStorageById(storageId);
                }

                request.setAttribute("storage", storage);

                if (request.getParameter("delete") != null) {
                    int delete = Integer.parseInt((String) request.getParameter("delete"));

                    switch (delete) {
                        case 0:
                            if (storage.getQuantity() > 0) {
                                response.setHeader("error", "Δεν μπορείτε να διαγράψετε την αποθήκη " + storage.getName() + " γιατί δεν είναι άδεια.");
                                request.getRequestDispatcher("/editStorage").forward(request, response);
                            } else {
                                String confirm = "<a href=\"/TEDex/" + Service.STORAGE.toString() + "?storageId=" + storageId + "&amp;delete=1\">Επιβεβαίωση</a>";
                                response.setHeader("warning", "Πρόκειται να διαγράψετε την αποθήκη " + storage.getName() + ". Είστε σίγουροι γιαυτό?  " + confirm);
                            }
                            break;
                        case 1:
                            sModel.deleteStorage(storageId);
                            response.sendRedirect("/TEDex/" + Service.STORAGE.toString());
                            return;
                        default:
                            throw new AssertionError();
                    }
                }

                request.getRequestDispatcher("/editStorage").forward(request, response);
            } else {

                ArrayList<Storage> storages = (ArrayList<Storage>) sModel.getStorages();

                request.setAttribute("storages", storages);
                request.getRequestDispatcher("/storages").forward(request, response);
            }
        } catch (SQLException ex) {
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
        request.setCharacterEncoding("UTF-8");

        try {
            StorageModel sModel = new StorageModel();
            int storageId = Integer.parseInt((String) request.getParameter("storageId"));

            Storage newStorage = new Storage();

            newStorage.setName((String) request.getParameter("name"));
            newStorage.setLocation((String) request.getParameter("location"));
            newStorage.setCapacity(Integer.parseInt((String) request.getParameter("capacity")));
            if (request.getParameter("standby") != null) {
                newStorage.setStandby(true);
            } else {
                newStorage.setStandby(false);
            }

            if (storageId == -1 && sModel.exists((String) request.getParameter("name"))) {
                response.setHeader("error", "Υπάρχει ήδη μια αποθήκη με το όνομα '" + (String) request.getParameter("name") + "'. Επιλέξτε άλλο όνομα.");
                request.setAttribute("storage", newStorage);
                request.getRequestDispatcher("/editStorage").forward(request, response);
            } else {
                if (storageId != -1) {
                    newStorage.setId(storageId);
                    sModel.updateStorage(newStorage);
                    response.setHeader("message", "Οι αλλαγες σας αποθηκεύτηκαν με επιτυχία!");
                } else {
                    sModel.addStorage(newStorage);
                }
                response.sendRedirect("/TEDex/" + Service.STORAGE.toString());
            }


        } catch (SQLException sqlEx) {
            response.setHeader("error", sqlEx.getMessage());
            request.getRequestDispatcher(Service.STORAGE.toString()).forward(request, response);
        } catch (Exception ex) {
            response.setHeader("error", ex.getMessage());
            request.getRequestDispatcher(Service.STORAGE.toString()).forward(request, response);
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
