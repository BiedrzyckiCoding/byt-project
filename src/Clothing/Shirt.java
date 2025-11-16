package Clothing;

import Enums.ClothingSize;
import Enums.Fit;
import Enums.SleeveLength;
import Validation.ValidationUtil;

import java.util.List;

public class Shirt extends ClothingItem {
    private SleeveLength sleeveLength;
    private Fit fit;

    public Shirt(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, ClothingSize clothingSize,  SleeveLength sleeveLength, Fit fit) {
        super(name, brand, price, stockQuantity, material, color, clothingSize);

        ValidationUtil.notNull(sleeveLength, "sleeveLength");
        ValidationUtil.notNull(fit, "fit");

        this.sleeveLength = sleeveLength;
        this.fit = fit;
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
}
