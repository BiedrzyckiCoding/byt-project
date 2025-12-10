package test.PersistenceModels;

import main.Enums.DeliveryType;
import main.Enums.OrderStatus;
import main.MembershipTiers.Basic;
import main.MembershipTiers.Premium;
import main.PersistenceModels.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @BeforeEach
    void setUp() {
        PersistenceUtil.loadAll();
    }

    private DebitCard createValidDebitCard() {
        return new DebitCard("1234-5678-9012-3456", LocalDate.now().plusYears(2), "123");
    }

    private List<String> createAddress() {
        return List.of("123 Main St", "Metropolis", "10001");
    }

    private LocalDate createBirthDate() {
        return LocalDate.of(1990, 5, 20);
    }

    private Order createOrder(LocalDateTime timestamp) {
        return new Order(DeliveryType.HOME_DELIVERY, timestamp, OrderStatus.ACCEPTED);
    }

    private Customer createCustomerWithDebitCard(DebitCard card) {
        return new Customer(
                "John", createAddress(), "Doe", "john@example.com", createBirthDate(),
                "johnAccount", LocalDate.now(), 100.0, card
        );
    }

    private Customer createStandardCustomer() {
        return createCustomerWithDebitCard(createValidDebitCard());
    }

    @Test
    void constructor_ShouldSetAccountName() {
        Customer c = createStandardCustomer();
        assertEquals("johnAccount", c.getAccountName());
    }

    @Test
    void constructor_ShouldSetDebitCard() {
        DebitCard card = createValidDebitCard();
        Customer c = createCustomerWithDebitCard(card);

        assertEquals(card, c.getDebitCard());
    }

    @Test
    void constructor_ShouldSetTotalSpent() {
        Customer c = createStandardCustomer();
        assertEquals(100.0, c.getTotalSpent());
    }

    @Test
    void constructor_ShouldAddToExtent() {
        Customer c = createStandardCustomer();
        assertTrue(Customer.getExtent().contains(c), "Customer should be added to the static extent");
    }

    @Test
    void constructor_ShouldRejectNullDebitCard() {
        assertThrows(IllegalArgumentException.class, () ->
                new Customer(
                        "John", createAddress(), "Doe", "john@example.com", createBirthDate(),
                        "johnAccount", LocalDate.now(), 100.0, null
                )
        );
    }

    @Test
    void constructor_ShouldRejectFutureAccountDate() {
        DebitCard card = createValidDebitCard();
        assertThrows(IllegalArgumentException.class, () ->
                new Customer(
                        "John", createAddress(), "Doe", "john@example.com", createBirthDate(),
                        "johnAccount", LocalDate.now().plusDays(1), 100.0, card
                )
        );
    }

    @Test
    void purchaseMembership_ShouldSetMembershipTier() {
        Customer c = createStandardCustomer();
        Premium premiumTier = new Premium();

        LocalDate end = LocalDate.now().plusYears(1);

        c.purchaseMembership(end, premiumTier);

        assertEquals(premiumTier, c.getMembershipTier());
    }

    //bag tests:
    @Test
    void purchaseMembership_ShouldSetCurrentMembershipCard() {
        Customer c = createStandardCustomer();

        LocalDate end = LocalDate.now().plusYears(1);

        c.purchaseMembership(end, new Basic());

        assertNotNull(c.getMembershipCard());
    }

    @Test
    void purchaseMembership_ShouldAddToHistoryList() {
        Customer c = createStandardCustomer();

        LocalDate end = LocalDate.now().plusYears(1);

        c.purchaseMembership(end, new Basic());

        assertEquals(1, c.getMembershipTiers().size());
    }

    @Test
    void getMembershipCard_ShouldThrowException_WhenNoMembershipExists() {
        Customer c = createStandardCustomer();

        assertThrows(NoSuchElementException.class, c::getMembershipCard);
    }

    @Test
    void changeMembershipTier_ShouldUpdateCurrentTier() {
        Customer c = createStandardCustomer();

        LocalDate end = LocalDate.now().plusYears(1);

        c.purchaseMembership(end, new Basic());

        c.changeMembershipTier(new Premium());

        assertTrue(c.getMembershipTier() instanceof Premium);
    }

    @Test
    void changeMembershipTier_ShouldRejectSameTier() {
        Customer c = createStandardCustomer();

        Basic basicTier = new Basic();

        LocalDate end = LocalDate.now().plusYears(1);

        c.purchaseMembership(end, basicTier);

        assertThrows(IllegalArgumentException.class, () -> c.changeMembershipTier(basicTier));
    }

    @Test
    void addOrder_ShouldStoreOrder() {
        Customer c = createStandardCustomer();
        LocalDateTime now = LocalDateTime.now();
        Order order = createOrder(now);

        c.addOrder(order);

        assertEquals(order, c.getOrderByTimestamp(now));
    }

    @Test
    void addOrder_ShouldRejectDuplicateTimestamp() {
        Customer c = createStandardCustomer();
        LocalDateTime now = LocalDateTime.now();

        Order order1 = createOrder(now);
        c.addOrder(order1);

        Order order2 = createOrder(now); // same timestamp

        assertThrows(IllegalArgumentException.class, () -> c.addOrder(order2));
    }
}