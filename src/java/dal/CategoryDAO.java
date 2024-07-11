/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import molder.Category;

public class CategoryDAO extends DBContext {
    // get all category

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        try {
            String sql = "Select * from Category";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("categoryId"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setCategoryStatus(rs.getInt("categoryStatus"));
                list.add(c);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // get category by id
    public Category getCategoryById(int categoryId) {
   
        try {
            String sql = "Select * from Category Where CategoryId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
//                c = new Category();
//                c.setCategoryId(rs.getInt("categoryId"));
//                c.setCategoryName(rs.getString("categoryName"));
//                c.setCategoryStatus(rs.getInt("categoryStatus"));
                return new Category(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }
    
    // check name cate 
        public Category checkNameCate(String categoryName) {
   
        try {
            String sql = "Select * from Category Where categoryName = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, categoryName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new Category(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }
    // add category

    public void insertCategory(Category category) {
        try {
            String sql = "INSERT INTO Category (CategoryId, CategoryName, CategoryStatus) VALUES (?, ?, ?)";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, category.getCategoryId());
            ps.setString(2, category.getCategoryName());
            ps.setInt(3, category.getCategoryStatus());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCategory(int categoryId) {
        try {
            String sql = "DELETE FROM Category WHERE CategoryId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// update cate

    public void updateCategory(Category category) {
        try {
            String sql = "UPDATE Category SET CategoryName = ?, CategoryStatus = ? WHERE CategoryId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getCategoryStatus());
            ps.setInt(3, category.getCategoryId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categories = categoryDAO.getAllCategory();

        for (Category category : categories) {
            System.out.println("Category ID: " + category.getCategoryId());
            System.out.println("Category Name: " + category.getCategoryName());
            System.out.println("Category Status: " + category.getCategoryStatus());
            System.out.println("-----------------------");
        }
    }

}
