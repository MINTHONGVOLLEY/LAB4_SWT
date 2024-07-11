/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Cart.Cart;
import Cart.CartDetail;
import Utils.Encryptor;
import dal.AdminDAO;
import dal.CartDAO;
import dal.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import molder.Customer;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;
import molder.Admins;
import molder.Product;

/**
 *
 * @author DELL DN
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})

public class LoginServlet extends HttpServlet {

    CartDAO cartdao = new CartDAO();

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        //get email , pass from cookie
        Cookie arr[] = request.getCookies();
        for (Cookie o : arr) {
            if (o.getName().equals("u_Email")) {
                request.setAttribute("email", o.getValue());
            }
            if (o.getName().equals("u_Password")) {
                request.setAttribute("password", o.getValue());
            }

            if (o.getName().equals("r_reMem")) {
                request.setAttribute("remember_me", o.getValue());
            }
        }
        // set email , pass into login form
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        try {
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");
            String rememberMe = request.getParameter("remember_me");
            CustomerDAO customerDAO = new CustomerDAO();
            AdminDAO adminDAO = new AdminDAO();

            // Encrypt the password using SHA1
            String encryptedPassword = Encryptor.toSHA1(password);

            // Attempt to log in as admin or customer
            Admins admin = adminDAO.AdminLogin(email, encryptedPassword);
            Customer customer = customerDAO.CustomerLogin(email, encryptedPassword);

            // If either a customer or admin is found, set session attributes and cookies
            if (customer != null || admin != null) {
                HttpSession session = request.getSession();
                Cart cart = new Cart();

                if (customer != null) {
                    setupSessionAndCart(session, customer.getCustomerId(), customer.getRoleId(), cart);
                    session.setAttribute("customer", customer);
                }

                if (admin != null) {
                    setupSessionAndCart(session, admin.getAdminId(), admin.getRoleId(), cart);
                    session.setAttribute("admin", admin);
                }

                setupCookies(response, email, password, rememberMe);
                request.getRequestDispatcher("home").forward(request, response);
            } else {
                // If login fails, set error message and forward back to login page
                request.setAttribute("msg", "Email or password incorrect!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Log the exception and show a generic error message
            e.printStackTrace();
            request.setAttribute("msg", "An error occurred during login. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

// Helper method to set up session and cart
    private void setupSessionAndCart(HttpSession session, int userId, int roleId, Cart cart) {
        CartDAO cartDAO = new CartDAO();
        Cart dbCart = cartDAO.getCartByCustomerId(userId, roleId);
        List<Product> cartDetailList = cartDAO.getCartDetailsByCartId(dbCart.getCartId());
        cart.addItems(cartDetailList);
        session.setAttribute("cart", cart);
    }

// Helper method to set up cookies
    private void setupCookies(HttpServletResponse response, String email, String password, String rememberMe) {
        Cookie emailCookie = new Cookie("u_Email", email);
        Cookie passwordCookie = new Cookie("u_Password", password);
        Cookie rememberMeCookie = new Cookie("r_reMem", rememberMe);

        int maxAge = (rememberMe != null) ? 60 * 60 * 24 * 30 * 3 : 0; // 3 months if rememberMe is checked, otherwise 0
        emailCookie.setMaxAge(60 * 60 * 24 * 30 * 3); // 3 months
        passwordCookie.setMaxAge(maxAge);
        rememberMeCookie.setMaxAge(maxAge);

        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);
        response.addCookie(rememberMeCookie);
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
