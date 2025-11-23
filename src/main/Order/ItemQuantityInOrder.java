package main.Order;

import main.Clothing.Item;
import main.Utils.ValidationUtil;

import java.io.Serializable;

public class ItemQuantityInOrder implements Serializable {
    private Item item;
    private int quantity;

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
