package Clothing;

import Enums.ClothingSize;

import java.util.List;

public class Trousers extends ClothingItem {
    private double waistLength;
    private double legLength;

    public Trousers(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, ClothingSize clothingSize, double waistLength, double legLength) {
        super(name, brand, price, stockQuantity, material, color, clothingSize);
        this.waistLength = waistLength;
        this.legLength = legLength;
    }

    public double getWaistLength() {
        return waistLength;
    }

    public void setWaistLength(double waistLength) {
        this.waistLength = waistLength;
    }

    public double getLegLength() {
        return legLength;
    }

    public void setLegLength(double legLength) {
        this.legLength = legLength;
    }
}
