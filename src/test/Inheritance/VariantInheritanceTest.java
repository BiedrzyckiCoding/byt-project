package test.Inheritance;

import main.Enums.ClothingSize;
import main.Enums.Fit;
import main.Enums.SleeveLength;
import main.PersistenceModels.ClothingItem;
import main.PersistenceModels.Footwear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VariantInheritanceTest {

    private List<String> materials;
    private List<String> colors;

    @BeforeEach
    void setUp() {
        materials = List.of("Cotton");
        colors = List.of("Blue");
    }

    // clothing

    @Test
    void clothing_ShouldBeShirt_WhenInitializedWithShirtVariant() {
        var shirtVariant = new ClothingItem.Shirt(SleeveLength.LONG, Fit.SLIM);

        ClothingItem item = new ClothingItem(
                "T-Shirt", "Nike", 50.0, 10, materials, colors,
                ClothingSize.L, shirtVariant,
                LocalDate.now(), "Season"
        );

        assertTrue(item.getVariant() instanceof ClothingItem.Shirt);
    }

    @Test
    void clothing_ShouldBeTrousers_WhenInitializedWithTrousersVariant() {
        var trousersVariant = new ClothingItem.Trousers(32.0, 30.0);

        ClothingItem item = new ClothingItem(
                "Jeans", "Levis", 80.0, 10, materials, colors,
                ClothingSize.M, trousersVariant,
                LocalDate.now(), "Season"
        );

        assertTrue(item.getVariant() instanceof ClothingItem.Trousers);
    }

    @Test
    void clothing_ShouldBeHoodie_WhenInitializedWithHoodieVariant() {
        var hoodieVariant = new ClothingItem.Hoodie(true);

        ClothingItem item = new ClothingItem(
                "Hoodie", "Gap", 60.0, 10, materials, colors,
                ClothingSize.XL, hoodieVariant,
                LocalDate.now(), "Season"
        );

        assertTrue(item.getVariant() instanceof ClothingItem.Hoodie);
    }

    // complete (Clothing)

    @Test
    void clothing_ShouldThrowException_WhenVariantIsNull() {
        ClothingItem.ClothingVariant nullVariant = null;

        assertThrows(Exception.class, () ->
                new ClothingItem(
                        "Item", "Brand", 50.0, 10, materials, colors,
                        ClothingSize.M, nullVariant,
                        LocalDate.now(), "Season"
                )
        );
    }

    // footwear

    @Test
    void footwear_ShouldBeBoot_WhenInitializedWithBootVariant() {
        var bootVariant = new Footwear.Boot(true);

        Footwear item = new Footwear(
                "Hikers", "Timberland", 120.0, 5, materials, colors,
                42.0, bootVariant,
                LocalDate.now(), "Season"
        );

        assertTrue(item.getVariant() instanceof Footwear.Boot);
    }

    @Test
    void footwear_ShouldBeHeeledShoe_WhenInitializedWithHeeledShoeVariant() {
        var heelVariant = new Footwear.HeeledShoe(10.0);

        Footwear item = new Footwear(
                "Stilettos", "Prada", 200.0, 5, materials, colors,
                38.0, heelVariant,
                LocalDate.now(), "Season"
        );

        assertTrue(item.getVariant() instanceof Footwear.HeeledShoe);
    }

    // complete (footwear)

    @Test
    void footwear_ShouldThrowException_WhenVariantIsNull() {
        Footwear.FootwearVariant nullVariant = null;

        assertThrows(Exception.class, () ->
                new Footwear(
                        "Shoe", "Brand", 100.0, 10, materials, colors,
                        40.0, nullVariant,
                        LocalDate.now(), "Season"
                )
        );
    }


    @Test
    void shirt_SetSleeveLength_ShouldThrow_WhenNull() {
        var shirt = new ClothingItem.Shirt(SleeveLength.SHORT, Fit.REGULAR);

        assertThrows(Exception.class, () -> shirt.setSleeveLength(null));
    }

    @Test
    void trousers_Constructor_ShouldThrow_WhenDimensionsNegative() {
        assertThrows(Exception.class, () -> new ClothingItem.Trousers(-1.0, 30.0));
    }

    @Test
    void trousers_SetWaist_ShouldThrow_WhenNegative() {
        var trousers = new ClothingItem.Trousers(32.0, 30.0);

        assertThrows(Exception.class, () -> trousers.setWaistLength(-5.0));
    }

    @Test
    void heeledShoe_Constructor_ShouldThrow_WhenHeightNegative() {
        assertThrows(Exception.class, () -> new Footwear.HeeledShoe(-5.0));
    }

    @Test
    void boot_SetWaterproof_ShouldUpdateState() {
        var boot = new Footwear.Boot(false);

        boot.setWaterproof(true);

        assertTrue(boot.isWaterproof());
    }
}