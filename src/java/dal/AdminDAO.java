/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static dal.DBContext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import molder.Admins;

/**
 *
 * @author DELL DN
 */
public class AdminDAO extends DBContext {

    public Admins AdminLogin(String email, String password) {
        Admins admin = null;
        try {
            String sql = "Select * from Admin where AdminEmail= ? and AdminPassword= ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admins();
                admin.setAdminId(rs.getInt("AdminId"));
                admin.setAdminName(rs.getString("AdminName"));
                admin.setAdminAge(rs.getInt("AdminAge"));
                admin.setAdminEmail(rs.getString("AdminEmail"));
                admin.setAdminPassword(rs.getString("AdminPassword"));
                admin.setAdminGender(rs.getBoolean("AdminGender"));
                admin.setAdminAddress(rs.getString("AdminAddress"));
                admin.setAdminCity(rs.getString("AdminCity"));
                admin.setAdminAvatar(rs.getString("AdminAvatar"));
                admin.setAdminPhoneNumber(rs.getString("AdminPhoneNumber"));
                admin.setAdminDOB(rs.getDate("AdminDOB"));
                admin.setAdminStatus(rs.getBoolean("AdminStatus"));
                admin.setRoleId(rs.getInt("RoleId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }

}
