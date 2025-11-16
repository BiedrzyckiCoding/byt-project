package Order;

import Clothing.Item;
import Validation.ValidationUtil;

public class ItemQuantityInOrder {
    private Item item;
    private double quantity;

    public ItemQuantityInOrder(Item item, double quantity) {
        ValidationUtil.notNull(item, "item");
        ValidationUtil.nonNegative(quantity, "quantity");

        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
