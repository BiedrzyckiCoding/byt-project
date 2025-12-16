package main.PersistenceModels;
import main.Clothing.Item;
import main.Enums.ClothingSize;
import main.Enums.Fit;
import main.Enums.SleeveLength;
import main.Utils.ValidationUtil;

import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ClothingItem extends Item {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final double MIN_PRICE = 40;

    private static List<ClothingItem> extent = new ArrayList<>();
    private ClothingSize clothingSize;
    private final ClothingVariant variant;

    public ClothingItem(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, ClothingSize clothingSize, ClothingVariant variant, LocalDate releaseDate, int totalProduced) {
        super(name, brand, price, stockQuantity, material, color, new Item.LimitedEditionInfo(releaseDate, totalProduced));

        ValidationUtil.notNull(clothingSize, "clothingSize");
        ValidationUtil.notNull(variant, "variant");
        validateMinPrice();

        this.clothingSize = clothingSize;
        this.variant = variant;

        addToExtent(this);
    }

    public ClothingItem(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, ClothingSize clothingSize, ClothingVariant variant, LocalDate productionStartDate, String season) {
        super(name, brand, price, stockQuantity, material, color, new Item.StandardEditionInfo(productionStartDate, season));

        ValidationUtil.notNull(clothingSize, "clothingSize");
        ValidationUtil.notNull(variant, "variant");
        validateMinPrice();

        this.clothingSize = clothingSize;
        this.variant = variant;

        addToExtent(this);
    }

    private static void addToExtent(ClothingItem item) {
        if (item == null) throw new IllegalArgumentException("ClothingItem cannot be null");
        extent.add(item);
    }

    public static List<ClothingItem> getExtent() {
        return new ArrayList<>(extent);
    }

    static void setExtent(List<ClothingItem> loaded) {
        extent = new ArrayList<>(loaded);
    }

    @Override
    protected void validateMinPrice() {
        if (getPrice() < MIN_PRICE) {
            throw new IllegalArgumentException("Price for clothing must be >= " + MIN_PRICE);
        }
    }

    public ClothingSize getClothingSize() { return clothingSize; }
    public void setClothingSize(ClothingSize clothingSize) {
        ValidationUtil.notNull(clothingSize, "clothingSize");
        this.clothingSize = clothingSize;
    }

    public ClothingVariant getVariant() { return variant; }

    public interface ClothingVariant { }

    public static final class Shirt implements ClothingVariant {
        private SleeveLength sleeveLength;
        private Fit fit;

        public Shirt(SleeveLength sleeveLength, Fit fit) {
            ValidationUtil.notNull(sleeveLength, "sleeveLength");
            ValidationUtil.notNull(fit, "fit");
            this.sleeveLength = sleeveLength;
            this.fit = fit;
        }

        public SleeveLength getSleeveLength() { return sleeveLength; }
        public void setSleeveLength(SleeveLength sleeveLength) {
            ValidationUtil.notNull(sleeveLength, "sleeveLength");
            this.sleeveLength = sleeveLength;
        }

        public Fit getFit() { return fit; }
        public void setFit(Fit fit) {
            ValidationUtil.notNull(fit, "fit");
            this.fit = fit;
        }
    }

    public static final class Trousers implements ClothingVariant {
        private double waistLength;
        private double legLength;

        public Trousers(double waistLength, double legLength) {
            ValidationUtil.positive(waistLength, "waistLength");
            ValidationUtil.positive(legLength, "legLength");
            this.waistLength = waistLength;
            this.legLength = legLength;
        }

        public double getWaistLength() { return waistLength; }
        public void setWaistLength(double waistLength) {
            ValidationUtil.positive(waistLength, "waistLength");
            this.waistLength = waistLength;
        }

        public double getLegLength() { return legLength; }
        public void setLegLength(double legLength) {
            ValidationUtil.positive(legLength, "legLength");
            this.legLength = legLength;
        }
    }

    public static final class Hoodie implements ClothingVariant {
        private boolean cape;

        public Hoodie(boolean cape) {
            this.cape = cape;
        }

        public boolean isCape() { return cape; }
        public void setCape(boolean cape) { this.cape = cape; }
    }
}
