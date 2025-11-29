//package test.legacy.Person;
//
//import main.MembershipTiers.Basic;
//import main.MembershipTiers.MembershipCard;
//import main.MembershipTiers.MembershipTier;
//import main.Person.Customer;
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
//class CustomerTest {
//
//    private DebitCard debitCard;
//    private MembershipCard membershipCard;
//    private MembershipTier membershipTier;
//
//    @BeforeEach
//    void setup() {
//        Customer.setExtent(List.of()); //reset extent before each test
//
//        debitCard = new DebitCard("1234567890123456", LocalDate.now().plusYears(3), "123");
//        membershipCard = new MembershipCard(LocalDate.now().minusDays(30), LocalDate.now().plusDays(30));
//        membershipTier = new Basic();
//    }
//
//    @Test
//    void testConstructorValid() {
//        Customer customer = new Customer(
//                "John",
//                List.of("123 Main St"),
//                "Doe",
//                "john@example.com",
//                LocalDate.now().minusYears(30),
//                "john_account",
//                LocalDate.now().minusYears(1), //year ago
//                100.0,
//                debitCard,
//                membershipCard,
//                membershipTier
//        );
//
//        assertEquals("John", customer.getName());
//        assertEquals("Doe", customer.getSurname());
//        assertEquals("john_account", customer.getAccountName());
//        assertEquals(100.0, customer.getTotalSpent());
//        assertEquals(debitCard, customer.getDebitCard());
//        assertEquals(membershipCard, customer.getMembershipCard());
//        assertEquals(membershipTier, customer.getMembershipTier());
//        assertTrue(Customer.getExtent().contains(customer));
//    }
//    @Test
//    void testConstructorRejectsNullAccountName() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new Customer("John", List.of("123 Main St"), "Doe", "john@example.com",
//                        LocalDate.now().minusYears(30), null,
//                        LocalDate.now().minusYears(1), 100.0, debitCard, membershipCard, membershipTier));
//
//        //don't know the exact message, this works fine
//        assertTrue(ex.getMessage().contains("accountName"));
//    }
//
//    @Test
//    void testConstructorRejectsFutureAccountCreatedDate() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new Customer("John", List.of("123 Main St"), "Doe", "john@example.com",
//                        LocalDate.now().minusYears(30), "john_account",
//                        LocalDate.now().plusDays(1), 100.0, debitCard, membershipCard, membershipTier));
//        assertTrue(ex.getMessage().contains("accountCreatedDate"));
//    }
//
//    @Test
//    void testConstructorRejectsNegativeTotalSpent() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new Customer("John", List.of("123 Main St"), "Doe", "john@example.com",
//                        LocalDate.now().minusYears(30), "john_account",
//                        LocalDate.now().minusYears(1), -50.0, debitCard, membershipCard, membershipTier));
//        assertTrue(ex.getMessage().contains("totalSpent"));
//    }
//    @Test
//    void testConstructorRejectsNullDebitCard() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new Customer("John", List.of("123 Main St"), "Doe", "john@example.com",
//                        LocalDate.now().minusYears(30), "john_account",
//                        LocalDate.now().minusYears(1), 100.0, null, membershipCard, membershipTier));
//        assertTrue(ex.getMessage().contains("debitCard"));
//    }
//
//    @Test
//    void testConstructorRejectsNullMembershipCard() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new Customer("John", List.of("123 Main St"), "Doe", "john@example.com",
//                        LocalDate.now().minusYears(30), "john_account",
//                        LocalDate.now().minusYears(1), 100.0, debitCard, null, membershipTier));
//        assertTrue(ex.getMessage().contains("membershipCard"));
//    }
//
//    @Test
//    void testConstructorRejectsNullMembershipTier() {
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> new Customer("John", List.of("123 Main St"), "Doe", "john@example.com",
//                        LocalDate.now().minusYears(30), "john_account",
//                        LocalDate.now().minusYears(1), 100.0, debitCard, membershipCard, null));
//        assertTrue(ex.getMessage().contains("membershipTier"));
//    }
//
//    @Test
//    void testSettersAndGetters() {
//        Customer customer = new Customer(
//                "John",
//                List.of("123 Main St"),
//                "Doe",
//                "john@example.com",
//                LocalDate.now().minusYears(30),
//                "john_account",
//                LocalDate.now().minusYears(1),
//                100.0,
//                debitCard,
//                membershipCard,
//                membershipTier
//        );
//
//        customer.setAccountName("new_account");
//        customer.setTotalSpent(200.0);
//        customer.setDebitCard(new DebitCard("9876543210987654", LocalDate.now().plusYears(5), "999"));
//        MembershipCard newCard = new MembershipCard(LocalDate.now().minusDays(10), LocalDate.now().plusDays(20));
//        customer.setMembershipCard(newCard);
//        MembershipTier newTier = new Basic();
//        customer.setMembershipTier(newTier);
//
//        assertEquals("new_account", customer.getAccountName());
//        assertEquals(200.0, customer.getTotalSpent());
//        assertEquals(newCard, customer.getMembershipCard());
//        assertEquals(newTier, customer.getMembershipTier());
//    }
//
//    @Test
//    void testPrivateAddToExtentRejectsNull() throws Exception {
//        Method method = Customer.class.getDeclaredMethod("addToExtent", Customer.class);
//        method.setAccessible(true);
//
//        InvocationTargetException ex = assertThrows(InvocationTargetException.class,
//                () -> method.invoke(null, (Object) null));
//
//        assertTrue(ex.getCause() instanceof IllegalArgumentException);
//        assertTrue(ex.getCause().getMessage().contains("Customer cannot be null"));
//    }
//
//    @Test
//    void testExtentOperations() {
//        Customer customer1 = new Customer(
//                "John",
//                List.of("123 Main St"),
//                "Doe",
//                "john@example.com",
//                LocalDate.now().minusYears(30),
//                "john_account",
//                LocalDate.now().minusYears(1),
//                100.0,
//                debitCard,
//                membershipCard,
//                membershipTier
//        );
//
//        Customer customer2 = new Customer(
//                "Jane",
//                List.of("456 Main St"),
//                "Smith",
//                "jane@example.com",
//                LocalDate.now().minusYears(25),
//                "jane_account",
//                LocalDate.now().minusYears(2),
//                150.0,
//                debitCard,
//                membershipCard,
//                membershipTier
//        );
//
//        List<Customer> extent = Customer.getExtent();
//        assertEquals(2, extent.size());
//        assertTrue(extent.contains(customer1));
//        assertTrue(extent.contains(customer2));
//
//        Customer.setExtent(List.of());
//        assertTrue(Customer.getExtent().isEmpty());
//    }
//}