/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package molder;

import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class Product extends Category{

    private int productId;
    private String productName;
    private String productDescription;
    private float productPrice;
    private int productQuantity;
    private String productBrand;
    private String productImage;
    private boolean productStatus; 
    private String createBy;
    private Date createDate;
    private String modifyBy; 
    private Date modifyDate;
    private Category category;
    private List<ProductImange> productImanges; 
    private Specification specification;
    private int numberInCart;


    
    
    
    public Product() {
    }

    public Product(int productId, String productName, String productDescription, float productPrice, int productQuantity, String productBrand, String productImage, boolean productStatus, String createBy, Date createDate, String modifyBy, Date modifyDate, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productImage = productImage;
        this.productStatus = productStatus;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.category = category;
    }

    public Product(int productId, String productName, String productDescription, float productPrice, int productQuantity, String productBrand, String productImage, boolean productStatus, String createBy, Date createDate, String modifyBy, Date modifyDate) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productImage = productImage;
        this.productStatus = productStatus;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
    }

    public Product(int productId, String productName, String productDescription, float productPrice, int productQuantity, String productBrand, String productImage, boolean productStatus, String createBy, Date createDate, String modifyBy, Date modifyDate, Specification specification) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productBrand = productBrand;
        this.productImage = productImage;
        this.productStatus = productStatus;
        this.createBy = createBy;
        this.createDate = createDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.specification = specification;
    }

    public Product(int productId, String productName, float productPrice, int productQuantity, String productImage, int numberInCart) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productImage = productImage;
        this.numberInCart = numberInCart;
    }

    public Product(int productId, String productName, float productPrice, int productQuantity, String productImage) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productImage = productImage;
    }
    
    
    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductImange> getProductImanges() {
        return productImanges;
    }

    public void setProductImanges(List<ProductImange> productImanges) {
        this.productImanges = productImanges;
    }

     public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", productDescription=" + productDescription + ", productPrice=" + productPrice + ", productQuantity=" + productQuantity + ", productBrand=" + productBrand + ", productImage=" + productImage + ", productStatus=" + productStatus + ", modifyBy=" + modifyBy + ", category=" + category + ", numberInCart=" + numberInCart + '}';
    }


   
}
