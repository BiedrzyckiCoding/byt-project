package test.PersistenceModels;

import main.Enums.DeliveryType;
import main.Enums.OrderStatus;
import main.MembershipTiers.MembershipTier;
import main.PersistenceModels.Customer;
import main.PersistenceModels.DebitCard;
import main.PersistenceModels.MembershipCard;
import main.PersistenceModels.Order;
import main.PersistenceModels.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Person validPerson;
    private Customer customer;
    private DebitCard validDebitCard;
    private Customer.CustomerData validData;

    @BeforeEach
    void setUp() {
//        Customer.setExtent(Collections.emptyList()); // Assuming Customer might have extent, though not shown in snippet, standard practice here
//        Order.setExtent(Collections.emptyList());
//        MembershipCard.setExtent(Collections.emptyList());

        validDebitCard = new DebitCard("1234567890123456", LocalDate.now().plusYears(1), "123");
        validData = new Customer.CustomerData("john_acc", LocalDate.now(), 0.0, validDebitCard);
        validPerson = new Person("John", List.of("Address"), "Doe", "john@test.com", LocalDate.of(1990, 1, 1), validData);
        customer = validPerson.asCustomer();
    }

    @Test
    void constructor_shouldThrowException_whenAccountNameEmpty() {
        Customer.CustomerData invalidData = new Customer.CustomerData("", LocalDate.now(), 0.0, validDebitCard);
        assertThrows(Exception.class, () ->
                new Person("John", List.of("Addr"), "Doe", "mail", LocalDate.now(), invalidData)
        );
    }

    @Test
    void constructor_shouldThrowException_whenCreatedDateInFuture() {
        Customer.CustomerData invalidData = new Customer.CustomerData("acc", LocalDate.now().plusDays(1), 0.0, validDebitCard);
        assertThrows(Exception.class, () ->
                new Person("John", List.of("Addr"), "Doe", "mail", LocalDate.now(), invalidData)
        );
    }

    @Test
    void constructor_shouldThrowException_whenTotalSpentNegative() {
        Customer.CustomerData invalidData = new Customer.CustomerData("acc", LocalDate.now(), -10.0, validDebitCard);
        assertThrows(Exception.class, () ->
                new Person("John", List.of("Addr"), "Doe", "mail", LocalDate.now(), invalidData)
        );
    }

    @Test
    void constructor_shouldThrowException_whenDebitCardNull() {
        Customer.CustomerData invalidData = new Customer.CustomerData("acc", LocalDate.now(), 0.0, null);
        assertThrows(Exception.class, () ->
                new Person("John", List.of("Addr"), "Doe", "mail", LocalDate.now(), invalidData)
        );
    }

    @Test
    void getOwner_shouldReturnCorrectPerson() {
        assertEquals(validPerson, customer.getOwner());
    }

    @Test
    void setAccountName_shouldUpdateValue_whenValid() {
        customer.setAccountName("NewName");
        assertEquals("NewName", customer.getAccountName());
    }

    @Test
    void setAccountName_shouldThrowException_whenEmpty() {
        assertThrows(Exception.class, () -> customer.setAccountName(""));
    }

    @Test
    void setAccountCreatedDate_shouldUpdateValue_whenValid() {
        LocalDate newDate = LocalDate.now().minusDays(1);
        customer.setAccountCreatedDate(newDate);
        assertEquals(newDate, customer.getAccountCreatedDate());
    }

    @Test
    void setAccountCreatedDate_shouldThrowException_whenFuture() {
        assertThrows(Exception.class, () -> customer.setAccountCreatedDate(LocalDate.now().plusDays(1)));
    }

    @Test
    void setTotalSpent_shouldUpdateValue_whenValid() {
        customer.setTotalSpent(100.0);
        assertEquals(100.0, customer.getTotalSpent());
    }

    @Test
    void setTotalSpent_shouldThrowException_whenNegative() {
        assertThrows(Exception.class, () -> customer.setTotalSpent(-5.0));
    }

    @Test
    void setDebitCard_shouldUpdateValue_whenValid() {
        DebitCard newCard = new DebitCard("9876543210987654", LocalDate.now().plusYears(1), "321");
        customer.setDebitCard(newCard);
        assertEquals(newCard, customer.getDebitCard());
    }

    @Test
    void setDebitCard_shouldThrowException_whenNull() {
        assertThrows(Exception.class, () -> customer.setDebitCard(null));
    }

    @Test
    void addOrder_shouldAddOrderToMap() {
        Order order = new Order(DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.SUBMITTED);
        customer.addOrder(order);
        assertEquals(order, customer.getOrderByTimestamp(order.getTimestamp()));
    }

    @Test
    void addOrder_shouldSetCustomerOnOrder() {
        Order order = new Order(DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.SUBMITTED);
        customer.addOrder(order);
        assertEquals(customer, order.getCustomer());
    }

    @Test
    void addOrder_shouldThrowException_whenTimestampDuplicated() {
        LocalDateTime now = LocalDateTime.now();
        Order order1 = new Order(DeliveryType.STORE_PICKUP, now, OrderStatus.SUBMITTED);
        Order order2 = new Order(DeliveryType.HOME_DELIVERY, now, OrderStatus.SUBMITTED);

        customer.addOrder(order1);
        assertThrows(IllegalArgumentException.class, () -> customer.addOrder(order2));
    }

    @Test
    void removeOrder_shouldRemoveFromMap() {
        Order order = new Order(DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.SUBMITTED);
        customer.addOrder(order);
        customer.removeOrder(order);
        assertNull(customer.getOrderByTimestamp(order.getTimestamp()));
    }

    @Test
    void removeOrder_shouldUnlinkCustomerFromOrder() {
        Order order = new Order(DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.SUBMITTED);
        customer.addOrder(order);
        customer.removeOrder(order);
        assertNull(order.getCustomer());
    }

    @Test
    void addMembershipTierToCustomer_shouldAddCardToList() {
        MembershipTier tier = new MembershipTier(MembershipTier.TierType.BASIC);
        // MembershipCard constructor calls addMembershipTierToCustomer
        MembershipCard card = new MembershipCard(LocalDate.now(), validPerson, tier);

        assertTrue(customer.getMembershipTiers().contains(card));
    }

    @Test
    void addMembershipTierToCustomer_shouldThrowException_whenDuplicateCard() {
        MembershipTier tier = new MembershipTier(MembershipTier.TierType.BASIC);
        MembershipCard card = new MembershipCard(LocalDate.now(), validPerson, tier);

        // Try adding the same card instance again explicitly
        assertThrows(IllegalArgumentException.class, () -> customer.addMembershipTierToCustomer(card));
    }

    @Test
    void getMembershipCard_shouldReturnLastAdded() {
        MembershipTier tier1 = new MembershipTier(MembershipTier.TierType.BASIC);
        MembershipTier tier2 = new MembershipTier(MembershipTier.TierType.PREMIUM);

        new MembershipCard(LocalDate.now().minusDays(1), validPerson, tier1);
        MembershipCard card2 = new MembershipCard(LocalDate.now(), validPerson, tier2);

        assertEquals(card2, customer.getMembershipCard());
    }

    @Test
    void getMembershipTier_shouldReturnTierOfLastCard() {
        MembershipTier tier = new MembershipTier(MembershipTier.TierType.PREMIUM);
        new MembershipCard(LocalDate.now(), validPerson, tier);

        assertEquals(tier, customer.getMembershipTier());
    }

    @Test
    void changeMembershipTier_shouldUpdateCurrentCardTier() {
        MembershipTier basic = new MembershipTier(MembershipTier.TierType.BASIC);
        MembershipTier premium = new MembershipTier(MembershipTier.TierType.PREMIUM);
        MembershipCard card = new MembershipCard(LocalDate.now(), validPerson, basic);

        customer.changeMembershipTier(premium);

        assertEquals(premium, card.getMembershipTier());
    }

    @Test
    void changeMembershipTier_shouldThrowException_whenSameTier() {
        MembershipTier basic = new MembershipTier(MembershipTier.TierType.BASIC);
        new MembershipCard(LocalDate.now(), validPerson, basic);

        assertThrows(IllegalArgumentException.class, () -> customer.changeMembershipTier(basic));
    }

    @Test
    void purchaseMembership_shouldCreateNewCardAndAddToCustomer() {
        MembershipTier tier = new MembershipTier(MembershipTier.TierType.BASIC);
        customer.purchaseMembership(tier);

        assertEquals(1, customer.getMembershipTiers().size());
    }

    @Test
    void purchaseMembership_withDateEnd_shouldCreateCardWithSpecificEndDate() {
        MembershipTier tier = new MembershipTier(MembershipTier.TierType.BASIC);
        LocalDate end = LocalDate.now().plusDays(30);

        customer.purchaseMembership(end, tier);

        assertEquals(end, customer.getMembershipCard().getDateEnd());
    }

    @Test
    void onDetach_shouldClearOrdersMap() {
        Order order = new Order(DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.SUBMITTED);
        customer.addOrder(order);

        validPerson.detachCustomer();

        assertNull(customer.getOrderByTimestamp(order.getTimestamp()));
    }

    @Test
    void onDetach_shouldUnlinkOrders() {
        Order order = new Order(DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.SUBMITTED);
        customer.addOrder(order);

        validPerson.detachCustomer();

        assertNull(order.getCustomer());
    }

    @Test
    void onDetach_shouldClearMembershipCards() {
        MembershipTier tier = new MembershipTier(MembershipTier.TierType.BASIC);
        new MembershipCard(LocalDate.now(), validPerson, tier);

        validPerson.detachCustomer();

        assertTrue(customer.getMembershipTiers().isEmpty());
    }
}