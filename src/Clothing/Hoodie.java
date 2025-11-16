package Clothing;


import Enums.ClothingSize;

import java.util.List;

public class Hoodie extends ClothingItem {
    private boolean cape;

    public Hoodie(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, ClothingSize clothingSize, boolean cape) {
        super(name, brand, price, stockQuantity, material, color, clothingSize);
        this.cape = cape;
    }

    public boolean isCape() {
        return cape;
    }

    public void setCape(boolean cape) {
        this.cape = cape;
    }
}
