package test.Order;

import main.Clothing.Item;
import main.Enums.DeliveryType;
import main.Enums.OrderStatus;
import main.MembershipTiers.Basic;
import main.MembershipTiers.MembershipCard;
import main.MembershipTiers.MembershipTier;
import main.Order.ItemQuantityInOrder;
import main.Order.Order;
import main.Person.Customer;
import main.Person.DebitCard;
import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Item createDummyItem() {
        return new Item("TestItem", "Brand", 100, 10, List.of("Cotton"), List.of("Red")) {};
    }

    private Customer createDummyCustomer() {
        List<String> address = List.of("123 Main St");
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        LocalDate accountCreatedDate = LocalDate.now().minusYears(1);

        DebitCard debitCard = new DebitCard("1234567890123456", LocalDate.now().plusYears(3), "123"); // dummy values
        MembershipCard membershipCard = new MembershipCard(LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(11));
        MembershipTier membershipTier = new Basic(); // use your Basic class

        return new Customer(
                "John",
                address,
                "Doe",
                "john@example.com",
                birthDate,
                "john123",
                accountCreatedDate,
                1000.0,
                debitCard,
                membershipCard,
                membershipTier
        );
    }

    private ItemQuantityInOrder createItemQuantity() {
        return new ItemQuantityInOrder(createDummyItem(), 2);
    }

    @BeforeEach
    void clearExtent() {
        Order.setExtent(new ArrayList<>());
    }

    @Test
    void testValidConstruction() {
        List<ItemQuantityInOrder> items = List.of(createItemQuantity());
        Customer customer = createDummyCustomer();
        LocalDate timestamp = LocalDate.now();

        Order order = new Order(
                items,
                customer,
                200,
                DeliveryType.HOME_DELIVERY,
                3600,
                timestamp,
                200,
                10,
                OrderStatus.SUBMITTED
        );

        assertEquals(items, order.getItems());
        assertEquals(customer, order.getCustomer());
        assertEquals(OrderStatus.SUBMITTED, order.getStatus());
        assertEquals(10, order.getDiscountApplied());
        assertEquals(200, order.getSumPrice());
        assertEquals(timestamp, order.getTimestamp());
        assertEquals(3600, order.getPaymentTimer());
        assertEquals(200 - (1 - 10.0/100), order.getFinalPrice());
        assertEquals(1, Order.getExtent().size());
    }

    @Test
    void testNullItemsThrowsException() {
        Customer customer = createDummyCustomer();
        LocalDate timestamp = LocalDate.now();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Order(null, customer, 100, DeliveryType.HOME_DELIVERY, 0, timestamp, 100, 0, OrderStatus.ACCEPTED)
        );
        assertTrue(ex.getMessage().contains("items"));
    }

    @Test
    void testNullCustomerThrowsException() {
        List<ItemQuantityInOrder> items = List.of(createItemQuantity());
        LocalDate timestamp = LocalDate.now();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Order(items, null, 100, DeliveryType.HOME_DELIVERY, 0, timestamp, 100, 0, OrderStatus.ACCEPTED)
        );
        assertTrue(ex.getMessage().contains("customer"));
    }

    @Test
    void testNegativeFinalPriceThrowsException() {
        List<ItemQuantityInOrder> items = List.of(createItemQuantity());
        Customer customer = createDummyCustomer();
        LocalDate timestamp = LocalDate.now();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Order(items, customer, -5, DeliveryType.HOME_DELIVERY, 0, timestamp, 100, 0, OrderStatus.ACCEPTED)
        );
        assertTrue(ex.getMessage().contains("finalPrice"));
    }

    @Test
    void testNegativeDiscountThrowsException() {
        List<ItemQuantityInOrder> items = List.of(createItemQuantity());
        Customer customer = createDummyCustomer();
        LocalDate timestamp = LocalDate.now();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Order(items, customer, 100, DeliveryType.HOME_DELIVERY, 0, timestamp, 100, -1, OrderStatus.ACCEPTED)
        );
        assertTrue(ex.getMessage().contains("discountApplied"));
    }

    @Test
    void testNegativeSumPriceThrowsException() {
        List<ItemQuantityInOrder> items = List.of(createItemQuantity());
        Customer customer = createDummyCustomer();
        LocalDate timestamp = LocalDate.now();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Order(items, customer, 100, DeliveryType.HOME_DELIVERY, 0, timestamp, -100, 0, OrderStatus.ACCEPTED)
        );
        assertTrue(ex.getMessage().contains("sumPrice"));
    }

    @Test
    void testNullStatusThrowsException() {
        List<ItemQuantityInOrder> items = List.of(createItemQuantity());
        Customer customer = createDummyCustomer();
        LocalDate timestamp = LocalDate.now();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Order(items, customer, 100, DeliveryType.HOME_DELIVERY, 0, timestamp, 100, 0, null)
        );
        assertTrue(ex.getMessage().contains("status"));
    }

    @Test
    void testNullDeliveryTypeThrowsException() {
        List<ItemQuantityInOrder> items = List.of(createItemQuantity());
        Customer customer = createDummyCustomer();
        LocalDate timestamp = LocalDate.now();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Order(items, customer, 100, null, 0, timestamp, 100, 0, OrderStatus.ACCEPTED)
        );
        assertTrue(ex.getMessage().contains("deliveryType"));
    }

    @Test
    void testNegativePaymentTimerThrowsException() {
        List<ItemQuantityInOrder> items = List.of(createItemQuantity());
        Customer customer = createDummyCustomer();
        LocalDate timestamp = LocalDate.now();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Order(items, customer, 100, DeliveryType.HOME_DELIVERY, -1, timestamp, 100, 0, OrderStatus.ACCEPTED)
        );
        assertTrue(ex.getMessage().contains("paymentTimer"));
    }

    @Test
    void testFutureTimestampThrowsException() {
        List<ItemQuantityInOrder> items = List.of(createItemQuantity());
        Customer customer = createDummyCustomer();
        LocalDate timestamp = LocalDate.now().plusDays(1);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Order(items, customer, 100, DeliveryType.HOME_DELIVERY, 0, timestamp, 100, 0, OrderStatus.ACCEPTED)
        );
        assertTrue(ex.getMessage().contains("timestamp"));
    }

    @Test
    void testSetters() {
        List<ItemQuantityInOrder> items = List.of(createItemQuantity());
        Customer customer = createDummyCustomer();
        LocalDate timestamp = LocalDate.now();

        Order order = new Order(items, customer, 100, DeliveryType.HOME_DELIVERY, 3600, timestamp, 100, 5, OrderStatus.ACCEPTED);

        order.setStatus(OrderStatus.COMPLETED);
        order.setDiscountApplied(10);
        order.setSumPrice(200);
        order.setFinalPrice(180);
        order.setPaymentTimer(7200);
        order.setDeliveryType(DeliveryType.STORE_PICKUP);
        order.setCustomer(createDummyCustomer());
        order.setItems(List.of(createItemQuantity()));
        order.setTimestamp(LocalDate.now());

        assertEquals(OrderStatus.COMPLETED, order.getStatus());
        assertEquals(10, order.getDiscountApplied());
        assertEquals(200, order.getSumPrice());
        assertEquals(180, order.getFinalPrice());
        assertEquals(7200, order.getPaymentTimer());
        assertEquals(DeliveryType.STORE_PICKUP, order.getDeliveryType());
        assertNotNull(order.getCustomer());
        assertNotNull(order.getItems());
        assertNotNull(order.getTimestamp());
    }

    @Test
    void testPrivateAddToExtentRejectsNull() throws Exception {
        var method = Order.class.getDeclaredMethod("addToExtent", Order.class);
        method.setAccessible(true);

        InvocationTargetException ex = assertThrows(InvocationTargetException.class,
                () -> method.invoke(null, (Object) null));

        assertTrue(ex.getCause() instanceof IllegalArgumentException);
        assertTrue(ex.getCause().getMessage().contains("Order cannot be null"));
    }

    @Test
    void testExtentManagement() {
        List<ItemQuantityInOrder> items = List.of(createItemQuantity());
        Customer customer = createDummyCustomer();
        LocalDate timestamp = LocalDate.now();

        Order order1 = new Order(items, customer, 100, DeliveryType.HOME_DELIVERY, 3600, timestamp, 100, 5, OrderStatus.ACCEPTED);
        Order order2 = new Order(items, customer, 200, DeliveryType.STORE_PICKUP, 7200, timestamp, 200, 10, OrderStatus.COMPLETED);

        assertEquals(2, Order.getExtent().size());

        List<Order> loaded = new ArrayList<>();
        Order.setExtent(loaded);
        assertEquals(0, Order.getExtent().size());
    }
}
