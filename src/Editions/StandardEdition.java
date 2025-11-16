package Editions;

import Clothing.Item;

import java.time.LocalDate;
import java.util.List;

public class StandardEdition extends Item {
    private LocalDate productionStartDate;
    private String season;

    public StandardEdition(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, LocalDate productionStartDate, String season) {
        super(name, brand, price, stockQuantity, material, color);
        this.productionStartDate = productionStartDate;
        this.season = season;
    }

    public LocalDate getProductionStartDate() {
        return productionStartDate;
    }

    public void setProductionStartDate(LocalDate productionStartDate) {
        this.productionStartDate = productionStartDate;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}