package main.Footwear;

import main.Clothing.Item;

import java.util.List;

public abstract class Footwear extends Item {
    private static final double MIN_PRICE = 60;
    private double footSize;

    public Footwear(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, double footSize) {
        super(name, brand, price, stockQuantity, material, color);

        if(price < MIN_PRICE) {
            throw new IllegalArgumentException("Price for footwear ust be greater than " + MIN_PRICE);
        }
        this.footSize = footSize;
    }

    public double getFootSize() {
        return footSize;
    }

    public void setFootSize(double footSize) {
        this.footSize = footSize;
    }
}
