//package test.Editions;
//
//import main.Editions.LimitedEdition;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public class LimitedEditionTest {
//
//    private final List<String> materials = List.of("Leather");
//    private final List<String> colors = List.of("Red");
//
//    @Test
//    void testValidCreation() {
//        LocalDate release = LocalDate.now().minusDays(1);
//        LimitedEdition le = new LimitedEdition(
//                "Special Jacket", "BrandX", 500.0, 5,
//                materials, colors, release, 100
//        );
//
//        Assertions.assertEquals("Special Jacket", le.getName());
//        Assertions.assertEquals("BrandX", le.getBrand());
//        Assertions.assertEquals(500.0, le.getPrice());
//        Assertions.assertEquals(5, le.getStockQuantity());
//        Assertions.assertEquals(materials, le.getMaterial());
//        Assertions.assertEquals(colors, le.getColor());
//        Assertions.assertEquals(release, le.getReleaseDate());
//        Assertions.assertEquals(100, le.getTotalProduced());
//    }
//
//    @Test
//    void testSettersAndGetters() {
//        LocalDate release = LocalDate.now().minusDays(2);
//        LimitedEdition le = new LimitedEdition(
//                "Special Jacket", "BrandX", 500.0, 5,
//                materials, colors, release, 100
//        );
//
//        LocalDate newRelease = LocalDate.now().minusDays(10);
//        le.setReleaseDate(newRelease);
//        le.setTotalProduced(200);
//
//        Assertions.assertEquals(newRelease, le.getReleaseDate());
//        Assertions.assertEquals(200, le.getTotalProduced());
//    }
//
//    @Test
//    void testReleaseDateInFutureThrows() {
//        LocalDate futureDate = LocalDate.now().plusDays(1);
//
//        IllegalArgumentException ex = Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> new LimitedEdition(
//                        "Future Jacket", "BrandX", 500.0, 5,
//                        materials, colors, futureDate, 50
//                )
//        );
//
//        Assertions.assertTrue(ex.getMessage().contains("releaseDate"));
//    }
//
//    @Test
//    void testTotalProducedNonPositiveThrows() {
//        LocalDate release = LocalDate.now().minusDays(1);
//
//        IllegalArgumentException ex = Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> new LimitedEdition(
//                        "Limited Jacket", "BrandX", 500.0, 5,
//                        materials, colors, release, 0
//                )
//        );
//
//        Assertions.assertTrue(ex.getMessage().contains("totalProduced"));
//    }
//}
