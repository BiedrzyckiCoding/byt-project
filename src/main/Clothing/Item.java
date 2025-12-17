package main.Clothing;

import main.Order.ItemQuantityInOrder;
import main.Utils.ValidationUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Item implements Serializable {
    private String name;
    private String brand;
    private double price;
    private int stockQuantity;
    private List<String> material;
    private List<String> color;

    private final Edition edition;

    private List<ItemQuantityInOrder> itemListAssociation = new ArrayList<>();

    public Item(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, Edition edition) {
        ValidationUtil.notEmptyString(name, "name");
        ValidationUtil.notEmptyString(brand, "brand");
        ValidationUtil.nonNegative(price, "price");
        ValidationUtil.nonNegative(stockQuantity, "stockQuantity");
        ValidationUtil.nonEmptyList(material, "material");
        ValidationUtil.nonEmptyList(color, "color");
        ValidationUtil.notNull(edition, "edition");

        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.material = material;
        this.color = color;
        this.edition = edition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidationUtil.notEmptyString(name, "name");
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        ValidationUtil.notEmptyString(brand, "brand");
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        ValidationUtil.nonNegative(price, "price");
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        ValidationUtil.nonNegative(stockQuantity, "stockQuantity");
        this.stockQuantity = stockQuantity;
    }

    public List<String> getMaterial() {
        return material;
    }

    public void setMaterial(List<String> material) {
        ValidationUtil.nonEmptyList(material, "material");
        this.material = material;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        ValidationUtil.nonEmptyList(color, "color");
        this.color = color;
    }

    public void addItemToList(ItemQuantityInOrder itemList) {
        if (itemListAssociation.contains(itemList)) {
            throw new IllegalArgumentException("Item already exists for this Item list");
        }
        itemListAssociation.add(itemList);
    }

    public void removeItemFromList(ItemQuantityInOrder itemList) {
        itemListAssociation.remove(itemList);
    }

    protected abstract void validateMinPrice();

    public interface Edition extends Serializable { }

    public static final class LimitedEditionInfo implements Edition {
        private final LocalDate releaseDate;
        private final int totalProduced;

        public LimitedEditionInfo(LocalDate releaseDate, int totalProduced) {
            ValidationUtil.notFuture(releaseDate, "releaseDate");
            ValidationUtil.positive(totalProduced, "totalProduced");
            this.releaseDate = releaseDate;
            this.totalProduced = totalProduced;
        }

        public LocalDate getReleaseDate() { return releaseDate; }
        public int getTotalProduced() { return totalProduced; }
    }

    public static final class StandardEditionInfo implements Edition {
        private final LocalDate productionStartDate;
        private final String season;

        public StandardEditionInfo(LocalDate productionStartDate, String season) {
            ValidationUtil.notFuture(productionStartDate, "productionStartDate");
            ValidationUtil.notEmptyString(season, "season");
            this.productionStartDate = productionStartDate;
            this.season = season;
        }

        public LocalDate getProductionStartDate() { return productionStartDate; }
        public String getSeason() { return season; }
    }



    public void addItemToOrder() {
        /* TODO */ }

    public void setItem() {
        /* TODO */ }

    public void showItemInStore() {
        /* TODO */ }

    public void showInfoAboutItem() {
        /* TODO */ }
}
