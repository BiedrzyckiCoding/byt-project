package test.PersistenceModels;

import main.PersistenceModels.Footwear;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FootwearTest {

    private List<String> validMaterial;
    private List<String> validColor;
    private Footwear.FootwearVariant validBootVariant;
    private Footwear.FootwearVariant validHeelVariant;

    @BeforeEach
    void setUp() {
//        Footwear.setExtent(Collections.emptyList());

        validMaterial = List.of("Leather");
        validColor = List.of("Black");
        validBootVariant = new Footwear.Boot(true);
        validHeelVariant = new Footwear.HeeledShoe(5.0);
    }

    @AfterEach
    void tearDown() {
//        Footwear.setExtent(Collections.emptyList());
    }

    @Test
    void constructor_shouldThrowException_whenPriceBelowMinimum() {
        assertThrows(IllegalArgumentException.class, () ->
                new Footwear("CheapBoot", "Brand", 59.99, 10, validMaterial, validColor,
                        42.0, validBootVariant, LocalDate.now(), "Season")
        );
    }

    @Test
    void constructor_shouldSucceed_whenPriceExactlyMinimum() {
        assertDoesNotThrow(() ->
                new Footwear("BudgetBoot", "Brand", 60.0, 10, validMaterial, validColor,
                        42.0, validBootVariant, LocalDate.now(), "Season")
        );
    }

    @Test
    void constructor_shouldThrowException_whenFootSizeTooSmall() {
        assertThrows(IllegalArgumentException.class, () ->
                new Footwear("SmallBoot", "Brand", 100.0, 10, validMaterial, validColor,
                        34.9, validBootVariant, LocalDate.now(), "Season")
        );
    }

    @Test
    void constructor_shouldThrowException_whenFootSizeTooLarge() {
        assertThrows(IllegalArgumentException.class, () ->
                new Footwear("BigBoot", "Brand", 100.0, 10, validMaterial, validColor,
                        49.1, validBootVariant, LocalDate.now(), "Season")
        );
    }

    @Test
    void constructor_shouldThrowException_whenVariantIsNull() {
        assertThrows(Exception.class, () ->
                new Footwear("NoVariant", "Brand", 100.0, 10, validMaterial, validColor,
                        42.0, null, LocalDate.now(), "Season")
        );
    }

    @Test
    void constructor_shouldAddObjectToExtent_whenValid() {
        Footwear item = new Footwear("ValidBoot", "Brand", 100.0, 10, validMaterial, validColor,
                42.0, validBootVariant, LocalDate.now(), "Season");

        assertTrue(Footwear.getExtent().contains(item));
    }

    @Test
    void constructor_shouldAddOneObjectToExtent_whenValid() {
        new Footwear("ValidBoot", "Brand", 100.0, 10, validMaterial, validColor,
                42.0, validBootVariant, LocalDate.now(), "Season");

        assertEquals(1, Footwear.getExtent().size());
    }

    @Test
    void constructor_shouldNotAddObjectToExtent_whenConstructionFails() {
        int initialSize = Footwear.getExtent().size();

        try {
            new Footwear("InvalidBoot", "Brand", 10.0, 10, validMaterial, validColor,
                    42.0, validBootVariant, LocalDate.now(), "Season");
        } catch (Exception ignored) {
        }

        assertEquals(initialSize, Footwear.getExtent().size());
    }

    @Test
    void setFootSize_shouldUpdateValue_whenValid() {
        Footwear item = new Footwear("Boot", "Brand", 100.0, 10, validMaterial, validColor,
                40.0, validBootVariant, LocalDate.now(), "Season");

        item.setFootSize(45.0);

        assertEquals(45.0, item.getFootSize());
    }

    @Test
    void setFootSize_shouldThrowException_whenTooSmall() {
        Footwear item = new Footwear("Boot", "Brand", 100.0, 10, validMaterial, validColor,
                40.0, validBootVariant, LocalDate.now(), "Season");

        assertThrows(IllegalArgumentException.class, () -> item.setFootSize(30.0));
    }

    @Test
    void setFootSize_shouldThrowException_whenTooLarge() {
        Footwear item = new Footwear("Boot", "Brand", 100.0, 10, validMaterial, validColor,
                40.0, validBootVariant, LocalDate.now(), "Season");

        assertThrows(IllegalArgumentException.class, () -> item.setFootSize(50.0));
    }

    @Test
    void boot_isWaterproof_shouldReturnTrue_whenTrue() {
        var boot = new Footwear.Boot(true);
        assertTrue(boot.isWaterproof());
    }

    @Test
    void boot_isWaterproof_shouldReturnFalse_whenFalse() {
        var boot = new Footwear.Boot(false);
        assertFalse(boot.isWaterproof());
    }

    @Test
    void boot_setWaterproof_shouldChangeValue() {
        var boot = new Footwear.Boot(false);
        boot.setWaterproof(true);
        assertTrue(boot.isWaterproof());
    }

    @Test
    void heeledShoe_constructor_shouldThrowException_whenHeightNegative() {
        assertThrows(Exception.class, () -> new Footwear.HeeledShoe(-5.0));
    }

    @Test
    void heeledShoe_setHeelHeight_shouldUpdateValue_whenValid() {
        var shoe = new Footwear.HeeledShoe(5.0);
        shoe.setHeelHeight(10.0);
        assertEquals(10.0, shoe.getHeelHeight());
    }

    @Test
    void heeledShoe_setHeelHeight_shouldThrowException_whenNegative() {
        var shoe = new Footwear.HeeledShoe(5.0);
        assertThrows(Exception.class, () -> shoe.setHeelHeight(-1.0));
    }
}