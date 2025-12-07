package main.Order;

import main.Clothing.Item;
import main.PersistenceModels.Order;
import main.Utils.ValidationUtil;

import java.io.Serializable;

public class ItemQuantityInOrder implements Serializable {
    private Item item;
    private Order order;
    private int quantity;

    public ItemQuantityInOrder(Item item, int quantity, Order order) {
        ValidationUtil.notNull(item, "item");
        ValidationUtil.nonNegative(quantity, "quantity");

        this.item = item;
        this.order = order;
        this.quantity = quantity;

        order.addItemToList(this);
        item.addItemToList(this);
    }

    public void remove() {
        order.removeItemFromList(this);
        item.removeItemFromList(this);
    }

    public Item getItem() {
        return item;
    }

    public Order getOrder() {
        return order;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        ValidationUtil.nonNegative(quantity, "quantity");
        this.quantity = quantity;
    }
}
