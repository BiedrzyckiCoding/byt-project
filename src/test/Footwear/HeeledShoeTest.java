//package test.Footwear;
//
//import main.Footwear.HeeledShoe;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class HeeledShoeTest {
//
//    private final List<String> materials = List.of("Leather");
//    private final List<String> colors = List.of("Red");
//
//    @BeforeEach
//    void resetExtent() {
//        HeeledShoe.setExtent(new ArrayList<>()); //empty state for each test
//    }
//
//    @Test
//    void testValidCreation() {
//        HeeledShoe shoe = new HeeledShoe(
//                "Evening Shoe", "BrandY", 120.0, 5,
//                materials, colors, 7.5, 39
//        );
//
//        assertEquals("Evening Shoe", shoe.getName());
//        assertEquals("BrandY", shoe.getBrand());
//        assertEquals(120.0, shoe.getPrice());
//        assertEquals(5, shoe.getStockQuantity());
//        assertEquals(materials, shoe.getMaterial());
//        assertEquals(colors, shoe.getColor());
//        assertEquals(7.5, shoe.getHeelHeight());
//    }
//
//    @Test
//    void testPriceTooLowThrows() {
//        IllegalArgumentException ex = assertThrows(
//                IllegalArgumentException.class,
//                () -> new HeeledShoe("Cheap Shoe", "BrandX", 50.0, 5, materials, colors, 5.0, 38)
//        );
//
//        assertTrue(ex.getMessage().contains("Price for footwear"));
//    }
//
//    @Test
//    void testHeelHeightSetterGetter() {
//        HeeledShoe shoe = new HeeledShoe("Evening Shoe", "BrandY", 120.0, 5, materials, colors, 7.5, 40);
//
//        shoe.setHeelHeight(8.0);
//        assertEquals(8.0, shoe.getHeelHeight());
//    }
//
//    @Test
//    void testExtentManagement() {
//        HeeledShoe shoe1 = new HeeledShoe("Shoe1", "BrandA", 100.0, 3, materials, colors, 5.0, 42);
//        HeeledShoe shoe2 = new HeeledShoe("Shoe2", "BrandB", 130.0, 2, materials, colors, 6.5, 35);
//
//        List<HeeledShoe> extent = HeeledShoe.getExtent();
//        assertEquals(2, extent.size());
//        assertTrue(extent.contains(shoe1));
//        assertTrue(extent.contains(shoe2));
//
//        HeeledShoe.setExtent(new ArrayList<>());
//        assertEquals(0, HeeledShoe.getExtent().size());
//    }
//
//    @Test
//    void testPrivateAddToExtentRejectsNull() throws Exception {
//        Method m = HeeledShoe.class.getDeclaredMethod("addToExtent", HeeledShoe.class);
//        m.setAccessible(true);
//
//        InvocationTargetException ex = assertThrows(InvocationTargetException.class, () ->
//                m.invoke(null, (HeeledShoe) null)
//        );
//
//        // literally like in the other classes lol
//        assertTrue(ex.getCause() instanceof IllegalArgumentException);
//        assertEquals("HeeledShoe cannot be null", ex.getCause().getMessage());
//    }
//}
