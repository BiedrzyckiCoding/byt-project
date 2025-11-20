package test.Clothing;

import main.Clothing.Shirt;
import main.Enums.ClothingSize;
import main.Enums.Fit;
import main.Enums.SleeveLength;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ShirtTest {

    private final List<String> materials = List.of("Cotton");
    private final List<String> colors = List.of("Black");

    @BeforeEach
    void resetExtent() {
        Shirt.setExtent(new ArrayList<>());
        //to have empty shirt list, because it is static in the original class
    }

    @Test
    void testValidCreation() {
        Shirt s = new Shirt(
                "Oxford Shirt", "Ralph Lauren", 120.0, 5,
                materials, colors, ClothingSize.L,
                SleeveLength.LONG, Fit.REGULAR
        );

        Assertions.assertEquals("Oxford Shirt", s.getName());
        Assertions.assertEquals("Ralph Lauren", s.getBrand());
        Assertions.assertEquals(120.0, s.getPrice());
        Assertions.assertEquals(5, s.getStockQuantity());
        Assertions.assertEquals(materials, s.getMaterial());
        Assertions.assertEquals(colors, s.getColor());
        Assertions.assertEquals(ClothingSize.L, s.getClothingSize());
        Assertions.assertEquals(SleeveLength.LONG, s.getSleeveLength());
        Assertions.assertEquals(Fit.REGULAR, s.getFit());
    }

    @Test
    void testNullSleeveLengthThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Shirt(
                        "Shirt", "Brand", 100.0, 5,
                        materials, colors, ClothingSize.M,
                        null, Fit.SLIM
                )
        );
    }

    @Test
    void testNullFitThrows() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new Shirt(
                        "Shirt", "Brand", 100.0, 5,
                        materials, colors, ClothingSize.M,
                        SleeveLength.SHORT, null
                )
        );
    }

    @Test
    void testExtentAddsAutomatically() {
        Assertions.assertEquals(0, Shirt.getExtent().size());

        Shirt s = new Shirt(
                "Shirt", "Brand", 90.0, 3,
                materials, colors, ClothingSize.S,
                SleeveLength.SHORT, Fit.SLIM
        );

        Assertions.assertEquals(1, Shirt.getExtent().size());
        Assertions.assertTrue(Shirt.getExtent().contains(s));
    }

    @Test
    void testSetExtentResetsList() {
        Shirt s1 = new Shirt(
                "A", "B", 50.0, 2,
                materials, colors, ClothingSize.M,
                SleeveLength.LONG, Fit.REGULAR
        );

        Assertions.assertEquals(1, Shirt.getExtent().size());

        Shirt.setExtent(new ArrayList<>());

        Assertions.assertEquals(0, Shirt.getExtent().size());
    }

    @Test
    void testSetters() {
        Shirt s = new Shirt(
                "Test Shirt", "Brand", 80.0, 4,
                materials, colors, ClothingSize.L,
                SleeveLength.LONG, Fit.REGULAR
        );

        s.setSleeveLength(SleeveLength.SHORT);
        s.setFit(Fit.SLIM);

        Assertions.assertEquals(SleeveLength.SHORT, s.getSleeveLength());
        Assertions.assertEquals(Fit.SLIM, s.getFit());
    }

    @Test
    void testPrivateAddToExtentRejectsNull() throws Exception {
        var method = Shirt.class.getDeclaredMethod("addToExtent", Shirt.class);
        method.setAccessible(true);

        InvocationTargetException ex = Assertions.assertThrows(InvocationTargetException.class, () ->
                method.invoke(null, new Object[]{null})
        );

        //unwrap the actual exception thrown by the private method
        Assertions.assertTrue(ex.getTargetException() instanceof IllegalArgumentException);
        Assertions.assertEquals("Shirt cannot be null", ex.getTargetException().getMessage());
    }
}
