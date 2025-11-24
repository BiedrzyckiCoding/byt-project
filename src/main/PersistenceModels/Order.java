package main.PersistenceModels;

import main.Enums.DeliveryType;
import main.Enums.OrderStatus;
import main.Order.ItemQuantityInOrder;
import main.Utils.ValidationUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static List<Order> extent = new ArrayList<>();

    private OrderStatus status;
    private boolean discountApplied;
    private LocalDate timestamp;
    private DeliveryType deliveryType;
    private Customer customer;
    private List<ItemQuantityInOrder> items;

    public Order(List<ItemQuantityInOrder> items, Customer customer, DeliveryType deliveryType,
                 LocalDate timestamp, OrderStatus status) {
        ValidationUtil.notNull(status, "status");
        ValidationUtil.notFuture(timestamp, "timestamp");
        ValidationUtil.notNull(deliveryType, "deliveryType");
        ValidationUtil.notNull(customer, "customer");
        ValidationUtil.notNull(items, "items");

        this.items = items;
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

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
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

    public void setCustomer(Customer customer) {
        ValidationUtil.notNull(customer, "customer");
        this.customer = customer;
    }

    public List<ItemQuantityInOrder> getItems() {
        return items;
    }

    public void setItems(List<ItemQuantityInOrder> items) {
        ValidationUtil.notNull(items, "items");
        this.items = items;
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
