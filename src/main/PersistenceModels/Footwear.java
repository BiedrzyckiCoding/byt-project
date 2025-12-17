package main.PersistenceModels;

import main.Clothing.Item;
import main.Utils.ValidationUtil;

import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Footwear extends Item {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final double MIN_PRICE = 60;

    private static List<Footwear> extent = new ArrayList<>();

    private double footSize;
    private final FootwearVariant variant;

    public Footwear(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, double footSize, FootwearVariant variant, LocalDate releaseDate, int totalProduced) {
        super(name, brand, price, stockQuantity, material, color, new Item.LimitedEditionInfo(releaseDate, totalProduced));

        ValidationUtil.notNull(variant, "variant");
        validateMinPrice();
        setFootSize(footSize);
        this.variant = variant;

        addToExtent(this);
    }

    public Footwear(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, double footSize, FootwearVariant variant, LocalDate productionStartDate, String season) {
        super(name, brand, price, stockQuantity, material, color, new Item.StandardEditionInfo(productionStartDate, season));

        ValidationUtil.notNull(variant, "variant");
        validateMinPrice();
        setFootSize(footSize);
        this.variant = variant;

        addToExtent(this);
    }

    private static void addToExtent(Footwear fw) {
        if (fw == null) throw new IllegalArgumentException("Footwear cannot be null");
        extent.add(fw);
    }

    public static List<Footwear> getExtent() {
        return new ArrayList<>(extent);
    }

    static void setExtent(List<Footwear> loaded) {
        extent = new ArrayList<>(loaded);
    }

    @Override
    protected void validateMinPrice() {
        if (getPrice() < MIN_PRICE) {
            throw new IllegalArgumentException("Price for footwear must be >= " + MIN_PRICE);
        }
    }

    public double getFootSize() { return footSize; }

    public void setFootSize(double footSize) {
        if (footSize < 35 || footSize > 49) {
            throw new IllegalArgumentException("Foot size must be between 35 and 49");
        }
        this.footSize = footSize;
    }

    public FootwearVariant getVariant() { return variant; }

    public interface FootwearVariant { }

    public static final class Boot implements FootwearVariant {
        private boolean waterproof;

        public Boot(boolean waterproof) {
            this.waterproof = waterproof;
        }

        public boolean isWaterproof() { return waterproof; }
        public void setWaterproof(boolean waterproof) { this.waterproof = waterproof; }
    }

    public static final class HeeledShoe implements FootwearVariant {
        private double heelHeight;

        public HeeledShoe(double heelHeight) {
            ValidationUtil.nonNegative(heelHeight, "heelHeight");
            this.heelHeight = heelHeight;
        }

        public double getHeelHeight() {
            return heelHeight;
        }

        public void setHeelHeight(double heelHeight) {
            ValidationUtil.nonNegative(heelHeight, "heelHeight");
            this.heelHeight = heelHeight;
        }
    }
}
