package beans;

import java.io.Serializable;

public class Cart implements Serializable {
    private ProductBean product;
    private int quantity;

    public Cart(ProductBean product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int totalPrice() {
        return this.product.getProductPrice() * this.quantity;
    }
}
