//package test.legacy.Clothing;
//
//import main.Clothing.ClothingItem;
//import main.Enums.ClothingSize;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;
//
//import java.util.List;
//
//class TestClothingItem extends ClothingItem {
//    public TestClothingItem(String name, String brand, double price, int stockQuantity,
//                            List<String> material, List<String> color, ClothingSize clothingSize) {
//        super(name, brand, price, stockQuantity, material, color, clothingSize);
//    }
//}
//
//public class ClothingItemTest {
//
//    private final List<String> materials = List.of("Cotton");
//    private final List<String> colors = List.of("Blue");
//
//    @Test
//    void testValidCreation() {
//        ClothingItem item = new TestClothingItem(
//                "T-Shirt", "Nike", 50.0, 10,
//                materials, colors, ClothingSize.M
//        );
//
//        Assertions.assertEquals("T-Shirt", item.getName());
//        Assertions.assertEquals("Nike", item.getBrand());
//        Assertions.assertEquals(50.0, item.getPrice());
//        Assertions.assertEquals(10, item.getStockQuantity());
//        Assertions.assertEquals(materials, item.getMaterial());
//        Assertions.assertEquals(colors, item.getColor());
//        Assertions.assertEquals(ClothingSize.M, item.getClothingSize());
//    }
//
//    @Test
//    void testNullClothingSize() {
//        IllegalArgumentException ex = Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> new TestClothingItem(
//                        "T-Shirt", "Nike", 50.0, 10,
//                        materials, colors, null
//                )
//        );
//
//        Assertions.assertTrue(ex.getMessage().contains("clothingSize"));
//    }
//
//    @Test
//    void testPriceTooLow() {
//        IllegalArgumentException ex = Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> new TestClothingItem(
//                        "T-Shirt", "Nike", 30.0, 10,
//                        materials, colors, ClothingSize.M
//                )
//        );
//
//        Assertions.assertTrue(ex.getMessage().contains("Price for clothing should be greater"));
//    }
//
//    @Test
//    void testClothingSizeSetter() {
//        ClothingItem item = new TestClothingItem(
//                "T-Shirt", "Nike", 60.0, 10,
//                materials, colors, ClothingSize.M
//        );
//
//        item.setClothingSize(ClothingSize.L);
//
//        Assertions.assertEquals(ClothingSize.L, item.getClothingSize());
//    }
//}
