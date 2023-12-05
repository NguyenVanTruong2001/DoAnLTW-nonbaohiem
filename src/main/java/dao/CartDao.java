package dao;

import beans.Cart;

import java.util.HashMap;

public class CartDao {
    private HashMap<Integer, Cart> cartMap;

    public CartDao(HashMap<Integer, Cart> cartMap) {
        this.cartMap = cartMap;
    }

    public CartDao() {
        this.cartMap = new HashMap<>();
    }

    public HashMap<Integer, Cart> getCartMap() {
        return cartMap;
    }

    public void setCartMap(HashMap<Integer, Cart> cartMap) {
        this.cartMap = cartMap;
    }

    public void addCart(Integer key, Cart cart) {
        boolean b = cartMap.containsKey(key);
        if (b) {
            int i = cartMap.get(key).getQuantity();
            int i1 = cart.getQuantity();
            cart.setQuantity(i1 + i);
            cartMap.put(key, cart);
        } else {
            cartMap.put(key, cart);
        }
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
