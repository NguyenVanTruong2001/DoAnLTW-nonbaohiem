package beans;

import java.io.Serializable;

public class ProductBean implements Serializable {
    private int productId;
    private CategoryBean categoryBean;
    private String productName;
    private String productImage;
    private String productDescription;
    private String productBrand;
    private String productSize;
    private int productPrice;

    public ProductBean(int productId, CategoryBean categoryBean, String productName, String productImage, String productDescription, String productBrand, String productSize, int productPrice) {
        this.productId = productId;
        this.categoryBean = categoryBean;
        this.productName = productName;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productBrand = productBrand;
        this.productSize = productSize;
        this.productPrice = productPrice;
    }

    public ProductBean() {}

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public CategoryBean getCategoryBean() { return categoryBean; }

    public void setCategoryBean(CategoryBean categoryBean) {
        this.categoryBean = categoryBean;
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
                ", categoryBean=" + categoryBean +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", productSize='" + productSize + '\'' +
                ", productPrice='" + productPrice + '\'' +
                '}';
    }
}
