package main.Footwear;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Boot extends Footwear {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<Boot> extent = new ArrayList<>();

    private boolean waterproof;

    public Boot(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, boolean waterproof) {
        super(name, brand, price, stockQuantity, material, color);
        this.waterproof = waterproof;

        addToExtent(this);
    }

    public boolean isWaterproof() {
        return waterproof;
    }

    public void setWaterproof(boolean waterproof) {
        this.waterproof = waterproof;
    }

    private static void addToExtent(Boot b) {
        if (b == null) throw new IllegalArgumentException("Boot cannot be null");
        extent.add(b);
    }

    public static List<Boot> getExtent() {
        return new ArrayList<>(extent);
    }

    public static void setExtent(List<Boot> loaded) {
        extent = new ArrayList<>(loaded);
    }
}
