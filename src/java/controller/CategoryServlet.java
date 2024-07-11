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
import java.util.List;
import molder.Category;
import molder.Product;

/**
 *
 * @author admin
 */
@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

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
        String cateID = request.getParameter("categoryId");
        String indexParam = request.getParameter("index");
        int index = 1;
        try {
            if (indexParam != null && !indexParam.isEmpty()) {
                index = Integer.parseInt(indexParam);
            }
        } catch (NumberFormatException e) {
            // Handle invalid indexParam (e.g., non-numeric value)
            e.printStackTrace();
            // You can set a default value or handle this error case as appropriate
        }
        // Đặt cateID thành 0 nếu nó là null hoặc rỗng
        if (cateID == null || cateID.isEmpty()) {
            cateID = "0";
        }

        ProductDAO pdao = new ProductDAO();
        CategoryDAO cd = new CategoryDAO();

        List<Category> listCc = cd.getAllCategory();
          List<Product> list = pdao.pagingProduct(index);

        if ("0".equals(cateID)) {
            // Nếu categoryId là "0", hiển thị tất cả sản phẩm từ tất cả danh mục
            list =pdao.pagingProduct(index);
        } else {
            // Ngược lại, lấy danh sách sản phẩm từ danh mục được chỉ định
            list = pdao.getProductListByCategory(cateID);
        }

        
        // #1 get total product
        int count = pdao.getTotalProduct(); // 12sp
        int endPage = count / 6; // trường hợp có đủ 12 sp
        if (count % 6 != 0) {
            endPage++;
        }
        // Gửi danh sách sản phẩm đã phân trang, số trang và danh sách danh mục tới Home.jsp
        request.setAttribute("listP", list);
           request.setAttribute("endP", endPage);
        request.setAttribute("listC", listCc);
        request.setAttribute("view", cateID);
        request.getRequestDispatcher("Home.jsp").forward(request, response);

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
        processRequest(request, response);
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
// ProductDAO pdao = new ProductDAO();
//        CategoryDAO cdao = new CategoryDAO();
//        // get Product
//        List<Product> list1 = pdao.getAllProduct();
//        // get all category
//        List<Category> listCc = cdao.getAllCategory();
//
//        String sortBy = request.getParameter("sortBy");
//        if (sortBy == null || sortBy.isEmpty() || sortBy.equals("Default")) {
//
//        } else {
//            // Ngược lại, sử dụng danh sách sản phẩm theo giá đã được sắp xếp
//            List<Product> sortedProducts = pdao.getAllProductByPrice(sortBy);
//            list1 = sortedProducts;
//        }
//
//        int page, numperpage = 6;
//        int size = list1.size();
//
//        // tinhs so trang
//        int num = (size % 6 == 0 ? (size / 6) : (size / 6) + 1); // soos trang
//        String xpage = request.getParameter("page");
//        if (xpage == null) {
//            page = 1;
//        } else {
//            page = Integer.parseInt(xpage);
//        }
//
//        int start, end;
//        start = (page - 1) * numperpage;
//        end = Math.min(page * numperpage, size);
//        List<Product> list = pdao.getListByPage(list1, start, end);
//
//        request.setAttribute("listP", list);
//        request.setAttribute("page", page);
//        request.setAttribute("num", num);
//        request.setAttribute("listC", listCc);
//        request.getRequestDispatcher("Home.jsp").forward(request, response);
}
