package test.PersistenceModels;

import main.PersistenceModels.Boot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BootTest {

//    @BeforeEach
//    void clearExent(){
//        Boot.setExtent(new ArrayList<>());
//    }
    @Test
    void constructor_shouldThrowException_whenPriceBelowMinimum() {
        assertThrows(IllegalArgumentException.class, () ->
                new Boot("Boot1", "Timberland", 20, 5,
                        List.of("Leather"), List.of("Brown"),
                        true, 42)
        );
    }

    @Test
    void constructor_shouldThrowException_whenFootSizeInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                new Boot("Boot2", "Timberland", 120, 5,
                        List.of("Leather"), List.of("Brown"),
                        true, -1)
        );
    }

    @Test
    void constructor_shouldAddObjectToExtent_whenValid() {
        Boot boot = new Boot("Boot3", "Nike", 150, 5,
                List.of("Leather"), List.of("Black"),
                true, 43);

        assertTrue(Boot.getExtent().contains(boot));
    }

    @Test
    void constructor_shouldNotAddObjectToExtent_whenConstructionFails() {
        int before = Boot.getExtent().size();

        try {
            new Boot("Boot4", "Adidas", 10, 5,
                    List.of("Leather"), List.of("Black"),
                    true, 42);
        } catch (Exception ignored) {}

        assertEquals(before, Boot.getExtent().size());
    }

    @Test
    void isWaterproof_shouldReturnTrue_whenWaterproofTrue() {
        Boot boot = new Boot("Boot5", "Puma", 200, 10,
                List.of("Leather"), List.of("Gray"),
                true, 44);

        assertTrue(boot.isWaterproof());
    }

    @Test
    void isWaterproof_shouldReturnFalse_whenWaterproofFalse() {
        Boot boot = new Boot("Boot6", "Puma", 200, 10,
                List.of("Leather"), List.of("Gray"),
                false, 44);

        assertFalse(boot.isWaterproof());
    }

    @Test
    void setWaterproof_shouldChangeValue_true() {
        Boot boot = new Boot("Boot7", "Puma", 200, 10,
                List.of("Leather"), List.of("Brown"),
                false, 41);

        boot.setWaterproof(true);

        assertTrue(boot.isWaterproof());
    }

    @Test
    void setWaterproof_shouldNotRemainFalse_false() {
        Boot boot = new Boot("Boot8", "Puma", 200, 10,
                List.of("Leather"), List.of("Brown"),
                false, 41);

        boot.setWaterproof(true);

        assertFalse(!boot.isWaterproof());
    }

    @Test
    void getExtent_shouldContainCreatedObject_true() {
        Boot boot = new Boot("Boot9", "Gucci", 300, 5,
                List.of("Leather"), List.of("Black"),
                true, 45);

        assertTrue(Boot.getExtent().contains(boot));
    }

    @Test
    void getExtent_shouldNotContainRemovedObject_false() {
        Boot boot = new Boot("Boot10", "Gucci", 300, 5,
                List.of("Leather"), List.of("Black"),
                true, 45);

        Boot.getExtent().remove(boot);

        assertFalse(Boot.getExtent().contains(boot));
    }
}
