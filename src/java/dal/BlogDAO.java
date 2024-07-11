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
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;
import molder.Blog;

/**
 *
 * @author quanb
 */
public class BlogDAO extends DBContext {

    public List<Blog> getAllBlog() {
        try {
            String sql = "select * from Blog"; // Added space after "pi"

            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Blog> blog = new ArrayList<>();
            while (rs.next()) {
                Blog p = new Blog();
                p.setBlogid(rs.getInt("BlogId"));
                p.setBlogtitle(rs.getString("BlogTitle"));
                p.setBlogdate(rs.getString("BlogUpdateDate"));
                p.setBlogcontent(rs.getString("BlogContent"));
                p.setBlogthumnail(rs.getString("BlogThumbnail"));
                p.setAdminid(rs.getInt("AdminId"));
                p.setCategoryblogid(rs.getInt("CategoryBlogId"));
                p.setBlogstatus(rs.getInt("BlogStatus"));
                p.setImg(rs.getString("img"));
                p.setUrl(rs.getString("url"));

                blog.add(p);
            }
            return blog;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public Blog getBlogById(int blogId) {
        try {
            String sql = "SELECT * FROM Blog WHERE BlogId = ?";
            Connection con = DBContext(); // Giả sử đã có phương thức DBContext() để kết nối CSDL
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogid(rs.getInt("BlogId"));
                blog.setBlogtitle(rs.getString("BlogTitle"));
                blog.setBlogdate(rs.getString("BlogUpdateDate"));
                blog.setBlogcontent(rs.getString("BlogContent"));
                blog.setBlogthumnail(rs.getString("BlogThumbnail"));
                blog.setAdminid(rs.getInt("AdminId"));
                blog.setCategoryblogid(rs.getInt("CategoryBlogId"));
                blog.setBlogstatus(rs.getInt("BlogStatus"));
                blog.setImg(rs.getString("img"));
                blog.setUrl(rs.getString("url"));

                return blog;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
