/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Product;
import entities.Storage;
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
import model.ProductModel;
import model.StorageModel;
import model.SupplierModel;

/**
 *
 * @author Constantine
 */
public class ProductController extends HttpServlet {

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
            out.println("<title>Servlet ProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
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
        ProductModel pModel = new ProductModel();
        StorageModel sModel = new StorageModel();
        SupplierModel supModel = new SupplierModel();

        try {
            ArrayList<Permission> permissions = (ArrayList<Permission>) request.getSession().getAttribute("permissions" + request.getSession().getId());

            if (!permissions.contains(Permission.administrator)) {
                if (!permissions.contains(Permission.product_write)
                        && !permissions.contains(Permission.product_read)) {
                    response.setHeader("error", "Δεν έχετε δικαιώματα πρόσβασης στα προϊόντα.");
                    request.getRequestDispatcher("/").forward(request, response);
                }
            }

            Product product = new Product();
            Storage storage = new Storage();
            Supplier supplier = new Supplier();


            if (request.getParameter("productId") != null) {
                ArrayList<Storage> storages = (ArrayList<Storage>) sModel.getStorages();
                ArrayList<Supplier> suppliers = (ArrayList<Supplier>) supModel.getSuppliers();

                int productId = Integer.parseInt((String) request.getParameter("productId"));

                if (productId != -1) {
                    product = pModel.getProductById(productId);
                    storage = sModel.getStorageById(product.getStorageId());
                    supplier = supModel.getSupplierById(product.getSupplierId());
                }

                request.setAttribute("product", product);

                request.setAttribute("storages", storages);
                request.setAttribute("suppliers", suppliers);

                request.setAttribute("storage", storage);
                request.setAttribute("supplier", supplier);

                if (request.getParameter("delete") != null) {
                    int delete = Integer.parseInt((String) request.getParameter("delete"));

                    switch (delete) {
                        case 0:
                            String confirm = "<a href=\"/TEDex/" + Service.PRODUCT.toString() + "?productId=" + productId + "&amp;delete=1\">Επιβεβαίωση</a>";
                            response.setHeader("warning", "Πρόκειται να διαγράψετε το προϊόν " + product.getName() + ". Είστε σίγουροι γιαυτό?  " + confirm);
                            break;
                        case 1:
                            pModel.deleteProduct(productId);
                            response.sendRedirect("/TEDex/" + Service.PRODUCT.toString());
                            return;
                        default:
                            throw new AssertionError();
                    }
                }

                request.getRequestDispatcher("/editProduct").forward(request, response);
            } else {

                ArrayList<Product> products;
                if (request.getParameter("storageId") != null) {
                    int storageId = Integer.parseInt((String) request.getParameter("storageId"));
                    products = (ArrayList<Product>) pModel.getProductsByStorage(storageId);
                } else if (request.getParameter("supplierId") != null) {
                    int supplierId = Integer.parseInt((String) request.getParameter("supplierId"));
                    products = (ArrayList<Product>) pModel.getProductsBySupplier(supplierId);
                } else {
                    products = (ArrayList<Product>) pModel.getProducts();
                }

                request.setAttribute("products", products);
                request.getRequestDispatcher("/products").forward(request, response);
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
//        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        try {

            ProductModel pModel = new ProductModel();
            int productId = Integer.parseInt((String) request.getParameter("productId"));

            Product newProduct = new Product();
            newProduct.setName((String) request.getParameter("name"));
            newProduct.setCode((String) request.getParameter("code"));
            newProduct.setDescription((String) request.getParameter("description"));
            newProduct.setQuantity(Integer.parseInt((String) request.getParameter("quantity")));
            newProduct.setPrice(Double.parseDouble((String) request.getParameter("price")));
            newProduct.setCost(Double.parseDouble((String) request.getParameter("cost")));
            newProduct.setStorageId(Integer.parseInt((String) request.getParameter("storageId")));
            newProduct.setSupplierId(Integer.parseInt((String) request.getParameter("supplierId")));

            if (productId == -1 && pModel.exists((String) request.getParameter("name"), (String) request.getParameter("code"))) {
                response.setHeader("error", "Υπάρχει ήδη προϊόν με τα ίδια χαρακτηριστικά '" + (String) request.getParameter("name") + "', '" + (String) request.getParameter("code") + "'.");
                request.setAttribute("product", newProduct);
                request.getRequestDispatcher("/editProduct").forward(request, response);
            } else {

                if (productId != -1) {
                    newProduct.setId(productId);
                    pModel.updateProduct(newProduct);


//                    response.setContentType("text/html;charset=UTF-8");
//                    PrintWriter out = response.getWriter();
//                    try {
//                        /* TODO output your page here. You may use following sample code. */
//                        out.println("<!DOCTYPE html>");
//                        out.println("<html>");
//                        out.println("<head>");
//                        out.println("<title>Servlet ProductServlet</title>");
//                        out.println("</head>");
//                        out.println("<body>");
//                        out.println("<h1> " + newProduct.getName() + "</h1>");
//                        out.println("<h1> " + newProduct.getCode()+ "</h1>");
//                        out.println("<h1> " + newProduct.getDescription()+ "</h1>");
//                        out.println("<h1> " + newProduct.getQuantity()+ "</h1>");
//                        out.println("<h1> " + newProduct.getCost()+ "</h1>");
//                        out.println("<h1> " + newProduct.getPrice()+ "</h1>");
//                        out.println("<h1> " + newProduct.getStorageId()+ "</h1>");
//                        out.println("<h1> " + newProduct.getSupplierId()+ "</h1>");
//                        out.println("<a href='#' onclick='history.go(-1)'>Go back</a>");
//                        out.println("</body>");
//                        out.println("</html>");
//                    } finally {
//                        out.close();
//                    }






                    response.setHeader("message", "Οι αλλαγες σας αποθηκεύτηκαν με επιτυχία!");
                } else {
                    pModel.addProduct(newProduct);
                }

                response.sendRedirect("/TEDex/" + Service.PRODUCT.toString());
            }

        } catch (SQLException sqlEx) {
            response.setHeader("error", sqlEx.getMessage());
            request.getRequestDispatcher(Service.PRODUCT.toString()).forward(request, response);
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
