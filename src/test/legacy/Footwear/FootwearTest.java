//package test.legacy.Footwear;
//
//import main.Footwear.Footwear;
//import main.Clothing.Item;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
////simple concrete subclass for testing
//class TestFootwear extends Footwear {
//    public TestFootwear(String name, String brand, double price, int stockQuantity, List<String> material, List<String> color, double footSize) {
//        super(name, brand, price, stockQuantity, material, color, footSize);
//    }
//}
//
//public class FootwearTest {
//
//    private final List<String> materials = List.of("Leather");
//    private final List<String> colors = List.of("Black");
//
//    @Test
//    void testValidCreation() {
//        Footwear shoe = new TestFootwear(
//                "Sneakers", "Nike", 120.0, 10,
//                materials, colors, 40
//        );
//
//        Assertions.assertEquals("Sneakers", shoe.getName());
//        Assertions.assertEquals("Nike", shoe.getBrand());
//        Assertions.assertEquals(120.0, shoe.getPrice());
//        Assertions.assertEquals(10, shoe.getStockQuantity());
//        Assertions.assertEquals(materials, shoe.getMaterial());
//        Assertions.assertEquals(colors, shoe.getColor());
//    }
//
//    @Test
//    void testPriceTooLowThrows() {
//        IllegalArgumentException ex = Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> new TestFootwear(
//                        "CheapShoes", "BrandX", 50.0, 5,
//                        materials, colors,39
//                )
//        );
//
//        Assertions.assertTrue(ex.getMessage().contains("Price for footwear"));
//    }
//
//    @Test
//    void testItemSettersAndGetters() {
//        Footwear shoe = new TestFootwear(
//                "Sneakers", "Nike", 120.0, 10,
//                materials, colors,45
//        );
//
//        shoe.setName("Running Shoes");
//        shoe.setBrand("Adidas");
//        shoe.setPrice(150.0);
//        shoe.setStockQuantity(7);
//        shoe.setMaterial(List.of("Synthetic"));
//        shoe.setColor(List.of("White"));
//
//        Assertions.assertEquals("Running Shoes", shoe.getName());
//        Assertions.assertEquals("Adidas", shoe.getBrand());
//        Assertions.assertEquals(150.0, shoe.getPrice());
//        Assertions.assertEquals(7, shoe.getStockQuantity());
//        Assertions.assertEquals(List.of("Synthetic"), shoe.getMaterial());
//        Assertions.assertEquals(List.of("White"), shoe.getColor());
//    }
//}
