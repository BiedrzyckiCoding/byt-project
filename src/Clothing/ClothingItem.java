package Clothing;
import Enums.ClothingSize;
import Utils.ValidationUtil;

import java.util.List;


public abstract class ClothingItem extends Item {
    private ClothingSize clothingSize;
    private static final double MIN_PRICE = 40;

    public ClothingItem(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, ClothingSize clothingSize) {
        super(name, brand, price, stockQuantity, material, color);

        ValidationUtil.notNull(clothingSize, "clothingSize");

        if(price < MIN_PRICE) {
            throw new IllegalArgumentException("Price for clothing should be greater than " + MIN_PRICE);
        }

        this.clothingSize = clothingSize;
    }

    public ClothingSize getClothingSize() {
        return clothingSize;
    }

    public void setClothingSize(ClothingSize clothingSize) {
        this.clothingSize = clothingSize;
    }
}
