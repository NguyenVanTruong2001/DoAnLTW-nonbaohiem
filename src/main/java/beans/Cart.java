package beans;

import java.io.Serializable;
import java.util.HashMap;

public class Cart implements Serializable {

    private HashMap<ProductBean, Integer> map;

    public Cart(HashMap<ProductBean, Integer> map) {
        this.map = map;
    }

    public Cart() {
        map = new HashMap<>();
    }

    public HashMap<ProductBean, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<ProductBean, Integer> map) {
        this.map = map;
    }

    public void addToCart(ProductBean product, int quantity) {
        boolean b = map.containsKey(product);

        if (b) {
            int i = map.get(product);
            quantity += i;
            map.put(product, quantity);
        } else {
            map.put(product, quantity);
        }
    }

    public void subToCart(ProductBean product, int quantity) {
        boolean b = map.containsKey(product);

        if (b) {
            int i = map.get(product);
            quantity -= i - quantity;

            if (quantity < 0) {
                map.remove(product);
            } else {
                map.remove(product);
                map.put(product, quantity);
            }
        }
    }

    public void removeFromCart(ProductBean product) {
        boolean b = map.containsKey(product);

        if (b) {
            map.remove(product);
        }
    }
}
