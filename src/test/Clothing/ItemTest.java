package test.Clothing;

import main.Clothing.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemTest {

    private final List<String> materials = List.of("Cotton");
    private final List<String> colors = List.of("Black");

    @Test
    void testValidItemCreation() {
        Item item = new Item(
                "Shirt", "Nike", 99.99, 10,
                materials, colors
        );

        Assertions.assertEquals("Shirt", item.getName());
        Assertions.assertEquals("Nike", item.getBrand());
        Assertions.assertEquals(99.99, item.getPrice());
        Assertions.assertEquals(10, item.getStockQuantity());
        Assertions.assertEquals(materials, item.getMaterial());
        Assertions.assertEquals(colors, item.getColor());
    }

    @Test
    void testEmptyNameThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Item("", "Brand", 100, 5, materials, colors));
    }

    @Test
    void testEmptyBrandThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Item("Name", "", 100, 5, materials, colors));
    }

    @Test
    void testNegativePriceThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Item("Name", "Brand", -1, 5, materials, colors));
    }

    @Test
    void testNegativeStockThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Item("Name", "Brand", 100, -1, materials, colors));
    }

    @Test
    void testNullMaterialThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Item("Name", "Brand", 100, 5, null, colors));
    }

    @Test
    void testEmptyMaterialThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Item("Name", "Brand", 100, 5, List.of(), colors));
    }

    @Test
    void testNullColorThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Item("Name", "Brand", 100, 5, materials, null));
    }

    @Test
    void testEmptyColorThrows() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Item("Name", "Brand", 100, 5, materials, List.of()));
    }

    @Test
    void testSetters() {
        Item item = new Item(
                "Shirt", "Nike", 100, 5,
                materials, colors
        );

        item.setName("NewName");
        item.setBrand("Adidas");
        item.setPrice(150);
        item.setStockQuantity(20);
        item.setMaterial(List.of("Wool"));
        item.setColor(List.of("Red"));

        Assertions.assertEquals("NewName", item.getName());
        Assertions.assertEquals("Adidas", item.getBrand());
        Assertions.assertEquals(150, item.getPrice());
        Assertions.assertEquals(20, item.getStockQuantity());
        Assertions.assertEquals(List.of("Wool"), item.getMaterial());
        Assertions.assertEquals(List.of("Red"), item.getColor());
    }

    //INDIVIDUAL TESTS: (not sure they are needed but just to have them:)
    @Test
    void testNameSetterGetter() {
        Item item = new Item("T-Shirt", "Nike", 60.0, 10, materials, colors);

        item.setName("Hoodie");

        Assertions.assertEquals("Hoodie", item.getName());
    }

    @Test
    void testBrandSetterGetter() {
        Item item = new Item("T-Shirt", "Nike", 60.0, 10, materials, colors);

        item.setBrand("Adidas");

        Assertions.assertEquals("Adidas", item.getBrand());
    }

    @Test
    void testPriceSetterGetter() {
        Item item = new Item("T-Shirt", "Nike", 60.0, 10, materials, colors);

        item.setPrice(99.99);

        Assertions.assertEquals(99.99, item.getPrice());
    }

    @Test
    void testStockQuantitySetterGetter() {
        Item item = new Item("T-Shirt", "Nike", 60.0, 10, materials, colors);

        item.setStockQuantity(50);

        Assertions.assertEquals(50, item.getStockQuantity());
    }

    @Test
    void testMaterialSetterGetter() {
        Item item = new Item("T-Shirt", "Nike", 60.0, 10, materials, colors);

        List<String> newMaterials = List.of("Wool", "Silk");
        item.setMaterial(newMaterials);

        Assertions.assertEquals(newMaterials, item.getMaterial());
    }

    @Test
    void testColorSetterGetter() {
        Item item = new Item("T-Shirt", "Nike", 60.0, 10, materials, colors);

        List<String> newColors = List.of("Black", "White");
        item.setColor(newColors);

        Assertions.assertEquals(newColors, item.getColor());
    }
}
