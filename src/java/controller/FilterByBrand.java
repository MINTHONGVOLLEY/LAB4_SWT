/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import molder.Category;
import molder.Product;

/**
 *
 * @author admin
 */
@WebServlet(name = "FilterByBrand", urlPatterns = {"/filter-brand"})
public class FilterByBrand extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FilterByBrand</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterByBrand at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] brands = request.getParameterValues("brand");
        String indexParam = request.getParameter("index");

        ProductDAO pdao = new ProductDAO();
        CategoryDAO cd = new CategoryDAO();

        List<String> selectedBrands = new ArrayList<>();
        if (brands != null) {
            for (String brand : brands) {
                selectedBrands.add(brand);
            }
        }

        int index = 1;

        try {
            if (indexParam != null && !indexParam.isEmpty()) {
                index = Integer.parseInt(indexParam);
            }
        } catch (Exception e) {
        }

        // #1 get total product
        int count = pdao.getTotalProduct(); // 12sp
        int endPage = count / 6; // trường hợp có đủ 12 sp
        if (count % 6 != 0) {
            endPage++;
        }

        List<Category> listCc = cd.getAllCategory();
        List<Product> list = pdao.getProductsByBrandsWithPaging(brands, index);
        request.setAttribute("listP", list);
        request.setAttribute("listC", listCc);
        request.setAttribute("endP", endPage);
        request.setAttribute("filter", selectedBrands);
        request.setAttribute("index", index);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
