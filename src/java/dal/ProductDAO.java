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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import molder.Product;
import molder.ProductImange;
import molder.Specification;

/**
 *
 * @author admin
 */
public class ProductDAO extends DBContext {

    public Product getProductByProductId(String productId) {
        Product product = null;
        try {
            String sql = "SELECT p.*, s.Color, s.Size, s.Weight, s.Manufacturer, pi.ProductImageId, pi.Image "
                    + "FROM Product p "
                    + "LEFT JOIN Specification s ON p.ProductId = s.ProductId "
                    + "LEFT JOIN ProductImage pi ON p.ProductId = pi.ProductId "
                    + "WHERE p.ProductId = ?";

            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setProductPrice(rs.getFloat("ProductPrice"));
                product.setProductQuantity(rs.getInt("ProductQuantity"));
                product.setProductBrand(rs.getString("ProductBrand"));
                product.setProductImage(rs.getString("productImage"));
                product.setProductStatus(rs.getBoolean("ProductStatus"));
                product.setCreateBy(rs.getString("CreateBy"));
                product.setCreateDate(rs.getDate("CreateDate"));
                product.setModifyBy(rs.getString("ModifyBy"));
                product.setModifyDate(rs.getDate("ModifyDate"));

                Specification spec = new Specification();
                spec.setColor(rs.getString("Color"));
                spec.setSize(rs.getString("Size"));
                spec.setWeight(rs.getString("Weight"));
                spec.setManufacturer(rs.getString("Manufacturer"));
                product.setSpecification(spec);

                List<ProductImange> productImages = new ArrayList<>();
                do {
                    int imageId = rs.getInt("ProductImageId");
                    if (imageId > 0) {
                        ProductImange image = new ProductImange();
                        image.setProductImageId(imageId);
                        image.setProductId(rs.getInt("ProductId"));
                        image.setImage(rs.getString("Image"));
                        productImages.add(image);
                    }
                } while (rs.next());
                product.setProductImanges(productImages);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    // get all product
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";

        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductId"),
                        rs.getString("ProductName"),
                        rs.getString("ProductDescription"),
                        rs.getFloat("ProductPrice"),
                        rs.getInt("ProductQuantity"),
                        rs.getString("ProductBrand"),
                        rs.getString("ProductImage"),
                        rs.getBoolean("ProductStatus"),
                        rs.getString("CreateBy"),
                        rs.getDate("CreateDate"),
                        rs.getString("ModifyBy"),
                        rs.getDate("ModifyDate")
                );
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    // count number product
    public int getTotalProduct() {
        try {
            String sql = "select count (*) from Product";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // search product by name;
    public List<Product> searchAndPagingProductByName(String productName, int index) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product "
                + "WHERE ProductName LIKE ? "
                + "ORDER BY ProductId "
                + "OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";

        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + productName + "%");
            ps.setInt(2, (index - 1) * 10);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("ProductId"),
                            rs.getString("ProductName"),
                            rs.getString("ProductDescription"),
                            rs.getFloat("ProductPrice"),
                            rs.getInt("ProductQuantity"),
                            rs.getString("ProductBrand"),
                            rs.getString("ProductImage"),
                            rs.getBoolean("ProductStatus"),
                            rs.getString("CreateBy"),
                            rs.getDate("CreateDate"),
                            rs.getString("ModifyBy"),
                            rs.getDate("ModifyDate")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

//  product by categoryId
    public List<Product> getProductListByCategory(String categoryId) {
        try {
            String sql = "SELECT TOP 3 * FROM Product WHERE CategoryId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, categoryId);
            ResultSet rs = ps.executeQuery();

            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setProductPrice(rs.getFloat("ProductPrice"));
                product.setProductQuantity(rs.getInt("ProductQuantity"));
                product.setProductBrand(rs.getString("ProductBrand"));
                product.setProductImage(rs.getString("ProductImage"));
                product.setProductStatus(rs.getBoolean("ProductStatus"));
                product.setCreateBy(rs.getString("CreateBy"));
                product.setCreateDate(rs.getDate("CreateDate"));
                product.setModifyBy(rs.getString("ModifyBy"));
                product.setModifyDate(rs.getDate("ModifyDate"));

                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
// price product theo thứ tự tăng , giảm

    public List<Product> getAllProductByPrice(String sortBy) {
        String orderBy = "";
        String orderColumn = "ProductPrice";

        // Check and build the ORDER BY clause of the query
        if ("LowToHigh".equals(sortBy)) {
            orderBy = "ASC";
        } else if ("HighToLow".equals(sortBy)) {
            orderBy = "DESC";
        } else if ("AToZ".equals(sortBy)) {
            orderColumn = "ProductName";
            orderBy = "ASC";
        } else if ("ZToA".equals(sortBy)) {
            orderColumn = "ProductName";
            orderBy = "DESC";
        } else {
            orderBy = "";
        }

        String sql = "SELECT TOP 6 * FROM Product";
        if (!orderBy.isEmpty()) {
            sql += " ORDER BY " + orderColumn + " " + orderBy;
        }

        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            List<Product> products = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setProductPrice(rs.getFloat("ProductPrice"));
                product.setProductQuantity(rs.getInt("ProductQuantity"));
                product.setProductBrand(rs.getString("ProductBrand"));
                product.setProductImage(rs.getString("ProductImage"));
                product.setProductStatus(rs.getBoolean("ProductStatus"));
                product.setCreateBy(rs.getString("CreateBy"));
                product.setCreateDate(rs.getDate("CreateDate"));
                product.setModifyBy(rs.getString("ModifyBy"));
                product.setModifyDate(rs.getDate("ModifyDate"));

                products.add(product);
            }

            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    // dua ra tung trang 1
    public List<Product> getListByPage(List<Product> list,
            int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));

        }
        return arr;

    }

    // sort by brand
    public List<Product> getProductsByBrandsWithPaging(String[] brands, int index) {
        if (brands == null || brands.length == 0) {
            return new ArrayList<>(); // Trả về danh sách trống nếu không có thương hiệu nào được chỉ định
        }

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Product WHERE ProductBrand IN (");
        for (int i = 0; i < brands.length; i++) {
            queryBuilder.append("?");
            if (i < brands.length - 1) {
                queryBuilder.append(",");
            }
        }
        queryBuilder.append(") ORDER BY ProductId OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY");
        String query = queryBuilder.toString();

        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(query)) {

            for (int i = 0; i < brands.length; i++) {
                ps.setString(i + 1, brands[i]);
            }
            ps.setInt(brands.length + 1, (index - 1) * 10); // Set index for pagination

            ResultSet rs = ps.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setProductPrice(rs.getFloat("ProductPrice"));
                product.setProductQuantity(rs.getInt("ProductQuantity"));
                product.setProductBrand(rs.getString("ProductBrand"));
                product.setProductImage(rs.getString("ProductImage"));
                product.setProductStatus(rs.getBoolean("ProductStatus"));
                product.setCreateBy(rs.getString("CreateBy"));
                product.setCreateDate(rs.getDate("CreateDate"));
                product.setModifyBy(rs.getString("ModifyBy"));
                product.setModifyDate(rs.getDate("ModifyDate"));
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // pagging product
    public List<Product> pagingProduct(int index) {

        try {

            String sql = "Select * from Product \n"
                    + "ORDER BY ProductId\n"
                    + "OFFSET ? ROW FETCH NEXT 10 ROWS ONLY";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 10);
            ResultSet rs = ps.executeQuery();
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("ProductId"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setProductPrice(rs.getFloat("ProductPrice"));
                product.setProductQuantity(rs.getInt("ProductQuantity"));
                product.setProductBrand(rs.getString("ProductBrand"));
                product.setProductImage(rs.getString("ProductImage"));
                product.setProductStatus(rs.getBoolean("ProductStatus"));
                product.setCreateBy(rs.getString("CreateBy"));
                product.setCreateDate(rs.getDate("CreateDate"));
                product.setModifyBy(rs.getString("ModifyBy"));
                product.setModifyDate(rs.getDate("ModifyDate"));

                list.add(product);
            }
            return list;

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    // pagging new version
    public List<Product> pagingProduct(int pageIndex, int pageSize) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product ORDER BY ProductId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            // Calculate the offset
            int offset = (pageIndex - 1) * pageSize;

            // Set the parameters for offset and fetch
            ps.setInt(1, offset);
            ps.setInt(2, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("ProductId"));
                    product.setProductName(rs.getString("ProductName"));
                    product.setProductDescription(rs.getString("ProductDescription"));
                    product.setProductPrice(rs.getFloat("ProductPrice"));
                    product.setProductQuantity(rs.getInt("ProductQuantity"));
                    product.setProductBrand(rs.getString("ProductBrand"));
                    product.setProductImage(rs.getString("ProductImage"));
                    product.setProductStatus(rs.getBoolean("ProductStatus"));
                    product.setCreateBy(rs.getString("CreateBy"));
                    product.setCreateDate(rs.getDate("CreateDate"));
                    product.setModifyBy(rs.getString("ModifyBy"));
                    product.setModifyDate(rs.getDate("ModifyDate"));

                    list.add(product);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // lấy ra top 2 sản phẩm tương tự
    public List<Product> getTop2SimilarProducts(String productId) {
        List<Product> similarProducts = new ArrayList<>();
        String sql = "WITH Category AS ("
                + "    SELECT CategoryId FROM Product WHERE ProductId = ?"
                + "), SimilarProducts AS ("
                + "    SELECT TOP 2 p.ProductId, p.ProductName, p.ProductDescription, p.ProductPrice, "
                + "           p.ProductQuantity, p.ProductBrand, p.ProductStatus, p.CreateBy, p.CreateDate, "
                + "           p.ModifyBy, p.ModifyDate, s.Color, s.Size, s.Weight, s.Manufacturer, "
                + "           pi.ProductImageId, pi.Image "
                + "    FROM Product p "
                + "    LEFT JOIN Specification s ON p.ProductId = s.ProductId "
                + "    LEFT JOIN ProductImage pi ON p.ProductId = pi.ProductId "
                + "    WHERE p.CategoryId = (SELECT CategoryId FROM Category) AND p.ProductId <> ? "
                + ")"
                + "SELECT * FROM SimilarProducts";
        try (Connection con = DBContext(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, productId);
            ps.setString(2, productId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("ProductId"));
                    product.setProductName(rs.getString("ProductName"));
                    product.setProductDescription(rs.getString("ProductDescription"));
                    product.setProductPrice(rs.getFloat("ProductPrice"));
                    product.setProductQuantity(rs.getInt("ProductQuantity"));
                    product.setProductBrand(rs.getString("ProductBrand"));
                    product.setProductStatus(rs.getBoolean("ProductStatus"));
                    product.setCreateBy(rs.getString("CreateBy"));
                    product.setCreateDate(rs.getDate("CreateDate"));
                    product.setModifyBy(rs.getString("ModifyBy"));
                    product.setModifyDate(rs.getDate("ModifyDate"));

                    Specification spec = new Specification();
                    spec.setColor(rs.getString("Color"));
                    spec.setSize(rs.getString("Size"));
                    spec.setWeight(rs.getString("Weight"));
                    spec.setManufacturer(rs.getString("Manufacturer"));
                    product.setSpecification(spec);

                    List<ProductImange> productImages = new ArrayList<>();
                    int imageId = rs.getInt("ProductImageId");
                    if (imageId > 0) {
                        ProductImange image = new ProductImange();
                        image.setProductImageId(imageId);
                        image.setProductId(rs.getInt("ProductId"));
                        image.setImage(rs.getString("Image"));
                        productImages.add(image);
                    }
                    product.setProductImanges(productImages);

                    similarProducts.add(product);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return similarProducts;
    }

    public void UpdateProductQuantity(int productId, int productQuantity) {
        try {
            String sql = "UPDATE dbo.Product SET ProductQuantity = ? WHERE ProductId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productQuantity);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            Object ex = null;
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCategoryName(int productId) {
        String categoryName = null;
        try {
            String sql = "SELECT c.CategoryName FROM Product p JOIN Category c ON p.CategoryId = c.CategoryId WHERE p.ProductId = ?";
            Connection con = DBContext();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                categoryName = rs.getString("CategoryName");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoryName;
    }

    public static void main(String[] args) {
        ProductDAO productdao = new ProductDAO();
        List<Product> list = productdao.getAllProduct();
        for (Product product : list) {
            productdao.UpdateProductQuantity(product.getProductId(), 10);
        }
    }

    // Gọi phương thức getProductByProductId với một productId cụ thể (ví dụ: "yourProductId")
//    Product product =ProductDAO .getProductByProductId("1");
//
//    // Kiểm tra xem product có null không
//    if (product != null) {
//        // In ra thông tin của sản phẩm
//        System.out.println("Product ID: " + product.getProductId());
//        System.out.println("Product Name: " + product.getProductName());
//        System.out.println("Product Description: " + product.getProductDescription());
//        System.out.println("Product Price: " + product.getProductPrice());
//        // In ra các thông tin khác của sản phẩm
//        
//        // In ra các hình ảnh liên quan
//        List<ProductImange> images = product.getProductImanges();
//        System.out.println("Product Images:");
//        for (ProductImange image : images) {
//            System.out.println("Image ID: " + image.getProductImageId());
//            System.out.println("Image URL: " + image.getImage());
//        }
//    } else {
//        System.out.println("Product not found!");
//    }
}
