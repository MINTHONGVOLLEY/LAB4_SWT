/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package molder;

/**
 *
 * @author DELL DN
 */
public class OrderDetail {

    private int orderid, productid, quantity;
    private double price;

    private String productImage;
    private String productName;
    private String productBrand;
    private String categoryName;

    public OrderDetail(int orderid, int productid, int quantity, double price) {
        this.orderid = orderid;
        this.productid = productid;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetail(int orderid, int productid, int quantity, double price, String productImage, String productName, String productBrand, String categoryName) {
        this.orderid = orderid;
        this.productid = productid;
        this.quantity = quantity;
        this.price = price;
        this.productImage = productImage;
        this.productName = productName;
        this.productBrand = productBrand;
        this.categoryName = categoryName;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orderid=" + orderid + ", productid=" + productid + ", quantity=" + quantity + ", price=" + price + ", productImage=" + productImage + ", productName=" + productName + ", productBrand=" + productBrand + ", categoryName=" + categoryName + '}';
    }
    
}
