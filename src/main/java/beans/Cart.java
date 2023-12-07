package beans;

import java.io.Serializable;
import java.util.HashMap;

public class Cart implements Serializable {

    private HashMap<Integer, ProductCart> map;

    public Cart() {}

    public Cart(HashMap<Integer, ProductCart> map) {
        this.map = map;
    }

    public HashMap<Integer, ProductCart> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, ProductCart> map) {
        this.map = map;
    }
}
