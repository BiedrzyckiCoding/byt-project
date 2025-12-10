package test.PersistenceModels;

import main.Enums.DeliveryType;
import main.Enums.OrderStatus;
import main.MembershipTiers.Premium;
import main.PersistenceModels.*;
import main.Clothing.Item;
import main.Enums.ClothingSize;
import main.Order.ItemQuantityInOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @BeforeEach
    void setUp() {
        PersistenceUtil.loadAll();
    }

    private Hoodie createHoodie(String name, double price) {
        return new Hoodie(name, "Brand", price, 5, List.of("Cotton"), List.of("Black"), ClothingSize.M, true);
    }

    private ItemQuantityInOrder createItemQuantity(double price, int quantity, Order order) {
        return new ItemQuantityInOrder(createHoodie("TestHoodie", price), quantity, order);
    }

    private Customer createCustomer() {
        DebitCard card = new DebitCard("1111-2222-3333-4444", LocalDate.now().plusYears(1), "123");
        return new Customer(
                "John", List.of("Address"), "Doe", "mail@mail.com", LocalDate.of(1990, 1, 1),
                "user1", LocalDate.now(), 0, card
        );
    }

    private Order createOrder(DeliveryType deliveryType) {
        return new Order(deliveryType, LocalDateTime.now(), OrderStatus.ACCEPTED);
    }

    @Test
    void constructor_ShouldSetDeliveryType() {
        Order o = new Order(DeliveryType.HOME_DELIVERY, LocalDateTime.now(), OrderStatus.ACCEPTED);
        assertEquals(DeliveryType.HOME_DELIVERY, o.getDeliveryType());
    }

    @Test
    void constructor_ShouldSetTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        Order o = new Order(DeliveryType.STORE_PICKUP, now, OrderStatus.ACCEPTED);
        assertEquals(now, o.getTimestamp());
    }

    @Test
    void constructor_ShouldSetStatus() {
        Order o = new Order(DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.PROCESSING);
        assertEquals(OrderStatus.PROCESSING, o.getStatus());
    }

    @Test
    void constructor_ShouldAddToExtent() {
        Order o = createOrder(DeliveryType.STORE_PICKUP);
        assertTrue(Order.getExtent().contains(o));
    }

    @Test
    void constructor_ShouldRejectNullDeliveryType() {
        assertThrows(IllegalArgumentException.class, () ->
                new Order(null, LocalDateTime.now(), OrderStatus.ACCEPTED));
    }

    @Test
    void constructor_ShouldRejectNullStatus() {
        assertThrows(IllegalArgumentException.class, () ->
                new Order(DeliveryType.STORE_PICKUP, LocalDateTime.now(), null));
    }

    @Test
    void constructor_ShouldRejectFutureTimestamp() {
        assertThrows(IllegalArgumentException.class, () ->
                new Order(DeliveryType.STORE_PICKUP, LocalDateTime.now().plusDays(1), OrderStatus.ACCEPTED));
    }

    @Test
    void newItemQuantity_ShouldAutomaticallyAddToOrderList() {
        Order o = createOrder(DeliveryType.STORE_PICKUP);

        ItemQuantityInOrder item = createItemQuantity(100, 1, o);

        assertTrue(o.getItemListAssociation().contains(item));
    }

    @Test
    void addItemToList_ShouldRejectDuplicateItemObject() {
        Order o = createOrder(DeliveryType.STORE_PICKUP);

        ItemQuantityInOrder item = createItemQuantity(100, 1, o);

        assertThrows(IllegalArgumentException.class, () -> o.addItemToList(item));
    }

    @Test
    void removeItemFromList_ShouldRemoveItem() {
        Order o = createOrder(DeliveryType.STORE_PICKUP);
        ItemQuantityInOrder item = createItemQuantity(100, 1, o);

        o.removeItemFromList(item);

        assertFalse(o.getItemListAssociation().contains(item));
    }

    @Test
    void getSumPrice_ShouldCalculateTotalCorrectly() {
        Order o = createOrder(DeliveryType.STORE_PICKUP);

        createItemQuantity(100, 2, o); // 200
        createItemQuantity(50, 1, o);  // 50

        assertEquals(250.0, o.getSumPrice());
    }

    @Test
    void getFinalPrice_ShouldAddDeliveryFee_WhenHomeDelivery() {
        Order o = createOrder(DeliveryType.HOME_DELIVERY); // +5 fee

        createItemQuantity(100, 1, o);

        assertEquals(105.0, o.getFinalPrice());
    }

    @Test
    void getFinalPrice_ShouldNotAddFee_WhenSTORE_PICKUP() {
        Order o = createOrder(DeliveryType.STORE_PICKUP);

        createItemQuantity(100, 1, o);

        assertEquals(100.0, o.getFinalPrice());
    }


    @Test
    void isDiscountApplied_ShouldSetFalse_WhenNoMembershipEndDatesSet() {
        Customer c = createCustomer();
        Order o = createOrder(DeliveryType.STORE_PICKUP);
        c.addOrder(o);

        c.purchaseMembership(LocalDate.now().plusYears(1), new Premium());

        o.isDiscountApplied();

        createItemQuantity(100, 1, o);

        assertTrue(o.getFinalPrice() < 100.0);
    }

    @Test
    void isDiscountApplied_ShouldThrowException_IfCustomerIsNull() {
        Order o = createOrder(DeliveryType.STORE_PICKUP);
        assertThrows(IllegalArgumentException.class, o::isDiscountApplied);
    }
}