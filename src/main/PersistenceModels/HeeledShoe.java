package main.PersistenceModels;

import main.Footwear.Footwear;
import main.Utils.ValidationUtil;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class HeeledShoe extends Footwear {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<HeeledShoe> extent = new ArrayList<>();

    private double heelHeight;

    public HeeledShoe(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, double heelHeight, double footSize) {
        super(name, brand, price, stockQuantity, material, color, footSize);

        ValidationUtil.nonNegative(heelHeight, "heelHeight");

        this.heelHeight = heelHeight;

        addToExtent(this);
    }

    public double getHeelHeight() {
        return heelHeight;
    }

    public void setHeelHeight(double heelHeight) {
        this.heelHeight = heelHeight;
    }

    private static void addToExtent(HeeledShoe hs) {
        if (hs == null) throw new IllegalArgumentException("HeeledShoe cannot be null");
        extent.add(hs);
    }

    public static List<HeeledShoe> getExtent() {
        return new ArrayList<>(extent);
    }

    static void setExtent(List<HeeledShoe> loaded) {
        extent = new ArrayList<>(loaded);
    }
}