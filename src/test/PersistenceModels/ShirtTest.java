package test.PersistenceModels;

import main.Enums.ClothingSize;
import main.Enums.Fit;
import main.Enums.SleeveLength;
import main.PersistenceModels.Shirt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShirtTest {

//    @BeforeEach
//    void resetExtent() {
//        Shirt.setExtent(new ArrayList<>());
//    }
    
    @Test
    void constructor_shouldThrowException_whenSleeveLengthIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Shirt("Shirt", "Zara", 80, 5,
                        List.of("Cotton"), List.of("White"),
                        ClothingSize.M, null, Fit.REGULAR)
        );

        assertTrue(exception.getMessage().contains("sleeveLength"));
    }

    @Test
    void constructor_shouldThrowException_whenFitIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Shirt("Shirt", "Zara", 80, 5,
                        List.of("Cotton"), List.of("White"),
                        ClothingSize.M, SleeveLength.SHORT, null)
        );

        assertTrue(exception.getMessage().contains("fit"));
    }

    @Test
    void constructor_shouldAddObjectToExtent_whenValid() {
        Shirt shirt = new Shirt("Shirt2", "Zara", 80, 5,
                List.of("Cotton"), List.of("White"),
                ClothingSize.M, SleeveLength.SHORT, Fit.REGULAR);

        boolean result = Shirt.getExtent().contains(shirt);

        assertTrue(result);
    }

    @Test
    void constructor_shouldNotAddObjectToExtent_whenConstructionFails() {
        int before = Shirt.getExtent().size();
    
        try {
            new Shirt("Shirt3", "Zara", 80, 5,
                    List.of("Cotton"), List.of("White"),
                    ClothingSize.M, null, Fit.REGULAR);
        } catch (Exception ignored) {}
    
        assertEquals(before, Shirt.getExtent().size());
    }

    @Test
    void getSleeveLength_shouldReturnCorrectValue_true() {
        Shirt shirt = new Shirt("Shirt", "Brand", 80, 5,
                List.of("Cotton"), List.of("White"),
                ClothingSize.M, SleeveLength.SHORT, Fit.REGULAR);
    
        SleeveLength result = shirt.getSleeveLength();

        assertEquals(SleeveLength.SHORT, result);
    }

    @Test
    void getSleeveLength_shouldReturnIncorrectValue_false() {
        Shirt shirt = new Shirt("Shirt", "Brand", 80, 5,
                List.of("Cotton"), List.of("White"),
                ClothingSize.M, SleeveLength.SHORT, Fit.REGULAR);
    
        SleeveLength result = shirt.getSleeveLength();

        assertNotEquals(SleeveLength.LONG, result);
    }

    @Test
    void setSleeveLength_shouldChangeValue_true() {
        Shirt shirt = new Shirt("Shirt", "Brand", 80, 5,
                List.of("Cotton"), List.of("White"),
                ClothingSize.M, SleeveLength.SHORT, Fit.REGULAR);
    
        shirt.setSleeveLength(SleeveLength.LONG);

        assertEquals(SleeveLength.LONG, shirt.getSleeveLength());
    }

    @Test
    void setSleeveLength_shouldNotKeepOldValue_false() {
        Shirt shirt = new Shirt("Shirt", "Brand", 80, 5,
                List.of("Cotton"), List.of("White"),
                ClothingSize.M, SleeveLength.SHORT, Fit.REGULAR);
    
        shirt.setSleeveLength(SleeveLength.LONG);

        assertNotEquals(SleeveLength.SHORT, shirt.getSleeveLength());
    }

    @Test
    void getFit_shouldReturnCorrectValue_true() {
        Shirt shirt = new Shirt("Shirt", "Brand", 80, 5,
                List.of("Cotton"), List.of("White"),
                ClothingSize.M, SleeveLength.SHORT, Fit.REGULAR);
    
        Fit result = shirt.getFit();
    
        assertEquals(Fit.REGULAR, result);
    }

    @Test
    void getFit_shouldReturnIncorrectValue_false() {
        Shirt shirt = new Shirt("Shirt", "Brand", 80, 5,
                List.of("Cotton"), List.of("White"),
                ClothingSize.M, SleeveLength.SHORT, Fit.REGULAR);
    
        Fit result = shirt.getFit();

        assertNotEquals(Fit.SLIM, result);
    }

    @Test
    void setFit_shouldChangeValue_true() {
        Shirt shirt = new Shirt("Shirt", "Brand", 80, 5,
                List.of("Cotton"), List.of("White"),
                ClothingSize.M, SleeveLength.SHORT, Fit.REGULAR);
    
        shirt.setFit(Fit.SLIM);

        assertEquals(Fit.SLIM, shirt.getFit());
    }

    @Test
    void setFit_shouldNotKeepOldValue_false() {
        Shirt shirt = new Shirt("Shirt", "Brand", 80, 5,
                List.of("Cotton"), List.of("White"),
                ClothingSize.M, SleeveLength.SHORT, Fit.REGULAR);
    
        shirt.setFit(Fit.SLIM);
    
        assertNotEquals(Fit.REGULAR, shirt.getFit());
    }

    @Test
    void getExtent_shouldContainCreatedObject_true() {
        Shirt shirt = new Shirt("Shirt", "Brand", 80, 5,
                List.of("Cotton"), List.of("White"),
                ClothingSize.M, SleeveLength.SHORT, Fit.REGULAR);
    
        boolean result = Shirt.getExtent().contains(shirt);

        assertTrue(result);
    }

    @Test
    void getExtent_shouldNotContainNewObject_false() {
        Shirt shirt = new Shirt("Shirt2", "Brand", 80, 5,
                List.of("Cotton"), List.of("Red"),
                ClothingSize.M, SleeveLength.SHORT, Fit.REGULAR);

        Shirt.getExtent().remove(shirt);

        assertFalse(Shirt.getExtent().contains(shirt));
    }
}

