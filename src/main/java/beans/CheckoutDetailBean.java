package beans;

import java.io.Serializable;

public class CheckoutDetailBean implements Serializable {
    private int orderId;
    private int productId;
    private int quantity;

    public CheckoutDetailBean(int userId, int productId, int quantity) {
        this.orderId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int userId) {
        this.orderId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
