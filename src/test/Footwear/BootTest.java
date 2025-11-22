//package test.Footwear;
//
//import main.Footwear.Boot;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BootTest {
//
//    private final List<String> materials = List.of("Leather");
//    private final List<String> colors = List.of("Brown");
//
//    @BeforeEach
//    void resetExtent() {
//        Boot.setExtent(new ArrayList<>()); //clean state before each test, like in other tests
//    }
//
//    @Test
//    void testValidCreation() {
//        Boot boot = new Boot("Hiking Boot", "Timberland", 150.0, 8, materials, colors, true, 44);
//
//        Assertions.assertEquals("Hiking Boot", boot.getName());
//        Assertions.assertEquals("Timberland", boot.getBrand());
//        Assertions.assertEquals(150.0, boot.getPrice());
//        Assertions.assertEquals(8, boot.getStockQuantity());
//        Assertions.assertEquals(materials, boot.getMaterial());
//        Assertions.assertEquals(colors, boot.getColor());
//        Assertions.assertTrue(boot.isWaterproof());
//    }
//
//    @Test
//    void testPriceTooLowThrows() {
//        IllegalArgumentException ex = Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> new Boot("Cheap Boot", "BrandX", 50.0, 5, materials, colors, false, 45)
//        );
//        Assertions.assertTrue(ex.getMessage().contains("Price for footwear"));
//    }
//
//    @Test
//    void testWaterproofSetterGetter() {
//        Boot boot = new Boot("Hiking Boot", "Timberland", 150.0, 8, materials, colors, true, 39);
//
//        boot.setWaterproof(false);
//        Assertions.assertFalse(boot.isWaterproof());
//    }
//
//    @Test
//    void testExtentManagement() {
//        Boot boot1 = new Boot("Boot1", "BrandA", 120.0, 5, materials, colors, true, 39);
//        Boot boot2 = new Boot("Boot2", "BrandB", 130.0, 3, materials, colors, false, 44);
//
//        List<Boot> extent = Boot.getExtent();
//        Assertions.assertEquals(2, extent.size());
//        Assertions.assertTrue(extent.contains(boot1));
//        Assertions.assertTrue(extent.contains(boot2));
//
//        Boot.setExtent(new ArrayList<>());
//        Assertions.assertEquals(0, Boot.getExtent().size());
//    }
//
//    @Test
//    void testPrivateAddToExtentRejectsNull() throws Exception {
//        var method = Boot.class.getDeclaredMethod("addToExtent", Boot.class);
//        method.setAccessible(true);
//
//        var ex = Assertions.assertThrows(
//                java.lang.reflect.InvocationTargetException.class,
//                () -> method.invoke(null, (Boot) null)
//        );
//
//        //unwrap and check the original exception
//        Assertions.assertTrue(ex.getCause() instanceof IllegalArgumentException);
//        Assertions.assertEquals("Boot cannot be null", ex.getCause().getMessage());
//    }
//}
