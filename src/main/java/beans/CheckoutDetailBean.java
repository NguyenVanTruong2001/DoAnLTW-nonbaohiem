package beans;

import java.io.Serializable;

public class CheckoutDetailBean implements Serializable {
    private CheckoutBean checkoutBean;
    private ProductBean productBean;
    private int quantity;

    public CheckoutDetailBean(CheckoutBean checkoutBean, ProductBean productBean, int quantity) {
        this.checkoutBean = checkoutBean;
        this.productBean = productBean;
        this.quantity = quantity;
    }

    public CheckoutDetailBean() {}

    public CheckoutBean getCheckoutBean() {
        return checkoutBean;
    }

    public void setCheckoutBean(CheckoutBean checkoutBean) {
        this.checkoutBean = checkoutBean;
    }

    public ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CheckoutDetailBean{" +
                "checkoutBean=" + checkoutBean +
                ", productBean=" + productBean +
                ", quantity=" + quantity +
                '}';
    }

    public int detailPrice() { return getQuantity() * getProductBean().getProductPrice(); }
}
