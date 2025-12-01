package test.PersistenceModels;

import main.PersistenceModels.Hoodie;
import main.Clothing.Item;
import main.Enums.ClothingSize;
import main.Order.ItemQuantityInOrder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Item sampleItem() {
        return new Hoodie("Hoodie1", "Nike", 100, 5,
                List.of("Cotton"), List.of("Black"), ClothingSize.M, true);
    }

    @Test
    void constructor_ShouldSetItemAndQuantity() {
        ItemQuantityInOrder iq = new ItemQuantityInOrder(sampleItem(), 3);
        assertEquals(sampleItem().getName(), iq.getItem().getName());
        assertEquals(3, iq.getQuantity());
    }

    @Test
    void constructor_ShouldRejectNullItem() {
        assertThrows(IllegalArgumentException.class, () -> new ItemQuantityInOrder(null, 2));
    }

    @Test
    void constructor_ShouldRejectNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> new ItemQuantityInOrder(sampleItem(), -1));
    }

    @Test
    void setItem_ShouldUpdateValue() {
        ItemQuantityInOrder iq = new ItemQuantityInOrder(sampleItem(), 3);

        Item newItem = new Hoodie("Hoodie2", "Adidas", 150, 5,
                List.of("Cotton"), List.of("Red"), ClothingSize.L, false);

        iq.setItem(newItem);

        assertEquals("Hoodie2", iq.getItem().getName());
    }

    @Test
    void setItem_ShouldRejectNull() {
        ItemQuantityInOrder iq = new ItemQuantityInOrder(sampleItem(), 3);

        assertThrows(IllegalArgumentException.class, () -> iq.setItem(null));
    }

    @Test
    void setQuantity_ShouldUpdateValue() {
        ItemQuantityInOrder iq = new ItemQuantityInOrder(sampleItem(), 3);

        iq.setQuantity(5);

        assertEquals(5, iq.getQuantity());
    }

    @Test
    void setQuantity_ShouldRejectNegative() {
        ItemQuantityInOrder iq = new ItemQuantityInOrder(sampleItem(), 3);

        assertThrows(IllegalArgumentException.class, () -> iq.setQuantity(-2));
    }
}
