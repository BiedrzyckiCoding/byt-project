package footwear;

import clothing.Item;

import java.util.List;

public abstract class Footwear extends Item {
    private static final double MIN_PRICE = 80;

    public Footwear(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color) {
        super(name, brand, price, stockQuantity, material, color);

        if(price < MIN_PRICE) {
            throw new IllegalArgumentException("Price for footwear ust be greater than " + MIN_PRICE);
        }
    }
}
