package beans;

import java.io.Serializable;

public class ProductBean implements Serializable, Comparable<ProductBean> {
    private int productId;
    private int categoryId;
    private String productName;
    private String productImage;
    private String productDescription;
    private String productBrand;
    private String productSize;
    private int productPrice;

    public ProductBean(int productId, int categoryId, String productName, String productImage, String productDescription, String productBrand, String productSize, int productPrice) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productBrand = productBrand;
        this.productSize = productSize;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "productId=" + productId +
                ", categoryId=" + categoryId +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", productSize='" + productSize + '\'' +
                ", productPrice='" + productPrice + '\'' +
                '}';
    }

    @Override
    public int compareTo(ProductBean o) {
        return this.productId - o.productId;
    }
}
