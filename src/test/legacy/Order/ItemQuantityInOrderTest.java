//package test.legacy.Order;
//
//import main.Clothing.Item;
//import main.Order.ItemQuantityInOrder;
//import main.Utils.ValidationUtil;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//class ItemQuantityInOrderTest {
//
//    private Item createDummyItem() {
//        return new Item(
//                "TestItem",
//                "TestBrand",
//                100.0,
//                10,
//                List.of("Cotton"),
//                List.of("Red")
//        ) {};
//    }
//
//    @Test
//    void testValidConstruction() {
//        Item item = createDummyItem();
//        ItemQuantityInOrder iq = new ItemQuantityInOrder(item, 5);
//
//        Assertions.assertEquals(item, iq.getItem());
//        Assertions.assertEquals(5, iq.getQuantity());
//    }
//
//    @Test
//    void testNullItemThrowsException() {
//        IllegalArgumentException ex = Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> new ItemQuantityInOrder(null, 5)
//        );
//        Assertions.assertTrue(ex.getMessage().contains("item"));
//    }
//
//    @Test
//    void testNegativeQuantityThrowsException() {
//        Item item = createDummyItem();
//        IllegalArgumentException ex = Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> new ItemQuantityInOrder(item, -1)
//        );
//        Assertions.assertTrue(ex.getMessage().contains("quantity"));
//    }
//
//    @Test
//    void testSetItem() {
//        Item item1 = createDummyItem();
//        Item item2 = new Item("OtherItem", "Brand", 50, 5, List.of("Wool"), List.of("Blue")) {};
//        ItemQuantityInOrder iq = new ItemQuantityInOrder(item1, 2);
//
//        iq.setItem(item2);
//        Assertions.assertEquals(item2, iq.getItem());
//    }
//
//    @Test
//    void testSetQuantity() {
//        Item item = createDummyItem();
//        ItemQuantityInOrder iq = new ItemQuantityInOrder(item, 2);
//
//        iq.setQuantity(10);
//        Assertions.assertEquals(10, iq.getQuantity());
//    }
//}
