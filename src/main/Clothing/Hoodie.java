package main.Clothing;


import main.Enums.ClothingSize;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Hoodie extends ClothingItem {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<Hoodie> extent = new ArrayList<>();

    private boolean cape;

    public Hoodie(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, ClothingSize clothingSize, boolean cape) {
        super(name, brand, price, stockQuantity, material, color, clothingSize);
        this.cape = cape;

        addToExtent(this);
    }

    public boolean isCape() {
        return cape;
    }

    public void setCape(boolean cape) {
        this.cape = cape;
    }

    private static void addToExtent(Hoodie h) {
        if (h == null) throw new IllegalArgumentException("Hoodie cannot be null");
        extent.add(h);
    }

    public static List<Hoodie> getExtent() {
        return new ArrayList<>(extent);
    }

    public static void setExtent(List<Hoodie> loaded) {
        extent = new ArrayList<>(loaded);
    }
}
