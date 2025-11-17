package Clothing;

import Enums.ClothingSize;
import Enums.Fit;
import Enums.SleeveLength;
import Utils.ValidationUtil;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Shirt extends ClothingItem {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<Shirt> extent = new ArrayList<>();

    private SleeveLength sleeveLength;
    private Fit fit;

    public Shirt(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, ClothingSize clothingSize,  SleeveLength sleeveLength, Fit fit) {
        super(name, brand, price, stockQuantity, material, color, clothingSize);

        ValidationUtil.notNull(sleeveLength, "sleeveLength");
        ValidationUtil.notNull(fit, "fit");

        this.sleeveLength = sleeveLength;
        this.fit = fit;

        addToExtent(this);
    }

    public SleeveLength getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(SleeveLength sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public Fit getFit() {
        return fit;
    }

    public void setFit(Fit fit) {
        this.fit = fit;
    }

    private static void addToExtent(Shirt s) {
        if (s == null) throw new IllegalArgumentException("Shirt cannot be null");
        extent.add(s);
    }

    public static List<Shirt> getExtent() {
        return new ArrayList<>(extent);
    }

    public static void setExtent(List<Shirt> loaded) {
        extent = new ArrayList<>(loaded);
    }
}
