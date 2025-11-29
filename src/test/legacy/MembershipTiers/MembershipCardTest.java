//package test.legacy.MembershipTiers;
//
//import main.MembershipTiers.MembershipCard;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//public class MembershipCardTest {
//
//    @BeforeEach
//    void resetExtent() {
//        MembershipCard.setExtent(new ArrayList<>());
//    }
//
//    @Test
//    void testValidCreation() {
//        LocalDate start = LocalDate.now().minusDays(1);
//        LocalDate end = LocalDate.now().plusDays(10);
//        MembershipCard card = new MembershipCard(start, end);
//
//        Assertions.assertEquals(start, card.getDateStart());
//        Assertions.assertEquals(end, card.getDateEnd());
//        Assertions.assertEquals(1, MembershipCard.getExtent().size());
//        Assertions.assertTrue(MembershipCard.getExtent().contains(card));
//    }
//
//    @Test
//    void testStartDateNotInFuture() {
//        LocalDate futureStart = LocalDate.now().plusDays(1);
//        LocalDate end = LocalDate.now().plusDays(10);
//
//        IllegalArgumentException ex = Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> new MembershipCard(futureStart, end)
//        );
//
//        Assertions.assertTrue(ex.getMessage().contains("dateStart"));
//    }
//
//    @Test
//    void testInvalidDateOrder() {
//        LocalDate start = LocalDate.now();
//        LocalDate end = LocalDate.now().minusDays(1);
//
//        IllegalArgumentException ex = Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> new MembershipCard(start, end)
//        );
//
//        Assertions.assertTrue(ex.getMessage().contains("End date cannot be before start date"));
//        //text defined in the validationUtil class
//    }
//
//    @Test
//    void testSetters() {
//        LocalDate start = LocalDate.now().minusDays(5);
//        LocalDate end = LocalDate.now().plusDays(5);
//        MembershipCard card = new MembershipCard(start, end);
//
//        LocalDate newStart = LocalDate.now().minusDays(10);
//        LocalDate newEnd = LocalDate.now().plusDays(15);
//        card.setDateStart(newStart);
//        card.setDateEnd(newEnd);
//
//        Assertions.assertEquals(newStart, card.getDateStart());
//        Assertions.assertEquals(newEnd, card.getDateEnd());
//    }
//
//    @Test
//    void testPrivateAddToExtentRejectsNull() throws Exception {
//        var method = MembershipCard.class.getDeclaredMethod("addToExtent", MembershipCard.class);
//        method.setAccessible(true);
//
//        Exception ex = Assertions.assertThrows(Exception.class, () ->
//                method.invoke(null, new Object[]{null})
//        );
//
//        // unwrap the InvocationTargetException
//        Throwable cause = ex.getCause();
//        Assertions.assertTrue(cause instanceof IllegalArgumentException);
//        Assertions.assertEquals("MembershipCard cannot be null", cause.getMessage());
//    }
//
//}
