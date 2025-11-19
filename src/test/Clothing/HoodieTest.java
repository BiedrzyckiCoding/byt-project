package test.Clothing;

import main.Clothing.Hoodie;
import main.Enums.ClothingSize;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HoodieTest {

    private List<String> materials;
    private List<String> colors;

    @BeforeEach
    void setUp() {
        materials = List.of("Cotton");
        colors = List.of("Black");

        //reset extent before each test
        Hoodie.setExtent(new ArrayList<>());
    }

    @Test
    //should correctly create Hoodie with valid attributes
    void testValidHoodieCreation() {
        Hoodie hoodie = new Hoodie(
                "Classic Hoodie", "Adidas", 60.0, 5,
                materials, colors, ClothingSize.L, true
        );

        assertEquals("Classic Hoodie", hoodie.getName());
        assertEquals("Adidas", hoodie.getBrand());
        assertEquals(60.0, hoodie.getPrice());
        assertEquals(5, hoodie.getStockQuantity());
        assertEquals(materials, hoodie.getMaterial());
        assertEquals(colors, hoodie.getColor());
        assertEquals(ClothingSize.L, hoodie.getClothingSize());
        assertTrue(hoodie.isCape());
    }

    @Test
    //setter should update cape value
    void testCapeSetter() {
        Hoodie hoodie = new Hoodie(
                "Hoodie X", "Nike", 55.0, 3,
                materials, colors, ClothingSize.M, false
        );

        hoodie.setCape(true);
        assertTrue(hoodie.isCape());

        hoodie.setCape(false);
        assertFalse(hoodie.isCape());
    }

    @Test
    //new Hoodie should be automatically added to the extent
    void testExtentAutoAdd() {
        assertEquals(0, Hoodie.getExtent().size());

        Hoodie h1 = new Hoodie(
                "H1", "BrandA", 50.0, 2,
                materials, colors, ClothingSize.S, false
        );

        assertEquals(1, Hoodie.getExtent().size());
        assertTrue(Hoodie.getExtent().contains(h1));
    }

    @Test
    //setExtent should replace entire extent list
    void testSetExtent() {
        Hoodie h1 = new Hoodie("H1", "B", 50, 1, materials, colors, ClothingSize.M, false);
        Hoodie h2 = new Hoodie("H2", "C", 60, 2, materials, colors, ClothingSize.L, true);

        List<Hoodie> newExtent = List.of(h1);

        Hoodie.setExtent(newExtent);

        assertEquals(1, Hoodie.getExtent().size());
        assertTrue(Hoodie.getExtent().contains(h1));
        assertFalse(Hoodie.getExtent().contains(h2));
    }

    @Test
    //addToExtent should throw when null is added
    void testNullHoodieInExtent() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Hoodie.addToExtent(null);
            //impossible to call private addToExtent directly, but constructor uses it.

            Hoodie.setExtent(new ArrayList<>());
            //simulate the behavior manually using reflection

            var method = Hoodie.class.getDeclaredMethod("addToExtent", Hoodie.class);
            method.setAccessible(true);
            method.invoke(null, (Hoodie) null);
        });
    }

    @Test
    //superclass validation: should throw for price below minimum
    void testInvalidPrice() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Hoodie(
                        "Cheap Hoodie", "Brand", 20.0, 5,
                        materials, colors, ClothingSize.M, false
                )
        );

        assertTrue(ex.getMessage().contains("Price for clothing should be greater"));
    }

    @Test
    //superclass validation: should throw when ClothingSize is null
    void testNullClothingSize() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new Hoodie(
                        "Hoodie", "Brand", 50.0, 5,
                        materials, colors, null, true
                )
        );

        assertTrue(ex.getMessage().contains("clothingSize"));
    }
}
