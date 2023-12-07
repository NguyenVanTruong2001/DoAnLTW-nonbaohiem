package beans;

public class ProductCart {
    private ProductBean product;
    private int quantity;

    public ProductCart(ProductBean product, int quantity) {
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
}
