package test.PersistenceModels;

import main.Enums.DeliveryType;
import main.Enums.OrderStatus;
import main.MembershipTiers.Basic;
import main.MembershipTiers.Premium;
import main.PersistenceModels.Customer;
import main.PersistenceModels.DebitCard;
import main.PersistenceModels.MembershipCard;
import main.PersistenceModels.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private List<String> address() {
        return List.of("Street 1", "City", "00000");
    }

    private LocalDate birthDate() {
        return LocalDate.of(1990, 1, 1);
    }

    private DebitCard sampleDebitCard() {
        return new DebitCard("1234", LocalDate.now().plusYears(2), "999");
    }

    private Customer createStandardCustomer() {
        return new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                "johnAccount", LocalDate.now(), 100, sampleDebitCard());
    }
    private Customer createCustomer() {
        Customer c = new Customer("John", List.of("Address"), "Doe", "mail", LocalDate.of(1990, 1, 1),
                "johnAccount", LocalDate.now(), 100, new DebitCard("123", LocalDate.now(), "999"));

        // Needed because creating an Order requires the Customer to have a membership
        c.purchaseMembership(LocalDate.now().plusDays(1), new Basic());
        return c;
    }


    @Test
    void constructor_ShouldSetAccountName() {
        String expectedAccountName = "johnAccount";

        Customer c = new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                expectedAccountName, LocalDate.now(), 100, sampleDebitCard());

        assertEquals(expectedAccountName, c.getAccountName());
    }

    @Test
    void constructor_ShouldSetTotalSpent() {
        double expectedTotalSpent = 100.0;

        Customer c = new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                "acc", LocalDate.now(), expectedTotalSpent, sampleDebitCard());

        assertEquals(expectedTotalSpent, c.getTotalSpent());
    }

    @Test
    void constructor_ShouldSetDebitCard() {
        DebitCard expectedCard = sampleDebitCard();

        Customer c = new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                "acc", LocalDate.now(), 100, expectedCard);

        assertEquals(expectedCard, c.getDebitCard());
    }

    @Test
    void constructor_ShouldInitializeEmptyMembershipBag() {
        Customer c = createStandardCustomer();

        List<MembershipCard> cards = c.getMembershipTiers();

        assertTrue(cards.isEmpty());
    }

    @Test
    void constructor_ShouldRejectNullDebitCard() {
        DebitCard nullCard = null;

        assertThrows(IllegalArgumentException.class, () ->
                new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                        "acc", LocalDate.now(), 10, nullCard)
        );
    }

    @Test
    void constructor_ShouldRejectNegativeTotalSpent() {
        double invalidSpent = -50.0;

        assertThrows(IllegalArgumentException.class, () ->
                new Customer("John", address(), "Doe", "mail@mail.com", birthDate(),
                        "acc", LocalDate.now(), invalidSpent, sampleDebitCard())
        );
    }

    //bag tests:
    @Test
    void purchaseMembership_ShouldIncreaseBagSize() {
        Customer c = createStandardCustomer();

        c.purchaseMembership(LocalDate.now().plusDays(1), new Basic());

        assertEquals(1, c.getMembershipTiers().size());
    }

    @Test
    void purchaseMembership_ShouldSetCorrectActiveTier() {
        Customer c = createStandardCustomer();

        c.purchaseMembership(LocalDate.now().plusDays(1),new Premium());

        assertTrue(c.getMembershipTier() instanceof Premium);
    }

    @Test
    void purchaseMembership_ShouldStackHistoryInBag() {
        Customer c = createStandardCustomer();
        c.purchaseMembership(LocalDate.now().plusDays(1),new Basic()); //first purchase

        c.purchaseMembership(LocalDate.now().plusDays(1),new Premium()); //second purchase

        assertEquals(2, c.getMembershipTiers().size());
    }

    @Test
    void getMembershipCard_ShouldReturnMostRecentFromBag() {
        Customer c = createStandardCustomer();
        c.purchaseMembership(LocalDate.now().plusDays(1),new Basic()); //old
        c.purchaseMembership(LocalDate.now().plusDays(1),new Premium()); //new (active)

        MembershipCard currentCard = c.getMembershipCard();

        assertTrue(currentCard.getMembershipTier() instanceof Premium);
    }

    @Test
    void getMembershipTiers_ShouldPreserveInsertionOrder() {
        Customer c = createStandardCustomer();
        c.purchaseMembership(LocalDate.now().plusDays(1),new Basic());
        c.purchaseMembership(LocalDate.now().plusDays(1),new Premium());

        MembershipCard firstCardInBag = c.getMembershipTiers().get(0);

        assertTrue(firstCardInBag.getMembershipTier() instanceof Basic);
    }

    @Test
    void addMembershipTierToCustomer_ShouldRejectDuplicateCardInstance() {
        Customer c = createStandardCustomer();
        c.purchaseMembership(LocalDate.now().plusDays(1),new Basic());
        MembershipCard existingCard = c.getMembershipCard();

        assertThrows(IllegalArgumentException.class, () ->
                c.addMembershipTierToCustomer(existingCard)
        );
    }

    @Test
    void getMembershipCard_ShouldThrowIfBagIsEmpty() {
        Customer c = createStandardCustomer(); //created with no membership

        assertThrows(NoSuchElementException.class, c::getMembershipCard);
    }

    @Test
    void changeMembershipTier_ShouldModifyActiveTierType() {
        Customer c = createStandardCustomer();
        c.purchaseMembership(LocalDate.now().plusDays(1),new Basic());

        c.changeMembershipTier(new Premium());

        assertTrue(c.getMembershipTier() instanceof Premium);
    }

    @Test
    void changeMembershipTier_ShouldNotIncreaseBagSize() {
        Customer c = createStandardCustomer();
        c.purchaseMembership(LocalDate.now().plusDays(1),new Basic());
        int initialSize = c.getMembershipTiers().size();

        c.changeMembershipTier(new Premium());

        assertEquals(initialSize, c.getMembershipTiers().size());
    }
    @Test
    void addOrder_ShouldStoreOrderByTimestamp() {
        Customer c = createCustomer();
        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        Order order = new Order(c, DeliveryType.STORE_PICKUP, time, OrderStatus.ACCEPTED);

        c.addOrder(order);

        assertEquals(order, c.getOrderByTimestamp(time));
    }

    @Test
    void addOrder_ShouldSetCustomerOnOrder() {
        Customer c = createCustomer();
        Order order = new Order(c, DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.ACCEPTED);

        Customer c2 = createCustomer();

        c2.addOrder(order);

        assertEquals(c2, order.getCustomer());
    }

    @Test
    void addOrder_ShouldRejectDuplicateTimestamp() {
        Customer c = createCustomer();
        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        Order order1 = new Order(c, DeliveryType.STORE_PICKUP, time, OrderStatus.ACCEPTED);
        Order order2 = new Order(c, DeliveryType.HOME_DELIVERY, time, OrderStatus.PROCESSING);

        c.addOrder(order1);

        //trying to add a second order with the exact same timestamp key
        assertThrows(IllegalArgumentException.class, () -> c.addOrder(order2));
    }

    @Test
    void removeOrder_ShouldRemoveFromMap() {
        Customer c = createCustomer();
        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        Order order = new Order(c, DeliveryType.STORE_PICKUP, time, OrderStatus.ACCEPTED);
        c.addOrder(order);

        c.removeOrder(order);

        assertNull(c.getOrderByTimestamp(time));
    }

    @Test
    void removeOrder_ShouldUnlinkCustomerFromOrder() {
        Customer c = createCustomer();
        Order order = new Order(c, DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.ACCEPTED);
        c.addOrder(order);

        c.removeOrder(order);

        assertNull(order.getCustomer());
    }

    @Test
    void getOrderByTimestamp_ShouldReturnNullForUnknownKey() {
        Customer c = createCustomer();

        LocalDateTime time = LocalDateTime.now();

        Order result = c.getOrderByTimestamp(time);

        assertNull(result);
    }
}