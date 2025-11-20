package test.Clothing;

import main.Clothing.Trousers;
import main.Enums.ClothingSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TrousersTest {

    private final List<String> materials = List.of("Denim");
    private final List<String> colors = List.of("Blue");

    @BeforeEach
    void resetExtent() {
        Trousers.setExtent(new ArrayList<>());
        //again we need new list
    }

    @Test
    void testValidCreation() {
        Trousers t = new Trousers(
                "Jeans", "Levis", 100.0, 5,
                materials, colors, ClothingSize.M,
                32.0, 34.0
        );

        Assertions.assertEquals("Jeans", t.getName());
        Assertions.assertEquals("Levis", t.getBrand());
        Assertions.assertEquals(100.0, t.getPrice());
        Assertions.assertEquals(5, t.getStockQuantity());
        Assertions.assertEquals(materials, t.getMaterial());
        Assertions.assertEquals(colors, t.getColor());
        Assertions.assertEquals(ClothingSize.M, t.getClothingSize());
        Assertions.assertEquals(32.0, t.getWaistLength());
        Assertions.assertEquals(34.0, t.getLegLength());
    }

    @Test
    void testSettersAndGetters() {
        Trousers t = new Trousers(
                "Jeans", "Levis", 100.0, 5,
                materials, colors, ClothingSize.M,
                32.0, 34.0
        );

        t.setWaistLength(36.0);
        t.setLegLength(38.0);

        Assertions.assertEquals(36.0, t.getWaistLength());
        Assertions.assertEquals(38.0, t.getLegLength());
    }

    @Test
    void testExtentAddsAutomatically() {
        Assertions.assertEquals(0, Trousers.getExtent().size());

        Trousers t = new Trousers(
                "Cargo Pants", "Brand", 80.0, 2,
                materials, colors, ClothingSize.L,
                30.0, 32.0
        );

        Assertions.assertEquals(1, Trousers.getExtent().size());
        Assertions.assertTrue(Trousers.getExtent().contains(t));
    }

    @Test
    void testSetExtentResetsList() {
        Trousers t1 = new Trousers(
                "Trousers1", "Brand", 50.0, 1,
                materials, colors, ClothingSize.S,
                28.0, 30.0
        );

        Assertions.assertEquals(1, Trousers.getExtent().size());

        Trousers.setExtent(new ArrayList<>());

        Assertions.assertEquals(0, Trousers.getExtent().size());
    }

    @Test
    void testPrivateAddToExtentRejectsNull() throws Exception {
        Method method = Trousers.class.getDeclaredMethod("addToExtent", Trousers.class);
        method.setAccessible(true);

        InvocationTargetException ex = Assertions.assertThrows(InvocationTargetException.class, () ->
                method.invoke(null, new Object[]{null})
        );

        Assertions.assertTrue(ex.getTargetException() instanceof IllegalArgumentException);
        Assertions.assertEquals("Trousers cannot be null", ex.getTargetException().getMessage());
    }
}
