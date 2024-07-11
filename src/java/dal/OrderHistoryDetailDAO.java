/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL DN
 */
public class OrderHistoryDetailDAO extends DBContext {

    public void insertOrderHistoryDetail(String serialNumber, double price, int orderHistoryId, int productId) {
        try {
            String sql = """
                     INSERT INTO [OrderHistoryDetail] (SerialNumber, Price, OrderHistoryId, ProductId)
                     VALUES (?, ?, ?, ?)""";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, serialNumber);
            ps.setDouble(2, price);
            ps.setInt(3, orderHistoryId);
            ps.setInt(4, productId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderHistoryDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        OrderHistoryDetailDAO o = new OrderHistoryDetailDAO();
        o.insertOrderHistoryDetail("LA120", 1000, 2, 3);
    }
}
