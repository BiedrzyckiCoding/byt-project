import java.time.LocalDate;
import java.util.List;



public class Order {
    private OrderStatus status;
    private double discountApplied; // 0..1
    private double sumPrice;
    private LocalDate timestamp;
    private long paymentTimer;
    private DeliveryType deliveryType;
    private List<Reservation> reservations;

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
