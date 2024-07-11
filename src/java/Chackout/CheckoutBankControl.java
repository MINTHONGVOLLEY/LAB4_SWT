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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CheckoutBankControl extends HttpServlet {

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
            out.println("<title>Servlet CheckoutBankControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutBankControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        String amount = request.getParameter("vnp_Amount");
        String cardType = request.getParameter("vnp_CardType");
        String vnp_PayDate = request.getParameter("vnp_PayDate");
        int orderHisId = ohd.getOrderHistoryId();

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date payDate = null;
        try {
            payDate = inputFormat.parse(vnp_PayDate);
        } catch (ParseException ex) {
            Logger.getLogger(CheckoutBankControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        ohd.updateIsSuccessById(ohd.getOrderHisByOrderHisId(orderHisId).getUserId(), ohd.getOrderHisByOrderHisId(orderHisId).getRoleId());
        long amounts = Integer.parseInt(amount) / 100;
        request.setAttribute("amount", amounts);
        request.setAttribute("cardType", cardType);
        request.setAttribute("payDate", payDate);
        request.setAttribute("orderId", orderHisId);
        request.getRequestDispatcher("CheckoutSuccess.jsp").forward(request, response);

    }

    private static final String EMAIL = "thongdnmhe176561@fpt.edu.vn";
    private static final String PASSWORD = "babv cccv blbu tvqj";

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
        String date = orderHis.getOrderDate();

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
            message.setSubject("FBTComputer: Order confirmation to verify card payment");

            StringBuilder html = new StringBuilder();
            html.append("<html><body>");
            html.append("<div>");
            html.append("<h3>Thanks for your order. It has been received.</h3>");
            html.append("<h4>Date: ").append(date).append("</h4>");
            html.append("<h4>Total: ").append(totalMoney).append(" VNĐ</h4>");
            html.append("<h4>Payment method: ").append("ATM").append("</h4>");
            html.append("<h4>Status: SUCCESSFUL</h4>");
            html.append("</div>");
            html.append("</body></html>");

            message.setContent(html.toString(), "text/html");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
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
