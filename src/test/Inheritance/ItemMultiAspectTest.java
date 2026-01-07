package test.Inheritance;

import main.Enums.ClothingSize;
import main.Enums.Fit;
import main.Enums.SleeveLength;
import main.PersistenceModels.ClothingItem;
import main.PersistenceModels.Footwear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

import main.Clothing.Item;

import static org.junit.jupiter.api.Assertions.*;

class ItemMultiAspectTest {

    private List<String> materials;
    private List<String> colors;
    private ClothingItem.ClothingVariant shirtVariant;
    private Footwear.FootwearVariant bootVariant;

    @BeforeEach
    void setUp() {
        materials = List.of("Cotton", "Polyester");
        colors = List.of("Red", "Blue");

        // Setup Variants using the inner classes defined in your code
        shirtVariant = new ClothingItem.Shirt(SleeveLength.LONG, Fit.SLIM);
        bootVariant = new Footwear.Boot(true);
    }

    //in item the edition field is private so there is no way to access the field.
    private Object getPrivateEditionField(Item item) {
        try {
            Field field = Item.class.getDeclaredField("edition");
            field.setAccessible(true);
            return field.get(item);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access private edition field", e);
        }
    }

    // Clothing + Standard Edition

    @Test
    void constructor_ShouldInitializeStandardEdition_WhenStandardDataProvided() {
        // Arrange
        ClothingItem item = new ClothingItem(
                "T-Shirt", "Nike", 50.0, 100, materials, colors,
                ClothingSize.L, shirtVariant,
                LocalDate.now(), "Summer Season"
        );

        // Act
        Object edition = getPrivateEditionField(item);

        // Assert
        assertTrue(edition instanceof Item.StandardEditionInfo);
    }

    // Clothing + Limited Edition

    @Test
    void constructor_ShouldCreateClothingWithLimitedEdition() {
        LocalDate releaseDate = LocalDate.now();
        int totalProduced = 500;

        ClothingItem item = new ClothingItem(
                "T-Shirt", "Supreme", 150.0, 10, materials, colors,
                ClothingSize.L, shirtVariant,
                releaseDate, totalProduced
        );

        assertTrue(item.getEdition() instanceof Item.LimitedEditionInfo);
    }

    // Footwear + Standard Edition

    @Test
    void constructor_ShouldCreateFootwearWithStandardEdition() {
        LocalDate productionDate = LocalDate.now();
        String season = "Winter 2024";

        Footwear item = new Footwear(
                "Boots", "Timberland", 120.0, 50, materials, colors,
                42.0, bootVariant,
                productionDate, season
        );

        assertTrue(item.getEdition() instanceof Item.StandardEditionInfo);
    }

    // Footwear + Limited Edition

    @Test
    void constructor_ShouldCreateFootwearWithLimitedEdition() {
        LocalDate releaseDate = LocalDate.now();
        int totalProduced = 100;

        Footwear item = new Footwear(
                "Yeezy", "Adidas", 300.0, 5, materials, colors,
                42.0, bootVariant,
                releaseDate, totalProduced
        );

        assertTrue(item.getEdition() instanceof Item.LimitedEditionInfo);
    }

    // Disjoint

    @Test
    void edition_ShouldBeImmutable_AndNotSwitchable() {
        ClothingItem item = new ClothingItem(
                "T-Shirt", "Nike", 50.0, 100, materials, colors,
                ClothingSize.L, shirtVariant,
                LocalDate.now(), "Season"
        );

        // disjoint: standard item should not be limited.
        assertFalse(item.getEdition() instanceof Item.LimitedEditionInfo);
    }


    @Test
    void constructor_Clothing_ShouldThrowException_WhenPriceBelowMin() {
        double invalidPrice = 30.0; // Min is 40

        assertThrows(IllegalArgumentException.class, () ->
                new ClothingItem(
                        "Cheap Shirt", "Brand", invalidPrice, 10, materials, colors,
                        ClothingSize.S, shirtVariant,
                        LocalDate.now(), "Season"
                )
        );
    }

    @Test
    void constructor_Footwear_ShouldThrowException_WhenPriceBelowMin() {
        double invalidPrice = 50.0; // Min is 60

        assertThrows(IllegalArgumentException.class, () ->
                new Footwear(
                        "Cheap Shoes", "Brand", invalidPrice, 10, materials, colors,
                        40.0, bootVariant,
                        LocalDate.now(), "Season"
                )
        );
    }

    @Test
    void setFootSize_ShouldThrowException_WhenSizeTooSmall() {
        Footwear item = new Footwear(
                "Boots", "Timberland", 120.0, 50, materials, colors,
                42.0, bootVariant,
                LocalDate.now(), "Season"
        );

        assertThrows(IllegalArgumentException.class, () -> item.setFootSize(30.0));
    }

    @Test
    void setFootSize_ShouldThrowException_WhenSizeTooLarge() {
        Footwear item = new Footwear(
                "Boots", "Timberland", 120.0, 50, materials, colors,
                42.0, bootVariant,
                LocalDate.now(), "Season"
        );

        assertThrows(IllegalArgumentException.class, () -> item.setFootSize(55.0));
    }
}