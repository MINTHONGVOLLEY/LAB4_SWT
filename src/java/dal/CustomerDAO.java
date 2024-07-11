/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;


import molder.Customer;
import molder.CustomerWithRole;
import molder.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author hungp
 */
public class CustomerDAO extends DBContext {

    public boolean checkEmail(String email) {
        boolean checkRegister = false;
        try {
            String sql = "SELECT COUNT(*) FROM customer WHERE customerEmail = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    checkRegister = true;
                }
            }
        } catch (SQLException ex) {
        }
        return checkRegister;
    }

    public Customer getCustomerById(int id) {
        try {
            String sql = "select * from Customer where CustomerID = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt("customerId"));
            customer.setCustomerName(rs.getString("customerName"));
            customer.setCustomerAge(rs.getInt("customerAge"));
            customer.setCustomerEmail(rs.getString("customerEmail"));
            customer.setCustomerPassword(rs.getString("customerPassword"));
            customer.setCustomerGender(rs.getBoolean("customerGender"));
            customer.setCustomerAddress(rs.getString("customerAddress"));
            customer.setCustomerCity(rs.getString("customerCity"));
            customer.setCustomerAvatar(rs.getString("customerAvatar"));
            customer.setCustomerPhoneNumber(rs.getString("customerPhoneNumber"));
            customer.setCustomerDOB(rs.getDate("CustomerDOB"));
            customer.setCustomerStatus(rs.getBoolean("customerStatus"));
            return customer;

        } catch (SQLException ex) {
        }
        return null;

    }

    public void updateCustomer(Customer customer) {
        try {
            String sql = "update customer set CustomerName = ?, CustomerAge = ?, CustomerEmail = ?, CustomerGender = ?, CustomerAddress = ?, CustomerCity = ?, CustomerPhoneNumber = ?, CustomerDOB = ? Where CustomerID = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setInt(2, customer.getCustomerAge());
            ps.setString(3, customer.getCustomerEmail());
            ps.setBoolean(4, customer.isCustomerGender());
            ps.setString(5, customer.getCustomerAddress());
            ps.setString(6, customer.getCustomerCity());
            ps.setString(7, customer.getCustomerPhoneNumber());
            ps.setDate(8, new java.sql.Date(customer.getCustomerDOB().getTime()));
            ps.setInt(9, customer.getCustomerId());
            ps.executeUpdate();

        } catch (SQLException ex) {
        }

    }

    public String checkPasswordExist(int id) {
        try {
            String sql = "select CustomerPassword from customer where customerId = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("CustomerPassword");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public void updateUserPassword(Customer customer) {

        try {
            String sql = "update customer set customerPassword = ? Where customerId = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customer.getCustomerPassword());
            ps.setInt(2, customer.getCustomerId());
            ps.executeUpdate();

        } catch (SQLException ex) {
        }

    }

    public String getEmailIdByCustomerId(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection connection = DBContext();
        try {
            if (connection != null) {
                st = connection.prepareStatement("SELECT CustomerEmail FROM [Customer] WHERE CustomerId = ?");
                st.setInt(1, id);
                rs = st.executeQuery();
            }
            if (rs != null && rs.next()) {
                return rs.getString("CustomerEmail");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Customer CustomerLogin(String customerEmail, String customerPassword) {
        Customer customer = null;
        try {
            String sql = "Select * from Customer where CustomerEmail= ? and CustomerPassword= ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customerEmail);
            ps.setString(2, customerPassword);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerID"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setCustomerAge(rs.getInt("CustomerAge"));
                customer.setCustomerEmail(rs.getString("CustomerEmail"));
                customer.setCustomerPassword(rs.getString("CustomerPassword"));
                customer.setCustomerGender(rs.getBoolean("CustomerGender"));
                customer.setCustomerAddress(rs.getString("CustomerAddress"));
                customer.setCustomerCity(rs.getString("CustomerCity"));
                customer.setCustomerAvatar(rs.getString("CustomerAvatar"));
                customer.setCustomerPhoneNumber(rs.getString("CustomerPhoneNumber"));
                customer.setCustomerDOB(rs.getDate("CustomerDOB"));
                customer.setCustomerStatus(rs.getBoolean("CustomerStatus"));
                customer.setRoleId(rs.getInt("RoleId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public Customer checkAccountExist(String email) {
        Customer customer = null;
        try {
            String sql = "select * from Customer where CustomerEmail = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerID"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setCustomerAge(rs.getInt("CustomerAge"));
                customer.setCustomerEmail(rs.getString("CustomerEmail"));
                customer.setCustomerPassword(rs.getString("CustomerPassword"));
                customer.setCustomerGender(rs.getBoolean("CustomerGender"));
                customer.setCustomerAddress(rs.getString("CustomerAddress"));
                customer.setCustomerCity(rs.getString("CustomerCity"));
                customer.setCustomerAvatar(rs.getString("CustomerAvatar"));
                customer.setCustomerPhoneNumber(rs.getString("CustomerPhoneNumber"));
                customer.setCustomerDOB(rs.getDate("CustomerDOB"));
                customer.setCustomerStatus(rs.getBoolean("CustomerStatus"));
                customer.setRoleId(rs.getInt("RoleId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public void signup(String customerName, String customerEmail, String customerPassword, String customerCity, String customerPhoneNumber) {
        try {
            String sql = "INSERT INTO Customer (CustomerName, CustomerEmail, CustomerPassword, CustomerCity, CustomerPhoneNumber, CustomerStatus, RoleId) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, customerEmail);
            ps.setString(3, customerPassword);
            ps.setString(4, customerCity);
            ps.setString(5, customerPhoneNumber);
            ps.setBoolean(6, true); // Assuming that the CustomerStatus is set to true for a new customer
            ps.setInt(7, 1);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Customer> searchByEmail(String customerEmail) {
        List<Customer> list = new ArrayList<>();
        if (customerEmail == null || customerEmail.isEmpty()) {
            return list;
        }
        try {
            String sql = "SELECT * FROM Customer WHERE CustomerEmail LIKE ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + customerEmail + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("CustomerID"));
                customer.setCustomerName(rs.getString("CustomerName"));
                customer.setCustomerAge(rs.getInt("CustomerAge"));
                customer.setCustomerEmail(rs.getString("CustomerEmail"));
                customer.setCustomerPassword(rs.getString("CustomerPassword"));
                customer.setCustomerGender(rs.getBoolean("CustomerGender"));
                customer.setCustomerAddress(rs.getString("CustomerAddress"));
                customer.setCustomerCity(rs.getString("CustomerCity"));
                customer.setCustomerAvatar(rs.getString("CustomerAvatar"));
                customer.setCustomerPhoneNumber(rs.getString("CustomerPhoneNumber"));
                customer.setCustomerDOB(rs.getDate("CustomerDOB"));
                customer.setCustomerStatus(rs.getBoolean("CustomerStatus"));
                customer.setRoleId(rs.getInt("RoleId"));
                list.add(customer);
            }
            rs.close(); // Close ResultSet
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void updatePasswordCustomer(String customerPassword, String customerEmail) {

        try {
            String sql = "update Customer set  CustomerPassword = ? Where CustomerEmail = ? ";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customerPassword);
            ps.setString(2, customerEmail);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public byte[] getImageData(int id) {
        String sql = "select CustomerAvatar from Customer Where Customerid = ?";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBytes("customerAvatar");
            }
        } catch (SQLException ex) {
        }
        return null;
    }

    public boolean updateUserAvatar(InputStream inputStream, int id) {
        String sql = "update Customer set CustomerAvatar = ? Where CustomerId = ?";
        try {
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBlob(1, inputStream);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
        }
        return false;
    }

    public List<CustomerWithRole> getAllCustomers() throws SQLException {
        List<CustomerWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            st = con.prepareStatement("SELECT \n"
                    + "    C.CustomerID, \n"
                    + "    C.CustomerName, \n"
                    + "    C.CustomerAge, \n"
                    + "    C.CustomerEmail, \n"
                    + "    C.CustomerPassword, \n"
                    + "    C.CustomerGender, \n"
                    + "    C.CustomerAddress, \n"
                    + "    C.CustomerCity, \n"
                    + "    C.CustomerAvatar, \n"
                    + "    C.CustomerPhoneNumber, \n"
                    + "    C.CustomerDOB, \n"
                    + "    C.CustomerStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Customer C\n"
                    + "JOIN \n"
                    + "    [Role] R ON C.RoleId = R.RoleId;");
            rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getInt("CustomerAge"),
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerPassword"),
                        rs.getBoolean("CustomerGender"),
                        rs.getString("CustomerAddress"),
                        rs.getString("CustomerCity"),
                        rs.getString("CustomerAvatar"),
                        rs.getString("CustomerPhoneNumber"),
                        rs.getDate("CustomerDOB"),
                        rs.getBoolean("CustomerStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                CustomerWithRole customerWithRole = new CustomerWithRole(customer, role);
                list.add(customerWithRole);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return list;
    }

    public List<CustomerWithRole> getCustomersByName(String searchQuery) throws SQLException {
        List<CustomerWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    C.CustomerID, \n"
                    + "    C.CustomerName, \n"
                    + "    C.CustomerAge, \n"
                    + "    C.CustomerEmail, \n"
                    + "    C.CustomerPassword, \n"
                    + "    C.CustomerGender, \n"
                    + "    C.CustomerAddress, \n"
                    + "    C.CustomerCity, \n"
                    + "    C.CustomerAvatar, \n"
                    + "    C.CustomerPhoneNumber, \n"
                    + "    C.CustomerDOB, \n"
                    + "    C.CustomerStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Customer C\n"
                    + "JOIN \n"
                    + "    [Role] R ON C.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    C.CustomerName LIKE ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + searchQuery + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getInt("CustomerAge"),
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerPassword"),
                        rs.getBoolean("CustomerGender"),
                        rs.getString("CustomerAddress"),
                        rs.getString("CustomerCity"),
                        rs.getString("CustomerAvatar"),
                        rs.getString("CustomerPhoneNumber"),
                        rs.getDate("CustomerDOB"),
                        rs.getBoolean("CustomerStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                CustomerWithRole customerWithRole = new CustomerWithRole(customer, role);
                list.add(customerWithRole);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return list;
    }
    public List<CustomerWithRole> getCustomersByEmail (String searchQuery) throws SQLException {
        List<CustomerWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    C.CustomerID, \n"
                    + "    C.CustomerName, \n"
                    + "    C.CustomerAge, \n"
                    + "    C.CustomerEmail, \n"
                    + "    C.CustomerPassword, \n"
                    + "    C.CustomerGender, \n"
                    + "    C.CustomerAddress, \n"
                    + "    C.CustomerCity, \n"
                    + "    C.CustomerAvatar, \n"
                    + "    C.CustomerPhoneNumber, \n"
                    + "    C.CustomerDOB, \n"
                    + "    C.CustomerStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Customer C\n"
                    + "JOIN \n"
                    + "    [Role] R ON C.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    C.CustomerEmail LIKE ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + searchQuery + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getInt("CustomerAge"),
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerPassword"),
                        rs.getBoolean("CustomerGender"),
                        rs.getString("CustomerAddress"),
                        rs.getString("CustomerCity"),
                        rs.getString("CustomerAvatar"),
                        rs.getString("CustomerPhoneNumber"),
                        rs.getDate("CustomerDOB"),
                        rs.getBoolean("CustomerStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                CustomerWithRole customerWithRole = new CustomerWithRole(customer, role);
                list.add(customerWithRole);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return list;
    }
    public List<CustomerWithRole> getCustomersByPhone (String searchQuery) throws SQLException {
        List<CustomerWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    C.CustomerID, \n"
                    + "    C.CustomerName, \n"
                    + "    C.CustomerAge, \n"
                    + "    C.CustomerEmail, \n"
                    + "    C.CustomerPassword, \n"
                    + "    C.CustomerGender, \n"
                    + "    C.CustomerAddress, \n"
                    + "    C.CustomerCity, \n"
                    + "    C.CustomerAvatar, \n"
                    + "    C.CustomerPhoneNumber, \n"
                    + "    C.CustomerDOB, \n"
                    + "    C.CustomerStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Customer C\n"
                    + "JOIN \n"
                    + "    [Role] R ON C.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    C.CustomerPhoneNumber LIKE ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + searchQuery + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getInt("CustomerAge"),
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerPassword"),
                        rs.getBoolean("CustomerGender"),
                        rs.getString("CustomerAddress"),
                        rs.getString("CustomerCity"),
                        rs.getString("CustomerAvatar"),
                        rs.getString("CustomerPhoneNumber"),
                        rs.getDate("CustomerDOB"),
                        rs.getBoolean("CustomerStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                CustomerWithRole customerWithRole = new CustomerWithRole(customer, role);
                list.add(customerWithRole);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return list;
    }
    
    public List<CustomerWithRole> getCustomersByGender (int gender) throws SQLException {
        List<CustomerWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    C.CustomerID, \n"
                    + "    C.CustomerName, \n"
                    + "    C.CustomerAge, \n"
                    + "    C.CustomerEmail, \n"
                    + "    C.CustomerPassword, \n"
                    + "    C.CustomerGender, \n"
                    + "    C.CustomerAddress, \n"
                    + "    C.CustomerCity, \n"
                    + "    C.CustomerAvatar, \n"
                    + "    C.CustomerPhoneNumber, \n"
                    + "    C.CustomerDOB, \n"
                    + "    C.CustomerStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Customer C\n"
                    + "JOIN \n"
                    + "    [Role] R ON C.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    C.CustomerGender = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, gender);
            rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getInt("CustomerAge"),
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerPassword"),
                        rs.getBoolean("CustomerGender"),
                        rs.getString("CustomerAddress"),
                        rs.getString("CustomerCity"),
                        rs.getString("CustomerAvatar"),
                        rs.getString("CustomerPhoneNumber"),
                        rs.getDate("CustomerDOB"),
                        rs.getBoolean("CustomerStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                CustomerWithRole customerWithRole = new CustomerWithRole(customer, role);
                list.add(customerWithRole);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return list;
    }
    public List<CustomerWithRole> getCustomersByStatus (int status) throws SQLException {
        List<CustomerWithRole> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            Connection con = DBContext();
            String sql = "SELECT \n"
                    + "    C.CustomerID, \n"
                    + "    C.CustomerName, \n"
                    + "    C.CustomerAge, \n"
                    + "    C.CustomerEmail, \n"
                    + "    C.CustomerPassword, \n"
                    + "    C.CustomerGender, \n"
                    + "    C.CustomerAddress, \n"
                    + "    C.CustomerCity, \n"
                    + "    C.CustomerAvatar, \n"
                    + "    C.CustomerPhoneNumber, \n"
                    + "    C.CustomerDOB, \n"
                    + "    C.CustomerStatus, \n"
                    + "    R.RoleId, \n"
                    + "    R.RoleName, \n"
                    + "    R.RoleStatus\n"
                    + "FROM \n"
                    + "    Customer C\n"
                    + "JOIN \n"
                    + "    [Role] R ON C.RoleId = R.RoleId\n"
                    + "WHERE\n"
                    + "    C.CustomerStatus = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, status);
            rs = st.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getInt("CustomerAge"),
                        rs.getString("CustomerEmail"),
                        rs.getString("CustomerPassword"),
                        rs.getBoolean("CustomerGender"),
                        rs.getString("CustomerAddress"),
                        rs.getString("CustomerCity"),
                        rs.getString("CustomerAvatar"),
                        rs.getString("CustomerPhoneNumber"),
                        rs.getDate("CustomerDOB"),
                        rs.getBoolean("CustomerStatus")
                );
                Role role = new Role(
                        rs.getInt("RoleId"),
                        rs.getString("RoleName"),
                        rs.getBoolean("RoleStatus")
                );
                CustomerWithRole customerWithRole = new CustomerWithRole(customer, role);
                list.add(customerWithRole);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return list;
    }
    
    public static void main(String[] args) {
        CustomerDAO CusDao = new CustomerDAO();
        Customer cus = CusDao.getCustomerById(2);
        System.out.println(cus);
    }
}
