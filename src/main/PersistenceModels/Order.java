package main.PersistenceModels;

import main.Enums.DeliveryType;
import main.Enums.OrderStatus;
import main.Order.ItemQuantityInOrder;
import main.Utils.ValidationUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<Order> extent = new ArrayList<>();

    private OrderStatus status;
    private boolean discountApplied;
    private LocalDateTime timestamp;
    private DeliveryType deliveryType;
    private Customer customer;
    private List<ItemQuantityInOrder> items = new ArrayList<>();

    public Order(Customer customer, DeliveryType deliveryType,
                 LocalDateTime timestamp, OrderStatus status) {
        ValidationUtil.notNull(status, "status");
        ValidationUtil.notFuture(timestamp, "timestamp");
        ValidationUtil.notNull(deliveryType, "deliveryType");
        ValidationUtil.notNull(customer, "customer");


        this.customer = customer;
        if (customer.getMembershipCard().getDateEnd() != null) {
            this.discountApplied = customer.getMembershipCard().getDateEnd().isAfter(LocalDate.now());
        } else this.discountApplied = false;
        this.deliveryType = deliveryType;
        this.timestamp = timestamp;
        this.status = status;

        addToExtent(this);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        ValidationUtil.notNull(status, "status");
        this.status = status;
    }

    public double getSumPrice() {
        double sum = 0;
        for (ItemQuantityInOrder item : items) {
            sum += item.getQuantity()*item.getItem().getPrice();
        }
        return sum;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        ValidationUtil.notFuture(timestamp, "timestamp");
        this.timestamp = timestamp;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        ValidationUtil.notNull(deliveryType, "deliveryType");
        this.deliveryType = deliveryType;
    }

    public double getFinalPrice() {
        double sumPrice = this.getSumPrice();
        double finalPrice = discountApplied ? sumPrice * (100 - customer.getMembershipTier().getDiscount()) / 100 : sumPrice;
        if (this.deliveryType == DeliveryType.HOME_DELIVERY) finalPrice += 5;
        return finalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    void setCustomer(Customer customer) {
        ValidationUtil.notNull(customer, "customer");
        this.customer = customer;
    }

    void removeCustomer() {
        this.customer = null;
    }

    public void setItems(List<ItemQuantityInOrder> items) {
        ValidationUtil.notNull(items, "items");
        this.items = items;
    }

    public void addItem(ItemQuantityInOrder item) {
        if (items.contains(item)) {
            throw new IllegalArgumentException("Item already exists for this order");
        }
        items.add(item);
    }

    public void removeItem(ItemQuantityInOrder item) {
        items.remove(item);
    }

    public List<ItemQuantityInOrder> getItems() {
        return new ArrayList<>(items);
    }

    private static void addToExtent(Order o) {
        if (o == null) throw new IllegalArgumentException("Order cannot be null");
        extent.add(o);
    }

    public static List<Order> getExtent() {
        return new ArrayList<>(extent);
    }

    static void setExtent(List<Order> loaded) {
        extent = new ArrayList<>(loaded);
    }

    public void viewOrder() {
        /* TODO */
    }

    public void finalizeOrder() {
        /* TODO */
    }

    public void makePayment() {
        /* TODO */
    }

    public void changeOrderStatus() {
        /* TODO */
    }

    public void checkPendingOrders() {
        /* TODO */
    }

    public void checkOrderMadeByCustomer() {
        /* TODO */
    }

    public void checkOrderDeliveryType() {
        /* TODO */
    }
}
