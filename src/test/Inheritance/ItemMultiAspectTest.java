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
        materials = List.of("Cotton");
        colors = List.of("Red");
        shirtVariant = new ClothingItem.Shirt(SleeveLength.LONG, Fit.SLIM);
        bootVariant = new Footwear.Boot(true);
    }

    // for private field access
    private Object getPrivateEditionField(Item item) {
        try {
            Field field = Item.class.getDeclaredField("edition");
            field.setAccessible(true);
            return field.get(item);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access private edition field", e);
        }
    }

    // clothing + standard edition

    @Test
    void constructor_ShouldInitializeStandardEdition_WhenStandardDataProvided() {
        ClothingItem item = new ClothingItem(
                "T-Shirt", "Nike", 50.0, 100, materials, colors,
                ClothingSize.L, shirtVariant,
                LocalDate.now(), "Summer Season"
        );

        Object edition = getPrivateEditionField(item);

        assertTrue(edition instanceof Item.StandardEditionInfo);
    }

    // clothing + limited edition

    @Test
    void constructor_ShouldInitializeLimitedEdition_WhenLimitedDataProvided() {
        ClothingItem item = new ClothingItem(
                "T-Shirt", "Supreme", 150.0, 10, materials, colors,
                ClothingSize.L, shirtVariant,
                LocalDate.now(), 500
        );

        Object edition = getPrivateEditionField(item);

        assertTrue(edition instanceof Item.LimitedEditionInfo);
    }

    // footwear + standard edition

    @Test
    void constructor_Footwear_ShouldInitializeStandardEdition() {
        Footwear item = new Footwear(
                "Boots", "Timberland", 120.0, 50, materials, colors,
                42.0, bootVariant,
                LocalDate.now(), "Winter Season"
        );

        Object edition = getPrivateEditionField(item);

        assertTrue(edition instanceof Item.StandardEditionInfo);
    }

    // footwear + limited edition

    @Test
    void constructor_Footwear_ShouldInitializeLimitedEdition() {
        Footwear item = new Footwear(
                "Yeezy", "Adidas", 300.0, 5, materials, colors,
                42.0, bootVariant,
                LocalDate.now(), 100
        );

        Object edition = getPrivateEditionField(item);

        assertTrue(edition instanceof Item.LimitedEditionInfo);
    }

    // disjoint

    @Test
    void disjoint_StandardConstructor_ShouldNeverCreateLimitedEdition() {
        ClothingItem item = new ClothingItem(
                "T-Shirt", "Nike", 50.0, 100, materials, colors,
                ClothingSize.L, shirtVariant,
                LocalDate.now(), "Summer Season"
        );

        Object edition = getPrivateEditionField(item);

        assertFalse(edition instanceof Item.LimitedEditionInfo);
    }


    @Test
    void constructor_Clothing_ShouldThrowException_WhenPriceBelowMin() {
        double invalidPrice = 30.0; // Min is 40.0

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
        double invalidPrice = 50.0; // Min is 60.0

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