package test.PersistenceModels;

import main.Clothing.Item;
import main.Enums.ClothingSize;
import main.Enums.Fit;
import main.Enums.SleeveLength;
import main.PersistenceModels.ClothingItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClothingItemTest {

    private List<String> validMaterial;
    private List<String> validColor;
    private ClothingItem.ClothingVariant validShirtVariant;
    private ClothingItem.ClothingVariant validTrousersVariant;
    private ClothingItem.ClothingVariant validHoodieVariant;

    @BeforeEach
    void setUp() {
//        ClothingItem.setExtent(Collections.emptyList());

        validMaterial = List.of("Cotton");
        validColor = List.of("Blue");
        validShirtVariant = new ClothingItem.Shirt(SleeveLength.LONG, Fit.REGULAR);
        validTrousersVariant = new ClothingItem.Trousers(32.0, 30.0);
        validHoodieVariant = new ClothingItem.Hoodie(true);
    }

    @AfterEach
    void tearDown() {
//        ClothingItem.setExtent(Collections.emptyList());
    }

    @Test
    void constructor_shouldThrowException_whenPriceBelowMinimum() {
        assertThrows(IllegalArgumentException.class, () ->
                new ClothingItem("CheapShirt", "Brand", 39.99, 10, validMaterial, validColor,
                        ClothingSize.M, validShirtVariant, LocalDate.now(), "Season")
        );
    }

    @Test
    void constructor_shouldSucceed_whenPriceExactlyMinimum() {
        assertDoesNotThrow(() ->
                new ClothingItem("BudgetShirt", "Brand", 40.0, 10, validMaterial, validColor,
                        ClothingSize.M, validShirtVariant, LocalDate.now(), "Season")
        );
    }

    @Test
    void constructor_shouldThrowException_whenClothingSizeIsNull() {
        assertThrows(Exception.class, () ->
                new ClothingItem("NoSize", "Brand", 50.0, 10, validMaterial, validColor,
                        null, validShirtVariant, LocalDate.now(), "Season")
        );
    }

    @Test
    void constructor_shouldThrowException_whenVariantIsNull() {
        assertThrows(Exception.class, () ->
                new ClothingItem("NoVariant", "Brand", 50.0, 10, validMaterial, validColor,
                        ClothingSize.L, null, LocalDate.now(), "Season")
        );
    }

    @Test
    void constructor_shouldAddObjectToExtent_whenValid() {
        ClothingItem item = new ClothingItem("ValidItem", "Brand", 50.0, 10, validMaterial, validColor,
                ClothingSize.L, validShirtVariant, LocalDate.now(), "Season");

        assertTrue(ClothingItem.getExtent().contains(item));
        assertEquals(1, ClothingItem.getExtent().size());
    }

    @Test
    void constructor_shouldNotAddObjectToExtent_whenConstructionFails() {
        int initialSize = ClothingItem.getExtent().size();

        try {
            new ClothingItem("InvalidItem", "Brand", 10.0, 10, validMaterial, validColor,
                    ClothingSize.L, validShirtVariant, LocalDate.now(), "Season");
        } catch (Exception ignored) {
        }

        assertEquals(initialSize, ClothingItem.getExtent().size());
    }

    @Test
    void setClothingSize_shouldUpdateValue_whenValid() {
        ClothingItem item = new ClothingItem("Shirt", "Brand", 50.0, 10, validMaterial, validColor,
                ClothingSize.S, validShirtVariant, LocalDate.now(), "Season");

        item.setClothingSize(ClothingSize.XL);

        assertEquals(ClothingSize.XL, item.getClothingSize());
    }

    @Test
    void setClothingSize_shouldThrowException_whenNull() {
        ClothingItem item = new ClothingItem("Shirt", "Brand", 50.0, 10, validMaterial, validColor,
                ClothingSize.S, validShirtVariant, LocalDate.now(), "Season");

        assertThrows(Exception.class, () -> item.setClothingSize(null));
    }

    @Test
    void shirt_constructor_shouldThrowException_whenSleeveLengthNull() {
        assertThrows(Exception.class, () -> new ClothingItem.Shirt(null, Fit.SLIM));
    }

    @Test
    void shirt_setFit_shouldThrowException_whenNull() {
        var shirt = new ClothingItem.Shirt(SleeveLength.SHORT, Fit.REGULAR);
        assertThrows(Exception.class, () -> shirt.setFit(null));
    }

    @Test
    void shirt_setSleeveLength_shouldUpdateValue() {
        var shirt = new ClothingItem.Shirt(SleeveLength.SHORT, Fit.REGULAR);
        shirt.setSleeveLength(SleeveLength.LONG);
        assertEquals(SleeveLength.LONG, shirt.getSleeveLength());
    }

    @Test
    void trousers_constructor_shouldThrowException_whenWaistNegative() {
        assertThrows(Exception.class, () -> new ClothingItem.Trousers(-30.0, 32.0));
    }

    @Test
    void trousers_constructor_shouldThrowException_whenLegZeroOrNegative() {
        assertThrows(Exception.class, () -> new ClothingItem.Trousers(30.0, 0.0));
    }

    @Test
    void trousers_setWaistLength_shouldThrowException_whenNegative() {
        var trousers = new ClothingItem.Trousers(32.0, 34.0);
        assertThrows(Exception.class, () -> trousers.setWaistLength(-1.0));
    }

    @Test
    void trousers_setLegLength_shouldUpdateValue_whenValid() {
        var trousers = new ClothingItem.Trousers(32.0, 34.0);
        trousers.setLegLength(36.0);
        assertEquals(36.0, trousers.getLegLength());
    }

    @Test
    void hoodie_isCape_shouldReturnTrue_whenCapeTrue() {
        var hoodie = new ClothingItem.Hoodie(true);
        assertTrue(hoodie.isCape());
    }

    @Test
    void hoodie_isCape_shouldReturnFalse_whenCapeFalse() {
        var hoodie = new ClothingItem.Hoodie(false);
        assertFalse(hoodie.isCape());
    }

    @Test
    void hoodie_setCape_shouldChangeValue() {
        var hoodie = new ClothingItem.Hoodie(false);
        hoodie.setCape(true);
        assertTrue(hoodie.isCape());
    }
}