package test.Editions;

import main.Editions.StandardEdition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class StandardEditionTest {

    private final List<String> materials = List.of("Cotton");
    private final List<String> colors = List.of("Green");

    @Test
    void testValidCreation() {
        LocalDate startDate = LocalDate.now().minusDays(1);
        StandardEdition se = new StandardEdition(
                "Spring Shirt", "BrandY", 200.0, 10,
                materials, colors, startDate, "Spring"
        );

        Assertions.assertEquals("Spring Shirt", se.getName());
        Assertions.assertEquals("BrandY", se.getBrand());
        Assertions.assertEquals(200.0, se.getPrice());
        Assertions.assertEquals(10, se.getStockQuantity());
        Assertions.assertEquals(materials, se.getMaterial());
        Assertions.assertEquals(colors, se.getColor());
        Assertions.assertEquals(startDate, se.getProductionStartDate());
        Assertions.assertEquals("Spring", se.getSeason());
    }

    @Test
    void testSettersAndGetters() {
        LocalDate startDate = LocalDate.now().minusDays(2);
        StandardEdition se = new StandardEdition(
                "Spring Shirt", "BrandY", 200.0, 10,
                materials, colors, startDate, "Spring"
        );

        LocalDate newDate = LocalDate.now().minusDays(10);
        se.setProductionStartDate(newDate);
        se.setSeason("Summer");

        Assertions.assertEquals(newDate, se.getProductionStartDate());
        Assertions.assertEquals("Summer", se.getSeason());
    }

    @Test
    void testProductionStartDateInFutureThrows() {
        LocalDate futureDate = LocalDate.now().plusDays(1);

        IllegalArgumentException ex = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new StandardEdition(
                        "Future Shirt", "BrandY", 200.0, 10,
                        materials, colors, futureDate, "Spring"
                )
        );

        Assertions.assertTrue(ex.getMessage().contains("productionStartDate"));
    }

    @Test
    void testNullSeasonThrows() {
        LocalDate startDate = LocalDate.now().minusDays(1);

        IllegalArgumentException ex = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new StandardEdition(
                        "Shirt", "BrandY", 200.0, 10,
                        materials, colors, startDate, null
                )
        );

        Assertions.assertTrue(ex.getMessage().contains("season"));
    }
}
