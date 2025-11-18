package main.Clothing;

import main.Enums.ClothingSize;
import main.Utils.ValidationUtil;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Trousers extends ClothingItem {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<Trousers> extent = new ArrayList<>();

    private double waistLength;
    private double legLength;

    public Trousers(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, ClothingSize clothingSize, double waistLength, double legLength) {
        super(name, brand, price, stockQuantity, material, color, clothingSize);

        ValidationUtil.positive(waistLength, "waistLength");
        ValidationUtil.positive(legLength, "legLength");

        this.waistLength = waistLength;
        this.legLength = legLength;

        addToExtent(this);
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

    private static void addToExtent(Trousers t) {
        if (t == null) throw new IllegalArgumentException("Trousers cannot be null");
        extent.add(t);
    }

    public static List<Trousers> getExtent() {
        return new ArrayList<>(extent);
    }

    public static void setExtent(List<Trousers> loaded) {
        extent = new ArrayList<>(loaded);
    }
}
