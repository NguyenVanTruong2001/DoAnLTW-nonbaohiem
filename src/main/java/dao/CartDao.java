package dao;

import beans.Cart;

import java.util.HashMap;

public class CartDao {
    private HashMap<Integer, Cart> cartMap;

    public CartDao(HashMap<Integer, Cart> cartMap) {
        this.cartMap = cartMap;
    }

    public HashMap<Integer, Cart> getCartMap() {
        return cartMap;
    }

    public void setCartMap(HashMap<Integer, Cart> cartMap) {
        this.cartMap = cartMap;
    }

    public void addCart(Integer key, Cart cart) {

    }

    public void updateCart(Integer key, Cart cart) {

    }

    public void removeCart(Integer key) {

    }

    public int count() {
        return 0;
    }

    public int totalPrice() {
        return 0;
    }
}
