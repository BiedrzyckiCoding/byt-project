//package test.legacy.Person;
//
//import main.Person.DebitCard;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class DebitCardTest {
//
//    @BeforeEach
//    void setup() {
//        DebitCard.setExtent(List.of()); //reset extent before each test
//    }
//
//    @Test
//    void testConstructorValid() {
//        DebitCard card = new DebitCard("1234567890123456", LocalDate.now().plusYears(3), "123");
//        assertEquals("1234567890123456", card.getCardNumber());
//        assertEquals(LocalDate.now().plusYears(3), card.getExpirationDate());
//        assertEquals("123", card.getSecurityCode());
//        assertTrue(DebitCard.getExtent().contains(card));
//    }
//
//    @Test
//    void testConstructorRejectsNullCardNumber() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new DebitCard(null, LocalDate.now().plusYears(3), "123"));
//        assertTrue(ex.getMessage().contains("cardNumber"));
//    }
//
//    @Test
//    void testConstructorRejectsNullExpirationDate() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new DebitCard("1234567890123456", null, "123"));
//        assertTrue(ex.getMessage().contains("expirationDate"));
//    }
//
//    @Test
//    void testConstructorRejectsNullSecurityCode() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new DebitCard("1234567890123456", LocalDate.now().plusYears(3), null));
//        assertTrue(ex.getMessage().contains("securityCode"));
//    }
//
//    @Test
//    void testGettersAndSetters() {
//        DebitCard card = new DebitCard("1234567890123456", LocalDate.now().plusYears(3), "123");
//
//        card.setCardNumber("6543210987654321");
//        card.setExpirationDate(LocalDate.now().plusYears(5));
//        card.setSecurityCode("999");
//
//        assertEquals("6543210987654321", card.getCardNumber());
//        assertEquals(LocalDate.now().plusYears(5), card.getExpirationDate());
//        assertEquals("999", card.getSecurityCode());
//    }
//
//    @Test
//    void testPrivateAddToExtentRejectsNull() throws Exception {
//        Method method = DebitCard.class.getDeclaredMethod("addToExtent", DebitCard.class);
//        method.setAccessible(true);
//
//        InvocationTargetException ex = assertThrows(InvocationTargetException.class,
//                () -> method.invoke(null, (Object) null));
//
//        assertTrue(ex.getCause() instanceof IllegalArgumentException);
//        assertTrue(ex.getCause().getMessage().contains("DebitCard cannot be null"));
//    }
//
//    @Test
//    void testExtentManipulation() {
//        DebitCard card1 = new DebitCard("1234567890123456", LocalDate.now().plusYears(3), "123");
//        DebitCard card2 = new DebitCard("6543210987654321", LocalDate.now().plusYears(4), "456");
//
//        List<DebitCard> extent = DebitCard.getExtent();
//        assertEquals(2, extent.size());
//        assertTrue(extent.contains(card1));
//        assertTrue(extent.contains(card2));
//
//        DebitCard.setExtent(List.of());
//        assertTrue(DebitCard.getExtent().isEmpty());
//    }
//}
