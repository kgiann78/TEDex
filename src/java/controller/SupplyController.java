/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Supplier;
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
import model.SupplierModel;

/**
 *
 * @author Constantine
 */
public class SupplyController extends HttpServlet {

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
            out.println("<title>Servlet SupplyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SupplyServlet at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        try {
            SupplierModel sModel = new SupplierModel();
            ArrayList<Permission> permissions = (ArrayList<Permission>) request.getSession().getAttribute("permissions" + request.getSession().getId());
            if (!permissions.contains(Permission.administrator)) {
                if (!permissions.contains(Permission.supplier_write)
                        && !permissions.contains(Permission.supplier_read)) {
                    response.setHeader("error", "Δεν έχετε δικαιώματα πρόσβασης στους προμηθευτές.");
//                ArrayList<Supplier> suppliers = (ArrayList<Supplier>) sModel.getSuppliers();
//
//                request.setAttribute("suppliers", suppliers);
                    request.getRequestDispatcher("/").forward(request, response);
                }
            }


            if (request.getParameter("supplierId") != null) {
                int supplierId = Integer.parseInt((String) request.getParameter("supplierId"));

                Supplier supplier = new Supplier();

                if (supplierId != -1) {
                    supplier = sModel.getSupplierById(supplierId);
                }

                request.setAttribute("supplier", supplier);

                if (request.getParameter("delete") != null) {
                    int delete = Integer.parseInt((String) request.getParameter("delete"));
                    String supplierName = "";

                    if (supplier.getCompany().isEmpty()) {
                        supplierName = supplier.getName() + " " + supplier.getSurname();
                    } else {
                        supplierName = supplier.getCompany();
                    }

                    switch (delete) {
                        case 0:
                            String confirm = "<a href=\"/TEDex/" + Service.SUPPLIER.toString() + "?supplierId=" + supplierId + "&amp;delete=1\">Επιβεβαίωση</a>";
                            response.setHeader("warning", "Πρόκειται να διαγράψετε τον προμηθευτή " + supplierName + ". Είστε σίγουροι γιαυτό?  " + confirm);
                            break;
                        case 1:
                            sModel.deleteSupplier(supplierId);
                            response.sendRedirect("/TEDex/" + Service.SUPPLIER.toString());
                            return;
                        default:
                            throw new AssertionError();
                    }
                }

                request.getRequestDispatcher("/editSupply").forward(request, response);
            } else {
                ArrayList<Supplier> suppliers = (ArrayList<Supplier>) sModel.getSuppliers();

                request.setAttribute("suppliers", suppliers);
                request.getRequestDispatcher("/suppliers").forward(request, response);
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
//        processRequest(request, response);\
        Boolean hasChanges = false;
        request.setCharacterEncoding("UTF-8");
        try {

            SupplierModel sModel = new SupplierModel();
            int supplierId = Integer.parseInt((String) request.getParameter("supplierId"));


            Supplier supplier = sModel.getSupplierById(Integer.parseInt((String) request.getParameter("supplierId")));

            Supplier newSupplier = new Supplier();
            newSupplier.setName((String) request.getParameter("name"));
            newSupplier.setSurname((String) request.getParameter("surname"));
            newSupplier.setCompany((String) request.getParameter("company"));
            newSupplier.setAddress((String) request.getParameter("address"));
            newSupplier.setPhone((String) request.getParameter("phone"));
            newSupplier.setEmail((String) request.getParameter("email"));

            if (supplierId != -1) {
                newSupplier.setId(supplierId);
                sModel.updateSupplier(newSupplier);
                response.setHeader("message", "Οι αλλαγες σας αποθηκεύτηκαν με επιτυχία!");
            } else {
                sModel.addSupplier(newSupplier);
            }

            response.sendRedirect("/TEDex/" + Service.SUPPLIER.toString());

        } catch (SQLException sqlEx) {
            response.setHeader("error", sqlEx.getMessage());
            request.getRequestDispatcher(Service.SUPPLIER.toString()).forward(request, response);
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
