package beans;

import java.util.Map;

public class Cart {
    private Map<ProductBean, Integer> cartList;
    private long cartId;

    public Cart() {}

    public Cart(Map<ProductBean, Integer> cartList, long cartId) {
        this.cartList = cartList;
        this.cartId = cartId;
    }

    public Map<ProductBean, Integer> getCartList() {
        return cartList;
    }

    public void setCartList(Map<ProductBean, Integer> cartList) {
        this.cartList = cartList;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }
}
