package test.PersistenceModels;

import main.Clothing.Item;
import main.Enums.ClothingSize;
import main.Enums.DeliveryType;
import main.Enums.Fit;
import main.Enums.OrderStatus;
import main.Enums.SleeveLength;
import main.MembershipTiers.MembershipTier;
import main.PersistenceModels.ClothingItem;
import main.PersistenceModels.Customer;
import main.PersistenceModels.DebitCard;
import main.PersistenceModels.MembershipCard;
import main.PersistenceModels.Order;
import main.PersistenceModels.Person;
import main.Order.ItemQuantityInOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private LocalDateTime validTimestamp;
    private OrderStatus validStatus;
    private DeliveryType validDelivery;
    private Customer validCustomer;
    private ClothingItem realItem1;
    private ClothingItem realItem2;
    private ItemQuantityInOrder association1;
    private ItemQuantityInOrder association2;

    @BeforeEach
    void setUp() {
//        Order.setExtent(Collections.emptyList());
        validTimestamp = LocalDateTime.now().minusMinutes(1);
        validStatus = OrderStatus.SUBMITTED;
        validDelivery = DeliveryType.STORE_PICKUP;

        DebitCard debitCard = new DebitCard("1234567890123456", LocalDate.now().plusYears(1), "123");
        Customer.CustomerData data = new Customer.CustomerData("acc", LocalDate.now(), 0, debitCard);
        Person person = new Person("John", List.of("Addr"), "Doe", "mail", LocalDate.now(), data);
        validCustomer = person.asCustomer();

        ClothingItem.Shirt shirtVariant = new ClothingItem.Shirt(SleeveLength.LONG, Fit.REGULAR);
        realItem1 = new ClothingItem("Shirt", "Nike", 100.0, 10, List.of("Cotton"), List.of("Red"), ClothingSize.M, shirtVariant, LocalDate.now(), "Season");

        ClothingItem.Shirt shirtVariant2 = new ClothingItem.Shirt(SleeveLength.SHORT, Fit.SLIM);
        realItem2 = new ClothingItem("Tee", "Adidas", 50.0, 10, List.of("Poly"), List.of("Blue"), ClothingSize.L, shirtVariant2, LocalDate.now(), "Season");

//        association1 = new ItemQuantityInOrder(realItem1, 2);
//        association2 = new ItemQuantityInOrder(realItem2, 1);
    }

    @AfterEach
    void tearDown() {
//        Order.setExtent(Collections.emptyList());
    }

    @Test
    void constructor_shouldCreateOrder_whenValid() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        assertNotNull(order);
    }

    @Test
    void constructor_shouldAddToExtent_whenValid() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        assertTrue(Order.getExtent().contains(order));
    }

    @Test
    void constructor_shouldThrowException_whenStatusIsNull() {
        assertThrows(Exception.class, () -> new Order(validDelivery, validTimestamp, null));
    }

    @Test
    void constructor_shouldThrowException_whenDeliveryTypeIsNull() {
        assertThrows(Exception.class, () -> new Order(null, validTimestamp, validStatus));
    }

    @Test
    void constructor_shouldThrowException_whenTimestampInFuture() {
        assertThrows(Exception.class, () -> new Order(validDelivery, LocalDateTime.now().plusDays(1), validStatus));
    }

    @Test
    void setStatus_shouldUpdateValue_whenValid() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        order.setStatus(OrderStatus.COMPLETED);
        assertEquals(OrderStatus.COMPLETED, order.getStatus());
    }

    @Test
    void setStatus_shouldThrowException_whenNull() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        assertThrows(Exception.class, () -> order.setStatus(null));
    }

    @Test
    void setTimestamp_shouldUpdateValue_whenValid() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        LocalDateTime newTime = LocalDateTime.now().minusHours(1);
        order.setTimestamp(newTime);
        assertEquals(newTime, order.getTimestamp());
    }

    @Test
    void setTimestamp_shouldThrowException_whenFuture() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        assertThrows(Exception.class, () -> order.setTimestamp(LocalDateTime.now().plusDays(1)));
    }

    @Test
    void setDeliveryType_shouldUpdateValue_whenValid() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        order.setDeliveryType(DeliveryType.HOME_DELIVERY);
        assertEquals(DeliveryType.HOME_DELIVERY, order.getDeliveryType());
    }

    @Test
    void setDeliveryType_shouldThrowException_whenNull() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        assertThrows(Exception.class, () -> order.setDeliveryType(null));
    }

    @Test
    void addCustomer_shouldSetCustomerField() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        validCustomer.addOrder(order);
        assertEquals(validCustomer, order.getCustomer());
    }

    @Test
    void removeCustomer_shouldNullifyCustomerField() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        validCustomer.addOrder(order);
        validCustomer.removeOrder(order);
        assertNull(order.getCustomer());
    }

    @Test
    void addItemToList_shouldAddItem() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        order.addItemToList(association1);
        assertTrue(order.getItemListAssociation().contains(association1));
    }

    @Test
    void addItemToList_shouldThrowException_whenDuplicate() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        order.addItemToList(association1);
        assertThrows(IllegalArgumentException.class, () -> order.addItemToList(association1));
    }

    @Test
    void removeItemFromList_shouldRemoveItem() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        order.addItemToList(association1);
        order.removeItemFromList(association1);
        assertFalse(order.getItemListAssociation().contains(association1));
    }

    @Test
    void getSumPrice_shouldCalculateCorrectly() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        order.addItemToList(association1);
        order.addItemToList(association2);
        assertEquals(250.0, order.getSumPrice());
    }

    @Test
    void getSumPrice_shouldReturnZero_whenEmpty() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        assertEquals(0.0, order.getSumPrice());
    }

    @Test
    void isDiscountApplied_shouldThrowException_whenCustomerNull() {
        Order order = new Order(validDelivery, validTimestamp, validStatus);
        assertThrows(Exception.class, () -> order.isDiscountApplied());
    }

    @Test
    void getFinalPrice_shouldIncludeHomeDeliveryFee() {
        Order order = new Order(DeliveryType.HOME_DELIVERY, validTimestamp, validStatus);
        order.addItemToList(association2);
        assertEquals(55.0, order.getFinalPrice());
    }

    @Test
    void getFinalPrice_shouldNotApplyDiscount_whenNotActive() {
        Order order = new Order(DeliveryType.STORE_PICKUP, validTimestamp, validStatus);
        validCustomer.addOrder(order);
        order.addItemToList(association1);

        order.isDiscountApplied();
        assertEquals(200.0, order.getFinalPrice());
    }

    @Test
    void getFinalPrice_shouldApplyDiscount_whenActive() {
        Order order = new Order(DeliveryType.STORE_PICKUP, validTimestamp, validStatus);

        MembershipTier tier = new MembershipTier(MembershipTier.TierType.PREMIUM);
        new MembershipCard(LocalDate.now(), LocalDate.now().plusDays(10), validCustomer.getOwner(), tier);

        validCustomer.addOrder(order);
        order.addItemToList(association1);
        order.isDiscountApplied();

        assertEquals(160.0, order.getFinalPrice());
    }
}