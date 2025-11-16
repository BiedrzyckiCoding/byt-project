package Clothing;

import Validation.ValidationUtil;

import java.util.List;

public class Item {
    private String name;
    private String brand;
    private double price;
    private int stockQuantity;
    private List<String> material;
    private List<String> color;

    public Item(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color) {
        ValidationUtil.notEmpty(name, "name");
        ValidationUtil.notEmpty(brand, "brand");
        ValidationUtil.nonNegative(price, "price");
        ValidationUtil.nonNegative(stockQuantity, "stockQuantity");
        ValidationUtil.emptyList(material, "material");
        ValidationUtil.emptyList(color, "color");

        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.material = material;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<String> getMaterial() {
        return material;
    }

    public void setMaterial(List<String> material) {
        this.material = material;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
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
