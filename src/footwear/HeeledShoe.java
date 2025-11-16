package footwear;

import java.util.List;

public class HeeledShoe extends Footwear {
    private double heelHeight;

    public HeeledShoe(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, double heelHeight) {
        super(name, brand, price, stockQuantity, material, color);
        this.heelHeight = heelHeight;
    }

    public double getHeelHeight() {
        return heelHeight;
    }

    public void setHeelHeight(double heelHeight) {
        this.heelHeight = heelHeight;
    }
}