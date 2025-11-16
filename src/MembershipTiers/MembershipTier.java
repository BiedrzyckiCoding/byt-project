package MembershipTiers;
public abstract class MembershipTier {
    private final double discount; // percent
    private final double price;

    public MembershipTier(double discount, double price) {
        this.discount = discount;
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public double getPrice() {
        return price;
    }
}