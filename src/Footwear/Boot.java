package Footwear;

import java.util.List;

public class Boot extends Footwear {
    private boolean waterproof;

    public Boot(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, boolean waterproof) {
        super(name, brand, price, stockQuantity, material, color);
        this.waterproof = waterproof;
    }

    public boolean isWaterproof() {
        return waterproof;
    }

    public void setWaterproof(boolean waterproof) {
        this.waterproof = waterproof;
    }
}
