package test.PersistenceModels;

import main.Enums.ClothingSize;
import main.PersistenceModels.Trousers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrousersTest {

    @Test
    void constructor_shouldThrowException_whenWaistLengthNotPositive() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Trousers("Trousers", "HM", 120, 5,
                        List.of("Denim"), List.of("Blue"),
                        ClothingSize.L, -10, 100)
        );

        assertTrue(exception.getMessage().contains("waistLength"));
    }

    @Test
    void constructor_shouldThrowException_whenLegLengthNotPositive() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Trousers("Trousers", "HM", 120, 5,
                        List.of("Denim"), List.of("Blue"),
                        ClothingSize.L, 80, 0)
        );

        assertTrue(exception.getMessage().contains("legLength"));
    }

    @Test
    void constructor_shouldAddObjectToExtent_whenValid() {
        Trousers trousers = new Trousers("Trousers2", "HM", 120, 5,
                List.of("Denim"), List.of("Blue"),
                ClothingSize.L, 80, 100);

        boolean result = Trousers.getExtent().contains(trousers);

        assertTrue(result);
    }

    @Test
    void constructor_shouldNotAddObjectToExtent_whenConstructionFails() {
        int before = Trousers.getExtent().size();

        try {
            new Trousers("Trousers3", "HM", 120, 5,
                    List.of("Denim"), List.of("Blue"),
                    ClothingSize.L, -1, 100);
        } catch (Exception ignored) {}

        assertEquals(before, Trousers.getExtent().size());
    }

    @Test
    void getWaistLength_shouldReturnCorrectValue_true() {
        Trousers trousers = new Trousers("Jeans", "Levis", 120, 5,
                List.of("Denim"), List.of("Blue"),
                ClothingSize.L, 80, 100);

        double result = trousers.getWaistLength();

        assertEquals(80, result);
    }

    @Test
    void getWaistLength_shouldReturnIncorrectValue_false() {
        Trousers trousers = new Trousers("Jeans", "Levis", 120, 5,
                List.of("Denim"), List.of("Blue"),
                ClothingSize.L, 80, 100);

        double result = trousers.getWaistLength();

        assertNotEquals(90, result);
    }

    @Test
    void setWaistLength_shouldChangeValue_true() {
        Trousers trousers = new Trousers("Jeans", "Levis", 120, 5,
                List.of("Denim"), List.of("Blue"),
                ClothingSize.L, 80, 100);

        trousers.setWaistLength(85);

        assertEquals(85, trousers.getWaistLength());
    }

    @Test
    void setWaistLength_shouldNotKeepOldValue_false() {
        Trousers trousers = new Trousers("Jeans", "Levis", 120, 5,
                List.of("Denim"), List.of("Blue"),
                ClothingSize.L, 80, 100);

        trousers.setWaistLength(85);

        assertNotEquals(80, trousers.getWaistLength());
    }

    @Test
    void getLegLength_shouldReturnCorrectValue_true() {
        Trousers trousers = new Trousers("Jeans", "Levis", 120, 5,
                List.of("Denim"), List.of("Blue"),
                ClothingSize.L, 80, 100);

        double result = trousers.getLegLength();

        assertEquals(100, result);
    }

    @Test
    void getLegLength_shouldReturnIncorrectValue_false() {
        Trousers trousers = new Trousers("Jeans", "Levis", 120, 5,
                List.of("Denim"), List.of("Blue"),
                ClothingSize.L, 80, 100);

        double result = trousers.getLegLength();

        assertNotEquals(95, result);
    }

    @Test
    void getExtent_shouldContainCreatedObject_true() {
        Trousers trousers = new Trousers("Jeans2", "Levis", 120, 5,
                List.of("Denim"), List.of("Black"),
                ClothingSize.L, 82, 102);

        boolean result = Trousers.getExtent().contains(trousers);

        assertTrue(result);
    }

    @Test
    void getExtent_shouldNotContainRemovedObject_false() {
        Trousers trousers = new Trousers("Jeans3", "Levis", 120, 5,
                List.of("Denim"), List.of("Black"),
                ClothingSize.L, 82, 102);

        Trousers.getExtent().remove(trousers);

        assertFalse(Trousers.getExtent().contains(trousers));
    }
}
