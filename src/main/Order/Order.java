package main.Order;

import main.Enums.DeliveryType;
import main.Enums.OrderStatus;
import main.Person.Customer;
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
    private double discountApplied;
    private double sumPrice;
    private LocalDate timestamp;
    private long paymentTimer;
    private DeliveryType deliveryType;
    private double finalPrice;
    private Customer customer;
    private List<ItemQuantityInOrder> items;

    public Order(List<ItemQuantityInOrder> items, Customer customer, double finalPrice, DeliveryType deliveryType, long paymentTimer,
                 LocalDate timestamp, double sumPrice, double discountApplied, OrderStatus status) {
        ValidationUtil.notNull(status, "status");
        ValidationUtil.nonNegative(discountApplied, "discountApplied");
        ValidationUtil.nonNegative(sumPrice, "sumPrice");
        ValidationUtil.notFuture(timestamp, "timestamp");
        ValidationUtil.nonNegative(paymentTimer, "paymentTimer");
        ValidationUtil.notNull(deliveryType, "deliveryType");
        ValidationUtil.nonNegative(finalPrice, "finalPrice");
        ValidationUtil.notNull(customer, "customer");
        ValidationUtil.notNull(items, "items");

        this.items = items;
        this.customer = customer;
        this.finalPrice = sumPrice - (1 - discountApplied/100);
        this.deliveryType = deliveryType;
        this.paymentTimer = paymentTimer;
        this.timestamp = timestamp;
        this.sumPrice = sumPrice;
        this.discountApplied = discountApplied;
        this.status = status;

        addToExtent(this);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(double discountApplied) {
        this.discountApplied = discountApplied;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public long getPaymentTimer() {
        return paymentTimer;
    }

    public void setPaymentTimer(long paymentTimer) {
        this.paymentTimer = paymentTimer;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ItemQuantityInOrder> getItems() {
        return items;
    }

    public void setItems(List<ItemQuantityInOrder> items) {
        this.items = items;
    }

    private static void addToExtent(Order o) {
        if (o == null) throw new IllegalArgumentException("Order cannot be null");
        extent.add(o);
    }

    public static List<Order> getExtent() {
        return new ArrayList<>(extent);
    }

    public static void setExtent(List<Order> loaded) {
        extent = new ArrayList<>(loaded);
    }

    public void viewOrder() {
        /* TODO */ }

    public void finalizeOrder() {
        /* TODO */ }

    public void makePayment() {
        /* TODO */ }

    public void changeOrderStatus() {
        /* TODO */ }

    public void checkPendingOrders() {
        /* TODO */ }

    public void checkOrderMadeByCustomer() {
        /* TODO */ }

    public void checkOrderDeliveryType() {
        /* TODO */ }
}
