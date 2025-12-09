package test.PersistenceModels;

import main.Clothing.Item;
import main.Enums.ClothingSize;
import main.Enums.DeliveryType;
import main.Enums.OrderStatus;
import main.MembershipTiers.Basic;
import main.MembershipTiers.Premium;
import main.Order.ItemQuantityInOrder;
import main.PersistenceModels.Customer;
import main.PersistenceModels.DebitCard;
import main.PersistenceModels.Hoodie; // Assuming Hoodie exists from previous context
import main.PersistenceModels.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Customer createCustomerWithMembership() {
        Customer c = new Customer("John", List.of("Addr"), "Doe", "mail", LocalDate.of(1990, 1, 1),
                "acc", LocalDate.now(), 0, new DebitCard("123", LocalDate.now(), "999"));

        c.purchaseMembership(LocalDate.now().plusDays(1), new Basic());
        return c;
    }


    private Customer createPremiumCustomer() {
        Customer c = new Customer("Jane", List.of("Addr"), "Doe", "mail", LocalDate.of(1990, 1, 1),
                "acc", LocalDate.now(), 0, new DebitCard("123", LocalDate.now(), "999"));
        c.purchaseMembership(LocalDate.now().plusDays(10), new Premium()); // Active premium
        return c;
    }

    private Item createItem(String name, double price) {
        return new Hoodie(name, "Brand", price, 5, List.of("Cotton"), List.of("Black"), ClothingSize.M, true);
    }


    @Test
    void itemQuantityConstructor_ShouldAddSelfToOrderList() {
        Customer c = createCustomerWithMembership();
        Order order = new Order(c, DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.ACCEPTED);
        Item item = createItem("Shirt", 50);

        new ItemQuantityInOrder(item, 1, order);

        assertEquals(1, order.getItems().size());
    }

    @Test
    void getItems_ShouldReturnCorrectBagContents() {
        Customer c = createCustomerWithMembership();
        Order order = new Order(c, DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.ACCEPTED);
        Item item = createItem("Shirt", 50);
        ItemQuantityInOrder bagEntry = new ItemQuantityInOrder(item, 1, order);

        List<ItemQuantityInOrder> items = order.getItems();

        assertTrue(items.contains(bagEntry));
    }

    @Test
    void addItem_ShouldRejectDuplicateBagEntry() {
        Customer c = createCustomerWithMembership();
        Order order = new Order(c, DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.ACCEPTED);
        Item item = createItem("Shirt", 50);
        ItemQuantityInOrder bagEntry = new ItemQuantityInOrder(item, 1, order);

        assertThrows(IllegalArgumentException.class, () -> order.addItem(bagEntry));
    }


    @Test
    void getSumPrice_ShouldCalculateTotalBasedOnBag() {
        Customer c = createCustomerWithMembership();
        Order order = new Order(c, DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.ACCEPTED);

        Item item1 = createItem("Item1", 100.0);
        Item item2 = createItem("Item2", 50.0);

        new ItemQuantityInOrder(item1, 2, order);
        new ItemQuantityInOrder(item2, 1, order);

        double sum = order.getSumPrice();

        assertEquals(250.0, sum);
    }

    @Test
    void getFinalPrice_ShouldApplyMembershipDiscount() {
        Customer c = createPremiumCustomer();
        Order order = new Order(c, DeliveryType.STORE_PICKUP, LocalDateTime.now(), OrderStatus.ACCEPTED);
        Item item = createItem("Item1", 100.0);
        new ItemQuantityInOrder(item, 1, order);

        double discountPercent = c.getMembershipTier().getDiscount();
        double expectedPrice = 100.0 * (100 - discountPercent) / 100.0;

        double finalPrice = order.getFinalPrice();

        assertEquals(expectedPrice, finalPrice);
    }

    @Test
    void getFinalPrice_ShouldAddDeliveryFeeForHomeDelivery() {
        Customer c = createCustomerWithMembership();
        Order order = new Order(c, DeliveryType.HOME_DELIVERY, LocalDateTime.now(), OrderStatus.ACCEPTED);
        Item item = createItem("Item1", 100.0);
        new ItemQuantityInOrder(item, 1, order);

        double expected = 105.0;

        double finalPrice = order.getFinalPrice();

        assertEquals(expected, finalPrice);
    }
}