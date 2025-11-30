package test.PersistenceModels;

import main.PersistenceModels.HeeledShoe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeeledShoeTest {

//    @BeforeEach
//    void clearExent(){
//        HeeledShoe.setExtent(new ArrayList<>());
//    }
    @Test
    void constructor_shouldThrowException_whenHeelHeightNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                new HeeledShoe("Heel1", "Zara", 150, 5,
                        List.of("Leather"), List.of("Black"),
                        -5, 38)
        );
    }

    @Test
    void constructor_shouldThrowException_whenPriceBelowMinimum() {
        assertThrows(IllegalArgumentException.class, () ->
                new HeeledShoe("Heel2", "Zara", 20, 5,
                        List.of("Leather"), List.of("Black"),
                        5, 38)
        );
    }

    @Test
    void constructor_shouldAddObjectToExtent_whenValid() {
        HeeledShoe hs = new HeeledShoe("Heel3", "Prada", 300, 3,
                List.of("Leather"), List.of("Red"),
                6, 39);

        assertTrue(HeeledShoe.getExtent().contains(hs));
    }

    @Test
    void constructor_shouldNotAddObjectToExtent_whenConstructionFails() {
        int before = HeeledShoe.getExtent().size();

        try {
            new HeeledShoe("Heel4", "Prada", 10, 3,
                    List.of("Leather"), List.of("Red"),
                    6, 39);
        } catch (Exception ignored) {}

        assertEquals(before, HeeledShoe.getExtent().size());
    }

    @Test
    void getHeelHeight_shouldReturnCorrectValue() {
        HeeledShoe hs = new HeeledShoe("Heel5", "Armani", 250, 4,
                List.of("Leather"), List.of("Blue"),
                7, 40);

        assertEquals(7, hs.getHeelHeight());
    }

    @Test
    void setHeelHeight_shouldThrowException_whenNegative() {
        HeeledShoe hs = new HeeledShoe("Heel6", "Armani", 250, 4,
                List.of("Leather"), List.of("Blue"),
                7, 40);

        assertThrows(IllegalArgumentException.class, () ->
                hs.setHeelHeight(-1)
        );
    }

    @Test
    void setHeelHeight_shouldUpdateValue() {
        HeeledShoe hs = new HeeledShoe("Heel7", "Armani", 250, 4,
                List.of("Leather"), List.of("Blue"),
                3, 40);

        hs.setHeelHeight(10);

        assertEquals(10, hs.getHeelHeight());
    }

    @Test
    void getExtent_shouldContainCreatedObject_true() {
        HeeledShoe hs = new HeeledShoe("Heel8", "Gucci", 500, 2,
                List.of("Leather"), List.of("Gold"),
                8, 38);

        assertTrue(HeeledShoe.getExtent().contains(hs));
    }

    @Test
    void getExtent_shouldNotContainRemovedObject_false() {
        HeeledShoe hs = new HeeledShoe("Heel9", "Gucci", 500, 2,
                List.of("Leather"), List.of("Gold"),
                8, 38);

        HeeledShoe.getExtent().remove(hs);

        assertFalse(HeeledShoe.getExtent().contains(hs));
    }
}
