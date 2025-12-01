package test.PersistenceModels;

import main.Enums.ClothingSize;
import main.PersistenceModels.Hoodie;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HoodieTest {

    @Test
    void constructor_shouldThrowException_whenClothingSizeIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Hoodie("Hoodie", "Nike", 100, 5,
                        List.of("Cotton"), List.of("Black"),
                        null, true)
        );

        assertTrue(exception.getMessage().contains("clothingSize"));
    }

    @Test
    void constructor_shouldThrowException_whenPriceBelowMinimum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Hoodie("Hoodie", "Nike", 30, 5,
                        List.of("Cotton"), List.of("Black"),
                        ClothingSize.M, true)
        );

        assertTrue(exception.getMessage().contains("Price for clothing"));
    }

    @Test
    void constructor_shouldAddObjectToExtent_whenValid() {
        Hoodie hoodie = new Hoodie("Hoodie2", "Nike", 100, 5,
                List.of("Cotton"), List.of("Black"),
                ClothingSize.M, true);

        boolean result = Hoodie.getExtent().contains(hoodie);

        assertTrue(result);
    }

    @Test
    void constructor_shouldNotAddObjectToExtent_whenConstructionFails() {
        int before = Hoodie.getExtent().size();

        try {
            new Hoodie("Hoodie3", "Nike", 20, 5,
                    List.of("Cotton"), List.of("Black"),
                    ClothingSize.M, true);
        } catch (Exception ignored) {}

        assertEquals(before, Hoodie.getExtent().size());
    }

    @Test
    void isCape_shouldReturnTrue_whenCapeTrue() {
        Hoodie hoodie = new Hoodie("Hoodie", "Adidas", 150, 5,
                List.of("Cotton"), List.of("Black"),
                ClothingSize.XL, true);

        boolean result = hoodie.isCape();

        assertTrue(result);
    }

    @Test
    void isCape_shouldReturnFalse_whenCapeFalse() {
        Hoodie hoodie = new Hoodie("Hoodie", "Adidas", 150, 5,
                List.of("Cotton"), List.of("Black"),
                ClothingSize.XL, false);

        boolean result = hoodie.isCape();

        assertFalse(result);
    }

    @Test
    void setCape_shouldChangeValue_true() {
        Hoodie hoodie = new Hoodie("Hoodie2", "Adidas", 150, 5,
                List.of("Cotton"), List.of("Gray"),
                ClothingSize.L, false);

        hoodie.setCape(true);

        assertTrue(hoodie.isCape());
    }

    @Test
    void setCape_shouldNotRemainFalse_false() {
        Hoodie hoodie = new Hoodie("Hoodie3", "Adidas", 150, 5,
                List.of("Cotton"), List.of("Gray"),
                ClothingSize.L, false);

        hoodie.setCape(true);
   
        assertFalse(!hoodie.isCape());
    }

    @Test
    void getExtent_shouldContainCreatedObject_true() {
        Hoodie hoodie = new Hoodie("Hoodie4", "Adidas", 150, 5,
                List.of("Cotton"), List.of("Red"),
                ClothingSize.M, true);

        boolean result = Hoodie.getExtent().contains(hoodie);

        assertTrue(result);
    }
}
