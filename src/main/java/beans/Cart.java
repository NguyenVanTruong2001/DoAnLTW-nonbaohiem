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

    public void insertToCart(ProductBean product, int quantity) {
        boolean b = map.containsKey(product);

        if (b) {
            int i = map.get(product);
            quantity += i;
            map.put(product, quantity);
        } else {
            map.put(product, quantity);
        }
    }
}
