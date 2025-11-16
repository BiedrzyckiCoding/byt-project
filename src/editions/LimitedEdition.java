package editions;

import clothing.Item;

import java.time.LocalDate;
import java.util.List;

public class LimitedEdition extends Item {
    private LocalDate releaseDate;
    private int totalProduced;

    public LimitedEdition(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, LocalDate releaseDate, int totalProduced) {
        super(name, brand, price, stockQuantity, material, color);
        this.releaseDate = releaseDate;
        this.totalProduced = totalProduced;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getTotalProduced() {
        return totalProduced;
    }

    public void setTotalProduced(int totalProduced) {
        this.totalProduced = totalProduced;
    }
}