/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Chackout;

import dal.OrderHistoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import molder.Admins;
import molder.Customer;
import molder.OrderHistory;

/**
 *
 * @author DELL DN
 */
public class CheckoutQRControl extends HttpServlet {

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
            out.println("<title>Servlet CheckoutQRControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutQRControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private static final String EMAIL = "thongdnmhe176561@fpt.edu.vn";
    private static final String PASSWORD = "babv cccv blbu tvqj";
    OrderHistoryDAO ohd = new OrderHistoryDAO();
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
        String amount = request.getParameter("amount");
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        Admins admin = (Admins) session.getAttribute("admin");
        String email = null;

        if (customer != null) {
            email = customer.getCustomerEmail();
        } else if (admin != null) {
            email = admin.getAdminEmail();
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        // Get all the customer's information.
        int orderHisId = ohd.getOrderHistoryId();
        OrderHistory orderHis = ohd.getOrderHisByOrderHisId(orderHisId);

        double totalMoney = orderHis.getTotalMoney();
        String nameCustomer = orderHis.getName();
        String phoneNumber = orderHis.getPhone();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session mailSession = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("FBTComputer: Order confirmation to verify QR payment");

            StringBuilder html = new StringBuilder();
            html.append("<html><body>");
            html.append("<img class=\"m-auto\" src=\"https://drive.google.com/uc?export=view&id=1s5NZaW90jz9MIGlh2CnJU1dL1arP03MQ\" alt=\"QR Code\">");
            html.append("<h2>Banking Guide</h2>");
            html.append("<ul>");
            html.append("<li>Account Name: DO ANH QUAN</li>");
            html.append("<li>Account Number: 0822 7847 86</li>");
            html.append("<li>Bank Name: MB BANK</li>");
            html.append("<li>Transfer Amount: ").append(totalMoney).append("</li>");
            html.append("<li>Banking Details: ").append(nameCustomer).append("_").append(phoneNumber).append("</li>");
            html.append("<li>Payment is accepted within 24 hours, no support for transactions after!</li>");
            
            html.append("</ul>");
            html.append("<p>If you have any issues, please contact our customer support for assistance by following the phone number - 0865024982</p>");
            html.append("<div class=\"footer\">Thank you for your purchase!</div>");
            html.append("</div>");
            html.append("</body></html>");

            message.setContent(html.toString(), "text/html");

            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        //orderhistorydao.insertOrderHistory(orderId, "Pending", "1", "QR");
        request.setAttribute("cardType", "QR");
        //request.setAttribute("orderId", orderId);
        request.getRequestDispatcher("CheckoutSuccess.jsp").forward(request, response);

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

}
